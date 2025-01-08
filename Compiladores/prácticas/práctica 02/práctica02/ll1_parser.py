from collections import defaultdict
from grammar import Grammar, Production

class LL1Parser:
    def __init__(self, grammar):
        self.grammar = grammar
        self.rewrite_grammar()

    def parse(self, input_string):
        table, _ = self.generate_table()  # Obtenemos la tabla LL(1)
        stack = ["$", self.grammar.getStartSymbol()]  # Inicializa la pila con el símbolo de inicio y el estado inicial
        index = 0  # Índice para recorrer la cadena de entrada
        input_string += "$"  # Agrega el símbolo de fin de entrada

        while True:
            current_symbol = stack[-1]  # Obtiene el símbolo actual del tope de la pila
            current_input = input_string[index]  # Obtiene el símbolo actual de la entrada

            if current_symbol == current_input:
                stack.pop()  # Coincidencia, consume el símbolo de la pila y la entrada
                index += 1
            else:
                if (current_symbol, current_input) in table:
                    production = table[current_symbol, current_input]
                    stack.pop()  # Desempila el símbolo actual
                    # Empuja los símbolos de la producción en orden inverso
                    for symbol in reversed(production):
                        if symbol != 'ε':  # No se necesita empujar 'ε' a la pila
                            stack.append(symbol)
                else:
                    return "Cadena no válida"

            if index == len(input_string) and not stack:
                return "Cadena válida"

            if index == len(input_string):
                return "Cadena no válida"


    def rewrite_grammar(self):
        # Modifica la gramática existente en lugar de crear una nueva instancia
        new_productions = defaultdict(list)
        for production in self.grammar.getProductions():
            left_side = production.getLeftSide()
            right_sides = production.getRightSide().split('|')
            for right_side in right_sides:
                symbols = right_side.split()
                if symbols:
                    new_prod = [symbols[0]]
                    for i in range(1, len(symbols)):
                        if symbols[i] in self.grammar.getTerminals() and symbols[i-1] in ['+', '-', '*', '/']:
                            new_prod.append(left_side + '_' + symbols[i])
                        else:
                            new_prod.append(symbols[i])
                    new_productions[left_side].append(' '.join(new_prod))
        # Actualiza la lista de producciones de la gramática
        self.grammar.productions = []
        for left_side, right_sides in new_productions.items():
            self.grammar.productions.extend([Production(left_side, rs) for rs in right_sides])

        return self.grammar  # Devuelve la gramática actualizada


    def generate_table(self):
        table = defaultdict(dict)
        first_dict = {}
        follow_dict = {}
        non_terminals = self.grammar.getNonTerminals()
        terminals = self.grammar.getTerminals()
        for non_terminal in non_terminals:
            self.FIRST(non_terminal, first_dict)
            self.FOLLOW(non_terminal, follow_dict, first_dict)
        for non_terminal in non_terminals:
            for production in self.grammar.getProductions(non_terminal):
                for terminal in first_dict[non_terminal]:
                    if terminal != 'ε':
                        table[non_terminal, terminal] = production.getRightSide().split()
                if 'ε' in first_dict[non_terminal]:
                    for terminal in follow_dict[non_terminal]:
                        table[non_terminal, terminal] = ['ε']

        goto_table = {}  # Nueva tabla para GOTO
        # for state, transitions in self.generate_lr0_items().items():
        #     for symbol, next_state in transitions.items():
        #         if symbol in self.grammar.getNonTerminals():
        #             goto_table[state, symbol] = next_state

        print("Tabla LL(1):")
        for key, value in table.items():
            print(key, ":", value)

        return table, goto_table  # Devolvemos ambas tablas


    def FIRST(self, symbol, first_dict):
        if symbol in self.grammar.getTerminals():
            first_dict[symbol] = set([symbol])
        elif symbol in first_dict:
            return first_dict[symbol]
        else:
            for production in self.grammar.getProductions(symbol):
                first_symbol = production.getRightSide().split()[0]
                if first_symbol != symbol:
                    self.FIRST(first_symbol, first_dict)

                first_dict[symbol] = first_dict.get(symbol, set()).union(first_dict.get(first_symbol, set()))
                if 'ε' not in first_dict.get(first_symbol, set()):
                    break
            else:
                first_dict[symbol].add('ε')

    def FOLLOW(self, symbol, follow_dict, first_dict):
        if symbol == self.grammar.getStartSymbol():
            follow_dict[symbol] = set(['$'])

        for production in self.grammar.getProductions():
            right_side = production.getRightSide().split()
            for i, s in enumerate(right_side):
                if s == symbol:
                    if i < len(right_side) - 1:
                        for first_symbol in right_side[i + 1:]:
                            if first_symbol in self.grammar.getTerminals():
                                follow_dict[symbol] = follow_dict.get(symbol, set()).union(set([first_symbol]))
                                break
                            elif 'ε' not in first_dict.get(first_symbol, set()):
                                follow_dict[symbol] = follow_dict.get(symbol, set()).union(first_dict.get(first_symbol, set()))
                                break
                        else:
                            follow_dict[symbol] = follow_dict.get(symbol, set()).union(follow_dict.get(production.getLeftSide(), set()))
                    else:
                        follow_dict[symbol] = follow_dict.get(symbol, set()).union(follow_dict.get(production.getLeftSide(), set()))
