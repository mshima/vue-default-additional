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
import tech.jhipster.sample.domain.EntityCustomIdRequiredDTOMapsId;
import tech.jhipster.sample.repository.EntityCustomIdRequiredDTOMapsIdRepository;
import tech.jhipster.sample.repository.EntityCustomIdRequiredDTORepository;
import tech.jhipster.sample.service.dto.EntityCustomIdRequiredDTOMapsIdDTO;
import tech.jhipster.sample.service.mapper.EntityCustomIdRequiredDTOMapsIdMapper;

/**
 * Service Implementation for managing {@link tech.jhipster.sample.domain.EntityCustomIdRequiredDTOMapsId}.
 */
@Service
@Transactional
public class EntityCustomIdRequiredDTOMapsIdService {

    private static final Logger LOG = LoggerFactory.getLogger(EntityCustomIdRequiredDTOMapsIdService.class);

    private final EntityCustomIdRequiredDTOMapsIdRepository entityCustomIdRequiredDTOMapsIdRepository;

    private final EntityCustomIdRequiredDTOMapsIdMapper entityCustomIdRequiredDTOMapsIdMapper;

    private final EntityCustomIdRequiredDTORepository entityCustomIdRequiredDTORepository;

    public EntityCustomIdRequiredDTOMapsIdService(
        EntityCustomIdRequiredDTOMapsIdRepository entityCustomIdRequiredDTOMapsIdRepository,
        EntityCustomIdRequiredDTOMapsIdMapper entityCustomIdRequiredDTOMapsIdMapper,
        EntityCustomIdRequiredDTORepository entityCustomIdRequiredDTORepository
    ) {
        this.entityCustomIdRequiredDTOMapsIdRepository = entityCustomIdRequiredDTOMapsIdRepository;
        this.entityCustomIdRequiredDTOMapsIdMapper = entityCustomIdRequiredDTOMapsIdMapper;
        this.entityCustomIdRequiredDTORepository = entityCustomIdRequiredDTORepository;
    }

    /**
     * Save a entityCustomIdRequiredDTOMapsId.
     *
     * @param entityCustomIdRequiredDTOMapsIdDTO the entity to save.
     * @return the persisted entity.
     */
    public EntityCustomIdRequiredDTOMapsIdDTO save(EntityCustomIdRequiredDTOMapsIdDTO entityCustomIdRequiredDTOMapsIdDTO) {
        LOG.debug("Request to save EntityCustomIdRequiredDTOMapsId : {}", entityCustomIdRequiredDTOMapsIdDTO);
        EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsId = entityCustomIdRequiredDTOMapsIdMapper.toEntity(
            entityCustomIdRequiredDTOMapsIdDTO
        );
        Long entityCustomIdRequiredDTOId = entityCustomIdRequiredDTOMapsId.getEntityCustomIdRequiredDTO().getCustomId();
        entityCustomIdRequiredDTORepository
            .findById(entityCustomIdRequiredDTOId)
            .ifPresent(entityCustomIdRequiredDTOMapsId::entityCustomIdRequiredDTO);
        entityCustomIdRequiredDTOMapsId = entityCustomIdRequiredDTOMapsIdRepository.save(entityCustomIdRequiredDTOMapsId);
        return entityCustomIdRequiredDTOMapsIdMapper.toDto(entityCustomIdRequiredDTOMapsId);
    }

    /**
     * Update a entityCustomIdRequiredDTOMapsId.
     *
     * @param entityCustomIdRequiredDTOMapsIdDTO the entity to save.
     * @return the persisted entity.
     */
    public EntityCustomIdRequiredDTOMapsIdDTO update(EntityCustomIdRequiredDTOMapsIdDTO entityCustomIdRequiredDTOMapsIdDTO) {
        LOG.debug("Request to update EntityCustomIdRequiredDTOMapsId : {}", entityCustomIdRequiredDTOMapsIdDTO);
        EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsId = entityCustomIdRequiredDTOMapsIdMapper.toEntity(
            entityCustomIdRequiredDTOMapsIdDTO
        );
        entityCustomIdRequiredDTOMapsId = entityCustomIdRequiredDTOMapsIdRepository.save(entityCustomIdRequiredDTOMapsId);
        return entityCustomIdRequiredDTOMapsIdMapper.toDto(entityCustomIdRequiredDTOMapsId);
    }

    /**
     * Partially update a entityCustomIdRequiredDTOMapsId.
     *
     * @param entityCustomIdRequiredDTOMapsIdDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EntityCustomIdRequiredDTOMapsIdDTO> partialUpdate(
        EntityCustomIdRequiredDTOMapsIdDTO entityCustomIdRequiredDTOMapsIdDTO
    ) {
        LOG.debug("Request to partially update EntityCustomIdRequiredDTOMapsId : {}", entityCustomIdRequiredDTOMapsIdDTO);

        return entityCustomIdRequiredDTOMapsIdRepository
            .findById(entityCustomIdRequiredDTOMapsIdDTO.getCustomId())
            .map(existingEntityCustomIdRequiredDTOMapsId -> {
                entityCustomIdRequiredDTOMapsIdMapper.partialUpdate(
                    existingEntityCustomIdRequiredDTOMapsId,
                    entityCustomIdRequiredDTOMapsIdDTO
                );

                return existingEntityCustomIdRequiredDTOMapsId;
            })
            .map(entityCustomIdRequiredDTOMapsIdRepository::save)
            .map(entityCustomIdRequiredDTOMapsIdMapper::toDto);
    }

    /**
     * Get all the entityCustomIdRequiredDTOMapsIds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EntityCustomIdRequiredDTOMapsIdDTO> findAll() {
        LOG.debug("Request to get all EntityCustomIdRequiredDTOMapsIds");
        return entityCustomIdRequiredDTOMapsIdRepository
            .findAll()
            .stream()
            .map(entityCustomIdRequiredDTOMapsIdMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the entityCustomIdRequiredDTOMapsIds where OneToOneMapsIdBack is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EntityCustomIdRequiredDTOMapsIdDTO> findAllWhereOneToOneMapsIdBackIsNull() {
        LOG.debug("Request to get all entityCustomIdRequiredDTOMapsIds where OneToOneMapsIdBack is null");
        return StreamSupport.stream(entityCustomIdRequiredDTOMapsIdRepository.findAll().spliterator(), false)
            .filter(entityCustomIdRequiredDTOMapsId -> entityCustomIdRequiredDTOMapsId.getOneToOneMapsIdBack() == null)
            .map(entityCustomIdRequiredDTOMapsIdMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one entityCustomIdRequiredDTOMapsId by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EntityCustomIdRequiredDTOMapsIdDTO> findOne(Long id) {
        LOG.debug("Request to get EntityCustomIdRequiredDTOMapsId : {}", id);
        return entityCustomIdRequiredDTOMapsIdRepository.findById(id).map(entityCustomIdRequiredDTOMapsIdMapper::toDto);
    }

    /**
     * Delete the entityCustomIdRequiredDTOMapsId by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete EntityCustomIdRequiredDTOMapsId : {}", id);
        entityCustomIdRequiredDTOMapsIdRepository.deleteById(id);
    }
}
