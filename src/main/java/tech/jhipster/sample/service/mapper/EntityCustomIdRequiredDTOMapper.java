package tech.jhipster.sample.service.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;
import tech.jhipster.sample.domain.EntityCustomIdRequiredDTO;
import tech.jhipster.sample.domain.EntityCustomIdRequiredDTORel;
import tech.jhipster.sample.service.dto.EntityCustomIdRequiredDTODTO;
import tech.jhipster.sample.service.dto.EntityCustomIdRequiredDTORelDTO;

/**
 * Mapper for the entity {@link EntityCustomIdRequiredDTO} and its DTO {@link EntityCustomIdRequiredDTODTO}.
 */
@Mapper(componentModel = "spring")
public interface EntityCustomIdRequiredDTOMapper extends EntityMapper<EntityCustomIdRequiredDTODTO, EntityCustomIdRequiredDTO> {
    @Mapping(target = "manyToManyBacks", source = "manyToManyBacks", qualifiedByName = "entityCustomIdRequiredDTORelRelatedIdSet")
    EntityCustomIdRequiredDTODTO toDto(EntityCustomIdRequiredDTO s);

    @Mapping(target = "manyToManyBacks", ignore = true)
    @Mapping(target = "removeManyToManyBack", ignore = true)
    EntityCustomIdRequiredDTO toEntity(EntityCustomIdRequiredDTODTO entityCustomIdRequiredDTODTO);

    @Named("entityCustomIdRequiredDTORelRelatedId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "relatedId", source = "relatedId")
    EntityCustomIdRequiredDTORelDTO toDtoEntityCustomIdRequiredDTORelRelatedId(EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel);

    @Named("entityCustomIdRequiredDTORelRelatedIdSet")
    default Set<EntityCustomIdRequiredDTORelDTO> toDtoEntityCustomIdRequiredDTORelRelatedIdSet(
        Set<EntityCustomIdRequiredDTORel> entityCustomIdRequiredDTORel
    ) {
        return entityCustomIdRequiredDTORel.stream().map(this::toDtoEntityCustomIdRequiredDTORelRelatedId).collect(Collectors.toSet());
    }
}
