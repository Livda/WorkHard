var end = true;
var test = 1;
while (end) {
    var end = false;
    for(var i = 2; i <= 20; i++) {
        if (test % i != 0) {
            end = true;
            break;
        }
    }
    test++;
}
console.log(test-1);
