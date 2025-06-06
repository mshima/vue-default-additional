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
import tech.jhipster.sample.repository.EntityCustomIdDTORepository;
import tech.jhipster.sample.service.EntityCustomIdDTOService;
import tech.jhipster.sample.service.dto.EntityCustomIdDTODTO;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.EntityCustomIdDTO}.
 */
@RestController
@RequestMapping("/api/entity-custom-id-dtos")
public class EntityCustomIdDTOResource {

    private static final Logger LOG = LoggerFactory.getLogger(EntityCustomIdDTOResource.class);

    private static final String ENTITY_NAME = "entityCustomIdDTO";

    @Value("${jhipster.clientApp.name:jhipsterVue}")
    private String applicationName;

    private final EntityCustomIdDTOService entityCustomIdDTOService;

    private final EntityCustomIdDTORepository entityCustomIdDTORepository;

    public EntityCustomIdDTOResource(
        EntityCustomIdDTOService entityCustomIdDTOService,
        EntityCustomIdDTORepository entityCustomIdDTORepository
    ) {
        this.entityCustomIdDTOService = entityCustomIdDTOService;
        this.entityCustomIdDTORepository = entityCustomIdDTORepository;
    }

    /**
     * {@code POST  /entity-custom-id-dtos} : Create a new entityCustomIdDTO.
     *
     * @param entityCustomIdDTODTO the entityCustomIdDTODTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityCustomIdDTODTO, or with status {@code 400 (Bad Request)} if the entityCustomIdDTO has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EntityCustomIdDTODTO> createEntityCustomIdDTO(@RequestBody EntityCustomIdDTODTO entityCustomIdDTODTO)
        throws URISyntaxException {
        LOG.debug("REST request to save EntityCustomIdDTO : {}", entityCustomIdDTODTO);
        if (entityCustomIdDTODTO.getCustomId() != null) {
            throw new BadRequestAlertException("A new entityCustomIdDTO cannot already have an ID", ENTITY_NAME, "idexists");
        }
        entityCustomIdDTODTO = entityCustomIdDTOService.save(entityCustomIdDTODTO);
        return ResponseEntity.created(new URI("/api/entity-custom-id-dtos/" + entityCustomIdDTODTO.getCustomId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, entityCustomIdDTODTO.getCustomId().toString())
            )
            .body(entityCustomIdDTODTO);
    }

    /**
     * {@code PUT  /entity-custom-id-dtos/:customId} : Updates an existing entityCustomIdDTO.
     *
     * @param customId the id of the entityCustomIdDTODTO to save.
     * @param entityCustomIdDTODTO the entityCustomIdDTODTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityCustomIdDTODTO,
     * or with status {@code 400 (Bad Request)} if the entityCustomIdDTODTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityCustomIdDTODTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{customId}")
    public ResponseEntity<EntityCustomIdDTODTO> updateEntityCustomIdDTO(
        @PathVariable(value = "customId", required = false) final Long customId,
        @RequestBody EntityCustomIdDTODTO entityCustomIdDTODTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update EntityCustomIdDTO : {}, {}", customId, entityCustomIdDTODTO);
        if (entityCustomIdDTODTO.getCustomId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(customId, entityCustomIdDTODTO.getCustomId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityCustomIdDTORepository.existsById(customId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        entityCustomIdDTODTO = entityCustomIdDTOService.update(entityCustomIdDTODTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityCustomIdDTODTO.getCustomId().toString()))
            .body(entityCustomIdDTODTO);
    }

    /**
     * {@code PATCH  /entity-custom-id-dtos/:customId} : Partial updates given fields of an existing entityCustomIdDTO, field will ignore if it is null
     *
     * @param customId the id of the entityCustomIdDTODTO to save.
     * @param entityCustomIdDTODTO the entityCustomIdDTODTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityCustomIdDTODTO,
     * or with status {@code 400 (Bad Request)} if the entityCustomIdDTODTO is not valid,
     * or with status {@code 404 (Not Found)} if the entityCustomIdDTODTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the entityCustomIdDTODTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{customId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EntityCustomIdDTODTO> partialUpdateEntityCustomIdDTO(
        @PathVariable(value = "customId", required = false) final Long customId,
        @RequestBody EntityCustomIdDTODTO entityCustomIdDTODTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update EntityCustomIdDTO partially : {}, {}", customId, entityCustomIdDTODTO);
        if (entityCustomIdDTODTO.getCustomId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(customId, entityCustomIdDTODTO.getCustomId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityCustomIdDTORepository.existsById(customId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EntityCustomIdDTODTO> result = entityCustomIdDTOService.partialUpdate(entityCustomIdDTODTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityCustomIdDTODTO.getCustomId().toString())
        );
    }

    /**
     * {@code GET  /entity-custom-id-dtos} : get all the entityCustomIdDTOS.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityCustomIdDTOS in body.
     */
    @GetMapping("")
    public List<EntityCustomIdDTODTO> getAllEntityCustomIdDTOS(@RequestParam(name = "filter", required = false) String filter) {
        if ("entitycustomiddtomapsid-is-null".equals(filter)) {
            LOG.debug("REST request to get all EntityCustomIdDTOs where entityCustomIdDTOMapsId is null");
            return entityCustomIdDTOService.findAllWhereEntityCustomIdDTOMapsIdIsNull();
        }

        if ("onetooneback-is-null".equals(filter)) {
            LOG.debug("REST request to get all EntityCustomIdDTOs where oneToOneBack is null");
            return entityCustomIdDTOService.findAllWhereOneToOneBackIsNull();
        }
        LOG.debug("REST request to get all EntityCustomIdDTOS");
        return entityCustomIdDTOService.findAll();
    }

    /**
     * {@code GET  /entity-custom-id-dtos/:id} : get the "id" entityCustomIdDTO.
     *
     * @param id the id of the entityCustomIdDTODTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityCustomIdDTODTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityCustomIdDTODTO> getEntityCustomIdDTO(@PathVariable("id") Long id) {
        LOG.debug("REST request to get EntityCustomIdDTO : {}", id);
        Optional<EntityCustomIdDTODTO> entityCustomIdDTODTO = entityCustomIdDTOService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entityCustomIdDTODTO);
    }

    /**
     * {@code DELETE  /entity-custom-id-dtos/:id} : delete the "id" entityCustomIdDTO.
     *
     * @param id the id of the entityCustomIdDTODTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntityCustomIdDTO(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete EntityCustomIdDTO : {}", id);
        entityCustomIdDTOService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
