file = open('input_022', 'r')
# get all the content of the file
for line in file:
    string = line
file.close()

# split the big line into an array
tab = [n for n in line.split(",")]
names = []
# remove all the " char of the strings
for name in tab:
    names.append(name.replace("\"", ""))
# sort the array by names
names.sort()
#get the value of each name
result = 0
for index in range(0, len(names)):
    char_array = list(names[index])
    temp_sum = 0
    for charactere in char_array:
        # 65 is the value of 'A'
        temp_sum += ord(charactere) - 64
    # the parenthesis are VERY important
    result += temp_sum * (index+1)
    # debug
    # if index == 937:
    #     print(char_array, temp_sum, index+1, temp_sum * (index+1))

print(result)
