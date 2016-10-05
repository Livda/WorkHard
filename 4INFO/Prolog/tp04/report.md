---
title: TP4 - Arbres binaires
author: François Boschet && Aurélien Fontaine
date: 4-INFO
geometry: margin=3cm
---

#Question 1

arbre_binaire(+B)

~~~~ {#mycode .prolog .numberLines}
arbre_binaire(arb_bin(R, vide, vide)) :- integer(R).
arbre_binaire(arb_bin(R, G, D)) :-
    integer(R),
    arbre_binaire(G),
    arbre_binaire(D).
~~~~

#Question 2

dans_arbre_binaire(+E, +B)

~~~~ {#mycode .prolog .numberLines}
dans_arbre_binaire(E, arb_bin(E, G, D)).
dans_arbre_binaire(E, arb_bin(R, G, D)) :-
    R \== E,
    dans_arbre_binaire(E, G).
dans_arbre_binaire(E, arb_bin(R, G, D)) :-
    R \== E,
    dans_arbre_binaire(E, D).
~~~~

#Question 3

sous_arbre_binaire(+S, +B)

~~~~ {#mycode .prolog .numberLines}
sous_arbre_binaire(arb_bin(R, G, D), arb_bin(R, G, D)).
sous_arbre_binaire(arb_bin(R1, G1, D1), arb_bin(R2, G2, D2)) :-
    sous_arbre_binaire(arb_bin(R1, G1, D1), G2);
    sous_arbre_binaire(arb_bin(R1, G1, D1), D2).
~~~~

#Question 4

remplacer(+SA1, +SA2, +B, -B1)

~~~~ {#mycode .prolog .numberLines}
remplacer(SA1, SA2, SA1, SA2).
remplacer(SA1, SA2, vide, vide).
remplacer(SA1, SA2, arb_bin(R, G1, D1), arb_bin(R, G2, D2)) :-
    SA1 \== arb_bin(R, G1, D1),
    remplacer(SA1, SA2, G1, G2),
    remplacer(SA1, SA2, D1, D2).
~~~~

#Question 5

isomorphe(+B1, +B2)

~~~~ {#mycode .prolog .numberLines}
isomorphe(arb_bin(R, G, D), arb_bin(R, D, G)).
isomorphe(arb_bin(R1, G1, D1), arb_bin(R2, G2, D2)) :-
    isomorphe(G1, G2),
    isomorphe(D1, D2).
isomorphe(arb_bin(R1, G1, D1), arb_bin(R2, G2, D2)) :-
    isomorphe(D1, G2),
    isomorphe(G1, D2).
~~~~

#Question 6

infixe(+B, -L)

~~~~ {#mycode .prolog .numberLines}
concat([], R, R).
concat([X|L1], L2, [X|L3]) :- concat(L1, L2, L3).

infixe(vide, L).
infixe(arb_bin(R, G, D), L) :-
    infixe(G, L1),
    infixe(D, L2),
    concat(L1, [R|L2], L).
~~~~

#Question 7

insertion_arbre_ordonnee(+X, +B1, -B2)

~~~~ {#mycode .prolog .numberLines}
insertion_arbre_ordonne(X, vide, arb_bin(X, vide, vide)).
insertion_arbre_ordonne(X, arb_bin(X, G, D), arb_bin(X, G, D)).
insertion_arbre_ordonne(X, arb_bin(R, G1, D), arb_bin(R, G2, D)) :-
    X < R,
    insertion_arbre_ordonne(X, G1, G2).
insertion_arbre_ordonne(X, arb_bin(R, G, D1), arb_bin(R, G, D2)) :-
    X > R,
    insertion_arbre_ordonne(X, D1, D2).
~~~~

#Question 8

insertion_arbre_ordonne1(+X, +B)

~~~~ {#mycode .prolog .numberLines}
insertion_arbre_ordonne1(X, arb_bin(X, vide, vide)).
insertion_arbre_ordonne1(X, vide) :-
    insertion_arbre_ordonne1(X, arb_bin(X, vide, vide)).
insertion_arbre_ordonne1(X, arb_bin(R, G, D)) :-
    X < R,
    insertion_arbre_ordonne1(X, G).
insertion_arbre_ordonne1(X, arb_bin(R, G, D)) :-
    X > R,
    insertion_arbre_ordonne1(X, D).
~~~~
