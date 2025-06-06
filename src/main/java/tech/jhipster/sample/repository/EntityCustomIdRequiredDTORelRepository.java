package tech.jhipster.sample.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.EntityCustomIdRequiredDTORel;

/**
 * Spring Data JPA repository for the EntityCustomIdRequiredDTORel entity.
 *
 * When extending this class, extend EntityCustomIdRequiredDTORelRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface EntityCustomIdRequiredDTORelRepository
    extends EntityCustomIdRequiredDTORelRepositoryWithBagRelationships, JpaRepository<EntityCustomIdRequiredDTORel, Long> {
    default Optional<EntityCustomIdRequiredDTORel> findOneWithEagerRelationships(Long relatedId) {
        return this.fetchBagRelationships(this.findById(relatedId));
    }

    default List<EntityCustomIdRequiredDTORel> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<EntityCustomIdRequiredDTORel> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}
