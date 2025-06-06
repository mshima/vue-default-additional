package tech.jhipster.sample.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityCustomIdRequiredDTORelDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityCustomIdRequiredDTORelDTO.class);
        EntityCustomIdRequiredDTORelDTO entityCustomIdRequiredDTORelDTO1 = new EntityCustomIdRequiredDTORelDTO();
        entityCustomIdRequiredDTORelDTO1.setRelatedId(1L);
        EntityCustomIdRequiredDTORelDTO entityCustomIdRequiredDTORelDTO2 = new EntityCustomIdRequiredDTORelDTO();
        assertThat(entityCustomIdRequiredDTORelDTO1).isNotEqualTo(entityCustomIdRequiredDTORelDTO2);
        entityCustomIdRequiredDTORelDTO2.setRelatedId(entityCustomIdRequiredDTORelDTO1.getRelatedId());
        assertThat(entityCustomIdRequiredDTORelDTO1).isEqualTo(entityCustomIdRequiredDTORelDTO2);
        entityCustomIdRequiredDTORelDTO2.setRelatedId(2L);
        assertThat(entityCustomIdRequiredDTORelDTO1).isNotEqualTo(entityCustomIdRequiredDTORelDTO2);
        entityCustomIdRequiredDTORelDTO1.setRelatedId(null);
        assertThat(entityCustomIdRequiredDTORelDTO1).isNotEqualTo(entityCustomIdRequiredDTORelDTO2);
    }
}
