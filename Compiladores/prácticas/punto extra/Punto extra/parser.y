%{
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

void yyerror(const char *s);
void print_result(float val);
void exit_program();
float lookup_variable(char *s);
void assign_variable(char *s, float val);

int yylex(void);  // Declaración de yylex
int num_symbols;
%}

%union {
    float val;
    char *id;
}

%token <val> NUM
%token <id> ID
%token EQ LT GT IF THEN ELSE PRINT EXIT AND OR NOT ADD SUB MUL DIV POW LPAREN RPAREN SEMICOLON ASSIGN

%type <val> program statement expr term factor primary

%%

program:
    program statement
    | statement
    {
        $$ = $1;
        print_result($$);
    }
    ;

statement:
    ID ASSIGN expr SEMICOLON
    {
        assign_variable($1, $3);
    }
    | PRINT expr SEMICOLON
    {
        printf("Imprimiendo valor: %g\n", $2);
        print_result($2);
    }
    | EXIT SEMICOLON
    {
        exit_program();
    }
    ;

expr:
    expr ADD term
    {
        $$ = $1 + $3;
        printf("Suma: %g\n", $$);
    }
    | expr SUB term
    {
        $$ = $1 - $3;
        printf("Resta: %g\n", $$);
    }
    | term
    ;

term:
    term MUL factor
    {
        $$ = $1 * $3;
        printf("Multiplicación: %g\n", $$);
    }
    | term DIV factor
    {
        $$ = $1 / $3;
        printf("División: %g\n", $$);
    }
    | factor
    ;

factor:
    factor POW primary
    {
        $$ = pow($1, $3);
        printf("Potencia: %g\n", $$);
    }
    | primary
    ;

primary:
    ID
    {
        $$ = lookup_variable($1);
        printf("Variable: %s, Valor: %g\n", $1, $$);
    }
    | NUM
    {
        $$ = $1;
        printf("Número: %g\n", $$);
    }
    | LPAREN expr RPAREN
    {
        $$ = $2;
        printf("Paréntesis: %g\n", $$);
    }
    ;

%%

void yyerror(const char *s); // Solo declaración
