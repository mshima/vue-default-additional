package tech.jhipster.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.sample.domain.UuidIdFilteringMapsIdTestSamples.*;
import static tech.jhipster.sample.domain.UuidIdFilteringRelationshipTestSamples.*;
import static tech.jhipster.sample.domain.UuidIdFilteringTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class UuidIdFilteringMapsIdTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UuidIdFilteringMapsId.class);
        UuidIdFilteringMapsId uuidIdFilteringMapsId1 = getUuidIdFilteringMapsIdSample1();
        UuidIdFilteringMapsId uuidIdFilteringMapsId2 = new UuidIdFilteringMapsId();
        assertThat(uuidIdFilteringMapsId1).isNotEqualTo(uuidIdFilteringMapsId2);

        uuidIdFilteringMapsId2.setCustomId(uuidIdFilteringMapsId1.getCustomId());
        assertThat(uuidIdFilteringMapsId1).isEqualTo(uuidIdFilteringMapsId2);

        uuidIdFilteringMapsId2 = getUuidIdFilteringMapsIdSample2();
        assertThat(uuidIdFilteringMapsId1).isNotEqualTo(uuidIdFilteringMapsId2);
    }

    @Test
    void uuidIdFilteringTest() {
        UuidIdFilteringMapsId uuidIdFilteringMapsId = getUuidIdFilteringMapsIdRandomSampleGenerator();
        UuidIdFiltering uuidIdFilteringBack = getUuidIdFilteringRandomSampleGenerator();

        uuidIdFilteringMapsId.setUuidIdFiltering(uuidIdFilteringBack);
        assertThat(uuidIdFilteringMapsId.getUuidIdFiltering()).isEqualTo(uuidIdFilteringBack);

        uuidIdFilteringMapsId.uuidIdFiltering(null);
        assertThat(uuidIdFilteringMapsId.getUuidIdFiltering()).isNull();
    }

    @Test
    void oneToOneMapsIdBackTest() {
        UuidIdFilteringMapsId uuidIdFilteringMapsId = getUuidIdFilteringMapsIdRandomSampleGenerator();
        UuidIdFilteringRelationship uuidIdFilteringRelationshipBack = getUuidIdFilteringRelationshipRandomSampleGenerator();

        uuidIdFilteringMapsId.setOneToOneMapsIdBack(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFilteringMapsId.getOneToOneMapsIdBack()).isEqualTo(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFilteringRelationshipBack.getOneToOneMapsId()).isEqualTo(uuidIdFilteringMapsId);

        uuidIdFilteringMapsId.oneToOneMapsIdBack(null);
        assertThat(uuidIdFilteringMapsId.getOneToOneMapsIdBack()).isNull();
        assertThat(uuidIdFilteringRelationshipBack.getOneToOneMapsId()).isNull();
    }

    @Test
    void manyToOneMapsIdBackTest() {
        UuidIdFilteringMapsId uuidIdFilteringMapsId = getUuidIdFilteringMapsIdRandomSampleGenerator();
        UuidIdFilteringRelationship uuidIdFilteringRelationshipBack = getUuidIdFilteringRelationshipRandomSampleGenerator();

        uuidIdFilteringMapsId.addManyToOneMapsIdBack(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFilteringMapsId.getManyToOneMapsIdBacks()).containsOnly(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFilteringRelationshipBack.getManyToOneMapsId()).isEqualTo(uuidIdFilteringMapsId);

        uuidIdFilteringMapsId.removeManyToOneMapsIdBack(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFilteringMapsId.getManyToOneMapsIdBacks()).doesNotContain(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFilteringRelationshipBack.getManyToOneMapsId()).isNull();

        uuidIdFilteringMapsId.manyToOneMapsIdBacks(new HashSet<>(Set.of(uuidIdFilteringRelationshipBack)));
        assertThat(uuidIdFilteringMapsId.getManyToOneMapsIdBacks()).containsOnly(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFilteringRelationshipBack.getManyToOneMapsId()).isEqualTo(uuidIdFilteringMapsId);

        uuidIdFilteringMapsId.setManyToOneMapsIdBacks(new HashSet<>());
        assertThat(uuidIdFilteringMapsId.getManyToOneMapsIdBacks()).doesNotContain(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFilteringRelationshipBack.getManyToOneMapsId()).isNull();
    }

    @Test
    void manyToManyMapsIdBackTest() {
        UuidIdFilteringMapsId uuidIdFilteringMapsId = getUuidIdFilteringMapsIdRandomSampleGenerator();
        UuidIdFilteringRelationship uuidIdFilteringRelationshipBack = getUuidIdFilteringRelationshipRandomSampleGenerator();

        uuidIdFilteringMapsId.addManyToManyMapsIdBack(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFilteringMapsId.getManyToManyMapsIdBacks()).containsOnly(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFilteringRelationshipBack.getManyToManyMapsIds()).containsOnly(uuidIdFilteringMapsId);

        uuidIdFilteringMapsId.removeManyToManyMapsIdBack(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFilteringMapsId.getManyToManyMapsIdBacks()).doesNotContain(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFilteringRelationshipBack.getManyToManyMapsIds()).doesNotContain(uuidIdFilteringMapsId);

        uuidIdFilteringMapsId.manyToManyMapsIdBacks(new HashSet<>(Set.of(uuidIdFilteringRelationshipBack)));
        assertThat(uuidIdFilteringMapsId.getManyToManyMapsIdBacks()).containsOnly(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFilteringRelationshipBack.getManyToManyMapsIds()).containsOnly(uuidIdFilteringMapsId);

        uuidIdFilteringMapsId.setManyToManyMapsIdBacks(new HashSet<>());
        assertThat(uuidIdFilteringMapsId.getManyToManyMapsIdBacks()).doesNotContain(uuidIdFilteringRelationshipBack);
        assertThat(uuidIdFilteringRelationshipBack.getManyToManyMapsIds()).doesNotContain(uuidIdFilteringMapsId);
    }
}
