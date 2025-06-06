package tech.jhipster.sample.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.UuidIdFilteringMapsId;

/**
 * Spring Data JPA repository for the UuidIdFilteringMapsId entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UuidIdFilteringMapsIdRepository
    extends JpaRepository<UuidIdFilteringMapsId, UUID>, JpaSpecificationExecutor<UuidIdFilteringMapsId> {}
