package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.sample.domain.EntityCustomIdAsserts.*;
import static tech.jhipster.sample.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
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
import tech.jhipster.sample.domain.EntityCustomId;
import tech.jhipster.sample.repository.EntityCustomIdRepository;

/**
 * Integration tests for the {@link EntityCustomIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EntityCustomIdResourceIT {

    private static final String ENTITY_API_URL = "/api/entity-custom-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{customId}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EntityCustomIdRepository entityCustomIdRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntityCustomIdMockMvc;

    private EntityCustomId entityCustomId;

    private EntityCustomId insertedEntityCustomId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityCustomId createEntity() {
        return new EntityCustomId();
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityCustomId createUpdatedEntity() {
        return new EntityCustomId();
    }

    @BeforeEach
    void initTest() {
        entityCustomId = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedEntityCustomId != null) {
            entityCustomIdRepository.delete(insertedEntityCustomId);
            insertedEntityCustomId = null;
        }
    }

    @Test
    @Transactional
    void createEntityCustomId() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the EntityCustomId
        var returnedEntityCustomId = om.readValue(
            restEntityCustomIdMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomId)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            EntityCustomId.class
        );

        // Validate the EntityCustomId in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertEntityCustomIdUpdatableFieldsEquals(returnedEntityCustomId, getPersistedEntityCustomId(returnedEntityCustomId));

        insertedEntityCustomId = returnedEntityCustomId;
    }

    @Test
    @Transactional
    void createEntityCustomIdWithExistingId() throws Exception {
        // Create the EntityCustomId with an existing ID
        entityCustomId.setCustomId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityCustomIdMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomId)))
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomId in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEntityCustomIds() throws Exception {
        // Initialize the database
        insertedEntityCustomId = entityCustomIdRepository.saveAndFlush(entityCustomId);

        // Get all the entityCustomIdList
        restEntityCustomIdMockMvc
            .perform(get(ENTITY_API_URL + "?sort=customId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].customId").value(hasItem(entityCustomId.getCustomId().intValue())));
    }

    @Test
    @Transactional
    void getEntityCustomId() throws Exception {
        // Initialize the database
        insertedEntityCustomId = entityCustomIdRepository.saveAndFlush(entityCustomId);

        // Get the entityCustomId
        restEntityCustomIdMockMvc
            .perform(get(ENTITY_API_URL_ID, entityCustomId.getCustomId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.customId").value(entityCustomId.getCustomId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingEntityCustomId() throws Exception {
        // Get the entityCustomId
        restEntityCustomIdMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEntityCustomId() throws Exception {
        // Initialize the database
        insertedEntityCustomId = entityCustomIdRepository.saveAndFlush(entityCustomId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomId
        EntityCustomId updatedEntityCustomId = entityCustomIdRepository.findById(entityCustomId.getCustomId()).orElseThrow();
        // Disconnect from session so that the updates on updatedEntityCustomId are not directly saved in db
        em.detach(updatedEntityCustomId);

        restEntityCustomIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEntityCustomId.getCustomId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedEntityCustomId))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedEntityCustomIdToMatchAllProperties(updatedEntityCustomId);
    }

    @Test
    @Transactional
    void putNonExistingEntityCustomId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomId.setCustomId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityCustomIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityCustomId.getCustomId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomId))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEntityCustomId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomId.setCustomId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomId))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEntityCustomId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomId.setCustomId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomId)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityCustomId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEntityCustomIdWithPatch() throws Exception {
        // Initialize the database
        insertedEntityCustomId = entityCustomIdRepository.saveAndFlush(entityCustomId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomId using partial update
        EntityCustomId partialUpdatedEntityCustomId = new EntityCustomId();
        partialUpdatedEntityCustomId.setCustomId(entityCustomId.getCustomId());

        restEntityCustomIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityCustomId.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityCustomId))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomId in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityCustomIdUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedEntityCustomId, entityCustomId),
            getPersistedEntityCustomId(entityCustomId)
        );
    }

    @Test
    @Transactional
    void fullUpdateEntityCustomIdWithPatch() throws Exception {
        // Initialize the database
        insertedEntityCustomId = entityCustomIdRepository.saveAndFlush(entityCustomId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomId using partial update
        EntityCustomId partialUpdatedEntityCustomId = new EntityCustomId();
        partialUpdatedEntityCustomId.setCustomId(entityCustomId.getCustomId());

        restEntityCustomIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityCustomId.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityCustomId))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomId in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityCustomIdUpdatableFieldsEquals(partialUpdatedEntityCustomId, getPersistedEntityCustomId(partialUpdatedEntityCustomId));
    }

    @Test
    @Transactional
    void patchNonExistingEntityCustomId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomId.setCustomId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityCustomIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, entityCustomId.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityCustomId))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEntityCustomId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomId.setCustomId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityCustomId))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEntityCustomId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomId.setCustomId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(entityCustomId)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityCustomId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEntityCustomId() throws Exception {
        // Initialize the database
        insertedEntityCustomId = entityCustomIdRepository.saveAndFlush(entityCustomId);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the entityCustomId
        restEntityCustomIdMockMvc
            .perform(delete(ENTITY_API_URL_ID, entityCustomId.getCustomId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return entityCustomIdRepository.count();
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

    protected EntityCustomId getPersistedEntityCustomId(EntityCustomId entityCustomId) {
        return entityCustomIdRepository.findById(entityCustomId.getCustomId()).orElseThrow();
    }

    protected void assertPersistedEntityCustomIdToMatchAllProperties(EntityCustomId expectedEntityCustomId) {
        assertEntityCustomIdAllPropertiesEquals(expectedEntityCustomId, getPersistedEntityCustomId(expectedEntityCustomId));
    }

    protected void assertPersistedEntityCustomIdToMatchUpdatableProperties(EntityCustomId expectedEntityCustomId) {
        assertEntityCustomIdAllUpdatablePropertiesEquals(expectedEntityCustomId, getPersistedEntityCustomId(expectedEntityCustomId));
    }
}
