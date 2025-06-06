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
 * A EntityUuidIdMapsId.
 */
@Entity
@Table(name = "entity_uuid_id_maps_id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityUuidIdMapsId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private UUID id;

    @JsonIgnoreProperties(value = { "entityUuidIdMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private EntityUuidId entityUuidId;

    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "oneToOneMapsId")
    private EntityUuidIdRelationship oneToOneMapsIdBack;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manyToOneMapsId")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<EntityUuidIdRelationship> manyToOneMapsIdBacks = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "manyToManyMapsIds")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<EntityUuidIdRelationship> manyToManyMapsIdBacks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public UUID getId() {
        return this.id;
    }

    public EntityUuidIdMapsId id(UUID id) {
        this.setId(id);
        return this;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public EntityUuidId getEntityUuidId() {
        return this.entityUuidId;
    }

    public void setEntityUuidId(EntityUuidId entityUuidId) {
        this.entityUuidId = entityUuidId;
    }

    public EntityUuidIdMapsId entityUuidId(EntityUuidId entityUuidId) {
        this.setEntityUuidId(entityUuidId);
        return this;
    }

    public EntityUuidIdRelationship getOneToOneMapsIdBack() {
        return this.oneToOneMapsIdBack;
    }

    public void setOneToOneMapsIdBack(EntityUuidIdRelationship entityUuidIdRelationship) {
        if (this.oneToOneMapsIdBack != null) {
            this.oneToOneMapsIdBack.setOneToOneMapsId(null);
        }
        if (entityUuidIdRelationship != null) {
            entityUuidIdRelationship.setOneToOneMapsId(this);
        }
        this.oneToOneMapsIdBack = entityUuidIdRelationship;
    }

    public EntityUuidIdMapsId oneToOneMapsIdBack(EntityUuidIdRelationship entityUuidIdRelationship) {
        this.setOneToOneMapsIdBack(entityUuidIdRelationship);
        return this;
    }

    public Set<EntityUuidIdRelationship> getManyToOneMapsIdBacks() {
        return this.manyToOneMapsIdBacks;
    }

    public void setManyToOneMapsIdBacks(Set<EntityUuidIdRelationship> entityUuidIdRelationships) {
        if (this.manyToOneMapsIdBacks != null) {
            this.manyToOneMapsIdBacks.forEach(i -> i.setManyToOneMapsId(null));
        }
        if (entityUuidIdRelationships != null) {
            entityUuidIdRelationships.forEach(i -> i.setManyToOneMapsId(this));
        }
        this.manyToOneMapsIdBacks = entityUuidIdRelationships;
    }

    public EntityUuidIdMapsId manyToOneMapsIdBacks(Set<EntityUuidIdRelationship> entityUuidIdRelationships) {
        this.setManyToOneMapsIdBacks(entityUuidIdRelationships);
        return this;
    }

    public EntityUuidIdMapsId addManyToOneMapsIdBack(EntityUuidIdRelationship entityUuidIdRelationship) {
        this.manyToOneMapsIdBacks.add(entityUuidIdRelationship);
        entityUuidIdRelationship.setManyToOneMapsId(this);
        return this;
    }

    public EntityUuidIdMapsId removeManyToOneMapsIdBack(EntityUuidIdRelationship entityUuidIdRelationship) {
        this.manyToOneMapsIdBacks.remove(entityUuidIdRelationship);
        entityUuidIdRelationship.setManyToOneMapsId(null);
        return this;
    }

    public Set<EntityUuidIdRelationship> getManyToManyMapsIdBacks() {
        return this.manyToManyMapsIdBacks;
    }

    public void setManyToManyMapsIdBacks(Set<EntityUuidIdRelationship> entityUuidIdRelationships) {
        if (this.manyToManyMapsIdBacks != null) {
            this.manyToManyMapsIdBacks.forEach(i -> i.removeManyToManyMapsId(this));
        }
        if (entityUuidIdRelationships != null) {
            entityUuidIdRelationships.forEach(i -> i.addManyToManyMapsId(this));
        }
        this.manyToManyMapsIdBacks = entityUuidIdRelationships;
    }

    public EntityUuidIdMapsId manyToManyMapsIdBacks(Set<EntityUuidIdRelationship> entityUuidIdRelationships) {
        this.setManyToManyMapsIdBacks(entityUuidIdRelationships);
        return this;
    }

    public EntityUuidIdMapsId addManyToManyMapsIdBack(EntityUuidIdRelationship entityUuidIdRelationship) {
        this.manyToManyMapsIdBacks.add(entityUuidIdRelationship);
        entityUuidIdRelationship.getManyToManyMapsIds().add(this);
        return this;
    }

    public EntityUuidIdMapsId removeManyToManyMapsIdBack(EntityUuidIdRelationship entityUuidIdRelationship) {
        this.manyToManyMapsIdBacks.remove(entityUuidIdRelationship);
        entityUuidIdRelationship.getManyToManyMapsIds().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityUuidIdMapsId)) {
            return false;
        }
        return getId() != null && getId().equals(((EntityUuidIdMapsId) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityUuidIdMapsId{" +
            "id=" + getId() +
            "}";
    }
}
