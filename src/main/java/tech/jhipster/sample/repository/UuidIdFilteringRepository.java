package tech.jhipster.sample.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.UuidIdFiltering;

/**
 * Spring Data JPA repository for the UuidIdFiltering entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UuidIdFilteringRepository extends JpaRepository<UuidIdFiltering, UUID>, JpaSpecificationExecutor<UuidIdFiltering> {}
