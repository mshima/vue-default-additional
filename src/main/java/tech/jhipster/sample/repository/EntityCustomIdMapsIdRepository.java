package tech.jhipster.sample.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.EntityCustomIdMapsId;

/**
 * Spring Data JPA repository for the EntityCustomIdMapsId entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityCustomIdMapsIdRepository extends JpaRepository<EntityCustomIdMapsId, Long> {}
