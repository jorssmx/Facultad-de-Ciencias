from __future__ import division
import math


class Rational(object):
    def __init__(self, numer, denom):
        self.numer = numer
        self.denom = denom

    def __eq__(self, other):
        return self.numer == other.numer and self.denom == other.denom

    def __repr__(self):
        return '{}/{}'.format(self.numer, self.denom)

    def __add__(self, other):
        newNum = self.numer*other.denom + self.denom*other.numer
        newDem = self.denom*other.denom
        return Rational(newNum,newDem)
        __repr__=__add__


    def __sub__(self, other):
        newNum = self.numer*other.denom - self.denom*other.numer
        newDem = self.denom*other.denom
        return Rational(newNum,newDem)
        __repr__=__sub__


    def __mul__(self, other):
        if type(other)== type(5):
            other=Rational(other)
        return Rational((self.numer * other.numer), self.denom * other.denom)
        __repr__=__mul__


    def __truediv__(self, other):
        if type(other)== type(5):
            other=Rational(other)
        return Rational((self.numer * other.denom), self.denom * other.numer)
        __repr__=__truediv__

    def __abs__(self):
        return Rational(math.fabs(self.numer),math.fabs(self.denom))
        __repr__=__abs__

    def __pow__(self, power):
        for power in range(0,3):
            return Rational(pow((self.numer),power),pow((self.denom),power))
        __repr__=__pow__

    def __rpow__(self, base):
        return null
