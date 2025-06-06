package tech.jhipster.sample.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.EntityCustomIdDTORel;

/**
 * Spring Data JPA repository for the EntityCustomIdDTORel entity.
 *
 * When extending this class, extend EntityCustomIdDTORelRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface EntityCustomIdDTORelRepository
    extends EntityCustomIdDTORelRepositoryWithBagRelationships, JpaRepository<EntityCustomIdDTORel, Long> {
    default Optional<EntityCustomIdDTORel> findOneWithEagerRelationships(Long relatedId) {
        return this.fetchBagRelationships(this.findById(relatedId));
    }

    default List<EntityCustomIdDTORel> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<EntityCustomIdDTORel> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}
