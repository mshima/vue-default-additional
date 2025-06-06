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
import tech.jhipster.sample.repository.EntityCustomIdRequiredDTOMapsIdRepository;
import tech.jhipster.sample.service.EntityCustomIdRequiredDTOMapsIdService;
import tech.jhipster.sample.service.dto.EntityCustomIdRequiredDTOMapsIdDTO;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.EntityCustomIdRequiredDTOMapsId}.
 */
@RestController
@RequestMapping("/api/entity-custom-id-required-dto-maps-ids")
public class EntityCustomIdRequiredDTOMapsIdResource {

    private static final Logger LOG = LoggerFactory.getLogger(EntityCustomIdRequiredDTOMapsIdResource.class);

    private static final String ENTITY_NAME = "entityCustomIdRequiredDTOMapsId";

    @Value("${jhipster.clientApp.name:jhipsterVue}")
    private String applicationName;

    private final EntityCustomIdRequiredDTOMapsIdService entityCustomIdRequiredDTOMapsIdService;

    private final EntityCustomIdRequiredDTOMapsIdRepository entityCustomIdRequiredDTOMapsIdRepository;

    public EntityCustomIdRequiredDTOMapsIdResource(
        EntityCustomIdRequiredDTOMapsIdService entityCustomIdRequiredDTOMapsIdService,
        EntityCustomIdRequiredDTOMapsIdRepository entityCustomIdRequiredDTOMapsIdRepository
    ) {
        this.entityCustomIdRequiredDTOMapsIdService = entityCustomIdRequiredDTOMapsIdService;
        this.entityCustomIdRequiredDTOMapsIdRepository = entityCustomIdRequiredDTOMapsIdRepository;
    }

