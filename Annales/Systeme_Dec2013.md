%Système Décembre 2013

#1. Cours
##Question 1.1

a. *Pourquoi l'algorithme LRU , n'est pas employé tel-quel pour déterminer
quelle page vider de la mémoire ?*

LRU pas utilisé tel quel car :

- Stocker la date de dernière utilisation (gourmand en mémoire)
- Parcourir toute la taille des pages pour prévoir une page à vider (gourmand
en temps)

b. *Dans quels états un processus peut-il se retrouver après avoir été dans
l'état "prêt" ?*

"prêt"  -> "en exécution"

        -> "terminé"

c. *Pourquoi pour certains signaux, on ne peut ni les masquer, ni redéfinir le
handler ? Citez 2 exemples de tels signaux.*

Signaux KILL et SUSPEND. Il faut bien le moyen à l'OS de reprendre la main sur
un processus qui se comporte bizarrement.

d. *Quelles sont les différences entre la notion de programme et la notion de
processus ?*

Programme : description statique de l'exécution.

Processus : machin dynamique qui l'exécute.

e. *Pourquoi un processus se retrouve dans l'état "suspendu" ?*

On peut mettre un processus en arrière plan via le raccourci `ctrl + z` ou via
un signal SUSPEND. On peut faire revenir un processus en premier plan avec la
commande `bg %job`.

f. *Comment entre deux programmes, peut-on identifier un segment de mémoire
partagée ?*

On peut partager un segment mémoire entre deux processus grâce à la clef de ce
segment ou de son id.

g. *Comment le système d'exploitation permet-il à un programme de communiquer
avec le contrôleur d'un périphérique ?*

En écrivant dans l’adresse mémoire associée au contrôleur ou sur numéro de port.

h. *Pourquoi les pages en mémoire vive sont-elles conservées sur le disque ?*

En cas de non utilisation de la page chargée en mémoire vive, l’OS ne recopie
pas la page dans le disque. ne pas garder cette page sur le disque créerai donc
une perte de donnée.

i. *Dans un système de traduction d'adresses donné qui requiert de nombreux
accès mémoire pour traduire les numéros de page virtuelle en numéro de page
réelle, avec quel mécanisme peut-on espérer accélérer la traduction ?*

On peut accélérer avec une cache.

j. *Comment sont gérés les threads du point de vue de l'ordonnanceur ?*

Par défaut les threads sont visibles par l’ordonnanceur, ils sont traités comme
n'importe quel processus pour l’ordonnanceur. Ils peuvent être invisible pour
l'ordonnanceur. Dans ce cas, les threads gèrent eux-mêmes leur temps
d'exécution.

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

```C
Debut application
Avant exec
mon_prog truc machin toto
Fin application
```

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

```
                                p
             /              /      \     \
            f1             f2      f3   f4
     /       |     \     /   \     |
   f11      f12   f13   f21  f22  f31
  /   \      |           |
f111 f112   f121       f211
  |
f1111

p :
i: 0
i: 1

f1
i: 1

f11
i: 1

f2
i: 1
```

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

```C
int init(){
    consummer = {sem(0),sem(0),sem(0)};//pas de nourriture sur la table au début
    server = sem(0);
}

//tranche de pain : 0
//beurre : 1
//confiture : 2
int server(){
    while(1){
        int to_keep = get_random();//int aléatoire entre  0 et 2
        V(consummer[to_keep]);
        P(server);
    }
}

//i : l’élement qu’il a
int consummer(int i){
    while(1){
        P(consummer[i]);
        eat();
        V(server);
    }
}
```

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

```java
char buffer[n];
int status[n];
int nb_vide = n;
int nb_plein = 0;
Object p;

void producteur() {
    int i;
    while(1){
        synchronized(this) {
            while(nb_vide == 0) {
                this.wait();
            }
            nb_vide --;
        }
        synchronized(p){
            for (int i=0, i < n, i++){
                if (status[i] == -1) {
                    status[i] == 0;
                    break;
                }
            }
        }
        buffer[i] = ...;
        status[i] = 1;
        synchronized(this) {
            nb_plein++;
            notifyAll();
        }
    }
}

void consommateur() {
    int i;
    while(1){
        synchronized(this) {
            while(nb_plein == 0) {
                this.wait();
            }
            nb_plein --;
        }
        synchronized(p){
            for (int i=0, i < n, i++){
                if (status[i] == 1) {
                    status[i] == 0;
                    break;
                }
            }
        }
        ... = buffer[i];
        status[i] = -1;
        synchronized(this) {
            nb_vide++;
            notifyAll();
        }
    }
}
```

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

```
| segment |  page   | déplacement |
| 11 bits | 10 bits |   11 bits   |
```

##Question 5.2
*L'adressage réel étant sur `16 bits`, quelle est la taille du numéro de page
réelle ?*

Le déplacement est toujours sur 11 bits, il reste donc 5 bits pour le numéro de
page réelle.

##Question 5.3
*Pour les processus `A` et `B`, donnez l'espace mémoire nécessaire pour stocker
les tables de traduction.*

La taille des segments est en réalité la taille mémoire occupée par leur page.
Du coup, pour les deux processus, la taille en nombre de pages de chaque segment
est bien inférieure à 1024.

Il nous faut donc 3\*(taille d’une table des pages = 1024\*64bits = 1024\*8 octets)
+ taille d’une table des pages à trois segment = 3\*8

- pour A, ce qui donne 40Ko.
- pour B, on remplace les 3 par 2 : 32Ko


##Question 5.4
*Représentez par un dessin les structures allouées (tables de segments, tables
des pages) et la mémoire centrale correspondant à l'allocation décrite pour les
processus `A` et `B`.*

```
A                                           Mémoire centrale
 _______         _______                     ______________
|_______|-------|_______|--- 4              |______________| 0
|_______|--     |_______|--- 5              |______________| 1
|_______|- \    |_______|                   |______________| 2
|       | \ \    _______                    |______________| 3
|       |  \ ---|_______|                   |______________| 4
|       |   \   |_______|--- 10             |______________| 5
|_______|    \   _______                    |______________| 6
              --|_______|--- 6              |______________| 7
                |_______|                   |______________| 8
                                            |______________| 9
                                            |______________| 10
                                            |______________| 11
                                            |______________| 12
                                            |______________| 13
                                            |______________| 14
```

##Question 5.5
*Si `1000 0000 0010` et `10 0000 0000 1001 0100 1001` sont dans les mêmes
adresses virtuelles en binaire pour `A`, déterminez les adresses réelles
correspondantes. Même question avec `1000 0000 1010` et `1 0000 0010 0010` pour
B.*

1 00000000010 -> page 1 du segment “1” -> page reelle 5 -> 0010100000 000010

1 0000000001 00101001001 -> page 1 du segment “2” -> page reelle 10 -> 0101000101001001

1 00000001010 -> page 1 du segment “1” -> ??? pas chargée en mémoire. A priori, i
l en prend donc une qui est libre, mais laquelle?

10 00000100010 -> page 2 du segment “1” -> page reelle 2 -> 0001000000100010
