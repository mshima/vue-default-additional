package tech.jhipster.sample.domain;

import java.util.UUID;

public class EntityUuidIdDTOMapsIdTestSamples {

    public static EntityUuidIdDTOMapsId getEntityUuidIdDTOMapsIdSample1() {
        return new EntityUuidIdDTOMapsId().id(UUID.fromString("23d8dc04-a48b-45d9-a01d-4b728f0ad4aa"));
    }

    public static EntityUuidIdDTOMapsId getEntityUuidIdDTOMapsIdSample2() {
        return new EntityUuidIdDTOMapsId().id(UUID.fromString("ad79f240-3727-46c3-b89f-2cf6ebd74367"));
    }

    public static EntityUuidIdDTOMapsId getEntityUuidIdDTOMapsIdRandomSampleGenerator() {
        return new EntityUuidIdDTOMapsId().id(UUID.randomUUID());
    }
}
