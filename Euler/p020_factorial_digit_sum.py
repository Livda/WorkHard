import math

value = 100
factorial_number = math.factorial(value)
tab = list(map(int, str(factorial_number)))
sum = 0
for x in tab:
    sum += x

print(sum)
