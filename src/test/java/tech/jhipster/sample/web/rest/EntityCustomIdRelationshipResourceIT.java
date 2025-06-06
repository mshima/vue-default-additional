package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.sample.domain.EntityCustomIdRelationshipAsserts.*;
import static tech.jhipster.sample.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
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
import tech.jhipster.sample.domain.EntityCustomIdRelationship;
import tech.jhipster.sample.repository.EntityCustomIdRelationshipRepository;

/**
 * Integration tests for the {@link EntityCustomIdRelationshipResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class EntityCustomIdRelationshipResourceIT {

    private static final String ENTITY_API_URL = "/api/entity-custom-id-relationships";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{relatedId}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EntityCustomIdRelationshipRepository entityCustomIdRelationshipRepository;

    @Mock
    private EntityCustomIdRelationshipRepository entityCustomIdRelationshipRepositoryMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntityCustomIdRelationshipMockMvc;

    private EntityCustomIdRelationship entityCustomIdRelationship;

    private EntityCustomIdRelationship insertedEntityCustomIdRelationship;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityCustomIdRelationship createEntity() {
        return new EntityCustomIdRelationship();
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityCustomIdRelationship createUpdatedEntity() {
        return new EntityCustomIdRelationship();
    }

    @BeforeEach
    void initTest() {
        entityCustomIdRelationship = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedEntityCustomIdRelationship != null) {
            entityCustomIdRelationshipRepository.delete(insertedEntityCustomIdRelationship);
            insertedEntityCustomIdRelationship = null;
        }
    }

    @Test
    @Transactional
    void createEntityCustomIdRelationship() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the EntityCustomIdRelationship
        var returnedEntityCustomIdRelationship = om.readValue(
            restEntityCustomIdRelationshipMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomIdRelationship))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            EntityCustomIdRelationship.class
        );

        // Validate the EntityCustomIdRelationship in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertEntityCustomIdRelationshipUpdatableFieldsEquals(
            returnedEntityCustomIdRelationship,
            getPersistedEntityCustomIdRelationship(returnedEntityCustomIdRelationship)
        );

        insertedEntityCustomIdRelationship = returnedEntityCustomIdRelationship;
    }

    @Test
    @Transactional
    void createEntityCustomIdRelationshipWithExistingId() throws Exception {
        // Create the EntityCustomIdRelationship with an existing ID
        entityCustomIdRelationship.setRelatedId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityCustomIdRelationshipMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomIdRelationship)))
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEntityCustomIdRelationships() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRelationship = entityCustomIdRelationshipRepository.saveAndFlush(entityCustomIdRelationship);

        // Get all the entityCustomIdRelationshipList
        restEntityCustomIdRelationshipMockMvc
            .perform(get(ENTITY_API_URL + "?sort=relatedId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].relatedId").value(hasItem(entityCustomIdRelationship.getRelatedId().intValue())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllEntityCustomIdRelationshipsWithEagerRelationshipsIsEnabled() throws Exception {
        when(entityCustomIdRelationshipRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restEntityCustomIdRelationshipMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(entityCustomIdRelationshipRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllEntityCustomIdRelationshipsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(entityCustomIdRelationshipRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restEntityCustomIdRelationshipMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(entityCustomIdRelationshipRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getEntityCustomIdRelationship() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRelationship = entityCustomIdRelationshipRepository.saveAndFlush(entityCustomIdRelationship);

        // Get the entityCustomIdRelationship
        restEntityCustomIdRelationshipMockMvc
            .perform(get(ENTITY_API_URL_ID, entityCustomIdRelationship.getRelatedId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.relatedId").value(entityCustomIdRelationship.getRelatedId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingEntityCustomIdRelationship() throws Exception {
        // Get the entityCustomIdRelationship
        restEntityCustomIdRelationshipMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEntityCustomIdRelationship() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRelationship = entityCustomIdRelationshipRepository.saveAndFlush(entityCustomIdRelationship);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdRelationship
        EntityCustomIdRelationship updatedEntityCustomIdRelationship = entityCustomIdRelationshipRepository
            .findById(entityCustomIdRelationship.getRelatedId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedEntityCustomIdRelationship are not directly saved in db
        em.detach(updatedEntityCustomIdRelationship);

        restEntityCustomIdRelationshipMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEntityCustomIdRelationship.getRelatedId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedEntityCustomIdRelationship))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedEntityCustomIdRelationshipToMatchAllProperties(updatedEntityCustomIdRelationship);
    }

    @Test
    @Transactional
    void putNonExistingEntityCustomIdRelationship() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRelationship.setRelatedId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityCustomIdRelationshipMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityCustomIdRelationship.getRelatedId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdRelationship))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEntityCustomIdRelationship() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRelationship.setRelatedId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdRelationshipMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdRelationship))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEntityCustomIdRelationship() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRelationship.setRelatedId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdRelationshipMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomIdRelationship)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityCustomIdRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEntityCustomIdRelationshipWithPatch() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRelationship = entityCustomIdRelationshipRepository.saveAndFlush(entityCustomIdRelationship);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdRelationship using partial update
        EntityCustomIdRelationship partialUpdatedEntityCustomIdRelationship = new EntityCustomIdRelationship();
        partialUpdatedEntityCustomIdRelationship.setRelatedId(entityCustomIdRelationship.getRelatedId());

        restEntityCustomIdRelationshipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityCustomIdRelationship.getRelatedId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityCustomIdRelationship))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdRelationship in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityCustomIdRelationshipUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedEntityCustomIdRelationship, entityCustomIdRelationship),
            getPersistedEntityCustomIdRelationship(entityCustomIdRelationship)
        );
    }

    @Test
    @Transactional
    void fullUpdateEntityCustomIdRelationshipWithPatch() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRelationship = entityCustomIdRelationshipRepository.saveAndFlush(entityCustomIdRelationship);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdRelationship using partial update
        EntityCustomIdRelationship partialUpdatedEntityCustomIdRelationship = new EntityCustomIdRelationship();
        partialUpdatedEntityCustomIdRelationship.setRelatedId(entityCustomIdRelationship.getRelatedId());

        restEntityCustomIdRelationshipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityCustomIdRelationship.getRelatedId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityCustomIdRelationship))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdRelationship in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityCustomIdRelationshipUpdatableFieldsEquals(
            partialUpdatedEntityCustomIdRelationship,
            getPersistedEntityCustomIdRelationship(partialUpdatedEntityCustomIdRelationship)
        );
    }

    @Test
    @Transactional
    void patchNonExistingEntityCustomIdRelationship() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRelationship.setRelatedId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityCustomIdRelationshipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, entityCustomIdRelationship.getRelatedId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityCustomIdRelationship))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEntityCustomIdRelationship() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRelationship.setRelatedId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdRelationshipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityCustomIdRelationship))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEntityCustomIdRelationship() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRelationship.setRelatedId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdRelationshipMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(entityCustomIdRelationship))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityCustomIdRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEntityCustomIdRelationship() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRelationship = entityCustomIdRelationshipRepository.saveAndFlush(entityCustomIdRelationship);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the entityCustomIdRelationship
        restEntityCustomIdRelationshipMockMvc
            .perform(delete(ENTITY_API_URL_ID, entityCustomIdRelationship.getRelatedId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return entityCustomIdRelationshipRepository.count();
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

    protected EntityCustomIdRelationship getPersistedEntityCustomIdRelationship(EntityCustomIdRelationship entityCustomIdRelationship) {
        return entityCustomIdRelationshipRepository.findById(entityCustomIdRelationship.getRelatedId()).orElseThrow();
    }

    protected void assertPersistedEntityCustomIdRelationshipToMatchAllProperties(
        EntityCustomIdRelationship expectedEntityCustomIdRelationship
    ) {
        assertEntityCustomIdRelationshipAllPropertiesEquals(
            expectedEntityCustomIdRelationship,
            getPersistedEntityCustomIdRelationship(expectedEntityCustomIdRelationship)
        );
    }

    protected void assertPersistedEntityCustomIdRelationshipToMatchUpdatableProperties(
        EntityCustomIdRelationship expectedEntityCustomIdRelationship
    ) {
        assertEntityCustomIdRelationshipAllUpdatablePropertiesEquals(
            expectedEntityCustomIdRelationship,
            getPersistedEntityCustomIdRelationship(expectedEntityCustomIdRelationship)
        );
    }
}
