package tech.jhipster.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EntityCustomIdRelationship.
 */
@Entity
@Table(name = "entity_custom_id_relationship")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityCustomIdRelationship implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "related_id")
    private Long relatedId;

    @JsonIgnoreProperties(value = { "entityCustomIdMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private EntityCustomId oneToOne;

    @JsonIgnoreProperties(
        value = { "entityCustomId", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private EntityCustomIdMapsId oneToOneMapsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "entityCustomIdMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" }, allowSetters = true)
    private EntityCustomId manyToOne;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "entityCustomId", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    private EntityCustomIdMapsId manyToOneMapsId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_entity_custom_id_relationship__many_to_many",
        joinColumns = @JoinColumn(name = "entity_custom_id_relationship_related_id"),
        inverseJoinColumns = @JoinColumn(name = "many_to_many_custom_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "entityCustomIdMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" }, allowSetters = true)
    private Set<EntityCustomId> manyToManies = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_entity_custom_id_relationship__many_to_many_maps_id",
        joinColumns = @JoinColumn(name = "entity_custom_id_relationship_related_id"),
        inverseJoinColumns = @JoinColumn(name = "many_to_many_maps_id_custom_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "entityCustomId", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    private Set<EntityCustomIdMapsId> manyToManyMapsIds = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getRelatedId() {
        return this.relatedId;
    }

    public EntityCustomIdRelationship relatedId(Long relatedId) {
        this.setRelatedId(relatedId);
        return this;
    }

    public void setRelatedId(Long relatedId) {
        this.relatedId = relatedId;
    }

    public EntityCustomId getOneToOne() {
        return this.oneToOne;
    }

    public void setOneToOne(EntityCustomId entityCustomId) {
        this.oneToOne = entityCustomId;
    }

    public EntityCustomIdRelationship oneToOne(EntityCustomId entityCustomId) {
        this.setOneToOne(entityCustomId);
        return this;
    }

    public EntityCustomIdMapsId getOneToOneMapsId() {
        return this.oneToOneMapsId;
    }

    public void setOneToOneMapsId(EntityCustomIdMapsId entityCustomIdMapsId) {
        this.oneToOneMapsId = entityCustomIdMapsId;
    }

    public EntityCustomIdRelationship oneToOneMapsId(EntityCustomIdMapsId entityCustomIdMapsId) {
        this.setOneToOneMapsId(entityCustomIdMapsId);
        return this;
    }

    public EntityCustomId getManyToOne() {
        return this.manyToOne;
    }

    public void setManyToOne(EntityCustomId entityCustomId) {
        this.manyToOne = entityCustomId;
    }

    public EntityCustomIdRelationship manyToOne(EntityCustomId entityCustomId) {
        this.setManyToOne(entityCustomId);
        return this;
    }

    public EntityCustomIdMapsId getManyToOneMapsId() {
        return this.manyToOneMapsId;
    }

    public void setManyToOneMapsId(EntityCustomIdMapsId entityCustomIdMapsId) {
        this.manyToOneMapsId = entityCustomIdMapsId;
    }

    public EntityCustomIdRelationship manyToOneMapsId(EntityCustomIdMapsId entityCustomIdMapsId) {
        this.setManyToOneMapsId(entityCustomIdMapsId);
        return this;
    }

    public Set<EntityCustomId> getManyToManies() {
        return this.manyToManies;
    }

    public void setManyToManies(Set<EntityCustomId> entityCustomIds) {
        this.manyToManies = entityCustomIds;
    }

    public EntityCustomIdRelationship manyToManies(Set<EntityCustomId> entityCustomIds) {
        this.setManyToManies(entityCustomIds);
        return this;
    }

    public EntityCustomIdRelationship addManyToMany(EntityCustomId entityCustomId) {
        this.manyToManies.add(entityCustomId);
        return this;
    }

    public EntityCustomIdRelationship removeManyToMany(EntityCustomId entityCustomId) {
        this.manyToManies.remove(entityCustomId);
        return this;
    }

    public Set<EntityCustomIdMapsId> getManyToManyMapsIds() {
        return this.manyToManyMapsIds;
    }

    public void setManyToManyMapsIds(Set<EntityCustomIdMapsId> entityCustomIdMapsIds) {
        this.manyToManyMapsIds = entityCustomIdMapsIds;
    }

    public EntityCustomIdRelationship manyToManyMapsIds(Set<EntityCustomIdMapsId> entityCustomIdMapsIds) {
        this.setManyToManyMapsIds(entityCustomIdMapsIds);
        return this;
    }

    public EntityCustomIdRelationship addManyToManyMapsId(EntityCustomIdMapsId entityCustomIdMapsId) {
        this.manyToManyMapsIds.add(entityCustomIdMapsId);
        return this;
    }

    public EntityCustomIdRelationship removeManyToManyMapsId(EntityCustomIdMapsId entityCustomIdMapsId) {
        this.manyToManyMapsIds.remove(entityCustomIdMapsId);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityCustomIdRelationship)) {
            return false;
        }
        return getRelatedId() != null && getRelatedId().equals(((EntityCustomIdRelationship) o).getRelatedId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityCustomIdRelationship{" +
            "relatedId=" + getRelatedId() +
            "}";
    }
}
