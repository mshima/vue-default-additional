package tech.jhipster.sample.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link tech.jhipster.sample.domain.UuidIdFilteringRelationship} entity. This class is used
 * in {@link tech.jhipster.sample.web.rest.UuidIdFilteringRelationshipResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /uuid-id-filtering-relationships?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UuidIdFilteringRelationshipCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private UUIDFilter relatedId;

    private UUIDFilter oneToOneId;

    private UUIDFilter oneToOneMapsIdId;

    private UUIDFilter manyToOneId;

    private UUIDFilter manyToOneMapsIdId;

    private UUIDFilter manyToManyId;

    private UUIDFilter manyToManyMapsIdId;

    private Boolean distinct;

    public UuidIdFilteringRelationshipCriteria() {}

    public UuidIdFilteringRelationshipCriteria(UuidIdFilteringRelationshipCriteria other) {
        this.relatedId = other.optionalRelatedId().map(UUIDFilter::copy).orElse(null);
        this.oneToOneId = other.optionalOneToOneId().map(UUIDFilter::copy).orElse(null);
        this.oneToOneMapsIdId = other.optionalOneToOneMapsIdId().map(UUIDFilter::copy).orElse(null);
        this.manyToOneId = other.optionalManyToOneId().map(UUIDFilter::copy).orElse(null);
        this.manyToOneMapsIdId = other.optionalManyToOneMapsIdId().map(UUIDFilter::copy).orElse(null);
        this.manyToManyId = other.optionalManyToManyId().map(UUIDFilter::copy).orElse(null);
        this.manyToManyMapsIdId = other.optionalManyToManyMapsIdId().map(UUIDFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public UuidIdFilteringRelationshipCriteria copy() {
        return new UuidIdFilteringRelationshipCriteria(this);
    }

    public UUIDFilter getRelatedId() {
        return relatedId;
    }

    public Optional<UUIDFilter> optionalRelatedId() {
        return Optional.ofNullable(relatedId);
    }

    public UUIDFilter relatedId() {
        if (relatedId == null) {
            setRelatedId(new UUIDFilter());
        }
        return relatedId;
    }

    public void setRelatedId(UUIDFilter relatedId) {
        this.relatedId = relatedId;
    }

    public UUIDFilter getOneToOneId() {
        return oneToOneId;
    }

    public Optional<UUIDFilter> optionalOneToOneId() {
        return Optional.ofNullable(oneToOneId);
    }

    public UUIDFilter oneToOneId() {
        if (oneToOneId == null) {
            setOneToOneId(new UUIDFilter());
        }
        return oneToOneId;
    }

    public void setOneToOneId(UUIDFilter oneToOneId) {
        this.oneToOneId = oneToOneId;
    }

    public UUIDFilter getOneToOneMapsIdId() {
        return oneToOneMapsIdId;
    }

    public Optional<UUIDFilter> optionalOneToOneMapsIdId() {
        return Optional.ofNullable(oneToOneMapsIdId);
    }

    public UUIDFilter oneToOneMapsIdId() {
        if (oneToOneMapsIdId == null) {
            setOneToOneMapsIdId(new UUIDFilter());
        }
        return oneToOneMapsIdId;
    }

    public void setOneToOneMapsIdId(UUIDFilter oneToOneMapsIdId) {
        this.oneToOneMapsIdId = oneToOneMapsIdId;
    }

    public UUIDFilter getManyToOneId() {
        return manyToOneId;
    }

    public Optional<UUIDFilter> optionalManyToOneId() {
        return Optional.ofNullable(manyToOneId);
    }

    public UUIDFilter manyToOneId() {
        if (manyToOneId == null) {
            setManyToOneId(new UUIDFilter());
        }
        return manyToOneId;
    }

    public void setManyToOneId(UUIDFilter manyToOneId) {
        this.manyToOneId = manyToOneId;
    }

    public UUIDFilter getManyToOneMapsIdId() {
        return manyToOneMapsIdId;
    }

    public Optional<UUIDFilter> optionalManyToOneMapsIdId() {
        return Optional.ofNullable(manyToOneMapsIdId);
    }

    public UUIDFilter manyToOneMapsIdId() {
        if (manyToOneMapsIdId == null) {
            setManyToOneMapsIdId(new UUIDFilter());
        }
        return manyToOneMapsIdId;
    }

    public void setManyToOneMapsIdId(UUIDFilter manyToOneMapsIdId) {
        this.manyToOneMapsIdId = manyToOneMapsIdId;
    }

    public UUIDFilter getManyToManyId() {
        return manyToManyId;
    }

    public Optional<UUIDFilter> optionalManyToManyId() {
        return Optional.ofNullable(manyToManyId);
    }

    public UUIDFilter manyToManyId() {
        if (manyToManyId == null) {
            setManyToManyId(new UUIDFilter());
        }
        return manyToManyId;
    }

    public void setManyToManyId(UUIDFilter manyToManyId) {
        this.manyToManyId = manyToManyId;
    }

    public UUIDFilter getManyToManyMapsIdId() {
        return manyToManyMapsIdId;
    }

    public Optional<UUIDFilter> optionalManyToManyMapsIdId() {
        return Optional.ofNullable(manyToManyMapsIdId);
    }

    public UUIDFilter manyToManyMapsIdId() {
        if (manyToManyMapsIdId == null) {
            setManyToManyMapsIdId(new UUIDFilter());
        }
        return manyToManyMapsIdId;
    }

    public void setManyToManyMapsIdId(UUIDFilter manyToManyMapsIdId) {
        this.manyToManyMapsIdId = manyToManyMapsIdId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public Optional<Boolean> optionalDistinct() {
        return Optional.ofNullable(distinct);
    }

    public Boolean distinct() {
        if (distinct == null) {
            setDistinct(true);
        }
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final UuidIdFilteringRelationshipCriteria that = (UuidIdFilteringRelationshipCriteria) o;
        return (
            Objects.equals(relatedId, that.relatedId) &&
            Objects.equals(oneToOneId, that.oneToOneId) &&
            Objects.equals(oneToOneMapsIdId, that.oneToOneMapsIdId) &&
            Objects.equals(manyToOneId, that.manyToOneId) &&
            Objects.equals(manyToOneMapsIdId, that.manyToOneMapsIdId) &&
            Objects.equals(manyToManyId, that.manyToManyId) &&
            Objects.equals(manyToManyMapsIdId, that.manyToManyMapsIdId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            relatedId,
            oneToOneId,
            oneToOneMapsIdId,
            manyToOneId,
            manyToOneMapsIdId,
            manyToManyId,
            manyToManyMapsIdId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UuidIdFilteringRelationshipCriteria{" +
            optionalRelatedId().map(f -> "relatedId=" + f + ", ").orElse("") +
            optionalOneToOneId().map(f -> "oneToOneId=" + f + ", ").orElse("") +
            optionalOneToOneMapsIdId().map(f -> "oneToOneMapsIdId=" + f + ", ").orElse("") +
            optionalManyToOneId().map(f -> "manyToOneId=" + f + ", ").orElse("") +
            optionalManyToOneMapsIdId().map(f -> "manyToOneMapsIdId=" + f + ", ").orElse("") +
            optionalManyToManyId().map(f -> "manyToManyId=" + f + ", ").orElse("") +
            optionalManyToManyMapsIdId().map(f -> "manyToManyMapsIdId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
