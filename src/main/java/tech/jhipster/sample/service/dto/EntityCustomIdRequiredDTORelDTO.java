package tech.jhipster.sample.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link tech.jhipster.sample.domain.EntityCustomIdRequiredDTORel} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityCustomIdRequiredDTORelDTO implements Serializable {

    private Long relatedId;

    @NotNull
    private EntityCustomIdRequiredDTODTO oneToOne;

    @NotNull
    private EntityCustomIdRequiredDTOMapsIdDTO oneToOneMapsId;

    @NotNull
    private EntityCustomIdRequiredDTODTO manyToOne;

    @NotNull
    private EntityCustomIdRequiredDTOMapsIdDTO manyToOneMapsId;

    @NotNull
    private Set<EntityCustomIdRequiredDTODTO> manyToManies = new HashSet<>();

    @NotNull
    private Set<EntityCustomIdRequiredDTOMapsIdDTO> manyToManyMapsIds = new HashSet<>();

    public Long getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(Long relatedId) {
        this.relatedId = relatedId;
    }

    public EntityCustomIdRequiredDTODTO getOneToOne() {
        return oneToOne;
    }

    public void setOneToOne(EntityCustomIdRequiredDTODTO oneToOne) {
        this.oneToOne = oneToOne;
    }

    public EntityCustomIdRequiredDTOMapsIdDTO getOneToOneMapsId() {
        return oneToOneMapsId;
    }

    public void setOneToOneMapsId(EntityCustomIdRequiredDTOMapsIdDTO oneToOneMapsId) {
        this.oneToOneMapsId = oneToOneMapsId;
    }

    public EntityCustomIdRequiredDTODTO getManyToOne() {
        return manyToOne;
    }

    public void setManyToOne(EntityCustomIdRequiredDTODTO manyToOne) {
        this.manyToOne = manyToOne;
    }

    public EntityCustomIdRequiredDTOMapsIdDTO getManyToOneMapsId() {
        return manyToOneMapsId;
    }

    public void setManyToOneMapsId(EntityCustomIdRequiredDTOMapsIdDTO manyToOneMapsId) {
        this.manyToOneMapsId = manyToOneMapsId;
    }

    public Set<EntityCustomIdRequiredDTODTO> getManyToManies() {
        return manyToManies;
    }

    public void setManyToManies(Set<EntityCustomIdRequiredDTODTO> manyToManies) {
        this.manyToManies = manyToManies;
    }

    public Set<EntityCustomIdRequiredDTOMapsIdDTO> getManyToManyMapsIds() {
        return manyToManyMapsIds;
    }

    public void setManyToManyMapsIds(Set<EntityCustomIdRequiredDTOMapsIdDTO> manyToManyMapsIds) {
        this.manyToManyMapsIds = manyToManyMapsIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityCustomIdRequiredDTORelDTO)) {
            return false;
        }

        EntityCustomIdRequiredDTORelDTO entityCustomIdRequiredDTORelDTO = (EntityCustomIdRequiredDTORelDTO) o;
        if (this.relatedId == null) {
            return false;
        }
        return Objects.equals(this.relatedId, entityCustomIdRequiredDTORelDTO.relatedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.relatedId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityCustomIdRequiredDTORelDTO{" +
            "relatedId=" + getRelatedId() +
            ", oneToOne=" + getOneToOne() +
            ", oneToOneMapsId=" + getOneToOneMapsId() +
            ", manyToOne=" + getManyToOne() +
            ", manyToOneMapsId=" + getManyToOneMapsId() +
            ", manyToManies=" + getManyToManies() +
            ", manyToManyMapsIds=" + getManyToManyMapsIds() +
            "}";
    }
}
