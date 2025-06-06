package tech.jhipster.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.sample.domain.EntityUuidIdDTOMapsIdTestSamples.*;
import static tech.jhipster.sample.domain.EntityUuidIdDTORelTestSamples.*;
import static tech.jhipster.sample.domain.EntityUuidIdDTOTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityUuidIdDTOMapsIdTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityUuidIdDTOMapsId.class);
        EntityUuidIdDTOMapsId entityUuidIdDTOMapsId1 = getEntityUuidIdDTOMapsIdSample1();
        EntityUuidIdDTOMapsId entityUuidIdDTOMapsId2 = new EntityUuidIdDTOMapsId();
        assertThat(entityUuidIdDTOMapsId1).isNotEqualTo(entityUuidIdDTOMapsId2);

        entityUuidIdDTOMapsId2.setId(entityUuidIdDTOMapsId1.getId());
        assertThat(entityUuidIdDTOMapsId1).isEqualTo(entityUuidIdDTOMapsId2);

        entityUuidIdDTOMapsId2 = getEntityUuidIdDTOMapsIdSample2();
        assertThat(entityUuidIdDTOMapsId1).isNotEqualTo(entityUuidIdDTOMapsId2);
    }

    @Test
    void entityUuidIdDTOTest() {
        EntityUuidIdDTOMapsId entityUuidIdDTOMapsId = getEntityUuidIdDTOMapsIdRandomSampleGenerator();
        EntityUuidIdDTO entityUuidIdDTOBack = getEntityUuidIdDTORandomSampleGenerator();

        entityUuidIdDTOMapsId.setEntityUuidIdDTO(entityUuidIdDTOBack);
        assertThat(entityUuidIdDTOMapsId.getEntityUuidIdDTO()).isEqualTo(entityUuidIdDTOBack);

        entityUuidIdDTOMapsId.entityUuidIdDTO(null);
        assertThat(entityUuidIdDTOMapsId.getEntityUuidIdDTO()).isNull();
    }

    @Test
    void oneToOneMapsIdBackTest() {
        EntityUuidIdDTOMapsId entityUuidIdDTOMapsId = getEntityUuidIdDTOMapsIdRandomSampleGenerator();
        EntityUuidIdDTORel entityUuidIdDTORelBack = getEntityUuidIdDTORelRandomSampleGenerator();

        entityUuidIdDTOMapsId.setOneToOneMapsIdBack(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTOMapsId.getOneToOneMapsIdBack()).isEqualTo(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTORelBack.getOneToOneMapsId()).isEqualTo(entityUuidIdDTOMapsId);

        entityUuidIdDTOMapsId.oneToOneMapsIdBack(null);
        assertThat(entityUuidIdDTOMapsId.getOneToOneMapsIdBack()).isNull();
        assertThat(entityUuidIdDTORelBack.getOneToOneMapsId()).isNull();
    }

    @Test
    void manyToOneMapsIdBackTest() {
        EntityUuidIdDTOMapsId entityUuidIdDTOMapsId = getEntityUuidIdDTOMapsIdRandomSampleGenerator();
        EntityUuidIdDTORel entityUuidIdDTORelBack = getEntityUuidIdDTORelRandomSampleGenerator();

        entityUuidIdDTOMapsId.addManyToOneMapsIdBack(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTOMapsId.getManyToOneMapsIdBacks()).containsOnly(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTORelBack.getManyToOneMapsId()).isEqualTo(entityUuidIdDTOMapsId);

        entityUuidIdDTOMapsId.removeManyToOneMapsIdBack(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTOMapsId.getManyToOneMapsIdBacks()).doesNotContain(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTORelBack.getManyToOneMapsId()).isNull();

        entityUuidIdDTOMapsId.manyToOneMapsIdBacks(new HashSet<>(Set.of(entityUuidIdDTORelBack)));
        assertThat(entityUuidIdDTOMapsId.getManyToOneMapsIdBacks()).containsOnly(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTORelBack.getManyToOneMapsId()).isEqualTo(entityUuidIdDTOMapsId);

        entityUuidIdDTOMapsId.setManyToOneMapsIdBacks(new HashSet<>());
        assertThat(entityUuidIdDTOMapsId.getManyToOneMapsIdBacks()).doesNotContain(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTORelBack.getManyToOneMapsId()).isNull();
    }

    @Test
    void manyToManyMapsIdBackTest() {
        EntityUuidIdDTOMapsId entityUuidIdDTOMapsId = getEntityUuidIdDTOMapsIdRandomSampleGenerator();
        EntityUuidIdDTORel entityUuidIdDTORelBack = getEntityUuidIdDTORelRandomSampleGenerator();

        entityUuidIdDTOMapsId.addManyToManyMapsIdBack(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTOMapsId.getManyToManyMapsIdBacks()).containsOnly(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTORelBack.getManyToManyMapsIds()).containsOnly(entityUuidIdDTOMapsId);

        entityUuidIdDTOMapsId.removeManyToManyMapsIdBack(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTOMapsId.getManyToManyMapsIdBacks()).doesNotContain(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTORelBack.getManyToManyMapsIds()).doesNotContain(entityUuidIdDTOMapsId);

        entityUuidIdDTOMapsId.manyToManyMapsIdBacks(new HashSet<>(Set.of(entityUuidIdDTORelBack)));
        assertThat(entityUuidIdDTOMapsId.getManyToManyMapsIdBacks()).containsOnly(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTORelBack.getManyToManyMapsIds()).containsOnly(entityUuidIdDTOMapsId);

        entityUuidIdDTOMapsId.setManyToManyMapsIdBacks(new HashSet<>());
        assertThat(entityUuidIdDTOMapsId.getManyToManyMapsIdBacks()).doesNotContain(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTORelBack.getManyToManyMapsIds()).doesNotContain(entityUuidIdDTOMapsId);
    }
}
