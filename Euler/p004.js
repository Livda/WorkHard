function isPalindrome(num) {
    var digits = (""+num).split("");
    var size = digits.length;
    var mid = size/2;
    var nope = true;
    for (var i = 0; i < mid; i++) {
        if(digits[i] != digits[size-i-1]){
            nope = false;
            break;
        }
    }
    if (nope) {
        //console.log("palindrome");
        return true;
    } else {
        //console.log("pas palindrome");
        return false;
    }
}
var pal = [];
function largestPalin(nbDigit) {
    var max = 1000;
    var min = 999;
    var test = 5000;
    var stop = false;
    for (var i = max; i > 1 && !stop; i--) {
        for (var j = max; j > 1 && !stop; j--) {
            if(isPalindrome(i*j)) {
                console.log("i : " + i + "; j : " + j + "; palindrome : " + i*j);
                stop = true;
            }
        }
    }
}

largestPalin(3);
//console.log(isPalindrome(99899));
