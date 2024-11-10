package es.uva.poo.lab;

import es.uva.inf.poo.maps.GPSCoordinate;


/**
 * Tipo de dato que representa un muelle de un puerto.
 * 
 * Un muelle tiene un código de identificación, una localización, un estado operativo, un número de plazas, un número máximo de contenedores apilables 
 * y un array de contenedores.
 *
 */
 
public class Muelle {

	private int codigoIdentificacion;
	private GPSCoordinate localizacion;
	private boolean operativo;
	private Contenedor[][] plazas;
	private int numeroPlazas;
	private int maximoContenedoresApilables;

	/**
	 * Constructor de la clase Muelle.
	 * 
	 * @param codigoIdentificacion Código de identificación del muelle de dos digitos.
	 * @param localizacion Localización del muelle.
	 * @param operativo Estado operativo del muelle (True si está operativo, False si no lo está).
	 * @param numeroPlazas Número de plazas del muelle.
	 * @param maximoContenedoresApilables Número máximo de contenedores apilables.
	 * 
	 * @throws IllegalArgumentException Si el código de identificación no es un número de dos dígitos, la localización es null, el número de plazas es negativo o el número máximo de contenedores apilables es negativo.
	 */	
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

	/**
	 * Constructor de copia de la clase Muelle.
	 * 
	 * @param muelle Muelle a copiar.
	 * 
	 * @throws IllegalArgumentException Si el muelle a copiar es null.
	 */
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
	
	/**
	 * Devuelve una copia de las plazas del muelle.
	 * 
	 * @return Copia de las plazas del muelle.
	 */
	public Contenedor[][] getPlazas() {
		Contenedor[][] plazasCopia = new Contenedor[getNumeroPlazas()][getMaximoContenedoresApilables()];

		for (int i = 0; i < plazasCopia.length; i++) {
			plazasCopia[i] = getContenedoresEnPlaza(i);
		}

		return plazas;
	}

	/**
	 * Devuelve el número máximo de contenedores apilables.
	 * 
	 * @return Número máximo de contenedores apilables.
	 */
	public int getMaximoContenedoresApilables() {
		return maximoContenedoresApilables;
	}
 

	/**
	 * Devuelve el código de identificación del muelle.
	 * 
	 * @return Código de identificación del muelle.
	 */
	public int getCodigoIdentificacion() {
		return codigoIdentificacion;
	}

	/**
	 * Establece el código de identificación del muelle.
	 * 
	 * @param codigoIdentificacion Nuevo código de identificación del muelle.
	 * 
	 * @throws IllegalArgumentException Si el código de identificación no es un número de dos dígitos.
	 */
	public void setCodigoIdentificacion(int codigoIdentificacion) {
		if (codigoIdentificacion < 10 || codigoIdentificacion > 99)
			throw new IllegalArgumentException("El código de identificación debe ser un número de dos digitos.");

		
		this.codigoIdentificacion = codigoIdentificacion;
	}

	/**
	 * Devuelve la localización del muelle.
	 * 
	 * @return Localización del muelle.
	 */
	public GPSCoordinate getLocalizacion() {
		return localizacion;
	}

	/**
	 * Establece la localización del muelle.
	 * 
	 * @param localizacion Nueva localización del muelle.
	 * 
	 * @throws IllegalArgumentException Si la localización es null.
	 */
	public void setLocalizacion(GPSCoordinate localizacion) {
		if (localizacion == null)
			throw new IllegalArgumentException("La localización no puede ser null.");
		this.localizacion = localizacion;
	}

	/**
	 * Devuelve el número de plazas del muelle.
	 * 
	 * @return Número de plazas del muelle.
	 */
	public int getNumeroPlazas() {
		return numeroPlazas;
	}

	/**
	 * Establece el número de plazas del muelle.
	 * 
	 * @param numeroPlazas Nuevo número de plazas del muelle.
	 * 
	 * @throws IllegalArgumentException Si el número de plazas es negativo.
	 */
	public boolean getOperativo() {
		return operativo;
	}

	/**
	 * Establece el estado operativo del muelle.
	 * 
	 * @param operativo Nuevo estado operativo del muelle.
	 */
	public void setOperativo(boolean operativo) {
		this.operativo = operativo;
	}

