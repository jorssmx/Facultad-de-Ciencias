import java.util.Scanner;
/**
 * Clase que implementa los metodos de la InterfazRecursion.
 * @author Sanchez Sanchez Jorge Angel.
 * @version 1a.
 * No Cuenta: 315155534.
 */
public class Recursivo implements InterfazRecursion{

  /**
   * Metodo con el cual ponemos una cadena al reves.
   * @param cadena - la cadena que nos dan.
   * @return String - la cadena al reves.
  */
  private static String reversa(String cadena){
    if(cadena.length() == 1)
      return cadena;
    else
      return reversa(cadena.substring(1)) + cadena.charAt(0);
  }

  @Override
  public boolean esPalindromo(String cadena){
    if(cadena == " ")
    return true;
    return cadena.toLowerCase().replace(" ","").equals(reversa(cadena.toLowerCase().replace(" ","")));
  }

  @Override
  public String reemplazaCaracter(String cadena, String reemplazado, String reemplazador){
    String resultado = cadena;
    if(cadena == null || reemplazado == null || reemplazador == null) return resultado;
    if(!cadena.equals("") && !reemplazado.equals("")){
      int pos = cadena.indexOf(reemplazado);
      if(pos != -1){
        int tamanio = reemplazado.length();
        String resto = cadena.substring(pos + tamanio);
        resultado = cadena.substring(0,pos) + reemplazador + reemplazaCaracter(resto,reemplazado,reemplazador);
      }
    }
    return resultado;
  }

  @Override
  public int cuentaCaracter(String cadena, char c){
    if(cadena == null || cadena.length() == 0) return 0;
    if(cadena.charAt(0) == c) return 1+cuentaCaracter(cadena.substring(1), c);
    else return cuentaCaracter(cadena.substring(1), c);
  }

  @Override
  public int fibonacci(int n){
    if(n == 1) return 1;
    if(n == 0) return 0;
    return fibonacci(n-1) + fibonacci(n-2);
  }

  public int[] construyeNivel(int[] anterior, int[] actual, int contador){
    if(contador == actual.length-1) return actual;
    actual[contador] = anterior[contador-1] + anterior[contador];
    return construyeNivel(anterior, actual, contador+1);
  }

  public int[][] construyeNiveles(int[][] triangulo, int contador, int niveles){
    if(contador == niveles) return triangulo;
    triangulo[contador] = new int[contador+1];
    construyeNivel(triangulo[contador-1], triangulo[contador], 1);
    triangulo[contador][0] = 1;
    triangulo[contador][contador] = 1;
    return construyeNiveles(triangulo, contador+1, niveles);
  }

  @Override
  public int[][] pascal(int n){
    int[][] triangulo = new int[4][];
    triangulo[0] = new int[1];
    triangulo[0][0] = 1;
    triangulo[1] = new int[2];
    triangulo[1][0] = 1;
    triangulo[1][1] = 1;
    return construyeNiveles(triangulo, 2, 4);
  }

  //main
  public static void main(String[] args) {
    Recursivo r = new Recursivo();
    Scanner sc = new Scanner(System.in);
    int entrada;

    do{
      System.out.println("----------------------\n"+
                         "0: salir del programa\n"+
                         "1: mostrar los datos de esPalindromo\n"+
                         "2: mostrar los datos de reemplazaCaracter\n"+
                         "3: mostrar los datos de cuentaCaracter\n"+
                         "4: mostrar los datos de fibonacci\n"+
                         "Introduce el numero:");
    while(!sc.hasNextInt()){
				System.out.println("\tEntrada invalida\t");
				sc.next();
		}
    entrada = sc.nextInt();
    switch(entrada){
      case 0:
        System.out.println("Saliste del programa :)");
        return;
      case 1:
        System.out.println("-----------------------\n"+
                           "Los datos de esPalindromo son:\n"+
                           "La palabra es: 'oso baboso' y ¿es palindromo?: "+
                           r.esPalindromo("oso baboso")+"\n"+
                           "La palabra es: 'o' y ¿es palindromo?: "+
                           r.esPalindromo("o")+"\n"+
                           "La palabra es: 'anita lava la tina' y ¿es palindromo?: "+
                           r.esPalindromo("anita lava la tina")+"\n"+
                           "La palabra es: 'Oso baboso' y ¿es palindromo?: "+
                           r.esPalindromo("Oso baboso")+"\n"+
                           "La palabra es: 'Hola mundo' y ¿es palindromo?: "+
                           r.esPalindromo("Hola mundo")+"\n"+
                           "La palabra es: ' ' y ¿es palindromo?: "+
                           r.esPalindromo(" "));
        break;
      case 2:
        System.out.println("-----------------------\n"+
                           "Los datos de reemplazarCaracter son:\n"+
                           "La palabra que utilizamos es: 'Palabra de prueba'\n"+
                           "sustituimos 'a' por 'o' -> "+
                           r.reemplazaCaracter("Palabra de prueba", "a", "o")+"\n"+
                           "sustituimos 'z' por 'h' -> "+
                           r.reemplazaCaracter("Palabra de prueba", "z", "h")+"\n"+
                           "sustituimos 'P' por 'H' -> "+
                           r.reemplazaCaracter("Palabra de prueba", "P", "H")+"\n"+
                           "sustituimos 'p' por 'w' -> "+
                           r.reemplazaCaracter("Palabra de prueba", "p", "w"));
        break;
      case 3:
        System.out.println("--------------------------------\n"+
                           "Los datos de cuentaCaracter son:\n"+
                           "La palabra que utilizamos es: 'Ferrocarrilero'\n"+
                           "¿cuantas veces aparece 'r'? -> "+
                           r.cuentaCaracter("Ferrocarrilero", 'r')+"\n"+
                           "¿cuantas veces aparece 'i'? -> "+
                           r.cuentaCaracter("Ferrocarrilero", 'i')+"\n"+
                           "¿cuantas veces aparece 'h'? -> "+
                           r.cuentaCaracter("Ferrocarrilero", 'h'));
        break;
      case 4:
        System.out.println("---------------------------\n"+
                           "Los datos de fibonacci son:\n"+
                           "fibonacci de 5 -> "+ r.fibonacci(5)+
                           "\nfibonacci de 9 -> "+ r.fibonacci(9)+
                           "\nfibonacci de 3 -> "+ r.fibonacci(3)+
                           "\nfibonacci de 11 -> "+ r.fibonacci(11)+
                           "\nfibonacci de 23 -> "+ r.fibonacci(23));
        break;
      case 5:
        System.out.println("------------------------\n"+
                           "casi sale :(\n"+
                           "Los datos de pascal son:\n"+
                           "pascal de 4 -> "+ r.pascal(4));
      default:
        System.out.println("\tEntrada invalida\t");
        break;
    }
    }while(entrada != 0);

  }

}
