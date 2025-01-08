#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>
#include <unistd.h>

#define NUM_NODOS 7 // Número de nodos
#define RECUPERACION_TIEMPO 5  // Tiempo para simular recuperación de nodos caídos

// Estructura Nodo
typedef struct Nodo {
    int identificador;
    bool activo;
} Nodo;

// Arreglo de nodos
Nodo nodos[NUM_NODOS];

// Método para imprimir la hora actual en formato HH:MM:SS
void imprimirHora() {
    time_t ahora;
    time(&ahora);
    struct tm *local = localtime(&ahora);
    printf("[%02d:%02d:%02d] ", local->tm_hour, local->tm_min, local->tm_sec);
}

// Método para inicializar los nodos, asignando identificadores y marcándolos como activos
void inicializarNodos() {
    for (int i = 0; i < NUM_NODOS; i++) {
        nodos[i].identificador = i + 1;
        nodos[i].activo = true;
    }
}

// Método para simular los fallos de algunos nodos al azar
void simularFallos() {
    srand(time(NULL));
    int numFallos = rand() % (NUM_NODOS - 1) + 1;  // Aseguramos que haya al menos 1 nodo caído
    printf("El Nodo o Nodos caídos son: ");
    for (int i = 0; i < numFallos; i++) {
        int nodoCaido = rand() % NUM_NODOS;
        if (nodos[nodoCaido].activo) {
            nodos[nodoCaido].activo = false;
            printf("%d ", nodos[nodoCaido].identificador);
        } else {
            i--; // Aseguramos que hayan caído los nodos necesarios
        }
    }
    printf("\n");
}

// Método para iniciar las elecciones a partir de un nodo determinado
bool iniciarElecciones(int inicio) {
    imprimirHora();
    printf("Nodo %d inicia elecciones.\n", nodos[inicio].identificador);

    //agregue esto para que se muestre la respuesta de los nodos.
    bool respuestaRecibida = false;
    for (int i = inicio + 1; i < NUM_NODOS; i++) {
        if (nodos[i].activo) {
            imprimirHora();
            printf("Nodo %d le envía mensaje de elección a nodo %d.\n", nodos[inicio].identificador, nodos[i].identificador);
            sleep(1);  // aquí se simula el tiempo de respuesta

            imprimirHora();
            printf("Nodo %d recibe respuesta de nodo %d.\n", nodos[inicio].identificador, nodos[i].identificador);
            respuestaRecibida = true;

            if (iniciarElecciones(i)) {
                return true;
            }
        }
    }

    if (!respuestaRecibida) {
        imprimirHora();
        printf("Nodo %d es el nuevo líder.\n", nodos[inicio].identificador);
        for (int i = 0; i < NUM_NODOS; i++) {
            if (nodos[i].activo && i != inicio) {
                imprimirHora();
                printf("Nodo %d le dice al nodo %d que es el nuevo líder.\n", nodos[inicio].identificador, nodos[i].identificador);
                sleep(1);
            }
        }
        return true;
    }

    return false;
}

// Método para simular la recuperación de los nodos caídos después de un tiempo determinado
void recuperarNodos() {
    sleep(RECUPERACION_TIEMPO);  // aquí se simula el tiempo de espera para la recuperación
    printf("Recuperación de nodos...\n");
    for (int i = 0; i < NUM_NODOS; i++) {
        if (!nodos[i].activo) {
            nodos[i].activo = true;
            imprimirHora();
            printf("Nodo %d se ha recuperado.\n", nodos[i].identificador);
        }
    }
}

// Método para detectar si el líder ha caído y reiniciar el proceso de elección si es necesario
void detectarFalloLider() {
    bool hayNodoActivo = false;
    for (int i = 0; i < NUM_NODOS; i++) {
        if (nodos[i].activo) {
            hayNodoActivo = true;
            break;
        }
    }

    if (!hayNodoActivo) {
        imprimirHora();
        printf("Error: Todos los nodos han caído y no se puede iniciar el proceso de elección.\n");
        return;
    }

    int inicio = 0;
    while (inicio < NUM_NODOS && !nodos[inicio].activo) {
        inicio++;
    }

    if (inicio < NUM_NODOS) {
        iniciarElecciones(inicio);
    } else {
        imprimirHora();
        printf("Todos los nodos han caído.\n");
    }
}

// Método main
int main() {
    inicializarNodos();
    simularFallos();

    detectarFalloLider();
    recuperarNodos();

    return 0;
}
