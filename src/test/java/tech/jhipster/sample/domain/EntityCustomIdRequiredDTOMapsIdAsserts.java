package tech.jhipster.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class EntityCustomIdRequiredDTOMapsIdAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertEntityCustomIdRequiredDTOMapsIdAllPropertiesEquals(
        EntityCustomIdRequiredDTOMapsId expected,
        EntityCustomIdRequiredDTOMapsId actual
    ) {
        assertEntityCustomIdRequiredDTOMapsIdAutoGeneratedPropertiesEquals(expected, actual);
        assertEntityCustomIdRequiredDTOMapsIdAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertEntityCustomIdRequiredDTOMapsIdAllUpdatablePropertiesEquals(
        EntityCustomIdRequiredDTOMapsId expected,
        EntityCustomIdRequiredDTOMapsId actual
    ) {
        assertEntityCustomIdRequiredDTOMapsIdUpdatableFieldsEquals(expected, actual);
        assertEntityCustomIdRequiredDTOMapsIdUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the derived primary key is set correctly.
     *
     * @param entityToPersist the entity used to persist
     * @param persisted the persisted entity
     */
    public static void assertEntityCustomIdRequiredDTOMapsIdMapsIdRelationshipPersistedValue(
        EntityCustomIdRequiredDTOMapsId entityToPersist,
        EntityCustomIdRequiredDTOMapsId persisted
    ) {
        // Validate the id for MapsId, the ids must be same
        assertThat(entityToPersist.getEntityCustomIdRequiredDTO().getCustomId()).isEqualTo(persisted.getCustomId());
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertEntityCustomIdRequiredDTOMapsIdAutoGeneratedPropertiesEquals(
        EntityCustomIdRequiredDTOMapsId expected,
        EntityCustomIdRequiredDTOMapsId actual
    ) {
        assertThat(actual)
            .as("Verify EntityCustomIdRequiredDTOMapsId auto generated properties")
            .satisfies(a -> assertThat(a.getCustomId()).as("check customId").isEqualTo(expected.getCustomId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertEntityCustomIdRequiredDTOMapsIdUpdatableFieldsEquals(
        EntityCustomIdRequiredDTOMapsId expected,
        EntityCustomIdRequiredDTOMapsId actual
    ) {
        // empty method

    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertEntityCustomIdRequiredDTOMapsIdUpdatableRelationshipsEquals(
        EntityCustomIdRequiredDTOMapsId expected,
        EntityCustomIdRequiredDTOMapsId actual
    ) {
        assertThat(actual)
            .as("Verify EntityCustomIdRequiredDTOMapsId relationships")
            .satisfies(a ->
                assertThat(a.getManyToManyMapsIdBacks()).as("check manyToManyMapsIdBacks").isEqualTo(expected.getManyToManyMapsIdBacks())
            );
    }
}
