#pragma once

#include <exception>
#include <string>
using namespace std;

class my_overflow_error : public exception{

public :
	my_overflow_error();
	my_overflow_error(string s);
	my_overflow_error(string s, int a, int b);
};