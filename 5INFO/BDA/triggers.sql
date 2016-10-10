/* =============================================================================
                            Les triggers
============================================================================= */

/* ---------------------------------
            Question a
------------------------------------ */
CREATE OR REPLACE FUNCTION ForbidUpdate() RETURNS TRIGGER AS $ForbidUpdate$
	BEGIN
		IF (NEW.noLivraison <> OLD.noLivraison)
			OR (NEW.noCommande <> OLD.noCommande)
			OR (NEW.noArticle <> OLD.noArticle)
		THEN
			RAISE NOTICE 'Opération interdite. Réfléchis à ce que tu fais !!!';
			RETURN OLD;
		ELSE
			RETURN NEW;
		END IF;
	END;
$ForbidUpdate$ LANGUAGE plpgsql;
/*
CREATE TRIGGER ForbidUpdate
BEFORE UPDATE ON DetailLivraison
FOR EACH ROW
EXECUTE PROCEDURE ForbidUpdate();
*/

/* ---------------------------------
            Question b
------------------------------------ */
CREATE OR REPLACE FUNCTION AjustInsert() RETURNS TRIGGER AS $AjustInsert$
	DECLARE
		quantite Article.quantiteEnStock%Type;
		no Article.noArticle%Type := NEW.noArticle;
	BEGIN
		SELECT quantiteEnStock INTO quantite
		FROM Article
		WHERE noArticle = no;
		UPDATE Article
		SET quantiteEnStock = (quantite + NEW.quantiteLivree)
		WHERE noArticle = no;
		RETURN NEW;
	END;
$AjustInsert$ LANGUAGE plpgsql;
/*
CREATE TRIGGER AjustInsert
BEFORE INSERT ON DetailLivraison
FOR EACH ROW
EXECUTE PROCEDURE AjustInsert();
*/

/* ---------------------------------
            Question c
------------------------------------ */
CREATE OR REPLACE FUNCTION ManageUp() RETURNS TRIGGER AS $ManageUp$
	DECLARE
		price Article.prixUnitaire%TYPE := OLD.prixUnitaire*1.1;
	BEGIN
		IF (NEW.prixUnitaire > price)
		THEN
			NEW.prixUnitaire := price;
		END IF;
		RETURN NEW;
	END;
$ManageUp$ LANGUAGE plpgsql;
/*
CREATE TRIGGER ManageUp
BEFORE UPDATE ON Article
FOR EACH ROW
EXECUTE PROCEDURE ManageUp();
*/

/* ---------------------------------
            Question d
------------------------------------ */
/*
CREATE OR REPLACE FUNCTION ControlCommand() RETURNS TRIGGER AS $ControlCommand$
	DECLARE
		qty LigneCommande.quantite%TYPE;
		livree DetailLivraison.quantiteLivree%TYPE := NEW.quantiteLivree;
	BEGIN
		SELECT quantite into qty
		FROM LigneCommande
		WHERE noCommande = NEW.noCommande
			AND no.Article = NEW.noArticle;
		IF livree > qty
		THEN
			RAISE NOTICE 'Tu as trop de choses dans ta livraison ! Recompte.';
			RETURN NULL;
		ELSE
			RETURN NEW;
		END IF;
	END;
$ControlCommand$ LANGUAGE plpgsql;

CREATE TRIGGER ControlCommand
BEFORE INSERT ON DetailLivraison
FOR EACH ROW
EXECUTE PROCEDURE ControlCommand();
*/

/* ---------------------------------
            Question e
------------------------------------ */
CREATE OR REPLACE FUNCTION ControlCommand() RETURNS TRIGGER AS $ControlCommand$
	DECLARE
		qty LigneCommande.quantite%TYPE;
		livree DetailLivraison.quantiteLivree%TYPE := NEW.quantiteLivree;
	BEGIN
		SELECT quantite into qty
		FROM LigneCommande
		WHERE noCommande = NEW.noCommande
			AND no.Article = NEW.noArticle;
		IF livree > qty
		THEN
			IF (TG_OP = 'INSERT')
			THEN
				RAISE NOTICE
                    'Tu as trop de choses dans ta livraison ! Recompte.';
				RETURN NULL;
			ELSE
				RAISE NOTICE
                    'Tu as trop de choses dans ta livraison !
                    J''ai pas changé les valeurs.';
        RETURN OLD;
			END IF;
		ELSE
			RETURN NEW;
		END IF;
	END;
$ControlCommand$ LANGUAGE plpgsql;

CREATE TRIGGER ControlCommand
BEFORE INSERT OR UPDATE ON DetailLivraison
FOR EACH ROW
EXECUTE PROCEDURE ControlCommand();

