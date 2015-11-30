---
title : Logique 2012
author : Aurélien Fontaine
geometry: margin=3cm
---
#Table de vérité
Soit la proposition suivante :
\begin{math}
P = (p_3 \lor (p_1 \land p_2 )) \Rightarrow (\neg p_1 \land \neg p_2)
\end{math}
où *p~1~* ,*p~2~* et *p~3~* sont des variables propositionnelles.

Après transformation on obtient :

\begin{math}
P = (\neg p_3 \land (\neg p_1 \lor \neg p_2 )) \lor (\neg p_1 \land \neg p_2)
\end{math}

##Question 1
*Montrer que P est satisfaisable.*

**Rappel :** Une proposition est satisfaisante quand elle peut obtenir la valeur
1. Il faut trouver un 3-uplet $(p_1,p_2,p_3)$ qui donne $A=1$.

La combinaison (0,0,0) fonctionne.

##Question 2
*Montrer que P est réfutable.*

**Rappel :** Une proposition est réfutable si on trouve une combinaison de
3-uplet telle que A=0.

Pour notre question, A vaut 0 si on a la combinaison $(p_1,p_2,p_3)=(1,1,0)$.

#Calcul propositionnel
##Question 3
*Soit A, B et C des propositions quelconques. Montrer que $A \Rightarrow
(B \Rightarrow C)$ est logiquement équivalente à $(A \land B) \Rightarrow C$.*

\begin{math}
A \Rightarrow (B \Rightarrow C) \newline
\equiv A \Rightarrow (\neg B \lor C) \newline
\equiv \neg A \lor \neg B \lor C \newline
\equiv \neg(A \land B) \lor C \newline
\equiv (A \land B) \Rightarrow C \newline
\end{math}

*On considère la proposition suivante :*
\begin{math}
F = (p_1 \land p_2) \Rightarrow (p_3 \Leftrightarrow (\neg p_1 \lor p_2))
\end{math}
*où p~1~ ,p~2~ et p~3~ sont des variables propositionnelles.*

##Question 4
*Déterminer une formule logiquement équivalente à *F*, écrite sans autre symbole
de connecteur que $\Rightarrow$ et $\Leftrightarrow$.*

**Rappel :** $A \Rightarrow B \equiv (\neg A \lor B)$

On voit que l'expression est de la même forme que pour la question 3 avec
A = *p~1~* , B = *p~2~* et C = $(p_3 \Leftrightarrow (\neg p_1 \lor p_2))$

\begin{math}
F = (p_1 \land p_2) \Rightarrow (p_3 \Leftrightarrow (\neg p_1 \lor p_2))
\newline
\equiv p_1 \Rightarrow (p_2 \Rightarrow (p_3 \Leftrightarrow (\neg p_1 \lor p_2)))
\newline
\equiv p_1 \Rightarrow (p_2 \Rightarrow (p_3 \Leftrightarrow (p_1 \Rightarrow p_2)))
\end{math}

##Question 5
*Donner la forme normale conjonctive de F aussi réduite que possible.*

**Rappels :** $A \Leftrightarrow B \equiv (A \land B) \lor (\neg A \land \neg
B)$ et la forme normale conjonctive est de la forme $(A \lor B) \land (\neg C
\lor \neg D)$

\begin{math}
F = (p_1 \land p_2) \Rightarrow (p_3 \Leftrightarrow (\neg p_1 \lor p_2))
\newline
\equiv \neg (p_1 \land p_2) \lor ((p_3 \land (\neg p_1 \lor p_2))
\lor (\neg p_3 \land \neg (\neg p_1 \lor p_2)))
\newline
\equiv (\neg p_1 \lor \neg p_2) \lor ((p_3 \land (\neg p_1 \lor p_2))
\lor (\neg p_3 \land p_1 \land \neg p_2))
\newline
\equiv (\neg p_1 \lor \neg p_2) \lor ((\neg p_3 \lor p_3) \land (\neg p_1 \lor
p_2 \lor \neg p_3) \land (p_3 \lor p_1) \land (\neg p_1 \lor p_2 \lor p_1) \land
(p_3 \lor \neg p_2) \land (\neg p_1 \lor p_2 \lor \neg p_2))
\newline
\equiv (\neg p_1 \lor \neg p_2) \lor ((\neg p_1 \lor p_2 \lor \neg p_3) \land (
p_3 \lor p_1) \land (p_3 \lor \neg p_2))
\newline
\equiv (\neg p_1 \lor \neg p_2 \lor \neg p_1 \lor p_2 \lor \neg p_3) \land
(\neg p_1 \lor \neg p_2 \lor p_3 \lor p_1) \land (\neg p_1 \lor \neg p_2 \lor
p_3 \lor \neg p_2)
\newline
\equiv \neg p_1 \lor \neg p_2 \lor p_3
\end{math}

##Question 6
*Montrer que les formules $(p_1 \Rightarrow (p_2 \Rightarrow (p_3 \Leftrightarrow
(p_2 \Rightarrow p_1))))$ et $(p_1 \Rightarrow (p_2 \Rightarrow p_3))$ sont
logiquement équivalentes.*

