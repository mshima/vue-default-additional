package tech.jhipster.sample.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.sample.domain.EntityUuidIdMapsId;
import tech.jhipster.sample.repository.EntityUuidIdMapsIdRepository;
import tech.jhipster.sample.repository.EntityUuidIdRepository;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.EntityUuidIdMapsId}.
 */
@RestController
@RequestMapping("/api/entity-uuid-id-maps-ids")
@Transactional
public class EntityUuidIdMapsIdResource {

    private static final Logger LOG = LoggerFactory.getLogger(EntityUuidIdMapsIdResource.class);

    private static final String ENTITY_NAME = "entityUuidIdMapsId";

    @Value("${jhipster.clientApp.name:jhipsterVue}")
    private String applicationName;

    private final EntityUuidIdMapsIdRepository entityUuidIdMapsIdRepository;

    private final EntityUuidIdRepository entityUuidIdRepository;

    public EntityUuidIdMapsIdResource(
        EntityUuidIdMapsIdRepository entityUuidIdMapsIdRepository,
        EntityUuidIdRepository entityUuidIdRepository
    ) {
        this.entityUuidIdMapsIdRepository = entityUuidIdMapsIdRepository;
        this.entityUuidIdRepository = entityUuidIdRepository;
    }

    /**
     * {@code POST  /entity-uuid-id-maps-ids} : Create a new entityUuidIdMapsId.
     *
     * @param entityUuidIdMapsId the entityUuidIdMapsId to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityUuidIdMapsId, or with status {@code 400 (Bad Request)} if the entityUuidIdMapsId has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EntityUuidIdMapsId> createEntityUuidIdMapsId(@RequestBody EntityUuidIdMapsId entityUuidIdMapsId)
        throws URISyntaxException {
        LOG.debug("REST request to save EntityUuidIdMapsId : {}", entityUuidIdMapsId);
        if (entityUuidIdMapsId.getId() != null) {
            throw new BadRequestAlertException("A new entityUuidIdMapsId cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (Objects.isNull(entityUuidIdMapsId.getEntityUuidId())) {
            throw new BadRequestAlertException("Invalid association value provided", ENTITY_NAME, "null");
        }
        UUID entityUuidIdId = entityUuidIdMapsId.getEntityUuidId().getId();
        entityUuidIdRepository.findById(entityUuidIdId).ifPresent(entityUuidIdMapsId::entityUuidId);
        entityUuidIdMapsId = entityUuidIdMapsIdRepository.save(entityUuidIdMapsId);
        return ResponseEntity.created(new URI("/api/entity-uuid-id-maps-ids/" + entityUuidIdMapsId.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, entityUuidIdMapsId.getId().toString()))
            .body(entityUuidIdMapsId);
    }

    /**
     * {@code PUT  /entity-uuid-id-maps-ids/:id} : Updates an existing entityUuidIdMapsId.
     *
     * @param id the id of the entityUuidIdMapsId to save.
     * @param entityUuidIdMapsId the entityUuidIdMapsId to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityUuidIdMapsId,
     * or with status {@code 400 (Bad Request)} if the entityUuidIdMapsId is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityUuidIdMapsId couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityUuidIdMapsId> updateEntityUuidIdMapsId(
        @PathVariable(value = "id", required = false) final UUID id,
        @RequestBody EntityUuidIdMapsId entityUuidIdMapsId
    ) throws URISyntaxException {
        LOG.debug("REST request to update EntityUuidIdMapsId : {}, {}", id, entityUuidIdMapsId);
        if (entityUuidIdMapsId.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, entityUuidIdMapsId.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityUuidIdMapsIdRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        entityUuidIdMapsId = entityUuidIdMapsIdRepository.save(entityUuidIdMapsId);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityUuidIdMapsId.getId().toString()))
            .body(entityUuidIdMapsId);
    }

    /**
     * {@code PATCH  /entity-uuid-id-maps-ids/:id} : Partial updates given fields of an existing entityUuidIdMapsId, field will ignore if it is null
     *
     * @param id the id of the entityUuidIdMapsId to save.
     * @param entityUuidIdMapsId the entityUuidIdMapsId to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityUuidIdMapsId,
     * or with status {@code 400 (Bad Request)} if the entityUuidIdMapsId is not valid,
     * or with status {@code 404 (Not Found)} if the entityUuidIdMapsId is not found,
     * or with status {@code 500 (Internal Server Error)} if the entityUuidIdMapsId couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EntityUuidIdMapsId> partialUpdateEntityUuidIdMapsId(
        @PathVariable(value = "id", required = false) final UUID id,
        @RequestBody EntityUuidIdMapsId entityUuidIdMapsId
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update EntityUuidIdMapsId partially : {}, {}", id, entityUuidIdMapsId);
        if (entityUuidIdMapsId.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, entityUuidIdMapsId.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityUuidIdMapsIdRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EntityUuidIdMapsId> result = entityUuidIdMapsIdRepository
            .findById(entityUuidIdMapsId.getId())
            .map(entityUuidIdMapsIdRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityUuidIdMapsId.getId().toString())
        );
    }

    /**
     * {@code GET  /entity-uuid-id-maps-ids} : get all the entityUuidIdMapsIds.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityUuidIdMapsIds in body.
     */
    @GetMapping("")
    @Transactional(readOnly = true)
    public List<EntityUuidIdMapsId> getAllEntityUuidIdMapsIds(@RequestParam(name = "filter", required = false) String filter) {
        if ("onetoonemapsidback-is-null".equals(filter)) {
            LOG.debug("REST request to get all EntityUuidIdMapsIds where oneToOneMapsIdBack is null");
            return StreamSupport.stream(entityUuidIdMapsIdRepository.findAll().spliterator(), false)
                .filter(entityUuidIdMapsId -> entityUuidIdMapsId.getOneToOneMapsIdBack() == null)
                .toList();
        }
        LOG.debug("REST request to get all EntityUuidIdMapsIds");
        return entityUuidIdMapsIdRepository.findAll();
    }

    /**
     * {@code GET  /entity-uuid-id-maps-ids/:id} : get the "id" entityUuidIdMapsId.
     *
     * @param id the id of the entityUuidIdMapsId to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityUuidIdMapsId, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<EntityUuidIdMapsId> getEntityUuidIdMapsId(@PathVariable("id") UUID id) {
        LOG.debug("REST request to get EntityUuidIdMapsId : {}", id);
        Optional<EntityUuidIdMapsId> entityUuidIdMapsId = entityUuidIdMapsIdRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(entityUuidIdMapsId);
    }

    /**
     * {@code DELETE  /entity-uuid-id-maps-ids/:id} : delete the "id" entityUuidIdMapsId.
     *
     * @param id the id of the entityUuidIdMapsId to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntityUuidIdMapsId(@PathVariable("id") UUID id) {
        LOG.debug("REST request to delete EntityUuidIdMapsId : {}", id);
        entityUuidIdMapsIdRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
