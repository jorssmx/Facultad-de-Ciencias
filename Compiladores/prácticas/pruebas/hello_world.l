%{
// #undef yywrap
// #define yywrap() 1
%}

%%

[\n] { printf("Â¡Hola mundo!\n"); }

%%

int main()
{
	yylex(); // llamando a la secciÃ³n de reglas
}