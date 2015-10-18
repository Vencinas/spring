package es.vencinas.study.spring.jdbc.crud.dao;

import org.springframework.jdbc.core.RowMapper;

import es.vencinas.study.spring.jdbc.crud.modelo.Tabla;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TablaDAO extends AbstractJdbcDAO{

    private static final String SQL_GET_ALL = "SELECT * FROM tablas";
    private static final String SQL_GET_BY_ID = "SELECT * FROM tablas WHERE id = ?";
    private static final String SQL_GET_BY_AGE = "SELECT * FROM tablas WHERE age = ?";
    private static final String SQL_GET_NAMES = "SELECT name FROM tablas WHERE id = ?";
    private static final String SQL_SAVE = "INSERT INTO tablas (age, name) VALUES (?, ?)";
    private static final String SQL_GET_BY_NAME = "SELECT * FROM tablas WHERE name = ?";
    private static final String SQL_DELETE = "DELETE FROM tablas WHERE id = ?";

	/**
	 * Devuelve todos los registros de la tabla
	 * @return Lista de registros
	 */
	public List<Tabla> getAll() {
        return this.jdbcTemplate.query(SQL_GET_ALL, new TablaMapper());
    }

    /**
     * Devuelve SIEMPRE un registro o falla
     * @param id
     * @return Un registro
     */
    public Tabla getById(int id) {
        return this.jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[]{id}, new TablaMapper());
    }

    /**
     * Obtiene todos los registros que tengan age anyos
     * @param age
     * @return Lista de registros
     */
    public List<Tabla> getByAge(int age) {
        return this.jdbcTemplate.query(SQL_GET_BY_AGE, new Object[]{age}, new TablaMapper());
    }

    /**
     * Obtiene todos los registros que tenga el nombre igual a name. 
     * @param name
     * @return Lista de registros
     */
    public List<Tabla> getByName(String name) {
        return this.jdbcTemplate.query(SQL_GET_BY_NAME, new Object[]{name}, new TablaMapper());
    }

    /**
     * Obtiene el nombre de todos los registros de la tabla que tengan age anyos
     * @param age
     * @return Lista de nombres
     */
    public List<String> getNames(int age) {
        return this.jdbcTemplate.queryForList(SQL_GET_NAMES, new Object[]{age}, String.class);
    }

    /**
     * Crea un nuevo registro en la BD
     * @param age
     * @param name
     * @return
     */
    public void save(int age, String name) {
        this.jdbcTemplate.update(SQL_SAVE, age, name);
    }

    public int delete(int id) {
        return this.jdbcTemplate.update(SQL_DELETE, new Object[]{id});
    }

    /**
     * Clase para mapear los resultados en un objetipo de tipo Tabla
     */
    private static final class TablaMapper implements RowMapper<Tabla> {
        @Override
        public Tabla mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Tabla(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age"));
        }
    }
}
