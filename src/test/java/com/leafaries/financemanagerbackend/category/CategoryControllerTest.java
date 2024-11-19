package com.leafaries.financemanagerbackend.category;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoryControllerTest {

//    private static final String USERNAME = System.getenv("TEST_USERNAME");
//    private static final String PASSWORD = System.getenv("TEST_PASSWORD");
    private static final String USERNAME = "test1";
    private static final String PASSWORD = "password_text";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String jwtToken;

    @BeforeAll
    public void setup() throws Exception {
        registerTestUser();
        jwtToken = obtainAccessToken(USERNAME, PASSWORD);
    }

    private void registerTestUser() throws Exception {
        var registrationRequest = Map.of("username", USERNAME, "password", PASSWORD);

        System.out.println(objectMapper.writeValueAsString(registrationRequest));

        mockMvc.perform(post("/api/auth/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(status().isCreated());
    }

    private String obtainAccessToken(String username, String password) throws Exception {
        var loginRequest = Map.of("username", username, "password", password);

        var result = mockMvc.perform(post("/api/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn();

        var response = result.getResponse().getContentAsString();
        var tokenData = objectMapper.readValue(response, Map.class);

        return tokenData.get("token").toString();
    }

    @Test
    public void testCreateCategory() throws Exception {
        CreateCategoryDto createCategoryDto = new CreateCategoryDto();
        createCategoryDto.setName("Electronics");

        mockMvc.perform(post("/api/categories")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createCategoryDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Electronics"));
    }

    @Test
    public void testGetAllCategories() throws Exception {
        mockMvc.perform(get("/api/categories")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetCategoryById() throws Exception {
        // Assuming a category with ID 1 exists
        mockMvc.perform(get("/api/categories/{id}", 1L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testUpdateCategory() throws Exception {
        // Assuming a category with ID 1 exists
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);
        categoryDto.setName("Updated Electronics");

        mockMvc.perform(put("/api/categories/{id}", 1L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Updated Electronics"));
    }

    @Test
    public void testDeleteCategory() throws Exception {
        // Assuming a category with ID 1 exists
        mockMvc.perform(delete("/api/categories/{id}", 1L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