On a vu dans la question 4 que *F* peut s'écrire sous la forme
$(p_1 \Rightarrow (p_2 \Rightarrow (p_3 \Leftrightarrow (p_2 \Rightarrow p_1))))$.

Mais à la question 5, nous avons montré que *F* peut s'écrire comme ceci :
$\neg p_1 \lor \neg p_2 \lor p_3$, or $\neg A \lor B \equiv A \Rightarrow B$.
On applique deux fois ce principe à cette expression et on retrouve bien le
résultat voulu, soit $(p_1 \Rightarrow (p_2 \Rightarrow p_3))$.

#Énigme
Dans ma mallette de peintre, j'ai des tubes de peinture. Les couleurs dont je
dispose sont parmi les suivantes : rouge, jaune, bleu, orange, gris et noir. On
désignera par :

- R la proposition "j'ai du rouge" (abréviation pour "j'ai un tube de rouge") ;
- J la proposition "j'ai du jaune" ;
- B la proposition "j'ai du bleu" ;
- O la proposition "j'ai du orange" ;
- G la proposition "j'ai du gris" ;
- N la proposition "j'ai du noir".

##Question 7
*Écrire sous la forme d'une formule chacune des affirmations suivantes. Pour la
2), la formule "il y a exactement trois couleurs dans ma mallette" est composée
de plusieurs combinaisons, n'exprimer qu'une seule combinaison et expliquer
comment faire pour les autres combinaisons.*

1. *je n'ai pas de jaune ;*

$M = \neg J$

2. *j'ai du noir ou du gris, si et seulement si il y a exactement 3 couleurs
dans ma mallette ;*

$M = (G \land N \land R \land \neg (J \lor B \lor O))\newline$
On construit toutes les autres combinaisons en changeant R par J, B, ou O et en
mettant R (ou son remplaçant) dans la parenthèse "non".
On fait de même en mettant G dans la parenthèse non et en faisant toutes
les combinaisons entre les autres couleurs. Puis on fait comme précédemment,
sauf que cette fois ci, N reste dans la parenthèse "non" et G reste en dehors.

3. *des trois couleurs : bleu, jaune et orange, j'en ai au moins deux ;*

$M = (B \land J) \lor (B \land O) \lor (J \land O) \lor (B \land J \land O)$

4. *des deux couleurs : gris et orange, j'en ai exactement une.*

$M = (G \lor O) \land \neg (G \land O)$

##Question 8
*On suppose que les quatre affirmations ci-dessus sont vraies. Déduire les
contenus possibles de la mallette. Justifier le raisonnement.*

Avec la proposition *1* on voit qu'il ne pourra jamais y avoir de jaune.

La proposition *3* nous dit qu'il y a 2 couleurs au moins parmi bleu, jaune et
orange. Hors la proposition *1* interdit la couleur jaune. Il y a donc du bleu
et du orange.

La proposition *4* dit qu'il a soit du gris, soit du orange. On vient de voir
que l'on a du bleu et du orange. Donc pas de gris.

La proposition *2* nous oblige à avoir trois couleurs si l'on a du gris ou du
noir. Nous n'avons pas de gris, du coup seul le cas noir se présente. Il se
trouve que nous avons déjà du bleu et du orange, si on lui ajoute le noir,
nous avons bien trois couleurs dans notre mallette.

Puis on peut rajouter toutes les combinaisons qui n'entrent pas en conflit avec
les propositions. Soit :
(B, O) ; (N, B, O).

#Résolution
Soit :
\begin{math}
A_1 = \forall x \exists y. E(f(x,y), e) \newline
A_2 = \forall x \forall y. (I(x, y) \land I(y, x)) \Rightarrow E(x, y) \newline
A_3 = \forall x. I(e, x) \newline
A_4 = \forall x \forall y. (I(x, g(y)) \Rightarrow I(x, y)) \newline
F = \forall x (I(x, g(e)) \Rightarrow E(x, E)) \newline
\end{math}
avec *e* une constante.

##Question 9
*Montrer que {A~1~, A~2~, A~3~, A~4~} $\models$ F.*

