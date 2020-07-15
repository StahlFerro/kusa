package com.stahlferro.kusa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeSpringBootTest {
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