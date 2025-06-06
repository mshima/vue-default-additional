package tech.jhipster.sample.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class UuidIdFilteringCriteriaTest {

    @Test
    void newUuidIdFilteringCriteriaHasAllFiltersNullTest() {
        var uuidIdFilteringCriteria = new UuidIdFilteringCriteria();
        assertThat(uuidIdFilteringCriteria).is(criteriaFiltersAre(Objects::isNull));
    }

    @Test
    void uuidIdFilteringCriteriaFluentMethodsCreatesFiltersTest() {
        var uuidIdFilteringCriteria = new UuidIdFilteringCriteria();

        setAllFilters(uuidIdFilteringCriteria);

        assertThat(uuidIdFilteringCriteria).is(criteriaFiltersAre(Objects::nonNull));
    }

    @Test
    void uuidIdFilteringCriteriaCopyCreatesNullFilterTest() {
        var uuidIdFilteringCriteria = new UuidIdFilteringCriteria();
        var copy = uuidIdFilteringCriteria.copy();

        assertThat(uuidIdFilteringCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::isNull)),
            criteria -> assertThat(criteria).isEqualTo(uuidIdFilteringCriteria)
        );
    }

    @Test
    void uuidIdFilteringCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var uuidIdFilteringCriteria = new UuidIdFilteringCriteria();
        setAllFilters(uuidIdFilteringCriteria);

        var copy = uuidIdFilteringCriteria.copy();

        assertThat(uuidIdFilteringCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::nonNull)),
            criteria -> assertThat(criteria).isEqualTo(uuidIdFilteringCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var uuidIdFilteringCriteria = new UuidIdFilteringCriteria();

        assertThat(uuidIdFilteringCriteria).hasToString("UuidIdFilteringCriteria{}");
    }

    private static void setAllFilters(UuidIdFilteringCriteria uuidIdFilteringCriteria) {
        uuidIdFilteringCriteria.customId();
        uuidIdFilteringCriteria.uuidIdFilteringMapsIdId();
        uuidIdFilteringCriteria.oneToOneBackId();
        uuidIdFilteringCriteria.manyToOneBackId();
        uuidIdFilteringCriteria.manyToManyBackId();
        uuidIdFilteringCriteria.distinct();
    }

    private static Condition<UuidIdFilteringCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getCustomId()) &&
                condition.apply(criteria.getUuidIdFilteringMapsIdId()) &&
                condition.apply(criteria.getOneToOneBackId()) &&
                condition.apply(criteria.getManyToOneBackId()) &&
                condition.apply(criteria.getManyToManyBackId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<UuidIdFilteringCriteria> copyFiltersAre(
        UuidIdFilteringCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getCustomId(), copy.getCustomId()) &&
                condition.apply(criteria.getUuidIdFilteringMapsIdId(), copy.getUuidIdFilteringMapsIdId()) &&
                condition.apply(criteria.getOneToOneBackId(), copy.getOneToOneBackId()) &&
                condition.apply(criteria.getManyToOneBackId(), copy.getManyToOneBackId()) &&
                condition.apply(criteria.getManyToManyBackId(), copy.getManyToManyBackId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
