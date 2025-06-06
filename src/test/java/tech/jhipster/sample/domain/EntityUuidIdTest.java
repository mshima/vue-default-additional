package tech.jhipster.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.sample.domain.EntityUuidIdMapsIdTestSamples.*;
import static tech.jhipster.sample.domain.EntityUuidIdRelationshipTestSamples.*;
import static tech.jhipster.sample.domain.EntityUuidIdTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityUuidIdTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityUuidId.class);
        EntityUuidId entityUuidId1 = getEntityUuidIdSample1();
        EntityUuidId entityUuidId2 = new EntityUuidId();
        assertThat(entityUuidId1).isNotEqualTo(entityUuidId2);

        entityUuidId2.setId(entityUuidId1.getId());
        assertThat(entityUuidId1).isEqualTo(entityUuidId2);

        entityUuidId2 = getEntityUuidIdSample2();
        assertThat(entityUuidId1).isNotEqualTo(entityUuidId2);
    }

    @Test
    void entityUuidIdMapsIdTest() {
        EntityUuidId entityUuidId = getEntityUuidIdRandomSampleGenerator();
        EntityUuidIdMapsId entityUuidIdMapsIdBack = getEntityUuidIdMapsIdRandomSampleGenerator();

        entityUuidId.setEntityUuidIdMapsId(entityUuidIdMapsIdBack);
        assertThat(entityUuidId.getEntityUuidIdMapsId()).isEqualTo(entityUuidIdMapsIdBack);
        assertThat(entityUuidIdMapsIdBack.getEntityUuidId()).isEqualTo(entityUuidId);

        entityUuidId.entityUuidIdMapsId(null);
        assertThat(entityUuidId.getEntityUuidIdMapsId()).isNull();
        assertThat(entityUuidIdMapsIdBack.getEntityUuidId()).isNull();
    }

    @Test
    void oneToOneBackTest() {
        EntityUuidId entityUuidId = getEntityUuidIdRandomSampleGenerator();
        EntityUuidIdRelationship entityUuidIdRelationshipBack = getEntityUuidIdRelationshipRandomSampleGenerator();

        entityUuidId.setOneToOneBack(entityUuidIdRelationshipBack);
        assertThat(entityUuidId.getOneToOneBack()).isEqualTo(entityUuidIdRelationshipBack);
        assertThat(entityUuidIdRelationshipBack.getOneToOne()).isEqualTo(entityUuidId);

        entityUuidId.oneToOneBack(null);
        assertThat(entityUuidId.getOneToOneBack()).isNull();
        assertThat(entityUuidIdRelationshipBack.getOneToOne()).isNull();
    }

    @Test
    void manyToOneBackTest() {
        EntityUuidId entityUuidId = getEntityUuidIdRandomSampleGenerator();
        EntityUuidIdRelationship entityUuidIdRelationshipBack = getEntityUuidIdRelationshipRandomSampleGenerator();

        entityUuidId.addManyToOneBack(entityUuidIdRelationshipBack);
        assertThat(entityUuidId.getManyToOneBacks()).containsOnly(entityUuidIdRelationshipBack);
        assertThat(entityUuidIdRelationshipBack.getManyToOne()).isEqualTo(entityUuidId);

        entityUuidId.removeManyToOneBack(entityUuidIdRelationshipBack);
        assertThat(entityUuidId.getManyToOneBacks()).doesNotContain(entityUuidIdRelationshipBack);
        assertThat(entityUuidIdRelationshipBack.getManyToOne()).isNull();

        entityUuidId.manyToOneBacks(new HashSet<>(Set.of(entityUuidIdRelationshipBack)));
        assertThat(entityUuidId.getManyToOneBacks()).containsOnly(entityUuidIdRelationshipBack);
        assertThat(entityUuidIdRelationshipBack.getManyToOne()).isEqualTo(entityUuidId);

        entityUuidId.setManyToOneBacks(new HashSet<>());
        assertThat(entityUuidId.getManyToOneBacks()).doesNotContain(entityUuidIdRelationshipBack);
        assertThat(entityUuidIdRelationshipBack.getManyToOne()).isNull();
    }

    @Test
    void manyToManyBackTest() {
        EntityUuidId entityUuidId = getEntityUuidIdRandomSampleGenerator();
        EntityUuidIdRelationship entityUuidIdRelationshipBack = getEntityUuidIdRelationshipRandomSampleGenerator();

        entityUuidId.addManyToManyBack(entityUuidIdRelationshipBack);
        assertThat(entityUuidId.getManyToManyBacks()).containsOnly(entityUuidIdRelationshipBack);
        assertThat(entityUuidIdRelationshipBack.getManyToManies()).containsOnly(entityUuidId);

        entityUuidId.removeManyToManyBack(entityUuidIdRelationshipBack);
        assertThat(entityUuidId.getManyToManyBacks()).doesNotContain(entityUuidIdRelationshipBack);
        assertThat(entityUuidIdRelationshipBack.getManyToManies()).doesNotContain(entityUuidId);

        entityUuidId.manyToManyBacks(new HashSet<>(Set.of(entityUuidIdRelationshipBack)));
        assertThat(entityUuidId.getManyToManyBacks()).containsOnly(entityUuidIdRelationshipBack);
        assertThat(entityUuidIdRelationshipBack.getManyToManies()).containsOnly(entityUuidId);

        entityUuidId.setManyToManyBacks(new HashSet<>());
        assertThat(entityUuidId.getManyToManyBacks()).doesNotContain(entityUuidIdRelationshipBack);
        assertThat(entityUuidIdRelationshipBack.getManyToManies()).doesNotContain(entityUuidId);
    }
}
