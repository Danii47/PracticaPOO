package es.uva.poo.lab;

import java.time.LocalDate;

/**
 * @author daferna & samsanj
 * 
 * Tipo de dato que representa el trayecto de un contenedor, teniendo en cuente el
 * muelle de origen, puerto de origen, fecha de salida, muelle de destino, puerto de destino,
 * fecha de llegada, coste por día viajado y coste por milla naútica viajada.
 */
public class Trayecto {
	public static final double CONVERSION_MILLAS_NAUTICAS = 0.539957;
	
	private Muelle muelleOrigen;
	private Puerto puertoOrigen;
	private LocalDate fechaInicio;
	
	private Muelle muelleDestino;
	private Puerto puertoDestino;
	private LocalDate fechaFin;
	
	private double costePorDia;
	private double costePorMillaNautica;

	/**
	 * Constructor principal de la clase Trayecto.
	 * 
	 * @param muelleOrigen Muelle de origen del trayecto.
	 * @param puertoOrigen Puerto de origen del trayecto.
	 * @param fechaInicio Fecha de inicio del trayecto.
	 * @param muelleDestino Muelle de destino del trayecto.
	 * @param puertoDestino Puerto de destino del trayecto.
	 * @param fechaFin Fecha de fin del trayecto.
	 * @param costePorDia Coste diario del trayecto.
	 * @param costePorMillaNautica Coste por milla náutica del trayecto.
	 * 
	 * @throws IllegalArgumentException Si alguno de los parámetros es nulo, si las fechas no son válidas, si los costes son negativos o si los puertos no están operativos.
	 */
	public Trayecto(Muelle muelleOrigen, Puerto puertoOrigen, LocalDate fechaInicio, Muelle muelleDestino, Puerto puertoDestino, LocalDate fechaFin , double costePorDia, double costePorMillaNautica) {
		if (muelleOrigen == null)
			throw new IllegalArgumentException("El muelle origen no puede ser null.");
		
		if (puertoOrigen == null)
			throw new IllegalArgumentException("El puerto origen no puede ser null.");
		
		if (fechaInicio == null)
			throw new IllegalArgumentException("La fecha inicio no puede ser null.");
		
		if (muelleDestino == null)
			throw new IllegalArgumentException("El muelle destino no puede ser null.");
		
		if (puertoDestino == null)
			throw new IllegalArgumentException("El puerto destino no puede ser null.");
		
		if (fechaFin == null)
			throw new IllegalArgumentException("La fecha fin no puede ser null.");
		
		if (fechaInicio.isAfter(fechaFin))
			throw new IllegalArgumentException("La fecha fin no puede ser antes que la fecha inicio.");
		
		if (costePorDia < 0)
			throw new IllegalArgumentException("El coste por día no puede ser negativo.");

		if (costePorMillaNautica < 0)
			throw new IllegalArgumentException("El coste por milla naútica no puede ser negativo.");

		if (!puertoOrigen.muellePerteneceAlPuerto(muelleOrigen))
			throw new IllegalArgumentException("El muelle de origen debe pertenecer al puerto de origen.");
	
		if (!puertoDestino.muellePerteneceAlPuerto(muelleDestino))
			throw new IllegalArgumentException("El muelle de destino debe pertenecer al puerto de destino.");
		
		if (!puertoOrigen.getOperativo())
			throw new IllegalArgumentException("El puerto origen debe estar operativo.");
		
		if (!puertoDestino.getOperativo())
			throw new IllegalArgumentException("El puerto destino debe estar operativo.");
		
		this.muelleOrigen = new Muelle(muelleOrigen);
		this.puertoOrigen = new Puerto(puertoOrigen);
		this.fechaInicio = fechaInicio;
		
		this.muelleDestino = new Muelle(muelleDestino);
		this.puertoDestino = new Puerto(puertoDestino);
		this.fechaFin = fechaFin;

		this.costePorDia = costePorDia;
		this.costePorMillaNautica = costePorMillaNautica;
		
	}
	
	/**
	 * Constructor copia de la clase Trayecto.
	 * @param trayecto Trayecto a copiar.
	 */
	public Trayecto(Trayecto trayecto) {
		this.muelleOrigen = trayecto.getMuelleOrigen();
		this.puertoOrigen = trayecto.getPuertoOrigen();
		this.fechaInicio = trayecto.getFechaInicio();
		
		this.muelleDestino = trayecto.getMuelleDestino();
		this.puertoDestino = trayecto.getPuertoDestino();
		this.fechaFin = trayecto.getFechaFin();

		this.costePorDia = trayecto.getCostePorDia();
		this.costePorMillaNautica = trayecto.getCostePorMillaNautica();
	}
	
