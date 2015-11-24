var nb = 600851475143;
var racine = 775146;
var tab = [2];
var i = 0;
var res;
for (i; i < 775146; i++){
    if ( tab.indexOf(i) == -1 ) {
        tab.push(i);
        if (nb%i == 0){
            res = i;
        }
    }
}
console.log(res);
