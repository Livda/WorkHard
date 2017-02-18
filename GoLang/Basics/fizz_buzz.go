package main

import "fmt"

func main() {
    for i := 1; i < 101; i++ {
        if IsMul5(i) {
            if IsMul3(i) {
                fmt.Println("FizzBuzz")
            } else {
                fmt.Println("Buzz")
            }
        } else if IsMul3(i) {
            fmt.Println("Fizz")
        } else {
            fmt.Println(i)
        }
    }
}

func IsMul5(number int) bool {
    modulo := number % 10
    return (modulo == 0) || (modulo == 5)
}

func IsMul3(number int) bool {
    sum := Sum(uint64(number), 10)
    return (sum % 3) == 0
}

// from https://rosettacode.org/wiki/Sum_digits_of_an_integer#Go
func Sum(i uint64, base int) (sum int) {
    b64 := uint64(base)
    for ; i > 0; i /= b64 {
        sum += int(i % b64)
    }
    return
}
