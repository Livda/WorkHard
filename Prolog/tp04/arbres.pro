/**
TP 4 Arbres binaires - Prolog

@author Prenom1 NOM1
@author Prenom2 NOM2
@version Annee scolaire 20__/20__
*/


/*
-------------------------------------------------------------------------------
 Définition des prédicats
-------------------------------------------------------------------------------
*/

arbre_binaire(arb_bin(R, vide, vide)) :- integer(R).
arbre_binaire(arb_bin(R, G, D)) :-
    integer(R),
    arbre_binaire(G),
    arbre_binaire(D).

dans_arbre_binaire(E, arb_bin(E, G, D)).
dans_arbre_binaire(E, arb_bin(R, G, D)) :-
    R \== E,
    dans_arbre_binaire(E, G).
dans_arbre_binaire(E, arb_bin(R, G, D)) :-
    R \== E,
    dans_arbre_binaire(E, D).

sous_arbre_binaire(arb_bin(R, G, D), arb_bin(R, G, D)).
sous_arbre_binaire(arb_bin(R1, G1, D1), arb_bin(R2, G2, D2)) :-
    sous_arbre_binaire(arb_bin(R1, G1, D1), G2);
    sous_arbre_binaire(arb_bin(R1, G1, D1), D2).

remplacer(SA1, SA2, SA1, SA2).
remplacer(SA1, SA2, vide, vide).
remplacer(SA1, SA2, arb_bin(R, G1, D1), arb_bin(R, G2, D2)) :-
    remplacer(SA1, SA2, G1, G2),
    remplacer(SA1, SA2, D1, D2).

isomorphe(arb_bin(R, G, D), arb_bin(R, D, G)).
isomorphe(arb_bin(R1, G1, D1), arb_bin(R2, G2, D2)) :-
    isomorphe(G1, G2),
    isomorphe(D1, D2).
isomorphe(arb_bin(R1, G1, D1), arb_bin(R2, G2, D2)) :-
    isomorphe(D1, G2),
    isomorphe(G1, D2).

concat([], R, R).
concat([X|L1], L2, [X|L3]) :- concat(L1, L2, L3).

infixe(vide, L).
infixe(arb_bin(R, G, D), L) :-
    infixe(G, L1),
    infixe(D, L2),
    concat(L1, [R|L2], L).

insertion_arbre_ordonne(X, vide, arb_bin(X, vide, vide)).
insertion_arbre_ordonne(X, arb_bin(X, G, D), arb_bin(X, G, D)).
insertion_arbre_ordonne(X, arb_bin(R, G1, D), arb_bin(R, G2, D)) :-
    X < R,
    insertion_arbre_ordonne(X, G1, G2).
insertion_arbre_ordonne(X, arb_bin(R, G, D1), arb_bin(R, G, D2)) :-
    X > R,
    insertion_arbre_ordonne(X, D1, D2).

insertion_arbre_ordonne1(X, arb_bin(X, vide, vide)).
insertion_arbre_ordonne1(X, vide) :-
    insertion_arbre_ordonne1(X, arb_bin(X, vide, vide)).
insertion_arbre_ordonne1(X, arb_bin(R, G, D)) :-
    X < R,
    insertion_arbre_ordonne1(X, G).
insertion_arbre_ordonne1(X, arb_bin(R, G, D)) :-
    X > R,
    insertion_arbre_ordonne1(X, D).

/*
-------------------------------------------------------------------------------
 Tests
-------------------------------------------------------------------------------
*/

% Quelques arbres à copier coller pour vous faire gagner du temps, mais
% n'hésitez pas à en définir d'autres

test(arbre_vide,vide).

test(arbre1,arb_bin(1, arb_bin(2, arb_bin(6, vide, vide), vide), arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide)))).

test(arbre2,arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))).

%%test(arbre3,arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, 7, vide))). on le commente car ce n'est pas un arbre binaire. On a testé cela avant de commenter.

test(arbre4,arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, arb_bin(6, vide, vide), arb_bin(7, vide, vide)))).

test(arbre5,arb_bin(3, arb_bin(5, arb_bin(6, vide, vide), arb_bin(7, vide, vide)), arb_bin(4, vide, vide))).

test(arbre6,arb_bin(3, arb_bin(6, vide, vide), arb_bin(5, arb_bin(4, vide, vide), arb_bin(7, vide, vide)))).

test(arbre7,arb_bin(8, arb_bin(4, arb_bin(2, vide, vide), arb_bin(6, vide, vide)), arb_bin(12, arb_bin(10, vide, vide), vide))).

test(arbre8,arb_bin(8, arb_bin(4, arb_bin(2, _, _), arb_bin(6, _, _)), arb_bin(12, arb_bin(10, _, _), _))).

test(arbre9,arb_bin(6,arb_bin(2,arb_bin(1,vide,vide),arb_bin(4,vide,vide)),arb_bin(8,vide,arb_bin(10,vide,vide)))).

test(arbre10,arb_bin(8,arb_bin(2,arb_bin(1,vide,vide),arb_bin(4,vide,vide)),arb_bin(6,vide,arb_bin(10,vide,vide)))).

test(arbre11,arb_bin(6,arb_bin(2,arb_bin(1,vide,vide),arb_bin(4,vide,vide)),arb_bin(8,arb_bin(2,arb_bin(1,vide,vide),arb_bin(4,vide,vide)),arb_bin(10,vide,vide)))).

test(arbre12, arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))).

test(arbre13, arb_bin(5, arb_bin(6,vide,vide), arb_bin(7,vide,vide))).

test(arbre14, arb_bin(1,vide,vide)).
