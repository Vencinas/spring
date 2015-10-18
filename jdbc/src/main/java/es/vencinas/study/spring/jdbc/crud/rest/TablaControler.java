package es.vencinas.study.spring.jdbc.crud.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.vencinas.study.spring.jdbc.crud.modelo.Tabla;
import es.vencinas.study.spring.jdbc.crud.servicio.Servicio;

@Controller
@RequestMapping("/tabla")
public class TablaControler {

    @Autowired
    private Servicio tablaService;

    private static final ObjectMapper jsonMapper = new ObjectMapper();

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String list() throws Exception {
        List<Tabla> allTablas = this.tablaService.getAll();
        String json = jsonMapper.writeValueAsString(allTablas);
        return json;
    }

    /**
     * Sample of using the GET request using the parameter as part of the url
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String get(@PathVariable int id) throws Exception {

        Tabla tabla = this.tablaService.getById(id);
        String json = jsonMapper.writeValueAsString(tabla);

        return json;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String getByName(String name) throws Exception {
        List<Tabla> tablas = this.tablaService.search(name);
        String json = jsonMapper.writeValueAsString(tablas);
        return json;
    }

    @RequestMapping(value = "/save/", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String save(int age, String name) throws Exception {
        this.tablaService.save(name, age);
        String json = jsonMapper.writeValueAsString("Fine");
        return json;
    }
}