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
import tech.jhipster.sample.repository.EntityUuidIdDTORelRepository;
import tech.jhipster.sample.service.EntityUuidIdDTORelService;
import tech.jhipster.sample.service.dto.EntityUuidIdDTORelDTO;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.EntityUuidIdDTORel}.
 */
@RestController
@RequestMapping("/api/entity-uuid-id-dto-rels")
public class EntityUuidIdDTORelResource {

    private static final Logger LOG = LoggerFactory.getLogger(EntityUuidIdDTORelResource.class);

    private static final String ENTITY_NAME = "entityUuidIdDTORel";

    @Value("${jhipster.clientApp.name:jhipsterVue}")
    private String applicationName;

    private final EntityUuidIdDTORelService entityUuidIdDTORelService;

    private final EntityUuidIdDTORelRepository entityUuidIdDTORelRepository;

    public EntityUuidIdDTORelResource(
        EntityUuidIdDTORelService entityUuidIdDTORelService,
        EntityUuidIdDTORelRepository entityUuidIdDTORelRepository
    ) {
        this.entityUuidIdDTORelService = entityUuidIdDTORelService;
        this.entityUuidIdDTORelRepository = entityUuidIdDTORelRepository;
    }

    /**
     * {@code POST  /entity-uuid-id-dto-rels} : Create a new entityUuidIdDTORel.
     *
     * @param entityUuidIdDTORelDTO the entityUuidIdDTORelDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityUuidIdDTORelDTO, or with status {@code 400 (Bad Request)} if the entityUuidIdDTORel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EntityUuidIdDTORelDTO> createEntityUuidIdDTORel(@RequestBody EntityUuidIdDTORelDTO entityUuidIdDTORelDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save EntityUuidIdDTORel : {}", entityUuidIdDTORelDTO);
        if (entityUuidIdDTORelDTO.getId() != null) {
            throw new BadRequestAlertException("A new entityUuidIdDTORel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        entityUuidIdDTORelDTO = entityUuidIdDTORelService.save(entityUuidIdDTORelDTO);
        return ResponseEntity.created(new URI("/api/entity-uuid-id-dto-rels/" + entityUuidIdDTORelDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, entityUuidIdDTORelDTO.getId().toString()))
            .body(entityUuidIdDTORelDTO);
    }

    /**
     * {@code PUT  /entity-uuid-id-dto-rels/:id} : Updates an existing entityUuidIdDTORel.
     *
     * @param id the id of the entityUuidIdDTORelDTO to save.
     * @param entityUuidIdDTORelDTO the entityUuidIdDTORelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityUuidIdDTORelDTO,
     * or with status {@code 400 (Bad Request)} if the entityUuidIdDTORelDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityUuidIdDTORelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityUuidIdDTORelDTO> updateEntityUuidIdDTORel(
        @PathVariable(value = "id", required = false) final UUID id,
        @RequestBody EntityUuidIdDTORelDTO entityUuidIdDTORelDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update EntityUuidIdDTORel : {}, {}", id, entityUuidIdDTORelDTO);
        if (entityUuidIdDTORelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, entityUuidIdDTORelDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityUuidIdDTORelRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        entityUuidIdDTORelDTO = entityUuidIdDTORelService.update(entityUuidIdDTORelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityUuidIdDTORelDTO.getId().toString()))
            .body(entityUuidIdDTORelDTO);
    }

    /**
     * {@code PATCH  /entity-uuid-id-dto-rels/:id} : Partial updates given fields of an existing entityUuidIdDTORel, field will ignore if it is null
     *
     * @param id the id of the entityUuidIdDTORelDTO to save.
     * @param entityUuidIdDTORelDTO the entityUuidIdDTORelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityUuidIdDTORelDTO,
     * or with status {@code 400 (Bad Request)} if the entityUuidIdDTORelDTO is not valid,
     * or with status {@code 404 (Not Found)} if the entityUuidIdDTORelDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the entityUuidIdDTORelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EntityUuidIdDTORelDTO> partialUpdateEntityUuidIdDTORel(
        @PathVariable(value = "id", required = false) final UUID id,
        @RequestBody EntityUuidIdDTORelDTO entityUuidIdDTORelDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update EntityUuidIdDTORel partially : {}, {}", id, entityUuidIdDTORelDTO);
        if (entityUuidIdDTORelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, entityUuidIdDTORelDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityUuidIdDTORelRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EntityUuidIdDTORelDTO> result = entityUuidIdDTORelService.partialUpdate(entityUuidIdDTORelDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityUuidIdDTORelDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /entity-uuid-id-dto-rels} : get all the entityUuidIdDTORels.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityUuidIdDTORels in body.
     */
    @GetMapping("")
    public List<EntityUuidIdDTORelDTO> getAllEntityUuidIdDTORels(
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        LOG.debug("REST request to get all EntityUuidIdDTORels");
        return entityUuidIdDTORelService.findAll();
    }

    /**
     * {@code GET  /entity-uuid-id-dto-rels/:id} : get the "id" entityUuidIdDTORel.
     *
     * @param id the id of the entityUuidIdDTORelDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityUuidIdDTORelDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityUuidIdDTORelDTO> getEntityUuidIdDTORel(@PathVariable("id") UUID id) {
        LOG.debug("REST request to get EntityUuidIdDTORel : {}", id);
        Optional<EntityUuidIdDTORelDTO> entityUuidIdDTORelDTO = entityUuidIdDTORelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entityUuidIdDTORelDTO);
    }

    /**
     * {@code DELETE  /entity-uuid-id-dto-rels/:id} : delete the "id" entityUuidIdDTORel.
     *
     * @param id the id of the entityUuidIdDTORelDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntityUuidIdDTORel(@PathVariable("id") UUID id) {
        LOG.debug("REST request to delete EntityUuidIdDTORel : {}", id);
        entityUuidIdDTORelService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
