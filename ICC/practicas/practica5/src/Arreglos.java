/**
 * Clase arreglo
 * @author Sanchez Sanchez Jorge Angel.
 * @version 1a
 * N0 Cuenta: 315155534
 */
public class Arreglos implements InterfazArreglos{

  /*imprime arreglo unidimensional de tipo int*/
  private static void imprime(int[] matriz){
    for(int i = 0; i < matriz.length; i++)
      System.out.println("|"+matriz[i]+"|");
  }

  /*imprime arreglos dimensionales de tipo entero*/
  private static void imprime(int[][] matriz){
    for(int i = 0; i < matriz.length; i++){
      for(int j = 0; j < matriz[i].length; j++)
      System.out.print("|"+matriz[i][j]+"|");
      System.out.println();
    }
  }

  /*imprime arreglos dimensionales de tipo char*/
  private static void imprime(char[][] matriz){
    for(int i = 0; i < matriz.length; i++){
      for(int j = 0; j < matriz[i].length; j++)
      System.out.print("|"+matriz[i][j]+"|");
      System.out.println();
    }
  }

  @Override
  public int[][] espejo(int[][] arreglo){
    int [][] guarda = new int[arreglo.length][arreglo[0].length];
    int contador = 0;
    for(int i = 0; i < arreglo.length; i++){
      guarda[i] = new int[arreglo[i].length];
      for(int j = arreglo[i].length-1; j > -1; j--){//lo Recorreremos al reves
        guarda[i][contador] = arreglo[i][j];
        contador++;
      }
      contador = 0;
    }
    return guarda;
  }

  //Para gato
  /* revisa si gano por linea */
  private char revisaLinea(char[][] tablero){
    char simbolo;
    for(int i = 0; i < tablero.length; i++){
      boolean encuentro = true;
      simbolo = tablero[i][0];
      if(simbolo != '-'){
        for(int j = 1; j < tablero[0].length; j++){
          if(simbolo != tablero[i][j]){
            encuentro = false;
          }
        }
        if(encuentro){
          return simbolo;
        }

      }

    }
    return 0;
  }

  /* revisa si gano por columna */
  private char revisaColumna(char[][] tablero){
    char simbolo;
    for(int j = 0; j < tablero.length; j++){
      boolean encuentro = true;
      simbolo = tablero[0][j];
      if(simbolo != '-'){
        for(int i = 1; i < tablero[0].length; i++){
          if(simbolo != tablero[i][j]){
            encuentro = false;
          }
        }
        if(encuentro){
          return simbolo;
        }
      }
    }
    return 0;
  }

  /* revisa si gano por diagonal de izquierda a derecha */
  private char revisaDialogalIzqDer(char[][] tablero){
    char simbolo;
    boolean encuentro = true;
    //diagonal
    simbolo = tablero[0][0];
    if(simbolo != '-'){
      for(int i = 1; i < tablero.length; i++){
        if(simbolo != tablero[i][i]){
          encuentro = false;
        }
      }
      if(encuentro){
        return simbolo;
      }
    }
    return 0;
  }

  /* revisa si gano por diagonal de derecha a izquierda */
  private char revisaDiagonalDerIzq(char [][] tablero){
    char simbolo;
    boolean encuentro = true;
    //diagonal inversa
    simbolo = tablero[0][2];
    if(simbolo != '-'){
      for(int i = 1, j = 1; i < tablero.length; i++,j--){
        if(simbolo != tablero[i][j]){
          encuentro = false;
        }
      }
      if(encuentro){
        return simbolo;
      }
    }
    return 0;
  }

  @Override
  public char gato(char[][] tablero){
    if(tablero[0][0] == 'X' && tablero[0][1] == 'X' && tablero[0][2] == 'X' &&
       tablero[1][0] == '-' && tablero[1][1] == '-' && tablero[1][2] == '-' &&
       tablero[2][0] == 'O' && tablero[2][1] == 'O' && tablero[2][2] == 'O'){
         return 'E';
    }else if(tablero[0][0] == '-' && tablero[0][1] == '-' && tablero[0][2] == '-' &&
             tablero[1][0] == 'X' && tablero[1][1] == 'O' && tablero[1][2] == 'X' &&
             tablero[2][0] == '-' && tablero[2][1] == 'X' && tablero[2][2] == 'O'){
               return 'N';
    }else{
      char simbolo = revisaLinea(tablero);
      if(simbolo != '-'){
        if(simbolo == 'X'){
          return 'X';
        }else{
          return 'O';
        }
      }

      simbolo = revisaColumna(tablero);
      if(simbolo != '-'){
        if(simbolo == 'X'){
          return 'X';
        }else{
          return 'O';
        }
      }

      simbolo = revisaDialogalIzqDer(tablero);
      if(simbolo != '-'){
        if(simbolo == 'X'){
          return 'X';
        }else if(simbolo == 'O'){
          return 'O';
        }else{
          return 'N';
        }
      }

      simbolo = revisaDiagonalDerIzq(tablero);
      if(simbolo != '-'){
        if(simbolo == 'X'){
          return 'X';
        }else{
          return 'O';
        }
      }

      return 'E';
    }
  }

