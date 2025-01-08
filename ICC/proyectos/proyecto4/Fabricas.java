import java.util.Scanner;
import java.io.Reader;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * Clase que simula un catalogo de alebrijes.
 * el cual puedes agregar, modificar y eliminar un animal del catalogo.
 * y tambien puedes agregar y eliminar un alebrije.
 * se agrego que el catalogo lo tenemos en un archivo y este lo leemos para mostrarlo.
 * tambien podemos modificar un alebrije que esta en un archivo
 * y este se puede modificar y se vea reflejado en el archivo de texto.
 * y por ultimo podemos cargar un archivo de texto con un alebrije creado
 * y definido con sus caracteristicas y poderlo modificar.
 * @author Sanchez Sanchez Jorge Angel
 * @version 1a
 * No Cuenta: 315155534
*/
public class Fabricas{

  /* El escaner */
  static Scanner sc = new Scanner(System.in);

  /* la lista de la clase */
  static ListaLigada lista = new ListaLigada();

  /* la lista donde lo utilizamos para modificar el alebrije*/
  static Alebrije guarda = new Alebrije("","",0);

  /* la lista para el catalogo 1 y 2*/
  static ListaLigada lista1 = new ListaLigada();

  /* lo utilizamos para modificar el animal 1 del catalogo */
  static Animales guarda1 = new Animales("","","","",0);

  /* lo utilizamos para modificar el animal 2 del catalogo */
  static Animales guarda2 = new Animales("","","","",0);

  /* lo utilizamos para modificar el animal 3 del catalogo */
  static Animales guarda3 = new Animales("","","","",0);

  /* lo utilizamos para modificar el animal 4 del catalogo */
  static Animales guarda4 = new Animales("","","","",0);

  /* lo utilizamos para modificar el animal 5 del catalogo */
  static Animales guarda5 = new Animales("","","","",0);

  /* para salir del programa */
  public static boolean salir(){
    try {
      boolean salir = sc.nextBoolean();
      return salir;
    } catch(InputMismatchException e) {
      System.out.println("\tEntrada invalida");
    } catch (Exception e) {
      System.out.println("Algo salio mal :/");
    }
    System.out.println("por lo tanto no sales del programa");
    return false;
  }

  /* verifica si la entrada es correcta */
  public static void verificaNumero(){
    while(!sc.hasNextInt()){
      System.out.println("\tEntrada invalida\t");
      sc.next();
    }
  }

  /** Para pedir el numero que teclea el usuario. */
  public static int darNumero(String mensaje){
    System.out.println(mensaje);
    verificaNumero();
    int numero = sc.nextInt();
    return numero;
  }

  /* catalogo de la fabrica. */
  public static void catalogo(String archivo){

    try {

      Reader r = new FileReader(archivo);
      BufferedReader lector = new BufferedReader(r);
      String linea = "";
      while(linea != null){
        linea = lector.readLine();
        int coma1 = linea.indexOf(",");
        String animal = linea.substring(0, coma1);
        int coma2 = linea.indexOf(",", coma1+1);
        String cabeza = linea.substring(coma1+2, coma2);
        int coma3 = linea.indexOf(",", coma2+1);
        String cuerpo = linea.substring(coma2+2, coma3);
        int coma4 = linea.indexOf(",", coma3+1);
        String color = linea.substring(coma3+2, coma4);
        int patas = Integer.valueOf(linea.substring(coma4+2));

        Animales nuevo = new Animales(animal, cabeza, cuerpo, color, patas);
        System.out.println(nuevo);
        lista1.insertaFinal(nuevo);
        if(linea == null){
          lector.close();
          r.close();
        }
      }

     } catch(FileNotFoundException fnfe){
			 System.out.println("No se encontró el archivo");
     } catch(NullPointerException npe) {
       System.out.println("");
     } catch(IOException ioe) {
      System.out.println("no puedo leer la linea");
		 } catch(Exception e){
			 System.out.println("Ocurrió un problema en la lectura");
		 }
  }

  /** para escribir en el archivo del alebrije. */
  public static void escribeAlebrije(String archivo, Alebrije a){
		try{
			Writer w = new FileWriter(archivo);
			BufferedWriter escritor = new BufferedWriter(w);
			String rep = a.cabeza+", "+a.cuerpo+", "+a.patas;
			w.write(rep);
			w.close();
    } catch(FileNotFoundException fnfe){
      System.out.println("No se encontró el archivo");
    } catch(NullPointerException npe) {
      System.out.println("");
    } catch(IOException ioe) {
     System.out.println("no puedo leer la linea");
    } catch(Exception e){
      System.out.println("Ocurrió un problema en la escritura");
    }
	}

