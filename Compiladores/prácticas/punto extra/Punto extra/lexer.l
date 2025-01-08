%{
#include "parser.tab.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void yyerror(const char *s);
%}

%%

[0-9]+(\.[0-9]+)?   { yylval.val = atof(yytext); return NUM; }
[a-zA-Z_][a-zA-Z0-9_]* { yylval.id = strdup(yytext); return ID; }
"="                 { return ASSIGN; }
"=="                { return EQ; }
"<"                 { return LT; }
">"                 { return GT; }
"if"                { return IF; }
"then"              { return THEN; }
"else"              { return ELSE; }
"print"             { return PRINT; }
"exit"              { return EXIT; }
"and"               { return AND; }
"or"                { return OR; }
"not"               { return NOT; }
"+"                 { return ADD; }
"-"                 { return SUB; }
"*"                 { return MUL; }
"/"                 { return DIV; }
"^"                 { return POW; }
"("                 { return LPAREN; }
")"                 { return RPAREN; }
";"                 { return SEMICOLON; }
[ \t\n]             { /* Ignorar espacios, tabulaciones y nuevas líneas */ }
.                   { yyerror("Carácter no reconocido"); }

%%

int yywrap() {
    return 1;
}
