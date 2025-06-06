package tech.jhipster.sample.service.mapper;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.mapstruct.*;
import tech.jhipster.sample.domain.EntityUuidIdDTO;
import tech.jhipster.sample.domain.EntityUuidIdDTORel;
import tech.jhipster.sample.service.dto.EntityUuidIdDTODTO;
import tech.jhipster.sample.service.dto.EntityUuidIdDTORelDTO;

/**
 * Mapper for the entity {@link EntityUuidIdDTO} and its DTO {@link EntityUuidIdDTODTO}.
 */
@Mapper(componentModel = "spring")
public interface EntityUuidIdDTOMapper extends EntityMapper<EntityUuidIdDTODTO, EntityUuidIdDTO> {
    @Mapping(target = "manyToManyBacks", source = "manyToManyBacks", qualifiedByName = "entityUuidIdDTORelIdSet")
    EntityUuidIdDTODTO toDto(EntityUuidIdDTO s);

    @Mapping(target = "manyToManyBacks", ignore = true)
    @Mapping(target = "removeManyToManyBack", ignore = true)
    EntityUuidIdDTO toEntity(EntityUuidIdDTODTO entityUuidIdDTODTO);

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