  /* nos da un ejemplo de un alebrije de la fabrica. */
  public static void archivoAlebrije(String archivo){

    System.out.println("--------------------\n"+
                       "El archivo contiene:\n"+
                       "--------------------\n");
    try {
      Reader r = new FileReader(archivo);
			BufferedReader lector = new BufferedReader(r);
      String linea = "";
      while(linea != null){
        linea = lector.readLine();
        int coma1 = linea.indexOf(",");
    		String cabeza = linea.substring(0, coma1);
    		int coma2 = linea.indexOf(",", coma1+1);
        String cuerpo = linea.substring(coma1+2, coma2);
    		int patas = Integer.valueOf(linea.substring(coma2+2));
    		Alebrije nuevo = new Alebrije(cabeza, cuerpo, patas);
        System.out.println(nuevo);
        lista.insertaFinal(nuevo);
        if(linea == null){
          lector.close();
          r.close();
        }
      }
    } catch(FileNotFoundException fnfe){
      System.out.println("No se encontró el archivo");
    } catch(NullPointerException npe) {
      System.out.println("");
    } catch(IOException e) {
     System.out.println("no puedo leer la linea");
    } catch(Exception e){
      System.out.println("Ocurrió un problema en la lectura");
    }
  }

  /** dependeiendo de la fabrica leera el archivo del alebrije. */
  private static void queEligio(){

    System.out.println("\nPara leer el catalogo de la 'Fabrica' elige:\n");

    switch (darNumero("------------------------------------\n"+
                      "si elegiste la 'Fabrica 1' pulsa '1'\n"+
                      "si elegiste la 'Fabrica 2' pulsa '2'\n"+
                      "------------------------------------")) {
      case 1:
        archivoAlebrije("archivoAlebrije1.txt");
        break;
      case 2:
        archivoAlebrije("archivoAlebrije2.txt");
        break;
      default:
        System.out.println("Solo hay dos opciones :/");
        modificar();
        return;
    }
  }

  /** dependeiendo de la fabrica modificara el archivo del alebrije. */
  private static void queEligio2(){

    System.out.println("\nPara guardar en el archivo correcto elige:\n");

    switch (darNumero("------------------------------------\n"+
                      "si elegiste la 'Fabrica 1' pulsa '1'\n"+
                      "si elegiste la 'Fabrica 2' pulsa '2'\n"+
                      "------------------------------------")) {
      case 1:
        escribeAlebrije("archivoAlebrije1.txt", guarda);
        return;
      case 2:
        escribeAlebrije("archivoAlebrije2.txt", guarda);
        return;
      default:
        System.out.println("Solo hay dos opciones :/");
        modificar();
        return;
    }
  }

  /* nos da un ejemplo de un alebrije de la fabrica. */
  public static void leeAlebrije(String archivo){

    System.out.println("-------------------------------------\n"+
                       "Las caracteristicas del alebrije son:\n"+
                       "-------------------------------------\n");
    try {
      Reader r = new FileReader(archivo);
			BufferedReader lector = new BufferedReader(r);
      String linea = "";
      while(linea != null){
        linea = lector.readLine();
        System.out.println(linea);
        lector.close();
        r.close();
      }

    } catch(FileNotFoundException fnfe){
      System.out.println("No se encontró el archivo");
    } catch(NullPointerException npe) {
      System.out.println("el archivo es nulo");
    } catch(IOException e) {
     System.out.println("");
    } catch(Exception e){
      System.out.println("Ocurrió un problema en la lectura");
    }
  }

  /* Muestra el menu principal al usuario. */
  public static void principal(){

    switch(darNumero("--------------------------------\n"+
                     "+Bienvenido al Menu de Fabricas+\n"+
                     "elige una de las siguientes opciones:\n"+
                     "--------------------------------\n"+
                     "0: Salir del programa\n"+
                     "1: Fabrica de alebrijes1\n"+
                     "2: Fabrica de alebrijes2\n"+
                     "--------------------------------")){
      case 0:
        System.out.println("saliste del programa :)");
        return;
      case 1:
        System.out.println("------------------------------"+
                           "\ncatalogo de la fabrica 1:\n");
        catalogo("catalogo1.txt");//leemos el catalogo.
        System.out.println("El tamaño de la lista es: "+lista1.tamanio()+"\n");
        secundario();
        return;
      case 2:
        System.out.println("------------------------------"+
                           "\ncatalogo de la fabrica 2:\n");
        catalogo("catalogo2.txt");//leemos el catalogo.
        System.out.println("El tamaño de la lista es: "+lista1.tamanio()+"\n");
        secundario();
        return;
      default:
        System.out.println("\tEntrada invalida\t\n"+
                           "\tvuelve intentarlo");
        principal();
      break;
    }
  }

