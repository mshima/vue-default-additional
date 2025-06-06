package tech.jhipster.sample.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class EntityCustomIdMapsIdTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static EntityCustomIdMapsId getEntityCustomIdMapsIdSample1() {
        return new EntityCustomIdMapsId().customId(1L);
    }

    public static EntityCustomIdMapsId getEntityCustomIdMapsIdSample2() {
        return new EntityCustomIdMapsId().customId(2L);
    }

    public static EntityCustomIdMapsId getEntityCustomIdMapsIdRandomSampleGenerator() {
        return new EntityCustomIdMapsId().customId(longCount.incrementAndGet());
    }
}
