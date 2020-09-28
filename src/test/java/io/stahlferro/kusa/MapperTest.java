package io.stahlferro.kusa;

import io.stahlferro.kusa.mappers.KeycardMapper;
import io.stahlferro.kusa.mappers.UserBaseMapper;
import io.stahlferro.kusa.models.Keycard;
import io.stahlferro.kusa.models.KeycardDto;
import io.stahlferro.kusa.models.UserBase;
import io.stahlferro.kusa.models.UserBaseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@SpringBootTest
public class MapperTest {
    @Autowired
    private UserBaseMapper userMapper;
    @Autowired
    private KeycardMapper keycardMapper;
//    private final UserBaseMapper userMapper = Mappers.getMapper(UserBaseMapper.class);
//    private final KeycardMapper keycardMapper = Mappers.getMapper(KeycardMapper.class);

//    @Test
//    public void userMapperDependenciesShouldNotBeNull() throws Exception {
//        assertThat(userMapper.bcryptEncoder).isNotNull();
//    }

    @Test
    public void userDtoPasswordShouldEncrypt() throws Exception {
        UserBaseDto userDto = new UserBaseDto();
        userDto.setLoginName("Wysen");
        userDto.setPassword("abcdefgh12345");
        UserBase user = userMapper.createUserFromDto(userDto);
        log.info("USER STRING\n" + user.toString());
        assertThat(user.getPasswordHash()).isNotEqualTo(userDto.getPassword());
    }

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
    public void keycardDtoShouldUpdateKeyCardEntity() throws Exception {
        Keycard keycard = new Keycard();
        keycard.setName("Biolabs Green Access");
        keycard.setAccessLevel(50);
        KeycardDto keycardDto = new KeycardDto();
        keycardDto.setName("Biolabs Red Access");
        keycardDto.setAccessLevel(81);
        keycardMapper.updateKeycardFromDto(keycardDto, keycard);
        assertThat(keycard.getName()).isEqualTo(keycardDto.getName());
        assertThat(keycard.getAccessLevel()).isEqualTo(keycardDto.getAccessLevel());
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
