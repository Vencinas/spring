package es.vencinas.study.spring.jdbc.crud.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.vencinas.study.spring.jdbc.crud.mock.TablaMock;
import es.vencinas.study.spring.jdbc.crud.modelo.Tabla;

@ContextConfiguration(locations = "classpath:test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TablaDAOTest {

    @Autowired
    private TablaDAO godDAO;

    @Test
    public void getAll() {
        List<Tabla> gods = this.godDAO.getAll();
        assertNotNull(gods);
        assertTrue(gods.size() > 0);
    }

    @Test
    public void getById() {
        Tabla god = this.godDAO.getById(TablaMock.LOKI.getTabla().getId());
        assertEquals(god, TablaMock.LOKI.getTabla());
    }

    @Test
    public void getByAge() {
        List<Tabla> gods = this.godDAO.getByAge(TablaMock.ODIN.getTabla().getAge());
        assertNotNull(gods);
        assertTrue(gods.size() == 1);
        assertEquals(gods.get(0), TablaMock.ODIN.getTabla());
    }

    @Test
    public void getNames() {
        List<String> gods = this.godDAO.getNames(TablaMock.THOR.getTabla().getId());
        assertNotNull(gods);
        assertTrue(gods.size() == 1);
        assertEquals(gods.get(0), TablaMock.THOR.getTabla().getName());
    }

    @DirtiesContext
    @Test
    public void delete() {
        List<Tabla> allTablas = this.godDAO.getAll();
        this.godDAO.delete(TablaMock.LOKI.getTabla().getId());
        List<Tabla> remainingTablas = this.godDAO.getAll();

        assertTrue(allTablas.size() > remainingTablas.size());
        assertFalse(remainingTablas.contains(TablaMock.LOKI.getTabla()));
    }

    @Test
    public void save() {
        List<Tabla> oldTablasNames = this.godDAO.getAll();
        for(Tabla god : oldTablasNames) {
            assertFalse(god.getName().equals("Zeus"));
        }

        this.godDAO.save(65, "Zeus");
        List<Tabla> newTablas = this.godDAO.getAll();
        boolean found = false;

        for(Tabla god : newTablas) {
            if(god.getName().equals("Zeus")) {
                found = true;
                break;
            }
        }

        assertTrue(found);
    }
}