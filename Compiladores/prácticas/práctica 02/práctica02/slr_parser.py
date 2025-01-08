from collections import defaultdict
from grammar import Grammar, Production

class SLRParser:
    def __init__(self, grammar):
        self.grammar = grammar

    def parse(self, input_string):
        action_table, _ = self.generate_table()  # Obtenemos la tabla SLR
        stack = [0]  # Inicializamos la pila con el estado inicial
        index = 0  # Índice para recorrer la cadena de entrada
        input_string += "$"  # Agregamos el símbolo de fin de entrada

        print("Paso 0: Stack =", stack, ", Input =", input_string[index:])

        while True:
            current_state = stack[-1]  # Obtenemos el estado actual del tope de la pila
            current_input = input_string[index]  # Obtenemos el símbolo actual de la entrada

            if (current_state, current_input) in action_table:
                action = action_table[current_state, current_input]
                if action.startswith("shift"):
                    _, next_state = action.split()
                    stack.append(int(next_state))
                    index += 1
                    print("SHIFT:", action)
                elif action.startswith("reduce"):
                    _, production_index = action.split()
                    production_index = int(production_index)
                    production = self.grammar.getProductions()[production_index]
                    num_symbols = len(production.getRightSide().split())
                    stack = stack[:-num_symbols]
                    current_state = stack[-1]
                    goto = action_table[current_state, production.getLeftSide()]
                    stack.append(int(goto))
                    print("REDUCE:", action)
                elif action == "accept":
                    return "Cadena válida"
            else:
                return "Cadena no válida"

            print("Paso", index, ": Stack =", stack, ", Input =", input_string[index:])


    def generate_lr0_items(self):
        start_production = self.grammar.getProductions(self.grammar.getStartSymbol())[0]
        start_item = tuple(['.', *start_production.getRightSide().split()])
        closure = self.get_closure_lr0({start_item})
        items = [closure]
        added = True
        while added:
            added = False
            for item_set in list(items):
                for symbol in self.grammar.getTerminals() + self.grammar.getNonTerminals():
                    goto = self.goto_lr0(item_set, symbol)
                    if goto and goto not in items:
                        items.append(goto)
                        added = True
        return items

    def get_closure_lr0(self, items):
        closure = set(items)
        added = True
        while added:
            added = False
            for item in list(closure):
                dot_index = item.index('.')
                if dot_index < len(item) - 1:
                    next_symbol = item[dot_index + 1]
                    if next_symbol in self.grammar.getNonTerminals():
                        productions = self.grammar.getProductions(next_symbol)
                        for prod in productions:
                            new_item = ['.', *prod.getRightSide().split()]
                            if tuple(new_item) not in closure:
                                closure.add(tuple(new_item))
                                added = True
        return closure

    def goto_lr0(self, items, symbol):
        goto_set = set()
        for item in items:
            dot_index = item.index('.')
            if dot_index < len(item) - 1 and item[dot_index + 1] == symbol:
                new_item = list(item)
                new_item[dot_index], new_item[dot_index + 1] = new_item[dot_index + 1], new_item[dot_index]
                goto_set.add(tuple(new_item))
        return self.get_closure_lr0(goto_set)

    def generate_table(self):
        items = self.generate_lr0_items()
        terminals = self.grammar.getTerminals()
        non_terminals = self.grammar.getNonTerminals()
        action_table = defaultdict(dict)
        goto_table = defaultdict(dict)
        for i, item_set in enumerate(items):
            for item in item_set:
                dot_index = item.index('.')
                if dot_index < len(item) - 1:
                    next_symbol = item[dot_index + 1]
                    if next_symbol in terminals:
                        goto_set = self.goto_lr0(item_set, next_symbol)
                        goto_index = items.index(goto_set)
                        action_table[i][next_symbol] = f"shift {goto_index}"
                    elif next_symbol in non_terminals:
                        goto_set = self.goto_lr0(item_set, next_symbol)
                        goto_index = items.index(goto_set)
                        goto_table[i][next_symbol] = goto_index
                else:
                    if item[0] != '.':
                        # Buscar la producción correspondiente
                        for prod_index, production in enumerate(self.grammar.getProductions()):
                            if production.getLeftSide() == item[0] and production.getRightSide() == ' '.join(item[1:]):
                                if item[0] == self.grammar.getStartSymbol():
                                    action_table[i]['$'] = f"accept"
                                else:
                                    for term in terminals:
                                        action_table[i][term] = f"reduce {prod_index}"
                                break

        print("Tabla SLR (Acciones):")
        for key, value in action_table.items():
            print(key, ":", value)

        return action_table, goto_table

