---
title: Intelligence Artificielle
author: Aurélien Fontaine
date: 5-INFO
geometry: margin=2.5cm
---

#Jeux de taquin

__États :__ Les cases du taquin
__Actions :__ bouger le blanc en haut / bas / gauche / droite
__Cout :__ 1 par mouvement

Distance de Manhattan -> on ignore les pièces "gênantes"

#Exercice de voyageur de commerce

Individus :

- 0 2 3 __5 6 4__ 7 8 1
- 1 2 8 __7 3 4__ 6 0 5

On essaie d'inverser les positions 3 à 5.

- 0 2 __5__ _7 3 4_ __6__ 8 1
- 1 2 8 _5 6 4_ __7__ 0 __3__
