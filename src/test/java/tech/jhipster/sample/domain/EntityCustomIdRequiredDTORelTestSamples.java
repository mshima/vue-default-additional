package tech.jhipster.sample.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class EntityCustomIdRequiredDTORelTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static EntityCustomIdRequiredDTORel getEntityCustomIdRequiredDTORelSample1() {
        return new EntityCustomIdRequiredDTORel().relatedId(1L);
    }

    public static EntityCustomIdRequiredDTORel getEntityCustomIdRequiredDTORelSample2() {
        return new EntityCustomIdRequiredDTORel().relatedId(2L);
    }

    public static EntityCustomIdRequiredDTORel getEntityCustomIdRequiredDTORelRandomSampleGenerator() {
        return new EntityCustomIdRequiredDTORel().relatedId(longCount.incrementAndGet());
    }
}
