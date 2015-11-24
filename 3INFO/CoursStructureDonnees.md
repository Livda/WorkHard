#Cours sur les Structures De Données
[TOC]

#Chapitre 1 : Les Types Abstraits de Données

##I - Introduction

Pourquoi abstraire ?

- Mieux comprendre : "conceptualiser"
- Solution pour réutilisable
- Faire des preuves de bonnes propriétés : "démonstrations"

Comment abstraire ?

- Bien séparer les préocupations
- Utiliser des formalismes/des modèles

##II - Séparation, mise en oeuvre / utilisation
=Rôle d'une interface Java

- Plusieurs mises en oeuvre différentes
- Les utilisateurs n'ont pas à se préoccuper ou à savoir comment la mise en oeuvre est faite. Ils ne sont pas parasités par des détails inutiles.

**Exemple d'une modélisation de liste en Java**
```JAVA
	public interface Pile {
		void empiler (Object ob);	//Pour empiler un élément
		void depiler();				//Pour dépiler un élément
		Object sommet();			//Pour consulter l'élément au sommet de la pile
		boolean pileVide();			//Indique si la pile est vide
	}
```
**Bilan**

On a ainsi spécifié

- toutes les actions modifiant une pile
- toutes les actions consultant une pile

mais pas

- comment est conservé cet état

Ensuite on peut concrétiser cette interface en faisant un choix d'implémentation. Ici on propose de stocker l'état de la pile via un tableau.
```JAVA
	public class PileTabulee implements Pile {
		private int pointeur;
		private Object[] tab;
		static final int N = 100;

		public PileTabulee(){//N.B. un constructeur ne peut pas être défini dans l'interface Java
			tab = new Object[N];		   
			pointeur = 0;
		}

		public void empiler(Object ob){
			if (pointeur < N) {
				tab[pointeur] = ob;
				pointeur ++;
			}
		}

		public void depiler(){
			if (!pileVide())
				pointeur --;
		}

		public Object sommetPile(){
			if (!pileVide()){
				return tab[pointeur-1];
			}else{
				else return null;
			}
		}

		public boolean pileVide(){
			return pointeur ==0;
		}
	}
```

>*N.B.* Ici la gestion des erreurs est rudimentaire, normalement on procède autrement en Java.

>*N.B.* On aurait pu simplement implémenter l'interface d'une autre façon. Par exemple en remplissant le tableau dans l'autre sens ...

On a bien ainsi :
```JAVA
	public class Pile2 implements Pile {
		private boolean face;
		
		public Object sommetPile(){
			if (!pileVide()) return null;
			else return new Interger(0);
		}

		public void depiler(){
			if (!pileVide()) face = !face;
		}

		public boolean pileVide(){
			return face;
		}

		public void empiler(Object ob){
			face = !face;
		}
	}
```
Donc une interface Java permet aussi de mauvaises mises en oeuvres : le concept d'*interface* en Java n'est pas suffisamment contraignant et n'exprime pas la totalité des propriétés d'une pile ici.

- Il manque une garantie sur la *sémantique* des opérations
- Lever toute ambiguité

##III - Vers des spécifications formelles

Pourquoi ?

- Classifier les ambiguités et se mettre d'accord sur le sens de chaque opération.
- Bâtir une mise en oeuvre respectant la spécification

Comment ?

###1 - Spécification par exemple d'implémentation

			|---| → empiler		sommetPile → |---|			depiler
	|	|		|	|				|	|					|	|
	|	|		|---|←				|---|←					|	|	
	|___|←		|___|				|___|		 			|___|←
	|___|		|___|				|___|					|___|
	|___|		|___|				|___|					|___|

*Problème* : Explicitement lié à un exemple lié à d'implémentation

###2 - Spécification par schéma

			--- → empiler		sommetPile → ---			depiler
				--- ←				--- ←							
	___←		___					___		 			___ ←
	___			___					___					___
	___			___					___					___

*Problème* : Mieux, mais cela reste informel et un peu bricolé.

###3 - Spécification par état

		empiler			empiler
	() 		↔		()		↔		()
	↑↓	depiler		↑↓	depiler		↑↓
				sommetPile


*Problème* : Mieux, toutes les transitions possibles sur l'état sont explicites mais par exemple rien d'explicite sur la valeur produite par sommetPile.

###4 - Spécification algébrique : Type Abstrait de Données

*But* : Exprimer "ce que ça fait" et non "comment ça le fait".

On choisi d'utiliser une notation à la base de fonction mathématiques (≠ méthodes orientées objet) pour définir chacune des opérations. Cela permet de s'imerger dans une monde formel qui permet des preuves.

On définit donc d'abord des *fonctions* exprimant chacune des opérations (donc associées à des domaines de définitions) :

	empiler : pile x objet 	→ pile
	depiler : pile 			→ pile

	Domaine de définition (depiler) = Pile \ { p € Piles | pileVide(p) }
	
	pileVide : pile 		→ boolean

En fait, les TAD proposent d'exprimer les constantes des domaines de définition sous forme de *préconditions* pour les fonctions dites *partielles* (qui restreignent le domaine de définition).
	
	Pour toute Pile p
	pré (depiler(p)) = non pileVide(p)

Ensuite, pour exprimer les propriétés des opérations, on écrit les *axiomes* qui sont les vérités de base propres au type abstrait.

*Exemple* : TAD Pile

	UTILISE boolean, objet

	FONCTIONS
	new : 							→ Pile
	pileVide : 		Pile 			→ boolean
	empiler : 		Pile x Objet 	→ Pile
	depiler : 		Pile 			→ Pile
	sommetPile : 	Pile 			→ Objet

	PRECONDITIONS
	Pour toute Pile p, pour tout objet o

	pre (depiler (p)) 		= non pileVide(p)
	pre (sommetPile (p)) 	= non pileVide(p)

	AXIOMEs
	Pour toute Pile p, pour tout objet o

	sommetPile (empiler (p,o)) = o
	depiler (empiler (p,o)) = p
	pileVide (new) = vrai
	pileVide (empiler (p,o)) = faux

- Les TAD sont définis en termes de fonctions. **ATTENTION** Ne pas faire de référence à une manière d'implémenter particulière.
- Les axiomes permettent de définir la *sémantique* des opérations.
- Les préconditions expriment le domaine de définition des fonctions. Ces fonctions qui sisposent d'une précondition sont appelées *fonctions partielles*.

*N.B.* On peut établir des catégories de fonctions quand le TAD est :

- à droite uniquement → il s'agit de "création" de Pile
- à gauche uniquement → il s'agit d'une "requête"
- des deux côtés → il s'agit de "commandes"

*Exemple* : depiler : Pile → Pile

###5 - On peut effectuer des démonstrations de théorèmes
**Définition** : Un théorème est une propriété que l'on peut démontrer à l'aide des axiomes ou de théorèmes préalablement démontrés.

