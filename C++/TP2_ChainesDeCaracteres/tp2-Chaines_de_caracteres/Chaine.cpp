/* This is an educational purpose String class.
 *
 * Implementation.
 *
 * Written (in french) mostly by Aurelien Fontaine
 * with the help of Francois Boschet (sometimes)
 *
 * 4INFO - INSA of Rennes - G2.1
 */

#include <cstring>
#include <iostream>

#include "Chaine.h"


 Chaine::Chaine()
 {
 	_lg = -1;
 	_chaine = NULL;
	//cout << "Construction d'une chaine vide." << endl << endl;
 }


 Chaine::Chaine(const char* c)
 {
    //cout << "Construction d'une chaine par recopie de tableau de caracteres." << endl;
    //cout << "La chaine est : ";
 	if (c == NULL){
 		_lg = -1;
 		_chaine = NULL;
 	}
 	else {
 		_lg = strlen(c);
 		_chaine = new char[_lg];
 		for (int i = 0; i < _lg; i++) {
 			_chaine[i] = c[i];
            //cout << _chaine[i];
 		}
 	}
    //cout << endl << endl;
 }

 Chaine::Chaine(const Chaine & c)
 {
	//cout << "Construction d'une chaine par recopie de Chaine." << endl;
 	if (c._lg == -1)
 	{
		//cout << "La chaine est vide." << endl;
 		_lg = -1;
 		_chaine = NULL;
 	}
 	else
 	{
		//cout << "La chaine est : ";
 		_lg = c._lg;
 		_chaine = new char[_lg];
 		for (int i = 0; i < _lg; i++)
 		{
 			_chaine[i] = c._chaine[i];
			//cout << _chaine[i];
 		}
		//cout << endl <<endl;
 	}
 }

 Chaine::~Chaine()
 {
	//cout << "Destruction de la chaine : ";
	/*for (int i = 0; i < _lg; i++)
	{
		cout << _chaine[i];
	}*/
	//cout << endl;
		delete[] _chaine;
		
	}

	char const Chaine::getChar(int i)
	{
		if (i > _lg|| i<=0){
		//cout << "Indice en parametre doit etre entre 1 et " << _lg << endl;
			return NULL;
		}
		else
		{
			return _chaine[i-1];
		} 
	}

	bool Chaine::operator== (const Chaine &c)
	{
		int lg = _lg;
		if (lg != c._lg) return false;
		for (int i = 0; i < lg; i++)
		{
			if (_chaine[i] != c._chaine[i]) return false;
		}
		return true;
	}

	bool Chaine::operator>(const Chaine &c)
	{
		if (c._lg <= _lg)return false;
		for (int i = 0; i < _lg; i++)
		{
			if (_chaine[i] != c._chaine[i]) return false;
		}
		return true;
	}
	bool Chaine::operator<(const Chaine &c)
	{
		if (_lg <= c._lg)return false;
		for (int i = 0; i < c._lg; i++)
		{
			if (_chaine[i] != c._chaine[i]) return false;
		}
		return true;
	}
	bool Chaine::operator>=(const Chaine &c)
	{
		if (c._lg < _lg)return false;
		for (int i = 0; i < _lg; i++)
		{
			if (_chaine[i] != c._chaine[i]) return false;
		}
		return true;
	}
	bool Chaine::operator<=(const Chaine &c)
	{
		if (_lg < c._lg)return false;
		for (int i = 0; i < c._lg; i++)
		{
			if (_chaine[i] != c._chaine[i]) return false;
		}
		return true;
	}
	Chaine* Chaine::operator+(const Chaine &c)
	{
		int lg = _lg + c._lg + 1 ;
		int lg1 = _lg;
		char* tmp = new char[lg];
		for (int i = 0; i < _lg; i++)
		{
			tmp[i] = _chaine[i];
		}
		for (int i = 0; i < c._lg; i++)
		{
			tmp[i + lg1] = c._chaine[i];
		}
		tmp[lg - 1] = '\0';
		return new Chaine(tmp);
	}

	Chaine* Chaine::sous_chaine(char deb, char fin)
	{
		int ind1=-1;
		int ind2=-1;
		int i;
		for (i = 0; i < _lg; i++)
		{
			if (_chaine[i] == deb) {
				ind1 = i;
				break;
			}
		}
		for (i; i < _lg; i++)
		{
			if (_chaine[i] == fin){
				ind2 = i;
				break;
			}
		}
		if (ind1 == -1 || ind2 == -1){
		//cout << "L'un des deux caracteres n'est pas presents dans la chaîne" << endl;
			return new Chaine();
		}
        /*
	else{
		if (ind1 == -1)
		{
			//cout << "Le caractere de debut n'est pas present dans la chaine" << endl;
			return NULL;
		}
		else
		{
			if (ind2 == -1)
            {
				//cout << "Le caractere de fin n'est pas present dans la chaine" << endl;
			    return NULL;
            }
		}

	}*/
		return this->sous_chaine(ind1, ind2);
	}
	Chaine* Chaine::sous_chaine(int ind1, int ind2)
	{
		if (ind1 < 0 || ind2 < 0){
		//cout << "Indice negatif place en parametre" << endl;
			return new Chaine();
		}
		if (ind1>_lg || ind2 > _lg)
		{
		//cout << "Indice hors de la chaine" << endl;
		}
		int lg = ind2 - ind1;
		if (lg < 0){
		//cout << "Indice de debut superieur a l'indice de fin" << endl;
			return new Chaine();
		}
		char* tmp = new char[lg+2];
		for (int i = 0; i < lg+1; i++)
		{
			tmp[i] = _chaine[i + lg-1];
		}
		tmp[lg + 1] = '\0';
		return new Chaine(tmp);
	}

	void Chaine::afficher()
	{
		if (_lg < 0) {
			cout << "empty string";
		}
		for (int i = 0; i < _lg; i++)
		{
			cout << _chaine[i];
		}
		cout << endl;
	}
