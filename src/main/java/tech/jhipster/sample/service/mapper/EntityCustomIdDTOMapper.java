package tech.jhipster.sample.service.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;
import tech.jhipster.sample.domain.EntityCustomIdDTO;
import tech.jhipster.sample.domain.EntityCustomIdDTORel;
import tech.jhipster.sample.service.dto.EntityCustomIdDTODTO;
import tech.jhipster.sample.service.dto.EntityCustomIdDTORelDTO;

/**
 * Mapper for the entity {@link EntityCustomIdDTO} and its DTO {@link EntityCustomIdDTODTO}.
 */
@Mapper(componentModel = "spring")
public interface EntityCustomIdDTOMapper extends EntityMapper<EntityCustomIdDTODTO, EntityCustomIdDTO> {
    @Mapping(target = "manyToManyBacks", source = "manyToManyBacks", qualifiedByName = "entityCustomIdDTORelRelatedIdSet")
    EntityCustomIdDTODTO toDto(EntityCustomIdDTO s);

    @Mapping(target = "manyToManyBacks", ignore = true)
    @Mapping(target = "removeManyToManyBack", ignore = true)
    EntityCustomIdDTO toEntity(EntityCustomIdDTODTO entityCustomIdDTODTO);

    @Named("entityCustomIdDTORelRelatedId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "relatedId", source = "relatedId")
    EntityCustomIdDTORelDTO toDtoEntityCustomIdDTORelRelatedId(EntityCustomIdDTORel entityCustomIdDTORel);

    @Named("entityCustomIdDTORelRelatedIdSet")
    default Set<EntityCustomIdDTORelDTO> toDtoEntityCustomIdDTORelRelatedIdSet(Set<EntityCustomIdDTORel> entityCustomIdDTORel) {
        return entityCustomIdDTORel.stream().map(this::toDtoEntityCustomIdDTORelRelatedId).collect(Collectors.toSet());
    }
}
