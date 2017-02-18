package main

import "fmt"

/* Print something */
func main() {
    // loop1()
    // loop2()
    loop3()
}

func loop1() {
    for cpt := 0; cpt < 10; cpt++ {
        fmt.Printf("Le compteur est à %d.\n", cpt)
    }
}

func loop2() {
    cpt := 0
False_for:
    fmt.Printf("Le compteur est à %d.\n", cpt)
    cpt++
    if cpt < 10 {
        goto False_for
    }
}

func loop3() {
    var array [10]int
    for cpt := 0; cpt < 10; cpt++ {
        array[cpt] = cpt
    }
    fmt.Printf("%v\n", array)
}