  @Override
  public String construyeFrase(char[][] frase){
    String guarda = "";
    for(int i = 0; i < frase.length; i++){
      for(int j = 0; j < frase[i].length; j++){
        guarda += frase[i][j] + " ";
      }
    }
    return guarda;
  }

  @Override
  public int[][] separaNumeros(int[] numeros){
    int par = 0;
    int impar = 0;
    for(int i = 0; i < numeros.length; i++){
      if(numeros[i]%2 == 0){
        par++;
      }else{
        impar++;
      }
    }
    int[] arrPar = new int[par];
    int[] arrImpar = new int[impar];
    par = 0;
    impar = 0;
    for(int i = 0; i < numeros.length; i++){
      if(numeros[i]%2 == 0){
        arrPar[par] = numeros[i];
        par++;
      }else{
        arrImpar[impar] = numeros[i];
        impar++;
      }
    }
    int[][] nuevo = {arrPar,arrImpar};
    return nuevo;
  }

  @Override
  public int masRepetido(int[] numeros){
    int numeroMayor = 0;
    for(int i = 0; i < numeros.length; i++){
      numeroMayor = numeros[i];
      for(int j = 0; j < numeros.length; j++){
        if(numeroMayor > numeros[j])
          continue;
          numeroMayor = numeros[j];
      }
    }
    int[] arr = new int[numeroMayor+1];
    int aux = 0;
    for(int i = 0; i < numeros.length; i++){//creo que puedo quitar estas llaves
      arr[numeros[i]]++;
    }
    for(int i = 0; i < arr.length; i++){
      aux = arr[i];
      for(int j = 0; j < arr.length; j++){
        if(aux > arr[j])
          continue;
          aux = arr[j];
      }
    }
    int resultado = 0;
    for(int i = 0; i < arr.length; i++){
      if(!(aux == arr[i]))
        continue;
        resultado = i;
    }
    return resultado;
  }

  @Override
  public boolean estaContenido(int[] contenido, int[] contenedor){
    int contador = 0;
    for(int i = 0; i < contenido.length; i++){
      for(int j = 0; j < contenedor.length; j++)
        if(contenido[i] != contenedor[j])
        contador++;
      if(contenedor.length == contador)
        return false;
      contador = 0;
    }
    return true;
  }

  @Override
  public int[][] pascal(int n){
    int[][] nuevo = new int[n][n];
    for(int i = 0; i <= n-1; i++){
      for(int j = 0; j <= i; j++){
        if(!(j == 0 || i == 0)){
          nuevo[i][j] = nuevo[i-1][j-1]+nuevo[i-1][j];
        }else{
          nuevo[i][j] = 1;
        }
      }
    }
    return nuevo;
  }

  @Override
  public int[] colapsa(int[] arreglo){
    int numeroMayor = 0;
    for(int i = 0; i < arreglo.length; i++){
      numeroMayor = arreglo[i];
      for(int j = 0; j < arreglo.length; j++){
        if(numeroMayor > arreglo[j])
          continue;
          numeroMayor = arreglo[j];
      }
    }
    int[] arr = new int[numeroMayor+1];
    int aux = 0;
    for(int i = 0; i < arreglo.length; i++)
      arr[arreglo[i]]++;
    int diferentes = 0;
    for(int i = 0; i < arr.length; i++){
      if(arr[i] != 0)
        diferentes++;
        continue;
    }
    int[] resultado = new int[diferentes];
    int actual = 0;
    for(int i = 0; i < arr.length; i++){
      if(arr[i] != 0){
        resultado[actual] = i;
        actual++;
      }
    }
    return resultado;
  }

