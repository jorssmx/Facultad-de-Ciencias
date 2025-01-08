# grammar.py

class Grammar:
    def __init__(self, terminals, non_terminals, start_symbol, productions):
        self.terminals = terminals
        self.non_terminals = non_terminals
        self.start_symbol = start_symbol
        self.productions = productions

    def getTerminals(self):
        return self.terminals

    def getNonTerminals(self):
        return self.non_terminals

    def getStartSymbol(self):
        return self.start_symbol

    def getProductions(self, non_terminal=None):
        if non_terminal:
            return [p for p in self.productions if p.getLeftSide() == non_terminal]
        else:
            return self.productions

class Production:
    def __init__(self, left_side, right_side):
        self.left_side = left_side
        self.right_side = right_side

    def getLeftSide(self):
        return self.left_side

    def getRightSide(self):
        return self.right_side

    def getSymbolSequences(self):
        return [seq.split() for seq in self.right_side.split('|')]


