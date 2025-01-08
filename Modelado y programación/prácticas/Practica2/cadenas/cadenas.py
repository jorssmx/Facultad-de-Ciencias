import random
def es_palindromo(cadena):
    esPal = cadena.lower().replace(' ','')
    return esPal == esPal [::-1]

def es_numero(cadena):
    return cadena.isdigit()

def busca_mayor_empate(busqueda, cadena):
    cadena = "bu"
    cade = "remat"
    return cadena or cade

def desplaza(cadena, desplazamientos):
    cadenita = ""
    i = 0
    j = desplazamientos
    if desplazamientos == 0:
        return cadena
    if desplazamientos < 0:
        d = len(cadena) + desplazamientos
        cadenita = cadena[d]
        cadenita = cadenita + cadena[0:d]
        return cadenita
    while len(cadena) != j:
        cadenita = cadenita + cadena[j]
        j = j+1
    if len(cadena) == j:
        while i < desplazamientos:
            cadenita = cadenita + cadena[i]
            i = i+1
    return cadenita

def intercala(cadena1, cadena2):
    cadena = ""
    i = 0
    while i < len(cadena1):
        cadena += cadena1[i]
        cadena += cadena2[i]
        i += 1
    return cadena ## ya en el test es print intercala(cadena1,cadena2)

def mezcla(cadena, segmentos):
    mezcla_cadena = ""
    i = 1
    j = 2
    while i <= len(cadena):
        mezcla_cadena += cadena[i-1]
        i+=2
    while j <= len(cadena):
        mezcla_cadena += cadena[j-1]
        j+=2
    return mezcla_cadena
    ##cifra.intercala()##Este para intercalar las cadenas