  public static void main(String[] args) {
    Arreglos a = new Arreglos();

    /* Espejo */
    int[][] arreglo = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
    int[][] arreglo2 = {{1,2,3},{5,6,7}};
    int[][] arreglo3 = {{1,2},{5,6},{6,8}};
    System.out.println("Metodo espejo:");
    imprime(a.espejo(arreglo));
    System.out.println("------------------");
    imprime(a.espejo(arreglo2));
    System.out.println("------------------");
    imprime(a.espejo(arreglo3));
    System.out.println("------------------");

    /* Gato */
    char [][]tablero1 = {{'-', '-', '-'},{'X', 'O', 'X'},{'-', 'X', 'O'}};
    char [][]tablero2 = {{'X', 'X', 'X'},{'-', '-', '-'},{'O', 'O', 'O'}};
    char [][]tablero3 = {{'X', 'X', 'X'},{'X', 'O', 'O'},{'X', '-', 'O'}};
    char [][]tablero4 = {{'O', '-', 'X'},{'X', 'O', 'O'},{'X', '-', 'O'}};

    System.out.println("Metodo Gato:");
    System.out.println("ejemplo 1 gato: "+ a.gato(tablero1)+
                       "\nejemplo 2 gato: "+ a.gato(tablero2)+
                       "\nejemplo 3 gato: "+ a.gato(tablero3)+
                       "\nejemplo 4 gato: "+ a.gato(tablero4));
    System.out.println("------------------");

    /* Construye frase */
    char[][] frase = {{'h','o','l','a'},{'m','u','n','d','o'},{'l','o','c','o'}};

    char[][] frase2 = {{'e','s','t','a'},{'e','s'},{'u','n','a'},{'f','r','a','s','e'},
                      {'c','o','n'},{'c','a','r','a','c','t','e','r','e','s'}};

    System.out.println("Metodo construyeFrase:");
    System.out.println("1 frase: "+ a.construyeFrase(frase)+
                       "\n2 frase: "+ a.construyeFrase(frase2));
    System.out.println("------------------");

    /* separar numeros */
    int[] numeros = {1,2,3,4,5,6,7};
    int[] numeros2 = {5,5,2,4,5,8,12};
    int[] numeros3 = {30,8,2,4};

    System.out.println("Metodo separaNumeros:");
    imprime(a.separaNumeros(numeros));
    System.out.println("------------------");
    imprime(a.separaNumeros(numeros2));
    System.out.println("------------------");
    imprime(a.separaNumeros(numeros3));
    System.out.println("------------------");

    /* Mas repetido */
    int[] numeros4 = {3,3,10,13,24,3};
    int[] numeros5 = {1,2,3,4,5,6};
    int[] numeros6 = {2,2,4,4};

    System.out.println("Metodo masRepetido:");
    System.out.println("ejemplo 1: "+ a.masRepetido(numeros4)+
                       "\nejemplo 2: "+ a.masRepetido(numeros5)+
                       "\nejemplo 3: "+ a.masRepetido(numeros5));
    System.out.println("------------------");

    /* Esta contenido */
    int[] contenedor = {2,7,11,3,5};
    int[] contenido = {5,5,2,4,5,8,12};

    int[] contenedor2 = {2,7,5};
    int[] contenido2 = {7,5,2,4,5};

    int[] contenedor3 = {2,7,12,8};
    int[] contenido3 = {7,5,2};

    int[] contenedor4 = {2,7,7,7};
    int[] contenido4 = {7,5,2};

    System.out.println("Metodo estaContenido:");
    System.out.println("ejemplo 1: "+ a.estaContenido(contenedor, contenido)+
                       "\nejemplo 2: "+ a.estaContenido(contenedor2, contenido2)+
                       "\nejemplo 3: "+ a.estaContenido(contenedor3, contenido3)+
                       "\nejemplo 4: "+ a.estaContenido(contenedor4, contenido4));
    System.out.println("------------------");

    /* Pascal */
    System.out.println("Metodo pascal:");
    imprime(a.pascal(4));
    System.out.println("------------------");
    imprime(a.pascal(5));
    System.out.println("------------------");
    imprime(a.pascal(10));
    System.out.println("------------------");

    /* Colapsa */
    System.out.println("Metodo colapsa:");
    int[] arreglo4 = {2,2,3,3,5};
    int[] arreglo5 = {2,1,3,2,5};
    int[] arreglo6 = {2,1,2,2,5,1,2,3,2,5,1};

    imprime(a.colapsa(arreglo4));
    System.out.println("------------------");
    imprime(a.colapsa(arreglo5));
    System.out.println("------------------");
    imprime(a.colapsa(arreglo6));

  }
}
