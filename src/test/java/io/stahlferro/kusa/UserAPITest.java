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
public class UserAPITest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postUserAPIShouldCreateUserThenDeleteIt() throws Exception {
        String newUser = new JSONObject()
                .put("name", "Hellagur")
                .put("email", "hellagur@azazel.com")
                .toString();

        MvcResult result = this.mockMvc.perform(post("/api/user/add")
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
        long savedUserId = savedUserJSON.getLong("id");

        this.mockMvc.perform(delete("/api/user/delete/" + savedUserId))
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/api/user/" + savedUserId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void patchUserAPIShouldUpdateUserThenDeleteIt() throws Exception {
        String newUser = new JSONObject()
                .put("name", "Schwarz")
                .put("email", "schwarz@siesta.com")
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
        long savedUserId = savedUserJSON.getLong("id");

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
