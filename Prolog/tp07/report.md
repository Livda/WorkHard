---
title: TP7  - Mondes possibles
author: François Boschet && Aurélien Fontaine
date: 4-INFO
geometry: margin=3cm
---

# Relations d'amitié dans une confrérie

## Question 1.1

~~~~ {#mycode .prolog .numberLines}
make_all_pairs([A], [likes(A,A)]).
make_all_pairs([A|L], Res) :-
    pairs_with_an_element(A, L, R1),
    make_all_pairs(L,R2),
    append(R1, R2, Res).

pairs_with_an_element(A, [], [likes(A,A)]).
pairs_with_an_element(A, [B|L], [likes(B,A),likes(A,B)|Res]) :-
    pairs_with_an_element(A, L, Res).
~~~~

### Tests

~~~~ {#mycode .prolog .numberLines}
?- make_all_pairs([1,2,3], A).

A = [likes(2, 1), likes(1, 2), likes(3, 1), likes(1, 3), likes(1, 1),
likes(3, 2), likes(2, 3), likes(2, 2), likes(3, 3)]
~~~~

## Question 1.2

~~~~ {#mycode .prolog .numberLines}
sub_list([], []).
sub_list([A|L1], [A|L2]) :-
    sub_list(L1, L2).
sub_list([_|L], Res) :-
    sub_list(L, Res).
~~~~

### Tests

~~~~ {#mycode .prolog .numberLines}
?- sub_list([1,2,3], A).

A = [1, 2, 3],
A = [1, 2],
A = [1, 3],
A = [1],
A = [2, 3],
A = [2],
A = [3],
A = [],
~~~~

## Question 1.3

~~~~ {#mycode .prolog .numberLines}
/*danna aime coddy*/
proposition1(W) :- member(likes(dana,cody),W).

/*bess n'aime pas dana*/
proposition2(W) :- not(member(likes(bess, dana), W)).

/*cody n'aime pas abby*/
proposition3(W) :- not(member(likes(coddy, dana), W)).

/*personne n'aime quelqu'un qui ne l'aime pas*/
proposition4(W) :- est_aime(W).

est_aime([]).
est_aime([likes(A, B)|L1]) :-
    delete_fait_a_la_main(L1, likes(B, A), L2),
    est_aime(L2).

delete_fait_a_la_main([], A, []).
delete_fait_a_la_main([A|L], A, L).
delete_fait_a_la_main([B|L], A, [B|Res]) :-
    B \== A,
    delete_fait_a_la_main(L, A, Res).

/*La proposition 4 ne fonctionnant pas, on a pas fini les propositions*/
/*abby aime tous ceux qui aiment bess*/
proposition5(W) :- member(likes(abby,A),W),member(likes(bess,A),W).

/*dana aime tous ceux qui aiment bess*/
proposition6(W) :- member(likes(dana,A),W),member(likes(bess,A),W).

/*tout le monde aime quelqu'un*/
proposition7(W).
~~~~

### Tests

~~~~ {#mycode .prolog .numberLines}
proposition1([likes(cody, dana)]).
No

proposition1([likes(dana, cody)]).
Yes
~~~~

## Question 1.4

On suppose que toutes les propositions fonctionnent.

~~~~ {#mycode .prolog .numberLines}
possible_worlds(W) :-
   people(P),
   make_all_pairs(P, LP),
   sub_list(LP, W),
   proposition1(W),
   proposition2(W),
   proposition3(W),
   proposition4(W),
   proposition5(W),
   proposition6(W),
   proposition7(W).
~~~~

### Tests

~~~~ {#mycode .prolog .numberLines}
/* don't know how to render tests properly. :/ */
~~~~

## Question 1.5

~~~~ {#mycode .prolog .numberLines}
~~~~

### Tests

~~~~ {#mycode .prolog .numberLines}
.
~~~~

## Question 1.6

Je n'ai pas compris comment ca fonctionne. :/

~~~~ {#mycode .prolog .numberLines}
~~~~

### Tests

~~~~ {#mycode .prolog .numberLines}
~~~~

## Question 1.7

Sans avoir fait le code, je suppose que changer la taille de la liste influe sur l'ensemble des solutions. Et que l'ordre des litéraux change les resultats de coverage.

~~~~ {#mycode .prolog .numberLines}
~~~~

### Tests

~~~~ {#mycode .prolog .numberLines}
~~~~
