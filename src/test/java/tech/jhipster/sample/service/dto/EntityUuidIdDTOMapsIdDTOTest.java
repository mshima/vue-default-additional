package tech.jhipster.sample.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityUuidIdDTOMapsIdDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityUuidIdDTOMapsIdDTO.class);
        EntityUuidIdDTOMapsIdDTO entityUuidIdDTOMapsIdDTO1 = new EntityUuidIdDTOMapsIdDTO();
        entityUuidIdDTOMapsIdDTO1.setId(UUID.randomUUID());
        EntityUuidIdDTOMapsIdDTO entityUuidIdDTOMapsIdDTO2 = new EntityUuidIdDTOMapsIdDTO();
        assertThat(entityUuidIdDTOMapsIdDTO1).isNotEqualTo(entityUuidIdDTOMapsIdDTO2);
        entityUuidIdDTOMapsIdDTO2.setId(entityUuidIdDTOMapsIdDTO1.getId());
        assertThat(entityUuidIdDTOMapsIdDTO1).isEqualTo(entityUuidIdDTOMapsIdDTO2);
        entityUuidIdDTOMapsIdDTO2.setId(UUID.randomUUID());
        assertThat(entityUuidIdDTOMapsIdDTO1).isNotEqualTo(entityUuidIdDTOMapsIdDTO2);
        entityUuidIdDTOMapsIdDTO1.setId(null);
        assertThat(entityUuidIdDTOMapsIdDTO1).isNotEqualTo(entityUuidIdDTOMapsIdDTO2);
    }
}
