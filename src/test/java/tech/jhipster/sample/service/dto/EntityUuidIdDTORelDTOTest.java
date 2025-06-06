package tech.jhipster.sample.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityUuidIdDTORelDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityUuidIdDTORelDTO.class);
        EntityUuidIdDTORelDTO entityUuidIdDTORelDTO1 = new EntityUuidIdDTORelDTO();
        entityUuidIdDTORelDTO1.setId(UUID.randomUUID());
        EntityUuidIdDTORelDTO entityUuidIdDTORelDTO2 = new EntityUuidIdDTORelDTO();
        assertThat(entityUuidIdDTORelDTO1).isNotEqualTo(entityUuidIdDTORelDTO2);
        entityUuidIdDTORelDTO2.setId(entityUuidIdDTORelDTO1.getId());
        assertThat(entityUuidIdDTORelDTO1).isEqualTo(entityUuidIdDTORelDTO2);
        entityUuidIdDTORelDTO2.setId(UUID.randomUUID());
        assertThat(entityUuidIdDTORelDTO1).isNotEqualTo(entityUuidIdDTORelDTO2);
        entityUuidIdDTORelDTO1.setId(null);
        assertThat(entityUuidIdDTORelDTO1).isNotEqualTo(entityUuidIdDTORelDTO2);
    }
}
