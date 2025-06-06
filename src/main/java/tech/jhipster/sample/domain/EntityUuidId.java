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
 * A EntityUuidId.
 */
@Entity
@Table(name = "entity_uuid_id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityUuidId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @JsonIgnoreProperties(
        value = { "entityUuidId", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "entityUuidId")
    private EntityUuidIdMapsId entityUuidIdMapsId;

    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "oneToOne")
    private EntityUuidIdRelationship oneToOneBack;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manyToOne")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<EntityUuidIdRelationship> manyToOneBacks = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "manyToManies")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<EntityUuidIdRelationship> manyToManyBacks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public UUID getId() {
        return this.id;
    }

    public EntityUuidId id(UUID id) {
        this.setId(id);
        return this;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public EntityUuidIdMapsId getEntityUuidIdMapsId() {
        return this.entityUuidIdMapsId;
    }

    public void setEntityUuidIdMapsId(EntityUuidIdMapsId entityUuidIdMapsId) {
        if (this.entityUuidIdMapsId != null) {
            this.entityUuidIdMapsId.setEntityUuidId(null);
        }
        if (entityUuidIdMapsId != null) {
            entityUuidIdMapsId.setEntityUuidId(this);
        }
        this.entityUuidIdMapsId = entityUuidIdMapsId;
    }

    public EntityUuidId entityUuidIdMapsId(EntityUuidIdMapsId entityUuidIdMapsId) {
        this.setEntityUuidIdMapsId(entityUuidIdMapsId);
        return this;
    }

    public EntityUuidIdRelationship getOneToOneBack() {
        return this.oneToOneBack;
    }

    public void setOneToOneBack(EntityUuidIdRelationship entityUuidIdRelationship) {
        if (this.oneToOneBack != null) {
            this.oneToOneBack.setOneToOne(null);
        }
        if (entityUuidIdRelationship != null) {
            entityUuidIdRelationship.setOneToOne(this);
        }
        this.oneToOneBack = entityUuidIdRelationship;
    }

    public EntityUuidId oneToOneBack(EntityUuidIdRelationship entityUuidIdRelationship) {
        this.setOneToOneBack(entityUuidIdRelationship);
        return this;
    }

    public Set<EntityUuidIdRelationship> getManyToOneBacks() {
        return this.manyToOneBacks;
    }

    public void setManyToOneBacks(Set<EntityUuidIdRelationship> entityUuidIdRelationships) {
        if (this.manyToOneBacks != null) {
            this.manyToOneBacks.forEach(i -> i.setManyToOne(null));
        }
        if (entityUuidIdRelationships != null) {
            entityUuidIdRelationships.forEach(i -> i.setManyToOne(this));
        }
        this.manyToOneBacks = entityUuidIdRelationships;
    }

    public EntityUuidId manyToOneBacks(Set<EntityUuidIdRelationship> entityUuidIdRelationships) {
        this.setManyToOneBacks(entityUuidIdRelationships);
        return this;
    }

    public EntityUuidId addManyToOneBack(EntityUuidIdRelationship entityUuidIdRelationship) {
        this.manyToOneBacks.add(entityUuidIdRelationship);
        entityUuidIdRelationship.setManyToOne(this);
        return this;
    }

    public EntityUuidId removeManyToOneBack(EntityUuidIdRelationship entityUuidIdRelationship) {
        this.manyToOneBacks.remove(entityUuidIdRelationship);
        entityUuidIdRelationship.setManyToOne(null);
        return this;
    }

    public Set<EntityUuidIdRelationship> getManyToManyBacks() {
        return this.manyToManyBacks;
    }

    public void setManyToManyBacks(Set<EntityUuidIdRelationship> entityUuidIdRelationships) {
        if (this.manyToManyBacks != null) {
            this.manyToManyBacks.forEach(i -> i.removeManyToMany(this));
        }
        if (entityUuidIdRelationships != null) {
            entityUuidIdRelationships.forEach(i -> i.addManyToMany(this));
        }
        this.manyToManyBacks = entityUuidIdRelationships;
    }

    public EntityUuidId manyToManyBacks(Set<EntityUuidIdRelationship> entityUuidIdRelationships) {
        this.setManyToManyBacks(entityUuidIdRelationships);
        return this;
    }

    public EntityUuidId addManyToManyBack(EntityUuidIdRelationship entityUuidIdRelationship) {
        this.manyToManyBacks.add(entityUuidIdRelationship);
        entityUuidIdRelationship.getManyToManies().add(this);
        return this;
    }

    public EntityUuidId removeManyToManyBack(EntityUuidIdRelationship entityUuidIdRelationship) {
        this.manyToManyBacks.remove(entityUuidIdRelationship);
        entityUuidIdRelationship.getManyToManies().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityUuidId)) {
            return false;
        }
        return getId() != null && getId().equals(((EntityUuidId) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityUuidId{" +
            "id=" + getId() +
            "}";
    }
}
