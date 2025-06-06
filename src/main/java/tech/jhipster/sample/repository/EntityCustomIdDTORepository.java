package tech.jhipster.sample.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.EntityCustomIdDTO;

/**
 * Spring Data JPA repository for the EntityCustomIdDTO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityCustomIdDTORepository extends JpaRepository<EntityCustomIdDTO, Long> {}
