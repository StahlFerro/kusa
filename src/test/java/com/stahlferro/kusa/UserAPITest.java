package com.stahlferro.kusa;

import com.stahlferro.kusa.controllers.api.APIUserController;
import com.stahlferro.kusa.repositories.UserRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
public class UserAPITest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void patchUserAPIShouldUpdateUser() throws Exception {
        String jsonString = new JSONObject()
                .put("name", "Hellagur")
                .put("email", "hellagur@azazel.com")
                .toString();
        this.mockMvc.perform(post("/api/user/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.name").value("Hellagur"))
                .andExpect(jsonPath("$.email").value("hellagur@azazel.com"));
    }
}
