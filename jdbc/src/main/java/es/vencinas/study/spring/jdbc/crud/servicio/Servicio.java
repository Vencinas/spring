package es.vencinas.study.spring.jdbc.crud.servicio;

import java.util.List;

import es.vencinas.study.spring.jdbc.crud.dao.TablaDAO;
import es.vencinas.study.spring.jdbc.crud.modelo.Tabla;

public class Servicio {

    private TablaDAO tablaDAO;

    public void setTablaDAO(TablaDAO tablaDAO) {
        this.tablaDAO = tablaDAO;
    }

    public List<Tabla> getAll() {
        return this.tablaDAO.getAll();
    }

    public Tabla getById(int id) {
        return this.tablaDAO.getById(id);
    }

    public void save(String name, int age) {
        this.tablaDAO.save(age, name);
    }

    public List<Tabla> search(int age) throws IllegalArgumentException {
        if(age < 0) {
            throw new IllegalArgumentException("Age cannot be negative, provided age was " + age);
        }
        return this.tablaDAO.getByAge(age);
    }

    public List<Tabla> search(String name) {
        return this.tablaDAO.getByName(name);
    }
}
