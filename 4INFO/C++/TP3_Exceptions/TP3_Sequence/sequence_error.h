#include <exception>
#include <string>
using namespace std;

#pragma once

/*
 * Je sais qu'il aurait fallut faire une classe generique
 * pour éviter de recopier le code. Mais ca fait plus de code
 * et vu que dans les entreprises les développeurs sont payés
 * à la quantité de code, ça fait plus d'argent. Et le pognon
 * est vraiment ma priorité dans la vie. Le pognon et la 
 * richesse, (avec un peu de notoriété).
 * Cordialement,
 * Francois Boschet.
 */

class bad_seq_char : public exception {
private:
	string stack;
public :
	bad_seq_char();
	bad_seq_char(const char c, const string & seq, const string & alpha);
	virtual const char* what() const throw () {
		return stack.c_str();
	}
};

class bad_seq_init : public exception {
private:
	string stack;
public :
	bad_seq_init();
	bad_seq_init(const string & seq, const string & should);
	virtual const char* what() const throw () {
		return stack.c_str();
	}
};

class bad_seq_stop : public exception {
private:
	string stack;
public :
	bad_seq_stop();
	bad_seq_stop(const string & seq, const string & should);
	virtual const char* what() const throw () {
		return stack.c_str();
	}
};

class bad_seq_null : public exception {
private:
	string stack;
public:
	bad_seq_null();
	bad_seq_null(const string & seq);
	virtual const char* what() const throw () {
		return stack.c_str();
	}
};

class bad_seq_len : public exception {
private:
	string stack;
public:
	bad_seq_len();
	bad_seq_len(const string & seq, int len);
	virtual const char* what() const throw () {
		return stack.c_str();
	}
};