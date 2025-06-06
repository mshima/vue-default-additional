package tech.jhipster.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EntityCustomIdDTOMapsId.
 */
@Entity
@Table(name = "entity_custom_id_dto_maps_id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityCustomIdDTOMapsId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "custom_id")
    private Long customId;

    @JsonIgnoreProperties(value = { "entityCustomIdDTOMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "custom_id")
    private EntityCustomIdDTO entityCustomIdDTO;

    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "oneToOneMapsId")
    private EntityCustomIdDTORel oneToOneMapsIdBack;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manyToOneMapsId")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<EntityCustomIdDTORel> manyToOneMapsIdBacks = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "manyToManyMapsIds")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<EntityCustomIdDTORel> manyToManyMapsIdBacks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getCustomId() {
        return this.customId;
    }

    public EntityCustomIdDTOMapsId customId(Long customId) {
        this.setCustomId(customId);
        return this;
    }

    public void setCustomId(Long customId) {
        this.customId = customId;
    }

    public EntityCustomIdDTO getEntityCustomIdDTO() {
        return this.entityCustomIdDTO;
    }

    public void setEntityCustomIdDTO(EntityCustomIdDTO entityCustomIdDTO) {
        this.entityCustomIdDTO = entityCustomIdDTO;
    }

    public EntityCustomIdDTOMapsId entityCustomIdDTO(EntityCustomIdDTO entityCustomIdDTO) {
        this.setEntityCustomIdDTO(entityCustomIdDTO);
        return this;
    }

    public EntityCustomIdDTORel getOneToOneMapsIdBack() {
        return this.oneToOneMapsIdBack;
    }

    public void setOneToOneMapsIdBack(EntityCustomIdDTORel entityCustomIdDTORel) {
        if (this.oneToOneMapsIdBack != null) {
            this.oneToOneMapsIdBack.setOneToOneMapsId(null);
        }
        if (entityCustomIdDTORel != null) {
            entityCustomIdDTORel.setOneToOneMapsId(this);
        }
        this.oneToOneMapsIdBack = entityCustomIdDTORel;
    }

    public EntityCustomIdDTOMapsId oneToOneMapsIdBack(EntityCustomIdDTORel entityCustomIdDTORel) {
        this.setOneToOneMapsIdBack(entityCustomIdDTORel);
        return this;
    }

    public Set<EntityCustomIdDTORel> getManyToOneMapsIdBacks() {
        return this.manyToOneMapsIdBacks;
    }

    public void setManyToOneMapsIdBacks(Set<EntityCustomIdDTORel> entityCustomIdDTORels) {
        if (this.manyToOneMapsIdBacks != null) {
            this.manyToOneMapsIdBacks.forEach(i -> i.setManyToOneMapsId(null));
        }
        if (entityCustomIdDTORels != null) {
            entityCustomIdDTORels.forEach(i -> i.setManyToOneMapsId(this));
        }
        this.manyToOneMapsIdBacks = entityCustomIdDTORels;
    }

    public EntityCustomIdDTOMapsId manyToOneMapsIdBacks(Set<EntityCustomIdDTORel> entityCustomIdDTORels) {
        this.setManyToOneMapsIdBacks(entityCustomIdDTORels);
        return this;
    }

    public EntityCustomIdDTOMapsId addManyToOneMapsIdBack(EntityCustomIdDTORel entityCustomIdDTORel) {
        this.manyToOneMapsIdBacks.add(entityCustomIdDTORel);
        entityCustomIdDTORel.setManyToOneMapsId(this);
        return this;
    }

    public EntityCustomIdDTOMapsId removeManyToOneMapsIdBack(EntityCustomIdDTORel entityCustomIdDTORel) {
        this.manyToOneMapsIdBacks.remove(entityCustomIdDTORel);
        entityCustomIdDTORel.setManyToOneMapsId(null);
        return this;
    }

    public Set<EntityCustomIdDTORel> getManyToManyMapsIdBacks() {
        return this.manyToManyMapsIdBacks;
    }

    public void setManyToManyMapsIdBacks(Set<EntityCustomIdDTORel> entityCustomIdDTORels) {
        if (this.manyToManyMapsIdBacks != null) {
            this.manyToManyMapsIdBacks.forEach(i -> i.removeManyToManyMapsId(this));
        }
        if (entityCustomIdDTORels != null) {
            entityCustomIdDTORels.forEach(i -> i.addManyToManyMapsId(this));
        }
        this.manyToManyMapsIdBacks = entityCustomIdDTORels;
    }

    public EntityCustomIdDTOMapsId manyToManyMapsIdBacks(Set<EntityCustomIdDTORel> entityCustomIdDTORels) {
        this.setManyToManyMapsIdBacks(entityCustomIdDTORels);
        return this;
    }

    public EntityCustomIdDTOMapsId addManyToManyMapsIdBack(EntityCustomIdDTORel entityCustomIdDTORel) {
        this.manyToManyMapsIdBacks.add(entityCustomIdDTORel);
        entityCustomIdDTORel.getManyToManyMapsIds().add(this);
        return this;
    }

    public EntityCustomIdDTOMapsId removeManyToManyMapsIdBack(EntityCustomIdDTORel entityCustomIdDTORel) {
        this.manyToManyMapsIdBacks.remove(entityCustomIdDTORel);
        entityCustomIdDTORel.getManyToManyMapsIds().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityCustomIdDTOMapsId)) {
            return false;
        }
        return getCustomId() != null && getCustomId().equals(((EntityCustomIdDTOMapsId) o).getCustomId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityCustomIdDTOMapsId{" +
            "customId=" + getCustomId() +
            "}";
    }
}
