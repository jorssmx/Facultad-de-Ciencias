package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.*;

public class EstructuraSVG {

	private class VerticeCoordenada implements Comparable<VerticeCoordenada> {

		double x;
		double y;
		Verticegr<Integer> v;

		public VerticeCoordenada(Verticegr<Integer> v, double x, double y) {
			this.v = v;
			this.x = x;
			this.y = y;
		}

		@Override public int compareTo(VerticeCoordenada vc) {
			return this.v.getElemento().compareTo(vc.v.getElemento());
		}

	}

	public String list(Lista<Integer> l) {
		int p = 15, border = 25;
		String list= "";
		int i = border, largoSVG = border;

		for (int e: l) {
			list+= u.rect(e, i, 40, p, border);
			i += this.numeroLargo(e)*10+p*2;
			if (e != l.getUltimo()) {
				list+= u.Fle(i+2, 58);
			}
			i += border;
		}
		largoSVG += i;

		return xml + "<svg width='"+ largoSVG +"' height='100'>" + list+ "</svg>";
	}

	public EstructuraSVG () {
		u = new SVG();
		xml = "<?xml version='1.0' encoding='utf-8'?>";
	}

	public boolean equals(VerticeCoordenada vc) {
					return vc.v.getElemento().equals(this.v.getElemento());
			}

	public String meSa(MeteSaca<Integer> ms) {
		String mss = "";
		int p = 15, border = 25;
		int i = border, largoSVG = border, e;

		while (!ms.esVacia()) {
			e = ms.saca();
			mss += u.rect(e, i, 40, p, border);
			i += this.numeroLargo(e)*10+p*2;
			if (!ms.esVacia()) {
				mss += u.fleDe(i+2, 58);
			}
			i += border;
		}
		largoSVG += i;

		return xml + "<svg width='"+ largoSVG +"' height='100'>" + mss + "</svg>";
	}

	public String arBinario (arBinario<Integer> ab, EstructuraDatos arbol_a) {
		int p = 15, largoSVG, altoSVG, rad;
		int iniX, iniY;
		String arbol;
		VerticearBinario<Integer> max;

		if (ab.esVacio()) {
			return xml;
		}

		max = this.getMaximo(ab.raiz());

		rad = (this.numeroLargo(max.get())*10+p*2)/2;
		largoSVG = this.getSVGArbol(ab,rad);
		altoSVG = this.getSVGArbol(ab,rad);

		iniX = largoSVG/2;
		iniY = rad*3;

		arbol = this.getVertices(ab.raiz(), rad, largoSVG/2, iniX, iniY, arbol_a);
		return xml + "<svg width='"+ largoSVG +"' height='"+ altoSVG +"'>" + arbol + "</svg>";
	}

	public String monticulo (MonticuloMinimo<Indexable<Integer>> mm) {
		arBinarioCompleto<Integer> abc = new arBinarioCompleto<Integer>();
		for (Indexable<Integer> i:mm) {
			abc.agrega(i.getElemento());
		}
		return this.arBinario(abc, EstructuraDatos.arBinarioCompleto);
	}

	private int numeroLargo (int n) {
		int i = 1;
		while (n >= 10) {
			n /= 10;
			i++;
		}
		return i;
	}

	public String gr (gr<Integer> g) {
		String gr;
		int p = 15, rad;
		int per, max;
		double radG;
		double largoSVG, altoSVG;

		max = this.getMaximo(g);

		rad = (this.numeroLargo(max)*10+p*2)/2;
		per = g.getElementos()*rad*3;
		radG = per / 3.1416;

		largoSVG = altoSVG = radG*2 + rad*2.0*2.0;

		gr = this.getVertices(g, radG, rad, largoSVG/2, altoSVG/2);
		return xml + "<svg width='"+ largoSVG +"' height='"+ altoSVG +"'>" + gr + "</svg>";
	}

	private SVG u;
	private final String xml;


	private int getMaximo (gr<Integer> g) {
		int max = 0;
		for (int i:g) {
			max = i;
			break;
		}
		for (int i: g) {
			if (max < i) {
				max = i;
			}
		}
		return max;
	}

	private int getSVGArbol (arBinario<Integer> ab, int rad) {
		int numeroHojas = (int) Math.pow(2,ab.prof());
		return (numeroHojas+(numeroHojas/2)+2)*(rad*2);
	}

	private String getVertices (gr<Integer> g, double radG, int rad, double x, double y)  {
		Verticegr<Integer> vi = null;
		String vertices = "", aristas = "", c = "blanco", le = "negro";
		double an = Math.toRadians(360 / g.getElementos());
		Arreglos arr = new Arreglos();
		double angu = 0, xi, yi;
		int i = 0;
		VerticeCoordenada cordi;
		VerticeCoordenada[] cor = new VerticeCoordenada[g.getElementos()];

		for (int v: g) {
			xi = radG*Math.cos(angu);
			yi = radG*Math.sin(angu);
			vertices += u.cirDatos(v, x+xi, y+yi, rad, c, le);

			vi = g.v(v);
			cordi = new VerticeCoordenada(vi, x+xi, y+yi);
			cor[i] = cordi;

			angu += an;
			i += 1;
		}

		arr.quickSort(cor);
		for (VerticeCoordenada v: cor) {
			for (Verticegr<Integer> vecino: v.v.ve()) {
				cordi = new VerticeCoordenada(vecino, 0, 0);
				cordi = cor[arr.busquedaBinaria(cor, cordi)];
				aristas += u.li(v.x, v.y, cordi.x, cordi.y);
			}
		}

		return aristas + vertices;

		private int getSVGArbol (arBinario<Integer> ab, int rad) {
			return (ab.prof()+3)*(rad*2);
		}

		private VerticearBinario<Integer> getMaximo(VerticearBinario<Integer> v) {
			VerticearBinario<Integer> iz = null, derecho = null, max;
			if (v == null) {
				return null;
			}
			if (!v.siizuierdo() && !v.siDerecho()) {
				return v;
			} else {
				if (v.siizuierdo()) {
					iz = this.getMaximo(v.getizuierdo());
				}
				if (v.siDerecho()) {
					derecho = this.getMaximo(v.getDerecho());
				}
			}

			if (iz != null && der != null) {
				max = ((iz.get().compareTo(derecho.get())>=0)? iz : derecho);
			} else {
				if (iz == null){
					max = derecho;
				} else {
					max = iz;
				}
			}

			return ((v.get().compareTo(max.get())>=0)? v : max);
		}

		private String getVertices (VerticearBinario<Integer> v, int rad, int i, int x, int y, EstructuraDatos arbol_a) {
			String arbol = "", c = "blanco", le = "negro";
			i /= 2;

			if (v.siizuierdo()) {
				arbol += u.li(x, y, x-i, y+rad*2);
				arbol += getVertices(v.getizuierdo(), rad, i, x-i, y+rad*2, arbol_a);
			}
			if (v.siDerecho()) {
				arbol += u.li(x, y, x+i, y+rad*2);
				arbol += getVertices(v.getDerecho(), rad, i, x+i, y+rad*2, arbol_a);
			}

			if (v.toString().charAt(0) == ('R')) {
				c = "rojo";
				le = "blanco";
			}
			if (v.toString().charAt(0) == ('N')) {
				c = "negro";
				le = "blanco";
			}

			arbol += u.cirDatos(v.get(), x, y, rad, c, le);
			if (arbol_a == EstructuraDatos.ArbolAVL) {
				arbol += u.tex(v.toString().split(" ")[1], x+rad, y-(rad/2), "text-anchor='middle'");
			}
			return arbol;
		}
	}
}
