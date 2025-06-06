package tech.jhipster.sample.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.sample.domain.UuidIdFiltering;
import tech.jhipster.sample.repository.UuidIdFilteringRepository;

/**
 * Service Implementation for managing {@link tech.jhipster.sample.domain.UuidIdFiltering}.
 */
@Service
@Transactional
public class UuidIdFilteringService {

    private static final Logger LOG = LoggerFactory.getLogger(UuidIdFilteringService.class);

    private final UuidIdFilteringRepository uuidIdFilteringRepository;

    public UuidIdFilteringService(UuidIdFilteringRepository uuidIdFilteringRepository) {
        this.uuidIdFilteringRepository = uuidIdFilteringRepository;
    }

    /**
     * Save a uuidIdFiltering.
     *
     * @param uuidIdFiltering the entity to save.
     * @return the persisted entity.
     */
    public UuidIdFiltering save(UuidIdFiltering uuidIdFiltering) {
        LOG.debug("Request to save UuidIdFiltering : {}", uuidIdFiltering);
        return uuidIdFilteringRepository.save(uuidIdFiltering);
    }

    /**
     * Update a uuidIdFiltering.
     *
     * @param uuidIdFiltering the entity to save.
     * @return the persisted entity.
     */
    public UuidIdFiltering update(UuidIdFiltering uuidIdFiltering) {
        LOG.debug("Request to update UuidIdFiltering : {}", uuidIdFiltering);
        return uuidIdFilteringRepository.save(uuidIdFiltering);
    }

    /**
     * Partially update a uuidIdFiltering.
     *
     * @param uuidIdFiltering the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<UuidIdFiltering> partialUpdate(UuidIdFiltering uuidIdFiltering) {
        LOG.debug("Request to partially update UuidIdFiltering : {}", uuidIdFiltering);

        return uuidIdFilteringRepository.findById(uuidIdFiltering.getCustomId()).map(uuidIdFilteringRepository::save);
    }

    /**
     *  Get all the uuidIdFilterings where UuidIdFilteringMapsId is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UuidIdFiltering> findAllWhereUuidIdFilteringMapsIdIsNull() {
        LOG.debug("Request to get all uuidIdFilterings where UuidIdFilteringMapsId is null");
        return StreamSupport.stream(uuidIdFilteringRepository.findAll().spliterator(), false)
            .filter(uuidIdFiltering -> uuidIdFiltering.getUuidIdFilteringMapsId() == null)
            .toList();
    }

    /**
     *  Get all the uuidIdFilterings where OneToOneBack is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UuidIdFiltering> findAllWhereOneToOneBackIsNull() {
        LOG.debug("Request to get all uuidIdFilterings where OneToOneBack is null");
        return StreamSupport.stream(uuidIdFilteringRepository.findAll().spliterator(), false)
            .filter(uuidIdFiltering -> uuidIdFiltering.getOneToOneBack() == null)
            .toList();
    }

    /**
     * Get one uuidIdFiltering by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UuidIdFiltering> findOne(UUID id) {
        LOG.debug("Request to get UuidIdFiltering : {}", id);
        return uuidIdFilteringRepository.findById(id);
    }

    /**
     * Delete the uuidIdFiltering by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        LOG.debug("Request to delete UuidIdFiltering : {}", id);
        uuidIdFilteringRepository.deleteById(id);
    }
}
