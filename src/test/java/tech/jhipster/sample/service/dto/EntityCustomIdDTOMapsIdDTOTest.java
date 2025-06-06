package tech.jhipster.sample.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityCustomIdDTOMapsIdDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityCustomIdDTOMapsIdDTO.class);
        EntityCustomIdDTOMapsIdDTO entityCustomIdDTOMapsIdDTO1 = new EntityCustomIdDTOMapsIdDTO();
        entityCustomIdDTOMapsIdDTO1.setCustomId(1L);
        EntityCustomIdDTOMapsIdDTO entityCustomIdDTOMapsIdDTO2 = new EntityCustomIdDTOMapsIdDTO();
        assertThat(entityCustomIdDTOMapsIdDTO1).isNotEqualTo(entityCustomIdDTOMapsIdDTO2);
        entityCustomIdDTOMapsIdDTO2.setCustomId(entityCustomIdDTOMapsIdDTO1.getCustomId());
        assertThat(entityCustomIdDTOMapsIdDTO1).isEqualTo(entityCustomIdDTOMapsIdDTO2);
        entityCustomIdDTOMapsIdDTO2.setCustomId(2L);
        assertThat(entityCustomIdDTOMapsIdDTO1).isNotEqualTo(entityCustomIdDTOMapsIdDTO2);
        entityCustomIdDTOMapsIdDTO1.setCustomId(null);
        assertThat(entityCustomIdDTOMapsIdDTO1).isNotEqualTo(entityCustomIdDTOMapsIdDTO2);
    }
}
