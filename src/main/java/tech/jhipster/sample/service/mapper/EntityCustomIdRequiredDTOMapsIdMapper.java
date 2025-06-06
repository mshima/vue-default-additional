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
 * Mapper for the entity {@link EntityCustomIdRequiredDTOMapsId} and its DTO {@link EntityCustomIdRequiredDTOMapsIdDTO}.
 */
@Mapper(componentModel = "spring")
public interface EntityCustomIdRequiredDTOMapsIdMapper
    extends EntityMapper<EntityCustomIdRequiredDTOMapsIdDTO, EntityCustomIdRequiredDTOMapsId> {
    @Mapping(
        target = "entityCustomIdRequiredDTO",
        source = "entityCustomIdRequiredDTO",
        qualifiedByName = "entityCustomIdRequiredDTOCustomId"
    )
    @Mapping(
        target = "manyToManyMapsIdBacks",
        source = "manyToManyMapsIdBacks",
        qualifiedByName = "entityCustomIdRequiredDTORelRelatedIdSet"
    )
    EntityCustomIdRequiredDTOMapsIdDTO toDto(EntityCustomIdRequiredDTOMapsId s);

    @Mapping(target = "manyToManyMapsIdBacks", ignore = true)
    @Mapping(target = "removeManyToManyMapsIdBack", ignore = true)
    EntityCustomIdRequiredDTOMapsId toEntity(EntityCustomIdRequiredDTOMapsIdDTO entityCustomIdRequiredDTOMapsIdDTO);

    @Named("entityCustomIdRequiredDTOCustomId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "customId", source = "customId")
    EntityCustomIdRequiredDTODTO toDtoEntityCustomIdRequiredDTOCustomId(EntityCustomIdRequiredDTO entityCustomIdRequiredDTO);

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
