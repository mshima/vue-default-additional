package tech.jhipster.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.sample.domain.EntityCustomIdMapsIdTestSamples.*;
import static tech.jhipster.sample.domain.EntityCustomIdRelationshipTestSamples.*;
import static tech.jhipster.sample.domain.EntityCustomIdTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityCustomIdTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityCustomId.class);
        EntityCustomId entityCustomId1 = getEntityCustomIdSample1();
        EntityCustomId entityCustomId2 = new EntityCustomId();
        assertThat(entityCustomId1).isNotEqualTo(entityCustomId2);

        entityCustomId2.setCustomId(entityCustomId1.getCustomId());
        assertThat(entityCustomId1).isEqualTo(entityCustomId2);

        entityCustomId2 = getEntityCustomIdSample2();
        assertThat(entityCustomId1).isNotEqualTo(entityCustomId2);
    }

    @Test
    void entityCustomIdMapsIdTest() {
        EntityCustomId entityCustomId = getEntityCustomIdRandomSampleGenerator();
        EntityCustomIdMapsId entityCustomIdMapsIdBack = getEntityCustomIdMapsIdRandomSampleGenerator();

        entityCustomId.setEntityCustomIdMapsId(entityCustomIdMapsIdBack);
        assertThat(entityCustomId.getEntityCustomIdMapsId()).isEqualTo(entityCustomIdMapsIdBack);
        assertThat(entityCustomIdMapsIdBack.getEntityCustomId()).isEqualTo(entityCustomId);

        entityCustomId.entityCustomIdMapsId(null);
        assertThat(entityCustomId.getEntityCustomIdMapsId()).isNull();
        assertThat(entityCustomIdMapsIdBack.getEntityCustomId()).isNull();
    }

    @Test
    void oneToOneBackTest() {
        EntityCustomId entityCustomId = getEntityCustomIdRandomSampleGenerator();
        EntityCustomIdRelationship entityCustomIdRelationshipBack = getEntityCustomIdRelationshipRandomSampleGenerator();

        entityCustomId.setOneToOneBack(entityCustomIdRelationshipBack);
        assertThat(entityCustomId.getOneToOneBack()).isEqualTo(entityCustomIdRelationshipBack);
        assertThat(entityCustomIdRelationshipBack.getOneToOne()).isEqualTo(entityCustomId);

        entityCustomId.oneToOneBack(null);
        assertThat(entityCustomId.getOneToOneBack()).isNull();
        assertThat(entityCustomIdRelationshipBack.getOneToOne()).isNull();
    }

    @Test
    void manyToOneBackTest() {
        EntityCustomId entityCustomId = getEntityCustomIdRandomSampleGenerator();
        EntityCustomIdRelationship entityCustomIdRelationshipBack = getEntityCustomIdRelationshipRandomSampleGenerator();

        entityCustomId.addManyToOneBack(entityCustomIdRelationshipBack);
        assertThat(entityCustomId.getManyToOneBacks()).containsOnly(entityCustomIdRelationshipBack);
        assertThat(entityCustomIdRelationshipBack.getManyToOne()).isEqualTo(entityCustomId);

        entityCustomId.removeManyToOneBack(entityCustomIdRelationshipBack);
        assertThat(entityCustomId.getManyToOneBacks()).doesNotContain(entityCustomIdRelationshipBack);
        assertThat(entityCustomIdRelationshipBack.getManyToOne()).isNull();

        entityCustomId.manyToOneBacks(new HashSet<>(Set.of(entityCustomIdRelationshipBack)));
        assertThat(entityCustomId.getManyToOneBacks()).containsOnly(entityCustomIdRelationshipBack);
        assertThat(entityCustomIdRelationshipBack.getManyToOne()).isEqualTo(entityCustomId);

        entityCustomId.setManyToOneBacks(new HashSet<>());
        assertThat(entityCustomId.getManyToOneBacks()).doesNotContain(entityCustomIdRelationshipBack);
        assertThat(entityCustomIdRelationshipBack.getManyToOne()).isNull();
    }

    @Test
    void manyToManyBackTest() {
        EntityCustomId entityCustomId = getEntityCustomIdRandomSampleGenerator();
        EntityCustomIdRelationship entityCustomIdRelationshipBack = getEntityCustomIdRelationshipRandomSampleGenerator();

        entityCustomId.addManyToManyBack(entityCustomIdRelationshipBack);
        assertThat(entityCustomId.getManyToManyBacks()).containsOnly(entityCustomIdRelationshipBack);
        assertThat(entityCustomIdRelationshipBack.getManyToManies()).containsOnly(entityCustomId);

        entityCustomId.removeManyToManyBack(entityCustomIdRelationshipBack);
        assertThat(entityCustomId.getManyToManyBacks()).doesNotContain(entityCustomIdRelationshipBack);
        assertThat(entityCustomIdRelationshipBack.getManyToManies()).doesNotContain(entityCustomId);

        entityCustomId.manyToManyBacks(new HashSet<>(Set.of(entityCustomIdRelationshipBack)));
        assertThat(entityCustomId.getManyToManyBacks()).containsOnly(entityCustomIdRelationshipBack);
        assertThat(entityCustomIdRelationshipBack.getManyToManies()).containsOnly(entityCustomId);

        entityCustomId.setManyToManyBacks(new HashSet<>());
        assertThat(entityCustomId.getManyToManyBacks()).doesNotContain(entityCustomIdRelationshipBack);
        assertThat(entityCustomIdRelationshipBack.getManyToManies()).doesNotContain(entityCustomId);
    }
}
