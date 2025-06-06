package tech.jhipster.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EntityCustomIdRequiredDTOMapsId.
 */
@Entity
@Table(name = "entity_custom_id_required_dto_maps_id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityCustomIdRequiredDTOMapsId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "custom_id")
    private Long customId;

    @JsonIgnoreProperties(
        value = { "entityCustomIdRequiredDTOMapsId", "oneToOneBack", "manyToOneBacks", "manyToManyBacks" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "custom_id")
    private EntityCustomIdRequiredDTO entityCustomIdRequiredDTO;

    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "oneToOneMapsId")
    private EntityCustomIdRequiredDTORel oneToOneMapsIdBack;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manyToOneMapsId")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<EntityCustomIdRequiredDTORel> manyToOneMapsIdBacks = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "manyToManyMapsIds")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<EntityCustomIdRequiredDTORel> manyToManyMapsIdBacks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getCustomId() {
        return this.customId;
    }

    public EntityCustomIdRequiredDTOMapsId customId(Long customId) {
        this.setCustomId(customId);
        return this;
    }

    public void setCustomId(Long customId) {
        this.customId = customId;
    }

    public EntityCustomIdRequiredDTO getEntityCustomIdRequiredDTO() {
        return this.entityCustomIdRequiredDTO;
    }

    public void setEntityCustomIdRequiredDTO(EntityCustomIdRequiredDTO entityCustomIdRequiredDTO) {
        this.entityCustomIdRequiredDTO = entityCustomIdRequiredDTO;
    }

    public EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTO(EntityCustomIdRequiredDTO entityCustomIdRequiredDTO) {
        this.setEntityCustomIdRequiredDTO(entityCustomIdRequiredDTO);
        return this;
    }

    public EntityCustomIdRequiredDTORel getOneToOneMapsIdBack() {
        return this.oneToOneMapsIdBack;
    }

    public void setOneToOneMapsIdBack(EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel) {
        if (this.oneToOneMapsIdBack != null) {
            this.oneToOneMapsIdBack.setOneToOneMapsId(null);
        }
        if (entityCustomIdRequiredDTORel != null) {
            entityCustomIdRequiredDTORel.setOneToOneMapsId(this);
        }
        this.oneToOneMapsIdBack = entityCustomIdRequiredDTORel;
    }

    public EntityCustomIdRequiredDTOMapsId oneToOneMapsIdBack(EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel) {
        this.setOneToOneMapsIdBack(entityCustomIdRequiredDTORel);
        return this;
    }

    public Set<EntityCustomIdRequiredDTORel> getManyToOneMapsIdBacks() {
        return this.manyToOneMapsIdBacks;
    }

    public void setManyToOneMapsIdBacks(Set<EntityCustomIdRequiredDTORel> entityCustomIdRequiredDTORels) {
        if (this.manyToOneMapsIdBacks != null) {
            this.manyToOneMapsIdBacks.forEach(i -> i.setManyToOneMapsId(null));
        }
        if (entityCustomIdRequiredDTORels != null) {
            entityCustomIdRequiredDTORels.forEach(i -> i.setManyToOneMapsId(this));
        }
        this.manyToOneMapsIdBacks = entityCustomIdRequiredDTORels;
    }

    public EntityCustomIdRequiredDTOMapsId manyToOneMapsIdBacks(Set<EntityCustomIdRequiredDTORel> entityCustomIdRequiredDTORels) {
        this.setManyToOneMapsIdBacks(entityCustomIdRequiredDTORels);
        return this;
    }

    public EntityCustomIdRequiredDTOMapsId addManyToOneMapsIdBack(EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel) {
        this.manyToOneMapsIdBacks.add(entityCustomIdRequiredDTORel);
        entityCustomIdRequiredDTORel.setManyToOneMapsId(this);
        return this;
    }

    public EntityCustomIdRequiredDTOMapsId removeManyToOneMapsIdBack(EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel) {
        this.manyToOneMapsIdBacks.remove(entityCustomIdRequiredDTORel);
        entityCustomIdRequiredDTORel.setManyToOneMapsId(null);
        return this;
    }

    public Set<EntityCustomIdRequiredDTORel> getManyToManyMapsIdBacks() {
        return this.manyToManyMapsIdBacks;
    }

    public void setManyToManyMapsIdBacks(Set<EntityCustomIdRequiredDTORel> entityCustomIdRequiredDTORels) {
        if (this.manyToManyMapsIdBacks != null) {
            this.manyToManyMapsIdBacks.forEach(i -> i.removeManyToManyMapsId(this));
        }
        if (entityCustomIdRequiredDTORels != null) {
            entityCustomIdRequiredDTORels.forEach(i -> i.addManyToManyMapsId(this));
        }
        this.manyToManyMapsIdBacks = entityCustomIdRequiredDTORels;
    }

    public EntityCustomIdRequiredDTOMapsId manyToManyMapsIdBacks(Set<EntityCustomIdRequiredDTORel> entityCustomIdRequiredDTORels) {
        this.setManyToManyMapsIdBacks(entityCustomIdRequiredDTORels);
        return this;
    }

    public EntityCustomIdRequiredDTOMapsId addManyToManyMapsIdBack(EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel) {
        this.manyToManyMapsIdBacks.add(entityCustomIdRequiredDTORel);
        entityCustomIdRequiredDTORel.getManyToManyMapsIds().add(this);
        return this;
    }

    public EntityCustomIdRequiredDTOMapsId removeManyToManyMapsIdBack(EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel) {
        this.manyToManyMapsIdBacks.remove(entityCustomIdRequiredDTORel);
        entityCustomIdRequiredDTORel.getManyToManyMapsIds().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityCustomIdRequiredDTOMapsId)) {
            return false;
        }
        return getCustomId() != null && getCustomId().equals(((EntityCustomIdRequiredDTOMapsId) o).getCustomId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityCustomIdRequiredDTOMapsId{" +
            "customId=" + getCustomId() +
            "}";
    }
}
