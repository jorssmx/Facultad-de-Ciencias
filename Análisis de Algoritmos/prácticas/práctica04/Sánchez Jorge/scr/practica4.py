class Grafo:
    def __init__(self):
        self.grafo = {}
        self.vertices = []

    def cargar_desde_archivo(self, nombre_archivo):
        """Carga el grafo desde un archivo de texto y crea un diccionario de adyacencia."""
        with open(nombre_archivo, 'r') as archivo:
            lineas = archivo.readlines()

            # Primer línea con los vértices
            self.vertices = lineas[0].strip().split(',')
            for vertice in self.vertices:
                self.grafo[vertice] = []

            # Lectura de las conexiones
            for linea in lineas[1:]:
                if ',' in linea:
                    vertice1, vertice2 = linea.strip().split(',')
                    if vertice1 in self.grafo and vertice2 in self.grafo:
                        self.grafo[vertice1].append(vertice2)
                        self.grafo[vertice2].append(vertice1)

    def dfs_recursivo(self, nodo, visitados):
        """Realiza una búsqueda en profundidad recursiva para encontrar los nodos conectados."""
        visitados.add(nodo)
        componente = [int(nodo)]  # Guardar el nodo como entero
        for vecino in self.grafo[nodo]:
            if vecino not in visitados:
                componente.extend(self.dfs_recursivo(vecino, visitados))  # Expandir la componente
        return componente

    def obtener_componentes(self):
        """Devuelve una lista de todas las componentes conexas en el grafo."""
        visitados = set()
        componentes = []

        for vertice in self.vertices:
            if vertice not in visitados:
                componente = self.dfs_recursivo(vertice, visitados)
                componentes.append(sorted(componente))  # Ordenar las componentes antes de agregar

        return componentes

def imprimir_componentes(componentes):
    """Imprime las componentes conexas de manera formateada."""
    for componente in componentes:
        print(f"[{','.join(map(str, componente))}]")

def main():
    grafo = Grafo()
    archivo_entrada = 'grafo.txt'  # Archivo con la representación del grafo

    # Cargar el grafo desde el archivo
    grafo.cargar_desde_archivo(archivo_entrada)

    # Obtener las componentes conexas
    componentes = grafo.obtener_componentes()

    # Imprimir las componentes conexas
    imprimir_componentes(componentes)

# Ejecutar el programa
if __name__ == '__main__':
    main()