**Fonctionnement d'une démonstration** :

*Fonctionnement déductif* : On démontre t1 = t2 par transformations successives d'un ou deux termes. On peut remplacer t par t' s'il existe un axiome, ou théorème déjà démontré, tel que t=t'.

*Et on utilise des règles pour décrire les termes* :

- réflexivité : t=t
- symétrique : si t1=t2 alors t2=t1
- transitivité : si t1=t2 et t2=t3, alors t1=t3
- substitution : t1 et t2 sont termes de même sorte à variables dans X, soit x € X et u est de même sorte que x : t1[u/x]=t2[u/x]
- congruence : t1=t1', t2=t2', ..., tn=tn', alors f(t1,t2,...,tn)=f(t1',t2',...,tn')

*Exemple* : Démonstration de pileVide (depiler (empiler (new,e)))=vrai

	depiler(empiler(p,e))=p					//axiome
	depiler(empiler(new,e))=new				//substitution
	pileVide(depiler(new,e))=pileVide(new)	//congruence
	or pileVide(new)=vrai					//axiome
	d'où pileVide(depiler(new,e))=vrai		//transitivité

##IV - Du TAD à la mise en oeuvre Java

On utilise la signature des fonctions du TAD pour définir l'interface utilisateur.

*N.B.* : Les fonctions du TAD qui étaient des *commandes* vont nécessiter un choix du programmeur.

**commande** : production d'un résultat en sortie & mutation de l'entrée avec notion d'état interne

*Exemple* :

	public interface Pile {
		//aucune traduction du new, car aucun constructeur dans les interfaces Java
		void empiler(Object ob);
		Object sommetPile();
		void depiler();
		boolean pileVide();
	}

*N.B.* : ON a utilisé ici la notion simple de Pile d'"Objects" qui est conforme à la description du TAD.

Ensuite on implémente cette interface via une (première) mise en oeuvre :

	public class MaPile implements Pile { ... }

Les axiomes ne devront pas être contredis par les algorithmes d'implémentations et peuvent servir à écrire des tests.

Les préconditions doivent être prises en compte au début de chacunes des fonctiones concernées pour tester si l'utilisation est licite.

*N.B.* : La gestion des erreurs induites par le non respect des préconditions peut suivre plusieurs approches :

- ne pas régir
	-  programmation par contrat : on a explicité la précondition sous forme de contrat que l'utilisateur doit respecter, sinon plantage)
- on peut réagir en renvoyant une valeur spéciale signalant l'erreur
	- un code d'erreur
	- une valeur prédéfinie, par exemple NaN qui se propage ensuite d'opération en opération pour informer de l'erreur
- on peut réagir en levant une exception, si le langage le permet
	- cela permet de découpler les cas normaux des cas exceptionnels
	- le principe consiste à vérifier la précondition dès le début de l'algorithme de l'opération et de lever une exception si elle n'est pas respectée

###1 - Généricité
 Une solution pour ne pas être obligé de définir autant de variantes d'un type abstrait en fonction d'un type externet utilisé. On va définir un seul TAD mais qui sera fonction d'un type inconnu formalisé.

*Exemple* :

	SORTIE Pile[T]

	UTILISE boolean

	FONCTIONS
	new : 							→ Pile
	pileVide : 		Pile 			→ boolean
	empiler : 		Pile x T	 	→ Pile
	depiler : 		Pile 			→ Pile
	sommetPile : 	Pile 			→ T

**Traduction en Java**

	public interface Pile <Type> {
		void empiler(Type e);
		void depiler() throws pileVideException;
		Type sommetPile() throws pileVideException;
		boolean pileVide();
	}

###2 - Bilan sur l'utilsation des TAD
- Approche formelle non ambigue séduisante pour les spécifications.
- Qui permet véritablement de séparer les sécifications de la mise en oeuvre
- pas explicite/exploitable tous les jours car assez fastidieuse et démonstration de correction impossible

###3 - Comment analyser la correction d'un TAD ?
- Contient-il des contradictions ?
- Y a-t-il convergence ?
	- 2 réécritures d'un même terme dans un ordre différent doivent aboutir au même résultat.
	- Il faut qu'une ou plusieurs réécritures de termes via les axiomes finissent par "raccourcir" le terme
- Y a-t-il complétude du TAD ?
	- Peut-on réécrire et simplifier toute expression valide et conforme au TAD ? Sinon c'est qu'il manque dans axiomes.

# Chapitre 2 : Les listes 

## I - Introduction

### 1- Définition

Une liste est une suite finie d'éléments du même type.

*Exemple* : liste de mots
###2 – Utilisation

Algorithmiquement, on peut alors se permettre d'écrire des programmes utilisant ce type (liste) et ces opérations (d'ajout, de retrait, …) comme tout autre tupe standard (nombre).
Ces opérations d'ajouts, de retraits, …, manipulent la liste font souvent référence à la notion d'élément courant que l'on notera ec.
###3 – Ebauche de TAD
	SORTIE liste

	UTILISE Objet, boolean

	FONCTIONs
	new :						→ Liste
	estVide :	Liste			→ boolean
	entete : 	Liste			→ Liste
	enQueue :	Liste			→ Liste
	pred :		Liste		 	→ Liste
	suc :		Liste			→ Liste
	estSorti :	Liste			→ boolean
	valec :		Liste			→ Objet
	mdifec :	Liste x Objet	→ Liste
	ajouterG :	Liste x Objet	→ Liste
	ajouterD :	Liste x Objet	→ Liste
	oterec :	Liste 			→ Liste
	videc :		Liste			→ Liste

	PRECONDITION
	Pour toute liste l, pour tout objet e :

	pre (suc(l)) = non estSorti(l)
	pre (pred(l)) =  ''
	…
	pre (ajouterG(l,e)) = non estSorti(l) ou estVide(l)
	pre (ajouterD(l,e))= non estSorti(l)

D'où l'interface JAVA correspondante :
```JAVA
	public interface Liste {
		boolean estVide() ; 	//l estSorti()
		boolean estSorti() ;
		void enTete() ;
		… 
	}
```
###4 – Exemple d'utilisation
Afficher tout le contenu d'une liste l existante
```JAVA
	l.entete() ;						for (l.entete() ;!l.estSorti() ; l.suc()){
	while ( !l.estSorti()){		 <=>		System.out.println(l.valec()) ;
		Object x = l.valec() ;			}
		System.out.println(x) ;
		l.suc() ;
	}
```
##II – Mise en œuvre des listes / Implémentation en Java
Dans la plupart des langages (y comprit Java) le type `Liste` n'est pas un type de base élémentaire :
il est programmé et construit selon des principes classiques.
###1 – Représentation mémoire contiguë
→ ranger les éléments dans un tableau
```JAVA
	public class ListeTabulee imprements Liste {
		private int cardinal ; 	//nb d'éléments actuellement dans la liste
		private int ec ;		//l'élément courant
		private Object[] conteneur ;

		public ListeTabulee() {
			conteneur = new Object[100] ;
			cardinal = 0 ;
			entete() ;
		}
	
		public void entete() {
			ec = ;
		}
	
		public boolean estVide() {
			return cardinal == 0 ;
		}
	
		public boolean estSorti() {
			return (ec < 0 ) || ec > cardinal -1 ;
		}
```
*Remarque* :* Cette mise en œuvre est simple, le procédé habituel consiste à créer un tableau de taille double à chaque fois qu'on manque de place. Mais les ajouts/suppressions nécessitent obligatoirement des décalages d'éléments dans le tableau => peu efficace

