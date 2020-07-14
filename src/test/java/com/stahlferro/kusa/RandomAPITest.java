package com.stahlferro.kusa;

import com.stahlferro.kusa.controllers.api.APIRandomController;
import com.stahlferro.kusa.services.RandomService;
import com.stahlferro.kusa.utils.RegexUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(APIRandomController.class)
public class RandomAPITest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RandomService randomService;


    @Test
    public void generateRandomNumberShouldReturnInteger() throws Exception {
        when(randomService.generateRandomNumber()).thenReturn(42069);
        this.mockMvc.perform(get("/api/random/number"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(is("42069")));
    }

    @Test
    public void generateRandomUUIDShouldMatchUUIDPattern() throws Exception {
        when(randomService.generateRandomUUID()).thenReturn("e15892c2-1771-4657-9186-53b796028d12");
        this.mockMvc.perform(get("/api/random/uuid"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(matchesPattern(RegexUtils.uuidRgx)));
    }
}
