"""
Práctica 01: Problema de adoquinamiento.
Autor: Jorge Sánchez
Fecha: [Fecha de entrega: 03/09/2024]
"""
import tkinter as tk # Biblioteca para crear interfaces gráficas
import sys
import random

# Paleta de colores para los bloques
PALETA_DE_COLORES = [
    'violet', 'lightcoral', 'forestgreen', 'gold', 'deepskyblue', 'crimson', 'peru', 'darkorange',
    'dodgerblue', 'slategray', 'mediumvioletred', 'salmon', 'thistle', 'lightseagreen', 'darkslateblue', 'seashell'
]

def seleccionar_color(excluidos):
    """Elige un color que no esté en la lista de colores excluidos."""
    colores_disponibles = [color for color in PALETA_DE_COLORES if color not in excluidos]
    return random.choice(colores_disponibles) if colores_disponibles else PALETA_DE_COLORES[0]

def dibujar_forma_L(canvas, coords, color):
    """Dibuja un bloque en forma de 'L' en el canvas con el color proporcionado."""
    for (fila, columna) in coords:
        canvas.create_rectangle(columna * 30, fila * 30, (columna + 1) * 30, (fila + 1) * 30, fill=color, outline='black')

def rellenar_area(canvas, dimension, inicio_x, inicio_y, x_especial, y_especial, colores_usados):
    """Divide el área y coloca los bloques en forma de 'L'."""
    if dimension == 1:
        return

    mitad = dimension // 2
    centro_x, centro_y = inicio_x + mitad, inicio_y + mitad
    
    # Elegir el color para el bloque central
    color_seleccionado = seleccionar_color(colores_usados)
    
    # Crear una copia de colores usados para evitar la repetición
    colores_para_evitar = colores_usados.copy()

    if x_especial < centro_x and y_especial < centro_y:
        dibujar_forma_L(canvas, [(centro_x - 1, centro_y), (centro_x, centro_y - 1), (centro_x, centro_y)], color_seleccionado)
        rellenar_area(canvas, mitad, inicio_x, inicio_y, x_especial, y_especial, colores_para_evitar)
        rellenar_area(canvas, mitad, inicio_x, centro_y, centro_x - 1, centro_y, [color_seleccionado] + colores_para_evitar)
        rellenar_area(canvas, mitad, centro_x, inicio_y, centro_x, centro_y - 1, [color_seleccionado] + colores_para_evitar)
        rellenar_area(canvas, mitad, centro_x, centro_y, centro_x, centro_y, [color_seleccionado] + colores_para_evitar)

    elif x_especial < centro_x and y_especial >= centro_y:
        dibujar_forma_L(canvas, [(centro_x - 1, centro_y - 1), (centro_x, centro_y - 1), (centro_x, centro_y)], color_seleccionado)
        rellenar_area(canvas, mitad, inicio_x, inicio_y, centro_x - 1, centro_y - 1, colores_para_evitar)
        rellenar_area(canvas, mitad, inicio_x, centro_y, x_especial, y_especial, [color_seleccionado] + colores_para_evitar)
        rellenar_area(canvas, mitad, centro_x, inicio_y, centro_x, centro_y - 1, [color_seleccionado] + colores_para_evitar)
        rellenar_area(canvas, mitad, centro_x, centro_y, centro_x, centro_y, [color_seleccionado] + colores_para_evitar)

    elif x_especial >= centro_x and y_especial < centro_y:
        dibujar_forma_L(canvas, [(centro_x - 1, centro_y - 1), (centro_x - 1, centro_y), (centro_x, centro_y)], color_seleccionado)
        rellenar_area(canvas, mitad, inicio_x, inicio_y, centro_x - 1, centro_y - 1, colores_para_evitar)
        rellenar_area(canvas, mitad, inicio_x, centro_y, centro_x - 1, centro_y, [color_seleccionado] + colores_para_evitar)
        rellenar_area(canvas, mitad, centro_x, inicio_y, x_especial, y_especial, [color_seleccionado] + colores_para_evitar)
        rellenar_area(canvas, mitad, centro_x, centro_y, centro_x, centro_y, [color_seleccionado] + colores_para_evitar)

    else:
        dibujar_forma_L(canvas, [(centro_x - 1, centro_y - 1), (centro_x - 1, centro_y), (centro_x, centro_y - 1)], color_seleccionado)
        rellenar_area(canvas, mitad, inicio_x, inicio_y, centro_x - 1, centro_y - 1, colores_para_evitar)
        rellenar_area(canvas, mitad, inicio_x, centro_y, centro_x - 1, centro_y, [color_seleccionado] + colores_para_evitar)
        rellenar_area(canvas, mitad, centro_x, inicio_y, centro_x, centro_y - 1, [color_seleccionado] + colores_para_evitar)
        rellenar_area(canvas, mitad, centro_x, centro_y, x_especial, y_especial, [color_seleccionado] + colores_para_evitar)

def crear_ventana(k, x_especial, y_especial):
    dimension = 2 ** k
    ventana = tk.Tk()
    ventana.title("Adoquinamiento")
    
    lienzo = tk.Canvas(ventana, width=dimension * 30, height=dimension * 30, bg='white')
    lienzo.pack()
    
    # Dibuja los bloques en el lienzo
    rellenar_area(lienzo, dimension, 0, 0, x_especial, y_especial, [])
    
    # Dibuja el bloque especial en negro
    coordenadas_cuadro_especial = [y_especial * 30, x_especial * 30, (y_especial + 1) * 30, (x_especial + 1) * 30]
    lienzo.create_rectangle(*coordenadas_cuadro_especial, fill='black', outline='black')
    
    ventana.mainloop()

def ejecutar():
    if len(sys.argv) < 2 or len(sys.argv) > 4:
        print("Uso: python3 main.py <k> [x_especial y_especial]") #La forma en como se debe poner en el terminal para que se ejecute el programa.
        return
    
    try:
        k = int(sys.argv[1])
        if k <= 0:
            print("El valor de k debe ser un número entero positivo.")
            return
    except ValueError:
        print("Ingrese un número entero válido para k.")
        return

    dimension = 2 ** k
    if len(sys.argv) == 4:
        try:
            x_especial = int(sys.argv[2])
            y_especial = int(sys.argv[3])
            if x_especial < 0 or x_especial >= dimension or y_especial < 0 or y_especial >= dimension:
                print(f"Las coordenadas del bloque especial deben estar en el rango [0, {dimension-1}].")
                return
        except ValueError:
            print("Ingrese valores enteros válidos para las coordenadas del bloque especial.")
            return
    else:
        x_especial, y_especial = dimension // 2, dimension // 2
    
    crear_ventana(k, x_especial, y_especial)

if __name__ == "__main__":
    ejecutar()
