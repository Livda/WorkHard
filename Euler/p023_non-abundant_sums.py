def all_divisors(number):
    array = []
    for divisor in range(1, number//2+1):
        if number % divisor == 0:
            array.append(divisor)
    return array

upper_bound = 28123
lower_bound = 12
abundant_numbers = []
# find all the abundant number between the bounds
for number in range(lower_bound, upper_bound):
    divisors = all_divisors(number)
    divisors_sum = 0
    for n in divisors:
        divisors_sum += n
    if divisors_sum > number:
        abundant_numbers.append(number)
# find all the numbers that are not a sum of abundant numbers
not_sum_of_abundant = []
for number in range(1, upper_bound):
    find = False
    for n1 in abundant_numbers:
        if n1 > number or find:
            break
        for n2 in abundant_numbers:
            if n2 >= n1 or find:
                break
            if (n1+n2) == number:
                find = True
    if not find:
        not_sum_of_abundant.append(number)

print(not_sum_of_abundant)
