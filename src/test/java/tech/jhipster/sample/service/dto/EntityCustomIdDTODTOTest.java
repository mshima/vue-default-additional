package tech.jhipster.sample.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityCustomIdDTODTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityCustomIdDTODTO.class);
        EntityCustomIdDTODTO entityCustomIdDTODTO1 = new EntityCustomIdDTODTO();
        entityCustomIdDTODTO1.setCustomId(1L);
        EntityCustomIdDTODTO entityCustomIdDTODTO2 = new EntityCustomIdDTODTO();
        assertThat(entityCustomIdDTODTO1).isNotEqualTo(entityCustomIdDTODTO2);
        entityCustomIdDTODTO2.setCustomId(entityCustomIdDTODTO1.getCustomId());
        assertThat(entityCustomIdDTODTO1).isEqualTo(entityCustomIdDTODTO2);
        entityCustomIdDTODTO2.setCustomId(2L);
        assertThat(entityCustomIdDTODTO1).isNotEqualTo(entityCustomIdDTODTO2);
        entityCustomIdDTODTO1.setCustomId(null);
        assertThat(entityCustomIdDTODTO1).isNotEqualTo(entityCustomIdDTODTO2);
    }
}
