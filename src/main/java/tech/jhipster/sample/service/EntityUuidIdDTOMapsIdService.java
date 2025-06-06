package tech.jhipster.sample.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.sample.domain.EntityUuidIdDTOMapsId;
import tech.jhipster.sample.repository.EntityUuidIdDTOMapsIdRepository;
import tech.jhipster.sample.repository.EntityUuidIdDTORepository;
import tech.jhipster.sample.service.dto.EntityUuidIdDTOMapsIdDTO;
import tech.jhipster.sample.service.mapper.EntityUuidIdDTOMapsIdMapper;

/**
 * Service Implementation for managing {@link tech.jhipster.sample.domain.EntityUuidIdDTOMapsId}.
 */
@Service
@Transactional
public class EntityUuidIdDTOMapsIdService {

    private static final Logger LOG = LoggerFactory.getLogger(EntityUuidIdDTOMapsIdService.class);

    private final EntityUuidIdDTOMapsIdRepository entityUuidIdDTOMapsIdRepository;

    private final EntityUuidIdDTOMapsIdMapper entityUuidIdDTOMapsIdMapper;

    private final EntityUuidIdDTORepository entityUuidIdDTORepository;

    public EntityUuidIdDTOMapsIdService(
        EntityUuidIdDTOMapsIdRepository entityUuidIdDTOMapsIdRepository,
        EntityUuidIdDTOMapsIdMapper entityUuidIdDTOMapsIdMapper,
        EntityUuidIdDTORepository entityUuidIdDTORepository
    ) {
        this.entityUuidIdDTOMapsIdRepository = entityUuidIdDTOMapsIdRepository;
        this.entityUuidIdDTOMapsIdMapper = entityUuidIdDTOMapsIdMapper;
        this.entityUuidIdDTORepository = entityUuidIdDTORepository;
    }

    /**
     * Save a entityUuidIdDTOMapsId.
     *
     * @param entityUuidIdDTOMapsIdDTO the entity to save.
     * @return the persisted entity.
     */
    public EntityUuidIdDTOMapsIdDTO save(EntityUuidIdDTOMapsIdDTO entityUuidIdDTOMapsIdDTO) {
        LOG.debug("Request to save EntityUuidIdDTOMapsId : {}", entityUuidIdDTOMapsIdDTO);
        EntityUuidIdDTOMapsId entityUuidIdDTOMapsId = entityUuidIdDTOMapsIdMapper.toEntity(entityUuidIdDTOMapsIdDTO);
        UUID entityUuidIdDTOId = entityUuidIdDTOMapsId.getEntityUuidIdDTO().getId();
        entityUuidIdDTORepository.findById(entityUuidIdDTOId).ifPresent(entityUuidIdDTOMapsId::entityUuidIdDTO);
        entityUuidIdDTOMapsId = entityUuidIdDTOMapsIdRepository.save(entityUuidIdDTOMapsId);
        return entityUuidIdDTOMapsIdMapper.toDto(entityUuidIdDTOMapsId);
    }

    /**
     * Update a entityUuidIdDTOMapsId.
     *
     * @param entityUuidIdDTOMapsIdDTO the entity to save.
     * @return the persisted entity.
     */
    public EntityUuidIdDTOMapsIdDTO update(EntityUuidIdDTOMapsIdDTO entityUuidIdDTOMapsIdDTO) {
        LOG.debug("Request to update EntityUuidIdDTOMapsId : {}", entityUuidIdDTOMapsIdDTO);
        EntityUuidIdDTOMapsId entityUuidIdDTOMapsId = entityUuidIdDTOMapsIdMapper.toEntity(entityUuidIdDTOMapsIdDTO);
        entityUuidIdDTOMapsId = entityUuidIdDTOMapsIdRepository.save(entityUuidIdDTOMapsId);
        return entityUuidIdDTOMapsIdMapper.toDto(entityUuidIdDTOMapsId);
    }

    /**
     * Partially update a entityUuidIdDTOMapsId.
     *
     * @param entityUuidIdDTOMapsIdDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EntityUuidIdDTOMapsIdDTO> partialUpdate(EntityUuidIdDTOMapsIdDTO entityUuidIdDTOMapsIdDTO) {
        LOG.debug("Request to partially update EntityUuidIdDTOMapsId : {}", entityUuidIdDTOMapsIdDTO);

        return entityUuidIdDTOMapsIdRepository
            .findById(entityUuidIdDTOMapsIdDTO.getId())
            .map(existingEntityUuidIdDTOMapsId -> {
                entityUuidIdDTOMapsIdMapper.partialUpdate(existingEntityUuidIdDTOMapsId, entityUuidIdDTOMapsIdDTO);

                return existingEntityUuidIdDTOMapsId;
            })
            .map(entityUuidIdDTOMapsIdRepository::save)
            .map(entityUuidIdDTOMapsIdMapper::toDto);
    }

    /**
     * Get all the entityUuidIdDTOMapsIds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EntityUuidIdDTOMapsIdDTO> findAll() {
        LOG.debug("Request to get all EntityUuidIdDTOMapsIds");
        return entityUuidIdDTOMapsIdRepository
            .findAll()
            .stream()
            .map(entityUuidIdDTOMapsIdMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the entityUuidIdDTOMapsIds where OneToOneMapsIdBack is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EntityUuidIdDTOMapsIdDTO> findAllWhereOneToOneMapsIdBackIsNull() {
        LOG.debug("Request to get all entityUuidIdDTOMapsIds where OneToOneMapsIdBack is null");
        return StreamSupport.stream(entityUuidIdDTOMapsIdRepository.findAll().spliterator(), false)
            .filter(entityUuidIdDTOMapsId -> entityUuidIdDTOMapsId.getOneToOneMapsIdBack() == null)
            .map(entityUuidIdDTOMapsIdMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one entityUuidIdDTOMapsId by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EntityUuidIdDTOMapsIdDTO> findOne(UUID id) {
        LOG.debug("Request to get EntityUuidIdDTOMapsId : {}", id);
        return entityUuidIdDTOMapsIdRepository.findById(id).map(entityUuidIdDTOMapsIdMapper::toDto);
    }

    /**
     * Delete the entityUuidIdDTOMapsId by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        LOG.debug("Request to delete EntityUuidIdDTOMapsId : {}", id);
        entityUuidIdDTOMapsIdRepository.deleteById(id);
    }
}
