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
    A \== B,
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

nieme2(0, [A|X], A).
nieme2(N, [B|X], A) :-
    A \== B,
    nieme2(M, X, A),
    N is M+1.

?-: nieme2(N, [1,2,3,4], 3).
N = 2 .
?-: nieme2(N, [1,2,3,4], 1).
N = 0 .
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

#### - conc3(+X,+Y,+Z,-T)

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
#### - conc3(-, -, -, T)

~~~~ {#mycode .prolog .numberLines}
conc3v2([], [], [], []).
conc3v2(X, Y, Z, T) :-
    remplir1st(X, Y, Z, T);
    remplir2nd(X, Y, Z, T);
    remplir3rd(X, Y, Z, T).
remplir1st([A|X], Y, Z, [A|T]) :-
    conc3v2(X, Y, Z, T).
remplir2nd(X, [A|Y], Z, [A|T]) :-
    conc3v2(X, Y, Z, T).
remplir3rd(X, Y, [A|Z], [A|T]) :-
    conc3v2(X, Y, Z, T).

?- conc3v2(A, B, C, [1, 2, 3])
A = [1, 2, 3]
B = []
C = []

A = [1, 2]
B = [3]
C = []

A = [1, 2]
B = []
C = [3]

A = [1, 3]
B = [2]
C = []

A = [1]
B = [2, 3]
C = []

A = [1]
B = [2]
C = [3]
...
~~~~

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
?:- sous_liste([1,2,3], X).
X = []
X = [1]
X = [1, 2]
X = [1, 2, 3]
X = [2]
X = [2, 3]
X = [3]
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


elim([], []);
elim([A|X], [A|Y]) :-
    hors_de(A,X),
    elim(X, Y).
elim([A|Y], Y) :-
    membre(A, X),
    elim(X, Y).
~~~~
#####On en était à "elim" à la fin des 2h.

#### tri(+X,-Y)
~~~~ {#mycode .prolog .numberLines}

tri([], []).
tri([A|X], Y) :-
    part(A, X, L, R),
    tri(L, LS),
    tri(R, RS),
    conc3(LS, [A], RS, Y).

part(A, [], [], []).
part(A, [S|X], [S|L], R) :-
    S =< A,
    part(A, X, L, R).
part(A, [S|X], L, [S|R]) :-
    S > A,
    part(A, X, L, R).

?-: tri([1,2,3,4], Y).
Y = [1, 2, 3, 4]
?-: tri([4,3,2,1], Y).
Y = [1, 2, 3, 4]
?-: tri([3,1,2,4], Y).
Y = [1, 2, 3, 4]
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
