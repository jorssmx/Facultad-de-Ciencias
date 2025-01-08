package mx.unam.ciencias.edd.proyecto2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;

import mx.unam.ciencias.edd.*;
/**
 * <p>Proyecto 2</p>
 *
 * @author Jorge Anjel Sanchez Sanchez
 * @version 1a
 */
public class Proyecto2 {


	public static Lista<Integer> getLista (Lista<Integer> l, String[] e) throws NumberFormatException {
		for (String i: e) {
			l.agrega(Integer.parseInt(i));
		}
		return l;
	}

	public static Pila<Integer> getPila (Pila<Integer> p, String[] e) throws NumberFormatException {
		for (String i: e) {
			p.mete(Integer.parseInt(i));
		}
		return p;
	}

	public static Cola<Integer> getCola (Cola<Integer> c, String[] e) throws NumberFormatException {
		for (String i: e) {
			c.mete(Integer.parseInt(i));
		}
		return c;
	}

	public static ArbolAVL<Integer> getAVL (ArbolAVL<Integer> a, String[] e) throws NumberFormatException {
		for (String i: e) {
			a.agrega(Integer.parseInt(i));
		}
		return a;
	}

	public static ArbolRojinegro<Integer> getRoNe (ArbolRojinegro<Integer> ar, String[] e) throws NumberFormatException {
		for (String i: e) {
			ar.agrega(Integer.parseInt(i));
		}
		return ar;
	}

	public static Lista<Indexable<Integer>> getInde (Lista<Indexable<Integer>> list, String[] e) throws NumberFormatException {
		Lista<Integer> l = getLista(new Lista<Integer>(), e);
		for (int i:l) {
			list.agrega(new Indexable<Integer>(i, i));
		}
		return list;
	}


	public static Grafica<Integer> getGrafica(Grafica<Integer> g, String[] e, String[] r) throws NumberFormatException {
		String[] arista;
		for (String i: e) {
			g.agrega(Integer.parseInt(i));
		}
		for (String i: r) {
			arista = i.split(",");
			g.conecta(Integer.parseInt(arista[0]), Integer.parseInt(arista[1]));
		}
		return g;
	}

	public static void main(String[] args) throws Exception {
		EstructuraDatos es = null;
		EstructuraSVG ed = new EstructuraSVG();
		Lista<Integer> lista;
		BufferedReader br = null;
		String estructura = "";
		String[] e = null, r = null;;

		try {
	        estructura = br.readLine();
		} catch (IOException e) {
			System.out.println(e);
		}

		if (estructura.charAt(0) != '#') {
			System.out.println("syntax error");
			return;
		}
		estructura = estructura.replace("#", "").replace(" ", "");
		try {
			es =  EstructuraDatos.valueOf(estructura);
		} catch (IllegalArgumentException e) {
			System.out.println("error al leer la estructura");
		}

		if (args.length == 0) {
			br = new BufferedReader(new InputStreamReader(System.in));
		} else {
			try {
				br = new BufferedReader(new FileReader(args[0]));
			} catch (IOException e) {
				System.out.println("archivo no encontrado");
				return;
			}
		}

		try {
	        e = br.readLine().replace(" ", "").split(",");
		} catch (IOException e) {
			System.out.println("Error");
			return;
		}
		try {
			switch (es) {
				case Lista:
					lis = getLista(new Lista<Integer>(), e);
					System.out.println(ed.lista(lis));
					break;
				case Pila:
					Pila<Integer> pi = new Pila<Integer>();
					pi = getPila(pi, e);
					System.out.println(ed.meSa(pi));
					break;
				case Cola:
					Cola<Integer> co = new Cola<Integer>();
					co = getCola(co, e);
					System.out.println(ed.meSa(co));
					break;
				case ArbolAVL:
					ArbolAVL<Integer> arbolAVL = new ArbolAVL<Integer>();
					arbolAVL = getAVL(arbolAVL, e);
					System.out.println(ed.arBinario(arbolAVL, es));
					break;
				case ArbolBinario:
					lista = getLista(new Lista<Integer>(), e);
					arBinarioOrdenado<Integer> arbolO = new arBinarioOrdenado<Integer>(lista);
					System.out.println(ed.arBinario(arbolO, es));
					break;
				case ArbolBinarioCompleto:
					lista = getLista(new Lista<Integer>(), e);
					arBiCompleto<Integer> arbolC = new arBiCompleto<Integer>(lista);
					System.out.println(ed.arBinario(arbolC, es));
					break;
				case ArbolRojinegro:
					ArbolRojinegro<Integer> arbolRN = new ArbolRojinegro<Integer>();
					arbolRN = getRoNe(arbolRN, e);
					System.out.println(ed.arBinario(arbolRN, es));
					break;
				case Grafica:
					try {
					       r = br.readLine().replace(" ", "").split(";");
					} catch (IOException e) {
						System.out.println("Error");
						return;
					}
					Grafica<Integer> g = getGrafica(new Grafica<Integer>(), e, r);
					System.out.println(ed.grafica(g));
					break;
				case Monticulo:
					Lista<Indexable<Integer>> li = getInde(new Lista<Indexable<Integer>>(), e);
					MonticuloMinimo<Indexable<Integer>> monticulo = new MonticuloMinimo<Indexable<Integer>>(li);
					System.out.println(ed.monticulo(monticulo));
					break;
			}
		} catch (NumberFormatException e) {
			System.out.println("Entrada Invalida");
		}
	}
}
