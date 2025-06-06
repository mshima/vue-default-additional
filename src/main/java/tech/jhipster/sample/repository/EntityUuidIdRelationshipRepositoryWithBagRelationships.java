package tech.jhipster.sample.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import tech.jhipster.sample.domain.EntityUuidIdRelationship;

public interface EntityUuidIdRelationshipRepositoryWithBagRelationships {
    Optional<EntityUuidIdRelationship> fetchBagRelationships(Optional<EntityUuidIdRelationship> entityUuidIdRelationship);

    List<EntityUuidIdRelationship> fetchBagRelationships(List<EntityUuidIdRelationship> entityUuidIdRelationships);

    Page<EntityUuidIdRelationship> fetchBagRelationships(Page<EntityUuidIdRelationship> entityUuidIdRelationships);
}
