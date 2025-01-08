#Practica 01: Problema de adoquinamiento.
Autor: Jorge Sánchez
Fecha de entrega: 03/09/2024
-------------------------------------------Instrucciones----------------------------------------------------------

1. pre-Condición: Tener instalado python3 ya que use python para hacer el programa.

2. Abre el terminal, ubícate en la carpeta donde se encuentra el archivo main.py, en este caso es la carpeta "src"

3. Ejecuta el programa usando: python adoquinamiento.py <tamaño de la cuadrícula> [coordenadaX coordenadaY]

1 para una cuadrícula de 2x2, 2 para una cuadrícula de 4x4, 3 para una cuadrícula de 8x8, etc.

Ejemplo: python3 main.py 3 0 0

el cuál se dibuja una cuadricula de 8x8 como lo dice el documento de la práctica que es requisito y en este caso agregué que el usuario pueda escoger dónde
aparecer o crear el cuadro especial en este caso le decimos que en el 0 0 pero puede ser en cuál quier coordenada dentro de el rango de la cuadrícula en 
caso de que no este en rango o el valor no sea valido el programa te lo va a decir.

En caso de que solo quieras poner el tamaño de la cuadricula es decir:

python adoquinamiento.py <tamaño de la cuadrícula>

Ejemplo: python3 main.py 3 

También se puede pero ahora el cuadro especial se generará en las coordenadas centrales de la cuadrícula.

-------------------------------------------Explicación------------------------------------------------------------

Hice el código de forma recursiva para dividir el área en secciones más pequeñas y colocar adoquines en forma de 'L'. La cuadrícula se llena con adoquines de colores diferentes, garantizando que los adoquines adyacentes no compartan el mismo color y así ver que si se generan bien. Además, se puede marcar una posición especial en la cuadrícula con un cuadro negro o en el caso de no generarlo en coordenadas especificas se genera en la parte central igualmente de color negro que 
es el que se representa el cuadro especial.

- La cuadrícula se divide recursivamente en cuatro subcuadrantes.

- En cada subcuadrante, se coloca un adoquin en forma de 'L' la cuál se determina de acuerdo con la posición especial proporcionada. en otro caso se pone en el centro

- Cada adoquin se pinta con un color aleatorio, evitando que adoquines adyacentes tengan el mismo color. y así poder distinguir las 'L'