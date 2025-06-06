package tech.jhipster.sample.service.mapper;

import static tech.jhipster.sample.domain.EntityCustomIdRequiredDTOMapsIdAsserts.*;
import static tech.jhipster.sample.domain.EntityCustomIdRequiredDTOMapsIdTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EntityCustomIdRequiredDTOMapsIdMapperTest {

    private EntityCustomIdRequiredDTOMapsIdMapper entityCustomIdRequiredDTOMapsIdMapper;

    @BeforeEach
    void setUp() {
        entityCustomIdRequiredDTOMapsIdMapper = new EntityCustomIdRequiredDTOMapsIdMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getEntityCustomIdRequiredDTOMapsIdSample1();
        var actual = entityCustomIdRequiredDTOMapsIdMapper.toEntity(entityCustomIdRequiredDTOMapsIdMapper.toDto(expected));
        assertEntityCustomIdRequiredDTOMapsIdAllPropertiesEquals(expected, actual);
    }
}
