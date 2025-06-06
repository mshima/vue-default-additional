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
import tech.jhipster.sample.domain.EntityUuidId;
import tech.jhipster.sample.repository.EntityUuidIdRepository;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.EntityUuidId}.
 */
@RestController
@RequestMapping("/api/entity-uuid-ids")
@Transactional
public class EntityUuidIdResource {

    private static final Logger LOG = LoggerFactory.getLogger(EntityUuidIdResource.class);

    private static final String ENTITY_NAME = "entityUuidId";

    @Value("${jhipster.clientApp.name:jhipsterVue}")
    private String applicationName;

    private final EntityUuidIdRepository entityUuidIdRepository;

    public EntityUuidIdResource(EntityUuidIdRepository entityUuidIdRepository) {
        this.entityUuidIdRepository = entityUuidIdRepository;
    }

    /**
     * {@code POST  /entity-uuid-ids} : Create a new entityUuidId.
     *
     * @param entityUuidId the entityUuidId to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityUuidId, or with status {@code 400 (Bad Request)} if the entityUuidId has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EntityUuidId> createEntityUuidId(@RequestBody EntityUuidId entityUuidId) throws URISyntaxException {
        LOG.debug("REST request to save EntityUuidId : {}", entityUuidId);
        if (entityUuidId.getId() != null) {
            throw new BadRequestAlertException("A new entityUuidId cannot already have an ID", ENTITY_NAME, "idexists");
        }
        entityUuidId = entityUuidIdRepository.save(entityUuidId);
        return ResponseEntity.created(new URI("/api/entity-uuid-ids/" + entityUuidId.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, entityUuidId.getId().toString()))
            .body(entityUuidId);
    }

    /**
     * {@code PUT  /entity-uuid-ids/:id} : Updates an existing entityUuidId.
     *
     * @param id the id of the entityUuidId to save.
     * @param entityUuidId the entityUuidId to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityUuidId,
     * or with status {@code 400 (Bad Request)} if the entityUuidId is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityUuidId couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityUuidId> updateEntityUuidId(
        @PathVariable(value = "id", required = false) final UUID id,
        @RequestBody EntityUuidId entityUuidId
    ) throws URISyntaxException {
        LOG.debug("REST request to update EntityUuidId : {}, {}", id, entityUuidId);
        if (entityUuidId.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, entityUuidId.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityUuidIdRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        entityUuidId = entityUuidIdRepository.save(entityUuidId);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityUuidId.getId().toString()))
            .body(entityUuidId);
    }

    /**
     * {@code PATCH  /entity-uuid-ids/:id} : Partial updates given fields of an existing entityUuidId, field will ignore if it is null
     *
     * @param id the id of the entityUuidId to save.
     * @param entityUuidId the entityUuidId to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityUuidId,
     * or with status {@code 400 (Bad Request)} if the entityUuidId is not valid,
     * or with status {@code 404 (Not Found)} if the entityUuidId is not found,
     * or with status {@code 500 (Internal Server Error)} if the entityUuidId couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EntityUuidId> partialUpdateEntityUuidId(
        @PathVariable(value = "id", required = false) final UUID id,
        @RequestBody EntityUuidId entityUuidId
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update EntityUuidId partially : {}, {}", id, entityUuidId);
        if (entityUuidId.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, entityUuidId.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityUuidIdRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EntityUuidId> result = entityUuidIdRepository.findById(entityUuidId.getId()).map(entityUuidIdRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityUuidId.getId().toString())
        );
    }

    /**
     * {@code GET  /entity-uuid-ids} : get all the entityUuidIds.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityUuidIds in body.
     */
    @GetMapping("")
    public List<EntityUuidId> getAllEntityUuidIds(@RequestParam(name = "filter", required = false) String filter) {
        if ("entityuuididmapsid-is-null".equals(filter)) {
            LOG.debug("REST request to get all EntityUuidIds where entityUuidIdMapsId is null");
            return StreamSupport.stream(entityUuidIdRepository.findAll().spliterator(), false)
                .filter(entityUuidId -> entityUuidId.getEntityUuidIdMapsId() == null)
                .toList();
        }

        if ("onetooneback-is-null".equals(filter)) {
            LOG.debug("REST request to get all EntityUuidIds where oneToOneBack is null");
            return StreamSupport.stream(entityUuidIdRepository.findAll().spliterator(), false)
                .filter(entityUuidId -> entityUuidId.getOneToOneBack() == null)
                .toList();
        }
        LOG.debug("REST request to get all EntityUuidIds");
        return entityUuidIdRepository.findAll();
    }

    /**
     * {@code GET  /entity-uuid-ids/:id} : get the "id" entityUuidId.
     *
     * @param id the id of the entityUuidId to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityUuidId, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityUuidId> getEntityUuidId(@PathVariable("id") UUID id) {
        LOG.debug("REST request to get EntityUuidId : {}", id);
        Optional<EntityUuidId> entityUuidId = entityUuidIdRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(entityUuidId);
    }

    /**
     * {@code DELETE  /entity-uuid-ids/:id} : delete the "id" entityUuidId.
     *
     * @param id the id of the entityUuidId to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntityUuidId(@PathVariable("id") UUID id) {
        LOG.debug("REST request to delete EntityUuidId : {}", id);
        entityUuidIdRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
