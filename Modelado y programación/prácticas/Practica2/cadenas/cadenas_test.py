import unittest

from cadenas import es_palindromo, es_numero, busca_mayor_empate, desplaza, intercala, mezcla

class CadenaTest(unittest.TestCase):

    def test_palindromo2(self):
        self.assertFalse(es_palindromo("camello"))

    def test_palindromo1(self):
        self.assertTrue(es_palindromo("Anita lava la tina"))

    def test_palindromo3 (self):
        self.assertTrue(es_palindromo("oso"))

    def test_es_numero(self):
        self.assertEqual(es_numero("15"), True)

    def test_busca_mayor_empate(self):
        self.assertEqual(busca_mayor_empate("buzo","busca aqui"),"bu")

    ##def test_busca_mayor_empate2(self):
    ##    self.assertEqual(busca_mayor_empate("crematorio","creo que remato"),"remat")

    def test_desplaza(self):
        self.assertEqual(desplaza("abcde",1),"bcdea")

    def test_desplaza(self):
        self.assertEqual(desplaza("abcde",-1),"eabcd")

    def test_intercala(self):
        self.assertEqual(intercala("ace","bdf"),"abcdef")

    def test_mezcla(self):
        self.assertEqual(mezcla("031425",3), "012345")

if __name__ == "__main__":
    unittest.main()
