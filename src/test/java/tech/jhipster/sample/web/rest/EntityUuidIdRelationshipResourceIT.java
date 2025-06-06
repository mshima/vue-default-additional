package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.sample.domain.EntityUuidIdRelationshipAsserts.*;
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
import tech.jhipster.sample.domain.EntityUuidIdRelationship;
import tech.jhipster.sample.repository.EntityUuidIdRelationshipRepository;

/**
 * Integration tests for the {@link EntityUuidIdRelationshipResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class EntityUuidIdRelationshipResourceIT {

    private static final String ENTITY_API_URL = "/api/entity-uuid-id-relationships";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EntityUuidIdRelationshipRepository entityUuidIdRelationshipRepository;

    @Mock
    private EntityUuidIdRelationshipRepository entityUuidIdRelationshipRepositoryMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntityUuidIdRelationshipMockMvc;

    private EntityUuidIdRelationship entityUuidIdRelationship;

    private EntityUuidIdRelationship insertedEntityUuidIdRelationship;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityUuidIdRelationship createEntity() {
        return new EntityUuidIdRelationship();
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityUuidIdRelationship createUpdatedEntity() {
        return new EntityUuidIdRelationship();
    }

    @BeforeEach
    void initTest() {
        entityUuidIdRelationship = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedEntityUuidIdRelationship != null) {
            entityUuidIdRelationshipRepository.delete(insertedEntityUuidIdRelationship);
            insertedEntityUuidIdRelationship = null;
        }
    }

    @Test
    @Transactional
    void createEntityUuidIdRelationship() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the EntityUuidIdRelationship
        var returnedEntityUuidIdRelationship = om.readValue(
            restEntityUuidIdRelationshipMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityUuidIdRelationship))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            EntityUuidIdRelationship.class
        );

        // Validate the EntityUuidIdRelationship in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertEntityUuidIdRelationshipUpdatableFieldsEquals(
            returnedEntityUuidIdRelationship,
            getPersistedEntityUuidIdRelationship(returnedEntityUuidIdRelationship)
        );

        insertedEntityUuidIdRelationship = returnedEntityUuidIdRelationship;
    }

    @Test
    @Transactional
    void createEntityUuidIdRelationshipWithExistingId() throws Exception {
        // Create the EntityUuidIdRelationship with an existing ID
        insertedEntityUuidIdRelationship = entityUuidIdRelationshipRepository.saveAndFlush(entityUuidIdRelationship);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityUuidIdRelationshipMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityUuidIdRelationship)))
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEntityUuidIdRelationships() throws Exception {
        // Initialize the database
        insertedEntityUuidIdRelationship = entityUuidIdRelationshipRepository.saveAndFlush(entityUuidIdRelationship);

        // Get all the entityUuidIdRelationshipList
        restEntityUuidIdRelationshipMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityUuidIdRelationship.getId().toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllEntityUuidIdRelationshipsWithEagerRelationshipsIsEnabled() throws Exception {
        when(entityUuidIdRelationshipRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restEntityUuidIdRelationshipMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(entityUuidIdRelationshipRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllEntityUuidIdRelationshipsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(entityUuidIdRelationshipRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restEntityUuidIdRelationshipMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(entityUuidIdRelationshipRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getEntityUuidIdRelationship() throws Exception {
        // Initialize the database
        insertedEntityUuidIdRelationship = entityUuidIdRelationshipRepository.saveAndFlush(entityUuidIdRelationship);

        // Get the entityUuidIdRelationship
        restEntityUuidIdRelationshipMockMvc
            .perform(get(ENTITY_API_URL_ID, entityUuidIdRelationship.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entityUuidIdRelationship.getId().toString()));
    }

    @Test
    @Transactional
    void getNonExistingEntityUuidIdRelationship() throws Exception {
        // Get the entityUuidIdRelationship
        restEntityUuidIdRelationshipMockMvc.perform(get(ENTITY_API_URL_ID, UUID.randomUUID().toString())).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEntityUuidIdRelationship() throws Exception {
        // Initialize the database
        insertedEntityUuidIdRelationship = entityUuidIdRelationshipRepository.saveAndFlush(entityUuidIdRelationship);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityUuidIdRelationship
        EntityUuidIdRelationship updatedEntityUuidIdRelationship = entityUuidIdRelationshipRepository
            .findById(entityUuidIdRelationship.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedEntityUuidIdRelationship are not directly saved in db
        em.detach(updatedEntityUuidIdRelationship);

        restEntityUuidIdRelationshipMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEntityUuidIdRelationship.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedEntityUuidIdRelationship))
            )
            .andExpect(status().isOk());

        // Validate the EntityUuidIdRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedEntityUuidIdRelationshipToMatchAllProperties(updatedEntityUuidIdRelationship);
    }

    @Test
    @Transactional
    void putNonExistingEntityUuidIdRelationship() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdRelationship.setId(UUID.randomUUID());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityUuidIdRelationshipMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityUuidIdRelationship.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityUuidIdRelationship))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEntityUuidIdRelationship() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdRelationship.setId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdRelationshipMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityUuidIdRelationship))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEntityUuidIdRelationship() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdRelationship.setId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdRelationshipMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityUuidIdRelationship)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityUuidIdRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEntityUuidIdRelationshipWithPatch() throws Exception {
        // Initialize the database
        insertedEntityUuidIdRelationship = entityUuidIdRelationshipRepository.saveAndFlush(entityUuidIdRelationship);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityUuidIdRelationship using partial update
        EntityUuidIdRelationship partialUpdatedEntityUuidIdRelationship = new EntityUuidIdRelationship();
        partialUpdatedEntityUuidIdRelationship.setId(entityUuidIdRelationship.getId());

        restEntityUuidIdRelationshipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityUuidIdRelationship.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityUuidIdRelationship))
            )
            .andExpect(status().isOk());

        // Validate the EntityUuidIdRelationship in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityUuidIdRelationshipUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedEntityUuidIdRelationship, entityUuidIdRelationship),
            getPersistedEntityUuidIdRelationship(entityUuidIdRelationship)
        );
    }

    @Test
    @Transactional
    void fullUpdateEntityUuidIdRelationshipWithPatch() throws Exception {
        // Initialize the database
        insertedEntityUuidIdRelationship = entityUuidIdRelationshipRepository.saveAndFlush(entityUuidIdRelationship);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityUuidIdRelationship using partial update
        EntityUuidIdRelationship partialUpdatedEntityUuidIdRelationship = new EntityUuidIdRelationship();
        partialUpdatedEntityUuidIdRelationship.setId(entityUuidIdRelationship.getId());

        restEntityUuidIdRelationshipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityUuidIdRelationship.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityUuidIdRelationship))
            )
            .andExpect(status().isOk());

        // Validate the EntityUuidIdRelationship in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityUuidIdRelationshipUpdatableFieldsEquals(
            partialUpdatedEntityUuidIdRelationship,
            getPersistedEntityUuidIdRelationship(partialUpdatedEntityUuidIdRelationship)
        );
    }

    @Test
    @Transactional
    void patchNonExistingEntityUuidIdRelationship() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdRelationship.setId(UUID.randomUUID());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityUuidIdRelationshipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, entityUuidIdRelationship.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityUuidIdRelationship))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEntityUuidIdRelationship() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdRelationship.setId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdRelationshipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityUuidIdRelationship))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityUuidIdRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEntityUuidIdRelationship() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityUuidIdRelationship.setId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityUuidIdRelationshipMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(entityUuidIdRelationship))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityUuidIdRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEntityUuidIdRelationship() throws Exception {
        // Initialize the database
        insertedEntityUuidIdRelationship = entityUuidIdRelationshipRepository.saveAndFlush(entityUuidIdRelationship);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the entityUuidIdRelationship
        restEntityUuidIdRelationshipMockMvc
            .perform(delete(ENTITY_API_URL_ID, entityUuidIdRelationship.getId().toString()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return entityUuidIdRelationshipRepository.count();
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

    protected EntityUuidIdRelationship getPersistedEntityUuidIdRelationship(EntityUuidIdRelationship entityUuidIdRelationship) {
        return entityUuidIdRelationshipRepository.findById(entityUuidIdRelationship.getId()).orElseThrow();
    }

    protected void assertPersistedEntityUuidIdRelationshipToMatchAllProperties(EntityUuidIdRelationship expectedEntityUuidIdRelationship) {
        assertEntityUuidIdRelationshipAllPropertiesEquals(
            expectedEntityUuidIdRelationship,
            getPersistedEntityUuidIdRelationship(expectedEntityUuidIdRelationship)
        );
    }

    protected void assertPersistedEntityUuidIdRelationshipToMatchUpdatableProperties(
        EntityUuidIdRelationship expectedEntityUuidIdRelationship
    ) {
        assertEntityUuidIdRelationshipAllUpdatablePropertiesEquals(
            expectedEntityUuidIdRelationship,
            getPersistedEntityUuidIdRelationship(expectedEntityUuidIdRelationship)
        );
    }
}
