package tech.jhipster.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EntityCustomId.
 */
@Entity
@Table(name = "entity_custom_id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityCustomId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "custom_id")
    private Long customId;

    @JsonIgnoreProperties(
        value = { "entityCustomId", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "entityCustomId")
    private EntityCustomIdMapsId entityCustomIdMapsId;

    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "oneToOne")
    private EntityCustomIdRelationship oneToOneBack;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manyToOne")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<EntityCustomIdRelationship> manyToOneBacks = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "manyToManies")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<EntityCustomIdRelationship> manyToManyBacks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getCustomId() {
        return this.customId;
    }

    public EntityCustomId customId(Long customId) {
        this.setCustomId(customId);
        return this;
    }

    public void setCustomId(Long customId) {
        this.customId = customId;
    }

    public EntityCustomIdMapsId getEntityCustomIdMapsId() {
        return this.entityCustomIdMapsId;
    }

    public void setEntityCustomIdMapsId(EntityCustomIdMapsId entityCustomIdMapsId) {
        if (this.entityCustomIdMapsId != null) {
            this.entityCustomIdMapsId.setEntityCustomId(null);
        }
        if (entityCustomIdMapsId != null) {
            entityCustomIdMapsId.setEntityCustomId(this);
        }
        this.entityCustomIdMapsId = entityCustomIdMapsId;
    }

    public EntityCustomId entityCustomIdMapsId(EntityCustomIdMapsId entityCustomIdMapsId) {
        this.setEntityCustomIdMapsId(entityCustomIdMapsId);
        return this;
    }

    public EntityCustomIdRelationship getOneToOneBack() {
        return this.oneToOneBack;
    }

    public void setOneToOneBack(EntityCustomIdRelationship entityCustomIdRelationship) {
        if (this.oneToOneBack != null) {
            this.oneToOneBack.setOneToOne(null);
        }
        if (entityCustomIdRelationship != null) {
            entityCustomIdRelationship.setOneToOne(this);
        }
        this.oneToOneBack = entityCustomIdRelationship;
    }

    public EntityCustomId oneToOneBack(EntityCustomIdRelationship entityCustomIdRelationship) {
        this.setOneToOneBack(entityCustomIdRelationship);
        return this;
    }

    public Set<EntityCustomIdRelationship> getManyToOneBacks() {
        return this.manyToOneBacks;
    }

    public void setManyToOneBacks(Set<EntityCustomIdRelationship> entityCustomIdRelationships) {
        if (this.manyToOneBacks != null) {
            this.manyToOneBacks.forEach(i -> i.setManyToOne(null));
        }
        if (entityCustomIdRelationships != null) {
            entityCustomIdRelationships.forEach(i -> i.setManyToOne(this));
        }
        this.manyToOneBacks = entityCustomIdRelationships;
    }

    public EntityCustomId manyToOneBacks(Set<EntityCustomIdRelationship> entityCustomIdRelationships) {
        this.setManyToOneBacks(entityCustomIdRelationships);
        return this;
    }

    public EntityCustomId addManyToOneBack(EntityCustomIdRelationship entityCustomIdRelationship) {
        this.manyToOneBacks.add(entityCustomIdRelationship);
        entityCustomIdRelationship.setManyToOne(this);
        return this;
    }

    public EntityCustomId removeManyToOneBack(EntityCustomIdRelationship entityCustomIdRelationship) {
        this.manyToOneBacks.remove(entityCustomIdRelationship);
        entityCustomIdRelationship.setManyToOne(null);
        return this;
    }

    public Set<EntityCustomIdRelationship> getManyToManyBacks() {
        return this.manyToManyBacks;
    }

    public void setManyToManyBacks(Set<EntityCustomIdRelationship> entityCustomIdRelationships) {
        if (this.manyToManyBacks != null) {
            this.manyToManyBacks.forEach(i -> i.removeManyToMany(this));
        }
        if (entityCustomIdRelationships != null) {
            entityCustomIdRelationships.forEach(i -> i.addManyToMany(this));
        }
        this.manyToManyBacks = entityCustomIdRelationships;
    }

    public EntityCustomId manyToManyBacks(Set<EntityCustomIdRelationship> entityCustomIdRelationships) {
        this.setManyToManyBacks(entityCustomIdRelationships);
        return this;
    }

    public EntityCustomId addManyToManyBack(EntityCustomIdRelationship entityCustomIdRelationship) {
        this.manyToManyBacks.add(entityCustomIdRelationship);
        entityCustomIdRelationship.getManyToManies().add(this);
        return this;
    }

    public EntityCustomId removeManyToManyBack(EntityCustomIdRelationship entityCustomIdRelationship) {
        this.manyToManyBacks.remove(entityCustomIdRelationship);
        entityCustomIdRelationship.getManyToManies().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityCustomId)) {
            return false;
        }
        return getCustomId() != null && getCustomId().equals(((EntityCustomId) o).getCustomId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityCustomId{" +
            "customId=" + getCustomId() +
            "}";
    }
}
