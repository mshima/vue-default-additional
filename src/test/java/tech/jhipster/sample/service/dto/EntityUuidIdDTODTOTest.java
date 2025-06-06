package tech.jhipster.sample.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityUuidIdDTODTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityUuidIdDTODTO.class);
        EntityUuidIdDTODTO entityUuidIdDTODTO1 = new EntityUuidIdDTODTO();
        entityUuidIdDTODTO1.setId(UUID.randomUUID());
        EntityUuidIdDTODTO entityUuidIdDTODTO2 = new EntityUuidIdDTODTO();
        assertThat(entityUuidIdDTODTO1).isNotEqualTo(entityUuidIdDTODTO2);
        entityUuidIdDTODTO2.setId(entityUuidIdDTODTO1.getId());
        assertThat(entityUuidIdDTODTO1).isEqualTo(entityUuidIdDTODTO2);
        entityUuidIdDTODTO2.setId(UUID.randomUUID());
        assertThat(entityUuidIdDTODTO1).isNotEqualTo(entityUuidIdDTODTO2);
        entityUuidIdDTODTO1.setId(null);
        assertThat(entityUuidIdDTODTO1).isNotEqualTo(entityUuidIdDTODTO2);
    }
}
