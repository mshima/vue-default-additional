package tech.jhipster.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.sample.domain.EntityUuidIdDTOMapsIdTestSamples.*;
import static tech.jhipster.sample.domain.EntityUuidIdDTORelTestSamples.*;
import static tech.jhipster.sample.domain.EntityUuidIdDTOTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityUuidIdDTORelTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityUuidIdDTORel.class);
        EntityUuidIdDTORel entityUuidIdDTORel1 = getEntityUuidIdDTORelSample1();
        EntityUuidIdDTORel entityUuidIdDTORel2 = new EntityUuidIdDTORel();
        assertThat(entityUuidIdDTORel1).isNotEqualTo(entityUuidIdDTORel2);

        entityUuidIdDTORel2.setId(entityUuidIdDTORel1.getId());
        assertThat(entityUuidIdDTORel1).isEqualTo(entityUuidIdDTORel2);

        entityUuidIdDTORel2 = getEntityUuidIdDTORelSample2();
        assertThat(entityUuidIdDTORel1).isNotEqualTo(entityUuidIdDTORel2);
    }

    @Test
    void oneToOneTest() {
        EntityUuidIdDTORel entityUuidIdDTORel = getEntityUuidIdDTORelRandomSampleGenerator();
        EntityUuidIdDTO entityUuidIdDTOBack = getEntityUuidIdDTORandomSampleGenerator();

        entityUuidIdDTORel.setOneToOne(entityUuidIdDTOBack);
        assertThat(entityUuidIdDTORel.getOneToOne()).isEqualTo(entityUuidIdDTOBack);

        entityUuidIdDTORel.oneToOne(null);
        assertThat(entityUuidIdDTORel.getOneToOne()).isNull();
    }

    @Test
    void oneToOneMapsIdTest() {
        EntityUuidIdDTORel entityUuidIdDTORel = getEntityUuidIdDTORelRandomSampleGenerator();
        EntityUuidIdDTOMapsId entityUuidIdDTOMapsIdBack = getEntityUuidIdDTOMapsIdRandomSampleGenerator();

        entityUuidIdDTORel.setOneToOneMapsId(entityUuidIdDTOMapsIdBack);
        assertThat(entityUuidIdDTORel.getOneToOneMapsId()).isEqualTo(entityUuidIdDTOMapsIdBack);

        entityUuidIdDTORel.oneToOneMapsId(null);
        assertThat(entityUuidIdDTORel.getOneToOneMapsId()).isNull();
    }

    @Test
    void manyToOneTest() {
        EntityUuidIdDTORel entityUuidIdDTORel = getEntityUuidIdDTORelRandomSampleGenerator();
        EntityUuidIdDTO entityUuidIdDTOBack = getEntityUuidIdDTORandomSampleGenerator();

        entityUuidIdDTORel.setManyToOne(entityUuidIdDTOBack);
        assertThat(entityUuidIdDTORel.getManyToOne()).isEqualTo(entityUuidIdDTOBack);

        entityUuidIdDTORel.manyToOne(null);
        assertThat(entityUuidIdDTORel.getManyToOne()).isNull();
    }

    @Test
    void manyToOneMapsIdTest() {
        EntityUuidIdDTORel entityUuidIdDTORel = getEntityUuidIdDTORelRandomSampleGenerator();
        EntityUuidIdDTOMapsId entityUuidIdDTOMapsIdBack = getEntityUuidIdDTOMapsIdRandomSampleGenerator();

        entityUuidIdDTORel.setManyToOneMapsId(entityUuidIdDTOMapsIdBack);
        assertThat(entityUuidIdDTORel.getManyToOneMapsId()).isEqualTo(entityUuidIdDTOMapsIdBack);

        entityUuidIdDTORel.manyToOneMapsId(null);
        assertThat(entityUuidIdDTORel.getManyToOneMapsId()).isNull();
    }

    @Test
    void manyToManyTest() {
        EntityUuidIdDTORel entityUuidIdDTORel = getEntityUuidIdDTORelRandomSampleGenerator();
        EntityUuidIdDTO entityUuidIdDTOBack = getEntityUuidIdDTORandomSampleGenerator();

        entityUuidIdDTORel.addManyToMany(entityUuidIdDTOBack);
        assertThat(entityUuidIdDTORel.getManyToManies()).containsOnly(entityUuidIdDTOBack);

        entityUuidIdDTORel.removeManyToMany(entityUuidIdDTOBack);
        assertThat(entityUuidIdDTORel.getManyToManies()).doesNotContain(entityUuidIdDTOBack);

        entityUuidIdDTORel.manyToManies(new HashSet<>(Set.of(entityUuidIdDTOBack)));
        assertThat(entityUuidIdDTORel.getManyToManies()).containsOnly(entityUuidIdDTOBack);

        entityUuidIdDTORel.setManyToManies(new HashSet<>());
        assertThat(entityUuidIdDTORel.getManyToManies()).doesNotContain(entityUuidIdDTOBack);
    }

    @Test
    void manyToManyMapsIdTest() {
        EntityUuidIdDTORel entityUuidIdDTORel = getEntityUuidIdDTORelRandomSampleGenerator();
        EntityUuidIdDTOMapsId entityUuidIdDTOMapsIdBack = getEntityUuidIdDTOMapsIdRandomSampleGenerator();

        entityUuidIdDTORel.addManyToManyMapsId(entityUuidIdDTOMapsIdBack);
        assertThat(entityUuidIdDTORel.getManyToManyMapsIds()).containsOnly(entityUuidIdDTOMapsIdBack);

        entityUuidIdDTORel.removeManyToManyMapsId(entityUuidIdDTOMapsIdBack);
        assertThat(entityUuidIdDTORel.getManyToManyMapsIds()).doesNotContain(entityUuidIdDTOMapsIdBack);

        entityUuidIdDTORel.manyToManyMapsIds(new HashSet<>(Set.of(entityUuidIdDTOMapsIdBack)));
        assertThat(entityUuidIdDTORel.getManyToManyMapsIds()).containsOnly(entityUuidIdDTOMapsIdBack);

        entityUuidIdDTORel.setManyToManyMapsIds(new HashSet<>());
        assertThat(entityUuidIdDTORel.getManyToManyMapsIds()).doesNotContain(entityUuidIdDTOMapsIdBack);
    }
}
