import sys

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
                # Validación de la entrada
                if a < 1 or a > vertices or b < 1 or b > vertices:
                    raise ValueError(f"Vértices fuera de rango en la arista: ({a}, {b})")
                adyacencia[a].append(b)
                adyacencia[b].append(a)
        return vertices, aristas, adyacencia


class RepresentacionGrafo:
    @staticmethod
    def lista_a_matriz(vertices, adyacencia):
        matriz_adj = [[0] * vertices for _ in range(vertices)]
        for v, vecinos in adyacencia.items():
            for vecino in vecinos:
                matriz_adj[v - 1][vecino - 1] = 1
                matriz_adj[vecino - 1][v - 1] = 1
        return matriz_adj

    @staticmethod
    def escribir_matriz(archivo_salida, matriz_adj):
        with open(archivo_salida, 'w') as archivo:
            for fila in matriz_adj:
                archivo.write(" ".join(map(str, fila)) + "\n")


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
    def detectar_puente(v, u, adyacencia):
        """Algoritmo optimizado para detectar puentes en un grafo"""
        tiempo = 0
        visitado = {}
        descubrimiento = {}
        bajo = {}
        padre = {}
        puentes = []

        def dfs(v):
            nonlocal tiempo
            visitado[v] = True
            descubrimiento[v] = bajo[v] = tiempo
            tiempo += 1

            for vecino in adyacencia[v]:
                if vecino not in visitado:
                    padre[vecino] = v
                    dfs(vecino)

                    bajo[v] = min(bajo[v], bajo[vecino])

                    if bajo[vecino] > descubrimiento[v]:
                        puentes.append((v, vecino))
                elif vecino != padre.get(v, -1):
                    bajo[v] = min(bajo[v], descubrimiento[vecino])

        for vertice in adyacencia.keys():
            if vertice not in visitado:
                dfs(vertice)

        return puentes

    @staticmethod
    def camino_euleriano(adyacencia, inicio):
        ruta = []
        actual = inicio
        while any(adyacencia.values()):
            for vecino in adyacencia[actual]:
                if not AnalisisGrafo.detectar_puente(actual, vecino, adyacencia) or len(adyacencia[actual]) == 1:
                    ruta.append((actual, vecino))
                    adyacencia[actual].remove(vecino)
                    adyacencia[vecino].remove(actual)
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

        if euleriano == "SI":
            print(f"Detalle: {detalle_euler}")
            if "Camino Euleriano" in detalle_euler:
                inicio = int(detalle_euler.split("de ")[1].split(" a ")[0])
            else:
                inicio = list(grados.keys())[0]
            adyacencia_copia = {v: vecinos.copy() for v, vecinos in grafo.adyacencia.items()}
            camino = AnalisisGrafo.camino_euleriano(adyacencia_copia, inicio)
            print("Camino Euleriano:", " -> ".join(f"{v1}-{v2}" for v1, v2 in camino))

        print("------------------------------------------------------------------------")
        print(f"¿Existe Camino Hamiltoniano?: {hamiltoniano}")
        if hamiltoniano == "SI":
            print("------------------------------------------------------------------------")
            recorrido, tipo = detalle_hamilton
            print(f"Detalle: {tipo}")
            print("Camino Hamiltoniano:", " -> ".join(map(str, recorrido)))
            print("------------------------------------------------------------------------")


def ejecutar(archivo_entrada, archivo_salida):
    try:
        grafo = Grafo(archivo_entrada)
        
        print(f"Vértices: {grafo.vertices}, Aristas: {grafo.aristas}")
        
        matriz_adj = RepresentacionGrafo.lista_a_matriz(grafo.vertices, grafo.adyacencia)
        
        RepresentacionGrafo.escribir_matriz(archivo_salida, matriz_adj)
        
        AnalisisGrafo.mostrar_info_grafo(grafo)
    except Exception as e:
        print(f"Error: {str(e)}")


if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("")
    else:
        archivo_entrada = sys.argv[1]
        archivo_salida = sys.argv[2]
        ejecutar(archivo_entrada, archivo_salida)
