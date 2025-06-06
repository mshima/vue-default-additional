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
 * Mapper for the entity {@link EntityUuidIdDTOMapsId} and its DTO {@link EntityUuidIdDTOMapsIdDTO}.
 */
@Mapper(componentModel = "spring")
public interface EntityUuidIdDTOMapsIdMapper extends EntityMapper<EntityUuidIdDTOMapsIdDTO, EntityUuidIdDTOMapsId> {
    @Mapping(target = "entityUuidIdDTO", source = "entityUuidIdDTO", qualifiedByName = "entityUuidIdDTOId")
    @Mapping(target = "manyToManyMapsIdBacks", source = "manyToManyMapsIdBacks", qualifiedByName = "entityUuidIdDTORelIdSet")
    EntityUuidIdDTOMapsIdDTO toDto(EntityUuidIdDTOMapsId s);

    @Mapping(target = "manyToManyMapsIdBacks", ignore = true)
    @Mapping(target = "removeManyToManyMapsIdBack", ignore = true)
    EntityUuidIdDTOMapsId toEntity(EntityUuidIdDTOMapsIdDTO entityUuidIdDTOMapsIdDTO);

    @Named("entityUuidIdDTOId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EntityUuidIdDTODTO toDtoEntityUuidIdDTOId(EntityUuidIdDTO entityUuidIdDTO);

    @Named("entityUuidIdDTORelId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EntityUuidIdDTORelDTO toDtoEntityUuidIdDTORelId(EntityUuidIdDTORel entityUuidIdDTORel);

    @Named("entityUuidIdDTORelIdSet")
    default Set<EntityUuidIdDTORelDTO> toDtoEntityUuidIdDTORelIdSet(Set<EntityUuidIdDTORel> entityUuidIdDTORel) {
        return entityUuidIdDTORel.stream().map(this::toDtoEntityUuidIdDTORelId).collect(Collectors.toSet());
    }

    default String map(UUID value) {
        return Objects.toString(value, null);
    }
}
