package edu.icet.crm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.crm.model.Institute;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class InstituteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void test_registerInstitutes_whenValidParameters() throws Exception {
        Institute validInstitute = new Institute("I001","Sakya Institute","sakyainstitute@gmial.com","0719401800","Kollupitiya","Password");
        mockMvc.perform(post("/institute/register-institutes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validInstitute)))
                .andExpect(status().isOk());
    }
    @Test
    void test_registerInstitutes_whenIdIsNull() throws Exception {
        Institute instituteWithNullId=new Institute(null ,"Sakya Institute","sakyainstitute@gmial.com","0719401800","Kollupitiya","Password");

        mockMvc.perform(post("/institute/register-institutes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(instituteWithNullId)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.instituteId").value("Institute id is missing !!"));
    }
}
