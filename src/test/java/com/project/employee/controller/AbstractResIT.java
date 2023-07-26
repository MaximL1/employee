package com.project.employee.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create"})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AbstractResIT {

    @Autowired
    protected MockMvc mockMvc;

    public final ObjectMapper objectMapper;

    public AbstractResIT() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    protected <T> T mapToType(MvcResult mvcResult, Class<T> clazz)
            throws JsonProcessingException, UnsupportedEncodingException
    {
        return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), clazz);
    }

    protected <T> List<T> mapToList(MvcResult mvcResult, Class<T> clazz)
            throws JsonProcessingException, UnsupportedEncodingException
    {
        CollectionType javaType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, clazz);
        return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), javaType);
    }
}
