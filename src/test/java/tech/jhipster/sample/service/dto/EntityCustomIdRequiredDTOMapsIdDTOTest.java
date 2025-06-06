package tech.jhipster.sample.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityCustomIdRequiredDTOMapsIdDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityCustomIdRequiredDTOMapsIdDTO.class);
        EntityCustomIdRequiredDTOMapsIdDTO entityCustomIdRequiredDTOMapsIdDTO1 = new EntityCustomIdRequiredDTOMapsIdDTO();
        entityCustomIdRequiredDTOMapsIdDTO1.setCustomId(1L);
        EntityCustomIdRequiredDTOMapsIdDTO entityCustomIdRequiredDTOMapsIdDTO2 = new EntityCustomIdRequiredDTOMapsIdDTO();
        assertThat(entityCustomIdRequiredDTOMapsIdDTO1).isNotEqualTo(entityCustomIdRequiredDTOMapsIdDTO2);
        entityCustomIdRequiredDTOMapsIdDTO2.setCustomId(entityCustomIdRequiredDTOMapsIdDTO1.getCustomId());
        assertThat(entityCustomIdRequiredDTOMapsIdDTO1).isEqualTo(entityCustomIdRequiredDTOMapsIdDTO2);
        entityCustomIdRequiredDTOMapsIdDTO2.setCustomId(2L);
        assertThat(entityCustomIdRequiredDTOMapsIdDTO1).isNotEqualTo(entityCustomIdRequiredDTOMapsIdDTO2);
        entityCustomIdRequiredDTOMapsIdDTO1.setCustomId(null);
        assertThat(entityCustomIdRequiredDTOMapsIdDTO1).isNotEqualTo(entityCustomIdRequiredDTOMapsIdDTO2);
    }
}
