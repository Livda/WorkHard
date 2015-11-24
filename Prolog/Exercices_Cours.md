%Exercices du cours

#Cours 1

##Petits exos d'application directe
Est-ce qu'une personne est père de Luc ?
```prolog
?-pere(P,luc).
```

Est-ce qu'il existe des personnes liées par des liens de paternité dans la base
de faits ?
```prolog
?-pere(P,F).
```

Est ce que Luc est le père de quelqu'un ?
```prolog
?-pere(luc,X).
```

Est ce que quelqu'un est le père à la fois de paul et de luc ?
```prolog
?-pere(P,luc),pere(P,paul).
```

Jean est-il père d'au moins deux enfants ?
```prolog
?-pere(jean,F1),pere(jean,F2),\==(F1,F2).
```

##Diapo c1-39
1. Existe-t-il une relation de alpha vers beta ?
```prolog
?-rel(alpha,beta).
```

2. Existe-t-il une relation symétrique entre alpha et beta ?
```prolog
?-rel(alpha,beta),rel(beta,alpha).
```

3. Objets en relation avec alpha ?
```prolog
?-rel(X,alpha).
```

4. Objets en relation symétrique avec alpha ?
```prolog
?-rel(X,alpha),rel(alpha,X),\==(X,alpha).
```

5. Objets en relation à la fois avec alpha et beta ?
```prolog
?-rel(X,alpha),rel(X,beta).
```

6. Ensemble des couples en relation symétrique ?
```prolog
?-rel(X,Y),rel(Y,X),\==(X,Y).
```

7. Ensemble des objets pour lesquels il existe une relation réflexive ?
```prolog
?-rel(X,X).
```

8. Ensemble des triplets pour lesquels la relation est transitive ?
```prolog
?-rel(X,Y),rel(Y,Z),rel(X,Z),\==(X,Y),\==(Y,Z),\==(X,Z).
```

##Diapo c1-49
```prolog
ff(gg(Y, hh(Z,Y)), X).
```
et
```prolog
ff(X, gg(hh(hh(Z,Z)), hh(aa, T))).
```

```prolog
X = gg(Y, hh(Z, Y))
X = gg(hh(hh(Z, Z)), hh(aa, T))
```

```prolog
Y = hh(hh(Z,Z))
Z = aa      T = Y
```

```prolog
Z = aa      Y = hh(hh(aa, aa))
T = hh(hh(aa, aa))
X = gg(hh(hh(aa, aa)), hh(aa, hh(hh(aa, aa))))
```
Ça fonctionne !

##Diapo c1-57
```prolog
?-somme(zero, quatre, X).       ?-somme(zero, quatre, trois).
X = quatre                      No

?-somme(zero, X, quatre).       ?-somme(zero, bonjour, X).
X = quatre                      X = bonjour

?-somme(Y, quatre, quatre).     ?-somme(X, zero, X).
Y = zero                        X = zero

?-somme(X, quatre, Y).
X = zero    Y = quatre
```

##Diapo c1-60
Définir `est-pere` à partir de `pere`.
```prolog
est-pere(X) :- pere(X, _).
```

Définir `est-grand-pere` à partir de `pere`.
```prolog
est-grand-pere(X,Y) :- pere(X, Z),pere(Z, Y).
```

