package com.stahlferro.kusa;

import com.stahlferro.kusa.controllers.api.APIHomeController;
import com.stahlferro.kusa.mappers.KeyCardMapper;
import com.stahlferro.kusa.mappers.UserMapper;
import com.stahlferro.kusa.models.KeyCard;
import com.stahlferro.kusa.models.KeyCardDto;
import com.stahlferro.kusa.models.User;
import com.stahlferro.kusa.models.UserDto;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.assertj.core.api.Assertions.assertThat;


@AutoConfigureMockMvc
public class MapperTest {
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final KeyCardMapper keyCardMapper = Mappers.getMapper(KeyCardMapper.class);
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void userDtoShouldUpdateUserEntity() throws Exception {
        User user = new User();
        user.setName("Malcolm");
        user.setEmail("malc@email.com");
        UserDto userDto = new UserDto();
        userDto.setName("Wright");
        userDto.setEmail("wright@email.com");
        userMapper.updateUserFromDto(userDto, user);
        assertThat(user.getName()).isEqualTo(userDto.getName());
        assertThat(user.getEmail()).isEqualTo(userDto.getEmail());
    }

    @Test
    public void keyCardDtoShouldUpdateKeyCardEntity() throws Exception {
        KeyCard keyCard = new KeyCard();
        keyCard.setName("Biolabs Green Access");
        keyCard.setAccessLevel(50);
        KeyCardDto keyCardDto = new KeyCardDto();
        keyCardDto.setName("Biolabs Red Access");
        keyCardDto.setAccessLevel(81);
        keyCardMapper.updateKeyCardFromDto(keyCardDto, keyCard);
        assertThat(keyCard.getName()).isEqualTo(keyCardDto.getName());
        assertThat(keyCard.getAccessLevel()).isEqualTo(keyCardDto.getAccessLevel());
    }

//    @Test
//    public void patchUserAPIShouldUpdateUser() throws Exception {
//        String jsonString = new JSONObject()
//                .put("name", "Hellagur")
//                .put("email", "hellagur@azazel.com")
//                .toString();
//        mockMvc.perform(post("/api/user/add")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonString)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(jsonPath("$.name").value("Hellagur"))
//                .andExpect(jsonPath("$.email").value("hellagur@azazel.com"));
//    }
}
