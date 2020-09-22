package io.stahlferro.kusa.mappers;

import io.stahlferro.kusa.models.UserBase;
import io.stahlferro.kusa.models.UserBaseDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserBaseDto dto, @MappingTarget UserBase userBase);
}
