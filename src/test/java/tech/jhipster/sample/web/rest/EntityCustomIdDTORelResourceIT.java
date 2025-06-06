package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.sample.domain.EntityCustomIdDTORelAsserts.*;
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
import tech.jhipster.sample.domain.EntityCustomIdDTORel;
import tech.jhipster.sample.repository.EntityCustomIdDTORelRepository;
import tech.jhipster.sample.service.EntityCustomIdDTORelService;
import tech.jhipster.sample.service.dto.EntityCustomIdDTORelDTO;
import tech.jhipster.sample.service.mapper.EntityCustomIdDTORelMapper;

/**
 * Integration tests for the {@link EntityCustomIdDTORelResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class EntityCustomIdDTORelResourceIT {

    private static final String ENTITY_API_URL = "/api/entity-custom-id-dto-rels";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{relatedId}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EntityCustomIdDTORelRepository entityCustomIdDTORelRepository;

    @Mock
    private EntityCustomIdDTORelRepository entityCustomIdDTORelRepositoryMock;

    @Autowired
    private EntityCustomIdDTORelMapper entityCustomIdDTORelMapper;

    @Mock
    private EntityCustomIdDTORelService entityCustomIdDTORelServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntityCustomIdDTORelMockMvc;

    private EntityCustomIdDTORel entityCustomIdDTORel;

    private EntityCustomIdDTORel insertedEntityCustomIdDTORel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityCustomIdDTORel createEntity() {
        return new EntityCustomIdDTORel();
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityCustomIdDTORel createUpdatedEntity() {
        return new EntityCustomIdDTORel();
    }

    @BeforeEach
    void initTest() {
        entityCustomIdDTORel = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedEntityCustomIdDTORel != null) {
            entityCustomIdDTORelRepository.delete(insertedEntityCustomIdDTORel);
            insertedEntityCustomIdDTORel = null;
        }
    }

    @Test
    @Transactional
    void createEntityCustomIdDTORel() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the EntityCustomIdDTORel
        EntityCustomIdDTORelDTO entityCustomIdDTORelDTO = entityCustomIdDTORelMapper.toDto(entityCustomIdDTORel);
        var returnedEntityCustomIdDTORelDTO = om.readValue(
            restEntityCustomIdDTORelMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomIdDTORelDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            EntityCustomIdDTORelDTO.class
        );

        // Validate the EntityCustomIdDTORel in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedEntityCustomIdDTORel = entityCustomIdDTORelMapper.toEntity(returnedEntityCustomIdDTORelDTO);
        assertEntityCustomIdDTORelUpdatableFieldsEquals(
            returnedEntityCustomIdDTORel,
            getPersistedEntityCustomIdDTORel(returnedEntityCustomIdDTORel)
        );

        insertedEntityCustomIdDTORel = returnedEntityCustomIdDTORel;
    }

    @Test
    @Transactional
    void createEntityCustomIdDTORelWithExistingId() throws Exception {
        // Create the EntityCustomIdDTORel with an existing ID
        entityCustomIdDTORel.setRelatedId(1L);
        EntityCustomIdDTORelDTO entityCustomIdDTORelDTO = entityCustomIdDTORelMapper.toDto(entityCustomIdDTORel);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityCustomIdDTORelMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomIdDTORelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEntityCustomIdDTORels() throws Exception {
        // Initialize the database
        insertedEntityCustomIdDTORel = entityCustomIdDTORelRepository.saveAndFlush(entityCustomIdDTORel);

        // Get all the entityCustomIdDTORelList
        restEntityCustomIdDTORelMockMvc
            .perform(get(ENTITY_API_URL + "?sort=relatedId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].relatedId").value(hasItem(entityCustomIdDTORel.getRelatedId().intValue())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllEntityCustomIdDTORelsWithEagerRelationshipsIsEnabled() throws Exception {
        when(entityCustomIdDTORelServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restEntityCustomIdDTORelMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(entityCustomIdDTORelServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllEntityCustomIdDTORelsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(entityCustomIdDTORelServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restEntityCustomIdDTORelMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(entityCustomIdDTORelRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getEntityCustomIdDTORel() throws Exception {
        // Initialize the database
        insertedEntityCustomIdDTORel = entityCustomIdDTORelRepository.saveAndFlush(entityCustomIdDTORel);

        // Get the entityCustomIdDTORel
        restEntityCustomIdDTORelMockMvc
            .perform(get(ENTITY_API_URL_ID, entityCustomIdDTORel.getRelatedId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.relatedId").value(entityCustomIdDTORel.getRelatedId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingEntityCustomIdDTORel() throws Exception {
        // Get the entityCustomIdDTORel
        restEntityCustomIdDTORelMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEntityCustomIdDTORel() throws Exception {
        // Initialize the database
        insertedEntityCustomIdDTORel = entityCustomIdDTORelRepository.saveAndFlush(entityCustomIdDTORel);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdDTORel
        EntityCustomIdDTORel updatedEntityCustomIdDTORel = entityCustomIdDTORelRepository
            .findById(entityCustomIdDTORel.getRelatedId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedEntityCustomIdDTORel are not directly saved in db
        em.detach(updatedEntityCustomIdDTORel);
        EntityCustomIdDTORelDTO entityCustomIdDTORelDTO = entityCustomIdDTORelMapper.toDto(updatedEntityCustomIdDTORel);

        restEntityCustomIdDTORelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityCustomIdDTORelDTO.getRelatedId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdDTORelDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedEntityCustomIdDTORelToMatchAllProperties(updatedEntityCustomIdDTORel);
    }

    @Test
    @Transactional
    void putNonExistingEntityCustomIdDTORel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdDTORel.setRelatedId(longCount.incrementAndGet());

        // Create the EntityCustomIdDTORel
        EntityCustomIdDTORelDTO entityCustomIdDTORelDTO = entityCustomIdDTORelMapper.toDto(entityCustomIdDTORel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityCustomIdDTORelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityCustomIdDTORelDTO.getRelatedId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdDTORelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEntityCustomIdDTORel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdDTORel.setRelatedId(longCount.incrementAndGet());

        // Create the EntityCustomIdDTORel
        EntityCustomIdDTORelDTO entityCustomIdDTORelDTO = entityCustomIdDTORelMapper.toDto(entityCustomIdDTORel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdDTORelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdDTORelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEntityCustomIdDTORel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdDTORel.setRelatedId(longCount.incrementAndGet());

        // Create the EntityCustomIdDTORel
        EntityCustomIdDTORelDTO entityCustomIdDTORelDTO = entityCustomIdDTORelMapper.toDto(entityCustomIdDTORel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdDTORelMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomIdDTORelDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityCustomIdDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEntityCustomIdDTORelWithPatch() throws Exception {
        // Initialize the database
        insertedEntityCustomIdDTORel = entityCustomIdDTORelRepository.saveAndFlush(entityCustomIdDTORel);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdDTORel using partial update
        EntityCustomIdDTORel partialUpdatedEntityCustomIdDTORel = new EntityCustomIdDTORel();
        partialUpdatedEntityCustomIdDTORel.setRelatedId(entityCustomIdDTORel.getRelatedId());

        restEntityCustomIdDTORelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityCustomIdDTORel.getRelatedId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityCustomIdDTORel))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdDTORel in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityCustomIdDTORelUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedEntityCustomIdDTORel, entityCustomIdDTORel),
            getPersistedEntityCustomIdDTORel(entityCustomIdDTORel)
        );
    }

    @Test
    @Transactional
    void fullUpdateEntityCustomIdDTORelWithPatch() throws Exception {
        // Initialize the database
        insertedEntityCustomIdDTORel = entityCustomIdDTORelRepository.saveAndFlush(entityCustomIdDTORel);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdDTORel using partial update
        EntityCustomIdDTORel partialUpdatedEntityCustomIdDTORel = new EntityCustomIdDTORel();
        partialUpdatedEntityCustomIdDTORel.setRelatedId(entityCustomIdDTORel.getRelatedId());

        restEntityCustomIdDTORelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityCustomIdDTORel.getRelatedId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityCustomIdDTORel))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdDTORel in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityCustomIdDTORelUpdatableFieldsEquals(
            partialUpdatedEntityCustomIdDTORel,
            getPersistedEntityCustomIdDTORel(partialUpdatedEntityCustomIdDTORel)
        );
    }

    @Test
    @Transactional
    void patchNonExistingEntityCustomIdDTORel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdDTORel.setRelatedId(longCount.incrementAndGet());

        // Create the EntityCustomIdDTORel
        EntityCustomIdDTORelDTO entityCustomIdDTORelDTO = entityCustomIdDTORelMapper.toDto(entityCustomIdDTORel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityCustomIdDTORelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, entityCustomIdDTORelDTO.getRelatedId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityCustomIdDTORelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEntityCustomIdDTORel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdDTORel.setRelatedId(longCount.incrementAndGet());

        // Create the EntityCustomIdDTORel
        EntityCustomIdDTORelDTO entityCustomIdDTORelDTO = entityCustomIdDTORelMapper.toDto(entityCustomIdDTORel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdDTORelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityCustomIdDTORelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEntityCustomIdDTORel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdDTORel.setRelatedId(longCount.incrementAndGet());

        // Create the EntityCustomIdDTORel
        EntityCustomIdDTORelDTO entityCustomIdDTORelDTO = entityCustomIdDTORelMapper.toDto(entityCustomIdDTORel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdDTORelMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(entityCustomIdDTORelDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityCustomIdDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEntityCustomIdDTORel() throws Exception {
        // Initialize the database
        insertedEntityCustomIdDTORel = entityCustomIdDTORelRepository.saveAndFlush(entityCustomIdDTORel);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the entityCustomIdDTORel
        restEntityCustomIdDTORelMockMvc
            .perform(delete(ENTITY_API_URL_ID, entityCustomIdDTORel.getRelatedId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return entityCustomIdDTORelRepository.count();
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

    protected EntityCustomIdDTORel getPersistedEntityCustomIdDTORel(EntityCustomIdDTORel entityCustomIdDTORel) {
        return entityCustomIdDTORelRepository.findById(entityCustomIdDTORel.getRelatedId()).orElseThrow();
    }

    protected void assertPersistedEntityCustomIdDTORelToMatchAllProperties(EntityCustomIdDTORel expectedEntityCustomIdDTORel) {
        assertEntityCustomIdDTORelAllPropertiesEquals(
            expectedEntityCustomIdDTORel,
            getPersistedEntityCustomIdDTORel(expectedEntityCustomIdDTORel)
        );
    }

    protected void assertPersistedEntityCustomIdDTORelToMatchUpdatableProperties(EntityCustomIdDTORel expectedEntityCustomIdDTORel) {
        assertEntityCustomIdDTORelAllUpdatablePropertiesEquals(
            expectedEntityCustomIdDTORel,
            getPersistedEntityCustomIdDTORel(expectedEntityCustomIdDTORel)
        );
    }
}
