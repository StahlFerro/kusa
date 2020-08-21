package io.stahlferro.kusa;

import io.stahlferro.kusa.mappers.KeyCardMapper;
import io.stahlferro.kusa.mappers.UserMapper;
import io.stahlferro.kusa.models.KeyCard;
import io.stahlferro.kusa.models.KeyCardDto;
import io.stahlferro.kusa.models.User;
import io.stahlferro.kusa.models.UserDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
