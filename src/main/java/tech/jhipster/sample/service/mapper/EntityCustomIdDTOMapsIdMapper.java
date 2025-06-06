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
 * Mapper for the entity {@link EntityCustomIdDTOMapsId} and its DTO {@link EntityCustomIdDTOMapsIdDTO}.
 */
@Mapper(componentModel = "spring")
public interface EntityCustomIdDTOMapsIdMapper extends EntityMapper<EntityCustomIdDTOMapsIdDTO, EntityCustomIdDTOMapsId> {
    @Mapping(target = "entityCustomIdDTO", source = "entityCustomIdDTO", qualifiedByName = "entityCustomIdDTOCustomId")
    @Mapping(target = "manyToManyMapsIdBacks", source = "manyToManyMapsIdBacks", qualifiedByName = "entityCustomIdDTORelRelatedIdSet")
    EntityCustomIdDTOMapsIdDTO toDto(EntityCustomIdDTOMapsId s);

    @Mapping(target = "manyToManyMapsIdBacks", ignore = true)
    @Mapping(target = "removeManyToManyMapsIdBack", ignore = true)
    EntityCustomIdDTOMapsId toEntity(EntityCustomIdDTOMapsIdDTO entityCustomIdDTOMapsIdDTO);

    @Named("entityCustomIdDTOCustomId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "customId", source = "customId")
    EntityCustomIdDTODTO toDtoEntityCustomIdDTOCustomId(EntityCustomIdDTO entityCustomIdDTO);

    @Named("entityCustomIdDTORelRelatedId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "relatedId", source = "relatedId")
    EntityCustomIdDTORelDTO toDtoEntityCustomIdDTORelRelatedId(EntityCustomIdDTORel entityCustomIdDTORel);

    @Named("entityCustomIdDTORelRelatedIdSet")
    default Set<EntityCustomIdDTORelDTO> toDtoEntityCustomIdDTORelRelatedIdSet(Set<EntityCustomIdDTORel> entityCustomIdDTORel) {
        return entityCustomIdDTORel.stream().map(this::toDtoEntityCustomIdDTORelRelatedId).collect(Collectors.toSet());
    }
}
