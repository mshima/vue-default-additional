package tech.jhipster.sample.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link tech.jhipster.sample.domain.EntityCustomIdDTORel} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityCustomIdDTORelDTO implements Serializable {

    private Long relatedId;

    private EntityCustomIdDTODTO oneToOne;

    private EntityCustomIdDTOMapsIdDTO oneToOneMapsId;

    private EntityCustomIdDTODTO manyToOne;

    private EntityCustomIdDTOMapsIdDTO manyToOneMapsId;

    private Set<EntityCustomIdDTODTO> manyToManies = new HashSet<>();

    private Set<EntityCustomIdDTOMapsIdDTO> manyToManyMapsIds = new HashSet<>();

    public Long getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(Long relatedId) {
        this.relatedId = relatedId;
    }

    public EntityCustomIdDTODTO getOneToOne() {
        return oneToOne;
    }

    public void setOneToOne(EntityCustomIdDTODTO oneToOne) {
        this.oneToOne = oneToOne;
    }

    public EntityCustomIdDTOMapsIdDTO getOneToOneMapsId() {
        return oneToOneMapsId;
    }

    public void setOneToOneMapsId(EntityCustomIdDTOMapsIdDTO oneToOneMapsId) {
        this.oneToOneMapsId = oneToOneMapsId;
    }

    public EntityCustomIdDTODTO getManyToOne() {
        return manyToOne;
    }

    public void setManyToOne(EntityCustomIdDTODTO manyToOne) {
        this.manyToOne = manyToOne;
    }

    public EntityCustomIdDTOMapsIdDTO getManyToOneMapsId() {
        return manyToOneMapsId;
    }

    public void setManyToOneMapsId(EntityCustomIdDTOMapsIdDTO manyToOneMapsId) {
        this.manyToOneMapsId = manyToOneMapsId;
    }

    public Set<EntityCustomIdDTODTO> getManyToManies() {
        return manyToManies;
    }

    public void setManyToManies(Set<EntityCustomIdDTODTO> manyToManies) {
        this.manyToManies = manyToManies;
    }

    public Set<EntityCustomIdDTOMapsIdDTO> getManyToManyMapsIds() {
        return manyToManyMapsIds;
    }

    public void setManyToManyMapsIds(Set<EntityCustomIdDTOMapsIdDTO> manyToManyMapsIds) {
        this.manyToManyMapsIds = manyToManyMapsIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityCustomIdDTORelDTO)) {
            return false;
        }

        EntityCustomIdDTORelDTO entityCustomIdDTORelDTO = (EntityCustomIdDTORelDTO) o;
        if (this.relatedId == null) {
            return false;
        }
        return Objects.equals(this.relatedId, entityCustomIdDTORelDTO.relatedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.relatedId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityCustomIdDTORelDTO{" +
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