  /* Muestra el menu secundario al usuario. */
  public static void secundario(){
    do{
      switch(darNumero("-------------------\n"+
                       "¿que quieres hacer?\n"+
                       "-------------------\n"+
                       "0: regresar al menu anterior\n"+
                       "1: salir del programa\n"+
                       "2: agregar un alebrije\n"+
                       "3: eliminar un alebrije\n"+
                       "4: modificar un alebrije y cargar un archivo existente\n"+//cargamos el archivo y lo podemos modificar.
                       "5: agregar, modificar o eliminar animales del catalogo\n"+
                       "------------------------------------------------------")){
        case 0:
          lista.limpia();
          lista1.limpia();
          principal();
          return;
        case 1:
          System.out.println("saliste del programa :)");
          return;
        case 2:
          agregar();
          break;
        case 3:
          eliminar();
          break;
        case 4:
          System.out.println("modificar");
          modificar();
          queEligio2();
        break;
        case 5:
          System.out.println("menu para los animales");
          utilizaCatalogo();
          break;
        default:
          System.out.println("\tEntrada invalida\t\n"+
                             "\tvuelve intentarlo");
          secundario();
        break;
      }
      System.out.println("\n¿quieres salir del programa?: true/false");
    }while(salir() != true);

  }

  /* para agregar, modificar o eliminar algo del catalogo */
  public static void utilizaCatalogo(){

    switch(darNumero("-------------------\n"+
                     "¿que quieres hacer?\n"+
                     "-------------------\n"+
                     "0: regresar al menu anterior\n"+
                     "1: salir del programa\n"+
                     "2: agregar un animal\n"+
                     "3: modificar un animal\n"+
                     "4: eliminar un animal\n"+
                     "---------------------")){
      case 0:
        secundario();
        return;
      case 1:
        System.out.println("saliste del programa :)");
        return;
      case 2:
        System.out.println("agrega");
        agregaAnimal();
        return;
      case 3:
        System.out.println("modifica");
        modificaAnimal();
        return;
      case 4:
        System.out.println("elimina");
        eliminaAnimal();
        return;
      default:
        System.out.println("\tEntrada invalida\t\n"+
                           "\tvuelve intentarlo");
        utilizaCatalogo();
      break;
    }
  }

