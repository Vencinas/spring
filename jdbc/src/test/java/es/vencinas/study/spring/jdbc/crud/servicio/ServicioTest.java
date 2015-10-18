package es.vencinas.study.spring.jdbc.crud.servicio;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.vencinas.study.spring.jdbc.crud.dao.TablaDAO;
import es.vencinas.study.spring.jdbc.crud.mock.TablaMock;
import es.vencinas.study.spring.jdbc.crud.modelo.Tabla;

@ContextConfiguration(loader = SpringockitoContextLoader.class, locations = "classpath:test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ServicioTest {

	@Autowired
	@InjectMocks
	private Servicio servicio;

	@ReplaceWithMock
	@Autowired
	private TablaDAO tablaDAO;

	@Test
	public void getAll() {
		List<Tabla> allTablas = TablaMock.allTablas();
		when(this.tablaDAO.getAll()).thenReturn(allTablas);
		List<Tabla> myTablas = this.servicio.getAll();

		assertEquals(myTablas, allTablas);
	}

	@Test
	public void getById() {
		Tabla thor = TablaMock.THOR.getTabla();
		when(this.tablaDAO.getById(thor.getId())).thenReturn(thor);

		Tabla found = this.tablaDAO.getById(thor.getId());

		assertEquals(thor, found);
	}

	@Test
	public void searchByAge() {
		Tabla odin = TablaMock.ODIN.getTabla();
		when(this.tablaDAO.getByAge(odin.getAge())).thenReturn(Arrays.asList(new Tabla[] { odin }));
		List<Tabla> found = this.tablaDAO.getByAge(odin.getAge());

		assertEquals(found.get(0), odin);

	}

	@Test(expected = IllegalArgumentException.class)
	public void searchByAge_InvalidAge() {
		Tabla loki = TablaMock.LOKI.getTabla();
		when(this.tablaDAO.getByAge(loki.getAge())).thenThrow(new IllegalArgumentException("Norrrrl"));
		List<Tabla> found = this.tablaDAO.getByAge(loki.getAge());

		assertEquals(found.get(0), loki);

	}

}