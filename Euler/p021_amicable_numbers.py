value = 10000

def all_divisors(number):
    array = []
    for divisor in range(1, number//2+1):
        if number % divisor == 0:
            array.append(divisor)
    return array

sum_proper_divisor = [0 for n in range(0, value+1)]

for number in range(2, value):
    array = all_divisors(number)
    sum = 0
    for x in array:
        sum += x
    sum_proper_divisor[number] = sum

result = 0
for index in range(0, value+1):
    temp = sum_proper_divisor[index]
    if temp < value and sum_proper_divisor[temp] == index and index < temp:
        result += temp + index

print(result)
