package tech.jhipster.sample.domain;

import java.util.UUID;

public class UuidIdFilteringMapsIdTestSamples {

    public static UuidIdFilteringMapsId getUuidIdFilteringMapsIdSample1() {
        return new UuidIdFilteringMapsId().customId(UUID.fromString("23d8dc04-a48b-45d9-a01d-4b728f0ad4aa"));
    }

    public static UuidIdFilteringMapsId getUuidIdFilteringMapsIdSample2() {
        return new UuidIdFilteringMapsId().customId(UUID.fromString("ad79f240-3727-46c3-b89f-2cf6ebd74367"));
    }

    public static UuidIdFilteringMapsId getUuidIdFilteringMapsIdRandomSampleGenerator() {
        return new UuidIdFilteringMapsId().customId(UUID.randomUUID());
    }
}
