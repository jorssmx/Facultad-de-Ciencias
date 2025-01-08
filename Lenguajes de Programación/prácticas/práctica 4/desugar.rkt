#lang plai

(require "parser.rkt")
(require "grammars.rkt")


;; Definicion del tipo AST.
(define-type AST
  [id (i symbol?)]
  [num (n number?)]
  [op (f procedure?) (args (listof AST?))]
  [fun (param symbol?) (body AST?)]
  [app (fun-expr AST?) (arg AST?)])

;; desugar: SAST → AST
(define ( desugar expr )
   (match expr
   [(idS i) (id i)]
   [(numS n) (num n)]
   ;;[(opS f) (f procedure?) (args (listof AST ?))]
   [(withS id value body) (app (fun id (desugar body)) (desugar value))]
   [(funS param body) (fun param (desugar body))]
   [(appS fun-expr arg) (app (desugar fun-expr) (desugar arg))]))

;;Algoritmo de sustitución expr[sub-id:=val]
#|
1.- id[id:= val]= val
2.- id[sub-id:= val]=id  si id distinto sub-id
3.- n[sub-id:= val] = n
4.- {op a b}[sub-id:=val] = {op a[sub-id:=val] b[sub-id:=val]}
5.- {fun param body}[sub-id:=val] = {fun param body[sub-id:=val]} si id =sub-id
6.- {fun param body}[sub-id:=val] = {fun param body} si id distinto sub-id
7.- {fun-expr arg}[sub-id:=val] = {fun-expr[sub-id:=val] arg[sub-id:=val]}
|#


;;Algoritmo de sustitución
;;subst: AST symbol AST -> AST
(define (subst expr sub-id val)
  (match expr
    [(id i) (if (symbol=? i sub-id) val expr)]
    [(num n) expr]
    ;;
    [(fun param body) (if (symbol=? param sub-id)
                          (fun param (subst body sub-id val))
                          expr)]
    [(app fun-expr arg) (app (subst fun-expr sub-id val) (subst arg sub-id val))]))

;;Interpretación de expresiones de FWAE
;;interp : AST -> AST
(define (interp expr)
  (match expr
    [(id i) (error 'interp "Variable libre")]
    [(num n) expr]
    ;;
    [(fun param body) expr]
    [(app fun-expr arg)
     (let ([fun-val (interp fun-expr)])
       (interp (subst (fun-body fun-val)
                      (fun-param fun-val)
                      (interp arg))))]))
