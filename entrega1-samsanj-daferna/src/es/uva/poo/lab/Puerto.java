package es.uva.poo.lab;

import java.util.ArrayList;
import java.util.Arrays;

import es.uva.inf.poo.maps.GPSCoordinate;

/** 
 * @author daferna & samsanj
 * 
 * Clase que representa un puerto con un código de identificación y una lista de muelles.
 * 
 */
public class Puerto {
	private String codigoIdentificacion;
	private ArrayList<Muelle> muelles;
	
	/**
	 * Constructor de la clase Puerto.
	 * 
	 * @param codigoIdentificacion Código de identificación del puerto con letras mayúsculas con el nombre del país, un guión y finalmente tres letras mayúsculas con el código de la localidad .
	 * @param muelles Array de muelles que pertenecen al puerto.
	 * 
	 * @throws IllegalArgumentException Si el código de identificación es null. Si el código de identificación no tiene 6 cifras. Si el código de identificación no tiene dos mayúsculas en los dos primeros digitos. Si el código de identificación no tiene un guión en el tercer digito. Si el código de identificación no tiene tres mayúsculas en los tres últimos digitos.
   */
	public Puerto(String codigoIdentificacion, Muelle[] muelles) {		
		comprobarcodigoIdentificacion(codigoIdentificacion);
		
		if (muelles == null)
			throw new IllegalArgumentException("El array de muelles no puede ser null.");
		
		this.codigoIdentificacion = codigoIdentificacion;
		this.muelles = new ArrayList<Muelle>();
		
		for (int i = 0; i < muelles.length; i++) {
			this.muelles.add(new Muelle(muelles[i]));
		}
	}
	
	/**
	 * Constructor de la clase Puerto.
	 * 
	 * @param codigoIdentificacion Código de identificación del puerto con letras mayúsculas con el nombre del país, un guión y finalmente tres letras mayúsculas con el código de la localidad .
	 * 
	 * @throws IllegalArgumentException Si el código de identificación no tiene la longitud adecuada.
	 * @throws IllegalArgumentException Si el código de identificación no tiene el formato adecuado.
	 */
	public Puerto(String codigoIdentificacion) {
		comprobarcodigoIdentificacion(codigoIdentificacion);
		
		this.codigoIdentificacion = codigoIdentificacion;
		this.muelles = new ArrayList<Muelle>();
	}
	
	/**
	 * Constructor de copia de la clase Puerto.
	 * 
	 * @param puerto Puerto a copiar.
	 * 
	 * @throws IllegalArgumentException Si el puerto introducido es null.
	 */
	public Puerto(Puerto puerto) {
		if (puerto == null)
			throw new IllegalArgumentException("El puerto introducido no puede ser null.");
		
		this.codigoIdentificacion = puerto.getCodigoIdentificacion();
		this.muelles = new ArrayList<Muelle>(Arrays.asList(puerto.getMuelles()));
	}
	
	/**
	 * Método que comprueba si el código de identificación tiene el formato adecuado.
	 * 
	 * @param codigoIdentificacion Código de identificación a comprobar.
	 * 
	 * @throws IllegalArgumentException Si el código de identificación es null. Si el código de identificación no tiene 6 cifras. Si el código de identificación no tiene dos mayúsculas en los dos primeros digitos. Si el código de identificación no tiene un guión en el tercer digito. Si el código de identificación no tiene tres mayúsculas en los tres últimos digitos.
	 */
	private void comprobarcodigoIdentificacion(String codigoIdentificacion) {
		if (codigoIdentificacion == null)
			throw new IllegalArgumentException("El codigo de identificación no puede ser null.");
		
		if (codigoIdentificacion.length() != 6)
			throw new IllegalArgumentException("El código de identificación no tiene la longitud adecuada.");
	
		for (int i = 0; i < 2; i++) {
			if (codigoIdentificacion.charAt(i) < 'A' || codigoIdentificacion.charAt(i) > 'Z')
				throw new IllegalArgumentException("Los dos primeros caracteres deben ser el código de nombre del país en mayusculas.");
		}
		
		if (codigoIdentificacion.charAt(2) != '-')
			throw new IllegalArgumentException("El tercer caracter del código debe ser un guión.");
		
		for (int i = 3; i < codigoIdentificacion.length(); i++) {
			if (codigoIdentificacion.charAt(i) < 'A' || codigoIdentificacion.charAt(i) > 'Z')
				throw new IllegalArgumentException("Los tres últimos caracteres deben ser el código de nombre de la localidad en mayusculas.");
		}
	}
	
