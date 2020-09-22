package io.stahlferro.kusa;

import io.stahlferro.kusa.mappers.KeyCardMapper;
import io.stahlferro.kusa.mappers.UserBaseMapper;
import io.stahlferro.kusa.models.KeyCard;
import io.stahlferro.kusa.models.KeyCardDto;
import io.stahlferro.kusa.models.UserBase;
import io.stahlferro.kusa.models.UserBaseDto;
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
    private final UserBaseMapper userMapper = Mappers.getMapper(UserBaseMapper.class);
    private final KeyCardMapper keyCardMapper = Mappers.getMapper(KeyCardMapper.class);
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void userDtoShouldUpdateUserEntity() throws Exception {
        UserBase userBase = new UserBase();
        userBase.setName("Malcolm");
        userBase.setEmail("malc@email.com");
        UserBaseDto userBaseDto = new UserBaseDto();
        userBaseDto.setName("Wright");
        userBaseDto.setEmail("wright@email.com");
        userMapper.updateUserFromDto(userBaseDto, userBase);
        assertThat(userBase.getName()).isEqualTo(userBaseDto.getName());
        assertThat(userBase.getEmail()).isEqualTo(userBaseDto.getEmail());
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
