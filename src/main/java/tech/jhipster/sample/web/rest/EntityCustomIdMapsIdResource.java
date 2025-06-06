package tech.jhipster.sample.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.sample.domain.EntityCustomIdMapsId;
import tech.jhipster.sample.repository.EntityCustomIdMapsIdRepository;
import tech.jhipster.sample.repository.EntityCustomIdRepository;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.EntityCustomIdMapsId}.
 */
@RestController
@RequestMapping("/api/entity-custom-id-maps-ids")
@Transactional
public class EntityCustomIdMapsIdResource {

    private static final Logger LOG = LoggerFactory.getLogger(EntityCustomIdMapsIdResource.class);

    private static final String ENTITY_NAME = "entityCustomIdMapsId";

    @Value("${jhipster.clientApp.name:jhipsterVue}")
    private String applicationName;

    private final EntityCustomIdMapsIdRepository entityCustomIdMapsIdRepository;

    private final EntityCustomIdRepository entityCustomIdRepository;

    public EntityCustomIdMapsIdResource(
        EntityCustomIdMapsIdRepository entityCustomIdMapsIdRepository,
        EntityCustomIdRepository entityCustomIdRepository
    ) {
        this.entityCustomIdMapsIdRepository = entityCustomIdMapsIdRepository;
        this.entityCustomIdRepository = entityCustomIdRepository;
    }

    /**
     * {@code POST  /entity-custom-id-maps-ids} : Create a new entityCustomIdMapsId.
     *
     * @param entityCustomIdMapsId the entityCustomIdMapsId to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityCustomIdMapsId, or with status {@code 400 (Bad Request)} if the entityCustomIdMapsId has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EntityCustomIdMapsId> createEntityCustomIdMapsId(@RequestBody EntityCustomIdMapsId entityCustomIdMapsId)
        throws URISyntaxException {
        LOG.debug("REST request to save EntityCustomIdMapsId : {}", entityCustomIdMapsId);
        if (entityCustomIdMapsId.getCustomId() != null) {
            throw new BadRequestAlertException("A new entityCustomIdMapsId cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (Objects.isNull(entityCustomIdMapsId.getEntityCustomId())) {
            throw new BadRequestAlertException("Invalid association value provided", ENTITY_NAME, "null");
        }
        Long entityCustomIdId = entityCustomIdMapsId.getEntityCustomId().getCustomId();
        entityCustomIdRepository.findById(entityCustomIdId).ifPresent(entityCustomIdMapsId::entityCustomId);
        entityCustomIdMapsId = entityCustomIdMapsIdRepository.save(entityCustomIdMapsId);
        return ResponseEntity.created(new URI("/api/entity-custom-id-maps-ids/" + entityCustomIdMapsId.getCustomId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, entityCustomIdMapsId.getCustomId().toString())
            )
            .body(entityCustomIdMapsId);
    }

    /**
     * {@code PUT  /entity-custom-id-maps-ids/:customId} : Updates an existing entityCustomIdMapsId.
     *
     * @param customId the id of the entityCustomIdMapsId to save.
     * @param entityCustomIdMapsId the entityCustomIdMapsId to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityCustomIdMapsId,
     * or with status {@code 400 (Bad Request)} if the entityCustomIdMapsId is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityCustomIdMapsId couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{customId}")
    public ResponseEntity<EntityCustomIdMapsId> updateEntityCustomIdMapsId(
        @PathVariable(value = "customId", required = false) final Long customId,
        @RequestBody EntityCustomIdMapsId entityCustomIdMapsId
    ) throws URISyntaxException {
        LOG.debug("REST request to update EntityCustomIdMapsId : {}, {}", customId, entityCustomIdMapsId);
        if (entityCustomIdMapsId.getCustomId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(customId, entityCustomIdMapsId.getCustomId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityCustomIdMapsIdRepository.existsById(customId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        entityCustomIdMapsId = entityCustomIdMapsIdRepository.save(entityCustomIdMapsId);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityCustomIdMapsId.getCustomId().toString()))
            .body(entityCustomIdMapsId);
    }

    /**
     * {@code PATCH  /entity-custom-id-maps-ids/:customId} : Partial updates given fields of an existing entityCustomIdMapsId, field will ignore if it is null
     *
     * @param customId the id of the entityCustomIdMapsId to save.
     * @param entityCustomIdMapsId the entityCustomIdMapsId to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityCustomIdMapsId,
     * or with status {@code 400 (Bad Request)} if the entityCustomIdMapsId is not valid,
     * or with status {@code 404 (Not Found)} if the entityCustomIdMapsId is not found,
     * or with status {@code 500 (Internal Server Error)} if the entityCustomIdMapsId couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{customId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EntityCustomIdMapsId> partialUpdateEntityCustomIdMapsId(
        @PathVariable(value = "customId", required = false) final Long customId,
        @RequestBody EntityCustomIdMapsId entityCustomIdMapsId
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update EntityCustomIdMapsId partially : {}, {}", customId, entityCustomIdMapsId);
        if (entityCustomIdMapsId.getCustomId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(customId, entityCustomIdMapsId.getCustomId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityCustomIdMapsIdRepository.existsById(customId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EntityCustomIdMapsId> result = entityCustomIdMapsIdRepository
            .findById(entityCustomIdMapsId.getCustomId())
            .map(entityCustomIdMapsIdRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityCustomIdMapsId.getCustomId().toString())
        );
    }

    /**
     * {@code GET  /entity-custom-id-maps-ids} : get all the entityCustomIdMapsIds.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityCustomIdMapsIds in body.
     */
    @GetMapping("")
    @Transactional(readOnly = true)
    public List<EntityCustomIdMapsId> getAllEntityCustomIdMapsIds(@RequestParam(name = "filter", required = false) String filter) {
        if ("onetoonemapsidback-is-null".equals(filter)) {
            LOG.debug("REST request to get all EntityCustomIdMapsIds where oneToOneMapsIdBack is null");
            return StreamSupport.stream(entityCustomIdMapsIdRepository.findAll().spliterator(), false)
                .filter(entityCustomIdMapsId -> entityCustomIdMapsId.getOneToOneMapsIdBack() == null)
                .toList();
        }
        LOG.debug("REST request to get all EntityCustomIdMapsIds");
        return entityCustomIdMapsIdRepository.findAll();
    }

    /**
     * {@code GET  /entity-custom-id-maps-ids/:id} : get the "id" entityCustomIdMapsId.
     *
     * @param id the id of the entityCustomIdMapsId to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityCustomIdMapsId, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<EntityCustomIdMapsId> getEntityCustomIdMapsId(@PathVariable("id") Long id) {
        LOG.debug("REST request to get EntityCustomIdMapsId : {}", id);
        Optional<EntityCustomIdMapsId> entityCustomIdMapsId = entityCustomIdMapsIdRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(entityCustomIdMapsId);
    }

    /**
     * {@code DELETE  /entity-custom-id-maps-ids/:id} : delete the "id" entityCustomIdMapsId.
     *
     * @param id the id of the entityCustomIdMapsId to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntityCustomIdMapsId(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete EntityCustomIdMapsId : {}", id);
        entityCustomIdMapsIdRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
