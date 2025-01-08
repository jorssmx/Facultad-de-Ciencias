import sys
import networkx as nx
import matplotlib.pyplot as plt

# Práctica 01 - Complejidad Computacional.
# Fecha de entrega: 10 Septiembre de 2024.

class Grafo:
    def __init__(self, archivo_entrada):
        self.vertices, self.aristas, self.adyacencia = self.cargar_grafo(archivo_entrada)

    # Esta función lee un archivo de entrada y carga el grafo.
    # Primero, lee el número de vértices y aristas, luego crea un diccionario donde cada vértice
    # tiene una lista de sus vecinos (vértices adyacentes).
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

class RepresentacionGrafo:

    # Convierte la lista de adyacencia en una matriz de adyacencia.
    # Se genera una matriz de ceros y luego se marcan con '1' las posiciones donde
    # existe una conexión entre los vértices.
    @staticmethod
    def lista_a_matriz(vertices, adyacencia):
        matriz_adj = [[0] * vertices for _ in range(vertices)]
        for v, vecinos in adyacencia.items():
            for vecino in vecinos:
                matriz_adj[v - 1][vecino - 1] = 1
                matriz_adj[vecino - 1][v - 1] = 1
        return matriz_adj

    # Escribe la matriz de adyacencia generada en un archivo de salida.
    # Cada fila de la matriz es escrita como una línea en el archivo.
    @staticmethod
    def escribir_matriz(archivo_salida, matriz_adj):
        with open(archivo_salida, 'w') as archivo:
            for fila in matriz_adj:
                archivo.write(" ".join(map(str, fila)) + "\n")

class AnalisisGrafo:

    # Verifica si el grafo es euleriano, es decir, si tiene un camino o ciclo euleriano.
    # Primero realiza una DFS (búsqueda en profundidad) para verificar si el grafo es conexo.
    # Luego cuenta los vértices con grado impar.
    # Si todos los vértices tienen grado par, existe un ciclo euleriano.
    # Si exactamente dos vértices tienen grado impar, existe un camino euleriano.
    @staticmethod
    def es_euleriano(adyacencia):
        visitados = set()

        def dfs(v):
            # DFS para visitar todos los vértices alcanzables.
            visitados.add(v)
            for vecino in adyacencia[v]:
                if vecino not in visitados:
                    dfs(vecino)

        # Iniciar DFS desde un vértice que tenga al menos una arista.
        inicio = next((v for v in adyacencia if len(adyacencia[v]) > 0), None)
        if inicio is not None:
            dfs(inicio)

        # Verificar si el grafo es conexo, es decir, si todos los vértices con aristas fueron visitados.
        vertices_con_aristas = [v for v in adyacencia if len(adyacencia[v]) > 0]
        if len(visitados) != len(vertices_con_aristas):
            return "NO", "El grafo no es conexo."

        # Contar los vértices con grado impar.
        grados = {v: len(vecinos) for v, vecinos in adyacencia.items()}
        impares = [v for v, grado in grados.items() if grado % 2 != 0]

        # Determinar si hay ciclo o camino euleriano.
        if len(impares) == 0:
            camino_euleriano = AnalisisGrafo.encontrar_camino_euleriano(adyacencia, inicio)
            return "SI", f"Es un Ciclo Euleriano\nCamino Euleriano: {' -> '.join(map(str, camino_euleriano))}"
        elif len(impares) == 2:
            camino_euleriano = AnalisisGrafo.encontrar_camino_euleriano(adyacencia, impares[0])
            return "SI", f"Es un Camino Euleriano de {impares[0]} a {impares[1]}\nCamino Euleriano: {' -> '.join(map(str, camino_euleriano))}"
        else:
            return "NO", "No hay camino ni ciclo euleriano."

    # Encuentra un ciclo o camino euleriano basado en el algoritmo de Fleury
    @staticmethod
    def encontrar_camino_euleriano(adyacencia, inicio):
        grafo = {v: vecinos[:] for v, vecinos in adyacencia.items()} # Copia el grafo para modificarlo
        camino = []
        pila = [inicio]

        while pila:
            v = pila[-1]
            if grafo[v]:
                vecino = grafo[v].pop()
                grafo[vecino].remove(v) # Elimina la arista entre v y vecino
                pila.append(vecino)
            else:
                camino.append(pila.pop()) # Añade el vértice al camino cuando no tiene vecinos

        return camino[::-1]

    # Busca un ciclo o camino hamiltoniano utilizando una búsqueda backtracking.
    # Intenta visitar todos los vértices una vez y retorna el camino si lo encuentra.
    @staticmethod
    def hamiltoniano(adyacencia, num_vertices):
        visitados = [False] * (num_vertices + 1)

        def encontrar_hamilton(v, recorrido):
            # Si ha visitado todos los vértices, revisa si es ciclo o solo ruta.
            if len(recorrido) == num_vertices:
                if recorrido[0] in adyacencia[recorrido[-1]]:
                    return recorrido + [recorrido[0]], "Ciclo Hamiltoniano"
                else:
                    return recorrido, "Ruta Hamiltoniana"

            # Explorar vecinos no visitados.
            for vecino in adyacencia[v]:
                if not visitados[vecino]:
                    visitados[vecino] = True
                    resultado = encontrar_hamilton(vecino, recorrido + [vecino])
                    if resultado:
                        return resultado
                    visitados[vecino] = False
            return None

        # Probar comenzar desde cada vértice.
        for vertice in range(1, num_vertices + 1):
            visitados[vertice] = True
            resultado = encontrar_hamilton(vertice, [vertice])
            if resultado:
                return "SI", resultado
            visitados[vertice] = False

        return "NO", None

    # Muestra información detallada del grafo: número de vértices, aristas, vértice con mayor grado,
    # existencia de caminos eulerianos y hamiltonianos, y sus detalles.
    @staticmethod
    def mostrar_info_grafo(grafo):
        grados = {v: len(vecinos) for v, vecinos in grafo.adyacencia.items()}
        vertice_max = max(grados, key=grados.get)
        euleriano, detalle_euler = AnalisisGrafo.es_euleriano(grafo.adyacencia)
        hamiltoniano, detalle_hamilton = AnalisisGrafo.hamiltoniano(grafo.adyacencia, grafo.vertices)

        print("------------------------------------------------------------------------")
        print(f"Número de Vértices: {grafo.vertices}")
        print("------------------------------------------------------------------------")
        print(f"Número de Aristas: {grafo.aristas}")
        print("------------------------------------------------------------------------")
        print(f"Vértice con mayor grado: {vertice_max} con grado {grados[vertice_max]}")
        print("------------------------------------------------------------------------")
        print(f"¿Existe Camino Euleriano?: {euleriano}")
        print("------------------------------------------------------------------------")
        print(f"Detalle: {detalle_euler}")
        print("------------------------------------------------------------------------")
        print(f"¿Existe Camino Hamiltoniano?: {hamiltoniano}")
        if hamiltoniano == "SI":
            recorrido, tipo = detalle_hamilton
            print(f"Detalle: {tipo}")
            print("Camino Hamiltoniano:", " -> ".join(map(str, recorrido)))
        else:
            print("No hay camino hamiltoniano.")
        print("------------------------------------------------------------------------")

        return euleriano, detalle_euler, hamiltoniano, detalle_hamilton

