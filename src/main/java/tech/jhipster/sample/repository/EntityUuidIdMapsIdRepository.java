package tech.jhipster.sample.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.EntityUuidIdMapsId;

/**
 * Spring Data JPA repository for the EntityUuidIdMapsId entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityUuidIdMapsIdRepository extends JpaRepository<EntityUuidIdMapsId, UUID> {}
