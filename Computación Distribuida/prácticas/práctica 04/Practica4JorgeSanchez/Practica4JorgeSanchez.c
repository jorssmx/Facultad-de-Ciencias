#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/ipc.h>
#include <sys/msg.h>

#define MSG_SIZE sizeof(int)

// Estructura para los mensajes
struct msg_buffer {
    long msg_type;
    int hora;
};

// Método para elegir un número entero aleatorio entre min y max
int obtener_hora_aleatoria(int min, int max) {
    return rand() % (max - min + 1) + min;
}

// Método para que cada nodo ajuste su hora
void nodo(int msg_queue_id, int min_hora, int max_hora) {
    srand(time(NULL) ^ (getpid() << 16)); // Inicializa la semilla con una combinación del tiempo y el PID
    int hora_local = obtener_hora_aleatoria(min_hora, max_hora);
    
    // Envía la hora local al líder
    struct msg_buffer message;
    message.msg_type = 1; // Tipo de mensaje arbitrario
    message.hora = hora_local;
    if (msgsnd(msg_queue_id, &message, MSG_SIZE, 0) == -1) {
        perror("msgsnd");
        exit(1);
    }

    // Recibe la hora promedio del líder
    if (msgrcv(msg_queue_id, &message, MSG_SIZE, 2, 0) == -1) {
        perror("msgrcv");
        exit(1);
    }
    int hora_promedio = message.hora;
    
    // Ajusta la hora local
    int ajuste = hora_promedio - hora_local;
    hora_local += ajuste;

    printf("Nodo PID %d - Hora original: %d, Hora ajustada: %d\n", getpid(), hora_local - ajuste, hora_local);
    exit(0);
}

// Método para realizar una ronda de sincronización
void sincronizar(int num_nodos, int min_hora, int max_hora) {
    int msg_queue_id = msgget(IPC_PRIVATE, IPC_CREAT | 0666); // Crear cola de mensajes
    if (msg_queue_id == -1) {
        perror("msgget");
        exit(1);
    }

    // para crear forks para los nodos
    for (int i = 0; i < num_nodos; i++) {
        if (fork() == 0) {
            nodo(msg_queue_id, min_hora, max_hora);
        }
    }

    // para recopilar las horas locales y calcular la hora promedio
    int suma_horas = 0;
    struct msg_buffer message;
    for (int i = 0; i < num_nodos; i++) {
        if (msgrcv(msg_queue_id, &message, MSG_SIZE, 1, 0) == -1) {
            perror("msgrcv");
            exit(1);
        }
        suma_horas += message.hora;
        printf("Líder - Hora recibida de nodo PID %d: %d\n", i, message.hora);
    }
    int hora_promedio = suma_horas / num_nodos;

    // para enviar la hora promedio a cada nodo
    message.msg_type = 2;
    message.hora = hora_promedio;
    for (int i = 0; i < num_nodos; i++) {
        if (msgsnd(msg_queue_id, &message, MSG_SIZE, 0) == -1) {
            perror("msgsnd");
            exit(1);
        }
    }

    // para que todos los procesos hijos terminen
    for (int i = 0; i < num_nodos; i++) {
        wait(NULL);
    }

    // para eliminar la cola de mensajes
    if (msgctl(msg_queue_id, IPC_RMID, NULL) == -1) {
        perror("msgctl");
        exit(1);
    }
}

int main() {
    srand(time(NULL)); // Inicializar la semilla para números aleatorios

    int num_nodos;
    do {
        printf("Ingrese el número de nodos (mayor que 0): ");
        scanf("%d", &num_nodos);
    } while (num_nodos <= 0);

    int min_hora, max_hora;
    do {
        printf("Ingrese el rango de horas (min y max): ");
        scanf("%d %d", &min_hora, &max_hora);
    } while (min_hora > max_hora);

    int iteraciones;
    do {
        printf("Ingrese el número de iteraciones (mayor que 0): ");
        scanf("%d", &iteraciones);
    } while (iteraciones <= 0);

    for (int i = 0; i < iteraciones; i++) {
        printf("\nIniciando una nueva ronda de sincronización (Iteración %d)...\n", i + 1);
        sincronizar(num_nodos, min_hora, max_hora);
        sleep(10); 
    }

    printf("\nTodas las iteraciones completadas.\n");

    return 0;
}
