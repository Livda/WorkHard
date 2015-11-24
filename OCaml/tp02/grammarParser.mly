%{
 type simple_grammar = string list *  string list * string * (string * string list) list
%}

%token <string> IDENT
%token LPAR RPAR LBR RBR COMMA ARROW
%start main
%type <string list *  string list *  string * (string * string list) list> main
%%

main: LPAR set_br COMMA set_br COMMA axiom COMMA prod_br RPAR { $2,$4,$6,$8 };

set_br : LBR set RBR { $2 }
;

set : 
 | IDENT COMMA set { $1 :: $3 }
 | IDENT  { [$1] }
;

axiom : IDENT { $1 }
;

prod_br : LBR prods RBR  {$2}
;


prods : production COMMA prods { $1 :: $3 }
 |      production  { [$1] }
;


production : IDENT ARROW id_list { ($1,$3) }

id_list : IDENT id_list { $1 ::  $2 }
 |            { [] }
 ;
