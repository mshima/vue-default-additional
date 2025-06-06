package tech.jhipster.sample.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.EntityCustomIdRelationship;

/**
 * Spring Data JPA repository for the EntityCustomIdRelationship entity.
 *
 * When extending this class, extend EntityCustomIdRelationshipRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface EntityCustomIdRelationshipRepository
    extends EntityCustomIdRelationshipRepositoryWithBagRelationships, JpaRepository<EntityCustomIdRelationship, Long> {
    default Optional<EntityCustomIdRelationship> findOneWithEagerRelationships(Long relatedId) {
        return this.fetchBagRelationships(this.findById(relatedId));
    }

    default List<EntityCustomIdRelationship> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<EntityCustomIdRelationship> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}
