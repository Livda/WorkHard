/**
 * \brief Main du TP1.
 *
 * Usage :  .\TP1 < fichier_a_lire
 */

#include "Carte.h"
#include <iostream>
#include <cstdlib>

int main()
{
    // Initialisation des paquets/cartes selon la suite des N et S provenant 
    // de l'entree standard std::cin (on suppose la redirection de stdin).
    char p[52]; // pour inverser la creation des listes, on lit 52 char.
    for(int i = 0; i < 52; i++)
    {
        // lecture d'une carte
        std::cin >> p[i];
    }

    for(int icoul = 3; icoul >= 0; icoul--)
    {
        for(int ihaut = 12; ihaut >= 0; ihaut--)
        {
            const char proprietaire = p[icoul * 13 + ihaut];
            if(proprietaire != 'N' && proprietaire != 'S')
            {
                std::cerr << "Erreur dans l'initialisation : "
                          << "'N' ou 'S' etait attendu"
                          << std::endl;
                exit(1);
            }

            Couleur couleur = static_cast<Couleur>(icoul + 1);
            Valeur valeur = static_cast<Valeur>(ihaut + 1);

            // Attention : le constructeur doit enchainer la Carte avec la
            // bonne liste.
            //
            // Note : les pointeurs dans les champs statiques de la classe
            // Carte permettront de liberer la memoire a la fin du programme.
            new Carte(couleur, valeur, proprietaire);
        }
    }

    // Affichages des paquets 
    std::cout << "Les paquets de depart sont :" << std::endl;
    std::cout << "Paquet de N :" << std::endl;
    Carte::afficherN(); 
    std::cout << std::endl;
    std::cout << "Paquet de S :" << std::endl;
    Carte::afficherS();   
    std::cout << std::endl;

    // Boucle principale : le jeu est termine, si une des listes est vide.
    std::cout << "Boucle principale" << std::endl;
    while(Carte::getNTete() != 0 && Carte::getSTete() != 0)
    {
        Carte* Nt = Carte::getNTete();
        Carte* St = Carte::getSTete();
		std::cout << "Carte de N :";
        Nt->afficher();
		std::cout << "Carte de S :";
        St->afficher();
        std::cout << std::endl;

        // detection d'une bataille (i.e. les deux cartes ont la meme valeur)
        while(0 != Nt && 0 != St && Nt->egale(*St))
        {
            std::cout << "** bataille ** " << std::endl;
            Nt = Nt->suc();
            if(0 != Nt)
            {
                // avancer de deux cartes pour N !
                Nt = Nt->suc(); 
				std::cout << "Carte de N :";
				Nt->afficher();		
            }
            St = St->suc(); 
            if(0 != St)
            {
                // avancer de deux cartes pour S !
                St = St->suc(); 
				std::cout << "Carte de S :";
				St->afficher();		
            }
        }
        if(0 == Nt)
        {
            // N n'a pas assez de carte : S gagne
            std::cout <<" N : plus de Carte" << std::endl;
            while(0 != Carte::getNTete())
            {
                Carte* Nec = Carte::getNTete();
                Nec->changerProp(); 
            }
        }
        else if(0 == St)
        {
            // S n'a pas assez de carte
            std::cout <<" S : plus de Carte" << std::endl;
            while(0 != Carte::getSTete())
            {
                Carte* Sec = Carte::getSTete();
                Sec->changerProp(); 
            }
        }
        else if(Nt->supAbs(*St))
        {
            // comparaison des dernieres cartes : S a perdu cette bataille
            std::cout << std::endl;
            std::cout << " N gagne les cartes :" << std::endl;
            Carte* Ss = St->suc(); // premiere Carte non gagnee
            while (Carte::getSTete() != Ss)
            {
                Carte* Sec = Carte::getSTete();
                // On met les cartes en question a la fin du paquet N
                Sec->changerProp();
                Carte* Nec = Carte::getNTete();
                Nec->passerDerriere(); 
                // puis on affiche la situation
                Sec->afficher();
                Nec->afficher();
                std::cout << std::endl;
            }
                std::cout << "----" << std::endl;
        }
        else
        {
            // N a perdu cette bataille
            std::cout << std::endl;
            std::cout << " S gagne les cartes :"  << std::endl;
            Carte* Ns = Nt->suc(); // premiere carte non gagnee 
            while(Carte::getNTete() != Ns)
            {
                Carte* Nec = Carte::getNTete();
                // On met les cartes en question a la fin du paquet S
				Nec->changerProp();
                Carte* Sec = Carte::getSTete();
                Sec->passerDerriere();
                // puis on affiche la situation
                Nec->afficher();
                Sec->afficher();
                std::cout << std::endl;
            }
            std::cout << "----" << std::endl;
        }
    }
    std::cout << " >>>>>>>>>>>>> le gagnant de ce jeu est : ";
    if(0 == Carte::getNTete())
    {
        // N a perdu les jeux
        std::cout << 'S' << std::endl;
        Carte* Sec = Carte::getSTete();
        while(0 != Sec)
        {
            // destruction du paquet de S
            Carte* Ss = Sec->suc();
            delete Sec;
            Sec = Ss;
        }
    }
    else
    {
        // S a perdu les jeux
        std::cout << 'N' << std::endl;
        Carte* Nec = Carte::getNTete();
        while(0 != Nec)
        {
            // destruction du paquet de N
            Carte* Ns = Nec->suc();
            delete Nec;
            Nec = Ns;
        }
    }
    std::cout << "fin de partie" << std::endl;
    return 0;
}

