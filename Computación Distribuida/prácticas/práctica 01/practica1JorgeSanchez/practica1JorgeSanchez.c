#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>

#define MAX_NODOS 100

// Representamos el Nodo
typedef struct Nodo {
    int id;
    bool visitado;
    bool encontrado;
} Nodo;

// Representamos la estrucura Cola
typedef struct Cola {
    int frente, trasero, tamaño;
    unsigned capacidad;
    int* array;
} Cola;

// Aquí creamos a la Cola
Cola* crearCola(unsigned capacidad) {
    Cola* cola = (Cola*)malloc(sizeof(Cola));
    cola->capacidad = capacidad;
    cola->frente = cola->tamaño = 0;
    cola->trasero = capacidad - 1;
    cola->array = (int*)malloc(cola->capacidad * sizeof(int));
    return cola;
}

// metodo si es vacia
int estaVacia(Cola* cola) {
    return (cola->tamaño == 0);
}

// metodo si esta llena la cola
int estaLlena(Cola* cola) {
    return (cola->tamaño == cola->capacidad);
}

// metodo para agregar un nodo a la cola
void encolar(Cola* cola, int elemento) {
    if (estaLlena(cola))
        return;
    cola->trasero = (cola->trasero + 1) % cola->capacidad;
    cola->array[cola->trasero] = elemento;
    cola->tamaño = cola->tamaño + 1;
}

// metodo para eliminar un nodo a la cola
int desencolar(Cola* cola) {
    if (estaVacia(cola))
        return -1;
    int elemento = cola->array[cola->frente];
    cola->frente = (cola->frente + 1) % cola->capacidad;
    cola->tamaño = cola->tamaño - 1;
    return elemento;
}

// Aquí inicializamos los nodos 
void inicializarNodos(Nodo nodos[], int numNodos) {
    for (int i = 0; i < numNodos; i++) {
        nodos[i].id = i;
        nodos[i].visitado = false;
        nodos[i].encontrado = false;
    }
}

// Aquí para hacer lo de aleatoriedad 50% de conexión
void simularTopologia(Nodo nodos[], int numNodos) {
    srand(time(NULL));
    for (int i = 0; i < numNodos; i++) {
        for (int j = i + 1; j < numNodos; j++) {
            if (rand() % 2 == 0) { 
                printf("Nodo %d está conectado con Nodo %d\n", nodos[i].id, nodos[j].id);
            }
        }
    }
}

// Implementación (BFS)
void BFS(Nodo nodos[], int numNodos, int nodoInicial) {
    Cola* cola = crearCola(numNodos);
    encolar(cola, nodoInicial);
    nodos[nodoInicial].visitado = true;

    printf("Comenzando BFS desde el nodo %d\n", nodoInicial);

    while (!estaVacia(cola)) {
        int nodoActual = desencolar(cola);
        printf("Explorando el nodo %d...\n", nodoActual);

        // Simular la comunicación y el envío de mensajes
        for (int i = 0; i < numNodos; i++) {
            if (!nodos[i].visitado) {
                printf("Nodo %d envía mensaje de búsqueda a Nodo %d\n", nodoActual, i);
                encolar(cola, i);
                nodos[i].visitado = true;
            }
            if (nodos[i].encontrado) {
                printf("Nodo %d recibe mensaje de elemento encontrado de Nodo %d\n", nodoActual, i);
                printf("Deteniendo exploración...\n");
                return;
            }
        }
    }

    printf("BFS completado\n");

    free(cola->array);
    free(cola);
}

// Implementación (DFS)
void DFS(Nodo nodos[], int numNodos, int nodoInicial) {
    printf("Comenzando DFS desde el nodo %d\n", nodoInicial);
    printf("Explorando el nodo %d...\n", nodoInicial);
    nodos[nodoInicial].visitado = true;

    // Simular la comunicación y el envío de mensajes
    for (int i = 0; i < numNodos; i++) {
        if (!nodos[i].visitado) {
            printf("Nodo %d envía mensaje de búsqueda a Nodo %d\n", nodoInicial, i);
            DFS(nodos, numNodos, i);
        }
        if (nodos[i].encontrado) {
            printf("Nodo %d recibe mensaje de elemento encontrado de Nodo %d\n", nodoInicial, i);
            printf("Deteniendo exploración...\n");
            return;
        }
    }

    printf("DFS completado\n");
}

// Main 
int main() {
    Nodo nodos[MAX_NODOS];
    int numNodos = 7; // Aquí podemos cambiar el numero de nodos 

    inicializarNodos(nodos, numNodos);
    simularTopologia(nodos, numNodos);

    int nodoInicial = rand() % numNodos;

    printf("\n");
    printf("----- Búsqueda en Amplitud (BFS) -----\n");
    BFS(nodos, numNodos, nodoInicial);
    printf("\n");
    printf("----- Búsqueda en Profundidad (DFS) -----\n");
    inicializarNodos(nodos, numNodos); 
    DFS(nodos, numNodos, nodoInicial);

    return 0;
}
