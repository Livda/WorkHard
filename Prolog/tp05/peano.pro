add(zero, O2, O2).
add(s(O1), O2, s(A)) :- add(O1, O2, A).

sub(O1, zero, O1).
sub(s(O1), s(O2), R) :- sub(O1, O2, R).

/* TODO FIX IT*/
prod(O1, O2, zero) :- O1 = zero ; O2 = zero.
prod(s(zero), O2, 02).
prod(s(01), 02, R) :-
    prod(O1, 02, R),
    add(zero, O2, R).

/*TODO Test it when previous one is fix*/
factorial(s(zero), s(zero)).
factorial(s(N), F) :-
    prod(s(N), s(zero), F),
    factorial(N, F).
