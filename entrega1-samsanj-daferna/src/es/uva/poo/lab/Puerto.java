package es.uva.poo.lab;

import java.util.ArrayList;

import es.uva.inf.poo.maps.GPSCoordinate;

public class Puerto {
	private String codigoIdentificacion;
	private ArrayList<Muelle> muelles;
	
	public Puerto(String codigoIdentificacion, Muelle[] muelles) {
		comprobarcodigoIdentificacion(codigoIdentificacion);
		
		this.codigoIdentificacion = codigoIdentificacion;
		this.muelles = new ArrayList<Muelle>();
		
		for (int i = 0; i < muelles.length; i++) {
			this.muelles.add(new Muelle(muelles[i]));
		}
	}
	
	public Puerto(String codigoIdentificacion) {
		comprobarcodigoIdentificacion(codigoIdentificacion);
		
		this.codigoIdentificacion = codigoIdentificacion;
		this.muelles = new ArrayList<Muelle>();
	}
	
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
	
	public String getCodigoIdentificacion() {
		return codigoIdentificacion;
	}
	
	public int getNumeroMuelles() {
		return muelles.size();
	}
	
	public boolean getOperativo() {
		
		for (int i = 0; i < getNumeroMuelles(); i++) {
			if (muelles.get(i).getOperativo())
				return true;
		}
		
		return false;
	}
	
	public void agregarMuelle(Muelle muelle) {
		if (muelle == null)
			throw new IllegalArgumentException("El muelle no puede ser null.");
		
		for (Muelle m: muelles) {
			if (m.getCodigoIdentificacion() == muelle.getCodigoIdentificacion())
				throw new IllegalArgumentException("Ya existe un muerto con ese código de identificación.");
		}
		
		muelles.add(new Muelle(muelle));
	}
	
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
	
	
	public boolean getCompleto() {
		for (Muelle muelle: muelles) {
			if (muelle.getPlazasLlenas() != muelle.getNumeroPlazas())
				return false;
		}
		
		return true;
	}
	
	public Muelle[] getMuellesOperativos() {
		ArrayList<Muelle> muellesOperativosList = new ArrayList<>();

	    for (Muelle muelle : muelles) {
	        if (muelle.getOperativo()) {
	            muellesOperativosList.add(new Muelle(muelle));
	        }
	    }

	    return muellesOperativosList.toArray(new Muelle[0]);
	}
	
	public Muelle[] getMuellesConEspacio() {
		ArrayList<Muelle> muellesConEspacio = new ArrayList<>();

	    for (Muelle muelle : muelles) {
	        if (muelle.getPlazasLlenas() != muelle.getNumeroPlazas()) {
	        	muellesConEspacio.add(new Muelle(muelle));
	        }
	    }

	    return muellesConEspacio.toArray(new Muelle[0]);
	}
	
	// TODO: DISTANCIA EN KM
	public Muelle[] getMuellesCercanos(GPSCoordinate localizacion, int distancia) {
		if (localizacion == null)
			throw new IllegalArgumentException("La localizacion no puede ser null.");
		
		ArrayList<Muelle> muellesConEspacio = new ArrayList<>();

	    for (Muelle muelle : muelles) {
	        if (muelle.getLocalizacion().getDistanceTo(localizacion) <= distancia){
	        	muellesConEspacio.add(new Muelle(muelle));
	        }
	    }

	    return muellesConEspacio.toArray(new Muelle[0]);
	}
}






