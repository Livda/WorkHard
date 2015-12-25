---
title: TP6  - Base de données déductives
author: François Boschet && Aurélien Fontaine
date: 4-INFO
geometry: margin=3cm
---

# Opérations realtionnelles

##Question 2.1 - Sélection

~~~~ {#mycode .prolog .numberLines}

selection(P) :-
    piece(P, _, lyon).
~~~~

##Question 2.2 - Projection

~~~~ {#mycode .prolog .numberLines}
projection(P, V) :-
    piece(P, _, V).
~~~~

##Question 2.3 - Union, Intersection et différence ensembliste

~~~~ {#mycode .prolog .numberLines}
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
~~~~

##Question 2.4 - Produit cartésien

~~~~ {#mycode .prolog .numberLines}
produit_cartesien(A1, A2, A3, B1, B2, B3) :-
    fournisseurReference(A1, A2, A3),
    livraison(B1, B2, B3).
~~~~

##Question 2.5 - Jointure

~~~~ {#mycode .prolog .numberLines}
jointure1(F, N, V, P, Q) :-
    fournisseurReference(F, N, V),
    livraison(F, P, Q).

jointure2(F, N, V, P, Q) :-
    fournisseurReference(F, N, V),
    livraison(F, P, Q),
    Q > 350.
~~~~

##Question 2.6 - Division

~~~~ {#mycode .prolog .numberLines}
il_existe_un_produit_fabrique_a_lyon_non_fourni_par(F) :-
    piece(P, _, lyon),
    fournisseurReference(F, _, _),
    not(livraison(F, P, _)).

division(F) :-
    piece(P, _, lyon),
    livraison(F, P, _),
    not(il_existe_un_produit_fabrique_a_lyon_non_fourni_par(F)).
~~~~

##Question 2.1 -

~~~~ {#mycode .prolog .numberLines}
sum_list([], 0).
sum_list([H|T], N) :-
    sum_list(T, R),
    N is H + R.

total_pieces(F, N) :-
    findall(Q, livraison(F, _, Q), L),
    sum_list(L, N).
~~~~

# Au delà de l'algèbre relationelle

## Question 3.1

~~~~ {#mycode .prolog .numberLines}
composant(E, Piece):-
	assemblage(E, Piece, _).
composant(E, Piece):-
	assemblage(E, Sub, _),
	composant(Sub, Piece).
~~~~

## Question 3.2

~~~~ {#mycode .prolog .numberLines}
nbExemplaires(E, Piece, N):-
	assemblage(E, Piece, N).

nbExemplaires(E, Piece, N):-
	assemblage(E, I, M),
	nbExemplaires(I, Piece, P),
	N is M*P.

estPiece(P):-
	piece(_, P, _),
	!.

piecesTotal(E, composant(R,N)):-
	composant(E, R),
	estPiece(R),
	nbExemplaires(E, R, N).
~~~~

## Question 3.3

(On a été un peu aidé pour celle-ci)

~~~~ {#mycode .prolog .numberLines}
sumQuantProd([], 0).

sumQuantProd([Prod|R], NB):-
	nbComposantFourni(Prod, Nombre),
	sumQuantProd(R, NbR),
	NB is NbR+Nombre.

nbComposantFourniNom(Nom, NB):-
	findall(CodeComp, piece(CodeComp, Nom, _), Prods),
	sumQuantProd(Prods, NB).

nbComposantFourni(CodeComp, NB):-
	findall(Quantite, livraison(_, CodeComp, Quantite), Quantites),
	sumQuantites(Quantites, NB).

ratioComposant([], []).

ratioComposant([composant(NomComp,N)|Compo], [Rat1|Rest]):-
	nbComposantFourniNom(NomComp, T),
	Rat1 is T/N,
	ratioComposant(Compo, Rest).

minBis([], Min, Min).

minBis([P|R], Min, Res):-
	P < Min,
	!,
	minBis(R, P, Res).

minBis([P|R], Min, Res):-
	minBis(R, Min, Res).

minL([P|R], N):-
	minBis(R, P, N).

nombreDeVoiture(NB):-
	findall(composant(Comp,N), piecesTotal(voiture, composant(Comp, N)), C),
	ratioComposant(C, R),
	minL(R, NBfloat),
	NB is truncate(NBfloat).
~~~~

Joeyeuses fêtes de fin d'année!