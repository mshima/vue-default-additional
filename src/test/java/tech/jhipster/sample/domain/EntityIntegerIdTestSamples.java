package tech.jhipster.sample.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class EntityIntegerIdTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static EntityIntegerId getEntityIntegerIdSample1() {
        return new EntityIntegerId().id(1).name("name1");
    }

    public static EntityIntegerId getEntityIntegerIdSample2() {
        return new EntityIntegerId().id(2).name("name2");
    }

    public static EntityIntegerId getEntityIntegerIdRandomSampleGenerator() {
        return new EntityIntegerId().id(intCount.incrementAndGet()).name(UUID.randomUUID().toString());
    }
}
