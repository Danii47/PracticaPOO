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

	// TODO REVISAR Y CONTINUAR DESDE AQUI
	@Test
	public void testGetContenedoresEnPlazaCorrecto() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		assertArrayEquals(new Contenedor[1], muelle.getContenedoresEnPlaza(0));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetContenedoresEnPlazaConPlazaNegativa() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		muelle.getContenedoresEnPlaza(-1);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testGetContenedoresEnPlazaConPlazaMayorQueNumeroPlazas() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		muelle.getContenedoresEnPlaza(1);
	}

	@Test
	public void testGetPlazasLlenas1MaximoApilableSinContenedor() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		assertEquals(0, muelle.getPlazasLlenas());
	}

	@Test
	public void testGetPlazasLlenas1MaximoApilableConContenedor() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		muelle.apilarContenedor(new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true), 0);
		assertEquals(1, muelle.getPlazasLlenas());
	}


	@Test
	public void testGetPlazasLlenas2MaximoApilableSinContenedores() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		assertEquals(0, muelle.getPlazasLlenas());
	}

	@Test
	public void testGetPlazasLlenas2MaximoApilableCon1Contenedor() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		muelle.apilarContenedor(new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true), 0);
		assertEquals(0, muelle.getPlazasLlenas());
	}

	@Test
	public void testGetPlazasLlenas2MaximoApilableCon2Contenedores() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		muelle.apilarContenedor(new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true), 0);
		muelle.apilarContenedor(new Contenedor("BICU1234570", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true), 0);
		assertEquals(1, muelle.getPlazasLlenas());
	}

	@Test
	public void testGetPlazasSemillenas() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		muelle.apilarContenedor(new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true), 0);
		assertEquals(1, muelle.getPlazasSemillenas());
	}

	
	@Test
	public void testGetPlazaContenedorCorrecto() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		Contenedor contenedor = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		muelle.apilarContenedor(contenedor, 0);
		assertEquals(0, muelle.getPlazaContenedor(contenedor.getCodigoIdentificador()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetPlazaContenedorNoEncontrado() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		Contenedor contenedor = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		muelle.getPlazaContenedor(contenedor.getCodigoIdentificador());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetPlazaContenedorCodigoNull() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		muelle.getPlazaContenedor(null);
	}

	@Test
	public void testGetNivelPlazaContenedorCorrecto() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		Contenedor contenedor = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		muelle.apilarContenedor(contenedor, 0);
		assertEquals(0, muelle.getNivelPlazaContenedor(contenedor.getCodigoIdentificador()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetNivelPlazaContenedorNoEncontrado() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		Contenedor contenedor = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		muelle.getNivelPlazaContenedor(contenedor.getCodigoIdentificador());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetNivelPlazaContenedorCodigoNull() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		muelle.getNivelPlazaContenedor(null);
	}

	@Test
	public void testPosibleApilarPlazaVacia() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		assertTrue(muelle.posibleApilar(new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true), 0));
	}

	@Test
	public void testPosibleApilarPlazaLlena() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		muelle.apilarContenedor(new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true), 0);
		assertFalse(muelle.posibleApilar(new Contenedor("BICU1234570", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true), 0));
	}

	@Test
	public void testPosibleApilarPlazaSemillena() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		muelle.apilarContenedor(new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true), 0);
		assertTrue(muelle.posibleApilar(new Contenedor("BICU1234570", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true), 0));	
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPosibleApilarContenedorNull() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		muelle.posibleApilar(null, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPosibleApilarPlazaNegativa() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		muelle.posibleApilar(new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true), -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPosibleApilarPlazaMayorQueNumeroPlazas() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		muelle.posibleApilar(new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true), 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPosibleApilarCodigoIdentificadorDuplicado() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		Contenedor contenedor1 = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		Contenedor contenedor2 = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		muelle.apilarContenedor(contenedor1, 0);
		muelle.posibleApilar(contenedor2, 0);
	}

	@Test
	public void testApilarContenedorCorrecto() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		Contenedor contenedor = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		muelle.apilarContenedor(contenedor, 0);
		assertArrayEquals(new Contenedor[][] {{contenedor}}, muelle.getPlazas());
	}

	@Test
	public void testApilarContenedorPlazaSemillena() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 3);
		Contenedor contenedor1 = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		Contenedor contenedor2 = new Contenedor("BICU1234570", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		muelle.apilarContenedor(contenedor1, 0);
		muelle.apilarContenedor(contenedor2, 0);
		assertArrayEquals(new Contenedor[][] {{contenedor1, contenedor2, null}}, muelle.getPlazas());
	}

	@Test
	public void testApilarContenedorMuelleVariasPlazas() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 3, 3);
		Contenedor contenedor1 = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		Contenedor contenedor2 = new Contenedor("BICU1234570", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		Contenedor contenedor3 = new Contenedor("CSQU3054383", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		muelle.apilarContenedor(contenedor1, 0);
		muelle.apilarContenedor(contenedor2, 1);
		muelle.apilarContenedor(contenedor3, 1);
		assertArrayEquals(new Contenedor[][] {{contenedor1,null,null}, {contenedor2,contenedor3,null},{null,null,null}}, muelle.getPlazas());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testApilarContenedorPlazaNoValida() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		Contenedor contenedor = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		muelle.apilarContenedor(contenedor, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testApilarContenedorNoPosibleApilar() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		Contenedor contenedor1 = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		Contenedor contenedor2 = new Contenedor("BICU1234570", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		muelle.apilarContenedor(contenedor1, 0);
		muelle.apilarContenedor(contenedor2, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDesapilarContenedorPlazaNoValida() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		muelle.desapilarContenedor(2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDesapilarContenedorPlazaVacia() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		muelle.desapilarContenedor(0);
	}

	@Test
	public void testDesapilarContenedorPlazaLlena() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		Contenedor contenedor = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		muelle.apilarContenedor(contenedor, 0);
		assertEquals(contenedor, muelle.desapilarContenedor(0));
	}

	@Test
	public void testDesapilarContenedorPlazaSemillena() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		Contenedor contenedor1 = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		muelle.apilarContenedor(contenedor1, 0);
	
		assertEquals(contenedor1, muelle.desapilarContenedor(0));
	}

	@Test
	public void testDesapilarContenedorMuelleVariasPlazas() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 3, 3);
		Contenedor contenedor1 = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		Contenedor contenedor2 = new Contenedor("BICU1234570", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);	
		Contenedor contenedor3 = new Contenedor("CSQU3054383", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		muelle.apilarContenedor(contenedor1, 0);
		muelle.apilarContenedor(contenedor2, 1);
		muelle.apilarContenedor(contenedor3, 1);
		assertEquals(contenedor3, muelle.desapilarContenedor(1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testComprobarCodigoYaExistente() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		muelle.comprobarCodigoYaExistente(null);
	}

	@Test
	public void testComprobarCodigoYaExistenteCodigoNoExistente() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		assertFalse(muelle.comprobarCodigoYaExistente("BICU1234565"));
	}

	@Test
	public void testComprobarCodigoYaExistenteCodigoExistente() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		Contenedor contenedor = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		muelle.apilarContenedor(contenedor, 0);
		assertTrue(muelle.comprobarCodigoYaExistente("BICU1234565"));

	}
	
	@Test
	public void testComprobarCodigoYaExistenteCodigoExistenteVariasPlazas() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 3, 3);
		Contenedor contenedor1 = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		Contenedor contenedor2 = new Contenedor("BICU1234570", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);	
		Contenedor contenedor3 = new Contenedor("CSQU3054383", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		muelle.apilarContenedor(contenedor1, 0);
		muelle.apilarContenedor(contenedor2, 1);
		muelle.apilarContenedor(contenedor3, 1);
		assertTrue(muelle.comprobarCodigoYaExistente("BICU1234565"));
	}

	@Test
	public void testEqualsMismoObjeto() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 3, 3);
		Muelle muelle2 = new Muelle(10, new GPSCoordinate(0, 0), true, 3, 3);
		Contenedor contenedor1 = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		Contenedor contenedor2 = new Contenedor("BICU1234570", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);	
		Contenedor contenedor3 = new Contenedor("CSQU3054383", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		muelle.apilarContenedor(contenedor1, 0);
		muelle.apilarContenedor(contenedor2, 1);
		muelle.apilarContenedor(contenedor3, 1);
		muelle2.apilarContenedor(contenedor1, 0);
		muelle2.apilarContenedor(contenedor2, 1);
		muelle2.apilarContenedor(contenedor3, 1);
		assertTrue(muelle.equals(muelle2));
	}

	@Test
	public void testEqualsObjetoNull() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		assertFalse(muelle.equals(null));
	}

	@Test
	public void testEqualsObjetoDistintoTipo() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		assertFalse(muelle.equals(new GPSCoordinate(0, 0)));
	}

	@Test
	public void testEqualsDistintoCodigoIdentificacion() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		Muelle muelle2 = new Muelle(11, new GPSCoordinate(0, 0), true, 1, 1);
		assertFalse(muelle.equals(muelle2));
	}

	@Test
	public void testEqualsDistintaLocalizacion() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		Muelle muelle2 = new Muelle(10, new GPSCoordinate(1, 1), true, 1, 1);
		assertFalse(muelle.equals(muelle2));
	}

	@Test
	public void testEqualsDistintoOperativo() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		Muelle muelle2 = new Muelle(10, new GPSCoordinate(0, 0), false, 1, 1);
		assertFalse(muelle.equals(muelle2));
	}

	@Test
	public void testEqualsDistintoNumeroPlazas() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		Muelle muelle2 = new Muelle(10, new GPSCoordinate(0, 0), true, 2, 1);
		assertFalse(muelle.equals(muelle2));
	}

	@Test
	public void testEqualsDistintoMaximoContenedoresApilables() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 1);
		Muelle muelle2 = new Muelle(10, new GPSCoordinate(0, 0), true, 1, 2);
		assertFalse(muelle.equals(muelle2));
	}

	@Test
	public void testEqualsDistintosContenedores() {
		Muelle muelle = new Muelle(10, new GPSCoordinate(0, 0), true, 3, 3);
		Muelle muelle2 = new Muelle(10, new GPSCoordinate(0, 0), true, 3, 3);
		Contenedor contenedor1 = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		Contenedor contenedor2 = new Contenedor("BICU1234570", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);	
		Contenedor contenedor3 = new Contenedor("CSQU3054383", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		muelle.apilarContenedor(contenedor1, 0);
		muelle.apilarContenedor(contenedor2, 1);
		muelle.apilarContenedor(contenedor3, 1);
		muelle2.apilarContenedor(contenedor1, 0);
		muelle2.apilarContenedor(contenedor2, 2);
		muelle2.apilarContenedor(contenedor3, 1);
		assertFalse(muelle.equals(muelle2));
	}

	
	


	
	

	
	


}
