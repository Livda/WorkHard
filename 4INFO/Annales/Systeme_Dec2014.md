%Système Décembre 2014

#1. Cours
##Question 1.1

a. *Expliquer la problématique associée à l'exclusion mutuelle ainsi que la
notion de section critique. Que dire de cela dans le cas de systèmes
non-péemptifs ?*

Lorsque deux programmes doivent accéder (modifier) à une même ressource, il est
nécessaire d’empêcher qu’ils y accèdent en même temps. Le code qui n’autorise
qu’un seul processus est appelé section critique.

Un système non-préemptif ne peut pas être interrompu, donc la notion de section
critique n'a pas de sens.

b. *Donner un exemple de stratégie d'ordonnancement qui peut conduire à des
famines. Expliquez pourquoi.*

Dans le cas d’une stratégie d’ordonnancement basée sur la longueur de la tâche,
on peut avoir une famine en cas d'une tâche très longue, elle ne s'exécuteras
jamais s'il y a toujours des tâches courtes.

c. *On imagine un ordinateur muni de suffisamment de mémoire vive pour stocker
toutes les données nécessaires à l'ensemble des processus qui s'exécutent en
même temps. Quel phénomène cela permit-il d'éviter ? L'utilisation de mémoire
virtuelle est-elle utile ?*

La pagination et les adresses virtuelles permettent d'éviter le swap (permet
d'adresser plus d'adresse que la memoire ne le permet). Les addr. virtuelles
permettent néanmoins de cantonner un processus dans sa propre partie de la
mémoire (permet de le protéger des autres processus).

d. *Expliquer la différence en mécanisme de synchronisation synchrone et
asynchrone. Donner un exemple de chaque type de mécanisme.*

Vu dans le cours (p38). La synchronisation synchrone est prévisible. La
synchronisation asynchrone est imprévisible et peut entraîner un déroutement.

synchrone : on attend un retour ou un certain état.
asynchrone : cela peut arriver n'importe quand dans le code.

1. asynchrone = émis par un processus ou le système via des signaux
2. synchrone = via des sémaphores et des pipes

e. *Expliquer pourquoi l'ensemble de la mémoire centrale ne peut pas être
paginée.*

Parce qu’il faut stocker en mémoire la traduction des pages.

f. *Si la valeur de retour de la fonction `getppid()` est 1, cela signifie
quoi ?*

Le père du processus s’est terminé avant d’appeler l’instruction getppid(), tous
ses enfants ont dont été réassignés en tant qu’enfant du processus initial, dont
le pid est 1.

g. *Comment le système d'exploitation peut être informé de la fin d'un transfert
vers un périphérique ?*

Le système d'exploitation peut être informé de la fin d'un transfert de données
par une interruption levée par le DMA (Direct Memory Access).

h. *Que se passe-t-il lorsqu'un processus tente d'accéder à une page qui n'est
pas dans la mémoire centrale ?*

Il y a défaut de page. On doit donc charger la page physique correspondante dans
la mémoire centrale.

#2. Section critique

Pour réaliser une section critique, on propose le code suivant :
```C
#define LIBRE 1
#define OCCUPE 0

//verrou : adresse d'une variable partagée entre plusieurs processus

void enter_section (int *verrou) {
    while (*verrou == OCCUPE) sleep(1);
    *verrou = OCCUPE;
}

void sortir_section (int *verrou) {
    verrou = LIBRE;
}
```

##Question 2.1
*Donner votre avis sur ce code.*

L'opération `entrer_section` n'est pas atomique et ne permet pas de garantir de
garantir l'exclusion mutuelle.

*Pour réaliser une section critique, on propose aussi le code suivant :*
```java
public class SC {
    private boolean verrou;

    public SC () {
        verrou = false;
    }

    public synchronized void enter_section () throws InterruptedException {
        while (this.verrou) Thread.sleep(1);
        this.verrou = true;
    }

    public synchronized void sortir_section () {
        verrou = false;
    }
}
```

##Question 2.2
*Donner votre avis sur ce code.*

Le `synchronized` permet de garantir les sections critiques, même un peu trop,
si un processus est bloqué dans la boucle de `entrer_section` il bloque l’accès
à tous les autres processus d’exécuter `sortir_section`.

(En java, `sleep(long millis)`, utilise des milisecondes, donc avec `1`  la
pause est négligeable.)

#3. Pagination
Lorsque la table des pages est de petite taille (environ une dizaine de pages),
elle est implémentée avec des registres rapides. Cependant, devant la taille des
espaces d'adressage de systèmes actuels, cette solution devient rapidement
inutilisable.

On s’intéresse à un système à mémoire paginée gérant des adresses sur `64 bits`.
La taille des pages est de `4Kio`. Une entrée de la table des pages est stockée
sur `128 bits`.

##Question 3.1
*Quel est le nombre maximum d'entrées d'une table des pages ? Quelle est la
taille d'espace mémoire nécessaire pour stocker cette table en totalité ?*

