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
sous_arbre_binaire(abr_bin(R1, G1, D1), abr_bin(R2, G2, D2)) :-
    sous_arbre_binaire(abr_bin(R1, G1, D1), G2);
    sous_arbre_binaire(abr_bin(R1, G1, D1), D2).

remplacer(SA1, SA2, vide, vide).
remplacer(SA1, SA2, SA1, SA2).
remplacer(SA1, SA2, arb_bin(R, G1, D1), arb_bin(R, G2, D2)) :-
    remplacer(SA1, SA2, G1, G2),
    remplacer(SA1, SA2, D1, D2).

isomorphe(arb_bin(R, G, D), arb_bin(R, D, G)).
isomorphe(arb_bin(R1, G1, D1), arb_bin(R2, G2, D2)) :-
    isomorphe(G1, G2) ; isomorphe(D1, D2).

/*
-------------------------------------------------------------------------------
 Tests
-------------------------------------------------------------------------------
*/

% Quelques arbres à copier coller pour vous faire gagner du temps, mais
% n'hésitez pas à en définir d'autres

/*
arb_bin(1, arb_bin(2, arb_bin(6, vide, vide), vide), arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide)))

arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))

arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, 7, vide))

arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, arb_bin(6, vide, vide), arb_bin(7, vide, vide)))

arb_bin(3, arb_bin(5, arb_bin(6, vide, vide), arb_bin(7, vide, vide)), arb_bin(4, vide, vide))

arb_bin(3, arb_bin(6, vide, vide), arb_bin(5, arb_bin(4, vide, vide), arb_bin(7, vide, vide)))

arb_bin(8, arb_bin(4, arb_bin(2, vide, vide), arb_bin(6, vide, vide)), arb_bin(12, arb_bin(10, vide, vide), vide))

arb_bin(8, arb_bin(4, arb_bin(2, _, _), arb_bin(6, _, _)), arb_bin(12, arb_bin(10, _, _), _))

arb_bin(6,arb_bin(2,arb_bin(1,vide,vide),arb_bin(4,vide,vide)),arb_bin(8,vide,arb_bin(10,vide,vide)))

arb_bin(8,arb_bin(2,arb_bin(1,vide,vide),arb_bin(4,vide,vide)),arb_bin(6,vide,arb_bin(10,vide,vide)))

arb_bin(6,arb_bin(2,arb_bin(1,vide,vide),arb_bin(4,vide,vide)),arb_bin(8,arb_bin(2,arb_bin(1,vide,vide),arb_bin(4,vide,vide)),arb_bin(10,vide,vide)))
*/
