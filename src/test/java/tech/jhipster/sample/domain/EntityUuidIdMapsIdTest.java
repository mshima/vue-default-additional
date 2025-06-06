package tech.jhipster.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.sample.domain.EntityUuidIdMapsIdTestSamples.*;
import static tech.jhipster.sample.domain.EntityUuidIdRelationshipTestSamples.*;
import static tech.jhipster.sample.domain.EntityUuidIdTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityUuidIdMapsIdTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityUuidIdMapsId.class);
        EntityUuidIdMapsId entityUuidIdMapsId1 = getEntityUuidIdMapsIdSample1();
        EntityUuidIdMapsId entityUuidIdMapsId2 = new EntityUuidIdMapsId();
        assertThat(entityUuidIdMapsId1).isNotEqualTo(entityUuidIdMapsId2);

        entityUuidIdMapsId2.setId(entityUuidIdMapsId1.getId());
        assertThat(entityUuidIdMapsId1).isEqualTo(entityUuidIdMapsId2);

        entityUuidIdMapsId2 = getEntityUuidIdMapsIdSample2();
        assertThat(entityUuidIdMapsId1).isNotEqualTo(entityUuidIdMapsId2);
    }

    @Test
    void entityUuidIdTest() {
        EntityUuidIdMapsId entityUuidIdMapsId = getEntityUuidIdMapsIdRandomSampleGenerator();
        EntityUuidId entityUuidIdBack = getEntityUuidIdRandomSampleGenerator();

        entityUuidIdMapsId.setEntityUuidId(entityUuidIdBack);
        assertThat(entityUuidIdMapsId.getEntityUuidId()).isEqualTo(entityUuidIdBack);

        entityUuidIdMapsId.entityUuidId(null);
        assertThat(entityUuidIdMapsId.getEntityUuidId()).isNull();
    }

    @Test
    void oneToOneMapsIdBackTest() {
        EntityUuidIdMapsId entityUuidIdMapsId = getEntityUuidIdMapsIdRandomSampleGenerator();
        EntityUuidIdRelationship entityUuidIdRelationshipBack = getEntityUuidIdRelationshipRandomSampleGenerator();

        entityUuidIdMapsId.setOneToOneMapsIdBack(entityUuidIdRelationshipBack);
        assertThat(entityUuidIdMapsId.getOneToOneMapsIdBack()).isEqualTo(entityUuidIdRelationshipBack);
        assertThat(entityUuidIdRelationshipBack.getOneToOneMapsId()).isEqualTo(entityUuidIdMapsId);

        entityUuidIdMapsId.oneToOneMapsIdBack(null);
        assertThat(entityUuidIdMapsId.getOneToOneMapsIdBack()).isNull();
        assertThat(entityUuidIdRelationshipBack.getOneToOneMapsId()).isNull();
    }

    @Test
    void manyToOneMapsIdBackTest() {
        EntityUuidIdMapsId entityUuidIdMapsId = getEntityUuidIdMapsIdRandomSampleGenerator();
        EntityUuidIdRelationship entityUuidIdRelationshipBack = getEntityUuidIdRelationshipRandomSampleGenerator();

        entityUuidIdMapsId.addManyToOneMapsIdBack(entityUuidIdRelationshipBack);
        assertThat(entityUuidIdMapsId.getManyToOneMapsIdBacks()).containsOnly(entityUuidIdRelationshipBack);
        assertThat(entityUuidIdRelationshipBack.getManyToOneMapsId()).isEqualTo(entityUuidIdMapsId);

        entityUuidIdMapsId.removeManyToOneMapsIdBack(entityUuidIdRelationshipBack);
        assertThat(entityUuidIdMapsId.getManyToOneMapsIdBacks()).doesNotContain(entityUuidIdRelationshipBack);
        assertThat(entityUuidIdRelationshipBack.getManyToOneMapsId()).isNull();

        entityUuidIdMapsId.manyToOneMapsIdBacks(new HashSet<>(Set.of(entityUuidIdRelationshipBack)));
        assertThat(entityUuidIdMapsId.getManyToOneMapsIdBacks()).containsOnly(entityUuidIdRelationshipBack);
        assertThat(entityUuidIdRelationshipBack.getManyToOneMapsId()).isEqualTo(entityUuidIdMapsId);

        entityUuidIdMapsId.setManyToOneMapsIdBacks(new HashSet<>());
        assertThat(entityUuidIdMapsId.getManyToOneMapsIdBacks()).doesNotContain(entityUuidIdRelationshipBack);
        assertThat(entityUuidIdRelationshipBack.getManyToOneMapsId()).isNull();
    }

    @Test
    void manyToManyMapsIdBackTest() {
        EntityUuidIdMapsId entityUuidIdMapsId = getEntityUuidIdMapsIdRandomSampleGenerator();
        EntityUuidIdRelationship entityUuidIdRelationshipBack = getEntityUuidIdRelationshipRandomSampleGenerator();

        entityUuidIdMapsId.addManyToManyMapsIdBack(entityUuidIdRelationshipBack);
        assertThat(entityUuidIdMapsId.getManyToManyMapsIdBacks()).containsOnly(entityUuidIdRelationshipBack);
        assertThat(entityUuidIdRelationshipBack.getManyToManyMapsIds()).containsOnly(entityUuidIdMapsId);

        entityUuidIdMapsId.removeManyToManyMapsIdBack(entityUuidIdRelationshipBack);
        assertThat(entityUuidIdMapsId.getManyToManyMapsIdBacks()).doesNotContain(entityUuidIdRelationshipBack);
        assertThat(entityUuidIdRelationshipBack.getManyToManyMapsIds()).doesNotContain(entityUuidIdMapsId);

        entityUuidIdMapsId.manyToManyMapsIdBacks(new HashSet<>(Set.of(entityUuidIdRelationshipBack)));
        assertThat(entityUuidIdMapsId.getManyToManyMapsIdBacks()).containsOnly(entityUuidIdRelationshipBack);
        assertThat(entityUuidIdRelationshipBack.getManyToManyMapsIds()).containsOnly(entityUuidIdMapsId);

        entityUuidIdMapsId.setManyToManyMapsIdBacks(new HashSet<>());
        assertThat(entityUuidIdMapsId.getManyToManyMapsIdBacks()).doesNotContain(entityUuidIdRelationshipBack);
        assertThat(entityUuidIdRelationshipBack.getManyToManyMapsIds()).doesNotContain(entityUuidIdMapsId);
    }
}
