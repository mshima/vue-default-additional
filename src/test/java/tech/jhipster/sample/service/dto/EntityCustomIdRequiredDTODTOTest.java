package tech.jhipster.sample.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityCustomIdRequiredDTODTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityCustomIdRequiredDTODTO.class);
        EntityCustomIdRequiredDTODTO entityCustomIdRequiredDTODTO1 = new EntityCustomIdRequiredDTODTO();
        entityCustomIdRequiredDTODTO1.setCustomId(1L);
        EntityCustomIdRequiredDTODTO entityCustomIdRequiredDTODTO2 = new EntityCustomIdRequiredDTODTO();
        assertThat(entityCustomIdRequiredDTODTO1).isNotEqualTo(entityCustomIdRequiredDTODTO2);
        entityCustomIdRequiredDTODTO2.setCustomId(entityCustomIdRequiredDTODTO1.getCustomId());
        assertThat(entityCustomIdRequiredDTODTO1).isEqualTo(entityCustomIdRequiredDTODTO2);
        entityCustomIdRequiredDTODTO2.setCustomId(2L);
        assertThat(entityCustomIdRequiredDTODTO1).isNotEqualTo(entityCustomIdRequiredDTODTO2);
        entityCustomIdRequiredDTODTO1.setCustomId(null);
        assertThat(entityCustomIdRequiredDTODTO1).isNotEqualTo(entityCustomIdRequiredDTODTO2);
    }
}
