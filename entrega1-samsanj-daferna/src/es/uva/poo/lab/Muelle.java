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
	
	// TODO: Docs de que devuelve al no encontrarlo (-1)
	public int getNivelPlazaContenedor(String codigoIdentificador) {
		for (int i = 0; i < getNumeroPlazas(); i++) {
			for (int j = 0; j < getMaximoApilables(); j++) {
				if (plazas[i][j].getCodigoIdentificador() == codigoIdentificador)
					return j;
			}
		}
		
		return -1;
	}
	
	// TODO: private quiza?
	public boolean posibleApilar(int plaza) {
		if (plazas[plaza][getMaximoApilables() - 1] != null) return false;
		
		for (int i = getMaximoApilables() - 1; i >= 0; i--) {
			if (plazas[plaza][i] != null && !plazas[plaza][i].getTecho()) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean plazaValida(int plaza) {
		return plaza < 0 || plaza > getNumeroPlazas() - 1;
	}
	
	public void asignarContenedor(Contenedor contenedor, int plaza) {
		if (!plazaValida(plaza))
			throw new IllegalArgumentException("Plaza no válida.");
		
		if (!posibleApilar(plaza)) 
			throw new IllegalArgumentException("No es posible asignar el contenedor a la plaza.");
		
		int i = 0;
		boolean colocado = false;
		while (i < getMaximoApilables() && !colocado) {
			if (plazas[plaza][i] == null) {
				 // Creo una copia del contenedor para evitar problemas con referencias
				plazas[plaza][i] = new Contenedor(contenedor);
				colocado = true;
			}
			i++;
		}
	}
	
	public Contenedor desapilarContenedor(int plaza) {
		if (!plazaValida(plaza))
			throw new IllegalArgumentException("Plaza no válida.");
			
		if (plazas[plaza][0] == null)
			throw new IllegalArgumentException("No es posible desapilar el contenedor ya que no hay contenedores en esa plaza.");
		
		int i = getMaximoApilables() - 1;
		boolean contenedorEncontrado = false;
		
		while (i >= 0 && !contenedorEncontrado) {
			if (plazas[plaza][i] == null) i--;
			else contenedorEncontrado = true;
		}

		Contenedor contenedorDesapilado = plazas[plaza][i];
		plazas[plaza][i] = null;
		
		return contenedorDesapilado;
	}
}
