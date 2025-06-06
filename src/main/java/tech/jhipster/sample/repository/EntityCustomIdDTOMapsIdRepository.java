package tech.jhipster.sample.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.EntityCustomIdDTOMapsId;

/**
 * Spring Data JPA repository for the EntityCustomIdDTOMapsId entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityCustomIdDTOMapsIdRepository extends JpaRepository<EntityCustomIdDTOMapsId, Long> {}
