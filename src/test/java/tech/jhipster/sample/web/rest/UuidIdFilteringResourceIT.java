package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.sample.domain.UuidIdFilteringAsserts.*;
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
import tech.jhipster.sample.domain.UuidIdFilteringRelationship;
import tech.jhipster.sample.repository.UuidIdFilteringRepository;

/**
 * Integration tests for the {@link UuidIdFilteringResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UuidIdFilteringResourceIT {

    private static final String ENTITY_API_URL = "/api/uuid-id-filterings";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{customId}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private UuidIdFilteringRepository uuidIdFilteringRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUuidIdFilteringMockMvc;

    private UuidIdFiltering uuidIdFiltering;

    private UuidIdFiltering insertedUuidIdFiltering;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UuidIdFiltering createEntity() {
        return new UuidIdFiltering();
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UuidIdFiltering createUpdatedEntity() {
        return new UuidIdFiltering();
    }

    @BeforeEach
    void initTest() {
        uuidIdFiltering = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedUuidIdFiltering != null) {
            uuidIdFilteringRepository.delete(insertedUuidIdFiltering);
            insertedUuidIdFiltering = null;
        }
    }

    @Test
    @Transactional
    void createUuidIdFiltering() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the UuidIdFiltering
        var returnedUuidIdFiltering = om.readValue(
            restUuidIdFilteringMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(uuidIdFiltering)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            UuidIdFiltering.class
        );

        // Validate the UuidIdFiltering in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertUuidIdFilteringUpdatableFieldsEquals(returnedUuidIdFiltering, getPersistedUuidIdFiltering(returnedUuidIdFiltering));

        insertedUuidIdFiltering = returnedUuidIdFiltering;
    }

    @Test
    @Transactional
    void createUuidIdFilteringWithExistingId() throws Exception {
        // Create the UuidIdFiltering with an existing ID
        insertedUuidIdFiltering = uuidIdFilteringRepository.saveAndFlush(uuidIdFiltering);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUuidIdFilteringMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(uuidIdFiltering)))
            .andExpect(status().isBadRequest());

        // Validate the UuidIdFiltering in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUuidIdFilterings() throws Exception {
        // Initialize the database
        insertedUuidIdFiltering = uuidIdFilteringRepository.saveAndFlush(uuidIdFiltering);

        // Get all the uuidIdFilteringList
        restUuidIdFilteringMockMvc
            .perform(get(ENTITY_API_URL + "?sort=customId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].customId").value(hasItem(uuidIdFiltering.getCustomId().toString())));
    }

    @Test
    @Transactional
    void getUuidIdFiltering() throws Exception {
        // Initialize the database
        insertedUuidIdFiltering = uuidIdFilteringRepository.saveAndFlush(uuidIdFiltering);

        // Get the uuidIdFiltering
        restUuidIdFilteringMockMvc
            .perform(get(ENTITY_API_URL_ID, uuidIdFiltering.getCustomId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.customId").value(uuidIdFiltering.getCustomId().toString()));
    }

    @Test
    @Transactional
    void getUuidIdFilteringsByIdFiltering() throws Exception {
        // Initialize the database
        insertedUuidIdFiltering = uuidIdFilteringRepository.saveAndFlush(uuidIdFiltering);

        UUID id = uuidIdFiltering.getCustomId();

        defaultUuidIdFilteringFiltering("customId.equals=" + id, "customId.notEquals=" + id);
    }

    @Test
    @Transactional
    void getAllUuidIdFilteringsByManyToManyBackIsEqualToSomething() throws Exception {
        UuidIdFilteringRelationship manyToManyBack;
        if (TestUtil.findAll(em, UuidIdFilteringRelationship.class).isEmpty()) {
            uuidIdFilteringRepository.saveAndFlush(uuidIdFiltering);
            manyToManyBack = UuidIdFilteringRelationshipResourceIT.createEntity();
        } else {
            manyToManyBack = TestUtil.findAll(em, UuidIdFilteringRelationship.class).get(0);
        }
        em.persist(manyToManyBack);
        em.flush();
        uuidIdFiltering.addManyToManyBack(manyToManyBack);
        uuidIdFilteringRepository.saveAndFlush(uuidIdFiltering);
        UUID manyToManyBackId = manyToManyBack.getRelatedId();
        // Get all the uuidIdFilteringList where manyToManyBack equals to manyToManyBackId
        defaultUuidIdFilteringShouldBeFound("manyToManyBackId.equals=" + manyToManyBackId);

        // Get all the uuidIdFilteringList where manyToManyBack equals to UUID.randomUUID()
        defaultUuidIdFilteringShouldNotBeFound("manyToManyBackId.equals=" + UUID.randomUUID());
    }

    private void defaultUuidIdFilteringFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultUuidIdFilteringShouldBeFound(shouldBeFound);
        defaultUuidIdFilteringShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultUuidIdFilteringShouldBeFound(String filter) throws Exception {
        restUuidIdFilteringMockMvc
            .perform(get(ENTITY_API_URL + "?sort=customId,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].customId").value(hasItem(uuidIdFiltering.getCustomId().toString())));

        // Check, that the count call also returns 1
        restUuidIdFilteringMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=customId,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultUuidIdFilteringShouldNotBeFound(String filter) throws Exception {
        restUuidIdFilteringMockMvc
            .perform(get(ENTITY_API_URL + "?sort=customId,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restUuidIdFilteringMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=customId,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingUuidIdFiltering() throws Exception {
        // Get the uuidIdFiltering
        restUuidIdFilteringMockMvc.perform(get(ENTITY_API_URL_ID, UUID.randomUUID().toString())).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingUuidIdFiltering() throws Exception {
        // Initialize the database
        insertedUuidIdFiltering = uuidIdFilteringRepository.saveAndFlush(uuidIdFiltering);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the uuidIdFiltering
        UuidIdFiltering updatedUuidIdFiltering = uuidIdFilteringRepository.findById(uuidIdFiltering.getCustomId()).orElseThrow();
        // Disconnect from session so that the updates on updatedUuidIdFiltering are not directly saved in db
        em.detach(updatedUuidIdFiltering);

        restUuidIdFilteringMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedUuidIdFiltering.getCustomId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedUuidIdFiltering))
            )
            .andExpect(status().isOk());

        // Validate the UuidIdFiltering in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedUuidIdFilteringToMatchAllProperties(updatedUuidIdFiltering);
    }

    @Test
    @Transactional
    void putNonExistingUuidIdFiltering() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        uuidIdFiltering.setCustomId(UUID.randomUUID());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUuidIdFilteringMockMvc
            .perform(
                put(ENTITY_API_URL_ID, uuidIdFiltering.getCustomId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(uuidIdFiltering))
            )
            .andExpect(status().isBadRequest());

        // Validate the UuidIdFiltering in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUuidIdFiltering() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        uuidIdFiltering.setCustomId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUuidIdFilteringMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(uuidIdFiltering))
            )
            .andExpect(status().isBadRequest());

        // Validate the UuidIdFiltering in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUuidIdFiltering() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        uuidIdFiltering.setCustomId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUuidIdFilteringMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(uuidIdFiltering)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UuidIdFiltering in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUuidIdFilteringWithPatch() throws Exception {
        // Initialize the database
        insertedUuidIdFiltering = uuidIdFilteringRepository.saveAndFlush(uuidIdFiltering);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the uuidIdFiltering using partial update
        UuidIdFiltering partialUpdatedUuidIdFiltering = new UuidIdFiltering();
        partialUpdatedUuidIdFiltering.setCustomId(uuidIdFiltering.getCustomId());

        restUuidIdFilteringMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUuidIdFiltering.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedUuidIdFiltering))
            )
            .andExpect(status().isOk());

        // Validate the UuidIdFiltering in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertUuidIdFilteringUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedUuidIdFiltering, uuidIdFiltering),
            getPersistedUuidIdFiltering(uuidIdFiltering)
        );
    }

    @Test
    @Transactional
    void fullUpdateUuidIdFilteringWithPatch() throws Exception {
        // Initialize the database
        insertedUuidIdFiltering = uuidIdFilteringRepository.saveAndFlush(uuidIdFiltering);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the uuidIdFiltering using partial update
        UuidIdFiltering partialUpdatedUuidIdFiltering = new UuidIdFiltering();
        partialUpdatedUuidIdFiltering.setCustomId(uuidIdFiltering.getCustomId());

        restUuidIdFilteringMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUuidIdFiltering.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedUuidIdFiltering))
            )
            .andExpect(status().isOk());

        // Validate the UuidIdFiltering in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertUuidIdFilteringUpdatableFieldsEquals(
            partialUpdatedUuidIdFiltering,
            getPersistedUuidIdFiltering(partialUpdatedUuidIdFiltering)
        );
    }

    @Test
    @Transactional
    void patchNonExistingUuidIdFiltering() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        uuidIdFiltering.setCustomId(UUID.randomUUID());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUuidIdFilteringMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, uuidIdFiltering.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(uuidIdFiltering))
            )
            .andExpect(status().isBadRequest());

        // Validate the UuidIdFiltering in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUuidIdFiltering() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        uuidIdFiltering.setCustomId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUuidIdFilteringMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(uuidIdFiltering))
            )
            .andExpect(status().isBadRequest());

        // Validate the UuidIdFiltering in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUuidIdFiltering() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        uuidIdFiltering.setCustomId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUuidIdFilteringMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(uuidIdFiltering)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UuidIdFiltering in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUuidIdFiltering() throws Exception {
        // Initialize the database
        insertedUuidIdFiltering = uuidIdFilteringRepository.saveAndFlush(uuidIdFiltering);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the uuidIdFiltering
        restUuidIdFilteringMockMvc
            .perform(delete(ENTITY_API_URL_ID, uuidIdFiltering.getCustomId().toString()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return uuidIdFilteringRepository.count();
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

    protected UuidIdFiltering getPersistedUuidIdFiltering(UuidIdFiltering uuidIdFiltering) {
        return uuidIdFilteringRepository.findById(uuidIdFiltering.getCustomId()).orElseThrow();
    }

    protected void assertPersistedUuidIdFilteringToMatchAllProperties(UuidIdFiltering expectedUuidIdFiltering) {
        assertUuidIdFilteringAllPropertiesEquals(expectedUuidIdFiltering, getPersistedUuidIdFiltering(expectedUuidIdFiltering));
    }

    protected void assertPersistedUuidIdFilteringToMatchUpdatableProperties(UuidIdFiltering expectedUuidIdFiltering) {
        assertUuidIdFilteringAllUpdatablePropertiesEquals(expectedUuidIdFiltering, getPersistedUuidIdFiltering(expectedUuidIdFiltering));
    }
}
