package tech.jhipster.sample.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.sample.domain.UuidIdFilteringMapsId;
import tech.jhipster.sample.repository.UuidIdFilteringMapsIdRepository;
import tech.jhipster.sample.repository.UuidIdFilteringRepository;

/**
 * Service Implementation for managing {@link tech.jhipster.sample.domain.UuidIdFilteringMapsId}.
 */
@Service
@Transactional
public class UuidIdFilteringMapsIdService {

    private static final Logger LOG = LoggerFactory.getLogger(UuidIdFilteringMapsIdService.class);

    private final UuidIdFilteringMapsIdRepository uuidIdFilteringMapsIdRepository;

    private final UuidIdFilteringRepository uuidIdFilteringRepository;

    public UuidIdFilteringMapsIdService(
        UuidIdFilteringMapsIdRepository uuidIdFilteringMapsIdRepository,
        UuidIdFilteringRepository uuidIdFilteringRepository
    ) {
        this.uuidIdFilteringMapsIdRepository = uuidIdFilteringMapsIdRepository;
        this.uuidIdFilteringRepository = uuidIdFilteringRepository;
    }

    /**
     * Save a uuidIdFilteringMapsId.
     *
     * @param uuidIdFilteringMapsId the entity to save.
     * @return the persisted entity.
     */
    public UuidIdFilteringMapsId save(UuidIdFilteringMapsId uuidIdFilteringMapsId) {
        LOG.debug("Request to save UuidIdFilteringMapsId : {}", uuidIdFilteringMapsId);
        UUID uuidIdFilteringId = uuidIdFilteringMapsId.getUuidIdFiltering().getCustomId();
        uuidIdFilteringRepository.findById(uuidIdFilteringId).ifPresent(uuidIdFilteringMapsId::uuidIdFiltering);
        return uuidIdFilteringMapsIdRepository.save(uuidIdFilteringMapsId);
    }

    /**
     * Update a uuidIdFilteringMapsId.
     *
     * @param uuidIdFilteringMapsId the entity to save.
     * @return the persisted entity.
     */
    public UuidIdFilteringMapsId update(UuidIdFilteringMapsId uuidIdFilteringMapsId) {
        LOG.debug("Request to update UuidIdFilteringMapsId : {}", uuidIdFilteringMapsId);
        return uuidIdFilteringMapsIdRepository.save(uuidIdFilteringMapsId);
    }

    /**
     * Partially update a uuidIdFilteringMapsId.
     *
     * @param uuidIdFilteringMapsId the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<UuidIdFilteringMapsId> partialUpdate(UuidIdFilteringMapsId uuidIdFilteringMapsId) {
        LOG.debug("Request to partially update UuidIdFilteringMapsId : {}", uuidIdFilteringMapsId);

        return uuidIdFilteringMapsIdRepository.findById(uuidIdFilteringMapsId.getCustomId()).map(uuidIdFilteringMapsIdRepository::save);
    }

    /**
     *  Get all the uuidIdFilteringMapsIds where OneToOneMapsIdBack is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UuidIdFilteringMapsId> findAllWhereOneToOneMapsIdBackIsNull() {
        LOG.debug("Request to get all uuidIdFilteringMapsIds where OneToOneMapsIdBack is null");
        return StreamSupport.stream(uuidIdFilteringMapsIdRepository.findAll().spliterator(), false)
            .filter(uuidIdFilteringMapsId -> uuidIdFilteringMapsId.getOneToOneMapsIdBack() == null)
            .toList();
    }

    /**
     * Get one uuidIdFilteringMapsId by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UuidIdFilteringMapsId> findOne(UUID id) {
        LOG.debug("Request to get UuidIdFilteringMapsId : {}", id);
        return uuidIdFilteringMapsIdRepository.findById(id);
    }

    /**
     * Delete the uuidIdFilteringMapsId by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        LOG.debug("Request to delete UuidIdFilteringMapsId : {}", id);
        uuidIdFilteringMapsIdRepository.deleteById(id);
    }
}
