package tech.jhipster.sample.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.sample.domain.EntityCustomIdDTORel;
import tech.jhipster.sample.repository.EntityCustomIdDTORelRepository;
import tech.jhipster.sample.service.dto.EntityCustomIdDTORelDTO;
import tech.jhipster.sample.service.mapper.EntityCustomIdDTORelMapper;

/**
 * Service Implementation for managing {@link tech.jhipster.sample.domain.EntityCustomIdDTORel}.
 */
@Service
@Transactional
public class EntityCustomIdDTORelService {

    private static final Logger LOG = LoggerFactory.getLogger(EntityCustomIdDTORelService.class);

    private final EntityCustomIdDTORelRepository entityCustomIdDTORelRepository;

    private final EntityCustomIdDTORelMapper entityCustomIdDTORelMapper;

    public EntityCustomIdDTORelService(
        EntityCustomIdDTORelRepository entityCustomIdDTORelRepository,
        EntityCustomIdDTORelMapper entityCustomIdDTORelMapper
    ) {
        this.entityCustomIdDTORelRepository = entityCustomIdDTORelRepository;
        this.entityCustomIdDTORelMapper = entityCustomIdDTORelMapper;
    }

    /**
     * Save a entityCustomIdDTORel.
     *
     * @param entityCustomIdDTORelDTO the entity to save.
     * @return the persisted entity.
     */
    public EntityCustomIdDTORelDTO save(EntityCustomIdDTORelDTO entityCustomIdDTORelDTO) {
        LOG.debug("Request to save EntityCustomIdDTORel : {}", entityCustomIdDTORelDTO);
        EntityCustomIdDTORel entityCustomIdDTORel = entityCustomIdDTORelMapper.toEntity(entityCustomIdDTORelDTO);
        entityCustomIdDTORel = entityCustomIdDTORelRepository.save(entityCustomIdDTORel);
        return entityCustomIdDTORelMapper.toDto(entityCustomIdDTORel);
    }

    /**
     * Update a entityCustomIdDTORel.
     *
     * @param entityCustomIdDTORelDTO the entity to save.
     * @return the persisted entity.
     */
    public EntityCustomIdDTORelDTO update(EntityCustomIdDTORelDTO entityCustomIdDTORelDTO) {
        LOG.debug("Request to update EntityCustomIdDTORel : {}", entityCustomIdDTORelDTO);
        EntityCustomIdDTORel entityCustomIdDTORel = entityCustomIdDTORelMapper.toEntity(entityCustomIdDTORelDTO);
        entityCustomIdDTORel = entityCustomIdDTORelRepository.save(entityCustomIdDTORel);
        return entityCustomIdDTORelMapper.toDto(entityCustomIdDTORel);
    }

    /**
     * Partially update a entityCustomIdDTORel.
     *
     * @param entityCustomIdDTORelDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EntityCustomIdDTORelDTO> partialUpdate(EntityCustomIdDTORelDTO entityCustomIdDTORelDTO) {
        LOG.debug("Request to partially update EntityCustomIdDTORel : {}", entityCustomIdDTORelDTO);

        return entityCustomIdDTORelRepository
            .findById(entityCustomIdDTORelDTO.getRelatedId())
            .map(existingEntityCustomIdDTORel -> {
                entityCustomIdDTORelMapper.partialUpdate(existingEntityCustomIdDTORel, entityCustomIdDTORelDTO);

                return existingEntityCustomIdDTORel;
            })
            .map(entityCustomIdDTORelRepository::save)
            .map(entityCustomIdDTORelMapper::toDto);
    }

    /**
     * Get all the entityCustomIdDTORels.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EntityCustomIdDTORelDTO> findAll() {
        LOG.debug("Request to get all EntityCustomIdDTORels");
        return entityCustomIdDTORelRepository
            .findAll()
            .stream()
            .map(entityCustomIdDTORelMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the entityCustomIdDTORels with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<EntityCustomIdDTORelDTO> findAllWithEagerRelationships(Pageable pageable) {
        return entityCustomIdDTORelRepository.findAllWithEagerRelationships(pageable).map(entityCustomIdDTORelMapper::toDto);
    }

    /**
     * Get one entityCustomIdDTORel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EntityCustomIdDTORelDTO> findOne(Long id) {
        LOG.debug("Request to get EntityCustomIdDTORel : {}", id);
        return entityCustomIdDTORelRepository.findOneWithEagerRelationships(id).map(entityCustomIdDTORelMapper::toDto);
    }

    /**
     * Delete the entityCustomIdDTORel by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete EntityCustomIdDTORel : {}", id);
        entityCustomIdDTORelRepository.deleteById(id);
    }
}
