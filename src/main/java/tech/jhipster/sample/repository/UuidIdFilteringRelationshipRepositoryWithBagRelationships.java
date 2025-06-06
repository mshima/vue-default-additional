package tech.jhipster.sample.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import tech.jhipster.sample.domain.UuidIdFilteringRelationship;

public interface UuidIdFilteringRelationshipRepositoryWithBagRelationships {
    Optional<UuidIdFilteringRelationship> fetchBagRelationships(Optional<UuidIdFilteringRelationship> uuidIdFilteringRelationship);

    List<UuidIdFilteringRelationship> fetchBagRelationships(List<UuidIdFilteringRelationship> uuidIdFilteringRelationships);

    Page<UuidIdFilteringRelationship> fetchBagRelationships(Page<UuidIdFilteringRelationship> uuidIdFilteringRelationships);
}
