package io.stahlferro.kusa;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class KeyCardAPITest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postKeyCardShouldCreateKeyCardThenDeleteIt() throws Exception {
        String newKeyCard = new JSONObject()
                .put("name", "Dimensional Research Lab")
                .put("accessLevel", 240).toString();

        MvcResult result = this.mockMvc.perform(post("/api/keycard/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(newKeyCard)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Dimensional Research Lab"))
                .andExpect(jsonPath("$.accessLevel").value(240))
                .andExpect(jsonPath("$.uuid").exists())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        JSONObject savedKeyCardJSON = new JSONObject(content);
        long savedKeyCardId = savedKeyCardJSON.getLong("id");

        this.mockMvc.perform(delete("/api/keycard/delete/" + savedKeyCardId))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/api/keycard/" + savedKeyCardId))
                .andExpect(status().isNotFound());

    }

    @Test
    public void patchKeyCardShouldUpdateKeyCardThenDeleteIt() throws Exception {
        String newKeyCard = new JSONObject()
                .put("name", "Antimatter Engine Room")
                .put("accessLevel", 251).toString();

        MvcResult result = this.mockMvc.perform(post("/api/keycard/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(newKeyCard)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Antimatter Engine Room"))
                .andExpect(jsonPath("$.accessLevel").value(251))
                .andExpect(jsonPath("$.uuid").exists())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        JSONObject savedKeyCardJSON = new JSONObject(content);
        long savedKeyCardId = savedKeyCardJSON.getLong("id");

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
                .andExpect(jsonPath("$.accessLevel").value(220))
                .andExpect(jsonPath("$.uuid").value(savedKeyCardJSON.get("uuid")));

        this.mockMvc.perform(delete("/api/keycard/delete/" + savedKeyCardId))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/api/keycard/" + savedKeyCardId))
                .andExpect(status().isNotFound());

    }
}
