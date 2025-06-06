package tech.jhipster.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EntityCustomIdMapsId.
 */
@Entity
@Table(name = "entity_custom_id_maps_id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityCustomIdMapsId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "custom_id")
    private Long customId;

    @JsonIgnoreProperties(value = { "entityCustomIdMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "custom_id")
    private EntityCustomId entityCustomId;

    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "oneToOneMapsId")
    private EntityCustomIdRelationship oneToOneMapsIdBack;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manyToOneMapsId")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<EntityCustomIdRelationship> manyToOneMapsIdBacks = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "manyToManyMapsIds")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<EntityCustomIdRelationship> manyToManyMapsIdBacks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getCustomId() {
        return this.customId;
    }

    public EntityCustomIdMapsId customId(Long customId) {
        this.setCustomId(customId);
        return this;
    }

    public void setCustomId(Long customId) {
        this.customId = customId;
    }

    public EntityCustomId getEntityCustomId() {
        return this.entityCustomId;
    }

    public void setEntityCustomId(EntityCustomId entityCustomId) {
        this.entityCustomId = entityCustomId;
    }

    public EntityCustomIdMapsId entityCustomId(EntityCustomId entityCustomId) {
        this.setEntityCustomId(entityCustomId);
        return this;
    }

    public EntityCustomIdRelationship getOneToOneMapsIdBack() {
        return this.oneToOneMapsIdBack;
    }

    public void setOneToOneMapsIdBack(EntityCustomIdRelationship entityCustomIdRelationship) {
        if (this.oneToOneMapsIdBack != null) {
            this.oneToOneMapsIdBack.setOneToOneMapsId(null);
        }
        if (entityCustomIdRelationship != null) {
            entityCustomIdRelationship.setOneToOneMapsId(this);
        }
        this.oneToOneMapsIdBack = entityCustomIdRelationship;
    }

    public EntityCustomIdMapsId oneToOneMapsIdBack(EntityCustomIdRelationship entityCustomIdRelationship) {
        this.setOneToOneMapsIdBack(entityCustomIdRelationship);
        return this;
    }

    public Set<EntityCustomIdRelationship> getManyToOneMapsIdBacks() {
        return this.manyToOneMapsIdBacks;
    }

    public void setManyToOneMapsIdBacks(Set<EntityCustomIdRelationship> entityCustomIdRelationships) {
        if (this.manyToOneMapsIdBacks != null) {
            this.manyToOneMapsIdBacks.forEach(i -> i.setManyToOneMapsId(null));
        }
        if (entityCustomIdRelationships != null) {
            entityCustomIdRelationships.forEach(i -> i.setManyToOneMapsId(this));
        }
        this.manyToOneMapsIdBacks = entityCustomIdRelationships;
    }

    public EntityCustomIdMapsId manyToOneMapsIdBacks(Set<EntityCustomIdRelationship> entityCustomIdRelationships) {
        this.setManyToOneMapsIdBacks(entityCustomIdRelationships);
        return this;
    }

    public EntityCustomIdMapsId addManyToOneMapsIdBack(EntityCustomIdRelationship entityCustomIdRelationship) {
        this.manyToOneMapsIdBacks.add(entityCustomIdRelationship);
        entityCustomIdRelationship.setManyToOneMapsId(this);
        return this;
    }

    public EntityCustomIdMapsId removeManyToOneMapsIdBack(EntityCustomIdRelationship entityCustomIdRelationship) {
        this.manyToOneMapsIdBacks.remove(entityCustomIdRelationship);
        entityCustomIdRelationship.setManyToOneMapsId(null);
        return this;
    }

    public Set<EntityCustomIdRelationship> getManyToManyMapsIdBacks() {
        return this.manyToManyMapsIdBacks;
    }

    public void setManyToManyMapsIdBacks(Set<EntityCustomIdRelationship> entityCustomIdRelationships) {
        if (this.manyToManyMapsIdBacks != null) {
            this.manyToManyMapsIdBacks.forEach(i -> i.removeManyToManyMapsId(this));
        }
        if (entityCustomIdRelationships != null) {
            entityCustomIdRelationships.forEach(i -> i.addManyToManyMapsId(this));
        }
        this.manyToManyMapsIdBacks = entityCustomIdRelationships;
    }

    public EntityCustomIdMapsId manyToManyMapsIdBacks(Set<EntityCustomIdRelationship> entityCustomIdRelationships) {
        this.setManyToManyMapsIdBacks(entityCustomIdRelationships);
        return this;
    }

    public EntityCustomIdMapsId addManyToManyMapsIdBack(EntityCustomIdRelationship entityCustomIdRelationship) {
        this.manyToManyMapsIdBacks.add(entityCustomIdRelationship);
        entityCustomIdRelationship.getManyToManyMapsIds().add(this);
        return this;
    }

    public EntityCustomIdMapsId removeManyToManyMapsIdBack(EntityCustomIdRelationship entityCustomIdRelationship) {
        this.manyToManyMapsIdBacks.remove(entityCustomIdRelationship);
        entityCustomIdRelationship.getManyToManyMapsIds().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityCustomIdMapsId)) {
            return false;
        }
        return getCustomId() != null && getCustomId().equals(((EntityCustomIdMapsId) o).getCustomId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityCustomIdMapsId{" +
            "customId=" + getCustomId() +
            "}";
    }
}
