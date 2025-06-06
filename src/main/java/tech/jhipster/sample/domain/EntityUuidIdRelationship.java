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
 * A EntityUuidIdRelationship.
 */
@Entity
@Table(name = "entity_uuid_id_relationship")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityUuidIdRelationship implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @JsonIgnoreProperties(value = { "entityUuidIdMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private EntityUuidId oneToOne;

    @JsonIgnoreProperties(
        value = { "entityUuidId", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private EntityUuidIdMapsId oneToOneMapsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "entityUuidIdMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" }, allowSetters = true)
    private EntityUuidId manyToOne;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "entityUuidId", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    private EntityUuidIdMapsId manyToOneMapsId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_entity_uuid_id_relationship__many_to_many",
        joinColumns = @JoinColumn(name = "entity_uuid_id_relationship_id"),
        inverseJoinColumns = @JoinColumn(name = "many_to_many_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "entityUuidIdMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" }, allowSetters = true)
    private Set<EntityUuidId> manyToManies = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_entity_uuid_id_relationship__many_to_many_maps_id",
        joinColumns = @JoinColumn(name = "entity_uuid_id_relationship_id"),
        inverseJoinColumns = @JoinColumn(name = "many_to_many_maps_id_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "entityUuidId", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    private Set<EntityUuidIdMapsId> manyToManyMapsIds = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public UUID getId() {
        return this.id;
    }

    public EntityUuidIdRelationship id(UUID id) {
        this.setId(id);
        return this;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public EntityUuidId getOneToOne() {
        return this.oneToOne;
    }

    public void setOneToOne(EntityUuidId entityUuidId) {
        this.oneToOne = entityUuidId;
    }

    public EntityUuidIdRelationship oneToOne(EntityUuidId entityUuidId) {
        this.setOneToOne(entityUuidId);
        return this;
    }

    public EntityUuidIdMapsId getOneToOneMapsId() {
        return this.oneToOneMapsId;
    }

    public void setOneToOneMapsId(EntityUuidIdMapsId entityUuidIdMapsId) {
        this.oneToOneMapsId = entityUuidIdMapsId;
    }

    public EntityUuidIdRelationship oneToOneMapsId(EntityUuidIdMapsId entityUuidIdMapsId) {
        this.setOneToOneMapsId(entityUuidIdMapsId);
        return this;
    }

    public EntityUuidId getManyToOne() {
        return this.manyToOne;
    }

    public void setManyToOne(EntityUuidId entityUuidId) {
        this.manyToOne = entityUuidId;
    }

    public EntityUuidIdRelationship manyToOne(EntityUuidId entityUuidId) {
        this.setManyToOne(entityUuidId);
        return this;
    }

    public EntityUuidIdMapsId getManyToOneMapsId() {
        return this.manyToOneMapsId;
    }

    public void setManyToOneMapsId(EntityUuidIdMapsId entityUuidIdMapsId) {
        this.manyToOneMapsId = entityUuidIdMapsId;
    }

    public EntityUuidIdRelationship manyToOneMapsId(EntityUuidIdMapsId entityUuidIdMapsId) {
        this.setManyToOneMapsId(entityUuidIdMapsId);
        return this;
    }

    public Set<EntityUuidId> getManyToManies() {
        return this.manyToManies;
    }

    public void setManyToManies(Set<EntityUuidId> entityUuidIds) {
        this.manyToManies = entityUuidIds;
    }

    public EntityUuidIdRelationship manyToManies(Set<EntityUuidId> entityUuidIds) {
        this.setManyToManies(entityUuidIds);
        return this;
    }

    public EntityUuidIdRelationship addManyToMany(EntityUuidId entityUuidId) {
        this.manyToManies.add(entityUuidId);
        return this;
    }

    public EntityUuidIdRelationship removeManyToMany(EntityUuidId entityUuidId) {
        this.manyToManies.remove(entityUuidId);
        return this;
    }

    public Set<EntityUuidIdMapsId> getManyToManyMapsIds() {
        return this.manyToManyMapsIds;
    }

    public void setManyToManyMapsIds(Set<EntityUuidIdMapsId> entityUuidIdMapsIds) {
        this.manyToManyMapsIds = entityUuidIdMapsIds;
    }

    public EntityUuidIdRelationship manyToManyMapsIds(Set<EntityUuidIdMapsId> entityUuidIdMapsIds) {
        this.setManyToManyMapsIds(entityUuidIdMapsIds);
        return this;
    }

    public EntityUuidIdRelationship addManyToManyMapsId(EntityUuidIdMapsId entityUuidIdMapsId) {
        this.manyToManyMapsIds.add(entityUuidIdMapsId);
        return this;
    }

    public EntityUuidIdRelationship removeManyToManyMapsId(EntityUuidIdMapsId entityUuidIdMapsId) {
        this.manyToManyMapsIds.remove(entityUuidIdMapsId);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityUuidIdRelationship)) {
            return false;
        }
        return getId() != null && getId().equals(((EntityUuidIdRelationship) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityUuidIdRelationship{" +
            "id=" + getId() +
            "}";
    }
}
