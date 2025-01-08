/**
 * Clase que representa las cosas de un super, y estas tienen
 * producto - que es el nombre del producto y
 * cantidad - que es la cantidad de productos representada en entero.
 * @author Sanchez Sanchez Jorge Angel
 * @version 1a
 * No Cuenta: 315155534
*/
public class CosasDelSuper{

  /* Nombre del producto. */
  public String producto;

  /* Cantidad de productos. */
  public int cantidad;

  /**
   * Metodo constructor el cual crea un nuevo producto.
   * @param producto el nombre del producto.
   * @param cantidad la cantidad de productos.
  */
  public CosasDelSuper(String producto, int cantidad){
    this.producto = producto;
    this.cantidad = cantidad;
  }

  /**
   * Metodo que te da el nombre del producto.
   * @return String - el nombre del producto.
  */
  public String etProducto(){
    return this.producto;
  }

  /**
   * Metodo que te da la cantidad de productos.
   * @return int - la cantidad de productos.
  */
  public int getCantidad(){
    return this.cantidad;
  }

  /**
   * Metodo que modifica el nombre del producto.
   * @param producto el nombre del producto.
  */
  public void setProducto(String producto){
    this.producto = producto;
  }

  /**
   * Metodo que modifica la cantidad de productos.
   * @param cantidad la cantidad de productos.
  */
  public void setCantidad(int cantidad){
    this.cantidad = cantidad;
  }

  /**
   * Metodo que nos muestra la representacion de los productos y su cantidad.
  */
  public String toString(){
    return "Producto: "+producto+"\nCantidad: "+cantidad+"\n";
  }
}
