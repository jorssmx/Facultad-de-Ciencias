/**
 * Clase que simula un trbajador para una zona de construcción
 * @author Pedro Ulises Cervantes González
 * @version 1.0
 */
public abstract class Trabajador{

    /** Las ganancias que ha acumulado el trabajador */
    protected double ganancias;

    /**
     * Trabaja en una zona de construcción. Sus ganancias aumentan
     * dependiendo del trabajo que realice.
     *
     * @param zona La zona donde labora.
     */
    public abstract void trabaja(ZonaConstruccion zona);

    /**
     * Devuelve las ganancias que ha acumulado.
     *
     * @return Las ganancias.
     */
    public double getGanancias(){
	return ganancias;
    }

    /**
     * Indica si puede trabajar o no.
     *
     * @param zona La zona donde laborará.
     * @return true si puede trabajar, false en otro caso.
     */
    public abstract boolean puedeTrabajar(ZonaConstruccion zona);

}
