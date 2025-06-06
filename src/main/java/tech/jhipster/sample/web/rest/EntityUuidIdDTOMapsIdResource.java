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
import tech.jhipster.sample.repository.EntityUuidIdDTOMapsIdRepository;
import tech.jhipster.sample.service.EntityUuidIdDTOMapsIdService;
import tech.jhipster.sample.service.dto.EntityUuidIdDTOMapsIdDTO;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.EntityUuidIdDTOMapsId}.
 */
@RestController
@RequestMapping("/api/entity-uuid-id-dto-maps-ids")
public class EntityUuidIdDTOMapsIdResource {

    private static final Logger LOG = LoggerFactory.getLogger(EntityUuidIdDTOMapsIdResource.class);

    private static final String ENTITY_NAME = "entityUuidIdDTOMapsId";

    @Value("${jhipster.clientApp.name:jhipsterVue}")
    private String applicationName;

    private final EntityUuidIdDTOMapsIdService entityUuidIdDTOMapsIdService;

    private final EntityUuidIdDTOMapsIdRepository entityUuidIdDTOMapsIdRepository;

    public EntityUuidIdDTOMapsIdResource(
        EntityUuidIdDTOMapsIdService entityUuidIdDTOMapsIdService,
        EntityUuidIdDTOMapsIdRepository entityUuidIdDTOMapsIdRepository
    ) {
        this.entityUuidIdDTOMapsIdService = entityUuidIdDTOMapsIdService;
        this.entityUuidIdDTOMapsIdRepository = entityUuidIdDTOMapsIdRepository;
    }

    /**
     * {@code POST  /entity-uuid-id-dto-maps-ids} : Create a new entityUuidIdDTOMapsId.
     *
     * @param entityUuidIdDTOMapsIdDTO the entityUuidIdDTOMapsIdDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityUuidIdDTOMapsIdDTO, or with status {@code 400 (Bad Request)} if the entityUuidIdDTOMapsId has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EntityUuidIdDTOMapsIdDTO> createEntityUuidIdDTOMapsId(
        @RequestBody EntityUuidIdDTOMapsIdDTO entityUuidIdDTOMapsIdDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to save EntityUuidIdDTOMapsId : {}", entityUuidIdDTOMapsIdDTO);
        if (entityUuidIdDTOMapsIdDTO.getId() != null) {
            throw new BadRequestAlertException("A new entityUuidIdDTOMapsId cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (Objects.isNull(entityUuidIdDTOMapsIdDTO.getEntityUuidIdDTO())) {
            throw new BadRequestAlertException("Invalid association value provided", ENTITY_NAME, "null");
        }
        entityUuidIdDTOMapsIdDTO = entityUuidIdDTOMapsIdService.save(entityUuidIdDTOMapsIdDTO);
        return ResponseEntity.created(new URI("/api/entity-uuid-id-dto-maps-ids/" + entityUuidIdDTOMapsIdDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, entityUuidIdDTOMapsIdDTO.getId().toString()))
            .body(entityUuidIdDTOMapsIdDTO);
    }

    /**
     * {@code PUT  /entity-uuid-id-dto-maps-ids/:id} : Updates an existing entityUuidIdDTOMapsId.
     *
     * @param id the id of the entityUuidIdDTOMapsIdDTO to save.
     * @param entityUuidIdDTOMapsIdDTO the entityUuidIdDTOMapsIdDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityUuidIdDTOMapsIdDTO,
     * or with status {@code 400 (Bad Request)} if the entityUuidIdDTOMapsIdDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityUuidIdDTOMapsIdDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityUuidIdDTOMapsIdDTO> updateEntityUuidIdDTOMapsId(
        @PathVariable(value = "id", required = false) final UUID id,
        @RequestBody EntityUuidIdDTOMapsIdDTO entityUuidIdDTOMapsIdDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update EntityUuidIdDTOMapsId : {}, {}", id, entityUuidIdDTOMapsIdDTO);
        if (entityUuidIdDTOMapsIdDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, entityUuidIdDTOMapsIdDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityUuidIdDTOMapsIdRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        entityUuidIdDTOMapsIdDTO = entityUuidIdDTOMapsIdService.update(entityUuidIdDTOMapsIdDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityUuidIdDTOMapsIdDTO.getId().toString()))
            .body(entityUuidIdDTOMapsIdDTO);
    }

    /**
     * {@code PATCH  /entity-uuid-id-dto-maps-ids/:id} : Partial updates given fields of an existing entityUuidIdDTOMapsId, field will ignore if it is null
     *
     * @param id the id of the entityUuidIdDTOMapsIdDTO to save.
     * @param entityUuidIdDTOMapsIdDTO the entityUuidIdDTOMapsIdDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityUuidIdDTOMapsIdDTO,
     * or with status {@code 400 (Bad Request)} if the entityUuidIdDTOMapsIdDTO is not valid,
     * or with status {@code 404 (Not Found)} if the entityUuidIdDTOMapsIdDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the entityUuidIdDTOMapsIdDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EntityUuidIdDTOMapsIdDTO> partialUpdateEntityUuidIdDTOMapsId(
        @PathVariable(value = "id", required = false) final UUID id,
        @RequestBody EntityUuidIdDTOMapsIdDTO entityUuidIdDTOMapsIdDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update EntityUuidIdDTOMapsId partially : {}, {}", id, entityUuidIdDTOMapsIdDTO);
        if (entityUuidIdDTOMapsIdDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, entityUuidIdDTOMapsIdDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityUuidIdDTOMapsIdRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EntityUuidIdDTOMapsIdDTO> result = entityUuidIdDTOMapsIdService.partialUpdate(entityUuidIdDTOMapsIdDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityUuidIdDTOMapsIdDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /entity-uuid-id-dto-maps-ids} : get all the entityUuidIdDTOMapsIds.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityUuidIdDTOMapsIds in body.
     */
    @GetMapping("")
    public List<EntityUuidIdDTOMapsIdDTO> getAllEntityUuidIdDTOMapsIds(@RequestParam(name = "filter", required = false) String filter) {
        if ("onetoonemapsidback-is-null".equals(filter)) {
            LOG.debug("REST request to get all EntityUuidIdDTOMapsIds where oneToOneMapsIdBack is null");
            return entityUuidIdDTOMapsIdService.findAllWhereOneToOneMapsIdBackIsNull();
        }
        LOG.debug("REST request to get all EntityUuidIdDTOMapsIds");
        return entityUuidIdDTOMapsIdService.findAll();
    }

    /**
     * {@code GET  /entity-uuid-id-dto-maps-ids/:id} : get the "id" entityUuidIdDTOMapsId.
     *
     * @param id the id of the entityUuidIdDTOMapsIdDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityUuidIdDTOMapsIdDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityUuidIdDTOMapsIdDTO> getEntityUuidIdDTOMapsId(@PathVariable("id") UUID id) {
        LOG.debug("REST request to get EntityUuidIdDTOMapsId : {}", id);
        Optional<EntityUuidIdDTOMapsIdDTO> entityUuidIdDTOMapsIdDTO = entityUuidIdDTOMapsIdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entityUuidIdDTOMapsIdDTO);
    }

    /**
     * {@code DELETE  /entity-uuid-id-dto-maps-ids/:id} : delete the "id" entityUuidIdDTOMapsId.
     *
     * @param id the id of the entityUuidIdDTOMapsIdDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntityUuidIdDTOMapsId(@PathVariable("id") UUID id) {
        LOG.debug("REST request to delete EntityUuidIdDTOMapsId : {}", id);
        entityUuidIdDTOMapsIdService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
