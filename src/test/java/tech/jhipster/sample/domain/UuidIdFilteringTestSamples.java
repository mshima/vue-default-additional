package tech.jhipster.sample.domain;

import java.util.UUID;

public class UuidIdFilteringTestSamples {

    public static UuidIdFiltering getUuidIdFilteringSample1() {
        return new UuidIdFiltering().customId(UUID.fromString("23d8dc04-a48b-45d9-a01d-4b728f0ad4aa"));
    }

    public static UuidIdFiltering getUuidIdFilteringSample2() {
        return new UuidIdFiltering().customId(UUID.fromString("ad79f240-3727-46c3-b89f-2cf6ebd74367"));
    }

    public static UuidIdFiltering getUuidIdFilteringRandomSampleGenerator() {
        return new UuidIdFiltering().customId(UUID.randomUUID());
    }
}
