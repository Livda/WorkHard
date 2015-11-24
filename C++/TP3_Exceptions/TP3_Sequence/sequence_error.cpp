#include "sequence_error.h"

#include <iostream>
#include <sstream>

using namespace std;

bad_seq_char::bad_seq_char(): exception() {}

bad_seq_char::bad_seq_char(const char c, const string & seq, const string & alpha) {
	std::ostringstream res;
	res << "Bad char (" << c << ") in sequence : " << seq << 
		", should have been one of the aphabet : " << alpha << endl;
	stack = res.str();
}

bad_seq_init::bad_seq_init() : exception() {}

bad_seq_init::bad_seq_init(const string & seq, const string & should){
	std::ostringstream res;
	res << "Bad init seq : " << seq << ", sould've been : " << should << endl;
	string msg = res.str();
	stack = msg;
}

bad_seq_stop::bad_seq_stop() : exception() {}

bad_seq_stop::bad_seq_stop(const string & seq, const string & should) {
	std::ostringstream res;
	res << "Bad init seq : " << seq << ", sould've been : " << should << endl;
	string msg = res.str();
	stack = msg;
}

bad_seq_null::bad_seq_null() : exception() {}

bad_seq_null::bad_seq_null(const string & seq) {
	std::ostringstream res;
	res << "Bad sequence lenght : " <<  seq << ", the sequence should contains more nucleotides" << endl;
	string msg = res.str();
	stack = msg;
}

bad_seq_len::bad_seq_len() : exception() {}

bad_seq_len::bad_seq_len(const string & seq, int len) {
	std::ostringstream res;
	res << "Bad sequence (" << seq << ") lenght : " << len << ", the sequence should be a multiple of 3" << endl;
	string msg = res.str();
	stack = msg;
}