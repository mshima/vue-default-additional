package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.sample.domain.EntityCustomIdRequiredDTOMapsIdAsserts.*;
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
import tech.jhipster.sample.domain.EntityCustomIdRequiredDTO;
import tech.jhipster.sample.domain.EntityCustomIdRequiredDTOMapsId;
import tech.jhipster.sample.repository.EntityCustomIdRequiredDTOMapsIdRepository;
import tech.jhipster.sample.service.dto.EntityCustomIdRequiredDTOMapsIdDTO;
import tech.jhipster.sample.service.mapper.EntityCustomIdRequiredDTOMapsIdMapper;

/**
 * Integration tests for the {@link EntityCustomIdRequiredDTOMapsIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EntityCustomIdRequiredDTOMapsIdResourceIT {

    private static final String ENTITY_API_URL = "/api/entity-custom-id-required-dto-maps-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{customId}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EntityCustomIdRequiredDTOMapsIdRepository entityCustomIdRequiredDTOMapsIdRepository;

    @Autowired
    private EntityCustomIdRequiredDTOMapsIdMapper entityCustomIdRequiredDTOMapsIdMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntityCustomIdRequiredDTOMapsIdMockMvc;

    private EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsId;

    private EntityCustomIdRequiredDTOMapsId insertedEntityCustomIdRequiredDTOMapsId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityCustomIdRequiredDTOMapsId createEntity(EntityManager em) {
        EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsId = new EntityCustomIdRequiredDTOMapsId();
        // Add required entity
        EntityCustomIdRequiredDTO entityCustomIdRequiredDTO;
        if (TestUtil.findAll(em, EntityCustomIdRequiredDTO.class).isEmpty()) {
            entityCustomIdRequiredDTO = EntityCustomIdRequiredDTOResourceIT.createEntity();
            em.persist(entityCustomIdRequiredDTO);
            em.flush();
        } else {
            entityCustomIdRequiredDTO = TestUtil.findAll(em, EntityCustomIdRequiredDTO.class).get(0);
        }
        entityCustomIdRequiredDTOMapsId.setEntityCustomIdRequiredDTO(entityCustomIdRequiredDTO);
        return entityCustomIdRequiredDTOMapsId;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityCustomIdRequiredDTOMapsId createUpdatedEntity(EntityManager em) {
        EntityCustomIdRequiredDTOMapsId updatedEntityCustomIdRequiredDTOMapsId = new EntityCustomIdRequiredDTOMapsId();
        // Add required entity
        EntityCustomIdRequiredDTO entityCustomIdRequiredDTO;
        entityCustomIdRequiredDTO = EntityCustomIdRequiredDTOResourceIT.createUpdatedEntity();
        em.persist(entityCustomIdRequiredDTO);
        em.flush();
        updatedEntityCustomIdRequiredDTOMapsId.setEntityCustomIdRequiredDTO(entityCustomIdRequiredDTO);
        return updatedEntityCustomIdRequiredDTOMapsId;
    }

    @BeforeEach
    void initTest() {
        entityCustomIdRequiredDTOMapsId = createEntity(em);
    }

    @AfterEach
    void cleanup() {
        if (insertedEntityCustomIdRequiredDTOMapsId != null) {
            entityCustomIdRequiredDTOMapsIdRepository.delete(insertedEntityCustomIdRequiredDTOMapsId);
            insertedEntityCustomIdRequiredDTOMapsId = null;
        }
    }

    @Test
    @Transactional
    void createEntityCustomIdRequiredDTOMapsId() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the EntityCustomIdRequiredDTOMapsId
        EntityCustomIdRequiredDTOMapsIdDTO entityCustomIdRequiredDTOMapsIdDTO = entityCustomIdRequiredDTOMapsIdMapper.toDto(
            entityCustomIdRequiredDTOMapsId
        );
        var returnedEntityCustomIdRequiredDTOMapsIdDTO = om.readValue(
            restEntityCustomIdRequiredDTOMapsIdMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(entityCustomIdRequiredDTOMapsIdDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            EntityCustomIdRequiredDTOMapsIdDTO.class
        );

        // Validate the EntityCustomIdRequiredDTOMapsId in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedEntityCustomIdRequiredDTOMapsId = entityCustomIdRequiredDTOMapsIdMapper.toEntity(
            returnedEntityCustomIdRequiredDTOMapsIdDTO
        );
        assertEntityCustomIdRequiredDTOMapsIdUpdatableFieldsEquals(
            returnedEntityCustomIdRequiredDTOMapsId,
            getPersistedEntityCustomIdRequiredDTOMapsId(returnedEntityCustomIdRequiredDTOMapsId)
        );

        assertEntityCustomIdRequiredDTOMapsIdMapsIdRelationshipPersistedValue(
            entityCustomIdRequiredDTOMapsId,
            returnedEntityCustomIdRequiredDTOMapsId
        );

        insertedEntityCustomIdRequiredDTOMapsId = returnedEntityCustomIdRequiredDTOMapsId;
    }

    @Test
    @Transactional
    void createEntityCustomIdRequiredDTOMapsIdWithExistingId() throws Exception {
        // Create the EntityCustomIdRequiredDTOMapsId with an existing ID
        entityCustomIdRequiredDTOMapsId.setCustomId(1L);
        EntityCustomIdRequiredDTOMapsIdDTO entityCustomIdRequiredDTOMapsIdDTO = entityCustomIdRequiredDTOMapsIdMapper.toDto(
            entityCustomIdRequiredDTOMapsId
        );

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityCustomIdRequiredDTOMapsIdMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdRequiredDTOMapsIdDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdRequiredDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void updateEntityCustomIdRequiredDTOMapsIdMapsIdAssociationWithNewId() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRequiredDTOMapsId = entityCustomIdRequiredDTOMapsIdRepository.saveAndFlush(entityCustomIdRequiredDTOMapsId);
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Add a new parent entity
        EntityCustomIdRequiredDTO entityCustomIdRequiredDTO = EntityCustomIdRequiredDTOResourceIT.createUpdatedEntity();
        em.persist(entityCustomIdRequiredDTO);
        em.flush();

        // Load the entityCustomIdRequiredDTOMapsId
        EntityCustomIdRequiredDTOMapsId updatedEntityCustomIdRequiredDTOMapsId = entityCustomIdRequiredDTOMapsIdRepository
            .findById(entityCustomIdRequiredDTOMapsId.getCustomId())
            .orElseThrow();
        assertThat(updatedEntityCustomIdRequiredDTOMapsId).isNotNull();
        // Disconnect from session so that the updates on updatedEntityCustomIdRequiredDTOMapsId are not directly saved in db
        em.detach(updatedEntityCustomIdRequiredDTOMapsId);

        // Update the EntityCustomIdRequiredDTO with new association value
        updatedEntityCustomIdRequiredDTOMapsId.setEntityCustomIdRequiredDTO(entityCustomIdRequiredDTO);
        EntityCustomIdRequiredDTOMapsIdDTO updatedEntityCustomIdRequiredDTOMapsIdDTO = entityCustomIdRequiredDTOMapsIdMapper.toDto(
            updatedEntityCustomIdRequiredDTOMapsId
        );
        assertThat(updatedEntityCustomIdRequiredDTOMapsIdDTO).isNotNull();

        // Update the entity
        restEntityCustomIdRequiredDTOMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEntityCustomIdRequiredDTOMapsIdDTO.getCustomId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedEntityCustomIdRequiredDTOMapsIdDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdRequiredDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
        /**
         * Validate the id for MapsId, the ids must be same
         * Uncomment the following line for assertion. However, please note that there is a known issue and uncommenting will fail the test.
         * Please look at https://github.com/jhipster/generator-jhipster/issues/9100. You can modify this test as necessary.
         * assertThat(testEntityCustomIdRequiredDTOMapsId.getCustomId()).isEqualTo(testEntityCustomIdRequiredDTOMapsId.getEntityCustomIdRequiredDTO().getCustomId());
         */
    }

    @Test
    @Transactional
    void getAllEntityCustomIdRequiredDTOMapsIds() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRequiredDTOMapsId = entityCustomIdRequiredDTOMapsIdRepository.saveAndFlush(entityCustomIdRequiredDTOMapsId);

        // Get all the entityCustomIdRequiredDTOMapsIdList
        restEntityCustomIdRequiredDTOMapsIdMockMvc
            .perform(get(ENTITY_API_URL + "?sort=customId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].customId").value(hasItem(entityCustomIdRequiredDTOMapsId.getCustomId().intValue())));
    }

    @Test
    @Transactional
    void getEntityCustomIdRequiredDTOMapsId() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRequiredDTOMapsId = entityCustomIdRequiredDTOMapsIdRepository.saveAndFlush(entityCustomIdRequiredDTOMapsId);

        // Get the entityCustomIdRequiredDTOMapsId
        restEntityCustomIdRequiredDTOMapsIdMockMvc
            .perform(get(ENTITY_API_URL_ID, entityCustomIdRequiredDTOMapsId.getCustomId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.customId").value(entityCustomIdRequiredDTOMapsId.getCustomId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingEntityCustomIdRequiredDTOMapsId() throws Exception {
        // Get the entityCustomIdRequiredDTOMapsId
        restEntityCustomIdRequiredDTOMapsIdMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEntityCustomIdRequiredDTOMapsId() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRequiredDTOMapsId = entityCustomIdRequiredDTOMapsIdRepository.saveAndFlush(entityCustomIdRequiredDTOMapsId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdRequiredDTOMapsId
        EntityCustomIdRequiredDTOMapsId updatedEntityCustomIdRequiredDTOMapsId = entityCustomIdRequiredDTOMapsIdRepository
            .findById(entityCustomIdRequiredDTOMapsId.getCustomId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedEntityCustomIdRequiredDTOMapsId are not directly saved in db
        em.detach(updatedEntityCustomIdRequiredDTOMapsId);
        EntityCustomIdRequiredDTOMapsIdDTO entityCustomIdRequiredDTOMapsIdDTO = entityCustomIdRequiredDTOMapsIdMapper.toDto(
            updatedEntityCustomIdRequiredDTOMapsId
        );

        restEntityCustomIdRequiredDTOMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityCustomIdRequiredDTOMapsIdDTO.getCustomId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdRequiredDTOMapsIdDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdRequiredDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedEntityCustomIdRequiredDTOMapsIdToMatchAllProperties(updatedEntityCustomIdRequiredDTOMapsId);
    }

    @Test
    @Transactional
    void putNonExistingEntityCustomIdRequiredDTOMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRequiredDTOMapsId.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdRequiredDTOMapsId
        EntityCustomIdRequiredDTOMapsIdDTO entityCustomIdRequiredDTOMapsIdDTO = entityCustomIdRequiredDTOMapsIdMapper.toDto(
            entityCustomIdRequiredDTOMapsId
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityCustomIdRequiredDTOMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityCustomIdRequiredDTOMapsIdDTO.getCustomId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdRequiredDTOMapsIdDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdRequiredDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEntityCustomIdRequiredDTOMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRequiredDTOMapsId.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdRequiredDTOMapsId
        EntityCustomIdRequiredDTOMapsIdDTO entityCustomIdRequiredDTOMapsIdDTO = entityCustomIdRequiredDTOMapsIdMapper.toDto(
            entityCustomIdRequiredDTOMapsId
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdRequiredDTOMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdRequiredDTOMapsIdDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdRequiredDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEntityCustomIdRequiredDTOMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRequiredDTOMapsId.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdRequiredDTOMapsId
        EntityCustomIdRequiredDTOMapsIdDTO entityCustomIdRequiredDTOMapsIdDTO = entityCustomIdRequiredDTOMapsIdMapper.toDto(
            entityCustomIdRequiredDTOMapsId
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdRequiredDTOMapsIdMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdRequiredDTOMapsIdDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityCustomIdRequiredDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEntityCustomIdRequiredDTOMapsIdWithPatch() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRequiredDTOMapsId = entityCustomIdRequiredDTOMapsIdRepository.saveAndFlush(entityCustomIdRequiredDTOMapsId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdRequiredDTOMapsId using partial update
        EntityCustomIdRequiredDTOMapsId partialUpdatedEntityCustomIdRequiredDTOMapsId = new EntityCustomIdRequiredDTOMapsId();
        partialUpdatedEntityCustomIdRequiredDTOMapsId.setCustomId(entityCustomIdRequiredDTOMapsId.getCustomId());

        restEntityCustomIdRequiredDTOMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityCustomIdRequiredDTOMapsId.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityCustomIdRequiredDTOMapsId))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdRequiredDTOMapsId in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityCustomIdRequiredDTOMapsIdUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedEntityCustomIdRequiredDTOMapsId, entityCustomIdRequiredDTOMapsId),
            getPersistedEntityCustomIdRequiredDTOMapsId(entityCustomIdRequiredDTOMapsId)
        );
    }

    @Test
    @Transactional
    void fullUpdateEntityCustomIdRequiredDTOMapsIdWithPatch() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRequiredDTOMapsId = entityCustomIdRequiredDTOMapsIdRepository.saveAndFlush(entityCustomIdRequiredDTOMapsId);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdRequiredDTOMapsId using partial update
        EntityCustomIdRequiredDTOMapsId partialUpdatedEntityCustomIdRequiredDTOMapsId = new EntityCustomIdRequiredDTOMapsId();
        partialUpdatedEntityCustomIdRequiredDTOMapsId.setCustomId(entityCustomIdRequiredDTOMapsId.getCustomId());

        restEntityCustomIdRequiredDTOMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityCustomIdRequiredDTOMapsId.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityCustomIdRequiredDTOMapsId))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdRequiredDTOMapsId in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityCustomIdRequiredDTOMapsIdUpdatableFieldsEquals(
            partialUpdatedEntityCustomIdRequiredDTOMapsId,
            getPersistedEntityCustomIdRequiredDTOMapsId(partialUpdatedEntityCustomIdRequiredDTOMapsId)
        );
    }

    @Test
    @Transactional
    void patchNonExistingEntityCustomIdRequiredDTOMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRequiredDTOMapsId.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdRequiredDTOMapsId
        EntityCustomIdRequiredDTOMapsIdDTO entityCustomIdRequiredDTOMapsIdDTO = entityCustomIdRequiredDTOMapsIdMapper.toDto(
            entityCustomIdRequiredDTOMapsId
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityCustomIdRequiredDTOMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, entityCustomIdRequiredDTOMapsIdDTO.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityCustomIdRequiredDTOMapsIdDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdRequiredDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEntityCustomIdRequiredDTOMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRequiredDTOMapsId.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdRequiredDTOMapsId
        EntityCustomIdRequiredDTOMapsIdDTO entityCustomIdRequiredDTOMapsIdDTO = entityCustomIdRequiredDTOMapsIdMapper.toDto(
            entityCustomIdRequiredDTOMapsId
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdRequiredDTOMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityCustomIdRequiredDTOMapsIdDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdRequiredDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEntityCustomIdRequiredDTOMapsId() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRequiredDTOMapsId.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdRequiredDTOMapsId
        EntityCustomIdRequiredDTOMapsIdDTO entityCustomIdRequiredDTOMapsIdDTO = entityCustomIdRequiredDTOMapsIdMapper.toDto(
            entityCustomIdRequiredDTOMapsId
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdRequiredDTOMapsIdMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityCustomIdRequiredDTOMapsIdDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityCustomIdRequiredDTOMapsId in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEntityCustomIdRequiredDTOMapsId() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRequiredDTOMapsId = entityCustomIdRequiredDTOMapsIdRepository.saveAndFlush(entityCustomIdRequiredDTOMapsId);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the entityCustomIdRequiredDTOMapsId
        restEntityCustomIdRequiredDTOMapsIdMockMvc
            .perform(delete(ENTITY_API_URL_ID, entityCustomIdRequiredDTOMapsId.getCustomId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return entityCustomIdRequiredDTOMapsIdRepository.count();
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

    protected EntityCustomIdRequiredDTOMapsId getPersistedEntityCustomIdRequiredDTOMapsId(
        EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsId
    ) {
        return entityCustomIdRequiredDTOMapsIdRepository.findById(entityCustomIdRequiredDTOMapsId.getCustomId()).orElseThrow();
    }

    protected void assertPersistedEntityCustomIdRequiredDTOMapsIdToMatchAllProperties(
        EntityCustomIdRequiredDTOMapsId expectedEntityCustomIdRequiredDTOMapsId
    ) {
        assertEntityCustomIdRequiredDTOMapsIdAllPropertiesEquals(
            expectedEntityCustomIdRequiredDTOMapsId,
            getPersistedEntityCustomIdRequiredDTOMapsId(expectedEntityCustomIdRequiredDTOMapsId)
        );
    }

    protected void assertPersistedEntityCustomIdRequiredDTOMapsIdToMatchUpdatableProperties(
        EntityCustomIdRequiredDTOMapsId expectedEntityCustomIdRequiredDTOMapsId
    ) {
        assertEntityCustomIdRequiredDTOMapsIdAllUpdatablePropertiesEquals(
            expectedEntityCustomIdRequiredDTOMapsId,
            getPersistedEntityCustomIdRequiredDTOMapsId(expectedEntityCustomIdRequiredDTOMapsId)
        );
    }
}
