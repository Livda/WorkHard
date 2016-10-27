longest_seq = 0
right_num = 1

def get_lg(number):
    if number == 1:
        return 1
    if number % 2 == 0:
        return 1 + get_lg(number / 2)
    else:
        return 1 + get_lg(3*number + 1)

for i in range (1, 1000000):
    calc = get_lg(i)
    if calc > longest_seq:
        longest_seq = calc
        right_num = i

print(longest_seq, " ", right_num)
