package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.sample.domain.EntityCustomIdDTOMapsIdAsserts.*;
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
import tech.jhipster.sample.domain.EntityCustomIdDTO;
import tech.jhipster.sample.domain.EntityCustomIdDTOMapsId;
import tech.jhipster.sample.repository.EntityCustomIdDTOMapsIdRepository;
import tech.jhipster.sample.service.dto.EntityCustomIdDTOMapsIdDTO;
import tech.jhipster.sample.service.mapper.EntityCustomIdDTOMapsIdMapper;

/**
 * Integration tests for the {@link EntityCustomIdDTOMapsIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EntityCustomIdDTOMapsIdResourceIT {

    private static final String ENTITY_API_URL = "/api/entity-custom-id-dto-maps-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{customId}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EntityCustomIdDTOMapsIdRepository entityCustomIdDTOMapsIdRepository;

    @Autowired
    private EntityCustomIdDTOMapsIdMapper entityCustomIdDTOMapsIdMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntityCustomIdDTOMapsIdMockMvc;

    private EntityCustomIdDTOMapsId entityCustomIdDTOMapsId;

    private EntityCustomIdDTOMapsId insertedEntityCustomIdDTOMapsId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityCustomIdDTOMapsId createEntity(EntityManager em) {
        EntityCustomIdDTOMapsId entityCustomIdDTOMapsId = new EntityCustomIdDTOMapsId();
        // Add required entity
        EntityCustomIdDTO entityCustomIdDTO;
        if (TestUtil.findAll(em, EntityCustomIdDTO.class).isEmpty()) {
            entityCustomIdDTO = EntityCustomIdDTOResourceIT.createEntity();
            em.persist(entityCustomIdDTO);
            em.flush();
        } else {
            entityCustomIdDTO = TestUtil.findAll(em, EntityCustomIdDTO.class).get(0);
        }
        entityCustomIdDTOMapsId.setEntityCustomIdDTO(entityCustomIdDTO);
        return entityCustomIdDTOMapsId;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityCustomIdDTOMapsId createUpdatedEntity(EntityManager em) {
        EntityCustomIdDTOMapsId updatedEntityCustomIdDTOMapsId = new EntityCustomIdDTOMapsId();
        // Add required entity
        EntityCustomIdDTO entityCustomIdDTO;
        entityCustomIdDTO = EntityCustomIdDTOResourceIT.createUpdatedEntity();
        em.persist(entityCustomIdDTO);
        em.flush();
        updatedEntityCustomIdDTOMapsId.setEntityCustomIdDTO(entityCustomIdDTO);
        return updatedEntityCustomIdDTOMapsId;
    }

    @BeforeEach
    void initTest() {
        entityCustomIdDTOMapsId = createEntity(em);
    }

    @AfterEach
    void cleanup() {
        if (insertedEntityCustomIdDTOMapsId != null) {
            entityCustomIdDTOMapsIdRepository.delete(insertedEntityCustomIdDTOMapsId);
            insertedEntityCustomIdDTOMapsId = null;
        }
    }

    @Test
    @Transactional
    void createEntityCustomIdDTOMapsId() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the EntityCustomIdDTOMapsId
        EntityCustomIdDTOMapsIdDTO entityCustomIdDTOMapsIdDTO = entityCustomIdDTOMapsIdMapper.toDto(entityCustomIdDTOMapsId);
        var returnedEntityCustomIdDTOMapsIdDTO = om.readValue(
            restEntityCustomIdDTOMapsIdMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomIdDTOMapsIdDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            EntityCustomIdDTOMapsIdDTO.class
        );

        // Validate the EntityCustomIdDTOMapsId in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedEntityCustomIdDTOMapsId = entityCustomIdDTOMapsIdMapper.toEntity(returnedEntityCustomIdDTOMapsIdDTO);
        assertEntityCustomIdDTOMapsIdUpdatableFieldsEquals(
            returnedEntityCustomIdDTOMapsId,
            getPersistedEntityCustomIdDTOMapsId(returnedEntityCustomIdDTOMapsId)
        );

        assertEntityCustomIdDTOMapsIdMapsIdRelationshipPersistedValue(entityCustomIdDTOMapsId, returnedEntityCustomIdDTOMapsId);

        insertedEntityCustomIdDTOMapsId = returnedEntityCustomIdDTOMapsId;
    }

    @Test
    @Transactional
    void createEntityCustomIdDTOMapsIdWithExistingId() throws Exception {
        // Create the EntityCustomIdDTOMapsId with an existing ID
        entityCustomIdDTOMapsId.setCustomId(1L);
        EntityCustomIdDTOMapsIdDTO entityCustomIdDTOMapsIdDTO = entityCustomIdDTOMapsIdMapper.toDto(entityCustomIdDTOMapsId);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityCustomIdDTOMapsIdMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomIdDTOMapsIdDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void updateEntityCustomIdDTOMapsIdMapsIdAssociationWithNewId() throws Exception {
        // Initialize the database
        insertedEntityCustomIdDTOMapsId = entityCustomIdDTOMapsIdRepository.saveAndFlush(entityCustomIdDTOMapsId);
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Add a new parent entity
        EntityCustomIdDTO entityCustomIdDTO = EntityCustomIdDTOResourceIT.createUpdatedEntity();
        em.persist(entityCustomIdDTO);
        em.flush();

        // Load the entityCustomIdDTOMapsId
        EntityCustomIdDTOMapsId updatedEntityCustomIdDTOMapsId = entityCustomIdDTOMapsIdRepository
            .findById(entityCustomIdDTOMapsId.getCustomId())
            .orElseThrow();
        assertThat(updatedEntityCustomIdDTOMapsId).isNotNull();
        // Disconnect from session so that the updates on updatedEntityCustomIdDTOMapsId are not directly saved in db
        em.detach(updatedEntityCustomIdDTOMapsId);

        // Update the EntityCustomIdDTO with new association value
        updatedEntityCustomIdDTOMapsId.setEntityCustomIdDTO(entityCustomIdDTO);
        EntityCustomIdDTOMapsIdDTO updatedEntityCustomIdDTOMapsIdDTO = entityCustomIdDTOMapsIdMapper.toDto(updatedEntityCustomIdDTOMapsId);
        assertThat(updatedEntityCustomIdDTOMapsIdDTO).isNotNull();

        // Update the entity
        restEntityCustomIdDTOMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEntityCustomIdDTOMapsIdDTO.getCustomId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedEntityCustomIdDTOMapsIdDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        /**
         * Validate the id for MapsId, the ids must be same
         * Uncomment the following line for assertion. However, please note that there is a known issue and uncommenting will fail the test.
         * Please look at https://github.com/jhipster/generator-jhipster/issues/9100. You can modify this test as necessary.
         * assertThat(testEntityCustomIdDTOMapsId.getCustomId()).isEqualTo(testEntityCustomIdDTOMapsId.getEntityCustomIdDTO().getCustomId());
         */
    }

    @Test
    @Transactional
    void getAllEntityCustomIdDTOMapsIds() throws Exception {
        // Initialize the database
        insertedEntityCustomIdDTOMapsId = entityCustomIdDTOMapsIdRepository.saveAndFlush(entityCustomIdDTOMapsId);

        // Get all the entityCustomIdDTOMapsIdList
        restEntityCustomIdDTOMapsIdMockMvc
            .perform(get(ENTITY_API_URL + "?sort=customId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].customId").value(hasItem(entityCustomIdDTOMapsId.getCustomId().intValue())));
    }

    @Test
    @Transactional
    void getEntityCustomIdDTOMapsId() throws Exception {
        // Initialize the database
        insertedEntityCustomIdDTOMapsId = entityCustomIdDTOMapsIdRepository.saveAndFlush(entityCustomIdDTOMapsId);

        // Get the entityCustomIdDTOMapsId
        restEntityCustomIdDTOMapsIdMockMvc
            .perform(get(ENTITY_API_URL_ID, entityCustomIdDTOMapsId.getCustomId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.customId").value(entityCustomIdDTOMapsId.getCustomId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingEntityCustomIdDTOMapsId() throws Exception {
        // Get the entityCustomIdDTOMapsId
        restEntityCustomIdDTOMapsIdMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEntityCustomIdDTOMapsId() throws Exception {
        // Initialize the database
        insertedEntityCustomIdDTOMapsId = entityCustomIdDTOMapsIdRepository.saveAndFlush(entityCustomIdDTOMapsId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdDTOMapsId
        EntityCustomIdDTOMapsId updatedEntityCustomIdDTOMapsId = entityCustomIdDTOMapsIdRepository
            .findById(entityCustomIdDTOMapsId.getCustomId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedEntityCustomIdDTOMapsId are not directly saved in db
        em.detach(updatedEntityCustomIdDTOMapsId);
        EntityCustomIdDTOMapsIdDTO entityCustomIdDTOMapsIdDTO = entityCustomIdDTOMapsIdMapper.toDto(updatedEntityCustomIdDTOMapsId);

        restEntityCustomIdDTOMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityCustomIdDTOMapsIdDTO.getCustomId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdDTOMapsIdDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedEntityCustomIdDTOMapsIdToMatchAllProperties(updatedEntityCustomIdDTOMapsId);
    }

    @Test
    @Transactional
    void putNonExistingEntityCustomIdDTOMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdDTOMapsId.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdDTOMapsId
        EntityCustomIdDTOMapsIdDTO entityCustomIdDTOMapsIdDTO = entityCustomIdDTOMapsIdMapper.toDto(entityCustomIdDTOMapsId);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityCustomIdDTOMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityCustomIdDTOMapsIdDTO.getCustomId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdDTOMapsIdDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEntityCustomIdDTOMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdDTOMapsId.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdDTOMapsId
        EntityCustomIdDTOMapsIdDTO entityCustomIdDTOMapsIdDTO = entityCustomIdDTOMapsIdMapper.toDto(entityCustomIdDTOMapsId);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdDTOMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdDTOMapsIdDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEntityCustomIdDTOMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdDTOMapsId.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdDTOMapsId
        EntityCustomIdDTOMapsIdDTO entityCustomIdDTOMapsIdDTO = entityCustomIdDTOMapsIdMapper.toDto(entityCustomIdDTOMapsId);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdDTOMapsIdMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomIdDTOMapsIdDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityCustomIdDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEntityCustomIdDTOMapsIdWithPatch() throws Exception {
        // Initialize the database
        insertedEntityCustomIdDTOMapsId = entityCustomIdDTOMapsIdRepository.saveAndFlush(entityCustomIdDTOMapsId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdDTOMapsId using partial update
        EntityCustomIdDTOMapsId partialUpdatedEntityCustomIdDTOMapsId = new EntityCustomIdDTOMapsId();
        partialUpdatedEntityCustomIdDTOMapsId.setCustomId(entityCustomIdDTOMapsId.getCustomId());

        restEntityCustomIdDTOMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityCustomIdDTOMapsId.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityCustomIdDTOMapsId))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdDTOMapsId in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityCustomIdDTOMapsIdUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedEntityCustomIdDTOMapsId, entityCustomIdDTOMapsId),
            getPersistedEntityCustomIdDTOMapsId(entityCustomIdDTOMapsId)
        );
    }

    @Test
    @Transactional
    void fullUpdateEntityCustomIdDTOMapsIdWithPatch() throws Exception {
        // Initialize the database
        insertedEntityCustomIdDTOMapsId = entityCustomIdDTOMapsIdRepository.saveAndFlush(entityCustomIdDTOMapsId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdDTOMapsId using partial update
        EntityCustomIdDTOMapsId partialUpdatedEntityCustomIdDTOMapsId = new EntityCustomIdDTOMapsId();
        partialUpdatedEntityCustomIdDTOMapsId.setCustomId(entityCustomIdDTOMapsId.getCustomId());

        restEntityCustomIdDTOMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityCustomIdDTOMapsId.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityCustomIdDTOMapsId))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdDTOMapsId in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityCustomIdDTOMapsIdUpdatableFieldsEquals(
            partialUpdatedEntityCustomIdDTOMapsId,
            getPersistedEntityCustomIdDTOMapsId(partialUpdatedEntityCustomIdDTOMapsId)
        );
    }

    @Test
    @Transactional
    void patchNonExistingEntityCustomIdDTOMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdDTOMapsId.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdDTOMapsId
        EntityCustomIdDTOMapsIdDTO entityCustomIdDTOMapsIdDTO = entityCustomIdDTOMapsIdMapper.toDto(entityCustomIdDTOMapsId);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityCustomIdDTOMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, entityCustomIdDTOMapsIdDTO.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityCustomIdDTOMapsIdDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEntityCustomIdDTOMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdDTOMapsId.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdDTOMapsId
        EntityCustomIdDTOMapsIdDTO entityCustomIdDTOMapsIdDTO = entityCustomIdDTOMapsIdMapper.toDto(entityCustomIdDTOMapsId);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdDTOMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityCustomIdDTOMapsIdDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEntityCustomIdDTOMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdDTOMapsId.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdDTOMapsId
        EntityCustomIdDTOMapsIdDTO entityCustomIdDTOMapsIdDTO = entityCustomIdDTOMapsIdMapper.toDto(entityCustomIdDTOMapsId);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdDTOMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(entityCustomIdDTOMapsIdDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityCustomIdDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEntityCustomIdDTOMapsId() throws Exception {
        // Initialize the database
        insertedEntityCustomIdDTOMapsId = entityCustomIdDTOMapsIdRepository.saveAndFlush(entityCustomIdDTOMapsId);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the entityCustomIdDTOMapsId
        restEntityCustomIdDTOMapsIdMockMvc
            .perform(delete(ENTITY_API_URL_ID, entityCustomIdDTOMapsId.getCustomId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return entityCustomIdDTOMapsIdRepository.count();
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

    protected EntityCustomIdDTOMapsId getPersistedEntityCustomIdDTOMapsId(EntityCustomIdDTOMapsId entityCustomIdDTOMapsId) {
        return entityCustomIdDTOMapsIdRepository.findById(entityCustomIdDTOMapsId.getCustomId()).orElseThrow();
    }

    protected void assertPersistedEntityCustomIdDTOMapsIdToMatchAllProperties(EntityCustomIdDTOMapsId expectedEntityCustomIdDTOMapsId) {
        assertEntityCustomIdDTOMapsIdAllPropertiesEquals(
            expectedEntityCustomIdDTOMapsId,
            getPersistedEntityCustomIdDTOMapsId(expectedEntityCustomIdDTOMapsId)
        );
    }

    protected void assertPersistedEntityCustomIdDTOMapsIdToMatchUpdatableProperties(
        EntityCustomIdDTOMapsId expectedEntityCustomIdDTOMapsId
    ) {
        assertEntityCustomIdDTOMapsIdAllUpdatablePropertiesEquals(
            expectedEntityCustomIdDTOMapsId,
            getPersistedEntityCustomIdDTOMapsId(expectedEntityCustomIdDTOMapsId)
        );
    }
}
