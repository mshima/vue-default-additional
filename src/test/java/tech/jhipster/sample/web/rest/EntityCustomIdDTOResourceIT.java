package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.sample.domain.EntityCustomIdDTOAsserts.*;
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
import tech.jhipster.sample.repository.EntityCustomIdDTORepository;
import tech.jhipster.sample.service.dto.EntityCustomIdDTODTO;
import tech.jhipster.sample.service.mapper.EntityCustomIdDTOMapper;

/**
 * Integration tests for the {@link EntityCustomIdDTOResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EntityCustomIdDTOResourceIT {

    private static final String ENTITY_API_URL = "/api/entity-custom-id-dtos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{customId}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EntityCustomIdDTORepository entityCustomIdDTORepository;

    @Autowired
    private EntityCustomIdDTOMapper entityCustomIdDTOMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntityCustomIdDTOMockMvc;

    private EntityCustomIdDTO entityCustomIdDTO;

    private EntityCustomIdDTO insertedEntityCustomIdDTO;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityCustomIdDTO createEntity() {
        return new EntityCustomIdDTO();
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityCustomIdDTO createUpdatedEntity() {
        return new EntityCustomIdDTO();
    }

    @BeforeEach
    void initTest() {
        entityCustomIdDTO = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedEntityCustomIdDTO != null) {
            entityCustomIdDTORepository.delete(insertedEntityCustomIdDTO);
            insertedEntityCustomIdDTO = null;
        }
    }

    @Test
    @Transactional
    void createEntityCustomIdDTO() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the EntityCustomIdDTO
        EntityCustomIdDTODTO entityCustomIdDTODTO = entityCustomIdDTOMapper.toDto(entityCustomIdDTO);
        var returnedEntityCustomIdDTODTO = om.readValue(
            restEntityCustomIdDTOMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomIdDTODTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            EntityCustomIdDTODTO.class
        );

        // Validate the EntityCustomIdDTO in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedEntityCustomIdDTO = entityCustomIdDTOMapper.toEntity(returnedEntityCustomIdDTODTO);
        assertEntityCustomIdDTOUpdatableFieldsEquals(returnedEntityCustomIdDTO, getPersistedEntityCustomIdDTO(returnedEntityCustomIdDTO));

        insertedEntityCustomIdDTO = returnedEntityCustomIdDTO;
    }

    @Test
    @Transactional
    void createEntityCustomIdDTOWithExistingId() throws Exception {
        // Create the EntityCustomIdDTO with an existing ID
        entityCustomIdDTO.setCustomId(1L);
        EntityCustomIdDTODTO entityCustomIdDTODTO = entityCustomIdDTOMapper.toDto(entityCustomIdDTO);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityCustomIdDTOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomIdDTODTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEntityCustomIdDTOS() throws Exception {
        // Initialize the database
        insertedEntityCustomIdDTO = entityCustomIdDTORepository.saveAndFlush(entityCustomIdDTO);

        // Get all the entityCustomIdDTOList
        restEntityCustomIdDTOMockMvc
            .perform(get(ENTITY_API_URL + "?sort=customId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].customId").value(hasItem(entityCustomIdDTO.getCustomId().intValue())));
    }

    @Test
    @Transactional
    void getEntityCustomIdDTO() throws Exception {
        // Initialize the database
        insertedEntityCustomIdDTO = entityCustomIdDTORepository.saveAndFlush(entityCustomIdDTO);

        // Get the entityCustomIdDTO
        restEntityCustomIdDTOMockMvc
            .perform(get(ENTITY_API_URL_ID, entityCustomIdDTO.getCustomId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.customId").value(entityCustomIdDTO.getCustomId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingEntityCustomIdDTO() throws Exception {
        // Get the entityCustomIdDTO
        restEntityCustomIdDTOMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEntityCustomIdDTO() throws Exception {
        // Initialize the database
        insertedEntityCustomIdDTO = entityCustomIdDTORepository.saveAndFlush(entityCustomIdDTO);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdDTO
        EntityCustomIdDTO updatedEntityCustomIdDTO = entityCustomIdDTORepository.findById(entityCustomIdDTO.getCustomId()).orElseThrow();
        // Disconnect from session so that the updates on updatedEntityCustomIdDTO are not directly saved in db
        em.detach(updatedEntityCustomIdDTO);
        EntityCustomIdDTODTO entityCustomIdDTODTO = entityCustomIdDTOMapper.toDto(updatedEntityCustomIdDTO);

        restEntityCustomIdDTOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityCustomIdDTODTO.getCustomId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdDTODTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedEntityCustomIdDTOToMatchAllProperties(updatedEntityCustomIdDTO);
    }

    @Test
    @Transactional
    void putNonExistingEntityCustomIdDTO() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdDTO.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdDTO
        EntityCustomIdDTODTO entityCustomIdDTODTO = entityCustomIdDTOMapper.toDto(entityCustomIdDTO);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityCustomIdDTOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityCustomIdDTODTO.getCustomId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdDTODTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEntityCustomIdDTO() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdDTO.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdDTO
        EntityCustomIdDTODTO entityCustomIdDTODTO = entityCustomIdDTOMapper.toDto(entityCustomIdDTO);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdDTOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdDTODTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEntityCustomIdDTO() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdDTO.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdDTO
        EntityCustomIdDTODTO entityCustomIdDTODTO = entityCustomIdDTOMapper.toDto(entityCustomIdDTO);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdDTOMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomIdDTODTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityCustomIdDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEntityCustomIdDTOWithPatch() throws Exception {
        // Initialize the database
        insertedEntityCustomIdDTO = entityCustomIdDTORepository.saveAndFlush(entityCustomIdDTO);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdDTO using partial update
        EntityCustomIdDTO partialUpdatedEntityCustomIdDTO = new EntityCustomIdDTO();
        partialUpdatedEntityCustomIdDTO.setCustomId(entityCustomIdDTO.getCustomId());

        restEntityCustomIdDTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityCustomIdDTO.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityCustomIdDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdDTO in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityCustomIdDTOUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedEntityCustomIdDTO, entityCustomIdDTO),
            getPersistedEntityCustomIdDTO(entityCustomIdDTO)
        );
    }

    @Test
    @Transactional
    void fullUpdateEntityCustomIdDTOWithPatch() throws Exception {
        // Initialize the database
        insertedEntityCustomIdDTO = entityCustomIdDTORepository.saveAndFlush(entityCustomIdDTO);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdDTO using partial update
        EntityCustomIdDTO partialUpdatedEntityCustomIdDTO = new EntityCustomIdDTO();
        partialUpdatedEntityCustomIdDTO.setCustomId(entityCustomIdDTO.getCustomId());

        restEntityCustomIdDTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityCustomIdDTO.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityCustomIdDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdDTO in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityCustomIdDTOUpdatableFieldsEquals(
            partialUpdatedEntityCustomIdDTO,
            getPersistedEntityCustomIdDTO(partialUpdatedEntityCustomIdDTO)
        );
    }

    @Test
    @Transactional
    void patchNonExistingEntityCustomIdDTO() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdDTO.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdDTO
        EntityCustomIdDTODTO entityCustomIdDTODTO = entityCustomIdDTOMapper.toDto(entityCustomIdDTO);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityCustomIdDTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, entityCustomIdDTODTO.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityCustomIdDTODTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEntityCustomIdDTO() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdDTO.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdDTO
        EntityCustomIdDTODTO entityCustomIdDTODTO = entityCustomIdDTOMapper.toDto(entityCustomIdDTO);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdDTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityCustomIdDTODTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEntityCustomIdDTO() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdDTO.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdDTO
        EntityCustomIdDTODTO entityCustomIdDTODTO = entityCustomIdDTOMapper.toDto(entityCustomIdDTO);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdDTOMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(entityCustomIdDTODTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityCustomIdDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEntityCustomIdDTO() throws Exception {
        // Initialize the database
        insertedEntityCustomIdDTO = entityCustomIdDTORepository.saveAndFlush(entityCustomIdDTO);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the entityCustomIdDTO
        restEntityCustomIdDTOMockMvc
            .perform(delete(ENTITY_API_URL_ID, entityCustomIdDTO.getCustomId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return entityCustomIdDTORepository.count();
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

    protected EntityCustomIdDTO getPersistedEntityCustomIdDTO(EntityCustomIdDTO entityCustomIdDTO) {
        return entityCustomIdDTORepository.findById(entityCustomIdDTO.getCustomId()).orElseThrow();
    }

    protected void assertPersistedEntityCustomIdDTOToMatchAllProperties(EntityCustomIdDTO expectedEntityCustomIdDTO) {
        assertEntityCustomIdDTOAllPropertiesEquals(expectedEntityCustomIdDTO, getPersistedEntityCustomIdDTO(expectedEntityCustomIdDTO));
    }

    protected void assertPersistedEntityCustomIdDTOToMatchUpdatableProperties(EntityCustomIdDTO expectedEntityCustomIdDTO) {
        assertEntityCustomIdDTOAllUpdatablePropertiesEquals(
            expectedEntityCustomIdDTO,
            getPersistedEntityCustomIdDTO(expectedEntityCustomIdDTO)
        );
    }
}
