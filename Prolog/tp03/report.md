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
compte(A, [B|X], N) :- compte(A, X, N).
compte(A, [A|X], N) :- compte(A, X, M), M is N+1.

renverser(L, R) :- renverser2(L, [], R).
renverser2([], A, A).
renverser2([X|L], A, R) :- renverser2(L, [X|A], R).
~~~~

##Question 2
