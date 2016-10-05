//=======================================================================
// Nom      : 	main.cpp
//
//=======================================================================

#include <fstream>
#include <iostream>
#include "ensemble.h"

//=======================================================================
// M�thode   : 	Ensemble<int> lire(char* fname)
// But       : 	Lecture d'un ensemble dans un fichier texte
// Param�tres: 	fname	: 
// Retour    : 	Ensemble<int> 
//=======================================================================
Ensemble<int> lire(char* fname) {
  Ensemble<int> res;
  // Ouverture du fichier contenant l'ensemble
  std::ifstream input(fname,std::ios::in);
  if (!input)	std::cerr  << "Erreur de lecture de " << fname << std::endl;
  else			input >> res;
  // Fermeture du fichier
  input.close();
  return res;
}

//=======================================================================
// M�thode   : 	void main()
// But       : 	Programme principal de test
// Param�tres: 	-
//=======================================================================
int main() {
	// Lecture des ensembles
	Ensemble<int> e1 = lire("test.txt");
	Ensemble<int> e2 = lire("test2.txt");

	// Affichage des ensembles
	std::cout << "e1 = " << e1 << std::endl;
	std::cout << "e2 = " << e2 << std::endl;

	// Op�rations sur les ensembles
	std::cout << "test" << std::endl;
	std::cout << "Union        :: " << (e1 + e2) << std::endl;
	std::cout << "Intersection :: " << (e1 * e2)<< std::endl;
	std::cout << "Soustraction :: " << (e1 - e2)<< std::endl;
	std::cout << "Diff�rence   :: "   << (e1 / e2)<< std::endl;
	return 0;
}
