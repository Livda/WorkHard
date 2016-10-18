var sum = 2;
var fibo1 = 1;
var fibo2 = 2;

while (fibo1 < 4000000) {
    var temp = fibo2;
    fibo2 += fibo1;
    fibo1 = temp;
    if (fibo2 % 2 == 0) {
        sum += fibo2;
    }
}

console.log(sum);
