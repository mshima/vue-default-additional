package tech.jhipster.sample.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class EntityCustomIdDTOMapsIdTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static EntityCustomIdDTOMapsId getEntityCustomIdDTOMapsIdSample1() {
        return new EntityCustomIdDTOMapsId().customId(1L);
    }

    public static EntityCustomIdDTOMapsId getEntityCustomIdDTOMapsIdSample2() {
        return new EntityCustomIdDTOMapsId().customId(2L);
    }

    public static EntityCustomIdDTOMapsId getEntityCustomIdDTOMapsIdRandomSampleGenerator() {
        return new EntityCustomIdDTOMapsId().customId(longCount.incrementAndGet());
    }
}
