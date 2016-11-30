# get all the divisors of a number
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

# test if the number n is abuntant
def is_abundant(n):
    return sum(divisors(n))+1 > n

# generate a list with all the abundant number under the upper_bound
upper_bound = 28123
abundants = [i for i in range(2, upper_bound+1) if is_abundant(i)]
sommes = {}
# generate all the possible sum of abundant numbers
for i in range(len(abundants)):
    for j in range(i, len(abundants)):
        somme = abundants[i] + abundants[j]
        if somme > upper_bound:
            break
        sommes[somme] = 1

resultat = (upper_bound*(upper_bound+1))//2 - sum(sommes.keys())
print(resultat)

# > time python3 p023_non-abundant_sums.py
# 4179871
# python3 p023_non-abundant_sums.py  6,37s user 0,01s system 100% cpu 6,379 total
