package es.uva.poo.lab;

import es.uva.inf.poo.maps.GPSCoordinate;

public class Muelle {

	private int codigoIdentificacion;
	private GPSCoordinate localizacion;
	private boolean operativo;
	private Contenedor[][] plazas;
	private int numeroPlazas;
	private int maximoContenedoresApilables;

	public Muelle(int codigoIdentificacion, GPSCoordinate localizacion, boolean operativo, int numeroPlazas,
			int maximoContenedoresApilables) {
		
		if (codigoIdentificacion < 10 || codigoIdentificacion > 99)
			throw new IllegalArgumentException("El código de identificación debe ser un número de dos digitos.");

		if (localizacion == null)
			throw new IllegalArgumentException("La localización no puede ser null.");

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

	public Muelle(Muelle muelle) {
		if (muelle == null)
			throw new IllegalArgumentException("El muelle a copiar no puede ser null.");

		codigoIdentificacion = muelle.getCodigoIdentificacion();
		localizacion = muelle.getLocalizacion();
		operativo = muelle.getOperativo();
		numeroPlazas = muelle.getNumeroPlazas();
		maximoContenedoresApilables = muelle.getMaximoContenedoresApilables();
		plazas = muelle.getPlazas();

	}

	public Contenedor[][] getPlazas() {
		Contenedor[][] plazasCopia = new Contenedor[getNumeroPlazas()][getMaximoContenedoresApilables()];

		for (int i = 0; i < plazasCopia.length; i++) {
			plazasCopia[i] = getContenedoresEnPlaza(i);
		}

		return plazas;
	}

	public void setPlazas(Contenedor[][] plazas) {
		for (int i = 0; i < plazas.length; i++) {
			for (int j = 0; j < plazas[i].length; j++) {
				this.plazas[i][j] = plazas[i][j] == null ? null : new Contenedor(plazas[i][j]);
			}
		}
	}

	public int getMaximoContenedoresApilables() {
		return maximoContenedoresApilables;
	}

	public void setMaximoContenedoresApilables(int maximoContenedoresApilables) {
		this.maximoContenedoresApilables = maximoContenedoresApilables;
	}

	public int getCodigoIdentificacion() {
		return codigoIdentificacion;
	}

	public void setCodigoIdentificacion(int codigoIdentificacion) {
		this.codigoIdentificacion = codigoIdentificacion;
	}

	public GPSCoordinate getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(GPSCoordinate localizacion) {
		this.localizacion = localizacion;
	}

	public int getNumeroPlazas() {
		return numeroPlazas;
	}

	public void setNumeroPlazas(int numeroPlazas) {
		this.numeroPlazas = numeroPlazas;
	}

	public boolean getOperativo() {
		return operativo;
	}

	public void setOperativo(boolean operativo) {
		this.operativo = operativo;
	}

	public int getPlazasVacias() {
		int plazasVacias = 0;

		for (int i = 0; i < getNumeroPlazas(); i++) {
			if (plazas[i][0] == null)
				plazasVacias++;
		}

		return plazasVacias;
	}

	public Contenedor[] getContenedoresEnPlaza(int plaza) {
		if (plaza < 0 || plaza > getNumeroPlazas())
			throw new IllegalArgumentException("Número de plaza invalido.");

		Contenedor[] contenedores = new Contenedor[plazas[plaza].length];

		for (int i = 0; i < contenedores.length; i++) {
			contenedores[i] = plazas[plaza][i] == null ? null : new Contenedor(plazas[plaza][i]);
		}

		return contenedores;
	}

	public int getPlazasLlenas() {
		int plazasLlenas = 0;

		for (int i = 0; i < getNumeroPlazas(); i++) {
			if (plazas[i][getMaximoContenedoresApilables() - 1] != null)
				plazasLlenas++;
			else {
				int j = 0;
				boolean flag = false;
				while (j < getMaximoContenedoresApilables() - 1 && !flag) {

					if (plazas[i][j] == null)
						flag = true;
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

	// TODO: Hacer la documentacion de que devuelve error al no encontrarlo

	public int getPlazaContenedor(String codigoIdentificador) {
		if (codigoIdentificador == null)
			throw new IllegalArgumentException("El código de identificación no puede ser null.");

		for (int i = 0; i < getNumeroPlazas(); i++) {
			for (int j = 0; j < getMaximoContenedoresApilables(); j++) {
				if (plazas[i][j].getCodigoIdentificador().equals(codigoIdentificador))
					return i;
			}
		}

		throw new IllegalArgumentException("No se encontró el contenedor con ese código de identificación.");
	}

	public int getNivelPlazaContenedor(String codigoIdentificador) {
		if (codigoIdentificador == null)
			throw new IllegalArgumentException("El código de identificación no puede ser null.");

		for (int i = 0; i < getNumeroPlazas(); i++) {
			for (int j = 0; j < getMaximoContenedoresApilables(); j++) {
				if (plazas[i][j].getCodigoIdentificador().equals(codigoIdentificador))
					return j;
			}
		}

		throw new IllegalArgumentException("No se encontró el contenedor con ese código de identificación.");
	}

	public boolean posibleApilar(Contenedor contenedor, int plaza) {
		if (contenedor == null)
			throw new IllegalArgumentException("El contenedor a consultar no puede ser null.");

		if (plazas[plaza][getMaximoContenedoresApilables() - 1] != null)
			return false;

		for (int i = getMaximoContenedoresApilables() - 1; i >= 0; i--) {
			if (plazas[plaza][i] != null) {
				return plazas[plaza][i].getTecho();
			}
		}

		return true;
	}

	private boolean plazaValida(int plaza) {
		return plaza < 0 || plaza > getNumeroPlazas() - 1;
	}
	
	public void apilarContenedor(Contenedor contenedor, int plaza) {
		if (!plazaValida(plaza))
			throw new IllegalArgumentException("Plaza no válida.");
		
		if (!posibleApilar(contenedor, plaza)) 
			throw new IllegalArgumentException("No es posible apilar el contenedor a la plaza.");
		
		int i = 0;
		boolean colocado = false;
		while (i < getMaximoContenedoresApilables() && !colocado) {
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
			throw new IllegalArgumentException(
					"No es posible desapilar el contenedor ya que no hay contenedores en esa plaza.");

		int i = getMaximoContenedoresApilables() - 1;
		boolean contenedorEncontrado = false;

		while (i >= 0 && !contenedorEncontrado) {
			if (plazas[plaza][i] == null)
				i--;
			else
				contenedorEncontrado = true;
		}

		Contenedor contenedorDesapilado = plazas[plaza][i];
		plazas[plaza][i] = null;

		return contenedorDesapilado;
	}
}
