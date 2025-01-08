import java.util.Scanner;
public class Remodelador extends Trabajador{

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
    return true;//zona >= 2; //duda con el igual.
  }

  public void trabaja(ZonaConstruccion zona){
    System.out.println("--------------------------------\n"+
                         "Solo trabajan en Edificios\n"+
                         "¿que quieres que haga?\n"+
                         "--------------------------------\n"+
                         "0: Salir del programa\n"+
                         "1: Agregar pisos\n"+
                         "2: quitar pisos\n"+
                         "--------------------------------");
      verificaNumero();
      int teclado = sc.nextInt();
      switch(teclado){
        case 0:
          System.out.println("saliste del programa :)");
          return;
        case 1:
          System.out.println("agrega en edificio");
          System.out.println("edificio");
          ZonaConstruccion z = new ZonaConstruccion(1);
          Edificio e = new Edificio(z,600000, 0);
          System.out.println(z);
          System.out.println("ahora está agregado");
          return;
        case 2:
          System.out.println("elimina en edificio");
          System.out.println("edificio");
          ZonaConstruccion z1 = new ZonaConstruccion(1);
          Edificio e1 = new Edificio(z1,600000, 0);
          System.out.println(z1);
          System.out.println("ahora está eliminado");
          return;
        default:
          System.out.println("\tEntrada invalida\t\n");
        break;
      }
  }

}
