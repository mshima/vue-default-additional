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
import tech.jhipster.sample.repository.EntityCustomIdRequiredDTORepository;
import tech.jhipster.sample.service.EntityCustomIdRequiredDTOService;
import tech.jhipster.sample.service.dto.EntityCustomIdRequiredDTODTO;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.EntityCustomIdRequiredDTO}.
 */
@RestController
@RequestMapping("/api/entity-custom-id-required-dtos")
public class EntityCustomIdRequiredDTOResource {

    private static final Logger LOG = LoggerFactory.getLogger(EntityCustomIdRequiredDTOResource.class);

    private static final String ENTITY_NAME = "entityCustomIdRequiredDTO";

    @Value("${jhipster.clientApp.name:jhipsterVue}")
    private String applicationName;

    private final EntityCustomIdRequiredDTOService entityCustomIdRequiredDTOService;

    private final EntityCustomIdRequiredDTORepository entityCustomIdRequiredDTORepository;

    public EntityCustomIdRequiredDTOResource(
        EntityCustomIdRequiredDTOService entityCustomIdRequiredDTOService,
        EntityCustomIdRequiredDTORepository entityCustomIdRequiredDTORepository
    ) {
        this.entityCustomIdRequiredDTOService = entityCustomIdRequiredDTOService;
        this.entityCustomIdRequiredDTORepository = entityCustomIdRequiredDTORepository;
    }

    /**
     * {@code POST  /entity-custom-id-required-dtos} : Create a new entityCustomIdRequiredDTO.
     *
     * @param entityCustomIdRequiredDTODTO the entityCustomIdRequiredDTODTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityCustomIdRequiredDTODTO, or with status {@code 400 (Bad Request)} if the entityCustomIdRequiredDTO has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EntityCustomIdRequiredDTODTO> createEntityCustomIdRequiredDTO(
        @RequestBody EntityCustomIdRequiredDTODTO entityCustomIdRequiredDTODTO
    ) throws URISyntaxException {
        LOG.debug("REST request to save EntityCustomIdRequiredDTO : {}", entityCustomIdRequiredDTODTO);
        if (entityCustomIdRequiredDTODTO.getCustomId() != null) {
            throw new BadRequestAlertException("A new entityCustomIdRequiredDTO cannot already have an ID", ENTITY_NAME, "idexists");
        }
        entityCustomIdRequiredDTODTO = entityCustomIdRequiredDTOService.save(entityCustomIdRequiredDTODTO);
        return ResponseEntity.created(new URI("/api/entity-custom-id-required-dtos/" + entityCustomIdRequiredDTODTO.getCustomId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    entityCustomIdRequiredDTODTO.getCustomId().toString()
                )
            )
            .body(entityCustomIdRequiredDTODTO);
    }

    /**
     * {@code PUT  /entity-custom-id-required-dtos/:customId} : Updates an existing entityCustomIdRequiredDTO.
     *
     * @param customId the id of the entityCustomIdRequiredDTODTO to save.
     * @param entityCustomIdRequiredDTODTO the entityCustomIdRequiredDTODTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityCustomIdRequiredDTODTO,
     * or with status {@code 400 (Bad Request)} if the entityCustomIdRequiredDTODTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityCustomIdRequiredDTODTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{customId}")
    public ResponseEntity<EntityCustomIdRequiredDTODTO> updateEntityCustomIdRequiredDTO(
        @PathVariable(value = "customId", required = false) final Long customId,
        @RequestBody EntityCustomIdRequiredDTODTO entityCustomIdRequiredDTODTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update EntityCustomIdRequiredDTO : {}, {}", customId, entityCustomIdRequiredDTODTO);
        if (entityCustomIdRequiredDTODTO.getCustomId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(customId, entityCustomIdRequiredDTODTO.getCustomId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityCustomIdRequiredDTORepository.existsById(customId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        entityCustomIdRequiredDTODTO = entityCustomIdRequiredDTOService.update(entityCustomIdRequiredDTODTO);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    entityCustomIdRequiredDTODTO.getCustomId().toString()
                )
            )
            .body(entityCustomIdRequiredDTODTO);
    }

    /**
     * {@code PATCH  /entity-custom-id-required-dtos/:customId} : Partial updates given fields of an existing entityCustomIdRequiredDTO, field will ignore if it is null
     *
     * @param customId the id of the entityCustomIdRequiredDTODTO to save.
     * @param entityCustomIdRequiredDTODTO the entityCustomIdRequiredDTODTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityCustomIdRequiredDTODTO,
     * or with status {@code 400 (Bad Request)} if the entityCustomIdRequiredDTODTO is not valid,
     * or with status {@code 404 (Not Found)} if the entityCustomIdRequiredDTODTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the entityCustomIdRequiredDTODTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{customId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EntityCustomIdRequiredDTODTO> partialUpdateEntityCustomIdRequiredDTO(
        @PathVariable(value = "customId", required = false) final Long customId,
        @RequestBody EntityCustomIdRequiredDTODTO entityCustomIdRequiredDTODTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update EntityCustomIdRequiredDTO partially : {}, {}", customId, entityCustomIdRequiredDTODTO);
        if (entityCustomIdRequiredDTODTO.getCustomId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(customId, entityCustomIdRequiredDTODTO.getCustomId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityCustomIdRequiredDTORepository.existsById(customId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EntityCustomIdRequiredDTODTO> result = entityCustomIdRequiredDTOService.partialUpdate(entityCustomIdRequiredDTODTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityCustomIdRequiredDTODTO.getCustomId().toString())
        );
    }

    /**
     * {@code GET  /entity-custom-id-required-dtos} : get all the entityCustomIdRequiredDTOS.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityCustomIdRequiredDTOS in body.
     */
    @GetMapping("")
    public List<EntityCustomIdRequiredDTODTO> getAllEntityCustomIdRequiredDTOS(
        @RequestParam(name = "filter", required = false) String filter
    ) {
        if ("entitycustomidrequireddtomapsid-is-null".equals(filter)) {
            LOG.debug("REST request to get all EntityCustomIdRequiredDTOs where entityCustomIdRequiredDTOMapsId is null");
            return entityCustomIdRequiredDTOService.findAllWhereEntityCustomIdRequiredDTOMapsIdIsNull();
        }

        if ("onetooneback-is-null".equals(filter)) {
            LOG.debug("REST request to get all EntityCustomIdRequiredDTOs where oneToOneBack is null");
            return entityCustomIdRequiredDTOService.findAllWhereOneToOneBackIsNull();
        }
        LOG.debug("REST request to get all EntityCustomIdRequiredDTOS");
        return entityCustomIdRequiredDTOService.findAll();
    }

    /**
     * {@code GET  /entity-custom-id-required-dtos/:id} : get the "id" entityCustomIdRequiredDTO.
     *
     * @param id the id of the entityCustomIdRequiredDTODTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityCustomIdRequiredDTODTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityCustomIdRequiredDTODTO> getEntityCustomIdRequiredDTO(@PathVariable("id") Long id) {
        LOG.debug("REST request to get EntityCustomIdRequiredDTO : {}", id);
        Optional<EntityCustomIdRequiredDTODTO> entityCustomIdRequiredDTODTO = entityCustomIdRequiredDTOService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entityCustomIdRequiredDTODTO);
    }

    /**
     * {@code DELETE  /entity-custom-id-required-dtos/:id} : delete the "id" entityCustomIdRequiredDTO.
     *
     * @param id the id of the entityCustomIdRequiredDTODTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntityCustomIdRequiredDTO(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete EntityCustomIdRequiredDTO : {}", id);
        entityCustomIdRequiredDTOService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
