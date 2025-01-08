/**
 * Clase que simula una casa.
 * @author Pedro Ulises Cervantes González
 * @version 1.0
 */
public class Casa extends Construccion{

  @Override
  public String[] toStringArray(){
    String[] array=new String[8];
    array[7]="  ______  ";
    array[6]=" /      \\ ";
    array[5]="/________\\";
    array[4]="|        |";
    array[3]="| _  [][]|";
    array[2]="|| | [][]|";
    array[1]="|| |     |";
    array[0]="TTTTTTTTTT";
    return array;
  }

  /* la posicion de la construccion. */
  int pos;

  /**
   * Metodo constructor de una casa con su zona, precio y posicion.
   * @param zona - la cantidad de terrenos que hay.
   * @param precioActual - el precio de la casa.
   * @param pos - la posicion donde quieres que este la casa.
  */
  public Casa(ZonaConstruccion zona, double precioActual, int pos){
    super(zona);
    this.precioActual = precioActual;
    this.pos = pos;
    zona.construcciones[pos] = this;
  }

  /**
   * Metodo que nos resta el 10% del valor de la casa o edificio.
   * @return int - el precio ya actualizado.
  */
  public double unTerrenoValdio(){
    double decrementa = (10*precioActual)/100;//el 10% del precio
    double nuevoPrecio = precioActual - decrementa;
    return nuevoPrecio;
  }

  /**
   * Metodo que nos resta el 25% del valor de la casa o edificio.
   * @return int - el precio ya actualizado.
  */
  public double dosTerrenosValdios(){
    double decrementa = (25*precioActual)/100;//el 25% del precio
    double nuevoPrecio = precioActual - decrementa;
    return nuevoPrecio;
  }

  /**
   * Metodo que nos dice si la construccion tiene al lado uno, dos
   * o ningun terreno valdio y depende de ello nos da el valor
   * actual de la construccion.
   * @param return double - el precio total o final de la construccion.
  */
  /*
  public double tieneTerrenosValdios(){
    //if(zona.construcciones[pos-1] != 0)

    if(zona.construcciones[pos+1] == null ||
       zona.construcciones[pos-1] == null){
      return precioActual; //
    }else{
      if((zona.construcciones[pos+1] instanceof Casa) == true){
                 return this.unTerrenoValdio(); //
      }else if((zona.construcciones[pos-1] instanceof Casa) == true){
                 return this.unTerrenoValdio(); //
      }else if((zona.construcciones[pos-1] instanceof Casa) == false &&
               (zona.construcciones[pos+1] instanceof Casa) == false){
                 return this.dosTerrenosValdios(); //
      }else if((zona.construcciones[pos-1] instanceof Casa) == false ||
               (zona.construcciones[pos+1] instanceof Casa) == true){
                 return this.unTerrenoValdio(); //
      }else if((zona.construcciones[pos-1] instanceof Casa) == true ||
               (zona.construcciones[pos+1] instanceof Casa) == false){
                 return this.unTerrenoValdio(); //
      }
    }
    return 0;
  }
  */

}
