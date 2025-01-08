/**
 * Clase ListaLigada que implementa una interfaz en este caso ListaInterfaz
 * y una clase interna Nodo para guardar la informacion y por ultimo
 * una clase Alebrije para crear objetos de este tipo.
 * @author Sanchez Sanchez Jorge Angel
 * @version 1a
 * No Cuenta: 315155534
 */
public class ListaLigada implements ListaInterfaz{

  /* Clase interna privada para nodos. */
  private class Nodo{
    /* El elemento del nodo. */
    private Object elemento;
    /* El nodo siguiente. */
    private Nodo siguiente;

    /* Construye un nodo con un elemento. */
    public Nodo(Object elemento){
      this.elemento = elemento;
      siguiente = null;
    }

    /**
     * Metodo con el cual obtenemos el elemento del nodo.
     * @return elemento - el elemento del nodo.
    */
    public Object getElemento(){
      return this.elemento;
    }

    /**
     * Metodo que nos regresa el nodo siguiente del nodo.
     * @return Nodo - el nodo siguiente.
    */
    public Nodo getSiguiente(){
      return this.siguiente;//elemento.siguiente;
    }

    /**
     * Metodo que modifica el siguiente nodo.
     * @param nuevo el nuevo elemento.
    */
    public void setSiguiente(Nodo nuevo){
      this.siguiente = nuevo;
    }

  }

  /* Primer elemento de la lista. */
  private Nodo cabeza;
  /* Número de elementos en la lista. */
  private int tamanio;

  @Override
  public int tamanio(){
    if(estaVacia())
      return 0;
    int contador = 0;
    Nodo iterador = cabeza;
    while(iterador != null){
      contador++;
      iterador = iterador.siguiente;
    }
    return contador;
    //return tamanio;
  }

  @Override
  public ListaInterfaz cola(){
    ListaLigada lista = new ListaLigada();
    if(lista != null){
      eliminaPrimero();
      return lista;
    }
    return null;
  }

  @Override
  public void insertaPrimero(Object objeto){
    Nodo nuevo = new Nodo(objeto);
    nuevo.setSiguiente(cabeza);
    cabeza = nuevo;
    tamanio++;
  }

  @Override
  public boolean inserta(Object objeto, int indice){

    Nodo actual = cabeza;
    int contador = 0;
    Nodo nuevo = new Nodo(objeto);

    if(indice == 0){
      insertaPrimero(objeto);
      return true;
    }else{
      if(indice >= 0 && indice <= tamanio()){

        while(contador < indice-1){
          actual = actual.siguiente;
          contador++;
        }
        nuevo.setSiguiente(actual.siguiente);
        actual.setSiguiente(nuevo);
        tamanio++;
        return true;
      }
      return false;
    }

  }

  @Override
  public void insertaFinal(Object objeto){
    Nodo nodo = new Nodo(objeto);
    Nodo aux = cabeza;
    if(aux == null){
      cabeza = nodo;
    }else{
      aux = cabeza;
      while(aux.getSiguiente() != null){
        aux = aux.getSiguiente();
      }
      aux.setSiguiente(nodo);
    }
    tamanio++;
  }

  @Override
  public Object obtenPrimero(){
    if(estaVacia())
      return null;
    return cabeza.elemento;
  }

  @Override
  public Object obten(int indice){
    if(indice >= 0 && indice < tamanio){
      Nodo actual = cabeza;
      int i = 0;
      while(i < indice){
        actual = actual.siguiente;
        i++;
      }
      return actual.elemento;
    }
    return null;

  }

  @Override
  public void eliminaPrimero(){
    if(!estaVacia())
      cabeza = cabeza.getSiguiente();
      tamanio--;
  }

  @Override
  public void elimina(int indice){
    if(indice == 0){
      eliminaPrimero();
    }else{
      if(indice >= 0 && indice < tamanio()){
        if(indice == 0)
          cabeza = cabeza.siguiente;
        int contador = 0;
        Nodo actual = cabeza;
        while(contador < indice-1){
          actual = actual.siguiente;
          contador++;
        }
        actual.setSiguiente(actual.getSiguiente().siguiente);
      }
      tamanio--;
    }
  }

  //Metodo auxiliar
  private int indice(int i, Nodo actual, Object objeto){
    if(actual == null) return -1;
    if(actual.getElemento().equals(objeto)) return i;
    return indice(i+1, actual.siguiente, objeto);
  }

  @Override
  public int indice(Object objeto){
    return indice(0, cabeza, objeto);
  }

  @Override
  public boolean estaVacia(){
    return cabeza == null;
  }

  @Override
  public ListaInterfaz concatena(ListaInterfaz otra){
    ListaLigada resultado = new ListaLigada();

    Nodo iterador = cabeza;
    while(iterador != null){
      resultado.insertaFinal(iterador.getElemento());
      iterador = iterador.siguiente;
    }

    ListaLigada nueva = (ListaLigada) otra;
    iterador = nueva.cabeza;
    while(iterador != null){
      resultado.insertaPrimero(iterador.getElemento());
      iterador = iterador.siguiente;
    }
    return resultado;
  }

  @Override
  public void limpia(){
    cabeza = null;
    tamanio = 0;
  }

  /**
   * Imprime los valores de la lista.
  */
  public void muestra(){
    if(estaVacia()){
      System.out.println("----------------------------\n"+
                         "No hay elementos en la lista");
      return;
    }
    Nodo actual = cabeza;
    System.out.println("------------------------------\n"+
                       "Los elementos de la lista son: \n");
    while(actual != null){
      System.out.println(actual.getElemento());
      actual = actual.siguiente;
    }
    System.out.println("El tamaño de la lista es: "+tamanio()+"\n");
  }

}
