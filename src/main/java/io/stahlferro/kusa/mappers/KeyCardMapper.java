package io.stahlferro.kusa.mappers;

import io.stahlferro.kusa.models.KeyCard;
import io.stahlferro.kusa.models.KeyCardDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class KeyCardMapper {
    public abstract KeyCard createKeyCardFromDto(KeyCardDto dto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateKeyCardFromDto(KeyCardDto dto, @MappingTarget KeyCard keyCard);
}
