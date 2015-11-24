%Système 2013

#1. Cours
##Question 1.1
a. *Comment le système détermine la réponse à apporter à un signal donné ?*

Chaque signal est identifié par un handler. C'est cette fonction qui sera
appelée à la réception du signal.

b. *Que signifie qu'un processus est en arrière plan ? Le processus peut-il
changer de plan d'exécution ?*

Un processus en arrière plan est un processus qui ne monopolise pas le
terminal. Il reste en vie et dans le système d'exploitation. Il peut changer
de plan grâce à la commande `fg` notamment.

c. *Pourquoi le pipe (tube) anonyme est utilisable qu'entre processus père et un
processus fils ?*

Un pipe anonyme n'est pas nommé et donc on ne peut pas y faire référence hors
de son espace de création. Comme entre père et fils, on a accès aux mêmes
attributs, on ne peut les utiliser que là.

d. *A quoi sert la formule accès aléatoire à un périphérique fait référence ?
A quel type de périphérique cela correspond ?*

Ça fait référence à des périphériques dans lesquels on peut stocker n'importe
quoi, contrairement aux périphériques de flux. Ce sont les clefs USB,
disque-durs, ...

e. *Que sont les déroutements ?*

Ce sont les interruptions dépendant du programme, quand l'OS prend la main sur
le programme pour gérer quelque chose, comme une erreur ou une violation
d'accès.

f. *Comment le père peut il connaitre le pid d'un processus fils ? Comment un
processus fils peut-il connaître le pid de son père ?*

```c
int pid = fork(); //retour du fork = pid du fils
if (pid == 0) {
    //code du fils
}
else {
    //code du père
}

getppid(); //donne le pid du processus père
```

g. *Quand une page est remplacée par une autre lors d'un défaut de page, quelles
sont les instructions à réaliser pour maintenir le système cohérent ?*

- Sauvegarder les objets éventuels si la page à retirer à été modifiée
- Mise à jour de la table des pages
- Charger la nouvelle page

h. *A quoi sert le principe du DMA ?*

Direct Memory Access :

Gère les transferts de données du disque à la RAM à la place du CPU. Celui-ci
peut donc effectuer un autre tâche pendant ce temps.


i. *Quelles sont les différences entre les processus et les threads ?*

Les threads ont une zone mémoire réduite (pile, et flot de contrôle). Ils
dépendent d'un processus et ont un espace d'adressage partagé.

j. *Comment un processus lisant dans un pipe (tube) peut savoir qu'il n'y a plus
rien à lire ?*

Le processus attend d'avoir qu'il n'y ait plus de processus en vie dans le pipe.

#2. Hiérarchie de processus
##Question 2.1
*Écrire le code `C` dont l'exécution engendre les 7 processus pères par l'arbre
généalogique suivant à l'aide de 2 boucles `for`. Les processus, en dehors
de la boucle `for` doivent afficher leur numéro (figurant sur la figure) avec
un seul `printf` exécuté par tous les processus en utilisant des variables
éventuellement partagées.*

```c
int val = 0.0;
int pidP = getpid();
for (int i = 0 ; i < 5 ; i++) {
    if (pidP == getpid()) {
        fork();
        if (i == 0 && pidP != getPid()) {
            int pidPP = getpid();
            val = i++;
            for (int j = 0 ; j < 2 ; j++) {
                if (pidPP == getpid()) {
                    fork();
                } else {
                    val = i + 1 + ((j + 1) / 10);
                    break;
                }
            }
        }
    } else if (piddP != getpid()) {
        val = i + 1;
    }
}
printf("%f\n", val);
```
#3. Pagination

Un ordinateur a 16 pages d'adressage virtuelle mais seulement 4 pages physiques.
Au départ, la mémoire est vide. Un programme référence les pages virtuelles
dans l'ordre : 0, 7, 2, 7, 5, 8, 9, 2, 4, 2.

##Question 3.1

*Quelles sont les références mémoire qui provoqueront des défauts de page (1)
avec l'algorithme LRU, avec l'algorithme FIFO (2) et (3) quels sont les
remplacements optimaux ?*

1. On a 8 défauts en LRU

