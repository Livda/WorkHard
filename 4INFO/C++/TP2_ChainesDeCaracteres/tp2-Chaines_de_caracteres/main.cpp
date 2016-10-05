/* This is an educational purpose String class.
 *
 * "Test" file.
 *
 * Written (in french) mostly by Aurelien Fontaine
 * with the help of Francois Boschet (sometimes)
 *
 * 4INFO - INSA of Rennes - G2.1
 */

#include <cassert>
#include <iostream>
#include "Chaine.h"


 int main() {

   cout << "Disclaimer: Le comportement des fonctions <, >, <=, >= sont comme definies pendant le tp : "
   "\"foo\" < \"foobar\" = false\n"
   "\"foobar\" < \"foo\" = true\n"
   "\"foo\" < \"foo\" =  false\n"
   "\"foo\" <= \"foo\" = true" << endl;

   cout << "Tests d'un comportement normal " << endl;
   cout << "==============================" << endl << endl;

   char text[5] = { 't','e','x','t' };
   char dragon[7] = { 'd','r', 'a', 'g', 'o', 'n' };

   cout << "Utilisation du constructeur par defaut" << endl;
   cout << "--------------------------------------" << endl;

   Chaine* cnull = new Chaine();
   Chaine* cnull2 = new Chaine(*cnull);
   cnull->afficher();
   cout << endl;

   cout << "Creation de c1" << endl;
   cout << "--------------" << endl;
   Chaine c1(text);
   c1.afficher();
   cout << endl;

   cout << "Creation de c1bis" << endl;
   cout << "-----------------" << endl;
   Chaine c1bis(c1);
   c1bis.afficher();
   cout << endl;

   cout << "Creation de c2" << endl;
   cout << "--------------" << endl;
   Chaine c2(dragon);
   c2.afficher();
   cout << endl;

   cout << "Utilisation des fonctions sur les objets" << endl;
   cout << "----------------------------------------" << endl;
   cout << endl;


   cout << "c1.getLong() = " << c1.getLong() << endl;
   assert(c1.getLong() == 4);

   cout << "c1 -> ";
   int len = c1.getLong(), i = 1;
   for (i = 1; i <= len; i++){
      assert(c1.getChar(i) == text[i-1]);
      cout << c1.getChar(i) << " ,";
  }
  cout << endl;

  assert(c1.getLong() == 4);
  cout << "c2.getLong() = " << c2.getLong() << endl;

  cout << "c2 -> ";
  len = c2.getLong();
  i = 1;
  for (i = 1; i <= len; i++){
      assert(c2.getChar(i) == dragon[i-1]);
      cout << c2.getChar(i) << " ,";
  }
  cout << endl;

  cout << "=== Sous chaines ===" << endl;

  cout << "c1 == \"text\"" << endl;
  cout << "c1.sous_chaine(2, 4) == "; c1.sous_chaine(2, 4)->afficher();
  cout << "c1.sous_chaine('e', 't') == " ; c1.sous_chaine('e', 't')->afficher();
  cout << "c1.sous_chaine(-1, 4) == "; c1.sous_chaine(-1, 4)->afficher();
  cout << "c1.sous_chaine('q', 't') == " ; c1.sous_chaine('q', 't')->afficher();

  cout << "c2 == \"dragon\"" << endl;
  cout << "c2.sous_chaine(2, 4) == " ; c2.sous_chaine(2, 4)->afficher();
  cout << "c2.sous_chaine('r', 'o') == " ; c2.sous_chaine('r', 'o')->afficher();
  cout << "c2.sous_chaine(-1, 4) == " ; c2.sous_chaine(-1, 4)->afficher();
  cout << "c2.sous_chaine('q', 't') == "  ; c2.sous_chaine('q', 't')->afficher();


  cout << "Utilisation des operateurs sur les objets" << endl;
  cout << "----------------------------------------" << endl;
  cout << endl;


  cout << " === Operateur + ===" << endl;
  Chaine c11(*(c1 + c1));
  c11.afficher();
  Chaine c12(*(c1 + c2));
  c12.afficher();
  Chaine c21(*(c2 + c1));
  c21.afficher();
  cout << endl << endl;

  cout << " === Operateur < ===" << endl;
  assert(!(c1 < c2));
  cout << "(c1 < c2) -> False" << endl;
  assert(!(c2 < c1));
  cout << "(c2 < c1) -> False" << endl;
  assert(!(c1 < c1));
  cout << "(c1 < c1) -> False" << endl;
  assert(c11 < c1);
  cout << "(c11 < c1) -> True" << endl;
  assert(!(c1 < c11));
  cout << "(c1 < c11) -> False" << endl;
  assert(!(c1 < c12));
  cout << "(c1 < c12) -> False" << endl;
  assert(c12 < c1);
  cout << "(c12 < c1) -> True" << endl;
  assert(!(c1 < c21));
  cout << "(c1 < c21) -> False" << endl;
  assert(!(c21 < c1));
  cout << "(c21 < c1) -> False" << endl;
  cout << endl;


  cout << " === Operateur > ===" << endl;
  assert(!(c1 > c2));
  cout << "(c1 > c2) -> False" << endl;
  assert(!(c2 > c1));
  cout << "(c2 > c1) -> False" << endl;
  assert(!(c1 > c1));
  cout << "(c1 > c1) -> False" << endl;
  assert(!(c11 > c1));
  cout << "(c11 > c1) -> False" << endl;
  assert(c1 > c11);
  cout << "(c1 > c11) -> True" << endl;
  assert(c1 > c12);
  cout << "(c1 > c12) -> True" << endl;
  assert(!(c12 > c1));
  cout << "(c12 > c1) -> False" << endl;
  assert(!(c1 > c21));
  cout << "(c1 > c21) -> False" << endl;
  assert(!(c21 > c1));
  cout << "(c21 > c1) -> False" << endl;
  cout << endl;


  cout << " === Operateur <= ===" << endl;
  assert(!(c1 <= c2));
  cout << "(c1 <= c2) -> False" << endl;
  assert(!(c2 <= c1));
  cout << "(c2 <= c1) -> False" << endl;
  assert(c1 <= c1);
  cout << "(c1 <= c1) -> True" << endl;
  assert(c11 <= c1);
  cout << "(c11 <= c1) -> True" << endl;
  assert(!(c1 <= c11));
  cout << "(c1 <= c11) -> False" << endl;
  assert(!(c1 <= c12));
  cout << "(c1 <= c12) -> False" << endl;
  assert(c12 <= c1);
  cout << "(c12 <= c1) -> True" << endl;
  assert(!(c1 <= c21));
  cout << "(c1 <= c21) -> False" << endl;
  assert(!(c21 <= c1));
  cout << "(c21 <= c1) -> False" << endl;
  cout << endl;

  cout << " === Operateur >= ===" << endl;
  assert(!(c1 >= c2));
  cout << "(c1 >= c2) -> False" << endl;
  assert(!(c2 >= c1));
  cout << "(c2 >= c1) -> False" << endl;
  assert(c1 >= c1);
  cout << "(c1 >= c1) -> True" << endl;
  assert(!(c11 >= c1));
  cout << "(c11 >= c1) -> False" << endl;
  assert(c1 >= c11);
  cout << "(c1 >= c11) -> True" << endl;
  assert(c1 >= c12);
  cout << "(c1 >= c12) -> True" << endl;
  assert(!(c12 >= c1));
  cout << "(c12 >= c1) -> False" << endl;
  assert(!(c1 >= c21));
  cout << "(c1 >= c21) -> False" << endl;
  assert(!(c21 >= c1));
  cout << "(c21 >= c1) -> False" << endl;
  cout << endl;

  cout << " === Operateur == ===" << endl;
  assert(c1 == c1bis);
  cout << "(c1 == c1bis) -> True" << endl;
  assert(!(c1 == c2));
  cout << "(c1 == c2) -> False" << endl;
  assert(!(c1 == c11));
  cout << "(c1 == c11) -> False" << endl;
  assert(!(c1 == c12));
  cout << "(c1 == c12) -> False" << endl;
  assert(!(c1 == c21));
  cout << "(c1 == c21) -> False" << endl;
  cout << endl;
  
  system("Pause");
  return 0;
}