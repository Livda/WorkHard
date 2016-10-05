//=======================================================================
// Nom      : 	list.h
//
//=======================================================================

#ifndef LIST_H_
#define LIST_H_

// Pr�-d�clarations n�cessaires pour l'utilisation des classes amies
template <class T> class List;
template <class T> class ListIterator;

// =====================================================================
// Classe: d�finition de la classe ListElement<T> d�crivant un �l�ment de
//         liste.
//         Tous les membres de la classe ListElement sont d�finis priv�s,
//		   ainsi seules les classes amies List et ListIterator y ont acc�s.
// =====================================================================
template <class T> class ListElement {
 private:
  // Valeur de l'�l�ment
  T _value;

  // Membres pour le chainage
  ListElement<T>* _next;
  ListElement<T>* _prev;

  // Constructeurs et destructeur : ils mettent � jour le chainage au sein de la liste
  ListElement(const T& v) :  _prev(NULL), _next(NULL), _value(v) {}
  ListElement(ListElement<T>* p,ListElement<T>* n) :  _prev(p), _next(n){
    if (p != NULL) p->_next = this;
    if (n != NULL) n->_prev = this;
  }
  ListElement(const T& v,ListElement<T>* p,ListElement<T>* n) : _prev(p), _next(n), _value(v) {
    if (p != NULL) p->_next = this;
    if (n != NULL) n->_prev = this;
  }
  ~ListElement() {
    if (_next != NULL) _next->_prev = _prev;
    if (_prev != NULL) _prev->_next = _next;
  }

  // Classes amies
  friend class List<T>;
  friend class ListIterator<T>;
};

