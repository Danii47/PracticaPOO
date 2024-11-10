package es.uva.poo.lab;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import es.uva.inf.poo.maps.GPSCoordinate;

public class ContenedorTest {
	
	private Contenedor contenedor;
	
	@Before
    public void setUp() {
		contenedor = new Contenedor("BICU1234565", 5000, 10000, 20, Contenedor.ESTADOS.RECOGIDA, true);
	}

	@Test
    public void testConstructor() {
        assertEquals("BICU1234565", contenedor.getCodigoIdentificador());
        assertEquals(5000, contenedor.getPesoKg(), 0.001);
        assertEquals(10000, contenedor.getCargaUtilMaximaKg(), 0.001);
        assertEquals(20, contenedor.getVolumenMetrosCubicos(), 0.001);
        assertEquals(Contenedor.ESTADOS.TRANSITO, contenedor.getEstado());
        assertTrue(contenedor.getTecho());
    }
	
	@Test(expected = IllegalArgumentException.class)
	public void testCrearContenedorConIdentificacionNoValidaDevuelveError() {
		new Contenedor("ABCD1231320", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
	}
	
	@Test(expected = IllegalArgumentException.class)
    public void testCrearContenedorConIdentificacionMenosCaracteresDevuelveError() {
        new Contenedor("BICU123", 5000, 10000, 20, Contenedor.ESTADOS.TRANSITO, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorDevuelveErrorConPesoNegativoPeso() {
        new Contenedor("BICU1234565", -5000, 10000, 20, Contenedor.ESTADOS.TRANSITO, true);
    }
	
	@Test(expected = IllegalArgumentException.class)
	public void testCrearContenedorConIdentificacionNullDevuelveError() {
		new Contenedor(null, 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true);
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
    public void testGetVolumenPiesCubicos() {
		double piesCubicos = contenedor.getVolumenMetrosCubicos() * Contenedor.CONVERSION_PIES;
        assertEquals(piesCubicos, contenedor.getVolumenPiesCubicos(), 0.001);
    }

    @Test
    public void testGetPesoLibras() {
    	double libras = contenedor.getPesoKg() * Contenedor.CONVERSION_LIBRAS;
        assertEquals(libras, contenedor.getPesoLibras(), 0.001);
    }

    @Test
    public void testGetTecho() {
    	assertTrue(contenedor.getTecho());
    	contenedor.setTecho(false);
    	assertFalse(contenedor.getTecho());
    }
    
    @Test
    public void testSetTecho() {
        contenedor.setTecho(false);
        assertFalse(contenedor.getTecho());
    }
	
	@Test
	public void testCopiarContenedorFunciona() {
		Contenedor c = new Contenedor(contenedor);
		assertNotSame(contenedor, c);
		assertEquals(contenedor, c);
	}
	
	@Test
    public void testEqualsIguales() {
        Contenedor c = new Contenedor("BICU1234565", 5000, 10000, 20, Contenedor.ESTADOS.RECOGIDA, true);
        assertTrue(contenedor.equals(c));
    }

    @Test
    public void testEqualsObjetoDiferenteId() {
        Contenedor c = new Contenedor("CSQU3054383", 5000, 10000, 20, Contenedor.ESTADOS.RECOGIDA, true);
        assertFalse(contenedor.equals(c));
    }
    
    @Test
    public void testEqualsObjetoDiferentePeso() {
        Contenedor c = new Contenedor("BICU1234565", 5001, 10000, 20, Contenedor.ESTADOS.RECOGIDA, true);
        assertFalse(contenedor.equals(c));
    }
    
    @Test
    public void testEqualsObjetoDiferentePesoMaximo() {
        Contenedor c = new Contenedor("BICU1234565", 5000, 10001, 20, Contenedor.ESTADOS.RECOGIDA, true);
        assertFalse(contenedor.equals(c));
    }
    
    @Test
    public void testEqualsObjetoDiferenteMetrosCubicos() {
        Contenedor c = new Contenedor("BICU1234565", 5000, 10000, 21, Contenedor.ESTADOS.RECOGIDA, true);
        assertFalse(contenedor.equals(c));
    }
    
    @Test
    public void testEqualsObjetoDiferenteEstado() {
        Contenedor c = new Contenedor("BICU1234565", 5000, 10000, 20, Contenedor.ESTADOS.TRANSITO, true);
        assertFalse(contenedor.equals(c));
    }
    
    @Test
    public void testEqualsObjetoDiferenteTecho() {
        Contenedor c = new Contenedor("BICU1234565", 5000, 10000, 20, Contenedor.ESTADOS.RECOGIDA, false);
        assertFalse(contenedor.equals(c));
    }
    
    @Test
    public void testGetTrayectosInicialmenteVacio() {
        assertEquals(0, contenedor.getTrayectos().length);
    }
    
    @Test
    public void testGetTrayectosConUnTrayecto() {
    	Muelle muelleOrigen = new Muelle(50, new GPSCoordinate(10, 10), true, 10, 10);
    	Muelle muelleDestino = new Muelle(50, new GPSCoordinate(10, 10), true, 10, 10);
        Puerto puertoOrigen = new Puerto("AA-AAA");
        Puerto puertoDestino = new Puerto("AA-AAB");
        LocalDate fechaInicio = LocalDate.of(2024, 11, 1);
        LocalDate fechaFin = LocalDate.of(2024, 11, 10);
        Trayecto trayecto = new Trayecto(muelleOrigen, puertoOrigen, fechaInicio, muelleDestino, puertoDestino, fechaFin, 100, 100);
    	
    	contenedor.agregarTrayecto(trayecto);
        assertEquals(1, contenedor.getTrayectos().length);
        assertEquals(trayecto, contenedor.getTrayectos()[0]);
    }
    
	
}
