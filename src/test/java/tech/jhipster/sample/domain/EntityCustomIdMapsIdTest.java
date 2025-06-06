package tech.jhipster.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.sample.domain.EntityCustomIdMapsIdTestSamples.*;
import static tech.jhipster.sample.domain.EntityCustomIdRelationshipTestSamples.*;
import static tech.jhipster.sample.domain.EntityCustomIdTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityCustomIdMapsIdTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityCustomIdMapsId.class);
        EntityCustomIdMapsId entityCustomIdMapsId1 = getEntityCustomIdMapsIdSample1();
        EntityCustomIdMapsId entityCustomIdMapsId2 = new EntityCustomIdMapsId();
        assertThat(entityCustomIdMapsId1).isNotEqualTo(entityCustomIdMapsId2);

        entityCustomIdMapsId2.setCustomId(entityCustomIdMapsId1.getCustomId());
        assertThat(entityCustomIdMapsId1).isEqualTo(entityCustomIdMapsId2);

        entityCustomIdMapsId2 = getEntityCustomIdMapsIdSample2();
        assertThat(entityCustomIdMapsId1).isNotEqualTo(entityCustomIdMapsId2);
    }

    @Test
    void entityCustomIdTest() {
        EntityCustomIdMapsId entityCustomIdMapsId = getEntityCustomIdMapsIdRandomSampleGenerator();
        EntityCustomId entityCustomIdBack = getEntityCustomIdRandomSampleGenerator();

        entityCustomIdMapsId.setEntityCustomId(entityCustomIdBack);
        assertThat(entityCustomIdMapsId.getEntityCustomId()).isEqualTo(entityCustomIdBack);

        entityCustomIdMapsId.entityCustomId(null);
        assertThat(entityCustomIdMapsId.getEntityCustomId()).isNull();
    }

    @Test
    void oneToOneMapsIdBackTest() {
        EntityCustomIdMapsId entityCustomIdMapsId = getEntityCustomIdMapsIdRandomSampleGenerator();
        EntityCustomIdRelationship entityCustomIdRelationshipBack = getEntityCustomIdRelationshipRandomSampleGenerator();

        entityCustomIdMapsId.setOneToOneMapsIdBack(entityCustomIdRelationshipBack);
        assertThat(entityCustomIdMapsId.getOneToOneMapsIdBack()).isEqualTo(entityCustomIdRelationshipBack);
        assertThat(entityCustomIdRelationshipBack.getOneToOneMapsId()).isEqualTo(entityCustomIdMapsId);

        entityCustomIdMapsId.oneToOneMapsIdBack(null);
        assertThat(entityCustomIdMapsId.getOneToOneMapsIdBack()).isNull();
        assertThat(entityCustomIdRelationshipBack.getOneToOneMapsId()).isNull();
    }

    @Test
    void manyToOneMapsIdBackTest() {
        EntityCustomIdMapsId entityCustomIdMapsId = getEntityCustomIdMapsIdRandomSampleGenerator();
        EntityCustomIdRelationship entityCustomIdRelationshipBack = getEntityCustomIdRelationshipRandomSampleGenerator();

        entityCustomIdMapsId.addManyToOneMapsIdBack(entityCustomIdRelationshipBack);
        assertThat(entityCustomIdMapsId.getManyToOneMapsIdBacks()).containsOnly(entityCustomIdRelationshipBack);
        assertThat(entityCustomIdRelationshipBack.getManyToOneMapsId()).isEqualTo(entityCustomIdMapsId);

        entityCustomIdMapsId.removeManyToOneMapsIdBack(entityCustomIdRelationshipBack);
        assertThat(entityCustomIdMapsId.getManyToOneMapsIdBacks()).doesNotContain(entityCustomIdRelationshipBack);
        assertThat(entityCustomIdRelationshipBack.getManyToOneMapsId()).isNull();

        entityCustomIdMapsId.manyToOneMapsIdBacks(new HashSet<>(Set.of(entityCustomIdRelationshipBack)));
        assertThat(entityCustomIdMapsId.getManyToOneMapsIdBacks()).containsOnly(entityCustomIdRelationshipBack);
        assertThat(entityCustomIdRelationshipBack.getManyToOneMapsId()).isEqualTo(entityCustomIdMapsId);

        entityCustomIdMapsId.setManyToOneMapsIdBacks(new HashSet<>());
        assertThat(entityCustomIdMapsId.getManyToOneMapsIdBacks()).doesNotContain(entityCustomIdRelationshipBack);
        assertThat(entityCustomIdRelationshipBack.getManyToOneMapsId()).isNull();
    }

    @Test
    void manyToManyMapsIdBackTest() {
        EntityCustomIdMapsId entityCustomIdMapsId = getEntityCustomIdMapsIdRandomSampleGenerator();
        EntityCustomIdRelationship entityCustomIdRelationshipBack = getEntityCustomIdRelationshipRandomSampleGenerator();

        entityCustomIdMapsId.addManyToManyMapsIdBack(entityCustomIdRelationshipBack);
        assertThat(entityCustomIdMapsId.getManyToManyMapsIdBacks()).containsOnly(entityCustomIdRelationshipBack);
        assertThat(entityCustomIdRelationshipBack.getManyToManyMapsIds()).containsOnly(entityCustomIdMapsId);

        entityCustomIdMapsId.removeManyToManyMapsIdBack(entityCustomIdRelationshipBack);
        assertThat(entityCustomIdMapsId.getManyToManyMapsIdBacks()).doesNotContain(entityCustomIdRelationshipBack);
        assertThat(entityCustomIdRelationshipBack.getManyToManyMapsIds()).doesNotContain(entityCustomIdMapsId);

        entityCustomIdMapsId.manyToManyMapsIdBacks(new HashSet<>(Set.of(entityCustomIdRelationshipBack)));
        assertThat(entityCustomIdMapsId.getManyToManyMapsIdBacks()).containsOnly(entityCustomIdRelationshipBack);
        assertThat(entityCustomIdRelationshipBack.getManyToManyMapsIds()).containsOnly(entityCustomIdMapsId);

        entityCustomIdMapsId.setManyToManyMapsIdBacks(new HashSet<>());
        assertThat(entityCustomIdMapsId.getManyToManyMapsIdBacks()).doesNotContain(entityCustomIdRelationshipBack);
        assertThat(entityCustomIdRelationshipBack.getManyToManyMapsIds()).doesNotContain(entityCustomIdMapsId);
    }
}
