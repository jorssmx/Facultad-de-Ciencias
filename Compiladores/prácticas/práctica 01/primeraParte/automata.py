class Automata:
    def __init__(self, states, initial, final, transitions):
        self.states = states
        self.initial = initial
        self.final = final
        self.transitions = transitions

    def accepts(self, token):
        current_state = self.initial
        for char in token:
            found = False
            for transition in self.transitions[current_state]:
                if transition[1] == char:
                    current_state = transition[2]
                    found = True
                    break
            if not found:
                return False
        return current_state in self.final

    @staticmethod
    def compile_regex(regex):
        pos = 0
        stack = []

        while pos < len(regex):
            sym = regex[pos]

            if sym == '?':
                last_aut = stack.pop()
                new_aut = Automata(last_aut.states, last_aut.initial, last_aut.final, last_aut.transitions)
                for state in last_aut.final:
                    new_aut.transitions[state].append((state, '', last_aut.initial))
                stack.append(new_aut)

            elif sym == '*':
                last_aut = stack.pop()
                for state in last_aut.final:
                    last_aut.transitions[state].append((state, '', last_aut.initial))
                stack.append(last_aut)

            elif sym == '+':
                last_aut = stack.pop()
                for state in last_aut.final:
                    last_aut.transitions[state].append((state, '', last_aut.initial))
                stack.append(last_aut)

            elif sym == '|':
                right_aut = stack.pop()
                left_aut = stack.pop()

                states = left_aut.states | right_aut.states
                initial = 'q0'
                final = left_aut.final | right_aut.final

                transitions = {}
                transitions.update(left_aut.transitions)
                transitions.update(right_aut.transitions)

                for state in left_aut.final:
                    transitions[state].append((state, '', right_aut.initial))

                stack.append(Automata(states, initial, final, transitions))

            else:
                new_aut = Automata(states={f'q{i}' for i in range(3)}, initial='q0', final={'q2'}, transitions={'q0': [(0, sym, 'q1')], 'q1': [(1, '', 'q2')], 'q2': []})
                stack.append(new_aut)

            pos += 1

        return stack.pop()

    def match_token(self, tokens):
        i = 0
        locations = []
        while i < len(tokens):
            max_length = 0
            for length in range(1, len(tokens) - i + 1):
                token = tokens[i:i + length]
                if self.accepts(token):
                    max_length = length
                    break
            if max_length > 0:
                locations.append((i, i + max_length))
                i += max_length
            else:
                i += 1
        return locations

def clean(token):
    return ''.join(ch for ch in token if ch.isalnum()).lower()

def tokenize(text):
    lower_text = text.strip().lower()
    tokens = lower_text.split()
    cleaned_tokens = [clean(token) for token in tokens]
    return cleaned_tokens