```
LOAD : défaut mémoire
OK   : pas de défaut mémoire

0 : LOAD (0)
7 : LOAD (0/7)
2 : LOAD (0/7/2)
7 : OK   (0/2/7)
5 : LOAD (0/2/7/5)
8 : LOAD (2/7/5/8)
9 : LOAD (7/5/8/9)
2 : LOAD (5/8/9/2)
4 : LOAD (8/9/2/4)
2 : OK   (8/9/4/2)
```

2. On a également 8 défauts en FIFO

```
0 : LOAD (0)
7 : LOAD (0/7)
2 : LOAD (0/7/2)
7 : OK   (0/7/2)
5 : LOAD (0/7/2/5)
8 : LOAD (7/2/5/8)
9 : LOAD (2/5/8/9)
2 : OK   (2/5/8/9)
4 : LOAD (5/8/9/4)
2 : LOAD (8/9/4/2)
```

3. On remarque que les pages 2 et 7 sont les seules à être appelées plusieurs
fois. La solution suivante propose 7 défauts, on ne pourra pas faire mieux.

```
0 : LOAD (0)
7 : LOAD (0/7)
2 : LOAD (0/7/2)
7 : OK   (0/7/2)
5 : LOAD (0/7/2/5)
8 : LOAD (7/2/5/8)
9 : LOAD (2/5/8/9)
2 : OK   (5/2/8/9)
4 : LOAD (2/8/9/4)
2 : OK   (8/2/9/4)
```


#4. Traduction d'adresse

Un système utilise des pages de `4Ko`. Les adresses virtuelles sont codées sur
`32 bits`.

##Question 4.1

*Quelle est la taille de l'espace virtuel de chaque processus ?*

On a 2^^32^^ bits d'adressage, on peut adresser jusqu'à 2^^32^^ processus.

##Question 4.2

*Les tables des pages de chaque processus sont gérés par le Système
d'Exploitation et elles sont donc stockées dans une zone mémoire réservée à
celui-ci. Le noyau d système d'exploitation n'utilise pas lui-même la pagination
(i.e. toutes les adresses mémoire manipulées par le noyau du SE sont des
adresses réelles). A votre avis, pourquoi ?*

Si on manipule les adresses réelles, on a pas de défaut de page, ni de
traduction et donc les accès mémoire se font plus rapidement.

##Question 4.3

