
/**
*@author Jorge Angel Sanchez Sanchez
*@version 1a
*
*/
public class Practica1{
  public static void main(String[] args) {
    //Actividad 1 y 2.
    /**
    * Definicion, declaracion y manipulacion de datos de los
    * tipos primitivos que java provee.
    */
    /**
    *Es un tipo que permite la facilidad de verificaciones.
    *
    */
    boolean verdadero = true;
    boolean falso = false;
    /**
    * Es un tipo que permite darle un valor de tipo entero..
    * con un maximo de 16 bites.
    */
    int a0 = 0;
    int a1 = 1;
    /**
    * Es un tipo que permite darte un valor de tipo flotante.
    * con un maximo de 32 bites.
    */
    float m0 = 0.0f;
    float m1 = 1.0f;
    /**
    * Es un tipo que permite darte un valor de tipo decimal tipo double.
    * con un maximo de 64 bites.
    */
    double c0 = 0.0;
    double c1 = 1.1;
    /**
    * Es un tipo que permite darte un numero de tipo short.
    * con un maximo de 16 bites.
    */
    short d0 = 0;
    short d1 = 3;
    /**
    * Es un tipo que permite darte un numero de tipo long.
    * con un maximo de 64 bites.
    */
    long e0 = 0;
    long e1 = 2;
    /**
    * Es un tipo que permite darte un numero de tipo bite.
    * con un maximo de 8 bites.
    */
    byte g0 = 01;
    byte g1 = 00;
    /**
    * Es un tipo que permite darte un caracter.
    * con un maximo de 16 bites.
    */
    char t0 = 't';
    char t1 = 'h';
    //Actividad 3.
    /**
    * Declaracion y asignacion de variables.
    */
    boolean boleanTrue = true;
    byte cientoVeintisiete = 127;
    short treintaYDosMil = 32000;
    int dosMilMillones = 2000000000;
    long doscientosMilMillones = 200000000000l;
    char b = 'b';
    float unoPuntodosMil = 1.2000f;
    double unoPuntoDoscientosMilMillones = 1.200000000000;
    //Actividad 4.
    /**
    * Declaracion y asignacion de variables para representar las
    * siguientes expresiones..
    */
    int y = 2;
    double p = y*y;
    double pr = y*y+1;
    double pru = y*y*y;
    double x = pru*(y/pr);
    double prueb = x*x;
    double prueba1 = x*x/y;
    double prueba = x+30;
    double z = x*(prueba/(p+prueba1));
    System.out.println(y);
    System.out.println(x);
    System.out.println(z);
    boolean w = (true && false || true || true);
    boolean v = (false || w && w || false || !false);
    System.out.println(w);
    System.out.println(v);
  }
}
