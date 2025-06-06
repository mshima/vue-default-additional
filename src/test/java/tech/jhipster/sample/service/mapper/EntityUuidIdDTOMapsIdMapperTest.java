package tech.jhipster.sample.service.mapper;

import static tech.jhipster.sample.domain.EntityUuidIdDTOMapsIdAsserts.*;
import static tech.jhipster.sample.domain.EntityUuidIdDTOMapsIdTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EntityUuidIdDTOMapsIdMapperTest {

    private EntityUuidIdDTOMapsIdMapper entityUuidIdDTOMapsIdMapper;

    @BeforeEach
    void setUp() {
        entityUuidIdDTOMapsIdMapper = new EntityUuidIdDTOMapsIdMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getEntityUuidIdDTOMapsIdSample1();
        var actual = entityUuidIdDTOMapsIdMapper.toEntity(entityUuidIdDTOMapsIdMapper.toDto(expected));
        assertEntityUuidIdDTOMapsIdAllPropertiesEquals(expected, actual);
    }
}
