def is_armstrong_number(number):

    number_String = str(number)
    number_long = len(number_String)
    resultado = 0
    i = 0

    while i < number_long:
        numero_Entero = int(number_String[i])
        resultado += (numero_Entero**number_long)
        i += 1
    return resultado == number  
