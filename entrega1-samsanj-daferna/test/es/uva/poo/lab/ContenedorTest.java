package es.uva.poo.lab;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ContenedorTest {
	
	private Contenedor contenedor;
	
	@Before
    public void setUp() {
		contenedor = new Contenedor("BICU1234565", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
	}

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
		contenedor.setEstado(Contenedor.ESTADOS.TRANSITO);
		
		assertEquals(Contenedor.ESTADOS.TRANSITO, contenedor.getEstado());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetterEstadoConNullTiraArgumentException() {
		contenedor.setEstado(null);
	}
	
	@Test
	public void testSetEnTransito() {
		contenedor.setEnTransito();
		
		assertEquals(Contenedor.ESTADOS.TRANSITO, contenedor.getEstado());
	}
	
	@Test
	public void testSetEnRecogida() {
		contenedor.setEnRecogida();
		
		assertEquals(Contenedor.ESTADOS.RECOGIDA, contenedor.getEstado());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCopiarContenedorConNullDaError() {
		new Contenedor(null);
	}
	
	@Test
	public void testCopiarContenedorFunciona() {
		Contenedor c = new Contenedor(contenedor);
		assertNotSame(contenedor, c);
		assertEquals(contenedor, c);
	}
}
