
import sys
from random import randint, shuffle, choice, sample



#Géneration des mutants

def gen_mutant_line(mat):
    res = []
    for line in mat:
        available = [e for e in  range(1, 10) if e not in [e-10 for e in line if e > 10]]
        #print(available)
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
        #print(available)
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
            #print(available)
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
    for line in r:
        fitness += len(set(line))
    for i in range(0, 9):
        fitness += len(set([line[i] for line in r]))
    for i in range(0,3):
        for j in range(0,3):
            square = set()
            for x in range(0, 3):
                for y in range(0, 3):
                    square.add(r[3*i+x][3*j+y])
            fitness += len(square)
    return fitness


#mutation

def mix_line(su1, su2, mut):
    
    mut_lo = mut[:]
    mut_lo.sort()
    
    son, goku = su1[:], su2[:]

    for i in mut_lo:
        son[i] = su1[i]
        goku[i] = su2[i]
    
    return son, goku 

def mix_column(su1, su2, mut):
    
    mut_lo = mut[:]
    mut_lo.sort()
    
    son, goku = su1[:], su2[:]

    for i in mut_lo:
        for j in range(0, 9):
            son[j][i] = su1[j][i]
            goku[j][i] = su2[j][i]
    
    return son, goku 

def mix_square(su1, su2, mut):
    
    mut_lo = mut[:]
    mut_lo.sort()
    
    mut_lo = [(i%3, i//3) for i in mut_lo]    

    son, goku = su1[:], su2[:]

    for i,j in mut_lo:
        for x in range(0, 3):
            for y in range(0, 3):
                son[3*i+x][3*y+j] = su1[3*i+x][3*y+j]
                goku[3*i+x][3*y+j] = su2[3*i+x][3*y+j]
    
    return son, goku 

def mix_gene(sudoku1, sudoku2):
    
    action = randint(0, 2)
    mutations = sample(range(0, 9), randint(1, 9))
    if action == 0:
        return mix_line(sudoku1, sudoku2, mutations)
    elif action == 1:
        return mix_column(sudoku1, sudoku2, mutations)
    else:
        return mix_square(sudoku1, sudoku2, mutations)


def mutate_gene(sudoku):
    
    son = sudoku[:]
    michel = True
    
    while michel:
        yamcha = randint(0, 80)
        i, j = yamcha%9, yamcha//9
        if sudoku[i][j] < 10:
            son[i][j] = randint(1, 9)
            return son
            

def splinter(pool):
    
    ny = pool[:]
    next_gen = []
    leonardo = max(pool, key=get_fitness)
    next_gen.append(leonardo)
    ny.remove(leonardo)

    while (len(ny) >= 2) :
        raphael = choice(ny)
        ny.remove(raphael)
        donatello = choice(ny)
        ny.remove(donatello)
        
        action = randint(0, 10)
        if action < 2:
            son, goku = mix_gene(raphael, donatello)
            next_gen.append(son)
            next_gen.append(goku)
        elif action < 9:
            mut = randint(0, 5)
            if mut <= 1:
                next_gen.append(mutate_gene(raphael))
            elif mut <= 3:
                next_gen.append(mutate_gene(donatello))
            else:
                next_gen.append(mutate_gene(raphael))
                next_gen.append(mutate_gene(donatello))
    return next_gen


# utils

def print_sudoku(mat):
    for line in mat:
        print(line)
    print("fitness: ", get_fitness(mat))

if __name__ == "__main__" :

    f = open(sys.argv[1], 'r')
    sudoku = [ list(map(int, line.split(" "))) for line in f ]
    f.close()

    sudoku = [[a+10 if a != 0 else a for a in line] for line in sudoku]

    ninja_turtles = [gen_mutant(sudoku) for a in range(5000)]

    max_fitness = max(ninja_turtles, key=get_fitness)

    it = 0

    while get_fitness(max_fitness) < 243:
        splinter(ninja_turtles)    
        max_fitness = max(ninja_turtles, key=get_fitness)
        print_sudoku(max_fitness)
        it += 1
        print("iteration: ", it, ", size: ", len(ninja_turtles))

    #for gene in pool:
    #    print("==============")
    #    print_sudoku(gene)

