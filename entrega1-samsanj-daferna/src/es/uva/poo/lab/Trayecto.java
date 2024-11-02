package es.uva.poo.lab;

import java.time.LocalDate;

public class Trayecto {
	// TODO: Usar Date o Timestamp?
	// https://mkyong.com/java/how-to-get-current-timestamps-in-java/
	private static final double CONVERSION_MILLAS_NAUTICAS = 0.539957;
	
	private Muelle muelleOrigen;
	private Puerto puertoOrigen;
	private LocalDate fechaInicio;

	private Muelle muelleDestino;
	private Puerto puertoDestino;
	private LocalDate fechaFin;
	
	public Trayecto(Muelle muelleOrigen, Puerto puertoOrigen, LocalDate fechaInicio, Muelle muelleDestino, Puerto puertoDestino, LocalDate fechaFin) {
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
		
		this.muelleOrigen = new Muelle(muelleOrigen);
		this.puertoOrigen = new Puerto(puertoOrigen);
		this.fechaInicio = fechaInicio;
		
		this.muelleDestino = new Muelle(muelleDestino);
		this.puertoDestino = new Puerto(puertoDestino);
		this.fechaFin = fechaFin;
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
	
	
	// A partir del coste por día de trayecto dado y el coste por milla marina, obtener el precio de un trayecto en euros.
	public double getCosteTrayecto(double costePorDia, double costePorMillaNautica) {
		int dias = getDuracionDias();
		double distanciaMillasNauticas = getDistanciaMillasNauticas();
		
		// TODO: Es esta la operacion?
		return (dias * costePorDia) + (distanciaMillasNauticas * costePorMillaNautica);
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
}






