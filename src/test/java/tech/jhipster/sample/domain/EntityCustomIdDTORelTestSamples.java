package tech.jhipster.sample.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class EntityCustomIdDTORelTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static EntityCustomIdDTORel getEntityCustomIdDTORelSample1() {
        return new EntityCustomIdDTORel().relatedId(1L);
    }

    public static EntityCustomIdDTORel getEntityCustomIdDTORelSample2() {
        return new EntityCustomIdDTORel().relatedId(2L);
    }

    public static EntityCustomIdDTORel getEntityCustomIdDTORelRandomSampleGenerator() {
        return new EntityCustomIdDTORel().relatedId(longCount.incrementAndGet());
    }
}
