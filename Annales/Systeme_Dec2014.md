%Système Décembre 2014

#1. Cours
##Question 1.1

a. *Expliquer la problématique associée à l'exclusion mutuelle ainsi que la
notion de section critique. Que dire de cela dans le cas de systèmes
non-péemptifs ?*

b. *Donner un exemple de stratégie d'ordonnancement qui peut conduire à des
famines. Expliquez pourquoi.*

c. *On imagine un ordinateur muni de suffisamment de mémoire vive pour stocker
toutes les données nécessaires à l'ensemble des processus qui s'exécutent en
même temps. Quel phénomène cela permit-il d'éviter ? L'utilisation de mémoire
virtuelle est-elle utile ?*

d. *Expliquer la différence en mécanisme de synchronisation synchrone et
asynchrone. Donner un exemple de chaque type de mécanisme.*

e. *Expliquer pourquoi l'ensemble de la mémoire centrale ne peut pas être
paginée.*

f. *Si la valeur de retour de la fonction `getppid()` est 1, cela signifie
quoi ?*

Le père du processus est `init`.

g. *Comment le système d'exploitation peut être informé de la fin d'un transfert
vers un périphérique ?*

h. *Que se passe-t-il lorsqu'un processus tente d'accéder à une page qui n'est
pas dans la mémoire centrale ?*

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

On peut adresser 2^^64^^ pages. Cela prend en mémoire 128bits * 2^^64^^.

*Pour éviter de stocker la table des pages de manière contiguë en mémoire, on
décide de la hiérarchiser à son tour. On considère ici une pagination à 3
niveaux (segments, livres, pages).*

##Question 3.2
*Donner une structure possible pour une adresse c'est à dire l'utilisation des
différents bits qui la composent.*

##Question 3.3
*Quelle est la taille mémoire adressable par un processus ?*

##Question 3.4
*Représenter les différents éléments intervenant lors de l'accès à une donnée
en mémoire.*

##Question 3.5
*Quel est le nombre d'accès mémoire nécessaires pour accéder à une donnée ?
Quelles sont les conséquences sur les performances par rapport à un système :*

- *avec une mémoire paginée avec une table des pages uniques ?*
- *sans mémoire paginée ?*

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
