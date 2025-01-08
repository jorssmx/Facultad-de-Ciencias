package mx.unam.ciencias.edd.proyecto1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.File;

import mx.unam.ciencias.edd.Lista;
/**
*Clase Proyecto1 el cual implimenta un Ordenador lexicografico.
*@author Jorge Angel Sanchez Sanchez
*@version 1a
*/

public class Proyecto1{

  static final String BANDERA_REVERSA = "-r";
  static final String BANDERA_IDENTIFICADOR = "-o";
  static final String MENSAJE_ERROR_ENTRADA_ESTANDAR = "Error con la entrada";
	static final String MENSAJE_ERROR_LECTURA_ARCHIVOS = "Error al leer archivo(s)";


  //Si se reciben archivos como argumentos se agrega en esta lista
  	static Lista<String> archivosLista = new Lista<>();

  //Las lineas del parrafo recibidas.
    static Lista<Cadena> parrafoLista = new Lista<>();

  // Booleano que nos indica si la bandera se activo.
    static boolean esReversa = false;
    static boolean cambia = false;

    /* Imprime el uso del programa y lo termina. */
      private static void uso() {
          System.err.println("Uso: java -jar proyecto1.jar N");
          System.exit(1);
      }

  public static void main(String[] args) {

     checaArgumentos(args);
     if(cambia){
        renombra(args);
        System.exit(-1);
      }
     boolean entradaEstandar = estandar(args, esReversa);
     String input;
     if (entradaEstandar)
     try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
       while ((input = br.readLine()) != null)
       parrafoLista.agrega(new Cadena(input));
     } catch (IOException io) {
       System.out.println(MENSAJE_ERROR_ENTRADA_ESTANDAR);
       System.exit(-1);
     }
     else
     for (String archivos : archivosLista)
     try (BufferedReader br = new BufferedReader(new FileReader(archivos))) {
       while ((input = br.readLine()) != null)
       parrafoLista.agrega(new Cadena(input));

     } catch (IOException io) {
       System.out.println(MENSAJE_ERROR_LECTURA_ARCHIVOS);
       System.exit(-1);
     }
     for (Cadena cad : esReversa ?
     Lista.mergeSort(parrafoLista).reversa() : Lista.mergeSort(parrafoLista))
     System.out.println(cad);

  }

  /**
    * Metodo checaArgumentos que analiza los argumentos recibidos y nos indicara si tiene alguna bandera,
	  * y en caso de que en los argumento tuviera archivos, los guardaria en una lista.
	  * @param args Argumentos recibidos de la consola.
	  */
    private static void checaArgumentos(String[] args) {
      for (String str : args) {
        if (str.equals(BANDERA_REVERSA))
        esReversa = true;
        if (!str.equals(BANDERA_REVERSA))
        archivosLista.agrega(str);
        if(str.equals(BANDERA_IDENTIFICADOR))
         cambia = true;

      }
    }
    /**
    * Metodo estandar que nos dice como se comportara nuestro prgrama, ya sea leyendo un
	  * archivo o leyendo el texto con la entrada estandar.
	  * @param args Argumentos recibidos de la consola.
	  * @param esReversa Booleano que nos indica si tiene activada la bandera.
	  * @return <tt>true</tt> Si es entrada estandar,
	  *         <tt>false</tt> si leera un archivo(s).
    */
    private static boolean estandar(String[] args, boolean esReversa ) {
      if (args.length == 0 || (args.length == 1 && esReversa))
      return true;
      return false;
    }
    /**
    * Metodo renombra el cual pasa el nombre del archivo actual a un nombre
	  * de archivo nuevo.
    *@param args Argumentos recibidos de la consola.
    */
    private static void renombra(String[] args){
      File actual = new File(args[0]);
      File nuevo = new File(args[2]);
      if(actual.renameTo(nuevo)){
        System.out.println("Archivo renombrado");
      }else{
        System.out.println("Ocurrio un error");
      }

    }
}
