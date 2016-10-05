all: lexer.mll parser.mly ast.ml value.ml print_ast.ml print_val.ml eval.ml main.ml
	ocamlbuild -yaccflag -v main.native

clean:
	rm -rf _build main.native
	rm -f *~ *aux *gz *log
