package tech.jhipster.sample.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.EntityCustomIdRequiredDTO;

/**
 * Spring Data JPA repository for the EntityCustomIdRequiredDTO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityCustomIdRequiredDTORepository extends JpaRepository<EntityCustomIdRequiredDTO, Long> {}
