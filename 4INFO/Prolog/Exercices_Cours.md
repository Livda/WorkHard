---
title: Exercices de cours - Prolog
author: Aurélien Fontaine
date: 2015
geometry: margin=3cm
header-includes:
    - \usepackage{tikz-qtree}
---
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

\begin{tikzpicture}
\tikzset{every tree node/.style={align=center,anchor=north}}
\Tree [
        .grand-pere(X,Y)
        [ .pere(X,Z),pere(Z,Y)
          [ .{X=jean\\Z=paul} [ .pere(paul,Y) vide ] ]
          [ .{X=jean\\Z=luc} [ .pere(luc,Y) echec ] ]
          [ .{X=jean\\Z=gaston} [ .pere(gaston,Y) echec ] ]
          [ .{X=jean\\Z=pierre} [ .pere(pierre,Y) echec ] ]
        ]
      ]
\end{tikzpicture}

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
et un dessert. Écrire le prédicat repas/3 définissant un repas.
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

Écrire le prédicat chemin/2 définissant la fermeture transitive de route.

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

#Cours 4
##Diapo c3-15
En mode (-,+).
Rappel :
```prolog
membre(X, [X|Y]).
membre(X, [A|Y]) :- membre(X, Y).
```

Arbre de recherche de `?- membre(X, [10, 20]).`

```prolog
  membre(X, [10, 20])
    /             \
X1 = X         X2 = X
X1 = 10        A2 = 10
Y1 = [20]      Y2 = [20]
   |              |
  vide        membre(X, [20])
                  |
               X3 = X
               X3 = 20
               Y3 = []
                  |
                vide
```

##Diapo c3-17

En mode (+,-).

Arbre de recherche de `?- membre(10, L).`

```prolog
       membre(10, L)
    /                \
X1 = 10             X2 = 10
L = [X1|Y1]        L = [A2|Y2]
  = [10|Y1]          = [_|Y2]
    |                |
   vide          membre(10,Y2)
               /              \
           X3 = 10         X4 = 10
           Y2 = [X3|Y3]    Y2 = [A4|Y4]
              = [10|Y3]       = [_|Y4]
              |               |
             vide          membre(10,Y4)
                           /          \
                       X5 = 10        ...
                       Y4 = [X5|Y5]
                          = [10|Y5]
                          |
                         vide
```

##Diapo c3-19
`[1,2,3,4,5]` : construire la liste formée des éléments de rang impair : `[1,3,5]`

Écrire le prédicat `elem_impair/2` en mode (+,-).

```prolog
elem_impar([], []).
elem_impair([X1], [X1]).
elem_impair([X1,X2|L], [X1| Reste]) :-
  elem_impair(L, Reste).
```

##Diapo c3-22
Construire l'arbre de recherche de `?- elem_impair([1,2,3], S).`

```prolog
elem_impair([1,2,3], S)
        |
    X11 = 1
    X21 = 2
    L = [3]
    S = [1 | Reste1]
        |
elem_impair([3], Reste1)
        |
    X12 = 3
    Reste1 = [3] = X12
        |
      vide ; S = [1 | Reste1] = [1, 3]
```

##Diapo c3-25
`[1,2,3]` : construire la liste formée des éléments dans le sens inverse :
`[3,2,1]`

Écrire le prédicat `renv/2` en mode (+,-).

On va parcourir `L` en mettant ses éléments lus en réserve: liste auxiliaire `A`
 initialisée à `[]`.

```prolog
renv(L, R) :- renverser(L, [], R).
renverser([], A, A).
renverser([X|L], A, R) :- renverser(L, [X|A], R).
```
Arbre de recherche de `?- renv([1,2,3], Res).`

```prolog
renv([1,2,3], Res)
      |
    L1 = [1,2,3]
    R1 = Res
      |
renverser([1,2,3], [], Res)
      |
    X2 = 1
    L2 = [2, 3]
    A2 = [1]
    R2 = Res
      |
renverser([2,3], [1], Res)
      |
    X3 = 2
    L3 = [3]
    A3 = [2, 3]
    R3 = Res
      |
renverser([3], [2, 1], Res)
      |
    X4 = []
    A4 = [3,2,1]
    Res = A4
      |
     vide
```

##Diapo c3-32
Écrire le prédicat `concat/3` en mode (+,+,-).

```prolog
concat([], L, L).
concat([X|L1], L2, [X|L3]) :- concat(L1, L2, L3).
```
On a un temps d'execution proportionnel à la taille de `L1`.