	/**
	 * Obtiene la fecha de inicio del trayecto.
	 * @return Fecha de inicio del trayecto.
	 */
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	
	/**
	 * Obtiene la fecha de fin del trayecto.
	 * @return Fecha de fin del trayecto.
	 */
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	
	/**
	 * Comprueba si la fecha de fin del trayecto es posterior a una fecha dada.
	 * @param otraFecha Fecha a comparar.
	 * 
	 * @return true si la fecha de fin del trayecto es posterior a otraFecha, false en caso contrario.
	 * 
	 * @throws IllegalArgumentException si la otraFecha es null.
	 */
	public boolean fechaFinSuperiorAOtra(LocalDate otraFecha) {
		if (otraFecha == null)
			throw new IllegalArgumentException("La otra fecha a comparar no puede ser null.");
		
		return getFechaFin().isAfter(otraFecha);
	}
	
	/**
	 * Obtiene la duración del trayecto en días.
	 * @return Duración en días del trayecto.
	 */
	public int getDuracionDias() {
		int dias = 0;
        
        if (getFechaFin().isAfter(getFechaInicio()) || getFechaFin().isEqual(getFechaInicio())) {
            LocalDate fechaActual = getFechaInicio();
            while (!fechaActual.isEqual(getFechaFin())) {
                dias++;
                fechaActual = fechaActual.plusDays(1); // Incrementar un día
            }
        }
        
        return dias; 
	}
	
	/**
	 * Calcula la distancia en kilómetros entre el muelle de origen y el de destino.
	 * 
	 * @return Distancia en kilómetros.
	 */
	public double getDistanciaKM() {
		return muelleOrigen.getLocalizacion().getDistanceTo(muelleDestino.getLocalizacion());
	}
	
	/**
	 * Calcula la distancia entre el origen y el destino en millas náuticas.
	 * 
	 * @return Distancia en millas náuticas.
	 */
	public double getDistanciaMillasNauticas() {
		return getDistanciaKM() * CONVERSION_MILLAS_NAUTICAS;
	}
	
	/**
	 * Establece el coste por día del trayecto.
	 * 
	 * @param costePorDia Nuevo coste diario.
	 * 
	 * @throws IllegalArgumentException Si el coste es negativo.
	 */
	public void setCostePorDia(double costePorDia) {
		if (costePorDia < 0)
			throw new IllegalArgumentException("El coste por día no puede ser negativo.");
		this.costePorDia = costePorDia;
	}	
	
	/**
	 * Obtiene el coste por día del trayecto.
	 * 
	 * @return Coste diario del trayecto.
	 */
	public double getCostePorDia() {
		return costePorDia;
	}
	
	/**
	 * Establece el coste por milla náutica del trayecto.
	 * 
	 * @param costePorMillaNautica Nuevo coste por milla náutica.
	 * 
	 * @throws IllegalArgumentException Si el coste es negativo.
	 */
	public void setCostePorMillaNautica(double costePorMillaNautica) {
		if (costePorMillaNautica < 0)
			throw new IllegalArgumentException("El coste por milla naútica no puede ser negativo.");
		this.costePorMillaNautica = costePorMillaNautica;
	}
	
	/**
	 * Obtiene el coste por milla náutica del trayecto.
	 * 
	 * @return Coste por milla náutica.
	 */
	public double getCostePorMillaNautica() {
		return costePorMillaNautica;
	}
	
	/**
	 * Obtiene una copia del muelle de origen.
	 * 
	 * @return Copia del muelle de origen.
	 */
	public Muelle getMuelleOrigen() {
		return new Muelle(muelleOrigen);
	}
	
	/**
	 * Establece el muelle de origen del trayecto.
	 * 
	 * @param muelleOrigen Muelle de origen.
	 * 
	 * @throws IllegalArgumentException Si el muelle es nulo o no pertenece al puerto de origen.
	 */
	public void setMuelleOrigen(Muelle muelleOrigen) {
		if (muelleOrigen == null)
			throw new IllegalArgumentException("El muelle introducido no puede ser null.");
		
		if (!puertoOrigen.muellePerteneceAlPuerto(muelleOrigen))
			throw new IllegalArgumentException("El muelle introducido no pertenece al puerto origen.");
	
		this.muelleOrigen = new Muelle(muelleOrigen);
	}
	
	/**
	 * Obtiene una copia del muelle de destino.
	 * 
	 * @return Copia del muelle de destino.
	 */
	public Muelle getMuelleDestino() {
		return new Muelle(muelleDestino);
	}
	
