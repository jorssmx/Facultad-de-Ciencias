import random

# Función para generar un arreglo en patrón zigzag controlado por un factor k
def generar_arreglo_zigzag(tamano, k):
    """
    Genera un arreglo de tamaño 'tamano' con un patrón zigzag basado en el factor 'k'.
    Un factor 'k' mayor crea un zigzag más pronunciado.
    """
    arreglo = []
    for i in range(tamano):
        if i % 2 == 0:  # Elementos en posiciones pares
            arreglo.append(i)
        else:  # Elementos en posiciones impares son alterados por 'k'
            arreglo.append(i + k)
    return arreglo


# Implementación del algoritmo Merge Sort
def merge_sort(arr, contador):
    """
    Ordena un arreglo usando el algoritmo Merge Sort.
    Lleva un contador para contar las operaciones realizadas.
    """
    if len(arr) > 1:
        # División del arreglo en dos mitades
        mid = len(arr) // 2
        L = arr[:mid]
        R = arr[mid:]

        # Llamadas recursivas para ordenar cada mitad
        merge_sort(L, contador)
        merge_sort(R, contador)

        # Mezclar (merge) las mitades ordenadas
        i = j = k = 0
        while i < len(L) and j < len(R):
            contador[0] += 1  # Contar la comparación
            if L[i] < R[j]:
                arr[k] = L[i]
                i += 1
            else:
                arr[k] = R[j]
                j += 1
            k += 1

        # Agregar los elementos restantes de L (si los hay)
        while i < len(L):
            contador[0] += 1
            arr[k] = L[i]
            i += 1
            k += 1

        # Agregar los elementos restantes de R (si los hay)
        while j < len(R):
            contador[0] += 1
            arr[k] = R[j]
            j += 1
            k += 1


# Implementación del algoritmo Insertion Sort
def insertion_sort(arr):
    """
    Ordena un arreglo usando el algoritmo Insertion Sort.
    Devuelve el número total de operaciones realizadas.
    """
    operaciones = 0
    for i in range(1, len(arr)):
        key = arr[i]
        j = i - 1
        # Mover elementos mayores a 'key' hacia la derecha
        while j >= 0 and key < arr[j]:
            operaciones += 1
            arr[j + 1] = arr[j]
            j -= 1
        operaciones += 1
        arr[j + 1] = key
    return operaciones


# Implementación del algoritmo Inserción Local
def insercion_local(arr):
    """
    Ordena un arreglo usando Inserción Local.
    (Simula un comportamiento similar a Insertion Sort)
    Devuelve el número total de operaciones realizadas.
    """
    return insertion_sort(arr)


# Función principal para comparar los algoritmos
def comparar_algoritmos(tamanos, factores_zigzag):
    """
    Compara los algoritmos Merge Sort, Insertion Sort e Inserción Local
    para diferentes tamaños de arreglo y factores zigzag.
    """
    for tamano in tamanos:
        for factor_zigzag in factores_zigzag:
            # Generar el arreglo zigzag
            arreglo = generar_arreglo_zigzag(tamano, factor_zigzag)

            # Merge Sort
            arreglo_merge = arreglo[:]
            contador_merge = [0]
            merge_sort(arreglo_merge, contador_merge)

            # Insertion Sort
            arreglo_insertion = arreglo[:]
            operaciones_insertion = insertion_sort(arreglo_insertion)

            # Inserción Local
            arreglo_local = arreglo[:]
            operaciones_local = insercion_local(arreglo_local)

            # Mostrar los resultados
            print(f"Resultados para tamaño = {tamano}, factor_zigzag = {factor_zigzag}:")
            print(f"Merge Sort operaciones: {contador_merge[0]}")
            print(f"Insertion Sort operaciones: {operaciones_insertion}")
            print(f"Inserción Local operaciones: {operaciones_local}")
            print("-" * 78)


# Parámetros de prueba
tamanos_prueba = [1000, 2500, 5000, 10000]  # Diferentes tamaños de arreglo
factores_zigzag_prueba = [1, 3]  # Factores zigzag a probar

# Llamar a la función principal
comparar_algoritmos(tamanos_prueba, factores_zigzag_prueba)