###2 – Mise en œuvre chaînée
L'idée est de ne plus dépendre de la contiguïté des voisins. Plutôt que le suivant soit implicitement après, il sera EXPLICITEMENT quelque part (éventuellement ailleurs). Il n'y aura donc plus besoin de décaler quand on insère ou qu'on retire.

Allongement facile en repoussant les extrémités de départ et de fin.

####a) Cas classique : double chaînage par pointeurs (ou références en Java) avec deux éléments fictifs marqueurs de début et de fin

>*N.B.* : L'ajout des 2 marqueurs ne vise qu'à simplifier l'écriture des algorithmes des opérations : ainsi il n'y a plus de cas particuliers, on a toujours affaire à une valeur encadrée par 2 autres éléments.
```JAVA
	public class ListeDoublementChainee implements Liste {

		private class Maillon {
			Object val ;
			Maillon s ;
			Maillon p ;
		}
	
		private Maillon debut, fin, ec ;
		
		public ListeDoublementChainee() {
			debut = new Maillon() ;
			fin = new Maillon() ;
			debut.s = fin ;
			fin.p = debut ;
			debut.p = fin.s = null ; //précaution inutile
			entete(); 
	}
```
####b) Cas partucliers
 **Traitement unidirectionnel : simple chaînage**

=> maillon = doublet

=> économie en mémoire

=> une apparente impossibilité à implémenter certaines méthodes de l'interface.

*Remarque* :

* Pour pouvoir insérer à gauche de l'élément courant, ou tout simplement supprimer l'élément courant, on  va mémoriser aussi la position actuelle du predecesseur de l'élément courant. Predec.

* On constate aussi que le marqueur de fin n'est plus très utile.

D'où :
```JAVA
	public class ListeSimplementChainee implements Liste {
		private Maillon debut, ec, predoc;
		struct private class Maillon {
			Object val ;
			Maillon o ;
		}
	}	
	
	public ListeSimpleNonChainee {
		debut = new Maillon() ;
		debut.s = null ;
		ec = null ;		| //entete
		predec = debut ;	|
	}

	public void entete() {
		predec = debut ;
		ec = predec.s ;
	}
```
*Remarque* : Pour réaliser les méthodes « hors sujet » (pred(), enQueue(), …), il est possible de parcourir fastidieusement les éléments de la liste pour obtenir le résultat escompté, mais au prix d'un coût démultiplié.

**Traitement récursif**

Souvent il peut être agréable de concevoir des solutions algorithmiques récursives (basés sur une récurrence) plutôt que itératives (basée sur des boucles).
```JAVA
	int longueur (Liste l){
		if ( listeVide() ) { return 0 ; }
		else { return 1 + longueur ( l.reste() ) ; }
	}
```
On va ajouter deux méthodes :
```JAVA
	l.tete() : qui permet d'obtenir l'élément en tête de liste.
	l.reste() : qui fournit la sous-liste issue du deuxième élément jusqu'à la fin.
```
De plus, on ajoute l'opération complémentaire qui produit une liste à partir de tete + reste.

=> On choisit de le faire sous forme de constructeur : 
```JAVA
	ListeRec l2 = new ListeRec (tde, l) ;
```
#Chapitre 3 : Listes et itérateurs

##I. Introduction / Motivation
```JAVA
	Liste :
		estVide()
			...
		enTete()
		enQueue()
		suc()
		pred()
```
Mélange de 2 finalités :

1.  Gestion du stockage des éléments de la liste => SDD
2.  Système d'énumération des éléments

=> Séparer en deux concepts

1. -> **Liste** : on réduit l'interface `Liste` antérieur à son unique vocation
2. -> **Itérateur** : On introduit cette notion pour toutes les opérations relatives à l'élément courant

et ajouter un moyen d'obtenir un itérateur sur une liste donnée

### Qu'est ce qu'un itérateur ?
```JAVA
	int i;											
	for(i=0;i<tab.length;i++)							
		{													
		//traiter l'élément désigné par i : tab[i]			
		}
	
															
	Iterateur i = l.iterateur();//pour obtenir un itérateur sur la liste l
	for(i.entete();!i.estSorti();i.suc())
		{
		//traiter l'élément déisgné par i : i.valec()
		}
```

>*N.B.* : Le découpage complet en interface Liste et interface utilisateur --> TP

##II. Mise en oeuvre : Cas de liste simplement chainée et son itérateur associé
1. Implémentation de `Liste`
```JAVA
		class ListSimplementChainee implements Liste{
			
			private class Maillon {
				Object val;
				Maillon s;
			}
	
			private Maillon tete, predec, ec;  	//=> Pas tout necessaire
	
			public ListeSimplementChainee(){
				tete = new Maillon();
				tete.s = nul;
				ec = null;						//=> Pas besoin
				predec = null;					//Idem
			}
			...
			//autres méthodes de la liste
	
			public Iterateur iterateur(){
				return new IterateurDeListeSimplementChainee(this);
			}
		}
```

2. Implémentation d'Iterateur
```JAVA
		class IterateurDeListeSimplementChainee implements Iterateur{
	
			private Maillon ec,predec;
			private ListeSimplementChainee l;	//référence vers la liste : laquelle est liée à cet itérateur

			public IterateurDeListeSimplementChainee(ListeSimplementChainee liste){
				l = liste;
				entete();
			}
			
			public void entete(){
				ec = l.tete.s;
				pred = l.tete;
			}

			public boolean estSorti(){
				return ec == null || ec == tete ;
			}
			...
		}
```

##III. Remarque / amélioration

- La gestion des erreurs doit être ajoutée via l'utilsation d'exception
- Visibilité des classes 

Tout `Maillon` n'avait pas besoin d'être visible de l'extérieur, on peut cacher en interne les itérateurs dans la classe liste concernée.
```JAVA
		class ListSimplementChainee implements Liste{
			
			static private class Maillon {
				...
			}

			private class IterateurDeListeSimplementChainee implements Iterateur{		

				private ListeSimplementChainee l;	//=> Facultatif
	
				public IterateurDeListeSimplementChainee(ListeSimplementChainee liste){
					l = liste; //useless
					entete();
				}
				
				public void entete(){
					ec = l.tete.s; //l est useless
					pred = l.tete; //Idem
				}
	
				public boolean estSorti(){
					return ec == null || ec == tete ;
				}
				...
			}
			
		}
```
		
