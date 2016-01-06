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

A = [likes(2, 1), likes(1, 2), likes(3, 1), likes(1, 3), likes(1, 1), likes(3, 2), likes(2, 3), likes(2, 2), likes(3, 3)]
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
    delete(L1, likes(B, A), L2),
    est_aime(L2).

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

~~~~ {#mycode .prolog .numberLines}
~~~~

### Tests

~~~~ {#mycode .prolog .numberLines}
~~~~

## Question 1.5

~~~~ {#mycode .prolog .numberLines}
~~~~

### Tests

~~~~ {#mycode .prolog .numberLines}
~~~~

## Question 1.6

~~~~ {#mycode .prolog .numberLines}
~~~~

### Tests

~~~~ {#mycode .prolog .numberLines}
~~~~

## Question 1.7

~~~~ {#mycode .prolog .numberLines}
~~~~

### Tests

~~~~ {#mycode .prolog .numberLines}
~~~~
