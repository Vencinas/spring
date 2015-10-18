package es.vencinas.study.spring.jdbc.crud;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.vencinas.study.spring.jdbc.crud.mock.TablaMock;
import es.vencinas.study.spring.jdbc.crud.modelo.Tabla;
import es.vencinas.study.spring.jdbc.crud.rest.TablaControler;
import es.vencinas.study.spring.jdbc.crud.servicio.Servicio;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class ControladorTest {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Mock
    private Servicio servicio;

    @Autowired
    @InjectMocks
    private TablaControler controlador;

    private MockMvc mockMvc;

    private final static ObjectMapper jsonMapper = new ObjectMapper();

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.controlador).build();
        //this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();  //Bypass mocks
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void list() throws Exception {
        List<Tabla> allTablas = TablaMock.allTablas();
        when(this.servicio.getAll()).thenReturn(allTablas);

        MockHttpServletRequestBuilder get = MockMvcRequestBuilders.get("/tabla/list");

        String rawJson = this.mockMvc.perform(get).andExpect(status().isOk()).andReturn().
                getResponse().getContentAsString();

        List<Tabla> deserializedTablas = jsonMapper.readValue(rawJson,
                new TypeReference<List<Tabla>>(){});

        assertEquals(deserializedTablas, allTablas);

    }

    @Test
    public void list_InvalidHTTPMethod() throws Exception{
        MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/tabla/list");

        this.mockMvc.perform(post).andExpect(status().is(405));
    }
}