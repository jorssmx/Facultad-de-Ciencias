/**
*@author Jorge Angel Sanchez Sanchez
*@version 1a
*No Cuenta: 315155534
*/
public class  Mascota{
private String tipo = "";
private String nombre = "";
private String tamano = "";
private String color = "";
private String masAdora = "";
public String recuerdos = "";
public static int instancias;
/**
  *Metodo Constructor que recibe los datos de la Mascota
  *@param tipo - cadena que representa el tipo de mascota
  *@param nombre - cadena que representa el nombre de la mascota
  *@param color - cadena que representa el color de la mascota
  *@param tamano - cadena que representa el tamano de la mascota
  *@param masAdora - cadena que representa lo que la mascota adora de su dueno
  */
public Mascota(String tipo,String nombre,String tamano,String color, String masAdora){
  this.tipo = tipo;
  this.nombre = nombre;
  this.tamano = tamano;
  this.color = color;
  this.masAdora = masAdora;
  System.out.println("Mascota: "+ instancias++);//para saber cuantas mascotas hemos creado
}
/**
  *Metodo Constructor que recibe los datos de la Mascota y desconoce el tamano y color
  *@param tipo - cadena que representa el tipo de mascota
  *@param nombre - cadena que representa el nombre de la mascota
  *@param masAdora - cadena que representa lo que la mascota adora de su dueno
  */
public Mascota(String tipo,String nombre, String masAdora){
  this.tipo = tipo;
  this.nombre = nombre;
  this.masAdora = masAdora;
  System.out.println("Mascota: "+ instancias++);//para saber cuantas mascotas hemos creado
}
/**
*Metodo para obtener el tipo de la mascota
*@return String - el tipo de la mascota
*/
public String getTipo(){
  return this.tipo;
}
/**
*Metodo para asignar el tipo de mascota
*@param tipo - el tipo de mascota
*/
public void setTipo(){
  this.tipo = tipo;
}
/**
*Metodo para obtener el nombre de la mascota
*@return String - el nombre de la mascota
*/
public String getNombre(){
  return this.nombre;
}
/**
*Metodo para asignar el nombre
*@param nombre - el nombre de la mascota
*/
public void setNombre(){
  this.tamano = nombre;
}
/**
  *Metodo para obtener el tamano de la mascota
  *@return String - el tamano de la mascota
  */
  public String getTamano(){
    return this.tamano;
  }
  /**
  *Metodo para asignar el tamano
  *@param tamano - el tamano de la mascota
  */
  public void setTamano(){
    this.tamano = tamano;
  }
/**
  *Metodo para obtener el color de la Mascota
  *@return String - el color de la mascota
  */
  public String getColor(){
    return this.color;
  }
  /**
  *Metodo para asignar el color
  *@param color - el color de la mascota
  */
  public void setColor(){
    this.color = color;
  }
  /**
  *Metodo para obtener la instancia de la mascota
  *@return int - la instancia de la mascota
  */
  public int getInstancia(){
    return this.instancias;
  }
  /**
  *Metodo para asignar la instancia
  *@param instancia - la instancia de la mascota
  */
  public void setInstancia(){
    this.instancias = instancias;
  }
  /**
  *Metodo para obtener lo que mas adora la mascota de su dueno
  *@return String - lo que mas adora la mascota de su dueno
  */
  public String getMasAdora(){
    return this.masAdora;
  }
  /**
  *Metodo para asignar lo que mas adora la mascota de su dueno
  *@param masAdora - lo que mas adora la mascota de su dueno
  */
  public void setMasAdora(){
    this.masAdora = masAdora;
  }
  /**
  *Metodo para obtener los recuerdos de la mascota
  *@return String - los recuerdos de la mascota
  */
  public String getRecuerdos(){
    return this.recuerdos;
  }
  /**
  *Metodo para asignar los recuerdos de la mascota
  *@param recuerdos - los recuerdos de la mascota
  */
  public void setRecuerdos(){
    this.recuerdos = recuerdos;
  }
  //MODIFICAR YA QUE HACE LO MISMO QUE getMasAdora
  /**
  *Metodo donde la mascota almacena el recuerdo de otra mascota
  *@return mascota - los recuerdos de otra mascota
  */
  public String recuerdos(){
    String mascota = getRecuerdos()+ getMasAdora();
    return mascota;
  }
  //main
  public static void main(String[] args) {
    System.out.println("------------------------------------------------------");
    Mascota m1 = new Mascota("Perro","Silverio","grande","negro","Salir a correr con el");
    System.out.println("El tipo de mascota es: "+ m1.getTipo());
    System.out.println("Su nombre es: "+ m1.getNombre());
    System.out.println("Su color es: "+ m1.getColor());
    System.out.println("Su tamano es: "+ m1.getTamano());
    System.out.println("Lo que más adora de su dueno es: "+ m1.getMasAdora());
    System.out.println("los recuerdos de la mascota son: "+ m1.getRecuerdos());
    System.out.println("------------------------------------------------------");
    Mascota m2 = new Mascota("Gato","castro","que jueguen con el");
    System.out.println("El tipo de mascota es: "+ m2.getTipo());
    System.out.println("Su nombre es: "+ m2.getNombre());
    System.out.println("Lo que más adora de su dueno es: "+ m2.getMasAdora());
    System.out.println("los recuerdos de la mascota son: "+ m2.getRecuerdos());
    System.out.println("------------------------------------------------------");
    Mascota m3 = new Mascota("Perico","periquito","chico","verde","que lo acaricien");
    System.out.println("El tipo de mascota es: "+ m3.getTipo());
    System.out.println("Su nombre es: "+ m3.getNombre());
    System.out.println("Su color es: "+ m3.getColor());
    System.out.println("Su tamano es: "+ m3.getTamano());
    System.out.println("Lo que más adora de su dueno es: "+ m3.getMasAdora());
    System.out.println("los recuerdos de la mascota son: "+ m3.getRecuerdos());
    System.out.println("------------------------------------------------------");
    Mascota m4 = new Mascota("Pez","meltan","que le den de comer");
    System.out.println("El tipo de mascota es: "+ m4.getTipo());
    System.out.println("Su nombre es: "+ m4.getNombre());
    System.out.println("Lo que más adora de su dueno es: "+ m4.getMasAdora());
    System.out.println("los recuerdos de la mascota son: "+ m4.getRecuerdos());
    System.out.println("------------------------------------------------------");
    Mascota m5 = new Mascota("Canario","pajaro","chico","Amarillo","que le canten");
    System.out.println("El tipo de mascota es: "+ m5.getTipo());
    System.out.println("Su nombre es: "+ m5.getNombre());
    System.out.println("Su color es: "+ m5.getColor());
    System.out.println("Su tamano es: "+ m5.getTamano());
    System.out.println("Lo que más adora de su dueno es: "+ m5.getMasAdora());
    System.out.println("los recuerdos de la mascota son: "+ m5.getRecuerdos());
    System.out.println("------------------------------------------------------");
    Mascota m6 = new Mascota("Gallina","galli","Mediano","Blanca","que le den maiz");
    System.out.println("El tipo de mascota es: "+ m6.getTipo());
    System.out.println("Su nombre es: "+ m6.getNombre());
    System.out.println("Su color es: "+ m6.getColor());
    System.out.println("Su tamano es: "+ m6.getTamano());
    System.out.println("Lo que más adora de su dueno es: "+ m6.getMasAdora());
    System.out.println("los recuerdos de la mascota son: "+ m6.getRecuerdos());
    System.out.println("------------------------------------------------------");
    System.out.println("------------------------------------------------------");
    System.out.println(m1.getNombre()+" recuerda que le contaron: "+ m2.recuerdos()+", "+ m3.recuerdos()+ ",\n" + m4.recuerdos());
    System.out.println("------------------------------------------------------");
    System.out.println(m2.getNombre()+" recuerda que le contaron: "+ m1.recuerdos()+", "+ m3.recuerdos()+ ",\n" + m4.recuerdos());
    System.out.println("------------------------------------------------------");
    System.out.println(m3.getNombre()+" recuerda que le contaron: "+ m1.recuerdos()+", "+ m2.recuerdos());
    System.out.println("------------------------------------------------------");
    System.out.println(m4.getNombre()+" recuerda que le contaron: "+ m1.recuerdos()+", "+ m2.recuerdos()+",\n"+ m5.recuerdos());
    System.out.println("------------------------------------------------------");
    System.out.println(m5.getNombre()+" recuerda que le contaron: "+ m3.recuerdos()+", "+ m4.recuerdos()+ ",\n" + m6.recuerdos());
    System.out.println("------------------------------------------------------");
    System.out.println(m6.getNombre()+" recuerda que le contaron: "+ m2.recuerdos()+", "+ m5.recuerdos());
    System.out.println("------------------------------------------------------");
    System.out.println("Mascotas creadas: "+ instancias);
  }

}
