package tech.jhipster.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.sample.domain.EntityCustomIdRequiredDTOMapsIdTestSamples.*;
import static tech.jhipster.sample.domain.EntityCustomIdRequiredDTORelTestSamples.*;
import static tech.jhipster.sample.domain.EntityCustomIdRequiredDTOTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityCustomIdRequiredDTOTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityCustomIdRequiredDTO.class);
        EntityCustomIdRequiredDTO entityCustomIdRequiredDTO1 = getEntityCustomIdRequiredDTOSample1();
        EntityCustomIdRequiredDTO entityCustomIdRequiredDTO2 = new EntityCustomIdRequiredDTO();
        assertThat(entityCustomIdRequiredDTO1).isNotEqualTo(entityCustomIdRequiredDTO2);

        entityCustomIdRequiredDTO2.setCustomId(entityCustomIdRequiredDTO1.getCustomId());
        assertThat(entityCustomIdRequiredDTO1).isEqualTo(entityCustomIdRequiredDTO2);

        entityCustomIdRequiredDTO2 = getEntityCustomIdRequiredDTOSample2();
        assertThat(entityCustomIdRequiredDTO1).isNotEqualTo(entityCustomIdRequiredDTO2);
    }

    @Test
    void entityCustomIdRequiredDTOMapsIdTest() {
        EntityCustomIdRequiredDTO entityCustomIdRequiredDTO = getEntityCustomIdRequiredDTORandomSampleGenerator();
        EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsIdBack = getEntityCustomIdRequiredDTOMapsIdRandomSampleGenerator();

        entityCustomIdRequiredDTO.setEntityCustomIdRequiredDTOMapsId(entityCustomIdRequiredDTOMapsIdBack);
        assertThat(entityCustomIdRequiredDTO.getEntityCustomIdRequiredDTOMapsId()).isEqualTo(entityCustomIdRequiredDTOMapsIdBack);
        assertThat(entityCustomIdRequiredDTOMapsIdBack.getEntityCustomIdRequiredDTO()).isEqualTo(entityCustomIdRequiredDTO);

        entityCustomIdRequiredDTO.entityCustomIdRequiredDTOMapsId(null);
        assertThat(entityCustomIdRequiredDTO.getEntityCustomIdRequiredDTOMapsId()).isNull();
        assertThat(entityCustomIdRequiredDTOMapsIdBack.getEntityCustomIdRequiredDTO()).isNull();
    }

    @Test
    void oneToOneBackTest() {
        EntityCustomIdRequiredDTO entityCustomIdRequiredDTO = getEntityCustomIdRequiredDTORandomSampleGenerator();
        EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORelBack = getEntityCustomIdRequiredDTORelRandomSampleGenerator();

        entityCustomIdRequiredDTO.setOneToOneBack(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTO.getOneToOneBack()).isEqualTo(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTORelBack.getOneToOne()).isEqualTo(entityCustomIdRequiredDTO);

        entityCustomIdRequiredDTO.oneToOneBack(null);
        assertThat(entityCustomIdRequiredDTO.getOneToOneBack()).isNull();
        assertThat(entityCustomIdRequiredDTORelBack.getOneToOne()).isNull();
    }

    @Test
    void manyToOneBackTest() {
        EntityCustomIdRequiredDTO entityCustomIdRequiredDTO = getEntityCustomIdRequiredDTORandomSampleGenerator();
        EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORelBack = getEntityCustomIdRequiredDTORelRandomSampleGenerator();

        entityCustomIdRequiredDTO.addManyToOneBack(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTO.getManyToOneBacks()).containsOnly(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTORelBack.getManyToOne()).isEqualTo(entityCustomIdRequiredDTO);

        entityCustomIdRequiredDTO.removeManyToOneBack(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTO.getManyToOneBacks()).doesNotContain(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTORelBack.getManyToOne()).isNull();

        entityCustomIdRequiredDTO.manyToOneBacks(new HashSet<>(Set.of(entityCustomIdRequiredDTORelBack)));
        assertThat(entityCustomIdRequiredDTO.getManyToOneBacks()).containsOnly(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTORelBack.getManyToOne()).isEqualTo(entityCustomIdRequiredDTO);

        entityCustomIdRequiredDTO.setManyToOneBacks(new HashSet<>());
        assertThat(entityCustomIdRequiredDTO.getManyToOneBacks()).doesNotContain(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTORelBack.getManyToOne()).isNull();
    }

    @Test
    void manyToManyBackTest() {
        EntityCustomIdRequiredDTO entityCustomIdRequiredDTO = getEntityCustomIdRequiredDTORandomSampleGenerator();
        EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORelBack = getEntityCustomIdRequiredDTORelRandomSampleGenerator();

        entityCustomIdRequiredDTO.addManyToManyBack(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTO.getManyToManyBacks()).containsOnly(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTORelBack.getManyToManies()).containsOnly(entityCustomIdRequiredDTO);

        entityCustomIdRequiredDTO.removeManyToManyBack(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTO.getManyToManyBacks()).doesNotContain(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTORelBack.getManyToManies()).doesNotContain(entityCustomIdRequiredDTO);

        entityCustomIdRequiredDTO.manyToManyBacks(new HashSet<>(Set.of(entityCustomIdRequiredDTORelBack)));
        assertThat(entityCustomIdRequiredDTO.getManyToManyBacks()).containsOnly(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTORelBack.getManyToManies()).containsOnly(entityCustomIdRequiredDTO);

        entityCustomIdRequiredDTO.setManyToManyBacks(new HashSet<>());
        assertThat(entityCustomIdRequiredDTO.getManyToManyBacks()).doesNotContain(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTORelBack.getManyToManies()).doesNotContain(entityCustomIdRequiredDTO);
    }
}
