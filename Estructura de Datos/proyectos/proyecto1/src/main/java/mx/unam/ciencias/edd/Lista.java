package mx.unam.ciencias.edd;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase genérica para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas no aceptan a <code>null</code> como elemento.</p>
 */
public class Lista<T> implements Coleccion<T> {

    /* Clase Nodo privada para uso interno de la clase Lista. */
    private class Nodo {
        /* El elemento del nodo. */
        public T elemento;
        /* El nodo anterior. */
        public Nodo anterior;
        /* El nodo siguiente. */
        public Nodo siguiente;

        /* Construye un nodo con un elemento. */
        public Nodo(T elemento) {
            // Aquí va su código.
            this.elemento = elemento;
        }
    }

    /* Clase Iterador privada para iteradores. */
    private class Iterador implements IteradorLista<T> {
        /* El nodo anterior. */
        public Nodo anterior;
        /* El nodo siguiente. */
        public Nodo siguiente;

        /* Construye un nuevo iterador. */
        public Iterador() {
            start();
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            return siguiente != null;
        }

        /* Nos da el elemento siguiente. */
        @Override public T next() {
            if (siguiente == null)throw new NoSuchElementException();
            anterior = siguiente;
            siguiente = siguiente.siguiente;
            return anterior.elemento;
        }

        /* Nos dice si hay un elemento anterior. */
        @Override public boolean hasPrevious() {
             return anterior != null;
        }

        /* Nos da el elemento anterior. */
        @Override public T previous() {
            if (anterior == null) throw new NoSuchElementException();
            siguiente = anterior;
            anterior = anterior.anterior;
            return siguiente.elemento;
        }

        /* Mueve el iterador al inicio de la lista. */
        @Override public void start() {
            anterior = null;
            siguiente = cabeza;
        }

