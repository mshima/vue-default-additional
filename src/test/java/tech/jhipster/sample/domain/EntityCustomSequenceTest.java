package tech.jhipster.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.sample.domain.EntityCustomSequenceTestSamples.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityCustomSequenceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityCustomSequence.class);
        EntityCustomSequence entityCustomSequence1 = getEntityCustomSequenceSample1();
        EntityCustomSequence entityCustomSequence2 = new EntityCustomSequence();
        assertThat(entityCustomSequence1).isNotEqualTo(entityCustomSequence2);

        entityCustomSequence2.setId(entityCustomSequence1.getId());
        assertThat(entityCustomSequence1).isEqualTo(entityCustomSequence2);

        entityCustomSequence2 = getEntityCustomSequenceSample2();
        assertThat(entityCustomSequence1).isNotEqualTo(entityCustomSequence2);
    }
}
