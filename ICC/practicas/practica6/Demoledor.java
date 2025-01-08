import java.util.Scanner;
public class Demoledor extends Trabajador{

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
  public boolean puedeTrabajar(ZonaConstruccion zona){
    return true;
  }

  public void trabaja(ZonaConstruccion zona){
    System.out.println("--------------------------------\n"+
                         "¿que quieres que destruya?\n"+
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
          ZonaConstruccion z = new ZonaConstruccion(1);
          Casa c = new Casa(z,500000, 0);
          System.out.println(z);
          System.out.println("ahora está destruida");

          return;
        case 2:
          System.out.println("edificio");
          ZonaConstruccion z1 = new ZonaConstruccion(1);
          Edificio e = new Edificio(z1,600000, 0);
          System.out.println(z1);
          System.out.println("ahora está destruida");
          return;
        default:
          System.out.println("\tEntrada invalida\t\n");
        break;
      }
    }

}