	/**
	 * Devuelve el número de plazas vacías del muelle.
	 * 
	 * @return Número de plazas vacías del muelle.
	 */
	public int getPlazasVacias() {
		int plazasVacias = 0;

		for (int i = 0; i < getNumeroPlazas(); i++) {
			if (plazas[i][0] == null)
				plazasVacias++;
		}

		return plazasVacias;
	}

	/**
	 * Devuelve los contenedores de una plaza del muelle.
	 * 
	 * @param plaza Número de la plaza a consultar.
	 * 
	 * @return Contenedores de la plaza.
	 * 
	 * @throws IllegalArgumentException Si el número de plaza no es válido (la plaza tiene que ser positiva y nunca mayor o igual que numeroPlaza).
	 */
	public Contenedor[] getContenedoresEnPlaza(int plaza) {
		if (!plazaValida(plaza))
			throw new IllegalArgumentException("Número de plaza invalido.");

		Contenedor[] contenedores = new Contenedor[plazas[plaza].length];

		for (int i = 0; i < contenedores.length; i++) {
			contenedores[i] = plazas[plaza][i] == null ? null : new Contenedor(plazas[plaza][i]);
		}

		return contenedores;
	}

	/**
	 * Devuelve el número de plazas llenas del muelle.
	 * 
	 * @return Número de plazas llenas del muelle.
	 */
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

	/**
	 * Devuelve el número de plazas semillenas del muelle.
	 * 
	 * @return Número de plazas semillenas del muelle.
	 */
	public int getPlazasSemillenas() {
		return getNumeroPlazas() - (getPlazasVacias() + getPlazasLlenas());
	}


	/**
	 * Devuelve el índice de la plaza de un contenedor del muelle apartir de su codigoIdentificador.
	 * 
	 * @param codigoIdentificador codigo del contenedor a buscar.
	 * 
	 * @return Índice de la plaza en la que se encuentra el contenedor.
	 * 
	 * @throws IllegalArgumentException Si el código de identificación es null o no se encontró un contenedor con ese codigo.
	 */
	public int getPlazaContenedor(String codigoIdentificador) {
		if (codigoIdentificador == null)
			throw new IllegalArgumentException("El código de identificación no puede ser null.");

		for (int i = 0; i < getNumeroPlazas(); i++) {
			for (int j = 0; j < getMaximoContenedoresApilables(); j++) {
				if (plazas[i][j] != null && plazas[i][j].getCodigoIdentificador().equals(codigoIdentificador))
					return i;
			}
		}

		throw new IllegalArgumentException("No se encontró el contenedor con ese código de identificación.");
	}

	/**
	 * Devuelve el nivel(altura) de la plaza de un contenedor del muelle apartir de su codigoIdentificador.
	 * 
	 * @param codigoIdentificador codigo del contenedor a buscar.
	 * 
	 * @return Nivel de la plaza en la que se encuentra el contenedor.
	 * 
	 * @throws IllegalArgumentException Si el código de identificación es null o no se encontró un contenedor con ese codigo.
	 */
	public int getNivelPlazaContenedor(String codigoIdentificador) {
		if (codigoIdentificador == null)
			throw new IllegalArgumentException("El código de identificación no puede ser null.");

		for (int i = 0; i < getNumeroPlazas(); i++) {
			for (int j = 0; j < getMaximoContenedoresApilables(); j++) {
				if (plazas[i][j]!=null && plazas[i][j].getCodigoIdentificador().equals(codigoIdentificador))
					return j;
			}
		}

		throw new IllegalArgumentException("No se encontró el contenedor con ese código de identificación.");
	}

	/**
	 * Comprueba si es posible apilar un contenedor en una plaza del muelle.
	 * 
	 * @param contenedor Contenedor a apilar.
	 * @param plaza Número de la plaza a consultar.
	 * 
	 * @return True si es posible apilar el contenedor, False si no es posible.
	 * 
	 * @throws IllegalArgumentException Si el contenedor es null, la plaza no es válida o el código de identificación ya existe en el muelle.
	 */
	public boolean posibleApilar(Contenedor contenedor, int plaza) {
		if (contenedor == null)
			throw new IllegalArgumentException("El contenedor a consultar no puede ser null.");

		if (!plazaValida(plaza))
			throw new IllegalArgumentException("Plaza no válida.");

		if (comprobarCodigoYaExistente(contenedor.getCodigoIdentificador()))
			throw new IllegalArgumentException("Ya existe un contenedor con ese código de identificación dentro de este muelle.");

		if (plazas[plaza][getMaximoContenedoresApilables() - 1] != null)
			return false;

		for (int i = getMaximoContenedoresApilables() - 1; i >= 0; i--) {
			if (plazas[plaza][i] != null) {
				return plazas[plaza][i].getTecho();
			}
		}

		return true;
	}

