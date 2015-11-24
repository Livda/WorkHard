#include "my_overflow_error.h"
#include <iostream>
#include <sstream>

my_overflow_error::my_overflow_error(){
}

my_overflow_error::my_overflow_error(string s){
}

my_overflow_error::my_overflow_error(string s, int a, int b){
	std::ostringstream res;
	res << ": " << a << ", " << b << endl;
	string sres = res.str();
	my_overflow_error(s + sres);
}