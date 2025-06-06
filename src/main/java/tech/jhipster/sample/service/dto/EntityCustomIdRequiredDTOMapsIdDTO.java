package tech.jhipster.sample.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link tech.jhipster.sample.domain.EntityCustomIdRequiredDTOMapsId} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityCustomIdRequiredDTOMapsIdDTO implements Serializable {

    private Long customId;

    private EntityCustomIdRequiredDTODTO entityCustomIdRequiredDTO;

    private Set<EntityCustomIdRequiredDTORelDTO> manyToManyMapsIdBacks = new HashSet<>();

    public Long getCustomId() {
        return customId;
    }

    public void setCustomId(Long customId) {
        this.customId = customId;
    }

    public EntityCustomIdRequiredDTODTO getEntityCustomIdRequiredDTO() {
        return entityCustomIdRequiredDTO;
    }

    public void setEntityCustomIdRequiredDTO(EntityCustomIdRequiredDTODTO entityCustomIdRequiredDTO) {
        this.entityCustomIdRequiredDTO = entityCustomIdRequiredDTO;
    }

    public Set<EntityCustomIdRequiredDTORelDTO> getManyToManyMapsIdBacks() {
        return manyToManyMapsIdBacks;
    }

    public void setManyToManyMapsIdBacks(Set<EntityCustomIdRequiredDTORelDTO> manyToManyMapsIdBacks) {
        this.manyToManyMapsIdBacks = manyToManyMapsIdBacks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityCustomIdRequiredDTOMapsIdDTO)) {
            return false;
        }

        EntityCustomIdRequiredDTOMapsIdDTO entityCustomIdRequiredDTOMapsIdDTO = (EntityCustomIdRequiredDTOMapsIdDTO) o;
        if (this.customId == null) {
            return false;
        }
        return Objects.equals(this.customId, entityCustomIdRequiredDTOMapsIdDTO.customId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.customId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityCustomIdRequiredDTOMapsIdDTO{" +
            "customId=" + getCustomId() +
            ", entityCustomIdRequiredDTO=" + getEntityCustomIdRequiredDTO() +
            ", manyToManyMapsIdBacks=" + getManyToManyMapsIdBacks() +
            "}";
    }
}
