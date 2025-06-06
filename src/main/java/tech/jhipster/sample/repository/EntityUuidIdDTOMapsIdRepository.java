package tech.jhipster.sample.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.EntityUuidIdDTOMapsId;

/**
 * Spring Data JPA repository for the EntityUuidIdDTOMapsId entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityUuidIdDTOMapsIdRepository extends JpaRepository<EntityUuidIdDTOMapsId, UUID> {}
