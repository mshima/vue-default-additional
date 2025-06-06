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
import tech.jhipster.sample.repository.EntityUuidIdDTORepository;
import tech.jhipster.sample.service.EntityUuidIdDTOService;
import tech.jhipster.sample.service.dto.EntityUuidIdDTODTO;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.EntityUuidIdDTO}.
 */
@RestController
@RequestMapping("/api/entity-uuid-id-dtos")
public class EntityUuidIdDTOResource {

    private static final Logger LOG = LoggerFactory.getLogger(EntityUuidIdDTOResource.class);

    private static final String ENTITY_NAME = "entityUuidIdDTO";

    @Value("${jhipster.clientApp.name:jhipsterVue}")
    private String applicationName;

    private final EntityUuidIdDTOService entityUuidIdDTOService;

    private final EntityUuidIdDTORepository entityUuidIdDTORepository;

    public EntityUuidIdDTOResource(EntityUuidIdDTOService entityUuidIdDTOService, EntityUuidIdDTORepository entityUuidIdDTORepository) {
        this.entityUuidIdDTOService = entityUuidIdDTOService;
        this.entityUuidIdDTORepository = entityUuidIdDTORepository;
    }

    /**
     * {@code POST  /entity-uuid-id-dtos} : Create a new entityUuidIdDTO.
     *
     * @param entityUuidIdDTODTO the entityUuidIdDTODTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityUuidIdDTODTO, or with status {@code 400 (Bad Request)} if the entityUuidIdDTO has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EntityUuidIdDTODTO> createEntityUuidIdDTO(@RequestBody EntityUuidIdDTODTO entityUuidIdDTODTO)
        throws URISyntaxException {
        LOG.debug("REST request to save EntityUuidIdDTO : {}", entityUuidIdDTODTO);
        if (entityUuidIdDTODTO.getId() != null) {
            throw new BadRequestAlertException("A new entityUuidIdDTO cannot already have an ID", ENTITY_NAME, "idexists");
        }
        entityUuidIdDTODTO = entityUuidIdDTOService.save(entityUuidIdDTODTO);
        return ResponseEntity.created(new URI("/api/entity-uuid-id-dtos/" + entityUuidIdDTODTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, entityUuidIdDTODTO.getId().toString()))
            .body(entityUuidIdDTODTO);
    }

    /**
     * {@code PUT  /entity-uuid-id-dtos/:id} : Updates an existing entityUuidIdDTO.
     *
     * @param id the id of the entityUuidIdDTODTO to save.
     * @param entityUuidIdDTODTO the entityUuidIdDTODTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityUuidIdDTODTO,
     * or with status {@code 400 (Bad Request)} if the entityUuidIdDTODTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityUuidIdDTODTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityUuidIdDTODTO> updateEntityUuidIdDTO(
        @PathVariable(value = "id", required = false) final UUID id,
        @RequestBody EntityUuidIdDTODTO entityUuidIdDTODTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update EntityUuidIdDTO : {}, {}", id, entityUuidIdDTODTO);
        if (entityUuidIdDTODTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, entityUuidIdDTODTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityUuidIdDTORepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        entityUuidIdDTODTO = entityUuidIdDTOService.update(entityUuidIdDTODTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityUuidIdDTODTO.getId().toString()))
            .body(entityUuidIdDTODTO);
    }

    /**
     * {@code PATCH  /entity-uuid-id-dtos/:id} : Partial updates given fields of an existing entityUuidIdDTO, field will ignore if it is null
     *
     * @param id the id of the entityUuidIdDTODTO to save.
     * @param entityUuidIdDTODTO the entityUuidIdDTODTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityUuidIdDTODTO,
     * or with status {@code 400 (Bad Request)} if the entityUuidIdDTODTO is not valid,
     * or with status {@code 404 (Not Found)} if the entityUuidIdDTODTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the entityUuidIdDTODTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EntityUuidIdDTODTO> partialUpdateEntityUuidIdDTO(
        @PathVariable(value = "id", required = false) final UUID id,
        @RequestBody EntityUuidIdDTODTO entityUuidIdDTODTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update EntityUuidIdDTO partially : {}, {}", id, entityUuidIdDTODTO);
        if (entityUuidIdDTODTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, entityUuidIdDTODTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityUuidIdDTORepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EntityUuidIdDTODTO> result = entityUuidIdDTOService.partialUpdate(entityUuidIdDTODTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityUuidIdDTODTO.getId().toString())
        );
    }

    /**
     * {@code GET  /entity-uuid-id-dtos} : get all the entityUuidIdDTOS.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityUuidIdDTOS in body.
     */
    @GetMapping("")
    public List<EntityUuidIdDTODTO> getAllEntityUuidIdDTOS(@RequestParam(name = "filter", required = false) String filter) {
        if ("entityuuididdtomapsid-is-null".equals(filter)) {
            LOG.debug("REST request to get all EntityUuidIdDTOs where entityUuidIdDTOMapsId is null");
            return entityUuidIdDTOService.findAllWhereEntityUuidIdDTOMapsIdIsNull();
        }

        if ("onetooneback-is-null".equals(filter)) {
            LOG.debug("REST request to get all EntityUuidIdDTOs where oneToOneBack is null");
            return entityUuidIdDTOService.findAllWhereOneToOneBackIsNull();
        }
        LOG.debug("REST request to get all EntityUuidIdDTOS");
        return entityUuidIdDTOService.findAll();
    }

    /**
     * {@code GET  /entity-uuid-id-dtos/:id} : get the "id" entityUuidIdDTO.
     *
     * @param id the id of the entityUuidIdDTODTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityUuidIdDTODTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityUuidIdDTODTO> getEntityUuidIdDTO(@PathVariable("id") UUID id) {
        LOG.debug("REST request to get EntityUuidIdDTO : {}", id);
        Optional<EntityUuidIdDTODTO> entityUuidIdDTODTO = entityUuidIdDTOService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entityUuidIdDTODTO);
    }

    /**
     * {@code DELETE  /entity-uuid-id-dtos/:id} : delete the "id" entityUuidIdDTO.
     *
     * @param id the id of the entityUuidIdDTODTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntityUuidIdDTO(@PathVariable("id") UUID id) {
        LOG.debug("REST request to delete EntityUuidIdDTO : {}", id);
        entityUuidIdDTOService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
