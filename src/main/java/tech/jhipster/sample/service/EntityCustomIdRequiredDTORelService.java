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
import tech.jhipster.sample.domain.EntityCustomIdRequiredDTORel;
import tech.jhipster.sample.repository.EntityCustomIdRequiredDTORelRepository;
import tech.jhipster.sample.service.dto.EntityCustomIdRequiredDTORelDTO;
import tech.jhipster.sample.service.mapper.EntityCustomIdRequiredDTORelMapper;

/**
 * Service Implementation for managing {@link tech.jhipster.sample.domain.EntityCustomIdRequiredDTORel}.
 */
@Service
@Transactional
public class EntityCustomIdRequiredDTORelService {

    private static final Logger LOG = LoggerFactory.getLogger(EntityCustomIdRequiredDTORelService.class);

    private final EntityCustomIdRequiredDTORelRepository entityCustomIdRequiredDTORelRepository;

    private final EntityCustomIdRequiredDTORelMapper entityCustomIdRequiredDTORelMapper;

    public EntityCustomIdRequiredDTORelService(
        EntityCustomIdRequiredDTORelRepository entityCustomIdRequiredDTORelRepository,
        EntityCustomIdRequiredDTORelMapper entityCustomIdRequiredDTORelMapper
    ) {
        this.entityCustomIdRequiredDTORelRepository = entityCustomIdRequiredDTORelRepository;
        this.entityCustomIdRequiredDTORelMapper = entityCustomIdRequiredDTORelMapper;
    }

    /**
     * Save a entityCustomIdRequiredDTORel.
     *
     * @param entityCustomIdRequiredDTORelDTO the entity to save.
     * @return the persisted entity.
     */
    public EntityCustomIdRequiredDTORelDTO save(EntityCustomIdRequiredDTORelDTO entityCustomIdRequiredDTORelDTO) {
        LOG.debug("Request to save EntityCustomIdRequiredDTORel : {}", entityCustomIdRequiredDTORelDTO);
        EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel = entityCustomIdRequiredDTORelMapper.toEntity(
            entityCustomIdRequiredDTORelDTO
        );
        entityCustomIdRequiredDTORel = entityCustomIdRequiredDTORelRepository.save(entityCustomIdRequiredDTORel);
        return entityCustomIdRequiredDTORelMapper.toDto(entityCustomIdRequiredDTORel);
    }

    /**
     * Update a entityCustomIdRequiredDTORel.
     *
     * @param entityCustomIdRequiredDTORelDTO the entity to save.
     * @return the persisted entity.
     */
    public EntityCustomIdRequiredDTORelDTO update(EntityCustomIdRequiredDTORelDTO entityCustomIdRequiredDTORelDTO) {
        LOG.debug("Request to update EntityCustomIdRequiredDTORel : {}", entityCustomIdRequiredDTORelDTO);
        EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel = entityCustomIdRequiredDTORelMapper.toEntity(
            entityCustomIdRequiredDTORelDTO
        );
        entityCustomIdRequiredDTORel = entityCustomIdRequiredDTORelRepository.save(entityCustomIdRequiredDTORel);
        return entityCustomIdRequiredDTORelMapper.toDto(entityCustomIdRequiredDTORel);
    }

    /**
     * Partially update a entityCustomIdRequiredDTORel.
     *
     * @param entityCustomIdRequiredDTORelDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EntityCustomIdRequiredDTORelDTO> partialUpdate(EntityCustomIdRequiredDTORelDTO entityCustomIdRequiredDTORelDTO) {
        LOG.debug("Request to partially update EntityCustomIdRequiredDTORel : {}", entityCustomIdRequiredDTORelDTO);

        return entityCustomIdRequiredDTORelRepository
            .findById(entityCustomIdRequiredDTORelDTO.getRelatedId())
            .map(existingEntityCustomIdRequiredDTORel -> {
                entityCustomIdRequiredDTORelMapper.partialUpdate(existingEntityCustomIdRequiredDTORel, entityCustomIdRequiredDTORelDTO);

                return existingEntityCustomIdRequiredDTORel;
            })
            .map(entityCustomIdRequiredDTORelRepository::save)
            .map(entityCustomIdRequiredDTORelMapper::toDto);
    }

    /**
     * Get all the entityCustomIdRequiredDTORels.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EntityCustomIdRequiredDTORelDTO> findAll() {
        LOG.debug("Request to get all EntityCustomIdRequiredDTORels");
        return entityCustomIdRequiredDTORelRepository
            .findAll()
            .stream()
            .map(entityCustomIdRequiredDTORelMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the entityCustomIdRequiredDTORels with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<EntityCustomIdRequiredDTORelDTO> findAllWithEagerRelationships(Pageable pageable) {
        return entityCustomIdRequiredDTORelRepository
            .findAllWithEagerRelationships(pageable)
            .map(entityCustomIdRequiredDTORelMapper::toDto);
    }

    /**
     * Get one entityCustomIdRequiredDTORel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EntityCustomIdRequiredDTORelDTO> findOne(Long id) {
        LOG.debug("Request to get EntityCustomIdRequiredDTORel : {}", id);
        return entityCustomIdRequiredDTORelRepository.findOneWithEagerRelationships(id).map(entityCustomIdRequiredDTORelMapper::toDto);
    }

    /**
     * Delete the entityCustomIdRequiredDTORel by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete EntityCustomIdRequiredDTORel : {}", id);
        entityCustomIdRequiredDTORelRepository.deleteById(id);
    }
}
