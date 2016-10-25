import math
import sys

def solve(tab):
    energy = [-1 for x in range(len(tab))]
    energy[0] = 0
    for i in range(len(tab)):
        j = 1
        while i + j < len(tab):
            testing = abs(tab[i] - tab[i+j])
            value = energy[i] + testing
            if energy[i+j] == -1 or energy[i+j] > value :
                energy[i+j] = value
            j = j*2
    return energy[-1]


Nbuilding = input()
read = [int(n) for n in input().split()]
sol = solve(read)
print(sol)
#Solved
