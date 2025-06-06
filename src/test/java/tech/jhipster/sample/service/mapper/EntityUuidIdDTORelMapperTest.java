package tech.jhipster.sample.service.mapper;

import static tech.jhipster.sample.domain.EntityUuidIdDTORelAsserts.*;
import static tech.jhipster.sample.domain.EntityUuidIdDTORelTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EntityUuidIdDTORelMapperTest {

    private EntityUuidIdDTORelMapper entityUuidIdDTORelMapper;

    @BeforeEach
    void setUp() {
        entityUuidIdDTORelMapper = new EntityUuidIdDTORelMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getEntityUuidIdDTORelSample1();
        var actual = entityUuidIdDTORelMapper.toEntity(entityUuidIdDTORelMapper.toDto(expected));
        assertEntityUuidIdDTORelAllPropertiesEquals(expected, actual);
    }
}
