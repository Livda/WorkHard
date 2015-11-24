/**
 * @author  Aurélien Fontaine
 * @author  François Boschet
 */

#include "Fraction.h"
#include "my_overflow_error.h"
#include "my_underflow_error.h"
#include <iostream>
#include <exception>
#include <algorithm>


using namespace std;

int Fraction::testOverflowAdd(int a, int b) {
	int res = a + b;
	if (res < 0) throw my_overflow_error("Addition de deux nombres trop grands", a, b);
	return res;
}

int Fraction::testUnderflowAdd(int a, int b) {
	int res = a + b;
	if (res > 0) throw my_underflow_error("Addition de deux nombres trop petits", a, b);
	return res;
}

int Fraction::testFlowAdd(int a, int b){
	if (a < 0 && b < 0) {
		return testUnderflowAdd(a, b);
	}
	else{
		return testOverflowAdd(a, b);
	}
}

int Fraction::testOverflowMul(int a, int b) {
	int res = a*b;
	if (res / a != b) throw my_overflow_error("Multiplication trop grande", a, b);
	return res;
}

int Fraction::testUnderflowMul(int a, int b) {
	int res = a*b;
	if (res / a != b) throw my_underflow_error("Multiplication trop petite (ctb)", a, b);
	return res;
}

int Fraction::testFlowMul(int a, int b){
	if (a < 0 || b < 0){
		return testUnderflowMul(a, b);
	}
	else{
		return testOverflowMul(a, b);
	}
}

Fraction Fraction::pgcd(int a, int b)
{
	int num = a;
	int den = b;
	while (b != 0) {
		int c = a%b;
		a = b;
		b = c;
	}
	return Fraction(num / a, den / a);
}

Fraction::Fraction()
{
	n = d = NULL;
}

Fraction::Fraction(const Fraction & f)
{
		n = f.n;
		d = f.d;
}

Fraction::Fraction(int entier)
{
	n = entier;
	d = 1;
}

Fraction::Fraction(int numerateur, int denominateur)
{
	if (denominateur == 0)
	{
		throw invalid_argument("Tentative de creation d'une fraction avec 0 pour denominateur");
	}
	else
	{
		n = numerateur;
		d = denominateur;
	}
}

Fraction::~Fraction()
{
}

Fraction Fraction::add(const Fraction & f)
{
	int num, den, tmp1, tmp2;
	/*
	* I had one of the most beautiful solution about the overflow problem but @AurelienFontaine betrayed me
	* So we used a more "reader-friendly" one. I am diappointed.
	* Francois Boschet
	int a = (f.d < 0 || this->n < 0) ? testUnderflowMul(this->n, f.d) : testOverflowMul(this->n, f.d),
		b = (f.n < 0 || this->d < 0) ? testUnderflowMul(this->d, f.n) : testOverflowMul(this->d, f.n);

	num = (a < 0 && b < 0) ? testUnderflowMul(a, b) : testOverflowMul(a, b);
	den = (f.d < 0 || this->d < 0) ? testUnderflowMul(this->d, f.d) : testOverflowMul(this->d, f.d);*/

	tmp1 = testFlowMul(this->n, f.d);
	tmp2 = testFlowMul(this->d, f.n);
	num = testFlowAdd(tmp1, tmp2);
	den = testFlowMul(this->d, f.d);
	return this->pgcd(num, den);
}

Fraction Fraction::sub(const Fraction & f)
{
	Fraction tmp = Fraction(f);
	tmp = tmp.mul(Fraction(-1));
	return this->add(tmp);
}

Fraction Fraction::mul(const Fraction & f)
{
	int num, den;
	num = testFlowMul(this->n, f.n);
	den = testFlowMul(this->d, f.d);
	return this->pgcd(num, den);
}

Fraction Fraction::div(const Fraction & f)
{
	if (f.n == 0)
	{
		throw domain_error("Tentative de division par 0");
	}
	else
	{
		int num, den;
		num = testFlowMul(this->n, f.d);
		den = testFlowMul(this->d, f.n);
		return this->pgcd(num, den);
	}
}

double Fraction::eval()
{
	return (double)n / d;
}

void const Fraction::afficher()
{
	cout << n << " / " << d << endl;
}
