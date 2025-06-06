package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.sample.domain.EntityCustomIdMapsIdAsserts.*;
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
import tech.jhipster.sample.domain.EntityCustomIdMapsId;
import tech.jhipster.sample.repository.EntityCustomIdMapsIdRepository;
import tech.jhipster.sample.repository.EntityCustomIdRepository;

/**
 * Integration tests for the {@link EntityCustomIdMapsIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EntityCustomIdMapsIdResourceIT {

    private static final String ENTITY_API_URL = "/api/entity-custom-id-maps-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{customId}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EntityCustomIdMapsIdRepository entityCustomIdMapsIdRepository;

    @Autowired
    private EntityCustomIdRepository entityCustomIdRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntityCustomIdMapsIdMockMvc;

    private EntityCustomIdMapsId entityCustomIdMapsId;

    private EntityCustomIdMapsId insertedEntityCustomIdMapsId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityCustomIdMapsId createEntity(EntityManager em) {
        EntityCustomIdMapsId entityCustomIdMapsId = new EntityCustomIdMapsId();
        // Add required entity
        EntityCustomId entityCustomId;
        if (TestUtil.findAll(em, EntityCustomId.class).isEmpty()) {
            entityCustomId = EntityCustomIdResourceIT.createEntity();
            em.persist(entityCustomId);
            em.flush();
        } else {
            entityCustomId = TestUtil.findAll(em, EntityCustomId.class).get(0);
        }
        entityCustomIdMapsId.setEntityCustomId(entityCustomId);
        return entityCustomIdMapsId;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityCustomIdMapsId createUpdatedEntity(EntityManager em) {
        EntityCustomIdMapsId updatedEntityCustomIdMapsId = new EntityCustomIdMapsId();
        // Add required entity
        EntityCustomId entityCustomId;
        entityCustomId = EntityCustomIdResourceIT.createUpdatedEntity();
        em.persist(entityCustomId);
        em.flush();
        updatedEntityCustomIdMapsId.setEntityCustomId(entityCustomId);
        return updatedEntityCustomIdMapsId;
    }

    @BeforeEach
    void initTest() {
        entityCustomIdMapsId = createEntity(em);
    }

    @AfterEach
    void cleanup() {
        if (insertedEntityCustomIdMapsId != null) {
            entityCustomIdMapsIdRepository.delete(insertedEntityCustomIdMapsId);
            insertedEntityCustomIdMapsId = null;
        }
    }

    @Test
    @Transactional
    void createEntityCustomIdMapsId() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the EntityCustomIdMapsId
        var returnedEntityCustomIdMapsId = om.readValue(
            restEntityCustomIdMapsIdMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomIdMapsId)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            EntityCustomIdMapsId.class
        );

        // Validate the EntityCustomIdMapsId in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertEntityCustomIdMapsIdUpdatableFieldsEquals(
            returnedEntityCustomIdMapsId,
            getPersistedEntityCustomIdMapsId(returnedEntityCustomIdMapsId)
        );

        assertEntityCustomIdMapsIdMapsIdRelationshipPersistedValue(entityCustomIdMapsId, returnedEntityCustomIdMapsId);

        insertedEntityCustomIdMapsId = returnedEntityCustomIdMapsId;
    }

    @Test
    @Transactional
    void createEntityCustomIdMapsIdWithExistingId() throws Exception {
        // Create the EntityCustomIdMapsId with an existing ID
        entityCustomIdMapsId.setCustomId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityCustomIdMapsIdMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomIdMapsId)))
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void updateEntityCustomIdMapsIdMapsIdAssociationWithNewId() throws Exception {
        // Initialize the database
        insertedEntityCustomIdMapsId = entityCustomIdMapsIdRepository.saveAndFlush(entityCustomIdMapsId);
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Add a new parent entity
        EntityCustomId entityCustomId = EntityCustomIdResourceIT.createUpdatedEntity();
        em.persist(entityCustomId);
        em.flush();

        // Load the entityCustomIdMapsId
        EntityCustomIdMapsId updatedEntityCustomIdMapsId = entityCustomIdMapsIdRepository
            .findById(entityCustomIdMapsId.getCustomId())
            .orElseThrow();
        assertThat(updatedEntityCustomIdMapsId).isNotNull();
        // Disconnect from session so that the updates on updatedEntityCustomIdMapsId are not directly saved in db
        em.detach(updatedEntityCustomIdMapsId);

        // Update the EntityCustomId with new association value
        updatedEntityCustomIdMapsId.setEntityCustomId(entityCustomId);

        // Update the entity
        restEntityCustomIdMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEntityCustomIdMapsId.getCustomId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedEntityCustomIdMapsId))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        /**
         * Validate the id for MapsId, the ids must be same
         * Uncomment the following line for assertion. However, please note that there is a known issue and uncommenting will fail the test.
         * Please look at https://github.com/jhipster/generator-jhipster/issues/9100. You can modify this test as necessary.
         * assertThat(testEntityCustomIdMapsId.getCustomId()).isEqualTo(testEntityCustomIdMapsId.getEntityCustomId().getCustomId());
         */
    }

    @Test
    @Transactional
    void getAllEntityCustomIdMapsIds() throws Exception {
        // Initialize the database
        insertedEntityCustomIdMapsId = entityCustomIdMapsIdRepository.saveAndFlush(entityCustomIdMapsId);

        // Get all the entityCustomIdMapsIdList
        restEntityCustomIdMapsIdMockMvc
            .perform(get(ENTITY_API_URL + "?sort=customId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].customId").value(hasItem(entityCustomIdMapsId.getCustomId().intValue())));
    }

    @Test
    @Transactional
    void getEntityCustomIdMapsId() throws Exception {
        // Initialize the database
        insertedEntityCustomIdMapsId = entityCustomIdMapsIdRepository.saveAndFlush(entityCustomIdMapsId);

        // Get the entityCustomIdMapsId
        restEntityCustomIdMapsIdMockMvc
            .perform(get(ENTITY_API_URL_ID, entityCustomIdMapsId.getCustomId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.customId").value(entityCustomIdMapsId.getCustomId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingEntityCustomIdMapsId() throws Exception {
        // Get the entityCustomIdMapsId
        restEntityCustomIdMapsIdMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEntityCustomIdMapsId() throws Exception {
        // Initialize the database
        insertedEntityCustomIdMapsId = entityCustomIdMapsIdRepository.saveAndFlush(entityCustomIdMapsId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdMapsId
        EntityCustomIdMapsId updatedEntityCustomIdMapsId = entityCustomIdMapsIdRepository
            .findById(entityCustomIdMapsId.getCustomId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedEntityCustomIdMapsId are not directly saved in db
        em.detach(updatedEntityCustomIdMapsId);

        restEntityCustomIdMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEntityCustomIdMapsId.getCustomId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedEntityCustomIdMapsId))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedEntityCustomIdMapsIdToMatchAllProperties(updatedEntityCustomIdMapsId);
    }

    @Test
    @Transactional
    void putNonExistingEntityCustomIdMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdMapsId.setCustomId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityCustomIdMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityCustomIdMapsId.getCustomId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdMapsId))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEntityCustomIdMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdMapsId.setCustomId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdMapsId))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEntityCustomIdMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdMapsId.setCustomId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdMapsIdMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomIdMapsId)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityCustomIdMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEntityCustomIdMapsIdWithPatch() throws Exception {
        // Initialize the database
        insertedEntityCustomIdMapsId = entityCustomIdMapsIdRepository.saveAndFlush(entityCustomIdMapsId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdMapsId using partial update
        EntityCustomIdMapsId partialUpdatedEntityCustomIdMapsId = new EntityCustomIdMapsId();
        partialUpdatedEntityCustomIdMapsId.setCustomId(entityCustomIdMapsId.getCustomId());

        restEntityCustomIdMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityCustomIdMapsId.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityCustomIdMapsId))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdMapsId in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityCustomIdMapsIdUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedEntityCustomIdMapsId, entityCustomIdMapsId),
            getPersistedEntityCustomIdMapsId(entityCustomIdMapsId)
        );
    }

    @Test
    @Transactional
    void fullUpdateEntityCustomIdMapsIdWithPatch() throws Exception {
        // Initialize the database
        insertedEntityCustomIdMapsId = entityCustomIdMapsIdRepository.saveAndFlush(entityCustomIdMapsId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdMapsId using partial update
        EntityCustomIdMapsId partialUpdatedEntityCustomIdMapsId = new EntityCustomIdMapsId();
        partialUpdatedEntityCustomIdMapsId.setCustomId(entityCustomIdMapsId.getCustomId());

        restEntityCustomIdMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityCustomIdMapsId.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityCustomIdMapsId))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdMapsId in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityCustomIdMapsIdUpdatableFieldsEquals(
            partialUpdatedEntityCustomIdMapsId,
            getPersistedEntityCustomIdMapsId(partialUpdatedEntityCustomIdMapsId)
        );
    }

    @Test
    @Transactional
    void patchNonExistingEntityCustomIdMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdMapsId.setCustomId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityCustomIdMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, entityCustomIdMapsId.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityCustomIdMapsId))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEntityCustomIdMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdMapsId.setCustomId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityCustomIdMapsId))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEntityCustomIdMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdMapsId.setCustomId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdMapsIdMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(entityCustomIdMapsId)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityCustomIdMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEntityCustomIdMapsId() throws Exception {
        // Initialize the database
        insertedEntityCustomIdMapsId = entityCustomIdMapsIdRepository.saveAndFlush(entityCustomIdMapsId);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the entityCustomIdMapsId
        restEntityCustomIdMapsIdMockMvc
            .perform(delete(ENTITY_API_URL_ID, entityCustomIdMapsId.getCustomId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return entityCustomIdMapsIdRepository.count();
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

    protected EntityCustomIdMapsId getPersistedEntityCustomIdMapsId(EntityCustomIdMapsId entityCustomIdMapsId) {
        return entityCustomIdMapsIdRepository.findById(entityCustomIdMapsId.getCustomId()).orElseThrow();
    }

    protected void assertPersistedEntityCustomIdMapsIdToMatchAllProperties(EntityCustomIdMapsId expectedEntityCustomIdMapsId) {
        assertEntityCustomIdMapsIdAllPropertiesEquals(
            expectedEntityCustomIdMapsId,
            getPersistedEntityCustomIdMapsId(expectedEntityCustomIdMapsId)
        );
    }

    protected void assertPersistedEntityCustomIdMapsIdToMatchUpdatableProperties(EntityCustomIdMapsId expectedEntityCustomIdMapsId) {
        assertEntityCustomIdMapsIdAllUpdatablePropertiesEquals(
            expectedEntityCustomIdMapsId,
            getPersistedEntityCustomIdMapsId(expectedEntityCustomIdMapsId)
        );
    }
}
