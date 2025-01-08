import random
import copy

class SortingExperiment:
    def __init__(self, n, k):
        self.n = n
        self.k = k
        self.sequence = self.generate_k_zig_zag_sequence()
        
    def generate_k_zig_zag_sequence(self):
        """Genera una secuencia k-zig-zag según el valor de k."""
        secuencia = []
        izquierda = True
        valores = list(range(1, self.n + 1))
        
        while valores:
            if izquierda:
                secuencia.extend(valores[:self.k])
                valores = valores[self.k:]
            else:
                secuencia.extend(valores[:self.k][::-1])
                valores = valores[self.k:]
            izquierda = not izquierda
        
        return secuencia

    def merge_sort(self, arr):
        """Implementación de Merge Sort con contador de operaciones elementales."""
        self.merge_operations = 0
        self._merge_sort(arr)
        return self.merge_operations

    def _merge_sort(self, arr):
        if len(arr) > 1:
            mid = len(arr) // 2
            L = arr[:mid]
            R = arr[mid:]

            self._merge_sort(L)
            self._merge_sort(R)

            i = j = k = 0
            while i < len(L) and j < len(R):
                self.merge_operations += 1  # Comparación
                if L[i] < R[j]:
                    arr[k] = L[i]
                    i += 1
                else:
                    arr[k] = R[j]
                    j += 1
                k += 1

            while i < len(L):
                arr[k] = L[i]
                i += 1
                k += 1

            while j < len(R):
                arr[k] = R[j]
                j += 1
                k += 1

    def insertion_sort(self, arr):
        """Implementación de Insertion Sort con contador de operaciones elementales."""
        self.insertion_operations = 0
        for i in range(1, len(arr)):
            key = arr[i]
            j = i - 1
            while j >= 0 and key < arr[j]:
                self.insertion_operations += 1  # Comparación
                arr[j + 1] = arr[j]
                j -= 1
            arr[j + 1] = key
            self.insertion_operations += 1  # Inserción
        return self.insertion_operations

    def local_insertion_sort(self, arr):
        """Implementación de Local Insertion Sort (modificado para k elementos) con contador."""
        self.local_insertion_operations = 0
        for i in range(1, len(arr)):
            key = arr[i]
            j = max(0, i - self.k)  # Limitar el rango de búsqueda a k elementos hacia atrás
            while i > j and key < arr[i - 1]:
                self.local_insertion_operations += 1  # Comparación
                arr[i] = arr[i - 1]
                i -= 1
            arr[i] = key
            self.local_insertion_operations += 1  # Inserción
        return self.local_insertion_operations

    def run_experiment(self):
        """Ejecuta los algoritmos de ordenamiento y cuenta las operaciones elementales."""
        # Crear copias de la secuencia para cada algoritmo
        merge_arr = copy.deepcopy(self.sequence)
        insertion_arr = copy.deepcopy(self.sequence)
        local_insertion_arr = copy.deepcopy(self.sequence)

        # Ejecutar y contar operaciones de Merge Sort
        merge_ops = self.merge_sort(merge_arr)
        
        # Ejecutar y contar operaciones de Insertion Sort
        insertion_ops = self.insertion_sort(insertion_arr)

        # Ejecutar y contar operaciones de Local Insertion Sort
        local_insertion_ops = self.local_insertion_sort(local_insertion_arr)

        # Imprimir resultados
        print(f"Resultados para n = {self.n}, k = {self.k}:")
        print(f"Merge Sort operaciones: {merge_ops}")
        print(f"Insertion Sort operaciones: {insertion_ops}")
        print(f"Local Insertion Sort operaciones: {local_insertion_ops}")

# Experimentar con varios valores de n y k
for n in [12, 16]:  # Puedes añadir más valores de n
    for k in [1, 2, 3]:
        experiment = SortingExperiment(n, k)
        experiment.run_experiment()
