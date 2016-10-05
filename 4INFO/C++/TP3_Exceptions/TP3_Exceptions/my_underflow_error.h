#pragma once

#include <exception>
#include <string>
using namespace std;

class my_underflow_error : public exception{

public :
	my_underflow_error();
	my_underflow_error(string s);
	my_underflow_error(string s, int a, int b);
};