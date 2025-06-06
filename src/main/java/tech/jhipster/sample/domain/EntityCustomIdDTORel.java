package tech.jhipster.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EntityCustomIdDTORel.
 */
@Entity
@Table(name = "entity_custom_id_dto_rel")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityCustomIdDTORel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "related_id")
    private Long relatedId;

    @JsonIgnoreProperties(value = { "entityCustomIdDTOMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private EntityCustomIdDTO oneToOne;

    @JsonIgnoreProperties(
        value = { "entityCustomIdDTO", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private EntityCustomIdDTOMapsId oneToOneMapsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "entityCustomIdDTOMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" }, allowSetters = true)
    private EntityCustomIdDTO manyToOne;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "entityCustomIdDTO", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    private EntityCustomIdDTOMapsId manyToOneMapsId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_entity_custom_id_dto_rel__many_to_many",
        joinColumns = @JoinColumn(name = "entity_custom_id_dto_rel_related_id"),
        inverseJoinColumns = @JoinColumn(name = "many_to_many_custom_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "entityCustomIdDTOMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" }, allowSetters = true)
    private Set<EntityCustomIdDTO> manyToManies = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_entity_custom_id_dto_rel__many_to_many_maps_id",
        joinColumns = @JoinColumn(name = "entity_custom_id_dto_rel_related_id"),
        inverseJoinColumns = @JoinColumn(name = "many_to_many_maps_id_custom_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "entityCustomIdDTO", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    private Set<EntityCustomIdDTOMapsId> manyToManyMapsIds = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getRelatedId() {
        return this.relatedId;
    }

    public EntityCustomIdDTORel relatedId(Long relatedId) {
        this.setRelatedId(relatedId);
        return this;
    }

    public void setRelatedId(Long relatedId) {
        this.relatedId = relatedId;
    }

    public EntityCustomIdDTO getOneToOne() {
        return this.oneToOne;
    }

    public void setOneToOne(EntityCustomIdDTO entityCustomIdDTO) {
        this.oneToOne = entityCustomIdDTO;
    }

    public EntityCustomIdDTORel oneToOne(EntityCustomIdDTO entityCustomIdDTO) {
        this.setOneToOne(entityCustomIdDTO);
        return this;
    }

    public EntityCustomIdDTOMapsId getOneToOneMapsId() {
        return this.oneToOneMapsId;
    }

    public void setOneToOneMapsId(EntityCustomIdDTOMapsId entityCustomIdDTOMapsId) {
        this.oneToOneMapsId = entityCustomIdDTOMapsId;
    }

    public EntityCustomIdDTORel oneToOneMapsId(EntityCustomIdDTOMapsId entityCustomIdDTOMapsId) {
        this.setOneToOneMapsId(entityCustomIdDTOMapsId);
        return this;
    }

    public EntityCustomIdDTO getManyToOne() {
        return this.manyToOne;
    }

    public void setManyToOne(EntityCustomIdDTO entityCustomIdDTO) {
        this.manyToOne = entityCustomIdDTO;
    }

    public EntityCustomIdDTORel manyToOne(EntityCustomIdDTO entityCustomIdDTO) {
        this.setManyToOne(entityCustomIdDTO);
        return this;
    }

    public EntityCustomIdDTOMapsId getManyToOneMapsId() {
        return this.manyToOneMapsId;
    }

    public void setManyToOneMapsId(EntityCustomIdDTOMapsId entityCustomIdDTOMapsId) {
        this.manyToOneMapsId = entityCustomIdDTOMapsId;
    }

    public EntityCustomIdDTORel manyToOneMapsId(EntityCustomIdDTOMapsId entityCustomIdDTOMapsId) {
        this.setManyToOneMapsId(entityCustomIdDTOMapsId);
        return this;
    }

    public Set<EntityCustomIdDTO> getManyToManies() {
        return this.manyToManies;
    }

    public void setManyToManies(Set<EntityCustomIdDTO> entityCustomIdDTOS) {
        this.manyToManies = entityCustomIdDTOS;
    }

    public EntityCustomIdDTORel manyToManies(Set<EntityCustomIdDTO> entityCustomIdDTOS) {
        this.setManyToManies(entityCustomIdDTOS);
        return this;
    }

    public EntityCustomIdDTORel addManyToMany(EntityCustomIdDTO entityCustomIdDTO) {
        this.manyToManies.add(entityCustomIdDTO);
        return this;
    }

    public EntityCustomIdDTORel removeManyToMany(EntityCustomIdDTO entityCustomIdDTO) {
        this.manyToManies.remove(entityCustomIdDTO);
        return this;
    }

    public Set<EntityCustomIdDTOMapsId> getManyToManyMapsIds() {
        return this.manyToManyMapsIds;
    }

    public void setManyToManyMapsIds(Set<EntityCustomIdDTOMapsId> entityCustomIdDTOMapsIds) {
        this.manyToManyMapsIds = entityCustomIdDTOMapsIds;
    }

    public EntityCustomIdDTORel manyToManyMapsIds(Set<EntityCustomIdDTOMapsId> entityCustomIdDTOMapsIds) {
        this.setManyToManyMapsIds(entityCustomIdDTOMapsIds);
        return this;
    }

    public EntityCustomIdDTORel addManyToManyMapsId(EntityCustomIdDTOMapsId entityCustomIdDTOMapsId) {
        this.manyToManyMapsIds.add(entityCustomIdDTOMapsId);
        return this;
    }

    public EntityCustomIdDTORel removeManyToManyMapsId(EntityCustomIdDTOMapsId entityCustomIdDTOMapsId) {
        this.manyToManyMapsIds.remove(entityCustomIdDTOMapsId);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityCustomIdDTORel)) {
            return false;
        }
        return getRelatedId() != null && getRelatedId().equals(((EntityCustomIdDTORel) o).getRelatedId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityCustomIdDTORel{" +
            "relatedId=" + getRelatedId() +
            "}";
    }
}