##Diapo c1-62
Définir la relation `est-parent-de`.
```prolog
parent(X, Y) :- pere(X, Y).
parent(X, Y) :- mere(X, Y).
```
ou (mais c'est dur de voir si il y a une erreur)
```prolog
parent(X, Y) :- pere(X, Y) ; mere(X, Y).
```
#Cours 2
##Diapo c1-72
Dessiner l'arbre de recherche de recherche de `?-grand-pere(X,Y).`

```prolog
?-grand-pere(X, Y) :- pere(X,Z),pere(Z,Y).

                      grand-pere(X,Y)
                            |
                     pere(X,Z),pere(Z,Y)
        /                 |                   |                 \
{X=jean, Z=paul}    {X=jean, Z=luc}  {X=jean, Z=gaston}  {x=paul, Z=pierre}
pere(paul, Y)       pere(luc, Y)     pere(gaston, Y)     pere(pierre,Y)
    |                   |                  |                    |
   vide               echec              echec                echec
```

##Diapo c2-5

Écrire les questions et les réponses obtenues.
1. Bar aux algues est-il un hors d’œuvre ?
```prolog
?-hors_d_oeuvre(bar_au_algues).
No
```

2. Qu'y a-t-il comme hors d'oeuvre dans la base ?
```prolog
?-hors_d_oeuvre(X).
X = artichauts_Melanie
X = truffes_sous_le_sel
X = cresson_oeuf_poche
```

##Diapo c2-7
Définir le prédicat plat_resistance/1 à partir de viande et poisson.
```prolog
plat_resistance(P):- viande(P) ; poisson(P).
```
ou
```prolog
plat_resistance(P):- viande(P).
plat_resistance(P):- poisson(P).
```

##Diapo c2-9
Pour composer un repas, on doit choisir un hors-d'oeuvre, un plat de resistance
et un dessert. Ecrire le prédicat repas/3 définissant un repas.
```prolog
repas(H, P, D) :-
    hors_d_oeuvre(H),
    plat_resistance(P),
    dessert(D).
```

##Diapo c2-12
On voudrait afficher plutôt un repas comme un tout, donc obtenir un terme
composé tel que ci-dessous quand on pose `?-repas(R).``:
```prolog
R = rep(artichauts_Melanie, grillade_de_boeuf, sorbet_aux_poires)
R = rep(artichauts_Melanie, grillade_de_boeuf, fraises_chantilly)
...
```
Modifier le prédicat repas/3 en repas/1 pour qu'il produise ce type de réponse.
```prolog
repas(rep(H, P, D)) :-
    hors_d_oeuvre(H),
    plat_resistance(P),
    dessert(D).
```

##Diapo c2-14
Que faut-il changer pour obtenir les solutions sous cette forme :
```prolog
R = rep(entree(artichauts_Melanie),
        plat(grillade_de_boeuf),
        dessert(sorbet_aux_poires))
R = rep(entree(artichauts_Melanie),
        plat(grillade_de_boeuf),
        dessert(fraises_chantilly))
...
```

Réponse :
```prolog
repas(rep(entree(H), plat(P), dessert(D))):-
    hors_d_oeuvre(H),
    plat_resistance(P),
    dessert(D).
```

##Diapo c2-16
Question pour être certain que le repas va contenir du poisson,
sachant que l'on dispose de la base de connaissance précédente
(avec la version repas/3) ?
```prolog
?-repas(H, P, D), poisson(P).
?-poisson(P), repas(H, P, D).
```
La deuxième solution est bien plus efficace au niveau de la recherche
de solution.

Dans le premier cas il y a `nbHorsOeuvre * nbViande * nbDessert` cas
d'échecs et  `nbHorsOeuvre * nbPoisson * nbDessert` cas de succès.

Dans le deuxième il y a `nbHorsOeuvre * nbPoisson * nbDessert` cas de succès
et `nbPlatRes * nbHorsOeuvre * nbPoisson` cas d'échecs.

##Diapo c2-21
Base de faits :
```prolog
route(rennes, nantes).
route(rennes, le_mans).
...
```

Ecrire le prédicat chemin/2 définissant la fermeture transitive de route.

```prolog
chemin(X, Y) :-
    route(X, Y).
chemin(X, Y) :-
    route(X, Z),
    chemin(Z, Y).
```

#Cours 3
##Diapo c2-23
`?-chemin(rennes, lyon).` produit 3 `Yes`.

Que se passe-t-il si l'on croise l'ordre de littéraux dans la seconde clause
(1^^re^^ clause inchangée) ?
Soit :
```prolog
chemin(X, Y) :-
    route(X, Y).
chemin(X, Y) :-
    chemin(Z, Y).
    route(X, Z),
```

On a trois solutions, puis on boucle.

Pour le comprendre, dessiner l'arbre de recherche de `?-chemin(rennes, lyon).`

Version de chemin 1 :

```prolog
                ?-chemin(rennes, lyon).
        /                                       \
    X = rennes                                X1 = rennes
    Y = lyon                                  Y1 = lyon
        |                                        |
route(rennes, lyon)               route(rennes, Z1), chemin(Z1, lyon)
        |                               /                   \
      echec                      Z1 = le_mans            Z1 = nantes
                                     |                      |
                             route(rennes, le_mans)   route(rennes, nantes)
                                     |                      |
                             chemin(le_mans, lyon)    chemin(nantes, lyon)
                                     |                      |
                                    ...                    ...
```
\newpage
Version de chemin 2 :
```prolog
            ?-chemin(rennes, lyon).
        /                            \
    X = rennes                     X1 = rennes
    Y = lyon                       Y1 = lyon
        |                             |
route(rennes, lyon)          route(rennes, Z1), chemin(Z1, lyon)
        |                    /                             \
      echec           X2 = rennes                        X3 = rennes
                      Y2 = Z1                            Y3 = Z1
                         |                                  |
        route(rennes, Z1), route(Z1, lyon)            chemin(rennes, Z3),
             /                      \                 route(Z3, Z1),
      Z1 = le_mans              Z1 = nantes           route(Z1, lyon)
           |                        |                       |
   route(le_mans, lyon)      route(nantes, lyon)      route(rennes, Z3),
           |                        |                 routes(Z3, Z1),
         echec                    echec               route(Z1, lyon)
                                                  /                     \
                                               Z3 = le_mans             ...
                                                  |
                                route(le_mans, Z1), route(Z1, lyon)
                                                  |
                                               Z1 = paris
                                                  |
                                            route(paris, lyon)
                                                  |
                                                vide
```

##Diapo c2-28
Écrire le prédicat `trajet(X, Y, T)` où `T` est un terme construit à l'aide de
la constante symbolique `rien` et du symbole fonctionnel `par`, indiquant les
villes traversées entre `X` et `Y`.

```prolog
trajet(X, Y, rien) :-
    route(X, Y).
trajet(X, Y, par(Z, T)) :-
    route(X, Z), trajet(Z, Y, T).
```
##Diapo c3-8
`X` appartient-t-il ou pas à `L` ?

```prolog
membre(X, [X|Y]).
membre(X, [A|Y]) :- membre(X, Y).
```
Quid des cas d'échec ?

- Non codés
- Exemple : lister les éléments d'une liste vide
