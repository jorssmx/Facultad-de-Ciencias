import random

# Función para construir un arreglo respetando la condición |A[i] − A[i+1]| ≤ 1
def construir_arreglo(tamano):
    arreglo = [random.randint(0, 10)]  # Generamos el primer valor aleatorio
    for indice in range(1, tamano):
        # Añadimos al arreglo un valor que cumpla la condición |A[i] − A[i-1]| ≤ 1
        arreglo.append(arreglo[-1] + random.choice([-1, 0, 1]))
    
    # Asegurarnos de que el último valor sea mayor que el primero
    arreglo[-1] = arreglo[0] + random.randint(1, 5)
    
    return arreglo

# Función de búsqueda logarítmica
def busqueda_logaritmica(arreglo, objetivo):
    inicio = 0
    fin = len(arreglo) - 1
    while inicio <= fin:
        mitad = (inicio + fin) // 2
        if arreglo[mitad] == objetivo:
            return mitad
        elif arreglo[mitad] < objetivo:
            inicio = mitad + 1
        else:
            fin = mitad - 1
    return -1  # Si el objetivo no se encuentra en el arreglo

# Programa principal
def ejecutar_busqueda(tamano):
    # Generamos el arreglo aleatorio
    arreglo_generado = construir_arreglo(tamano)
    print("Arreglo generado:", arreglo_generado)
    
    # Seleccionamos un valor aleatorio dentro del rango del arreglo
    objetivo = random.randint(arreglo_generado[0], arreglo_generado[-1])
    print(f"Buscando el valor {objetivo} en el arreglo...")

    # Realizamos la búsqueda logarítmica
    indice = busqueda_logaritmica(arreglo_generado, objetivo)
    
    if indice != -1:
        print(f"El valor {objetivo} se encuentra en el índice {indice}.")
    else:
        print(f"-1: El valor {objetivo} no se encuentra en el arreglo.")

# Ejecución
tamano = int(input("Dame un número entero que será el tamaño del arreglo: "))
ejecutar_busqueda(tamano)
