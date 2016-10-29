file = open('input_067', 'r')

triangle = []
for line in file:
    tab = [int(n) for n in line.split()]
    triangle.append(tab)

result = 0
position = 0
last_array = []
for line in reversed(triangle):
    if last_array == []:
        last_array = line
    elif len(line) == 1:
        result = line[0] + max(last_array)
    else:
        new_array = []
        for index in range(0, len(line)):
            new_array.append(line[index] + max(last_array[index], last_array[index+1]))
        last_array = new_array
print(result)

