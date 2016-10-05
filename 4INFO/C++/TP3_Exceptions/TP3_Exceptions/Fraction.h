#pragma once

class Fraction
{
private:
	int n; // Le numerateur de la fraction
	int d; // le denominateur de la fraction

	/**
	* Teste si l'addition de deux entiers
	* ne depasse pas la valeur maximale d'un int
	* @param a Premier entier a tester
	* @param b Deuxieme entier a tester
	* @return La somme des deux entiers
	* @throw my_overflow_error si la somme depasse la valeur max d'un int
	*/
	int testOverflowAdd(int a, int b);

	/**
	* Teste si l'addition de deux entiers
	* ne depasse pas la valeur minimale d'un int
	* @param a Premier entier a tester
	* @param b Deuxieme entier a tester
	* @return La somme des deux entiers
	* @throw my_underflow_error si la somme depasse la valeur min d'un int
	*/
	int testUnderflowAdd(int a, int b);

	/**
	* Teste si l'addition de deux entiers ne depasse pas
	* la valeur maximale ou minimale d'un int
	* @param a Premier entier a tester
	* @param b Deuxieme entier a tester
	* @return La somme des deux entiers
	* @throw my_overflow_error si la somme depasse la valeur max d'un int
	* @throw my_underflow_error si la somme depasse la valeur min d'un int
	*/
	int testFlowAdd(int a, int b);

	/**
	* Teste si la multiplication de deux entiers
	* ne depasse pas la valeur maximale d'un int
	* @param a Premier entier a tester
	* @param b Deuxieme entier a tester
	* @return Le produit des deux entiers
	* @throw my_overflow_error si le produit depasse la valeur max d'un int
	*/
	int testOverflowMul(int a, int b);

	/**
	* Teste si la multiplication de deux entiers
	* ne depasse pas la valeur minimale d'un int
	* @param a Premier entier a tester
	* @param b Deuxieme entier a tester
	* @return Le produit des deux entiers
	* @throw my_underflow_error si le produit depasse la valeur min d'un int
	*/
	int testUnderflowMul(int a, int b);

	/**
	* Teste si la multiplication de deux entiers ne depasse pas
	* la valeur maximale ou minimale d'un int
	* @param a Premier entier a tester
	* @param b Deuxieme entier a tester
	* @return La somme des deux entiers
	* @throw my_overflow_error si le produit depasse la valeur max d'un int
	* @throw my_underflow_error si le produit depasse la valeur min d'un int
	*/
	int testFlowMul(int a, int b);

	/**
	* Permet de simplifier une fonction
	* @param a Le numerateur a simplifier
	* @param b Le denominateur a simplifier
	* @return La fraction simplifiee
	*/
	Fraction pgcd(int a, int b);
public:
	/**
	* Constructeur par defaut d'une fraction
	*/
	Fraction();

	/**
	* Constructeur par recopie d'une fraction
	* @param f La fraction a recopier
	*/
	Fraction(const Fraction & f);

	/**
	* Constructeur d'une fraction a partir d'un entier
	* @param entier L'entier a transformer en fraction
	*/
	Fraction(int entier);

	/**
	* Constructeur d'une fraction a partir d'un denominateur
	* et d'un numerateur
	* @param numerateur Le numerateur de la nouvelle fraction
	* @param denominateur Le denominateur de la nouvelle fraction
	*/
	Fraction(int numerateur, int denominateur);

	/**
	* Destructeur d'une fraction
	*/
	~Fraction();

	/**
	* Ajoute deux fractions
	* @param f La fraction a ajouter a la courante
	* @return La fraction additionnee
	*/
	Fraction add(const Fraction & f);
	
	/**
	* Soustrait deux fractions
	* @param f La fraction a soutraire a la courante
	* @return La fraction soutraite
	*/
	Fraction sub(const Fraction & f);

	/**
	* Multiplie deux fractions
	* @param f La fraction a multiplier a la courante
	* @return La fraction multipliee
	*/
	Fraction mul(const Fraction & f);

	/**
	* Divise deux fractions
	* @param f La fraction a diviser a la courante
	* @return La fraction divisee
	*/
	Fraction div(const Fraction & f);

	/**
	* Evaluation de la fraction
	* @return Un double rendant un resultat approche de la fraction
	*/
	double eval();

	/**
	* Methode d'affichage d'une fraction
	*/
	void const afficher();

	/*
	* Numerator
	*/
	int getN();

	/*
	* Denominator
	*/
	int getD();
};

inline int Fraction::getN() { return this->n; }
inline int Fraction::getD() { return this->d; }