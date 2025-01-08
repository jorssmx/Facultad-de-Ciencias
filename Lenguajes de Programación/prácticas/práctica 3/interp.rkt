#lang plai

(require "grammars.rkt")
(require "parser.rkt")

;; Algoritmo de sustitución.
;; subst: AST symbol AST → AST
(define (subst expr sub-id value)
  (match expr
    [(id i)
     (if (symbol=? i sub-id) val expr)] ;symbol=? v1 v2 
    [(num n) expr]
    [(binop f izq der) (binop f (subst izq sub-id val) (subst der sub-id val))]
    [(with id value body)
     (if (symbol=? id sub-id)
         (with id (subst value sub-id val) body)
         (with id (subst value sub-id val) (subst body sub-id val)))]))

 ;;Análisis semántico
 ;;interp: AST → number
(define (interp expr)
  (define (interp expr)
  (match expr
    [(id i) (error 'interp "Variable libre")]
    [(num n) n]
    [(binop f izq der) (f (interp izq) (interp der))]
    [(with id value body) (interp (subst body id value))]))

