from automata import Automata

# Construye el autómata de la regex
pattern = Automata.compile_regex('niña|os?')

# Cadenas que acepta
print(pattern.accepts('niño'))
print(pattern.accepts('niña'))
print(pattern.accepts('niñas'))
print(pattern.accepts('niños'))
print(pattern.accepts('niñs'))

# Tokenización del texto
text = 'Las niñas extranjeras jugaban con el niño y la niña en el patio.'
tokens = text.split()

# Encuentra las correspondencias
matches = pattern.match_token(tokens)

#Imprime índices y tokenes
#que cumplen el patrón dentro del texto
for i in matches:
    print(i, text[i])

# Imprime índices y tokens que cumplen el patrón dentro del texto
for start, end in matches:
    print(start, end, ' '.join(tokens[start:end]))
