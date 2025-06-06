package tech.jhipster.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.sample.domain.EntityCustomIdRequiredDTOMapsIdTestSamples.*;
import static tech.jhipster.sample.domain.EntityCustomIdRequiredDTORelTestSamples.*;
import static tech.jhipster.sample.domain.EntityCustomIdRequiredDTOTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityCustomIdRequiredDTORelTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityCustomIdRequiredDTORel.class);
        EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel1 = getEntityCustomIdRequiredDTORelSample1();
        EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel2 = new EntityCustomIdRequiredDTORel();
        assertThat(entityCustomIdRequiredDTORel1).isNotEqualTo(entityCustomIdRequiredDTORel2);

        entityCustomIdRequiredDTORel2.setRelatedId(entityCustomIdRequiredDTORel1.getRelatedId());
        assertThat(entityCustomIdRequiredDTORel1).isEqualTo(entityCustomIdRequiredDTORel2);

        entityCustomIdRequiredDTORel2 = getEntityCustomIdRequiredDTORelSample2();
        assertThat(entityCustomIdRequiredDTORel1).isNotEqualTo(entityCustomIdRequiredDTORel2);
    }

    @Test
    void oneToOneTest() {
        EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel = getEntityCustomIdRequiredDTORelRandomSampleGenerator();
        EntityCustomIdRequiredDTO entityCustomIdRequiredDTOBack = getEntityCustomIdRequiredDTORandomSampleGenerator();

        entityCustomIdRequiredDTORel.setOneToOne(entityCustomIdRequiredDTOBack);
        assertThat(entityCustomIdRequiredDTORel.getOneToOne()).isEqualTo(entityCustomIdRequiredDTOBack);

        entityCustomIdRequiredDTORel.oneToOne(null);
        assertThat(entityCustomIdRequiredDTORel.getOneToOne()).isNull();
    }

    @Test
    void oneToOneMapsIdTest() {
        EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel = getEntityCustomIdRequiredDTORelRandomSampleGenerator();
        EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsIdBack = getEntityCustomIdRequiredDTOMapsIdRandomSampleGenerator();

        entityCustomIdRequiredDTORel.setOneToOneMapsId(entityCustomIdRequiredDTOMapsIdBack);
        assertThat(entityCustomIdRequiredDTORel.getOneToOneMapsId()).isEqualTo(entityCustomIdRequiredDTOMapsIdBack);

        entityCustomIdRequiredDTORel.oneToOneMapsId(null);
        assertThat(entityCustomIdRequiredDTORel.getOneToOneMapsId()).isNull();
    }

    @Test
    void manyToOneTest() {
        EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel = getEntityCustomIdRequiredDTORelRandomSampleGenerator();
        EntityCustomIdRequiredDTO entityCustomIdRequiredDTOBack = getEntityCustomIdRequiredDTORandomSampleGenerator();

        entityCustomIdRequiredDTORel.setManyToOne(entityCustomIdRequiredDTOBack);
        assertThat(entityCustomIdRequiredDTORel.getManyToOne()).isEqualTo(entityCustomIdRequiredDTOBack);

        entityCustomIdRequiredDTORel.manyToOne(null);
        assertThat(entityCustomIdRequiredDTORel.getManyToOne()).isNull();
    }

    @Test
    void manyToOneMapsIdTest() {
        EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel = getEntityCustomIdRequiredDTORelRandomSampleGenerator();
        EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsIdBack = getEntityCustomIdRequiredDTOMapsIdRandomSampleGenerator();

        entityCustomIdRequiredDTORel.setManyToOneMapsId(entityCustomIdRequiredDTOMapsIdBack);
        assertThat(entityCustomIdRequiredDTORel.getManyToOneMapsId()).isEqualTo(entityCustomIdRequiredDTOMapsIdBack);

        entityCustomIdRequiredDTORel.manyToOneMapsId(null);
        assertThat(entityCustomIdRequiredDTORel.getManyToOneMapsId()).isNull();
    }

    @Test
    void manyToManyTest() {
        EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel = getEntityCustomIdRequiredDTORelRandomSampleGenerator();
        EntityCustomIdRequiredDTO entityCustomIdRequiredDTOBack = getEntityCustomIdRequiredDTORandomSampleGenerator();

        entityCustomIdRequiredDTORel.addManyToMany(entityCustomIdRequiredDTOBack);
        assertThat(entityCustomIdRequiredDTORel.getManyToManies()).containsOnly(entityCustomIdRequiredDTOBack);

        entityCustomIdRequiredDTORel.removeManyToMany(entityCustomIdRequiredDTOBack);
        assertThat(entityCustomIdRequiredDTORel.getManyToManies()).doesNotContain(entityCustomIdRequiredDTOBack);

        entityCustomIdRequiredDTORel.manyToManies(new HashSet<>(Set.of(entityCustomIdRequiredDTOBack)));
        assertThat(entityCustomIdRequiredDTORel.getManyToManies()).containsOnly(entityCustomIdRequiredDTOBack);

        entityCustomIdRequiredDTORel.setManyToManies(new HashSet<>());
        assertThat(entityCustomIdRequiredDTORel.getManyToManies()).doesNotContain(entityCustomIdRequiredDTOBack);
    }

    @Test
    void manyToManyMapsIdTest() {
        EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel = getEntityCustomIdRequiredDTORelRandomSampleGenerator();
        EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsIdBack = getEntityCustomIdRequiredDTOMapsIdRandomSampleGenerator();

        entityCustomIdRequiredDTORel.addManyToManyMapsId(entityCustomIdRequiredDTOMapsIdBack);
        assertThat(entityCustomIdRequiredDTORel.getManyToManyMapsIds()).containsOnly(entityCustomIdRequiredDTOMapsIdBack);

        entityCustomIdRequiredDTORel.removeManyToManyMapsId(entityCustomIdRequiredDTOMapsIdBack);
        assertThat(entityCustomIdRequiredDTORel.getManyToManyMapsIds()).doesNotContain(entityCustomIdRequiredDTOMapsIdBack);

        entityCustomIdRequiredDTORel.manyToManyMapsIds(new HashSet<>(Set.of(entityCustomIdRequiredDTOMapsIdBack)));
        assertThat(entityCustomIdRequiredDTORel.getManyToManyMapsIds()).containsOnly(entityCustomIdRequiredDTOMapsIdBack);

        entityCustomIdRequiredDTORel.setManyToManyMapsIds(new HashSet<>());
        assertThat(entityCustomIdRequiredDTORel.getManyToManyMapsIds()).doesNotContain(entityCustomIdRequiredDTOMapsIdBack);
    }
}
