package es.uva.poo.lab;

import static org.junit.Assert.*;

import org.junit.Test;

public class ContenedorTest {

	
	// TODO: Cuantos tests se hacen para el código?
	@Test(expected = IllegalArgumentException.class)
	public void testCrearContenedorConIdentificacionNullDevuelveError() {
		new Contenedor(null, 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCrearContenedorConIdentificacionNoValidaDevuelveError() {
		new Contenedor("ABCD1231320", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
	}

	@Test
	public void testGetterEstadoRecogida() {
		Contenedor c = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		assertEquals(Contenedor.ESTADOS.RECOGIDA, c.getEstado());
	}
	
	@Test
	public void testGetterEstadoTransito() {
		Contenedor c = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.TRANSITO, true);

		assertEquals(Contenedor.ESTADOS.TRANSITO, c.getEstado());
	}
	
	@Test
	public void testSetterEstadoNormal() {
		Contenedor c = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		
		c.setEstado(Contenedor.ESTADOS.TRANSITO);
		
		assertEquals(Contenedor.ESTADOS.TRANSITO, c.getEstado());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetterEstadoConNullTiraArgumentException() {
		Contenedor c = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		
		c.setEstado(null);
	}
	
	@Test
	public void testSetEnTransito() {
		Contenedor c = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
		
		c.setEnTransito();
		
		assertEquals(Contenedor.ESTADOS.TRANSITO, c.getEstado());
	}
	
	@Test
	public void testSetEnRecogida() {
		Contenedor c = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.TRANSITO, true);
		
		c.setEnRecogida();
		
		assertEquals(Contenedor.ESTADOS.RECOGIDA, c.getEstado());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCopiarContenedorConNullDaError() {
		new Contenedor(null);
	}
}
