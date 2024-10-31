package es.uva.poo.lab;

import es.uva.inf.poo.maps.GPSCoordinate;

public class Muelle {
	
	private int codigoIdentificacion;
	private GPSCoordinate localizacion;
	private boolean operativo;
	private Contenedor[][] plazas;
	private int numeroPlazas;
	private int maximoContenedoresApilables;
	
	public Muelle(int codigoIdentificacion, GPSCoordinate localizacion, boolean operativo, int numeroPlazas, int maximoContenedoresApilables) {
		if (codigoIdentificacion < 10 || codigoIdentificacion > 99)
			throw new IllegalArgumentException("El código de identificación debe ser un número de dos digitos.");
		
		if (numeroPlazas <= 0)
			throw new IllegalArgumentException("Las plazas deben ser positivas.");
		
		if (maximoContenedoresApilables <= 0)
			throw new IllegalArgumentException("El número de contenedores máximos apilados debe ser mayor que 0.");
		
		this.codigoIdentificacion = codigoIdentificacion;
		this.localizacion = localizacion;
		this.operativo = operativo;
		this.plazas = new Contenedor[numeroPlazas][maximoContenedoresApilables];
		this.numeroPlazas = numeroPlazas;
		this.maximoContenedoresApilables = maximoContenedoresApilables;
	}
	
	public int getNumeroPlazas() {
		return numeroPlazas;
	}
	
	// MÉTODO EXTRA
	public int getMaximoApilables() {
		return maximoContenedoresApilables;
	}
	
	public int getPlazasVacias() {
		int plazasVacias = 0;
		
		for (int i = 0; i < getNumeroPlazas(); i++) {
			if (plazas[i][0] == null) plazasVacias++;
		}
		
		return plazasVacias;
	}
	
	public int getPlazasLlenas() {
		int plazasLlenas = 0;
		
		
		for (int i = 0; i < getNumeroPlazas(); i++) {
			if (plazas[i][getMaximoApilables() - 1] != null) plazasLlenas++;
			else {
				int j = 0;
				boolean flag = false;
				while (j < getMaximoApilables() - 1 && !flag) {
					if (plazas[i][j] == null) flag = true;
					else if (!plazas[i][j].getTecho()) {
						plazasLlenas++;
						flag = true;
					}
					j++;
				}
			}
		}
		
		return plazasLlenas;
	}
	
	public int getPlazasSemillenas() {
		return getNumeroPlazas() - (getPlazasVacias() + getPlazasLlenas());
	}
	
	// TODO: Hacer la documentacion de que devuelve -1 al no encontrarlo
	public int getPlazaContenedor(String codigoIdentificador) {
		for (int i = 0; i < getNumeroPlazas(); i++) {
			for (int j = 0; j < getMaximoApilables(); j++) {
				if (plazas[i][j].getCodigoIdentificador() == codigoIdentificador)
					return i;
			}
		}
		
		return -1;
	}
}
