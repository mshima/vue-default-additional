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
import org.springframework.web.bind.annotation.*;
import tech.jhipster.sample.domain.UuidIdFilteringRelationship;
import tech.jhipster.sample.repository.UuidIdFilteringRelationshipRepository;
import tech.jhipster.sample.service.UuidIdFilteringRelationshipQueryService;
import tech.jhipster.sample.service.UuidIdFilteringRelationshipService;
import tech.jhipster.sample.service.criteria.UuidIdFilteringRelationshipCriteria;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.UuidIdFilteringRelationship}.
 */
@RestController
@RequestMapping("/api/uuid-id-filtering-relationships")
public class UuidIdFilteringRelationshipResource {

    private static final Logger LOG = LoggerFactory.getLogger(UuidIdFilteringRelationshipResource.class);

    private static final String ENTITY_NAME = "uuidIdFilteringRelationship";

    @Value("${jhipster.clientApp.name:jhipsterVue}")
    private String applicationName;

    private final UuidIdFilteringRelationshipService uuidIdFilteringRelationshipService;

    private final UuidIdFilteringRelationshipRepository uuidIdFilteringRelationshipRepository;

    private final UuidIdFilteringRelationshipQueryService uuidIdFilteringRelationshipQueryService;

    public UuidIdFilteringRelationshipResource(
        UuidIdFilteringRelationshipService uuidIdFilteringRelationshipService,
        UuidIdFilteringRelationshipRepository uuidIdFilteringRelationshipRepository,
        UuidIdFilteringRelationshipQueryService uuidIdFilteringRelationshipQueryService
    ) {
        this.uuidIdFilteringRelationshipService = uuidIdFilteringRelationshipService;
        this.uuidIdFilteringRelationshipRepository = uuidIdFilteringRelationshipRepository;
        this.uuidIdFilteringRelationshipQueryService = uuidIdFilteringRelationshipQueryService;
    }

