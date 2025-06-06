package tech.jhipster.sample.domain;

import java.util.UUID;

public class EntityUuidIdDTORelTestSamples {

    public static EntityUuidIdDTORel getEntityUuidIdDTORelSample1() {
        return new EntityUuidIdDTORel().id(UUID.fromString("23d8dc04-a48b-45d9-a01d-4b728f0ad4aa"));
    }

    public static EntityUuidIdDTORel getEntityUuidIdDTORelSample2() {
        return new EntityUuidIdDTORel().id(UUID.fromString("ad79f240-3727-46c3-b89f-2cf6ebd74367"));
    }

    public static EntityUuidIdDTORel getEntityUuidIdDTORelRandomSampleGenerator() {
        return new EntityUuidIdDTORel().id(UUID.randomUUID());
    }
}