Une page fait 4Ko, soit 4096 octets, soit 2^^12^^ octets. (4Ko = 2^^12^^ o).

Les adresses font 64bits, on a déjà 12 bits pour le déplacement. 64 -12 -> il
reste 52 bits pour adresser la page. 2^^52^^ = 4.5 10^^15^^ => nb d’entree d’une
table = 2^52

128bit = 16o = 2^^4^^ => taille d’espace d’une table en totale = 2^^52^^ *
2^^4^^ soit 72 Petaoctets ?

*Pour éviter de stocker la table des pages de manière contiguë en mémoire, on
décide de la hiérarchiser à son tour. On considère ici une pagination à 3
niveaux (segments, livres, pages).*

##Question 3.2
*Donner une structure possible pour une adresse c'est à dire l'utilisation des
différents bits qui la composent.*

52/3 ->  17.3, OUTCH, pas facile d’etre équitable..

Segments : 18 bits, Livres : 17 bits, Pages, 17 bits. Déplacement dans la page:
12bits.

##Question 3.3
*Quelle est la taille mémoire adressable par un processus ?*

Comme on a des adresses sur 64 bits : 2^^64^^ octets soit 2^^24^^To

##Question 3.4
*Représenter les différents éléments intervenant lors de l'accès à une donnée
en mémoire.*

##Question 3.5
*Quel est le nombre d'accès mémoire nécessaires pour accéder à une donnée ?
Quelles sont les conséquences sur les performances par rapport à un système :*

- *avec une mémoire paginée avec une table des pages uniques ?*
- *sans mémoire paginée ?*

Il y a 4 accès mémoires (segments, livres, pages, déplacement). Soit 3 accès de
plus qu’une mémoire non paginée et 2 accès de plus qu’une mémoire paginée avec
une table des pages unique.


*Pour limiter la dégradation des performances, on utilise une mémoire cache à
consultation rapide, appelée TLB (Translation Look-aside Buffer) ou registres
associatifs. Des correspondances adresses virtuelles / adresses réelles sont
stockées dans ces registres. La gestion du contenue de la TBL est telle que,
lors de l'accès à une donnée, dans 90% des cas, son numéro de page figure dans
les registres.*

*Un accès mémoire dure 100ns et un accès à la TBL 15ns.*

##Question 3.6
*Quel est le temps moyen effectif d'accès à une donnée dans l'organisation
multi-niveaux ?*

Au dessus on a dit qu’on avait 4 accès mémoires pour récupérer une valeur. On
souhaite rajouter un cache appelé le TLB. Dans 90% des cas, on va hit, et on va
directement lire la valeur là ou elle est dans la mémoire : 90% * (T[TLB] +
T[MEM]). Dans les 10% restants, on a fait une requête au TLB, qui n’a pas
abouti, puis on a fait une requete au segment, livre, page et enfin on récupère
la valeur (4 accès mémoire). Donc 10% * (T[TLB] + 4 * T[MEM])

On a au final T[MOYEN] = 0.9 * (T[TLB] + T[MEM]) + 0.1 * (T[TLB] + 4 * T[MEM]) =
145ns

#4. Pool de Threads
Pour gérer un ensemble de tâches, `n` threads sont chargés de se répartir les
tâches.

Pour se faire, les tâches sont insérées dans une liste partagée au fur et à
mesure de leur création. Les tâches doivent être traitées une unique fois.

Les threads sont en attente tant qu'il n'y a pas de tâche à traiter. Quand il y
a une tâche à traiter, un thread prend la tâche dans la liste (en la retirant de
la liste), la traite, puis prends une nouvelle tâche dans la liste s'il en
reste. L'objectif est de permettre le plus de parallélisme possible entre les
threads.

##Question 4.1
*Implémenter en Java le pool de threads en utilisant uniquement des sémaphores.*

~~~~ {#mycode .java .numberLines}
import java.util.ArrayDeque;
import java.util.concurrent.Semaphore;

public class CustomThreadPool {

    private CustomThread[] tab;
    private ArrayDeque<Runnable> tasks = new ArrayDeque<Runnable>();
    private Semaphore mutex = new Semaphore(1);
    private Semaphore needWork = new Semaphore(0);

    public CustomThreadPool(int nbThreads) {
     tab = new CustomThread[nbThreads];
     for (int i = 0; i < nbThreads; i++) {
         tab[i] = new CustomThread();
         tab[i].start();
     }
    }

    public void addTache(Runnable task) {
     mutex.aquire();
     tasks.addLast(task);
     mutex.release();
     needWork.release();
    }

    private class CustomThread extends Thread {
     public void run() {
         while (true) {
             try {
                 needWork.acquire();
                 mutex.acquire();
             } catch (Exception e) {
                 break;
             }
             Runnable task = tasks.removeFirst();
             mutex.release();

             task.run();
         }
     }
    }
}
~~~~