    /**
     * {@code POST  /entity-custom-id-required-dto-maps-ids} : Create a new entityCustomIdRequiredDTOMapsId.
     *
     * @param entityCustomIdRequiredDTOMapsIdDTO the entityCustomIdRequiredDTOMapsIdDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityCustomIdRequiredDTOMapsIdDTO, or with status {@code 400 (Bad Request)} if the entityCustomIdRequiredDTOMapsId has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EntityCustomIdRequiredDTOMapsIdDTO> createEntityCustomIdRequiredDTOMapsId(
        @RequestBody EntityCustomIdRequiredDTOMapsIdDTO entityCustomIdRequiredDTOMapsIdDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to save EntityCustomIdRequiredDTOMapsId : {}", entityCustomIdRequiredDTOMapsIdDTO);
        if (entityCustomIdRequiredDTOMapsIdDTO.getCustomId() != null) {
            throw new BadRequestAlertException("A new entityCustomIdRequiredDTOMapsId cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (Objects.isNull(entityCustomIdRequiredDTOMapsIdDTO.getEntityCustomIdRequiredDTO())) {
            throw new BadRequestAlertException("Invalid association value provided", ENTITY_NAME, "null");
        }
        entityCustomIdRequiredDTOMapsIdDTO = entityCustomIdRequiredDTOMapsIdService.save(entityCustomIdRequiredDTOMapsIdDTO);
        return ResponseEntity.created(
            new URI("/api/entity-custom-id-required-dto-maps-ids/" + entityCustomIdRequiredDTOMapsIdDTO.getCustomId())
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    entityCustomIdRequiredDTOMapsIdDTO.getCustomId().toString()
                )
            )
            .body(entityCustomIdRequiredDTOMapsIdDTO);
    }

    /**
     * {@code PUT  /entity-custom-id-required-dto-maps-ids/:customId} : Updates an existing entityCustomIdRequiredDTOMapsId.
     *
     * @param customId the id of the entityCustomIdRequiredDTOMapsIdDTO to save.
     * @param entityCustomIdRequiredDTOMapsIdDTO the entityCustomIdRequiredDTOMapsIdDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityCustomIdRequiredDTOMapsIdDTO,
     * or with status {@code 400 (Bad Request)} if the entityCustomIdRequiredDTOMapsIdDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityCustomIdRequiredDTOMapsIdDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{customId}")
    public ResponseEntity<EntityCustomIdRequiredDTOMapsIdDTO> updateEntityCustomIdRequiredDTOMapsId(
        @PathVariable(value = "customId", required = false) final Long customId,
        @RequestBody EntityCustomIdRequiredDTOMapsIdDTO entityCustomIdRequiredDTOMapsIdDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update EntityCustomIdRequiredDTOMapsId : {}, {}", customId, entityCustomIdRequiredDTOMapsIdDTO);
        if (entityCustomIdRequiredDTOMapsIdDTO.getCustomId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(customId, entityCustomIdRequiredDTOMapsIdDTO.getCustomId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityCustomIdRequiredDTOMapsIdRepository.existsById(customId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        entityCustomIdRequiredDTOMapsIdDTO = entityCustomIdRequiredDTOMapsIdService.update(entityCustomIdRequiredDTOMapsIdDTO);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    entityCustomIdRequiredDTOMapsIdDTO.getCustomId().toString()
                )
            )
            .body(entityCustomIdRequiredDTOMapsIdDTO);
    }

    /**
     * {@code PATCH  /entity-custom-id-required-dto-maps-ids/:customId} : Partial updates given fields of an existing entityCustomIdRequiredDTOMapsId, field will ignore if it is null
     *
     * @param customId the id of the entityCustomIdRequiredDTOMapsIdDTO to save.
     * @param entityCustomIdRequiredDTOMapsIdDTO the entityCustomIdRequiredDTOMapsIdDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityCustomIdRequiredDTOMapsIdDTO,
     * or with status {@code 400 (Bad Request)} if the entityCustomIdRequiredDTOMapsIdDTO is not valid,
     * or with status {@code 404 (Not Found)} if the entityCustomIdRequiredDTOMapsIdDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the entityCustomIdRequiredDTOMapsIdDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{customId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EntityCustomIdRequiredDTOMapsIdDTO> partialUpdateEntityCustomIdRequiredDTOMapsId(
        @PathVariable(value = "customId", required = false) final Long customId,
        @RequestBody EntityCustomIdRequiredDTOMapsIdDTO entityCustomIdRequiredDTOMapsIdDTO
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update EntityCustomIdRequiredDTOMapsId partially : {}, {}",
            customId,
            entityCustomIdRequiredDTOMapsIdDTO
        );
        if (entityCustomIdRequiredDTOMapsIdDTO.getCustomId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(customId, entityCustomIdRequiredDTOMapsIdDTO.getCustomId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityCustomIdRequiredDTOMapsIdRepository.existsById(customId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EntityCustomIdRequiredDTOMapsIdDTO> result = entityCustomIdRequiredDTOMapsIdService.partialUpdate(
            entityCustomIdRequiredDTOMapsIdDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                entityCustomIdRequiredDTOMapsIdDTO.getCustomId().toString()
            )
        );
    }

    /**
     * {@code GET  /entity-custom-id-required-dto-maps-ids} : get all the entityCustomIdRequiredDTOMapsIds.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityCustomIdRequiredDTOMapsIds in body.
     */
    @GetMapping("")
    public List<EntityCustomIdRequiredDTOMapsIdDTO> getAllEntityCustomIdRequiredDTOMapsIds(
        @RequestParam(name = "filter", required = false) String filter
    ) {
        if ("onetoonemapsidback-is-null".equals(filter)) {
            LOG.debug("REST request to get all EntityCustomIdRequiredDTOMapsIds where oneToOneMapsIdBack is null");
            return entityCustomIdRequiredDTOMapsIdService.findAllWhereOneToOneMapsIdBackIsNull();
        }
        LOG.debug("REST request to get all EntityCustomIdRequiredDTOMapsIds");
        return entityCustomIdRequiredDTOMapsIdService.findAll();
    }

    /**
     * {@code GET  /entity-custom-id-required-dto-maps-ids/:id} : get the "id" entityCustomIdRequiredDTOMapsId.
     *
     * @param id the id of the entityCustomIdRequiredDTOMapsIdDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityCustomIdRequiredDTOMapsIdDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityCustomIdRequiredDTOMapsIdDTO> getEntityCustomIdRequiredDTOMapsId(@PathVariable("id") Long id) {
        LOG.debug("REST request to get EntityCustomIdRequiredDTOMapsId : {}", id);
        Optional<EntityCustomIdRequiredDTOMapsIdDTO> entityCustomIdRequiredDTOMapsIdDTO = entityCustomIdRequiredDTOMapsIdService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(entityCustomIdRequiredDTOMapsIdDTO);
    }

    /**
     * {@code DELETE  /entity-custom-id-required-dto-maps-ids/:id} : delete the "id" entityCustomIdRequiredDTOMapsId.
     *
     * @param id the id of the entityCustomIdRequiredDTOMapsIdDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntityCustomIdRequiredDTOMapsId(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete EntityCustomIdRequiredDTOMapsId : {}", id);
        entityCustomIdRequiredDTOMapsIdService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
