package tech.jhipster.sample.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link tech.jhipster.sample.domain.EntityCustomIdDTOMapsId} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityCustomIdDTOMapsIdDTO implements Serializable {

    private Long customId;

    private EntityCustomIdDTODTO entityCustomIdDTO;

    private Set<EntityCustomIdDTORelDTO> manyToManyMapsIdBacks = new HashSet<>();

    public Long getCustomId() {
        return customId;
    }

    public void setCustomId(Long customId) {
        this.customId = customId;
    }

    public EntityCustomIdDTODTO getEntityCustomIdDTO() {
        return entityCustomIdDTO;
    }

    public void setEntityCustomIdDTO(EntityCustomIdDTODTO entityCustomIdDTO) {
        this.entityCustomIdDTO = entityCustomIdDTO;
    }

    public Set<EntityCustomIdDTORelDTO> getManyToManyMapsIdBacks() {
        return manyToManyMapsIdBacks;
    }

    public void setManyToManyMapsIdBacks(Set<EntityCustomIdDTORelDTO> manyToManyMapsIdBacks) {
        this.manyToManyMapsIdBacks = manyToManyMapsIdBacks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityCustomIdDTOMapsIdDTO)) {
            return false;
        }

        EntityCustomIdDTOMapsIdDTO entityCustomIdDTOMapsIdDTO = (EntityCustomIdDTOMapsIdDTO) o;
        if (this.customId == null) {
            return false;
        }
        return Objects.equals(this.customId, entityCustomIdDTOMapsIdDTO.customId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.customId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityCustomIdDTOMapsIdDTO{" +
            "customId=" + getCustomId() +
            ", entityCustomIdDTO=" + getEntityCustomIdDTO() +
            ", manyToManyMapsIdBacks=" + getManyToManyMapsIdBacks() +
            "}";
    }
}
