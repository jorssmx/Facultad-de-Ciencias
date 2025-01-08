/**
 * Clase que representa a un animal.
 * @author Sanchez Sanchez Jorge Angel
 * @version 1a
 * No Cuenta: 315155534
*/
public class Animales{ //posiblemente nada mas el animal, color y patas.

  /* el Animal. */
  private String animal;

  /* cabeza del Animal. */
  private String cabeza;

  /* cuerpo del Animal. */
  private String cuerpo;

  /* color del Animal. */
  private String color;

  /* patas del Animal. */
  private int patas;

  /**
   * Metodo constructor que crea un nuevo Animal con patas.
   * @param cabeza - la cabeza del animal.
   * @param cuerpo - el cuerpo del animal.
   * @param color - el color del animal.
   * @param patas - las patas del animal.
  */
  public Animales(String animal, String cabeza, String cuerpo, String color, int patas){
    this.animal = animal;
    this.cabeza = cabeza;
    this.cuerpo = cuerpo;
    this.color = color;
    this.patas = patas;
  }

  /**
   * Metodo que te da el animal.
   * @return String - el animal.
  */
  public String getAnimal(){
    return this.cabeza;
  }

  /**
   * Metodo que modifica el animal.
   * @param animal - el animal.
  */
  public void setAnimal(String cabeza){
    this.cabeza = cabeza;
  }

  /**
   * Metodo que te da la cabeza del animal.
   * @return String - la cabeza del animal.
  */
  public String getCabeza(){
    return this.cabeza;
  }

  /**
   * Metodo que modifica la cabeza del animal.
   * @param cabeza - la cabeza del animal.
  */
  public void setCabeza(String cabeza){
    this.cabeza = cabeza;
  }

  /**
   * Metodo que te da el cuerpo del animal.
   * @return String - el cuerpo del animal.
  */
  public String getCuerpo(){
    return this.cuerpo;
  }

  /**
   * Metodo que modifica el cuerpo del animal.
   * @param cuerpo - el cuerpo del animal.
  */
  public void setCuerpo(String cuerpo){
    this.cuerpo = cuerpo;
  }

  /**
   * Metodo que te da el color del animal.
   * @return String - el color del animal.
  */
  public String getColor(){
    return this.color;
  }

  /**
   * Metodo que modifica el color del animal.
   * @param color - el color del animal.
  */
  public void setColor(String color){
    this.color = color;
  }

  /**
   * Metodo que te da la cantidad de patas del animal.
   * @return int - la cantidad de patas del animal.
  */
  public int getPatas(){
    return this.patas;
  }

  /**
   * Metodo que modifica la cantidad de patas del animal.
   * @param patas - la cantidad de patas del animal.
  */
  public void setPatas(int patas){
    this.patas = patas;
  }

  /**
   * Metodo que nos muestra la representacion de un animal.
  */
  public String toString(){
    return "El animal es: "+ animal+
           "\npor lo tanto su cabeza es de: "+cabeza+
           "\nsu cuerpo es de: "+cuerpo+
           "\nsu color es: "+color+
           "\ny tiene: "+ patas+ " patas\n";
  }

}
