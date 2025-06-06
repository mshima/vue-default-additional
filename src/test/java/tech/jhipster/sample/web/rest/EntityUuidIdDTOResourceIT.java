package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.sample.domain.EntityUuidIdDTOAsserts.*;
import static tech.jhipster.sample.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.sample.IntegrationTest;
import tech.jhipster.sample.domain.EntityUuidIdDTO;
import tech.jhipster.sample.repository.EntityUuidIdDTORepository;
import tech.jhipster.sample.service.dto.EntityUuidIdDTODTO;
import tech.jhipster.sample.service.mapper.EntityUuidIdDTOMapper;

/**
 * Integration tests for the {@link EntityUuidIdDTOResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EntityUuidIdDTOResourceIT {

    private static final String ENTITY_API_URL = "/api/entity-uuid-id-dtos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EntityUuidIdDTORepository entityUuidIdDTORepository;

    @Autowired
    private EntityUuidIdDTOMapper entityUuidIdDTOMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntityUuidIdDTOMockMvc;

    private EntityUuidIdDTO entityUuidIdDTO;

    private EntityUuidIdDTO insertedEntityUuidIdDTO;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityUuidIdDTO createEntity() {
        return new EntityUuidIdDTO();
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityUuidIdDTO createUpdatedEntity() {
        return new EntityUuidIdDTO();
    }

    @BeforeEach
    void initTest() {
        entityUuidIdDTO = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedEntityUuidIdDTO != null) {
            entityUuidIdDTORepository.delete(insertedEntityUuidIdDTO);
            insertedEntityUuidIdDTO = null;
        }
    }

    @Test
    @Transactional
    void createEntityUuidIdDTO() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the EntityUuidIdDTO
        EntityUuidIdDTODTO entityUuidIdDTODTO = entityUuidIdDTOMapper.toDto(entityUuidIdDTO);
        var returnedEntityUuidIdDTODTO = om.readValue(
            restEntityUuidIdDTOMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityUuidIdDTODTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            EntityUuidIdDTODTO.class
        );

        // Validate the EntityUuidIdDTO in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedEntityUuidIdDTO = entityUuidIdDTOMapper.toEntity(returnedEntityUuidIdDTODTO);
        assertEntityUuidIdDTOUpdatableFieldsEquals(returnedEntityUuidIdDTO, getPersistedEntityUuidIdDTO(returnedEntityUuidIdDTO));

        insertedEntityUuidIdDTO = returnedEntityUuidIdDTO;
    }

    @Test
    @Transactional
    void createEntityUuidIdDTOWithExistingId() throws Exception {
        // Create the EntityUuidIdDTO with an existing ID
        insertedEntityUuidIdDTO = entityUuidIdDTORepository.saveAndFlush(entityUuidIdDTO);
        EntityUuidIdDTODTO entityUuidIdDTODTO = entityUuidIdDTOMapper.toDto(entityUuidIdDTO);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityUuidIdDTOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityUuidIdDTODTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEntityUuidIdDTOS() throws Exception {
        // Initialize the database
        insertedEntityUuidIdDTO = entityUuidIdDTORepository.saveAndFlush(entityUuidIdDTO);

        // Get all the entityUuidIdDTOList
        restEntityUuidIdDTOMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityUuidIdDTO.getId().toString())));
    }

    @Test
    @Transactional
    void getEntityUuidIdDTO() throws Exception {
        // Initialize the database
        insertedEntityUuidIdDTO = entityUuidIdDTORepository.saveAndFlush(entityUuidIdDTO);

        // Get the entityUuidIdDTO
        restEntityUuidIdDTOMockMvc
            .perform(get(ENTITY_API_URL_ID, entityUuidIdDTO.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entityUuidIdDTO.getId().toString()));
    }

    @Test
    @Transactional
    void getNonExistingEntityUuidIdDTO() throws Exception {
        // Get the entityUuidIdDTO
        restEntityUuidIdDTOMockMvc.perform(get(ENTITY_API_URL_ID, UUID.randomUUID().toString())).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEntityUuidIdDTO() throws Exception {
        // Initialize the database
        insertedEntityUuidIdDTO = entityUuidIdDTORepository.saveAndFlush(entityUuidIdDTO);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityUuidIdDTO
        EntityUuidIdDTO updatedEntityUuidIdDTO = entityUuidIdDTORepository.findById(entityUuidIdDTO.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedEntityUuidIdDTO are not directly saved in db
        em.detach(updatedEntityUuidIdDTO);
        EntityUuidIdDTODTO entityUuidIdDTODTO = entityUuidIdDTOMapper.toDto(updatedEntityUuidIdDTO);

        restEntityUuidIdDTOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityUuidIdDTODTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityUuidIdDTODTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityUuidIdDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedEntityUuidIdDTOToMatchAllProperties(updatedEntityUuidIdDTO);
    }

    @Test
    @Transactional
    void putNonExistingEntityUuidIdDTO() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdDTO.setId(UUID.randomUUID());

        // Create the EntityUuidIdDTO
        EntityUuidIdDTODTO entityUuidIdDTODTO = entityUuidIdDTOMapper.toDto(entityUuidIdDTO);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityUuidIdDTOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityUuidIdDTODTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityUuidIdDTODTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEntityUuidIdDTO() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdDTO.setId(UUID.randomUUID());

        // Create the EntityUuidIdDTO
        EntityUuidIdDTODTO entityUuidIdDTODTO = entityUuidIdDTOMapper.toDto(entityUuidIdDTO);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdDTOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityUuidIdDTODTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEntityUuidIdDTO() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdDTO.setId(UUID.randomUUID());

        // Create the EntityUuidIdDTO
        EntityUuidIdDTODTO entityUuidIdDTODTO = entityUuidIdDTOMapper.toDto(entityUuidIdDTO);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdDTOMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityUuidIdDTODTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityUuidIdDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEntityUuidIdDTOWithPatch() throws Exception {
        // Initialize the database
        insertedEntityUuidIdDTO = entityUuidIdDTORepository.saveAndFlush(entityUuidIdDTO);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityUuidIdDTO using partial update
        EntityUuidIdDTO partialUpdatedEntityUuidIdDTO = new EntityUuidIdDTO();
        partialUpdatedEntityUuidIdDTO.setId(entityUuidIdDTO.getId());

        restEntityUuidIdDTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityUuidIdDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityUuidIdDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityUuidIdDTO in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityUuidIdDTOUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedEntityUuidIdDTO, entityUuidIdDTO),
            getPersistedEntityUuidIdDTO(entityUuidIdDTO)
        );
    }

    @Test
    @Transactional
    void fullUpdateEntityUuidIdDTOWithPatch() throws Exception {
        // Initialize the database
        insertedEntityUuidIdDTO = entityUuidIdDTORepository.saveAndFlush(entityUuidIdDTO);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityUuidIdDTO using partial update
        EntityUuidIdDTO partialUpdatedEntityUuidIdDTO = new EntityUuidIdDTO();
        partialUpdatedEntityUuidIdDTO.setId(entityUuidIdDTO.getId());

        restEntityUuidIdDTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityUuidIdDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityUuidIdDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityUuidIdDTO in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityUuidIdDTOUpdatableFieldsEquals(
            partialUpdatedEntityUuidIdDTO,
            getPersistedEntityUuidIdDTO(partialUpdatedEntityUuidIdDTO)
        );
    }

    @Test
    @Transactional
    void patchNonExistingEntityUuidIdDTO() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdDTO.setId(UUID.randomUUID());

        // Create the EntityUuidIdDTO
        EntityUuidIdDTODTO entityUuidIdDTODTO = entityUuidIdDTOMapper.toDto(entityUuidIdDTO);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityUuidIdDTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, entityUuidIdDTODTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityUuidIdDTODTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEntityUuidIdDTO() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdDTO.setId(UUID.randomUUID());

        // Create the EntityUuidIdDTO
        EntityUuidIdDTODTO entityUuidIdDTODTO = entityUuidIdDTOMapper.toDto(entityUuidIdDTO);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdDTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityUuidIdDTODTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEntityUuidIdDTO() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdDTO.setId(UUID.randomUUID());

        // Create the EntityUuidIdDTO
        EntityUuidIdDTODTO entityUuidIdDTODTO = entityUuidIdDTOMapper.toDto(entityUuidIdDTO);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdDTOMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(entityUuidIdDTODTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityUuidIdDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEntityUuidIdDTO() throws Exception {
        // Initialize the database
        insertedEntityUuidIdDTO = entityUuidIdDTORepository.saveAndFlush(entityUuidIdDTO);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the entityUuidIdDTO
        restEntityUuidIdDTOMockMvc
            .perform(delete(ENTITY_API_URL_ID, entityUuidIdDTO.getId().toString()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return entityUuidIdDTORepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected EntityUuidIdDTO getPersistedEntityUuidIdDTO(EntityUuidIdDTO entityUuidIdDTO) {
        return entityUuidIdDTORepository.findById(entityUuidIdDTO.getId()).orElseThrow();
    }

    protected void assertPersistedEntityUuidIdDTOToMatchAllProperties(EntityUuidIdDTO expectedEntityUuidIdDTO) {
        assertEntityUuidIdDTOAllPropertiesEquals(expectedEntityUuidIdDTO, getPersistedEntityUuidIdDTO(expectedEntityUuidIdDTO));
    }

    protected void assertPersistedEntityUuidIdDTOToMatchUpdatableProperties(EntityUuidIdDTO expectedEntityUuidIdDTO) {
        assertEntityUuidIdDTOAllUpdatablePropertiesEquals(expectedEntityUuidIdDTO, getPersistedEntityUuidIdDTO(expectedEntityUuidIdDTO));
    }
}
