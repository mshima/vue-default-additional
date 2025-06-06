package tech.jhipster.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.sample.domain.EntityCustomIdRequiredDTOMapsIdTestSamples.*;
import static tech.jhipster.sample.domain.EntityCustomIdRequiredDTORelTestSamples.*;
import static tech.jhipster.sample.domain.EntityCustomIdRequiredDTOTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityCustomIdRequiredDTOMapsIdTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityCustomIdRequiredDTOMapsId.class);
        EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsId1 = getEntityCustomIdRequiredDTOMapsIdSample1();
        EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsId2 = new EntityCustomIdRequiredDTOMapsId();
        assertThat(entityCustomIdRequiredDTOMapsId1).isNotEqualTo(entityCustomIdRequiredDTOMapsId2);

        entityCustomIdRequiredDTOMapsId2.setCustomId(entityCustomIdRequiredDTOMapsId1.getCustomId());
        assertThat(entityCustomIdRequiredDTOMapsId1).isEqualTo(entityCustomIdRequiredDTOMapsId2);

        entityCustomIdRequiredDTOMapsId2 = getEntityCustomIdRequiredDTOMapsIdSample2();
        assertThat(entityCustomIdRequiredDTOMapsId1).isNotEqualTo(entityCustomIdRequiredDTOMapsId2);
    }

    @Test
    void entityCustomIdRequiredDTOTest() {
        EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsId = getEntityCustomIdRequiredDTOMapsIdRandomSampleGenerator();
        EntityCustomIdRequiredDTO entityCustomIdRequiredDTOBack = getEntityCustomIdRequiredDTORandomSampleGenerator();

        entityCustomIdRequiredDTOMapsId.setEntityCustomIdRequiredDTO(entityCustomIdRequiredDTOBack);
        assertThat(entityCustomIdRequiredDTOMapsId.getEntityCustomIdRequiredDTO()).isEqualTo(entityCustomIdRequiredDTOBack);

        entityCustomIdRequiredDTOMapsId.entityCustomIdRequiredDTO(null);
        assertThat(entityCustomIdRequiredDTOMapsId.getEntityCustomIdRequiredDTO()).isNull();
    }

    @Test
    void oneToOneMapsIdBackTest() {
        EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsId = getEntityCustomIdRequiredDTOMapsIdRandomSampleGenerator();
        EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORelBack = getEntityCustomIdRequiredDTORelRandomSampleGenerator();

        entityCustomIdRequiredDTOMapsId.setOneToOneMapsIdBack(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTOMapsId.getOneToOneMapsIdBack()).isEqualTo(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTORelBack.getOneToOneMapsId()).isEqualTo(entityCustomIdRequiredDTOMapsId);

        entityCustomIdRequiredDTOMapsId.oneToOneMapsIdBack(null);
        assertThat(entityCustomIdRequiredDTOMapsId.getOneToOneMapsIdBack()).isNull();
        assertThat(entityCustomIdRequiredDTORelBack.getOneToOneMapsId()).isNull();
    }

    @Test
    void manyToOneMapsIdBackTest() {
        EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsId = getEntityCustomIdRequiredDTOMapsIdRandomSampleGenerator();
        EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORelBack = getEntityCustomIdRequiredDTORelRandomSampleGenerator();

        entityCustomIdRequiredDTOMapsId.addManyToOneMapsIdBack(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTOMapsId.getManyToOneMapsIdBacks()).containsOnly(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTORelBack.getManyToOneMapsId()).isEqualTo(entityCustomIdRequiredDTOMapsId);

        entityCustomIdRequiredDTOMapsId.removeManyToOneMapsIdBack(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTOMapsId.getManyToOneMapsIdBacks()).doesNotContain(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTORelBack.getManyToOneMapsId()).isNull();

        entityCustomIdRequiredDTOMapsId.manyToOneMapsIdBacks(new HashSet<>(Set.of(entityCustomIdRequiredDTORelBack)));
        assertThat(entityCustomIdRequiredDTOMapsId.getManyToOneMapsIdBacks()).containsOnly(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTORelBack.getManyToOneMapsId()).isEqualTo(entityCustomIdRequiredDTOMapsId);

        entityCustomIdRequiredDTOMapsId.setManyToOneMapsIdBacks(new HashSet<>());
        assertThat(entityCustomIdRequiredDTOMapsId.getManyToOneMapsIdBacks()).doesNotContain(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTORelBack.getManyToOneMapsId()).isNull();
    }

    @Test
    void manyToManyMapsIdBackTest() {
        EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsId = getEntityCustomIdRequiredDTOMapsIdRandomSampleGenerator();
        EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORelBack = getEntityCustomIdRequiredDTORelRandomSampleGenerator();

        entityCustomIdRequiredDTOMapsId.addManyToManyMapsIdBack(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTOMapsId.getManyToManyMapsIdBacks()).containsOnly(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTORelBack.getManyToManyMapsIds()).containsOnly(entityCustomIdRequiredDTOMapsId);

        entityCustomIdRequiredDTOMapsId.removeManyToManyMapsIdBack(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTOMapsId.getManyToManyMapsIdBacks()).doesNotContain(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTORelBack.getManyToManyMapsIds()).doesNotContain(entityCustomIdRequiredDTOMapsId);

        entityCustomIdRequiredDTOMapsId.manyToManyMapsIdBacks(new HashSet<>(Set.of(entityCustomIdRequiredDTORelBack)));
        assertThat(entityCustomIdRequiredDTOMapsId.getManyToManyMapsIdBacks()).containsOnly(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTORelBack.getManyToManyMapsIds()).containsOnly(entityCustomIdRequiredDTOMapsId);

        entityCustomIdRequiredDTOMapsId.setManyToManyMapsIdBacks(new HashSet<>());
        assertThat(entityCustomIdRequiredDTOMapsId.getManyToManyMapsIdBacks()).doesNotContain(entityCustomIdRequiredDTORelBack);
        assertThat(entityCustomIdRequiredDTORelBack.getManyToManyMapsIds()).doesNotContain(entityCustomIdRequiredDTOMapsId);
    }
}
