package es.uva.poo.lab;

import java.time.LocalDate;
import static org.junit.Assert.*;

import org.junit.Test;

import es.uva.inf.poo.maps.GPSCoordinate;

public class TrayectoTest {

	@Test(expected = IllegalArgumentException.class)
	public void testCrearTrayectoConMuelleOrigenNull() {
		Puerto po = new Puerto("AA-AAA");
		LocalDate fi = LocalDate.now();
		
		GPSCoordinate gpsd = new GPSCoordinate(10, 10);
		Muelle md = new Muelle(50, gpsd, true, 10, 10);
		Puerto pd = new Puerto("AA-AAB");
		LocalDate ff = LocalDate.now();
		
		new Trayecto(null, po, fi, md, pd, ff);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCrearTrayectoConPuertoOrigenNull() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle mo = new Muelle(50, gpsi, true, 10, 10);
		LocalDate fi = LocalDate.now();
		
		GPSCoordinate gpsd = new GPSCoordinate(10, 10);
		Muelle md = new Muelle(50, gpsd, true, 10, 10);
		Puerto pd = new Puerto("AA-AAB");
		LocalDate ff = LocalDate.now();
		
		new Trayecto(mo, null, fi, md, pd, ff);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCrearTrayectoConFechaInicioNull() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle mo = new Muelle(50, gpsi, true, 10, 10);
		Puerto po = new Puerto("AA-AAA");
		
		GPSCoordinate gpsd = new GPSCoordinate(10, 10);
		Muelle md = new Muelle(50, gpsd, true, 10, 10);
		Puerto pd = new Puerto("AA-AAB");
		LocalDate ff = LocalDate.now();
		
		new Trayecto(mo, po, null, md, pd, ff);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCrearTrayectoConMuelleDestinoNull() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle mo = new Muelle(50, gpsi, true, 10, 10);
		Puerto po = new Puerto("AA-AAA");
		LocalDate fi = LocalDate.now();
		
		Puerto pd = new Puerto("AA-AAB");
		LocalDate ff = LocalDate.now();
		
		new Trayecto(mo, po, fi, null, pd, ff);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCrearTrayectoConPuertoDestinoNull() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle mo = new Muelle(50, gpsi, true, 10, 10);
		Puerto po = new Puerto("AA-AAA");
		LocalDate fi = LocalDate.now();
		
		GPSCoordinate gpsd = new GPSCoordinate(10, 10);
		Muelle md = new Muelle(50, gpsd, true, 10, 10);
		LocalDate ff = LocalDate.now();
		
		new Trayecto(mo, po, fi, md, null, ff);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCrearTrayectoConFechaFinNull() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle mo = new Muelle(50, gpsi, true, 10, 10);
		Puerto po = new Puerto("AA-AAA");
		LocalDate fi = LocalDate.now();
		
		GPSCoordinate gpsd = new GPSCoordinate(10, 10);
		Muelle md = new Muelle(50, gpsd, true, 10, 10);
		Puerto pd = new Puerto("AA-AAB");
		
		new Trayecto(mo, po, fi, md, pd, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCrearTrayectoConFechaInicioMasTardeQueFin() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle mo = new Muelle(50, gpsi, true, 10, 10);
		Puerto po = new Puerto("AA-AAA");
		LocalDate fi = LocalDate.now();
		
		GPSCoordinate gpsd = new GPSCoordinate(10, 10);
		Muelle md = new Muelle(50, gpsd, true, 10, 10);
		Puerto pd = new Puerto("AA-AAB");
		LocalDate ff = LocalDate.of(2000, 1, 1);
		
		new Trayecto(mo, po, fi, md, pd, ff);
	}
	
	@Test
	public void testFechaFinSuperiorAOtraFalso() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle mo = new Muelle(50, gpsi, true, 10, 10);
		Puerto po = new Puerto("AA-AAA");
		LocalDate fi = LocalDate.now();
		
		GPSCoordinate gpsd = new GPSCoordinate(10, 10);
		Muelle md = new Muelle(50, gpsd, true, 10, 10);
		Puerto pd = new Puerto("AA-AAA");
		LocalDate ff = LocalDate.now();
		
		Trayecto t = new Trayecto(mo, po, fi, md, pd, ff);
		
		assertFalse(t.fechaFinSuperiorAOtra(LocalDate.now()));
	}
	
	@Test
	public void testFechaFinSuperiorAOtraVerdadero() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle mo = new Muelle(50, gpsi, true, 10, 10);
		Puerto po = new Puerto("AA-AAA");
		LocalDate fi = LocalDate.now();
		
		GPSCoordinate gpsd = new GPSCoordinate(10, 10);
		Muelle md = new Muelle(50, gpsd, true, 10, 10);
		Puerto pd = new Puerto("AA-AAA");
		LocalDate ff = LocalDate.now();
		
		Trayecto t = new Trayecto(mo, po, fi, md, pd, ff);
		
