hors_d_oeuvre(artichauts_Melanie).
hors_d_oeuvre(truffes_sous_le_sel).
hors_d_oeuvre(cresson_oeuf_poche).

viande(grillade_de_boeuf).
viande(poulet_au_tilleul).

poisson(bar_aux_algues).
poisson(saumon_oseille).

dessert(sorbet_aux_poires).
dessert(fraises_chantilly).
dessert(melon_en_surprise).

calories(artichauts_Melanie, 150).
calories(truffes_sous_le_sel, 202).
calories(cresson_oeuf_poche, 212).
calories(grillade_de_boeuf, 532).
calories(poulet_au_tilleul, 400).
calories(bar_aux_algues, 292).
calories(saumon_oseille, 254).
calories(sorbet_aux_poires, 223).
calories(fraises_chantilly, 289).
calories(melon_en_surprise, 122).

plat_resistance(P) :- viande(P).
plat_resistance(P) :- poisson(P).

repas(H, P, D) :-
    hors_d_oeuvre(H),
    plat_resistance(P),
    dessert(D).

plat_cal(P) :-
    plat_resistance(P),
    calories(P, C),
    C < 400,
    C > 200.

plat_bar(P) :-
    plat_resistance(P),
    calories(P, C),
    calories(bar_aux_algues, D),
    C > D.

val_repas(H, P, D, RES) :-
    repas(H, P, D),
    calories(H, C1),
    calories(P, C2),
    calories(D, C3),
    RES = C1+C2+C3.

repas_equilibre(H, P, D) :-
    val_repas(H, P, D, RES),
    RES < 800.
