---
title: TP3 - Listes
author: François Boschet _”Tyzeppelin”_ & Aurélien Fontaine _”Malabar”_
date: 4-INFO
geometry: margin=3cm
---

#Quelques classiques sur les listes
##Question 1

~~~~ {#mycode .prolog .numberLines}
membre(A, [A|X]).
membre(A, [B|X]) :- membre(A, X).

compte(A, [], 0).
compte(A, [B|X], N) :-
    compte(A, X, N),
    A \== B.
compte(A, [A|X], N) :-
    compte(A, X, M),
    N is M+1.

renverser(L, R) :- renverser2(L, [], R).
renverser2([], A, A).
renverser2([X|L], A, R) :- renverser2(L, [X|A], R).

palind(X):-pal(X, []).
pal(X, X).
pal([A|X], Y) :- pal(X, [A|Y]).

nieme(0, [A|X], A).
nieme(N, [B|X], A) :-
    nieme(M, X, A),
    M is N-1.

nieme2(N, [A|X], A).
nieme2(N, [B|X], A) :-
    A \== B,
    nieme2(M, X, A),
    N is M+1.

hors_de(A, []).
hors_de(A, [B|X]) :-
    hors_de(A, X),
    A \== B.

tous_diff([]).
tous_diff([A|X]) :-
    hors_de(A, X),
    tous_diff(X).

conc3(X, Y, Z, T) :-
    concat(X, Y, A),
    concat(A, Z, T).
concat([], L, L).
concat([X|L1], L2, [X|L3]) :- concat(L1, L2, L3).

#TODO conc3(-, -, -, T)

debute_par(X, []).
debute_par([D|X], [D|R]):- debute_par(X, R).

sous_liste(X, Y) :- debute_par(X, Y).
sous_liste([A|X], Y) :- sous_liste(X, Y).
#TODO rendre toutes les sous-listes possibles.

elim(X, Y) :- i_m_alone(X, Y, []).
i_m_alone([], Y, Y).
i_m_alone([A|X], Y, [A|Z]) :-
    hors_de(A, Z),
    i_m_alone(X, Y, Z).
i_m_alone([A|X], Y, Z) :- i_m_alone(X, Y, Z).

#On en était à "elim" à la fin des 2h.
~~~~

##Question 2
