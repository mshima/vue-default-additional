package tech.jhipster.sample.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * A DTO for the {@link tech.jhipster.sample.domain.EntityUuidIdDTOMapsId} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityUuidIdDTOMapsIdDTO implements Serializable {

    private UUID id;

    private EntityUuidIdDTODTO entityUuidIdDTO;

    private Set<EntityUuidIdDTORelDTO> manyToManyMapsIdBacks = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public EntityUuidIdDTODTO getEntityUuidIdDTO() {
        return entityUuidIdDTO;
    }

    public void setEntityUuidIdDTO(EntityUuidIdDTODTO entityUuidIdDTO) {
        this.entityUuidIdDTO = entityUuidIdDTO;
    }

    public Set<EntityUuidIdDTORelDTO> getManyToManyMapsIdBacks() {
        return manyToManyMapsIdBacks;
    }

    public void setManyToManyMapsIdBacks(Set<EntityUuidIdDTORelDTO> manyToManyMapsIdBacks) {
        this.manyToManyMapsIdBacks = manyToManyMapsIdBacks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityUuidIdDTOMapsIdDTO)) {
            return false;
        }

        EntityUuidIdDTOMapsIdDTO entityUuidIdDTOMapsIdDTO = (EntityUuidIdDTOMapsIdDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, entityUuidIdDTOMapsIdDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityUuidIdDTOMapsIdDTO{" +
            "id='" + getId() + "'" +
            ", entityUuidIdDTO=" + getEntityUuidIdDTO() +
            ", manyToManyMapsIdBacks=" + getManyToManyMapsIdBacks() +
            "}";
    }
}