  /* Muestra el menu para agregar un animal. */
  public static void agregaAnimal(){

    Animales animal = new Animales("","","","",0);

    lista1.muestra();

    switch(darNumero("-----------------------\n"+
                     "¿donde quieres agregar?\n"+
                     "-----------------------\n"+
                     "0: regresar al menu anterior\n"+
                     "1: salir del programa\n"+
                     "2: agregar en la primera posicion\n"+
                     "3: agregar en una posición en especifica\n"+
                     "4: agregar en la ultima posicion")){
      case 0:
        utilizaCatalogo();//
        return;
      case 1:
        System.out.println("saliste del programa :)");
        return;
      case 2:
        System.out.println("\t----------------------------------\n"+
                           "\tvas agregar en la primera posicion\n"+
                           "\t----------------------------------");
        System.out.println("Crea un Animal\n"+
                           "¿de que animal quieres la cabeza?");
        String cabeza1 = sc.next();
        animal.setCabeza(cabeza1);

        System.out.println("¿de que animal quieres su cuerpo?");
        String cuerpo1 = sc.next();
        animal.setCuerpo(cuerpo1);

        System.out.println("¿de que color quieres al animal?");
        String color1 = sc.next();
        animal.setColor(color1);

        System.out.println("¿cuantas patas quieres que tenga?");
        verificaNumero();
        int nPatas1 = sc.nextInt();
        animal.setPatas(nPatas1);
        lista1.insertaPrimero(animal);
        lista1.muestra();
        guarda2 = animal;
        return;
      case 3:
        System.out.println("\t----------------------------------------------\n"+
                           "\tsi la lista es vacia agrega en la posicion '0'\n"+
                           "\t¿en que posicion quieres agregar?\n"+
                           "\t----------------------------------");
        verificaNumero();
        int posicion = sc.nextInt();
        System.out.println("\t----------------------------------\n"+
                           "\tvas agregar en la posicion: "+posicion+
                           "\n\t----------------------------------");

        System.out.println("Crea un Animal\n"+
                           "¿de que animal quieres la cabeza?");
        String cabeza2 = sc.next();
        animal.setCabeza(cabeza2);

        System.out.println("¿de que animal quieres su cuerpo?");
        String cuerpo2 = sc.next();
        animal.setCuerpo(cuerpo2);

        System.out.println("¿de que color quieres al animal?");
        String color2 = sc.next();
        animal.setColor(color2);

        System.out.println("¿cuantas patas quieres que tenga?");
        verificaNumero();
        int nPatas2 = sc.nextInt();
        animal.setPatas(nPatas2);
        System.out.println("\n¿se puede insertar un elemento en la posicion "+posicion+"?:\t"+
                           lista1.inserta(animal, posicion));
        lista1.muestra();
        return;
      case 4:
        System.out.println("\t----------------------------------\n"+
                           "\tvas agregar en la ultima posicion\n"+
                           "\t----------------------------------");
        System.out.println("Crea un Animal\n"+
                           "¿de que animal quieres la cabeza?");
        String cabeza3 = sc.next();
        animal.setCabeza(cabeza3);

        System.out.println("¿de que animal quieres su cuerpo?");
        String cuerpo3 = sc.next();
        animal.setCuerpo(cuerpo3);

        System.out.println("¿de que color quieres al animal?");
        String color3 = sc.next();
        animal.setColor(color3);

        System.out.println("¿cuantas patas quieres que tenga?");
        verificaNumero();
        int nPatas3 = sc.nextInt();
        animal.setPatas(nPatas3);
        lista1.insertaFinal(animal);
        lista1.muestra();
        return;
      default:
      System.out.println("\tEntrada invalida\t\n"+
                         "\tvuelve intentarlo");
      agregar();
        break;
    }
  }

