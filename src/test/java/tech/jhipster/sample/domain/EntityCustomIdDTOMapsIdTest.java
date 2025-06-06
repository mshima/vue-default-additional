package tech.jhipster.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.sample.domain.EntityCustomIdDTOMapsIdTestSamples.*;
import static tech.jhipster.sample.domain.EntityCustomIdDTORelTestSamples.*;
import static tech.jhipster.sample.domain.EntityCustomIdDTOTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityCustomIdDTOMapsIdTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityCustomIdDTOMapsId.class);
        EntityCustomIdDTOMapsId entityCustomIdDTOMapsId1 = getEntityCustomIdDTOMapsIdSample1();
        EntityCustomIdDTOMapsId entityCustomIdDTOMapsId2 = new EntityCustomIdDTOMapsId();
        assertThat(entityCustomIdDTOMapsId1).isNotEqualTo(entityCustomIdDTOMapsId2);

        entityCustomIdDTOMapsId2.setCustomId(entityCustomIdDTOMapsId1.getCustomId());
        assertThat(entityCustomIdDTOMapsId1).isEqualTo(entityCustomIdDTOMapsId2);

        entityCustomIdDTOMapsId2 = getEntityCustomIdDTOMapsIdSample2();
        assertThat(entityCustomIdDTOMapsId1).isNotEqualTo(entityCustomIdDTOMapsId2);
    }

    @Test
    void entityCustomIdDTOTest() {
        EntityCustomIdDTOMapsId entityCustomIdDTOMapsId = getEntityCustomIdDTOMapsIdRandomSampleGenerator();
        EntityCustomIdDTO entityCustomIdDTOBack = getEntityCustomIdDTORandomSampleGenerator();

        entityCustomIdDTOMapsId.setEntityCustomIdDTO(entityCustomIdDTOBack);
        assertThat(entityCustomIdDTOMapsId.getEntityCustomIdDTO()).isEqualTo(entityCustomIdDTOBack);

        entityCustomIdDTOMapsId.entityCustomIdDTO(null);
        assertThat(entityCustomIdDTOMapsId.getEntityCustomIdDTO()).isNull();
    }

    @Test
    void oneToOneMapsIdBackTest() {
        EntityCustomIdDTOMapsId entityCustomIdDTOMapsId = getEntityCustomIdDTOMapsIdRandomSampleGenerator();
        EntityCustomIdDTORel entityCustomIdDTORelBack = getEntityCustomIdDTORelRandomSampleGenerator();

        entityCustomIdDTOMapsId.setOneToOneMapsIdBack(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTOMapsId.getOneToOneMapsIdBack()).isEqualTo(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTORelBack.getOneToOneMapsId()).isEqualTo(entityCustomIdDTOMapsId);

        entityCustomIdDTOMapsId.oneToOneMapsIdBack(null);
        assertThat(entityCustomIdDTOMapsId.getOneToOneMapsIdBack()).isNull();
        assertThat(entityCustomIdDTORelBack.getOneToOneMapsId()).isNull();
    }

    @Test
    void manyToOneMapsIdBackTest() {
        EntityCustomIdDTOMapsId entityCustomIdDTOMapsId = getEntityCustomIdDTOMapsIdRandomSampleGenerator();
        EntityCustomIdDTORel entityCustomIdDTORelBack = getEntityCustomIdDTORelRandomSampleGenerator();

        entityCustomIdDTOMapsId.addManyToOneMapsIdBack(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTOMapsId.getManyToOneMapsIdBacks()).containsOnly(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTORelBack.getManyToOneMapsId()).isEqualTo(entityCustomIdDTOMapsId);

        entityCustomIdDTOMapsId.removeManyToOneMapsIdBack(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTOMapsId.getManyToOneMapsIdBacks()).doesNotContain(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTORelBack.getManyToOneMapsId()).isNull();

        entityCustomIdDTOMapsId.manyToOneMapsIdBacks(new HashSet<>(Set.of(entityCustomIdDTORelBack)));
        assertThat(entityCustomIdDTOMapsId.getManyToOneMapsIdBacks()).containsOnly(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTORelBack.getManyToOneMapsId()).isEqualTo(entityCustomIdDTOMapsId);

        entityCustomIdDTOMapsId.setManyToOneMapsIdBacks(new HashSet<>());
        assertThat(entityCustomIdDTOMapsId.getManyToOneMapsIdBacks()).doesNotContain(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTORelBack.getManyToOneMapsId()).isNull();
    }

    @Test
    void manyToManyMapsIdBackTest() {
        EntityCustomIdDTOMapsId entityCustomIdDTOMapsId = getEntityCustomIdDTOMapsIdRandomSampleGenerator();
        EntityCustomIdDTORel entityCustomIdDTORelBack = getEntityCustomIdDTORelRandomSampleGenerator();

        entityCustomIdDTOMapsId.addManyToManyMapsIdBack(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTOMapsId.getManyToManyMapsIdBacks()).containsOnly(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTORelBack.getManyToManyMapsIds()).containsOnly(entityCustomIdDTOMapsId);

        entityCustomIdDTOMapsId.removeManyToManyMapsIdBack(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTOMapsId.getManyToManyMapsIdBacks()).doesNotContain(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTORelBack.getManyToManyMapsIds()).doesNotContain(entityCustomIdDTOMapsId);

        entityCustomIdDTOMapsId.manyToManyMapsIdBacks(new HashSet<>(Set.of(entityCustomIdDTORelBack)));
        assertThat(entityCustomIdDTOMapsId.getManyToManyMapsIdBacks()).containsOnly(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTORelBack.getManyToManyMapsIds()).containsOnly(entityCustomIdDTOMapsId);

        entityCustomIdDTOMapsId.setManyToManyMapsIdBacks(new HashSet<>());
        assertThat(entityCustomIdDTOMapsId.getManyToManyMapsIdBacks()).doesNotContain(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTORelBack.getManyToManyMapsIds()).doesNotContain(entityCustomIdDTOMapsId);
    }
}
