#pragma once
#include <string>
#include <iostream>
#include <cstdlib>
using namespace std;

enum Couleur : int {pique=1, trefle, coeur, carreau};

enum Valeur : int{as=1, deux, trois, quatre, cinq, six, sept, huit, neuf, dix, valet, dame, roi};

class Carte
{
private:
	Couleur _c;
	Valeur _v;
	char _prop;
	Carte* _suiv = NULL;
	static Carte* _premN;
	static Carte* _premS;
	static Carte* _lastN;
	static Carte* _lastS;
	string coul[4] = { "pique", "trefle", "coeur", "carreau" };
	string val[13] = { "as","deux","trois","quatre","cinq","six","sept","huit","neuf","dix","valet","dame","roi" };
public:
	/**
	* Constructeur par défaut
	*/
	Carte();

	/**
	* Constructeur appele dans le main à partir de 3 valeurs
	* @param c la couleur de la carte
	* @param v la valeur de la carte
	* @param prop la proprietaire de la carte ('N' ou 'S')
	*/
	Carte(Couleur c, Valeur v, char prop);

	/**
	* Rend la couleur de la carte courante
	* @return la couleur de la carte
	*/
	Couleur getCouleur() const;

	/**
	* Rend la valeur de la carte courante
	* @return la valeur de la carte
	*/
	Valeur getValeur() const;

	/**
	* Rend le proprietaire de la carte courante
	* @return le proprietaire
	*/
	char getProp();

	/**
	* Change le prorietaire de la carte courante
	* @param p le nouveau proprietaire
	*/
	void setProp(char p);

	/**
	* Donne le successeur de la carte courante
	* @return le poitneur sur la carte suivante
	*/
	Carte* suc();

	/**
	* Retourne la premiere carte du paquet de N
	* @return le pointeur sur la premiere carte
	*/
	static Carte* getNTete();

	/**
	* Met un carte en tete de paquet de S
	* @param c le pointeur vers la carte a placer
	*/
	void setNTete(Carte * c);

	/**
	* Retourne la premiere carte du paquet de S
	* @return le pointeur sur la premiere carte
	*/
	static Carte* getSTete();

	/**
	* Met un carte en tete de paquet de S
	* @param c le pointeur vers la carte a placer
	*/
	void setSTete(Carte * c);

	/**
	* Retourne la derniere carte
	* du paquet de N
	* @return le pointeur sur la derniere carte
	*/
	Carte * getNLast();

	/**
	* Change la carte en fin de paquet de N
	* @param c le pointeur vers la nouvelle carte en fin de paquet
	*/
	void setNLast(Carte * c);

	/**
	* Retourne la derniere carte
	* du paquet de S
	* @return le pointeur sur la derniere carte
	*/
	Carte * getSLast();

	/**
	* Change la carte en fin de paquet de S
	* @param c le pointeur vers la nouvelle carte en fin de paquet
	*/
	void setSLast(Carte * c);

	/**
	* Rend la derniere carte du paquet
	* @param p le proprietaire du paquet
	* @return un pointeur sur la derniere carte
	*/
	Carte* Carte::getTete(char p);

	/**
	* Met la carte courante en position dans le paquet
	* @param p le proprietaire du paquet
	*/
	void setPrem(char p);

	/**
	* Met la carte courante en derniere position dans le paquet
	* @param p le proprietaire du paquet
	*/
	void setDern(char p);

	/**
	* Rend la derniere carte du paquet
	* @param p le propprietaire du paquet
	* @return le pointeur sur la derniere carte
	*/
	Carte* getDern(char p);

	/**
	* Affiche une carte sur la sortie standard
	*/
	void afficher();

	/**
	* Affiche toutes les cartes de N sur la sortie standard
	*/
	static void afficherN();

	/**
	* Affiche toutes les cartes de S sur la sortie standard
	*/
	static void afficherS();

	/**
	* Teste l'egalite entre deux cartes
	* @param c la carte a tester avec la courante
	* @return vrai si les deux cartes sont egales
	*/
	bool egale(Carte c);

	/**
	* Change le proprietaire de la carte
	*/
	void changerProp();

	/**
	* Fait passer la carte en dernier
	* Et reorganise le paquet ainsi
	* que les dependances entre les cartes
	*/
	void passerDerriere();

	/**
	* Teste si la carte courante est
	* strictement plus grande
	* @param c la carte a comparer avec celle courante
	* @return vrai si la carte courante est strictement plus grande
	*/
	bool supAbs(Carte c);

};

inline Couleur Carte::getCouleur() const { return _c; }

inline Valeur Carte::getValeur() const { return _v; }

inline char Carte::getProp() { return _prop; }
inline void Carte::setProp(char p) { _prop = p; }

inline Carte* Carte::suc() { return _suiv; }

inline Carte* Carte::getNTete() { return _premN; }
inline void Carte::setNTete(Carte* c) { _premN = c; }

inline Carte* Carte::getSTete() { return _premS; }
inline void Carte::setSTete(Carte* c) { _premS = c; }

inline Carte* Carte::getNLast() { return _lastN; }
inline void Carte::setNLast(Carte* c) { _lastN = c; }

inline Carte* Carte::getSLast() { return _lastS; }
inline void Carte::setSLast(Carte* c) { _lastS = c; }