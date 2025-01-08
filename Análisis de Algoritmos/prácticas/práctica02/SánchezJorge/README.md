#Practica 02: Búsquedas.
Autor: Jorge Angel Sánchez Sánchez
Fecha de entrega: 09/09/2024
-------------------------------------------Instrucciones----------------------------------------------------------

1. pre-Condición: Tener instalado python3 ya que use python para hacer el programa.

2. Abre el terminal, ubícate en la carpeta donde se encuentra el archivo practica02.py, en este caso es la carpeta "src"

3. Ejecuta el programa usando: python practica02.py o como en mi caso python3 practica02.py

el cuál te pedira un número entero es decir el n de nuestro arreglo

Esto generará un arreglo X del tamaño n que es el que le pasamos que cumpla las condiciones dadas y nos va a dar un valor objetivo z que se encuentre en el rango [a, b]. 

Daré dos ejemplos de dos ejecuciones uno que no encuentre el número y otro dónde sí lo encuentra:

Ejemplo 1: python3 practica02.py

Dame un número entero que será el tamaño del arreglo: 5
Arreglo generado: [5, 5, 5, 4, 8]
Buscando el valor 7 en el arreglo...
-1: El valor 7 no se encuentra en el arreglo.

Ejemplo 2: python3 practica02.py

Dame un número entero que será el tamaño del arreglo: 5
Arreglo generado: [4, 3, 3, 2, 5]
Buscando el valor 5 en el arreglo...
El valor 5 se encuentra en el índice 4.


-------------------------------------------Explicación------------------------------------------------------------

*Función construir_arreglo(tamano)
En esta función se encarga de construir un arreglo de tamaño tamano cumpliendo con la condición 
∣𝑋[𝑖]−𝑋[𝑖+1]∣≤1∣X[i]−X[i+1]∣≤1 y que el último valor sea mayor que el primero.

 Creamos un arreglo y le asignamos el primer número de forma aleatoria entre 0 y 10.

 Bucle for: Para cada índice a partir de 1 hasta tamano-1, se agrega un número nuevo al arreglo.
La diferencia entre cada número y el anterior cumple con la condición 
∣𝑋[𝑖]−𝑋[𝑖−1]∣≤1∣X[i]−X[i−1]∣≤1 gracias a la selección aleatoria entre [-1, 0, 1]. Esto asegura que el número nuevo esté a una distancia máxima de 1 del anterior.

Aseguramos que el último elemento del arreglo sea mayor que el primero, incrementando el primer valor por un número aleatorio entre 1 y 5. Esto satisface la condición de que el primer valor debe ser menor que el último.

*Función busqueda_logaritmica(arreglo, objetivo)
Esta función implementa una búsqueda binaria para localizar el índice j tal que 
𝑋[𝑗]=𝑧X[j]=z. La búsqueda binaria tiene una complejidad de tiempo 𝑂(log 𝑛).

Se definen dos punteros, inicio y fin, que representan los límites de la búsqueda (al principio, todo el arreglo).

Bucle while: La búsqueda continúa mientras el puntero inicio sea menor o igual que el puntero fin.
La variable mitad se calcula como el punto medio entre inicio y fin.

Si el valor en la posición mitad es igual al valor que estamos buscando (objetivo), se devuelve el índice mitad.

Si el valor en mitad es menor que el objetivo, significa que el objetivo debe estar en la parte derecha, por lo que movemos el puntero inicio hacia la derecha (a mitad + 1). Si el valor es mayor, ajustamos el puntero fin hacia la izquierda (a mitad - 1).

Si el bucle termina sin encontrar el objetivo, se devuelve -1.

*Función ejecutar_busqueda(tamano)

Llama a la función construir_arreglo(tamano) para generar un arreglo aleatorio respetando las condiciones, y luego imprime el arreglo resultante.

Elige un valor aleatorio dentro del rango del arreglo, desde el primer valor (arreglo_generado[0]) hasta el último (arreglo_generado[-1]). Este será el valor que intentará buscar en el arreglo.

Llama a la función busqueda_logaritmica(arreglo_generado, objetivo) para buscar el valor y almacena el índice resultante en la variable indice.

Resultado: Si indice es diferente de -1, significa que el valor fue encontrado, y se imprime su posición. Si no fue encontrado, se imprime -1.