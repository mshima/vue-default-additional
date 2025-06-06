package tech.jhipster.sample.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class EntityCustomIdDTOTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static EntityCustomIdDTO getEntityCustomIdDTOSample1() {
        return new EntityCustomIdDTO().customId(1L);
    }

    public static EntityCustomIdDTO getEntityCustomIdDTOSample2() {
        return new EntityCustomIdDTO().customId(2L);
    }

    public static EntityCustomIdDTO getEntityCustomIdDTORandomSampleGenerator() {
        return new EntityCustomIdDTO().customId(longCount.incrementAndGet());
    }
}
