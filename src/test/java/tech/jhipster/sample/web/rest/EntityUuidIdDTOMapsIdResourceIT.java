package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.sample.domain.EntityUuidIdDTOMapsIdAsserts.*;
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
import tech.jhipster.sample.domain.EntityUuidIdDTOMapsId;
import tech.jhipster.sample.repository.EntityUuidIdDTOMapsIdRepository;
import tech.jhipster.sample.service.dto.EntityUuidIdDTOMapsIdDTO;
import tech.jhipster.sample.service.mapper.EntityUuidIdDTOMapsIdMapper;

/**
 * Integration tests for the {@link EntityUuidIdDTOMapsIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EntityUuidIdDTOMapsIdResourceIT {

    private static final String ENTITY_API_URL = "/api/entity-uuid-id-dto-maps-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EntityUuidIdDTOMapsIdRepository entityUuidIdDTOMapsIdRepository;

    @Autowired
    private EntityUuidIdDTOMapsIdMapper entityUuidIdDTOMapsIdMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntityUuidIdDTOMapsIdMockMvc;

    private EntityUuidIdDTOMapsId entityUuidIdDTOMapsId;

    private EntityUuidIdDTOMapsId insertedEntityUuidIdDTOMapsId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityUuidIdDTOMapsId createEntity(EntityManager em) {
        EntityUuidIdDTOMapsId entityUuidIdDTOMapsId = new EntityUuidIdDTOMapsId();
        // Add required entity
        EntityUuidIdDTO entityUuidIdDTO;
        if (TestUtil.findAll(em, EntityUuidIdDTO.class).isEmpty()) {
            entityUuidIdDTO = EntityUuidIdDTOResourceIT.createEntity();
            em.persist(entityUuidIdDTO);
            em.flush();
        } else {
            entityUuidIdDTO = TestUtil.findAll(em, EntityUuidIdDTO.class).get(0);
        }
        entityUuidIdDTOMapsId.setEntityUuidIdDTO(entityUuidIdDTO);
        return entityUuidIdDTOMapsId;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityUuidIdDTOMapsId createUpdatedEntity(EntityManager em) {
        EntityUuidIdDTOMapsId updatedEntityUuidIdDTOMapsId = new EntityUuidIdDTOMapsId();
        // Add required entity
        EntityUuidIdDTO entityUuidIdDTO;
        entityUuidIdDTO = EntityUuidIdDTOResourceIT.createUpdatedEntity();
        em.persist(entityUuidIdDTO);
        em.flush();
        updatedEntityUuidIdDTOMapsId.setEntityUuidIdDTO(entityUuidIdDTO);
        return updatedEntityUuidIdDTOMapsId;
    }

    @BeforeEach
    void initTest() {
        entityUuidIdDTOMapsId = createEntity(em);
    }

    @AfterEach
    void cleanup() {
        if (insertedEntityUuidIdDTOMapsId != null) {
            entityUuidIdDTOMapsIdRepository.delete(insertedEntityUuidIdDTOMapsId);
            insertedEntityUuidIdDTOMapsId = null;
        }
    }

    @Test
    @Transactional
    void createEntityUuidIdDTOMapsId() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the EntityUuidIdDTOMapsId
        EntityUuidIdDTOMapsIdDTO entityUuidIdDTOMapsIdDTO = entityUuidIdDTOMapsIdMapper.toDto(entityUuidIdDTOMapsId);
        var returnedEntityUuidIdDTOMapsIdDTO = om.readValue(
            restEntityUuidIdDTOMapsIdMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityUuidIdDTOMapsIdDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            EntityUuidIdDTOMapsIdDTO.class
        );

        // Validate the EntityUuidIdDTOMapsId in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedEntityUuidIdDTOMapsId = entityUuidIdDTOMapsIdMapper.toEntity(returnedEntityUuidIdDTOMapsIdDTO);
        assertEntityUuidIdDTOMapsIdUpdatableFieldsEquals(
            returnedEntityUuidIdDTOMapsId,
            getPersistedEntityUuidIdDTOMapsId(returnedEntityUuidIdDTOMapsId)
        );

        assertEntityUuidIdDTOMapsIdMapsIdRelationshipPersistedValue(entityUuidIdDTOMapsId, returnedEntityUuidIdDTOMapsId);

        insertedEntityUuidIdDTOMapsId = returnedEntityUuidIdDTOMapsId;
    }

    @Test
    @Transactional
    void createEntityUuidIdDTOMapsIdWithExistingId() throws Exception {
        // Create the EntityUuidIdDTOMapsId with an existing ID
        insertedEntityUuidIdDTOMapsId = entityUuidIdDTOMapsIdRepository.saveAndFlush(entityUuidIdDTOMapsId);
        EntityUuidIdDTOMapsIdDTO entityUuidIdDTOMapsIdDTO = entityUuidIdDTOMapsIdMapper.toDto(entityUuidIdDTOMapsId);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityUuidIdDTOMapsIdMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityUuidIdDTOMapsIdDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void updateEntityUuidIdDTOMapsIdMapsIdAssociationWithNewId() throws Exception {
        // Initialize the database
        insertedEntityUuidIdDTOMapsId = entityUuidIdDTOMapsIdRepository.saveAndFlush(entityUuidIdDTOMapsId);
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Add a new parent entity
        EntityUuidIdDTO entityUuidIdDTO = EntityUuidIdDTOResourceIT.createUpdatedEntity();
        em.persist(entityUuidIdDTO);
        em.flush();

        // Load the entityUuidIdDTOMapsId
        EntityUuidIdDTOMapsId updatedEntityUuidIdDTOMapsId = entityUuidIdDTOMapsIdRepository
            .findById(entityUuidIdDTOMapsId.getId())
            .orElseThrow();
        assertThat(updatedEntityUuidIdDTOMapsId).isNotNull();
        // Disconnect from session so that the updates on updatedEntityUuidIdDTOMapsId are not directly saved in db
        em.detach(updatedEntityUuidIdDTOMapsId);

        // Update the EntityUuidIdDTO with new association value
        updatedEntityUuidIdDTOMapsId.setEntityUuidIdDTO(entityUuidIdDTO);
        EntityUuidIdDTOMapsIdDTO updatedEntityUuidIdDTOMapsIdDTO = entityUuidIdDTOMapsIdMapper.toDto(updatedEntityUuidIdDTOMapsId);
        assertThat(updatedEntityUuidIdDTOMapsIdDTO).isNotNull();

        // Update the entity
        restEntityUuidIdDTOMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEntityUuidIdDTOMapsIdDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedEntityUuidIdDTOMapsIdDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityUuidIdDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        /**
         * Validate the id for MapsId, the ids must be same
         * Uncomment the following line for assertion. However, please note that there is a known issue and uncommenting will fail the test.
         * Please look at https://github.com/jhipster/generator-jhipster/issues/9100. You can modify this test as necessary.
         * assertThat(testEntityUuidIdDTOMapsId.getId()).isEqualTo(testEntityUuidIdDTOMapsId.getEntityUuidIdDTO().getId());
         */
    }

    @Test
    @Transactional
    void getAllEntityUuidIdDTOMapsIds() throws Exception {
        // Initialize the database
        insertedEntityUuidIdDTOMapsId = entityUuidIdDTOMapsIdRepository.saveAndFlush(entityUuidIdDTOMapsId);

        // Get all the entityUuidIdDTOMapsIdList
        restEntityUuidIdDTOMapsIdMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityUuidIdDTOMapsId.getId().toString())));
    }

    @Test
    @Transactional
    void getEntityUuidIdDTOMapsId() throws Exception {
        // Initialize the database
        insertedEntityUuidIdDTOMapsId = entityUuidIdDTOMapsIdRepository.saveAndFlush(entityUuidIdDTOMapsId);

        // Get the entityUuidIdDTOMapsId
        restEntityUuidIdDTOMapsIdMockMvc
            .perform(get(ENTITY_API_URL_ID, entityUuidIdDTOMapsId.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entityUuidIdDTOMapsId.getId().toString()));
    }

    @Test
    @Transactional
    void getNonExistingEntityUuidIdDTOMapsId() throws Exception {
        // Get the entityUuidIdDTOMapsId
        restEntityUuidIdDTOMapsIdMockMvc.perform(get(ENTITY_API_URL_ID, UUID.randomUUID().toString())).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEntityUuidIdDTOMapsId() throws Exception {
        // Initialize the database
        insertedEntityUuidIdDTOMapsId = entityUuidIdDTOMapsIdRepository.saveAndFlush(entityUuidIdDTOMapsId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityUuidIdDTOMapsId
        EntityUuidIdDTOMapsId updatedEntityUuidIdDTOMapsId = entityUuidIdDTOMapsIdRepository
            .findById(entityUuidIdDTOMapsId.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedEntityUuidIdDTOMapsId are not directly saved in db
        em.detach(updatedEntityUuidIdDTOMapsId);
        EntityUuidIdDTOMapsIdDTO entityUuidIdDTOMapsIdDTO = entityUuidIdDTOMapsIdMapper.toDto(updatedEntityUuidIdDTOMapsId);

        restEntityUuidIdDTOMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityUuidIdDTOMapsIdDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityUuidIdDTOMapsIdDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityUuidIdDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedEntityUuidIdDTOMapsIdToMatchAllProperties(updatedEntityUuidIdDTOMapsId);
    }

    @Test
    @Transactional
    void putNonExistingEntityUuidIdDTOMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdDTOMapsId.setId(UUID.randomUUID());

        // Create the EntityUuidIdDTOMapsId
        EntityUuidIdDTOMapsIdDTO entityUuidIdDTOMapsIdDTO = entityUuidIdDTOMapsIdMapper.toDto(entityUuidIdDTOMapsId);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityUuidIdDTOMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityUuidIdDTOMapsIdDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityUuidIdDTOMapsIdDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEntityUuidIdDTOMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdDTOMapsId.setId(UUID.randomUUID());

        // Create the EntityUuidIdDTOMapsId
        EntityUuidIdDTOMapsIdDTO entityUuidIdDTOMapsIdDTO = entityUuidIdDTOMapsIdMapper.toDto(entityUuidIdDTOMapsId);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdDTOMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityUuidIdDTOMapsIdDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEntityUuidIdDTOMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdDTOMapsId.setId(UUID.randomUUID());

        // Create the EntityUuidIdDTOMapsId
        EntityUuidIdDTOMapsIdDTO entityUuidIdDTOMapsIdDTO = entityUuidIdDTOMapsIdMapper.toDto(entityUuidIdDTOMapsId);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdDTOMapsIdMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityUuidIdDTOMapsIdDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityUuidIdDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEntityUuidIdDTOMapsIdWithPatch() throws Exception {
        // Initialize the database
        insertedEntityUuidIdDTOMapsId = entityUuidIdDTOMapsIdRepository.saveAndFlush(entityUuidIdDTOMapsId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityUuidIdDTOMapsId using partial update
        EntityUuidIdDTOMapsId partialUpdatedEntityUuidIdDTOMapsId = new EntityUuidIdDTOMapsId();
        partialUpdatedEntityUuidIdDTOMapsId.setId(entityUuidIdDTOMapsId.getId());

        restEntityUuidIdDTOMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityUuidIdDTOMapsId.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityUuidIdDTOMapsId))
            )
            .andExpect(status().isOk());

        // Validate the EntityUuidIdDTOMapsId in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityUuidIdDTOMapsIdUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedEntityUuidIdDTOMapsId, entityUuidIdDTOMapsId),
            getPersistedEntityUuidIdDTOMapsId(entityUuidIdDTOMapsId)
        );
    }

    @Test
    @Transactional
    void fullUpdateEntityUuidIdDTOMapsIdWithPatch() throws Exception {
        // Initialize the database
        insertedEntityUuidIdDTOMapsId = entityUuidIdDTOMapsIdRepository.saveAndFlush(entityUuidIdDTOMapsId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityUuidIdDTOMapsId using partial update
        EntityUuidIdDTOMapsId partialUpdatedEntityUuidIdDTOMapsId = new EntityUuidIdDTOMapsId();
        partialUpdatedEntityUuidIdDTOMapsId.setId(entityUuidIdDTOMapsId.getId());

        restEntityUuidIdDTOMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityUuidIdDTOMapsId.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityUuidIdDTOMapsId))
            )
            .andExpect(status().isOk());

        // Validate the EntityUuidIdDTOMapsId in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityUuidIdDTOMapsIdUpdatableFieldsEquals(
            partialUpdatedEntityUuidIdDTOMapsId,
            getPersistedEntityUuidIdDTOMapsId(partialUpdatedEntityUuidIdDTOMapsId)
        );
    }

    @Test
    @Transactional
    void patchNonExistingEntityUuidIdDTOMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdDTOMapsId.setId(UUID.randomUUID());

        // Create the EntityUuidIdDTOMapsId
        EntityUuidIdDTOMapsIdDTO entityUuidIdDTOMapsIdDTO = entityUuidIdDTOMapsIdMapper.toDto(entityUuidIdDTOMapsId);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityUuidIdDTOMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, entityUuidIdDTOMapsIdDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityUuidIdDTOMapsIdDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEntityUuidIdDTOMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdDTOMapsId.setId(UUID.randomUUID());

        // Create the EntityUuidIdDTOMapsId
        EntityUuidIdDTOMapsIdDTO entityUuidIdDTOMapsIdDTO = entityUuidIdDTOMapsIdMapper.toDto(entityUuidIdDTOMapsId);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdDTOMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityUuidIdDTOMapsIdDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEntityUuidIdDTOMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdDTOMapsId.setId(UUID.randomUUID());

        // Create the EntityUuidIdDTOMapsId
        EntityUuidIdDTOMapsIdDTO entityUuidIdDTOMapsIdDTO = entityUuidIdDTOMapsIdMapper.toDto(entityUuidIdDTOMapsId);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdDTOMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(entityUuidIdDTOMapsIdDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityUuidIdDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEntityUuidIdDTOMapsId() throws Exception {
        // Initialize the database
        insertedEntityUuidIdDTOMapsId = entityUuidIdDTOMapsIdRepository.saveAndFlush(entityUuidIdDTOMapsId);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the entityUuidIdDTOMapsId
        restEntityUuidIdDTOMapsIdMockMvc
            .perform(delete(ENTITY_API_URL_ID, entityUuidIdDTOMapsId.getId().toString()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return entityUuidIdDTOMapsIdRepository.count();
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

    protected EntityUuidIdDTOMapsId getPersistedEntityUuidIdDTOMapsId(EntityUuidIdDTOMapsId entityUuidIdDTOMapsId) {
        return entityUuidIdDTOMapsIdRepository.findById(entityUuidIdDTOMapsId.getId()).orElseThrow();
    }

    protected void assertPersistedEntityUuidIdDTOMapsIdToMatchAllProperties(EntityUuidIdDTOMapsId expectedEntityUuidIdDTOMapsId) {
        assertEntityUuidIdDTOMapsIdAllPropertiesEquals(
            expectedEntityUuidIdDTOMapsId,
            getPersistedEntityUuidIdDTOMapsId(expectedEntityUuidIdDTOMapsId)
        );
    }

    protected void assertPersistedEntityUuidIdDTOMapsIdToMatchUpdatableProperties(EntityUuidIdDTOMapsId expectedEntityUuidIdDTOMapsId) {
        assertEntityUuidIdDTOMapsIdAllUpdatablePropertiesEquals(
            expectedEntityUuidIdDTOMapsId,
            getPersistedEntityUuidIdDTOMapsId(expectedEntityUuidIdDTOMapsId)
        );
    }
}
