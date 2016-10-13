---
title: Exercices de cours
author: Aurélien Fontaine
date: 5-INFO
geometry: margin=3cm
---

# La table

``Emp ( num_secu, nom, sal, dept, boss )``


##Une requête est lente

`select * from Emp where nom='xxx';` Index secondaire hashé sur ``nom``.

###Quel est le problème ?

Il y a beaucoup de personnes avec le même nom. Les statistiques ne sont plus à jours ce qui pose des problèmes de mémoire et de parcours de tables.

###Que faire pour le résoudre ?

- Remettre les statistiques à jours
- Nettoyer / Recréer les index
    - avoir des requêtes étalons pour connaitre les temps d'exécution "normaux"

##Une requête sur deux trop lente
`select * from Emp where sal = 24000;` $\rightarrow$ rapide

`select * from Emp where sal = 30000;` $\rightarrow$ lente

- On remet à jour les statistiques.
- On regarde le plan d'exécution de la requête
    - modifier l'exécution prévue pour exécuter la requête différemment.
