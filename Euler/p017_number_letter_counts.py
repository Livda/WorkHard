#Création des listes contenant les mots
unites = ['one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine', 'ten', 'eleven', 'twelve', 'thirteen', 'fourteen', 'fifteen', 'sixteen', 'seventeen', 'eighteen', 'nineteen']
dizaines = ['twenty', 'thirty', 'forty', 'fifty', 'sixty', 'seventy', 'eighty', 'ninety']
autre = ['hundred', 'thousand', 'and']
resultat = 0
#Calcul du nombre de lettres à utiliser pour écrire les nombres de 1 à 1000
for i in range(1, 1001):
    #Stockage du chiffre des unités, dizaines, centaines, milliers dans des variables
    unite = i%10
    dizaine = (i//10)%10
    centaine = (i//100)%10
    millier = (i//1000)%10
    #Testes pour savoir à quelles conditions le nombre répond
    if millier != 0:
        resultat += len(unites[0]) + len(autre[1])
    if i%1000 != 0:
        if centaine != 0:
            resultat += len(unites[centaine-1]) + len(autre[0])
            if i%100 != 0:
                resultat += len(autre[2])
        if i%100 != 0:
            if dizaine < 2:
                resultat += len(unites[i%100-1])
            else:
                resultat += len(dizaines[dizaine-2])
                if unite != 0:
                    resultat += len(unites[unite-1])
print(resultat)
