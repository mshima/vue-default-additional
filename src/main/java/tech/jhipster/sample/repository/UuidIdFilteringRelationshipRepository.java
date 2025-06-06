package tech.jhipster.sample.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.UuidIdFilteringRelationship;

/**
 * Spring Data JPA repository for the UuidIdFilteringRelationship entity.
 *
 * When extending this class, extend UuidIdFilteringRelationshipRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface UuidIdFilteringRelationshipRepository
    extends
        UuidIdFilteringRelationshipRepositoryWithBagRelationships,
        JpaRepository<UuidIdFilteringRelationship, UUID>,
        JpaSpecificationExecutor<UuidIdFilteringRelationship> {
    default Optional<UuidIdFilteringRelationship> findOneWithEagerRelationships(UUID relatedId) {
        return this.fetchBagRelationships(this.findById(relatedId));
    }

    default List<UuidIdFilteringRelationship> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<UuidIdFilteringRelationship> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}
