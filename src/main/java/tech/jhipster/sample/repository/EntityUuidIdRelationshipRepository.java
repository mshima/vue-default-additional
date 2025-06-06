package tech.jhipster.sample.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.EntityUuidIdRelationship;

/**
 * Spring Data JPA repository for the EntityUuidIdRelationship entity.
 *
 * When extending this class, extend EntityUuidIdRelationshipRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface EntityUuidIdRelationshipRepository
    extends EntityUuidIdRelationshipRepositoryWithBagRelationships, JpaRepository<EntityUuidIdRelationship, UUID> {
    default Optional<EntityUuidIdRelationship> findOneWithEagerRelationships(UUID id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<EntityUuidIdRelationship> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<EntityUuidIdRelationship> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}
