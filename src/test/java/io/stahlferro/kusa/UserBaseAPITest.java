package io.stahlferro.kusa;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserBaseAPITest {
    @Autowired
    private WebApplicationContext context;

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
    public void postUserAPIShouldCreateUserThenDeleteIt() throws Exception {
        String newUser = new JSONObject()
                .put("name", "Hellagur")
                .put("email", "hellagur@azazel.com")
                .put("loginName", "hellagur")
                .put("password", "screwUrsus")
                .toString();

        MvcResult result = mockMvc.perform(post("/api/user/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newUser)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Hellagur"))
                .andExpect(jsonPath("$.email").value("hellagur@azazel.com"))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        JSONObject savedUserJSON = new JSONObject(content);
        String savedUserId = savedUserJSON.getString("id");

        this.mockMvc.perform(delete("/api/user/delete/" + savedUserId))
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/api/user/" + savedUserId))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    public void patchUserAPIShouldUpdateUserThenDeleteIt() throws Exception {
        String newUser = new JSONObject()
                .put("name", "Schwarz")
                .put("email", "schwarz@siesta.com")
                .put("loginName", "schwarz")
                .put("password", "ceylon")
                .toString();

        MvcResult result = this.mockMvc.perform(post("/api/user/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(newUser)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Schwarz"))
                .andExpect(jsonPath("$.email").value("schwarz@siesta.com"))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        JSONObject savedUserJSON = new JSONObject(content);
        String savedUserId = savedUserJSON.getString("id");

        String newEmail = new JSONObject().put("email", "schwarz@rhodesisland.com").toString();

        this.mockMvc.perform(patch("/api/user/update/" + savedUserId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(newEmail)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/api/user/" + savedUserId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.name").value(savedUserJSON.get("name")))
                .andExpect(jsonPath("$.email").value("schwarz@rhodesisland.com"));

        this.mockMvc.perform(delete("/api/user/delete/" + savedUserId))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/api/user/" + savedUserId))
                .andExpect(status().isNotFound());
    }
}
