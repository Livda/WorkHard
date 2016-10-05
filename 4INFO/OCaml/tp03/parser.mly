%{
    open Type
%}
%token <string> IDENT NUMBER
%token LPAR RPAR LBR RBR COMMA PTVIRG EOF
%token BEGIN END
%token ENTIER
%token BOOLEEN
%token AFFECT AND INF PLUS
%nonassoc AFFECT
%left AND
%nonassoc INF
%left PLUS
%start main
%type<Type.ast> main
%%

main: bloc EOF { $1 } | error { Error }
;
bloc : BEGIN sdecl PTVIRG sinst END { Bloc ($2, $4) }
;
sdecl :
    | decl { $1 :: [] }
    | decl COMMA sdecl { $1 :: $3 }
;
decl : vartype IDENT { Vartype ($1, $2) }
;
vartype :
    | ENTIER { "int" }
    | BOOLEEN { "bool" }
;
sinst :
    | inst { $1 :: [] }
    | inst PTVIRG sinst { $1 :: $3 }
;
inst :
    | bloc { NewBloc $1 }
    | IDENT AFFECT expr { Inst ($1, $3) }
;
expr :
    | expr PLUS expr { Add ($1, $3) }
    | expr INF expr { Inf ($1, $3) }
    | expr AND expr { And ($1, $3) }
    | LPAR expr RPAR { Par $2 }
    | NUMBER { Number $1 }
    | IDENT { Ident $1 }
;
