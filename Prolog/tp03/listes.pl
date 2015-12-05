/**
TP Listes Prolog

@author Aurélien Fontaine
@author François Boschet
@version Annee scolaire 2015/2016
*/


/* is A member of the list -> membre(?A, +X) */
membre(A, [A|X]).
membre(A, [B|X]) :- membre(A, X).

/*count the number of occurence of A in X -> compte(+A, +X, ?N) */
compte(A, [], 0).
compte(A, [B|X], N) :-
    A \== B,
    compte(A, X, N).
compte(A, [A|X], N) :-
    compte(A, X, M),
    N is M+1.

/*reverse A into B -> renverser(+L, ?R) */
renverser(L, R) :- renv(L, [], R).
renv([], A, A).
renv([X|L], A, R) :- renv(L, [X|A], R).

/* is X a palindrom -> palind(+X) */
palind(X) :- renverser(X, X).

/* A is the nth element of X -> nieme(+N, +X, -A) */
nieme(0, [A|X], A).
nieme(N, [B|X], A) :-
    nieme(M, X, A),
    N is M+1.

/* second version (-,+,+) i.e. what is the first occurence of A in X*/
nieme2(0, [A|X], A).
nieme2(N, [B|X], A) :-
    A \== B,
    nieme2(M, X, A),
    N is M+1.

/* A isn't in X -> hors_de(+A, +X) */
hors_de(A, []).
hors_de(A, [B|X]) :-
    A \== B,
    hors_de(A, X).

/* Are all the elts of an array different -> tous_diff(+X) */
tous_diff([]).
tous_diff([A|X]) :-
    hors_de(A, X),
    tous_diff(X).

/* Concatenate X, Y and Z in T -> conc3(+X, +Y, +Z, ?T) */
conc3(X, Y, Z, T) :-
    concat(X, Y, A),
    concat(A, Z, T).
concat([], R, R).
concat([X|L1], L2, [X|L3]) :- concat(L1, L2, L3).

/* Show all the sub-lists X, Y and Z, witch can create T*/
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

/* do X beginning is Y -> debute_par(+X, ?Y) */
debute_par(X, []).
debute_par([A|X], [A|Y]) :- debute_par(X, Y).

/* is Y a sublist of X -> sous_liste(+X, ?Y) */
sous_liste(X, Y) :- debute_par(X, Y).
sous_liste([A|X], Y) :- sous_liste(X, Y).

/* make a set Y from a list X -> elim(+X, -Y) */

/*elim([A], Y).
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

/* Y <- sorted X : tri(+X, ?Y)*/
/* THIS IS NOT WORKING AND I DON'T KNOW WHY */
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

/* all elts in X are also in Y -> inclus(+X,+Y) */
inclus([], Y).
inclus([A|X], Y) :-
    membre(A, Y),
    inclus(X, Y).

/* at least an elts of X is not in Y -> non_inclus(+X, +Y) */
non_inclus([A|X], Y) :-
    compte(A, Y, 0).
non_inclus([A|X], Y) :-
    non_inclus(X, Y).

/* unify X and Y into Z -> union_ens(+X, +Y, ?Z) */
union_ens([], Y, Y).
union_ens(X, [], X).
union_ens(X, Y, Z) :-
    concat(X, Y, S),
    elim(S, Z).
