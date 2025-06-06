package tech.jhipster.sample.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.EntityUuidId;

/**
 * Spring Data JPA repository for the EntityUuidId entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityUuidIdRepository extends JpaRepository<EntityUuidId, UUID> {}
