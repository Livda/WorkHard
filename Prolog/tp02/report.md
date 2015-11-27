---
title: TP2 - Manipulation des termes construits
author: François Boschet & Aurélien Fontaine
date: 4-INFO
header-includes:
    - \usepackage[top=2.5cm, bottom=2.5cm, left=2.5cm, right=2.5cm]{geometry}
---

#Monde de poker

##QUESTION 1 : est_carte(carte(Hauteur,Couleur))
~~~~ {#mycode .prolog .numberLines}
est_carte(carte(H, C)) :-
    hauteur(H),
    couleur(C).
~~~~

##QUESTION 2 : est_main(main(C1,C2,C3,C4,C5))
~~~~ {#mycode .prolog .numberLines}
est_main(main(C1,C2,C3,C4,C5)) :-
    est_carte(C1), est_carte(C2), est_carte(C3), est_carte(C4), est_carte(C5),
    C1 \== C2, C1 \== C3, C1 \== C4, C1 \== C5,
    C2 \== C3, C2 \== C4, C2 \== C5,
    C3 \== C4, C3 \== C5,
    C4 \== C5.
~~~~

##QUESTION 3 : inf_carte(C1,C2) première version
~~~~ {#mycode .prolog .numberLines}
inf_hauteur(H1, H2) :-
    succ_hauteur(H1, H2).
inf_hauteur(H1, H2) :-
    succ_hauteur(H1, H),
    inf_hauteur(H, H2).

inf_coul(C1, C2) :-
    succ_couleur(C1, C2).
inf_coul(C1, C2) :-
    succ_couleur(C1, C),
    inf_coul(C, C2).

inf_carte(carte(H1, _), carte(H2, _)) :-
    inf_hauteur(H1, H2).
inf_carte(carte(H1, C1), carte(H2, C2)) :-
    H1 = H2,
    inf_coul(C1, C2).

inf_carte_52coeur(carte(H, C)) :-
    inf_carte(carte(cinq, coeur), carte(H,C)).
~~~~

##QUESTION 4 : est_main_triee(main(C1,C2,C3,C4,C5))
~~~~ {#mycode .prolog .numberLines}
est_main_triee(main(C1,C2,C3,C4,C5)) :-
    est_main(main(C1, C2, C3, C4, C5)),
    inf_carte(C1, C2),
    inf_carte(C2, C3),
    inf_carte(C3, C4),
    inf_carte(C4, C5).
~~~~

##QUESTION 5 : une_paire(main(C1,C2,C3,C4,C5))
~~~~ {#mycode .prolog .numberLines}
paire(carte(H1, _), carte(H2, _)) :-
    H1 = H2.

%Je pars du principe que l'on donne une main valide et triée
une_paire(main(C1,C2,C3,C4,C5)) :-
    paire(C1, C2) ; paire(C2, C3) ; paire(C3, C4) ; paire(C4, C5).
~~~~

##QUESTION 6 : deux_paires(main(C1,C2,C3,C4,C5))
~~~~ {#mycode .prolog .numberLines}
%Je pars du principe que l'on donne une main valide et triée
% Les points virgules c'est bien.
%Attention, ce ne sont pas des virgules en fin de ligne
deux_paires(main(C1,C2,C3,C4,C5)) :-
    paire(C1, C2), paire(C3, C4);
    paire(C1, C2), paire(C4, C5);
    paire(C2, C3), paire(C4, C5).
~~~~

##QUESTION 7 : brelan(main(C1,C2,C3,C4,C5))
~~~~ {#mycode .prolog .numberLines}
triplette(C1, C2, C3) :-
    paire(C1, C2), paire(C2, C3).

%Je pars du principe que l'on donne une main valide et triée
brelan(main(C1,C2,C3,C4,C5)) :-
    triplette(C1, C2, C3);
    triplette(C2, C3, C4);
    triplette(C3, C4, C5).
~~~~

##QUESTION 8 : suite(main(C1,C2,C3,C4,C5))
~~~~ {#mycode .prolog .numberLines}
%Je pars du principe que l'on donne une main valide et triée
suite(main(carte(H1,_), carte(H2, _), carte(H3, _), carte(H4, _), carte(H5, _))) :-
    succ_hauteur(H1, H2),
    succ_hauteur(H2, H3),
    succ_hauteur(H3, H4),
    succ_hauteur(H4, H5).
~~~~

##QUESTION 9 : full(main(C1,C2,C3,C4,C5))
~~~~ {#mycode .prolog .numberLines}
%Je pars du principe que l'on donne une main valide et triée
%Attention, ce ne sont pas des virgules en fin de ligne
full(main(C1,C2,C3,C4,C5)) :-
    paire(C1, C2), triplette(C3, C4, C5);
    triplette(C1, C2, C3), paire(C4, C5).
~~~~

##TESTS QUESTION 1 : carte_test
~~~~ {#mycode .prolog .numberLines}
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
~~~~

##TESTS QUESTION 2 : est_main

##TESTS QUESTION 3

##TESTS QUESTION 4

##TESTS QUESTION 5

##TESTS QUESTION 6

##TESTS QUESTION 7

##TESTS QUESTION 8

##TESTS QUESTION 9
