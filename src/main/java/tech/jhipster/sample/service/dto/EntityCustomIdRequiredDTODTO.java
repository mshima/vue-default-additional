package tech.jhipster.sample.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link tech.jhipster.sample.domain.EntityCustomIdRequiredDTO} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityCustomIdRequiredDTODTO implements Serializable {

    private Long customId;

    private Set<EntityCustomIdRequiredDTORelDTO> manyToManyBacks = new HashSet<>();

    public Long getCustomId() {
        return customId;
    }

    public void setCustomId(Long customId) {
        this.customId = customId;
    }

    public Set<EntityCustomIdRequiredDTORelDTO> getManyToManyBacks() {
        return manyToManyBacks;
    }

    public void setManyToManyBacks(Set<EntityCustomIdRequiredDTORelDTO> manyToManyBacks) {
        this.manyToManyBacks = manyToManyBacks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityCustomIdRequiredDTODTO)) {
            return false;
        }

        EntityCustomIdRequiredDTODTO entityCustomIdRequiredDTODTO = (EntityCustomIdRequiredDTODTO) o;
        if (this.customId == null) {
            return false;
        }
        return Objects.equals(this.customId, entityCustomIdRequiredDTODTO.customId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.customId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityCustomIdRequiredDTODTO{" +
            "customId=" + getCustomId() +
            ", manyToManyBacks=" + getManyToManyBacks() +
            "}";
    }
}