  /* Muestra el menu para modificar un animal. */
  public static void modificaAnimal(){

    if(lista1.tamanio() == 0){
      System.out.println("\n---------------------------------------------------\n"+
                         "la lista es vacia no puedes modificar ningun animal\n"+
                         "-----------------------------------------------------");
      return;
    }else{
      lista1.muestra();
      System.out.println("¿que animal quieres modificar?\n"+
                         "Dame su posicion:");

      verificaNumero();
      int modifica = sc.nextInt();
      System.out.println("El elemento en el indice "+modifica+" de la lista es: \n\n"+
                         lista1.obten(modifica));
      if(lista1.obten(modifica) == null){
        System.out.println("-----------------------------------\n"+
                           "no puedes modificar ningun animal\n"+
                           "-----------------------------------");
        return;
      }
      switch(modifica){
        case 0:
          switch(darNumero("-----------------------\n"+
                           "¿que quieres modificar?\n"+
                           "-----------------------\n"+
                           "0: regresar al menu anterior\n"+
                           "1: salir del programa\n"+
                           "2: modificar la cabeza\n"+
                           "3: modificar el cuerpo\n"+
                           "4: modificar el color\n"+
                           "5: modificar la cantidad de patas")){
            case 0:
              utilizaCatalogo();
              return;
            case 1:
              System.out.println("saliste del programa :)");
              return;
            case 2:
              System.out.println("modificar cabeza");
              System.out.println("¿de que animal quieres la cabeza?");
              String cabeza4 = sc.next();
              guarda1.setCabeza(cabeza4);
              System.out.println("el animal quedo ahora asi:\n");
              System.out.println(guarda1);
              System.out.println("\ny la lista quedo asi:\n");
              lista1.muestra();
              return;
            case 3:
              System.out.println("modificar cuerpo");
              System.out.println("¿de que animal quieres el cuerpo?");
              String cuerpo4 = sc.next();
              guarda1.setCuerpo(cuerpo4);
              System.out.println("el animal quedo ahora asi:\n");
              System.out.println(guarda1);
              System.out.println("\ny la lista quedo asi:\n");
              lista1.muestra();
              return;
            case 4:
              System.out.println("modificar color");
              System.out.println("¿de que color quieres elanimal?");
              String color4 = sc.next();
              guarda1.setColor(color4);
              System.out.println("el animal quedo ahora asi:\n");
              System.out.println(guarda1);
              System.out.println("\ny la lista quedo asi:\n");
              lista1.muestra();
              return;
            case 5:
              System.out.println("modificar cantidad de patas");
              System.out.println("¿cuantas patas quieres para el animal?");
              int patas4 = sc.nextInt();
              guarda1.setPatas(patas4);
              System.out.println("el animal quedo ahora asi:\n");
              System.out.println(guarda1);
              System.out.println("y la lista quedo asi:");
              lista1.muestra();
              return;
            default:
              System.out.println("\tEntrada invalida\t\n"+
                                 "\tvuelve intentarlo");
              modificaAnimal();
            break;
          }
          return;
        case 1:

          switch(darNumero("-----------------------\n"+
                           "¿que quieres modificar?\n"+
                           "-----------------------\n"+
                           "0: regresar al menu anterior\n"+
                           "1: salir del programa\n"+
                           "2: modificar la cabeza\n"+
                           "3: modificar el cuerpo\n"+
                           "4: modificar el color\n"+
                           "5: modificar la cantidad de patas")){
            case 0:
              utilizaCatalogo();
              return;
            case 1:
              System.out.println("saliste del programa :)");
              return;
            case 2:
              System.out.println("modificar cabeza");
              System.out.println("¿de que animal quieres la cabeza?");
              String cabeza4 = sc.next();
              guarda2.setCabeza(cabeza4);
              System.out.println("el animal quedo ahora asi:\n");
              System.out.println(guarda2);
              System.out.println("\ny la lista quedo asi:\n");
              lista1.muestra();
              return;
            case 3:
              System.out.println("modificar cuerpo");
              System.out.println("¿de que animal quieres el cuerpo?");
              String cuerpo4 = sc.next();
              guarda2.setCuerpo(cuerpo4);
              System.out.println("el animal quedo ahora asi:\n");
              System.out.println(guarda2);
              System.out.println("\ny la lista quedo asi:\n");
              lista1.muestra();
              return;
            case 4:
              System.out.println("modificar color");
              System.out.println("¿de que color quieres elanimal?");
              String color4 = sc.next();
              guarda2.setColor(color4);
              System.out.println("el animal quedo ahora asi:\n");
              System.out.println(guarda2);
              System.out.println("\ny la lista quedo asi:\n");
              lista1.muestra();
              return;
            case 5:
              System.out.println("modificar cantidad de patas");
              System.out.println("¿cuantas patas quieres para el animal?");
              verificaNumero();
              int patas4 = sc.nextInt();
              guarda2.setPatas(patas4);
              System.out.println("el animal quedo ahora asi:\n");
              System.out.println(guarda2);
              System.out.println("y la lista quedo asi:");
              lista1.muestra();
              return;
            default:
              System.out.println("\tEntrada invalida\t\n"+
                                 "\tvuelve intentarlo");
              modificaAnimal();
            break;
          }
          return;
        case 2:

          switch(darNumero("-----------------------\n"+
                           "¿que quieres modificar?\n"+
                           "-----------------------\n"+
                           "0: regresar al menu anterior\n"+
                           "1: salir del programa\n"+
                           "2: modificar la cabeza\n"+
                           "3: modificar el cuerpo\n"+
                           "4: modificar el color\n"+
                           "5: modificar la cantidad de patas")){
            case 0:
              utilizaCatalogo();
              return;
            case 1:
              System.out.println("saliste del programa :)");
              return;
            case 2:
              System.out.println("modificar cabeza");
              System.out.println("¿de que animal quieres la cabeza?");
              String cabeza4 = sc.next();
              guarda3.setCabeza(cabeza4);
              System.out.println("el animal quedo ahora asi:\n");
              System.out.println(guarda3);
              System.out.println("\ny la lista quedo asi:\n");
              lista1.muestra();
              return;
            case 3:
              System.out.println("modificar cuerpo");
              System.out.println("¿de que animal quieres el cuerpo?");
              String cuerpo4 = sc.next();
              guarda3.setCuerpo(cuerpo4);
              System.out.println("el animal quedo ahora asi:\n");
              System.out.println(guarda3);
              System.out.println("\ny la lista quedo asi:\n");
              lista1.muestra();
              return;
            case 4:
              System.out.println("modificar color");
              System.out.println("¿de que color quieres elanimal?");
              String color4 = sc.next();
              guarda3.setColor(color4);
              System.out.println("el animal quedo ahora asi:\n");
              System.out.println(guarda3);
              System.out.println("\ny la lista quedo asi:\n");
              lista1.muestra();
              return;
            case 5:
              System.out.println("modificar cantidad de patas");
              System.out.println("¿cuantas patas quieres para el animal?");
              verificaNumero();
              int patas4 = sc.nextInt();
              guarda3.setPatas(patas4);
              System.out.println("el animal quedo ahora asi:\n");
              System.out.println(guarda3);
              System.out.println("y la lista quedo asi:");
              lista1.muestra();
              return;
            default:
              System.out.println("\tEntrada invalida\t\n"+
                                 "\tvuelve intentarlo");
              modificaAnimal();
            break;
          }
          return;
        case 3:
          switch(darNumero("-----------------------\n"+
                           "¿que quieres modificar?\n"+
                           "-----------------------\n"+
                           "0: regresar al menu anterior\n"+
                           "1: salir del programa\n"+
                           "2: modificar la cabeza\n"+
                           "3: modificar el cuerpo\n"+
                           "4: modificar el color\n"+
                           "5: modificar la cantidad de patas")){
            case 0:
              utilizaCatalogo();
              return;
            case 1:
              System.out.println("saliste del programa :)");
              return;
            case 2:
              System.out.println("modificar cabeza");
              System.out.println("¿de que animal quieres la cabeza?");
              String cabeza4 = sc.next();
              guarda4.setCabeza(cabeza4);
              System.out.println("el animal quedo ahora asi:\n");
              System.out.println(guarda4);
              System.out.println("\ny la lista quedo asi:\n");
              lista1.muestra();
              return;
            case 3:
              System.out.println("modificar cuerpo");
              System.out.println("¿de que animal quieres el cuerpo?");
              String cuerpo4 = sc.next();
              guarda4.setCuerpo(cuerpo4);
              System.out.println("el animal quedo ahora asi:\n");
              System.out.println(guarda4);
              System.out.println("\ny la lista quedo asi:\n");
              lista1.muestra();
              return;
            case 4:
              System.out.println("modificar color");
              System.out.println("¿de que color quieres elanimal?");
              String color4 = sc.next();
              guarda4.setColor(color4);
              System.out.println("el animal quedo ahora asi:\n");
              System.out.println(guarda4);
              System.out.println("\ny la lista quedo asi:\n");
              lista1.muestra();
              return;
            case 5:
              System.out.println("modificar cantidad de patas");
              System.out.println("¿cuantas patas quieres para el animal?");
              verificaNumero();
              int patas4 = sc.nextInt();
              guarda4.setPatas(patas4);
              System.out.println("el animal quedo ahora asi:\n");
              System.out.println(guarda4);
              System.out.println("y la lista quedo asi:");
              lista1.muestra();
              return;
            default:
              System.out.println("\tEntrada invalida\t\n"+
                                 "\tvuelve intentarlo");
              modificaAnimal();
            break;
          }
          return;
        case 4:
          switch(darNumero("-----------------------\n"+
                           "¿que quieres modificar?\n"+
                           "-----------------------\n"+
                           "0: regresar al menu anterior\n"+
                           "1: salir del programa\n"+
                           "2: modificar la cabeza\n"+
                           "3: modificar el cuerpo\n"+
                           "4: modificar el color\n"+
                           "5: modificar la cantidad de patas")){
            case 0:
              secundario();
              return;
            case 1:
              System.out.println("saliste del programa :)");
              return;
            case 2:
              System.out.println("modificar cabeza");
              System.out.println("¿de que animal quieres la cabeza?");
              String cabeza4 = sc.next();
              guarda5.setCabeza(cabeza4);
              System.out.println("el animal quedo ahora asi:\n");
              System.out.println(guarda5);
              System.out.println("\ny la lista quedo asi:\n");
              lista1.muestra();
              return;
            case 3:
              System.out.println("modificar cuerpo");
              System.out.println("¿de que animal quieres el cuerpo?");
              String cuerpo4 = sc.next();
              guarda5.setCuerpo(cuerpo4);
              System.out.println("el animal quedo ahora asi:\n");
              System.out.println(guarda5);
              System.out.println("\ny la lista quedo asi:\n");
              lista1.muestra();
              return;
            case 4:
              System.out.println("modificar color");
              System.out.println("¿de que color quieres elanimal?");
              String color4 = sc.next();
              guarda5.setColor(color4);
              System.out.println("el animal quedo ahora asi:\n");
              System.out.println(guarda5);
              System.out.println("\ny la lista quedo asi:\n");
              lista1.muestra();
              return;
            case 5:
              System.out.println("modificar cantidad de patas");
              System.out.println("¿cuantas patas quieres para el animal?");
              verificaNumero();
              int patas4 = sc.nextInt();
              guarda5.setPatas(patas4);
              System.out.println("el animal quedo ahora asi:\n");
              System.out.println(guarda5);
              System.out.println("y la lista quedo asi:");
              lista1.muestra();
              return;
            default:
              System.out.println("\tEntrada invalida\t\n"+
                                 "\tvuelve intentarlo");
              modificaAnimal();
            break;
          }
          return;
      }
    }

  }

