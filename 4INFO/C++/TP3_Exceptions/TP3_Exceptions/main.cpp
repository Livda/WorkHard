#include "Fraction.h"
#include "my_overflow_error.h"
#include "my_underflow_error.h"

#include <assert.h>
#include <exception>
#include <iostream>

/*
 * Test-purpose functions
 */
bool equal(Fraction &a, Fraction &b) { return a.eval() == b.eval(); }
bool equal(Fraction &a, double b) {  return a.eval() == b; }
// Useless method but method's pointers are cool. Like bowties.
// I did this because it might be useful in the future (we never know)
template <class E> bool except(Fraction &f1, Fraction &f2, Fraction (Fraction::*method)(const Fraction & f)) {
	try {
		(f1.*method)(f2);
	}
	catch (const E &e) { return true; }
	catch (...) { cout << "c'est la mer noire" << endl; return false; }
	return false;
}

int main()
{

	Fraction &f1 = Fraction(1, 2);
	Fraction &f2 = Fraction(-1, 2);
	Fraction &f3 = Fraction(-1, -2);
	Fraction &f4 = Fraction(1, -2);
	Fraction &f5 = Fraction(INT_MAX, 2);
	Fraction &f6 = Fraction(2, INT_MAX);
	Fraction &f7 = Fraction(INT_MIN, 2);
	Fraction &f8 = Fraction(2, INT_MIN);

	assert(equal(f1, f3));
	assert(equal(f2, f4));
	cout << "Fraction equivalence passed" << endl;

	assert(equal(f1.add(f3), 1));
	assert(equal(f1.add(f4), 0));
	assert(except<my_overflow_error>(f1, f5, &Fraction::add));
	assert(except<my_overflow_error>(f1, f6, &Fraction::add));
	assert(except<my_underflow_error>(f2, f7, &Fraction::add));
	assert(except<my_underflow_error>(f4, f8, &Fraction::add));
	cout << "Fraction addition passed" << endl;

	assert(equal(f1.sub(f3), 0));
	assert(equal(f1.sub(f2), 1));
	assert(except<my_underflow_error>(f7, f1, &Fraction::sub));
	assert(except<my_underflow_error>(f7, f4, &Fraction::sub));
	assert(except<my_overflow_error>(f5, f2, &Fraction::sub));
	cout << "Fraction substraction passed" << endl;

	assert(equal(f1.mul(f3), 0.25));
	assert(equal(f2.mul(f4), 0.25));
	assert(except<my_overflow_error>(f5, f6, &Fraction::mul));
	assert(except<my_underflow_error>(f8, f2, &Fraction::mul));
	assert(except<my_underflow_error>(f8, f6, &Fraction::mul));
	cout << "Fraction multiplication passed" << endl;

	assert(equal(f1.div(f3), 1));
	assert(equal(f2.div(f4), 1));
	assert(except<my_overflow_error>(f5, f1, &Fraction::div));
	assert(except<my_underflow_error>(f7, f4, &Fraction::div));
	assert(except<my_underflow_error>(f7, f6, &Fraction::div));
	cout << "Fraction division passed" << endl;

	cout << "yata!" << endl;
	system("pause");
    return 0;
}

