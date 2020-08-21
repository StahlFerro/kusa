package io.stahlferro.kusa.mappers;

import io.stahlferro.kusa.models.KeyCard;
import io.stahlferro.kusa.models.KeyCardDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface KeyCardMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateKeyCardFromDto(KeyCardDto dto, @MappingTarget KeyCard keyCard);
}
