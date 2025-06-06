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
import tech.jhipster.sample.repository.EntityCustomIdDTOMapsIdRepository;
import tech.jhipster.sample.service.EntityCustomIdDTOMapsIdService;
import tech.jhipster.sample.service.dto.EntityCustomIdDTOMapsIdDTO;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.EntityCustomIdDTOMapsId}.
 */
@RestController
@RequestMapping("/api/entity-custom-id-dto-maps-ids")
public class EntityCustomIdDTOMapsIdResource {

    private static final Logger LOG = LoggerFactory.getLogger(EntityCustomIdDTOMapsIdResource.class);

    private static final String ENTITY_NAME = "entityCustomIdDTOMapsId";

    @Value("${jhipster.clientApp.name:jhipsterVue}")
    private String applicationName;

    private final EntityCustomIdDTOMapsIdService entityCustomIdDTOMapsIdService;

    private final EntityCustomIdDTOMapsIdRepository entityCustomIdDTOMapsIdRepository;

    public EntityCustomIdDTOMapsIdResource(
        EntityCustomIdDTOMapsIdService entityCustomIdDTOMapsIdService,
        EntityCustomIdDTOMapsIdRepository entityCustomIdDTOMapsIdRepository
    ) {
        this.entityCustomIdDTOMapsIdService = entityCustomIdDTOMapsIdService;
        this.entityCustomIdDTOMapsIdRepository = entityCustomIdDTOMapsIdRepository;
    }

