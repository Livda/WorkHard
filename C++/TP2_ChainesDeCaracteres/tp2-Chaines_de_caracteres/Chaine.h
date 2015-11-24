/* This is an educational purpose String class.
 *
 * Headers.
 *
 * Written (in french) mostly by Aurelien Fontaine
 * with the help of Francois Boschet (sometimes)
 *
 * 4INFO - INSA of Rennes - G2.1
 */

#pragma once


 using namespace std;

 class Chaine
 {
 private:
	int _lg; //longueur de la Chaine
	char* _chaine; //tableau de caracteres contenant la chaine
public:
	/**
	* Constructeur par defaut d'une Chaine
	*/
	Chaine();
	
	/**
	* Constructeur d'une Chaine par recopie
	* d'une chaine de caratère
	* @param c Le poitneur sur la chaine de caractere a recopier
	*/
	Chaine(const char* c);

	/**
	* Constructeur d'unbe Chaine par recopie d'une Chaine 
	* @param c La reference sur la chaine de caractere a recopier
	*/
	Chaine(const Chaine & c);

	/**
	* Destructeur d'une Chaine
	*/
	~Chaine();

	/**
	* Donne la longueur de la Chaine
	* @return longueur de la Chaine courante
	*/
	int const getLong();

	/**
	* Donne le caratere a la ieme position dans la Chaine
	* @return le ieme caratere de la Chaine courante
	*/
	char const getChar(int i);

	/**
	* Teste l'egalite entre deux Chaines
	* @param c La reference vers la Chaine a comparer avec l'objet courant
	* @return true si les deux chaines sont egales
	*/
	bool operator==(const Chaine &c);

	bool operator>(const Chaine &c);
	bool operator<(const Chaine &c);
	bool operator>=(const Chaine &c);
	bool operator<=(const Chaine &c);

	/**
	* Redefinition de l'operateur de concatenation pour deux Chaines
	* @param c La refence vers la Chaine a concatener avec l'objet courant
	* @return le pointeur sur la nouvelle Chaine
	*/
	Chaine* operator+(const Chaine &c);

	/**
	* Extrait la sous-chaine commencant par le caractere
	* deb et se terminant par le caractere fin
	* @param deb Premier caratere de la sous chaine a extraire
	* @param fin Dernier caratere de la sous chaine a extraire
	* @return le pointeur sur la nouvelle Chaine
	*/
	Chaine* sous_chaine(char deb, char fin);

	/**
	* Extrait la sous-chaine commencant par le caractere a l'indice
	* ind1 et se terminant par le caractere a l'indice ind2
	* @param ind1 Indice du premier caratere de la sous chaine a extraire
	* @param ind2 Indice du dernier caratere de la sous chaine a extraire
	* @return le pointeur sur la nouvelle Chaine
	*/
	Chaine* sous_chaine(int ind1, int ind2);

	/**
	* Methode d'affichage d'une Chaine
	*/
	void afficher();
};
inline int const Chaine::getLong() { return _lg; }

