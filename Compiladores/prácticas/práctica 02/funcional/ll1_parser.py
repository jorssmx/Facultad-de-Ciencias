from collections import defaultdict
from grammar import Grammar, Production

class LL1Parser:
    def __init__(self, grammar):
        self.grammar = grammar

    def rewrite_grammar(self):
        new_productions = defaultdict(list)
        for production in self.grammar.getProductions():
            left_side = production.getLeftSide()
            right_sides = production.getRightSide().split('|')
            for right_side in right_sides:
                symbols = right_side.split()
                new_prod = [symbols[0]]
                for i in range(1, len(symbols)):
                    if symbols[i] in self.grammar.getTerminals() and symbols[i-1] in ['+', '-', '*', '/']:
                        new_prod.append(left_side + '_' + symbols[i])
                    else:
                        new_prod.append(symbols[i])
                new_productions[left_side].append(' '.join(new_prod))
        new_prod_list = []
        for left_side, right_sides in new_productions.items():
            new_prod_list.extend([Production(left_side, rs) for rs in right_sides])
        return Grammar(self.grammar.getTerminals(), self.grammar.getNonTerminals(), self.grammar.getStartSymbol(), new_prod_list)

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

        return table, goto_table  # Devolver ambas tablas

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
