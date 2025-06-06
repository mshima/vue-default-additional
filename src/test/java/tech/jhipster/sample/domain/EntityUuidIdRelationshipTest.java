package tech.jhipster.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.sample.domain.EntityUuidIdMapsIdTestSamples.*;
import static tech.jhipster.sample.domain.EntityUuidIdRelationshipTestSamples.*;
import static tech.jhipster.sample.domain.EntityUuidIdTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityUuidIdRelationshipTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityUuidIdRelationship.class);
        EntityUuidIdRelationship entityUuidIdRelationship1 = getEntityUuidIdRelationshipSample1();
        EntityUuidIdRelationship entityUuidIdRelationship2 = new EntityUuidIdRelationship();
        assertThat(entityUuidIdRelationship1).isNotEqualTo(entityUuidIdRelationship2);

        entityUuidIdRelationship2.setId(entityUuidIdRelationship1.getId());
        assertThat(entityUuidIdRelationship1).isEqualTo(entityUuidIdRelationship2);

        entityUuidIdRelationship2 = getEntityUuidIdRelationshipSample2();
        assertThat(entityUuidIdRelationship1).isNotEqualTo(entityUuidIdRelationship2);
    }

    @Test
    void oneToOneTest() {
        EntityUuidIdRelationship entityUuidIdRelationship = getEntityUuidIdRelationshipRandomSampleGenerator();
        EntityUuidId entityUuidIdBack = getEntityUuidIdRandomSampleGenerator();

        entityUuidIdRelationship.setOneToOne(entityUuidIdBack);
        assertThat(entityUuidIdRelationship.getOneToOne()).isEqualTo(entityUuidIdBack);

        entityUuidIdRelationship.oneToOne(null);
        assertThat(entityUuidIdRelationship.getOneToOne()).isNull();
    }

    @Test
    void oneToOneMapsIdTest() {
        EntityUuidIdRelationship entityUuidIdRelationship = getEntityUuidIdRelationshipRandomSampleGenerator();
        EntityUuidIdMapsId entityUuidIdMapsIdBack = getEntityUuidIdMapsIdRandomSampleGenerator();

        entityUuidIdRelationship.setOneToOneMapsId(entityUuidIdMapsIdBack);
        assertThat(entityUuidIdRelationship.getOneToOneMapsId()).isEqualTo(entityUuidIdMapsIdBack);

        entityUuidIdRelationship.oneToOneMapsId(null);
        assertThat(entityUuidIdRelationship.getOneToOneMapsId()).isNull();
    }

    @Test
    void manyToOneTest() {
        EntityUuidIdRelationship entityUuidIdRelationship = getEntityUuidIdRelationshipRandomSampleGenerator();
        EntityUuidId entityUuidIdBack = getEntityUuidIdRandomSampleGenerator();

        entityUuidIdRelationship.setManyToOne(entityUuidIdBack);
        assertThat(entityUuidIdRelationship.getManyToOne()).isEqualTo(entityUuidIdBack);

        entityUuidIdRelationship.manyToOne(null);
        assertThat(entityUuidIdRelationship.getManyToOne()).isNull();
    }

    @Test
    void manyToOneMapsIdTest() {
        EntityUuidIdRelationship entityUuidIdRelationship = getEntityUuidIdRelationshipRandomSampleGenerator();
        EntityUuidIdMapsId entityUuidIdMapsIdBack = getEntityUuidIdMapsIdRandomSampleGenerator();

        entityUuidIdRelationship.setManyToOneMapsId(entityUuidIdMapsIdBack);
        assertThat(entityUuidIdRelationship.getManyToOneMapsId()).isEqualTo(entityUuidIdMapsIdBack);

        entityUuidIdRelationship.manyToOneMapsId(null);
        assertThat(entityUuidIdRelationship.getManyToOneMapsId()).isNull();
    }

    @Test
    void manyToManyTest() {
        EntityUuidIdRelationship entityUuidIdRelationship = getEntityUuidIdRelationshipRandomSampleGenerator();
        EntityUuidId entityUuidIdBack = getEntityUuidIdRandomSampleGenerator();

        entityUuidIdRelationship.addManyToMany(entityUuidIdBack);
        assertThat(entityUuidIdRelationship.getManyToManies()).containsOnly(entityUuidIdBack);

        entityUuidIdRelationship.removeManyToMany(entityUuidIdBack);
        assertThat(entityUuidIdRelationship.getManyToManies()).doesNotContain(entityUuidIdBack);

        entityUuidIdRelationship.manyToManies(new HashSet<>(Set.of(entityUuidIdBack)));
        assertThat(entityUuidIdRelationship.getManyToManies()).containsOnly(entityUuidIdBack);

        entityUuidIdRelationship.setManyToManies(new HashSet<>());
        assertThat(entityUuidIdRelationship.getManyToManies()).doesNotContain(entityUuidIdBack);
    }

    @Test
    void manyToManyMapsIdTest() {
        EntityUuidIdRelationship entityUuidIdRelationship = getEntityUuidIdRelationshipRandomSampleGenerator();
        EntityUuidIdMapsId entityUuidIdMapsIdBack = getEntityUuidIdMapsIdRandomSampleGenerator();

        entityUuidIdRelationship.addManyToManyMapsId(entityUuidIdMapsIdBack);
        assertThat(entityUuidIdRelationship.getManyToManyMapsIds()).containsOnly(entityUuidIdMapsIdBack);

        entityUuidIdRelationship.removeManyToManyMapsId(entityUuidIdMapsIdBack);
        assertThat(entityUuidIdRelationship.getManyToManyMapsIds()).doesNotContain(entityUuidIdMapsIdBack);

        entityUuidIdRelationship.manyToManyMapsIds(new HashSet<>(Set.of(entityUuidIdMapsIdBack)));
        assertThat(entityUuidIdRelationship.getManyToManyMapsIds()).containsOnly(entityUuidIdMapsIdBack);

        entityUuidIdRelationship.setManyToManyMapsIds(new HashSet<>());
        assertThat(entityUuidIdRelationship.getManyToManyMapsIds()).doesNotContain(entityUuidIdMapsIdBack);
    }
}
