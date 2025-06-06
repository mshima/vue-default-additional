package tech.jhipster.sample.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.EntityCustomIdRequiredDTOMapsId;

/**
 * Spring Data JPA repository for the EntityCustomIdRequiredDTOMapsId entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityCustomIdRequiredDTOMapsIdRepository extends JpaRepository<EntityCustomIdRequiredDTOMapsId, Long> {}
