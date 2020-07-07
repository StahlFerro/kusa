package com.stahlferro.kusa;

import com.stahlferro.kusa.controllers.api.APIRandomController;
import com.stahlferro.kusa.controllers.services.RandomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(APIRandomController.class)
public class RandomTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RandomService randomService;

    @Test
    public void randNumShouldReturnInteger() throws Exception {
        when(randomService.RandomNumber()).thenReturn(42069);
        this.mockMvc.perform(get("/randnum"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(is("42069")));
    }
}