1. Ne "parasiste" pas la documentation utilisateur de noms multiples et inutiles
2. Cela permet aussi au programmeur de mieux gérer la visibilités des attributts de la liste du point de vue de l'itérateur : en classe interne l'itérateur peut accéder aux attributs privés de la liste
3. On peut aussi "paufiner" cette notion de classe interne :
 - `Mallion` doit être `static`
 - `Itérateur` peut probablement ne pas être `static`

##IV. Le framework Java existant = framework des collections ?

```sequence 
Iterator -> Collection :
Set -> Collection :
List -> Collection :
ListIterator -> List :
```

- *ListIterator* tire à partir de la relation d'ordre dans la liste et propose *next()* et *previous()* dans les  directions. Au contraire *Iterator* ne fait qu'implémenter les éléments un par un sans notion d'ordre ni de retour possible.
- `Map` n'est pas une collection
```JAVA
		Iterator i = l.iterator();
		while(i.hasNext())
			{
			System.out.println(i.next());
			}
```

#Chapitre 4 : Les tables (application aux tables de hachage)

##I. Définition et accès élémentaires

####Définition 
Une table est une application finie d'un ensemble : vers un ensemble t.

 - i est appelé ici l'ensemble des clés
 - t est appelé l'ensemble des valeurs

####Remarques 
- On peut donc voir une table comme un ensemble de doublets, ou paires, (clé,valeurs)
- De manière encore plus concrète, on peut imaginer une table comme un tableau généralisé

###1. Utilisation en Java

####Déclaration 
Il existe l'interface générale `Map` dont Java propose plusieurs implémentations :

 - HashMap		(hachage)
 - WeakHashMap 	(utilise le concept de références faibles)
 - TreeMap		(organise les valeurs triables via un arbre)
 - Hashtable	(ancienne mise en oeuvre par hachage)

Constructeurs de HashMap :
```JAVA
	public HashMap()
	public HashMap(int initialCapacity)
	public HashMap(int intialCapacity, float loadFactor)
```

`initialCapacity` est la capacité de départ qui va croître.

`loadFactor` est le seuil (entre 0 et 1) à partir duquel la SDD va se rallonger pour fonctionner.

>*N.B.* La capacité initiale s'accroît dès que le facteur de charge est atteint.

####Méthodes principales
```JAVA
	clear();
		//on efface le contenu de la table
	Object put(Object key, Object value);
		//si la clé est déjà présente, cela y associe la nouvelle valeur et retourne l'ancienne. Sinon la nouvelle association est créée
```

*Remarque :* Comme `NULL` n'est pas interdit comme valeur ou clé, ce n'est pas un indicateur de présence.
```JAVA
	boolean containsKey(Object key);//indique si la clé est présente dans la table
	Object remove(Object key);//si la clé est présente, l'association (clé,valeur) est effacée et sa valeur est renvoyée, sinon la valeur NULL est renvoyée
	Object get(Object key);//retourne la valeur associée à la clé, ou NULL si la clé est absente
	boolean isEmpty();//indique si la table est vide
	int size();//indique le nombre d'associations dans la table
```

####Itérations sur les tables
- En Java, les tables ne sont pas des collections <strike>(Itérateurs)</strike>
- Java propose à la place d'exploiter les "Collection Views"
```JAVA
		Set keySet();//la table fournit la vue collection correspondant à l'ensemble des clés
		Collection values();//la table fourni l'intégralité des valeurs qu'elle contient
		Set entrySet();//la table renvoie la totalité des associations qu'elle contient
```
		
- Java modélise ces associations via le type `Map.Entry` qui propose les méthodes suivantes :
```JAVA
		equals();
		getKey();
		getValue();
		setValue();
```
		
####Exemple 
Sachant qu'un objet Java de type `Properties` est un object qui implémente l'interface `Map` et que l'environnement des variables systèmes peut être obtenue par la méthode `System.getProperties()`, afficher à l'écran toutes ces variables d'environnement.

**Réponse :**
```JAVA
	Properties props = System.getProperties();
	Set s = props.entrySet();
	Iterator iter = s.iterator();
	while(iter.hasNext())
		{
		Map.Entry entry = (Map.Entry) iter.next();
		System.out.println(entry.getKey()+" : "+entry.getValue);
		}
```
*Remarques :* 
- On aurait pu expliciter seulement le type `Map`. Désormais le framework des collections respecte les notations génériques et tout découle.
- `Map<K,V>` qui par défaut est donc bien `Map <Object, Object>`
- Ce qui se passe quand on itère sur une vue collection de table dont le contenu change n'est pas à priori conditionné par le type `Map`. Par conséquent il est préférable d'éviter de **modifier** une table (et notamment de supprimer une clé) en cours d'itération sur une de ses vues.

```JAVA
boolean containsValue(Object v);//indique si la valeur v est présente dans la table
String toString();
putAll(Map m);
```
>*N.B. :* L'interface `Map` ne peut pas imposer de constructeur en Java mais cependant elle en préconise 2 :
>- Un constructeur SANS argument
>- Un constructeur par recopie d'une autre `Map` en paramètre (~`putAll`)

####Exemple
1. Réécrivons la méthode qui permet de rechercher si une valeur est présente dans la table `mapContainsValue(Map m,Object v)` (**SANS** utiliser `containsValue()`)
```JAVA
boolean mapContainsValue(Map m, Object v){
	Collection c = m.values();
	Iterator i = i.iterator();
	while(i.hasNext())
		{
		if(v.equals(i.next()))
			{
			return true;
			}
		}
	return false;
}
```

