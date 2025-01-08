Programa en python3

Se necesita tener instalado las librerías:

- networkx : para crear y manipular grafos.

- matplotlib : para generar gráficos y visualizaciones.

En caso de no tenerlos instalados usar :

pip install networkx si no funciona usa: pip3 install networkx

pip install matplotlib si no funciona usa: pip3 install matplotlib

Comandos:
---------------------------------------------------------

Para probar el que tiene a los dos: Hamiltoniano y Euleriano:

python3 practica01.py grafoConHyE.txt m_ConHyE.txt

---------------------------------------------------------

Para probar el que tiene Hamiltoniano pero no Euleriano:

python3 practica01.py grafoSinEuler.txt m_SinE.txt

---------------------------------------------------------

Para probar el que tiene Euleriano pero no Hamiltoniano:

python3 practica01.py grafosinhamiltoniana.txt m_sinH.txt

---------------------------------------------------------

Para probar el que no tiene ninguno:

python3 practica01.py grafoNinguno.txt m_Ninguno.txt