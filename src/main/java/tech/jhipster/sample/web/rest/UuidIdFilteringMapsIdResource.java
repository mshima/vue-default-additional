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
import tech.jhipster.sample.domain.UuidIdFilteringMapsId;
import tech.jhipster.sample.repository.UuidIdFilteringMapsIdRepository;
import tech.jhipster.sample.service.UuidIdFilteringMapsIdQueryService;
import tech.jhipster.sample.service.UuidIdFilteringMapsIdService;
import tech.jhipster.sample.service.criteria.UuidIdFilteringMapsIdCriteria;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.UuidIdFilteringMapsId}.
 */
@RestController
@RequestMapping("/api/uuid-id-filtering-maps-ids")
public class UuidIdFilteringMapsIdResource {

    private static final Logger LOG = LoggerFactory.getLogger(UuidIdFilteringMapsIdResource.class);

    private static final String ENTITY_NAME = "uuidIdFilteringMapsId";

    @Value("${jhipster.clientApp.name:jhipsterVue}")
    private String applicationName;

    private final UuidIdFilteringMapsIdService uuidIdFilteringMapsIdService;

    private final UuidIdFilteringMapsIdRepository uuidIdFilteringMapsIdRepository;

    private final UuidIdFilteringMapsIdQueryService uuidIdFilteringMapsIdQueryService;

    public UuidIdFilteringMapsIdResource(
        UuidIdFilteringMapsIdService uuidIdFilteringMapsIdService,
        UuidIdFilteringMapsIdRepository uuidIdFilteringMapsIdRepository,
        UuidIdFilteringMapsIdQueryService uuidIdFilteringMapsIdQueryService
    ) {
        this.uuidIdFilteringMapsIdService = uuidIdFilteringMapsIdService;
        this.uuidIdFilteringMapsIdRepository = uuidIdFilteringMapsIdRepository;
        this.uuidIdFilteringMapsIdQueryService = uuidIdFilteringMapsIdQueryService;
    }

