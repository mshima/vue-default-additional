package tech.jhipster.sample.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import tech.jhipster.sample.domain.EntityCustomIdRequiredDTORel;

public interface EntityCustomIdRequiredDTORelRepositoryWithBagRelationships {
    Optional<EntityCustomIdRequiredDTORel> fetchBagRelationships(Optional<EntityCustomIdRequiredDTORel> entityCustomIdRequiredDTORel);

    List<EntityCustomIdRequiredDTORel> fetchBagRelationships(List<EntityCustomIdRequiredDTORel> entityCustomIdRequiredDTORels);

    Page<EntityCustomIdRequiredDTORel> fetchBagRelationships(Page<EntityCustomIdRequiredDTORel> entityCustomIdRequiredDTORels);
}
