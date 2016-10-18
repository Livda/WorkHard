var max = 100;
var sum = 0;
var sum2 = 0;
for(var i = 1; i <= max ; i++) {
    sum += i;
    sum2 += i*i;
}
var square = sum*sum;
console.log(square - sum2);

