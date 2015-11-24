var nbr = parseInt(prompt("Entrez un nombre entre 0 et 999"));

if (isNaN(nbr) || nbr > 999) {
    alert("Entre un nombre crétin entre 0 et 999");
} else {
    var unites = ["","un", "deux", "trois", "quatre", "cinq", "six", "sept", "huit", "neuf"];
    var dizaines = ["","dix","vingt","trente","quarente","cinquante","soixante","soixante-dix","quatre-vingt","quatre-vingt-dix"];
    var dizBiz = ["","onze","douze","treize","quatorze","quinze","seize"];
    var unite = parseInt(nbr % 10);
    var dizaine = parseInt((nbr % 100 - unite)/10);
    var centaine = parseInt((nbr - dizaine*10 - unite)/100);

    var zero = unite == 0 && dizaine == 0 && centaine == 0;
    var u, d, c; //les chaines de caracrères

    if (centaine != 0){
	c = unites[centaine];
	c += " cent";
	if (unite == 0 && dizaine == 0) {
	    c += "s ";
	    d = "";
	    u = "";
	}
	if (centaine == 1) {
	    c = "cent";
	}
    }else {
	c = "";
    }

    if(dizaine != 0){
	if (unite == 1) {
	    u = " et un";
	}
	var uni7 = unite < 7;
	if (dizaine == 1 && uni7){
	    d = "";
	    u = dizBiz[unite];
	} else if (dizaine == 7 && uni7) {
    	    d = "soixante et";
	    u = dizBiz[unite];
	} else if (dizaine == 9 && uni7) {
	    d = "quatre-vingt-";
	    u = dizBiz[unite];
	} else {
	    d = dizaines[dizaine] + "-";
	    u = unites[unite];
	}
    }

    if (zero) {
	alert("zéro");
    } else {
/*	console.log(centaine + "  " + dizaine + "  " + unite);
	console.log("nombre = "+nbr+"\n");
	console.log(" c = "+c+"\n");
	console.log(" d = "+d+"\n");
	console.log(" u = "+u+"\n");*/
	alert(c+d+u);
    }
}
