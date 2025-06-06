package tech.jhipster.sample.service.mapper;

import static tech.jhipster.sample.domain.EntityCustomIdRequiredDTOAsserts.*;
import static tech.jhipster.sample.domain.EntityCustomIdRequiredDTOTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EntityCustomIdRequiredDTOMapperTest {

    private EntityCustomIdRequiredDTOMapper entityCustomIdRequiredDTOMapper;

    @BeforeEach
    void setUp() {
        entityCustomIdRequiredDTOMapper = new EntityCustomIdRequiredDTOMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getEntityCustomIdRequiredDTOSample1();
        var actual = entityCustomIdRequiredDTOMapper.toEntity(entityCustomIdRequiredDTOMapper.toDto(expected));
        assertEntityCustomIdRequiredDTOAllPropertiesEquals(expected, actual);
    }
}
