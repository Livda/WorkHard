def divisors(nb, extremum = False):
    divisors = []
    inf = 1 if extremum else 2
    for i in range(inf, int(nb**0.5)+1):
        q, r = divmod(nb, i)
        if r == 0:
            if q >= i:
                divisors.append(i)
                if q > i:
                    divisors.append(nb//i)
    return divisors
#Initialisation du premier nombre triangulaire à 3
triangle = 3
i = 2
nb_divisors = 0
while nb_divisors < 500:
    #Calcul du nouveau nombre triangulaire
    i += 1
    triangle += i
    #Récupération du nombre de diviseurs qu'il possède
    nb_divisors = len(divisors(triangle, True))
print(triangle)
