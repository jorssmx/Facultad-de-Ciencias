class Automata(object):
    def __init__(self, estados, inicial, final, transiciones):
        """Clase para objeto Autómata finito"""
        self.states = estados
        self.initial = inicial
        self.final = final
        self.transitions = transiciones

    def accepts(self, cadena):
        """Método que acepta o rechaza una cadena según si esta pertenece o no al lenguaje"""
        current_state = self.initial
        for symbol in cadena:
            if (current_state, symbol) in self.transitions:
                current_state = self.transitions[(current_state, symbol)]
            else:
                return False
        return current_state in self.final

    def match_token(self, texto):
        """Regresa la posición de un tóken cuando este es aceptado por el autómata"""
        matches = []
        current_state = self.initial
        token = ''
        for i, char in enumerate(texto):
            if (current_state, char) in self.transitions:
                current_state = self.transitions[(current_state, char)]
                token += char
                if current_state in self.final:
                    matches.append((i - len(token) + 1, token))
            else:
                current_state = self.initial
                token = ''
        return matches

    @staticmethod
    def compile(regex):
        """Función que regresa un autómata finito construyéndolo a partir de una expresión regular"""
        # Variables para el autómata
        estados = set()
        transiciones = {}
        inicial = 0
        final = set()

        # Función auxiliar para generar nuevos estados
        def new_state():
            nonlocal estados
            state = len(estados)
            estados.add(state)
            return state

        # Función auxiliar para agregar transiciones
        def add_transition(from_state, to_state, symbol):
            transiciones[(from_state, symbol)] = to_state

        # Función auxiliar para procesar la expresión regular recursivamente
        def process_regex(regex, start_state, end_state):
            nonlocal estados, final

            if regex == '':
                add_transition(start_state, end_state, '')
                final.add(end_state)
                return

            if regex[0] == '(':
                subexpression_end = regex.find(')')
                process_regex(regex[1:subexpression_end], start_state, end_state)
                process_regex(regex[subexpression_end+1:], end_state, end_state)
                return

            if len(regex) > 1 and regex[1] == '*':
                new_start_state = new_state()
                new_end_state = new_state()
                add_transition(start_state, new_start_state, '')
                add_transition(new_end_state, new_start_state, '')
                add_transition(new_end_state, end_state, '')
                process_regex(regex[0], new_start_state, new_end_state)
                process_regex(regex[2:], new_end_state, end_state)
                return

            if regex[0] == '|':
                or_index = regex.find('|')
                process_regex(regex[1:or_index], start_state, end_state)
                process_regex(regex[or_index+1:], start_state, end_state)
                return

            if regex[0] == '+':
                new_start_state = new_state()
                new_end_state = new_state()
                add_transition(start_state, new_start_state, '')
                process_regex(regex[1:], new_start_state, new_end_state)
                add_transition(new_end_state, new_start_state, '')
                add_transition(new_end_state, end_state, '')
                return

            add_transition(start_state, new_state(), regex[0])
            process_regex(regex[1:], new_state(), end_state)

        # Agrega el estado inicial
        inicial = new_state()

        # Procesa la expresión regular
        process_regex(regex, inicial, new_state())

        # Retorna el autómata
        return Automata(estados, inicial, final, transiciones)

def tokenize(texto):
    """Función que tokeniza un texto dividiéndolo en palabras"""
    return texto.split()