		assertTrue(t.fechaFinSuperiorAOtra(LocalDate.of(2020, 1, 1)));
	}
	
	 @Test
	 public void testGetDuracionDias0() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle mo = new Muelle(50, gpsi, true, 10, 10);
		Puerto po = new Puerto("AA-AAA");
		LocalDate fi = LocalDate.now();
		
		GPSCoordinate gpsd = new GPSCoordinate(10, 10);
		Muelle md = new Muelle(50, gpsd, true, 10, 10);
		Puerto pd = new Puerto("AA-AAA");
		LocalDate ff = LocalDate.now();
		
		Trayecto t = new Trayecto(mo, po, fi, md, pd, ff);
		
		assertEquals(0, t.getDuracionDias());
	 }
	 
	 @Test
	 public void testGetDuracionDias9() {
		GPSCoordinate gpsi = new GPSCoordinate(10, 10);
		Muelle mo = new Muelle(50, gpsi, true, 10, 10);
		Puerto po = new Puerto("AA-AAA");
		LocalDate fi = LocalDate.of(2024, 1, 10);
		
		GPSCoordinate gpsd = new GPSCoordinate(10, 10);
		Muelle md = new Muelle(50, gpsd, true, 10, 10);
		Puerto pd = new Puerto("AA-AAA");
		LocalDate ff = LocalDate.of(2024, 1, 20);
		
		Trayecto t = new Trayecto(mo, po, fi, md, pd, ff);
		
		assertEquals(10, t.getDuracionDias());
	 }
	 
	 @Test
	 public void testGetDistanciaKM() {
		 GPSCoordinate gpsi = new GPSCoordinate(41.623071, -4.749593);
		 Muelle mo = new Muelle(50, gpsi, true, 10, 10);
		 Puerto po = new Puerto("AA-AAA");
		 LocalDate fi = LocalDate.of(2024, 1, 10);

		 GPSCoordinate gpsd = new GPSCoordinate(41.6372231, -4.738908);
		 Muelle md = new Muelle(50, gpsd, true, 10, 10);
		 Puerto pd = new Puerto("AA-AAA");
		 LocalDate ff = LocalDate.of(2024, 1, 20);

		 Trayecto t = new Trayecto(mo, po, fi, md, pd, ff);
		 assertEquals(1.807, t.getDistanciaKM(), GPSCoordinate.PRECISION_IN_DISTANCE);
	 }
		
	 @Test
	 public void testGetDistanciaMillasNauticas() {
		 GPSCoordinate gpsi = new GPSCoordinate(41.623071, -4.749593);
		 Muelle mo = new Muelle(50, gpsi, true, 10, 10);
		 Puerto po = new Puerto("AA-AAA");
		 LocalDate fi = LocalDate.of(2024, 1, 10);

		 GPSCoordinate gpsd = new GPSCoordinate(41.6372231, -4.738908);
		 Muelle md = new Muelle(50, gpsd, true, 10, 10);
		 Puerto pd = new Puerto("AA-AAA");
		 LocalDate ff = LocalDate.of(2024, 1, 20);

		 Trayecto t = new Trayecto(mo, po, fi, md, pd, ff);
		 assertEquals(t.getDistanciaKM() * Trayecto.CONVERSION_MILLAS_NAUTICAS, t.getDistanciaMillasNauticas(), GPSCoordinate.PRECISION_IN_DISTANCE);
	 }
		
	 @Test
	 public void testGetCosteTrayecto() {
		 GPSCoordinate gpsi = new GPSCoordinate(41.623071, -4.749593);
		 Muelle mo = new Muelle(50, gpsi, true, 10, 10);
		 Puerto po = new Puerto("AA-AAA");
		 LocalDate fi = LocalDate.of(2024, 1, 10);

		 GPSCoordinate gpsd = new GPSCoordinate(41.6372231, -4.738908);
		 Muelle md = new Muelle(50, gpsd, true, 10, 10);
		 Puerto pd = new Puerto("AA-AAA");
		 LocalDate ff = LocalDate.of(2024, 1, 20);

		 Trayecto t = new Trayecto(mo, po, fi, md, pd, ff);
		 
		 double costePorDia = 100;
		 double costePorMillaNautica = 50;
		 double costeEsperado = (t.getDuracionDias() * costePorDia) + (t.getDistanciaMillasNauticas() * costePorMillaNautica);
		 assertEquals(costeEsperado, t.getCosteTrayecto(costePorDia, costePorMillaNautica), 0.001);
	 }
		
	 @Test
	 public void testToString() {
		 GPSCoordinate gpsi = new GPSCoordinate(41.623071, -4.749593);
		 Muelle mo = new Muelle(50, gpsi, true, 10, 10);
		 Puerto po = new Puerto("AA-AAA");
		 LocalDate fi = LocalDate.of(2024, 1, 10);

		 GPSCoordinate gpsd = new GPSCoordinate(41.6372231, -4.738908);
		 Muelle md = new Muelle(50, gpsd, true, 10, 10);
		 Puerto pd = new Puerto("AA-AAA");
		 LocalDate ff = LocalDate.of(2024, 1, 20);

		 Trayecto t = new Trayecto(mo, po, fi, md, pd, ff);
			
		 String esperado = (
				 "Localidad y país Puerto Origen: " + po.getCodigoIdentificacion() +
				 "\nFecha de inicio del trayecto: " + fi.toString() +
				 "\nLocalidad y país Puerto Destino: " + pd.getCodigoIdentificacion() +
				 "\nFecha de fin del trayecto: " + ff.toString()
		 );

		 assertEquals(esperado, t.toString());
	 }
}
