package tech.jhipster.sample.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.sample.domain.EntityCustomIdDTOMapsId;
import tech.jhipster.sample.repository.EntityCustomIdDTOMapsIdRepository;
import tech.jhipster.sample.repository.EntityCustomIdDTORepository;
import tech.jhipster.sample.service.dto.EntityCustomIdDTOMapsIdDTO;
import tech.jhipster.sample.service.mapper.EntityCustomIdDTOMapsIdMapper;

/**
 * Service Implementation for managing {@link tech.jhipster.sample.domain.EntityCustomIdDTOMapsId}.
 */
@Service
@Transactional
public class EntityCustomIdDTOMapsIdService {

    private static final Logger LOG = LoggerFactory.getLogger(EntityCustomIdDTOMapsIdService.class);

    private final EntityCustomIdDTOMapsIdRepository entityCustomIdDTOMapsIdRepository;

    private final EntityCustomIdDTOMapsIdMapper entityCustomIdDTOMapsIdMapper;

    private final EntityCustomIdDTORepository entityCustomIdDTORepository;

    public EntityCustomIdDTOMapsIdService(
        EntityCustomIdDTOMapsIdRepository entityCustomIdDTOMapsIdRepository,
        EntityCustomIdDTOMapsIdMapper entityCustomIdDTOMapsIdMapper,
        EntityCustomIdDTORepository entityCustomIdDTORepository
    ) {
        this.entityCustomIdDTOMapsIdRepository = entityCustomIdDTOMapsIdRepository;
        this.entityCustomIdDTOMapsIdMapper = entityCustomIdDTOMapsIdMapper;
        this.entityCustomIdDTORepository = entityCustomIdDTORepository;
    }

    /**
     * Save a entityCustomIdDTOMapsId.
     *
     * @param entityCustomIdDTOMapsIdDTO the entity to save.
     * @return the persisted entity.
     */
    public EntityCustomIdDTOMapsIdDTO save(EntityCustomIdDTOMapsIdDTO entityCustomIdDTOMapsIdDTO) {
        LOG.debug("Request to save EntityCustomIdDTOMapsId : {}", entityCustomIdDTOMapsIdDTO);
        EntityCustomIdDTOMapsId entityCustomIdDTOMapsId = entityCustomIdDTOMapsIdMapper.toEntity(entityCustomIdDTOMapsIdDTO);
        Long entityCustomIdDTOId = entityCustomIdDTOMapsId.getEntityCustomIdDTO().getCustomId();
        entityCustomIdDTORepository.findById(entityCustomIdDTOId).ifPresent(entityCustomIdDTOMapsId::entityCustomIdDTO);
        entityCustomIdDTOMapsId = entityCustomIdDTOMapsIdRepository.save(entityCustomIdDTOMapsId);
        return entityCustomIdDTOMapsIdMapper.toDto(entityCustomIdDTOMapsId);
    }

    /**
     * Update a entityCustomIdDTOMapsId.
     *
     * @param entityCustomIdDTOMapsIdDTO the entity to save.
     * @return the persisted entity.
     */
    public EntityCustomIdDTOMapsIdDTO update(EntityCustomIdDTOMapsIdDTO entityCustomIdDTOMapsIdDTO) {
        LOG.debug("Request to update EntityCustomIdDTOMapsId : {}", entityCustomIdDTOMapsIdDTO);
        EntityCustomIdDTOMapsId entityCustomIdDTOMapsId = entityCustomIdDTOMapsIdMapper.toEntity(entityCustomIdDTOMapsIdDTO);
        entityCustomIdDTOMapsId = entityCustomIdDTOMapsIdRepository.save(entityCustomIdDTOMapsId);
        return entityCustomIdDTOMapsIdMapper.toDto(entityCustomIdDTOMapsId);
    }

    /**
     * Partially update a entityCustomIdDTOMapsId.
     *
     * @param entityCustomIdDTOMapsIdDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EntityCustomIdDTOMapsIdDTO> partialUpdate(EntityCustomIdDTOMapsIdDTO entityCustomIdDTOMapsIdDTO) {
        LOG.debug("Request to partially update EntityCustomIdDTOMapsId : {}", entityCustomIdDTOMapsIdDTO);

        return entityCustomIdDTOMapsIdRepository
            .findById(entityCustomIdDTOMapsIdDTO.getCustomId())
            .map(existingEntityCustomIdDTOMapsId -> {
                entityCustomIdDTOMapsIdMapper.partialUpdate(existingEntityCustomIdDTOMapsId, entityCustomIdDTOMapsIdDTO);

                return existingEntityCustomIdDTOMapsId;
            })
            .map(entityCustomIdDTOMapsIdRepository::save)
            .map(entityCustomIdDTOMapsIdMapper::toDto);
    }

    /**
     * Get all the entityCustomIdDTOMapsIds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EntityCustomIdDTOMapsIdDTO> findAll() {
        LOG.debug("Request to get all EntityCustomIdDTOMapsIds");
        return entityCustomIdDTOMapsIdRepository
            .findAll()
            .stream()
            .map(entityCustomIdDTOMapsIdMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the entityCustomIdDTOMapsIds where OneToOneMapsIdBack is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EntityCustomIdDTOMapsIdDTO> findAllWhereOneToOneMapsIdBackIsNull() {
        LOG.debug("Request to get all entityCustomIdDTOMapsIds where OneToOneMapsIdBack is null");
        return StreamSupport.stream(entityCustomIdDTOMapsIdRepository.findAll().spliterator(), false)
            .filter(entityCustomIdDTOMapsId -> entityCustomIdDTOMapsId.getOneToOneMapsIdBack() == null)
            .map(entityCustomIdDTOMapsIdMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one entityCustomIdDTOMapsId by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EntityCustomIdDTOMapsIdDTO> findOne(Long id) {
        LOG.debug("Request to get EntityCustomIdDTOMapsId : {}", id);
        return entityCustomIdDTOMapsIdRepository.findById(id).map(entityCustomIdDTOMapsIdMapper::toDto);
    }

    /**
     * Delete the entityCustomIdDTOMapsId by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete EntityCustomIdDTOMapsId : {}", id);
        entityCustomIdDTOMapsIdRepository.deleteById(id);
    }
}
