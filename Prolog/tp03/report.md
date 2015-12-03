---
title: TP3 - Listes
author: François Boschet && Aurélien Fontaine 
date: 4-INFO
geometry: margin=3cm
---

#Quelques classiques sur les listes
##Question 1

####- membre(?A,+X)
~~~~ {#mycode .prolog .numberLines}
membre(A, [A|X]).
membre(A, [B|X]) :-
    membre(A, X).

?- membre(2, [1,2,3,4]).
true .
?- membre(5, [1,2,3,4]).
false .

~~~~

#### - compte(+A,+X,?N)

~~~~ {#mycode .prolog .numberLines}
compte(A, [], 0).
compte(A, [B|X], N) :-
    compte(A, X, N),
    A \== B.
compte(A, [A|X], N) :-
    compte(A, X, M),
    N is M+1.

?:- compte(1, [1,2,3,4], X).
X = 1.
?:- compte(5, [1,2,3,4], X).
X = 0.
?:- compte(1, [1,2,1,4], X).
X = 2.
compte(1, [1,2,1,4], 2).
true .

~~~~

#### - renverser(+X, ?Y)

~~~~ {#mycode .prolog .numberLines}
renverser(L, R) :-
    renverser2(L, [], R).

renverser2([], A, A).
renverser2([X|L], A, R) :-
    renverser2(L, [X|A], R).

?:- renverser([1,2,3,4], Y).
Y = [4, 3, 2, 1].
?:- renverser([1,2,3,4], [4,3,2,1]).
true .
?:- renverser([1,2,3], [2]).
false .

~~~~

#### - palind(+X)

~~~~ {#mycode .prolog .numberLines}
palind(X):-pal(X, []).
pal(X, X).
pal([A|X], Y) :- pal(X, [A|Y]).

?:- palind([1,2,3,4]).
false .
?:- palind([4,3,3,4]).
true .
?:- palind([3]).
true .
?:- palind([]).
false .

~~~~

#### - nieme(+N, +X, -A)

~~~~ {#mycode .prolog .numberLines}
nieme(0, [A|X], A).
nieme(N, [B|X], A) :-
    nieme(M, X, A),
    M is N-1.

?:- nieme(0, [1,2,3,4], A).
A = 1 .
?:- nieme(3, [1,2,3,4], A).
A = 4 .

/* is’nt working properly */
nieme2(N, [A|X], A).
nieme2(N, [B|X], A) :-
    A \== B,
    nieme2(M, X, A),
    N is M+1.

~~~~

#### - hors_de(+A, +X)

~~~~ {#mycode .prolog .numberLines}
hors_de(A, []).
hors_de(A, [B|X]) :-
    hors_de(A, X),
    A \== B.

?:- hors_de(0, [1,2,3,4]).
true .
?:- hors_de(3, [1,2,3,4]).
false .
?:- hors_de(3, []).
true .

~~~~

#### - tous_diff(+X)

~~~~ {#mycode .prolog .numberLines}
tous_diff([]).
tous_diff([A|X]) :-
    hors_de(A, X),
    tous_diff(X).

?:- tous_diff([1,2,3,4]).
true .
?:- tous_diff([1,2,4,4]).
false .
?:- tous_diff([]).
true .

~~~~

#### - concat3(+X,+Y,+Z,-T)

~~~~ {#mycode .prolog .numberLines}
conc3(X, Y, Z, T) :-
    concat(X, Y, A),
    concat(A, Z, T).
concat([], L, L).
concat([X|L1], L2, [X|L3]) :- concat(L1, L2, L3).

?:- conc3([1,2], [3,4], [5,6], T).
T = [1, 2, 3, 4, 5, 6] .
?:- conc3([1,2], [3,4], [5,6], [1,2,3,4,5,6]).
true .
?:- conc3([1,2], [3,4], [5,6], [1,2,3,4,5]).
false .
?:- conc3([], [], [], []).
true.

~~~~
##### TODO: conc3(-, -, -, T)

#### - debute_par(+x,?Y)

~~~~ {#mycode .prolog .numberLines}
debute_par(X, []).
debute_par([D|X], [D|R]):- debute_par(X, R).

?:- debute_par([1,2,3], []).
true .
?:- debute_par([1,2,3], [1]).
true .
?:- debute_par([1,2,3], [1,2,3]).
true .
?:- Adebute_par([1,2,3], [2,3]).
false .

~~~~

#### - sous_liste(+X,?Y)

~~~~ {#mycode .prolog .numberLines}
sous_liste(X, Y) :- debute_par(X, Y).
sous_liste([A|X], Y) :- sous_liste(X, Y).

?:- sous_liste([1,2,3], []).
true .
?:- sous_liste([1,2,3], [1]).
true .
?:- sous_liste([1,2,3], [2,3]).
true .
?:- sous_liste([1,2,3], [1,3]).
false .

~~~~
##### TODO: rendre toutes les sous-listes possibles.

#### - elim(+X, -Y)

~~~~ {#mycode .prolog .numberLines}
/*Another version
elim([A], Y).
elim([A|X], Y) :-
    membre(A, Y),
    !,
    elim(X, Y).
elim([A|X], Y) :- elim(X, [A|Y]).*/


elim(X, Y) :- i_m_alone(X, Y, []).

i_m_alone([], Y, Y).
i_m_alone([A|X], Y, Z) :-
    compte(A, Z, 1),
    i_m_alone(X, Y, Z).
i_m_alone([A|X], Y, Z) :-
    compte(A, Z, 0),
    i_m_alone(X, Y, [A|Z]).

?:- elim([1,2,3], Y).
Y = [3, 2, 1] .
?:- elim([3,1,2,3,3], Y).
Y = [2, 1, 3]
?:- elim([], Y).
Y = [] .

~~~~
#####On en était à "elim" à la fin des 2h.

#### tri(+X,-Y)
~~~~ {#mycode .prolog .numberLines}

/* It’s not working. And I can’t see why. */
tri([], Y).
tri([A|X], Y) :-
    part(A, X, L, R),
    tri(L, LS),
    tri(R, RS),
    concat3(LS, [A], RS).

part(A, [], [], []).
part(A, [xs|X], [xs|L], R) :-
    xs =< A,
    part(A, X, L, R).
part(A, [xs|X], L, [xs|R]) :-
    xs > A,
    part(A, X, L, R).

?:- tri([1,2,3,4], Y).
?:- tri([4,3,2,1], Y).
?:- tri([3,1,2,4], Y).
?:- tri([], Y).

~~~~

# Modélisation des ensembles

## Question 2

#### - inclus(+X,+Y)

~~~~ {#mycode .prolog .numberLines}
inclus([], Y).
inclus([A|X], Y) :-
    membre(A, Y),
    inclus(X, Y).

?:- inclus([], [1,2,3,4]).
true .
?:- inclus([1,2,3,4], [1,2,3,4]).
true .
?:- inclus([1,2,4], [1,2,3,4]).
true .
?:- inclus([1,2,5], [1,2,3,4]).
false .

~~~~

#### - non_inclus(+X,+Y)

~~~~ {#mycode .prolog .numberLines}
non_inclus([A|X], Y) :-
    compte(A, Y, 0).
non_inclus([A|X], Y) :-
    non_inclus(X, Y).

?:- non_inclus([1,2,3,4], []).
false .
?:- non_inclus([5], [1,2,3,4]).
true .
?:- non_inclus([], [1,2,3,4]).
false .
?:- non_inclus([2,3], [1,2,3,4]).
false .

~~~~

#### - inter_ens(+X,+Y,?Z)

~~~~ {#mycode .prolog .numberLines}
union_ens([], Y, Y).
union_ens(X, [], X).
union_ens(X, Y, Z) :-
    concat(X, Y, S),
    elim(S, Z).

?:- union_ens([1,2,3], [2,3,4], [4,3,2,1]).
true .
?:- union_ens([2,3,1], [4,2,3], [4,1,3,2]).
true .
?:- union_ens([2,3,1], [4,2,3], [3,2,1]).
false .
?:- union_ens([1,2,3], [2,3,4], Z).
Z = [4, 3, 2, 1] .
?:- union_ens([2,3,1], [4,2,3], Z).
Z = [4, 1, 3, 2] .

~~~~

## Question 3

inclus/2 ne fonctionne pas en mode (?,?)

La nouvelle version :

~~~~ {#mycode .prolog .numberLines}
~~~~

##### TODO
