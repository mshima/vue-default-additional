package tech.jhipster.sample.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.EntityCustomSequence;

/**
 * Spring Data JPA repository for the EntityCustomSequence entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityCustomSequenceRepository extends JpaRepository<EntityCustomSequence, Long> {}
