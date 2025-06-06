package tech.jhipster.sample.domain;

import java.util.UUID;

public class EntityUuidIdRelationshipTestSamples {

    public static EntityUuidIdRelationship getEntityUuidIdRelationshipSample1() {
        return new EntityUuidIdRelationship().id(UUID.fromString("23d8dc04-a48b-45d9-a01d-4b728f0ad4aa"));
    }

    public static EntityUuidIdRelationship getEntityUuidIdRelationshipSample2() {
        return new EntityUuidIdRelationship().id(UUID.fromString("ad79f240-3727-46c3-b89f-2cf6ebd74367"));
    }

    public static EntityUuidIdRelationship getEntityUuidIdRelationshipRandomSampleGenerator() {
        return new EntityUuidIdRelationship().id(UUID.randomUUID());
    }
}
