package io.stahlferro.kusa;

import io.stahlferro.kusa.controllers.api.APIRandomController;
import io.stahlferro.kusa.services.RandomService;
import io.stahlferro.kusa.utils.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RandomAPITest {
    @Autowired
    private WebApplicationContext context;

    @MockBean
    private RandomService randomService;

    private MockMvc mockMvc;

    @BeforeAll
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


    @Test
    @WithMockUser
    public void generateRandomNumberShouldReturnInteger() throws Exception {
        when(randomService.generateRandomNumber()).thenReturn(42069);
        this.mockMvc.perform(get("/api/random/number"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(is("42069")));
    }

    @Test
    @WithMockUser
    public void generateRandomUUIDShouldMatchUUIDPattern() throws Exception {
        when(randomService.generateRandomUUID()).thenReturn(UUID.fromString("e15892c2-1771-4657-9186-53b796028d12"));
        this.mockMvc.perform(get("/api/random/uuid"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(matchesPattern(RegexUtils.uuidRgxQuoted)));
//        this.mockMvc.perform(get("/api/random/uuid"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(matchesPattern(RegexUtils.uuidRgx)));
    }
}