    /**
     * {@code POST  /uuid-id-filtering-maps-ids} : Create a new uuidIdFilteringMapsId.
     *
     * @param uuidIdFilteringMapsId the uuidIdFilteringMapsId to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new uuidIdFilteringMapsId, or with status {@code 400 (Bad Request)} if the uuidIdFilteringMapsId has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<UuidIdFilteringMapsId> createUuidIdFilteringMapsId(@RequestBody UuidIdFilteringMapsId uuidIdFilteringMapsId)
        throws URISyntaxException {
        LOG.debug("REST request to save UuidIdFilteringMapsId : {}", uuidIdFilteringMapsId);
        if (uuidIdFilteringMapsId.getCustomId() != null) {
            throw new BadRequestAlertException("A new uuidIdFilteringMapsId cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (Objects.isNull(uuidIdFilteringMapsId.getUuidIdFiltering())) {
            throw new BadRequestAlertException("Invalid association value provided", ENTITY_NAME, "null");
        }
        uuidIdFilteringMapsId = uuidIdFilteringMapsIdService.save(uuidIdFilteringMapsId);
        return ResponseEntity.created(new URI("/api/uuid-id-filtering-maps-ids/" + uuidIdFilteringMapsId.getCustomId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, uuidIdFilteringMapsId.getCustomId().toString())
            )
            .body(uuidIdFilteringMapsId);
    }

    /**
     * {@code PUT  /uuid-id-filtering-maps-ids/:customId} : Updates an existing uuidIdFilteringMapsId.
     *
     * @param customId the id of the uuidIdFilteringMapsId to save.
     * @param uuidIdFilteringMapsId the uuidIdFilteringMapsId to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uuidIdFilteringMapsId,
     * or with status {@code 400 (Bad Request)} if the uuidIdFilteringMapsId is not valid,
     * or with status {@code 500 (Internal Server Error)} if the uuidIdFilteringMapsId couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{customId}")
    public ResponseEntity<UuidIdFilteringMapsId> updateUuidIdFilteringMapsId(
        @PathVariable(value = "customId", required = false) final UUID customId,
        @RequestBody UuidIdFilteringMapsId uuidIdFilteringMapsId
    ) throws URISyntaxException {
        LOG.debug("REST request to update UuidIdFilteringMapsId : {}, {}", customId, uuidIdFilteringMapsId);
        if (uuidIdFilteringMapsId.getCustomId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(customId, uuidIdFilteringMapsId.getCustomId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!uuidIdFilteringMapsIdRepository.existsById(customId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        uuidIdFilteringMapsId = uuidIdFilteringMapsIdService.update(uuidIdFilteringMapsId);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, uuidIdFilteringMapsId.getCustomId().toString()))
            .body(uuidIdFilteringMapsId);
    }

    /**
     * {@code PATCH  /uuid-id-filtering-maps-ids/:customId} : Partial updates given fields of an existing uuidIdFilteringMapsId, field will ignore if it is null
     *
     * @param customId the id of the uuidIdFilteringMapsId to save.
     * @param uuidIdFilteringMapsId the uuidIdFilteringMapsId to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uuidIdFilteringMapsId,
     * or with status {@code 400 (Bad Request)} if the uuidIdFilteringMapsId is not valid,
     * or with status {@code 404 (Not Found)} if the uuidIdFilteringMapsId is not found,
     * or with status {@code 500 (Internal Server Error)} if the uuidIdFilteringMapsId couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{customId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UuidIdFilteringMapsId> partialUpdateUuidIdFilteringMapsId(
        @PathVariable(value = "customId", required = false) final UUID customId,
        @RequestBody UuidIdFilteringMapsId uuidIdFilteringMapsId
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update UuidIdFilteringMapsId partially : {}, {}", customId, uuidIdFilteringMapsId);
        if (uuidIdFilteringMapsId.getCustomId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(customId, uuidIdFilteringMapsId.getCustomId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!uuidIdFilteringMapsIdRepository.existsById(customId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UuidIdFilteringMapsId> result = uuidIdFilteringMapsIdService.partialUpdate(uuidIdFilteringMapsId);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, uuidIdFilteringMapsId.getCustomId().toString())
        );
    }

    /**
     * {@code GET  /uuid-id-filtering-maps-ids} : get all the uuidIdFilteringMapsIds.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of uuidIdFilteringMapsIds in body.
     */
    @GetMapping("")
    public ResponseEntity<List<UuidIdFilteringMapsId>> getAllUuidIdFilteringMapsIds(UuidIdFilteringMapsIdCriteria criteria) {
        LOG.debug("REST request to get UuidIdFilteringMapsIds by criteria: {}", criteria);

        List<UuidIdFilteringMapsId> entityList = uuidIdFilteringMapsIdQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /uuid-id-filtering-maps-ids/count} : count all the uuidIdFilteringMapsIds.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countUuidIdFilteringMapsIds(UuidIdFilteringMapsIdCriteria criteria) {
        LOG.debug("REST request to count UuidIdFilteringMapsIds by criteria: {}", criteria);
        return ResponseEntity.ok().body(uuidIdFilteringMapsIdQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /uuid-id-filtering-maps-ids/:id} : get the "id" uuidIdFilteringMapsId.
     *
     * @param id the id of the uuidIdFilteringMapsId to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the uuidIdFilteringMapsId, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UuidIdFilteringMapsId> getUuidIdFilteringMapsId(@PathVariable("id") UUID id) {
        LOG.debug("REST request to get UuidIdFilteringMapsId : {}", id);
        Optional<UuidIdFilteringMapsId> uuidIdFilteringMapsId = uuidIdFilteringMapsIdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(uuidIdFilteringMapsId);
    }

    /**
     * {@code DELETE  /uuid-id-filtering-maps-ids/:id} : delete the "id" uuidIdFilteringMapsId.
     *
     * @param id the id of the uuidIdFilteringMapsId to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUuidIdFilteringMapsId(@PathVariable("id") UUID id) {
        LOG.debug("REST request to delete UuidIdFilteringMapsId : {}", id);
        uuidIdFilteringMapsIdService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
