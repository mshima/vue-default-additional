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
 * A EntityUuidIdDTO.
 */
@Entity
@Table(name = "entity_uuid_id_dto")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityUuidIdDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @JsonIgnoreProperties(
        value = { "entityUuidIdDTO", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "entityUuidIdDTO")
    private EntityUuidIdDTOMapsId entityUuidIdDTOMapsId;

    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "oneToOne")
    private EntityUuidIdDTORel oneToOneBack;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manyToOne")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<EntityUuidIdDTORel> manyToOneBacks = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "manyToManies")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<EntityUuidIdDTORel> manyToManyBacks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public UUID getId() {
        return this.id;
    }

    public EntityUuidIdDTO id(UUID id) {
        this.setId(id);
        return this;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public EntityUuidIdDTOMapsId getEntityUuidIdDTOMapsId() {
        return this.entityUuidIdDTOMapsId;
    }

    public void setEntityUuidIdDTOMapsId(EntityUuidIdDTOMapsId entityUuidIdDTOMapsId) {
        if (this.entityUuidIdDTOMapsId != null) {
            this.entityUuidIdDTOMapsId.setEntityUuidIdDTO(null);
        }
        if (entityUuidIdDTOMapsId != null) {
            entityUuidIdDTOMapsId.setEntityUuidIdDTO(this);
        }
        this.entityUuidIdDTOMapsId = entityUuidIdDTOMapsId;
    }

    public EntityUuidIdDTO entityUuidIdDTOMapsId(EntityUuidIdDTOMapsId entityUuidIdDTOMapsId) {
        this.setEntityUuidIdDTOMapsId(entityUuidIdDTOMapsId);
        return this;
    }

    public EntityUuidIdDTORel getOneToOneBack() {
        return this.oneToOneBack;
    }

    public void setOneToOneBack(EntityUuidIdDTORel entityUuidIdDTORel) {
        if (this.oneToOneBack != null) {
            this.oneToOneBack.setOneToOne(null);
        }
        if (entityUuidIdDTORel != null) {
            entityUuidIdDTORel.setOneToOne(this);
        }
        this.oneToOneBack = entityUuidIdDTORel;
    }

    public EntityUuidIdDTO oneToOneBack(EntityUuidIdDTORel entityUuidIdDTORel) {
        this.setOneToOneBack(entityUuidIdDTORel);
        return this;
    }

    public Set<EntityUuidIdDTORel> getManyToOneBacks() {
        return this.manyToOneBacks;
    }

    public void setManyToOneBacks(Set<EntityUuidIdDTORel> entityUuidIdDTORels) {
        if (this.manyToOneBacks != null) {
            this.manyToOneBacks.forEach(i -> i.setManyToOne(null));
        }
        if (entityUuidIdDTORels != null) {
            entityUuidIdDTORels.forEach(i -> i.setManyToOne(this));
        }
        this.manyToOneBacks = entityUuidIdDTORels;
    }

    public EntityUuidIdDTO manyToOneBacks(Set<EntityUuidIdDTORel> entityUuidIdDTORels) {
        this.setManyToOneBacks(entityUuidIdDTORels);
        return this;
    }

    public EntityUuidIdDTO addManyToOneBack(EntityUuidIdDTORel entityUuidIdDTORel) {
        this.manyToOneBacks.add(entityUuidIdDTORel);
        entityUuidIdDTORel.setManyToOne(this);
        return this;
    }

    public EntityUuidIdDTO removeManyToOneBack(EntityUuidIdDTORel entityUuidIdDTORel) {
        this.manyToOneBacks.remove(entityUuidIdDTORel);
        entityUuidIdDTORel.setManyToOne(null);
        return this;
    }

    public Set<EntityUuidIdDTORel> getManyToManyBacks() {
        return this.manyToManyBacks;
    }

    public void setManyToManyBacks(Set<EntityUuidIdDTORel> entityUuidIdDTORels) {
        if (this.manyToManyBacks != null) {
            this.manyToManyBacks.forEach(i -> i.removeManyToMany(this));
        }
        if (entityUuidIdDTORels != null) {
            entityUuidIdDTORels.forEach(i -> i.addManyToMany(this));
        }
        this.manyToManyBacks = entityUuidIdDTORels;
    }

    public EntityUuidIdDTO manyToManyBacks(Set<EntityUuidIdDTORel> entityUuidIdDTORels) {
        this.setManyToManyBacks(entityUuidIdDTORels);
        return this;
    }

    public EntityUuidIdDTO addManyToManyBack(EntityUuidIdDTORel entityUuidIdDTORel) {
        this.manyToManyBacks.add(entityUuidIdDTORel);
        entityUuidIdDTORel.getManyToManies().add(this);
        return this;
    }

    public EntityUuidIdDTO removeManyToManyBack(EntityUuidIdDTORel entityUuidIdDTORel) {
        this.manyToManyBacks.remove(entityUuidIdDTORel);
        entityUuidIdDTORel.getManyToManies().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityUuidIdDTO)) {
            return false;
        }
        return getId() != null && getId().equals(((EntityUuidIdDTO) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityUuidIdDTO{" +
            "id=" + getId() +
            "}";
    }
}
