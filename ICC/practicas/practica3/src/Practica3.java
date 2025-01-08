import java.util.Scanner;
/**
 * Clase que implementa los metodos de una Interfaz
 * que es un analizador de cadenas
 * @author Jorge Angel Sanchez Sanchez
 * @version 1a
 * No Cuenta: 315155534
*/
public class Practica3 implements AnalizadorDeCadenas{

  /**
   * quita los espacios en blanco de una cadena
   * y los guarda en una nueva variable ya sin los espacios
   * @param cadena - la cadena a recorrer
   * @return String - la nueva cadena ya sin espacios
  */
  private String quitaEspacios(String cadena){
    String sinEspacios = "";
    for(int i = 0; i < cadena.length(); i++){
      if(cadena.charAt(i) != ' ')
      sinEspacios += cadena.charAt(i);
    }
    return sinEspacios != null ? sinEspacios : "";
  }

  @Override
  public int encuentraPosicion(String cadena, char c){
    this.quitaEspacios(cadena);
    for(int i = 0; i < cadena.length(); i++)
      while(c == cadena.toLowerCase().charAt(i)){
        return i;
      }
      return -1;
  }

  @Override
  public boolean esNumero(String cadena){
    char abecedario;//caracter abecedario donde este guarde la letra
    //for para recorrer las letras del abecedario
    for(abecedario = 'z'; abecedario <= 'a'; abecedario--)
      for(int i = 0; i < cadena.length(); i++)
        while(abecedario == cadena.toLowerCase().charAt(i)){
          return false;
        }
        return true;
  }
  /**
   * reemplaza un caracter por otro
   * @param a - caracter que se va a ser reemplazado
   * @param b - caracter que va a reemplazar
   * @return char - el caracter ya actualizado
  */
  private char reemplaza(char a, char b){
    return a != b ? a = b : a;
  }

  @Override
  public String reemplazaCaracter(String cadena, char reemplazado, char reemplazador){
    this.quitaEspacios(cadena);
    //String resultado = "";
    //return resultado;
    return cadena.replace(reemplazado,reemplazador);
  }

  @Override
  public int cuentaCaracter(String cadena, char c){
    this.quitaEspacios(cadena);
    int contador = 0;//contamos
    for(int i = 0; i < cadena.length(); i++){
      if(cadena.charAt(i) == c)
        contador++;
    }
    return contador != 0 ? contador : 0;
  }

  /**
   * metodo auxiliar para palindromo
   * @param cadena - la cadena a recorrer
   * @param ini - posicion inicial de la cadena
   * @param fin - posicion final de la cadena
   * @return boolean - si es palindroma true y false en otro caso
  */
  private boolean esPalindromo(String cadena, int ini, int fin){
    this.quitaEspacios(cadena);
    if(fin-ini+1 == 0 || fin-ini+1 == 1){
      return true;
    }else if(cadena.toLowerCase().charAt(ini) == cadena.toLowerCase().charAt(fin)){
      return esPalindromo(cadena.toLowerCase(), ini+1, fin-1);
    }else{
      return false;
    }
  }

  @Override
  public boolean esPalindromo(String cadena){
    this.quitaEspacios(cadena);
    return esPalindromo(cadena.toLowerCase(), 0, cadena.length()-1);
  }

  @Override
  public boolean contiene(String cadena, String buscada){
    this.quitaEspacios(cadena);
    this.quitaEspacios(buscada);
    boolean verdadero = true;//variable en verdadero
    int contador = 0;//contamos

    if(cadena.length() < buscada.length()){
      return false;
    }
    for(int i = 0; i <  cadena.length(); i++){
      if(cadena.charAt(i) == buscada.charAt(0)){
        if((cadena.length()-i) < buscada.length()){
          return false;
        }
        for(int j = 0; j < buscada.length(); j++)
          if(cadena.charAt(i) != cadena.charAt(contador)){
            verdadero = false;
          }
      }
      verdadero = true;
    }
    return true;
  }

  @Override
  public boolean sonIguales(String cadena1, String cadena2){
    this.quitaEspacios(cadena1);
    this.quitaEspacios(cadena2);
    return cadena1.toLowerCase().equals(cadena2.toLowerCase());
  }

  @Override
  public String daSubcadena(String cadena, int inicio, int fin){
    this.quitaEspacios(cadena);
    if(inicio < 0 || inicio > cadena.length()){
      return cadena;
    }
    String guarda = "";//guardademor la palabra actualizada
    for(int i = 0; i < cadena.length(); i++){
      if(inicio == i){
        for(int j = inicio; j < cadena.length(); j++){
          while(inicio != fin){
            guarda += ""+cadena.charAt(inicio);
            inicio++;
            break;
          }
        }
      }
    }
    return guarda;
  }

