#lang plai

(require "grammars.rkt")
(require "parser.rkt")
(require "desugar.rkt")
;; Búsqueda en el ambiente de evaluación.
;; lookup: symbol Env → AST
(define (lookup sub-id env)
   (match env
      [(mtSub) 
         (error 'lookup "Variable libre")]
      [(aSub id value rest-env)
         (if (symbol=? id sub-id)
             value
             (lookup sub-id rest-env))]))

;; Aplicación de puntos estrictos.
;; strict: AST-Value → AST-Value
(define (strict expr)
   (match expr
      [(exprV expr env) (strict (interp expr env))]
      [else expr]))

;; Función que realiza la creación del ambiente recursivo e interpete. En pocas palabras aplica 
;; la regla de los tres pasos.
;; cyclically-bind-and-interp: symbol ASA Env -> Env
(define (cyclically-bind-and-interp id value env)
  (let* ([contenedor (box (numV 1729))] ; ; Paso 1
        [ambiente (aRecSub id contenedor env)]
        [valor (interp value ambiente)])
    (begin
      (set-box! contenedor valor) ; ; Paso 2
      ambiente)))


;; Análisis semántico
;; interp: AST Env → AST-Value
(define (interp expr env)
  (match expr
    [(id i) 
      (lookup i env)]
    [(num n) 
      (numV n)]
    [(add i d) 
      (numV (+ (numV-n (interp i env)) (numV-n (interp d env))))]
    [(sub i d) 
      (numV (- (numV-n (interp i env)) (numV-n (interp d env))))]
    [(plus i d) 
      (numV (* (numV-n (interp i env)) (numV-n (interp d env))))]
    [(if0 cond-expr then-expr else-expr)
      (if (zero? (numV-n (interp cond-expr env)))
          (interp then-expr env)
          (interp else-expr env))]
    [(rec id value body)
      (interp body (cyclically-bind-and-interp id value env))]
    [(fun p b) 
      (closureV p b env)]
    [(app f a)
        (let ([fun-val (interp f env)])
          (interp (closureV-body fun-val)
                  (aSub (closureV-param fun-val) (interp a env) (closureV-env fun-val))))]))
