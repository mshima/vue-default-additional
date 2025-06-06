package tech.jhipster.sample.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.EntityIntegerId;

/**
 * Spring Data JPA repository for the EntityIntegerId entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityIntegerIdRepository extends JpaRepository<EntityIntegerId, Integer> {}
