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
import tech.jhipster.sample.domain.EntityCustomIdRelationship;
import tech.jhipster.sample.repository.EntityCustomIdRelationshipRepository;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.EntityCustomIdRelationship}.
 */
@RestController
@RequestMapping("/api/entity-custom-id-relationships")
@Transactional
public class EntityCustomIdRelationshipResource {

    private static final Logger LOG = LoggerFactory.getLogger(EntityCustomIdRelationshipResource.class);

    private static final String ENTITY_NAME = "entityCustomIdRelationship";

    @Value("${jhipster.clientApp.name:jhipsterVue}")
    private String applicationName;

    private final EntityCustomIdRelationshipRepository entityCustomIdRelationshipRepository;

    public EntityCustomIdRelationshipResource(EntityCustomIdRelationshipRepository entityCustomIdRelationshipRepository) {
        this.entityCustomIdRelationshipRepository = entityCustomIdRelationshipRepository;
    }

    /**
     * {@code POST  /entity-custom-id-relationships} : Create a new entityCustomIdRelationship.
     *
     * @param entityCustomIdRelationship the entityCustomIdRelationship to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityCustomIdRelationship, or with status {@code 400 (Bad Request)} if the entityCustomIdRelationship has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EntityCustomIdRelationship> createEntityCustomIdRelationship(
        @RequestBody EntityCustomIdRelationship entityCustomIdRelationship
    ) throws URISyntaxException {
        LOG.debug("REST request to save EntityCustomIdRelationship : {}", entityCustomIdRelationship);
        if (entityCustomIdRelationship.getRelatedId() != null) {
            throw new BadRequestAlertException("A new entityCustomIdRelationship cannot already have an ID", ENTITY_NAME, "idexists");
        }
        entityCustomIdRelationship = entityCustomIdRelationshipRepository.save(entityCustomIdRelationship);
        return ResponseEntity.created(new URI("/api/entity-custom-id-relationships/" + entityCustomIdRelationship.getRelatedId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    entityCustomIdRelationship.getRelatedId().toString()
                )
            )
            .body(entityCustomIdRelationship);
    }

    /**
     * {@code PUT  /entity-custom-id-relationships/:relatedId} : Updates an existing entityCustomIdRelationship.
     *
     * @param relatedId the id of the entityCustomIdRelationship to save.
     * @param entityCustomIdRelationship the entityCustomIdRelationship to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityCustomIdRelationship,
     * or with status {@code 400 (Bad Request)} if the entityCustomIdRelationship is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityCustomIdRelationship couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{relatedId}")
    public ResponseEntity<EntityCustomIdRelationship> updateEntityCustomIdRelationship(
        @PathVariable(value = "relatedId", required = false) final Long relatedId,
        @RequestBody EntityCustomIdRelationship entityCustomIdRelationship
    ) throws URISyntaxException {
        LOG.debug("REST request to update EntityCustomIdRelationship : {}, {}", relatedId, entityCustomIdRelationship);
        if (entityCustomIdRelationship.getRelatedId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(relatedId, entityCustomIdRelationship.getRelatedId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityCustomIdRelationshipRepository.existsById(relatedId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        entityCustomIdRelationship = entityCustomIdRelationshipRepository.save(entityCustomIdRelationship);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityCustomIdRelationship.getRelatedId().toString())
            )
            .body(entityCustomIdRelationship);
    }

    /**
     * {@code PATCH  /entity-custom-id-relationships/:relatedId} : Partial updates given fields of an existing entityCustomIdRelationship, field will ignore if it is null
     *
     * @param relatedId the id of the entityCustomIdRelationship to save.
     * @param entityCustomIdRelationship the entityCustomIdRelationship to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityCustomIdRelationship,
     * or with status {@code 400 (Bad Request)} if the entityCustomIdRelationship is not valid,
     * or with status {@code 404 (Not Found)} if the entityCustomIdRelationship is not found,
     * or with status {@code 500 (Internal Server Error)} if the entityCustomIdRelationship couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{relatedId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EntityCustomIdRelationship> partialUpdateEntityCustomIdRelationship(
        @PathVariable(value = "relatedId", required = false) final Long relatedId,
        @RequestBody EntityCustomIdRelationship entityCustomIdRelationship
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update EntityCustomIdRelationship partially : {}, {}", relatedId, entityCustomIdRelationship);
        if (entityCustomIdRelationship.getRelatedId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(relatedId, entityCustomIdRelationship.getRelatedId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityCustomIdRelationshipRepository.existsById(relatedId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EntityCustomIdRelationship> result = entityCustomIdRelationshipRepository
            .findById(entityCustomIdRelationship.getRelatedId())
            .map(entityCustomIdRelationshipRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityCustomIdRelationship.getRelatedId().toString())
        );
    }

    /**
     * {@code GET  /entity-custom-id-relationships} : get all the entityCustomIdRelationships.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityCustomIdRelationships in body.
     */
    @GetMapping("")
    public List<EntityCustomIdRelationship> getAllEntityCustomIdRelationships(
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        LOG.debug("REST request to get all EntityCustomIdRelationships");
        if (eagerload) {
            return entityCustomIdRelationshipRepository.findAllWithEagerRelationships();
        } else {
            return entityCustomIdRelationshipRepository.findAll();
        }
    }

    /**
     * {@code GET  /entity-custom-id-relationships/:id} : get the "id" entityCustomIdRelationship.
     *
     * @param id the id of the entityCustomIdRelationship to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityCustomIdRelationship, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityCustomIdRelationship> getEntityCustomIdRelationship(@PathVariable("id") Long id) {
        LOG.debug("REST request to get EntityCustomIdRelationship : {}", id);
        Optional<EntityCustomIdRelationship> entityCustomIdRelationship =
            entityCustomIdRelationshipRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(entityCustomIdRelationship);
    }

    /**
     * {@code DELETE  /entity-custom-id-relationships/:id} : delete the "id" entityCustomIdRelationship.
     *
     * @param id the id of the entityCustomIdRelationship to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntityCustomIdRelationship(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete EntityCustomIdRelationship : {}", id);
        entityCustomIdRelationshipRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
