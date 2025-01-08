import sys
import networkx as nx
import matplotlib.pyplot as plt

# Práctica 02 - Complejidad Computacional.
# Fecha de entrega: 23 Octubre de 2024.

class Grafo:
    def __init__(self, archivo_entrada):
        self.vertices, self.aristas, self.adyacencia = self.cargar_grafo(archivo_entrada)

    # Esta función lee un archivo de entrada y carga el grafo.
    # El archivo contiene el número de vértices en la primera línea, el número de aristas en la segunda línea, 
    # y cada línea siguiente contiene dos números que representan una arista entre dos vértices.
    def cargar_grafo(self, archivo_entrada):
        with open(archivo_entrada, 'r') as archivo:
            vertices = int(archivo.readline().strip())
            aristas = int(archivo.readline().strip())
            adyacencia = {i: [] for i in range(1, vertices + 1)}
            for _ in range(aristas):
                a, b = map(int, archivo.readline().strip().split())
                adyacencia[a].append(b)
                adyacencia[b].append(a)
        return vertices, aristas, adyacencia

class AnalisisGrafo:
    # Método que lee el archivo que contiene un certificado (ruta hamiltoniana) y lo convierte en una lista de vértices.
    # Entrada: Un archivo con una secuencia de vértices en una ruta, separados por espacios.
    # Salida: Una lista de enteros que representa la secuencia de vértices de la ruta.
    @staticmethod
    def leer_certificado(archivo_certificado):
        # Leer el certificado de ruta hamiltoniana desde un archivo.
        with open(archivo_certificado, 'r') as archivo:
            ruta = list(map(int, archivo.readline().strip().split()))
        return ruta

    # Método que verifica si una secuencia de vértices (la ruta) es un camino hamiltoniano válido en el grafo.
    # Verifica que todos los vértices estén en la ruta
    # Verifica las conexiones entre vértices consecutivos
    # Verifica que no haya vértices repetidos.
    @staticmethod
    def verificar_ruta_hamiltoniana(grafo, ruta):
        # Verificar si la ruta es un camino hamiltoniano.

        # 1. Verificar que todos los vértices están en la ruta.
        if set(ruta) != set(range(1, grafo.vertices + 1)):
            return False, "La ruta no contiene todos los vértices."

        # 2. Verificar que existe una arista entre cada par consecutivo de vértices en la ruta.
        for i in range(len(ruta) - 1):
            if ruta[i + 1] not in grafo.adyacencia[ruta[i]]:
                return False, f"No hay arista entre {ruta[i]} y {ruta[i + 1]}."

        # 3. Verificar que cada vértice aparece solo una vez.
        if len(ruta) != len(set(ruta)):
            return False, "La ruta contiene ciclos o vértices repetidos."

        # Si todas las verificaciones pasan, es una ruta hamiltoniana.
        return True, "La ruta es hamiltoniana."

    # Método que dibuja el grafo y resalta la ruta hamiltoniana.    
    @staticmethod
    def dibujar_grafo(grafo, ruta):
        try:
            G = nx.Graph()

            # Agregar los vértices y aristas al grafo.
            for v, vecinos in grafo.adyacencia.items():
                for vecino in vecinos:
                    G.add_edge(v, vecino)

            pos = nx.spring_layout(G)

            # Dibujar todos los vértices y aristas
            nx.draw(G, pos, with_labels=True, node_color="skyblue", edge_color="gray", node_size=700, font_size=10, font_weight='bold')

            # Resaltar el camino hamiltoniano
            aristas_hamiltonianas = [(ruta[i], ruta[i + 1]) for i in range(len(ruta) - 1)]
            nx.draw_networkx_edges(G, pos, edgelist=aristas_hamiltonianas, edge_color="green", width=2)
            
            plt.show()

        except Exception as e:
            print(f"Error al dibujar el grafo: {str(e)}")

# Función principal que ejecuta el programa.
# Carga el grafo, lee el certificado, Verifica si la ruta es un camino hamiltoniano
# Muestra en la consola información como el número de vértices y aristas, el primer y último vértice de la ruta, y si la ruta es hamiltoniana.
# Si la ruta es hamiltoniana, llama a dibujar_grafo para generar una visualización.
def ejecutar(archivo_entrada, archivo_certificado):
    # Cargar el grafo desde el archivo de entrada.
    grafo = Grafo(archivo_entrada)
    
    # Leer el certificado de ruta hamiltoniana.
    ruta = AnalisisGrafo.leer_certificado(archivo_certificado)
    
    # Verificar la ruta hamiltoniana.
    es_hamiltoniana, mensaje = AnalisisGrafo.verificar_ruta_hamiltoniana(grafo, ruta)
    
    # Imprimir la información solicitada.
    print("------------------------------------------------------------------------")
    print(f"Número de Vértices: {grafo.vertices}")
    print(f"Número de Aristas: {grafo.aristas}")
    print(f"Primer vértice de la ruta: {ruta[0]}")
    print(f"Último vértice de la ruta: {ruta[-1]}")
    print("------------------------------------------------------------------------")
    print(f"Resultado: {mensaje}")
    print("------------------------------------------------------------------------")
    
    # Si es hamiltoniana, dibujar el grafo resaltando la ruta.
    if es_hamiltoniana:
        AnalisisGrafo.dibujar_grafo(grafo, ruta)


if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("")
    else:
        archivo_entrada = sys.argv[1]
        archivo_certificado = sys.argv[2]
        ejecutar(archivo_entrada, archivo_certificado)
