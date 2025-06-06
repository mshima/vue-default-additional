package tech.jhipster.sample.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.EntityCustomId;

/**
 * Spring Data JPA repository for the EntityCustomId entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityCustomIdRepository extends JpaRepository<EntityCustomId, Long> {}
