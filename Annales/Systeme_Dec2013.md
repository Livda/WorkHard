%Système Décembre 2013

#1. Cours
##Question 1.1

a. *Pourquoi l'algorithme LRU , n'est pas employé tel-quel pour déterminer
quelle page vider de la mémoire ?*

b. *Dans quels états un processus peut-il se retrouver après avoir été dans
l'état "prêt" ?*

c. *Pourquoi pour certains signaux, on ne peut ni les masquer, ni redéfinir le
handler ? Citez 2 exemples de tels signaux.*

d. *Quelles sont les différences entre la notion de programme et la notion de
processus ?*

e. *Pourquoi un processus se retrouve dans l'état "suspendu" ?*

f. *Comment entre deux programmes, peut-on identifier un segment de mémoire
partagée ?*

g. *Comment le système d'exploitation permet-il à un programme de communiquer
avec le contrôleur d'un périphérique ?*

h. *Pourquoi les pages en mémoire vive sont-elles conservées sur le disque ?*

i. *Dans un système de traduction d'adresses donné qui requiert de nombreux
accès mémoire pour traduire les numéros de page virtuelle en numéro de page
réelle, avec quel mécanisme peut-on espérer accélérer la traduction ?*

j. *Comment sont gérés les threads du point de vue de l'ordonnanceur ?*

#2. Comment ça marche
*On considère le programme dont le code est le suivant :*
```C
int main(int argc, char* argv[]) {
    int pid;
    printf("Debut de l'application\n");
    pid = fork();
    if (pid ==0) {
        printf("Avant exec\n");
        execl ("/bin/echo", "mon_prog", "truc", "machin", "toto", NULL);
        printf("Apres exec\n");
        exit(0);
    }
    wait(NULL);
    printf("Fin application\n");
    return 0;
}
```

##Question 2.1
*Donnez les affichages possibles issus de l'exécution de ce programme.*

*Soit le programme suivant :*
```C
int main() {
    int i = 0;
    while(i < 2) {
        printf("i: %d\n", i);
        fork();
        fork();
        i++;
    }
    return 0;
}
```

##Question 2.2
*Donnez l'arbre de filiation des processus générés ainsi que leurs affichages.*

#3. Problème du toast
Considérons un système composé de 3 processus mangeur de toasts et d'un
serveur. Un toast est composé nécessairement des 3 composants suivants : une
tranche de pain, du beurre et de la confiture. Chaque processus mangeur de
toast ne possède des éléments que d'un type. Un processus mangeur possède donc
des portions de beurre, un autre des portions de confiture et le dernier des
tranches de pain. Le serveur possède lui des 3 aliments en quantité illimitée.
Les processus mangeurs ne communiquent pas entre eux et n'échangent donc jamais
d'aliments entre eux.

Le comportement du système est le suivant :

- le serveur pose sur la table 2 aliments choisi au hasard parmi les trois et
attend ;
- un processus mangeur prend les aliments dont il a besoin sur la table pour
faire un toast, fait son toast, le mange puis demande au serveur de
recommencer ;
- le cycle recommence.

#Question 3.1
*Donnez le code, utilisant les sémaphores pour la synchronisation, des processus
mangeurs et du serveur à l'aide de sémaphores.*

#4. Producteurs / Consommateurs
On se place dans le contexte producteurs / consommateurs. Donc un ensemble de
processus sont producteurs, ils fournissent des informations que les processus
consommateurs utilisent. Les contraintes sont classiques :

- une information ne doit pas être écrasée sans avoir été consommée ;
- une information ne être consommée que pas un seul consommateur.

La communication des informations entre les producteurs et les consommateurs est
réalisée par un buffer de taille `n`.

#Question 4.1
*Donnez le code des processus producteurs, consommateurs en java en n'utilisant
pas de sémaphores.*

#5. Pagination

On considère un système de mémoire virtuelle propre à chaque processus, associé
à un adressage sur `32 bits`. Le découpage de la mémoire virtuelle est le
suivant :

- la taille des pages est de `2Ko` (2048 octets) ;
- des segments de `1024 pages virtuelles`.

Les descripteurs contenus dans les tables des segments et dans les tables des
pages ont une taille de `64 bits` et contiennent entre autre le numéro de page
réelle, l'adresse disque et le bit de validité (`bit V`).

La mémoire centrale (réelle) compte au total 15 cases numérotées de 0 à 14 (une
case permet de stocker une page). Dans ce contexte, on considère deux processus
`A` et `B`.

Le processus `A` a une espace d'adressage composé de trois segments que l'on
nomme : `SA1`, `SA2` et `SA3` qui sont respectivement de `8Ko`, `12Ko` et `4Ko`.

Le processus `B`a un espace de stockage composé de deux segments que l'on nomme
: `SB1` et `SB2` qui sont respectivement de `16Ko` et `8Ko`.

Pour le processus `A`, seules les pages 0 et 1 du segment `SA1`, la page 1 du
segment `SA2` et la page 0 du segment `SA3` sont chargées en mémoire centrale
respectivement dans les cases 4, 5, 10 et 6.

Pour le processus `B`, seules les pages 0 et 2 du segment `SB1`, et la page 0 du
segment `SB2` sont chargées en mémoire centrale respectivement dans les cases
11, 2 et 14.

##Question 5.1
*Donnez le découpage de l'adresse virtuelle en numéro de segment, numéro de page
et déplacement.*

##Question 5.2
*L'adressage réel étant sur `16 bits`, quelle est la taille du numéro de page
réelle ?*

##Question 5.3
*Pour les processus `A` et `B`, donnez l'espace mémoire nécessaire pour stocker
les tables de traduction.*

##Question 5.4
*Représentez par un dessin les structures allouées (tables de segments, tables
des pages) et la mémoire centrale correspondant à l'allocation décrite pour les
processus `A` et `B`.*

##Question 5.5
*Si `1000 0000 0010` et `10 0000 0000 1001 0100 1001` sont dans les mêmes
adresses virtuelles en binaire pour `A`, déterminez les adresses réelles
correspondantes. Même question avec `1000 0000 1010` et `1 0000 0010 0010` pour
B.*
