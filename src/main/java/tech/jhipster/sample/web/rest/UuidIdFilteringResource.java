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
import tech.jhipster.sample.domain.UuidIdFiltering;
import tech.jhipster.sample.repository.UuidIdFilteringRepository;
import tech.jhipster.sample.service.UuidIdFilteringQueryService;
import tech.jhipster.sample.service.UuidIdFilteringService;
import tech.jhipster.sample.service.criteria.UuidIdFilteringCriteria;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.UuidIdFiltering}.
 */
@RestController
@RequestMapping("/api/uuid-id-filterings")
public class UuidIdFilteringResource {

    private static final Logger LOG = LoggerFactory.getLogger(UuidIdFilteringResource.class);

    private static final String ENTITY_NAME = "uuidIdFiltering";

    @Value("${jhipster.clientApp.name:jhipsterVue}")
    private String applicationName;

    private final UuidIdFilteringService uuidIdFilteringService;

    private final UuidIdFilteringRepository uuidIdFilteringRepository;

    private final UuidIdFilteringQueryService uuidIdFilteringQueryService;

    public UuidIdFilteringResource(
        UuidIdFilteringService uuidIdFilteringService,
        UuidIdFilteringRepository uuidIdFilteringRepository,
        UuidIdFilteringQueryService uuidIdFilteringQueryService
    ) {
        this.uuidIdFilteringService = uuidIdFilteringService;
        this.uuidIdFilteringRepository = uuidIdFilteringRepository;
        this.uuidIdFilteringQueryService = uuidIdFilteringQueryService;
    }

    /**
     * {@code POST  /uuid-id-filterings} : Create a new uuidIdFiltering.
     *
     * @param uuidIdFiltering the uuidIdFiltering to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new uuidIdFiltering, or with status {@code 400 (Bad Request)} if the uuidIdFiltering has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<UuidIdFiltering> createUuidIdFiltering(@RequestBody UuidIdFiltering uuidIdFiltering) throws URISyntaxException {
        LOG.debug("REST request to save UuidIdFiltering : {}", uuidIdFiltering);
        if (uuidIdFiltering.getCustomId() != null) {
            throw new BadRequestAlertException("A new uuidIdFiltering cannot already have an ID", ENTITY_NAME, "idexists");
        }
        uuidIdFiltering = uuidIdFilteringService.save(uuidIdFiltering);
        return ResponseEntity.created(new URI("/api/uuid-id-filterings/" + uuidIdFiltering.getCustomId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, uuidIdFiltering.getCustomId().toString()))
            .body(uuidIdFiltering);
    }

    /**
     * {@code PUT  /uuid-id-filterings/:customId} : Updates an existing uuidIdFiltering.
     *
     * @param customId the id of the uuidIdFiltering to save.
     * @param uuidIdFiltering the uuidIdFiltering to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uuidIdFiltering,
     * or with status {@code 400 (Bad Request)} if the uuidIdFiltering is not valid,
     * or with status {@code 500 (Internal Server Error)} if the uuidIdFiltering couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{customId}")
    public ResponseEntity<UuidIdFiltering> updateUuidIdFiltering(
        @PathVariable(value = "customId", required = false) final UUID customId,
        @RequestBody UuidIdFiltering uuidIdFiltering
    ) throws URISyntaxException {
        LOG.debug("REST request to update UuidIdFiltering : {}, {}", customId, uuidIdFiltering);
        if (uuidIdFiltering.getCustomId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(customId, uuidIdFiltering.getCustomId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!uuidIdFilteringRepository.existsById(customId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        uuidIdFiltering = uuidIdFilteringService.update(uuidIdFiltering);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, uuidIdFiltering.getCustomId().toString()))
            .body(uuidIdFiltering);
    }

    /**
     * {@code PATCH  /uuid-id-filterings/:customId} : Partial updates given fields of an existing uuidIdFiltering, field will ignore if it is null
     *
     * @param customId the id of the uuidIdFiltering to save.
     * @param uuidIdFiltering the uuidIdFiltering to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uuidIdFiltering,
     * or with status {@code 400 (Bad Request)} if the uuidIdFiltering is not valid,
     * or with status {@code 404 (Not Found)} if the uuidIdFiltering is not found,
     * or with status {@code 500 (Internal Server Error)} if the uuidIdFiltering couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{customId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UuidIdFiltering> partialUpdateUuidIdFiltering(
        @PathVariable(value = "customId", required = false) final UUID customId,
        @RequestBody UuidIdFiltering uuidIdFiltering
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update UuidIdFiltering partially : {}, {}", customId, uuidIdFiltering);
        if (uuidIdFiltering.getCustomId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(customId, uuidIdFiltering.getCustomId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!uuidIdFilteringRepository.existsById(customId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UuidIdFiltering> result = uuidIdFilteringService.partialUpdate(uuidIdFiltering);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, uuidIdFiltering.getCustomId().toString())
        );
    }

    /**
     * {@code GET  /uuid-id-filterings} : get all the uuidIdFilterings.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of uuidIdFilterings in body.
     */
    @GetMapping("")
    public ResponseEntity<List<UuidIdFiltering>> getAllUuidIdFilterings(UuidIdFilteringCriteria criteria) {
        LOG.debug("REST request to get UuidIdFilterings by criteria: {}", criteria);

        List<UuidIdFiltering> entityList = uuidIdFilteringQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /uuid-id-filterings/count} : count all the uuidIdFilterings.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countUuidIdFilterings(UuidIdFilteringCriteria criteria) {
        LOG.debug("REST request to count UuidIdFilterings by criteria: {}", criteria);
        return ResponseEntity.ok().body(uuidIdFilteringQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /uuid-id-filterings/:id} : get the "id" uuidIdFiltering.
     *
     * @param id the id of the uuidIdFiltering to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the uuidIdFiltering, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UuidIdFiltering> getUuidIdFiltering(@PathVariable("id") UUID id) {
        LOG.debug("REST request to get UuidIdFiltering : {}", id);
        Optional<UuidIdFiltering> uuidIdFiltering = uuidIdFilteringService.findOne(id);
        return ResponseUtil.wrapOrNotFound(uuidIdFiltering);
    }

    /**
     * {@code DELETE  /uuid-id-filterings/:id} : delete the "id" uuidIdFiltering.
     *
     * @param id the id of the uuidIdFiltering to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUuidIdFiltering(@PathVariable("id") UUID id) {
        LOG.debug("REST request to delete UuidIdFiltering : {}", id);
        uuidIdFilteringService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
