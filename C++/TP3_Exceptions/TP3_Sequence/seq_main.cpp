/*!
 * \file  seq_main.cpp
 * \brief  Test for reading sequences  
 * \date to be updated
 * \author to be updated
*/

#include "sequences.h"
#include "sequence_error.h"

using namespace std;

template <class E> void test(string & seq, string & name) {
	try {
		E R(seq, name);
		cout << R << endl;
	}
	catch (bad_seq_char e) { cout << e.what() << endl; }
	catch (bad_seq_init e) { cout << e.what() << endl; }
	catch (bad_seq_stop e) { cout << e.what() << endl; }
	catch (bad_seq_null e)  { cout << e.what() << endl; }
	catch (bad_seq_len e) { cout << e.what() << endl; }
	catch (...) { cout << "unexpected error" << endl; }
}

int main()
{
    string s1,s2,s3;

    cout << "Entrez une sequence proteique: ";
    cin >> s1;
    cout << "Entrez son nom: ";
    cin >> s2;
	cout << endl;

    test<seqprot>(s1,s2);

    cout << "Entrez une sequence d'adn: ";
    cin >> s1;
    cout << "Entrez son nom: ";
    cin >> s2;
	cout << endl;

	test<seqadn>(s1, s2);

    cout << "Entrez une sequence d'arn: ";
    cin >> s1;
    cout << "Entrez son nom: ";
    cin >> s2;
	cout << endl;

	test<seqarn>(s1,s2);
	
    return 0;

}