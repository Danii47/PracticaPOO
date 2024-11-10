package es.uva.poo.lab;

import java.time.LocalDate;

public class Trayecto {
	// TODO: Usar Date o Timestamp?
	// https://mkyong.com/java/how-to-get-current-timestamps-in-java/

	public static final double CONVERSION_MILLAS_NAUTICAS = 0.539957;
	
	
	private Muelle muelleOrigen;
	private Puerto puertoOrigen;
	private LocalDate fechaInicio;
	
	private Muelle muelleDestino;
	private Puerto puertoDestino;
	private LocalDate fechaFin;
	
	private double costePorDia;
	private double costePorMillaNautica;

	public Trayecto(Muelle muelleOrigen, Puerto puertoOrigen, LocalDate fechaInicio, Muelle muelleDestino, Puerto puertoDestino, LocalDate fechaFin , double costePorDia, double costePorMillaNautica) {
		// TODO: Habría que comprobar que el muelleOrigen pertenece al puertoOrigen e igual con los destino
		
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
		
		this.muelleOrigen = new Muelle(muelleOrigen);
		this.puertoOrigen = new Puerto(puertoOrigen);
		this.fechaInicio = fechaInicio;
		
		this.muelleDestino = new Muelle(muelleDestino);
		this.puertoDestino = new Puerto(puertoDestino);
		this.fechaFin = fechaFin;

		this.costePorDia = costePorDia;
		this.costePorMillaNautica = costePorMillaNautica;
		
	}
	
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
	
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	
	public boolean fechaFinSuperiorAOtra(LocalDate otraFecha) {
		return getFechaFin().isAfter(otraFecha);
	}
	
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
	
	public double getDistanciaKM() {
		return muelleOrigen.getLocalizacion().getDistanceTo(muelleDestino.getLocalizacion());
	}
	
	public void setCostePorDia(double costePorDia) {
		if (costePorDia < 0)
			throw new IllegalArgumentException("El coste por día no puede ser negativo.");
		this.costePorDia = costePorDia;
	}	
	
	public double getCostePorDia() {
		return costePorDia;
	}
	
	public void setCostePorMillaNautica(double costePorMillaNautica) {
		if (costePorMillaNautica < 0)
			throw new IllegalArgumentException("El coste por milla naútica no puede ser negativo.");
		this.costePorMillaNautica = costePorMillaNautica;
	}
	
	public double getCostePorMillaNautica() {
		return costePorMillaNautica;
	}
	
	public Muelle getMuelleOrigen() {
		return new Muelle(muelleOrigen);
	}
	
	public void setMuelleOrigen(Muelle muelleOrigen) {
		if (muelleOrigen == null)
			throw new IllegalArgumentException("El muelle introducido no puede ser null.");
		
		if (!puertoOrigen.muellePerteneceAlPuerto(muelleOrigen))
			throw new IllegalArgumentException("El muelle introducido no pertenece al puerto origen.");
	
		this.muelleOrigen = new Muelle(muelleOrigen);
	}
	
	public Muelle getMuelleDestino() {
		return new Muelle(muelleDestino);
	}
	
	public void setMuelleDestino(Muelle muelleDestino) {
		if (muelleDestino == null)
			throw new IllegalArgumentException("El muelle introducido no puede ser null.");
		
		if (!puertoDestino.muellePerteneceAlPuerto(muelleDestino))
			throw new IllegalArgumentException("El muelle introducido no pertenece al puerto destino.");
	
		this.muelleDestino = new Muelle(muelleDestino);
	}
	
	public Puerto getPuertoOrigen() {
		return new Puerto(puertoOrigen);
	}
	
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
	
	public Puerto getPuertoDestino() {
		return new Puerto(puertoDestino);
	}
	
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
	
	// A partir del coste por día de trayecto dado y el coste por milla marina, obtener el precio de un trayecto en euros.
	public double getCosteTrayecto() {
		
		int dias = getDuracionDias();
		double distanciaMillasNauticas = getDistanciaMillasNauticas();
		
		return (dias * getCostePorDia()) + (distanciaMillasNauticas * getCostePorMillaNautica());
	}
	
	public double getDistanciaMillasNauticas() {
		return getDistanciaKM() * CONVERSION_MILLAS_NAUTICAS;
	}
	
	public String toString() {
		return (
			"Localidad y país Puerto Origen: " + puertoOrigen.getCodigoIdentificacion() +
			"\nFecha de inicio del trayecto: " + fechaInicio.toString() +
			"\nLocalidad y país Puerto Destino: " + puertoDestino.getCodigoIdentificacion() +
			"\nFecha de fin del trayecto: " + fechaFin.toString()
		);
	}
	
	public boolean equals(Object trayecto) {
		if (!(trayecto instanceof Trayecto)) return false;
		
		Trayecto t = (Trayecto) trayecto;
		
		return (
			muelleOrigen.equals(t.getMuelleOrigen()) &&
			muelleDestino.equals(t.getMuelleDestino()) &&
			puertoOrigen.equals(t.getPuertoOrigen()) &&
			puertoDestino.equals(t.getPuertoDestino()) &&
			fechaInicio.equals(t.getFechaInicio()) &&
			fechaFin.equals(t.getFechaFin()) &&
			costePorDia == t.getCostePorDia() &&
			costePorMillaNautica == t.getCostePorMillaNautica()
		);
	}
}






