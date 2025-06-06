package tech.jhipster.sample.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link tech.jhipster.sample.domain.EntityCustomIdDTO} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityCustomIdDTODTO implements Serializable {

    private Long customId;

    private Set<EntityCustomIdDTORelDTO> manyToManyBacks = new HashSet<>();

    public Long getCustomId() {
        return customId;
    }

    public void setCustomId(Long customId) {
        this.customId = customId;
    }

    public Set<EntityCustomIdDTORelDTO> getManyToManyBacks() {
        return manyToManyBacks;
    }

    public void setManyToManyBacks(Set<EntityCustomIdDTORelDTO> manyToManyBacks) {
        this.manyToManyBacks = manyToManyBacks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityCustomIdDTODTO)) {
            return false;
        }

        EntityCustomIdDTODTO entityCustomIdDTODTO = (EntityCustomIdDTODTO) o;
        if (this.customId == null) {
            return false;
        }
        return Objects.equals(this.customId, entityCustomIdDTODTO.customId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.customId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityCustomIdDTODTO{" +
            "customId=" + getCustomId() +
            ", manyToManyBacks=" + getManyToManyBacks() +
            "}";
    }
}
