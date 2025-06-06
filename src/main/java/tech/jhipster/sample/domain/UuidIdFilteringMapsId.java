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
 * A UuidIdFilteringMapsId.
 */
@Entity
@Table(name = "uuid_id_filtering_maps_id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UuidIdFilteringMapsId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "custom_id")
    private UUID customId;

    @JsonIgnoreProperties(value = { "uuidIdFilteringMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "custom_id")
    private UuidIdFiltering uuidIdFiltering;

    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "oneToOneMapsId")
    private UuidIdFilteringRelationship oneToOneMapsIdBack;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manyToOneMapsId")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<UuidIdFilteringRelationship> manyToOneMapsIdBacks = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "manyToManyMapsIds")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<UuidIdFilteringRelationship> manyToManyMapsIdBacks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public UUID getCustomId() {
        return this.customId;
    }

    public UuidIdFilteringMapsId customId(UUID customId) {
        this.setCustomId(customId);
        return this;
    }

    public void setCustomId(UUID customId) {
        this.customId = customId;
    }

    public UuidIdFiltering getUuidIdFiltering() {
        return this.uuidIdFiltering;
    }

    public void setUuidIdFiltering(UuidIdFiltering uuidIdFiltering) {
        this.uuidIdFiltering = uuidIdFiltering;
    }

    public UuidIdFilteringMapsId uuidIdFiltering(UuidIdFiltering uuidIdFiltering) {
        this.setUuidIdFiltering(uuidIdFiltering);
        return this;
    }

    public UuidIdFilteringRelationship getOneToOneMapsIdBack() {
        return this.oneToOneMapsIdBack;
    }

    public void setOneToOneMapsIdBack(UuidIdFilteringRelationship uuidIdFilteringRelationship) {
        if (this.oneToOneMapsIdBack != null) {
            this.oneToOneMapsIdBack.setOneToOneMapsId(null);
        }
        if (uuidIdFilteringRelationship != null) {
            uuidIdFilteringRelationship.setOneToOneMapsId(this);
        }
        this.oneToOneMapsIdBack = uuidIdFilteringRelationship;
    }

    public UuidIdFilteringMapsId oneToOneMapsIdBack(UuidIdFilteringRelationship uuidIdFilteringRelationship) {
        this.setOneToOneMapsIdBack(uuidIdFilteringRelationship);
        return this;
    }

    public Set<UuidIdFilteringRelationship> getManyToOneMapsIdBacks() {
        return this.manyToOneMapsIdBacks;
    }

    public void setManyToOneMapsIdBacks(Set<UuidIdFilteringRelationship> uuidIdFilteringRelationships) {
        if (this.manyToOneMapsIdBacks != null) {
            this.manyToOneMapsIdBacks.forEach(i -> i.setManyToOneMapsId(null));
        }
        if (uuidIdFilteringRelationships != null) {
            uuidIdFilteringRelationships.forEach(i -> i.setManyToOneMapsId(this));
        }
        this.manyToOneMapsIdBacks = uuidIdFilteringRelationships;
    }

    public UuidIdFilteringMapsId manyToOneMapsIdBacks(Set<UuidIdFilteringRelationship> uuidIdFilteringRelationships) {
        this.setManyToOneMapsIdBacks(uuidIdFilteringRelationships);
        return this;
    }

    public UuidIdFilteringMapsId addManyToOneMapsIdBack(UuidIdFilteringRelationship uuidIdFilteringRelationship) {
        this.manyToOneMapsIdBacks.add(uuidIdFilteringRelationship);
        uuidIdFilteringRelationship.setManyToOneMapsId(this);
        return this;
    }

    public UuidIdFilteringMapsId removeManyToOneMapsIdBack(UuidIdFilteringRelationship uuidIdFilteringRelationship) {
        this.manyToOneMapsIdBacks.remove(uuidIdFilteringRelationship);
        uuidIdFilteringRelationship.setManyToOneMapsId(null);
        return this;
    }

    public Set<UuidIdFilteringRelationship> getManyToManyMapsIdBacks() {
        return this.manyToManyMapsIdBacks;
    }

    public void setManyToManyMapsIdBacks(Set<UuidIdFilteringRelationship> uuidIdFilteringRelationships) {
        if (this.manyToManyMapsIdBacks != null) {
            this.manyToManyMapsIdBacks.forEach(i -> i.removeManyToManyMapsId(this));
        }
        if (uuidIdFilteringRelationships != null) {
            uuidIdFilteringRelationships.forEach(i -> i.addManyToManyMapsId(this));
        }
        this.manyToManyMapsIdBacks = uuidIdFilteringRelationships;
    }

    public UuidIdFilteringMapsId manyToManyMapsIdBacks(Set<UuidIdFilteringRelationship> uuidIdFilteringRelationships) {
        this.setManyToManyMapsIdBacks(uuidIdFilteringRelationships);
        return this;
    }

    public UuidIdFilteringMapsId addManyToManyMapsIdBack(UuidIdFilteringRelationship uuidIdFilteringRelationship) {
        this.manyToManyMapsIdBacks.add(uuidIdFilteringRelationship);
        uuidIdFilteringRelationship.getManyToManyMapsIds().add(this);
        return this;
    }

    public UuidIdFilteringMapsId removeManyToManyMapsIdBack(UuidIdFilteringRelationship uuidIdFilteringRelationship) {
        this.manyToManyMapsIdBacks.remove(uuidIdFilteringRelationship);
        uuidIdFilteringRelationship.getManyToManyMapsIds().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UuidIdFilteringMapsId)) {
            return false;
        }
        return getCustomId() != null && getCustomId().equals(((UuidIdFilteringMapsId) o).getCustomId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UuidIdFilteringMapsId{" +
            "customId=" + getCustomId() +
            "}";
    }
}
