from collections import defaultdict
from grammar import Grammar, Production

class SLRParser:
    def __init__(self, grammar):
        self.grammar = grammar

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
        return action_table, goto_table
