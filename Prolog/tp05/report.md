---
title: TP5 - Arithmétique
author: François Boschet && Aurélien Fontaine
date: 4-INFO
geometry: margin=3cm
---

#Arithmétique de *Peano*

##Question 1.1

add(?, ?, ?): peano number * peano number * peano number
add(Op1, Op2, Add) avec Add = Op1 + Op2

~~~~ {#mycode .prolog .numberLines}
add(zero, O2, O2).
add(s(O1), O2, s(R)) :- add(O1, O2, R).
~~~~

##Question 1.2

sub(?, ?, ?): peano number * peano number * peano number
sub(Op1, Op2, Sub) avec Sub = Op1 - Op2

~~~~ {#mycode .prolog .numberLines}
sub(O1, zero, O1).
sub(s(O1), s(O2), R) :- sub(O1, O2, R).
~~~~

##Question 1.3

prod(+, +, -): peano number * peano number * peano number
prod(Op1, Op2, Prod) avec Prod = Op1 * Op2

~~~~ {#mycode .prolog .numberLines}
prod(s(zero), O2, R) :- add(s(zero), O2, R).
prod(s(01), 02, R) :-
    add(zero, O2, R),
    prod(O1, 02, R).
~~~~

##Question 1.4

factorial(+, -): peano number * peano number
factorial(N, Fact) avec Fact = N!

~~~~ {#mycode .prolog .numberLines}
factorial(s(zero), s(zero)).
factorial(s(N), F) :-
    prod(s(N), s(zero), F),
    factorial(N, F).
~~~~

#Représentation binaire

##Question 2.1

add(?, ?, ?): bit list * bit list * bit list
add(Op1, Op2, Sum) avec Sum = Op1 + Op2

~~~~ {#mycode .prolog .numberLines}
add([], [], []).
add([1|L1], [1|L2], [0|R]) :-
    add_bit_carryin(L1, L2, R, 1).
add([0|L1], [0|L2], [0|R]) :-
    add_bit_carryin(L1, L2, R, 0).
add([B1|L1], [B2|L2], [1|R]) :-
    B1 \== B2,
    add_bit_carryin(L1, L2, R, 0).

add_bit_carryin([], [], [], 0).
add_bit_carryin([], [], [1], 1).
add_bit_carryin([], [B2|L2], [B3|R], CI) :-
    add_bit(0, B2, CI, B3, CO),
    add_bit_carryin([], L2, R, CO).
add_bit_carryin([B1|L1], [], [B3|R], CI) :-
    add_bit(B1, 0, CI, B3, CO),
    add_bit_carryin(L1, [], R, CO).
add_bit_carryin([B1|L1], [B2|L2], [B3|R], CI) :-
    add_bit(B1, B2, CI, B3, CO),
    add_bit_carryin(L1, L2, R, CO).
~~~~~

##Question 2.2

sub(?, ?, ?): bit list * bit list * bit list
sub(Op1, Op2, Sub) avec Sub = Op1 - Op2

~~~~ {#mycode .prolog .numberLines}
~~~~
