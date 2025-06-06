package tech.jhipster.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.sample.domain.EntityCustomIdDTOMapsIdTestSamples.*;
import static tech.jhipster.sample.domain.EntityCustomIdDTORelTestSamples.*;
import static tech.jhipster.sample.domain.EntityCustomIdDTOTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityCustomIdDTORelTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityCustomIdDTORel.class);
        EntityCustomIdDTORel entityCustomIdDTORel1 = getEntityCustomIdDTORelSample1();
        EntityCustomIdDTORel entityCustomIdDTORel2 = new EntityCustomIdDTORel();
        assertThat(entityCustomIdDTORel1).isNotEqualTo(entityCustomIdDTORel2);

        entityCustomIdDTORel2.setRelatedId(entityCustomIdDTORel1.getRelatedId());
        assertThat(entityCustomIdDTORel1).isEqualTo(entityCustomIdDTORel2);

        entityCustomIdDTORel2 = getEntityCustomIdDTORelSample2();
        assertThat(entityCustomIdDTORel1).isNotEqualTo(entityCustomIdDTORel2);
    }

    @Test
    void oneToOneTest() {
        EntityCustomIdDTORel entityCustomIdDTORel = getEntityCustomIdDTORelRandomSampleGenerator();
        EntityCustomIdDTO entityCustomIdDTOBack = getEntityCustomIdDTORandomSampleGenerator();

        entityCustomIdDTORel.setOneToOne(entityCustomIdDTOBack);
        assertThat(entityCustomIdDTORel.getOneToOne()).isEqualTo(entityCustomIdDTOBack);

        entityCustomIdDTORel.oneToOne(null);
        assertThat(entityCustomIdDTORel.getOneToOne()).isNull();
    }

    @Test
    void oneToOneMapsIdTest() {
        EntityCustomIdDTORel entityCustomIdDTORel = getEntityCustomIdDTORelRandomSampleGenerator();
        EntityCustomIdDTOMapsId entityCustomIdDTOMapsIdBack = getEntityCustomIdDTOMapsIdRandomSampleGenerator();

        entityCustomIdDTORel.setOneToOneMapsId(entityCustomIdDTOMapsIdBack);
        assertThat(entityCustomIdDTORel.getOneToOneMapsId()).isEqualTo(entityCustomIdDTOMapsIdBack);

        entityCustomIdDTORel.oneToOneMapsId(null);
        assertThat(entityCustomIdDTORel.getOneToOneMapsId()).isNull();
    }

    @Test
    void manyToOneTest() {
        EntityCustomIdDTORel entityCustomIdDTORel = getEntityCustomIdDTORelRandomSampleGenerator();
        EntityCustomIdDTO entityCustomIdDTOBack = getEntityCustomIdDTORandomSampleGenerator();

        entityCustomIdDTORel.setManyToOne(entityCustomIdDTOBack);
        assertThat(entityCustomIdDTORel.getManyToOne()).isEqualTo(entityCustomIdDTOBack);

        entityCustomIdDTORel.manyToOne(null);
        assertThat(entityCustomIdDTORel.getManyToOne()).isNull();
    }

    @Test
    void manyToOneMapsIdTest() {
        EntityCustomIdDTORel entityCustomIdDTORel = getEntityCustomIdDTORelRandomSampleGenerator();
        EntityCustomIdDTOMapsId entityCustomIdDTOMapsIdBack = getEntityCustomIdDTOMapsIdRandomSampleGenerator();

        entityCustomIdDTORel.setManyToOneMapsId(entityCustomIdDTOMapsIdBack);
        assertThat(entityCustomIdDTORel.getManyToOneMapsId()).isEqualTo(entityCustomIdDTOMapsIdBack);

        entityCustomIdDTORel.manyToOneMapsId(null);
        assertThat(entityCustomIdDTORel.getManyToOneMapsId()).isNull();
    }

    @Test
    void manyToManyTest() {
        EntityCustomIdDTORel entityCustomIdDTORel = getEntityCustomIdDTORelRandomSampleGenerator();
        EntityCustomIdDTO entityCustomIdDTOBack = getEntityCustomIdDTORandomSampleGenerator();

        entityCustomIdDTORel.addManyToMany(entityCustomIdDTOBack);
        assertThat(entityCustomIdDTORel.getManyToManies()).containsOnly(entityCustomIdDTOBack);

        entityCustomIdDTORel.removeManyToMany(entityCustomIdDTOBack);
        assertThat(entityCustomIdDTORel.getManyToManies()).doesNotContain(entityCustomIdDTOBack);

        entityCustomIdDTORel.manyToManies(new HashSet<>(Set.of(entityCustomIdDTOBack)));
        assertThat(entityCustomIdDTORel.getManyToManies()).containsOnly(entityCustomIdDTOBack);

        entityCustomIdDTORel.setManyToManies(new HashSet<>());
        assertThat(entityCustomIdDTORel.getManyToManies()).doesNotContain(entityCustomIdDTOBack);
    }

    @Test
    void manyToManyMapsIdTest() {
        EntityCustomIdDTORel entityCustomIdDTORel = getEntityCustomIdDTORelRandomSampleGenerator();
        EntityCustomIdDTOMapsId entityCustomIdDTOMapsIdBack = getEntityCustomIdDTOMapsIdRandomSampleGenerator();

        entityCustomIdDTORel.addManyToManyMapsId(entityCustomIdDTOMapsIdBack);
        assertThat(entityCustomIdDTORel.getManyToManyMapsIds()).containsOnly(entityCustomIdDTOMapsIdBack);

        entityCustomIdDTORel.removeManyToManyMapsId(entityCustomIdDTOMapsIdBack);
        assertThat(entityCustomIdDTORel.getManyToManyMapsIds()).doesNotContain(entityCustomIdDTOMapsIdBack);

        entityCustomIdDTORel.manyToManyMapsIds(new HashSet<>(Set.of(entityCustomIdDTOMapsIdBack)));
        assertThat(entityCustomIdDTORel.getManyToManyMapsIds()).containsOnly(entityCustomIdDTOMapsIdBack);

        entityCustomIdDTORel.setManyToManyMapsIds(new HashSet<>());
        assertThat(entityCustomIdDTORel.getManyToManyMapsIds()).doesNotContain(entityCustomIdDTOMapsIdBack);
    }
}
