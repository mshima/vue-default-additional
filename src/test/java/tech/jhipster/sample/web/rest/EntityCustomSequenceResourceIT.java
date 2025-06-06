package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.sample.domain.EntityCustomSequenceAsserts.*;
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
import tech.jhipster.sample.domain.EntityCustomSequence;
import tech.jhipster.sample.repository.EntityCustomSequenceRepository;

/**
 * Integration tests for the {@link EntityCustomSequenceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EntityCustomSequenceResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/entity-custom-sequences";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EntityCustomSequenceRepository entityCustomSequenceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntityCustomSequenceMockMvc;

    private EntityCustomSequence entityCustomSequence;

    private EntityCustomSequence insertedEntityCustomSequence;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityCustomSequence createEntity() {
        return new EntityCustomSequence().name(DEFAULT_NAME);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityCustomSequence createUpdatedEntity() {
        return new EntityCustomSequence().name(UPDATED_NAME);
    }

    @BeforeEach
    void initTest() {
        entityCustomSequence = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedEntityCustomSequence != null) {
            entityCustomSequenceRepository.delete(insertedEntityCustomSequence);
            insertedEntityCustomSequence = null;
        }
    }

    @Test
    @Transactional
    void createEntityCustomSequence() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the EntityCustomSequence
        var returnedEntityCustomSequence = om.readValue(
            restEntityCustomSequenceMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomSequence)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            EntityCustomSequence.class
        );

        // Validate the EntityCustomSequence in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertEntityCustomSequenceUpdatableFieldsEquals(
            returnedEntityCustomSequence,
            getPersistedEntityCustomSequence(returnedEntityCustomSequence)
        );

        insertedEntityCustomSequence = returnedEntityCustomSequence;
    }

    @Test
    @Transactional
    void createEntityCustomSequenceWithExistingId() throws Exception {
        // Create the EntityCustomSequence with an existing ID
        entityCustomSequence.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityCustomSequenceMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomSequence)))
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomSequence in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEntityCustomSequences() throws Exception {
        // Initialize the database
        insertedEntityCustomSequence = entityCustomSequenceRepository.saveAndFlush(entityCustomSequence);

        // Get all the entityCustomSequenceList
        restEntityCustomSequenceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityCustomSequence.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getEntityCustomSequence() throws Exception {
        // Initialize the database
        insertedEntityCustomSequence = entityCustomSequenceRepository.saveAndFlush(entityCustomSequence);

        // Get the entityCustomSequence
        restEntityCustomSequenceMockMvc
            .perform(get(ENTITY_API_URL_ID, entityCustomSequence.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entityCustomSequence.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingEntityCustomSequence() throws Exception {
        // Get the entityCustomSequence
        restEntityCustomSequenceMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEntityCustomSequence() throws Exception {
        // Initialize the database
        insertedEntityCustomSequence = entityCustomSequenceRepository.saveAndFlush(entityCustomSequence);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomSequence
        EntityCustomSequence updatedEntityCustomSequence = entityCustomSequenceRepository
            .findById(entityCustomSequence.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedEntityCustomSequence are not directly saved in db
        em.detach(updatedEntityCustomSequence);
        updatedEntityCustomSequence.name(UPDATED_NAME);

        restEntityCustomSequenceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEntityCustomSequence.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedEntityCustomSequence))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomSequence in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedEntityCustomSequenceToMatchAllProperties(updatedEntityCustomSequence);
    }

    @Test
    @Transactional
    void putNonExistingEntityCustomSequence() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomSequence.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityCustomSequenceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityCustomSequence.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomSequence))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomSequence in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEntityCustomSequence() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomSequence.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomSequenceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomSequence))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomSequence in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEntityCustomSequence() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomSequence.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomSequenceMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomSequence)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityCustomSequence in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEntityCustomSequenceWithPatch() throws Exception {
        // Initialize the database
        insertedEntityCustomSequence = entityCustomSequenceRepository.saveAndFlush(entityCustomSequence);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomSequence using partial update
        EntityCustomSequence partialUpdatedEntityCustomSequence = new EntityCustomSequence();
        partialUpdatedEntityCustomSequence.setId(entityCustomSequence.getId());

        partialUpdatedEntityCustomSequence.name(UPDATED_NAME);

        restEntityCustomSequenceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityCustomSequence.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityCustomSequence))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomSequence in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityCustomSequenceUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedEntityCustomSequence, entityCustomSequence),
            getPersistedEntityCustomSequence(entityCustomSequence)
        );
    }

    @Test
    @Transactional
    void fullUpdateEntityCustomSequenceWithPatch() throws Exception {
        // Initialize the database
        insertedEntityCustomSequence = entityCustomSequenceRepository.saveAndFlush(entityCustomSequence);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomSequence using partial update
        EntityCustomSequence partialUpdatedEntityCustomSequence = new EntityCustomSequence();
        partialUpdatedEntityCustomSequence.setId(entityCustomSequence.getId());

        partialUpdatedEntityCustomSequence.name(UPDATED_NAME);

        restEntityCustomSequenceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityCustomSequence.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityCustomSequence))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomSequence in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityCustomSequenceUpdatableFieldsEquals(
            partialUpdatedEntityCustomSequence,
            getPersistedEntityCustomSequence(partialUpdatedEntityCustomSequence)
        );
    }

    @Test
    @Transactional
    void patchNonExistingEntityCustomSequence() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomSequence.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityCustomSequenceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, entityCustomSequence.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityCustomSequence))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomSequence in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEntityCustomSequence() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomSequence.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomSequenceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityCustomSequence))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomSequence in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEntityCustomSequence() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomSequence.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomSequenceMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(entityCustomSequence)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityCustomSequence in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEntityCustomSequence() throws Exception {
        // Initialize the database
        insertedEntityCustomSequence = entityCustomSequenceRepository.saveAndFlush(entityCustomSequence);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the entityCustomSequence
        restEntityCustomSequenceMockMvc
            .perform(delete(ENTITY_API_URL_ID, entityCustomSequence.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return entityCustomSequenceRepository.count();
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

    protected EntityCustomSequence getPersistedEntityCustomSequence(EntityCustomSequence entityCustomSequence) {
        return entityCustomSequenceRepository.findById(entityCustomSequence.getId()).orElseThrow();
    }

    protected void assertPersistedEntityCustomSequenceToMatchAllProperties(EntityCustomSequence expectedEntityCustomSequence) {
        assertEntityCustomSequenceAllPropertiesEquals(
            expectedEntityCustomSequence,
            getPersistedEntityCustomSequence(expectedEntityCustomSequence)
        );
    }

    protected void assertPersistedEntityCustomSequenceToMatchUpdatableProperties(EntityCustomSequence expectedEntityCustomSequence) {
        assertEntityCustomSequenceAllUpdatablePropertiesEquals(
            expectedEntityCustomSequence,
            getPersistedEntityCustomSequence(expectedEntityCustomSequence)
        );
    }
}
