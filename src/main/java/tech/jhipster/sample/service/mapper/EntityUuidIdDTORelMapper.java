package tech.jhipster.sample.service.mapper;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.mapstruct.*;
import tech.jhipster.sample.domain.EntityUuidIdDTO;
import tech.jhipster.sample.domain.EntityUuidIdDTOMapsId;
import tech.jhipster.sample.domain.EntityUuidIdDTORel;
import tech.jhipster.sample.service.dto.EntityUuidIdDTODTO;
import tech.jhipster.sample.service.dto.EntityUuidIdDTOMapsIdDTO;
import tech.jhipster.sample.service.dto.EntityUuidIdDTORelDTO;

/**
 * Mapper for the entity {@link EntityUuidIdDTORel} and its DTO {@link EntityUuidIdDTORelDTO}.
 */
@Mapper(componentModel = "spring")
public interface EntityUuidIdDTORelMapper extends EntityMapper<EntityUuidIdDTORelDTO, EntityUuidIdDTORel> {
    @Mapping(target = "oneToOne", source = "oneToOne", qualifiedByName = "entityUuidIdDTOId")
    @Mapping(target = "oneToOneMapsId", source = "oneToOneMapsId", qualifiedByName = "entityUuidIdDTOMapsIdId")
    @Mapping(target = "manyToOne", source = "manyToOne", qualifiedByName = "entityUuidIdDTOId")
    @Mapping(target = "manyToOneMapsId", source = "manyToOneMapsId", qualifiedByName = "entityUuidIdDTOMapsIdId")
    @Mapping(target = "manyToManies", source = "manyToManies", qualifiedByName = "entityUuidIdDTOIdSet")
    @Mapping(target = "manyToManyMapsIds", source = "manyToManyMapsIds", qualifiedByName = "entityUuidIdDTOMapsIdIdSet")
    EntityUuidIdDTORelDTO toDto(EntityUuidIdDTORel s);

    @Mapping(target = "removeManyToMany", ignore = true)
    @Mapping(target = "removeManyToManyMapsId", ignore = true)
    EntityUuidIdDTORel toEntity(EntityUuidIdDTORelDTO entityUuidIdDTORelDTO);

    @Named("entityUuidIdDTOId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EntityUuidIdDTODTO toDtoEntityUuidIdDTOId(EntityUuidIdDTO entityUuidIdDTO);

    @Named("entityUuidIdDTOIdSet")
    default Set<EntityUuidIdDTODTO> toDtoEntityUuidIdDTOIdSet(Set<EntityUuidIdDTO> entityUuidIdDTO) {
        return entityUuidIdDTO.stream().map(this::toDtoEntityUuidIdDTOId).collect(Collectors.toSet());
    }

    @Named("entityUuidIdDTOMapsIdId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EntityUuidIdDTOMapsIdDTO toDtoEntityUuidIdDTOMapsIdId(EntityUuidIdDTOMapsId entityUuidIdDTOMapsId);

    @Named("entityUuidIdDTOMapsIdIdSet")
    default Set<EntityUuidIdDTOMapsIdDTO> toDtoEntityUuidIdDTOMapsIdIdSet(Set<EntityUuidIdDTOMapsId> entityUuidIdDTOMapsId) {
        return entityUuidIdDTOMapsId.stream().map(this::toDtoEntityUuidIdDTOMapsIdId).collect(Collectors.toSet());
    }

    default String map(UUID value) {
        return Objects.toString(value, null);
    }
}
