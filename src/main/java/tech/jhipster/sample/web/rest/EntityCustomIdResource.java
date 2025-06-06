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
import tech.jhipster.sample.domain.EntityCustomId;
import tech.jhipster.sample.repository.EntityCustomIdRepository;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.EntityCustomId}.
 */
@RestController
@RequestMapping("/api/entity-custom-ids")
@Transactional
public class EntityCustomIdResource {

    private static final Logger LOG = LoggerFactory.getLogger(EntityCustomIdResource.class);

    private static final String ENTITY_NAME = "entityCustomId";

    @Value("${jhipster.clientApp.name:jhipsterVue}")
    private String applicationName;

    private final EntityCustomIdRepository entityCustomIdRepository;

    public EntityCustomIdResource(EntityCustomIdRepository entityCustomIdRepository) {
        this.entityCustomIdRepository = entityCustomIdRepository;
    }

    /**
     * {@code POST  /entity-custom-ids} : Create a new entityCustomId.
     *
     * @param entityCustomId the entityCustomId to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityCustomId, or with status {@code 400 (Bad Request)} if the entityCustomId has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EntityCustomId> createEntityCustomId(@RequestBody EntityCustomId entityCustomId) throws URISyntaxException {
        LOG.debug("REST request to save EntityCustomId : {}", entityCustomId);
        if (entityCustomId.getCustomId() != null) {
            throw new BadRequestAlertException("A new entityCustomId cannot already have an ID", ENTITY_NAME, "idexists");
        }
        entityCustomId = entityCustomIdRepository.save(entityCustomId);
        return ResponseEntity.created(new URI("/api/entity-custom-ids/" + entityCustomId.getCustomId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, entityCustomId.getCustomId().toString()))
            .body(entityCustomId);
    }

    /**
     * {@code PUT  /entity-custom-ids/:customId} : Updates an existing entityCustomId.
     *
     * @param customId the id of the entityCustomId to save.
     * @param entityCustomId the entityCustomId to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityCustomId,
     * or with status {@code 400 (Bad Request)} if the entityCustomId is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityCustomId couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{customId}")
    public ResponseEntity<EntityCustomId> updateEntityCustomId(
        @PathVariable(value = "customId", required = false) final Long customId,
        @RequestBody EntityCustomId entityCustomId
    ) throws URISyntaxException {
        LOG.debug("REST request to update EntityCustomId : {}, {}", customId, entityCustomId);
        if (entityCustomId.getCustomId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(customId, entityCustomId.getCustomId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityCustomIdRepository.existsById(customId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        entityCustomId = entityCustomIdRepository.save(entityCustomId);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityCustomId.getCustomId().toString()))
            .body(entityCustomId);
    }

    /**
     * {@code PATCH  /entity-custom-ids/:customId} : Partial updates given fields of an existing entityCustomId, field will ignore if it is null
     *
     * @param customId the id of the entityCustomId to save.
     * @param entityCustomId the entityCustomId to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityCustomId,
     * or with status {@code 400 (Bad Request)} if the entityCustomId is not valid,
     * or with status {@code 404 (Not Found)} if the entityCustomId is not found,
     * or with status {@code 500 (Internal Server Error)} if the entityCustomId couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{customId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EntityCustomId> partialUpdateEntityCustomId(
        @PathVariable(value = "customId", required = false) final Long customId,
        @RequestBody EntityCustomId entityCustomId
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update EntityCustomId partially : {}, {}", customId, entityCustomId);
        if (entityCustomId.getCustomId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(customId, entityCustomId.getCustomId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityCustomIdRepository.existsById(customId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EntityCustomId> result = entityCustomIdRepository
            .findById(entityCustomId.getCustomId())
            .map(entityCustomIdRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityCustomId.getCustomId().toString())
        );
    }

    /**
     * {@code GET  /entity-custom-ids} : get all the entityCustomIds.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityCustomIds in body.
     */
    @GetMapping("")
    public List<EntityCustomId> getAllEntityCustomIds(@RequestParam(name = "filter", required = false) String filter) {
        if ("entitycustomidmapsid-is-null".equals(filter)) {
            LOG.debug("REST request to get all EntityCustomIds where entityCustomIdMapsId is null");
            return StreamSupport.stream(entityCustomIdRepository.findAll().spliterator(), false)
                .filter(entityCustomId -> entityCustomId.getEntityCustomIdMapsId() == null)
                .toList();
        }

        if ("onetooneback-is-null".equals(filter)) {
            LOG.debug("REST request to get all EntityCustomIds where oneToOneBack is null");
            return StreamSupport.stream(entityCustomIdRepository.findAll().spliterator(), false)
                .filter(entityCustomId -> entityCustomId.getOneToOneBack() == null)
                .toList();
        }
        LOG.debug("REST request to get all EntityCustomIds");
        return entityCustomIdRepository.findAll();
    }

    /**
     * {@code GET  /entity-custom-ids/:id} : get the "id" entityCustomId.
     *
     * @param id the id of the entityCustomId to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityCustomId, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityCustomId> getEntityCustomId(@PathVariable("id") Long id) {
        LOG.debug("REST request to get EntityCustomId : {}", id);
        Optional<EntityCustomId> entityCustomId = entityCustomIdRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(entityCustomId);
    }

    /**
     * {@code DELETE  /entity-custom-ids/:id} : delete the "id" entityCustomId.
     *
     * @param id the id of the entityCustomId to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntityCustomId(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete EntityCustomId : {}", id);
        entityCustomIdRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
