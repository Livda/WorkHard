%Travail à réaliser tp01 Prolog

#Base Menu
##Question 1.1

Interroger la base afin de lister le contenu de la
base.

```prolog
hors_d_oeuvre(H).
viande(V).
poisson(P).
dessert(D).
calorieis(A, C).
```

Tests:

```prolog
?- hors_d_oeuvre(H).

H = artichauts_Melanie
Yes (0.00s cpu, solution 1, maybe more) ? ;

H = truffes_sous_le_sel
Yes (0.00s cpu, solution 2, maybe more) ? ;

H = cresson_oeuf_poche
Yes (0.00s cpu, solution 3)

?- viande(V).

V = grillade_de_boeuf
Yes (0.00s cpu, solution 1, maybe more) ? ;

V = poulet_au_tilleul
Yes (0.00s cpu, solution 2)

?- poisson(P).

P = bar_aux_algues
Yes (0.00s cpu, solution 1, maybe more) ? ;

P = saumon_oseille
Yes (0.00s cpu, solution 2)

?- dessert(D).

D = sorbet_aux_poires
Yes (0.00s cpu, solution 1, maybe more) ? ;

D = fraises_chantilly
Yes (0.00s cpu, solution 2, maybe more) ? ;

D = melon_en_surprise
Yes (0.00s cpu, solution 3)

?- calories(A, C).

A = artichauts_Melanie
C = 150
Yes (0.00s cpu, solution 1, maybe more) ? ;

A = truffes_sous_le_sel
C = 202
Yes (0.00s cpu, solution 2, maybe more) ? ;

A = cresson_oeuf_poche
C = 212
Yes (0.00s cpu, solution 3, maybe more) ? ;

A = grillade_de_boeuf
C = 532
Yes (0.00s cpu, solution 4, maybe more) ? ;

A = poulet_au_tilleul
C = 400
Yes (0.00s cpu, solution 5, maybe more) ? ;

A = bar_aux_algues
C = 292
Yes (0.00s cpu, solution 6, maybe more) ? ;

A = saumon_oseille
C = 254
Yes (0.00s cpu, solution 7, maybe more) ? ;

A = sorbet_aux_poires
C = 223
Yes (0.00s cpu, solution 8, maybe more) ? ;

A = fraises_chantilly
C = 289
Yes (0.00s cpu, solution 9, maybe more) ? ;

A = melon_en_surprise
C = 122
Yes (0.01s cpu, solution 10)

```

Dans la suite du rapport, nous ommettrons les lignes “YES (...)” s’ils elles n’ont pas d’interêt particulier.

##Question 1.2
1. Un plat de résistance est un plat à base de viande ou de poisson.

```prolog
plat_resistance(P) :- viande(P).
plat_resistance(P) :- poisson(P).
```

Tests :
```prolog
?- plat_resistnace(P).

P = grillade_de_boeuf
P = poulet_au_tilleul
P = bar_aux_algues
P = saumon_oseille
```

2. Un repas se compose d'un hors d'oeuvre, d'un plat et d'un dessert.

```prolog
repas(H, P, D) :-
    hors_d_oeuvre(H),
    plat_resistance(P),
    dessert(D).
```

Tests :
```prolog
?- repas(H,P,D).

H = artichauts_Melanie
P = grillade_de_boeuf
D = sorbet_aux_poires

H = artichauts_Melanie
P = grillade_de_boeuf
D = fraises_chantilly

H = artichauts_Melanie
P = grillade_de_boeuf
D = melon_en_surprise

H = artichauts_Melanie
P = poulet_au_tilleul
D = sorbet_aux_poires

H = artichauts_Melanie
P = poulet_au_tilleul
D = fraises_chantilly

H = artichauts_Melanie
P = poulet_au_tilleul
D = melon_en_surprise

H = artichauts_Melanie
P = bar_aux_algues
D = sorbet_aux_poires

H = artichauts_Melanie
P = bar_aux_algues
D = fraises_chantilly

H = artichauts_Melanie
P = bar_aux_algues
D = melon_en_surprise

...

```

3. Plat dont le nombre de calories est compris entre 200 et 400.

```prolog
plat_cal(P) :-
    plat_de_resistance(P),
    calories(P, C),
    C < 400,
    C > 200.
```

Tests :
```prolog
?- plat_cal(P).

P = bar_aux_algues

P = saumon_oseille
```

4. Plat plus calorique que le "Bar aux algues".

```prolog
plat_bar(P) :-
    plat_resistance(P),
    calories(P, C),
    calories(bar_aux_algues, D),
    C > D.
```

Tests :
```prolog
?- plat_bar(P).

P = grillade_de_boeuf

P = poulet_au_tilleul
```

