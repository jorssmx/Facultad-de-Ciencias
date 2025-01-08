#lang plai

(require "grammars.rkt")

;; Funcion que realiza la busqueda de variables en el ambiente
;; lookup: symbol Env -> AST
(define (lookup sub-id env)
  (match env
    [(mtSub)
         (error 'lookup "Variable libre")]
    [(aSub id value rest-env)
         (if (symbol=? id sub-id)
             value
             (lookup sub-id rest-env))]))


;; EVALUACIÓN PEREZOSA Y ALCANCE ESTÁTICO

;; Definicion del tipo AST-Value
(define-type AST-Value
  [numV (n number?)]
  [closureV (param symbol?) (body AST?) (env Env?)]
  [exprV (expr AST?) (env Env?)])

;; Representación de ambientes con cerraduras
(define-type Env
  [mtSub]
  [aSub (id symbol?) (value AST-Value?) (rest-env Env?)])

;; Función strict para el intérprete perezoso / estático.
;; strict: AST-Value → AST-Value
(define (strict expr)
  (match expr
    [(exprV expr env) (strict (interp expr env))]
    [else expr]))

;;intérprete perezoso / estático.
;;interp: AST Env -> AST-Value
(define (interp expr env)
  (match expr
    [(id i) (stric (lookup i env))]
    [(num n) (numV n)]
    [(binop f izq der)
     (numV (f (numV-n (stric (interp izq env))) (numV-n (strict (interp der env)))))]
    [(if0 condition then-expr else-expr)
      (if (zero? (numV-n (strict (interp test-expr env))))
          (interp test-expr env)
          (interp then-expr env))]
    [(fun param body) (closureV param body env)]
    [(app fun-expr arg)
     (let ([fun-val (strict (interp fun-expr env))])
       (interp (closureV-body fun-val)
                (aSub (closureV-param fun-val)
                      (exprV arg env);;Antes haciamos el interp pero ahora no
                      (closureV-env fun-val))))]))

;;Operaciones unarias
(interp (desugar (parse '{add1 18})) (mtSub))
(interp (desugar (parse '{sub1 35})) (mtSub))

;;Operaciones binarias
(interp (desugar (parse '{modulo 10 2})) (mtSub))
(interp (desugar (parse '{expt 2 3})) (mtSub))

;;Operaciones n-arias
(interp (desugar (parse '{+ 1 2 3})) (mtSub))
(interp (desugar (parse '{- 3 2 1})) (mtSub))
(interp (desugar (parse '{* 1 2 3})) (mtSub))
(interp (desugar (parse '{/ 8 2 2})) (mtSub))

;;Funciones 
(interp (desugar (parse '{fun {x} 2})) (mtSub))
(interp (desugar (parse '{fun {x} {+ x 6}})) (mtSub))

;;Aplicación de función