##Diapo c4-5

~PGU : Plus grand unificateur~

PGU de `T1 = [a,b,c,d]` et `T2 = [Y|Z]` ?

Y = [a]
Z = [b,c,d]

PGU de `append([a,b],[c,d],L)` et `append([X|X1], Y, [X|Z])` ?

X = a
X1 = [b]
Y = [c,d]
L = [X|Z] donc Z = []

PGU de `p([a,b], [c], [Y, Z|T])` et de `p(A, B, [A|B])` ?

A = [a,b]
B = [c]
Y = A = [a,b]
B = [Z|T] donc Z = [c] et T = []

#Cours 5
##Diapo c4-10
Écrire le prédicat `oter_prem(+L, -R)` qui ôte le premier élément d'un liste.

```prolog
oter_prem([T|Q], Q).
```

Écrire le prédicat `oter_der(+L, -R)` qui ôte le dernier élément d'une liste.

```prolog
oter_der([X1], []).
oter_der([X1|L], [X1|R]) :- oter_der(L, R).
```

##Diapo c4-12
Écrire le prédicat `longueur(+L, ?N)` qui calcule la longueur d'une liste.

```prolog
longueur([], 0).
longueur([X|L], N) :- longueur(L, M), N is M+1.
```

##Diapo c4-13
Arbre de recherche de `longueur([1,2], S)`.

```prolog
longueur([1,2], S)
      |
    X1 = 1
    L1 = [2]
    S = N1
      |
longueur([2], M1), S is M1+1
      |
    X2 = 2
    L2 = []
    M1 = N2
      |
longueur([], M2) M1 is M2+1, S is M1+1
      |
    M2 = 0
      |
M1 is 0+1, S is M1+1
      |
    M1 = 1
      |
    S = 2
      |
    vide
```

##Diapo c4-20
Écrire le prédicat `longueur(+L, ?N)` avec un accumulateur.

```prolog
longueur(L, S) :- lg(L, 0, S).
lg([], N, N).
lg([X|L], A, N) :- lg(L, B, N), B is A+1.
```

##Diapo c4-21
Arbre de recherche de `longeur([1,2], S)`.

```prolog
longeur([1,2], S)
      |
  L1 = [1,2]
  S1 = S
      |
  lg([1, 2], 0, S1)
      |
    X2 = 1
    L2 = [2]
    A2 = 0
    N2 = S1
    B2 = A2+1 = 1
      |
  lg([2], 1, N2)
      |
    X3 = 2
    L3 = []
    A3 = 1
    N3 = N2
    B3 = A3+1 = 2
      |
  lg([], 2, 2)
      |
    vide
B3 = N2 = N1 = S1 = S = 2
```

##Diapo c4-23
Écrire le prédicat `meme_longueur(+L1, +L2)` qui rend vrai si les deux listes
ont la même taille.

```prolog
meme_longueur([],[]).
meme_longueur([X|L1], [Y|L2]) :- meme_longueur(L1, L2).
```

##Diapo c5-6

```prolog
pp0(X, Y) :-      pp2(X, Y):-
  qq(X),            qq(X),
  qq(Y).            !,
pp0(0,1).           qq(Y).
                  pp2(0,1).

pp1(X, Y) :-
  qq(X),          qq(1).
  qq(Y),          qq(2).
  !.
pp1(0,1).
```

Arbre de recherche et affichage des X et Y obtenus par : `?- pp0(X,Y).`,
`?- pp1(X, Y).` et `?-pp2(X, Y).`

```prolog
                  pp0(X, Y)
              /               \
         qq(X), qq(Y)        X = 0
      /             \        Y = 1
    X = 1          X = 2       |
      |              |      pp0(0,1)
    qq(Y)          qq(Y)
  /      \        /     \
Y = 1   Y = 2  Y = 1   Y = 2
  |       |      |       |
succès  succès succès succès


                  pp1(X, Y)
              /               \
       qq(X), qq(Y), !         cut
      /             \
    X = 1           cut
      |
    qq(Y), !
  /      \
Y = 1    cut
  |
succès


                  pp2(X, Y)
              /                  \
      qq(X), !, qq(Y)           cut
      /             \
    X = 1           cut
      |
    !, qq(Y)
  /       \
Y = 1    Y = 0
  |        |
succès  succès
```

##Diapo c5-10

Que calculent les questions posées et quels sont les résultats affichés.

