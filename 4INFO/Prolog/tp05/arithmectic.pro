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
	
sub(A,B,C) :- add(C, B, A).


prod(X, Y, Z):-
	prod2(X, Y, Z, Y).

prod2([], _, [], _).
prod2([1|X], Y, Z, Off):-
	prod2(X, Y, W, [0|Off]),
	add(W, Off, Z).
prod2([0|X], Y, Z, Off):-
	prod2(X, Y, Z, [0|Off]).


factorial([], [1]).
factorial([1|M], Z) :-
	factorial(M, W),
	prod(M, W, Z).
factorial([0|M], Z) :-
	factorial(M, Z).

%%%%%%%%%%% Binary representation
add_bit(0, 0, 0, 0, 0).
add_bit(0, 0, 1, 1, 0).
add_bit(0, 1, 0, 1, 0).
add_bit(0, 1, 1, 0, 1).
add_bit(1, 0, 0, 1, 0).
add_bit(1, 0, 1, 0, 1).
add_bit(1, 1, 0, 0, 1).
add_bit(1, 1, 1, 1, 1).

%%%%%%%%%%% Optional part
evaluate_numbers(N1, M1, N2, M2) :-
        evaluate(N1, N2),
        evaluate(M1, M2),
        number(N2),
        number(M2).

evaluate(N, N) :- number(N).

evaluate(add(N1, M1), N) :-
        evaluate_numbers(N1, M1, N2, M2),
        N is N2 + M2.

evaluate(sub(N1, M1), N) :-
        evaluate_numbers(N1, M1, N2, M2),
        N is N2 - M2.

evaluate(prod(N1, M1), N) :-
        evaluate_numbers(N1, M1, N2, M2),
        N is N2 * M2.

evaluate(eq(N1, M1), Res) :-
        evaluate_numbers(N1, M1, N2, M2),
        (
            N2 = M2, Res = t
        ;
            N2 \= M2, Res = f
        ).

evaluate(fun(X, Body), fun(X, Body)).


fresh_variables(Expr, Res) :-
       fresh_variables(Expr, [], Res).

fresh_variables(X, Assoc, Y) :-
        var(X),
        !,
        assoc(X, Assoc, Y).

fresh_variables(add(X1, Y1), Assoc, add(X2, Y2)) :-
        fresh_variables(X1, Assoc, X2),
        fresh_variables(Y1, Assoc, Y2).

fresh_variables(prod(X1, Y1), Assoc, prod(X2, Y2)) :-
        fresh_variables(X1, Assoc, X2),
        fresh_variables(Y1, Assoc, Y2).

fresh_variables(sub(X1, Y1), Assoc, sub(X2, Y2)) :-
        fresh_variables(X1, Assoc, X2),
        fresh_variables(Y1, Assoc, Y2).

fresh_variables(eq(X1, Y1), Assoc, eq(X2, Y2)) :-
        fresh_variables(X1, Assoc, X2),
        fresh_variables(Y1, Assoc, Y2).

fresh_variables(if(Cond1, X1, Y1), Assoc, if(Cond2, X2, Y2)) :-
        fresh_variables(Cond1, Assoc, Cond2),
        fresh_variables(X1, Assoc, X2),
        fresh_variables(Y1, Assoc, Y2).

fresh_variables(Number, _, Number) :- number(Number).

fresh_variables(fun(X, Body1), Assoc, fun(Y, Body2)) :-
        fresh_variables(Body1, [(X, Y) | Assoc], Body2).

fresh_variables(apply(Fun1, Param1), Assoc, apply(Fun2, Param2)) :-
        fresh_variables(Fun1, Assoc, Fun2),
        fresh_variables(Param1, Assoc, Param2).

%Fun = fun(N, fun(F, if(eq(N, 0), 1, prod(N, apply(apply(F, sub(N, 1)), F))))), Factorial = fun(N, apply(apply(Fun, N), Fun)), evaluate(apply(Factorial, 42), Res).


test_bin(0,[]).
test_bin(1,[1]).
test_bin(2,[0,1]).
test_bin(3,[1,1]).
test_bin(4,[0,0,1]).
test_bin(5,[1,0,1]).
test_bin(6,[0,1,1]).
