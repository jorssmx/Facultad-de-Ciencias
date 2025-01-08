import java.util.Scanner;
public class Constructor extends Trabajador{

  /* El escaner */
  static Scanner sc = new Scanner(System.in);

  /* verifica si la entrada es correcta */
  static void verificaNumero(){
    while(!sc.hasNextInt()){
      System.out.println("\tEntrada invalida\t");
      sc.next();
    }
  }

  @Override
  public /*abstract*/ boolean puedeTrabajar(ZonaConstruccion zona){
    return true;//zona >= 0;
  }

  //@Override
  public void trabaja(ZonaConstruccion zona){
    System.out.println("--------------------------------\n"+
                         "¿que quieres que construya?\n"+
                         "--------------------------------\n"+
                         "0: Salir del programa\n"+
                         "1: Casa\n"+
                         "2: Edificio\n"+
                         "--------------------------------");
      verificaNumero();
      int teclado = sc.nextInt();
      switch(teclado){
        case 0:
          System.out.println("saliste del programa :)");
          return;
        case 1:
          System.out.println("casa");
          System.out.println("\n¿cuantas zonas quieres?");
          verificaNumero();
          int zonas = sc.nextInt();
          ZonaConstruccion z = new ZonaConstruccion(zonas);//probablemente preguntar cuantas construcciones quiere
          System.out.println("\n¿en que posición quieres la casa?");
          verificaNumero();
          int pos = sc.nextInt();
          int precioActual = 500000;
          Casa c = new Casa(z,precioActual, pos);
          System.out.println(z);
          System.out.println("el valor de la casa es: "+ c.precioActual);
                             //+"\nsi se le suma el valor del terreno base el total es: "
                             //+c.precioBase);
          //Constructor c1 = new Constructor(0);
          //System.out.println("el constructor gano :"+ c1);
          return;
        case 2:
          System.out.println("edificio");
          System.out.println("\n¿cuantas zonas quieres?");
          verificaNumero();
          int zonas2 = sc.nextInt();
          ZonaConstruccion z1 = new ZonaConstruccion(zonas2);
          System.out.println("\n¿en que posición quieres el edificio?");
          verificaNumero();
          int pos2 = sc.nextInt();
          int precioActual2 = 600000;
          Edificio e = new Edificio(z1,precioActual2,pos2);
          System.out.println(z1);
          return;
        default:
          System.out.println("\tEntrada invalida\t");
        break;
      }
  }

}
