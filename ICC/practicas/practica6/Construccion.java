/**
 * Clase que simula una construcción
 * @author Pedro Ulises Cervantes González
 * @version 1.0
 */
public class Construccion{

    /** El precio base de un terreno baldío*/
    protected static double precioBase=200000;

    /** El precio actual de la construcción*/
    protected double precioActual;

    /** La zona de construcción donde estará*/
    protected ZonaConstruccion zona;

    /**
     * Crea una nueva construcción.
     *
     * @param zona La zona donde estará
     */
    public Construccion(ZonaConstruccion zona){
	this.zona=zona;
	precioActual=precioBase;
    }

    /**
     * Devuelve el precio actual de la construcción
     *
     * @return El precio actual, después de recalcularlo
     */
    public final double getPrecioActual(){
	recalculaPrecioActual();
	return precioActual;
    }

    /**
     * Devuelve una represetnación de arreglos de cadenas
     * de la construcción.
     *
     * @return Un arreglo de cadenas en el que mientras mayor
     *         es el índice i, más arriba se está en la construcción.
     */
    public String[] toStringArray(){
	return new String[]{"TTTTTTTTTT"};
    }

    /**
     * Recalcula el precio actual.
     */
    protected void recalculaPrecioActual(){}

    /**
     * Devuelve una cadena que representa el cielo.
     *
     * @return Una cadena de espacios tan larga como el ancho
     *         de la construcción
     */
    public static String cielo(){
	return "          ";
    }
}
