package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.sample.domain.EntityUuidIdDTORelAsserts.*;
import static tech.jhipster.sample.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.sample.IntegrationTest;
import tech.jhipster.sample.domain.EntityUuidIdDTORel;
import tech.jhipster.sample.repository.EntityUuidIdDTORelRepository;
import tech.jhipster.sample.service.EntityUuidIdDTORelService;
import tech.jhipster.sample.service.dto.EntityUuidIdDTORelDTO;
import tech.jhipster.sample.service.mapper.EntityUuidIdDTORelMapper;

/**
 * Integration tests for the {@link EntityUuidIdDTORelResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class EntityUuidIdDTORelResourceIT {

    private static final String ENTITY_API_URL = "/api/entity-uuid-id-dto-rels";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EntityUuidIdDTORelRepository entityUuidIdDTORelRepository;

    @Mock
    private EntityUuidIdDTORelRepository entityUuidIdDTORelRepositoryMock;

    @Autowired
    private EntityUuidIdDTORelMapper entityUuidIdDTORelMapper;

    @Mock
    private EntityUuidIdDTORelService entityUuidIdDTORelServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntityUuidIdDTORelMockMvc;

    private EntityUuidIdDTORel entityUuidIdDTORel;

    private EntityUuidIdDTORel insertedEntityUuidIdDTORel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityUuidIdDTORel createEntity() {
        return new EntityUuidIdDTORel();
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityUuidIdDTORel createUpdatedEntity() {
        return new EntityUuidIdDTORel();
    }

    @BeforeEach
    void initTest() {
        entityUuidIdDTORel = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedEntityUuidIdDTORel != null) {
            entityUuidIdDTORelRepository.delete(insertedEntityUuidIdDTORel);
            insertedEntityUuidIdDTORel = null;
        }
    }

    @Test
    @Transactional
    void createEntityUuidIdDTORel() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the EntityUuidIdDTORel
        EntityUuidIdDTORelDTO entityUuidIdDTORelDTO = entityUuidIdDTORelMapper.toDto(entityUuidIdDTORel);
        var returnedEntityUuidIdDTORelDTO = om.readValue(
            restEntityUuidIdDTORelMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityUuidIdDTORelDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            EntityUuidIdDTORelDTO.class
        );

        // Validate the EntityUuidIdDTORel in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedEntityUuidIdDTORel = entityUuidIdDTORelMapper.toEntity(returnedEntityUuidIdDTORelDTO);
        assertEntityUuidIdDTORelUpdatableFieldsEquals(
            returnedEntityUuidIdDTORel,
            getPersistedEntityUuidIdDTORel(returnedEntityUuidIdDTORel)
        );

        insertedEntityUuidIdDTORel = returnedEntityUuidIdDTORel;
    }

    @Test
    @Transactional
    void createEntityUuidIdDTORelWithExistingId() throws Exception {
        // Create the EntityUuidIdDTORel with an existing ID
        insertedEntityUuidIdDTORel = entityUuidIdDTORelRepository.saveAndFlush(entityUuidIdDTORel);
        EntityUuidIdDTORelDTO entityUuidIdDTORelDTO = entityUuidIdDTORelMapper.toDto(entityUuidIdDTORel);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityUuidIdDTORelMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityUuidIdDTORelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEntityUuidIdDTORels() throws Exception {
        // Initialize the database
        insertedEntityUuidIdDTORel = entityUuidIdDTORelRepository.saveAndFlush(entityUuidIdDTORel);

        // Get all the entityUuidIdDTORelList
        restEntityUuidIdDTORelMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityUuidIdDTORel.getId().toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllEntityUuidIdDTORelsWithEagerRelationshipsIsEnabled() throws Exception {
        when(entityUuidIdDTORelServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restEntityUuidIdDTORelMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(entityUuidIdDTORelServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllEntityUuidIdDTORelsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(entityUuidIdDTORelServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restEntityUuidIdDTORelMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(entityUuidIdDTORelRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getEntityUuidIdDTORel() throws Exception {
        // Initialize the database
        insertedEntityUuidIdDTORel = entityUuidIdDTORelRepository.saveAndFlush(entityUuidIdDTORel);

        // Get the entityUuidIdDTORel
        restEntityUuidIdDTORelMockMvc
            .perform(get(ENTITY_API_URL_ID, entityUuidIdDTORel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entityUuidIdDTORel.getId().toString()));
    }

    @Test
    @Transactional
    void getNonExistingEntityUuidIdDTORel() throws Exception {
        // Get the entityUuidIdDTORel
        restEntityUuidIdDTORelMockMvc.perform(get(ENTITY_API_URL_ID, UUID.randomUUID().toString())).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEntityUuidIdDTORel() throws Exception {
        // Initialize the database
        insertedEntityUuidIdDTORel = entityUuidIdDTORelRepository.saveAndFlush(entityUuidIdDTORel);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityUuidIdDTORel
        EntityUuidIdDTORel updatedEntityUuidIdDTORel = entityUuidIdDTORelRepository.findById(entityUuidIdDTORel.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedEntityUuidIdDTORel are not directly saved in db
        em.detach(updatedEntityUuidIdDTORel);
        EntityUuidIdDTORelDTO entityUuidIdDTORelDTO = entityUuidIdDTORelMapper.toDto(updatedEntityUuidIdDTORel);

        restEntityUuidIdDTORelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityUuidIdDTORelDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityUuidIdDTORelDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityUuidIdDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedEntityUuidIdDTORelToMatchAllProperties(updatedEntityUuidIdDTORel);
    }

    @Test
    @Transactional
    void putNonExistingEntityUuidIdDTORel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdDTORel.setId(UUID.randomUUID());

        // Create the EntityUuidIdDTORel
        EntityUuidIdDTORelDTO entityUuidIdDTORelDTO = entityUuidIdDTORelMapper.toDto(entityUuidIdDTORel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityUuidIdDTORelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityUuidIdDTORelDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityUuidIdDTORelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEntityUuidIdDTORel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdDTORel.setId(UUID.randomUUID());

        // Create the EntityUuidIdDTORel
        EntityUuidIdDTORelDTO entityUuidIdDTORelDTO = entityUuidIdDTORelMapper.toDto(entityUuidIdDTORel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdDTORelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityUuidIdDTORelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEntityUuidIdDTORel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdDTORel.setId(UUID.randomUUID());

        // Create the EntityUuidIdDTORel
        EntityUuidIdDTORelDTO entityUuidIdDTORelDTO = entityUuidIdDTORelMapper.toDto(entityUuidIdDTORel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdDTORelMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityUuidIdDTORelDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityUuidIdDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEntityUuidIdDTORelWithPatch() throws Exception {
        // Initialize the database
        insertedEntityUuidIdDTORel = entityUuidIdDTORelRepository.saveAndFlush(entityUuidIdDTORel);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityUuidIdDTORel using partial update
        EntityUuidIdDTORel partialUpdatedEntityUuidIdDTORel = new EntityUuidIdDTORel();
        partialUpdatedEntityUuidIdDTORel.setId(entityUuidIdDTORel.getId());

        restEntityUuidIdDTORelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityUuidIdDTORel.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityUuidIdDTORel))
            )
            .andExpect(status().isOk());

        // Validate the EntityUuidIdDTORel in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityUuidIdDTORelUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedEntityUuidIdDTORel, entityUuidIdDTORel),
            getPersistedEntityUuidIdDTORel(entityUuidIdDTORel)
        );
    }

    @Test
    @Transactional
    void fullUpdateEntityUuidIdDTORelWithPatch() throws Exception {
        // Initialize the database
        insertedEntityUuidIdDTORel = entityUuidIdDTORelRepository.saveAndFlush(entityUuidIdDTORel);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityUuidIdDTORel using partial update
        EntityUuidIdDTORel partialUpdatedEntityUuidIdDTORel = new EntityUuidIdDTORel();
        partialUpdatedEntityUuidIdDTORel.setId(entityUuidIdDTORel.getId());

        restEntityUuidIdDTORelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityUuidIdDTORel.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityUuidIdDTORel))
            )
            .andExpect(status().isOk());

        // Validate the EntityUuidIdDTORel in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityUuidIdDTORelUpdatableFieldsEquals(
            partialUpdatedEntityUuidIdDTORel,
            getPersistedEntityUuidIdDTORel(partialUpdatedEntityUuidIdDTORel)
        );
    }

    @Test
    @Transactional
    void patchNonExistingEntityUuidIdDTORel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdDTORel.setId(UUID.randomUUID());

        // Create the EntityUuidIdDTORel
        EntityUuidIdDTORelDTO entityUuidIdDTORelDTO = entityUuidIdDTORelMapper.toDto(entityUuidIdDTORel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityUuidIdDTORelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, entityUuidIdDTORelDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityUuidIdDTORelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEntityUuidIdDTORel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdDTORel.setId(UUID.randomUUID());

        // Create the EntityUuidIdDTORel
        EntityUuidIdDTORelDTO entityUuidIdDTORelDTO = entityUuidIdDTORelMapper.toDto(entityUuidIdDTORel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdDTORelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityUuidIdDTORelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEntityUuidIdDTORel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdDTORel.setId(UUID.randomUUID());

        // Create the EntityUuidIdDTORel
        EntityUuidIdDTORelDTO entityUuidIdDTORelDTO = entityUuidIdDTORelMapper.toDto(entityUuidIdDTORel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdDTORelMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(entityUuidIdDTORelDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityUuidIdDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEntityUuidIdDTORel() throws Exception {
        // Initialize the database
        insertedEntityUuidIdDTORel = entityUuidIdDTORelRepository.saveAndFlush(entityUuidIdDTORel);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the entityUuidIdDTORel
        restEntityUuidIdDTORelMockMvc
            .perform(delete(ENTITY_API_URL_ID, entityUuidIdDTORel.getId().toString()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return entityUuidIdDTORelRepository.count();
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

    protected EntityUuidIdDTORel getPersistedEntityUuidIdDTORel(EntityUuidIdDTORel entityUuidIdDTORel) {
        return entityUuidIdDTORelRepository.findById(entityUuidIdDTORel.getId()).orElseThrow();
    }

    protected void assertPersistedEntityUuidIdDTORelToMatchAllProperties(EntityUuidIdDTORel expectedEntityUuidIdDTORel) {
        assertEntityUuidIdDTORelAllPropertiesEquals(expectedEntityUuidIdDTORel, getPersistedEntityUuidIdDTORel(expectedEntityUuidIdDTORel));
    }

    protected void assertPersistedEntityUuidIdDTORelToMatchUpdatableProperties(EntityUuidIdDTORel expectedEntityUuidIdDTORel) {
        assertEntityUuidIdDTORelAllUpdatablePropertiesEquals(
            expectedEntityUuidIdDTORel,
            getPersistedEntityUuidIdDTORel(expectedEntityUuidIdDTORel)
        );
    }
}
