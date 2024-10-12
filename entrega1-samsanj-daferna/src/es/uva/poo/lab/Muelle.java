package es.uva.poo.lab;

import es.uva.inf.poo.maps.GPSCoordinate;

public class Muelle {
	
	private class Plaza {
		
	}
	
	private int codigoIdentificacion;
	private GPSCoordinate localizacion;
	private boolean operativo;
	private int plazas;
	private int maximoContenedoresApilables;
	
	public Muelle(int codigoIdentificacion, GPSCoordinate localizacion, boolean opeartivo, int plazas, int maximoContenedoresApilables) {
		if (codigoIdentificacion < 10 || codigoIdentificacion > 99)
			throw new IllegalArgumentException("El código de identificación debe ser un número de dos digitos.");
		
		if (plazas <= 0)
			throw new IllegalArgumentException("Las plazas deben ser positivas.");
		
		if (maximoContenedoresApilables <= 0)
			throw new IllegalArgumentException("El número de contenedores máximos apilados debe ser mayor que 0.");
		
	}
	
	
}
