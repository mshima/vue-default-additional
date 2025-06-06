package tech.jhipster.sample.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class UuidIdFilteringMapsIdCriteriaTest {

    @Test
    void newUuidIdFilteringMapsIdCriteriaHasAllFiltersNullTest() {
        var uuidIdFilteringMapsIdCriteria = new UuidIdFilteringMapsIdCriteria();
        assertThat(uuidIdFilteringMapsIdCriteria).is(criteriaFiltersAre(Objects::isNull));
    }

    @Test
    void uuidIdFilteringMapsIdCriteriaFluentMethodsCreatesFiltersTest() {
        var uuidIdFilteringMapsIdCriteria = new UuidIdFilteringMapsIdCriteria();

        setAllFilters(uuidIdFilteringMapsIdCriteria);

        assertThat(uuidIdFilteringMapsIdCriteria).is(criteriaFiltersAre(Objects::nonNull));
    }

    @Test
    void uuidIdFilteringMapsIdCriteriaCopyCreatesNullFilterTest() {
        var uuidIdFilteringMapsIdCriteria = new UuidIdFilteringMapsIdCriteria();
        var copy = uuidIdFilteringMapsIdCriteria.copy();

        assertThat(uuidIdFilteringMapsIdCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::isNull)),
            criteria -> assertThat(criteria).isEqualTo(uuidIdFilteringMapsIdCriteria)
        );
    }

    @Test
    void uuidIdFilteringMapsIdCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var uuidIdFilteringMapsIdCriteria = new UuidIdFilteringMapsIdCriteria();
        setAllFilters(uuidIdFilteringMapsIdCriteria);

        var copy = uuidIdFilteringMapsIdCriteria.copy();

        assertThat(uuidIdFilteringMapsIdCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::nonNull)),
            criteria -> assertThat(criteria).isEqualTo(uuidIdFilteringMapsIdCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var uuidIdFilteringMapsIdCriteria = new UuidIdFilteringMapsIdCriteria();

        assertThat(uuidIdFilteringMapsIdCriteria).hasToString("UuidIdFilteringMapsIdCriteria{}");
    }

    private static void setAllFilters(UuidIdFilteringMapsIdCriteria uuidIdFilteringMapsIdCriteria) {
        uuidIdFilteringMapsIdCriteria.customId();
        uuidIdFilteringMapsIdCriteria.uuidIdFilteringId();
        uuidIdFilteringMapsIdCriteria.oneToOneMapsIdBackId();
        uuidIdFilteringMapsIdCriteria.manyToOneMapsIdBackId();
        uuidIdFilteringMapsIdCriteria.manyToManyMapsIdBackId();
        uuidIdFilteringMapsIdCriteria.distinct();
    }

    private static Condition<UuidIdFilteringMapsIdCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getCustomId()) &&
                condition.apply(criteria.getUuidIdFilteringId()) &&
                condition.apply(criteria.getOneToOneMapsIdBackId()) &&
                condition.apply(criteria.getManyToOneMapsIdBackId()) &&
                condition.apply(criteria.getManyToManyMapsIdBackId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<UuidIdFilteringMapsIdCriteria> copyFiltersAre(
        UuidIdFilteringMapsIdCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getCustomId(), copy.getCustomId()) &&
                condition.apply(criteria.getUuidIdFilteringId(), copy.getUuidIdFilteringId()) &&
                condition.apply(criteria.getOneToOneMapsIdBackId(), copy.getOneToOneMapsIdBackId()) &&
                condition.apply(criteria.getManyToOneMapsIdBackId(), copy.getManyToOneMapsIdBackId()) &&
                condition.apply(criteria.getManyToManyMapsIdBackId(), copy.getManyToManyMapsIdBackId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
