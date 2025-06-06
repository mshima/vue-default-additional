package tech.jhipster.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.sample.domain.EntityUuidIdDTOMapsIdTestSamples.*;
import static tech.jhipster.sample.domain.EntityUuidIdDTORelTestSamples.*;
import static tech.jhipster.sample.domain.EntityUuidIdDTOTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityUuidIdDTOTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityUuidIdDTO.class);
        EntityUuidIdDTO entityUuidIdDTO1 = getEntityUuidIdDTOSample1();
        EntityUuidIdDTO entityUuidIdDTO2 = new EntityUuidIdDTO();
        assertThat(entityUuidIdDTO1).isNotEqualTo(entityUuidIdDTO2);

        entityUuidIdDTO2.setId(entityUuidIdDTO1.getId());
        assertThat(entityUuidIdDTO1).isEqualTo(entityUuidIdDTO2);

        entityUuidIdDTO2 = getEntityUuidIdDTOSample2();
        assertThat(entityUuidIdDTO1).isNotEqualTo(entityUuidIdDTO2);
    }

    @Test
    void entityUuidIdDTOMapsIdTest() {
        EntityUuidIdDTO entityUuidIdDTO = getEntityUuidIdDTORandomSampleGenerator();
        EntityUuidIdDTOMapsId entityUuidIdDTOMapsIdBack = getEntityUuidIdDTOMapsIdRandomSampleGenerator();

        entityUuidIdDTO.setEntityUuidIdDTOMapsId(entityUuidIdDTOMapsIdBack);
        assertThat(entityUuidIdDTO.getEntityUuidIdDTOMapsId()).isEqualTo(entityUuidIdDTOMapsIdBack);
        assertThat(entityUuidIdDTOMapsIdBack.getEntityUuidIdDTO()).isEqualTo(entityUuidIdDTO);

        entityUuidIdDTO.entityUuidIdDTOMapsId(null);
        assertThat(entityUuidIdDTO.getEntityUuidIdDTOMapsId()).isNull();
        assertThat(entityUuidIdDTOMapsIdBack.getEntityUuidIdDTO()).isNull();
    }

    @Test
    void oneToOneBackTest() {
        EntityUuidIdDTO entityUuidIdDTO = getEntityUuidIdDTORandomSampleGenerator();
        EntityUuidIdDTORel entityUuidIdDTORelBack = getEntityUuidIdDTORelRandomSampleGenerator();

        entityUuidIdDTO.setOneToOneBack(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTO.getOneToOneBack()).isEqualTo(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTORelBack.getOneToOne()).isEqualTo(entityUuidIdDTO);

        entityUuidIdDTO.oneToOneBack(null);
        assertThat(entityUuidIdDTO.getOneToOneBack()).isNull();
        assertThat(entityUuidIdDTORelBack.getOneToOne()).isNull();
    }

    @Test
    void manyToOneBackTest() {
        EntityUuidIdDTO entityUuidIdDTO = getEntityUuidIdDTORandomSampleGenerator();
        EntityUuidIdDTORel entityUuidIdDTORelBack = getEntityUuidIdDTORelRandomSampleGenerator();

        entityUuidIdDTO.addManyToOneBack(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTO.getManyToOneBacks()).containsOnly(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTORelBack.getManyToOne()).isEqualTo(entityUuidIdDTO);

        entityUuidIdDTO.removeManyToOneBack(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTO.getManyToOneBacks()).doesNotContain(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTORelBack.getManyToOne()).isNull();

        entityUuidIdDTO.manyToOneBacks(new HashSet<>(Set.of(entityUuidIdDTORelBack)));
        assertThat(entityUuidIdDTO.getManyToOneBacks()).containsOnly(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTORelBack.getManyToOne()).isEqualTo(entityUuidIdDTO);

        entityUuidIdDTO.setManyToOneBacks(new HashSet<>());
        assertThat(entityUuidIdDTO.getManyToOneBacks()).doesNotContain(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTORelBack.getManyToOne()).isNull();
    }

    @Test
    void manyToManyBackTest() {
        EntityUuidIdDTO entityUuidIdDTO = getEntityUuidIdDTORandomSampleGenerator();
        EntityUuidIdDTORel entityUuidIdDTORelBack = getEntityUuidIdDTORelRandomSampleGenerator();

        entityUuidIdDTO.addManyToManyBack(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTO.getManyToManyBacks()).containsOnly(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTORelBack.getManyToManies()).containsOnly(entityUuidIdDTO);

        entityUuidIdDTO.removeManyToManyBack(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTO.getManyToManyBacks()).doesNotContain(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTORelBack.getManyToManies()).doesNotContain(entityUuidIdDTO);

        entityUuidIdDTO.manyToManyBacks(new HashSet<>(Set.of(entityUuidIdDTORelBack)));
        assertThat(entityUuidIdDTO.getManyToManyBacks()).containsOnly(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTORelBack.getManyToManies()).containsOnly(entityUuidIdDTO);

        entityUuidIdDTO.setManyToManyBacks(new HashSet<>());
        assertThat(entityUuidIdDTO.getManyToManyBacks()).doesNotContain(entityUuidIdDTORelBack);
        assertThat(entityUuidIdDTORelBack.getManyToManies()).doesNotContain(entityUuidIdDTO);
    }
}
