% dana likes cody
% bess does not like dana
% cody does not like abby
% nobody likes someone who does not like her
% abby likes everyone who likes bess
% dana likes everyone bess likes
% everybody likes somebody

people([abby, bess, cody, dana]).

% Question 1.1

make_all_pairs([A], [likes(A,A)]).
make_all_pairs([A|L], Res) :-
    pairs_with_an_element(A, L, R1),
    make_all_pairs(L,R2),
    append(R1, R2, Res).

pairs_with_an_element(A, [], [likes(A,A)]).
pairs_with_an_element(A, [B|L], [likes(B,A),likes(A,B)|Res]) :-
    pairs_with_an_element(A, L, Res).

/*Tests :
make_all_pairs([1,2,3], A).

A = [likes(2, 1), likes(1, 2), likes(3, 1), likes(1, 3), likes(1, 1), likes(3, 2), likes(2, 3), likes(2, 2), likes(3, 3)]
Yes*/

% Question 1.2

sub_list([], []).
sub_list([A|L1], [A|L2]) :-
    sub_list(L1, L2).
sub_list([_|L], Res) :-
    sub_list(L, Res).

/*Tests :
sub_list([1,2,3], A).

A = [1, 2, 3]
Yes

A = [1, 2]
Yes

A = [1, 3]
Yes

A = [1]
Yes

A = [2, 3]
Yes

A = [2]
Yes

A = [3]
Yes

A = []
Yes*/

% Question 1.3

/*danna aime coddy*/
proposition1(W):-member(likes(dana,cody),W).

/*Tests :
proposition1([likes(cody, dana)]).
No

proposition1([likes(dana, cody)]).
Yes
*/

/*bess n'aime pas dana*/
proposition2(W):-not(member(likes(bess, dana), W)).

/*cody n'aime pas abby*/
proposition3(W):-not(member(likes(coddy, dana), W)).

/*personne n'aime quelqu'un qui ne l'aime pas*/
proposition4(W):-est_aime(W).

est_aime([]).
est_aime([likes(A, B)|L1]) :-
    delete(L1, likes(B, A), L2),
    est_aime(L2).

/*abby aime tous ceux qui aiment bess*/
proposition5(W):-member(likes(abby,A),W),member(likes(bess,A),W).

/*dana aime tous ceux qui aiment bess*/
proposition6(W):-member(likes(dana,A),W),member(likes(bess,A),W).

/*tout le monde aime quelqu'un*/
proposition7(W).

% Question 1.4

possible_worlds(W) :-
	people(P),
	make_all_pairs(P, LP),
	sub_list(LP, W),
	proposition1(W);!,
	proposition2(W);!,
	proposition3(W);!,
	proposition4(W);!,
	proposition5(W);!,
	proposition6(W);!,
	proposition7(W).
	
	
	
	

% Questions 1.6 and 1.7
test_possible_worlds :-
        possible_worlds(World),
        writeln(World),
        fail.
