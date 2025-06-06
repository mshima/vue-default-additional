package tech.jhipster.sample.service.mapper;

import static tech.jhipster.sample.domain.EntityCustomIdDTOAsserts.*;
import static tech.jhipster.sample.domain.EntityCustomIdDTOTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EntityCustomIdDTOMapperTest {

    private EntityCustomIdDTOMapper entityCustomIdDTOMapper;

    @BeforeEach
    void setUp() {
        entityCustomIdDTOMapper = new EntityCustomIdDTOMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getEntityCustomIdDTOSample1();
        var actual = entityCustomIdDTOMapper.toEntity(entityCustomIdDTOMapper.toDto(expected));
        assertEntityCustomIdDTOAllPropertiesEquals(expected, actual);
    }
}
