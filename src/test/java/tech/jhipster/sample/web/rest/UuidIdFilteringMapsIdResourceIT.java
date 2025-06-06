package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.sample.domain.UuidIdFilteringMapsIdAsserts.*;
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
import tech.jhipster.sample.domain.UuidIdFiltering;
import tech.jhipster.sample.domain.UuidIdFilteringMapsId;
import tech.jhipster.sample.domain.UuidIdFilteringRelationship;
import tech.jhipster.sample.repository.UuidIdFilteringMapsIdRepository;

/**
 * Integration tests for the {@link UuidIdFilteringMapsIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UuidIdFilteringMapsIdResourceIT {

    private static final String ENTITY_API_URL = "/api/uuid-id-filtering-maps-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{customId}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private UuidIdFilteringMapsIdRepository uuidIdFilteringMapsIdRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUuidIdFilteringMapsIdMockMvc;

    private UuidIdFilteringMapsId uuidIdFilteringMapsId;

    private UuidIdFilteringMapsId insertedUuidIdFilteringMapsId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UuidIdFilteringMapsId createEntity(EntityManager em) {
        UuidIdFilteringMapsId uuidIdFilteringMapsId = new UuidIdFilteringMapsId();
        // Add required entity
        UuidIdFiltering uuidIdFiltering;
        if (TestUtil.findAll(em, UuidIdFiltering.class).isEmpty()) {
            uuidIdFiltering = UuidIdFilteringResourceIT.createEntity();
            em.persist(uuidIdFiltering);
            em.flush();
        } else {
            uuidIdFiltering = TestUtil.findAll(em, UuidIdFiltering.class).get(0);
        }
        uuidIdFilteringMapsId.setUuidIdFiltering(uuidIdFiltering);
        return uuidIdFilteringMapsId;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UuidIdFilteringMapsId createUpdatedEntity(EntityManager em) {
        UuidIdFilteringMapsId updatedUuidIdFilteringMapsId = new UuidIdFilteringMapsId();
        // Add required entity
        UuidIdFiltering uuidIdFiltering;
        uuidIdFiltering = UuidIdFilteringResourceIT.createUpdatedEntity();
        em.persist(uuidIdFiltering);
        em.flush();
        updatedUuidIdFilteringMapsId.setUuidIdFiltering(uuidIdFiltering);
        return updatedUuidIdFilteringMapsId;
    }

    @BeforeEach
    void initTest() {
        uuidIdFilteringMapsId = createEntity(em);
    }

    @AfterEach
    void cleanup() {
        if (insertedUuidIdFilteringMapsId != null) {
            uuidIdFilteringMapsIdRepository.delete(insertedUuidIdFilteringMapsId);
            insertedUuidIdFilteringMapsId = null;
        }
    }

    @Test
    @Transactional
    void createUuidIdFilteringMapsId() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the UuidIdFilteringMapsId
        var returnedUuidIdFilteringMapsId = om.readValue(
            restUuidIdFilteringMapsIdMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(uuidIdFilteringMapsId)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            UuidIdFilteringMapsId.class
        );

        // Validate the UuidIdFilteringMapsId in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertUuidIdFilteringMapsIdUpdatableFieldsEquals(
            returnedUuidIdFilteringMapsId,
            getPersistedUuidIdFilteringMapsId(returnedUuidIdFilteringMapsId)
        );

        assertUuidIdFilteringMapsIdMapsIdRelationshipPersistedValue(uuidIdFilteringMapsId, returnedUuidIdFilteringMapsId);

        insertedUuidIdFilteringMapsId = returnedUuidIdFilteringMapsId;
    }

    @Test
    @Transactional
    void createUuidIdFilteringMapsIdWithExistingId() throws Exception {
        // Create the UuidIdFilteringMapsId with an existing ID
        insertedUuidIdFilteringMapsId = uuidIdFilteringMapsIdRepository.saveAndFlush(uuidIdFilteringMapsId);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUuidIdFilteringMapsIdMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(uuidIdFilteringMapsId)))
            .andExpect(status().isBadRequest());

        // Validate the UuidIdFilteringMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void updateUuidIdFilteringMapsIdMapsIdAssociationWithNewId() throws Exception {
        // Initialize the database
        insertedUuidIdFilteringMapsId = uuidIdFilteringMapsIdRepository.saveAndFlush(uuidIdFilteringMapsId);
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Add a new parent entity
        UuidIdFiltering uuidIdFiltering = UuidIdFilteringResourceIT.createUpdatedEntity();
        em.persist(uuidIdFiltering);
        em.flush();

        // Load the uuidIdFilteringMapsId
        UuidIdFilteringMapsId updatedUuidIdFilteringMapsId = uuidIdFilteringMapsIdRepository
            .findById(uuidIdFilteringMapsId.getCustomId())
            .orElseThrow();
        assertThat(updatedUuidIdFilteringMapsId).isNotNull();
        // Disconnect from session so that the updates on updatedUuidIdFilteringMapsId are not directly saved in db
        em.detach(updatedUuidIdFilteringMapsId);

        // Update the UuidIdFiltering with new association value
        updatedUuidIdFilteringMapsId.setUuidIdFiltering(uuidIdFiltering);

        // Update the entity
        restUuidIdFilteringMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedUuidIdFilteringMapsId.getCustomId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedUuidIdFilteringMapsId))
            )
            .andExpect(status().isOk());

        // Validate the UuidIdFilteringMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        /**
         * Validate the id for MapsId, the ids must be same
         * Uncomment the following line for assertion. However, please note that there is a known issue and uncommenting will fail the test.
         * Please look at https://github.com/jhipster/generator-jhipster/issues/9100. You can modify this test as necessary.
         * assertThat(testUuidIdFilteringMapsId.getCustomId()).isEqualTo(testUuidIdFilteringMapsId.getUuidIdFiltering().getCustomId());
         */
    }

    @Test
    @Transactional
    void getAllUuidIdFilteringMapsIds() throws Exception {
        // Initialize the database
        insertedUuidIdFilteringMapsId = uuidIdFilteringMapsIdRepository.saveAndFlush(uuidIdFilteringMapsId);

        // Get all the uuidIdFilteringMapsIdList
        restUuidIdFilteringMapsIdMockMvc
            .perform(get(ENTITY_API_URL + "?sort=customId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].customId").value(hasItem(uuidIdFilteringMapsId.getCustomId().toString())));
    }

    @Test
    @Transactional
    void getUuidIdFilteringMapsId() throws Exception {
        // Initialize the database
        insertedUuidIdFilteringMapsId = uuidIdFilteringMapsIdRepository.saveAndFlush(uuidIdFilteringMapsId);

        // Get the uuidIdFilteringMapsId
        restUuidIdFilteringMapsIdMockMvc
            .perform(get(ENTITY_API_URL_ID, uuidIdFilteringMapsId.getCustomId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.customId").value(uuidIdFilteringMapsId.getCustomId().toString()));
    }

    @Test
    @Transactional
    void getUuidIdFilteringMapsIdsByIdFiltering() throws Exception {
        // Initialize the database
        insertedUuidIdFilteringMapsId = uuidIdFilteringMapsIdRepository.saveAndFlush(uuidIdFilteringMapsId);

        UUID id = uuidIdFilteringMapsId.getCustomId();

        defaultUuidIdFilteringMapsIdFiltering("customId.equals=" + id, "customId.notEquals=" + id);
    }

    @Test
    @Transactional
    void getAllUuidIdFilteringMapsIdsByUuidIdFilteringIsEqualToSomething() throws Exception {
        // Get already existing entity
        UuidIdFiltering uuidIdFiltering = uuidIdFilteringMapsId.getUuidIdFiltering();
        uuidIdFilteringMapsIdRepository.saveAndFlush(uuidIdFilteringMapsId);
        UUID uuidIdFilteringId = uuidIdFiltering.getCustomId();
        // Get all the uuidIdFilteringMapsIdList where uuidIdFiltering equals to uuidIdFilteringId
        defaultUuidIdFilteringMapsIdShouldBeFound("uuidIdFilteringId.equals=" + uuidIdFilteringId);

        // Get all the uuidIdFilteringMapsIdList where uuidIdFiltering equals to UUID.randomUUID()
        defaultUuidIdFilteringMapsIdShouldNotBeFound("uuidIdFilteringId.equals=" + UUID.randomUUID());
    }

    @Test
    @Transactional
    void getAllUuidIdFilteringMapsIdsByManyToManyMapsIdBackIsEqualToSomething() throws Exception {
        UuidIdFilteringRelationship manyToManyMapsIdBack;
        if (TestUtil.findAll(em, UuidIdFilteringRelationship.class).isEmpty()) {
            uuidIdFilteringMapsIdRepository.saveAndFlush(uuidIdFilteringMapsId);
            manyToManyMapsIdBack = UuidIdFilteringRelationshipResourceIT.createEntity();
        } else {
            manyToManyMapsIdBack = TestUtil.findAll(em, UuidIdFilteringRelationship.class).get(0);
        }
        em.persist(manyToManyMapsIdBack);
        em.flush();
        uuidIdFilteringMapsId.addManyToManyMapsIdBack(manyToManyMapsIdBack);
        uuidIdFilteringMapsIdRepository.saveAndFlush(uuidIdFilteringMapsId);
        UUID manyToManyMapsIdBackId = manyToManyMapsIdBack.getRelatedId();
        // Get all the uuidIdFilteringMapsIdList where manyToManyMapsIdBack equals to manyToManyMapsIdBackId
        defaultUuidIdFilteringMapsIdShouldBeFound("manyToManyMapsIdBackId.equals=" + manyToManyMapsIdBackId);

        // Get all the uuidIdFilteringMapsIdList where manyToManyMapsIdBack equals to UUID.randomUUID()
        defaultUuidIdFilteringMapsIdShouldNotBeFound("manyToManyMapsIdBackId.equals=" + UUID.randomUUID());
    }

    private void defaultUuidIdFilteringMapsIdFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultUuidIdFilteringMapsIdShouldBeFound(shouldBeFound);
        defaultUuidIdFilteringMapsIdShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultUuidIdFilteringMapsIdShouldBeFound(String filter) throws Exception {
        restUuidIdFilteringMapsIdMockMvc
            .perform(get(ENTITY_API_URL + "?sort=customId,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].customId").value(hasItem(uuidIdFilteringMapsId.getCustomId().toString())));

        // Check, that the count call also returns 1
        restUuidIdFilteringMapsIdMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=customId,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultUuidIdFilteringMapsIdShouldNotBeFound(String filter) throws Exception {
        restUuidIdFilteringMapsIdMockMvc
            .perform(get(ENTITY_API_URL + "?sort=customId,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restUuidIdFilteringMapsIdMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=customId,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingUuidIdFilteringMapsId() throws Exception {
        // Get the uuidIdFilteringMapsId
        restUuidIdFilteringMapsIdMockMvc.perform(get(ENTITY_API_URL_ID, UUID.randomUUID().toString())).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingUuidIdFilteringMapsId() throws Exception {
        // Initialize the database
        insertedUuidIdFilteringMapsId = uuidIdFilteringMapsIdRepository.saveAndFlush(uuidIdFilteringMapsId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the uuidIdFilteringMapsId
        UuidIdFilteringMapsId updatedUuidIdFilteringMapsId = uuidIdFilteringMapsIdRepository
            .findById(uuidIdFilteringMapsId.getCustomId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedUuidIdFilteringMapsId are not directly saved in db
        em.detach(updatedUuidIdFilteringMapsId);

        restUuidIdFilteringMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedUuidIdFilteringMapsId.getCustomId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedUuidIdFilteringMapsId))
            )
            .andExpect(status().isOk());

        // Validate the UuidIdFilteringMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedUuidIdFilteringMapsIdToMatchAllProperties(updatedUuidIdFilteringMapsId);
    }

    @Test
    @Transactional
    void putNonExistingUuidIdFilteringMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        uuidIdFilteringMapsId.setCustomId(UUID.randomUUID());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUuidIdFilteringMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, uuidIdFilteringMapsId.getCustomId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(uuidIdFilteringMapsId))
            )
            .andExpect(status().isBadRequest());

        // Validate the UuidIdFilteringMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUuidIdFilteringMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        uuidIdFilteringMapsId.setCustomId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUuidIdFilteringMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(uuidIdFilteringMapsId))
            )
            .andExpect(status().isBadRequest());

        // Validate the UuidIdFilteringMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUuidIdFilteringMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        uuidIdFilteringMapsId.setCustomId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUuidIdFilteringMapsIdMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(uuidIdFilteringMapsId)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UuidIdFilteringMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUuidIdFilteringMapsIdWithPatch() throws Exception {
        // Initialize the database
        insertedUuidIdFilteringMapsId = uuidIdFilteringMapsIdRepository.saveAndFlush(uuidIdFilteringMapsId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the uuidIdFilteringMapsId using partial update
        UuidIdFilteringMapsId partialUpdatedUuidIdFilteringMapsId = new UuidIdFilteringMapsId();
        partialUpdatedUuidIdFilteringMapsId.setCustomId(uuidIdFilteringMapsId.getCustomId());

        restUuidIdFilteringMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUuidIdFilteringMapsId.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedUuidIdFilteringMapsId))
            )
            .andExpect(status().isOk());

        // Validate the UuidIdFilteringMapsId in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertUuidIdFilteringMapsIdUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedUuidIdFilteringMapsId, uuidIdFilteringMapsId),
            getPersistedUuidIdFilteringMapsId(uuidIdFilteringMapsId)
        );
    }

    @Test
    @Transactional
    void fullUpdateUuidIdFilteringMapsIdWithPatch() throws Exception {
        // Initialize the database
        insertedUuidIdFilteringMapsId = uuidIdFilteringMapsIdRepository.saveAndFlush(uuidIdFilteringMapsId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the uuidIdFilteringMapsId using partial update
        UuidIdFilteringMapsId partialUpdatedUuidIdFilteringMapsId = new UuidIdFilteringMapsId();
        partialUpdatedUuidIdFilteringMapsId.setCustomId(uuidIdFilteringMapsId.getCustomId());

        restUuidIdFilteringMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUuidIdFilteringMapsId.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedUuidIdFilteringMapsId))
            )
            .andExpect(status().isOk());

        // Validate the UuidIdFilteringMapsId in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertUuidIdFilteringMapsIdUpdatableFieldsEquals(
            partialUpdatedUuidIdFilteringMapsId,
            getPersistedUuidIdFilteringMapsId(partialUpdatedUuidIdFilteringMapsId)
        );
    }

    @Test
    @Transactional
    void patchNonExistingUuidIdFilteringMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        uuidIdFilteringMapsId.setCustomId(UUID.randomUUID());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUuidIdFilteringMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, uuidIdFilteringMapsId.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(uuidIdFilteringMapsId))
            )
            .andExpect(status().isBadRequest());

        // Validate the UuidIdFilteringMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUuidIdFilteringMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        uuidIdFilteringMapsId.setCustomId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUuidIdFilteringMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(uuidIdFilteringMapsId))
            )
            .andExpect(status().isBadRequest());

        // Validate the UuidIdFilteringMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUuidIdFilteringMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        uuidIdFilteringMapsId.setCustomId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUuidIdFilteringMapsIdMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(uuidIdFilteringMapsId)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UuidIdFilteringMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUuidIdFilteringMapsId() throws Exception {
        // Initialize the database
        insertedUuidIdFilteringMapsId = uuidIdFilteringMapsIdRepository.saveAndFlush(uuidIdFilteringMapsId);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the uuidIdFilteringMapsId
        restUuidIdFilteringMapsIdMockMvc
            .perform(delete(ENTITY_API_URL_ID, uuidIdFilteringMapsId.getCustomId().toString()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return uuidIdFilteringMapsIdRepository.count();
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

    protected UuidIdFilteringMapsId getPersistedUuidIdFilteringMapsId(UuidIdFilteringMapsId uuidIdFilteringMapsId) {
        return uuidIdFilteringMapsIdRepository.findById(uuidIdFilteringMapsId.getCustomId()).orElseThrow();
    }

    protected void assertPersistedUuidIdFilteringMapsIdToMatchAllProperties(UuidIdFilteringMapsId expectedUuidIdFilteringMapsId) {
        assertUuidIdFilteringMapsIdAllPropertiesEquals(
            expectedUuidIdFilteringMapsId,
            getPersistedUuidIdFilteringMapsId(expectedUuidIdFilteringMapsId)
        );
    }

    protected void assertPersistedUuidIdFilteringMapsIdToMatchUpdatableProperties(UuidIdFilteringMapsId expectedUuidIdFilteringMapsId) {
        assertUuidIdFilteringMapsIdAllUpdatablePropertiesEquals(
            expectedUuidIdFilteringMapsId,
            getPersistedUuidIdFilteringMapsId(expectedUuidIdFilteringMapsId)
        );
    }
}
