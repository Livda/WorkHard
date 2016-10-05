
#include "sequences.h"
#include "sequence_error.h"

using namespace std;

ostream & operator<< (ostream & os, const seqmac & s) 
{
    s.writeseq(os);
    return os;
}

seqmac::seqmac(const string & seq, const string & name, const string & alphabet) : 
  _alph(alphabet), _name(name) 
{
    string s="";
    for(string::const_iterator c = seq.begin();c != seq.end(); c++ ) 
    {
	if (_alph.is_in_alpha(*c)) { s += *c; }
	else { throw bad_seq_char(*c, seq, alphabet); }
    }
    _seq = s;
}

seqadn::seqadn(const string & seq, const string & name) : seqmac(seq,name,"CGAT")
{
	string s = seq.substr(0, 3);
	if (s != "ATG") throw bad_seq_init(s, "ATG");

	int len = seq.length();
	s = seq.substr(len-3, 3);
	if (s != "TAA" && s != "TAG" && s != "TGA") throw bad_seq_stop(s, "TAA");

	if (len == 6) throw bad_seq_null(seq);

	if (len % 3 != 0) throw bad_seq_len(seq, len);
}

seqarn::seqarn(const string & seq, const string & name) : seqmac(seq,name,"ACGU") 
{
	string s = seq.substr(0, 3);
	if (s != "AUG") throw bad_seq_init(s, "AUG");

	int len = seq.length();
	s = seq.substr(len - 3, 3);
	if (s != "UAA" && s != "UAG" && s != "UGA") throw bad_seq_stop(s, "UAA");

	if (len == 6) throw bad_seq_null(seq);

	if (len % 3 != 0) throw bad_seq_len(seq, len);
}
