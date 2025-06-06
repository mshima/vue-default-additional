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
 * A UuidIdFiltering.
 */
@Entity
@Table(name = "uuid_id_filtering")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UuidIdFiltering implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "custom_id")
    private UUID customId;

    @JsonIgnoreProperties(
        value = { "uuidIdFiltering", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "uuidIdFiltering")
    private UuidIdFilteringMapsId uuidIdFilteringMapsId;

    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "oneToOne")
    private UuidIdFilteringRelationship oneToOneBack;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manyToOne")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<UuidIdFilteringRelationship> manyToOneBacks = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "manyToManies")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<UuidIdFilteringRelationship> manyToManyBacks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public UUID getCustomId() {
        return this.customId;
    }

    public UuidIdFiltering customId(UUID customId) {
        this.setCustomId(customId);
        return this;
    }

    public void setCustomId(UUID customId) {
        this.customId = customId;
    }

    public UuidIdFilteringMapsId getUuidIdFilteringMapsId() {
        return this.uuidIdFilteringMapsId;
    }

    public void setUuidIdFilteringMapsId(UuidIdFilteringMapsId uuidIdFilteringMapsId) {
        if (this.uuidIdFilteringMapsId != null) {
            this.uuidIdFilteringMapsId.setUuidIdFiltering(null);
        }
        if (uuidIdFilteringMapsId != null) {
            uuidIdFilteringMapsId.setUuidIdFiltering(this);
        }
        this.uuidIdFilteringMapsId = uuidIdFilteringMapsId;
    }

    public UuidIdFiltering uuidIdFilteringMapsId(UuidIdFilteringMapsId uuidIdFilteringMapsId) {
        this.setUuidIdFilteringMapsId(uuidIdFilteringMapsId);
        return this;
    }

    public UuidIdFilteringRelationship getOneToOneBack() {
        return this.oneToOneBack;
    }

    public void setOneToOneBack(UuidIdFilteringRelationship uuidIdFilteringRelationship) {
        if (this.oneToOneBack != null) {
            this.oneToOneBack.setOneToOne(null);
        }
        if (uuidIdFilteringRelationship != null) {
            uuidIdFilteringRelationship.setOneToOne(this);
        }
        this.oneToOneBack = uuidIdFilteringRelationship;
    }

    public UuidIdFiltering oneToOneBack(UuidIdFilteringRelationship uuidIdFilteringRelationship) {
        this.setOneToOneBack(uuidIdFilteringRelationship);
        return this;
    }

    public Set<UuidIdFilteringRelationship> getManyToOneBacks() {
        return this.manyToOneBacks;
    }

    public void setManyToOneBacks(Set<UuidIdFilteringRelationship> uuidIdFilteringRelationships) {
        if (this.manyToOneBacks != null) {
            this.manyToOneBacks.forEach(i -> i.setManyToOne(null));
        }
        if (uuidIdFilteringRelationships != null) {
            uuidIdFilteringRelationships.forEach(i -> i.setManyToOne(this));
        }
        this.manyToOneBacks = uuidIdFilteringRelationships;
    }

    public UuidIdFiltering manyToOneBacks(Set<UuidIdFilteringRelationship> uuidIdFilteringRelationships) {
        this.setManyToOneBacks(uuidIdFilteringRelationships);
        return this;
    }

    public UuidIdFiltering addManyToOneBack(UuidIdFilteringRelationship uuidIdFilteringRelationship) {
        this.manyToOneBacks.add(uuidIdFilteringRelationship);
        uuidIdFilteringRelationship.setManyToOne(this);
        return this;
    }

    public UuidIdFiltering removeManyToOneBack(UuidIdFilteringRelationship uuidIdFilteringRelationship) {
        this.manyToOneBacks.remove(uuidIdFilteringRelationship);
        uuidIdFilteringRelationship.setManyToOne(null);
        return this;
    }

    public Set<UuidIdFilteringRelationship> getManyToManyBacks() {
        return this.manyToManyBacks;
    }

    public void setManyToManyBacks(Set<UuidIdFilteringRelationship> uuidIdFilteringRelationships) {
        if (this.manyToManyBacks != null) {
            this.manyToManyBacks.forEach(i -> i.removeManyToMany(this));
        }
        if (uuidIdFilteringRelationships != null) {
            uuidIdFilteringRelationships.forEach(i -> i.addManyToMany(this));
        }
        this.manyToManyBacks = uuidIdFilteringRelationships;
    }

    public UuidIdFiltering manyToManyBacks(Set<UuidIdFilteringRelationship> uuidIdFilteringRelationships) {
        this.setManyToManyBacks(uuidIdFilteringRelationships);
        return this;
    }

    public UuidIdFiltering addManyToManyBack(UuidIdFilteringRelationship uuidIdFilteringRelationship) {
        this.manyToManyBacks.add(uuidIdFilteringRelationship);
        uuidIdFilteringRelationship.getManyToManies().add(this);
        return this;
    }

    public UuidIdFiltering removeManyToManyBack(UuidIdFilteringRelationship uuidIdFilteringRelationship) {
        this.manyToManyBacks.remove(uuidIdFilteringRelationship);
        uuidIdFilteringRelationship.getManyToManies().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UuidIdFiltering)) {
            return false;
        }
        return getCustomId() != null && getCustomId().equals(((UuidIdFiltering) o).getCustomId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UuidIdFiltering{" +
            "customId=" + getCustomId() +
            "}";
    }
}