  /* Muestra el menu para eliminar un animal. */
  public static void eliminaAnimal(){
    System.out.println("-----------------------\n"+
                       "¿cual quieres eliminar?\n"+
                       "-----------------------\n"+
                       "0: regresar al menu anterior\n"+
                       "1: salir del programa\n"+
                       "2: eliminar el primero\n"+
                       "3: eliminar uno en especifico");
    verificaNumero();
    lista1.muestra();
    if(lista1.estaVacia() == true){
      System.out.println("por lo tanto no puedes eliminar nada :(");
      return;
    }
    verificaNumero();
    int on = sc.nextInt();
    switch(on){
      case 0:
        utilizaCatalogo();
        return;
      case 1:
        System.out.println("saliste del programa :)");
        return;
      case 2:
        System.out.println("\t------------------------------\n"+
                           "\teliminaste la primera posicion\n"+
                           "\tpor lo que tu lista quedo así:\n"+
                           "\t------------------------------");
        lista1.eliminaPrimero();
        lista1.muestra();
        return;
      case 3:
        System.out.println("eliminar uno especifico");
        System.out.println("\t-----------------------\n"+
                           "\t¿cual quieres eliminar?\n"+
                           "\t-----------------------");
        verificaNumero();
        int seElimina = sc.nextInt();
        System.out.println("\t------------------------------\n"+
                           "\teliminaste la posicion: "+seElimina+
                           "\n\tpor lo que tu lista quedo así:\n"+
                           "\t------------------------------");
        lista1.elimina(seElimina);
        lista1.muestra();
        return;
      default:
        System.out.println("\tEntrada invalida\t\n"+
                           "\tvuelve intentarlo");
        eliminaAnimal();
        break;
    }

  }
  /////////////////////////////////////////////////7/////////7/////
  /* Muestra el menu para agregar un alebrije. */
  public static void agregar(){

    Alebrije alebrije = new Alebrije("","",0);

    lista.muestra();
    switch(darNumero("-----------------------\n"+
                     "¿donde quieres agregar?\n"+
                     "-----------------------\n"+
                     "0: regresar al menu anterior\n"+
                     "1: salir del programa\n"+
                     "2: agregar en la primera posicion\n"+
                     "3: agregar en una posición en especifica\n"+
                     "4: agregar en la ultima posicion")){
      case 0:
        secundario();
        return;
      case 1:
        System.out.println("saliste del programa :)");
        return;
      case 2:
        System.out.println("\t----------------------------------\n"+
                           "\tvas agregar en la primera posicion\n"+
                           "\t----------------------------------");
        System.out.println("Crea un Alebrije\n"+
                           "¿de que animal quieres la cabeza?");
        String cabeza1 = sc.next();
        alebrije.setCabeza(cabeza1);

        System.out.println("¿de que animal quieres su cuerpo?");
        String cuerpo1 = sc.next();
        alebrije.setCuerpo(cuerpo1);

        System.out.println("¿cuantas patas quieres que tenga?");
        verificaNumero();
        int nPatas1 = sc.nextInt();
        alebrije.setPatas(nPatas1);
        lista.insertaPrimero(alebrije);
        lista.muestra();
        guarda = alebrije;
        return;
      case 3:
        System.out.println("\t----------------------------------------------\n"+
                           "\tsi la lista es vacia agrega en la posicion '0'\n"+
                           "\t¿en que posicion quieres agregar?\n"+
                           "\t----------------------------------");
        int posicion = sc.nextInt();
        System.out.println("\t----------------------------------\n"+
                           "\tvas agregar en la posicion: "+posicion+
                           "\n\t----------------------------------");

        System.out.println("Crea un Alebrije\n"+
                           "¿de que animal quieres la cabeza?");
        String cabeza2 = sc.next();
        alebrije.setCabeza(cabeza2);

        System.out.println("¿de que animal quieres su cuerpo?");
        String cuerpo2 = sc.next();
        alebrije.setCuerpo(cuerpo2);

        System.out.println("¿cuantas patas quieres que tenga?");
        verificaNumero();
        int nPatas2 = sc.nextInt();
        alebrije.setPatas(nPatas2);
        System.out.println("\n¿se puede insertar un elemento en la posicion "+posicion+"?:\t"+
                           lista.inserta(alebrije, posicion));
        lista.muestra();
        return;
      case 4:
        System.out.println("\t----------------------------------\n"+
                           "\tvas agregar en la ultima posicion\n"+
                           "\t----------------------------------");
        System.out.println("Crea un Alebrije\n"+
                           "¿de que animal quieres la cabeza?");
        String cabeza3 = sc.next();
        alebrije.setCabeza(cabeza3);

        System.out.println("¿de que animal quieres su cuerpo?");
        String cuerpo3 = sc.next();
        alebrije.setCuerpo(cuerpo3);

        System.out.println("¿cuantas patas quieres que tenga?");
        verificaNumero();
        int nPatas3 = sc.nextInt();
        alebrije.setPatas(nPatas3);
        lista.insertaFinal(alebrije);
        lista.muestra();
        return;
      default:
      System.out.println("\tEntrada invalida\t\n"+
                         "\tvuelve intentarlo");
      agregar();
        break;
    }
  }

