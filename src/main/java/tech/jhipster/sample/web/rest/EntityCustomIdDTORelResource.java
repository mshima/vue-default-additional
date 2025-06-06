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
import org.springframework.web.bind.annotation.*;
import tech.jhipster.sample.repository.EntityCustomIdDTORelRepository;
import tech.jhipster.sample.service.EntityCustomIdDTORelService;
import tech.jhipster.sample.service.dto.EntityCustomIdDTORelDTO;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.EntityCustomIdDTORel}.
 */
@RestController
@RequestMapping("/api/entity-custom-id-dto-rels")
public class EntityCustomIdDTORelResource {

    private static final Logger LOG = LoggerFactory.getLogger(EntityCustomIdDTORelResource.class);

    private static final String ENTITY_NAME = "entityCustomIdDTORel";

    @Value("${jhipster.clientApp.name:jhipsterVue}")
    private String applicationName;

    private final EntityCustomIdDTORelService entityCustomIdDTORelService;

    private final EntityCustomIdDTORelRepository entityCustomIdDTORelRepository;

    public EntityCustomIdDTORelResource(
        EntityCustomIdDTORelService entityCustomIdDTORelService,
        EntityCustomIdDTORelRepository entityCustomIdDTORelRepository
    ) {
        this.entityCustomIdDTORelService = entityCustomIdDTORelService;
        this.entityCustomIdDTORelRepository = entityCustomIdDTORelRepository;
    }

    /**
     * {@code POST  /entity-custom-id-dto-rels} : Create a new entityCustomIdDTORel.
     *
     * @param entityCustomIdDTORelDTO the entityCustomIdDTORelDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityCustomIdDTORelDTO, or with status {@code 400 (Bad Request)} if the entityCustomIdDTORel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EntityCustomIdDTORelDTO> createEntityCustomIdDTORel(@RequestBody EntityCustomIdDTORelDTO entityCustomIdDTORelDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save EntityCustomIdDTORel : {}", entityCustomIdDTORelDTO);
        if (entityCustomIdDTORelDTO.getRelatedId() != null) {
            throw new BadRequestAlertException("A new entityCustomIdDTORel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        entityCustomIdDTORelDTO = entityCustomIdDTORelService.save(entityCustomIdDTORelDTO);
        return ResponseEntity.created(new URI("/api/entity-custom-id-dto-rels/" + entityCustomIdDTORelDTO.getRelatedId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, entityCustomIdDTORelDTO.getRelatedId().toString())
            )
            .body(entityCustomIdDTORelDTO);
    }

    /**
     * {@code PUT  /entity-custom-id-dto-rels/:relatedId} : Updates an existing entityCustomIdDTORel.
     *
     * @param relatedId the id of the entityCustomIdDTORelDTO to save.
     * @param entityCustomIdDTORelDTO the entityCustomIdDTORelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityCustomIdDTORelDTO,
     * or with status {@code 400 (Bad Request)} if the entityCustomIdDTORelDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityCustomIdDTORelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{relatedId}")
    public ResponseEntity<EntityCustomIdDTORelDTO> updateEntityCustomIdDTORel(
        @PathVariable(value = "relatedId", required = false) final Long relatedId,
        @RequestBody EntityCustomIdDTORelDTO entityCustomIdDTORelDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update EntityCustomIdDTORel : {}, {}", relatedId, entityCustomIdDTORelDTO);
        if (entityCustomIdDTORelDTO.getRelatedId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(relatedId, entityCustomIdDTORelDTO.getRelatedId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityCustomIdDTORelRepository.existsById(relatedId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        entityCustomIdDTORelDTO = entityCustomIdDTORelService.update(entityCustomIdDTORelDTO);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityCustomIdDTORelDTO.getRelatedId().toString())
            )
            .body(entityCustomIdDTORelDTO);
    }

    /**
     * {@code PATCH  /entity-custom-id-dto-rels/:relatedId} : Partial updates given fields of an existing entityCustomIdDTORel, field will ignore if it is null
     *
     * @param relatedId the id of the entityCustomIdDTORelDTO to save.
     * @param entityCustomIdDTORelDTO the entityCustomIdDTORelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityCustomIdDTORelDTO,
     * or with status {@code 400 (Bad Request)} if the entityCustomIdDTORelDTO is not valid,
     * or with status {@code 404 (Not Found)} if the entityCustomIdDTORelDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the entityCustomIdDTORelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{relatedId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EntityCustomIdDTORelDTO> partialUpdateEntityCustomIdDTORel(
        @PathVariable(value = "relatedId", required = false) final Long relatedId,
        @RequestBody EntityCustomIdDTORelDTO entityCustomIdDTORelDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update EntityCustomIdDTORel partially : {}, {}", relatedId, entityCustomIdDTORelDTO);
        if (entityCustomIdDTORelDTO.getRelatedId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(relatedId, entityCustomIdDTORelDTO.getRelatedId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityCustomIdDTORelRepository.existsById(relatedId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EntityCustomIdDTORelDTO> result = entityCustomIdDTORelService.partialUpdate(entityCustomIdDTORelDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityCustomIdDTORelDTO.getRelatedId().toString())
        );
    }

    /**
     * {@code GET  /entity-custom-id-dto-rels} : get all the entityCustomIdDTORels.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityCustomIdDTORels in body.
     */
    @GetMapping("")
    public List<EntityCustomIdDTORelDTO> getAllEntityCustomIdDTORels(
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        LOG.debug("REST request to get all EntityCustomIdDTORels");
        return entityCustomIdDTORelService.findAll();
    }

    /**
     * {@code GET  /entity-custom-id-dto-rels/:id} : get the "id" entityCustomIdDTORel.
     *
     * @param id the id of the entityCustomIdDTORelDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityCustomIdDTORelDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityCustomIdDTORelDTO> getEntityCustomIdDTORel(@PathVariable("id") Long id) {
        LOG.debug("REST request to get EntityCustomIdDTORel : {}", id);
        Optional<EntityCustomIdDTORelDTO> entityCustomIdDTORelDTO = entityCustomIdDTORelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entityCustomIdDTORelDTO);
    }

    /**
     * {@code DELETE  /entity-custom-id-dto-rels/:id} : delete the "id" entityCustomIdDTORel.
     *
     * @param id the id of the entityCustomIdDTORelDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntityCustomIdDTORel(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete EntityCustomIdDTORel : {}", id);
        entityCustomIdDTORelService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
