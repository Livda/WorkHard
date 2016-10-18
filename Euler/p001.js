var max = 1000;
var sum = 0;

for (var i = 0; i < max; i++) {
    if (i%3 == 0) {
        sum += i;
    } else if (i%5 == 0) {
        sum += i;
    }
}

console.log(sum);
