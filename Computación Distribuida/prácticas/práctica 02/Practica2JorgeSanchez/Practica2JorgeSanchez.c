#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>

#define MAX_NODOS 10 // Aquí podemos cambiar el numero de nodos 
#define MAX_TRAIDORES 3 // Aquí podemos cambiar el numero de traidores
#define MAX_RONDAS 25 // Aquí podemos cambiar el numero de rondas

// Representamos el Nodo
typedef struct {
    bool noble; 
    bool traidor;
    bool plan; 
    bool res; 
} Nodo;

// método que simula la comunicación entre los nodos
void comunicacion(Nodo nodos[], int numNodos, int ronda) {
    // Implementación simplificada: cada nodo comparte su plan con algunos nodos vecinos
    printf("Comunicación en la ronda %d:\n", ronda);
    for (int i = 0; i < numNodos; i++) {
        printf("  Nodo %d comparte su plan (%s):\n", i, nodos[i].plan ? "Ataque" : "Retirada");
        for (int j = 0; j < numNodos; j++) {
            if (i != j && rand() % 2 == 0) { // Probabilidad del 50% de compartir el plan
                printf("    Nodo %d recibe el plan de Nodo %d\n", j, i);
                nodos[j].res = nodos[i].plan;
            }
        }
    }
}

// método que determina el consenso
bool dConsenso(Nodo nodos[], int numNodos) {
    // Se requiere que al menos la mayoría de los nodos coincida en el res
    int contadorAtaque = 0;
    int contadorRetirada = 0;
    for (int i = 0; i < numNodos; i++) {
        if (nodos[i].res) {
            contadorAtaque++;
        } else {
            contadorRetirada++;
        }
    }
    return (contadorAtaque > contadorRetirada) ? true : false;
}

// método que asigna al rey
int eRey(Nodo nodos[], int numNodos) {
    int maximoApoyo = 0;
    int indiceRey = 0;
    for (int i = 0; i < numNodos; i++) {
        int apoyo = 0;
        for (int j = 0; j < numNodos; j++) {
            if (nodos[i].res == nodos[j].res) {
                apoyo++;
            }
        }
        if (apoyo > maximoApoyo) {
            maximoApoyo = apoyo;
            indiceRey = i;
        }
    }
    return indiceRey;
}

// Main 
int main() {
    srand(time(NULL)); 

    // Aquí inicializamos a los nodos
    Nodo nodos[MAX_NODOS];
    for (int i = 0; i < MAX_NODOS; i++) {
        nodos[i].noble = true; 
        nodos[i].traidor = false;
        nodos[i].plan = rand() % 2; // Plan inicial aleatorio (ataque o retirada)
    }

    // Aquí simulamos a los nodos traidores
    for (int i = 0; i < MAX_TRAIDORES; i++) {
        int indice = rand() % MAX_NODOS;
        nodos[indice].noble = false; 
        nodos[indice].traidor = true;
    }

    // Aquí hacemos la comunicación y determinación del consenso
    int rondas = 0;
    while (!dConsenso(nodos, MAX_NODOS) && rondas < MAX_RONDAS) {
        comunicacion(nodos, MAX_NODOS, rondas + 1);
        
        printf("Resultados parciales de la ronda %d:\n", rondas + 1);
        for (int i = 0; i < MAX_NODOS; i++) {
            printf("  Nodo %d (%s): Plan: %s, Resultado: %s\n", i,
                   nodos[i].traidor ? "Traidor" : "Noble", 
                   nodos[i].plan ? "Ataque" : "Retirada",
                   nodos[i].res ? "Ataque" : "Retirada");
        }
        
        rondas++;
    }

    // Aquí mostramos los resultados de las votaciones
    printf("Resultados finales de la votación:\n");
    for (int i = 0; i < MAX_NODOS; i++) {
        printf("Nodo %d (%s): Plan: %s, Resultado: %s\n", i,
               nodos[i].traidor ? "Traidor" : "Noble", 
               nodos[i].plan ? "Ataque" : "Retirada",
               nodos[i].res ? "Ataque" : "Retirada");
    }

    if (dConsenso(nodos, MAX_NODOS)) {
        printf("Se alcanzó un consenso después de %d rondas.\n", rondas);
    } else {
        printf("No se alcanzó un consenso después de %d rondas.\n", MAX_RONDAS);
    }

    // Aquí mostramos al rey que fué elegido
    int indiceRey = eRey(nodos, MAX_NODOS);
    printf("El rey es el nodo %d\n", indiceRey);

    return 0;
}
