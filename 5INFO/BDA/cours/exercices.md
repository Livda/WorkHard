---
title: Exercices de cours
author: Aurélien Fontaine
date: 5-INFO
geometry: margin=3cm
---

#Tuning BDD
## Exercices sur des Employés

~~~~ {.SQL}
Emp ( num_secu, nom, sal, dept, boss )
~~~~

1 page = 2Ko ; 30 employés / page ;

###Une requête est lente

`select * from Emp where nom='xxx';` Index secondaire hashé sur ``nom``.

####Quel est le problème ?

Il y a beaucoup de personnes avec le même nom. Les statistiques ne sont plus à jours ce qui pose des problèmes de mémoire et de parcours de tables.

####Que faire pour le résoudre ?

- Remettre les statistiques à jours
- Nettoyer / Recréer les index
    - avoir des requêtes étalons pour connaitre les temps d'exécution "normaux"

###Une requête sur deux trop lente
`select * from Emp where sal = 24000;` $\rightarrow$ rapide

`select * from Emp where sal = 30000;` $\rightarrow$ lente

- On remet à jour les statistiques.
- On regarde le plan d'exécution de la requête
    - modifier l'exécution prévue pour exécuter la requête différemment.

###Trouver les employés d'un département
####20 valeurs de département

On ne pose pas d'index secondaire car il y a 1,5 employé par page d'un département par page. En supposant qu'il y a une répartition uniforme.

####5000 valeurs de département

Ici, on en met un.

###Met on un index sur une de ces requêtes ?

~~~~ {.SQL}
select count(*) from Emp where sal = 'xxx';
~~~~

- index dense secondaire hash
    - secondaire : salaire non fixe
    - dense : pour compter dans l'index
    - hash : on veut une valeur précise

~~~~ {.SQL}
select * from Emp where sal = 'xxx';
~~~~

Ici on doit accéder aux données, on ne peut pas rester dans un index. On regarde la sélectivité de la requête :

- si forte : NON
    - sauf si on peut se permettre un primaire
- sinon : OUI
    - index secondaire dense hash
        - idem précédent

## Exercices sur des compagnies aériennes

1000 vols / jour ; 1 table / vol ; 1 table total ;

###Tables

