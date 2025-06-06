package tech.jhipster.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EntityUuidIdDTOMapsId.
 */
@Entity
@Table(name = "entity_uuid_id_dto_maps_id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityUuidIdDTOMapsId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private UUID id;

    @JsonIgnoreProperties(value = { "entityUuidIdDTOMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private EntityUuidIdDTO entityUuidIdDTO;

    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "oneToOneMapsId")
    private EntityUuidIdDTORel oneToOneMapsIdBack;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manyToOneMapsId")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<EntityUuidIdDTORel> manyToOneMapsIdBacks = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "manyToManyMapsIds")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<EntityUuidIdDTORel> manyToManyMapsIdBacks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public UUID getId() {
        return this.id;
    }

    public EntityUuidIdDTOMapsId id(UUID id) {
        this.setId(id);
        return this;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public EntityUuidIdDTO getEntityUuidIdDTO() {
        return this.entityUuidIdDTO;
    }

    public void setEntityUuidIdDTO(EntityUuidIdDTO entityUuidIdDTO) {
        this.entityUuidIdDTO = entityUuidIdDTO;
    }

    public EntityUuidIdDTOMapsId entityUuidIdDTO(EntityUuidIdDTO entityUuidIdDTO) {
        this.setEntityUuidIdDTO(entityUuidIdDTO);
        return this;
    }

    public EntityUuidIdDTORel getOneToOneMapsIdBack() {
        return this.oneToOneMapsIdBack;
    }

    public void setOneToOneMapsIdBack(EntityUuidIdDTORel entityUuidIdDTORel) {
        if (this.oneToOneMapsIdBack != null) {
            this.oneToOneMapsIdBack.setOneToOneMapsId(null);
        }
        if (entityUuidIdDTORel != null) {
            entityUuidIdDTORel.setOneToOneMapsId(this);
        }
        this.oneToOneMapsIdBack = entityUuidIdDTORel;
    }

    public EntityUuidIdDTOMapsId oneToOneMapsIdBack(EntityUuidIdDTORel entityUuidIdDTORel) {
        this.setOneToOneMapsIdBack(entityUuidIdDTORel);
        return this;
    }

    public Set<EntityUuidIdDTORel> getManyToOneMapsIdBacks() {
        return this.manyToOneMapsIdBacks;
    }

    public void setManyToOneMapsIdBacks(Set<EntityUuidIdDTORel> entityUuidIdDTORels) {
        if (this.manyToOneMapsIdBacks != null) {
            this.manyToOneMapsIdBacks.forEach(i -> i.setManyToOneMapsId(null));
        }
        if (entityUuidIdDTORels != null) {
            entityUuidIdDTORels.forEach(i -> i.setManyToOneMapsId(this));
        }
        this.manyToOneMapsIdBacks = entityUuidIdDTORels;
    }

    public EntityUuidIdDTOMapsId manyToOneMapsIdBacks(Set<EntityUuidIdDTORel> entityUuidIdDTORels) {
        this.setManyToOneMapsIdBacks(entityUuidIdDTORels);
        return this;
    }

    public EntityUuidIdDTOMapsId addManyToOneMapsIdBack(EntityUuidIdDTORel entityUuidIdDTORel) {
        this.manyToOneMapsIdBacks.add(entityUuidIdDTORel);
        entityUuidIdDTORel.setManyToOneMapsId(this);
        return this;
    }

    public EntityUuidIdDTOMapsId removeManyToOneMapsIdBack(EntityUuidIdDTORel entityUuidIdDTORel) {
        this.manyToOneMapsIdBacks.remove(entityUuidIdDTORel);
        entityUuidIdDTORel.setManyToOneMapsId(null);
        return this;
    }

    public Set<EntityUuidIdDTORel> getManyToManyMapsIdBacks() {
        return this.manyToManyMapsIdBacks;
    }

    public void setManyToManyMapsIdBacks(Set<EntityUuidIdDTORel> entityUuidIdDTORels) {
        if (this.manyToManyMapsIdBacks != null) {
            this.manyToManyMapsIdBacks.forEach(i -> i.removeManyToManyMapsId(this));
        }
        if (entityUuidIdDTORels != null) {
            entityUuidIdDTORels.forEach(i -> i.addManyToManyMapsId(this));
        }
        this.manyToManyMapsIdBacks = entityUuidIdDTORels;
    }

    public EntityUuidIdDTOMapsId manyToManyMapsIdBacks(Set<EntityUuidIdDTORel> entityUuidIdDTORels) {
        this.setManyToManyMapsIdBacks(entityUuidIdDTORels);
        return this;
    }

    public EntityUuidIdDTOMapsId addManyToManyMapsIdBack(EntityUuidIdDTORel entityUuidIdDTORel) {
        this.manyToManyMapsIdBacks.add(entityUuidIdDTORel);
        entityUuidIdDTORel.getManyToManyMapsIds().add(this);
        return this;
    }

    public EntityUuidIdDTOMapsId removeManyToManyMapsIdBack(EntityUuidIdDTORel entityUuidIdDTORel) {
        this.manyToManyMapsIdBacks.remove(entityUuidIdDTORel);
        entityUuidIdDTORel.getManyToManyMapsIds().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityUuidIdDTOMapsId)) {
            return false;
        }
        return getId() != null && getId().equals(((EntityUuidIdDTOMapsId) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityUuidIdDTOMapsId{" +
            "id=" + getId() +
            "}";
    }
}
