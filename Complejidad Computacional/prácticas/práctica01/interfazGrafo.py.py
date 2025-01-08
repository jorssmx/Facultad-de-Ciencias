import sys
import networkx as nx
import matplotlib.pyplot as plt
from tkinter import Tk, Canvas, Button
from collections import defaultdict

class Grafo:
    def __init__(self, archivo_entrada):
        self.vertices, self.aristas, self.adyacencia = self.cargar_grafo(archivo_entrada)

    def cargar_grafo(self, archivo_entrada):
        with open(archivo_entrada, 'r') as archivo:
            vertices = int(archivo.readline().strip())
            aristas = int(archivo.readline().strip())
            adyacencia = {i: [] for i in range(1, vertices + 1)}
            for _ in range(aristas):
                a, b = map(int, archivo.readline().strip().split())
                if a < 1 or a > vertices or b < 1 or b > vertices:
                    raise ValueError(f"Vértices fuera de rango en la arista: ({a}, {b})")
                adyacencia[a].append(b)
                adyacencia[b].append(a)
        return vertices, aristas, adyacencia

class RepresentacionGrafo:
    @staticmethod
    def graficar_grafo(adyacencia, euleriano_camino=None, hamiltoniano_camino=None):
        G = nx.Graph()

        # Añadir los nodos
        for vertice, vecinos in adyacencia.items():
            G.add_node(vertice)
            for vecino in vecinos:
                G.add_edge(vertice, vecino)

        # Definir la posición de los nodos para visualizarlos
        pos = nx.spring_layout(G)
        
        # Dibujar el grafo
        nx.draw(G, pos, with_labels=True, node_color='lightblue', node_size=500, font_size=10, font_color='black')

        # Si hay un camino Euleriano, resaltarlo
        if euleriano_camino:
            edges = [(euleriano_camino[i][0], euleriano_camino[i][1]) for i in range(len(euleriano_camino))]
            nx.draw_networkx_edges(G, pos, edgelist=edges, edge_color='red', width=2.0)

        # Si hay un camino Hamiltoniano, resaltarlo
        if hamiltoniano_camino:
            edges = [(hamiltoniano_camino[i], hamiltoniano_camino[i + 1]) for i in range(len(hamiltoniano_camino) - 1)]
            nx.draw_networkx_edges(G, pos, edgelist=edges, edge_color='green', width=2.0)

        plt.show()

class AnalisisGrafo:
    @staticmethod
    def es_euleriano(adyacencia):
        grados = {v: len(vecinos) for v, vecinos in adyacencia.items()}
        impares = [v for v, grado in grados.items() if grado % 2 != 0]
        if len(impares) == 0:
            return "SI", "Es un Ciclo Euleriano"
        elif len(impares) == 2:
            return "SI", f"Es un Camino Euleriano de {impares[0]} a {impares[1]}"
        else:
            return "NO", None

    @staticmethod
    def camino_euleriano(adyacencia, inicio):
        ruta = []
        actual = inicio
        while any(adyacencia.values()):
            for vecino in adyacencia[actual]:
                adyacencia[actual].remove(vecino)
                adyacencia[vecino].remove(actual)
                ruta.append((actual, vecino))
                actual = vecino
                break
        return ruta

    @staticmethod
    def hamiltoniano(adyacencia, num_vertices):
        visitados = [False] * (num_vertices + 1)

        def encontrar_hamilton(v, recorrido):
            if len(recorrido) == num_vertices:
                if recorrido[0] in adyacencia[recorrido[-1]]:
                    return recorrido + [recorrido[0]], "Ciclo Hamiltoniano"
                else:
                    return recorrido, "Ruta Hamiltoniana"

            for vecino in adyacencia[v]:
                if not visitados[vecino]:
                    visitados[vecino] = True
                    resultado = encontrar_hamilton(vecino, recorrido + [vecino])
                    if resultado:
                        return resultado
                    visitados[vecino] = False
            return None

        for vertice in range(1, num_vertices + 1):
            visitados[vertice] = True
            resultado = encontrar_hamilton(vertice, [vertice])
            if resultado:
                return "SI", resultado
            visitados[vertice] = False

        return "NO", None

def ejecutar(archivo_entrada):
    try:
        grafo = Grafo(archivo_entrada)
        euleriano, detalle_euler = AnalisisGrafo.es_euleriano(grafo.adyacencia)
        hamiltoniano, detalle_hamilton = AnalisisGrafo.hamiltoniano(grafo.adyacencia, grafo.vertices)

        euleriano_camino = None
        hamiltoniano_camino = None

        if euleriano == "SI" and "Camino Euleriano" in detalle_euler:
            inicio = int(detalle_euler.split("de ")[1].split(" a ")[0])
            adyacencia_copia = {v: vecinos.copy() for v, vecinos in grafo.adyacencia.items()}
            euleriano_camino = AnalisisGrafo.camino_euleriano(adyacencia_copia, inicio)

        if hamiltoniano == "SI":
            recorrido, _ = detalle_hamilton
            hamiltoniano_camino = recorrido

        # Mostrar el grafo con caminos resaltados
        RepresentacionGrafo.graficar_grafo(grafo.adyacencia, euleriano_camino, hamiltoniano_camino)
    except Exception as e:
        print(f"Error: {str(e)}")

# Interfaz gráfica básica con Tkinter
def iniciar_interfaz():
    root = Tk()
    root.title("Visualizador de Grafos")

    canvas = Canvas(root, width=300, height=200)
    canvas.pack()

    btn_cargar = Button(root, text="Cargar Grafo", command=lambda: ejecutar('grafo.txt'))
    btn_cargar.pack()

    root.mainloop()

if __name__ == "__main__":
    iniciar_interfaz()