5. Valeur calorique d'un repas

```prolog
val_repas(H, P, D, RES) :-
    repas(H, P, D),
    calories(H, C1),
    calories(P, C2),
    calories(D, C3),
    RES = C1+C2+C3.
```

Tests :
```prolog
?- val_repas(H, P, D, RES).

H = artichauts_Melanie
P = grillade_de_boeuf
D = sorbet_aux_poires
RES = 150 + 532 + 223

H = artichauts_Melanie
P = grillade_de_boeuf
D = fraises_chantilly
RES = 150 + 532 + 289

H = artichauts_Melanie
P = grillade_de_boeuf
D = melon_en_surprise
RES = 150 + 532 + 122

H = artichauts_Melanie
P = poulet_au_tilleul
D = sorbet_aux_poires
RES = 150 + 400 + 223

H = artichauts_Melanie
P = poulet_au_tilleul
D = fraises_chantilly
RES = 150 + 400 + 289

H = artichauts_Melanie
P = poulet_au_tilleul
D = melon_en_surprise
RES = 150 + 400 + 122

H = artichauts_Melanie
P = bar_aux_algues
D = sorbet_aux_poires
RES = 150 + 292 + 223

H = artichauts_Melanie
P = bar_aux_algues
D = fraises_chantilly
RES = 150 + 292 + 289

H = artichauts_Melanie
P = bar_aux_algues
D = melon_en_surprise
RES = 150 + 292 + 122

...
```

6. Un repas équilibré est un repas dont le nombre total de calories est inférieur à 800.

```prolog
repas_equilibre(H, P, D) :-
    val_repas(H, P, D, RES),
    RES < 800.
```

Tests :
```prolog

?- repas_equilibre(H, P, D).

H = artichauts_Melanie
P = poulet_au_tilleul
D = sorbet_aux_poires

H = artichauts_Melanie
P = poulet_au_tilleul
D = melon_en_surprise

H = artichauts_Melanie
P = bar_aux_algues
D = sorbet_aux_poires

H = artichauts_Melanie
P = bar_aux_algues
D = fraises_chantilly

H = artichauts_Melanie
P = bar_aux_algues
D = melon_en_surprise

H = artichauts_Melanie
P = saumon_oseille
D = sorbet_aux_poires

H = artichauts_Melanie
P = saumon_oseille
D = fraises_chantilly

H = artichauts_Melanie
P = saumon_oseille
D = melon_en_surprise

H = truffes_sous_le_sel
P = poulet_au_tilleul
D = melon_en_surprise

H = truffes_sous_le_sel
P = bar_aux_algues
D = sorbet_aux_poires

H = truffes_sous_le_sel
P = bar_aux_algues
D = fraises_chantilly

H = truffes_sous_le_sel
P = bar_aux_algues
D = melon_en_surprise

H = truffes_sous_le_sel
P = saumon_oseille
D = sorbet_aux_poires

H = truffes_sous_le_sel
P = saumon_oseille
D = fraises_chantilly

H = truffes_sous_le_sel
P = saumon_oseille
D = melon_en_surprise

H = cresson_oeuf_poche
P = poulet_au_tilleul
D = melon_en_surprise

H = cresson_oeuf_poche
P = bar_aux_algues
D = sorbet_aux_poires

H = cresson_oeuf_poche
P = bar_aux_algues
D = fraises_chantilly

H = cresson_oeuf_poche
P = bar_aux_algues
D = melon_en_surprise

H = cresson_oeuf_poche
P = saumon_oseille
D = sorbet_aux_poires

H = cresson_oeuf_poche
P = saumon_oseille
D = fraises_chantilly

H = cresson_oeuf_poche
P = saumon_oseille
D = melon_en_surprise

```

##Question 1.3

#Base Famille de France (De Valois)
##Question 2.1

1. `enfant(E, P)` : `E` est un enfant de `P`;

```prolog
enfant(E, P) :- pere(P, E).
enfant(E, P) :- mere(P, E).
```

Tests :
```prolog
?- enfant(E, P)

E = claude_de_france
P = louis_XII

E = philippe_VI
P = charles_de_Valois

E = jean_II
P = philippe_VI

E = charles_V
P = jean_II

E = charles_VI
P = charles_V

E = charles_VII
P = charles_VI

E = louis_XI
P = charles_VII

E = louis_XII
P = charles_d_Orleans

E = francois_I
P = charles_d_angouleme

E = henri_II
P = francois_I

...
```

2. `parent(P, E)` : `P` est un parent direct de `E`;

```prolog
parent(P, E) :- enfant(E, P).
```

Tests:

