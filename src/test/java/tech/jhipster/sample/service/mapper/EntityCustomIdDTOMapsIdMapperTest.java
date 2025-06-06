package tech.jhipster.sample.service.mapper;

import static tech.jhipster.sample.domain.EntityCustomIdDTOMapsIdAsserts.*;
import static tech.jhipster.sample.domain.EntityCustomIdDTOMapsIdTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EntityCustomIdDTOMapsIdMapperTest {

    private EntityCustomIdDTOMapsIdMapper entityCustomIdDTOMapsIdMapper;

    @BeforeEach
    void setUp() {
        entityCustomIdDTOMapsIdMapper = new EntityCustomIdDTOMapsIdMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getEntityCustomIdDTOMapsIdSample1();
        var actual = entityCustomIdDTOMapsIdMapper.toEntity(entityCustomIdDTOMapsIdMapper.toDto(expected));
        assertEntityCustomIdDTOMapsIdAllPropertiesEquals(expected, actual);
    }
}
