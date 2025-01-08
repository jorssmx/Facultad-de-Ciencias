#lang plai

;;Función que dados el largo, ancho y altura de un paralelepípedo
;;rectángulo respectivamente, calcula el área lateral del mismo.
;;area-lateral : number number number -> number
(define (area-lateral a b c)
  (*(*(+ a b)2)c)
  )

;;(test (area-lateral 2 4 8) 96)
;;(test (area-lateral 1 1 1) 4)
;;(test (area-lateral 1 2 2) 12)

;;Función que nos el radio a tráves del diametro.
(define (dame-el-radio d)
  (/ d 2))

;;Función ue dada la generatriz, la altura y el diámetro de la base
;;de un cono circular recto, calcula el área total del mismo.
;;area-total : number number -> number
(define (area-total g d)
  (let([dame-el-radio d])
    (+(* pi d g) (*(* d d)pi)))
  )

;;(test (area-total 20 10)942.477796077)

;;Función que dados cuatro números indica si se encuentran ordenados
;;de forma decremental.
;;decremental ?: number number number number -> boolean
(define (decremental ? a b c d)
  (if (<= d c (and <= c b) (and <= b a)) #t #f))

;;(test (decremental #t 1 7 2 9)#f)
;;(test (decremental #t 1 8 3 5)#f)
;;(test (decremental #t 6 3 1 0)#t)
;;(test (decremental #t 8 5 2 2)#t)

;;Función que dado un número que representa el número de escalones
;;de una escalera, nos indica el número de formas en que se puede
;;subir saltando, en cada salto se puede subir 1, 2 o 3 escalones
;;tomando en cuenta que hay cuatro formas dinstintas de subir:
;;1, 1, 1; 1, 2; 2, 1 o 3.
;;numero-formas : number -> number
(define (numero-formas e)
   (cond
    [(= e 0) 0]
    [(= e 1) 1]
    [(= e 2) 2]
    [(= e 3) 4]
    [else (+ (numero-formas (- e 1)) (numero-formas(- e 2)) 
             (numero-formas(- e 3)))]))

;;(test (numero-formas 3)4)
;;(test (numero-formas 4)7)
;;(test (numero-formas 10)274)


;;Función que dado un número natural, indica si es raro.Un número natural
;;es raro si al sumar cada una de sus cifras elevadas al número de cifras
;;que lo forman, se obtiene el propio número.
;; raro ?: number → boolean
(define (raro ? n )
  (cond [0 #t]
        [1 #t]
        [2 #f]
        [else #t])
  )

;;Función que dado un número los separa en unitarios es decir en uno en uno.
;;separa number -> number
(define (separa n)
 (let* [(a (modulo n 10))] (expt a 3)))


;(let* [(a (modulo n 10))] (expt a 3))

;;Función que dado un número, construya una cadena que dibuje un
;;rombo con dicho número de dígitos.
;;rombo : number -> string
(define (rombo n)
  "borra esta linea y escribe tu codigo"
  )

;;Función que dado un símbolo lo entierra n número de veces. Es decir,
;;se deberán anidar n − 1 listas hasta que se llegue a la lista que
;;tiene como único elemento al símbolo correspondiente.
;;entierra : symbol number -> list
(define (entierra s n)
  (if(zero? n) (cons s '())
     (cons (entierra s (- n 1))'())))

;;Función que encuentra los números primos en un rango de 2 a n
;;usando la Criba de Eratóstenes.
;;criba-eratostenes : number -> (listof number)
(define (criba-eratostenes n)
  (if (<= n 2) '(2) 
      #t) 
  )

;; Función que devuelve los n primeros números de una lista
;; Lista, Número -> Lista
(define (nPrimeros lst n)
  (cond [(> n (length lst)) #f]
        [(zero? n) empty]
        [else (cons (first lst) (nPrimeros (rest lst) (sub1 n)))]))

;;Función que toma un número y regresa una lista de pares con la
;;descomposición en factores primos del mismo.
;;descomposicion-primos : number -> (listof (pairof number))
(define (descomposicion-primos n )
  "borra esta linea y escribe tu codigo"
  )

;;Función que recibe una lista de números entre 0 y 99 regresa una
;;lista con su representación en japones.
;;a-japones : (listof number) -> (listof string)
(define (a-japones n) 
     (map a-japones-dos n))            

;;Función que complementa la función principal de a-japones.
(define (a-japones-dos n)
(cond [(< n 10) (simbolo)]
      [(equal? n 10) "ju"]
      [(and (> n 10) (zero? (modulo n 10)))
       (string-append (simbolo (quotient n 10))" ju ")]
      [(> n 10) (string-append (simbolo (quotient n 10))
                               " ju " (simbolo (modulo n 10)))]
  )  
)

(define (simbolo n)
  ( cond [(= n 0) "rei"]
        [(= n 1) "ichi"]
        [(= n 2) "ni"]
        [(= n 3) "san"]
        [(= n 4) "yon"]
        [(= n 5) "go"]
        [(= n 6) "roku"]
        [(= n 7) "nana"]
        [(= n 8) "haci"]
        [(= n 9) "kyu"]
    )
 )

;;Función que recibe una lista de números y regresa una nueva lista
;;que contiene únicamente aquellos que son perfectos.
;;perfectos : (listof number) -> (listof number)
#|(define (perfectos xs)
  (cond
    [(= '()) "no hay números"]
    [(= '(0)) "no se puede dividir 0/0"]
    [(= '(1)) "no se puede ya que solo tenemos un número"]
    [else (let*([a ((first xs)) (if (= (/ a a) 0) (and ))])) ])
  )|#

;;Función que dado un número nos da una lista de sus divisores.
;;divisores-de : number -> (listof number)

(define (divisores-de n)
  (let [(l (cons '() null))]
    (if (zero? n) '()
        (cond
    [(= (remainder n n) 0) (cons n l)]
    [else "algo"]))))
  

;;(suma-lista '(1 2 3)) = 6
;;Función que suma los elementos de una lista
;;suma-lista : (listof number) -> number
(define (suma-lista l)
  (match l
    ['() 0]
    [(cons x xs) (+ x (suma-lista xs))])) 

;;Función que recibe un número y calcula su serie,
;;n=Sum(1/i!) con i=0 hasta i=n.
;;aproxima : number -> number
(define (aproxima n)
  "borra esta linea y escribe tu codigo"
  )

;;Función que produce todas las rotaciones de una lista.
;;rota : (listof any) -> (listof (listof any))
(define (rota xs)
  (if (null? xs)'()
      (append (cdr xs) (cons (car xs)'())))
  )