\begin{math}
A_1 = \forall x_1 \exists y_1. E(f(x_1,y_1), e) \newline
A_2 = \forall x_2 \forall y_2. (I(x_2, y_2) \land I(y_2, x_2)) \Rightarrow
E(x_2, y_2) \newline
A_3 = \forall x_3. I(e, x_3) \newline
A_4 = \forall x_4 \forall y_4. (I(x_4, g(y_4)) \Rightarrow I(x_4, y_4)) \newline
F = \forall x_F. (I(x_F, g(e)) \Rightarrow E(x_F, e)) \newline
\vspace{0.5cm} \newline
A_1 = \forall x_1. E(f(x_1, h(x_1),) e) \newline
A_2 = \forall x_2 \forall y_2. (I(x_2, y_2) \land I(y_2, x_2)) \Rightarrow
E(x_2, y_2) \newline
A_3 = \forall x_3. I(e, x_3) \newline
A_4 = \forall x_4 \forall y_4. (I(x_4, g(y_4)) \Rightarrow I(x_4, y_4)) \newline
F = \forall x_F. (I(x_F, g(e)) \Rightarrow E(x_F, e)) \newline
\vspace{0.5cm} \newline
A_1 = \forall x_1. E(f(x_1,h(x_1)), e) \newline
A_2 = \forall x_2 \forall y_2. \neg (I(x_2, y_2) \land I(y_2, x_2)) \lor
E(x_2, y_2) \newline
A_3 = \forall x_3. I(e, x_3) \newline
A_4 = \forall x_4 \forall y_4. \neg I(x_4, g(y_4)) \lor I(x_4, y_4) \newline
F = \forall x_F. \neg I(x_F, g(e)) \lor E(x_F, e) \newline
\vspace{0.5cm} \newline
A_1 = \forall x_1. E(f(x_1,h(x_1)), e) \newline
A_2 = \forall x_2 \forall y_2. (\neg I(x_2, y_2) \lor \neg I(y_2, x_2)) \lor
E(x_2, y_2) \newline
A_3 = \forall x_3. I(e, x_3) \newline
A_4 = \forall x_4 \forall y_4. \neg I(x_4, g(y_4)) \lor I(x_4, y_4) \newline
F = \forall x_F. \neg I(x_F, g(e)) \lor E(x_F, e) \newline
\vspace{0.5cm} \newline
A_1 = \forall x_1. E(f(x_1,h(x_1)), e) \newline
A_2 = \forall x_2 \forall y_2. (\neg I(x_2, y_2) \lor \neg I(y_2, x_2)) \lor
E(x_2, y_2) \newline
A_3 = \forall x_3. I(e, x_3) \newline
A_4 = \forall x_4 \forall y_4. \neg I(x_4, g(y_4)) \lor I(x_4, y_4) \newline
\neg F = \neg (\neg I(a, g(e)) \lor E(a, e)) \newline
\vspace{0.5cm} \newline
A_1 = \forall x_1. E(f(x_1,h(x_1), e) \newline
A_2 = \forall x_2 \forall y_2. (\neg I(x_2, y_2) \lor \neg I(y_2, x_2)) \lor
E(x_2, y_2) \newline
A_3 = \forall x_3. I(e, x_3) \newline
A_4 = \forall x_4 \forall y_4. \neg I(x_4, g(y_4)) \lor I(x_4, y_4) \newline
\neg F = I(a, g(e)) \land E(a, e) \newline
\end{math}
avec *a* une constante.

On séprare $\neg F$ en deux closes à cause du $\land$. On a donc :
\begin{math}
\neg F_1 = I(a, g(e)) \newline
\neg F_2 = E(a, e) \newline
\end{math}

On essaie d'atteindre la clause vide :
\begin{math}
\underbrace{I(e, x_3) \hspace{5pt} I(a, g(e))} \newline
\null \hspace{1.2cm} \downarrow \mbox{ avec } a = e \mbox{ et } x_3 = g(e) \newline
\null \hspace{1.2cm} \Box
\end{math}

##Question 10
*Montrer que $G = \forall x \forall y \forall z. I(x,y) \Rightarrow I(f(x, y),
f(y, z))$ n'est pas conséquence valide de {A~1~, A~2~, A~3~, A~4~}.*

#Prolog
Soit le programme *Prolog* suivant :

~~~~ {#mycode .prolog .numberLines}
add(zero, X, X).
add(s(X), Y, s(Z)) :- add(X, Y, Z).
prod(zero, _, zero).
prod(s(X), Y, Z) :- prod(X, Y, Z1), add(Y, Z1, Z).
~~~~

##Question 11
*Transformer ce programme en un ensemble de clauses.*

*On veut que Prolog réponde à la question :*

~~~~ {#mycode .prolog .numberLines}
?- prod(s(zero), s(zero), Res).
~~~~

##Question 12
*Transformer cette question en clause puis effectuer la résolution du l'ensemble
de clauses pour obtenir la réponse.*

#Système formel
Le système formel *MUI* (inventé par Douglas Hofstadter et présenté dans *Gödel,
Escher, Bach, les brins d'une guirlande éternelle*) est le suivant :

- le langage est *{M, I, U}\** (les chaines formées à partir des symboles *M*,
*I* et *U*);
- le schema d'induction :
    - base : l'axiome *MI* ;
    - les quatres règles d'inférence (x et y sont des chaînes quelconques de
*{M, I, U}\**)
        1. $xI \vdash xIU$
        2. $Mx \vdash Mxx$
        3. $xIIIy \vdash xUy$
        4. $xUUy \vdash xy$.

##Question 13
*Rédiger la démonstration du théorème MUIIU.*

\begin{math}
MI \rightarrow (2. x = I) MII \rightarrow (2.x = II) MIIII \rightarrow
(2. x = IIII) MIIIIIIII \rightarrow (3. x = M, y = IIIII) MUIIIII
\rightarrow (3. x = MUII, y = \emptyset) MUIIU
\end{math}

##Question 14
*On veut montrer que l'on ne peut pas démontrer MU. Pour ce faire une propriété
qui vous aidera est "le nombre de I n'est pas divisible par 3". Montrer que l'on
ne peut pas démontrer MU.*