*Remarque :* On peut utiliser "implicitement" des itérateurs Java grâce au raccourci de la nouvelle syntaxe for :
```JAVA
for(Object val : m.values()) //prononcé "for each val in ..."
	{
	if(val.equals(v)) return true;
	}
```
2. Supprimer les valeurs "doublons" (c.a.d les multiples exemplaires d'une même valeur) dans une table.
>*N.B. :* On veut éviter le danger de modifier directement la table alors qu'on est en train d'itérer sur une de ses vues. On note d'abord à part les éléments à supprimer, puis on parcourt ces notes pour effectuer les suppressions.
```JAVA
//auxiliaire qui note l'existence de doublons de val dans la table m et en archive sa clé
void noteDoublon(Object val, Map m, Collection clés){
	Set s = m.entrySet();//ensemble des entiers à parcourir
	int nombre =0;
	Iterator i = s.iterator();
	while(i.hasNext())
		{
		Map.Entry entree = (Map.Entry) i.next();
		if(val.equals(entreee.getValue())
			{
			nombre++;
			if(nombre > 1)
				{
				cles.add(empty.getKey());
				}
			}
		}
}

//algorithme principal
void supprimerDoublons(Map m){
	//1) On recense les associations à supprimer
	Collection cles = new HashSet();
	Collection valeurs = new HashSet(m.values());
	Iterator i = valeurs.iterator();
	while(i.hasNext())
		{
		Object val = i.next();
		noteDoublons(m,val,cles);
		}
	//2)suppression effective
	Iterator j = cles.iterator();
	while(j.hasNext())
		{
		m.remove(j.next());
		}
}
```
##II. Les tables de hachage
###1. Principe du hachage
Un calcul sur la clé (par évaluation d'une expression mathématique) va donner la position (ou le rang) de la valeur de l'élément.

1. La fonction est injective (rappel : $\forall y\in$l'ensemble des valeurs, il existe un antécédent). Deux clés distinctes donnent 2 références distinctes. On parle d'adressage *direct* ou calculé.

* **Intéret :** Le passage à la valeur associée est direct
* **Défaut : **
		- Si beaucoup de clés, l'espace nécessaire peut être très gros (voir rédhibitoire)
		- Si peu de clés sont exploitées, il y a beaucoup de mémoire perdue

2. La fonction est surjective  (rappel : $\forall y\in$l'ensemble d'arrivée, il existe au moins un antécédent). 2 clés distinctes aboutissent à la même position. Dans ce 2ème cas, il y a donc conflit lorsqu'il faut associer 2 valeurs à 2 clés dont la fonction amène à la même position : il faudra mettre en place un arbitrage. On parle d'adressage *dispersé*, ou du hachage.

* **Intêret : ** Table de taille raisonnable, peu de pertes
* **Défaut :** collisions à régler

*Remarque :* L'étude des fonctions de hachage cherche :
- Minimiser les collisions : on cherche des choses d'équivalence équitables
- Maximiser l'exploitation de toutes les @ : bien répartir les clés

###2.Exemple d'adressage calculé
Considérant qu'à l'INSA il y a 7 départements ( numérotés de 1 à 7) et que chacune des promotions d'étudiants ne dépasse pas soixante (numérotés de 1 à 60) ont donc la possibilité de caractériser un étudiant par :

* son département $D \in [1,7]$
* son année dans le département $A \in [1,3]$
* son rang dans la promo $R \in [1,60]$

Solution d'adresse calculée pour associer en mémoire les données d'un étudiant :
```JAVA
	int id = (D-1)*180+(A-1)*60+(R-1)
```
Le calcul est simple, mais on devine aisément qu'il y a de la place perdue. Ces pertes deviennent catastrophiques (si on garde la même fonction) en ajoutant le département STPI (2 années, 300/prom).

###3. Exemple d'adressage par hachage (simples)
A partir des différentes clés, le calcul va produire des classes d'équivalence (idéalement égales en nombre). Pour résoudre les cas d'équivalence, on peut introduire un 2ème niveau de hachage (par une fonction différence) puis éventuellement un 3ème ... Au final, le dernier niveau résoudra définitivement le conflit par une liste de débordement.

####Exemple
Si la clé est un mot 
$$P1(mot) = ((int)mot.charAt(o)-(int)'A')$$

- cela correspond à se baser sur l’intervalle
- très mauvais choix car les classes d'équivalences en français sont peu équitables. Et le nombre de classes est faible ce qui multiplie les conflits

$$P2(mot) = (\sum_{i=0}^{mot.lenght()}{(int)mot.charAt(i)})\%P$$

P étant la taille de l'espace d'arrivée

$$P3(mot) = \mbox{ produit un "grand" nombre dépendant de la clé }\% \mbox{ n}$$

Cette méthode générale et simple est appelée *méthode de la division* et Java fait reposer dessus son mecanisme concernant les collection utilisant le hachage (HashMap, HashDouble, ...)

**Division du travail :** cle % u
- cle = hashCode()
- u = effectué dans la classe

*Remarque :* Donc une nouvelle classe doit fournir une implémentation de la méthode hashCode pour bien fonctioner en Java. En fait elle est héritée de `Object` (qui retourne tout simplement la référence de l'objet). Chaque classe est censée la spécialisée.

#Chapitre 5 : Les arbres (en particulier les arbres binaires)

##I. Vue abstraite d'un arbre
###1. Définition et terminologie
Un *arbre orienté* : est une structure chaînée telle que :

- il exite un unique élément d'entrée : la *racine*
- tout élément (sauf la racine) possède un et un seul prédécesseur : le *père*
- on peut atteindre tout élément à partir de la racine par un *chemin unique*

```ASCII ART
     /\     Exemple d'un arbre
    /  \
   /\  /\
```
- Un *arbre ordonnée* : est un arbre orienté tel que les successeurs de tout élément soient ordonnés (c.a.d il y a un 1er successeur, un 2eme successeur, ...)
- Un *arbre binaire* : est un arbre orienté tel que tout élément a au plus 2 successeurs (appelés habituellement fils gauche et fils droit)

####Exemple
```ASCII ART
        ARBRE      PROFONDEUR         HAUTEUR
          X1           0               x1=3
        /    \
       /      \ 
     x11      x12      1            x11=2 x12=1
   /    \       \
 x111   x112   x122    2         x111=0 x112=1 ...
         /
      x1121            3              x1121=0
```
####Terminologie usuelle
* Les Xi sont appelés *noeuds* de l'arbre
* x1 est le noeud *racine*
* x111, x1121, x122 sont des noeuds *feuilles*
* tout chemin de x1 à une feuille qui est appelé *branche*
* profondeur (x) = longueur du chemin de la racine à x
* hauteur (x) = longueur maximale parmi les chemins allant de x à une feuille
* niveau (x) = hauteur(racine) - profondeur(x)

*Remarque :* Se réduire à l'étude du cas des arbres binaires seulement n'est pas limitatif : car il existe une transformation *bijective* qui permet de passer d'un arbre quelconque à un arbre binaire.

Le procédé général de la transformation générale est simple :
* On place les fils dans le sous-arbre gauche
* On place les frères dans le sous-arbre droit
```ASCII ART
         1                            1
     /   |    \                      /
    2    3     4                    2
    |  / | \  / \        <=>       / \
    5 6  7 8 9  10                5   3
                                     / \
                                    6   4
                                    \   /
                                    7  9
                                     \  \
                                      8 10
```
###2. Fonction du TAD Arbre


```
SORTIE
Arbre[T]

UTILISE
boolean

FONCTIONS

new : 									-> Arbre[T]
new : 			T x Arbre[T] x Arbre[T] -> Arbre[T]
arbreVide : 	Arbre[T]				-> boolean
racine : 		Arbre[T] 				-> T
arbreG :		Arbre[T] 				-> Arbre[T]
arbreD : 		Arbre[T] 				-> Arbre[T]
changeArbreG : 	Arbre[T] x Arbre[T]		-> Arbre[T]
changeArbreD : 	Arbre[T] x Arbre[T]		-> Arbre[T]
changeRacine : 	Arbre[T] x T			-> Arbre[T]
viderArbre :	Arbre[T]				-> Arbre[T]
oterRacine : 	Arbre[T]				-> Arbre[T]
```

###3. Exemples d'utilisation

Il existe 3 parcours classiques :

- le parcours préfixe :
```ASCII ART
		i          Pour tout nœud i de l'arbre,
	   / \         Pour tout nœud j du sous-arbre gauche,
	  j   k        Pour tout nœud k du sous-arbre droit,

Ordre de passage de i < Ordre de j < Ordre de k
```
- le parcours infixe (symétrique)
```ASCII ART
Pour tout i,j,k ...

Ordre de passage de j < Ordre de i < Ordre de k
```
- le parcours postfixe
```ASCII ART
Pour tout i,j,k ...

Ordre de passage de j < Ordre de k < Ordre de i
```

####Exercice : Application de ces parcours 
1. Structurer l'expression suivante dans une arbre binaire
$$((a+b)*(c+(d/e)))-(c*f)$$
2.  Dans l"énumération des noeuds de l'arbre selon les 3 parcours

```ASCII ART
				 -
			  /    \
			 *      *
			/  \   / \
		   +    + c   f
		  / \  / \
		 a  b c  '/'
		         / \
		        d   e
```
En préfixe :
- * + a b + c / d e * c f   ( Aux parenthèses près, c'est la notation fonctionnelle)
En infixe :
a + b * c + d / e - c * f  ( Aux parenthèses près, c'est la notation mathématique usuelle)
En postfixe :
a b + c d e / + * c f * - ( Notation polonaise inverse)

####Exemple : Arbre de tri
Cas particulier où les éléments possèdent une relation d'ordre, que l'on va utiliser au fil de leur placement. Ce placement progressif respect la règle suivante :
$$\forall x_{i} \in \mbox{ sous-arbre gauche de } x, x{i} < x$$
$$\forall x_{j} \in \mbox{ sous-arbre droit de } x, x{j} > x$$

Résultat après insertion de la séquence 7, 1, 8, 4, 6, 10, 11, 2, 5

```ASCII ART
			    7
			  /   \
			 1     8
			   \    \
		        4    10
		       / \     \
		      2   6    11
		         / 
		        5  
```
*Remarque : * Le parcours **infixe** produit alors la séquence triée.
*Remarque : * Des algorithmes annexes peuvent permettre de mieux équilibrer de tels arbres.

``` java
void parcoursInfixe (Arbre[T] a) {
	if (!arbreVide(a))
		{
		parcoursInfixe(arbreG(a));
		traiter racine(a); //affichage ou autre traitement
		parcoursInfixeG(arbreD(a));
		}
}
```
*Remarque :* Cette écriture simple suppose que les fonctions `arbreG()` et `arbreD()` renvoient un arbre vide si cette arborescence ne contient aucun nœud.

Les deux autres parcours s'en déduisent facilement :
- préfixe : en déplaçant  le traitement de la racine au début du bloc de la conditionnelle
- postfixe : en déplaçant  le traitement de la racine à la fin de la conditionnelle

##II. Arbre avec mécanisme de navigation

**Motivation :** Les traitements sur les arbres sont intrinsèquement le plus souvent récursifs. Cependant l'approche précédente ne permet pas une navigation libre dans l'abre (c.a.d. descendre/remonter). Il faut donc ajouter une tête de lecture que l'on peut déplacer (= ec).

###1. Fonctions ajoutées au TAD
```
positRacine : 	Arbre[T] -> Arbre[T]
positPere : 	Arbre[T] -> Arbre[T]
positFilsG : 	Arbre[T] -> Arbre[T]
positFilsD : 	Arbre[T] -> Arbre[T]
aFilsG : 		Arbre[T] -> boolean
aFilsD : 		Arbre[T] -> boolean
valNoeud : 		Arbre[T] -> T
estHorsArbre : 	Arbre[T] -> boolean
surRacine : 	Arbre[T] -> boolean
surFeuille : 	Arbre[T] -> boolean
```

####Exemple : réécriture possible du parcours avec outillage
```java
void parcoursInfixe(Arbre[T] a){
	if(aFilsG(a))
		{
		positFilsG(a);
		parcoursInfixe(a);
		positPere(a);
		}
	traiter valNoeud(a); //affichage
	if(aFilsD(a))
		{
		positFilsD(a);
		parcoursInfixe(a);
		positPere(a);
		}
}

void parcours(Arbre[T] a){
	if(!arbreVide(a))
		{
		positRacine(a);
		parcoursInfixe(a);
		}
}
```

###2. Fonction supplémentaire pour modifier l'arbre

```
modifNoeud : Arbre[T] x T -> Arbre[T]
modifFilsG : Arbre[T] x T -> Arbre[T]
modifFilsD : Arbre[T] x T -> Arbre[T]
oterArbre : Arbre[T] -> Arbre[T]
oterNoeud : Arbre[T] -> Arbre[T] //n'est définie que s'il existe au maximum 1 fils dans le cas général
```
*Remarque :* `oterNoeud()` n'a pas de solution générale quand il existe 2 fils. Mais il peut exister des solutions particulières avec 2 fils quand on a plus d'informations (ex : l'organisation en arbre de tri).

*Remarque :* Le cas échéant, pour permettre des parcours en largeur, il peut être utile d'ajouter ces fonctions :
```
positFrereG : 	Arbre[T] -> Arbre[T]
positFrereD : 	Arbre[T] -> Arbre[T]
aFrereG : 		Arbre[T] -> boolean
aFrereD : 		Arbre[T] -> boolean
```
###3. Réflexion sur la nature et l'exécution des types de parcours
Un parcours en profondeur utilise implicitement un mécanisme de **Pile** (c'était la pile d'exécution grâce à la récursivité), que l'on peut expliciter.

> *N.B. :* Pile = LIFO (Last In First Out)

**Algorithme** explicite avec Pile :
```ALGORITHME
Pile lifo
ajouter la racine dans lifo
tant que lifo est non vide
	retirer un élément de lifo
	traiter cet élément
	ajouter dans lifo le filsD s'il existe
	ajouter dans lifo le filsG s'il existe
fin
```
Pour généraliser, il faut être capable de
- ré-empiler un noeud pour le traiter plus tard
- distinguer donc la 1ère visite de la 2ème
=> lifo va contenir des doublets (noeuds, n° de viste $\in \{1,2\}$)
```
Pile lifo
ajouter (racine,1) dans lifo
tant que lifo est non vide
	retirer un élément (noeud,1) de lifo
	si i = 2 alors 
		traiter noeud
	sinon 
		ajouter (filsD, 1) s'il existe
		ajouter (noeud, 2)
		ajouter (filsG, 1) s'il existe
	
fin
```
*Remarque :* Pour décliner les 3 variantes de parcours en profondeur (onfixe, préfixe, postfixe), il suffit de bien ordonner les 3 impléments dans la conditionnelle. Ici c'est infixe, mais avec `ajouter(nœud, 2)` en 1er ce serait postfixe.

*Remarque :* Pour décliner les variantes de parcours en largeur, io suffit de remplacer la `pile(lifo)` par une `pile (lipo)`

##III. Mise en oeuvre JAVA

### 1. Représentation contiguë

Arbre complet à gauche : tous les niveaux de l'arbre sont complets sauf le niveau $0$ qui n'est pas complet qu'en partie gauche.

``` java
public class ArbreBinaireCompletAGauche {
	private Object[] val; //les valeurs des noeuds
	private int derNoeud; // l'indice du dernier noeud
	private int ec; // l'indice du noeud courant
}
```
$\Rightarrow$ Les opérations qui formalisent les liens de parenté seront obtenues par calcul. Pour le nœud d'indice $i$ :

- le père est en $\frac{i}{2}$
- le fils gauche est en $2i$
- le fils droit est en $2i+1$

*Remarque :* ces expressions sont valables pour une racine à l'indice $1$, à modifier si on veut commencer à l'indice $0$.

- Avantages :

	- Adressage calculé simple et rapide

- Défauts :

	- Trop limité aux arbres complets à gauche

Pour généraliser aux arbres non complets, on pourrait accepter d'avoir des cases inutilisées concernant les nœuds inexistants. Cependant l'explosion mémoire devient vite rédhibitoire, puisque une branche d'arbre "manquante" de longueur $h$, occupe un nombre de cases proportionnelle à $2^{h}$

$\Rightarrow$ Vite beaucoup trop important

###2. Représentation récursive

```java
public class ArbreRecursif {
	private Object valeurRacine; //valeur de la racine
	private Arbre arbreG; //sous-arbre gauche
	private Arbre arbreD; //sous-arbre droit
}
```
Cette version est simple et présente une vue assez "fonctionnelle" d'un arbre. Cependant elle n'est pratique que pour des parcours récursifs en profondeur. L'écriture des autres opérations s'avère inefficaces et fastidieuses.

###3. Représentation chaînée

ON met en place une structure de base `Noeud`

```
						|  père   | <- indique le neoud père
						| valeur  |
noeud frère gauche ->	|    |    | <- noeud frère droit
noeud fils gauche ->	|    |    | <- noeud fils droit
```

```java
public class ArbreChaine {
	private class Noeud {
		private Object valeur;
		private Noeud prec;
		private Noeud filsg;
		private Noeud filsd;
		private Noeud frereg;
		private Noeud frered;

	private Noeud racine; //indique la racine de l'arbre
...
}
```
*Remarques :*
- Bien souvent, on omet les 2 références vers les frères si ce type de parcours n'est pas envisagé.
- Notre structure de données intègre un mécanisme de navigation.

On pourrait être tenté de produire une séparation comme le concept d'itérateur séparé sur la liste. Cependant, ce découpage est **non classique** pour les arbres. La piste qui s'en rapproche le plus est le modèle de conception "Visiteur" (Design Pattern) qui est général et applicable pour créer une classe dont le but est de fournir un rapport sur le contenu d'autre chose.

En conception plus stricte, on s'impose parfois d'éviter ce type `Noeud` généraliste pour décliner des types noeuds dérivés plus précis et ciblés. Par exemple : `NoeudInterne`,`NoeudFeuille`,`NoeudValeur`, ...

#Chapitre 6 : Les tris

##Introduction
On considère une collection d'éléments à trier : quel algorithme le permet ?

Il existe de nombreuses variantes d'algorithmes, nous en déclinerons ici quelque exemples sur la base d'un **tableau d'entiers**. Il sera possible de décliner pour d'autres éléments que des entiers et d'autres structures que des tableaux.

##I. Le tri à bulles
###1. Explication du fonctionnement

Une "bulle" démarre au début du tableau et "remonte" vers le fin. Elle emporte la plus grande valeur rencontrée, en procédant par permutations. Puis on répète avec les bulles suivantes.

###2. Implémentation JAVA

``` java
void bubbleSort(int[] t){
	for(int b=t.lenght; b >=2 ; b--)
		{
		for(int j=1; j<b ; j++)
			{
			if(t[j] < t[j-1])
				{
				permuter(t,j,j-1);
				}
			}
		}
}

void permuter(int[] tab, int a, int b){
	int temp = t[a];
	t[a] = t[b];
	t[b] = temp;
}
```
Complexité opératoire de cet algorithme, en nombre de comparaisons :
 - la première bulle effectue $n-1$ comparaisons (pour un tableau de taille $n$)
 - la deuxième bulle effectue $n-2$ comparaisons
 - ...
 - la dernière bulle fait $1$ comparaison entre deux valeurs

$$(n-1)+(n-2)+...+1= \frac{(n-1)*(1+(n-1))}{2} = \frac{n(n-1)}{2}$$
On ne s’intéresse qu'à l'ordre de grandeur la complexité est donc de l'ordre $n^{2} = \theta (n^{2})$

> *Bilan :* 
> - Algorithme simple et facile à comprendre
> - fonctionne **in situ**
> - cet algorithme est dit **stable** (c.a.d qu'il ne remet pas en cause l'ordre de 2 éléments s'ils étaient égaux au regard de la relation d'autre) $\Rightarrow$ utile pour enchaîner les tris en cascade

##II. Tri par extraction du maximum
L'algorithme précédent faisait énormément de permutations, pourquoi pas placer la plus grande valeur directement à la fin.

```java
void extractionSort(int[] t) {
	for(int i=t.lenght-1 ; i >= 0 ; i--)
		{
		int imax = i;
		for(int j=0 ; j<i ; j++)
			{
			if(t[j]>t[imax])
				{
				imax=j;
				}
			}
		if(imax!=1)
			{
			permuter(t,imax,i);
			}
		}
}
```
*Remarque :* 
- Cet algorithme reste en $\theta (n^{2})$ en **nombre de comparaisons** mais en revanche, on a énormément gagné en permutations
- il est simple à écrire
- il est instable (du au fait de la permutation **arbitraire** de l'élément vers une portion indéterminée dans le tableau)

##IIi. Tri par insertion

###1. Principe
 On suppose le tableau trié jusqu'au rang $n$ et on insère la valeur $n+1$ dans la portion triée. On obtient un tableau trié jusqu'au rang $n+1$. On réitère pour trier jusqu'à la fin du tableau.

###2. Implémentation Java
```java
void insetSort(int[] t){
	int i; //position du nouvel élément à insérer
	int j; //position "libre" envisagée pour l'insertion
	boolean trouve; //indique que la position es trouvée
	int element; //mémorise l'élément à inverser

	for(i=1 ; i<t.lenght ; i++)
		{
		element = t[i];
		j=i;
		trouver=false;
		while(j>0 && !trouve)
			{
			if(t[i-1]>element)
				{
				t[j]=t[j-1];
				j--;
				}
			else
				{
				trouvé = true;
				}
			}
		t[j]=element;
		}
}
```

>**Bilan :**
>- algorithme simple, "in situs"
>- compléxité en $\theta (n^2)$
>- stable

##IV. Tri par fusion
*Remarque :* En fait il est démontré que l'opération de tri (dans le cas général de valeurs quelques et d'une relation d'ordre quelconque) est intrinsèquement en $n*log_{2}(n)$.

- On ne peut pas faire mieux
- Existe-t-il des algos qui atteignent cette valeur ?

###1. Idée 
$log_{2}(n)$ fait penser aux arbres binaires / dichotomie

On coupe le problème du tri de taille $n$ en 2 sous problèmes de taille $n/2$. On tri ces 2 sous-tableaux puis on les fusionne.

###2. Fonction

```java
fonction mergeSort(int[] t, int min, int max){
	if(min < max)
		{
		int milieu = (min + max)/2;
		mergeSort(t,min,milieu);
		mergeSort(t,milieu,max);
		fusionner(t,min,milieu,max);
		}
}

fonction fusionner(int[] t, int min, int milieu, int max){
	int[] fusion = new int[max-min +1];
	int i = min;
	int j = milieu + 1;
	int k = 0;
	while( i <= milieu && j <= max)
		{
		if(t[i] <= t[j])
			{
			fusion[k]=t[i];
			k++;
			i++;
			}
		else
			{
			fusion[k]=k[j];
			k++;
			j++;
			}
		}
	while(i<=milieu)
		{
		fusion[k++]=t[i++];
		}
	while(j<=milieu)
		{
		fusion[k++]=t[j++];
		}
	for(k=0; k<fusion.lenght; k++)
		{
		t[min+k]=fusion[k];
		}
}
```

>**Bilan :**
>- Plus évolué (récursif)
>- Le tri s'effectue en réalité DANS la fusion
>- Stable
>- Complexité : $T_{n} = 2 * T_{n/2} + t(fusion_{n})$

On arriverait à une complexité de $\theta(n*log_{2}(n))$.

**MAIS** ça nécessiter un tableau auxiliaire (!= "in situ") qui va jusqu'à la taille du tableau initial à trier dans le cas le plus défavorable.

##V. Tri rapide

###1. Principe

Le soucis du précédent est la fusion qui nécessite un tableau complémentaire. Pour l'éviter, on va découper en 2 parties qui ne nécessite pas d'interclassement une fois triées.

###2. Fonction

```java
void quickSort(int[] t, int min, int max){
	if(min<max)
		{
		int m = partition(t, min, max);
		quickSort(t,min,m-1);
		quickSort(t,m+1,max);
		}
}
```

*Remarque :* Le tri va se faire en "plaçant" ce pivot en position (quasi) centrale.

La placement du pivot va être "forcé" :
- choisit arbitrairement la 1ere valeur comme pivot
- on réorganise le reste du tableau en fonctionde ce pivot
- on place le pivot en milieu des 2 groupes

```java
int partition(int[] t; int min, int max)	{
	if(t[max]<t[min]) permuter(t,min,max);

	int pivot = t[min];
	i = min;
	j = max+1;

	do {
		do{i++;} while(t[i]<pivot);
		do{j--;} while(t[j]>pivot);
		if(i<j) permuter (t,i,j);
	} while (i<j);

	permuter(t,min,j);
	return j;
}
```
**Bilan :** 
> - pas simple, mais efficace et très rapide
> - intéret : pas de 2eme tableau
> - défauts : 
 - instable (permutation arbitraires)
	- n'est optimal qu'en moyenne
	- elle n'est en $n*log_{2}(n)$ que lorsque la partition coupe par la moitié (on est sur l'optimum) et les autres découpages s'en écarte. En particulier, si le tableau est déjà trié, la complexité atteint  $\theta(n^2)$

> **Correction finale :**
> 1. La boucle descendante sur j ne peut pas descendre car au pire elle s'arrete sur le pivot lui-même
> 2. La boucle ascendante peut déborder sauf si elle était elle aussi limitée par le dernier élément.

##VI. Tri par tas
###1. Le tas

*Rappel :* arbre binaire complet à gauche

**Définition :**
TAS = un arbre à gauche complet à gauche qui assure que pour tout noeud j la valeur du noeud est >= à celle de ses fils.

*Remarques :*
- donc la plus grande valeur est à la racine
- comme les opération que nous allons utiliser sur le tas ne parcourent qu'une branche, leur complexité sera en $\theta(log_{2}(n))$

###2. Deux opérations nécessaires

####1. insertion dans un tas (qui démarre seulement à l'indice 1 du tableau de stockage)

```
Algo d'insertion de v

inserer (entier v) 
	//a[1 ... n] est un tas
	n=n+1
	a[n]= v
	//a[1 .. n] ABCg mais pas un tas
	//a[1 .. m-1] tas
	retablir VersLeHaut(n)
	//a[1 ... n] est un tas


retablirVersLeHaut(entier k) 
	entier v
	v=a[k] avec a[0]=MAX_INTEGER
	tant que a[k/2]<v
		a[k]=a[k/2]
		k=k2
	fin
	a[k]=v
	
```

####2. Opération de suppression de la racine
```
entier supprimer()
	entier sup = a[1]
	a[1]=a[n]
	n=n-1
	//a[1 ... n] est un ABCg, mais plus un tas
	retablirVersLeBas(1)
	//a[1 ... n] est un tas
	return sup

retablirVersLeBas(entier k)
	entier j,v
	boolean fini = faux
	v=a[k]
	tant que k <= n/2 et non(fini) //tant qu'il y a au moins un fils
		j=2*k //le fils gauche
		si j<n et a[j] < a[j+1] alors 
			j = j+1 
		fin si
		si a[j] <= v alors 
			fini = vrai 
		sinon 
			a[k] = a[j] 
			k = j
		fin si
	fin tant que
	a[k]=v
		
```

####3. Algorithme de tri

Pour trier N valeurs d'un tableau de taille N, il faut :
- Faire N itérations dans le tas
- faire N suppression pour les sortir

> N.B. L'astuce du tri par tas, est d'utiliser le même tableau pour servir de contenant du tableau à trier et de la structure tas.