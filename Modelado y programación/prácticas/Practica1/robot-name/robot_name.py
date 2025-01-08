import random;

def nombre_genera():
    letters = 'abcdefghijklmnopqrstuvwxyz'.upper()
    return '{}{}'.format(''.join(random.sample(letters,2)),random.choice(range(100,999)))

class Robot(object):

    def __init__(self):
        self.name = ""
        self.reset()

    def reset(self):
        nombre = nombre_genera()
        while self.name == nombre:
            nombre = nombre_genera()
        self.name = nombre
