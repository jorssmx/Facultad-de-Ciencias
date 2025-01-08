%{
#include <stdio.h>
%}

/* Aqui definimos las 'reglas' o 'patrones'. */

BOOL            True|False
DELIM           [{};]
DIGIT           [0-9]
ID              [a-zA-Z]
INT             {DIGIT}+
EXPONENT        [eE][+-]?{DIGIT}+
FLOAT           ({DIGIT}+(\.{DIGIT}*)?{EXPONENT}?|\.{DIGIT}+{EXPONENT}?)
KEYWORD         if|then|else
RELOP           [\+\*<>=]|<=|>=|==|\*\*

/* Aqui definimos las convenciones a realizar asociados al token correspondiente. */

%%
{BOOL}          { printf("BOOL: %s\n", yytext); }
{DELIM}         { printf("DELIM: %s\n", yytext); }
{FLOAT}         { printf("FLOAT: %s\n", yytext); }
{ID}            { printf("ID: %s\n", yytext); }
{INT}           { printf("INT: %s\n", yytext); }
{KEYWORD}       { printf("KEYWORD: %s\n", yytext); }
{RELOP}         { printf("RELOP: %s\n", yytext); }
\n               { printf("\n"); }
%%


int main() {
    yylex();
    return 0;
}
