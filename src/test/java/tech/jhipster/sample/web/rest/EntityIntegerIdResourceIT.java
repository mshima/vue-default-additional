package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.sample.domain.EntityIntegerIdAsserts.*;
import static tech.jhipster.sample.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
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
import tech.jhipster.sample.domain.EntityIntegerId;
import tech.jhipster.sample.repository.EntityIntegerIdRepository;

/**
 * Integration tests for the {@link EntityIntegerIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EntityIntegerIdResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/entity-integer-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EntityIntegerIdRepository entityIntegerIdRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntityIntegerIdMockMvc;

    private EntityIntegerId entityIntegerId;

    private EntityIntegerId insertedEntityIntegerId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityIntegerId createEntity() {
        return new EntityIntegerId().name(DEFAULT_NAME);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityIntegerId createUpdatedEntity() {
        return new EntityIntegerId().name(UPDATED_NAME);
    }

    @BeforeEach
    void initTest() {
        entityIntegerId = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedEntityIntegerId != null) {
            entityIntegerIdRepository.delete(insertedEntityIntegerId);
            insertedEntityIntegerId = null;
        }
    }

    @Test
    @Transactional
    void createEntityIntegerId() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the EntityIntegerId
        var returnedEntityIntegerId = om.readValue(
            restEntityIntegerIdMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityIntegerId)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            EntityIntegerId.class
        );

        // Validate the EntityIntegerId in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertEntityIntegerIdUpdatableFieldsEquals(returnedEntityIntegerId, getPersistedEntityIntegerId(returnedEntityIntegerId));

        insertedEntityIntegerId = returnedEntityIntegerId;
    }

    @Test
    @Transactional
    void createEntityIntegerIdWithExistingId() throws Exception {
        // Create the EntityIntegerId with an existing ID
        entityIntegerId.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityIntegerIdMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityIntegerId)))
            .andExpect(status().isBadRequest());

        // Validate the EntityIntegerId in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEntityIntegerIds() throws Exception {
        // Initialize the database
        insertedEntityIntegerId = entityIntegerIdRepository.saveAndFlush(entityIntegerId);

        // Get all the entityIntegerIdList
        restEntityIntegerIdMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityIntegerId.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getEntityIntegerId() throws Exception {
        // Initialize the database
        insertedEntityIntegerId = entityIntegerIdRepository.saveAndFlush(entityIntegerId);

        // Get the entityIntegerId
        restEntityIntegerIdMockMvc
            .perform(get(ENTITY_API_URL_ID, entityIntegerId.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entityIntegerId.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingEntityIntegerId() throws Exception {
        // Get the entityIntegerId
        restEntityIntegerIdMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEntityIntegerId() throws Exception {
        // Initialize the database
        insertedEntityIntegerId = entityIntegerIdRepository.saveAndFlush(entityIntegerId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityIntegerId
        EntityIntegerId updatedEntityIntegerId = entityIntegerIdRepository.findById(entityIntegerId.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedEntityIntegerId are not directly saved in db
        em.detach(updatedEntityIntegerId);
        updatedEntityIntegerId.name(UPDATED_NAME);

        restEntityIntegerIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEntityIntegerId.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedEntityIntegerId))
            )
            .andExpect(status().isOk());

        // Validate the EntityIntegerId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedEntityIntegerIdToMatchAllProperties(updatedEntityIntegerId);
    }

    @Test
    @Transactional
    void putNonExistingEntityIntegerId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityIntegerId.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityIntegerIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityIntegerId.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityIntegerId))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityIntegerId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEntityIntegerId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityIntegerId.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityIntegerIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityIntegerId))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityIntegerId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEntityIntegerId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityIntegerId.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityIntegerIdMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityIntegerId)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityIntegerId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEntityIntegerIdWithPatch() throws Exception {
        // Initialize the database
        insertedEntityIntegerId = entityIntegerIdRepository.saveAndFlush(entityIntegerId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityIntegerId using partial update
        EntityIntegerId partialUpdatedEntityIntegerId = new EntityIntegerId();
        partialUpdatedEntityIntegerId.setId(entityIntegerId.getId());

        restEntityIntegerIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityIntegerId.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityIntegerId))
            )
            .andExpect(status().isOk());

        // Validate the EntityIntegerId in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityIntegerIdUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedEntityIntegerId, entityIntegerId),
            getPersistedEntityIntegerId(entityIntegerId)
        );
    }

    @Test
    @Transactional
    void fullUpdateEntityIntegerIdWithPatch() throws Exception {
        // Initialize the database
        insertedEntityIntegerId = entityIntegerIdRepository.saveAndFlush(entityIntegerId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityIntegerId using partial update
        EntityIntegerId partialUpdatedEntityIntegerId = new EntityIntegerId();
        partialUpdatedEntityIntegerId.setId(entityIntegerId.getId());

        partialUpdatedEntityIntegerId.name(UPDATED_NAME);

        restEntityIntegerIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityIntegerId.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityIntegerId))
            )
            .andExpect(status().isOk());

        // Validate the EntityIntegerId in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityIntegerIdUpdatableFieldsEquals(
            partialUpdatedEntityIntegerId,
            getPersistedEntityIntegerId(partialUpdatedEntityIntegerId)
        );
    }

    @Test
    @Transactional
    void patchNonExistingEntityIntegerId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityIntegerId.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityIntegerIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, entityIntegerId.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityIntegerId))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityIntegerId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEntityIntegerId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityIntegerId.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityIntegerIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityIntegerId))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityIntegerId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEntityIntegerId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityIntegerId.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityIntegerIdMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(entityIntegerId)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityIntegerId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEntityIntegerId() throws Exception {
        // Initialize the database
        insertedEntityIntegerId = entityIntegerIdRepository.saveAndFlush(entityIntegerId);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the entityIntegerId
        restEntityIntegerIdMockMvc
            .perform(delete(ENTITY_API_URL_ID, entityIntegerId.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return entityIntegerIdRepository.count();
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

    protected EntityIntegerId getPersistedEntityIntegerId(EntityIntegerId entityIntegerId) {
        return entityIntegerIdRepository.findById(entityIntegerId.getId()).orElseThrow();
    }

    protected void assertPersistedEntityIntegerIdToMatchAllProperties(EntityIntegerId expectedEntityIntegerId) {
        assertEntityIntegerIdAllPropertiesEquals(expectedEntityIntegerId, getPersistedEntityIntegerId(expectedEntityIntegerId));
    }

    protected void assertPersistedEntityIntegerIdToMatchUpdatableProperties(EntityIntegerId expectedEntityIntegerId) {
        assertEntityIntegerIdAllUpdatablePropertiesEquals(expectedEntityIntegerId, getPersistedEntityIntegerId(expectedEntityIntegerId));
    }
}
