import sys
import random
import os

class Grafo:
    def __init__(self, archivo_entrada):
        self.vertices, self.aristas, self.adyacencia = self.leer_grafo(archivo_entrada)

    # Esta función lee un archivo de entrada y carga el grafo.
    # La primera línea: el número de vértices, la segunda línea: el número de aristas,
    # Las siguientes líneas: pares de vértices conectados por aristas,
    # Crea un diccionario adyacencia, donde cada clave es un vértice, y el valor es un conjunto de vértices adyacentes.
    def leer_grafo(self, archivo_entrada):
        # Leer y construir el grafo desde el archivo
        with open(archivo_entrada, 'r') as archivo:
            vertices = int(archivo.readline().strip())
            aristas = int(archivo.readline().strip())
            adyacencia = {i: set() for i in range(1, vertices + 1)}  # Usar conjuntos (set)
            for _ in range(aristas):
                u, v = map(int, archivo.readline().strip().split())
                adyacencia[u].add(v)
                adyacencia[v].add(u)
        return vertices, aristas, adyacencia


class CertificadoHamiltoniano:
    # Método que incluye todos los vértices del grafo, asegurando que cada vértice esté en la ruta al menos una vez.
    # eligiendo un vértice inicial aleatorio y luego, en un ciclo, selecciona un vértice adyacente al último vértice de la ruta.
    # Si no hay vértices adyacentes, selecciona aleatoriamente un vértice de los que aún no han sido visitados.
    @staticmethod
    def generar_ruta_aleatoria(grafo):
        # Genera una ruta aleatoria con todos los vértices
        ruta = [random.choice(list(grafo.adyacencia.keys()))]  # Comienza en un vértice aleatorio
        vertices_restantes = set(grafo.adyacencia.keys()) - set(ruta)
        
        while vertices_restantes:
            ultimo_vertice = ruta[-1]
            posibles_siguientes = grafo.adyacencia[ultimo_vertice].intersection(vertices_restantes)
            if not posibles_siguientes:
                # Si no hay vértices adyacentes, elige uno restante
                posibles_siguientes = vertices_restantes
            siguiente_vertice = random.choice(list(posibles_siguientes))
            ruta.append(siguiente_vertice)
            vertices_restantes.remove(siguiente_vertice)
        
        return ruta

    # Método que guarda la ruta generada (certificado) en un archivo en el directorio especificado.
    # Escribe la ruta en el archivo como una lista de números separados por espacios.
    @staticmethod
    def guardar_certificado(ruta, directorio_salida):
        # Guardar el certificado en un archivo con nombre único
        contador = 1
        while True:
            archivo_salida = os.path.join(directorio_salida, f"certificado{contador}.txt")
            if not os.path.exists(archivo_salida):
                break
            contador += 1
        
        with open(archivo_salida, 'w') as archivo:
            archivo.write(" ".join(map(str, ruta)) + "\n") 
        return archivo_salida

# Método que ejecuta el proceso completo de generación de un certificado hamiltoniano.
# Crea un objeto Grafo utilizando el archivo de entrada, luego genera una ruta hamiltoniana aleatoria con generar_ruta_aleatoria(grafo)
# y guarda el certificado en el directorio especificado con guardar_certificado.
def generar_certificado(archivo_entrada, directorio_salida):
    # Crea el grafo y genera un certificado basado en una ruta aleatoria
    grafo = Grafo(archivo_entrada)
    certificado = CertificadoHamiltoniano.generar_ruta_aleatoria(grafo)
    archivo_generado = CertificadoHamiltoniano.guardar_certificado(certificado, directorio_salida)
    print(f"Certificado generado y guardado en {archivo_generado}")


if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("")
    else:
        archivo_entrada = sys.argv[1]
        directorio_salida = sys.argv[2]
        if not os.path.exists(directorio_salida):
            os.makedirs(directorio_salida)  # Crear el directorio si no existe
        generar_certificado(archivo_entrada, directorio_salida)
