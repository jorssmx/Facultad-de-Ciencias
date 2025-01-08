import java.util.Random;
/**
 * Clase que representa a un alebrijes.
 * @author Sanchez Sanchez Jorge Angel
 * @version 1a
 * No Cuenta: 315155534
*/
public class Alebrije{

  /* cabeza del alebrije. */
  public String cabeza;

  /* cuerpo del alebrije. */
  public String cuerpo;

  /* color del alebrije. */
  public String color;

  /* patas del alebrije. */
  public int patas;

  /**
   * Metodo constructor que crea un nuevo alebrije.
   * @param cabeza - la cabeza del alebrije.
   * @param cuerpo - el cuerpo del alebrije.
   * @param color - el color del alebrije.
   * @param patas - las patas del alebrije.
  */
  public Alebrije(String cabeza, String cuerpo, int patas){
    this.cabeza = cabeza;
    this.cuerpo = cuerpo;
    this.color = color;
    this.patas = patas;
  }

  /**
   * Metodo que te da la cabeza del alebrije.
   * @return String - la cabeza del alebrije.
  */
  public String getCabeza(){
    return this.cabeza;
  }

  /**
   * Metodo que modifica la cabeza del alebrije.
   * @param cabeza - la cabeza del alebrije.
  */
  public void setCabeza(String cabeza){
    this.cabeza = cabeza;
  }

  /**
   * Metodo que te da el cuerpo del alebrije.
   * @return String - el cuerpo del alebrije.
  */
  public String getCuerpo(){
    return this.cuerpo;
  }

  /**
   * Metodo que modifica el cuerpo del alebrije.
   * @param cuerpo - el cuerpo del alebrije.
  */
  public void setCuerpo(String cuerpo){
    this.cuerpo = cuerpo;
  }

  /**
   * Metodo que te da el color del alebrije.
   * @return String - el color del alebrije.
  */
  public String getColor(){
    return this.color;
  }

  /**
   * Metodo que modifica el color del alebrije.
   * @param color - el color del alebrije.
  */
  public void setColor(String color){
    this.color = color;
  }

  /**
   * Metodo que te da la cantidad de patas del alebrije.
   * @return int - la cantidad de patas del alebrije.
  */
  public int getPatas(){
    return this.patas;
  }

  /**
   * Metodo que modifica la cantidad de patas del alebrije.
   * @param patas - la cantidad de patas del alebrije.
  */
  public void setPatas(int patas){
    this.patas = patas;
  }

  /**
   * Metodo que genera un color aleatorio.
   * @return String - el color aleatorio.
  */
  public String generaColor(){
    Random random = new Random();
    int numero = 1 + random.nextInt(15);
    switch(numero){
      case 1:
        String c1 = "verde";
        return c1;
      case 2:
        String c2 = "amarillo";
        return c2;
      case 3:
        String c3 = "rojo";
        return c3;
      case 4:
        String c4 = "azul";
        return c4;
      case 5:
        String c5 = "morado";
        return c5;
      case 6:
        String c6 = "dorado";
        return c6;
      case 7:
        String c7 = "plateado";
        return c7;
      case 8:
        String c8 = "gris";
        return c8;
      case 9:
        String c9 = "blanco";
        return c9;
      case 10:
        String c10 = "negro";
        return c10;
      case 11:
        String c11 = "naranja";
        return c11;
      case 12:
        String c12 = "cafe";
        return c12;
      case 13:
        String c13 = "rosa";
        return c13;
      case 14:
        String c14 = "beige";
        return c14;
      case 15:
        String c15 = "purpura";
        return c15;
    }
    return generaColor();
  }

  /**
   * Metodo que nos muestra la representacion de un alebrije.
  */
  public String toString(){
    return "El alebrije tiene:\n"+
           "cabeza de: "+cabeza+ " color "+generaColor()+
           "\ncuerpo de: "+cuerpo+ " color "+generaColor()+
           "\ny tiene: "+ patas+ " patas\n";
  }

}
