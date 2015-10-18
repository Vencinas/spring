package es.vencinas.study.spring.jdbc.crud.mock;

import java.util.Arrays;
import java.util.List;

import es.vencinas.study.spring.jdbc.crud.modelo.Tabla;

public enum TablaMock {

	ODIN(new Tabla(1, "Nombre1", 50)),
    THOR(new Tabla(2, "Nombre2", 25)),
    LOKI(new Tabla(3, "Nombre3", 28));

    private Tabla tabla;

    private TablaMock(Tabla tabla) {
        this.tabla = tabla;
    }

    public Tabla getTabla() {
        return this.tabla;
    }

    public static List<Tabla> allTablas() {
        return Arrays.asList(new Tabla[]{ODIN.getTabla(), THOR.getTabla(), LOKI.getTabla()});
    }

    
}
