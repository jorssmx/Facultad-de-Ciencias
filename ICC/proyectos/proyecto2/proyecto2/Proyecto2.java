import java.util.Scanner;
import java.util.Random;
/**
 * Proyecto 2 de ICC - Programar tres tipos de cifrado de mensajes
 * que lee y guarda en un archivo de texto.
 * @author Sanchez Sanchez Jorge Angel
 * @version 1a
 * No. Cuenta: 315155534
*/
public class Proyecto2{

  private String mensaje;//mansaje a cifrar
  public String cifradoCesar;
  public String descifradoCesar;
  public String cifradoRieles;
  public String descifradoRieles;
  public final String ALFABETO = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
  //public Scanner sc = new Scanner(new File("lee.txt"));

  /**
   * Metodo que lee la cadena de entrada
   * @param sc el escaner que lee la entrada
  */
  public String obtenCadena(Scanner sc){
    String lee = sc.nextLine();
    this.mensaje = lee.replaceAll("[-+.^:,*/@(){}!=%&|#$?¿_;]0123456789","").toUpperCase();
    return lee;
  }

  /**
   * Trabaja con un atributo de la clase como cadena
   * origen y regresa la cadena entrada
   *
   * @param k El desplazamiento que se desea dar
   * @return la cadena cifrada
  */
  public String cifradoDeCesar(int k){
    String resultado = "";
    for(int i = 0; i < this.mensaje.length(); i++){
      resultado += ""+ ALFABETO.charAt((ALFABETO.indexOf(this.mensaje.charAt(i)) + k)
               % ALFABETO.length());
    }
    cifradoCesar = resultado;
    return this.cifradoCesar;
  }

  /**
   * Trabaja con un atributo donde se encuentra la cadena
   * cifrada y regresa la cadena original
   *
   * @param k El desplazamiento original
   * @return la cadena descifrada sin blancos ni caracteres especiales
  */
  public String descifradoDeCesar(int k){
    String resultado = "";
    for(int i = 0; i < cifradoCesar.length(); i++){
      resultado += ""+ ALFABETO.charAt((ALFABETO.indexOf(cifradoCesar.charAt(i)) + k)
               % ALFABETO.length());
    }
    descifradoCesar = resultado;
    return this.descifradoCesar;
  }

  /**
   * Regresa los tres rieles pegados . Trabaja a partir
   * de un atributo donde se encuentra la cadena origen .
   *
   * @return Una cadena con los rieles pegados sin blancos .
  */
  public String cifraRieles(){
    return null;
  }

  /**
   * Trabaja con un atributo donde se encuentra la cadena
   * y regresa la cadena original
   *
   * @return La cadena original , pero sin blancos ni caracteres
   * especiales.
  */
  public String descifraRieles(){
    return null;
  }

  /**
   * Revuelve el alfabeto. Trabaja a partir del alfabeto, que
   * puede ser estático y constante.
   *
   * @return El alfabeto revuelto.
  */
  public String resuelve(){
    return null;
  }

  /**
   * Trabaja con un atributo de la clase como cadena origen y
   * regresa la cadena cifrada.
   *
   * @param revuelto - El alfabeto que se uso para cifrar.
   * @return La cadena cifrada.
  */
  public String cifradoKamaSutra(String revuelto){
    return null;
  }

  /**
   * Trabaja con un atributo donde se encuentra la cadena
   * cifrada y regresa la cadena original.
   *
   * @param revuelto El alfabeto que se uso para cifrar.
   * @return La cadena descifrada, aunque sin blancos ni
   *         caracteres especiales.
  */
  public String descifraKamaSutra(String revuelto){
    return null;
  }

  //main
  public static void main(String[] args) {
    Proyecto2 p = new Proyecto2();
    Scanner sc = new Scanner(System.in);
    while(sc.hasNextLine() != false){
      p.obtenCadena(sc);
      System.out.println("---------------------------------------------------\n"+
                         "El texto es: "+p.mensaje+"\n"+
                         "El cifrado de Cesar es: "+p.cifradoDeCesar(5)+"\n"+
                         "El descifrado de Cesar es: "+p.descifradoDeCesar(22));
    }

  }
}
