---
title: TP5 - Arithmétique
author: François Boschet && Aurélien Fontaine
date: 4-INFO
geometry: margin=3cm
---

#Arithmétique de *Peano*

Pour toute cette partie, les tests seront effectués avec les données suivantes :

~~~~ {.mycode .prolog .numberLines}
test(0,zero).
test(1,s(zero)).
test(2,s(s(zero))).
test(3,s(s(s(zero)))).
test(4,s(s(s(s(zero))))).
test(5,s(s(s(s(s(zero)))))).
test(6,s(s(s(s(s(s(zero))))))).
~~~~

##Question 1.1

add(?, ?, ?): peano number * peano number * peano number
add(Op1, Op2, Add) avec Add = Op1 + Op2

~~~~ {#mycode .prolog .numberLines}
add(zero, O2, O2).
add(s(O1), O2, s(R)) :- add(O1, O2, R).
~~~~

###Tests

~~~~{.prolog}
?-test(0,X),test(2,Y),add(X,Y,Z).
X = zero
Y = s(s(zero))
Z = s(s(zero))
Yes (0.00s cpu)

?-test(3,X),test(2,Y),test(5,Z),add(X,Y,Z).
X = s(s(s(zero)))
Y = s(s(zero))
Z = s(s(s(s(s(zero)))))
Yes (0.00s cpu)

?-test(3,X),test(2,Y),test(6,Z),add(X,Y,Z).
No (0.00s cpu)

?-test(3,Z),add(X,Y,Z).

Z = s(s(s(zero)))
X = zero
Y = s(s(s(zero)))
Yes (0.00s cpu, solution 1, maybe more) ? ;

Z = s(s(s(zero)))
X = s(zero)
Y = s(s(zero))
Yes (0.00s cpu, solution 2, maybe more) ? ;

Z = s(s(s(zero)))
X = s(s(zero))
Y = s(zero)
Yes (0.00s cpu, solution 3, maybe more) ? ;

Z = s(s(s(zero)))
X = s(s(s(zero)))
Y = zero
Yes (0.00s cpu, solution 4)
~~~~

##Question 1.2

sub(?, ?, ?): peano number * peano number * peano number
sub(Op1, Op2, Sub) avec Sub = Op1 - Op2

~~~~ {#mycode .prolog .numberLines}
sub(O1, zero, O1).
sub(s(O1), s(O2), R) :- sub(O1, O2, R).
~~~~

###Tests

~~~~{.prolog}
test(5,X),test(3,Y),sub(X,Y,Z).

X = s(s(s(s(s(zero)))))
Y = s(s(s(zero)))
Z = s(s(zero))
Yes (0.00s cpu)

?-test(3,X),test(3,Y),test(3,Z),sub(X,Y,Z).
No (0.00s cpu)

?-test(2,Z),sub(X,Y,Z).

Z = s(s(zero))
X = s(s(zero))
Y = zero
Yes (0.00s cpu, solution 1, maybe more) ? ;

Z = s(s(zero))
X = s(s(s(zero)))
Y = s(zero)
Yes (0.00s cpu, solution 2, maybe more) ? ;

Z = s(s(zero))
X = s(s(s(s(zero))))
Y = s(s(zero))
Yes (0.00s cpu, solution 3, maybe more) ? ;

Z = s(s(zero))
X = s(s(s(s(s(zero)))))
Y = s(s(s(zero)))
Yes (0.00s cpu, solution 4, maybe more) ? ;

Z = s(s(zero))
X = s(s(s(s(s(s(zero))))))
Y = s(s(s(s(zero))))
Yes (0.00s cpu, solution 5, maybe more) ? ;

...
~~~~

##Question 1.3

prod(+, +, -): peano number * peano number * peano number
prod(Op1, Op2, Prod) avec Prod = Op1 * Op2

~~~~ {#mycode .prolog .numberLines}
prod(zero, Y, zero).
prod(s(X), Y, Prod):-
	prod(X, Y, Prod1),
	add(Y, Prod1, Prod).
~~~~

###Tests

~~~~{.prolog}
?- test(2,X),test(3,Y),prod(X,Y,Z).

X = s(s(zero)),
Y = s(s(s(zero))),
Z = s(s(s(s(s(s(zero)))))).

?- test(2,X),test(0,Y),prod(X,Y,Z).
X = s(s(zero)),
Y = Z,
Z = zero
~~~~

##Question 1.4

factorial(+, -): peano number * peano number
factorial(N, Fact) avec Fact = N!

~~~~ {#mycode .prolog .numberLines}
factorial(zero, s(zero)).
factorial(s(N), F) :-
    factorial(N, A),
	prod(s(N), A, F).
~~~~

###Tests

~~~~{.prolog}
?- test(3,X),factorial(X,Y).

X = s(s(s(zero))),
Y = s(s(s(s(s(s(zero)))))).

?- test(0,X),factorial(X,Y)

X = zero,
Y = s(zero)
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
