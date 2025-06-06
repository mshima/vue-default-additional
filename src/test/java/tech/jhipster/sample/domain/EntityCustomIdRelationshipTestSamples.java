package tech.jhipster.sample.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class EntityCustomIdRelationshipTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static EntityCustomIdRelationship getEntityCustomIdRelationshipSample1() {
        return new EntityCustomIdRelationship().relatedId(1L);
    }

    public static EntityCustomIdRelationship getEntityCustomIdRelationshipSample2() {
        return new EntityCustomIdRelationship().relatedId(2L);
    }

    public static EntityCustomIdRelationship getEntityCustomIdRelationshipRandomSampleGenerator() {
        return new EntityCustomIdRelationship().relatedId(longCount.incrementAndGet());
    }
}
