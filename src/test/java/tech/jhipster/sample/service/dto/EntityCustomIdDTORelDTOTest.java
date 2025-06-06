package tech.jhipster.sample.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.sample.web.rest.TestUtil;

class EntityCustomIdDTORelDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityCustomIdDTORelDTO.class);
        EntityCustomIdDTORelDTO entityCustomIdDTORelDTO1 = new EntityCustomIdDTORelDTO();
        entityCustomIdDTORelDTO1.setRelatedId(1L);
        EntityCustomIdDTORelDTO entityCustomIdDTORelDTO2 = new EntityCustomIdDTORelDTO();
        assertThat(entityCustomIdDTORelDTO1).isNotEqualTo(entityCustomIdDTORelDTO2);
        entityCustomIdDTORelDTO2.setRelatedId(entityCustomIdDTORelDTO1.getRelatedId());
        assertThat(entityCustomIdDTORelDTO1).isEqualTo(entityCustomIdDTORelDTO2);
        entityCustomIdDTORelDTO2.setRelatedId(2L);
        assertThat(entityCustomIdDTORelDTO1).isNotEqualTo(entityCustomIdDTORelDTO2);
        entityCustomIdDTORelDTO1.setRelatedId(null);
        assertThat(entityCustomIdDTORelDTO1).isNotEqualTo(entityCustomIdDTORelDTO2);
    }
}
