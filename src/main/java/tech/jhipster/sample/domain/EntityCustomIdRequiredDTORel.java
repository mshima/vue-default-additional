package tech.jhipster.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EntityCustomIdRequiredDTORel.
 */
@Entity
@Table(name = "entity_custom_id_required_dto_rel")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityCustomIdRequiredDTORel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "related_id")
    private Long relatedId;

    @JsonIgnoreProperties(
        value = { "entityCustomIdRequiredDTOMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private EntityCustomIdRequiredDTO oneToOne;

    @JsonIgnoreProperties(
        value = { "entityCustomIdRequiredDTO", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private EntityCustomIdRequiredDTOMapsId oneToOneMapsId;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(
        value = { "entityCustomIdRequiredDTOMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" },
        allowSetters = true
    )
    private EntityCustomIdRequiredDTO manyToOne;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(
        value = { "entityCustomIdRequiredDTO", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    private EntityCustomIdRequiredDTOMapsId manyToOneMapsId;

    @ManyToMany(fetch = FetchType.LAZY)
    @NotNull
    @JoinTable(
        name = "rel_entity_custom_id_required_dto_rel__many_to_many",
        joinColumns = @JoinColumn(name = "entity_custom_id_required_dto_rel_related_id"),
        inverseJoinColumns = @JoinColumn(name = "many_to_many_custom_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "entityCustomIdRequiredDTOMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" },
        allowSetters = true
    )
    private Set<EntityCustomIdRequiredDTO> manyToManies = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @NotNull
    @JoinTable(
        name = "rel_entity_custom_id_required_dto_rel__many_to_many_maps_id",
        joinColumns = @JoinColumn(name = "entity_custom_id_required_dto_rel_related_id"),
        inverseJoinColumns = @JoinColumn(name = "many_to_many_maps_id_custom_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "entityCustomIdRequiredDTO", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    private Set<EntityCustomIdRequiredDTOMapsId> manyToManyMapsIds = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getRelatedId() {
        return this.relatedId;
    }

    public EntityCustomIdRequiredDTORel relatedId(Long relatedId) {
        this.setRelatedId(relatedId);
        return this;
    }

    public void setRelatedId(Long relatedId) {
        this.relatedId = relatedId;
    }

    public EntityCustomIdRequiredDTO getOneToOne() {
        return this.oneToOne;
    }

    public void setOneToOne(EntityCustomIdRequiredDTO entityCustomIdRequiredDTO) {
        this.oneToOne = entityCustomIdRequiredDTO;
    }

    public EntityCustomIdRequiredDTORel oneToOne(EntityCustomIdRequiredDTO entityCustomIdRequiredDTO) {
        this.setOneToOne(entityCustomIdRequiredDTO);
        return this;
    }

    public EntityCustomIdRequiredDTOMapsId getOneToOneMapsId() {
        return this.oneToOneMapsId;
    }

    public void setOneToOneMapsId(EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsId) {
        this.oneToOneMapsId = entityCustomIdRequiredDTOMapsId;
    }

    public EntityCustomIdRequiredDTORel oneToOneMapsId(EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsId) {
        this.setOneToOneMapsId(entityCustomIdRequiredDTOMapsId);
        return this;
    }

    public EntityCustomIdRequiredDTO getManyToOne() {
        return this.manyToOne;
    }

    public void setManyToOne(EntityCustomIdRequiredDTO entityCustomIdRequiredDTO) {
        this.manyToOne = entityCustomIdRequiredDTO;
    }

    public EntityCustomIdRequiredDTORel manyToOne(EntityCustomIdRequiredDTO entityCustomIdRequiredDTO) {
        this.setManyToOne(entityCustomIdRequiredDTO);
        return this;
    }

    public EntityCustomIdRequiredDTOMapsId getManyToOneMapsId() {
        return this.manyToOneMapsId;
    }

    public void setManyToOneMapsId(EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsId) {
        this.manyToOneMapsId = entityCustomIdRequiredDTOMapsId;
    }

    public EntityCustomIdRequiredDTORel manyToOneMapsId(EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsId) {
        this.setManyToOneMapsId(entityCustomIdRequiredDTOMapsId);
        return this;
    }

    public Set<EntityCustomIdRequiredDTO> getManyToManies() {
        return this.manyToManies;
    }

    public void setManyToManies(Set<EntityCustomIdRequiredDTO> entityCustomIdRequiredDTOS) {
        this.manyToManies = entityCustomIdRequiredDTOS;
    }

    public EntityCustomIdRequiredDTORel manyToManies(Set<EntityCustomIdRequiredDTO> entityCustomIdRequiredDTOS) {
        this.setManyToManies(entityCustomIdRequiredDTOS);
        return this;
    }

    public EntityCustomIdRequiredDTORel addManyToMany(EntityCustomIdRequiredDTO entityCustomIdRequiredDTO) {
        this.manyToManies.add(entityCustomIdRequiredDTO);
        return this;
    }

    public EntityCustomIdRequiredDTORel removeManyToMany(EntityCustomIdRequiredDTO entityCustomIdRequiredDTO) {
        this.manyToManies.remove(entityCustomIdRequiredDTO);
        return this;
    }

    public Set<EntityCustomIdRequiredDTOMapsId> getManyToManyMapsIds() {
        return this.manyToManyMapsIds;
    }

    public void setManyToManyMapsIds(Set<EntityCustomIdRequiredDTOMapsId> entityCustomIdRequiredDTOMapsIds) {
        this.manyToManyMapsIds = entityCustomIdRequiredDTOMapsIds;
    }

    public EntityCustomIdRequiredDTORel manyToManyMapsIds(Set<EntityCustomIdRequiredDTOMapsId> entityCustomIdRequiredDTOMapsIds) {
        this.setManyToManyMapsIds(entityCustomIdRequiredDTOMapsIds);
        return this;
    }

    public EntityCustomIdRequiredDTORel addManyToManyMapsId(EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsId) {
        this.manyToManyMapsIds.add(entityCustomIdRequiredDTOMapsId);
        return this;
    }

    public EntityCustomIdRequiredDTORel removeManyToManyMapsId(EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsId) {
        this.manyToManyMapsIds.remove(entityCustomIdRequiredDTOMapsId);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityCustomIdRequiredDTORel)) {
            return false;
        }
        return getRelatedId() != null && getRelatedId().equals(((EntityCustomIdRequiredDTORel) o).getRelatedId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityCustomIdRequiredDTORel{" +
            "relatedId=" + getRelatedId() +
            "}";
    }
}
