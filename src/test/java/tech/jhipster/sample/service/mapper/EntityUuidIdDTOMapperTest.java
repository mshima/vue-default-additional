package tech.jhipster.sample.service.mapper;

import static tech.jhipster.sample.domain.EntityUuidIdDTOAsserts.*;
import static tech.jhipster.sample.domain.EntityUuidIdDTOTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EntityUuidIdDTOMapperTest {

    private EntityUuidIdDTOMapper entityUuidIdDTOMapper;

    @BeforeEach
    void setUp() {
        entityUuidIdDTOMapper = new EntityUuidIdDTOMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getEntityUuidIdDTOSample1();
        var actual = entityUuidIdDTOMapper.toEntity(entityUuidIdDTOMapper.toDto(expected));
        assertEntityUuidIdDTOAllPropertiesEquals(expected, actual);
    }
}
