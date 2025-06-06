package tech.jhipster.sample.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.EntityUuidIdDTO;

/**
 * Spring Data JPA repository for the EntityUuidIdDTO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityUuidIdDTORepository extends JpaRepository<EntityUuidIdDTO, UUID> {}
