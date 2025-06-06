package tech.jhipster.sample.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * A DTO for the {@link tech.jhipster.sample.domain.EntityUuidIdDTORel} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityUuidIdDTORelDTO implements Serializable {

    private UUID id;

    private EntityUuidIdDTODTO oneToOne;

    private EntityUuidIdDTOMapsIdDTO oneToOneMapsId;

    private EntityUuidIdDTODTO manyToOne;

    private EntityUuidIdDTOMapsIdDTO manyToOneMapsId;

    private Set<EntityUuidIdDTODTO> manyToManies = new HashSet<>();

    private Set<EntityUuidIdDTOMapsIdDTO> manyToManyMapsIds = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public EntityUuidIdDTODTO getOneToOne() {
        return oneToOne;
    }

    public void setOneToOne(EntityUuidIdDTODTO oneToOne) {
        this.oneToOne = oneToOne;
    }

    public EntityUuidIdDTOMapsIdDTO getOneToOneMapsId() {
        return oneToOneMapsId;
    }

    public void setOneToOneMapsId(EntityUuidIdDTOMapsIdDTO oneToOneMapsId) {
        this.oneToOneMapsId = oneToOneMapsId;
    }

    public EntityUuidIdDTODTO getManyToOne() {
        return manyToOne;
    }

    public void setManyToOne(EntityUuidIdDTODTO manyToOne) {
        this.manyToOne = manyToOne;
    }

    public EntityUuidIdDTOMapsIdDTO getManyToOneMapsId() {
        return manyToOneMapsId;
    }

    public void setManyToOneMapsId(EntityUuidIdDTOMapsIdDTO manyToOneMapsId) {
        this.manyToOneMapsId = manyToOneMapsId;
    }

    public Set<EntityUuidIdDTODTO> getManyToManies() {
        return manyToManies;
    }

    public void setManyToManies(Set<EntityUuidIdDTODTO> manyToManies) {
        this.manyToManies = manyToManies;
    }

    public Set<EntityUuidIdDTOMapsIdDTO> getManyToManyMapsIds() {
        return manyToManyMapsIds;
    }

    public void setManyToManyMapsIds(Set<EntityUuidIdDTOMapsIdDTO> manyToManyMapsIds) {
        this.manyToManyMapsIds = manyToManyMapsIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityUuidIdDTORelDTO)) {
            return false;
        }

        EntityUuidIdDTORelDTO entityUuidIdDTORelDTO = (EntityUuidIdDTORelDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, entityUuidIdDTORelDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityUuidIdDTORelDTO{" +
            "id='" + getId() + "'" +
            ", oneToOne=" + getOneToOne() +
            ", oneToOneMapsId=" + getOneToOneMapsId() +
            ", manyToOne=" + getManyToOne() +
            ", manyToOneMapsId=" + getManyToOneMapsId() +
            ", manyToManies=" + getManyToManies() +
            ", manyToManyMapsIds=" + getManyToManyMapsIds() +
            "}";
    }
}
