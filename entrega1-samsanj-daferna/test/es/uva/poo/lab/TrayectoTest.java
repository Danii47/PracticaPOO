package es.uva.poo.lab;

import java.time.LocalDate;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import es.uva.inf.poo.maps.GPSCoordinate;


public class TrayectoTest {

    private Muelle muelleOrigen;
    private Muelle muelleDestino;
    private Puerto puertoOrigen;
    private Puerto puertoDestino;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double costePorDia;
    private double costePorMillaNautica;
    private Trayecto trayecto;

    @Before
    public void setUp() {
        muelleOrigen = new Muelle(50, new GPSCoordinate(41.623071, -4.749593), true, 10, 10);
        muelleDestino = new Muelle(51, new GPSCoordinate(41.6372231, -4.738908), true, 10, 10);
        puertoOrigen = new Puerto("AA-AAA", new Muelle[] {muelleOrigen});
        puertoDestino = new Puerto("AA-AAB", new Muelle[] {muelleDestino});
        fechaInicio = LocalDate.of(2024, 11, 1);
        fechaFin = LocalDate.of(2024, 11, 10);
        costePorDia = 100;
        costePorMillaNautica = 50;
        
        trayecto = new Trayecto(muelleOrigen, puertoOrigen, fechaInicio, muelleDestino, puertoDestino, fechaFin, costePorDia, costePorMillaNautica);
    }
	

    @Test
    public void testConstructorValido() {
        assertEquals(muelleOrigen, trayecto.getMuelleOrigen());
        assertEquals(puertoOrigen, trayecto.getPuertoOrigen());
        assertEquals(muelleDestino, trayecto.getMuelleDestino());
        assertEquals(puertoDestino, trayecto.getPuertoDestino());
        assertEquals(fechaInicio, trayecto.getFechaInicio());
        assertEquals(fechaFin, trayecto.getFechaFin());
        assertEquals(costePorDia, trayecto.getCostePorDia(), 0.001);
        assertEquals(costePorMillaNautica, trayecto.getCostePorMillaNautica(), 0.001);
    }
    

