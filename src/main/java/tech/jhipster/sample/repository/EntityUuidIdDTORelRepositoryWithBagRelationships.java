package tech.jhipster.sample.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import tech.jhipster.sample.domain.EntityUuidIdDTORel;

public interface EntityUuidIdDTORelRepositoryWithBagRelationships {
    Optional<EntityUuidIdDTORel> fetchBagRelationships(Optional<EntityUuidIdDTORel> entityUuidIdDTORel);

    List<EntityUuidIdDTORel> fetchBagRelationships(List<EntityUuidIdDTORel> entityUuidIdDTORels);

    Page<EntityUuidIdDTORel> fetchBagRelationships(Page<EntityUuidIdDTORel> entityUuidIdDTORels);
}
