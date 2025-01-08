/**
*@author Jorge Angel Sanchez Sanchez
*@version 1a
*No Cuenta: 315155534
*/
public class Veterinario{
  //instancias de la mascota
  Mascota m1 = new Mascota("Perro","Silverio","grande","negro","Salir a correr con el");
  Mascota m2 = new Mascota("Gato","castro","que juegue con el");
  Mascota m3 = new Mascota("Perico","periquito","chico","verde","que lo acaricien");
  Mascota m4 = new Mascota("Pez","meltan","que le den de comer");
  Mascota m5 = new Mascota("Canario","pajaro","chico","Amarillo","que le canten");
  Mascota m6 = new Mascota("Gallina","galli","Mediano","Blanca","que le den maiz");
  /**
  *Metodo que hace que dos mascotas sean mejores amigos
  *y que el veterinario revierta 3 relaciones de amistad
  *@return String - las mascotas que son mejores amigos,
  *las mascotas que eran amigos y las mascotas que volvieron hacer amigos
  */
  public String hazlosMejoresAmigos(){
    return (m1.getNombre()+" y "+m5.getNombre()+" ya son mejores amigos :D "+"\n"+
           (m1.getNombre()+" y "+m3.getNombre()+" ya son mejores amigos :D ")+"\n"+
           (m1.getNombre()+" y "+m6.getNombre()+" ya son mejores amigos :D ")+"\n"+
           (m1.getNombre()+" y "+m2.getNombre()+" ya son mejores amigos :D")+"\n"+
           (m2.getNombre()+" y "+m3.getNombre()+" ya son mejores amigos :D ")+"\n"+
           (m2.getNombre()+" y "+m5.getNombre()+" eran mejores amigos :D ")+"\n"
           +"El veterinario hizo que:"+"\n"
           +(m2.getNombre()+" y "+m5.getNombre()+" volvieran a hacer mejores amigos")+"\n"
           +(m3.getNombre()+" y "+m6.getNombre()+" eran mejores amigos :D ")+"\n"
           +"El veterinario hizo que:"+"\n"
           +(m3.getNombre()+" y "+m6.getNombre()+" volvieran a hacer mejores amigos")+"\n"
           +(m3.getNombre()+" y "+m5.getNombre()+" eran mejores amigos :D ")+"\n"
           +"El veterinario hizo que:"+"\n"
           +(m3.getNombre()+" y "+m5.getNombre()+" volvieran a hacer mejores amigos"));
  }
  //main
  public static void main(String[] args) {
    //instancia del veterinario
    Veterinario v1 = new Veterinario();
    System.out.println("--------------------------------------------");
    System.out.println(v1.hazlosMejoresAmigos());

  }

}
