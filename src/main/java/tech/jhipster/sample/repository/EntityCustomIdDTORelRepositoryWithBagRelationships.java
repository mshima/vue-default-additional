package tech.jhipster.sample.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import tech.jhipster.sample.domain.EntityCustomIdDTORel;

public interface EntityCustomIdDTORelRepositoryWithBagRelationships {
    Optional<EntityCustomIdDTORel> fetchBagRelationships(Optional<EntityCustomIdDTORel> entityCustomIdDTORel);

    List<EntityCustomIdDTORel> fetchBagRelationships(List<EntityCustomIdDTORel> entityCustomIdDTORels);

    Page<EntityCustomIdDTORel> fetchBagRelationships(Page<EntityCustomIdDTORel> entityCustomIdDTORels);
}
