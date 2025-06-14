package tech.jhipster.sample.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.EntityUuidIdDTORel;

/**
 * Spring Data JPA repository for the EntityUuidIdDTORel entity.
 *
 * When extending this class, extend EntityUuidIdDTORelRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface EntityUuidIdDTORelRepository
    extends EntityUuidIdDTORelRepositoryWithBagRelationships, JpaRepository<EntityUuidIdDTORel, UUID> {
    default Optional<EntityUuidIdDTORel> findOneWithEagerRelationships(UUID id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<EntityUuidIdDTORel> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<EntityUuidIdDTORel> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}
