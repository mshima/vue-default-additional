package tech.jhipster.sample.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import tech.jhipster.sample.domain.EntityCustomIdRelationship;

public interface EntityCustomIdRelationshipRepositoryWithBagRelationships {
    Optional<EntityCustomIdRelationship> fetchBagRelationships(Optional<EntityCustomIdRelationship> entityCustomIdRelationship);

    List<EntityCustomIdRelationship> fetchBagRelationships(List<EntityCustomIdRelationship> entityCustomIdRelationships);

    Page<EntityCustomIdRelationship> fetchBagRelationships(Page<EntityCustomIdRelationship> entityCustomIdRelationships);
}
