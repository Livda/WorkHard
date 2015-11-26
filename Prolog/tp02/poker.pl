% Septembre 2012
% TP2 TERMES CONSTRUITS - A compl?er et faire tourner sous Eclipse Prolog
% ==============================================================================
% ==============================================================================
%	FAITS
% ==============================================================================
/*
	hauteur(Valeur)
*/
hauteur(deux).
hauteur(trois).
hauteur(quatre).
hauteur(cinq).
hauteur(six).
hauteur(sept).
hauteur(huit).
hauteur(neuf).
hauteur(dix).
hauteur(valet).
hauteur(dame).
hauteur(roi).
hauteur(as).

/*
	couleur(Valeur)
*/
couleur(trefle).
couleur(carreau).
couleur(coeur).
couleur(pique).

/*
	succ_hauteur(H1, H2)
*/
succ_hauteur(deux, trois).
succ_hauteur(trois, quatre).
succ_hauteur(quatre, cinq).
succ_hauteur(cinq, six).
succ_hauteur(six, sept).
succ_hauteur(sept, huit).
succ_hauteur(huit, neuf).
succ_hauteur(neuf, dix).
succ_hauteur(dix, valet).
succ_hauteur(valet, dame).
succ_hauteur(dame, roi).
succ_hauteur(roi, as).

/*
	succ_couleur(C1, C2)
*/
succ_couleur(trefle, carreau).
succ_couleur(carreau, coeur).
succ_couleur(coeur, pique).

/*
  carte_test
  cartes pour tester le pr?icat EST_CARTE
*/

carte_test(c1,carte(sept,trefle)).
carte_test(c2,carte(neuf,carreau)).
carte_test(ce1,carte(7,trefle)).
carte_test(ce2,carte(sept,t)).

/*
	main_test(NumeroTest, Main)
	mains pour tester le pr?icat EST_MAIN
*/

main_test(main_triee_une_paire, main(carte(sept,trefle), carte(valet,coeur), carte(dame,carreau), carte(dame,pique), carte(roi,pique))).
% attention ici m2 repr?ente un ensemble de mains
main_test(m2, main(carte(valet,_), carte(valet,coeur), carte(dame,carreau), carte(roi,coeur), carte(as,pique))).
main_test(main_triee_deux_paires, main(carte(valet,trefle), carte(valet,coeur), carte(dame,carreau), carte(roi,coeur), carte(roi,pique))).
main_test(main_triee_brelan, main(carte(sept,trefle), carte(dame,carreau), carte(dame,coeur), carte(dame,pique), carte(roi,pique))).
main_test(main_triee_suite,main(carte(sept,trefle),carte(huit,pique),carte(neuf,coeur),carte(dix,carreau),carte(valet,carreau))).
main_test(main_triee_full,main(carte(deux,coeur),carte(deux,pique),carte(quatre,trefle),carte(quatre,coeur),carte(quatre,pique))).

main_test(merreur1, main(carte(sep,trefle), carte(sept,coeur), carte(dame,pique), carte(as,trefle), carte(as,pique))).
main_test(merreur2, main(carte(sep,trefle), carte(sept,coeur), carte(dame,pique), carte(as,trefle))).

% ==============================================================================
%        QUESTION 1 : est_carte(carte(Hauteur,Couleur))
% ==============================================================================

est_carte(carte(H, C)) :-
    hauteur(H),
    couleur(C).

% ==============================================================================
%	QUESTION 2 : est_main(main(C1,C2,C3,C4,C5))
% ==============================================================================

est_main(main(C1,C2,C3,C4,C5)) :-
    est_carte(C1),
    est_carte(C2),
    est_carte(C3),
    est_carte(C4),
    est_carte(C5),
    C1 \== C2.


% ==============================================================================
%       QUESTION 3 : inf_carte(C1,C2) premi?e version
% ==============================================================================




% =============================================================================
%       QUESTION 3 : inf_carte_b(C1,C2) deuxi?e version
% ==============================================================================



% ==============================================================================
%       QUESTION 4 : est_main_triee(main(C1,C2,C3,C4,C5))
% ==============================================================================




% ==============================================================================
%       QUESTION 5 : une_paire(main(C1,C2,C3,C4,C5))
% ==============================================================================



% ==============================================================================
%       QUESTION 6 : deux_paires(main(C1,C2,C3,C4,C5))
% ==============================================================================



% =============================================================================
%       QUESTION 7 : brelan(main(C1,C2,C3,C4,C5))
% =============================================================================




% =============================================================================
%       QUESTION 8 : suite(main(C1,C2,C3,C4,C5))
% ==============================================================================



% =============================================================================
%       QUESTION 9 : full(main(C1,C2,C3,C4,C5))
% =============================================================================





% ==============================================================================

/* TESTS QUESTION 1 : carte_test

?-carte_test(c1,carte(sept,trefle)).

Yes (0.00s cpu)
?-carte_test(c1,carte(sept,trefle)).

Yes (0.00s cpu)
?-carte_test(c2,carte(neuf,carreau)).

Yes (0.00s cpu)
?-carte_test(ce1,carte(7,trefle)).

Yes (0.00s cpu)
?-carte_test(ce2,carte(sept,t)).

Yes (0.00s cpu)

*/

% =============================================================================

/*  TESTS QUESTION 2 : est_main



*/

% =============================================================================

/* TESTS QUESTION 3 premiere version

*/

% ==============================================================================

/* TESTS QUESTION 3 deuxieme version

*/

% ==============================================================================

/* TESTS QUESTION 4


*/

% =============================================================================

/* TESTS QUESTION 5


*/

% ==============================================================================

/* TESTS QUESTION 6


*/

% ==============================================================================


/* TESTS QUESTION 7


*/

% ==============================================================================

/* TESTS QUESTION 8


*/

% =============================================================================

/* TESTS QUESTION 9


*/