        /* Mueve el iterador al final de la lista. */
        @Override public void end() {
            anterior = rabo;
            siguiente = null;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista. El método es idéntico a {@link
     * #getElementos}.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * Regresa el número elementos en la lista. El método es idéntico a {@link
     * #getLongitud}.
     * @return el número elementos en la lista.
     */
    @Override public int getElementos() {
        return getLongitud();
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    @Override public boolean esVacia() {
      return longitud < 1;
    }

    /**
     * Agrega un elemento a la lista. Si la lista no tiene elementos, el
     * elemento a agregar será el primero y último. El método es idéntico a
     * {@link #agregaFinal}.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    @Override public void agrega(T elemento) {
        agregaFinal(elemento);
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        if (elemento==null) throw new IllegalArgumentException();

        Nodo nAux = new Nodo(elemento);
        if(longitud < 1){
            rabo = cabeza = nAux;
        }else{
            rabo.siguiente = nAux;
            nAux.anterior = rabo;
            rabo = nAux;
        }
        longitud++;
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
      if(elemento == null) throw new IllegalArgumentException();

      Nodo nAux = new Nodo(elemento);
      if(longitud < 1){
          cabeza = rabo = nAux;
      }else{
          cabeza.anterior = nAux;
          nAux.siguiente = cabeza;
          cabeza = nAux;
      }
      longitud++;
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void inserta(int i, T elemento) {
      if(elemento==null){
        throw new IllegalArgumentException("elemento es null");
      }
          if ( i<=0){
              agregaInicio(elemento);
              return ;
          }
          if (i>=longitud) {
              agregaFinal(elemento);
              return ;
          }
          Nodo nuevo = new Nodo (elemento);
          Nodo aux = cabeza;
          for (int j=0;j<i ;j++)
              aux = aux.siguiente;
          nuevo.siguiente = aux;
          nuevo.anterior = aux.anterior;
          aux.anterior.siguiente = nuevo;
          aux.anterior=nuevo;
          longitud ++;
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
      Nodo nodo = buscaNodo(cabeza, elemento);
      if(nodo == null){
          return;
      } else if(cabeza == rabo){
          cabeza = rabo = null;
      } else if(cabeza == nodo){
          cabeza = cabeza.siguiente;
          cabeza.anterior = null;
      } else if(rabo == nodo){
          rabo = rabo.anterior;
          rabo.siguiente = null;
      } else {
          nodo.siguiente.anterior = nodo.anterior;
          nodo.anterior.siguiente = nodo.siguiente;
      }
      longitud--;
    }

    private Nodo buscaNodo(Nodo a, T elemento) {
        if(a == null) return null;
        if(a.elemento.equals(elemento)) return a;
        return buscaNodo(a.siguiente, elemento);
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
      if(longitud < 1) throw new NoSuchElementException();

      T eAux = cabeza.elemento;

      if(longitud == 1){
          cabeza = rabo = null;
      } else {
          cabeza = cabeza.siguiente;
          cabeza.anterior = null;
      }
      longitud--;
      return eAux;
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {
      if(rabo == null) throw new NoSuchElementException();

      T eAux = rabo.elemento;

      if(longitud == 1){
          rabo = cabeza = null;
      } else{
          rabo = rabo.anterior;
          rabo.siguiente = null;
      }

      longitud--;
      return eAux;
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <tt>true</tt> si <tt>elemento</tt> está en la lista,
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
        return buscaNodo(cabeza, elemento) != null;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
      Lista<T> listAux = new Lista<>();
      return reversa(listAux, cabeza);
    }

    private Lista<T> reversa(Lista<T> list, Nodo nodo){
        if(nodo == null) return list;
        list.agregaInicio(nodo.elemento);
        return reversa(list, nodo.siguiente);
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
      Lista<T> listAux = new Lista<>();
        return copia(listAux, cabeza);
    }

    private Lista<T> copia(Lista<T> list, Nodo nodo) {
      if(nodo == null) return list;
      list.agregaFinal(nodo.elemento);
      return copia(list, nodo.siguiente);
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    @Override public void limpia() {
      cabeza = rabo = null;
      longitud = 0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {
      if(longitud < 1) throw new NoSuchElementException();
      return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() {
      if(longitud < 1) throw new NoSuchElementException();
      return rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public T get(int i) {
      if(i < 0 || i >= getLongitud()) throw new ExcepcionIndiceInvalido();
      return get(i, cabeza, 0);
    }

    private T get(int i, Nodo nodo, int j){
        if(i == j) return nodo.elemento;
        return get(i, nodo.siguiente, j+1);
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si
     *         el elemento no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
        return indiceDe(0, cabeza, elemento);
    }

    private int indiceDe(int i, Nodo nodo, T elemento) {
        if(nodo == null) return -1;
        if(nodo.elemento.equals(elemento)) return i;

        return indiceDe(i+1, nodo.siguiente, elemento);
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
      StringBuilder strBldr = new StringBuilder();
      strBldr.append("[");
      Nodo nodo = cabeza;
      while(nodo != null){
          strBldr.append(nodo.elemento.toString());
          nodo = nodo.siguiente;
          if(nodo != null) strBldr.append(", ");
      }
      strBldr.append("]");
      return strBldr.toString();
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param o el objeto con el que hay que comparar.
     * @return <tt>true</tt> si la lista es igual al objeto recibido;
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)o;
        if(lista.getLongitud() != longitud) return false;

       Nodo n1 = cabeza;
       Nodo n2 = lista.cabeza;
       while(n1 != null && n2 != null){
           if(!n1.elemento.equals(n2.elemento)) return false;
           n1 = n1.siguiente;
           n2 = n2.siguiente;
       }
       return true;
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }

    private Lista<T> mezcla(Lista<T> lista, Comparator<T> comp) {
        Lista<T> l = new Lista<>();
        Nodo a = this.cabeza;
        Nodo b = lista.cabeza;

        while (a != null && b != null)
            if (comp.compare(a.elemento, b.elemento) <= 0) {
                l.agregaFinal(a.elemento);
                a = a.siguiente;
            } else {
                l.agregaFinal(b.elemento);
                b = b.siguiente;
            }

            Nodo n = (a == null ? b : a);
            while(n != null) {
              l.agregaFinal(n.elemento);
              n = n.siguiente;

            }

            return l;
    }
    /**
     * Regresa una copia de la lista, pero ordenada. Para poder hacer el
     * ordenamiento, el método necesita una instancia de {@link Comparator} para
     * poder comparar los elementos de la lista.
     * @param comparador el comparador que la lista usará para hacer el
     *                   ordenamiento.
     * @return una copia de la lista, pero ordenada.
     */
    public Lista<T> mergeSort(Comparator<T> comparador) {
      return mergeSort(copia(), comparador);
    }

    private Lista<T> mergeSort(Lista<T> lista, Comparator<T> comparador) {
      if(longitud < 2)
        return lista;
      Lista<T> ld = new Lista<T>();
      Lista<T> li = new Lista<T>();
      int medio = longitud/2;
      Nodo n = cabeza;

      while(n != null && medio != 0) {
        li.agrega(n.elemento);
        medio--;
        n = n.siguiente;
      }

      while(n != null) {
        ld.agrega(n.elemento);
        n = n.siguiente;
      }

      li = li.mergeSort(comparador);
      ld = ld.mergeSort(comparador);
      return li.mezcla(ld, comparador);
    }

    /**
     * Regresa una copia de la lista recibida, pero ordenada. La lista recibida
     * tiene que contener nada más elementos que implementan la interfaz {@link
     * Comparable}.
     * @param <T> tipo del que puede ser la lista.
     * @param lista la lista que se ordenará.
     * @return una copia de la lista recibida, pero ordenada.
     */
    public static <T extends Comparable<T>> Lista<T> mergeSort(Lista<T> lista) {
        return lista.mergeSort((a, b) -> a.compareTo(b));
    }

    /**
     * Busca un elemento en la lista ordenada, usando el comparador recibido. El
     * método supone que la lista está ordenada usando el mismo comparador.
     * @param elemento el elemento a buscar.
     * @param comparador el comparador con el que la lista está ordenada.
     * @return <tt>true</tt> si elemento está contenido en la lista,
     *         <tt>false</tt> en otro caso.
     */
    public boolean busquedaLineal(T elemento, Comparator<T> comparador) {
        for(T elem : this)
        if(comparador.compare(elem,elemento) == 0)
        return true;
        return false;
    }

    /**
     * Busca un elemento en una lista ordenada. La lista recibida tiene que
     * contener nada más elementos que implementan la interfaz {@link
     * Comparable}, y se da por hecho que está ordenada.
     * @param <T> tipo del que puede ser la lista.
     * @param lista la lista donde se buscará.
     * @param elemento el elemento a buscar.
     * @return <tt>true</tt> si el elemento está contenido en la lista,
     *         <tt>false</tt> en otro caso.
     */
    public static <T extends Comparable<T>> boolean busquedaLineal(Lista<T> lista, T elemento) {
        return lista.busquedaLineal(elemento, (a, b) -> a.compareTo(b));
    }
}
