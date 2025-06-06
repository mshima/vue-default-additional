package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.sample.domain.EntityCustomIdRequiredDTOAsserts.*;
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
import tech.jhipster.sample.repository.EntityCustomIdRequiredDTORepository;
import tech.jhipster.sample.service.dto.EntityCustomIdRequiredDTODTO;
import tech.jhipster.sample.service.mapper.EntityCustomIdRequiredDTOMapper;

/**
 * Integration tests for the {@link EntityCustomIdRequiredDTOResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EntityCustomIdRequiredDTOResourceIT {

    private static final String ENTITY_API_URL = "/api/entity-custom-id-required-dtos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{customId}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EntityCustomIdRequiredDTORepository entityCustomIdRequiredDTORepository;

    @Autowired
    private EntityCustomIdRequiredDTOMapper entityCustomIdRequiredDTOMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntityCustomIdRequiredDTOMockMvc;

    private EntityCustomIdRequiredDTO entityCustomIdRequiredDTO;

    private EntityCustomIdRequiredDTO insertedEntityCustomIdRequiredDTO;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityCustomIdRequiredDTO createEntity() {
        return new EntityCustomIdRequiredDTO();
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityCustomIdRequiredDTO createUpdatedEntity() {
        return new EntityCustomIdRequiredDTO();
    }

    @BeforeEach
    void initTest() {
        entityCustomIdRequiredDTO = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedEntityCustomIdRequiredDTO != null) {
            entityCustomIdRequiredDTORepository.delete(insertedEntityCustomIdRequiredDTO);
            insertedEntityCustomIdRequiredDTO = null;
        }
    }

    @Test
    @Transactional
    void createEntityCustomIdRequiredDTO() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the EntityCustomIdRequiredDTO
        EntityCustomIdRequiredDTODTO entityCustomIdRequiredDTODTO = entityCustomIdRequiredDTOMapper.toDto(entityCustomIdRequiredDTO);
        var returnedEntityCustomIdRequiredDTODTO = om.readValue(
            restEntityCustomIdRequiredDTOMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomIdRequiredDTODTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            EntityCustomIdRequiredDTODTO.class
        );

        // Validate the EntityCustomIdRequiredDTO in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedEntityCustomIdRequiredDTO = entityCustomIdRequiredDTOMapper.toEntity(returnedEntityCustomIdRequiredDTODTO);
        assertEntityCustomIdRequiredDTOUpdatableFieldsEquals(
            returnedEntityCustomIdRequiredDTO,
            getPersistedEntityCustomIdRequiredDTO(returnedEntityCustomIdRequiredDTO)
        );

        insertedEntityCustomIdRequiredDTO = returnedEntityCustomIdRequiredDTO;
    }

    @Test
    @Transactional
    void createEntityCustomIdRequiredDTOWithExistingId() throws Exception {
        // Create the EntityCustomIdRequiredDTO with an existing ID
        entityCustomIdRequiredDTO.setCustomId(1L);
        EntityCustomIdRequiredDTODTO entityCustomIdRequiredDTODTO = entityCustomIdRequiredDTOMapper.toDto(entityCustomIdRequiredDTO);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityCustomIdRequiredDTOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomIdRequiredDTODTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdRequiredDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEntityCustomIdRequiredDTOS() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRequiredDTO = entityCustomIdRequiredDTORepository.saveAndFlush(entityCustomIdRequiredDTO);

        // Get all the entityCustomIdRequiredDTOList
        restEntityCustomIdRequiredDTOMockMvc
            .perform(get(ENTITY_API_URL + "?sort=customId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].customId").value(hasItem(entityCustomIdRequiredDTO.getCustomId().intValue())));
    }

    @Test
    @Transactional
    void getEntityCustomIdRequiredDTO() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRequiredDTO = entityCustomIdRequiredDTORepository.saveAndFlush(entityCustomIdRequiredDTO);

        // Get the entityCustomIdRequiredDTO
        restEntityCustomIdRequiredDTOMockMvc
            .perform(get(ENTITY_API_URL_ID, entityCustomIdRequiredDTO.getCustomId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.customId").value(entityCustomIdRequiredDTO.getCustomId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingEntityCustomIdRequiredDTO() throws Exception {
        // Get the entityCustomIdRequiredDTO
        restEntityCustomIdRequiredDTOMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEntityCustomIdRequiredDTO() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRequiredDTO = entityCustomIdRequiredDTORepository.saveAndFlush(entityCustomIdRequiredDTO);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdRequiredDTO
        EntityCustomIdRequiredDTO updatedEntityCustomIdRequiredDTO = entityCustomIdRequiredDTORepository
            .findById(entityCustomIdRequiredDTO.getCustomId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedEntityCustomIdRequiredDTO are not directly saved in db
        em.detach(updatedEntityCustomIdRequiredDTO);
        EntityCustomIdRequiredDTODTO entityCustomIdRequiredDTODTO = entityCustomIdRequiredDTOMapper.toDto(updatedEntityCustomIdRequiredDTO);

        restEntityCustomIdRequiredDTOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityCustomIdRequiredDTODTO.getCustomId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdRequiredDTODTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdRequiredDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedEntityCustomIdRequiredDTOToMatchAllProperties(updatedEntityCustomIdRequiredDTO);
    }

    @Test
    @Transactional
    void putNonExistingEntityCustomIdRequiredDTO() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRequiredDTO.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdRequiredDTO
        EntityCustomIdRequiredDTODTO entityCustomIdRequiredDTODTO = entityCustomIdRequiredDTOMapper.toDto(entityCustomIdRequiredDTO);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityCustomIdRequiredDTOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityCustomIdRequiredDTODTO.getCustomId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdRequiredDTODTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdRequiredDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEntityCustomIdRequiredDTO() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRequiredDTO.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdRequiredDTO
        EntityCustomIdRequiredDTODTO entityCustomIdRequiredDTODTO = entityCustomIdRequiredDTOMapper.toDto(entityCustomIdRequiredDTO);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdRequiredDTOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdRequiredDTODTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdRequiredDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEntityCustomIdRequiredDTO() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRequiredDTO.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdRequiredDTO
        EntityCustomIdRequiredDTODTO entityCustomIdRequiredDTODTO = entityCustomIdRequiredDTOMapper.toDto(entityCustomIdRequiredDTO);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdRequiredDTOMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomIdRequiredDTODTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityCustomIdRequiredDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEntityCustomIdRequiredDTOWithPatch() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRequiredDTO = entityCustomIdRequiredDTORepository.saveAndFlush(entityCustomIdRequiredDTO);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdRequiredDTO using partial update
        EntityCustomIdRequiredDTO partialUpdatedEntityCustomIdRequiredDTO = new EntityCustomIdRequiredDTO();
        partialUpdatedEntityCustomIdRequiredDTO.setCustomId(entityCustomIdRequiredDTO.getCustomId());

        restEntityCustomIdRequiredDTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityCustomIdRequiredDTO.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityCustomIdRequiredDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdRequiredDTO in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityCustomIdRequiredDTOUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedEntityCustomIdRequiredDTO, entityCustomIdRequiredDTO),
            getPersistedEntityCustomIdRequiredDTO(entityCustomIdRequiredDTO)
        );
    }

    @Test
    @Transactional
    void fullUpdateEntityCustomIdRequiredDTOWithPatch() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRequiredDTO = entityCustomIdRequiredDTORepository.saveAndFlush(entityCustomIdRequiredDTO);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdRequiredDTO using partial update
        EntityCustomIdRequiredDTO partialUpdatedEntityCustomIdRequiredDTO = new EntityCustomIdRequiredDTO();
        partialUpdatedEntityCustomIdRequiredDTO.setCustomId(entityCustomIdRequiredDTO.getCustomId());

        restEntityCustomIdRequiredDTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityCustomIdRequiredDTO.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityCustomIdRequiredDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdRequiredDTO in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityCustomIdRequiredDTOUpdatableFieldsEquals(
            partialUpdatedEntityCustomIdRequiredDTO,
            getPersistedEntityCustomIdRequiredDTO(partialUpdatedEntityCustomIdRequiredDTO)
        );
    }

    @Test
    @Transactional
    void patchNonExistingEntityCustomIdRequiredDTO() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRequiredDTO.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdRequiredDTO
        EntityCustomIdRequiredDTODTO entityCustomIdRequiredDTODTO = entityCustomIdRequiredDTOMapper.toDto(entityCustomIdRequiredDTO);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityCustomIdRequiredDTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, entityCustomIdRequiredDTODTO.getCustomId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityCustomIdRequiredDTODTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdRequiredDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEntityCustomIdRequiredDTO() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRequiredDTO.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdRequiredDTO
        EntityCustomIdRequiredDTODTO entityCustomIdRequiredDTODTO = entityCustomIdRequiredDTOMapper.toDto(entityCustomIdRequiredDTO);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdRequiredDTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityCustomIdRequiredDTODTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdRequiredDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEntityCustomIdRequiredDTO() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRequiredDTO.setCustomId(longCount.incrementAndGet());

        // Create the EntityCustomIdRequiredDTO
        EntityCustomIdRequiredDTODTO entityCustomIdRequiredDTODTO = entityCustomIdRequiredDTOMapper.toDto(entityCustomIdRequiredDTO);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdRequiredDTOMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityCustomIdRequiredDTODTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityCustomIdRequiredDTO in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEntityCustomIdRequiredDTO() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRequiredDTO = entityCustomIdRequiredDTORepository.saveAndFlush(entityCustomIdRequiredDTO);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the entityCustomIdRequiredDTO
        restEntityCustomIdRequiredDTOMockMvc
            .perform(delete(ENTITY_API_URL_ID, entityCustomIdRequiredDTO.getCustomId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return entityCustomIdRequiredDTORepository.count();
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

    protected EntityCustomIdRequiredDTO getPersistedEntityCustomIdRequiredDTO(EntityCustomIdRequiredDTO entityCustomIdRequiredDTO) {
        return entityCustomIdRequiredDTORepository.findById(entityCustomIdRequiredDTO.getCustomId()).orElseThrow();
    }

    protected void assertPersistedEntityCustomIdRequiredDTOToMatchAllProperties(
        EntityCustomIdRequiredDTO expectedEntityCustomIdRequiredDTO
    ) {
        assertEntityCustomIdRequiredDTOAllPropertiesEquals(
            expectedEntityCustomIdRequiredDTO,
            getPersistedEntityCustomIdRequiredDTO(expectedEntityCustomIdRequiredDTO)
        );
    }

    protected void assertPersistedEntityCustomIdRequiredDTOToMatchUpdatableProperties(
        EntityCustomIdRequiredDTO expectedEntityCustomIdRequiredDTO
    ) {
        assertEntityCustomIdRequiredDTOAllUpdatablePropertiesEquals(
            expectedEntityCustomIdRequiredDTO,
            getPersistedEntityCustomIdRequiredDTO(expectedEntityCustomIdRequiredDTO)
        );
    }
}
