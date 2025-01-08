import random  
import numpy as np  

# Función para verificar si el Sudoku es válido
def verificar_sudoku(tablero):
    for i in range(9):
        # Verificamos filas y columnas
        if not es_unico([tablero[i][j] for j in range(9)]) or not es_unico([tablero[j][i] for j in range(9)]):
            return False
    # Verificamos bloques de 3x3
    for fila in range(0, 9, 3):
        for col in range(0, 9, 3):
            bloque = [tablero[fila + i][col + j] for i in range(3) for j in range(3)]
            if not es_unico(bloque):
                return False
    return True

# Función para verificar si los valores en una lista son únicos, ignorando los ceros
def es_unico(valores):
    valores_sin_ceros = [v for v in valores if v != 0]
    return len(valores_sin_ceros) == len(set(valores_sin_ceros))

# Función para rellenar el Sudoku con números válidos de manera aleatoria
def completar_sudoku(tablero):
    for i in range(9):
        for j in range(9):
            if tablero[i][j] == 0:
                opciones = list(range(1, 10))
                random.shuffle(opciones)
                for num in opciones:
                    if es_valido(tablero, i, j, num):
                        tablero[i][j] = num
                        if completar_sudoku(tablero):
                            return True
                        tablero[i][j] = 0
                return False
    return True

# Función para verificar si un número puede colocarse en una posición sin violar las reglas
def es_valido(tablero, fila, col, num):
    if all(tablero[fila][j] != num for j in range(9)) and \
       all(tablero[i][col] != num for i in range(9)) and \
       all(tablero[fila // 3 * 3 + i][col // 3 * 3 + j] != num for i in range(3) for j in range(3)):
        return True
    return False

# Función para hacer que el tablero no sea válido introduciendo número respetidos en alguna fila o columna
def es_no_valido(tablero, num_conflictos=3):
    for _ in range(num_conflictos):
        fila = random.randint(0, 8)
        col = random.randint(0, 8)
        tablero[fila][col] = random.randint(1, 9)  # Colocamos un número aleatorio que puede no cumplir las reglas

# Función para generar un certificado de validez e imprimirlo en la terminal
def generar_certificado(tablero):
    if verificar_sudoku(tablero):
        print("Este Sudoku es válido :D.")
    else:
        print("El Sudoku NO es válido :c.")

# Ejemplo de tablero de Sudoku incompleto
sudoku_incompleto = [
    [8, 0, 0, 0, 0, 0, 0, 0, 0],
    [0, 0, 3, 6, 0, 0, 0, 0, 0],
    [0, 7, 0, 0, 9, 0, 2, 0, 0],
    [0, 5, 0, 0, 0, 7, 0, 0, 0],
    [0, 0, 0, 0, 4, 5, 7, 0, 0],
    [0, 0, 0, 1, 0, 0, 0, 3, 0],
    [0, 0, 1, 0, 0, 0, 0, 6, 8],
    [0, 0, 8, 5, 0, 0, 0, 1, 0],
    [0, 9, 0, 0, 0, 0, 4, 0, 0]
]

# Ejemplo 1: Sudoku completado y válido
print("Tablero incompleto:")
print(np.array(sudoku_incompleto))

# Completar el Sudoku
completar_sudoku(sudoku_incompleto)
print("\nTablero completado (válido):")
print(np.array(sudoku_incompleto))

# Generamos el certificado con el ejemplo válido
generar_certificado(sudoku_incompleto)

# Ejemplo 2: Sudoku completado pero con conflictos
es_no_valido(sudoku_incompleto, num_conflictos=6)
print("\nTablero con conflictos:")
print(np.array(sudoku_incompleto))

# Generamos el certificado con el ejemplo pero no válido
generar_certificado(sudoku_incompleto)
