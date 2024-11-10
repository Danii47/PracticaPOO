package es.uva.poo.lab;

import static java.lang.Math.pow;
import java.util.ArrayList;
import java.util.Arrays;

public class Contenedor {

	/**
	 * Enum que representa los posibles estados del contenedor.
	 * [TRANSITO] El contenedor se encuentra en transito.
	 * [RECOGIDA] El contenedor se encuentra en recogida.
	 */
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
	private ArrayList<Trayecto> trayectos;

	/**
	 * Constructor de la clase Contenedor.
	 * 
	 * @param codigoIdentificador Identificador único del contenedor.
	 * @param pesoKg Peso en kilogramos.
	 * @param cargaUtilMaximaKg Carga útil máxima en kilogramos.
	 * @param volumenMetrosCubicos Volumen en metros cúbicos.
	 * @param estado Estado inicial del contenedor.
	 * @param techo Indica si el contenedor tiene techo o no.
	 * 
	 * @throws IllegalArgumentException Si algún parámetro es negativo / null.
	 */
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
		trayectos = new ArrayList<Trayecto>();
	}
	
	/**
	 * Constructor de copia de la clase Contenedor.
	 * 
	 * @param contenedor Contenedor a copiar.
	 * 
	 * @throws IllegalArgumentException Si el contenedor es null.
	 */
	public Contenedor(Contenedor contenedor) {
		if (contenedor == null)
			throw new IllegalArgumentException("El contenedor a copiar no puede ser null");
		
		this.codigoIdentificador = contenedor.getCodigoIdentificador();
		this.pesoKg = contenedor.getPesoKg();
		this.cargaUtilMaximaKg = contenedor.getCargaUtilMaximaKg();
		this.volumenMetrosCubicos = contenedor.getVolumenMetrosCubicos();
		this.estado = contenedor.getEstado();
		this.techo = contenedor.getTecho();
		this.trayectos = new ArrayList<Trayecto>(Arrays.asList(contenedor.getTrayectos()));
	}

	/**
	 * Establece el estado del contenedor a RECOGIDA.
	 */
	public void setEnRecogida() {
		estado = ESTADOS.RECOGIDA;
	}

	/**
	 * Establece el estado del contenedor a TRANSITO.
	 */
	public void setEnTransito() {
		estado = ESTADOS.TRANSITO;
	}

	/**
	 * Establece el estado del contenedor.
	 * 
	 * @param estado Nuevo estado del contenedor.
	 * 
	 * @throws IllegalArgumentException Si el estado es null.
	 */
	public void setEstado(ESTADOS estado) {
		if (estado == null)
			throw new IllegalArgumentException("El estado introducido no puede ser null.");
		
		this.estado = estado;
	}
	
	/**
	 * Establece si el contenedor tiene techo o no.
	 * 
	 * @param techo true si el contenedor tiene techo; false en caso contrario.
	 */
	public void setTecho(boolean techo) {
		this.techo = techo;
	}
	
	/**
	 * Indica si el contenedor tiene techo.
	 * 
	 * @return true si el contenedor tiene techo; false en caso contrario.
	 */
	public boolean getTecho() {
		return techo;
	}

	/**
	 * Obtiene el código identificador del contenedor.
	 * 
	 * @return Código identificador del contenedor.
	 */
	public String getCodigoIdentificador() {
		return codigoIdentificador;
	}
	
	/**
	 * Obtiene el volumen del contenedor en metros cúbicos.
	 * 
	 * @return Volumen en metros cúbicos.
	 */
	public double getVolumenMetrosCubicos() {
		return volumenMetrosCubicos;
	}

	/**
	 * Obtiene el volumen del contenedor en pies cúbicos.
	 * 
	 * @return Volumen en pies cúbicos.
	 */
	public double getVolumenPiesCubicos() {
		return getVolumenMetrosCubicos() * CONVERSION_PIES;
	}

	/**
	 * Obtiene la carga útil máxima en kilogramos.
	 * 
	 * @return Carga útil máxima en kilogramos.
	 */
	public double getCargaUtilMaximaKg() {
		return cargaUtilMaximaKg;
	}
	
	/**
	 * Obtiene el peso del contenedor en kilogramos.
	 * 
	 * @return Peso en kilogramos.
	 */
	public double getPesoKg() {
		return pesoKg;
	}
	
	/**
	 * Establece el peso del contenedor en kilogramos.
	 * 
	 * @param pesoKg Nuevo peso en kilogramos.
	 * 
	 * @throws IllegalArgumentException Si el peso es menor o igual a cero.
	 */
	public void setPesoKg(double pesoKg) {
		if (pesoKg <= 0)
			throw new IllegalArgumentException("El peso introducido debe ser positivo");
		
		this.pesoKg = pesoKg;
	}
	
	/**
	 * Obtiene el estado actual del contenedor.
	 * 
	 * @return Estado actual del contenedor.
	 */
	public ESTADOS getEstado() {
		return estado;
	}

	/**
	 * Obtiene el peso del contenedor en libras.
	 * 
	 * @return Peso en libras.
	 */
	public double getPesoLibras() {
		return getPesoKg() * CONVERSION_LIBRAS;
	}
	
	/**
	 * Obtiene una copia del array de trayectos del contenedor.
	 * 
	 * @return Array de trayectos.
	 */
	public Trayecto[] getTrayectos() {
		Trayecto[] trayectosArray = new Trayecto[trayectos.size()];
		for (int i = 0; i < trayectos.size(); i++) {
			trayectosArray[i] = new Trayecto(trayectos.get(i));
		}
		
		return trayectosArray;
	}
	
	/**
	 * Agrega un nuevo trayecto al contenedor.
	 * 
	 * @param trayecto Trayecto a agregar.
	 */
	public void agregarTrayecto(Trayecto trayecto) {
		trayectos.add(new Trayecto(trayecto));
	}

	/**
	 * Comprueba la validez del código identificador del contenedor.
	 * 
	 * @param codigoIdentificador Código identificador a comprobar.
	 * 
	 * @throws IllegalArgumentException Si el código es inválido.
	 */
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
	
	/**
	 * Calcula el precio total del transporte del contenedor a partir de sus trayectos.
	 * 
	 * @return Precio total del transporte.
	 */
	public double getPrecioAPartirDeTrayectos() {
		double precio = 0;
		
		for (Trayecto trayecto: trayectos) {
			precio += trayecto.getCosteTrayecto();
		}
		
		return precio;
	}
	
	/**
	 * Comprueba si los trayectos de este contenedor son iguales a los de otro contenedor.
	 * 
	 * @param contenedor Contenedor con el que se comparan los trayectos.
	 * 
	 * @return true si los trayectos son iguales; false en caso contrario.
	 */
	private boolean comprobarTrayectosIguales(Contenedor contenedor) {
		if (getTrayectos().length != contenedor.getTrayectos().length) return false;
		
		Trayecto[] contenedorOtroTrayectos = contenedor.getTrayectos();
		
		for (int i = 0; i < getTrayectos().length; i++) {
			if (!getTrayectos()[i].equals(contenedorOtroTrayectos[i])) return false;
		}
		
		return true;
	}
	
	/**
	 * Compara este contenedor con otro objeto para verificar si son iguales.
	 * 
	 * @param contenedor Objeto con el que se compara.
	 * 
	 * @return true si los contenedores son iguales; false en caso contrario.
	 */
	@Override
	public boolean equals(Object contenedor) {
		if (!(contenedor instanceof Contenedor)) return false;
		
		Contenedor c = (Contenedor) contenedor;
		
		return (
			codigoIdentificador.equals(c.getCodigoIdentificador()) &&
			pesoKg == c.getPesoKg() &&
			cargaUtilMaximaKg == c.getCargaUtilMaximaKg() &&
			volumenMetrosCubicos == c.getVolumenMetrosCubicos() &&
			estado == c.getEstado() &&
			techo == c.getTecho() &&
			comprobarTrayectosIguales(c)	
		);
	}
}
