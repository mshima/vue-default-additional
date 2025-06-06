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
 * A EntityUuidIdDTORel.
 */
@Entity
@Table(name = "entity_uuid_id_dto_rel")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityUuidIdDTORel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @JsonIgnoreProperties(value = { "entityUuidIdDTOMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private EntityUuidIdDTO oneToOne;

    @JsonIgnoreProperties(
        value = { "entityUuidIdDTO", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private EntityUuidIdDTOMapsId oneToOneMapsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "entityUuidIdDTOMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" }, allowSetters = true)
    private EntityUuidIdDTO manyToOne;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "entityUuidIdDTO", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    private EntityUuidIdDTOMapsId manyToOneMapsId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_entity_uuid_id_dto_rel__many_to_many",
        joinColumns = @JoinColumn(name = "entity_uuid_id_dto_rel_id"),
        inverseJoinColumns = @JoinColumn(name = "many_to_many_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "entityUuidIdDTOMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" }, allowSetters = true)
    private Set<EntityUuidIdDTO> manyToManies = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_entity_uuid_id_dto_rel__many_to_many_maps_id",
        joinColumns = @JoinColumn(name = "entity_uuid_id_dto_rel_id"),
        inverseJoinColumns = @JoinColumn(name = "many_to_many_maps_id_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "entityUuidIdDTO", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    private Set<EntityUuidIdDTOMapsId> manyToManyMapsIds = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public UUID getId() {
        return this.id;
    }

    public EntityUuidIdDTORel id(UUID id) {
        this.setId(id);
        return this;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public EntityUuidIdDTO getOneToOne() {
        return this.oneToOne;
    }

    public void setOneToOne(EntityUuidIdDTO entityUuidIdDTO) {
        this.oneToOne = entityUuidIdDTO;
    }

    public EntityUuidIdDTORel oneToOne(EntityUuidIdDTO entityUuidIdDTO) {
        this.setOneToOne(entityUuidIdDTO);
        return this;
    }

    public EntityUuidIdDTOMapsId getOneToOneMapsId() {
        return this.oneToOneMapsId;
    }

    public void setOneToOneMapsId(EntityUuidIdDTOMapsId entityUuidIdDTOMapsId) {
        this.oneToOneMapsId = entityUuidIdDTOMapsId;
    }

    public EntityUuidIdDTORel oneToOneMapsId(EntityUuidIdDTOMapsId entityUuidIdDTOMapsId) {
        this.setOneToOneMapsId(entityUuidIdDTOMapsId);
        return this;
    }

    public EntityUuidIdDTO getManyToOne() {
        return this.manyToOne;
    }

    public void setManyToOne(EntityUuidIdDTO entityUuidIdDTO) {
        this.manyToOne = entityUuidIdDTO;
    }

    public EntityUuidIdDTORel manyToOne(EntityUuidIdDTO entityUuidIdDTO) {
        this.setManyToOne(entityUuidIdDTO);
        return this;
    }

    public EntityUuidIdDTOMapsId getManyToOneMapsId() {
        return this.manyToOneMapsId;
    }

    public void setManyToOneMapsId(EntityUuidIdDTOMapsId entityUuidIdDTOMapsId) {
        this.manyToOneMapsId = entityUuidIdDTOMapsId;
    }

    public EntityUuidIdDTORel manyToOneMapsId(EntityUuidIdDTOMapsId entityUuidIdDTOMapsId) {
        this.setManyToOneMapsId(entityUuidIdDTOMapsId);
        return this;
    }

    public Set<EntityUuidIdDTO> getManyToManies() {
        return this.manyToManies;
    }

    public void setManyToManies(Set<EntityUuidIdDTO> entityUuidIdDTOS) {
        this.manyToManies = entityUuidIdDTOS;
    }

    public EntityUuidIdDTORel manyToManies(Set<EntityUuidIdDTO> entityUuidIdDTOS) {
        this.setManyToManies(entityUuidIdDTOS);
        return this;
    }

    public EntityUuidIdDTORel addManyToMany(EntityUuidIdDTO entityUuidIdDTO) {
        this.manyToManies.add(entityUuidIdDTO);
        return this;
    }

    public EntityUuidIdDTORel removeManyToMany(EntityUuidIdDTO entityUuidIdDTO) {
        this.manyToManies.remove(entityUuidIdDTO);
        return this;
    }

    public Set<EntityUuidIdDTOMapsId> getManyToManyMapsIds() {
        return this.manyToManyMapsIds;
    }

    public void setManyToManyMapsIds(Set<EntityUuidIdDTOMapsId> entityUuidIdDTOMapsIds) {
        this.manyToManyMapsIds = entityUuidIdDTOMapsIds;
    }

    public EntityUuidIdDTORel manyToManyMapsIds(Set<EntityUuidIdDTOMapsId> entityUuidIdDTOMapsIds) {
        this.setManyToManyMapsIds(entityUuidIdDTOMapsIds);
        return this;
    }

    public EntityUuidIdDTORel addManyToManyMapsId(EntityUuidIdDTOMapsId entityUuidIdDTOMapsId) {
        this.manyToManyMapsIds.add(entityUuidIdDTOMapsId);
        return this;
    }

    public EntityUuidIdDTORel removeManyToManyMapsId(EntityUuidIdDTOMapsId entityUuidIdDTOMapsId) {
        this.manyToManyMapsIds.remove(entityUuidIdDTOMapsId);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityUuidIdDTORel)) {
            return false;
        }
        return getId() != null && getId().equals(((EntityUuidIdDTORel) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityUuidIdDTORel{" +
            "id=" + getId() +
            "}";
    }
}
