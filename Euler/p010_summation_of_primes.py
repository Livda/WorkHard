prime = "2 101 233 383 3 103 239 389 5 107 241 397 7 109 251 401 11 113 257 409 13 127 263 419 17 131 269 421 19 137 271 431 23 139 277 433 29 149 281 439 31 151 283 443 37 157 293 449 41 163 307 457 43 167 311 461 47 173 313 463 53 179 317 467 59 181 331 479 61 191 337 487 67 193 347 491 71 197 349 499 73 199 353 503 79 211 359 509 83 223 367 521 89 227 373 523 97 229 379 541"

array_prime = [int(n) for n in prime.split()]

def isPrime(num):
    if num == 2:
        return True
    if num == 3:
        return True
    if num % 2 == 0:
        return False
    if num % 3 == 0:
        return False
    i = 5
    w = 2
    while i * i <= num:
        if num % i == 0:
            return False
        i += w
        w = 6 - w
    return True

for i in range(543, 2000000, 2):
    if isPrime(i):
        array_prime.append(i)

all_of_them = 0

for x in array_prime:
    all_of_them += x

print(all_of_them)
