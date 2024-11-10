package es.uva.poo.lab;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uva.inf.poo.maps.GPSCoordinate;
public class MuelleTest {

	// hacer todos los test para comprobar que el constructor de Muelle 

	@Test (expected = IllegalArgumentException.class)
	public void testCrearMuelleCodigoIdentificacionMenor10() {
		new Muelle(9, new GPSCoordinate(0, 0), true, 1, 1);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testCrearMuelleCodigoIdentificacionMayor99() {
		new Muelle(100, new GPSCoordinate(0, 0), true, 1, 1);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testCrearMuelleLocalizacionNull() {
		new Muelle(10, null, true, 1, 1);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testCrearMuelleNumeroPlazasMenorIgual0() {
		new Muelle(10, new GPSCoordinate(0, 0), true, 0, 1);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testCrearMuelleMaximoContenedoresApilablesMenorIgual0() {
		new Muelle(10, new GPSCoordinate(0, 0), true, 1, 0);
	}

	@Test
	public void testCrearMuelleAPartirDeOtroMuelle() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		Muelle muelle2 = new Muelle(muelle);
		assertEquals(muelle.getCodigoIdentificacion(), muelle2.getCodigoIdentificacion());
		assertEquals(muelle.getLocalizacion(), muelle2.getLocalizacion());
		assertEquals(muelle.getOperativo(), muelle2.getOperativo());
		assertEquals(muelle.getNumeroPlazas(), muelle2.getNumeroPlazas());
		assertEquals(muelle.getMaximoContenedoresApilables(), muelle2.getMaximoContenedoresApilables());
	}

	@Test (expected = IllegalArgumentException.class)
	public void testCrearMuelleAPartirDeOtroMuelleNull() {
		new Muelle(null);
	}

	@Test
	public void testGetPlazasConPlazasVacias() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		assertArrayEquals(new Contenedor[1][1], muelle.getPlazas());
	}

	@Test
	public void testGetPlazasConPlazasConContenedor() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		Contenedor contenedor = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		muelle.apilarContenedor(contenedor, 0);
		assertArrayEquals(new Contenedor[][] {{contenedor}}, muelle.getPlazas());
	}

	@Test
	public void testGetMaximoContenedoresApilables() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		assertEquals(1, muelle.getMaximoContenedoresApilables());
	}

	@Test
	public void testGetCodigoIdentificacion() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		assertEquals(10, muelle.getCodigoIdentificacion());
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSetCodigoIdentificacionMenor10() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		muelle.setCodigoIdentificacion(9);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSetCodigoIdentificacionMayor99() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		muelle.setCodigoIdentificacion(100);
	}

	@Test
	public void testSetCodigoIdentificacionCorrecto() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		muelle.setCodigoIdentificacion(11);
		assertEquals(11, muelle.getCodigoIdentificacion());
	}

	@Test
	public void testGetLocalizacion() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		assertEquals(new GPSCoordinate(0, 0), muelle.getLocalizacion());
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSetLocalizacionNull() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		muelle.setLocalizacion(null);
	}

	@Test
	public void testSetLocalizacionCorrecta() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		muelle.setLocalizacion(new GPSCoordinate(1, 1));
		assertEquals(new GPSCoordinate(1, 1), muelle.getLocalizacion());
	}

	@Test
	public void testGetNumeroPlazas() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		assertEquals(1, muelle.getNumeroPlazas());
	}

	@Test
	public void testGetOperativo() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		assertTrue(muelle.getOperativo());
	}

	@Test
	public void testSetOperativo() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		muelle.setOperativo(false);
		assertFalse(muelle.getOperativo());
	}

	@Test
	public void testGetPlazasVacias() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		assertEquals(1, muelle.getPlazasVacias());
	}

	@Test
	public void testGetPlazasLlenas1MaximoApilable() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		assertEquals(0, muelle.getPlazasLlenas());
	}

	

	@Test
	public void testGetPlazasLlenas2MaximoApilable() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		assertEquals(0, muelle.getPlazasLlenas());
	}

	@Test
	public void testGetPlazasSemillenas() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		muelle.apilarContenedor(new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true), 0);
		assertEquals(1, muelle.getPlazasSemillenas());
	}

	

	@Test
	public void testGetContenedoresEnPlazaVacias() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);

		assertArrayEquals(new Contenedor[1], muelle.getContenedoresEnPlaza(0));
	}

	@Test
	public void testGetContenedoresEnPlazaConContenedor() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		Contenedor contenedor = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		muelle.apilarContenedor(contenedor, 0);
		assertArrayEquals(new Contenedor[] {contenedor}, muelle.getContenedoresEnPlaza(0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetContenedoresEnPlazaNull() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
	  muelle.getContenedoresEnPlaza(-1);
	}



	

	
	


}
