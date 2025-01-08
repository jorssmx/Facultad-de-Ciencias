import re

class Lexer:
    def __init__(self, input_string):
        self.input_string = input_string
        self.tokens = []

    def tokenize(self):
        token_specification = [
            ('NUMBER',   r'\d+(\.\d*)?'),  # Integer or decimal number
            ('PLUS',     r'\+'),           # Plus operator
            ('MINUS',    r'-'),            # Minus operator
            ('TIMES',    r'\*'),           # Multiplication operator
            ('DIVIDE',   r'/'),            # Division operator
            ('LPAREN',   r'\('),           # Left parenthesis
            ('RPAREN',   r'\)'),           # Right parenthesis
            ('SKIP',     r'[ \t\n]'),      # Skip whitespace and tabs
        ]
        tok_regex = '|'.join('(?P<%s>%s)' % pair for pair in token_specification)
        for mo in re.finditer(tok_regex, self.input_string):
            kind = mo.lastgroup
            value = mo.group()
            if kind != 'SKIP':
                self.tokens.append((kind, value))
        return self.tokens
