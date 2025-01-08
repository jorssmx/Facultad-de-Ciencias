Materia: Compiladores
Práctica 02

Equipo:

Jorge Angel Sánchez Sánchez - 315155534

Axel Ducloux Hurtado - 316309132

Alejandro Axel Rodríguez Sánchez - 315247697

Alan Bellon García - 319159565

Compilación y Ejecución:

flex lexer.l

bison -d parser.y

Antes de seguir abrir el archivo que se genera parse.tab.h y agrega la siguiente linea hasta al final del código:

int yyparse(void);

es decir debe de quedar así:

extern YYSTYPE yylval;
int yyparse(void);

lo guardas y sigues con la compilación:

gcc -o interpreter main.c parser.tab.c lex.yy.c -lm

ya por último lo ejecutas:

./interpreter entrada.txt

