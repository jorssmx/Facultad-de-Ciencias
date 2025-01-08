#lang plai

(require "grammars.rkt")

;; Función que realiza un mapeo entre símbols y funciones.
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
      ['sub1 sub1]
      ['not not]
      ['and (λ args (foldr (λ (x y) (and x y)) #t args))]
      ['or  (λ args (foldr (λ (x y) (or x y)) #f args))]
      ['< <]
      ['> >]
      ['<= <=]
      ['>= >=]
      ['= =]
      ['zero? zero?]
      ['empty? empty?]
      ['car car]
      ['cdr cdr]))

;;Definición del tipo SBinding.
(Define-type SBinding
        [sbinding (id symbol?) (value SAST?)])

;;Definición del tipo Condition.
(define-type Condition
  [condition (test-expr SAST?) (then-expr SAST?)]
  [else-cond (else-expr SAST?)])

;;Definición del tipo SAST.
(define-type SAST
  [idS (i symbol?)]
  [numS (n number?)]
  [boolS (b boolean?)]
  [listS (elems (listof SAST?))]
  [opS (f procedure?) (args (listof SAST?))]
  [ifS (test-expr SAST?) (then-expr SAST?) (else-expr SAST?)]
  [condS (cases (listof condition?))]
  [withS (bindings (listof sbinding?)) (body SAST?)]
  [withS* (bindings (listof sbinding?)) (body SAST?)]
  [recS (bindings (listof sbinding?)) (body SAST?)]
  [funS (params (listof symbol?)) (body SAST?)]
  [appS (fun-expr SAST?) (args (listof SAST?))])

;; Análisis sintáctico
;; parse: s-expression → SAST
(define (parse sexp)
 (match sexp
   [(? symbol?) (idS sexp)]
   [(? number?) (numS sexp)]
   [(? bool?) (boolS sexp)]))
   [(list 'if test-expr then-expr else-expr)
     (ifS (parse test-expr) (parse then-expr) (parse else-expr))]
   [(list 'with bindings body)
     (withS (parse sbinding) (parse body))]
   [(list 'with* bindings body)
     (withS* (parse sbinding) (parse body))]
   [(list 'rec bindings body)
     (recS (parse sbinding) (parse body))]
   [(list 'fun params body)
     (funS param (parse body))]
   [(list 'app fun-expr args)
     (appS (parse fun-expr) (parse args))]