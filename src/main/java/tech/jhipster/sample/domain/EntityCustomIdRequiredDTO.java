package tech.jhipster.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EntityCustomIdRequiredDTO.
 */
@Entity
@Table(name = "entity_custom_id_required_dto")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EntityCustomIdRequiredDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "custom_id")
    private Long customId;

    @JsonIgnoreProperties(
        value = { "entityCustomIdRequiredDTO", "oneToOneMapsIdBack", "manyToOneMapsIdBacks", "manyToManyMapsIdBacks" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "entityCustomIdRequiredDTO")
    private EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsId;

    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "oneToOne")
    private EntityCustomIdRequiredDTORel oneToOneBack;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manyToOne")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<EntityCustomIdRequiredDTORel> manyToOneBacks = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "manyToManies")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "oneToOne", "oneToOneMapsId", "manyToOne", "manyToOneMapsId", "manyToManies", "manyToManyMapsIds" },
        allowSetters = true
    )
    private Set<EntityCustomIdRequiredDTORel> manyToManyBacks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getCustomId() {
        return this.customId;
    }

    public EntityCustomIdRequiredDTO customId(Long customId) {
        this.setCustomId(customId);
        return this;
    }

    public void setCustomId(Long customId) {
        this.customId = customId;
    }

    public EntityCustomIdRequiredDTOMapsId getEntityCustomIdRequiredDTOMapsId() {
        return this.entityCustomIdRequiredDTOMapsId;
    }

    public void setEntityCustomIdRequiredDTOMapsId(EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsId) {
        if (this.entityCustomIdRequiredDTOMapsId != null) {
            this.entityCustomIdRequiredDTOMapsId.setEntityCustomIdRequiredDTO(null);
        }
        if (entityCustomIdRequiredDTOMapsId != null) {
            entityCustomIdRequiredDTOMapsId.setEntityCustomIdRequiredDTO(this);
        }
        this.entityCustomIdRequiredDTOMapsId = entityCustomIdRequiredDTOMapsId;
    }

    public EntityCustomIdRequiredDTO entityCustomIdRequiredDTOMapsId(EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsId) {
        this.setEntityCustomIdRequiredDTOMapsId(entityCustomIdRequiredDTOMapsId);
        return this;
    }

    public EntityCustomIdRequiredDTORel getOneToOneBack() {
        return this.oneToOneBack;
    }

    public void setOneToOneBack(EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel) {
        if (this.oneToOneBack != null) {
            this.oneToOneBack.setOneToOne(null);
        }
        if (entityCustomIdRequiredDTORel != null) {
            entityCustomIdRequiredDTORel.setOneToOne(this);
        }
        this.oneToOneBack = entityCustomIdRequiredDTORel;
    }

    public EntityCustomIdRequiredDTO oneToOneBack(EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel) {
        this.setOneToOneBack(entityCustomIdRequiredDTORel);
        return this;
    }

    public Set<EntityCustomIdRequiredDTORel> getManyToOneBacks() {
        return this.manyToOneBacks;
    }

    public void setManyToOneBacks(Set<EntityCustomIdRequiredDTORel> entityCustomIdRequiredDTORels) {
        if (this.manyToOneBacks != null) {
            this.manyToOneBacks.forEach(i -> i.setManyToOne(null));
        }
        if (entityCustomIdRequiredDTORels != null) {
            entityCustomIdRequiredDTORels.forEach(i -> i.setManyToOne(this));
        }
        this.manyToOneBacks = entityCustomIdRequiredDTORels;
    }

    public EntityCustomIdRequiredDTO manyToOneBacks(Set<EntityCustomIdRequiredDTORel> entityCustomIdRequiredDTORels) {
        this.setManyToOneBacks(entityCustomIdRequiredDTORels);
        return this;
    }

    public EntityCustomIdRequiredDTO addManyToOneBack(EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel) {
        this.manyToOneBacks.add(entityCustomIdRequiredDTORel);
        entityCustomIdRequiredDTORel.setManyToOne(this);
        return this;
    }

    public EntityCustomIdRequiredDTO removeManyToOneBack(EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel) {
        this.manyToOneBacks.remove(entityCustomIdRequiredDTORel);
        entityCustomIdRequiredDTORel.setManyToOne(null);
        return this;
    }

    public Set<EntityCustomIdRequiredDTORel> getManyToManyBacks() {
        return this.manyToManyBacks;
    }

    public void setManyToManyBacks(Set<EntityCustomIdRequiredDTORel> entityCustomIdRequiredDTORels) {
        if (this.manyToManyBacks != null) {
            this.manyToManyBacks.forEach(i -> i.removeManyToMany(this));
        }
        if (entityCustomIdRequiredDTORels != null) {
            entityCustomIdRequiredDTORels.forEach(i -> i.addManyToMany(this));
        }
        this.manyToManyBacks = entityCustomIdRequiredDTORels;
    }

    public EntityCustomIdRequiredDTO manyToManyBacks(Set<EntityCustomIdRequiredDTORel> entityCustomIdRequiredDTORels) {
        this.setManyToManyBacks(entityCustomIdRequiredDTORels);
        return this;
    }

    public EntityCustomIdRequiredDTO addManyToManyBack(EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel) {
        this.manyToManyBacks.add(entityCustomIdRequiredDTORel);
        entityCustomIdRequiredDTORel.getManyToManies().add(this);
        return this;
    }

    public EntityCustomIdRequiredDTO removeManyToManyBack(EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel) {
        this.manyToManyBacks.remove(entityCustomIdRequiredDTORel);
        entityCustomIdRequiredDTORel.getManyToManies().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityCustomIdRequiredDTO)) {
            return false;
        }
        return getCustomId() != null && getCustomId().equals(((EntityCustomIdRequiredDTO) o).getCustomId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityCustomIdRequiredDTO{" +
            "customId=" + getCustomId() +
            "}";
    }
}
