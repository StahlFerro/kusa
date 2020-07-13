package com.stahlferro.kusa;

import static org.assertj.core.api.Assertions.assertThat;

import com.stahlferro.kusa.mappers.KeyCardMapper;
import com.stahlferro.kusa.mappers.UserMapper;
import com.stahlferro.kusa.models.KeyCard;
import com.stahlferro.kusa.models.KeyCardDto;
import com.stahlferro.kusa.models.User;
import com.stahlferro.kusa.models.UserDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

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

}
