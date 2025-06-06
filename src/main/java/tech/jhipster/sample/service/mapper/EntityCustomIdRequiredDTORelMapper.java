package tech.jhipster.sample.service.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;
import tech.jhipster.sample.domain.EntityCustomIdRequiredDTO;
import tech.jhipster.sample.domain.EntityCustomIdRequiredDTOMapsId;
import tech.jhipster.sample.domain.EntityCustomIdRequiredDTORel;
import tech.jhipster.sample.service.dto.EntityCustomIdRequiredDTODTO;
import tech.jhipster.sample.service.dto.EntityCustomIdRequiredDTOMapsIdDTO;
import tech.jhipster.sample.service.dto.EntityCustomIdRequiredDTORelDTO;

/**
 * Mapper for the entity {@link EntityCustomIdRequiredDTORel} and its DTO {@link EntityCustomIdRequiredDTORelDTO}.
 */
@Mapper(componentModel = "spring")
public interface EntityCustomIdRequiredDTORelMapper extends EntityMapper<EntityCustomIdRequiredDTORelDTO, EntityCustomIdRequiredDTORel> {
    @Mapping(target = "oneToOne", source = "oneToOne", qualifiedByName = "entityCustomIdRequiredDTOCustomId")
    @Mapping(target = "oneToOneMapsId", source = "oneToOneMapsId", qualifiedByName = "entityCustomIdRequiredDTOMapsIdCustomId")
    @Mapping(target = "manyToOne", source = "manyToOne", qualifiedByName = "entityCustomIdRequiredDTOCustomId")
    @Mapping(target = "manyToOneMapsId", source = "manyToOneMapsId", qualifiedByName = "entityCustomIdRequiredDTOMapsIdCustomId")
    @Mapping(target = "manyToManies", source = "manyToManies", qualifiedByName = "entityCustomIdRequiredDTOCustomIdSet")
    @Mapping(target = "manyToManyMapsIds", source = "manyToManyMapsIds", qualifiedByName = "entityCustomIdRequiredDTOMapsIdCustomIdSet")
    EntityCustomIdRequiredDTORelDTO toDto(EntityCustomIdRequiredDTORel s);

    @Mapping(target = "removeManyToMany", ignore = true)
    @Mapping(target = "removeManyToManyMapsId", ignore = true)
    EntityCustomIdRequiredDTORel toEntity(EntityCustomIdRequiredDTORelDTO entityCustomIdRequiredDTORelDTO);

    @Named("entityCustomIdRequiredDTOCustomId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "customId", source = "customId")
    EntityCustomIdRequiredDTODTO toDtoEntityCustomIdRequiredDTOCustomId(EntityCustomIdRequiredDTO entityCustomIdRequiredDTO);

    @Named("entityCustomIdRequiredDTOCustomIdSet")
    default Set<EntityCustomIdRequiredDTODTO> toDtoEntityCustomIdRequiredDTOCustomIdSet(
        Set<EntityCustomIdRequiredDTO> entityCustomIdRequiredDTO
    ) {
        return entityCustomIdRequiredDTO.stream().map(this::toDtoEntityCustomIdRequiredDTOCustomId).collect(Collectors.toSet());
    }

    @Named("entityCustomIdRequiredDTOMapsIdCustomId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "customId", source = "customId")
    EntityCustomIdRequiredDTOMapsIdDTO toDtoEntityCustomIdRequiredDTOMapsIdCustomId(
        EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsId
    );

    @Named("entityCustomIdRequiredDTOMapsIdCustomIdSet")
    default Set<EntityCustomIdRequiredDTOMapsIdDTO> toDtoEntityCustomIdRequiredDTOMapsIdCustomIdSet(
        Set<EntityCustomIdRequiredDTOMapsId> entityCustomIdRequiredDTOMapsId
    ) {
        return entityCustomIdRequiredDTOMapsId.stream().map(this::toDtoEntityCustomIdRequiredDTOMapsIdCustomId).collect(Collectors.toSet());
    }
}
