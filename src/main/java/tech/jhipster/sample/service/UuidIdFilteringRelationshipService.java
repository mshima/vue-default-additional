package tech.jhipster.sample.service;

import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.sample.domain.UuidIdFilteringRelationship;
import tech.jhipster.sample.repository.UuidIdFilteringRelationshipRepository;

/**
 * Service Implementation for managing {@link tech.jhipster.sample.domain.UuidIdFilteringRelationship}.
 */
@Service
@Transactional
public class UuidIdFilteringRelationshipService {

    private static final Logger LOG = LoggerFactory.getLogger(UuidIdFilteringRelationshipService.class);

    private final UuidIdFilteringRelationshipRepository uuidIdFilteringRelationshipRepository;

    public UuidIdFilteringRelationshipService(UuidIdFilteringRelationshipRepository uuidIdFilteringRelationshipRepository) {
        this.uuidIdFilteringRelationshipRepository = uuidIdFilteringRelationshipRepository;
    }

    /**
     * Save a uuidIdFilteringRelationship.
     *
     * @param uuidIdFilteringRelationship the entity to save.
     * @return the persisted entity.
     */
    public UuidIdFilteringRelationship save(UuidIdFilteringRelationship uuidIdFilteringRelationship) {
        LOG.debug("Request to save UuidIdFilteringRelationship : {}", uuidIdFilteringRelationship);
        return uuidIdFilteringRelationshipRepository.save(uuidIdFilteringRelationship);
    }

    /**
     * Update a uuidIdFilteringRelationship.
     *
     * @param uuidIdFilteringRelationship the entity to save.
     * @return the persisted entity.
     */
    public UuidIdFilteringRelationship update(UuidIdFilteringRelationship uuidIdFilteringRelationship) {
        LOG.debug("Request to update UuidIdFilteringRelationship : {}", uuidIdFilteringRelationship);
        return uuidIdFilteringRelationshipRepository.save(uuidIdFilteringRelationship);
    }

    /**
     * Partially update a uuidIdFilteringRelationship.
     *
     * @param uuidIdFilteringRelationship the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<UuidIdFilteringRelationship> partialUpdate(UuidIdFilteringRelationship uuidIdFilteringRelationship) {
        LOG.debug("Request to partially update UuidIdFilteringRelationship : {}", uuidIdFilteringRelationship);

        return uuidIdFilteringRelationshipRepository
            .findById(uuidIdFilteringRelationship.getRelatedId())
            .map(uuidIdFilteringRelationshipRepository::save);
    }

    /**
     * Get all the uuidIdFilteringRelationships with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<UuidIdFilteringRelationship> findAllWithEagerRelationships(Pageable pageable) {
        return uuidIdFilteringRelationshipRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one uuidIdFilteringRelationship by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UuidIdFilteringRelationship> findOne(UUID id) {
        LOG.debug("Request to get UuidIdFilteringRelationship : {}", id);
        return uuidIdFilteringRelationshipRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the uuidIdFilteringRelationship by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        LOG.debug("Request to delete UuidIdFilteringRelationship : {}", id);
        uuidIdFilteringRelationshipRepository.deleteById(id);
    }
}
