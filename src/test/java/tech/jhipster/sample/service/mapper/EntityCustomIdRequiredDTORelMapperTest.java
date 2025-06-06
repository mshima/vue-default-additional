package tech.jhipster.sample.service.mapper;

import static tech.jhipster.sample.domain.EntityCustomIdRequiredDTORelAsserts.*;
import static tech.jhipster.sample.domain.EntityCustomIdRequiredDTORelTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EntityCustomIdRequiredDTORelMapperTest {

    private EntityCustomIdRequiredDTORelMapper entityCustomIdRequiredDTORelMapper;

    @BeforeEach
    void setUp() {
        entityCustomIdRequiredDTORelMapper = new EntityCustomIdRequiredDTORelMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getEntityCustomIdRequiredDTORelSample1();
        var actual = entityCustomIdRequiredDTORelMapper.toEntity(entityCustomIdRequiredDTORelMapper.toDto(expected));
        assertEntityCustomIdRequiredDTORelAllPropertiesEquals(expected, actual);
    }
}
