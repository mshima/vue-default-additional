package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.sample.domain.EntityUuidIdMapsIdAsserts.*;
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
import tech.jhipster.sample.domain.EntityUuidIdMapsId;
import tech.jhipster.sample.repository.EntityUuidIdMapsIdRepository;
import tech.jhipster.sample.repository.EntityUuidIdRepository;

/**
 * Integration tests for the {@link EntityUuidIdMapsIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EntityUuidIdMapsIdResourceIT {

    private static final String ENTITY_API_URL = "/api/entity-uuid-id-maps-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EntityUuidIdMapsIdRepository entityUuidIdMapsIdRepository;

    @Autowired
    private EntityUuidIdRepository entityUuidIdRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntityUuidIdMapsIdMockMvc;

    private EntityUuidIdMapsId entityUuidIdMapsId;

    private EntityUuidIdMapsId insertedEntityUuidIdMapsId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityUuidIdMapsId createEntity(EntityManager em) {
        EntityUuidIdMapsId entityUuidIdMapsId = new EntityUuidIdMapsId();
        // Add required entity
        EntityUuidId entityUuidId;
        if (TestUtil.findAll(em, EntityUuidId.class).isEmpty()) {
            entityUuidId = EntityUuidIdResourceIT.createEntity();
            em.persist(entityUuidId);
            em.flush();
        } else {
            entityUuidId = TestUtil.findAll(em, EntityUuidId.class).get(0);
        }
        entityUuidIdMapsId.setEntityUuidId(entityUuidId);
        return entityUuidIdMapsId;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityUuidIdMapsId createUpdatedEntity(EntityManager em) {
        EntityUuidIdMapsId updatedEntityUuidIdMapsId = new EntityUuidIdMapsId();
        // Add required entity
        EntityUuidId entityUuidId;
        entityUuidId = EntityUuidIdResourceIT.createUpdatedEntity();
        em.persist(entityUuidId);
        em.flush();
        updatedEntityUuidIdMapsId.setEntityUuidId(entityUuidId);
        return updatedEntityUuidIdMapsId;
    }

    @BeforeEach
    void initTest() {
        entityUuidIdMapsId = createEntity(em);
    }

    @AfterEach
    void cleanup() {
        if (insertedEntityUuidIdMapsId != null) {
            entityUuidIdMapsIdRepository.delete(insertedEntityUuidIdMapsId);
            insertedEntityUuidIdMapsId = null;
        }
    }

    @Test
    @Transactional
    void createEntityUuidIdMapsId() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the EntityUuidIdMapsId
        var returnedEntityUuidIdMapsId = om.readValue(
            restEntityUuidIdMapsIdMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityUuidIdMapsId)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            EntityUuidIdMapsId.class
        );

        // Validate the EntityUuidIdMapsId in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertEntityUuidIdMapsIdUpdatableFieldsEquals(
            returnedEntityUuidIdMapsId,
            getPersistedEntityUuidIdMapsId(returnedEntityUuidIdMapsId)
        );

        assertEntityUuidIdMapsIdMapsIdRelationshipPersistedValue(entityUuidIdMapsId, returnedEntityUuidIdMapsId);

        insertedEntityUuidIdMapsId = returnedEntityUuidIdMapsId;
    }

    @Test
    @Transactional
    void createEntityUuidIdMapsIdWithExistingId() throws Exception {
        // Create the EntityUuidIdMapsId with an existing ID
        insertedEntityUuidIdMapsId = entityUuidIdMapsIdRepository.saveAndFlush(entityUuidIdMapsId);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityUuidIdMapsIdMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityUuidIdMapsId)))
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void updateEntityUuidIdMapsIdMapsIdAssociationWithNewId() throws Exception {
        // Initialize the database
        insertedEntityUuidIdMapsId = entityUuidIdMapsIdRepository.saveAndFlush(entityUuidIdMapsId);
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Add a new parent entity
        EntityUuidId entityUuidId = EntityUuidIdResourceIT.createUpdatedEntity();
        em.persist(entityUuidId);
        em.flush();

        // Load the entityUuidIdMapsId
        EntityUuidIdMapsId updatedEntityUuidIdMapsId = entityUuidIdMapsIdRepository.findById(entityUuidIdMapsId.getId()).orElseThrow();
        assertThat(updatedEntityUuidIdMapsId).isNotNull();
        // Disconnect from session so that the updates on updatedEntityUuidIdMapsId are not directly saved in db
        em.detach(updatedEntityUuidIdMapsId);

        // Update the EntityUuidId with new association value
        updatedEntityUuidIdMapsId.setEntityUuidId(entityUuidId);

        // Update the entity
        restEntityUuidIdMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEntityUuidIdMapsId.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedEntityUuidIdMapsId))
            )
            .andExpect(status().isOk());

        // Validate the EntityUuidIdMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        /**
         * Validate the id for MapsId, the ids must be same
         * Uncomment the following line for assertion. However, please note that there is a known issue and uncommenting will fail the test.
         * Please look at https://github.com/jhipster/generator-jhipster/issues/9100. You can modify this test as necessary.
         * assertThat(testEntityUuidIdMapsId.getId()).isEqualTo(testEntityUuidIdMapsId.getEntityUuidId().getId());
         */
    }

    @Test
    @Transactional
    void getAllEntityUuidIdMapsIds() throws Exception {
        // Initialize the database
        insertedEntityUuidIdMapsId = entityUuidIdMapsIdRepository.saveAndFlush(entityUuidIdMapsId);

        // Get all the entityUuidIdMapsIdList
        restEntityUuidIdMapsIdMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityUuidIdMapsId.getId().toString())));
    }

    @Test
    @Transactional
    void getEntityUuidIdMapsId() throws Exception {
        // Initialize the database
        insertedEntityUuidIdMapsId = entityUuidIdMapsIdRepository.saveAndFlush(entityUuidIdMapsId);

        // Get the entityUuidIdMapsId
        restEntityUuidIdMapsIdMockMvc
            .perform(get(ENTITY_API_URL_ID, entityUuidIdMapsId.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entityUuidIdMapsId.getId().toString()));
    }

    @Test
    @Transactional
    void getNonExistingEntityUuidIdMapsId() throws Exception {
        // Get the entityUuidIdMapsId
        restEntityUuidIdMapsIdMockMvc.perform(get(ENTITY_API_URL_ID, UUID.randomUUID().toString())).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEntityUuidIdMapsId() throws Exception {
        // Initialize the database
        insertedEntityUuidIdMapsId = entityUuidIdMapsIdRepository.saveAndFlush(entityUuidIdMapsId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityUuidIdMapsId
        EntityUuidIdMapsId updatedEntityUuidIdMapsId = entityUuidIdMapsIdRepository.findById(entityUuidIdMapsId.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedEntityUuidIdMapsId are not directly saved in db
        em.detach(updatedEntityUuidIdMapsId);

        restEntityUuidIdMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEntityUuidIdMapsId.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedEntityUuidIdMapsId))
            )
            .andExpect(status().isOk());

        // Validate the EntityUuidIdMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedEntityUuidIdMapsIdToMatchAllProperties(updatedEntityUuidIdMapsId);
    }

    @Test
    @Transactional
    void putNonExistingEntityUuidIdMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdMapsId.setId(UUID.randomUUID());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityUuidIdMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityUuidIdMapsId.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityUuidIdMapsId))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEntityUuidIdMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdMapsId.setId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityUuidIdMapsId))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEntityUuidIdMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdMapsId.setId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdMapsIdMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityUuidIdMapsId)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityUuidIdMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEntityUuidIdMapsIdWithPatch() throws Exception {
        // Initialize the database
        insertedEntityUuidIdMapsId = entityUuidIdMapsIdRepository.saveAndFlush(entityUuidIdMapsId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityUuidIdMapsId using partial update
        EntityUuidIdMapsId partialUpdatedEntityUuidIdMapsId = new EntityUuidIdMapsId();
        partialUpdatedEntityUuidIdMapsId.setId(entityUuidIdMapsId.getId());

        restEntityUuidIdMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityUuidIdMapsId.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityUuidIdMapsId))
            )
            .andExpect(status().isOk());

        // Validate the EntityUuidIdMapsId in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityUuidIdMapsIdUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedEntityUuidIdMapsId, entityUuidIdMapsId),
            getPersistedEntityUuidIdMapsId(entityUuidIdMapsId)
        );
    }

    @Test
    @Transactional
    void fullUpdateEntityUuidIdMapsIdWithPatch() throws Exception {
        // Initialize the database
        insertedEntityUuidIdMapsId = entityUuidIdMapsIdRepository.saveAndFlush(entityUuidIdMapsId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityUuidIdMapsId using partial update
        EntityUuidIdMapsId partialUpdatedEntityUuidIdMapsId = new EntityUuidIdMapsId();
        partialUpdatedEntityUuidIdMapsId.setId(entityUuidIdMapsId.getId());

        restEntityUuidIdMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityUuidIdMapsId.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityUuidIdMapsId))
            )
            .andExpect(status().isOk());

        // Validate the EntityUuidIdMapsId in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityUuidIdMapsIdUpdatableFieldsEquals(
            partialUpdatedEntityUuidIdMapsId,
            getPersistedEntityUuidIdMapsId(partialUpdatedEntityUuidIdMapsId)
        );
    }

    @Test
    @Transactional
    void patchNonExistingEntityUuidIdMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdMapsId.setId(UUID.randomUUID());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityUuidIdMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, entityUuidIdMapsId.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityUuidIdMapsId))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEntityUuidIdMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdMapsId.setId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityUuidIdMapsId))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEntityUuidIdMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdMapsId.setId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdMapsIdMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(entityUuidIdMapsId)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityUuidIdMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEntityUuidIdMapsId() throws Exception {
        // Initialize the database
        insertedEntityUuidIdMapsId = entityUuidIdMapsIdRepository.saveAndFlush(entityUuidIdMapsId);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the entityUuidIdMapsId
        restEntityUuidIdMapsIdMockMvc
            .perform(delete(ENTITY_API_URL_ID, entityUuidIdMapsId.getId().toString()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return entityUuidIdMapsIdRepository.count();
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

    protected EntityUuidIdMapsId getPersistedEntityUuidIdMapsId(EntityUuidIdMapsId entityUuidIdMapsId) {
        return entityUuidIdMapsIdRepository.findById(entityUuidIdMapsId.getId()).orElseThrow();
    }

    protected void assertPersistedEntityUuidIdMapsIdToMatchAllProperties(EntityUuidIdMapsId expectedEntityUuidIdMapsId) {
        assertEntityUuidIdMapsIdAllPropertiesEquals(expectedEntityUuidIdMapsId, getPersistedEntityUuidIdMapsId(expectedEntityUuidIdMapsId));
    }

    protected void assertPersistedEntityUuidIdMapsIdToMatchUpdatableProperties(EntityUuidIdMapsId expectedEntityUuidIdMapsId) {
        assertEntityUuidIdMapsIdAllUpdatablePropertiesEquals(
            expectedEntityUuidIdMapsId,
            getPersistedEntityUuidIdMapsId(expectedEntityUuidIdMapsId)
        );
    }
}
