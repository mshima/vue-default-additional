package tech.jhipster.sample.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class EntityCustomSequenceTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static EntityCustomSequence getEntityCustomSequenceSample1() {
        return new EntityCustomSequence().id(1L).name("name1");
    }

    public static EntityCustomSequence getEntityCustomSequenceSample2() {
        return new EntityCustomSequence().id(2L).name("name2");
    }

    public static EntityCustomSequence getEntityCustomSequenceRandomSampleGenerator() {
        return new EntityCustomSequence().id(longCount.incrementAndGet()).name(UUID.randomUUID().toString());
    }
}
