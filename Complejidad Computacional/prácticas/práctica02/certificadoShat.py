import sys
import random

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
                adyacencia[a].append(b)
                adyacencia[b].append(a)
        return vertices, aristas, adyacencia


class GeneradorCertificados:
    @staticmethod
    def generar_certificado_aleatorio(grafo):
        # Crear un camino aleatorio con todos los vértices
        ruta = list(range(1, grafo.vertices + 1))
        random.shuffle(ruta)  # Mezclar aleatoriamente los vértices
        return ruta

    @staticmethod
    def guardar_certificado(ruta, archivo_salida):
        # Guardar el certificado (ruta) en el archivo
        with open(archivo_salida, 'w') as archivo:
            archivo.write(" ".join(map(str, ruta)) + "\n")


def generar_certificados(archivo_entrada, archivo_salida):
    grafo = Grafo(archivo_entrada)
    certificado = GeneradorCertificados.generar_certificado_aleatorio(grafo)
    GeneradorCertificados.guardar_certificado(certificado, archivo_salida)
    print(f"Certificado generado y guardado en {archivo_salida}")


if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Uso: python generar_certificado.py <archivo_entrada> <archivo_salida>")
    else:
        archivo_entrada = sys.argv[1]
        archivo_salida = sys.argv[2]
        generar_certificados(archivo_entrada, archivo_salida)
