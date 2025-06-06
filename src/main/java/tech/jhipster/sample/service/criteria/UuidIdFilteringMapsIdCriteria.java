package tech.jhipster.sample.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link tech.jhipster.sample.domain.UuidIdFilteringMapsId} entity. This class is used
 * in {@link tech.jhipster.sample.web.rest.UuidIdFilteringMapsIdResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /uuid-id-filtering-maps-ids?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UuidIdFilteringMapsIdCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private UUIDFilter customId;

    private UUIDFilter uuidIdFilteringId;

    private UUIDFilter oneToOneMapsIdBackId;

    private UUIDFilter manyToOneMapsIdBackId;

    private UUIDFilter manyToManyMapsIdBackId;

    private Boolean distinct;

    public UuidIdFilteringMapsIdCriteria() {}

    public UuidIdFilteringMapsIdCriteria(UuidIdFilteringMapsIdCriteria other) {
        this.customId = other.optionalCustomId().map(UUIDFilter::copy).orElse(null);
        this.uuidIdFilteringId = other.optionalUuidIdFilteringId().map(UUIDFilter::copy).orElse(null);
        this.oneToOneMapsIdBackId = other.optionalOneToOneMapsIdBackId().map(UUIDFilter::copy).orElse(null);
        this.manyToOneMapsIdBackId = other.optionalManyToOneMapsIdBackId().map(UUIDFilter::copy).orElse(null);
        this.manyToManyMapsIdBackId = other.optionalManyToManyMapsIdBackId().map(UUIDFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public UuidIdFilteringMapsIdCriteria copy() {
        return new UuidIdFilteringMapsIdCriteria(this);
    }

    public UUIDFilter getCustomId() {
        return customId;
    }

    public Optional<UUIDFilter> optionalCustomId() {
        return Optional.ofNullable(customId);
    }

    public UUIDFilter customId() {
        if (customId == null) {
            setCustomId(new UUIDFilter());
        }
        return customId;
    }

    public void setCustomId(UUIDFilter customId) {
        this.customId = customId;
    }

    public UUIDFilter getUuidIdFilteringId() {
        return uuidIdFilteringId;
    }

    public Optional<UUIDFilter> optionalUuidIdFilteringId() {
        return Optional.ofNullable(uuidIdFilteringId);
    }

    public UUIDFilter uuidIdFilteringId() {
        if (uuidIdFilteringId == null) {
            setUuidIdFilteringId(new UUIDFilter());
        }
        return uuidIdFilteringId;
    }

    public void setUuidIdFilteringId(UUIDFilter uuidIdFilteringId) {
        this.uuidIdFilteringId = uuidIdFilteringId;
    }

    public UUIDFilter getOneToOneMapsIdBackId() {
        return oneToOneMapsIdBackId;
    }

    public Optional<UUIDFilter> optionalOneToOneMapsIdBackId() {
        return Optional.ofNullable(oneToOneMapsIdBackId);
    }

    public UUIDFilter oneToOneMapsIdBackId() {
        if (oneToOneMapsIdBackId == null) {
            setOneToOneMapsIdBackId(new UUIDFilter());
        }
        return oneToOneMapsIdBackId;
    }

    public void setOneToOneMapsIdBackId(UUIDFilter oneToOneMapsIdBackId) {
        this.oneToOneMapsIdBackId = oneToOneMapsIdBackId;
    }

    public UUIDFilter getManyToOneMapsIdBackId() {
        return manyToOneMapsIdBackId;
    }

    public Optional<UUIDFilter> optionalManyToOneMapsIdBackId() {
        return Optional.ofNullable(manyToOneMapsIdBackId);
    }

    public UUIDFilter manyToOneMapsIdBackId() {
        if (manyToOneMapsIdBackId == null) {
            setManyToOneMapsIdBackId(new UUIDFilter());
        }
        return manyToOneMapsIdBackId;
    }

    public void setManyToOneMapsIdBackId(UUIDFilter manyToOneMapsIdBackId) {
        this.manyToOneMapsIdBackId = manyToOneMapsIdBackId;
    }

    public UUIDFilter getManyToManyMapsIdBackId() {
        return manyToManyMapsIdBackId;
    }

    public Optional<UUIDFilter> optionalManyToManyMapsIdBackId() {
        return Optional.ofNullable(manyToManyMapsIdBackId);
    }

    public UUIDFilter manyToManyMapsIdBackId() {
        if (manyToManyMapsIdBackId == null) {
            setManyToManyMapsIdBackId(new UUIDFilter());
        }
        return manyToManyMapsIdBackId;
    }

    public void setManyToManyMapsIdBackId(UUIDFilter manyToManyMapsIdBackId) {
        this.manyToManyMapsIdBackId = manyToManyMapsIdBackId;
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
        final UuidIdFilteringMapsIdCriteria that = (UuidIdFilteringMapsIdCriteria) o;
        return (
            Objects.equals(customId, that.customId) &&
            Objects.equals(uuidIdFilteringId, that.uuidIdFilteringId) &&
            Objects.equals(oneToOneMapsIdBackId, that.oneToOneMapsIdBackId) &&
            Objects.equals(manyToOneMapsIdBackId, that.manyToOneMapsIdBackId) &&
            Objects.equals(manyToManyMapsIdBackId, that.manyToManyMapsIdBackId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(customId, uuidIdFilteringId, oneToOneMapsIdBackId, manyToOneMapsIdBackId, manyToManyMapsIdBackId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UuidIdFilteringMapsIdCriteria{" +
            optionalCustomId().map(f -> "customId=" + f + ", ").orElse("") +
            optionalUuidIdFilteringId().map(f -> "uuidIdFilteringId=" + f + ", ").orElse("") +
            optionalOneToOneMapsIdBackId().map(f -> "oneToOneMapsIdBackId=" + f + ", ").orElse("") +
            optionalManyToOneMapsIdBackId().map(f -> "manyToOneMapsIdBackId=" + f + ", ").orElse("") +
            optionalManyToManyMapsIdBackId().map(f -> "manyToManyMapsIdBackId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
