//Les variables
var age = parseInt(prompt('Quel est votre âge ?'));

if  (age > 0 && age < 18) {
    alert('Vous n\'êtes pas majeur.');
} else if (age >= 18 && age < 50) {
    alert('Vous êtes majeur mais pas encore senior.');
} else if (age >= 50 && age < 60) {
    alert('Vous êtes sénior mais pas encore retraité');
} else if (age >= 60 && age < 120) {
    alert('Vous êtes retraité, profitez du temps qu\'il vous reste !');
} else {
    alert('Veuillez entrer un nombre compris entre 1 et 120.');
}


//Les objets et les tableaux
var nicks = [], nick;
while (nick = prompt('Entrez un prénom')) {
    nicks.push(nick);
}

if(nicks.length > 0) {
    alert(nicks.join('\n'));
}else {
    alert('Ta mémoire est vide crétin des Alpes !');
}


//Le débug
function test() {
    alert('Hello !');
}

for (var i = 0; i < 10; i++){
    console.log('La valeur de i : '+i);
}

// On crée un objet basique.
var helloObject = {
    english: 'Hello',
    french: 'Bonjour',
    spanish: 'Hola'
};
// Et on l'affiche.
console.log(helloObject);
// Tant qu'à faire, on affiche aussi un tableau.
var helloArray = ['Hello', 'Bonjour', 'Hola'];
console.log(helloArray);

// On déclare une variable contenant un texte quelconque.
var myVar = 'Hello';
// Toutes les secondes, on affiche le contenu de cette variable dans la console.
setInterval(function() {
    console.log(myVar);
}, 1000);
