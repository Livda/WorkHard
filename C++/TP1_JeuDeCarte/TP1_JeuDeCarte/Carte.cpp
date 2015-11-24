#include "Carte.h"

Carte* Carte::_premN = NULL;
Carte* Carte::_premS = NULL;
Carte* Carte::_lastN = NULL;
Carte* Carte::_lastS = NULL;

/**
* Constructeur par défaut
*/
Carte::Carte()
{
}

/**
* Constructeur appele dans le main à partir de 3 valeurs
* @param c la couleur de la carte
* @param v la valeur de la carte
* @param prop la proprietaire de la carte ('N' ou 'S')
*/
Carte::Carte(Couleur c, Valeur v, char prop){
	_c = c;
	_v = v;
	_prop = prop;
	if (prop == 'N'){
		if (_lastN == NULL) {
			_lastN = this;
		}
		this->_suiv = getNTete();
		setNTete(this);	
	}
	else{
		if (_lastS == NULL) {
			_lastS = this;
		}
		this->_suiv = getSTete();
		setSTete(this);
	}
};

/**
* Affiche une carte sur la sortie standard
*/
void Carte::afficher()
{
	int valeur = this->getValeur();
	int couleur = this->getCouleur();
	cout << " "<< val[valeur - 1] << " de " << coul[couleur - 1] << " ("<< valeur << ", " << couleur << ")"<< endl;
};

/**
* Affiche toutes les cartes de N sur la sortie standard
*/
void Carte::afficherN()
{
	Carte::getNTete()->afficher();
	Carte* suivante = Carte::getNTete()->suc();
	while (suivante->suc() != NULL){
		suivante->afficher();
		suivante = suivante->suc();
	}
	suivante->afficher();

};

/**
* Affiche toutes les cartes de S sur la sortie standard
*/
void Carte::afficherS()
{
	Carte::getSTete()->afficher();
	Carte* suivante = Carte::getSTete()->suc();
	while (suivante->suc() != NULL){
		suivante->afficher();
		suivante = suivante->suc();
	}
	suivante->afficher();
};

/**
* Teste l'egalite entre deux cartes
* @param c la carte a tester avec la courante
* @return vrai si les deux cartes sont egales
*/
bool Carte::egale(Carte c)
{
	 return c.getValeur()==this->getValeur();
};

/**
* Change le proprietaire de la carte
* et la place en fin de paquet
*/
void Carte::changerProp()
{
	char prop = this->getProp();
	if (prop == 'N')
	{
		this->_prop = 'S';
		_premN = this->suc();
		this->_suiv = NULL;
		_lastS->_suiv = this;
		_lastS = this;
	}
	else {
		this->_prop = 'N';
		_premS = this->suc();
		_lastN->_suiv = this;
		_lastN = this;
		this->_suiv = NULL;
		
	}
};

/**
* Rend la derniere carte du paquet
* @param p le proprietaire du paquet
* @return un pointeur sur la derniere carte
*/
Carte* Carte::getDern(char p) {
	if (p = 'N')
	{
		return getNLast();
	}
	else {
		return getSLast();
	}
}

/**
* Met la carte courante en derniere position dans le paquet
* @param p le proprietaire du paquet
*/
void Carte::setDern(char p) {
	if (p = 'N')
	{
		_lastN = this;
	}
	else {
		_lastS = this;
	}
}

/**
* Rend la derniere carte du paquet
* @param p le proprietaire du paquet
* @return un pointeur sur la derniere carte
*/
Carte* Carte::getTete(char p) {
	if (p = 'N')
	{
		return getNTete();
	}
	else {
		return getSTete();
	}
}

/**
* Met la carte courante en premiere position dans le paquet
* @param p le proprietaire du paquet
*/
void Carte::setPrem(char p) {
	if (p = 'N')
	{
		setNTete(this);
	}
	else {
		setSTete(this);
	}
}

/**
* Fait passer la carte en dernier
* Et reorganise le paquet ainsi
* que les dependances entre les cartes
*/
void Carte::passerDerriere()
{
	//On récupère le propriétaire
	char prop = this->getProp();
	//On passe la deuxième carte en premier
	Carte* Sec = this->suc();
	if (prop == 'N') {
		_premN = Sec;
	}
	else {
		_premS = Sec;
	}
	//On enlève le lien entre la carte courante et sa suivante
	this->_suiv = NULL;
	//On récupère la dernière carte du paquet
	Carte* last;
	if (prop = 'N') {
		last = _lastN;
	}
	else{
		last = _lastS;
	}
	//On met la carte courante à la suite de la derniere
	last->_suiv = this;
	//On place la carte courante en fin de paquet
	if (prop = 'N') {
		_lastN = this;
	}
	else {
		_lastS = this;
	}
};

/**
* Teste si la carte courante est 
* strictement plus grande
* @param c la carte a comparer avec celle courante
* @return vrai si la carte courante est strictement plus grande
*/
bool Carte::supAbs(Carte c)
{
	return this->getValeur() > c.getValeur() ;
};