~~~~ {.SQL}
Vol (vol.id, siege.id, #passager)
~~~~

SGBD 2PL granule ligne par page

~~~~ {.SQL}
Total (vol.id, totalPassagers)
~~~~

###Questions

__Problèmes de performances à des contentions de verrous sur Total.__

_Pourquoi ?_

Pas d'index sur la table `Total`, comme on est en 2PL, on passe séquentiellement sur toute la table et on la verrouille intégralement.

_Solutions_

Ajout index dense hash primaire ou secondaire sur le `vol.id`.

## Exercices sur Employés (bis)

~~~~ {.SQL}
Emp (num_secu, nom, dept , salaire)
ServiceARisque (service, boss, #tel)
~~~~

Emp :

- __primaire__ sur `nss` non dense
- __secondaire__ sur `dept`
- __secondaire__ sur `salaire`
- __secondaire__ sur `dept, salaire`
- `nss` clef
- `nom` clef

SAR :

- __primaire__ sur `service` non dense
- `service` clef

##Requête lente

`SELECT DISTINCT nss FROM Emp WHERE dept='X';`

Le `DISTINCT` est inutile et contre-productif.

##Numéro des employés travaillant dans un service à risque

~~~~ {.SQL .numberLines}
SELECT nss
FROM Emp, ServiceARisque
WHERE Emp.dept = ServiceARisque.service;
~~~~

On sait que `Emp` >> `ServiceARisque`.

1. Scan sequentiel de `ServiceARisque`
2. Si sélectivité élevée
    - Scan sequentiel de `Emp`
3. Si sélectivité faible
    - recherche des `Emp` correspondants via l'index secondaire

$\rightarrow$ plusieurs lectures de `Emp`

1. Scan séquentiel de `Emp`
2. Vérification de présence dans `ServiceARisque` via l'index primaire

$\rightarrow$ une seule lecture de `Emp` et des lectures d'index. Beaucoup plus rapide.

~~~~ {.SQL .numberLines}
SELECT nss
FROM Emp
WHERE dept in
    (SELECT service
     FROM ServiceARisque);
~~~~

Si l'optimiseur est bien fait, il va faire comme au dessus.

##Afficher les employés les mieux payés de chaque département

~~~~ {.SQL .numberLines}
SELECT nss
FROM Emp as e1
WHERE salaire =
    (SELECT max(salaire)
     FROM Emp as e2
     WHERE e2.dept = e1.dept);
~~~~

Du fait de la référence `e1` dans la sous-requête, on lit la requête de gauche à droite.
Créer une vue concrète (`CONCRETE VIEW`) avec le salaire max de chaque `dept`.

\newpage

#XML Schema

~~~~ {.xml .numberLines}
<xsd:schema xmlns:xsd="http://www.w3.org/2000/10/XMLSchema">
    <xsd:element name="degustation">
    <xsd:complexType>
        <xsd:sequence>
            <xsd:element name="vin" maxOccurs="unbounded">
            <xsd:complexType>
                <xsd:sequence>
                    <xsd:element name="cru" type="xsd:string"></xsd:element>
                    <xsd:element name="mil" type="xsd:string" minOccurs="0"></xsd:element>
                    <xsd:element name="degre" type="xsd:float"></xsd:element>
                    <xsd:element name="region">
                    <xsd:complexType>
                        <xsd:sequence>
                            <xsd:element name="nom" type="xsd:string"></xsd:element>
                            <xsd:element name="ville" type="xsd:string"></xsd:element>
                            <xsd:choice>
                                <xsd:element name="pays" type="xsd:string"></xsd:element>
                                <xsd:element name="etat" type="xsd:string"></xsd:element>
                            </xsd:choice>
                        </xsd:sequence>
                    </xsd:complexType>
                    </xsd:element>
                </xsd:sequence>
            <xsd:attribute name="id" type="xsd:byte" use="required"></xsd:attribute>
            </xsd:complexType>
            </xsd:element>
        </xsd:sequence>
    <xsd:attribute name="version" type="xsd:decimal" use="required"></xsd:attribute>
    <xsd:attribute name="date" type="xsd:date" use="required"></xsd:attribute>
    </xsd:complexType>
    </xsd:element>
</xsd:schema>
~~~~

#XPath

##Vineyard of wines
~~~~ {.xpath}
/child::degustation/child::vins/child::cru
~~~~

##Text of the name of the second wine region
~~~~ {.xpath .numberLines}
/child::degustation/
    child::vins[position()=2]/child::region/
    child::nom/child::text()
~~~~

##Attributes of the wines
~~~~ {.xpath}
/descendant::vins/attributes::id
~~~~

##Wine vineyards in regions with states
~~~~ {.xpath}
/descendant::vins[descendant::etat]/child::cru
~~~~

##Sections of a chapter
~~~~ {.xpath}
/livre/chapitre/section
~~~~

##Text of a chapter 1 section 2
~~~~ {.xpath}
//chapitre[1]/section[2]/text()
~~~~

##Caption of the first figure in the second section of the third chapter
~~~~ {.xpath}
//chapitre[3]/section[2]//figure[1]/légende
~~~~

#XQuery

##Names of the restaurants
~~~~{.xpath}
collection("Guide")/Restaurant/Nom
~~~~

##Text of the names of the restaurants
~~~~{.xpath}
collection("Guide")/Restaurant/Nom/text()
~~~~

##Menus of all the restaurants
~~~~{.xpath}
collection("Guide")//Menu
~~~~

##Values of the attributes in the menus of all the restaurants
~~~~{.xpath}
collection("Guide")//Menu/@*
or
collection("Guide")//Menu/@*/string
~~~~

##Restaurants in Cabourg
~~~~{.xpath}
collection("Guide")/Restaurant/Adresse[Ville="Cabourg"]
or
collection("Guide")/Restaurant/Adresse[Ville/text()="Cabourg"]
~~~~

##Addresses of the restaurants in Cabourg
~~~~{.xpath}
collection("Guide")/Restaurant/Adresse[Ville="Cabourg"]/Adresse
~~~~
~~~~{.xquery}
for $R in collection("Guide")/
    Restaurant[Adresse/Ville="Cabourg"]
return $R/Adresse
~~~~

##Names of the hotels, and names and phone numbers of the restaurants located in te same street
~~~~{.xquery}
for
    $R in collection("Guide")/Restaurant,
    $H in collection("Repertoire")/Hotel
where
    $H/Adresse/Rue=$R/Adresse/Rue
return
    <HotelRestau>
        {$H/Nom}
        {$R/Nom}
        {$R/Téléphone}
    </HotelRestau>
~~~~

##List of the restaurants sorted by towns
~~~~{.xquery}
for
    $V in distinct-values(collection("Guide")/Restaurant//Ville)
return
    <Restoparville>
        <Ville>{$V}</Ville> #Remark: no $V/text() because distinct-values
        <Restos>
            {for $R in collection("Guide")/Restaurant
            where $R//Ville=$V
            return $R/Nom}
        </Restos>
    </Restoparville>
~~~~

##Names of the restaurants together with the average price of the proposed menus
~~~~{.xquery}
for $R in collection("Guide")/Restaurant
let $A:=$R/Menu/@Prix
return
    <resultat>
        {$R/Nom}
        <avgprix>{avg($A)}</avgprix>
    </resultat>
~~~~