```prolog
?- parent(P, E).

P = louis_XII
E = claude_de_france

P = charles_de_Valois
E = philippe_VI

P = philippe_VI
E = jean_II

P = jean_II
E = charles_V

P = charles_V
E = charles_VI

P = charles_VI
E = charles_VII

P = charles_VII
E = louis_XI

P = charles_d_Orleans
E = louis_XII

P = charles_d_angouleme
E = francois_I

P = francois_I
E = henri_II

...
```

3. `grand_pere(G, E)` : `G` est un grand-père de `E`;

```prolog
grand_pere(G, E) :-
    pere(G, P),
    parent(P, E).
```

Tests:
```prolog
?- grand_pere(G, E).

G = louis_XII
E = henri_II

G = charles_de_Valois
E = jean_II

G = philippe_VI
E = charles_V

G = jean_II
E = charles_VI

G = jean_II
E = louis_d_Orleans

G = charles_V
E = charles_VII

G = charles_VI
E = louis_XI

G = charles_d_Orleans
E = claude_de_france

G = charles_d_angouleme
E = henri_II

G = francois_I
E = francois_II

...
```

4. `frere(F, E)` : `F` est un frère de `E`;

```prolog
frere(F, E) :-
    homme(F),
    enfant(F, P),
    enfant(E, P),
    F \== E.
```

Tests:
```prolog
?- frere(F, E).

F = charles_VI
E = louis_d_Orleans

F = charles_VI
E = louis_d_Orleans

F = francois_II
E = charles_IX

F = francois_II
E = henri_III

F = francois_II
E = charles_IX

F = francois_II
E = henri_III

F = charles_IX
E = francois_II

F = charles_IX
E = henri_III

F = charles_IX
E = francois_II

F = charles_IX
E = henri_III

...
```


5. `oncle(O, E)` : `O` est un oncle de `E`;

```prolog
fratrie(F, E) :-
    parent(P, F),
    parent(P, E),
    F \== E.
```

```prolog
oncle(O, E) :-
    homme(O),
    fratrie(O, P),
    parent(P, E).
oncle(O, E) :-
    homme(O),
    epoux(O, F),
    fratrie(F, P),
    parent(P, E).
```

Tests:
```prolog
?- oncle(O, E).

O = charles_V
E = charles_VI

O = charles_V
E = louis_d_Orleans

O = charles_V
E = charles_VI

O = charles_V
E = louis_d_Orleans

O = charles_VI
E = charles_VII

O = charles_VI
E = charles_d_Orleans

O = charles_VI
E = jean_d_angouleme

O = charles_VI
E = charles_VII

O = charles_VI
E = charles_d_Orleans

O = charles_VI
E = jean_d_angouleme

...
```

6. `cousin(C, E)` : `C` est un cousin de `E`;

```prolog
oncle_et_tante(T, E) :-
    fratrie(T, P),
    parent(P, E).
oncle_et_tante(T, E) :-
    epoux(T, F),
    fratrie(F, P),
    parent(P, E).
```

```prolog
cousin(C, E) :-
    enfant(C, T),
    oncle_et_tante(T, E),
    C \== E.
```

Tests:
```prolog
?- cousin(C, E).

C = charles_VI
E = louis_d_Orleans

C = charles_VI
E = louis_d_Orleans

C = charles_VII
E = charles_d_Orleans

C = charles_VII
E = jean_d_angouleme

C = charles_VII
E = charles_d_Orleans

C = charles_VII
E = jean_d_angouleme

C = louis_XII
E = charles_d_angouleme

C = louis_XII
E = charles_d_angouleme

C = francois_II
E = charles_IX

C = francois_II
E = henri_III

...
```


7. `le_roi_est_mort_vive_le_roi(R1, D, R2)` :
en l'an `D`, le règne du roi `R1` se termine et
celui du roi `R2` débute.

```prolog
le_roi_est_mort_vive_le_roi(R1, D, R2) :-
    roi(R1, _, _, D),
    roi(R2, _, D, _).
```

Tests:
```prolog
?- le_roi_est_mort_vive_le_roi(R1, D, R2).

R1 = charles_V
D = 1380
R2 = charles_VI

R1 = charles_VI
D = 1422
R2 = charles_VII

R1 = charles_VII
D = 1461
R2 = louis_XI

R1 = louis_XI
D = 1483
R2 = charles_VIII

R1 = charles_VIII
D = 1498
R2 = louis_XII

R1 = louis_XII
D = 1515
R2 = francois_I

R1 = francois_I
D = 1547
R2 = henri_II

R1 = henri_II
D = 1559
R2 = francois_II

R1 = francois_II
D = 1560
R2 = charles_IX

R1 = charles_IX
D = 1574
R2 = henri_III

...
```
