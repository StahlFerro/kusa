package io.stahlferro.kusa.mappers;

import io.stahlferro.kusa.models.UserBase;
import io.stahlferro.kusa.models.UserBaseDto;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public abstract class UserBaseMapper {
    @Autowired
    public PasswordEncoder passwordEncoder;

    @Mapping(source = "password", target = "passwordHash", qualifiedByName = "hashPassword")
    public abstract UserBase createUserFromDto(UserBaseDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateUserFromDto(UserBaseDto dto, @MappingTarget UserBase userBase);

    @Named("hashPassword")
    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
//        return EncryptionUtils.encryptPassword(password);
    }
}