# Dibuja el grafo y, si se proporcionan, resalta los caminos eulerianos o hamiltonianos.
def dibujar_grafo(grafo, detalle_euler=None, detalle_hamilton=None):
    try:
        G = nx.Graph()

        # Agregar los vértices y aristas al grafo.
        for v, vecinos in grafo.adyacencia.items():
            for vecino in vecinos:
                G.add_edge(v, vecino)

        pos = nx.spring_layout(G)

        # Dibujar el grafo base.
        nx.draw(G, pos, with_labels=True, node_color="skyblue", edge_color="gray", node_size=700, font_size=10, font_weight='bold')

        # Resaltar el camino euleriano si está disponible.
        if detalle_euler and "Camino Euleriano" in detalle_euler:
            eulerian_path = list(map(int, detalle_euler.split("Camino Euleriano: ")[1].strip().split(" -> ")))
            aristas_euler = [(eulerian_path[i], eulerian_path[i + 1]) for i in range(len(eulerian_path) - 1)]
            nx.draw_networkx_edges(G, pos, edgelist=aristas_euler, edge_color="red", width=2)

        # Resaltar el camino hamiltoniano si está disponible.
        if detalle_hamilton and detalle_hamilton != "NO":
            recorrido, _ = detalle_hamilton
            aristas_hamilton = [(recorrido[i], recorrido[i + 1]) for i in range(len(recorrido) - 1)]
            nx.draw_networkx_edges(G, pos, edgelist=aristas_hamilton, edge_color="green", width=2)

        plt.show()
    except Exception as e:
        print(f"Error al dibujar el grafo: {str(e)}")

# Función principal que ejecuta el programa.
# Carga el grafo, genera su matriz de adyacencia, muestra la información
# sobre caminos eulerianos y hamiltonianos y dibuja el grafo.
def ejecutar(archivo_entrada, archivo_salida):
    try:
        grafo = Grafo(archivo_entrada)
        
        print(f"Vértices: {grafo.vertices}, Aristas: {grafo.aristas}")
        
        # Generar y escribir la matriz de adyacencia.
        matriz_adj = RepresentacionGrafo.lista_a_matriz(grafo.vertices, grafo.adyacencia)
        RepresentacionGrafo.escribir_matriz(archivo_salida, matriz_adj)
        print(f"Matriz de adyacencia escrita en {archivo_salida}")
        
        # Mostrar información sobre caminos Eulerianos y Hamiltonianos.
        euleriano, detalle_euler, hamiltoniano, detalle_hamilton = AnalisisGrafo.mostrar_info_grafo(grafo)
        
        # Dibujar el grafo y resaltar los caminos si existen.
        dibujar_grafo(grafo, detalle_euler, detalle_hamilton)

    except Exception as e:
        print(f"Error: {str(e)}")

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("")
    else:
        archivo_entrada = sys.argv[1]
        archivo_salida = sys.argv[2]
        ejecutar(archivo_entrada, archivo_salida)
