package tech.jhipster.sample.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
import tech.jhipster.sample.repository.EntityCustomIdRequiredDTORelRepository;
import tech.jhipster.sample.service.EntityCustomIdRequiredDTORelService;
import tech.jhipster.sample.service.dto.EntityCustomIdRequiredDTORelDTO;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.EntityCustomIdRequiredDTORel}.
 */
@RestController
@RequestMapping("/api/entity-custom-id-required-dto-rels")
public class EntityCustomIdRequiredDTORelResource {

    private static final Logger LOG = LoggerFactory.getLogger(EntityCustomIdRequiredDTORelResource.class);

    private static final String ENTITY_NAME = "entityCustomIdRequiredDTORel";

    @Value("${jhipster.clientApp.name:jhipsterVue}")
    private String applicationName;

    private final EntityCustomIdRequiredDTORelService entityCustomIdRequiredDTORelService;

    private final EntityCustomIdRequiredDTORelRepository entityCustomIdRequiredDTORelRepository;

    public EntityCustomIdRequiredDTORelResource(
        EntityCustomIdRequiredDTORelService entityCustomIdRequiredDTORelService,
        EntityCustomIdRequiredDTORelRepository entityCustomIdRequiredDTORelRepository
    ) {
        this.entityCustomIdRequiredDTORelService = entityCustomIdRequiredDTORelService;
        this.entityCustomIdRequiredDTORelRepository = entityCustomIdRequiredDTORelRepository;
    }

    /**
     * {@code POST  /entity-custom-id-required-dto-rels} : Create a new entityCustomIdRequiredDTORel.
     *
     * @param entityCustomIdRequiredDTORelDTO the entityCustomIdRequiredDTORelDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityCustomIdRequiredDTORelDTO, or with status {@code 400 (Bad Request)} if the entityCustomIdRequiredDTORel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EntityCustomIdRequiredDTORelDTO> createEntityCustomIdRequiredDTORel(
        @Valid @RequestBody EntityCustomIdRequiredDTORelDTO entityCustomIdRequiredDTORelDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to save EntityCustomIdRequiredDTORel : {}", entityCustomIdRequiredDTORelDTO);
        if (entityCustomIdRequiredDTORelDTO.getRelatedId() != null) {
            throw new BadRequestAlertException("A new entityCustomIdRequiredDTORel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        entityCustomIdRequiredDTORelDTO = entityCustomIdRequiredDTORelService.save(entityCustomIdRequiredDTORelDTO);
        return ResponseEntity.created(new URI("/api/entity-custom-id-required-dto-rels/" + entityCustomIdRequiredDTORelDTO.getRelatedId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    entityCustomIdRequiredDTORelDTO.getRelatedId().toString()
                )
            )
            .body(entityCustomIdRequiredDTORelDTO);
    }

    /**
     * {@code PUT  /entity-custom-id-required-dto-rels/:relatedId} : Updates an existing entityCustomIdRequiredDTORel.
     *
     * @param relatedId the id of the entityCustomIdRequiredDTORelDTO to save.
     * @param entityCustomIdRequiredDTORelDTO the entityCustomIdRequiredDTORelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityCustomIdRequiredDTORelDTO,
     * or with status {@code 400 (Bad Request)} if the entityCustomIdRequiredDTORelDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityCustomIdRequiredDTORelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{relatedId}")
    public ResponseEntity<EntityCustomIdRequiredDTORelDTO> updateEntityCustomIdRequiredDTORel(
        @PathVariable(value = "relatedId", required = false) final Long relatedId,
        @Valid @RequestBody EntityCustomIdRequiredDTORelDTO entityCustomIdRequiredDTORelDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update EntityCustomIdRequiredDTORel : {}, {}", relatedId, entityCustomIdRequiredDTORelDTO);
        if (entityCustomIdRequiredDTORelDTO.getRelatedId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(relatedId, entityCustomIdRequiredDTORelDTO.getRelatedId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityCustomIdRequiredDTORelRepository.existsById(relatedId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        entityCustomIdRequiredDTORelDTO = entityCustomIdRequiredDTORelService.update(entityCustomIdRequiredDTORelDTO);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    entityCustomIdRequiredDTORelDTO.getRelatedId().toString()
                )
            )
            .body(entityCustomIdRequiredDTORelDTO);
    }

    /**
     * {@code PATCH  /entity-custom-id-required-dto-rels/:relatedId} : Partial updates given fields of an existing entityCustomIdRequiredDTORel, field will ignore if it is null
     *
     * @param relatedId the id of the entityCustomIdRequiredDTORelDTO to save.
     * @param entityCustomIdRequiredDTORelDTO the entityCustomIdRequiredDTORelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityCustomIdRequiredDTORelDTO,
     * or with status {@code 400 (Bad Request)} if the entityCustomIdRequiredDTORelDTO is not valid,
     * or with status {@code 404 (Not Found)} if the entityCustomIdRequiredDTORelDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the entityCustomIdRequiredDTORelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{relatedId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EntityCustomIdRequiredDTORelDTO> partialUpdateEntityCustomIdRequiredDTORel(
        @PathVariable(value = "relatedId", required = false) final Long relatedId,
        @NotNull @RequestBody EntityCustomIdRequiredDTORelDTO entityCustomIdRequiredDTORelDTO
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update EntityCustomIdRequiredDTORel partially : {}, {}",
            relatedId,
            entityCustomIdRequiredDTORelDTO
        );
        if (entityCustomIdRequiredDTORelDTO.getRelatedId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(relatedId, entityCustomIdRequiredDTORelDTO.getRelatedId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityCustomIdRequiredDTORelRepository.existsById(relatedId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EntityCustomIdRequiredDTORelDTO> result = entityCustomIdRequiredDTORelService.partialUpdate(
            entityCustomIdRequiredDTORelDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                entityCustomIdRequiredDTORelDTO.getRelatedId().toString()
            )
        );
    }

    /**
     * {@code GET  /entity-custom-id-required-dto-rels} : get all the entityCustomIdRequiredDTORels.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityCustomIdRequiredDTORels in body.
     */
    @GetMapping("")
    public List<EntityCustomIdRequiredDTORelDTO> getAllEntityCustomIdRequiredDTORels(
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        LOG.debug("REST request to get all EntityCustomIdRequiredDTORels");
        return entityCustomIdRequiredDTORelService.findAll();
    }

    /**
     * {@code GET  /entity-custom-id-required-dto-rels/:id} : get the "id" entityCustomIdRequiredDTORel.
     *
     * @param id the id of the entityCustomIdRequiredDTORelDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityCustomIdRequiredDTORelDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityCustomIdRequiredDTORelDTO> getEntityCustomIdRequiredDTORel(@PathVariable("id") Long id) {
        LOG.debug("REST request to get EntityCustomIdRequiredDTORel : {}", id);
        Optional<EntityCustomIdRequiredDTORelDTO> entityCustomIdRequiredDTORelDTO = entityCustomIdRequiredDTORelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entityCustomIdRequiredDTORelDTO);
    }

    /**
     * {@code DELETE  /entity-custom-id-required-dto-rels/:id} : delete the "id" entityCustomIdRequiredDTORel.
     *
     * @param id the id of the entityCustomIdRequiredDTORelDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntityCustomIdRequiredDTORel(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete EntityCustomIdRequiredDTORel : {}", id);
        entityCustomIdRequiredDTORelService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
