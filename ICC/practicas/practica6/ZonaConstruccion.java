import java.util.Scanner;
/**
 * Clase que simula una zona con cierto número de construcciones.
 * @author Pedro Ulises Cervantes González
 * @version 1.0
 */
public class ZonaConstruccion{

    /** Las construcciones de la zona*/
    protected Construccion[] construcciones;

    /**
     * Crea una nueva zona de construcción.
     *
     * @param nCons El número de construcciones que habrá.
     */
    public ZonaConstruccion(int nCons){
	construcciones=new Construccion[nCons];
	for(int i=0;i<nCons;i++){
	    construcciones[i]=new Construccion(this);
	}
    }

    /**
     * Devuelve las construcciones
     *
     * @return Las construcciones
     */
    public Construccion[] getConstrucciones(){
	return construcciones;
    }

    @Override
    public String toString(){
	String[][] consArray=new String[construcciones.length][];
	int max=0;
	for(int i=0;i<construcciones.length;i++){
	    consArray[i]=construcciones[i].toStringArray();
	    if(max<consArray[i].length) max=consArray[i].length;
	}
	String cad="";
	for(int i=max-1;i>=0;i--){
	    for(int j=0;j<consArray.length;j++){
		if(i>=consArray[j].length) cad+=Construccion.cielo();
		else cad+=consArray[j][i];
		if(j<consArray.length-1)
		    if(i>0) cad+="  ";
		    else cad+="TT";
		else if(i>0) cad+="\n";
	    }
	}
	return cad;
    }

    /* El escaner */
    static Scanner sc = new Scanner(System.in);

    /* verifica si la entrada es correcta */
    public static void verificaNumero(){
      while(!sc.hasNextInt()){
        System.out.println("\tEntrada invalida\t");
        sc.next();
      }
    }
    //main
    public static void main(String[] args) {
      ZonaConstruccion z = new ZonaConstruccion(1);

      System.out.println("--------------------------------\n"+
                         "Bienvenido al Menu de los trabajadores\n"+
                         "¿quien quieres que trabaje?\n"+
                         "elige una de las siguientes opciones:\n"+
                         "--------------------------------\n"+
                         "0: Salir del programa\n"+
                         "1: Constructor\n"+
                         "2: Demoledor\n"+
                         "3: Remodelador\n"+
                         "--------------------------------");
      System.out.println();
      verificaNumero();
      int entrada = sc.nextInt();
      switch(entrada){
        case 0:
          System.out.println("saliste del programa :)");
          return;
        case 1:
          System.out.println("Constructor");
          Constructor c = new Constructor();
          c.trabaja(z);
          return;
        case 2:
          System.out.println("Demoledor");
          Demoledor d = new Demoledor();
          d.trabaja(z);
          return;
        case 3:
          System.out.println("Remodelador");
          Remodelador r = new Remodelador();
          r.trabaja(z);
          return;
        default:
          System.out.println("\tEntrada invalida\t\n"+
                             "\tvuelve intentarlo");
        break;
      }
    }

}
