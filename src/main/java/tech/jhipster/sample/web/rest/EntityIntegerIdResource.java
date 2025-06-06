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
import tech.jhipster.sample.domain.EntityIntegerId;
import tech.jhipster.sample.repository.EntityIntegerIdRepository;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.EntityIntegerId}.
 */
@RestController
@RequestMapping("/api/entity-integer-ids")
@Transactional
public class EntityIntegerIdResource {

    private static final Logger LOG = LoggerFactory.getLogger(EntityIntegerIdResource.class);

    private static final String ENTITY_NAME = "entityIntegerId";

    @Value("${jhipster.clientApp.name:jhipsterVue}")
    private String applicationName;

    private final EntityIntegerIdRepository entityIntegerIdRepository;

    public EntityIntegerIdResource(EntityIntegerIdRepository entityIntegerIdRepository) {
        this.entityIntegerIdRepository = entityIntegerIdRepository;
    }

    /**
     * {@code POST  /entity-integer-ids} : Create a new entityIntegerId.
     *
     * @param entityIntegerId the entityIntegerId to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityIntegerId, or with status {@code 400 (Bad Request)} if the entityIntegerId has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EntityIntegerId> createEntityIntegerId(@RequestBody EntityIntegerId entityIntegerId) throws URISyntaxException {
        LOG.debug("REST request to save EntityIntegerId : {}", entityIntegerId);
        if (entityIntegerId.getId() != null) {
            throw new BadRequestAlertException("A new entityIntegerId cannot already have an ID", ENTITY_NAME, "idexists");
        }
        entityIntegerId = entityIntegerIdRepository.save(entityIntegerId);
        return ResponseEntity.created(new URI("/api/entity-integer-ids/" + entityIntegerId.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, entityIntegerId.getId().toString()))
            .body(entityIntegerId);
    }

    /**
     * {@code PUT  /entity-integer-ids/:id} : Updates an existing entityIntegerId.
     *
     * @param id the id of the entityIntegerId to save.
     * @param entityIntegerId the entityIntegerId to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityIntegerId,
     * or with status {@code 400 (Bad Request)} if the entityIntegerId is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityIntegerId couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityIntegerId> updateEntityIntegerId(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody EntityIntegerId entityIntegerId
    ) throws URISyntaxException {
        LOG.debug("REST request to update EntityIntegerId : {}, {}", id, entityIntegerId);
        if (entityIntegerId.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, entityIntegerId.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityIntegerIdRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        entityIntegerId = entityIntegerIdRepository.save(entityIntegerId);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityIntegerId.getId().toString()))
            .body(entityIntegerId);
    }

    /**
     * {@code PATCH  /entity-integer-ids/:id} : Partial updates given fields of an existing entityIntegerId, field will ignore if it is null
     *
     * @param id the id of the entityIntegerId to save.
     * @param entityIntegerId the entityIntegerId to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityIntegerId,
     * or with status {@code 400 (Bad Request)} if the entityIntegerId is not valid,
     * or with status {@code 404 (Not Found)} if the entityIntegerId is not found,
     * or with status {@code 500 (Internal Server Error)} if the entityIntegerId couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EntityIntegerId> partialUpdateEntityIntegerId(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody EntityIntegerId entityIntegerId
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update EntityIntegerId partially : {}, {}", id, entityIntegerId);
        if (entityIntegerId.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, entityIntegerId.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityIntegerIdRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EntityIntegerId> result = entityIntegerIdRepository
            .findById(entityIntegerId.getId())
            .map(existingEntityIntegerId -> {
                if (entityIntegerId.getName() != null) {
                    existingEntityIntegerId.setName(entityIntegerId.getName());
                }

                return existingEntityIntegerId;
            })
            .map(entityIntegerIdRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityIntegerId.getId().toString())
        );
    }

    /**
     * {@code GET  /entity-integer-ids} : get all the entityIntegerIds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityIntegerIds in body.
     */
    @GetMapping("")
    public List<EntityIntegerId> getAllEntityIntegerIds() {
        LOG.debug("REST request to get all EntityIntegerIds");
        return entityIntegerIdRepository.findAll();
    }

    /**
     * {@code GET  /entity-integer-ids/:id} : get the "id" entityIntegerId.
     *
     * @param id the id of the entityIntegerId to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityIntegerId, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityIntegerId> getEntityIntegerId(@PathVariable("id") Integer id) {
        LOG.debug("REST request to get EntityIntegerId : {}", id);
        Optional<EntityIntegerId> entityIntegerId = entityIntegerIdRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(entityIntegerId);
    }

    /**
     * {@code DELETE  /entity-integer-ids/:id} : delete the "id" entityIntegerId.
     *
     * @param id the id of the entityIntegerId to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntityIntegerId(@PathVariable("id") Integer id) {
        LOG.debug("REST request to delete EntityIntegerId : {}", id);
        entityIntegerIdRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
