package tech.jhipster.sample.domain;

import java.util.UUID;

public class UuidIdFilteringRelationshipTestSamples {

    public static UuidIdFilteringRelationship getUuidIdFilteringRelationshipSample1() {
        return new UuidIdFilteringRelationship().relatedId(UUID.fromString("23d8dc04-a48b-45d9-a01d-4b728f0ad4aa"));
    }

    public static UuidIdFilteringRelationship getUuidIdFilteringRelationshipSample2() {
        return new UuidIdFilteringRelationship().relatedId(UUID.fromString("ad79f240-3727-46c3-b89f-2cf6ebd74367"));
    }

    public static UuidIdFilteringRelationship getUuidIdFilteringRelationshipRandomSampleGenerator() {
        return new UuidIdFilteringRelationship().relatedId(UUID.randomUUID());
    }
}
