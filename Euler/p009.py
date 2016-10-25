import math

goal = 0

for a in range (500):
    for b in range(500):
        c2 = a*a + b*b
        c = math.sqrt(c2)
        print(c)
        if a + b + c == 1000:
            goal = a*b*c
            break

print(goal)
