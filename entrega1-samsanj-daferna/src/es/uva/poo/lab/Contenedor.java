package es.uva.poo.lab;

import static java.lang.Math.pow;

public class Contenedor {

	public static enum ESTADOS {
        TRANSITO, RECOGIDA;
    }
	
	public static final double CONVERSION_PIES = 35.3147;
	public static final double CONVERSION_LIBRAS = 2.20462;

	private String codigoIdentificador;
	private double pesoKg;
	private double cargaUtilMaximaKg;
	private double volumenMetrosCubicos;
	private ESTADOS estado;
	private boolean techo;

	public Contenedor(String codigoIdentificador, double pesoKg, double cargaUtilMaximaKg, double volumenMetrosCubicos,
			ESTADOS estado, boolean techo) {
		comprobarCodigoIdentificador(codigoIdentificador);

		if (pesoKg <= 0)
			throw new IllegalArgumentException("El peso debe ser positivo.");

		if (cargaUtilMaximaKg <= 0)
			throw new IllegalArgumentException("La carga útil máxima debe ser positiva.");

		if (volumenMetrosCubicos <= 0)
			throw new IllegalArgumentException("El volumen debe ser positivo.");

		this.codigoIdentificador = codigoIdentificador;
		this.pesoKg = pesoKg;
		this.cargaUtilMaximaKg = cargaUtilMaximaKg;
		this.volumenMetrosCubicos = volumenMetrosCubicos;
		this.estado = estado;
		this.techo = techo;
	}
	
	public Contenedor(Contenedor contenedor) {
		if (contenedor == null)
			throw new IllegalArgumentException("El contenedor a copiar no puede ser null");
		
		this.codigoIdentificador = contenedor.getCodigoIdentificador();
		this.pesoKg = contenedor.getPesoKg();
		this.cargaUtilMaximaKg = contenedor.getCargaUtilMaximaKg();
		this.volumenMetrosCubicos = contenedor.getVolumenMetrosCubicos();
		this.estado = contenedor.getEstado();
		this.techo = contenedor.getTecho();
	}

	// TODO: Deberiamos crear todos los getters y setters?
	public void setEnRecogida() {
		estado = ESTADOS.RECOGIDA;
	}

	public void setEnTransito() {
		estado = ESTADOS.TRANSITO;
	}

	public void setEstado(ESTADOS estado) {
		if (estado == null)
			throw new IllegalArgumentException("El estado introducido no puede ser null.");
		
		this.estado = estado;
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
		return getVolumenMetrosCubicos() * CONVERSION_PIES;
	}

	public double getCargaUtilMaximaKg() {
		return cargaUtilMaximaKg;
	}
	
	public double getPesoKg() {
		return pesoKg;
	}
	
	public ESTADOS getEstado() {
		return estado;
	}

	public double getPesoLibras() {
		return getPesoKg() * CONVERSION_LIBRAS;
	}

	private static void comprobarCodigoIdentificador(String codigoIdentificador) {
		if (codigoIdentificador == null)
			throw new IllegalArgumentException("El código no puede ser null.");
		
		if (codigoIdentificador.length() != 11)
			throw new IllegalArgumentException("La logitud del código no es adecuada.");

		int digitoControl = 0;

		for (int i = 0; i < 3; i++) {
			if (codigoIdentificador.charAt(i) < 'A' || codigoIdentificador.charAt(i) > 'Z') {
				throw new IllegalArgumentException("Los tres primeros caracteres no son letras mayusculas.");
			} else {
				// Suma correspondiente al cálculo del dígito de control
				digitoControl += pow(2, i)
						* (codigoIdentificador.charAt(i) - 'A' + 10 + ((codigoIdentificador.charAt(i) - 'A' + 10) / 11));
			}
		}

		if (codigoIdentificador.charAt(3) != 'U' && codigoIdentificador.charAt(3) != 'J'
				&& codigoIdentificador.charAt(3) != 'Z') {
			throw new IllegalArgumentException("El cuarto caracter debe ser una 'U', 'J' o una 'Z'.");
		} else {
			// Suma correspondiente al cálculo del dígito de control
			digitoControl += pow(2, 3)
					* (codigoIdentificador.charAt(3) - 'A' + 10 + ((codigoIdentificador.charAt(3) - 'A' + 10) / 11));
		}

		for (int i = 4; i < 10; i++) {
			if (codigoIdentificador.charAt(i) < '0' || codigoIdentificador.charAt(i) > '9') {
				throw new IllegalArgumentException("Del caracter 5 al 10 deben ser números");
			} else {
				// Suma correspondiente al cálculo del dígito de control
				digitoControl += pow(2, i) * (codigoIdentificador.charAt(i) - '0');
			}
		}

		digitoControl = (digitoControl % 11 == 10) ? 0 : digitoControl % 11;

		if (digitoControl != codigoIdentificador.charAt(10) - '0') {
			throw new IllegalArgumentException("El dígito de control no es válido.");
		}
	}
	
	// TODO: Añadir método que calcule el precio del transporte total de un
	// contenedor a partir de sus trayectos
	// TODO: Ni idea de si es lo que pide
	public double getPrecioAPartirDeTrayectos(Trayecto[] trayectos) {
		double precio = 0;
		
		for (Trayecto trayecto: trayectos) {
			precio += trayecto.getCosteTrayecto();
		}
		
		return precio;
	}
}