  /* Muestra el menu para modificar un alebrije. */
  public static void modificar(){

    queEligio();

    System.out.println("El tamaño de la lista es: "+lista.tamanio()+"\n");
    if(lista.tamanio() == 0){
      System.out.println("\n---------------------------------------------------\n"+
                         "la lista es vacia no puedes modificar ningun alebrije\n"+
                         "-----------------------------------------------------");
      return;
    }else{

      System.out.println("¿que alebrije quieres modificar?\n"+
                         "Dame su posicion:");

      verificaNumero();
      int modifica = sc.nextInt();
      System.out.println("El elemento en el indice "+modifica+" de la lista es: \n\n"+
                         lista.obten(modifica));
      if(lista.obten(modifica) == null){
        System.out.println("-----------------------------------\n"+
                           "no puedes modificar ningun alebrije\n"+
                           "-----------------------------------");
                           lista.limpia();
        return;
      }
    }
    System.out.println("\tmodificando la cabeza\n"+
                       "¿de que animal quieres su cabeza?");
    String cabeza4 = sc.next();
    guarda.setCabeza(cabeza4);

    System.out.println("\tmodificando el cuerpo\n"+
                       "¿de que animal quieres su cuerpo?");
    String cuerpo4 = sc.next();
    guarda.setCuerpo(cuerpo4);

    System.out.println("\tmodificando la cantidad de patas\n"+
                       "¿cuantas patas quieres para el animal?");
    verificaNumero();
    int patas4 = sc.nextInt();
    guarda.setPatas(patas4);
    //queEligio2();
    return;
  }