	/**
	 * Método que devuelve el código de identificación del puerto.
	 * 
	 * @return Código de identificación del puerto.
	 */
	public String getCodigoIdentificacion() {
		return codigoIdentificacion;
	}
	
	/**
	 * Método que devuelve un array con los muelles del puerto.
	 * 
	 * @return Array con los muelles del puerto.
	 */
	public Muelle[] getMuelles() {
		Muelle[] muelles = new Muelle[this.muelles.size()];
		for (int i = 0; i < muelles.length; i++) {
			muelles[i] = new Muelle(this.muelles.get(i));
		}
		
		return muelles;
	}
	
	/**
	 * Método que devuelve el número de muelles del puerto.
	 * 
	 * @return Número de muelles del puerto.
	 */
	public int getNumeroMuelles() {
		return muelles.size();
	}
	
	/**
	 * Método que devuelve un muelle del puerto por su código de índice.
	 * 
	 * @param codigoIdentificacion Índice del muelle.
	 * 
	 * @return Muelle con el índice especificado.
	 * 
	 * @throws IllegalArgumentException Si el índice es negativo o mayor o igual que el número de muelles.
	 */
	public Muelle getMuellePorIndice(int indice) {
		if (indice < 0 || indice >= getNumeroMuelles())
			throw new IllegalArgumentException("El número de indice no es válido.");
		
		return new Muelle(muelles.get(indice));
	}
	
	/**
	 * Método que devuelve si el puerto está operativo en función de si todos sus muelles están operativos.
	 * 
	 * @return True si el puerto está operativo, false en caso contrario.
	 */
	public boolean getOperativo() {
		
		for (int i = 0; i < getNumeroMuelles(); i++) {
			if (muelles.get(i).getOperativo())
				return true;
		}
		
		return false;
	}
	
	/**
	 * Método que añade un muelle al puerto si se puede.
	 * 
	 * @param muelle Muelle a añadir.
	 * 
	 * @throws IllegalArgumentException Si el muelle es null. Si ya existe un muelle con el mismo código de identificación.
	 */
	public void agregarMuelle(Muelle muelle) {
		if (muelle == null)
			throw new IllegalArgumentException("El muelle no puede ser null.");
		
		for (Muelle m: muelles) {
			if (m.getCodigoIdentificacion() == muelle.getCodigoIdentificacion())
				throw new IllegalArgumentException("Ya existe un muelle con ese código de identificación.");
		}
		
		muelles.add(new Muelle(muelle));
	}
	
	/**
	 * Método que elimina un muelle del puerto a partir de un código de identificación.
	 * 
	 * @param codigoIdentificacion Código de identificación del muelle a eliminar.
	 * 
	 * @throws IllegalArgumentException Si el código de identificación no es un número de dos cifras. Si no se encuentra un muelle con el código de identificación especificado.
	 */
	public void eliminarMuelle(int codigoIdentificacion) {
		if (codigoIdentificacion < 10 || codigoIdentificacion > 99)
			throw new IllegalArgumentException("El código de identificación debe ser un número de dos digitos.");
		
		for (int i = 0; i < muelles.size(); i++) {
			if (muelles.get(i).getCodigoIdentificacion() == codigoIdentificacion) {
				muelles.remove(i);
				return;
			}
		}
		
		throw new IllegalArgumentException("No se encontró un muelle con el código de identificación especificado.");
	}
	
