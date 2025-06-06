package tech.jhipster.sample.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class EntityCustomIdRequiredDTOTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static EntityCustomIdRequiredDTO getEntityCustomIdRequiredDTOSample1() {
        return new EntityCustomIdRequiredDTO().customId(1L);
    }

    public static EntityCustomIdRequiredDTO getEntityCustomIdRequiredDTOSample2() {
        return new EntityCustomIdRequiredDTO().customId(2L);
    }

    public static EntityCustomIdRequiredDTO getEntityCustomIdRequiredDTORandomSampleGenerator() {
        return new EntityCustomIdRequiredDTO().customId(longCount.incrementAndGet());
    }
}
