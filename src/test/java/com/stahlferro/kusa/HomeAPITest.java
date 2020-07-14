package com.stahlferro.kusa;

import com.stahlferro.kusa.controllers.api.APIHomeController;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(APIHomeController.class)
public class HomeAPITest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void grassCallShouldReturnGrass() throws Exception {
        this.mockMvc.perform(get("/api/home/grass"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("grass")));
    }
}
