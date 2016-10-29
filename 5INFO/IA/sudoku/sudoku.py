
import sys
from random import randint, shuffle



def gen_mutant_line(mat):
    res = []
    for line in mat:
        available = [e for e in  range(1, 10) if e not in [e-10 for e in line if e > 10]]
        print(available)
        shuffle(available)
        l_res =[]
        for num in line:
            if (num < 10):
                l_res.append(available.pop(0))
            else :
                l_res.append(num)
        res.append(l_res)
    return res

def gen_mutant_column(mat):
    res = [[], [], [], [], [], [], [], [], []]
    for i in range(0, 9):
        verboten = []
        for j in range(0, 9):
            if mat[j][i] > 10:
                verboten.append(mat[j][i]-10)
        available = [e for e in  range(1, 10) if e not in verboten]
        print(available)
        shuffle(available)
        for j in range(0,9):
            if (mat[j][i] < 10):
                res[j].append(available.pop(0))
            else :
                res[j].append(mat[j][i])
    return res

def gen_mutant_square(mat):
    res = [[], [], [], [], [], [], [], [], []]
    for i in range(0, 3):
        for j in range(0, 3):
            verboten = []
            for x in range(0, 3):
                for y in range(0, 3):
                    if mat[3*i+x][3*j+y] > 10:
                        verboten.append(mat[3*i+x][3*j+y]-10)
            available = [e for e in  range(1, 10) if e not in verboten]
            print(available)
            shuffle(available)
            for x in range(0, 3):
                for y in range(0, 3):
                    if (mat[3*i+x][3*j+y] < 10):
                        res[3*i+x].append(available.pop(0))
                    else :
                        res[3*i+x].append(mat[3*i+x][3*j+y])
    return res


def gen_mutant(mat):

    criterion = randint(1, 3)
    if criterion == 1:
       return gen_mutant_line(mat)
    elif criterion == 2:
        return gen_mutant_column(mat)
    elif criterion == 3:
        return gen_mutant_square(mat)
    else:
        print("WTF")
        return gen_mutant_line(mat)


def get_fitness(mat):
    r = [[e-10 if e > 10 else e for e in line] for line in mat]
    fitness = 0
    for line in mat:
        fitness += len(set(line))
    for i in range(0, 9):
        fitness += len(set([line[i] for line in mat]))
    for i in range(0,9):
        for j in range(0,9):



if __name__ == "__main__" :

    f = open(sys.argv[1], 'r')
    sudoku = [ list(map(int, line.split(" "))) for line in f ]
    f.close()

    sudoku = [[a+10 if a != 0 else a for a in line] for line in sudoku]


    print(gen_mutant_square(sudoku))