	@Test(expected = IllegalArgumentException.class)
	public void testCrearTrayectoConMuelleOrigenNull() {
		new Trayecto(null, puertoOrigen, fechaInicio, muelleDestino, puertoDestino, fechaFin, costePorDia, costePorMillaNautica);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCrearTrayectoConPuertoOrigenNull() {
		new Trayecto(muelleOrigen, null, fechaInicio, muelleDestino, puertoDestino, fechaFin, costePorDia, costePorMillaNautica);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCrearTrayectoConFechaInicioNull() {
		new Trayecto(muelleOrigen, puertoOrigen, null, muelleDestino, puertoDestino, fechaFin, costePorDia, costePorMillaNautica);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCrearTrayectoConMuelleDestinoNull() {
		new Trayecto(muelleOrigen, puertoOrigen, fechaInicio, null, puertoDestino, fechaFin, costePorDia, costePorMillaNautica);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCrearTrayectoConPuertoDestinoNull() {
		new Trayecto(muelleOrigen, puertoOrigen, fechaInicio, muelleDestino, null, fechaFin, costePorDia, costePorMillaNautica);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCrearTrayectoConFechaFinNull() {
		new Trayecto(muelleOrigen, puertoOrigen, fechaInicio, muelleDestino, puertoDestino, null, costePorDia, costePorMillaNautica);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCrearTrayectoConFechaInicioMasTardeQueFin() {
		LocalDate fi = LocalDate.now();
		LocalDate ff = LocalDate.of(2000, 1, 1);
		
		new Trayecto(muelleOrigen, puertoOrigen, fi, muelleDestino, puertoDestino, ff, costePorDia, costePorMillaNautica);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCrearTrayectoConCostePorDiaNegativo() {
		double costePorDia = -100;
		new Trayecto(muelleOrigen, puertoOrigen, fechaInicio, muelleDestino, puertoDestino, fechaFin, costePorDia, costePorMillaNautica);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCrearTrayectoConCostePorMillaNegativo() {
		double costePorMillaNautica = -50;
		new Trayecto(muelleOrigen, puertoOrigen, fechaInicio, muelleDestino, puertoDestino, fechaFin, costePorDia, costePorMillaNautica);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCrearTrayectoConMuelleOrigenNoPerteneceAPuertoOrigen() {
		Puerto po = new Puerto("BB-AAA");
		new Trayecto(muelleOrigen, po, fechaInicio, muelleDestino, puertoDestino, fechaFin, costePorDia, costePorMillaNautica);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCrearTrayectoConMuelleDestinoNoPerteneceAPuertoDestino() {
		Puerto pd = new Puerto("CC-AAA");
		new Trayecto(muelleOrigen, puertoOrigen, fechaInicio, muelleDestino, pd, fechaFin, costePorDia, costePorMillaNautica);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCrearTrayectoConPuertoOrigenNoOperativoDaError() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle mo = new Muelle(70, gpsi, false, 10, 10);
		Puerto po = new Puerto("CC-AAA", new Muelle[] {mo});

		new Trayecto(mo, po, fechaInicio, muelleDestino, puertoDestino, fechaFin, costePorDia, costePorMillaNautica);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCrearTrayectoConPuertoDestinoNoOperativoDaError() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle md = new Muelle(70, gpsi, false, 10, 10);
		Puerto pd = new Puerto("CC-AAA", new Muelle[] {md});

		new Trayecto(muelleOrigen, puertoOrigen, fechaInicio, md, pd, fechaFin, costePorDia, costePorMillaNautica);
	}
	
	@Test
	public void testCrearTrayectoConstructorCopia() {
		Trayecto t = new Trayecto(trayecto);
		assertEquals(trayecto, t);
		assertNotSame(trayecto, t);
	}
	
	@Test
	public void testGetCostePorDia() {
		assertEquals(100, trayecto.getCostePorDia(), 0.001);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetCostePorDiaNegativoDaError() {
		trayecto.setCostePorDia(-1);
	}
	
	@Test
	public void testSetCostePorDiaFunciona() {
		trayecto.setCostePorDia(1);
		assertEquals(1, trayecto.getCostePorDia(), 0.001);
	}
	
	@Test
	public void testGetCostePorMillaNautica() {
		assertEquals(50, trayecto.getCostePorMillaNautica(), 0.001);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetCostePorMillaNauticaNegativoDaError() {
		trayecto.setCostePorMillaNautica(-1);
	}
	
	@Test
	public void testSetCostePorMillaNauticaFunciona() {
		trayecto.setCostePorMillaNautica(1);
		assertEquals(1, trayecto.getCostePorMillaNautica(), 0.001);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetMuelleOrigenNoPerteneceAlPuertoOrigenDaError() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle mo = new Muelle(70, gpsi, true, 10, 10);
		trayecto.setMuelleOrigen(mo);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetMuelleOrigenNullDaError() {
		trayecto.setMuelleOrigen(null);
	}
	
	@Test
	public void testSetMuelleOrigenFunciona() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle mo = new Muelle(70, gpsi, true, 10, 10);
		puertoOrigen.agregarMuelle(mo);
		
		Trayecto t = new Trayecto(muelleOrigen, puertoOrigen, fechaInicio, muelleDestino, puertoDestino, fechaFin, costePorDia, costePorMillaNautica);
		
		t.setMuelleOrigen(mo);
		assertEquals(t.getMuelleOrigen(), mo);
		assertNotSame(t.getMuelleOrigen(), mo);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetMuelleDestinoNoPerteneceAlPuertoDestinoDaError() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle md = new Muelle(70, gpsi, true, 10, 10);
		trayecto.setMuelleDestino(md);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetMuelleDestinoNullDaError() {
		trayecto.setMuelleDestino(null);
	}
	
	@Test
	public void testSetMuelleDestinoFunciona() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle md = new Muelle(70, gpsi, true, 10, 10);
		puertoDestino.agregarMuelle(md);
		
		Trayecto t = new Trayecto(muelleOrigen, puertoOrigen, fechaInicio, muelleDestino, puertoDestino, fechaFin, costePorDia, costePorMillaNautica);
		
		t.setMuelleDestino(md);
		assertEquals(t.getMuelleDestino(), md);
		assertNotSame(t.getMuelleDestino(), md);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFechaFinSuperiorAOtraNull() {
		trayecto.fechaFinSuperiorAOtra(null);
	}
	
	@Test
	public void testFechaFinSuperiorAOtraFalso() {
		LocalDate fi = LocalDate.now();
		LocalDate ff = LocalDate.now();
		
		Trayecto t = new Trayecto(muelleOrigen, puertoOrigen, fi, muelleDestino, puertoDestino, ff, costePorDia, costePorMillaNautica);
		
		assertFalse(t.fechaFinSuperiorAOtra(LocalDate.now()));
	}
	
	@Test
	public void testFechaFinSuperiorAOtraVerdadero() {
		LocalDate ff = LocalDate.now();
		
		Trayecto t = new Trayecto(muelleOrigen, puertoOrigen, fechaInicio, muelleDestino, puertoDestino, ff, costePorDia, costePorMillaNautica);
		
		assertTrue(t.fechaFinSuperiorAOtra(LocalDate.of(2020, 1, 1)));
	}
	
	@Test
	public void testGetDuracionDias0() {
		LocalDate fi = LocalDate.now();
		LocalDate ff = LocalDate.now();
		
		Trayecto t = new Trayecto(muelleOrigen, puertoOrigen, fi, muelleDestino, puertoDestino, ff, costePorDia, costePorMillaNautica);
		
		assertEquals(0, t.getDuracionDias());
	}
	 
	@Test
	public void testGetDuracion10Dias() {
		LocalDate fi = LocalDate.of(2024, 1, 10);
		LocalDate ff = LocalDate.of(2024, 1, 20);
		
		Trayecto t = new Trayecto(muelleOrigen, puertoOrigen, fi, muelleDestino, puertoDestino, ff, costePorDia, costePorMillaNautica);
		
		assertEquals(10, t.getDuracionDias());
	}
	
	@Test
	public void testGetDistanciaKM() {
		assertEquals(1.807, trayecto.getDistanciaKM(), GPSCoordinate.PRECISION_IN_DISTANCE);
	}

	@Test
	public void testGetDistanciaMillasNauticas() {
		assertEquals(trayecto.getDistanciaKM() * Trayecto.CONVERSION_MILLAS_NAUTICAS, trayecto.getDistanciaMillasNauticas(), GPSCoordinate.PRECISION_IN_DISTANCE);
	}
	
	@Test
	public void testGetCosteTrayecto() {
		double costeEsperado = (trayecto.getDuracionDias() * trayecto.getCostePorDia()) + (trayecto.getDistanciaMillasNauticas() * trayecto.getCostePorMillaNautica());
		 
		assertEquals(costeEsperado, trayecto.getCosteTrayecto(), 0.001);
	}
	
	@Test
	public void testGetCosteTrayectoCon0Dias() {
		LocalDate fi = LocalDate.now();
		LocalDate ff = LocalDate.now();

		Trayecto t = new Trayecto(muelleOrigen, puertoOrigen, fi, muelleDestino, puertoDestino, ff, costePorDia, costePorMillaNautica);

		double costeEsperado = t.getCostePorMillaNautica() * t.getDistanciaMillasNauticas();
 
		assertEquals(costeEsperado, t.getCosteTrayecto(), 0.001);
	}
	 
	@Test
	public void testGetCosteTrayectoCon0MillasNauticas() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle mo = new Muelle(55, gpsi, true, 10, 10);
		puertoOrigen.agregarMuelle(mo);
		GPSCoordinate gpsd = new GPSCoordinate(10, 10);
		Muelle md = new Muelle(60, gpsd, true, 10, 10);
		puertoDestino.agregarMuelle(md);

		Trayecto t = new Trayecto(mo, puertoOrigen, fechaInicio, md, puertoDestino, fechaFin, costePorDia, costePorMillaNautica);

		double costeEsperado = t.getCostePorDia() * t.getDuracionDias();

		assertEquals(costeEsperado, t.getCosteTrayecto(), 0.001);
	}
	 
	@Test(expected = IllegalArgumentException.class)
	public void testSetPuertoYMuelleOrigenPuertoOrigenNullDaError() {
		trayecto.setPuertoYMuelleOrigen(null, muelleOrigen);
	}
	 
	@Test(expected = IllegalArgumentException.class)
	public void testSetPuertoYMuelleOrigenMuelleOrigenNullDaError() {
		trayecto.setPuertoYMuelleOrigen(puertoOrigen, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetPuertoYMuelleOrigenMuelleOrigenNoPerteneceAPuertoOrigenDaError() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle mo = new Muelle(70, gpsi, true, 10, 10);

		trayecto.setPuertoYMuelleOrigen(puertoOrigen, mo);
	}
	 
	@Test
	public void testSetPuertoYMuelleOrigenFunciona() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle mo = new Muelle(80, gpsi, true, 10, 10);
		Puerto po = new Puerto("FF-AAA", new Muelle[] {mo});

		trayecto.setPuertoYMuelleOrigen(po, mo);
		assertEquals(trayecto.getPuertoOrigen(), po);
		assertEquals(trayecto.getMuelleOrigen(), mo);
		assertNotSame(trayecto.getPuertoOrigen(), po);
		assertNotSame(trayecto.getMuelleOrigen(), mo);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetPuertoYMuelleDestinoPuertoDestinoNullDaError() {
		trayecto.setPuertoYMuelleDestino(null, muelleDestino);
	}
	 
	@Test(expected = IllegalArgumentException.class)
	public void testSetPuertoYMuelleDestinoMuelleDestinoNullDaError() {
		trayecto.setPuertoYMuelleDestino(puertoDestino, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetPuertoYMuelleDestinoMuelleDestinoNoPerteneceAPuertoDestinoDaError() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle md = new Muelle(85, gpsi, true, 10, 10);

		trayecto.setPuertoYMuelleDestino(puertoDestino, md);
	}
	
	@Test
	public void testSetPuertoYMuelleDestinoFunciona() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle md = new Muelle(77, gpsi, true, 10, 10);
		Puerto pd = new Puerto("EE-AAA", new Muelle[] {md});

		trayecto.setPuertoYMuelleDestino(pd, md);
		assertEquals(trayecto.getPuertoDestino(), pd);
		assertEquals(trayecto.getMuelleDestino(), md);
		assertNotSame(trayecto.getPuertoDestino(), pd);
		assertNotSame(trayecto.getMuelleDestino(), md);
	}
	
	@Test
	public void testEqualsValido() {
		Trayecto t = new Trayecto(muelleOrigen, puertoOrigen, fechaInicio, muelleDestino, puertoDestino, fechaFin, costePorDia, costePorMillaNautica);

		assertTrue(t.equals(trayecto));
	}
	
	@Test
	public void testEqualsValidoConSiMismo() {	
		assertTrue(trayecto.equals(trayecto));
	}
	
	@Test
	public void testEqualsMuelleOrigenDistino() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle mo = new Muelle(69, gpsi, true, 10, 10);
		puertoOrigen.agregarMuelle(mo);

		Trayecto t = new Trayecto(mo, puertoOrigen, fechaInicio, muelleDestino, puertoDestino, fechaFin, costePorDia, costePorMillaNautica);

		assertFalse(t.equals(trayecto));
	}
	
	@Test
	public void testEqualsPuertoOrigenDistinto() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle mo = new Muelle(70, gpsi, true, 10, 10);
		Puerto po = new Puerto("DD-AAA", new Muelle[] {mo});

		Trayecto t = new Trayecto(mo, po, fechaInicio, muelleDestino, puertoDestino, fechaFin, costePorDia, costePorMillaNautica);
			
		assertFalse(t.equals(trayecto));
	}

	@Test
	public void testEqualsFechaInicioDistinta() {
		Trayecto t = new Trayecto(muelleOrigen, puertoOrigen, LocalDate.of(2001, 1, 1), muelleDestino, puertoDestino, fechaFin, costePorDia, costePorMillaNautica);
		
		assertFalse(t.equals(trayecto));
	}

	@Test
	public void testEqualsMuelleDestinoDistino() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle md = new Muelle(70, gpsi, true, 10, 10);
		puertoDestino.agregarMuelle(md);
		
		Trayecto t = new Trayecto(muelleOrigen, puertoOrigen, fechaInicio, md, puertoDestino, fechaFin, costePorDia, costePorMillaNautica);
		
		assertFalse(t.equals(trayecto));
	}

	@Test
	public void testEqualsPuertoDestinoDistinto() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle md = new Muelle(70, gpsi, true, 10, 10);
		Puerto pd = new Puerto("DD-AAA", new Muelle[] {md});
		
		Trayecto t = new Trayecto(muelleOrigen, puertoOrigen, fechaInicio, md, pd, fechaFin, costePorDia, costePorMillaNautica);
		
		assertFalse(t.equals(trayecto));
	}

	@Test
	public void testEqualsFechaFinDistinta() {
		Trayecto t = new Trayecto(muelleOrigen, puertoOrigen, fechaInicio, muelleDestino, puertoDestino, LocalDate.of(2040, 1, 1), costePorDia, costePorMillaNautica);
		assertFalse(t.equals(trayecto));
	}

	@Test
	public void testEqualsCostePorDiaDistinto() {
		Trayecto t = new Trayecto(muelleOrigen, puertoOrigen, fechaInicio, muelleDestino, puertoDestino, fechaFin, 1, costePorMillaNautica);
		assertFalse(t.equals(trayecto));
	}

	@Test
	public void testEqualsCostePorMillaNautica() {
		Trayecto t = new Trayecto(muelleOrigen, puertoOrigen, fechaInicio, muelleDestino, puertoDestino, fechaFin, costePorDia, 1);
		assertFalse(t.equals(trayecto));
	}

	@Test
	public void testEqualsConNullDaFalse() {
		assertFalse(trayecto.equals(null));
	}

	@Test
	public void testToString() {	
		String esperado = (
			"Localidad y país Puerto Origen: " + puertoOrigen.getCodigoIdentificacion() +
			"\nFecha de inicio del trayecto: " + fechaInicio.toString() +
			"\nLocalidad y país Puerto Destino: " + puertoDestino.getCodigoIdentificacion() +
			"\nFecha de fin del trayecto: " + fechaFin.toString()
		);

		assertEquals(esperado, trayecto.toString());
	}
}
