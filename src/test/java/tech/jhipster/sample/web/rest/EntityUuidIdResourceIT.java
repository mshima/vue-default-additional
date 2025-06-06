package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.sample.domain.EntityUuidIdAsserts.*;
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
import tech.jhipster.sample.domain.EntityUuidId;
import tech.jhipster.sample.repository.EntityUuidIdRepository;

/**
 * Integration tests for the {@link EntityUuidIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EntityUuidIdResourceIT {

    private static final String ENTITY_API_URL = "/api/entity-uuid-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EntityUuidIdRepository entityUuidIdRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntityUuidIdMockMvc;

    private EntityUuidId entityUuidId;

    private EntityUuidId insertedEntityUuidId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityUuidId createEntity() {
        return new EntityUuidId();
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityUuidId createUpdatedEntity() {
        return new EntityUuidId();
    }

    @BeforeEach
    void initTest() {
        entityUuidId = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedEntityUuidId != null) {
            entityUuidIdRepository.delete(insertedEntityUuidId);
            insertedEntityUuidId = null;
        }
    }

    @Test
    @Transactional
    void createEntityUuidId() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the EntityUuidId
        var returnedEntityUuidId = om.readValue(
            restEntityUuidIdMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityUuidId)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            EntityUuidId.class
        );

        // Validate the EntityUuidId in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertEntityUuidIdUpdatableFieldsEquals(returnedEntityUuidId, getPersistedEntityUuidId(returnedEntityUuidId));

        insertedEntityUuidId = returnedEntityUuidId;
    }

    @Test
    @Transactional
    void createEntityUuidIdWithExistingId() throws Exception {
        // Create the EntityUuidId with an existing ID
        insertedEntityUuidId = entityUuidIdRepository.saveAndFlush(entityUuidId);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityUuidIdMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityUuidId)))
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidId in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEntityUuidIds() throws Exception {
        // Initialize the database
        insertedEntityUuidId = entityUuidIdRepository.saveAndFlush(entityUuidId);

        // Get all the entityUuidIdList
        restEntityUuidIdMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityUuidId.getId().toString())));
    }

    @Test
    @Transactional
    void getEntityUuidId() throws Exception {
        // Initialize the database
        insertedEntityUuidId = entityUuidIdRepository.saveAndFlush(entityUuidId);

        // Get the entityUuidId
        restEntityUuidIdMockMvc
            .perform(get(ENTITY_API_URL_ID, entityUuidId.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entityUuidId.getId().toString()));
    }

    @Test
    @Transactional
    void getNonExistingEntityUuidId() throws Exception {
        // Get the entityUuidId
        restEntityUuidIdMockMvc.perform(get(ENTITY_API_URL_ID, UUID.randomUUID().toString())).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEntityUuidId() throws Exception {
        // Initialize the database
        insertedEntityUuidId = entityUuidIdRepository.saveAndFlush(entityUuidId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityUuidId
        EntityUuidId updatedEntityUuidId = entityUuidIdRepository.findById(entityUuidId.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedEntityUuidId are not directly saved in db
        em.detach(updatedEntityUuidId);

        restEntityUuidIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEntityUuidId.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedEntityUuidId))
            )
            .andExpect(status().isOk());

        // Validate the EntityUuidId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedEntityUuidIdToMatchAllProperties(updatedEntityUuidId);
    }

    @Test
    @Transactional
    void putNonExistingEntityUuidId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidId.setId(UUID.randomUUID());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityUuidIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityUuidId.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityUuidId))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEntityUuidId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidId.setId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityUuidId))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEntityUuidId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidId.setId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityUuidId)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityUuidId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEntityUuidIdWithPatch() throws Exception {
        // Initialize the database
        insertedEntityUuidId = entityUuidIdRepository.saveAndFlush(entityUuidId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityUuidId using partial update
        EntityUuidId partialUpdatedEntityUuidId = new EntityUuidId();
        partialUpdatedEntityUuidId.setId(entityUuidId.getId());

        restEntityUuidIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityUuidId.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityUuidId))
            )
            .andExpect(status().isOk());

        // Validate the EntityUuidId in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityUuidIdUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedEntityUuidId, entityUuidId),
            getPersistedEntityUuidId(entityUuidId)
        );
    }

    @Test
    @Transactional
    void fullUpdateEntityUuidIdWithPatch() throws Exception {
        // Initialize the database
        insertedEntityUuidId = entityUuidIdRepository.saveAndFlush(entityUuidId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityUuidId using partial update
        EntityUuidId partialUpdatedEntityUuidId = new EntityUuidId();
        partialUpdatedEntityUuidId.setId(entityUuidId.getId());

        restEntityUuidIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityUuidId.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityUuidId))
            )
            .andExpect(status().isOk());

        // Validate the EntityUuidId in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityUuidIdUpdatableFieldsEquals(partialUpdatedEntityUuidId, getPersistedEntityUuidId(partialUpdatedEntityUuidId));
    }

    @Test
    @Transactional
    void patchNonExistingEntityUuidId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidId.setId(UUID.randomUUID());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityUuidIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, entityUuidId.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityUuidId))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEntityUuidId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidId.setId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityUuidId))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEntityUuidId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidId.setId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(entityUuidId)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityUuidId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEntityUuidId() throws Exception {
        // Initialize the database
        insertedEntityUuidId = entityUuidIdRepository.saveAndFlush(entityUuidId);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the entityUuidId
        restEntityUuidIdMockMvc
            .perform(delete(ENTITY_API_URL_ID, entityUuidId.getId().toString()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return entityUuidIdRepository.count();
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

    protected EntityUuidId getPersistedEntityUuidId(EntityUuidId entityUuidId) {
        return entityUuidIdRepository.findById(entityUuidId.getId()).orElseThrow();
    }

    protected void assertPersistedEntityUuidIdToMatchAllProperties(EntityUuidId expectedEntityUuidId) {
        assertEntityUuidIdAllPropertiesEquals(expectedEntityUuidId, getPersistedEntityUuidId(expectedEntityUuidId));
    }

    protected void assertPersistedEntityUuidIdToMatchUpdatableProperties(EntityUuidId expectedEntityUuidId) {
        assertEntityUuidIdAllUpdatablePropertiesEquals(expectedEntityUuidId, getPersistedEntityUuidId(expectedEntityUuidId));
    }
}
