from grammar import Grammar, Production
from ll1_parser import LL1Parser
from slr_parser import SLRParser

if __name__ == "__main__":
    # producciones de la gramática
    productions = [
        Production('E', 'T E\''),
        Production('E\'', '+ T E\''),
        Production('E\'', '- T E\''),
        Production('E\'', ''),
        Production('T', 'F T\''),
        Production('T\'', '* F T\''),
        Production('T\'', '/ F T\''),
        Production('T\'', ''),
        Production('F', '( E )'),
        Production('F', 'n')
    ]

    # instancia de la gramática
    grammar = Grammar(['+', '*', '-', '/', 'n', '(', ')'], ['E', 'T', 'F'], 'E', productions)

    # instancias de los parsers
    ll1_parser = LL1Parser(grammar)
    slr_parser = SLRParser(grammar)

    # Generar las tablas
    ll1_action_table, ll1_goto_table = ll1_parser.generate_table()
    slr_action_table, slr_goto_table = slr_parser.generate_table()

    # Cadenas de prueba
    test_strings = ["10+12/3", "10+", "3*(2+4)/5", "3*4+"]

    # Prueba LL(1)
    for test_string in test_strings:
        print(f"Analizando cadena '{test_string}' con LL(1):")
        result = ll1_parser.parse(test_string)
        print("Resultado:", result)

    # Prueba SLR
    for test_string in test_strings:
        print(f"Analizando cadena '{test_string}' con SLR:")
        result = slr_parser.parse(test_string)
        print("Resultado:", result)
