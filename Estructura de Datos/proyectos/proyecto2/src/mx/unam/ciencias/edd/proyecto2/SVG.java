package mx.unam.ciencias.edd.proyecto2;

public class SVG {

	public String Fle (double x, double y) {
		return this.tex("↔", x, y, "font-weight='bold'");
	}

	public String tex (String tex, double x, double y, String ex) {
		return "<text x='"+ x +"' y='"+ y +"' font-size='20' "+ ex +">"+ tex +"</text>";
	}

	public String rect (int n, double x, double y, int p, int a) {
		return this.rec(this.numeroLargo(n)*10+p*2, a, x, y) + this.nu(n, x+p, y+20,"");
	}

	public String cir (double rad, double x, double y, String c) {
		String c_s = "";
		if (!c.equals("")) {
			c_s="fill='"+ c +"'";
		}
		return "<circle cx='"+ x +"' cy='"+ y +"' r='"+ rad +"' "+ c_s +" stroke='black' stroke-width='1'/>";
	}

	public String li (double x1, double y1, double x2, double y2) {
		return "<line x1='"+ x1 +"' y1='"+ y1 +"' x2='"+ x2 +"' y2='"+ y2 +"' stroke='black' stroke-width='2'/>";
	}

	private int numeroLargo (int numero) {
		int i = 1;
		while (numero >= 10) {
			numero /= 10;
			i++;
		}
		return i;
	}

	public String cirDatos (int n, double x, double y, int rad, String c, String le) {
		return this.cir(rad, x, y, c) + this.nu(n, x, y+5, "text-anchor='middle' fill='"+ le +"'");
	}

	public String recta (String tex, double x, double y) {
		return this.rec(tex.length()*10+10, 25, x, y) + this.tex(tex, x+5, y+20, "");
	}

	public String nu (int n, double x, double y, String ex) {
		return "<text x='"+ x +"' y='"+ y +"' font-size='20' "+ ex +">"+ n +"</text>";
	}

	public String rec (double ba, double a, double x, double y) {
		return "<rect x='"+ x +"' y='"+ y +"' width='"+ ba +"' height='"+ a +"' stroke='black' stroke-width='1' fill='white'/>";
	}

	public String fleDe (double x, double y) {
		return this.tex("→", x, y, "font-weight='bold'");
	}
}
