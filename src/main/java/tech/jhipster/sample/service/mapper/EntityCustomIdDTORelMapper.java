package tech.jhipster.sample.service.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;
import tech.jhipster.sample.domain.EntityCustomIdDTO;
import tech.jhipster.sample.domain.EntityCustomIdDTOMapsId;
import tech.jhipster.sample.domain.EntityCustomIdDTORel;
import tech.jhipster.sample.service.dto.EntityCustomIdDTODTO;
import tech.jhipster.sample.service.dto.EntityCustomIdDTOMapsIdDTO;
import tech.jhipster.sample.service.dto.EntityCustomIdDTORelDTO;

/**
 * Mapper for the entity {@link EntityCustomIdDTORel} and its DTO {@link EntityCustomIdDTORelDTO}.
 */
@Mapper(componentModel = "spring")
public interface EntityCustomIdDTORelMapper extends EntityMapper<EntityCustomIdDTORelDTO, EntityCustomIdDTORel> {
    @Mapping(target = "oneToOne", source = "oneToOne", qualifiedByName = "entityCustomIdDTOCustomId")
    @Mapping(target = "oneToOneMapsId", source = "oneToOneMapsId", qualifiedByName = "entityCustomIdDTOMapsIdCustomId")
    @Mapping(target = "manyToOne", source = "manyToOne", qualifiedByName = "entityCustomIdDTOCustomId")
    @Mapping(target = "manyToOneMapsId", source = "manyToOneMapsId", qualifiedByName = "entityCustomIdDTOMapsIdCustomId")
    @Mapping(target = "manyToManies", source = "manyToManies", qualifiedByName = "entityCustomIdDTOCustomIdSet")
    @Mapping(target = "manyToManyMapsIds", source = "manyToManyMapsIds", qualifiedByName = "entityCustomIdDTOMapsIdCustomIdSet")
    EntityCustomIdDTORelDTO toDto(EntityCustomIdDTORel s);

    @Mapping(target = "removeManyToMany", ignore = true)
    @Mapping(target = "removeManyToManyMapsId", ignore = true)
    EntityCustomIdDTORel toEntity(EntityCustomIdDTORelDTO entityCustomIdDTORelDTO);

    @Named("entityCustomIdDTOCustomId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "customId", source = "customId")
    EntityCustomIdDTODTO toDtoEntityCustomIdDTOCustomId(EntityCustomIdDTO entityCustomIdDTO);

    @Named("entityCustomIdDTOCustomIdSet")
    default Set<EntityCustomIdDTODTO> toDtoEntityCustomIdDTOCustomIdSet(Set<EntityCustomIdDTO> entityCustomIdDTO) {
        return entityCustomIdDTO.stream().map(this::toDtoEntityCustomIdDTOCustomId).collect(Collectors.toSet());
    }

    @Named("entityCustomIdDTOMapsIdCustomId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "customId", source = "customId")
    EntityCustomIdDTOMapsIdDTO toDtoEntityCustomIdDTOMapsIdCustomId(EntityCustomIdDTOMapsId entityCustomIdDTOMapsId);

    @Named("entityCustomIdDTOMapsIdCustomIdSet")
    default Set<EntityCustomIdDTOMapsIdDTO> toDtoEntityCustomIdDTOMapsIdCustomIdSet(Set<EntityCustomIdDTOMapsId> entityCustomIdDTOMapsId) {
        return entityCustomIdDTOMapsId.stream().map(this::toDtoEntityCustomIdDTOMapsIdCustomId).collect(Collectors.toSet());
    }
}