	/**
	 * Establece el muelle de destino del trayecto.
	 * 
	 * @param muelleDestino Muelle de destino.
	 * 
	 * @throws IllegalArgumentException Si el muelle es nulo o no pertenece al puerto de destino.
	 */
	public void setMuelleDestino(Muelle muelleDestino) {
		if (muelleDestino == null)
			throw new IllegalArgumentException("El muelle introducido no puede ser null.");
		
		if (!puertoDestino.muellePerteneceAlPuerto(muelleDestino))
			throw new IllegalArgumentException("El muelle introducido no pertenece al puerto destino.");
	
		this.muelleDestino = new Muelle(muelleDestino);
	}
	
	/**
	 * Obtiene una copia del puerto de origen.
	 * 
	 * @return Copia del puerto de origen.
	 */
	public Puerto getPuertoOrigen() {
		return new Puerto(puertoOrigen);
	}
	
	/**
	 * Establece el puerto y el muelle de origen del trayecto.
	 * 
	 * @param puertoOrigen Puerto de origen.
	 * @param muelleOrigen Muelle de origen.
	 * 
	 * @throws IllegalArgumentException Si el puerto o el muelle son nulos, o si el muelle no pertenece al puerto.
	 */
	public void setPuertoYMuelleOrigen(Puerto puertoOrigen, Muelle muelleOrigen) {
		if (puertoOrigen == null)
			throw new IllegalArgumentException("El puerto introducido no puede ser null.");
		
		if (muelleOrigen == null)
			throw new IllegalArgumentException("El muelle introducido no puede ser null.");
		
		if (!puertoOrigen.muellePerteneceAlPuerto(muelleOrigen))
			throw new IllegalArgumentException("El muelle introducido no pertenece al puerto introducido.");
	
		this.puertoOrigen = new Puerto(puertoOrigen);
		this.muelleOrigen = new Muelle(muelleOrigen);
	}
	
	/**
	 * Obtiene una copia del puerto de destino.
	 * 
	 * @return Copia del puerto de destino.
	 */
	public Puerto getPuertoDestino() {
		return new Puerto(puertoDestino);
	}
	
	/**
	 * Establece el puerto y el muelle de destino del trayecto.
	 * 
	 * @param puertoDestino Puerto de destino.
	 * @param muelleDestino Muelle de destino.
	 * 
	 * @throws IllegalArgumentException Si el puerto o el muelle son nulos, o si el muelle no pertenece al puerto.
	 */
	public void setPuertoYMuelleDestino(Puerto puertoDestino, Muelle muelleDestino) {
		if (puertoDestino == null)
			throw new IllegalArgumentException("El puerto introducido no puede ser null.");
		
		if (muelleDestino == null)
			throw new IllegalArgumentException("El muelle introducido no puede ser null.");
		
		if (!puertoDestino.muellePerteneceAlPuerto(muelleDestino))
			throw new IllegalArgumentException("El muelle introducido no pertenece al puerto introducido.");
	
		this.puertoDestino = new Puerto(puertoDestino);
		this.muelleDestino = new Muelle(muelleDestino);
	}
	
	/**
	 * Calcula el coste total del trayecto en función de la duración y la distancia en millas náuticas.
	 *
	 * @return Coste total del trayecto.
	 */
	public double getCosteTrayecto() {
		
		int dias = getDuracionDias();
		double distanciaMillasNauticas = getDistanciaMillasNauticas();
		
		return (dias * getCostePorDia()) + (distanciaMillasNauticas * getCostePorMillaNautica());
	}
	
	/**
	 * Devuelve una representación en formato cadena de texto del trayecto.
	 * 
	 * @return Representación en formato cadena de texto del trayecto.
	 */
	public String toString() {
		return (
			"Localidad y país Puerto Origen: " + puertoOrigen.getCodigoIdentificacion() +
			"\nFecha de inicio del trayecto: " + fechaInicio.toString() +
			"\nLocalidad y país Puerto Destino: " + puertoDestino.getCodigoIdentificacion() +
			"\nFecha de fin del trayecto: " + fechaFin.toString()
		);
	}
	
	/**
	 * Compara si el trayecto actual es igual a otro objeto.
	 * 
	 * @param trayecto Objeto a comparar.
	 * 
	 * @return true si los trayectos son iguales, false en caso contrario.
	 */
	@Override
	public boolean equals(Object trayecto) {
		if (!(trayecto instanceof Trayecto)) return false;
		
		Trayecto t = (Trayecto) trayecto;
		
		return (
			puertoOrigen.equals(t.getPuertoOrigen()) &&
			puertoDestino.equals(t.getPuertoDestino()) &&
			muelleOrigen.equals(t.getMuelleOrigen()) &&
			muelleDestino.equals(t.getMuelleDestino()) &&
			fechaInicio.equals(t.getFechaInicio()) &&
			fechaFin.equals(t.getFechaFin()) &&
			costePorDia == t.getCostePorDia() &&
			costePorMillaNautica == t.getCostePorMillaNautica()
		);
	}
}






