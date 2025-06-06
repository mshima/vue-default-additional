package tech.jhipster.sample.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.sample.domain.EntityUuidIdDTORel;
import tech.jhipster.sample.repository.EntityUuidIdDTORelRepository;
import tech.jhipster.sample.service.dto.EntityUuidIdDTORelDTO;
import tech.jhipster.sample.service.mapper.EntityUuidIdDTORelMapper;

/**
 * Service Implementation for managing {@link tech.jhipster.sample.domain.EntityUuidIdDTORel}.
 */
@Service
@Transactional
public class EntityUuidIdDTORelService {

    private static final Logger LOG = LoggerFactory.getLogger(EntityUuidIdDTORelService.class);

    private final EntityUuidIdDTORelRepository entityUuidIdDTORelRepository;

    private final EntityUuidIdDTORelMapper entityUuidIdDTORelMapper;

    public EntityUuidIdDTORelService(
        EntityUuidIdDTORelRepository entityUuidIdDTORelRepository,
        EntityUuidIdDTORelMapper entityUuidIdDTORelMapper
    ) {
        this.entityUuidIdDTORelRepository = entityUuidIdDTORelRepository;
        this.entityUuidIdDTORelMapper = entityUuidIdDTORelMapper;
    }

    /**
     * Save a entityUuidIdDTORel.
     *
     * @param entityUuidIdDTORelDTO the entity to save.
     * @return the persisted entity.
     */
    public EntityUuidIdDTORelDTO save(EntityUuidIdDTORelDTO entityUuidIdDTORelDTO) {
        LOG.debug("Request to save EntityUuidIdDTORel : {}", entityUuidIdDTORelDTO);
        EntityUuidIdDTORel entityUuidIdDTORel = entityUuidIdDTORelMapper.toEntity(entityUuidIdDTORelDTO);
        entityUuidIdDTORel = entityUuidIdDTORelRepository.save(entityUuidIdDTORel);
        return entityUuidIdDTORelMapper.toDto(entityUuidIdDTORel);
    }

    /**
     * Update a entityUuidIdDTORel.
     *
     * @param entityUuidIdDTORelDTO the entity to save.
     * @return the persisted entity.
     */
    public EntityUuidIdDTORelDTO update(EntityUuidIdDTORelDTO entityUuidIdDTORelDTO) {
        LOG.debug("Request to update EntityUuidIdDTORel : {}", entityUuidIdDTORelDTO);
        EntityUuidIdDTORel entityUuidIdDTORel = entityUuidIdDTORelMapper.toEntity(entityUuidIdDTORelDTO);
        entityUuidIdDTORel = entityUuidIdDTORelRepository.save(entityUuidIdDTORel);
        return entityUuidIdDTORelMapper.toDto(entityUuidIdDTORel);
    }

    /**
     * Partially update a entityUuidIdDTORel.
     *
     * @param entityUuidIdDTORelDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EntityUuidIdDTORelDTO> partialUpdate(EntityUuidIdDTORelDTO entityUuidIdDTORelDTO) {
        LOG.debug("Request to partially update EntityUuidIdDTORel : {}", entityUuidIdDTORelDTO);

        return entityUuidIdDTORelRepository
            .findById(entityUuidIdDTORelDTO.getId())
            .map(existingEntityUuidIdDTORel -> {
                entityUuidIdDTORelMapper.partialUpdate(existingEntityUuidIdDTORel, entityUuidIdDTORelDTO);

                return existingEntityUuidIdDTORel;
            })
            .map(entityUuidIdDTORelRepository::save)
            .map(entityUuidIdDTORelMapper::toDto);
    }

    /**
     * Get all the entityUuidIdDTORels.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EntityUuidIdDTORelDTO> findAll() {
        LOG.debug("Request to get all EntityUuidIdDTORels");
        return entityUuidIdDTORelRepository
            .findAll()
            .stream()
            .map(entityUuidIdDTORelMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the entityUuidIdDTORels with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<EntityUuidIdDTORelDTO> findAllWithEagerRelationships(Pageable pageable) {
        return entityUuidIdDTORelRepository.findAllWithEagerRelationships(pageable).map(entityUuidIdDTORelMapper::toDto);
    }

    /**
     * Get one entityUuidIdDTORel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EntityUuidIdDTORelDTO> findOne(UUID id) {
        LOG.debug("Request to get EntityUuidIdDTORel : {}", id);
        return entityUuidIdDTORelRepository.findOneWithEagerRelationships(id).map(entityUuidIdDTORelMapper::toDto);
    }

    /**
     * Delete the entityUuidIdDTORel by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        LOG.debug("Request to delete EntityUuidIdDTORel : {}", id);
        entityUuidIdDTORelRepository.deleteById(id);
    }
}