  //main
  public static void main(String[] args) {
    Scanner teclea = new Scanner(System.in);
    int teclado;
    System.out.println("--------------Bienvenido al Menu--------------");
      System.out.println("presiona 1: si quieres saber la posicion de una letra de la palabra que me des");
      System.out.println("presiona 2: si quieres saber si la cadena que me diste es un numero");
      System.out.println("presiona 3: si quieres reemplazar un caracter en una cadena");
      System.out.println("presione 4: si quieres saber cuantos caracteres hay del mismo");
      System.out.println("presione 5: si quieres saber si una cadena es palindromo");
      System.out.println("presione 6: si quieres saber si una cadena contiene a otra");
      System.out.println("presione 7: si quieres saber si dos cadenas son iguales o no");
      System.out.println("presiona 8: si quieres dar una subcadena");
      System.out.println("presiona 0: si quieres salir del programa");
      System.out.println("introduce el numero: ");
      teclado = teclea.nextInt();
      switch(teclado){
        case 1:
          System.out.println("-------------------------------------------------------");
          teclea.nextLine();
          System.out.println("Dame la cadena: ");
          String n1 = teclea.nextLine();
          System.out.println("Dame el caracter: ");
          char n2 = teclea.next().charAt(0);
          System.out.println("-------------------------------------------------------");
          Practica3 p = new Practica3();
          System.out.println(p.encuentraPosicion(n1,n2));
          break;

        case 2:
          System.out.println("-------------------------------------------------------");
          System.out.println("Dame la cadena: ");
          String numero = teclea.next();
          System.out.println("-------------------------------------------------------");
          Practica3 p2 = new Practica3();
          System.out.println(p2.esNumero(numero));
          break;

        case 3:
          System.out.println("-------------------------------------------------------");
          teclea.nextLine();
          System.out.println("Dame la cadena: ");
          String cad = teclea.nextLine();
          System.out.println("Dame el caracter que quieres reemplazar: ");
          char c1 = teclea.next().charAt(0);
          System.out.println("Dame el caracter que quieres que reemplace: ");
          char c2 = teclea.next().charAt(0);
          Practica3 p3 = new Practica3();
          System.out.println(p3.reemplazaCaracter(cad,c1,c2));
          break;

        case 4:
          System.out.println("-------------------------------------------------------");
          teclea.nextLine();
          System.out.println("Dame la cadena: ");
          String n3 = teclea.nextLine();
          System.out.println("Dame el caracter: ");
          char n4 = teclea.next().charAt(0);
          System.out.println("-------------------------------------------------------");
          Practica3 p4 = new Practica3();
          System.out.println(p4.cuentaCaracter(n3,n4));
          break;

        case 5:
          System.out.println("-------------------------------------------------------");
          System.out.println("Dame la cadena: ");
          String cadena = teclea.next();
          System.out.println("-------------------------------------------------------");
          Practica3 p5 = new Practica3();
          System.out.println(p5.esPalindromo(cadena));
          break;

        case 6:
          System.out.println("-------------------------------------------------------");
          teclea.nextLine();
          System.out.println("Dame la cadena: ");
          String n5 = teclea.nextLine();
          System.out.println("Dame la cadena: ");
          String n6 = teclea.nextLine();
          System.out.println("-------------------------------------------------------");
          Practica3 p6 = new Practica3();
          System.out.println(p6.contiene(n5,n6));
          break;

        case 7:
          System.out.println("-------------------------------------------------------");
          teclea.nextLine();
          System.out.println("Dame la 1 cadena: ");
          String cadena1 = teclea.nextLine();
          System.out.println("Dame la 2 cadena: ");
          String cadena2 = teclea.nextLine();
          System.out.println("-------------------------------------------------------");
          Practica3 p7 = new Practica3();
          System.out.println(p7.sonIguales(cadena1,cadena2));
          break;

        case 8:
          System.out.println("-------------------------------------------------------");
          teclea.nextLine();
          System.out.println("Dame la 1 cadena: ");
          String c = teclea.nextLine();
          System.out.println("Dame la posicion de inicio de la subcadena que quieres: ");
          int inicio = teclea.nextInt();
          System.out.println("Dame la posicion final de la subcadena que quieres: ");
          int fin = teclea.nextInt();
          System.out.println("-------------------------------------------------------");
          Practica3 p8 = new Practica3();
          System.out.println(p8.daSubcadena(c,inicio,fin));
          break;

        case 0:
          System.out.println("Saliste del programa :)");
          break;

        default:
          System.out.println("Entrada invalida");
          break;
      }
  }
}
