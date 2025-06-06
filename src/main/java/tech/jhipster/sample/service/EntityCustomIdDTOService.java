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
import tech.jhipster.sample.domain.EntityCustomIdDTO;
import tech.jhipster.sample.repository.EntityCustomIdDTORepository;
import tech.jhipster.sample.service.dto.EntityCustomIdDTODTO;
import tech.jhipster.sample.service.mapper.EntityCustomIdDTOMapper;

/**
 * Service Implementation for managing {@link tech.jhipster.sample.domain.EntityCustomIdDTO}.
 */
@Service
@Transactional
public class EntityCustomIdDTOService {

    private static final Logger LOG = LoggerFactory.getLogger(EntityCustomIdDTOService.class);

    private final EntityCustomIdDTORepository entityCustomIdDTORepository;

    private final EntityCustomIdDTOMapper entityCustomIdDTOMapper;

    public EntityCustomIdDTOService(
        EntityCustomIdDTORepository entityCustomIdDTORepository,
        EntityCustomIdDTOMapper entityCustomIdDTOMapper
    ) {
        this.entityCustomIdDTORepository = entityCustomIdDTORepository;
        this.entityCustomIdDTOMapper = entityCustomIdDTOMapper;
    }

    /**
     * Save a entityCustomIdDTO.
     *
     * @param entityCustomIdDTODTO the entity to save.
     * @return the persisted entity.
     */
    public EntityCustomIdDTODTO save(EntityCustomIdDTODTO entityCustomIdDTODTO) {
        LOG.debug("Request to save EntityCustomIdDTO : {}", entityCustomIdDTODTO);
        EntityCustomIdDTO entityCustomIdDTO = entityCustomIdDTOMapper.toEntity(entityCustomIdDTODTO);
        entityCustomIdDTO = entityCustomIdDTORepository.save(entityCustomIdDTO);
        return entityCustomIdDTOMapper.toDto(entityCustomIdDTO);
    }

    /**
     * Update a entityCustomIdDTO.
     *
     * @param entityCustomIdDTODTO the entity to save.
     * @return the persisted entity.
     */
    public EntityCustomIdDTODTO update(EntityCustomIdDTODTO entityCustomIdDTODTO) {
        LOG.debug("Request to update EntityCustomIdDTO : {}", entityCustomIdDTODTO);
        EntityCustomIdDTO entityCustomIdDTO = entityCustomIdDTOMapper.toEntity(entityCustomIdDTODTO);
        entityCustomIdDTO = entityCustomIdDTORepository.save(entityCustomIdDTO);
        return entityCustomIdDTOMapper.toDto(entityCustomIdDTO);
    }

    /**
     * Partially update a entityCustomIdDTO.
     *
     * @param entityCustomIdDTODTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EntityCustomIdDTODTO> partialUpdate(EntityCustomIdDTODTO entityCustomIdDTODTO) {
        LOG.debug("Request to partially update EntityCustomIdDTO : {}", entityCustomIdDTODTO);

        return entityCustomIdDTORepository
            .findById(entityCustomIdDTODTO.getCustomId())
            .map(existingEntityCustomIdDTO -> {
                entityCustomIdDTOMapper.partialUpdate(existingEntityCustomIdDTO, entityCustomIdDTODTO);

                return existingEntityCustomIdDTO;
            })
            .map(entityCustomIdDTORepository::save)
            .map(entityCustomIdDTOMapper::toDto);
    }

    /**
     * Get all the entityCustomIdDTOS.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EntityCustomIdDTODTO> findAll() {
        LOG.debug("Request to get all EntityCustomIdDTOS");
        return entityCustomIdDTORepository
            .findAll()
            .stream()
            .map(entityCustomIdDTOMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the entityCustomIdDTOS where EntityCustomIdDTOMapsId is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EntityCustomIdDTODTO> findAllWhereEntityCustomIdDTOMapsIdIsNull() {
        LOG.debug("Request to get all entityCustomIdDTOS where EntityCustomIdDTOMapsId is null");
        return StreamSupport.stream(entityCustomIdDTORepository.findAll().spliterator(), false)
            .filter(entityCustomIdDTO -> entityCustomIdDTO.getEntityCustomIdDTOMapsId() == null)
            .map(entityCustomIdDTOMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the entityCustomIdDTOS where OneToOneBack is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EntityCustomIdDTODTO> findAllWhereOneToOneBackIsNull() {
        LOG.debug("Request to get all entityCustomIdDTOS where OneToOneBack is null");
        return StreamSupport.stream(entityCustomIdDTORepository.findAll().spliterator(), false)
            .filter(entityCustomIdDTO -> entityCustomIdDTO.getOneToOneBack() == null)
            .map(entityCustomIdDTOMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one entityCustomIdDTO by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EntityCustomIdDTODTO> findOne(Long id) {
        LOG.debug("Request to get EntityCustomIdDTO : {}", id);
        return entityCustomIdDTORepository.findById(id).map(entityCustomIdDTOMapper::toDto);
    }

    /**
     * Delete the entityCustomIdDTO by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete EntityCustomIdDTO : {}", id);
        entityCustomIdDTORepository.deleteById(id);
    }
}
