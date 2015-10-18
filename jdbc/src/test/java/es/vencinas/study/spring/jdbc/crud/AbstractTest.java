package es.vencinas.study.spring.jdbc.crud;

import java.util.Arrays;
import java.util.List;

import es.vencinas.study.spring.jdbc.crud.mock.TablaMock;
import es.vencinas.study.spring.jdbc.crud.modelo.Tabla;

public class AbstractTest {

    private List<Tabla> tablaList;

    public AbstractTest() {
        this.tablaList = Arrays.asList(new Tabla[]{TablaMock.LOKI.getTabla(), TablaMock.ODIN.getTabla(), TablaMock.THOR.getTabla()});
    }
    
}
