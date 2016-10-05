---
title : Logique 2013
author : Aurélien Fontaine
geometry: margin=3cm
---

#Calcul propositionnel
Soient *p~1~*, *p~2~* et *p~3~* des variables propositionnelles. Soit la formule
*A* définie comme suit :

$(p_1 \land p_2 \land \neg p_3) \lor (p_1 \land \neg p_2 \land p_3) \lor
(\neg p_1 \land p_2 \land p_3)$

Soit *B* la formule où *p~1~* est remplacée par $\neg p_1$ dans la formule *A*.

##Question 1
*A et $\neg A$ sont-elles satisfaisables ? Justifier vos réponses.*
*A* est satisfaisable car (1, 1, 0) rend *A* vraie.

$\neg A = (\neg p_1 \lor \neg p_2 \lor p_3) \land (\neg p_1 \lor p_2 \lor \neg
p_3) \land (p_1 \lor \neg p_2 \lor \neg p_3)$

On a (1, 1, 1) qui rend vrai avec la formule $\neg A$ donc elle est
satisfaisable.

##Question 2
*Est-ce que $A \models B$ ? Justifier votre réponse.*

On a $B = (\neg p_1 \land p_2 \land \neg p_3) \lor (\neg p_1 \land \neg p_2
\land p_3) \lor (p_1 \land p_2 \land p_3)$

Faisons le tableau de vérité de *A* et de *B* :

|$p_1$ | $p_2$ | $p_3$ |  \| | *A* | *B* |
|:----:|:-----:|:-----:|:--:|:---:|:---:|
|0 | 0 | 0 |\|| 0 | 0 |
|0 | 0 | 1 |\|| 0 | 1 |
|0 | 1 | 1 |\|| 1 | 0 |
|0 | 1 | 0 |\|| 0 | 1 |

On voit que toute réalisation de *A* n'est pas réalisation de *B*.
Donc *B* n'est pas condition valide de *A*.

##Question 3
*Est-ce que $B \models A$ ? Justifier votre réponse.*

cf tableau précédent, toute réalisation de *B* n'est pas réalisation de *A*.
Donc *A* n'est pas condition valide de *B*.

#Système formel
Le système *PG* de D. Hofstadter est défini par :

- un alphabet *Z* = `{p, g, -}`
- des mots : toute suite de lettres de *Z*
- d'un axiome : `xp-gx-` avec `x` composé d'un ou plusieurs tirets
- d'une règle d'inférence : `xpygz -> xpy-gz-` où `x`, `y` et `z` sont composés
d'un ou plusieurs tirets.

##Question 4
*Montrer que `--p----g------` est un théorème.*

On part de l'axiome avec `x = --`
```
--p-g---
-> (x = --, y = -, z = ---) --p--g----
-> (x = --, y = --, z = ----) --p---g-----
-> (x = --, y = ---, z = -----) --p----g------
```

##Question 5
*Montrer que `----p--g------` est un théorème.*

On part de l'axiome avec `x = ----`
```
----p-g-----
-> (x = ----, y = -, z = -----) ----p--g------
```

##Question 6
*Quelle interprétation pouvez-vous donner à ces théorèmes, i.e. à `p`, `g` et
`-` ?*

C'est la fonction somme. Le nombre de `-` sont les nombres à sommer, `p` est
l'opérateur d'addition et `g` est l'opérateur de résultat.

#Énigme
Nous sommes dans l'île des chevaliers et des valets. Sur cette île, les
chevaliers disent toujours la vérité et les valets mentent toujours. Tous les
habitants de cette ville sont soit des chevaliers, soit des valets.

McGregor, l'agent de recensement, frappe à une porte et le mari ouvre. Après
s'être présenté, McGregor demande au mari : "vous et votre femme êtes de quel
type ?".

Le mari répond : "ma femme et moi sommes du même type, nous sommes donc deux
chevalier ou deux valets."

Que peut-on déduire sur la femme, et que peut-on déduire sur le mari ?

##Question 7
*Donner une argumentation en français.*

Partons du principe que le mari dise la vérité. C'est donc un chevalier. Donc sa
femme aussi.

Si le mari ment, c'est donc un valet. Et comme il a dit que sa femme était du
même type que lui, et que c'est un mensonge, alors sa femme est un chevalier.

Donc la femme est un chevalier (*la classe*) et le mari on sait pas.

##Question 8
*Exprimer sous forme de propositions le problème et justifier votre réponse en
fonction de ces propositions*

Soit *c~1~* le mari est chevalier et *c~2~* la femme est chevalier. On a donc :

\begin{math}
(c_1 \Rightarrow (c_1 \land c_2) \lor (\neg c_1 \Rightarrow (\neg c_1 \land c_2))
\end{math}

#Résolution
##Question 9
*Mettre sous forme clausale la formule suivante :*
\begin{math}
\forall x. ((\forall y. Q(0, x, f(y))) \Rightarrow R(x, 1)) \lor (\exists y.
Q(y, 1, x) \land \neg R(y, 1))
\end{math}

On trouve :

- $\neg Q(0, x_1, f(h(x_1))) \lor R(x_1, 1) \lor Q(g(x_1), 1, x_1)$
- $\neg Q(0, x_2, f(h(x_2))) \lor R(x_2, 1) \lor R(g(x_2), 1)$

##Question 10
*Montrer en utilisant la résolution que la formule suivante est une tautologie
:*
$\exists x. \forall y. P(x) \Rightarrow P(y)$

##Question 11
*Montrer que la formule suivante n'est pas une tautologie :*
$(\forall x. \exists y. P(f(x), y)) \Leftrightarrow (\exists y. \forall x. P(x,
g(y)))$

#Prolog
*Soit le programme Prolog suivant :*

~~~~ {#mycode .prolog .numberLines}
append(nil, X, X).
append(cons(X, R), Y, cons(X, R1)) :-
    append(R, Y, R1).
reverse(nil, nil).
reverse(cons(X, R), Y) :-
    reverse(R, R1),
    append(R1, cons(X, nil), Y).
~~~~

##Question 12
*Transformer ce programme en un ensemble de clauses.*

Soit A = append, c = cons et R = reverse :

\begin{math}
A1 = A(nil, X, X)
\newline
A2 = A(c(X, R), Y, cons(X, R1)) \lor \neg A(R, Y, R1)
\newline
A3 = R(nil, nil)
\newline
A4 = R(cons(X, R), Y) \lor \neg R(R, R1) \lor A(R1, cons(X, nil), Y)
\end{math}

*On veut que Prolog réponde à la question suivante :*

~~~~ {#mycode .prolog .numberLines}
?- reverse(cons(1, cons(2, nil)), Res).
~~~~

##Question 13
*Transformer la question précédente en clause puis effectuer la résolution sur
l'ensemble des clauses pour obtenir la réponse.*

Soit F la clause issue de la transformation de la question :

\begin{math}
F = R(cons(1, cons(2, nil)), Res)
\newline
\neg F = \neg R(1, cons(2, nil), Res)
\end{math}

On essaie d'unifier les clauses pour atteindre la clause vide :
