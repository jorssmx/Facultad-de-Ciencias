#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "parser.tab.h"

#define MAX_SYMBOLS 100

typedef struct {
    char *name;
    float value;
} Symbol;

// Definición de la tabla de símbolos
Symbol symbol_table[MAX_SYMBOLS];
int num_symbols = 0; // Inicializa el contador de símbolos

extern FILE *yyin;  // Variable especial para Flex que almacena el archivo de entrada

void print_result(float val);
void exit_program();
float lookup_variable(char *s);
void assign_variable(char *s, float val);

int yylex(void); // Declaración de yylex

void yyerror(const char *s); // Declaración de yyerror

int main(int argc, char *argv[]) {
    if (argc != 2) {
        fprintf(stderr, "Uso: %s <archivo_de_entrada>\n", argv[0]);
        return 1;
    }

    // Abre el archivo de entrada
    FILE *file = fopen(argv[1], "r");
    if (file == NULL) {
        perror("Error al abrir el archivo");
        return 1;
    }

    yyin = file; // Asigna el archivo de entrada a yyin

    // Llama a la función de análisis sintáctico generada por Bison
    yyparse();

    // Cierra el archivo de entrada
    fclose(file);

    return 0;
}

void yyerror(const char *s) {
    fprintf(stderr, "Error: %s\n", s);
}

void print_result(float val) {
    printf("Resultado: %g\n", val);
}

void exit_program() {
    printf("Saliendo del programa...\n");
    exit(0);
}

// Implementación de lookup_variable
float lookup_variable(char *s) {
    for (int i = 0; i < num_symbols; i++) {
        if (strcmp(symbol_table[i].name, s) == 0) {
            return symbol_table[i].value;
        }
    }
    yyerror("Variable no encontrada");
    return 0;
}

// Implementación de assign_variable
void assign_variable(char *s, float val) {
    for (int i = 0; i < num_symbols; i++) {
        if (strcmp(symbol_table[i].name, s) == 0) {
            symbol_table[i].value = val;
            return;
        }
    }
    if (num_symbols < MAX_SYMBOLS) {
        symbol_table[num_symbols].name = strdup(s);
        symbol_table[num_symbols].value = val;
        num_symbols++;
    } else {
        yyerror("Tabla de símbolos llena");
    }
}
