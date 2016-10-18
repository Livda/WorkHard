function printPrime(value) {
    var div = [];
    var index = 0;
    for (var i = 2; i < Math.sqrt(value) ; i++) {
        if (value % i == 0) {
            div[index] = i;
            index++;
        }
    }
    var prime = div;
    for (var i = 0; i < div.length; i++) {
        for (var j = 0; j < div.length; j++) {
            if (i != j && div[j] % div[i] == 0) {
                prime[j] = 0;
            }
        }
    }
    console.log(div);
}

printPrime(600851475143);
