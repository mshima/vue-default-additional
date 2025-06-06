package tech.jhipster.sample.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.sample.domain.EntityCustomSequence;
import tech.jhipster.sample.repository.EntityCustomSequenceRepository;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.EntityCustomSequence}.
 */
@RestController
@RequestMapping("/api/entity-custom-sequences")
@Transactional
public class EntityCustomSequenceResource {

    private static final Logger LOG = LoggerFactory.getLogger(EntityCustomSequenceResource.class);

    private static final String ENTITY_NAME = "entityCustomSequence";

    @Value("${jhipster.clientApp.name:jhipsterVue}")
    private String applicationName;

    private final EntityCustomSequenceRepository entityCustomSequenceRepository;

    public EntityCustomSequenceResource(EntityCustomSequenceRepository entityCustomSequenceRepository) {
        this.entityCustomSequenceRepository = entityCustomSequenceRepository;
    }

    /**
     * {@code POST  /entity-custom-sequences} : Create a new entityCustomSequence.
     *
     * @param entityCustomSequence the entityCustomSequence to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityCustomSequence, or with status {@code 400 (Bad Request)} if the entityCustomSequence has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EntityCustomSequence> createEntityCustomSequence(@RequestBody EntityCustomSequence entityCustomSequence)
        throws URISyntaxException {
        LOG.debug("REST request to save EntityCustomSequence : {}", entityCustomSequence);
        if (entityCustomSequence.getId() != null) {
            throw new BadRequestAlertException("A new entityCustomSequence cannot already have an ID", ENTITY_NAME, "idexists");
        }
        entityCustomSequence = entityCustomSequenceRepository.save(entityCustomSequence);
        return ResponseEntity.created(new URI("/api/entity-custom-sequences/" + entityCustomSequence.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, entityCustomSequence.getId().toString()))
            .body(entityCustomSequence);
    }

    /**
     * {@code PUT  /entity-custom-sequences/:id} : Updates an existing entityCustomSequence.
     *
     * @param id the id of the entityCustomSequence to save.
     * @param entityCustomSequence the entityCustomSequence to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityCustomSequence,
     * or with status {@code 400 (Bad Request)} if the entityCustomSequence is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityCustomSequence couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityCustomSequence> updateEntityCustomSequence(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EntityCustomSequence entityCustomSequence
    ) throws URISyntaxException {
        LOG.debug("REST request to update EntityCustomSequence : {}, {}", id, entityCustomSequence);
        if (entityCustomSequence.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, entityCustomSequence.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityCustomSequenceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        entityCustomSequence = entityCustomSequenceRepository.save(entityCustomSequence);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityCustomSequence.getId().toString()))
            .body(entityCustomSequence);
    }

    /**
     * {@code PATCH  /entity-custom-sequences/:id} : Partial updates given fields of an existing entityCustomSequence, field will ignore if it is null
     *
     * @param id the id of the entityCustomSequence to save.
     * @param entityCustomSequence the entityCustomSequence to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityCustomSequence,
     * or with status {@code 400 (Bad Request)} if the entityCustomSequence is not valid,
     * or with status {@code 404 (Not Found)} if the entityCustomSequence is not found,
     * or with status {@code 500 (Internal Server Error)} if the entityCustomSequence couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EntityCustomSequence> partialUpdateEntityCustomSequence(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EntityCustomSequence entityCustomSequence
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update EntityCustomSequence partially : {}, {}", id, entityCustomSequence);
        if (entityCustomSequence.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, entityCustomSequence.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityCustomSequenceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EntityCustomSequence> result = entityCustomSequenceRepository
            .findById(entityCustomSequence.getId())
            .map(existingEntityCustomSequence -> {
                if (entityCustomSequence.getName() != null) {
                    existingEntityCustomSequence.setName(entityCustomSequence.getName());
                }

                return existingEntityCustomSequence;
            })
            .map(entityCustomSequenceRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityCustomSequence.getId().toString())
        );
    }

    /**
     * {@code GET  /entity-custom-sequences} : get all the entityCustomSequences.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityCustomSequences in body.
     */
    @GetMapping("")
    public List<EntityCustomSequence> getAllEntityCustomSequences() {
        LOG.debug("REST request to get all EntityCustomSequences");
        return entityCustomSequenceRepository.findAll();
    }

    /**
     * {@code GET  /entity-custom-sequences/:id} : get the "id" entityCustomSequence.
     *
     * @param id the id of the entityCustomSequence to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityCustomSequence, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityCustomSequence> getEntityCustomSequence(@PathVariable("id") Long id) {
        LOG.debug("REST request to get EntityCustomSequence : {}", id);
        Optional<EntityCustomSequence> entityCustomSequence = entityCustomSequenceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(entityCustomSequence);
    }

    /**
     * {@code DELETE  /entity-custom-sequences/:id} : delete the "id" entityCustomSequence.
     *
     * @param id the id of the entityCustomSequence to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntityCustomSequence(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete EntityCustomSequence : {}", id);
        entityCustomSequenceRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
