import math

def combination(n, r): # correct calculation of combinations, n choose k
    return int((math.factorial(n)) / ((math.factorial(r)) * math.factorial(n - r)))

def pascals_triangle(rows):
    result = []
    for count in range(rows + 1):
        row = []
        for element in range(count + 1):
            row.append(combination(count, element))
        result.append(row)
    return result

size = 20
result = pascals_triangle(size)
for row in result:
    print(row)
print(result[-1][len(result[-1])//2])
