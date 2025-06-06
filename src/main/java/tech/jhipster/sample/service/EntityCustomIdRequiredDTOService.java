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
import tech.jhipster.sample.domain.EntityCustomIdRequiredDTO;
import tech.jhipster.sample.repository.EntityCustomIdRequiredDTORepository;
import tech.jhipster.sample.service.dto.EntityCustomIdRequiredDTODTO;
import tech.jhipster.sample.service.mapper.EntityCustomIdRequiredDTOMapper;

/**
 * Service Implementation for managing {@link tech.jhipster.sample.domain.EntityCustomIdRequiredDTO}.
 */
@Service
@Transactional
public class EntityCustomIdRequiredDTOService {

    private static final Logger LOG = LoggerFactory.getLogger(EntityCustomIdRequiredDTOService.class);

    private final EntityCustomIdRequiredDTORepository entityCustomIdRequiredDTORepository;

    private final EntityCustomIdRequiredDTOMapper entityCustomIdRequiredDTOMapper;

    public EntityCustomIdRequiredDTOService(
        EntityCustomIdRequiredDTORepository entityCustomIdRequiredDTORepository,
        EntityCustomIdRequiredDTOMapper entityCustomIdRequiredDTOMapper
    ) {
        this.entityCustomIdRequiredDTORepository = entityCustomIdRequiredDTORepository;
        this.entityCustomIdRequiredDTOMapper = entityCustomIdRequiredDTOMapper;
    }

    /**
     * Save a entityCustomIdRequiredDTO.
     *
     * @param entityCustomIdRequiredDTODTO the entity to save.
     * @return the persisted entity.
     */
    public EntityCustomIdRequiredDTODTO save(EntityCustomIdRequiredDTODTO entityCustomIdRequiredDTODTO) {
        LOG.debug("Request to save EntityCustomIdRequiredDTO : {}", entityCustomIdRequiredDTODTO);
        EntityCustomIdRequiredDTO entityCustomIdRequiredDTO = entityCustomIdRequiredDTOMapper.toEntity(entityCustomIdRequiredDTODTO);
        entityCustomIdRequiredDTO = entityCustomIdRequiredDTORepository.save(entityCustomIdRequiredDTO);
        return entityCustomIdRequiredDTOMapper.toDto(entityCustomIdRequiredDTO);
    }

    /**
     * Update a entityCustomIdRequiredDTO.
     *
     * @param entityCustomIdRequiredDTODTO the entity to save.
     * @return the persisted entity.
     */
    public EntityCustomIdRequiredDTODTO update(EntityCustomIdRequiredDTODTO entityCustomIdRequiredDTODTO) {
        LOG.debug("Request to update EntityCustomIdRequiredDTO : {}", entityCustomIdRequiredDTODTO);
        EntityCustomIdRequiredDTO entityCustomIdRequiredDTO = entityCustomIdRequiredDTOMapper.toEntity(entityCustomIdRequiredDTODTO);
        entityCustomIdRequiredDTO = entityCustomIdRequiredDTORepository.save(entityCustomIdRequiredDTO);
        return entityCustomIdRequiredDTOMapper.toDto(entityCustomIdRequiredDTO);
    }

    /**
     * Partially update a entityCustomIdRequiredDTO.
     *
     * @param entityCustomIdRequiredDTODTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EntityCustomIdRequiredDTODTO> partialUpdate(EntityCustomIdRequiredDTODTO entityCustomIdRequiredDTODTO) {
        LOG.debug("Request to partially update EntityCustomIdRequiredDTO : {}", entityCustomIdRequiredDTODTO);

        return entityCustomIdRequiredDTORepository
            .findById(entityCustomIdRequiredDTODTO.getCustomId())
            .map(existingEntityCustomIdRequiredDTO -> {
                entityCustomIdRequiredDTOMapper.partialUpdate(existingEntityCustomIdRequiredDTO, entityCustomIdRequiredDTODTO);

                return existingEntityCustomIdRequiredDTO;
            })
            .map(entityCustomIdRequiredDTORepository::save)
            .map(entityCustomIdRequiredDTOMapper::toDto);
    }

    /**
     * Get all the entityCustomIdRequiredDTOS.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EntityCustomIdRequiredDTODTO> findAll() {
        LOG.debug("Request to get all EntityCustomIdRequiredDTOS");
        return entityCustomIdRequiredDTORepository
            .findAll()
            .stream()
            .map(entityCustomIdRequiredDTOMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the entityCustomIdRequiredDTOS where EntityCustomIdRequiredDTOMapsId is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EntityCustomIdRequiredDTODTO> findAllWhereEntityCustomIdRequiredDTOMapsIdIsNull() {
        LOG.debug("Request to get all entityCustomIdRequiredDTOS where EntityCustomIdRequiredDTOMapsId is null");
        return StreamSupport.stream(entityCustomIdRequiredDTORepository.findAll().spliterator(), false)
            .filter(entityCustomIdRequiredDTO -> entityCustomIdRequiredDTO.getEntityCustomIdRequiredDTOMapsId() == null)
            .map(entityCustomIdRequiredDTOMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the entityCustomIdRequiredDTOS where OneToOneBack is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EntityCustomIdRequiredDTODTO> findAllWhereOneToOneBackIsNull() {
        LOG.debug("Request to get all entityCustomIdRequiredDTOS where OneToOneBack is null");
        return StreamSupport.stream(entityCustomIdRequiredDTORepository.findAll().spliterator(), false)
            .filter(entityCustomIdRequiredDTO -> entityCustomIdRequiredDTO.getOneToOneBack() == null)
            .map(entityCustomIdRequiredDTOMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one entityCustomIdRequiredDTO by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EntityCustomIdRequiredDTODTO> findOne(Long id) {
        LOG.debug("Request to get EntityCustomIdRequiredDTO : {}", id);
        return entityCustomIdRequiredDTORepository.findById(id).map(entityCustomIdRequiredDTOMapper::toDto);
    }

    /**
     * Delete the entityCustomIdRequiredDTO by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete EntityCustomIdRequiredDTO : {}", id);
        entityCustomIdRequiredDTORepository.deleteById(id);
    }
}
