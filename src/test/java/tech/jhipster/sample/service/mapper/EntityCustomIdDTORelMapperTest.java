package tech.jhipster.sample.service.mapper;

import static tech.jhipster.sample.domain.EntityCustomIdDTORelAsserts.*;
import static tech.jhipster.sample.domain.EntityCustomIdDTORelTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EntityCustomIdDTORelMapperTest {

    private EntityCustomIdDTORelMapper entityCustomIdDTORelMapper;

    @BeforeEach
    void setUp() {
        entityCustomIdDTORelMapper = new EntityCustomIdDTORelMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getEntityCustomIdDTORelSample1();
        var actual = entityCustomIdDTORelMapper.toEntity(entityCustomIdDTORelMapper.toDto(expected));
        assertEntityCustomIdDTORelAllPropertiesEquals(expected, actual);
    }
}