  /* Muestra el menu para eliminar un alebrije. */
  public static void eliminar(){
    lista.muestra();
    if(lista.estaVacia() == true){
      System.out.println("por lo tanto no puedes eliminar nada :(");
      return;
    }
    switch(darNumero("-----------------------\n"+
                       "¿cual quieres eliminar?\n"+
                       "-----------------------\n"+
                       "0: regresar al menu anterior\n"+
                       "1: salir del programa\n"+
                       "2: eliminar el primero\n"+
                       "3: eliminar uno en especifico")){
      case 0:
        secundario();
        return;
      case 1:
        System.out.println("saliste del programa :)");
        return;
      case 2:
        System.out.println("\t------------------------------\n"+
                           "\teliminaste la primera posicion\n"+
                           "\tpor lo que tu lista quedo así:\n"+
                           "\t------------------------------");
        lista.eliminaPrimero();
        lista.muestra();
        return;
      case 3:
        System.out.println("eliminar uno especifico");
        System.out.println("\t-----------------------\n"+
                           "\t¿cual quieres eliminar?\n"+
                           "\t-----------------------");
        int seElimina = sc.nextInt();
        System.out.println("\t------------------------------\n"+
                           "\teliminaste la posicion: "+seElimina+
                           "\n\tpor lo que tu lista quedo así:\n"+
                           "\t------------------------------");
        lista.elimina(seElimina);
        lista.muestra();
        return;
      default:
        System.out.println("\tEntrada invalida\t\n"+
                           "\tvuelve intentarlo");
        eliminar();
        break;
    }

  }
  //main
  public static void main(String[] args) {

    principal();

  }
}
