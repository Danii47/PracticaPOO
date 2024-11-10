package es.uva.poo.lab;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import es.uva.inf.poo.maps.GPSCoordinate;

public class PuertoTest {

	private Muelle muelle;
	private Muelle[] muelles;
	private Puerto puerto;
	private String codigoIdentificacion;
	
	@Before
	public void setUp() {
		Muelle muelle = new Muelle(50, new GPSCoordinate(41.623071, -4.749593), true, 10, 10);
		this.muelle = muelle;
		muelles = new Muelle[] {muelle};
		codigoIdentificacion = "ZA-ZAZ";
		puerto = new Puerto(codigoIdentificacion, muelles);
	}

	@Test
    public void testConstructorValido() {
        assertArrayEquals(muelles, puerto.getMuelles());
        assertEquals(codigoIdentificacion, puerto.getCodigoIdentificacion());
    }
	
	@Test
    public void testConstructorSoloCodigoIdentificacionValido() {
		Puerto p = new Puerto(codigoIdentificacion);
        assertArrayEquals(new Muelle[] {}, p.getMuelles());
        assertEquals(codigoIdentificacion, p.getCodigoIdentificacion());
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testConstructorCodigoDeIdentificacionNullDaError() {
        new Puerto(null, muelles);
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testConstructorMuellesNullDaError() {
        new Puerto(codigoIdentificacion, null);
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testConstructorCodigoDeIdentificacionConDistintaLongitudDaError() {
        new Puerto("AAA-AAA", muelles);
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testConstructorCodigoDeIdentificacionCon3LetrasGuion2LetrasDaError() {
        new Puerto("AAA-AAA", muelles);
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testConstructorCodigoDeIdentificacionConNumerosDaError() {
        new Puerto("A2-AAA", muelles);
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testConstructorCodigoDeIdentificacionConNumeros2DaError() {
        new Puerto("AA-A0A", muelles);
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testConstructorCodigoDeIdentificacionConCaracteresInvalidosDaError() {
        new Puerto("AA-A[A", muelles);
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testConstructorCodigoDeIdentificacionConCaracteresInvalidos2DaError() {
        new Puerto("AA-A@A", muelles);
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testConstructorCodigoDeIdentificacionConCaracteresInvalidos3DaError() {
        new Puerto("[A-AAA", muelles);
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testConstructorCodigoDeIdentificacionConCaracteresInvalidos4DaError() {
        new Puerto("A@-AAA", muelles);
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testConstructorSoloCodigoDeIdentificacionConDistintaLongitudDaError() {
        new Puerto("AAA-AAA");
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testConstructorSoloCodigoDeIdentificacionCon3LetrasGuion2LetrasDaError() {
        new Puerto("AAA-AAA");
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testConstructorSoloCodigoDeIdentificacionConNumerosDaError() {
        new Puerto("A2-AAA");
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testConstructorSoloCodigoDeIdentificacionConNumeros2DaError() {
        new Puerto("AA-A0A");
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testConstructorSoloCodigoDeIdentificacionConCaracteresInvalidosDaError() {
        new Puerto("AA-[ZA");
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testConstructorSoloCodigoDeIdentificacionConCaracteresInvalidos2DaError() {
        new Puerto("AA-A@A");
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testConstructorSoloCodigoDeIdentificacionConCaracteresInvalidos3DaError() {
        new Puerto("[A-AAA");
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testConstructorSoloCodigoDeIdentificacionConCaracteresInvalidos4DaError() {
        new Puerto("A@-AAA");
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testConstructorSoloCodigoDeIdentificacionSinGuionDaError() {
        new Puerto("AAAAAA");
    }
	
	@Test
	public void testConstructorCopiaFunciona() {
		Puerto p = new Puerto(puerto);
		assertEquals(p, puerto);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorCopiaConNullDaError() {
		new Puerto((Puerto) null);
	}
	
	@Test
	public void testGetNumeroMuellesFuncionaCon1Longitud() {
		assertEquals(1, puerto.getNumeroMuelles());
	}
	
	@Test
	public void testGetNumeroMuellesFuncionaCon0Longitud() {
		Puerto p = new Puerto(codigoIdentificacion);
		
		assertEquals(0, p.getNumeroMuelles());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetMuellePorIndiceInvalidoPorAbajoDaError() {
		puerto.getMuellePorIndice(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetMuellePorIndiceInvalidoPorArribaDaError() {
		puerto.getMuellePorIndice(1);
	}
	
	@Test
	public void testGetMuellePorIndiceFunciona() {
		Muelle m = puerto.getMuellePorIndice(0);
		assertEquals(muelle, m);
		assertNotSame(muelle, m);
	}
	
	@Test
	public void testGetOperativoFuncionaTrue() {
		assertTrue(puerto.getOperativo());
	}
	
	@Test
	public void testGetOperativoFuncionaFalse() {
		Puerto p = new Puerto(codigoIdentificacion);
		assertFalse(p.getOperativo());
	}
	
	@Test
	public void testGetOperativoFuncionaTrueCon2Muelles() {
		Muelle m = new Muelle(60, new GPSCoordinate(41.623071, -4.749593), false, 10, 10);
		puerto.agregarMuelle(m);
		assertTrue(puerto.getOperativo());
	}
	
	@Test
	public void testGetOperativoFuncionaFalseCon2Muelles() {
		Muelle m = new Muelle(60, new GPSCoordinate(41.623071, -4.749593), false, 10, 10);
		Muelle m2 = new Muelle(65, new GPSCoordinate(41.623071, -4.749593), false, 10, 10);
		Puerto p = new Puerto(codigoIdentificacion, new Muelle[] {m, m2});
		assertFalse(p.getOperativo());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAgregarMuelleNullDaError() {
		puerto.agregarMuelle(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAgregarMuelleConMismaIdentificacionDaError() {
		Muelle m = new Muelle(50, new GPSCoordinate(41.623071, -4.749593), false, 10, 10);
		puerto.agregarMuelle(m);
	}
	
	@Test
	public void testAgregarMuelleFunciona() {
		Muelle m = new Muelle(55, new GPSCoordinate(41.623071, -4.749593), false, 10, 10);
		puerto.agregarMuelle(m);
		
		assertArrayEquals(new Muelle[] {muelle, m}, puerto.getMuelles());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEliminarMuelleCodigoInvalidoAbajo() {
		puerto.eliminarMuelle(9);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEliminarMuelleCodigoInvalidoArriba() {
		puerto.eliminarMuelle(100);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEliminarMuelleCodigoInvalidoNoExiste() {
		puerto.eliminarMuelle(55);
	}
	
	@Test
	public void testEliminarMuelleCodigoValido() {
		puerto.eliminarMuelle(50);
		assertArrayEquals(new Muelle[] {}, puerto.getMuelles());
	}
	
	@Test
	public void testGetCompletoFalse() {
		assertFalse(puerto.getCompleto());
	}
	
	@Test
	public void testGetCompletoFalseConContenedores() {
		muelle.apilarContenedor(new Contenedor("ABCJ1231327", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true), 1);
		muelle.setCodigoIdentificacion(69);
		puerto.agregarMuelle(muelle);
		assertFalse(puerto.getCompleto());
	}
	
	@Test
	public void testGetCompletoLlenoDaTrue() {
		Muelle m = new Muelle(75, new GPSCoordinate(41.623071, -4.749593), true, 1, 1);
		m.apilarContenedor(new Contenedor("ABCJ1231327", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true), 0);
		Puerto p = new Puerto("AA-AAA", new Muelle[] {m});
		assertTrue(p.getCompleto());
	}
	
	@Test
	public void testGetMuellesOperativosTodosOperativos() {
		assertArrayEquals(muelles, puerto.getMuellesOperativos());
		assertNotSame(muelles, puerto.getMuellesOperativos());
	}
	
	@Test
	public void testGetMuellesOperativosAlgunosOperativos() {
		Muelle m = new Muelle(75, new GPSCoordinate(41.623071, -4.749593), false, 1, 1);
		puerto.agregarMuelle(m);
		
		assertArrayEquals(muelles, puerto.getMuellesOperativos());
		assertNotSame(muelles, puerto.getMuellesOperativos());
	}
	
	@Test
	public void testGetMuellesOperativosTodosOperativosDosLongitud() {
		Muelle m = new Muelle(75, new GPSCoordinate(41.623071, -4.749593), true, 1, 1);
		puerto.agregarMuelle(m);
		
		assertArrayEquals(new Muelle[] {muelle, m}, puerto.getMuellesOperativos());
		assertNotSame(new Muelle[] {muelle, m}, puerto.getMuellesOperativos());
	}
	
	@Test
	public void testGetMuellesConEspacio() {
		assertArrayEquals(muelles, puerto.getMuellesConEspacio());
		assertNotSame(muelles, puerto.getMuellesConEspacio());
	}
	
	@Test
	public void testGetMuellesConEspacioSoloAlgunos() {
		Muelle m = new Muelle(75, new GPSCoordinate(41.623071, -4.749593), true, 1, 1);
		m.apilarContenedor(new Contenedor("ABCJ1231327", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true), 0);
		puerto.agregarMuelle(m);
		
		assertArrayEquals(muelles, puerto.getMuellesConEspacio());
		assertNotSame(muelles, puerto.getMuellesConEspacio());
	}
	
	@Test
	public void testGetMuellesConEspacioNinguno() {
		Muelle m = new Muelle(75, new GPSCoordinate(41.623071, -4.749593), true, 1, 1);
		m.apilarContenedor(new Contenedor("ABCJ1231327", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true), 0);
		Puerto p = new Puerto(codigoIdentificacion, new Muelle[] {m});
		
		assertArrayEquals(new Muelle[] {}, p.getMuellesConEspacio());
		assertNotSame(new Muelle[] {}, p.getMuellesConEspacio());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetMuellesCercanosLocalizacionNullDaError() {
		puerto.getMuellesCercanos(null, 10);
	}
	
	@Test
	public void testGetMuellesCercanosLocalizacionFunciona() {
		assertArrayEquals(puerto.getMuellesCercanos(new GPSCoordinate(41.623071, -4.749593), 5), muelles);
	}
	
	@Test
	public void testGetMuellesCercanosAlgunos() {
		Muelle m = new Muelle(75, new GPSCoordinate(0, 0), true, 1, 1);
		m.apilarContenedor(new Contenedor("ABCJ1231327", 1, 1, 1, Contenedor.ESTADOS.RECOGIDA, true), 0);
		puerto.agregarMuelle(m);
		assertArrayEquals(puerto.getMuellesCercanos(new GPSCoordinate(41.623071, -4.749593), 5), muelles);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMuellePerteneceAlPuertoNullDaError() {
		puerto.muellePerteneceAlPuerto(null);
	}
	
	@Test
	public void testMuellePerteneceAlPuertoNoPertenece() {
		Muelle m = new Muelle(75, new GPSCoordinate(0, 0), true, 1, 1);
		assertFalse(puerto.muellePerteneceAlPuerto(m));
	}
	
	@Test
	public void testMuellePerteneceAlPuertoPertenece() {
		assertTrue(puerto.muellePerteneceAlPuerto(muelle));
	}
	
	@Test
	public void testEqualsConNull() {
		assertFalse(puerto.equals(null));
	}
	
	@Test
	public void testEqualsIguales() {
		Puerto p = new Puerto(puerto);
		assertTrue(puerto.equals(p));
	}
}
