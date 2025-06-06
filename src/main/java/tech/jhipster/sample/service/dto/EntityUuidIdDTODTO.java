package tech.jhipster.sample.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * A DTO for the {@link tech.jhipster.sample.domain.EntityUuidIdDTO} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityUuidIdDTODTO implements Serializable {

    private UUID id;

    private Set<EntityUuidIdDTORelDTO> manyToManyBacks = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<EntityUuidIdDTORelDTO> getManyToManyBacks() {
        return manyToManyBacks;
    }

    public void setManyToManyBacks(Set<EntityUuidIdDTORelDTO> manyToManyBacks) {
        this.manyToManyBacks = manyToManyBacks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityUuidIdDTODTO)) {
            return false;
        }

        EntityUuidIdDTODTO entityUuidIdDTODTO = (EntityUuidIdDTODTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, entityUuidIdDTODTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityUuidIdDTODTO{" +
            "id='" + getId() + "'" +
            ", manyToManyBacks=" + getManyToManyBacks() +
            "}";
    }
}
