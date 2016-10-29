import math

number = pow(2, 1000)

tab = list(map(int, str(number)))

sum = 0
for x in tab:
    sum += x

print(sum)
