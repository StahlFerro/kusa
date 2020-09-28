package io.stahlferro.kusa.mappers;

import io.stahlferro.kusa.models.Keycard;
import io.stahlferro.kusa.models.KeycardDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class KeycardMapper {
    public abstract Keycard createKeycardFromDto(KeycardDto dto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateKeycardFromDto(KeycardDto dto, @MappingTarget Keycard keycard);
}
