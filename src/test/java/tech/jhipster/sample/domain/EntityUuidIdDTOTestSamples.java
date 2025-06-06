package tech.jhipster.sample.domain;

import java.util.UUID;

public class EntityUuidIdDTOTestSamples {

    public static EntityUuidIdDTO getEntityUuidIdDTOSample1() {
        return new EntityUuidIdDTO().id(UUID.fromString("23d8dc04-a48b-45d9-a01d-4b728f0ad4aa"));
    }

    public static EntityUuidIdDTO getEntityUuidIdDTOSample2() {
        return new EntityUuidIdDTO().id(UUID.fromString("ad79f240-3727-46c3-b89f-2cf6ebd74367"));
    }

    public static EntityUuidIdDTO getEntityUuidIdDTORandomSampleGenerator() {
        return new EntityUuidIdDTO().id(UUID.randomUUID());
    }
}
