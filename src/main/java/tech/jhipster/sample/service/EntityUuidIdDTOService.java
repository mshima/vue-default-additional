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
import tech.jhipster.sample.domain.EntityUuidIdDTO;
import tech.jhipster.sample.repository.EntityUuidIdDTORepository;
import tech.jhipster.sample.service.dto.EntityUuidIdDTODTO;
import tech.jhipster.sample.service.mapper.EntityUuidIdDTOMapper;

/**
 * Service Implementation for managing {@link tech.jhipster.sample.domain.EntityUuidIdDTO}.
 */
@Service
@Transactional
public class EntityUuidIdDTOService {

    private static final Logger LOG = LoggerFactory.getLogger(EntityUuidIdDTOService.class);

    private final EntityUuidIdDTORepository entityUuidIdDTORepository;

    private final EntityUuidIdDTOMapper entityUuidIdDTOMapper;

    public EntityUuidIdDTOService(EntityUuidIdDTORepository entityUuidIdDTORepository, EntityUuidIdDTOMapper entityUuidIdDTOMapper) {
        this.entityUuidIdDTORepository = entityUuidIdDTORepository;
        this.entityUuidIdDTOMapper = entityUuidIdDTOMapper;
    }

    /**
     * Save a entityUuidIdDTO.
     *
     * @param entityUuidIdDTODTO the entity to save.
     * @return the persisted entity.
     */
    public EntityUuidIdDTODTO save(EntityUuidIdDTODTO entityUuidIdDTODTO) {
        LOG.debug("Request to save EntityUuidIdDTO : {}", entityUuidIdDTODTO);
        EntityUuidIdDTO entityUuidIdDTO = entityUuidIdDTOMapper.toEntity(entityUuidIdDTODTO);
        entityUuidIdDTO = entityUuidIdDTORepository.save(entityUuidIdDTO);
        return entityUuidIdDTOMapper.toDto(entityUuidIdDTO);
    }

    /**
     * Update a entityUuidIdDTO.
     *
     * @param entityUuidIdDTODTO the entity to save.
     * @return the persisted entity.
     */
    public EntityUuidIdDTODTO update(EntityUuidIdDTODTO entityUuidIdDTODTO) {
        LOG.debug("Request to update EntityUuidIdDTO : {}", entityUuidIdDTODTO);
        EntityUuidIdDTO entityUuidIdDTO = entityUuidIdDTOMapper.toEntity(entityUuidIdDTODTO);
        entityUuidIdDTO = entityUuidIdDTORepository.save(entityUuidIdDTO);
        return entityUuidIdDTOMapper.toDto(entityUuidIdDTO);
    }

    /**
     * Partially update a entityUuidIdDTO.
     *
     * @param entityUuidIdDTODTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EntityUuidIdDTODTO> partialUpdate(EntityUuidIdDTODTO entityUuidIdDTODTO) {
        LOG.debug("Request to partially update EntityUuidIdDTO : {}", entityUuidIdDTODTO);

        return entityUuidIdDTORepository
            .findById(entityUuidIdDTODTO.getId())
            .map(existingEntityUuidIdDTO -> {
                entityUuidIdDTOMapper.partialUpdate(existingEntityUuidIdDTO, entityUuidIdDTODTO);

                return existingEntityUuidIdDTO;
            })
            .map(entityUuidIdDTORepository::save)
            .map(entityUuidIdDTOMapper::toDto);
    }

    /**
     * Get all the entityUuidIdDTOS.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EntityUuidIdDTODTO> findAll() {
        LOG.debug("Request to get all EntityUuidIdDTOS");
        return entityUuidIdDTORepository
            .findAll()
            .stream()
            .map(entityUuidIdDTOMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the entityUuidIdDTOS where EntityUuidIdDTOMapsId is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EntityUuidIdDTODTO> findAllWhereEntityUuidIdDTOMapsIdIsNull() {
        LOG.debug("Request to get all entityUuidIdDTOS where EntityUuidIdDTOMapsId is null");
        return StreamSupport.stream(entityUuidIdDTORepository.findAll().spliterator(), false)
            .filter(entityUuidIdDTO -> entityUuidIdDTO.getEntityUuidIdDTOMapsId() == null)
            .map(entityUuidIdDTOMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the entityUuidIdDTOS where OneToOneBack is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EntityUuidIdDTODTO> findAllWhereOneToOneBackIsNull() {
        LOG.debug("Request to get all entityUuidIdDTOS where OneToOneBack is null");
        return StreamSupport.stream(entityUuidIdDTORepository.findAll().spliterator(), false)
            .filter(entityUuidIdDTO -> entityUuidIdDTO.getOneToOneBack() == null)
            .map(entityUuidIdDTOMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one entityUuidIdDTO by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EntityUuidIdDTODTO> findOne(UUID id) {
        LOG.debug("Request to get EntityUuidIdDTO : {}", id);
        return entityUuidIdDTORepository.findById(id).map(entityUuidIdDTOMapper::toDto);
    }

    /**
     * Delete the entityUuidIdDTO by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        LOG.debug("Request to delete EntityUuidIdDTO : {}", id);
        entityUuidIdDTORepository.deleteById(id);
    }
}
