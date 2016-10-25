import sys

matrix = [[0 for x in range(51)] for y in range(51)]

def solve(n, k, m):
    if n == 0 and k == 0:
        return 1
    if k <= 0:
        return 0
    if n <= 0:
        return 0

    if matrix[n][k] == 0:
        som = 0
        for i in range(1, m+1):
            som += solve(n-i, k-1, m)
        matrix[n][k] = som
    return matrix[n][k]

for line in sys.stdin:
    read = [int(n) for n in line.split()]
    n = read[0]
    k = read[1]
    m = read[2]
    resultat = solve(n, k, m)
    print(resultat)
