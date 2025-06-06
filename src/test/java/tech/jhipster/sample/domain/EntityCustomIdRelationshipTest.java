package tech.jhipster.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.sample.domain.EntityCustomIdMapsIdTestSamples.*;
import static tech.jhipster.sample.domain.EntityCustomIdRelationshipTestSamples.*;
import static tech.jhipster.sample.domain.EntityCustomIdTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityCustomIdRelationshipTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityCustomIdRelationship.class);
        EntityCustomIdRelationship entityCustomIdRelationship1 = getEntityCustomIdRelationshipSample1();
        EntityCustomIdRelationship entityCustomIdRelationship2 = new EntityCustomIdRelationship();
        assertThat(entityCustomIdRelationship1).isNotEqualTo(entityCustomIdRelationship2);

        entityCustomIdRelationship2.setRelatedId(entityCustomIdRelationship1.getRelatedId());
        assertThat(entityCustomIdRelationship1).isEqualTo(entityCustomIdRelationship2);

        entityCustomIdRelationship2 = getEntityCustomIdRelationshipSample2();
        assertThat(entityCustomIdRelationship1).isNotEqualTo(entityCustomIdRelationship2);
    }

    @Test
    void oneToOneTest() {
        EntityCustomIdRelationship entityCustomIdRelationship = getEntityCustomIdRelationshipRandomSampleGenerator();
        EntityCustomId entityCustomIdBack = getEntityCustomIdRandomSampleGenerator();

        entityCustomIdRelationship.setOneToOne(entityCustomIdBack);
        assertThat(entityCustomIdRelationship.getOneToOne()).isEqualTo(entityCustomIdBack);

        entityCustomIdRelationship.oneToOne(null);
        assertThat(entityCustomIdRelationship.getOneToOne()).isNull();
    }

    @Test
    void oneToOneMapsIdTest() {
        EntityCustomIdRelationship entityCustomIdRelationship = getEntityCustomIdRelationshipRandomSampleGenerator();
        EntityCustomIdMapsId entityCustomIdMapsIdBack = getEntityCustomIdMapsIdRandomSampleGenerator();

        entityCustomIdRelationship.setOneToOneMapsId(entityCustomIdMapsIdBack);
        assertThat(entityCustomIdRelationship.getOneToOneMapsId()).isEqualTo(entityCustomIdMapsIdBack);

        entityCustomIdRelationship.oneToOneMapsId(null);
        assertThat(entityCustomIdRelationship.getOneToOneMapsId()).isNull();
    }

    @Test
    void manyToOneTest() {
        EntityCustomIdRelationship entityCustomIdRelationship = getEntityCustomIdRelationshipRandomSampleGenerator();
        EntityCustomId entityCustomIdBack = getEntityCustomIdRandomSampleGenerator();

        entityCustomIdRelationship.setManyToOne(entityCustomIdBack);
        assertThat(entityCustomIdRelationship.getManyToOne()).isEqualTo(entityCustomIdBack);

        entityCustomIdRelationship.manyToOne(null);
        assertThat(entityCustomIdRelationship.getManyToOne()).isNull();
    }

    @Test
    void manyToOneMapsIdTest() {
        EntityCustomIdRelationship entityCustomIdRelationship = getEntityCustomIdRelationshipRandomSampleGenerator();
        EntityCustomIdMapsId entityCustomIdMapsIdBack = getEntityCustomIdMapsIdRandomSampleGenerator();

        entityCustomIdRelationship.setManyToOneMapsId(entityCustomIdMapsIdBack);
        assertThat(entityCustomIdRelationship.getManyToOneMapsId()).isEqualTo(entityCustomIdMapsIdBack);

        entityCustomIdRelationship.manyToOneMapsId(null);
        assertThat(entityCustomIdRelationship.getManyToOneMapsId()).isNull();
    }

    @Test
    void manyToManyTest() {
        EntityCustomIdRelationship entityCustomIdRelationship = getEntityCustomIdRelationshipRandomSampleGenerator();
        EntityCustomId entityCustomIdBack = getEntityCustomIdRandomSampleGenerator();

        entityCustomIdRelationship.addManyToMany(entityCustomIdBack);
        assertThat(entityCustomIdRelationship.getManyToManies()).containsOnly(entityCustomIdBack);

        entityCustomIdRelationship.removeManyToMany(entityCustomIdBack);
        assertThat(entityCustomIdRelationship.getManyToManies()).doesNotContain(entityCustomIdBack);

        entityCustomIdRelationship.manyToManies(new HashSet<>(Set.of(entityCustomIdBack)));
        assertThat(entityCustomIdRelationship.getManyToManies()).containsOnly(entityCustomIdBack);

        entityCustomIdRelationship.setManyToManies(new HashSet<>());
        assertThat(entityCustomIdRelationship.getManyToManies()).doesNotContain(entityCustomIdBack);
    }

    @Test
    void manyToManyMapsIdTest() {
        EntityCustomIdRelationship entityCustomIdRelationship = getEntityCustomIdRelationshipRandomSampleGenerator();
        EntityCustomIdMapsId entityCustomIdMapsIdBack = getEntityCustomIdMapsIdRandomSampleGenerator();

        entityCustomIdRelationship.addManyToManyMapsId(entityCustomIdMapsIdBack);
        assertThat(entityCustomIdRelationship.getManyToManyMapsIds()).containsOnly(entityCustomIdMapsIdBack);

        entityCustomIdRelationship.removeManyToManyMapsId(entityCustomIdMapsIdBack);
        assertThat(entityCustomIdRelationship.getManyToManyMapsIds()).doesNotContain(entityCustomIdMapsIdBack);

        entityCustomIdRelationship.manyToManyMapsIds(new HashSet<>(Set.of(entityCustomIdMapsIdBack)));
        assertThat(entityCustomIdRelationship.getManyToManyMapsIds()).containsOnly(entityCustomIdMapsIdBack);

        entityCustomIdRelationship.setManyToManyMapsIds(new HashSet<>());
        assertThat(entityCustomIdRelationship.getManyToManyMapsIds()).doesNotContain(entityCustomIdMapsIdBack);
    }
}
