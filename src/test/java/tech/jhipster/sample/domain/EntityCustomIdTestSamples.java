package tech.jhipster.sample.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class EntityCustomIdTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static EntityCustomId getEntityCustomIdSample1() {
        return new EntityCustomId().customId(1L);
    }

    public static EntityCustomId getEntityCustomIdSample2() {
        return new EntityCustomId().customId(2L);
    }

    public static EntityCustomId getEntityCustomIdRandomSampleGenerator() {
        return new EntityCustomId().customId(longCount.incrementAndGet());
    }
}
