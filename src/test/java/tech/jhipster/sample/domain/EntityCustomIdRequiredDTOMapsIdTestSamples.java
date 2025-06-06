package tech.jhipster.sample.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class EntityCustomIdRequiredDTOMapsIdTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static EntityCustomIdRequiredDTOMapsId getEntityCustomIdRequiredDTOMapsIdSample1() {
        return new EntityCustomIdRequiredDTOMapsId().customId(1L);
    }

    public static EntityCustomIdRequiredDTOMapsId getEntityCustomIdRequiredDTOMapsIdSample2() {
        return new EntityCustomIdRequiredDTOMapsId().customId(2L);
    }

    public static EntityCustomIdRequiredDTOMapsId getEntityCustomIdRequiredDTOMapsIdRandomSampleGenerator() {
        return new EntityCustomIdRequiredDTOMapsId().customId(longCount.incrementAndGet());
    }
}
