var num = 2;
var find = 0;
while (find < 10001) {
    if (isPrime(num)) {find++;}
    num++;
}
console.log(num-1)

function isPrime(num) {
    if (num == 2) return true;
    if (num == 3) return true;
    if (num % 2 == 0) return false;
    if (num % 3 == 0) return false;
    var i = 5;
    var w = 2;
    while (i * i <= num){
        if (num % i == 0) {return false;}
        i += w;
        w = 6 - w;
    }
    return true;
}

