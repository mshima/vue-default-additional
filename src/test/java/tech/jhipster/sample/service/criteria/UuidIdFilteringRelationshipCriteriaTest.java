package tech.jhipster.sample.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class UuidIdFilteringRelationshipCriteriaTest {

    @Test
    void newUuidIdFilteringRelationshipCriteriaHasAllFiltersNullTest() {
        var uuidIdFilteringRelationshipCriteria = new UuidIdFilteringRelationshipCriteria();
        assertThat(uuidIdFilteringRelationshipCriteria).is(criteriaFiltersAre(Objects::isNull));
    }

    @Test
    void uuidIdFilteringRelationshipCriteriaFluentMethodsCreatesFiltersTest() {
        var uuidIdFilteringRelationshipCriteria = new UuidIdFilteringRelationshipCriteria();

        setAllFilters(uuidIdFilteringRelationshipCriteria);

        assertThat(uuidIdFilteringRelationshipCriteria).is(criteriaFiltersAre(Objects::nonNull));
    }

    @Test
    void uuidIdFilteringRelationshipCriteriaCopyCreatesNullFilterTest() {
        var uuidIdFilteringRelationshipCriteria = new UuidIdFilteringRelationshipCriteria();
        var copy = uuidIdFilteringRelationshipCriteria.copy();

        assertThat(uuidIdFilteringRelationshipCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::isNull)),
            criteria -> assertThat(criteria).isEqualTo(uuidIdFilteringRelationshipCriteria)
        );
    }

    @Test
    void uuidIdFilteringRelationshipCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var uuidIdFilteringRelationshipCriteria = new UuidIdFilteringRelationshipCriteria();
        setAllFilters(uuidIdFilteringRelationshipCriteria);

        var copy = uuidIdFilteringRelationshipCriteria.copy();

        assertThat(uuidIdFilteringRelationshipCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::nonNull)),
            criteria -> assertThat(criteria).isEqualTo(uuidIdFilteringRelationshipCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var uuidIdFilteringRelationshipCriteria = new UuidIdFilteringRelationshipCriteria();

        assertThat(uuidIdFilteringRelationshipCriteria).hasToString("UuidIdFilteringRelationshipCriteria{}");
    }

    private static void setAllFilters(UuidIdFilteringRelationshipCriteria uuidIdFilteringRelationshipCriteria) {
        uuidIdFilteringRelationshipCriteria.relatedId();
        uuidIdFilteringRelationshipCriteria.oneToOneId();
        uuidIdFilteringRelationshipCriteria.oneToOneMapsIdId();
        uuidIdFilteringRelationshipCriteria.manyToOneId();
        uuidIdFilteringRelationshipCriteria.manyToOneMapsIdId();
        uuidIdFilteringRelationshipCriteria.manyToManyId();
        uuidIdFilteringRelationshipCriteria.manyToManyMapsIdId();
        uuidIdFilteringRelationshipCriteria.distinct();
    }

    private static Condition<UuidIdFilteringRelationshipCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getRelatedId()) &&
                condition.apply(criteria.getOneToOneId()) &&
                condition.apply(criteria.getOneToOneMapsIdId()) &&
                condition.apply(criteria.getManyToOneId()) &&
                condition.apply(criteria.getManyToOneMapsIdId()) &&
                condition.apply(criteria.getManyToManyId()) &&
                condition.apply(criteria.getManyToManyMapsIdId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<UuidIdFilteringRelationshipCriteria> copyFiltersAre(
        UuidIdFilteringRelationshipCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getRelatedId(), copy.getRelatedId()) &&
                condition.apply(criteria.getOneToOneId(), copy.getOneToOneId()) &&
                condition.apply(criteria.getOneToOneMapsIdId(), copy.getOneToOneMapsIdId()) &&
                condition.apply(criteria.getManyToOneId(), copy.getManyToOneId()) &&
                condition.apply(criteria.getManyToOneMapsIdId(), copy.getManyToOneMapsIdId()) &&
                condition.apply(criteria.getManyToManyId(), copy.getManyToManyId()) &&
                condition.apply(criteria.getManyToManyMapsIdId(), copy.getManyToManyMapsIdId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
