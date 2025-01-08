public class Edificio extends Casa{

  @Override //checar si va el Override.
  public String[] toStringArray(){
    String[] array=new String[11];

    array[10]=" ________";
    array[9]="|        ||";
    array[8]="|[]  [][]||";
    array[7]="|[]  [][]||";
    array[6]="|    TTTT||";
    array[5]="|________||";
    array[4]="|        ||";
    array[3]="| _  [][]||";
    array[2]="|| | [][]||";
    array[1]="|| |     ||";
    array[0]="TTTTTTTTTT";
    return array;
  }

  /* los pisos del edificio */
  int pisos = 2;
  /* el precio actual del edificio */
  double precioActual;

  /**
   * Metodo constructor de un edificio con su valor.
   * @param precioActual - el precio del edificio.
   * @param pisos - los pisos del edificio.
  */
  public Edificio(ZonaConstruccion zona, double precioActual, int pos){
    super(zona, precioActual, pos);
    zona.construcciones[pos] = this;
  }
}