1er ensemble de valeurs satisfaisant `repas`

`E=artichauts_Melanie, P = grillade_de_boeuf, D = sorbet_aux_poires`

1er repas comportant du poisson

`E = artichauts_Melanie, P = , D = sorbet_aux_poires`

1er repas et vérification qu'il contient du poisson
`No`

##Diapo c5-18

Écrire le prédicat `effacer(+P, +E1, -E2)` qui efface l'élément `P` de l'ensemble
`E1`.

###Version SANS coupure

```prolog
effacer(P, [P|E], E).
effacer(P, [P1|E], [P1|E1]) :- effacer(P, E, E1), \==(P, P1).
```

###Version AVEC coupure

```prolog
effacer(P, [P|E], E) :- !.
effacer(P, [P1|E], [P1|E1]) :- effacer(P, E, E1).
```

##Diapo c5-25

Version 1 ou version 2 ?

###Version 1
```prolog
sauver(X) :-
  homme(X),
  !,
  moins_de_quatoze_ans(X).
sauver(X).
```

###Version 2
```prolog
sauver(X) :-
  homme(X),
  moins_de_quatoze_ans(X),
  !.
sauver(X).
```

La version 1 est la seule correcte. Dans la seconde, tout le monde est sauvé car
`age(georges, 50). ?-sauver(georges).` rend `Yes`.

#Cours 6
##Diapo c5-36
```prolog
not(P) :-
  P,
  !,
  fail.
not(P).

grand(pierre).
grand(paul).
petit(X) -: not(grand(X)).
```

###Questions | Réponses
```
?-petit(arthur).          | succès (arrive pas à démontrer grand(arthur))
?-petit(pierre).          | échec
?-petit(X), X is arthur.  | échec (il teste d'abord avec X sur pierre, puis cut
                                 et fail)
```

##Diapo c6-10

```
S ::= aSB | Ba
B ::= b | epsilon
```

Ecrivez l'analyseur correspondant.

```prolog
s(L) :-
  concat_3([a], L1, L2, L),
  s(L1),
  b(L2).
s(L) :-
  concat_2(L1, [a], L),
  b(L1).
b([]).
b([b]).
```

##Diapo c6-24

```prolog
s(L) :-
  concat_2(L1, L2, L),
  s(L1),
  b(L2).
s([a]).       concat([], L, L).
b([b]).       concat([X|L1], L2, [X|L]):-
                concat(L1, L2, L3).
```

Arbre de recherche de `?- s([a,b]).`.

```
s([a,b])
|
concat(L1, L2) #PasEuLeTempsDeRecopier
```

On modifie `concat/3` pour qu'il ne dérive pas `[]` en 1^^er^^ argument. On
s'arrête sur l'élément d'avant.

```prolog
concat([X], L, [X|L]).
concat([X|L1], L2, [X|L]):-
  concat(L1, L2, L3).
```

##Diapo c6-22

```prolog
s([c|L], L).
s([a|L], R) :- s(L, [b|R]).
```

Arbre de recherche de `?- s([a,a,c,b,b], [])`.

```
s([a,a,c,b,b], [])
      |
  L1 = [a,c,b,b]
  R1 = []
      |
s([a,c,b,b], [b])
      |
  L2 = [c,b,b]
  R2 = [b]
      |
s([c,b,b], [b,b])
      |
  L3 = [b,b]
      |
    succes
```

##Diapo c6-24

```
E ::= a E F b | a F
F ::= c d F | F a | epsilon
```

Ecrivez l'analyseur correspondant.

```prolog
e([a|L], R) :-
  e(L, L1),
  f(L1, [b|R]).
e([a|L], R) :- f(L, R).
f([c|d|L], R) :- f(L, R).
f(L, R) :- f(L, [a|R]).
f(L, L).
```

##Diapo c6-34

```
E ::= T Op1 E | T
T ::= F Op2 T | F
F ::= ( E ) | atome
Op1 ::= + | -
Opé :: *
```
DCG correspondante ?

```prolog
e --> t, op1, e | t.
t --> f, op2, t | f.
f --> ['('], e, [')'], [A], {atom(A)}.
op1 --> ['+'] | ['-'].
op2 --> ['*'].
```

##Diapo c6-39

```prolog
e --> chiffre
e --> ['(', plus], l, [')'].
e --> ['(', mult], l, [')'].
l --> e, e.
l --> e, l.
chiffre --> [c], {integer(C), c>=0, C=<9}.
```
