package io.stahlferro.kusa.mappers;

import io.stahlferro.kusa.models.UserBase;
import io.stahlferro.kusa.models.UserBaseDto;
import io.stahlferro.kusa.utils.EncryptionUtils;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserBaseMapper {
    @Mapping(source = "password", target = "passwordHash")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserBaseDto dto, @MappingTarget UserBase userBase);
    @Named("hashPassword")
    default public String hashPassword(String password) {
        return EncryptionUtils.encryptPassword(password);
    }
}
