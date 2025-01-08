/**
 * Interfaz que provee varios métodos referentes a listas ordenadas.
 * @author Cervantes Gonzales Pedro Ulises
 * @version 1.0 03/04/17
 */
public interface ListaOrdenadaInterfaz{

    /**
     * Devuelve el tamaño de la lista.
     *
     * @return El tamaño de la lista. Si está vacía devuelve cero.
     */
    public int tamanio();

    /**
     * Devuelve la cola de la lista.
     *
     * @return Una lista con todos los elementos de le invocante menos el primero.
     */
    public ListaOrdenadaInterfaz cola();

    /**
     * Inserta un elemento en el lugar que le corresponde de la lista, desplazando todos los
     * demás elementos.
     *
     * @param objeto El elemento a insertar.
     */
    public void insertaEnOrden(Object objeto);

    /**
     * Devuelve el primer elemento de la lista.
     *
     * @return El primer elemento de la lista. Si la lista está vacía, devuelve null.
     */
    public Object obtenPrimero();

    /**
     * Devuleve el elemento en la posición especificada de la lista.
     *
     * @param indice La posición donde se encuentra el objeto. La posición debe
     *               ser mayor o igual a cero, y menor al tamaño de la lista.
     * @return El elemento en la posición especificada. Si la posición es inválida
     *         devuelve null.
     */
    public Object obten(int indice);

    /**
     * Elimina el primer elemento de la lista y actualiza la cabeza de la lista
     */
    public void eliminaPrimero();

    /**
     * Elimina un elemento en la posición especificada de la lista, desplazando
     * todos los demás elementos.
     *
     * @param indice La posición donde se eliminará el objeto. La posición debe
     *               ser mayor o igual a cero, y menor al tamaño de la lista.
     */
    public void elimina(int indice);

    /**
     * Busca un elemento en la lista y devuelve la primera posición donde aparece.
     *
     * @return El índice del objeto. Si no lo encuentra, devuelve -1.
     */
    public int indice(Object objeto);

    /**
     * Menciona si la lista no tiene elementos
     *
     * @return true si la lista no tiene elementos, false en otro caso.
     */
    public boolean estaVacia();

    /**
     * Combina la lista invocante con una nueva lista.
     *
     * @param otra La lista con la que se combinará.
     * @return Una lista ordenada con los elementos de ambas listas. Esta lista
     *         es independiente a las otras dos.
     */
    public ListaOrdenadaInterfaz combina(ListaOrdenadaInterfaz otra);

    /**
     * Vacia la lista. Elimina todos los elementos.
     */
    public void limpia();
}
