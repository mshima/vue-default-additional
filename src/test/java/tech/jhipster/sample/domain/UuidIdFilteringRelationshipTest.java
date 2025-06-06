package tech.jhipster.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.sample.domain.UuidIdFilteringMapsIdTestSamples.*;
import static tech.jhipster.sample.domain.UuidIdFilteringRelationshipTestSamples.*;
import static tech.jhipster.sample.domain.UuidIdFilteringTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class UuidIdFilteringRelationshipTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UuidIdFilteringRelationship.class);
        UuidIdFilteringRelationship uuidIdFilteringRelationship1 = getUuidIdFilteringRelationshipSample1();
        UuidIdFilteringRelationship uuidIdFilteringRelationship2 = new UuidIdFilteringRelationship();
        assertThat(uuidIdFilteringRelationship1).isNotEqualTo(uuidIdFilteringRelationship2);

        uuidIdFilteringRelationship2.setRelatedId(uuidIdFilteringRelationship1.getRelatedId());
        assertThat(uuidIdFilteringRelationship1).isEqualTo(uuidIdFilteringRelationship2);

        uuidIdFilteringRelationship2 = getUuidIdFilteringRelationshipSample2();
        assertThat(uuidIdFilteringRelationship1).isNotEqualTo(uuidIdFilteringRelationship2);
    }

    @Test
    void oneToOneTest() {
        UuidIdFilteringRelationship uuidIdFilteringRelationship = getUuidIdFilteringRelationshipRandomSampleGenerator();
        UuidIdFiltering uuidIdFilteringBack = getUuidIdFilteringRandomSampleGenerator();

        uuidIdFilteringRelationship.setOneToOne(uuidIdFilteringBack);
        assertThat(uuidIdFilteringRelationship.getOneToOne()).isEqualTo(uuidIdFilteringBack);

        uuidIdFilteringRelationship.oneToOne(null);
        assertThat(uuidIdFilteringRelationship.getOneToOne()).isNull();
    }

    @Test
    void oneToOneMapsIdTest() {
        UuidIdFilteringRelationship uuidIdFilteringRelationship = getUuidIdFilteringRelationshipRandomSampleGenerator();
        UuidIdFilteringMapsId uuidIdFilteringMapsIdBack = getUuidIdFilteringMapsIdRandomSampleGenerator();

        uuidIdFilteringRelationship.setOneToOneMapsId(uuidIdFilteringMapsIdBack);
        assertThat(uuidIdFilteringRelationship.getOneToOneMapsId()).isEqualTo(uuidIdFilteringMapsIdBack);

        uuidIdFilteringRelationship.oneToOneMapsId(null);
        assertThat(uuidIdFilteringRelationship.getOneToOneMapsId()).isNull();
    }

    @Test
    void manyToOneTest() {
        UuidIdFilteringRelationship uuidIdFilteringRelationship = getUuidIdFilteringRelationshipRandomSampleGenerator();
        UuidIdFiltering uuidIdFilteringBack = getUuidIdFilteringRandomSampleGenerator();

        uuidIdFilteringRelationship.setManyToOne(uuidIdFilteringBack);
        assertThat(uuidIdFilteringRelationship.getManyToOne()).isEqualTo(uuidIdFilteringBack);

        uuidIdFilteringRelationship.manyToOne(null);
        assertThat(uuidIdFilteringRelationship.getManyToOne()).isNull();
    }

    @Test
    void manyToOneMapsIdTest() {
        UuidIdFilteringRelationship uuidIdFilteringRelationship = getUuidIdFilteringRelationshipRandomSampleGenerator();
        UuidIdFilteringMapsId uuidIdFilteringMapsIdBack = getUuidIdFilteringMapsIdRandomSampleGenerator();

        uuidIdFilteringRelationship.setManyToOneMapsId(uuidIdFilteringMapsIdBack);
        assertThat(uuidIdFilteringRelationship.getManyToOneMapsId()).isEqualTo(uuidIdFilteringMapsIdBack);

        uuidIdFilteringRelationship.manyToOneMapsId(null);
        assertThat(uuidIdFilteringRelationship.getManyToOneMapsId()).isNull();
    }

    @Test
    void manyToManyTest() {
        UuidIdFilteringRelationship uuidIdFilteringRelationship = getUuidIdFilteringRelationshipRandomSampleGenerator();
        UuidIdFiltering uuidIdFilteringBack = getUuidIdFilteringRandomSampleGenerator();

        uuidIdFilteringRelationship.addManyToMany(uuidIdFilteringBack);
        assertThat(uuidIdFilteringRelationship.getManyToManies()).containsOnly(uuidIdFilteringBack);

        uuidIdFilteringRelationship.removeManyToMany(uuidIdFilteringBack);
        assertThat(uuidIdFilteringRelationship.getManyToManies()).doesNotContain(uuidIdFilteringBack);

        uuidIdFilteringRelationship.manyToManies(new HashSet<>(Set.of(uuidIdFilteringBack)));
        assertThat(uuidIdFilteringRelationship.getManyToManies()).containsOnly(uuidIdFilteringBack);

        uuidIdFilteringRelationship.setManyToManies(new HashSet<>());
        assertThat(uuidIdFilteringRelationship.getManyToManies()).doesNotContain(uuidIdFilteringBack);
    }

    @Test
    void manyToManyMapsIdTest() {
        UuidIdFilteringRelationship uuidIdFilteringRelationship = getUuidIdFilteringRelationshipRandomSampleGenerator();
        UuidIdFilteringMapsId uuidIdFilteringMapsIdBack = getUuidIdFilteringMapsIdRandomSampleGenerator();

        uuidIdFilteringRelationship.addManyToManyMapsId(uuidIdFilteringMapsIdBack);
        assertThat(uuidIdFilteringRelationship.getManyToManyMapsIds()).containsOnly(uuidIdFilteringMapsIdBack);

        uuidIdFilteringRelationship.removeManyToManyMapsId(uuidIdFilteringMapsIdBack);
        assertThat(uuidIdFilteringRelationship.getManyToManyMapsIds()).doesNotContain(uuidIdFilteringMapsIdBack);

        uuidIdFilteringRelationship.manyToManyMapsIds(new HashSet<>(Set.of(uuidIdFilteringMapsIdBack)));
        assertThat(uuidIdFilteringRelationship.getManyToManyMapsIds()).containsOnly(uuidIdFilteringMapsIdBack);

        uuidIdFilteringRelationship.setManyToManyMapsIds(new HashSet<>());
        assertThat(uuidIdFilteringRelationship.getManyToManyMapsIds()).doesNotContain(uuidIdFilteringMapsIdBack);
    }
}