    /**
     * {@code POST  /entity-custom-id-dto-maps-ids} : Create a new entityCustomIdDTOMapsId.
     *
     * @param entityCustomIdDTOMapsIdDTO the entityCustomIdDTOMapsIdDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityCustomIdDTOMapsIdDTO, or with status {@code 400 (Bad Request)} if the entityCustomIdDTOMapsId has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EntityCustomIdDTOMapsIdDTO> createEntityCustomIdDTOMapsId(
        @RequestBody EntityCustomIdDTOMapsIdDTO entityCustomIdDTOMapsIdDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to save EntityCustomIdDTOMapsId : {}", entityCustomIdDTOMapsIdDTO);
        if (entityCustomIdDTOMapsIdDTO.getCustomId() != null) {
            throw new BadRequestAlertException("A new entityCustomIdDTOMapsId cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (Objects.isNull(entityCustomIdDTOMapsIdDTO.getEntityCustomIdDTO())) {
            throw new BadRequestAlertException("Invalid association value provided", ENTITY_NAME, "null");
        }
        entityCustomIdDTOMapsIdDTO = entityCustomIdDTOMapsIdService.save(entityCustomIdDTOMapsIdDTO);
        return ResponseEntity.created(new URI("/api/entity-custom-id-dto-maps-ids/" + entityCustomIdDTOMapsIdDTO.getCustomId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    entityCustomIdDTOMapsIdDTO.getCustomId().toString()
                )
            )
            .body(entityCustomIdDTOMapsIdDTO);
    }

    /**
     * {@code PUT  /entity-custom-id-dto-maps-ids/:customId} : Updates an existing entityCustomIdDTOMapsId.
     *
     * @param customId the id of the entityCustomIdDTOMapsIdDTO to save.
     * @param entityCustomIdDTOMapsIdDTO the entityCustomIdDTOMapsIdDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityCustomIdDTOMapsIdDTO,
     * or with status {@code 400 (Bad Request)} if the entityCustomIdDTOMapsIdDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityCustomIdDTOMapsIdDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{customId}")
    public ResponseEntity<EntityCustomIdDTOMapsIdDTO> updateEntityCustomIdDTOMapsId(
        @PathVariable(value = "customId", required = false) final Long customId,
        @RequestBody EntityCustomIdDTOMapsIdDTO entityCustomIdDTOMapsIdDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update EntityCustomIdDTOMapsId : {}, {}", customId, entityCustomIdDTOMapsIdDTO);
        if (entityCustomIdDTOMapsIdDTO.getCustomId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(customId, entityCustomIdDTOMapsIdDTO.getCustomId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityCustomIdDTOMapsIdRepository.existsById(customId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        entityCustomIdDTOMapsIdDTO = entityCustomIdDTOMapsIdService.update(entityCustomIdDTOMapsIdDTO);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityCustomIdDTOMapsIdDTO.getCustomId().toString())
            )
            .body(entityCustomIdDTOMapsIdDTO);
    }

    /**
     * {@code PATCH  /entity-custom-id-dto-maps-ids/:customId} : Partial updates given fields of an existing entityCustomIdDTOMapsId, field will ignore if it is null
     *
     * @param customId the id of the entityCustomIdDTOMapsIdDTO to save.
     * @param entityCustomIdDTOMapsIdDTO the entityCustomIdDTOMapsIdDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityCustomIdDTOMapsIdDTO,
     * or with status {@code 400 (Bad Request)} if the entityCustomIdDTOMapsIdDTO is not valid,
     * or with status {@code 404 (Not Found)} if the entityCustomIdDTOMapsIdDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the entityCustomIdDTOMapsIdDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{customId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EntityCustomIdDTOMapsIdDTO> partialUpdateEntityCustomIdDTOMapsId(
        @PathVariable(value = "customId", required = false) final Long customId,
        @RequestBody EntityCustomIdDTOMapsIdDTO entityCustomIdDTOMapsIdDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update EntityCustomIdDTOMapsId partially : {}, {}", customId, entityCustomIdDTOMapsIdDTO);
        if (entityCustomIdDTOMapsIdDTO.getCustomId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(customId, entityCustomIdDTOMapsIdDTO.getCustomId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityCustomIdDTOMapsIdRepository.existsById(customId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EntityCustomIdDTOMapsIdDTO> result = entityCustomIdDTOMapsIdService.partialUpdate(entityCustomIdDTOMapsIdDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityCustomIdDTOMapsIdDTO.getCustomId().toString())
        );
    }

    /**
     * {@code GET  /entity-custom-id-dto-maps-ids} : get all the entityCustomIdDTOMapsIds.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityCustomIdDTOMapsIds in body.
     */
    @GetMapping("")
    public List<EntityCustomIdDTOMapsIdDTO> getAllEntityCustomIdDTOMapsIds(@RequestParam(name = "filter", required = false) String filter) {
        if ("onetoonemapsidback-is-null".equals(filter)) {
            LOG.debug("REST request to get all EntityCustomIdDTOMapsIds where oneToOneMapsIdBack is null");
            return entityCustomIdDTOMapsIdService.findAllWhereOneToOneMapsIdBackIsNull();
        }
        LOG.debug("REST request to get all EntityCustomIdDTOMapsIds");
        return entityCustomIdDTOMapsIdService.findAll();
    }

    /**
     * {@code GET  /entity-custom-id-dto-maps-ids/:id} : get the "id" entityCustomIdDTOMapsId.
     *
     * @param id the id of the entityCustomIdDTOMapsIdDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityCustomIdDTOMapsIdDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityCustomIdDTOMapsIdDTO> getEntityCustomIdDTOMapsId(@PathVariable("id") Long id) {
        LOG.debug("REST request to get EntityCustomIdDTOMapsId : {}", id);
        Optional<EntityCustomIdDTOMapsIdDTO> entityCustomIdDTOMapsIdDTO = entityCustomIdDTOMapsIdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entityCustomIdDTOMapsIdDTO);
    }

    /**
     * {@code DELETE  /entity-custom-id-dto-maps-ids/:id} : delete the "id" entityCustomIdDTOMapsId.
     *
     * @param id the id of the entityCustomIdDTOMapsIdDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntityCustomIdDTOMapsId(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete EntityCustomIdDTOMapsId : {}", id);
        entityCustomIdDTOMapsIdService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
