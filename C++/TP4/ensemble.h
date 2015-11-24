#pragma once

#include "list.h"

template <class T> class Ensemble : public List<T>{

public:

// =====================================================================
// But: constructeur par défaut : création d'une liste vide
// =====================================================================
	Ensemble<T>() : List<T>() {}

// =====================================================================
// But: constructeur par recopie
// =====================================================================
	Ensemble<T>(const Ensemble<T> & ens) : List<T>(ens){}

// =====================================================================
// But: destructeur
// =====================================================================
	~Ensemble<T>(){}
	
// =====================================================================
// But: les magasins près de chez vous
// =====================================================================
	bool doesContain(const T & v) {
		//std::cout << "gaston" << std::endl;
		int i, len = this->card();
		bool exist = false;
		for (i = 1; i <= len; i++){
			T & zz = (*this)[i];
			//std::cout << "prunelle " << zz << " mlle jeanne" << std::endl;
			if (zz == v)
				exist = true;
		}
		return exist;
	}

// =====================================================================
// But: ajout d'un élément à une liste (par défaut en tête de liste)
// =====================================================================
	void addElement(const T& v, eListPosition pos = LP_last) {
		//std::cout << "fantasio" << std::endl;
		if (! this->doesContain(v))
			List::addElement(v, LP_last);
	}

// =====================================================================
// But: opérateur d'union
// =====================================================================
	Ensemble<T> operator+(const Ensemble<T> & v) const{
		Ensemble<T> lres(*this);
		//std::cout << "poupilou" << card() << std::endl;
		int i, len = v.card();
		for (i = 1; i <= len; i++){
			//std::cout << "bulgroz" << std::endl;
			lres.addElement(v[i], LP_last);
		}
		//std::cout << "spirou" << std::endl;
		return lres;
	}

// =====================================================================
// But: opérateur de difference (standard)
// =====================================================================
Ensemble<T> operator-(const Ensemble<T> & v) const{
		Ensemble<T> lres(*this);
		int i, len = v.card();
		for (i = 1; i <= len; i++)
			lres.delElement(v[i]);
		return lres;
	}

// =====================================================================
// But: opérateur de intersection d'un élément d'une liste
// =====================================================================
	Ensemble<T> operator*(const Ensemble<T> & v) const{
		Ensemble<T> temp(*this);
		Ensemble<T> lres;
		//std::cout << "Rastapopoulos" << lres << std::endl;
		int i, len = v.card();
		for (i = 1; i <= len; i++){
			//std::cout << "Tournesol -> " << v[i] << std::endl;;
				if (temp.doesContain(v[i]))
					lres.addElement(v[i], LP_last);
		}
		return lres;
	}

// =====================================================================
// But: opérateur de difference symetrique d'un élément d'une liste
// =====================================================================
	Ensemble<T> operator/(const Ensemble<T> & v) const{
		Ensemble<T> lres = ((*this) + v) - ((*this)*v);
		return lres;
	}

};