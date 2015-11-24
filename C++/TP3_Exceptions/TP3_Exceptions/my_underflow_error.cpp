#include "my_underflow_error.h"
#include <iostream>
#include <sstream>

my_underflow_error::my_underflow_error(){
}

my_underflow_error::my_underflow_error(string s){
}

my_underflow_error::my_underflow_error(string s, int a, int b){
	std::ostringstream res;
	res << ": " << a << ", " << b << endl;
	string sres = res.str();
	my_underflow_error(s + sres);
}