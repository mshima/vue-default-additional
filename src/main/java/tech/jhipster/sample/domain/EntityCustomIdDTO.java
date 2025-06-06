package tech.jhipster.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EntityCustomIdDTO.
 */
@Entity
@Table(name = "entity_custom_id_dto")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityCustomIdDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "custom_id")
    private Long customId;

    @JsonIgnoreProperties(
        value = { "entityCustomIdDTO", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "entityCustomIdDTO")
    private EntityCustomIdDTOMapsId entityCustomIdDTOMapsId;

    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "oneToOne")
    private EntityCustomIdDTORel oneToOneBack;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manyToOne")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<EntityCustomIdDTORel> manyToOneBacks = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "manyToManies")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<EntityCustomIdDTORel> manyToManyBacks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getCustomId() {
        return this.customId;
    }

    public EntityCustomIdDTO customId(Long customId) {
        this.setCustomId(customId);
        return this;
    }

    public void setCustomId(Long customId) {
        this.customId = customId;
    }

    public EntityCustomIdDTOMapsId getEntityCustomIdDTOMapsId() {
        return this.entityCustomIdDTOMapsId;
    }

    public void setEntityCustomIdDTOMapsId(EntityCustomIdDTOMapsId entityCustomIdDTOMapsId) {
        if (this.entityCustomIdDTOMapsId != null) {
            this.entityCustomIdDTOMapsId.setEntityCustomIdDTO(null);
        }
        if (entityCustomIdDTOMapsId != null) {
            entityCustomIdDTOMapsId.setEntityCustomIdDTO(this);
        }
        this.entityCustomIdDTOMapsId = entityCustomIdDTOMapsId;
    }

    public EntityCustomIdDTO entityCustomIdDTOMapsId(EntityCustomIdDTOMapsId entityCustomIdDTOMapsId) {
        this.setEntityCustomIdDTOMapsId(entityCustomIdDTOMapsId);
        return this;
    }

    public EntityCustomIdDTORel getOneToOneBack() {
        return this.oneToOneBack;
    }

    public void setOneToOneBack(EntityCustomIdDTORel entityCustomIdDTORel) {
        if (this.oneToOneBack != null) {
            this.oneToOneBack.setOneToOne(null);
        }
        if (entityCustomIdDTORel != null) {
            entityCustomIdDTORel.setOneToOne(this);
        }
        this.oneToOneBack = entityCustomIdDTORel;
    }

    public EntityCustomIdDTO oneToOneBack(EntityCustomIdDTORel entityCustomIdDTORel) {
        this.setOneToOneBack(entityCustomIdDTORel);
        return this;
    }

    public Set<EntityCustomIdDTORel> getManyToOneBacks() {
        return this.manyToOneBacks;
    }

    public void setManyToOneBacks(Set<EntityCustomIdDTORel> entityCustomIdDTORels) {
        if (this.manyToOneBacks != null) {
            this.manyToOneBacks.forEach(i -> i.setManyToOne(null));
        }
        if (entityCustomIdDTORels != null) {
            entityCustomIdDTORels.forEach(i -> i.setManyToOne(this));
        }
        this.manyToOneBacks = entityCustomIdDTORels;
    }

    public EntityCustomIdDTO manyToOneBacks(Set<EntityCustomIdDTORel> entityCustomIdDTORels) {
        this.setManyToOneBacks(entityCustomIdDTORels);
        return this;
    }

    public EntityCustomIdDTO addManyToOneBack(EntityCustomIdDTORel entityCustomIdDTORel) {
        this.manyToOneBacks.add(entityCustomIdDTORel);
        entityCustomIdDTORel.setManyToOne(this);
        return this;
    }

    public EntityCustomIdDTO removeManyToOneBack(EntityCustomIdDTORel entityCustomIdDTORel) {
        this.manyToOneBacks.remove(entityCustomIdDTORel);
        entityCustomIdDTORel.setManyToOne(null);
        return this;
    }

    public Set<EntityCustomIdDTORel> getManyToManyBacks() {
        return this.manyToManyBacks;
    }

    public void setManyToManyBacks(Set<EntityCustomIdDTORel> entityCustomIdDTORels) {
        if (this.manyToManyBacks != null) {
            this.manyToManyBacks.forEach(i -> i.removeManyToMany(this));
        }
        if (entityCustomIdDTORels != null) {
            entityCustomIdDTORels.forEach(i -> i.addManyToMany(this));
        }
        this.manyToManyBacks = entityCustomIdDTORels;
    }

    public EntityCustomIdDTO manyToManyBacks(Set<EntityCustomIdDTORel> entityCustomIdDTORels) {
        this.setManyToManyBacks(entityCustomIdDTORels);
        return this;
    }

    public EntityCustomIdDTO addManyToManyBack(EntityCustomIdDTORel entityCustomIdDTORel) {
        this.manyToManyBacks.add(entityCustomIdDTORel);
        entityCustomIdDTORel.getManyToManies().add(this);
        return this;
    }

    public EntityCustomIdDTO removeManyToManyBack(EntityCustomIdDTORel entityCustomIdDTORel) {
        this.manyToManyBacks.remove(entityCustomIdDTORel);
        entityCustomIdDTORel.getManyToManies().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityCustomIdDTO)) {
            return false;
        }
        return getCustomId() != null && getCustomId().equals(((EntityCustomIdDTO) o).getCustomId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityCustomIdDTO{" +
            "customId=" + getCustomId() +
            "}";
    }
}
