package tech.jhipster.sample.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.sample.domain.EntityUuidIdRelationship;
import tech.jhipster.sample.repository.EntityUuidIdRelationshipRepository;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.EntityUuidIdRelationship}.
 */
@RestController
@RequestMapping("/api/entity-uuid-id-relationships")
@Transactional
public class EntityUuidIdRelationshipResource {

    private static final Logger LOG = LoggerFactory.getLogger(EntityUuidIdRelationshipResource.class);

    private static final String ENTITY_NAME = "entityUuidIdRelationship";

    @Value("${jhipster.clientApp.name:jhipsterVue}")
    private String applicationName;

    private final EntityUuidIdRelationshipRepository entityUuidIdRelationshipRepository;

    public EntityUuidIdRelationshipResource(EntityUuidIdRelationshipRepository entityUuidIdRelationshipRepository) {
        this.entityUuidIdRelationshipRepository = entityUuidIdRelationshipRepository;
    }

    /**
     * {@code POST  /entity-uuid-id-relationships} : Create a new entityUuidIdRelationship.
     *
     * @param entityUuidIdRelationship the entityUuidIdRelationship to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityUuidIdRelationship, or with status {@code 400 (Bad Request)} if the entityUuidIdRelationship has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EntityUuidIdRelationship> createEntityUuidIdRelationship(
        @RequestBody EntityUuidIdRelationship entityUuidIdRelationship
    ) throws URISyntaxException {
        LOG.debug("REST request to save EntityUuidIdRelationship : {}", entityUuidIdRelationship);
        if (entityUuidIdRelationship.getId() != null) {
            throw new BadRequestAlertException("A new entityUuidIdRelationship cannot already have an ID", ENTITY_NAME, "idexists");
        }
        entityUuidIdRelationship = entityUuidIdRelationshipRepository.save(entityUuidIdRelationship);
        return ResponseEntity.created(new URI("/api/entity-uuid-id-relationships/" + entityUuidIdRelationship.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, entityUuidIdRelationship.getId().toString()))
            .body(entityUuidIdRelationship);
    }

    /**
     * {@code PUT  /entity-uuid-id-relationships/:id} : Updates an existing entityUuidIdRelationship.
     *
     * @param id the id of the entityUuidIdRelationship to save.
     * @param entityUuidIdRelationship the entityUuidIdRelationship to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityUuidIdRelationship,
     * or with status {@code 400 (Bad Request)} if the entityUuidIdRelationship is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityUuidIdRelationship couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityUuidIdRelationship> updateEntityUuidIdRelationship(
        @PathVariable(value = "id", required = false) final UUID id,
        @RequestBody EntityUuidIdRelationship entityUuidIdRelationship
    ) throws URISyntaxException {
        LOG.debug("REST request to update EntityUuidIdRelationship : {}, {}", id, entityUuidIdRelationship);
        if (entityUuidIdRelationship.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, entityUuidIdRelationship.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityUuidIdRelationshipRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        entityUuidIdRelationship = entityUuidIdRelationshipRepository.save(entityUuidIdRelationship);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityUuidIdRelationship.getId().toString()))
            .body(entityUuidIdRelationship);
    }

    /**
     * {@code PATCH  /entity-uuid-id-relationships/:id} : Partial updates given fields of an existing entityUuidIdRelationship, field will ignore if it is null
     *
     * @param id the id of the entityUuidIdRelationship to save.
     * @param entityUuidIdRelationship the entityUuidIdRelationship to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityUuidIdRelationship,
     * or with status {@code 400 (Bad Request)} if the entityUuidIdRelationship is not valid,
     * or with status {@code 404 (Not Found)} if the entityUuidIdRelationship is not found,
     * or with status {@code 500 (Internal Server Error)} if the entityUuidIdRelationship couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EntityUuidIdRelationship> partialUpdateEntityUuidIdRelationship(
        @PathVariable(value = "id", required = false) final UUID id,
        @RequestBody EntityUuidIdRelationship entityUuidIdRelationship
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update EntityUuidIdRelationship partially : {}, {}", id, entityUuidIdRelationship);
        if (entityUuidIdRelationship.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, entityUuidIdRelationship.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityUuidIdRelationshipRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EntityUuidIdRelationship> result = entityUuidIdRelationshipRepository
            .findById(entityUuidIdRelationship.getId())
            .map(entityUuidIdRelationshipRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityUuidIdRelationship.getId().toString())
        );
    }

    /**
     * {@code GET  /entity-uuid-id-relationships} : get all the entityUuidIdRelationships.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityUuidIdRelationships in body.
     */
    @GetMapping("")
    public List<EntityUuidIdRelationship> getAllEntityUuidIdRelationships(
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        LOG.debug("REST request to get all EntityUuidIdRelationships");
        if (eagerload) {
            return entityUuidIdRelationshipRepository.findAllWithEagerRelationships();
        } else {
            return entityUuidIdRelationshipRepository.findAll();
        }
    }

    /**
     * {@code GET  /entity-uuid-id-relationships/:id} : get the "id" entityUuidIdRelationship.
     *
     * @param id the id of the entityUuidIdRelationship to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityUuidIdRelationship, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityUuidIdRelationship> getEntityUuidIdRelationship(@PathVariable("id") UUID id) {
        LOG.debug("REST request to get EntityUuidIdRelationship : {}", id);
        Optional<EntityUuidIdRelationship> entityUuidIdRelationship = entityUuidIdRelationshipRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(entityUuidIdRelationship);
    }

    /**
     * {@code DELETE  /entity-uuid-id-relationships/:id} : delete the "id" entityUuidIdRelationship.
     *
     * @param id the id of the entityUuidIdRelationship to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntityUuidIdRelationship(@PathVariable("id") UUID id) {
        LOG.debug("REST request to delete EntityUuidIdRelationship : {}", id);
        entityUuidIdRelationshipRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
