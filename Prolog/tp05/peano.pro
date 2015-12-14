add(zero, O2, O2).
add(s(O1), O2, s(A)) :- add(O1, O2, A).

sub(O1, zero, O1).
sub(s(O1), s(O2), R) :- sub(O1, O2, R).

/* TODO FIX IT*/
prod(zero, O2, zero).
prod(O1, zero, zero).
prod(s(zero), O2, O2).
prod(s(01), 02, R) :-
    prod(O1, 02, A),
    add(A, O2, R).

/*TODO Test it when previous one is fix*/
factorial(s(zero), s(zero)).
factorial(s(N), F) :-
    prod(s(N), A, F),
    factorial(N, A).


test(0,zero).
test(1,s(zero)).
test(2,s(s(zero))).
test(3,s(s(s(zero)))).
test(4,s(s(s(s(zero))))).
test(5,s(s(s(s(s(zero)))))).
test(6,s(s(s(s(s(s(zero))))))).
