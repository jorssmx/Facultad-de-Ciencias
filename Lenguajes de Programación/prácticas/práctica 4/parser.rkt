#lang plai

(require "grammars.rkt")

;; Función que realiza un mapeo entre símbolos y funciones.
;; elige: symbol → procedure
(define (elige s)
   (match s
      ['+ +]
      ['- -]
      ['* *]
      ['/ /]
      ['modulo modulo]
      ['expt expt]
      ['add1 add1]
      ['sub1 sub1]))


;;(parse e)

;; Análisis sintáctico
;; parse: s-expression → SAST
(define (parse sexp)
  (match  sexp
    [(? symbol?) (idS sexp)]
    [(? number?) (numS sexp)]
    [(list (? symbol?) val) (binding (first sexp) (parse val))]
    [(list 'with b body) (withS (map (lambda (k)(parse k)) b)(parse body) )]
    [(list 'with* b body) (withS* (map (lambda (k)(parse k)) b)(parse body))]
    [(list 'fun simbolos body) (funS (map (lambda (k)(parse k)) simbolos)(parse body) )]
    [(list (? symbol?)) (first sexp)]
    [(list expre a) ((parse expre)(map (lambda (k)(parse k)) a))]
    [list (opS (elige (first sexp)) (map (lambda (k)(parse k)) (cdr sexp)))]
    
    ))

(define e
  '{+ 3 2 4 1 3 2})

(define e2
  '{with {{r 2}{k 3}} {+ 3 r}})

(define e3
  '{with {{r 2}} {+ 3 r}})

(define e4
  '{fun {{r}} {+ 3 r}})