    /**
     * {@code POST  /uuid-id-filtering-relationships} : Create a new uuidIdFilteringRelationship.
     *
     * @param uuidIdFilteringRelationship the uuidIdFilteringRelationship to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new uuidIdFilteringRelationship, or with status {@code 400 (Bad Request)} if the uuidIdFilteringRelationship has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<UuidIdFilteringRelationship> createUuidIdFilteringRelationship(
        @RequestBody UuidIdFilteringRelationship uuidIdFilteringRelationship
    ) throws URISyntaxException {
        LOG.debug("REST request to save UuidIdFilteringRelationship : {}", uuidIdFilteringRelationship);
        if (uuidIdFilteringRelationship.getRelatedId() != null) {
            throw new BadRequestAlertException("A new uuidIdFilteringRelationship cannot already have an ID", ENTITY_NAME, "idexists");
        }
        uuidIdFilteringRelationship = uuidIdFilteringRelationshipService.save(uuidIdFilteringRelationship);
        return ResponseEntity.created(new URI("/api/uuid-id-filtering-relationships/" + uuidIdFilteringRelationship.getRelatedId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    uuidIdFilteringRelationship.getRelatedId().toString()
                )
            )
            .body(uuidIdFilteringRelationship);
    }

    /**
     * {@code PUT  /uuid-id-filtering-relationships/:relatedId} : Updates an existing uuidIdFilteringRelationship.
     *
     * @param relatedId the id of the uuidIdFilteringRelationship to save.
     * @param uuidIdFilteringRelationship the uuidIdFilteringRelationship to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uuidIdFilteringRelationship,
     * or with status {@code 400 (Bad Request)} if the uuidIdFilteringRelationship is not valid,
     * or with status {@code 500 (Internal Server Error)} if the uuidIdFilteringRelationship couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{relatedId}")
    public ResponseEntity<UuidIdFilteringRelationship> updateUuidIdFilteringRelationship(
        @PathVariable(value = "relatedId", required = false) final UUID relatedId,
        @RequestBody UuidIdFilteringRelationship uuidIdFilteringRelationship
    ) throws URISyntaxException {
        LOG.debug("REST request to update UuidIdFilteringRelationship : {}, {}", relatedId, uuidIdFilteringRelationship);
        if (uuidIdFilteringRelationship.getRelatedId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(relatedId, uuidIdFilteringRelationship.getRelatedId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!uuidIdFilteringRelationshipRepository.existsById(relatedId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        uuidIdFilteringRelationship = uuidIdFilteringRelationshipService.update(uuidIdFilteringRelationship);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    uuidIdFilteringRelationship.getRelatedId().toString()
                )
            )
            .body(uuidIdFilteringRelationship);
    }

    /**
     * {@code PATCH  /uuid-id-filtering-relationships/:relatedId} : Partial updates given fields of an existing uuidIdFilteringRelationship, field will ignore if it is null
     *
     * @param relatedId the id of the uuidIdFilteringRelationship to save.
     * @param uuidIdFilteringRelationship the uuidIdFilteringRelationship to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uuidIdFilteringRelationship,
     * or with status {@code 400 (Bad Request)} if the uuidIdFilteringRelationship is not valid,
     * or with status {@code 404 (Not Found)} if the uuidIdFilteringRelationship is not found,
     * or with status {@code 500 (Internal Server Error)} if the uuidIdFilteringRelationship couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{relatedId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UuidIdFilteringRelationship> partialUpdateUuidIdFilteringRelationship(
        @PathVariable(value = "relatedId", required = false) final UUID relatedId,
        @RequestBody UuidIdFilteringRelationship uuidIdFilteringRelationship
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update UuidIdFilteringRelationship partially : {}, {}", relatedId, uuidIdFilteringRelationship);
        if (uuidIdFilteringRelationship.getRelatedId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(relatedId, uuidIdFilteringRelationship.getRelatedId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!uuidIdFilteringRelationshipRepository.existsById(relatedId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UuidIdFilteringRelationship> result = uuidIdFilteringRelationshipService.partialUpdate(uuidIdFilteringRelationship);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, uuidIdFilteringRelationship.getRelatedId().toString())
        );
    }

    /**
     * {@code GET  /uuid-id-filtering-relationships} : get all the uuidIdFilteringRelationships.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of uuidIdFilteringRelationships in body.
     */
    @GetMapping("")
    public ResponseEntity<List<UuidIdFilteringRelationship>> getAllUuidIdFilteringRelationships(
        UuidIdFilteringRelationshipCriteria criteria
    ) {
        LOG.debug("REST request to get UuidIdFilteringRelationships by criteria: {}", criteria);

        List<UuidIdFilteringRelationship> entityList = uuidIdFilteringRelationshipQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /uuid-id-filtering-relationships/count} : count all the uuidIdFilteringRelationships.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countUuidIdFilteringRelationships(UuidIdFilteringRelationshipCriteria criteria) {
        LOG.debug("REST request to count UuidIdFilteringRelationships by criteria: {}", criteria);
        return ResponseEntity.ok().body(uuidIdFilteringRelationshipQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /uuid-id-filtering-relationships/:id} : get the "id" uuidIdFilteringRelationship.
     *
     * @param id the id of the uuidIdFilteringRelationship to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the uuidIdFilteringRelationship, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UuidIdFilteringRelationship> getUuidIdFilteringRelationship(@PathVariable("id") UUID id) {
        LOG.debug("REST request to get UuidIdFilteringRelationship : {}", id);
        Optional<UuidIdFilteringRelationship> uuidIdFilteringRelationship = uuidIdFilteringRelationshipService.findOne(id);
        return ResponseUtil.wrapOrNotFound(uuidIdFilteringRelationship);
    }

    /**
     * {@code DELETE  /uuid-id-filtering-relationships/:id} : delete the "id" uuidIdFilteringRelationship.
     *
     * @param id the id of the uuidIdFilteringRelationship to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUuidIdFilteringRelationship(@PathVariable("id") UUID id) {
        LOG.debug("REST request to delete UuidIdFilteringRelationship : {}", id);
        uuidIdFilteringRelationshipService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