*Combien de place prend la table des pages de chaque processus en mémoire en
supposant que chaque entrée de la table utilise `4 octets` (`20 bits` pour le numéro
de page réelle et `12 bits` pour gérer l'état) ?*

On a 2^^20^^ pages, ce qui correspond à 2^^20^^ entrées dans la table de pages.
Chaque entrée fait 4 octets. La table des pages fait donc 2^^20^^ * 4o = 4Mo.

*Pour réduire l'espace mémoire occupé par les tables des pages, on utilise des tables des pages à plusieurs niveaux (ici deux).*

*Le numéro de page est découpé en 2 parties égales. La première sert à adresser
une table des livres. Chaque entrée de la table des livres référence une table
des pages. La seconde partie du numéro de page sert à adresser dans cette table
qui contient le numéro de page réelle et les bits d'état.*

##Question 4.4
*Avec la table suivante (les bits d'états des pages ne sont pas indiqués),
traduire les adresses virtuelles `802EF5`, `402AAF`, `5003` et `403345`.*

*Respectivement en binaire : `1000 0000 0010 1110 1111 0101`,
`0100 0000 0010 1010 1111`, `0101 0000 0000 0011` et
`0100 0000 0011 0011 0100 0010`.*

On obtient :

```
0000 0000 10 00 0000 0010 1110 1111 0101
   Livre    |    Index   | Déplacement
Livre : 10 => 2
Index : 10 => 2

L'adresse virtuelle 802ef5 devient donc 2ccf4ef5

On a de la même façon :

402aaf -> 34ceaaf
5003   -> 34fe003
403345 -> 64a345
```

##Question 4.5
*Quelle va être la taille nécessaire pour stocker ces tables pour un
processus utilisant `100Ko` de mémoire ? `1Mo` de mémoire ? `10Mo` de mémoire ?*

100Ko : 100/4 = 25 pages.

25 pages < 2^^10^^ donc 1 livre

1 table des livres + 1 table des pages

1 * 2^^10^^ * 4 + 1 * 2^^10^^ * 4 = 8Ko

1Mo : 100/4 = 250 pages

1 livre = 8Ko

10Mo : 2500 pages

3 livres = 4Ko + 3 * 4Ko = 16Ko

#5. Déroutement

##Question 5.1
*Quels sont les affichages possibles suite à l'exécution en parallèle des
processus PA et PB ?*
```
A = 2, B = 6
B = 5, A = 6

A = 2, B = 5
B = 5, A = 2

A = 2, B = 2

A = 5, B = 5
```
#6. Synchronisation entre processus

Soit trois processus `P1`, `P2` et `P3` dont les pseudo-codes spécifiques sont
les suivants :
```
P1                  P2                  P3

while(1){           while(1){           while(1){
    printf("A");        printf("B");        printf("C");
}                   }                   }
```
Le résultat de l'exécution des trois processus doit être une chaîne caractère
où le caractère `A` est suivi du caractère `B`, qui lui est suivi du caractère
`C`. Comme dans l'exemple suivant :

`ABCABCABCABCABCABC ...`

##Question 6.1
*Donner le code des processus `p1`, `p2` et `p3`.*

```
init(A, 1);
init(B, 0);
init(C, 0);

P1                  P2                  P3

while(1){           while(1){           while(1){
    P(A);               P(B);               P(C);
    printf("A");        printf("B");        printf("C");
    V(B);               V(C);               V(A);
}                   }                   }
```

Le résultat de l'exécution des trois processus doit être une chaîne caractère
où le caractère `A` est alterné avec le caractère `B` ou le caractère `C`.
Comme dans l'exemple suivant :

`ABACABABACABAC ...`

##Question 6.2
*Donner le code des processus `p1`, `p2` et `p3`.*

```
init(A, 1);
init(BC, 0);

P1                  P2                  P3

while(1){           while(1){           while(1){
    P(A);               P(BC);              P(BC);
    printf("A");        printf("B");        printf("C");
    V(BC);              V(A);               V(A);
}                   }                   }
```

Nous voulons que la séquence `ABC` et la séquence `ACB` s'alternent strictement.
Comme dans l'exemple suivant :

`ABCACBABCACBABC ...`

##Question 6.3
*Donner le code des processus `p1`, `p2` et `p3`.*

```
init(A,0);
init(B,0);
init(C,0);

P1                  P2                  P3

int i = 0;          while(1){           while(1){
while (true) {          P(B);               P(C);
  printf("A");          printf("B");        printf("C");
  if (i++ == 0) {       V(A);               V(A);
    V(B);           }                   }
    P(A);
    V(C);
    P(A);
  } else {
    V(C);
    P(A);
    V(B);
    P(A);
  }
  i %= 2;
}
```

#7. Salle de bain

La salle de bain commune des pensions mixte est utilisée par les filles et les
garçons. Les règles suivantes sont observées :

- les filles peuvent utiliser simultanément la salle de bain ;
- les garçons peuvent utiliser simultanément la salle de bain ;
- la salle de bain n'est jamais utilisée simultanément par des garçons et des
filles.

##Question 7.1
*Donner le code des fonctions :*
```
void boy_want_to_use_bathroom();
void boy_leaves_bathroom();
```

Solution des lecteurs/écrivains

```
init(mutex[2], 1);
init(bathroom, 1);
int boy, girl = 0;

boys_want_to_use_bathroom(){        boy_leaves_bathroom(){
    P(mutex[0]);                        P(mutex[0]);
    if(boys == 0) {P(bathroom);}        boys--;
    boys++;                             if(boys == 0){V(bathroom);}
    V(mutex[0]);                        V(mutex[0]);
}                                   }
```

*Pour éviter que les garçons ne monopolisent la salle de bain, on décide qu'à
partir du moment où une fille se présente à la porte de la salle de bain, alors
qu'elle est occupée par des garçons, alors, elle ne peut se faire doubler par au
maximum 2 garçons avant de pouvoir utiliser la salle de bain (en attendant que
les garçons sortent de la salle de bain).*

##Question 7.2

*Pour prendre en compte cette modification, modifier les fonctions :*
```
void boy_want_to_use_bathroom();
void boy_leaves_bathroom();
void girl_want_to_use_the_bathroom();
void girl_leaves_bathroom();
```
