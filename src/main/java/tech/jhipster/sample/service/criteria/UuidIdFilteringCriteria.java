package tech.jhipster.sample.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link tech.jhipster.sample.domain.UuidIdFiltering} entity. This class is used
 * in {@link tech.jhipster.sample.web.rest.UuidIdFilteringResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /uuid-id-filterings?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UuidIdFilteringCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private UUIDFilter customId;

    private UUIDFilter uuidIdFilteringMapsIdId;

    private UUIDFilter oneToOneBackId;

    private UUIDFilter manyToOneBackId;

    private UUIDFilter manyToManyBackId;

    private Boolean distinct;

    public UuidIdFilteringCriteria() {}

    public UuidIdFilteringCriteria(UuidIdFilteringCriteria other) {
        this.customId = other.optionalCustomId().map(UUIDFilter::copy).orElse(null);
        this.uuidIdFilteringMapsIdId = other.optionalUuidIdFilteringMapsIdId().map(UUIDFilter::copy).orElse(null);
        this.oneToOneBackId = other.optionalOneToOneBackId().map(UUIDFilter::copy).orElse(null);
        this.manyToOneBackId = other.optionalManyToOneBackId().map(UUIDFilter::copy).orElse(null);
        this.manyToManyBackId = other.optionalManyToManyBackId().map(UUIDFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public UuidIdFilteringCriteria copy() {
        return new UuidIdFilteringCriteria(this);
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

    public UUIDFilter getUuidIdFilteringMapsIdId() {
        return uuidIdFilteringMapsIdId;
    }

    public Optional<UUIDFilter> optionalUuidIdFilteringMapsIdId() {
        return Optional.ofNullable(uuidIdFilteringMapsIdId);
    }

    public UUIDFilter uuidIdFilteringMapsIdId() {
        if (uuidIdFilteringMapsIdId == null) {
            setUuidIdFilteringMapsIdId(new UUIDFilter());
        }
        return uuidIdFilteringMapsIdId;
    }

    public void setUuidIdFilteringMapsIdId(UUIDFilter uuidIdFilteringMapsIdId) {
        this.uuidIdFilteringMapsIdId = uuidIdFilteringMapsIdId;
    }

    public UUIDFilter getOneToOneBackId() {
        return oneToOneBackId;
    }

    public Optional<UUIDFilter> optionalOneToOneBackId() {
        return Optional.ofNullable(oneToOneBackId);
    }

    public UUIDFilter oneToOneBackId() {
        if (oneToOneBackId == null) {
            setOneToOneBackId(new UUIDFilter());
        }
        return oneToOneBackId;
    }

    public void setOneToOneBackId(UUIDFilter oneToOneBackId) {
        this.oneToOneBackId = oneToOneBackId;
    }

    public UUIDFilter getManyToOneBackId() {
        return manyToOneBackId;
    }

    public Optional<UUIDFilter> optionalManyToOneBackId() {
        return Optional.ofNullable(manyToOneBackId);
    }

    public UUIDFilter manyToOneBackId() {
        if (manyToOneBackId == null) {
            setManyToOneBackId(new UUIDFilter());
        }
        return manyToOneBackId;
    }

    public void setManyToOneBackId(UUIDFilter manyToOneBackId) {
        this.manyToOneBackId = manyToOneBackId;
    }

    public UUIDFilter getManyToManyBackId() {
        return manyToManyBackId;
    }

    public Optional<UUIDFilter> optionalManyToManyBackId() {
        return Optional.ofNullable(manyToManyBackId);
    }

    public UUIDFilter manyToManyBackId() {
        if (manyToManyBackId == null) {
            setManyToManyBackId(new UUIDFilter());
        }
        return manyToManyBackId;
    }

    public void setManyToManyBackId(UUIDFilter manyToManyBackId) {
        this.manyToManyBackId = manyToManyBackId;
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
        final UuidIdFilteringCriteria that = (UuidIdFilteringCriteria) o;
        return (
            Objects.equals(customId, that.customId) &&
            Objects.equals(uuidIdFilteringMapsIdId, that.uuidIdFilteringMapsIdId) &&
            Objects.equals(oneToOneBackId, that.oneToOneBackId) &&
            Objects.equals(manyToOneBackId, that.manyToOneBackId) &&
            Objects.equals(manyToManyBackId, that.manyToManyBackId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(customId, uuidIdFilteringMapsIdId, oneToOneBackId, manyToOneBackId, manyToManyBackId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UuidIdFilteringCriteria{" +
            optionalCustomId().map(f -> "customId=" + f + ", ").orElse("") +
            optionalUuidIdFilteringMapsIdId().map(f -> "uuidIdFilteringMapsIdId=" + f + ", ").orElse("") +
            optionalOneToOneBackId().map(f -> "oneToOneBackId=" + f + ", ").orElse("") +
            optionalManyToOneBackId().map(f -> "manyToOneBackId=" + f + ", ").orElse("") +
            optionalManyToManyBackId().map(f -> "manyToManyBackId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
