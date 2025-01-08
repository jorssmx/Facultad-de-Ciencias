import sys

def leer_grafo(nombre_archivo):
    with open(nombre_archivo, 'r') as archivo:
        lineas = archivo.readlines()
        n_vertices = int(lineas[0].strip())  # Número de vértices
        aristas = []
        for linea in lineas[1:]:
            u, v = map(int, linea.strip().split())
            aristas.append((u, v))
        return n_vertices, aristas

def leer_certificado(nombre_archivo):
    with open(nombre_archivo, 'r') as archivo:
        ruta = list(map(int, archivo.readline().strip().split()))
        return ruta

def verificar_ruta_hamiltoniana(n_vertices, aristas, ruta):
    if len(ruta) != n_vertices:
        return False, "La ruta no contiene todos los vértices."

    arista_set = set(aristas)

    # Verificar que cada par consecutivo en la ruta sea una arista en el grafo
    for i in range(len(ruta) - 1):
        if (ruta[i], ruta[i + 1]) not in arista_set:
            return False, f"No hay arista entre {ruta[i]} y {ruta[i + 1]}."

    # Verificar que no haya vértices repetidos
    if len(set(ruta)) != n_vertices:
        return False, "La ruta tiene vértices repetidos."

    # Verificar si es un ciclo Hamiltoniano (opcional)
    # if (ruta[-1], ruta[0]) not in arista_set:
    #     return False, f"No hay arista entre {ruta[-1]} y {ruta[0]} para cerrar el ciclo."

    return True, "La ruta es una ruta Hamiltoniana válida."

def main():
    if len(sys.argv) != 3:
        print("Uso: python verificador_hamiltoniano.py <archivo_grafo> <archivo_certificado>")
        sys.exit(1)

    archivo_grafo = sys.argv[1]
    archivo_certificado = sys.argv[2]

    # Leer el grafo y el certificado
    n_vertices, aristas = leer_grafo(archivo_grafo)
    ruta = leer_certificado(archivo_certificado)

    # Verificar la ruta Hamiltoniana
    es_valido, mensaje = verificar_ruta_hamiltoniana(n_vertices, aristas, ruta)

    # Imprimir resultados
    print(f"Número de vértices: {n_vertices}")
    print(f"Número de aristas: {len(aristas)}")
    print(f"Primer vértice de la ruta: {ruta[0]}")
    print(f"Último vértice de la ruta: {ruta[-1]}")
    print(f"Verificación: {mensaje}")

if __name__ == "__main__":
    main()
