package tech.jhipster.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.sample.domain.UuidIdFilteringMapsIdTestSamples.*;
import static tech.jhipster.sample.domain.UuidIdFilteringRelationshipTestSamples.*;
import static tech.jhipster.sample.domain.UuidIdFilteringTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class UuidIdFilteringTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UuidIdFiltering.class);
        UuidIdFiltering uuidIdFiltering1 = getUuidIdFilteringSample1();
        UuidIdFiltering uuidIdFiltering2 = new UuidIdFiltering();
        assertThat(uuidIdFiltering1).isNotEqualTo(uuidIdFiltering2);

        uuidIdFiltering2.setCustomId(uuidIdFiltering1.getCustomId());
        assertThat(uuidIdFiltering1).isEqualTo(uuidIdFiltering2);

        uuidIdFiltering2 = getUuidIdFilteringSample2();
        assertThat(uuidIdFiltering1).isNotEqualTo(uuidIdFiltering2);
    }

    @Test
    void uuidIdFilteringMapsIdTest() {
        UuidIdFiltering uuidIdFiltering = getUuidIdFilteringRandomSampleGenerator();
        UuidIdFilteringMapsId uuidIdFilteringMapsIdBack = getUuidIdFilteringMapsIdRandomSampleGenerator();

        uuidIdFiltering.setUuidIdFilteringMapsId(uuidIdFilteringMapsIdBack);
        assertThat(uuidIdFiltering.getUuidIdFilteringMapsId()).isEqualTo(uuidIdFilteringMapsIdBack);
        assertThat(uuidIdFilteringMapsIdBack.getUuidIdFiltering()).isEqualTo(uuidIdFiltering);

        uuidIdFiltering.uuidIdFilteringMapsId(null);
        assertThat(uuidIdFiltering.getUuidIdFilteringMapsId()).isNull();
        assertThat(uuidIdFilteringMapsIdBack.getUuidIdFiltering()).isNull();
    }

    @Test
    void oneToOneBackTest() {
        UuidIdFiltering uuidIdFiltering = getUuidIdFilteringRandomSampleGenerator();
        UuidIdFilteringRelationship uuidIdFilteringRelationshipBack = getUuidIdFilteringRelationshipRandomSampleGenerator();

        uuidIdFiltering.setOneToOneBack(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFiltering.getOneToOneBack()).isEqualTo(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFilteringRelationshipBack.getOneToOne()).isEqualTo(uuidIdFiltering);

        uuidIdFiltering.oneToOneBack(null);
        assertThat(uuidIdFiltering.getOneToOneBack()).isNull();
        assertThat(uuidIdFilteringRelationshipBack.getOneToOne()).isNull();
    }

    @Test
    void manyToOneBackTest() {
        UuidIdFiltering uuidIdFiltering = getUuidIdFilteringRandomSampleGenerator();
        UuidIdFilteringRelationship uuidIdFilteringRelationshipBack = getUuidIdFilteringRelationshipRandomSampleGenerator();

        uuidIdFiltering.addManyToOneBack(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFiltering.getManyToOneBacks()).containsOnly(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFilteringRelationshipBack.getManyToOne()).isEqualTo(uuidIdFiltering);

        uuidIdFiltering.removeManyToOneBack(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFiltering.getManyToOneBacks()).doesNotContain(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFilteringRelationshipBack.getManyToOne()).isNull();

        uuidIdFiltering.manyToOneBacks(new HashSet<>(Set.of(uuidIdFilteringRelationshipBack)));
        assertThat(uuidIdFiltering.getManyToOneBacks()).containsOnly(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFilteringRelationshipBack.getManyToOne()).isEqualTo(uuidIdFiltering);

        uuidIdFiltering.setManyToOneBacks(new HashSet<>());
        assertThat(uuidIdFiltering.getManyToOneBacks()).doesNotContain(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFilteringRelationshipBack.getManyToOne()).isNull();
    }

    @Test
    void manyToManyBackTest() {
        UuidIdFiltering uuidIdFiltering = getUuidIdFilteringRandomSampleGenerator();
        UuidIdFilteringRelationship uuidIdFilteringRelationshipBack = getUuidIdFilteringRelationshipRandomSampleGenerator();

        uuidIdFiltering.addManyToManyBack(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFiltering.getManyToManyBacks()).containsOnly(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFilteringRelationshipBack.getManyToManies()).containsOnly(uuidIdFiltering);

        uuidIdFiltering.removeManyToManyBack(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFiltering.getManyToManyBacks()).doesNotContain(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFilteringRelationshipBack.getManyToManies()).doesNotContain(uuidIdFiltering);

        uuidIdFiltering.manyToManyBacks(new HashSet<>(Set.of(uuidIdFilteringRelationshipBack)));
        assertThat(uuidIdFiltering.getManyToManyBacks()).containsOnly(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFilteringRelationshipBack.getManyToManies()).containsOnly(uuidIdFiltering);

        uuidIdFiltering.setManyToManyBacks(new HashSet<>());
        assertThat(uuidIdFiltering.getManyToManyBacks()).doesNotContain(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFilteringRelationshipBack.getManyToManies()).doesNotContain(uuidIdFiltering);
    }
}
