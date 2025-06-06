package tech.jhipster.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.sample.domain.EntityCustomIdDTOMapsIdTestSamples.*;
import static tech.jhipster.sample.domain.EntityCustomIdDTORelTestSamples.*;
import static tech.jhipster.sample.domain.EntityCustomIdDTOTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityCustomIdDTOTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityCustomIdDTO.class);
        EntityCustomIdDTO entityCustomIdDTO1 = getEntityCustomIdDTOSample1();
        EntityCustomIdDTO entityCustomIdDTO2 = new EntityCustomIdDTO();
        assertThat(entityCustomIdDTO1).isNotEqualTo(entityCustomIdDTO2);

        entityCustomIdDTO2.setCustomId(entityCustomIdDTO1.getCustomId());
        assertThat(entityCustomIdDTO1).isEqualTo(entityCustomIdDTO2);

        entityCustomIdDTO2 = getEntityCustomIdDTOSample2();
        assertThat(entityCustomIdDTO1).isNotEqualTo(entityCustomIdDTO2);
    }

    @Test
    void entityCustomIdDTOMapsIdTest() {
        EntityCustomIdDTO entityCustomIdDTO = getEntityCustomIdDTORandomSampleGenerator();
        EntityCustomIdDTOMapsId entityCustomIdDTOMapsIdBack = getEntityCustomIdDTOMapsIdRandomSampleGenerator();

        entityCustomIdDTO.setEntityCustomIdDTOMapsId(entityCustomIdDTOMapsIdBack);
        assertThat(entityCustomIdDTO.getEntityCustomIdDTOMapsId()).isEqualTo(entityCustomIdDTOMapsIdBack);
        assertThat(entityCustomIdDTOMapsIdBack.getEntityCustomIdDTO()).isEqualTo(entityCustomIdDTO);

        entityCustomIdDTO.entityCustomIdDTOMapsId(null);
        assertThat(entityCustomIdDTO.getEntityCustomIdDTOMapsId()).isNull();
        assertThat(entityCustomIdDTOMapsIdBack.getEntityCustomIdDTO()).isNull();
    }

    @Test
    void oneToOneBackTest() {
        EntityCustomIdDTO entityCustomIdDTO = getEntityCustomIdDTORandomSampleGenerator();
        EntityCustomIdDTORel entityCustomIdDTORelBack = getEntityCustomIdDTORelRandomSampleGenerator();

        entityCustomIdDTO.setOneToOneBack(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTO.getOneToOneBack()).isEqualTo(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTORelBack.getOneToOne()).isEqualTo(entityCustomIdDTO);

        entityCustomIdDTO.oneToOneBack(null);
        assertThat(entityCustomIdDTO.getOneToOneBack()).isNull();
        assertThat(entityCustomIdDTORelBack.getOneToOne()).isNull();
    }

    @Test
    void manyToOneBackTest() {
        EntityCustomIdDTO entityCustomIdDTO = getEntityCustomIdDTORandomSampleGenerator();
        EntityCustomIdDTORel entityCustomIdDTORelBack = getEntityCustomIdDTORelRandomSampleGenerator();

        entityCustomIdDTO.addManyToOneBack(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTO.getManyToOneBacks()).containsOnly(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTORelBack.getManyToOne()).isEqualTo(entityCustomIdDTO);

        entityCustomIdDTO.removeManyToOneBack(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTO.getManyToOneBacks()).doesNotContain(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTORelBack.getManyToOne()).isNull();

        entityCustomIdDTO.manyToOneBacks(new HashSet<>(Set.of(entityCustomIdDTORelBack)));
        assertThat(entityCustomIdDTO.getManyToOneBacks()).containsOnly(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTORelBack.getManyToOne()).isEqualTo(entityCustomIdDTO);

        entityCustomIdDTO.setManyToOneBacks(new HashSet<>());
        assertThat(entityCustomIdDTO.getManyToOneBacks()).doesNotContain(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTORelBack.getManyToOne()).isNull();
    }

    @Test
    void manyToManyBackTest() {
        EntityCustomIdDTO entityCustomIdDTO = getEntityCustomIdDTORandomSampleGenerator();
        EntityCustomIdDTORel entityCustomIdDTORelBack = getEntityCustomIdDTORelRandomSampleGenerator();

        entityCustomIdDTO.addManyToManyBack(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTO.getManyToManyBacks()).containsOnly(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTORelBack.getManyToManies()).containsOnly(entityCustomIdDTO);

        entityCustomIdDTO.removeManyToManyBack(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTO.getManyToManyBacks()).doesNotContain(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTORelBack.getManyToManies()).doesNotContain(entityCustomIdDTO);

        entityCustomIdDTO.manyToManyBacks(new HashSet<>(Set.of(entityCustomIdDTORelBack)));
        assertThat(entityCustomIdDTO.getManyToManyBacks()).containsOnly(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTORelBack.getManyToManies()).containsOnly(entityCustomIdDTO);

        entityCustomIdDTO.setManyToManyBacks(new HashSet<>());
        assertThat(entityCustomIdDTO.getManyToManyBacks()).doesNotContain(entityCustomIdDTORelBack);
        assertThat(entityCustomIdDTORelBack.getManyToManies()).doesNotContain(entityCustomIdDTO);
    }
}
