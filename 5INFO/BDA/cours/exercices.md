---
title: Exercices de cours
author: Aurélien Fontaine
date: 5-INFO
geometry: margin=3cm
---

#Tuning BDD
## Exercices sur des Employés

``Emp ( num_secu, nom, sal, dept, boss )``

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

`select count(*) from Emp where sal = 'xxx';`

- index dense secondaire hash
    - secondaire : salaire non fixe
    - dense : pour compter dans l'index
    - hash : on veut une valeur précise

`select * from Emp where sal = 'xxx';`

Ici on doit accéder aux données, on ne peut pas rester dans un index. On regarde la sélectivité de la requête :

- si forte : NON
    - sauf si on peut se permettre un primaire
- sinon : OUI
    - index secondaire dense hash
        - idem précédent

## Exercices sur des compagnies aériennes

1000 vols / jour ; 1 table / vol ; 1 table total ;

###Tables

``Vol (vol.id, siege.id, #passager)``

SGBD 2PL granule ligne par page

``Total (vol.id, totalPassagers)``

###Questions

__Problèmes de performances à des contentions de verrous sur Total.__

_Pourquoi ?_

Pas d'index sur la table `Total`, comme on est en 2PL, on passe séquentiellement sur toute la table et on la verrouille intégralement.

_Solutions_

Ajout index dense hash primaire ou secondaire sur le `vol.id`.

## Exercices sur Employés (bis)

``Emp (num_secu, nom, dept , salaire)``

``ServiceARisque (service, boss, #tel)``

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

\newpage

#XML Schema

~~~~ {#mycode .xml .numberLines}
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
~~~~ {#mycode .xpath .numberLines}
/child::degustation/child::vins/child::cru
~~~~

##Text of the name of the second wine region
~~~~ {#mycode .xpath .numberLines}
/child::degustation/
    child::vins[position()=2]/child::region/
    child::nom/child::text()
~~~~

##Attributes of the wines
~~~~ {#mycode .xpath .numberLines}
/descendant::vins/attributes::id
~~~~

##Wine vineyards in regions with states
~~~~ {#mycode .xpath .numberLines}
/descendant::vins[descendant::etat]/child::cru
~~~~

##Sections of a chapter
~~~~ {#mycode .xpath .numberLines}
/livre/chapitre/section
~~~~

##Text of a chapter 1 section 2
~~~~ {#mycode .xpath .numberLines}
//chapitre[1]/section[2]/text()
~~~~

##Caption of the first figure in the second section of the third chapter
~~~~ {#mycode .xpath .numberLines}
//chapitre[3]/section[2]//figure[1]/légende
~~~~