/* ---------------------------------
            Question f
------------------------------------ */
CREATE OR REPLACE FUNCTION BeforeDelete() RETURNS TRIGGER AS $BeforeDelete$
    DECLARE
        client_no Client.noClient%TYPE := OLD.noClient;
        montant Article.prixUnitaire%TYPE := 0;
        test INTEGER;
    BEGIN
        SELECT count(*) into test
        FROM Commande as c, LigneCommande as l
        WHERE c.noCommande = l.noCommande
            AND c.noClient = client_no;
        IF (test > 0)
        THEN
            SELECT sum(montant) into montant
            FROM Commande as c, LigneCommande as l
            WHERE c.noCommande = l.noCommande
                AND c.noClient = client_no;
        END IF;
        /* A voir pour ne pas créer une table à chaque fois */
        CREATE TABLE Archive
        (
            noClient INTEGER NOT NULL,
            montanttotal INTEGER NOT NULL
        );
        INSERT INTO Archive
        VALUES (client_no, montant);
        RETURN NEW;
    END;
$BeforeDelete$ as LANGUAGE plpgsql;

CREATE TRIGGER BeforeDelete
BEFORE DELETE ON Client
FOR EACH ROW
EXECUTE PROCEDURE BeforeDelete();

/* =============================================================================
                    Les Contraintes d'intégrité structurelles
============================================================================= */

/* ---------------------------------
        Question a, b et c
------------------------------------ */
CREATE TABLE Client
(noClient INTEGER PRIMARY KEY NOT NULL,
 nomClient VARCHAR(20) NOT NULL,
 noTelephone VARCHAR(15) NOT NULL
);

CREATE TABLE Article
(noArticle INTEGER PRIMARY KEY NOT NULL,
 description VARCHAR(20),
 prixUnitaire DECIMAL(10,2) NOT NULL,
 quantiteEnStock INTEGER DEFAULT 0 NOT NULL,
 CHECK quantiteEnStock > 0
);

CREATE TABLE Commande
(noCommande INTEGER PRIMARY KEY NOT NULL,
 dateCommande VARCHAR NOT NULL,
 noClient INTEGER NOT NULL,
 FOREIGN KEY (noClient) REFERENCES Client(noClient) ON DELETE CASCADE
);

CREATE TABLE LigneCommande
(noCommande INTEGER NOT NULL,
 noArticle INTEGER NOT NULL,
 quantite INTEGER NOT NULL,
 montant DECIMAL(10,2) NOT NULL,
 PRIMARY KEY (noCommande, noArticle),
 FOREIGN KEY (noCommande) REFERENCES Commande(noCommande) ON DELETE CASCADE,
 FOREIGN KEY (noArticle) REFERENCES Article(noArticle),
 CHECK quantite > 0
);

CREATE TABLE Livraison
(noLivraison INTEGER PRIMARY KEY NOT NULL,
 dateLivraison VARCHAR NOT NULL
);

CREATE TABLE DetailLivraison
(noLivraison INTEGER NOT NULL,
 noCommande INTEGER NOT NULL,
 noArticle INTEGER NOT NULL,
 quantiteLivree INTEGER NOT NULL,
 PRIMARY KEY (noLivraison, noCommande, noArticle),
 FOREIGN KEY (noLivraison) REFERENCES Livraison(noLivraison),
 FOREIGN KEY (noCommande) REFERENCES Commande(noCommande) ON DELETE CASCADE,
 FOREIGN KEY (noArticle) REFERENCES Article(noArticle),
 CHECK quantiteLivree > 0
);

/* ---------------------------------
            Question c
------------------------------------ */
DELETE FROM Client
WHERE noClient = 30;

/* ---------------------------------
            Question d
------------------------------------ */
CREATE OR REPLACE FUNCTION MaxQty() RETURNS TRIGGER AS $MaxQty$
    BEGIN
        IF (NEW.noArticle > 10000 AND NEW.quantite > 5)
        THEN
            NEW.quantite := 5;
        END IF;
        RETURN NEW;
    END;
$MaxQty$ as LANGUAGE plpgsql;

CREATE TRIGGER MaxQty
BEFORE INSERT OR UPDATE ON LigneCommande
FOR EACH ROW
EXECUTE PROCEDURE MaxQty();

/* =============================================================================
                            Fin du TP
============================================================================= */

DROP TABLE Archive;
DROP TABLE DetailLivraison;
DROP TABLE Livraison;
DROP TABLE LigneCommande;
DROP TABLE Commande;
DROP TABLE Article;
DROP TABLE Client;