	/**
	 * Método que devuelve si el puerto está completo en función de si todos sus muelles están llenos.
	 * 
	 * @return True si el puerto está completo, false en caso contrario.
	 */
	public boolean getCompleto() {
		for (Muelle muelle: muelles) {
			if (muelle.getPlazasLlenas() != muelle.getNumeroPlazas())
				return false;
		}
		
		return true;
	}
	
	/**
	 * Método que devuelve un array con los muelles operativos del puerto.
	 * 
	 * @return Array con los muelles operativos del puerto.
	 */
	public Muelle[] getMuellesOperativos() {
		ArrayList<Muelle> muellesOperativosList = new ArrayList<Muelle>();

	    for (Muelle muelle: muelles) {
	        if (muelle.getOperativo()) {
	            muellesOperativosList.add(new Muelle(muelle));
	        }
	    }

	    return muellesOperativosList.toArray(new Muelle[0]);
	}
	
	/**
	 * Método que devuelve un array con los muelles con espacio del puerto.
	 * 
	 * @return Array con los muelles con espacio del puerto.
	 */
	public Muelle[] getMuellesConEspacio() {
		ArrayList<Muelle> muellesConEspacio = new ArrayList<>();

	    for (Muelle muelle: muelles) {
	        if (muelle.getPlazasLlenas() != muelle.getNumeroPlazas()) {
	        	muellesConEspacio.add(new Muelle(muelle));
	        }
	    }

	    return muellesConEspacio.toArray(new Muelle[0]);
	}
	
	/**
	 * Método que devuelve un array con los muelles cercanos a una localización en función de una distancia.
	 * 
	 * @param localizacion Localización a la que se comparan los muelles.
	 * @param distancia Distancia máxima a la que se encuentran los muelles.
	 * 
	 * @return Array con los muelles cercanos a la localización.
	 */
	public Muelle[] getMuellesCercanos(GPSCoordinate localizacion, double distancia) {
		if (localizacion == null)
			throw new IllegalArgumentException("La localizacion no puede ser null.");
		
		ArrayList<Muelle> muellesCercanos = new ArrayList<>();

	    for (Muelle muelle: muelles) {
	        if (muelle.getLocalizacion().getDistanceTo(localizacion) <= distancia){
	        	muellesCercanos.add(new Muelle(muelle));
	        }
	    }

	    return muellesCercanos.toArray(new Muelle[0]);
	}
	
	/**
	 * Método que devuelve si un muelle pertenece al puerto.
	 * 
	 * @param muelle Muelle a comprobar.
	 * 
	 * @return True si el muelle pertenece al puerto, false en caso contrario.
	 */
	public boolean muellePerteneceAlPuerto(Muelle muelle) {
		if (muelle == null)
			throw new IllegalArgumentException("El muelle introducido no puede ser null.");
		
		for (Muelle m: muelles) {
			if (m.equals(muelle)) return true;
		}
		
		return false;
	}
	
	/**
	 * Método que comprueba si los muelles de dos puertos son iguales.
	 * 
	 * @param puerto Puerto a comparar.
	 * 
	 * @return True si los muelles de los dos puertos son iguales, false en caso contrario.
	 */
	private boolean comprobarMuellesIguales(Puerto puerto) {
		if (getMuelles().length != puerto.getMuelles().length) return false;
		Muelle[] puertoOtroMuelles = puerto.getMuelles();
		
		for (int i = 0; i < getMuelles().length; i++) {
			if (!getMuelles()[i].equals(puertoOtroMuelles[i])) return false;
		}
		
		return true;
	}
	
	/**
	 * Método que devuelve si dos puertos son iguales.
	 * 
	 * @param puerto Puerto a comparar.
	 * 
	 * @return True si los puertos son iguales, false en caso contrario.
	 */
	@Override
	public boolean equals(Object puerto) {
		if (!(puerto instanceof Puerto)) return false;
		
		Puerto p = (Puerto) puerto;
		
		return (
			codigoIdentificacion.equals(p.getCodigoIdentificacion()) &&
			comprobarMuellesIguales(p)
		);
	}
}