// =====================================================================
// Classe: d�finition de la classe List<T> d�crivant des listes g�n�riques
//         La gestion de la liste est en double chainage avec deux �l�ments 
//         fictifs _head et _tail pour g�rer le chainage.
// =====================================================================
template <class T> class List {
 private:
  // Les deux �l�ments fictifs
  ListElement<T>* _head;
  ListElement<T>* _tail;

  // Nombre d'�l�ments contenus
  int _card;

  // =====================================================================
  // But: suppression des �l�ments contenus (mais pas les �l�ments fictifs)
  // =====================================================================
  void _freelist() {
    ListElement<T>* tmp = _head->_next;
    while (tmp != _tail) {
      ListElement<T>* n = tmp->_next;
      _card--;
      delete tmp;
      tmp = n;
    }
  }

 protected:
  // D�finition du type �num�r� eListPosition
  // D�finition des diff�rents emplacements d'insertion d'un nouvel �l�ment 
  // dans une liste.
  typedef enum eListPosition {
	LP_first=-2, // en d�but de liste
	LP_last=-1,  // en fin de liste
	LP_pos=0	 // � une position donn�e (entre 1 et le cardinal de la liste)
  };

 public:
  // =====================================================================
  // But: constructeur par d�faut : cr�ation d'une liste vide
  // =====================================================================
  List() : _card(0) {
    _head = new ListElement<T>(NULL,NULL);
    _tail = new ListElement<T>(_head,NULL);
	_head->_next=_tail;
  }

  // =====================================================================
  // But: constructeur par recopie
  // =====================================================================
  List(const List<T>& lref) : _card(0) {
    _head = new ListElement<T>(NULL,NULL);
    _tail = new ListElement<T>(_head,NULL);
	_head->_next=_tail;

    ListElement<T>* tmp = lref._head->_next;
    while (tmp != lref._tail) {
      ListElement<T>* toadd;
	  // Ajout � la fin de la liste
      toadd = new ListElement<T>(tmp->_value,_tail->_prev,_tail);
      _card++;
      tmp = tmp->_next;
    }
  }

  // =====================================================================
  // But: destructeur
  // =====================================================================
  ~List() {
    _freelist();
    delete _head;
    delete _tail;
  }

  // =====================================================================
  // But: op�rateur d'affectation
  // =====================================================================
  List<T>& operator=(const List<T>& lref) {
    if (this != &lref) {
      _freelist();
      ListElement<T>* tmp = lref._head->_next;
      while (tmp != lref._tail) {
		ListElement<T>* toadd;
		// Ajout � la fin de la liste
		toadd = new ListElement<T>(tmp->_value,_tail->_prev,_tail);
		_card++;
		tmp = tmp->_next;
      }
    }
    return (*this);
  }

  // =====================================================================
  // But: test d'appartenance d'un �l�ment � une liste
  // =====================================================================
  int operator==(const T& v) const {
    int res = 0;
    int idx = 0;
    ListElement<T>* tmp = _head->_next;
    while ((tmp != _tail) && (!res)) {
      res = (tmp->_value == v);
      tmp = tmp->_next;
      idx++;
    }
    return (res ? idx : 0);
  }

  // =====================================================================
  // But: cardinal de la liste
  // =====================================================================
  int card() const { return _card; }

  // =====================================================================
  // But: ajout d'un �l�ment � une liste (par d�faut en t�te de liste)
  // =====================================================================
  void addElement(const T& v,eListPosition pos = LP_first) {
    ListElement<T>* toadd;
    switch (pos) {
    case LP_first:	// Ajout en d�but
      toadd = new ListElement<T>(v,_head,_head->_next);
      _card++;
      break;
    case LP_last:	// Ajout en fin
      toadd = new ListElement<T>(v,_tail->_prev,_tail);
      _card++;
      break;
    case LP_pos: // Pas d'ajout en position 0 de la liste
    default:
      int realpos = pos; 
      ListElement<T>* tmp = _head->_next;
      while ((tmp != _tail) && (realpos > 1)) { 
		  tmp = tmp->_next; 
		  realpos--; 
	  }
	  // Ajout effectif de l'�l�ment
      if (realpos == 0) 
		  toadd = new ListElement<T>(v,tmp->_prev,tmp);
      _card++;
      break;
    }
  }

  // =====================================================================
  // But: op�rateur d'ajout d'un �l�ment � une liste (l'ajout se fait en 
  //	  d�but de liste)
  // =====================================================================
  List<T> operator+(const T& v) const{
	List<T> lres(*this);
	lres.addElement(v,LP_first);
	return lres;
  }

  // =====================================================================
  // But: op�rateur de suppression d'un �l�ment d'une liste
  // =====================================================================
  List<T> operator-(const T& v) const{
	List<T> lres(*this);
	lres.delElement(v);
	return lres;
  }

  // =====================================================================
  // But: suppression d'un �l�ment
  // =====================================================================
  void delElement(const T& v) {
	  // Recherche de l'�l�ment
    ListElement<T>* tmp = _head->_next;
    while ((tmp != _tail) && (tmp->_value != v)) { 
		tmp = tmp->_next; 
	}
	// Si l'�l�ment a �t� trouv�, le d�truire
    if (tmp != _tail) { 
		delete tmp; 
		_card--; 
	}
  }

  // =====================================================================
  // But: acc�s � un �l�ment donn� de la liste en donnant un indice
  // =====================================================================
  T& operator[](const int& idx) const {
    int id = 1;
    ListElement<T>* tmp = _head->_next;
    while ((tmp != _tail) && (id != idx)) { 
		tmp = tmp->_next; 
		id++; 
	}
    return tmp->_value;
  }

  // =====================================================================
  // But: it�rateur de liste � partir du d�but
  // =====================================================================
  ListIterator< T > beg() const {
    return ListIterator< T >(*this);
  }

  // =====================================================================
  // But: it�rateur de liste � partir de la fin
  // =====================================================================
  ListIterator< T > end() {
    ListIterator< T > res(*this);
    res._crtelt = _tail->_prev;
    return res;
  }

  // =====================================================================
  // But: it�rateur de liste � partir d'une position donn�e
  // =====================================================================
  ListIterator< T > pos(const int& idx) {
    int realpos(idx);
    ListIterator< T > res(*this);
    ListElement<T>* tmp = _head->_next;
    while ((tmp != _tail) && (realpos > 1)) { 
		tmp = tmp->_next; 
		realpos--; 
	}
    res._crtelt = tmp;
    return res;
  }

  // =====================================================================
  // But: op�rateur d'affichage d'une liste dans un flux
  // =====================================================================
  template <class T> friend std::ostream& operator<<(std::ostream& out,const List<T>& lref);

  // =====================================================================
  // But: op�rateur de lecture d'une liste dans un flux
  // =====================================================================
  friend std::istream& operator>>(std::istream& in,List<T>& lref) {
	  int nb; in >> nb;
	  for (int i=0;i<nb;i++) {
		T tmp;
		in >> tmp; 
		lref.addElement(tmp,LP_last);
	  }
	  return in;
  }

  // Classe amie
  friend class ListIterator<T>;
};

// =====================================================================
// Classe: d�finition de la classe ListIterator<T> de parcours des listes
// =====================================================================
template <class T> class ListIterator {
  // La liste de r�f�rence
  const List<T>& _listref;
  // La position courante
  ListElement<T>* _crtelt;

  // Constructeur : on ne cr�e un it�rateur que gr�ce � la classe List
  ListIterator(const List<T>& lref) : _listref(lref) { _crtelt = lref._head->_next; }

 public:
  // Destructeur */
  ~ListIterator() {}
  // Fin du parcours (on est sur l'un des �l�ments fictifs
  int finished() const { return ((_crtelt == _listref._tail) || (_crtelt == _listref._head)); }
  // Parcours en marche avant
  ListIterator<T>& operator++() { _crtelt = _crtelt->_next; return *this; }
  // Parcours en marche arri�re
  ListIterator<T>& operator--() { _crtelt = _crtelt->_prev; return *this; }
  // R�cup�ration de l'�l�ment courant
  T& get() { return _crtelt->_value; }

  // Classe amie
  friend class List<T>;
};

template <class T> std::ostream& operator<<(std::ostream& out,const List<T>& lref) {
  out << lref.card() << "    ";
  for (ListIterator<T> iterlst = lref.beg();!(iterlst.finished());++iterlst) {
    out << iterlst.get() << "  ";
  }
  return out;
}


#endif
