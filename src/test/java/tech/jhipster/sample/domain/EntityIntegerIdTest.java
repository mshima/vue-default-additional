package tech.jhipster.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.sample.domain.EntityIntegerIdTestSamples.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityIntegerIdTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityIntegerId.class);
        EntityIntegerId entityIntegerId1 = getEntityIntegerIdSample1();
        EntityIntegerId entityIntegerId2 = new EntityIntegerId();
        assertThat(entityIntegerId1).isNotEqualTo(entityIntegerId2);

        entityIntegerId2.setId(entityIntegerId1.getId());
        assertThat(entityIntegerId1).isEqualTo(entityIntegerId2);

        entityIntegerId2 = getEntityIntegerIdSample2();
        assertThat(entityIntegerId1).isNotEqualTo(entityIntegerId2);
    }
}
