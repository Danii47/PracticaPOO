package es.uva.poo.lab;

import static java.lang.Math.pow;

public class Contenedor {

	private final double conversionPies = 35.3147;
	private final double conversionLibras = 2.20462;

	private String codigoIdentificador;
	private double pesoKg;
	private double cargaUtilMaximaKg;
	private double volumenMetrosCubicos;
	private String estado;
	private boolean techo;

	public Contenedor(String codigoIdentificador, double pesoKg, double cargaUtilMaximaKg, double volumenMetrosCubicos,
			String estado, boolean techo) {
		if (!comprobarCodigoIdentificador(codigoIdentificador))
			throw new IllegalArgumentException("El código de identificación no es válido.");

		if (pesoKg <= 0)
			throw new IllegalArgumentException("El peso debe ser positivo.");

		if (cargaUtilMaximaKg <= 0)
			throw new IllegalArgumentException("La carga útil máxima debe ser positiva.");

		if (volumenMetrosCubicos <= 0)
			throw new IllegalArgumentException("El volumen debe ser positivo.");

		if (!estado.equals("transito") && !estado.equals("recogida"))
			throw new IllegalArgumentException("El estado solo puede ser 'transito' o 'recogida'.");

		this.codigoIdentificador = codigoIdentificador;
		this.pesoKg = pesoKg;
		this.cargaUtilMaximaKg = cargaUtilMaximaKg;
		this.volumenMetrosCubicos = volumenMetrosCubicos;
		this.estado = estado;
		this.techo = techo;
	}
	
	public Contenedor(Contenedor contenedor) {
		this.codigoIdentificador = contenedor.getCodigoIdentificador();
		this.pesoKg = contenedor.getPesoKg();
		this.cargaUtilMaximaKg = contenedor.getCargaUtilMaximaKg();
		this.volumenMetrosCubicos = contenedor.getVolumenMetrosCubicos();
		this.estado = contenedor.getEstado();
		this.techo = contenedor.getTecho();
	}

	// TODO: Deberiamos crear todos los getters y setters?
	public void setEnRecogida() {
		estado = "recogida";
	}

	public void setEnTransito() {
		estado = "transito";
	}

	public void setTecho(boolean techo) {
		this.techo = techo;
	}
	
	public boolean getTecho() {
		return techo;
	}

	public String getCodigoIdentificador() {
		return codigoIdentificador;
	}
	
	public double getVolumenMetrosCubicos() {
		return volumenMetrosCubicos;
	}

	public double getVolumenPiesCubicos() {
		return getVolumenMetrosCubicos() * conversionPies;
	}

	public double getCargaUtilMaximaKg() {
		return cargaUtilMaximaKg;
	}
	
	public double getPesoKg() {
		return pesoKg;
	}
	
	public String getEstado() {
		return estado;
	}

	public double getPesoLibras() {
		return getPesoKg() * conversionLibras;
	}

	// TODO: Añadir método que calcule el precio del transporte total de un
	// contenedor a partir de sus trayectos

	// TODO: Está bien tirar un error dentro del método?
	private static boolean comprobarCodigoIdentificador(String codigoIdentificador) {
		if (codigoIdentificador.length() != 11)
			return false;

		int digitoControl = 0;

		for (int i = 0; i < 3; i++) {
			if (codigoIdentificador.charAt(i) < 'A' || codigoIdentificador.charAt(i) > 'Z') {
				return false;
			} else {
				// Suma correspondiente al cálculo del dígito de control
				digitoControl += pow(2, i)
						* (codigoIdentificador.charAt(i) - 'A' + 10 + ((codigoIdentificador.charAt(i) - 'A' + 10) / 11));
			}
		}

		if (codigoIdentificador.charAt(3) != 'U' && codigoIdentificador.charAt(3) != 'J'
				&& codigoIdentificador.charAt(3) != 'Z') {
			return false;
		} else {
			// Suma correspondiente al cálculo del dígito de control
			digitoControl += pow(2, 3)
					* (codigoIdentificador.charAt(3) - 'A' + 10 + ((codigoIdentificador.charAt(3) - 'A' + 10) / 11));
		}

		for (int i = 4; i < 10; i++) {
			if (codigoIdentificador.charAt(i) < '0' || codigoIdentificador.charAt(i) > '9') {
				return false;
			} else {
				// Suma correspondiente al cálculo del dígito de control
				digitoControl += pow(2, i) * (codigoIdentificador.charAt(i) - '0');
			}
		}

		digitoControl = (digitoControl % 11 == 10) ? 0 : digitoControl % 11;

		if (digitoControl != codigoIdentificador.charAt(10) - '0') {
			return false;
		}

		return true;

	}

	//public static void main(String[] args) {
		//Contenedor container = new Contenedor("BICU1234565", 0, 0, 0, "a", true);
	//}
}
