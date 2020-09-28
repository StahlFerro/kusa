package io.stahlferro.kusa;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class KeycardAPITest {
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
    public void postKeyCardShouldCreateKeyCardThenDeleteIt() throws Exception {
        String newKeycard = new JSONObject()
                .put("name", "Dimensional Research Lab")
                .put("accessLevel", 240).toString();

        MvcResult result = this.mockMvc.perform(post("/api/keycard/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(newKeycard)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Dimensional Research Lab"))
                .andExpect(jsonPath("$.accessLevel").value(240))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        JSONObject savedKeycardJSON = new JSONObject(content);
        String savedKeyCardId = savedKeycardJSON.getString("id");

        this.mockMvc.perform(delete("/api/keycard/delete/" + savedKeyCardId))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/api/keycard/" + savedKeyCardId))
                .andExpect(status().isNotFound());

    }

    @Test
    @WithMockUser
    public void patchKeyCardShouldUpdateKeyCardThenDeleteIt() throws Exception {
        String newKeycard = new JSONObject()
                .put("name", "Antimatter Engine Room")
                .put("accessLevel", 251).toString();

        MvcResult result = this.mockMvc.perform(post("/api/keycard/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(newKeycard)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Antimatter Engine Room"))
                .andExpect(jsonPath("$.accessLevel").value(251))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        JSONObject savedKeycardJSON = new JSONObject(content);
        String savedKeyCardId = savedKeycardJSON.getString("id");

        String newData = new JSONObject()
                .put("name", "Antimatter Core Storage")
                .put("accessLevel", 220).toString();

        this.mockMvc.perform(patch("/api/keycard/update/" + savedKeyCardId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(newData)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/api/keycard/" + savedKeyCardId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.name").value("Antimatter Core Storage"))
                .andExpect(jsonPath("$.accessLevel").value(220));

        this.mockMvc.perform(delete("/api/keycard/delete/" + savedKeyCardId))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/api/keycard/" + savedKeyCardId))
                .andExpect(status().isNotFound());

    }
}
