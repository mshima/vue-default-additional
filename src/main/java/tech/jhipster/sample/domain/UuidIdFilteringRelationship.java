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
 * A UuidIdFilteringRelationship.
 */
@Entity
@Table(name = "uuid_id_filtering_relationship")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UuidIdFilteringRelationship implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "related_id")
    private UUID relatedId;

    @JsonIgnoreProperties(value = { "uuidIdFilteringMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private UuidIdFiltering oneToOne;

    @JsonIgnoreProperties(
        value = { "uuidIdFiltering", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private UuidIdFilteringMapsId oneToOneMapsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "uuidIdFilteringMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" }, allowSetters = true)
    private UuidIdFiltering manyToOne;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "uuidIdFiltering", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    private UuidIdFilteringMapsId manyToOneMapsId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_uuid_id_filtering_relationship__many_to_many",
        joinColumns = @JoinColumn(name = "uuid_id_filtering_relationship_related_id"),
        inverseJoinColumns = @JoinColumn(name = "many_to_many_custom_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "uuidIdFilteringMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" }, allowSetters = true)
    private Set<UuidIdFiltering> manyToManies = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_uuid_id_filtering_relationship__many_to_many_maps_id",
        joinColumns = @JoinColumn(name = "uuid_id_filtering_relationship_related_id"),
        inverseJoinColumns = @JoinColumn(name = "many_to_many_maps_id_custom_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "uuidIdFiltering", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    private Set<UuidIdFilteringMapsId> manyToManyMapsIds = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public UUID getRelatedId() {
        return this.relatedId;
    }

    public UuidIdFilteringRelationship relatedId(UUID relatedId) {
        this.setRelatedId(relatedId);
        return this;
    }

    public void setRelatedId(UUID relatedId) {
        this.relatedId = relatedId;
    }

    public UuidIdFiltering getOneToOne() {
        return this.oneToOne;
    }

    public void setOneToOne(UuidIdFiltering uuidIdFiltering) {
        this.oneToOne = uuidIdFiltering;
    }

    public UuidIdFilteringRelationship oneToOne(UuidIdFiltering uuidIdFiltering) {
        this.setOneToOne(uuidIdFiltering);
        return this;
    }

    public UuidIdFilteringMapsId getOneToOneMapsId() {
        return this.oneToOneMapsId;
    }

    public void setOneToOneMapsId(UuidIdFilteringMapsId uuidIdFilteringMapsId) {
        this.oneToOneMapsId = uuidIdFilteringMapsId;
    }

    public UuidIdFilteringRelationship oneToOneMapsId(UuidIdFilteringMapsId uuidIdFilteringMapsId) {
        this.setOneToOneMapsId(uuidIdFilteringMapsId);
        return this;
    }

    public UuidIdFiltering getManyToOne() {
        return this.manyToOne;
    }

    public void setManyToOne(UuidIdFiltering uuidIdFiltering) {
        this.manyToOne = uuidIdFiltering;
    }

    public UuidIdFilteringRelationship manyToOne(UuidIdFiltering uuidIdFiltering) {
        this.setManyToOne(uuidIdFiltering);
        return this;
    }

    public UuidIdFilteringMapsId getManyToOneMapsId() {
        return this.manyToOneMapsId;
    }

    public void setManyToOneMapsId(UuidIdFilteringMapsId uuidIdFilteringMapsId) {
        this.manyToOneMapsId = uuidIdFilteringMapsId;
    }

    public UuidIdFilteringRelationship manyToOneMapsId(UuidIdFilteringMapsId uuidIdFilteringMapsId) {
        this.setManyToOneMapsId(uuidIdFilteringMapsId);
        return this;
    }

    public Set<UuidIdFiltering> getManyToManies() {
        return this.manyToManies;
    }

    public void setManyToManies(Set<UuidIdFiltering> uuidIdFilterings) {
        this.manyToManies = uuidIdFilterings;
    }

    public UuidIdFilteringRelationship manyToManies(Set<UuidIdFiltering> uuidIdFilterings) {
        this.setManyToManies(uuidIdFilterings);
        return this;
    }

    public UuidIdFilteringRelationship addManyToMany(UuidIdFiltering uuidIdFiltering) {
        this.manyToManies.add(uuidIdFiltering);
        return this;
    }

    public UuidIdFilteringRelationship removeManyToMany(UuidIdFiltering uuidIdFiltering) {
        this.manyToManies.remove(uuidIdFiltering);
        return this;
    }

    public Set<UuidIdFilteringMapsId> getManyToManyMapsIds() {
        return this.manyToManyMapsIds;
    }

    public void setManyToManyMapsIds(Set<UuidIdFilteringMapsId> uuidIdFilteringMapsIds) {
        this.manyToManyMapsIds = uuidIdFilteringMapsIds;
    }

    public UuidIdFilteringRelationship manyToManyMapsIds(Set<UuidIdFilteringMapsId> uuidIdFilteringMapsIds) {
        this.setManyToManyMapsIds(uuidIdFilteringMapsIds);
        return this;
    }

    public UuidIdFilteringRelationship addManyToManyMapsId(UuidIdFilteringMapsId uuidIdFilteringMapsId) {
        this.manyToManyMapsIds.add(uuidIdFilteringMapsId);
        return this;
    }

    public UuidIdFilteringRelationship removeManyToManyMapsId(UuidIdFilteringMapsId uuidIdFilteringMapsId) {
        this.manyToManyMapsIds.remove(uuidIdFilteringMapsId);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UuidIdFilteringRelationship)) {
            return false;
        }
        return getRelatedId() != null && getRelatedId().equals(((UuidIdFilteringRelationship) o).getRelatedId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UuidIdFilteringRelationship{" +
            "relatedId=" + getRelatedId() +
            "}";
    }
}
