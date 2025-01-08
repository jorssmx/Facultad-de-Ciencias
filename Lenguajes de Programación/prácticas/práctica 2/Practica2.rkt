#lang plai
;;Definimos tipo Figura
(define-type Figura
  [triangulo (a number?) (b number?) (c number?)]
  [cuadrado (a number?)]
  [rectangulo (a number?) (b number?)]
  [rombo (a number?) (D number?) (d number?)]
  [paralelogramo (a number?) (b number?) (h number?)]
  [circulo(D number?)]
  [elipse (a number?)(b number?)])

;;Función que regresa el perimetro de una figura del tipo figuras
(define (perimetro f)
  (type-case Figura f
    [triangulo (a b c) (+ (+ a b) c)]
    [cuadrado (a) (+(+(+ a a) a)a)]
    [rectangulo (a b)(+ (+ a b)(+ a b))]
    [rombo (a D d) (+(+(+ a a) a)a)]
    [paralelogramo (a b h) (* (+ a b) 2)]
    [circulo(D) (*(*(/ D 2) pi)2)]
    ;;[elipse (a b) (*(* 2 pi)(sqrt(/(+ (* a a)(* b b))2)))]
    [else 0])
    
  )

;;Función que regresa area de la elipse del tipo figuras
(define (perimetro1 f)
  (type-case Figura f
  [elipse (a b) (*(* 2 pi)(sqrt(/(+ (* a a)(* b b))2)))]
    [else 0]))  

;;Función que regresa area de una figura del tipo figuras
(define (area f)
  (type-case Figura f
    [triangulo (a b c) (/(sqrt(*(*(* (+(+ a b)c)(+(- a b)c))(+(- b a)c)) (-(+ a b)c))) 4)]
    [cuadrado (a) (* a a)]
    [rectangulo (a b) (* a b)]
    [rombo (a D d) (/(* d D)2)]
    [paralelogramo (a b h) (* b h)]
    [circulo(D) (* pi(*(/ D 2) (/ D 2)))]
    ;;[elipse (a b) (*(* pi a)b)]
    [else 0])
    
  )
;;Función que regresa area de la elipse del tipo figuras
(define (area1 f)
  (type-case Figura f
    [elipse (a b) (*(* pi a)b)]
    [else 0])
 )
;;----------------------------------------------------------------------------------------------------------------------------------------------------;;

;;Función que regresa si un vagon es de otro tipo que no sea locomotora
;;otro? : Vagon -> boolean
(define (otro? v)
  (type-case Vagon v
    [vagon-locomotora (c) false]
    [else true]))

;;Definimos tipo Vagon
(define-type Vagon
  [vagon-simple (pasajeros number?) (capacidad number?)]
  [vagon-locomotora (carbon number?)]
  [vagon-restaurante (mesas number?) (meseros number?)]
  [vagon-dormitorio (camas number?)]
  [vagon-carga (peso number?)]
  [vagones (siguiente otro?) (siguiente-siguiente otro?)])


;;Definicion el tipo Tren
(define-type Tren
  [tren (primero vagon-locomotora?) (centro otro?) (ultimo vagon-locomotora?)])

(define tre
  (tren (vagon-locomotora 400) (vagones (vagones (vagon-carga 400)(vagones (vagon-carga 400)(vagon-carga 400)) ) (vagon-carga 400)) (vagon-locomotora 400)))

;;este tren sirve para probar la función sin-cama y también pasajeros.
(define tren-uno
  (tren (vagon-locomotora 400) (vagones (vagones (vagon-simple 400 500)(vagones (vagon-dormitorio 300)(vagon-dormitorio 300)) ) (vagon-simple 250 500)) (vagon-locomotora 400)))

;;este tren sirve para probar la función pasajeros.
(define tren-dos
  (tren (vagon-locomotora 400) (vagones (vagones (vagon-simple 400 500)(vagones (vagon-carga 300)(vagon-restaurante 300 100)) ) (vagon-simple 250 500)) (vagon-locomotora 400)))

;;Función que obtiene el número total de pasajeros.
;; pasajeros: Tren → number
( define ( pasajeros t)
   (type-case Tren t
     [tren (p c u)(+ (pasajeros-vagon c) 0)]))

;;Función que nos hace la suma de todos los pasajeros de un vagon
;;pasajeros-vagon : Vagon -> number
(define (pasajeros-vagon v)
  (type-case Vagon v
    [vagon-locomotora (c) 0]
    [vagon-restaurante (m1 m2) (+ m2 0)]
    [vagon-simple (p c) (+ p 0)]
    [vagon-carga (p) 0]
    [vagones (s ss) (+ (pasajeros-vagon s) (pasajeros-vagon ss))]
    [else 0]))


;; Función que regresa el peso en carbon
;; peso-carbon: Tren → number
( define ( peso-carbon t )
   (type-case Tren t
    [tren (p c u) (+ (peso p)(peso u))]))

;; Función que nos dice cuantos pasajeros se quedarán sin cama.
;; sin-cama: Tren → number
( define ( sin-cama t )
   (type-case Tren t
     [tren (p c u) (- (pasajeros t) (camas t))]))

;;Función que nos da el número total de camas del tren.
;;camas: Tren -> numer
( define ( camas t)
   (type-case Tren t
     [tren (p c u)(+ (camas-vagon c) 0)]))

;;Función que regresa el número total de camas de un vagon
;;camas-vagon : Vagon -> number
(define (camas-vagon v)
  (type-case Vagon v
    [vagon-locomotora (c) 0]
    [vagon-restaurante (m1 m2) 0]
    [vagon-simple (p c) 0]
    [vagon-carga (p) 0]
    [vagones (s ss) (+ (camas-vagon s) (camas-vagon ss))]
    [vagon-dormitorio (d) (+ d 0)]))


;; Función que da el peso aproximado que carga el tren
;; peso-aproximado : Tren → number
( define ( peso-aproximado t )
   (type-case Tren t
    [tren (p c u) (+ (peso p)(peso u)(peso c))]))


;;Función que regresa el peso que carga un vagon
;;peso : Vagon -> number
(define (peso v)
  (type-case Vagon v
    [vagon-locomotora (c) c]
    [vagon-restaurante (m1 m2) m2]
    [vagon-simple (p c) (* p 73)]
    [vagon-carga (p) p]
    [vagones (s ss) (+ (peso s) (peso ss))]
    [else 0]))