	/**
	 * Comprueba si una plaza es un índice válido.
	 * 
	 * @param plaza Índice de la plaza a comprobar.
	 * 
	 * @return True si la plaza es válida, False en caso contrario.
	 */
	private boolean plazaValida(int plaza) {
		return plaza >= 0 && plaza <= getNumeroPlazas() - 1;
	}


	/**
	 * Apila un contenedor en una plaza del muelle si es posible.
	 * 
	 * @param contenedor Contenedor a apilar.
	 * @param plaza Número de la plaza en la que apilar el contenedor.
	 * 
	 * @throws IllegalArgumentException Si la plaza no es válida, no es posible apilar el contenedor en la plaza o el contenedor es null.
	 */
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

	/**
	 * Desapila un contenedor de una plaza del muelle.
	 * 
	 * @param plaza Número de la plaza en la que desapilar el contenedor.
	 * 
	 * @return Contenedor desapilado.
	 * 
	 * @throws IllegalArgumentException Si la plaza no es válida o no hay contenedores en la plaza.
	 */
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
	
	/**
	 * Comprueba si las plazas de dos muelles son iguales.
	 * 
	 * @param muelle Muelle a comparar.
	 * 
	 * @return True si las plazas son iguales, False en caso contrario.
	 */
	private boolean comprobarContenedoresIguales(Muelle muelle) {

		

		if (getPlazas().length != muelle.getPlazas().length) return false;
		if (getPlazas()[0].length != muelle.getPlazas()[0].length) return false;
		
		Contenedor[][] muelleOtroContenedores = muelle.getPlazas();
		
		for (int i = 0; i < muelleOtroContenedores.length; i++) {
			for (int j = 0; j < muelleOtroContenedores[0].length; j++) {				
				if (
					(getPlazas()[i][j] == null && muelleOtroContenedores[i][j] != null) ||
					(
						getPlazas()[i][j] != null &&
						!getPlazas()[i][j].equals(muelleOtroContenedores[i][j])
					)
				) return false;
			}
			
		}
		
		return true;
	}


	/**
	 * Comprueba si un código de identificación ya existe en el muelle.
	 * 
	 * @param codigoIdentificador Código de identificación a comprobar.
	 * 
	 * @return True si el código de identificación ya existe, False en caso contrario.
	 * 
	 * @throws IllegalArgumentException Si el código de identificación es null.
	 */
	public boolean comprobarCodigoYaExistente(String codigoIdentificador) {
		if (codigoIdentificador == null)
			throw new IllegalArgumentException("El código de identificación no puede ser null.");

		for (int i = 0; i < getNumeroPlazas(); i++) {
			for (int j = 0; j < getMaximoContenedoresApilables(); j++) {
				if (plazas[i][j] != null && plazas[i][j].getCodigoIdentificador().equals(codigoIdentificador))
					return true;
			}
		}

		return false;
	}
	
	/**
	 * Comprueba si dos muelles son iguales.
	 * 
	 * @param muelle Muelle a comparar.
	 * 
	 * @return True si los muelles son iguales, False en caso contrario.
	 */
	@Override
	public boolean equals(Object muelle) {
		if (!(muelle instanceof Muelle)) return false;
		
		Muelle m = (Muelle) muelle;
		
		return (
			getCodigoIdentificacion() == m.getCodigoIdentificacion() &&
			getLocalizacion().equals(m.getLocalizacion()) &&
			getOperativo() == m.getOperativo() &&
			getNumeroPlazas() == m.getNumeroPlazas() &&
			getMaximoContenedoresApilables() == m.getMaximoContenedoresApilables() &&
			comprobarContenedoresIguales(m)
		);
	}
}
