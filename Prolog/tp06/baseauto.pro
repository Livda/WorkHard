/**
TP 7 Base de Données Déductives (BDD) - Prolog

@author Prenom1 NOM1
@author Prenom2 NOM2
@version Annee scolaire 20__/20__
*/


/*
===============================================================================
===============================================================================
 Définition des prédicats
===============================================================================
*/
% =============================================================================
% SECTION 1 : Base de données
% =============================================================================

assemblage(voiture, porte, 4).
assemblage(voiture, roue, 4).
assemblage(voiture, moteur, 1).
assemblage(roue, jante, 1).
assemblage(porte, tole, 1).
assemblage(porte, vitre, 1).
assemblage(roue, pneu, 1).
assemblage(moteur, piston, 4).
assemblage(moteur, soupape, 16).


piece(p1, tole, lyon).
piece(p2, jante, lyon).
piece(p3, jante, marseille).
piece(p4, pneu, clermontFerrand).
piece(p5, piston, toulouse).
piece(p6, soupape, lille).
piece(p7, vitre, nancy).
piece(p8, tole, marseille).
piece(p9, vitre, marseille).


demandeFournisseur(dupont, lyon).
demandeFournisseur(michel, clermontFerrand).
demandeFournisseur(durand, lille).
demandeFournisseur(dupond, lille).
demandeFournisseur(martin, rennes).
demandeFournisseur(smith, paris).
demandeFournisseur(brown, marseille).


fournisseurReference(f1, dupont, lyon).
fournisseurReference(f2, durand, lille).
fournisseurReference(f3, martin, rennes).
fournisseurReference(f4, michel, clermontFerrand).
fournisseurReference(f5, smith, paris).
fournisseurReference(f6, brown, marseille).


livraison(f1, p1, 300).
livraison(f2, p2, 200).
livraison(f3, p3, 200).
livraison(f4, p4, 400).
livraison(f6, p5, 500).
livraison(f6, p6, 1000).
livraison(f6, p7, 300).
livraison(f1, p2, 300).
livraison(f4, p2, 300).
livraison(f4, p1, 300).


% =============================================================================
% SECTION 2 : Opération relationnelles
% =============================================================================

selection(P) :-
    piece(P, _, lyon).

projection(P, V) :-
    piece(P, _, V).

intersection(N, V) :-
    demandeFournisseur(N, V),
    fournisseurReference(_, N, V).

non(P) :- P, !, fail.
non(P).

union(N, V) :-
    demandeFournisseur(N, V).
union(N, V) :-
    non(intersection(N,V)),
    fournisseurReference(_, N, V).

difference_ensembliste(N, V) :-
    demandeFournisseur(N, V),
    non(intersection(N,V)).

produit_cartesien(A1, A2, A3, B1, B2, B3) :-
    fournisseurReference(A1, A2, A3),
    livraison(B1, B2, B3).

jointure1(F, N, V, P, Q) :-
    fournisseurReference(F, N, V),
    livraison(F, P, Q).

jointure2(F, N, V, P, Q) :-
    fournisseurReference(F, N, V),
    livraison(F, P, Q),
    Q > 350.

il_existe_un_produit_fabrique_a_lyon_non_fourni_par(F) :-
    piece(P, _, lyon),
    fournisseurReference(F, _, _),
    not(livraison(F, P, _)).

division(F) :-
    piece(P, _, lyon),
    livraison(F, P, _),
    not(il_existe_un_produit_fabrique_a_lyon_non_fourni_par(F)).

sum_list([], 0).
sum_list([H|T], N) :-
    sum_list(T, R),
    N is H + R.

total_pieces(F, N) :-
    findall(Q, livraison(F, _, Q), L),
    sum_list(L, N).

% =============================================================================
% SECTION 3 : Au delà de l’algèbre relationnelle
% =============================================================================

ensemble_des_composants_et_pieces_pour_fabriquer_un_composant(C, L) :-
    assemblage(C, C2, _),
    ensemble_des_composants_et_pieces_pour_fabriquer_un_composant(C2, [C2|L]).
ensemble_des_composants_et_pieces_pour_fabriquer_un_composant(C, [C]) :-
    piece(_, C, _).

%==============================================================================
%==============================================================================
% Tests
%==============================================================================
%==============================================================================

