package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.sample.domain.EntityCustomIdRequiredDTORelAsserts.*;
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
import tech.jhipster.sample.domain.EntityCustomIdRequiredDTO;
import tech.jhipster.sample.domain.EntityCustomIdRequiredDTOMapsId;
import tech.jhipster.sample.domain.EntityCustomIdRequiredDTORel;
import tech.jhipster.sample.repository.EntityCustomIdRequiredDTORelRepository;
import tech.jhipster.sample.service.EntityCustomIdRequiredDTORelService;
import tech.jhipster.sample.service.dto.EntityCustomIdRequiredDTORelDTO;
import tech.jhipster.sample.service.mapper.EntityCustomIdRequiredDTORelMapper;

/**
 * Integration tests for the {@link EntityCustomIdRequiredDTORelResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class EntityCustomIdRequiredDTORelResourceIT {

    private static final String ENTITY_API_URL = "/api/entity-custom-id-required-dto-rels";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{relatedId}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EntityCustomIdRequiredDTORelRepository entityCustomIdRequiredDTORelRepository;

    @Mock
    private EntityCustomIdRequiredDTORelRepository entityCustomIdRequiredDTORelRepositoryMock;

    @Autowired
    private EntityCustomIdRequiredDTORelMapper entityCustomIdRequiredDTORelMapper;

    @Mock
    private EntityCustomIdRequiredDTORelService entityCustomIdRequiredDTORelServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntityCustomIdRequiredDTORelMockMvc;

    private EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel;

    private EntityCustomIdRequiredDTORel insertedEntityCustomIdRequiredDTORel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityCustomIdRequiredDTORel createEntity(EntityManager em) {
        EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel = new EntityCustomIdRequiredDTORel();
        // Add required entity
        EntityCustomIdRequiredDTO entityCustomIdRequiredDTO;
        if (TestUtil.findAll(em, EntityCustomIdRequiredDTO.class).isEmpty()) {
            entityCustomIdRequiredDTO = EntityCustomIdRequiredDTOResourceIT.createEntity();
            em.persist(entityCustomIdRequiredDTO);
            em.flush();
        } else {
            entityCustomIdRequiredDTO = TestUtil.findAll(em, EntityCustomIdRequiredDTO.class).get(0);
        }
        entityCustomIdRequiredDTORel.setOneToOne(entityCustomIdRequiredDTO);
        // Add required entity
        EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsId;
        if (TestUtil.findAll(em, EntityCustomIdRequiredDTOMapsId.class).isEmpty()) {
            entityCustomIdRequiredDTOMapsId = EntityCustomIdRequiredDTOMapsIdResourceIT.createEntity(em);
            em.persist(entityCustomIdRequiredDTOMapsId);
            em.flush();
        } else {
            entityCustomIdRequiredDTOMapsId = TestUtil.findAll(em, EntityCustomIdRequiredDTOMapsId.class).get(0);
        }
        entityCustomIdRequiredDTORel.setOneToOneMapsId(entityCustomIdRequiredDTOMapsId);
        // Add required entity
        entityCustomIdRequiredDTORel.setManyToOne(entityCustomIdRequiredDTO);
        // Add required entity
        entityCustomIdRequiredDTORel.setManyToOneMapsId(entityCustomIdRequiredDTOMapsId);
        // Add required entity
        entityCustomIdRequiredDTORel.getManyToManies().add(entityCustomIdRequiredDTO);
        // Add required entity
        entityCustomIdRequiredDTORel.getManyToManyMapsIds().add(entityCustomIdRequiredDTOMapsId);
        return entityCustomIdRequiredDTORel;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityCustomIdRequiredDTORel createUpdatedEntity(EntityManager em) {
        EntityCustomIdRequiredDTORel updatedEntityCustomIdRequiredDTORel = new EntityCustomIdRequiredDTORel();
        // Add required entity
        EntityCustomIdRequiredDTO entityCustomIdRequiredDTO;
        if (TestUtil.findAll(em, EntityCustomIdRequiredDTO.class).isEmpty()) {
            entityCustomIdRequiredDTO = EntityCustomIdRequiredDTOResourceIT.createUpdatedEntity();
            em.persist(entityCustomIdRequiredDTO);
            em.flush();
        } else {
            entityCustomIdRequiredDTO = TestUtil.findAll(em, EntityCustomIdRequiredDTO.class).get(0);
        }
        updatedEntityCustomIdRequiredDTORel.setOneToOne(entityCustomIdRequiredDTO);
        // Add required entity
        EntityCustomIdRequiredDTOMapsId entityCustomIdRequiredDTOMapsId;
        if (TestUtil.findAll(em, EntityCustomIdRequiredDTOMapsId.class).isEmpty()) {
            entityCustomIdRequiredDTOMapsId = EntityCustomIdRequiredDTOMapsIdResourceIT.createUpdatedEntity(em);
            em.persist(entityCustomIdRequiredDTOMapsId);
            em.flush();
        } else {
            entityCustomIdRequiredDTOMapsId = TestUtil.findAll(em, EntityCustomIdRequiredDTOMapsId.class).get(0);
        }
        updatedEntityCustomIdRequiredDTORel.setOneToOneMapsId(entityCustomIdRequiredDTOMapsId);
        // Add required entity
        updatedEntityCustomIdRequiredDTORel.setManyToOne(entityCustomIdRequiredDTO);
        // Add required entity
        updatedEntityCustomIdRequiredDTORel.setManyToOneMapsId(entityCustomIdRequiredDTOMapsId);
        // Add required entity
        updatedEntityCustomIdRequiredDTORel.getManyToManies().add(entityCustomIdRequiredDTO);
        // Add required entity
        updatedEntityCustomIdRequiredDTORel.getManyToManyMapsIds().add(entityCustomIdRequiredDTOMapsId);
        return updatedEntityCustomIdRequiredDTORel;
    }

    @BeforeEach
    void initTest() {
        entityCustomIdRequiredDTORel = createEntity(em);
    }

    @AfterEach
    void cleanup() {
        if (insertedEntityCustomIdRequiredDTORel != null) {
            entityCustomIdRequiredDTORelRepository.delete(insertedEntityCustomIdRequiredDTORel);
            insertedEntityCustomIdRequiredDTORel = null;
        }
    }

    @Test
    @Transactional
    void createEntityCustomIdRequiredDTORel() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the EntityCustomIdRequiredDTORel
        EntityCustomIdRequiredDTORelDTO entityCustomIdRequiredDTORelDTO = entityCustomIdRequiredDTORelMapper.toDto(
            entityCustomIdRequiredDTORel
        );
        var returnedEntityCustomIdRequiredDTORelDTO = om.readValue(
            restEntityCustomIdRequiredDTORelMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(entityCustomIdRequiredDTORelDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            EntityCustomIdRequiredDTORelDTO.class
        );

        // Validate the EntityCustomIdRequiredDTORel in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedEntityCustomIdRequiredDTORel = entityCustomIdRequiredDTORelMapper.toEntity(returnedEntityCustomIdRequiredDTORelDTO);
        assertEntityCustomIdRequiredDTORelUpdatableFieldsEquals(
            returnedEntityCustomIdRequiredDTORel,
            getPersistedEntityCustomIdRequiredDTORel(returnedEntityCustomIdRequiredDTORel)
        );

        insertedEntityCustomIdRequiredDTORel = returnedEntityCustomIdRequiredDTORel;
    }

    @Test
    @Transactional
    void createEntityCustomIdRequiredDTORelWithExistingId() throws Exception {
        // Create the EntityCustomIdRequiredDTORel with an existing ID
        entityCustomIdRequiredDTORel.setRelatedId(1L);
        EntityCustomIdRequiredDTORelDTO entityCustomIdRequiredDTORelDTO = entityCustomIdRequiredDTORelMapper.toDto(
            entityCustomIdRequiredDTORel
        );

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityCustomIdRequiredDTORelMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomIdRequiredDTORelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdRequiredDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEntityCustomIdRequiredDTORels() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRequiredDTORel = entityCustomIdRequiredDTORelRepository.saveAndFlush(entityCustomIdRequiredDTORel);

        // Get all the entityCustomIdRequiredDTORelList
        restEntityCustomIdRequiredDTORelMockMvc
            .perform(get(ENTITY_API_URL + "?sort=relatedId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].relatedId").value(hasItem(entityCustomIdRequiredDTORel.getRelatedId().intValue())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllEntityCustomIdRequiredDTORelsWithEagerRelationshipsIsEnabled() throws Exception {
        when(entityCustomIdRequiredDTORelServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restEntityCustomIdRequiredDTORelMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(entityCustomIdRequiredDTORelServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllEntityCustomIdRequiredDTORelsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(entityCustomIdRequiredDTORelServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restEntityCustomIdRequiredDTORelMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(entityCustomIdRequiredDTORelRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getEntityCustomIdRequiredDTORel() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRequiredDTORel = entityCustomIdRequiredDTORelRepository.saveAndFlush(entityCustomIdRequiredDTORel);

        // Get the entityCustomIdRequiredDTORel
        restEntityCustomIdRequiredDTORelMockMvc
            .perform(get(ENTITY_API_URL_ID, entityCustomIdRequiredDTORel.getRelatedId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.relatedId").value(entityCustomIdRequiredDTORel.getRelatedId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingEntityCustomIdRequiredDTORel() throws Exception {
        // Get the entityCustomIdRequiredDTORel
        restEntityCustomIdRequiredDTORelMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEntityCustomIdRequiredDTORel() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRequiredDTORel = entityCustomIdRequiredDTORelRepository.saveAndFlush(entityCustomIdRequiredDTORel);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdRequiredDTORel
        EntityCustomIdRequiredDTORel updatedEntityCustomIdRequiredDTORel = entityCustomIdRequiredDTORelRepository
            .findById(entityCustomIdRequiredDTORel.getRelatedId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedEntityCustomIdRequiredDTORel are not directly saved in db
        em.detach(updatedEntityCustomIdRequiredDTORel);
        EntityCustomIdRequiredDTORelDTO entityCustomIdRequiredDTORelDTO = entityCustomIdRequiredDTORelMapper.toDto(
            updatedEntityCustomIdRequiredDTORel
        );

        restEntityCustomIdRequiredDTORelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityCustomIdRequiredDTORelDTO.getRelatedId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdRequiredDTORelDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdRequiredDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedEntityCustomIdRequiredDTORelToMatchAllProperties(updatedEntityCustomIdRequiredDTORel);
    }

    @Test
    @Transactional
    void putNonExistingEntityCustomIdRequiredDTORel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRequiredDTORel.setRelatedId(longCount.incrementAndGet());

        // Create the EntityCustomIdRequiredDTORel
        EntityCustomIdRequiredDTORelDTO entityCustomIdRequiredDTORelDTO = entityCustomIdRequiredDTORelMapper.toDto(
            entityCustomIdRequiredDTORel
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityCustomIdRequiredDTORelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityCustomIdRequiredDTORelDTO.getRelatedId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdRequiredDTORelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdRequiredDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEntityCustomIdRequiredDTORel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRequiredDTORel.setRelatedId(longCount.incrementAndGet());

        // Create the EntityCustomIdRequiredDTORel
        EntityCustomIdRequiredDTORelDTO entityCustomIdRequiredDTORelDTO = entityCustomIdRequiredDTORelMapper.toDto(
            entityCustomIdRequiredDTORel
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdRequiredDTORelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(entityCustomIdRequiredDTORelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdRequiredDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEntityCustomIdRequiredDTORel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRequiredDTORel.setRelatedId(longCount.incrementAndGet());

        // Create the EntityCustomIdRequiredDTORel
        EntityCustomIdRequiredDTORelDTO entityCustomIdRequiredDTORelDTO = entityCustomIdRequiredDTORelMapper.toDto(
            entityCustomIdRequiredDTORel
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdRequiredDTORelMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(entityCustomIdRequiredDTORelDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityCustomIdRequiredDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEntityCustomIdRequiredDTORelWithPatch() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRequiredDTORel = entityCustomIdRequiredDTORelRepository.saveAndFlush(entityCustomIdRequiredDTORel);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdRequiredDTORel using partial update
        EntityCustomIdRequiredDTORel partialUpdatedEntityCustomIdRequiredDTORel = new EntityCustomIdRequiredDTORel();
        partialUpdatedEntityCustomIdRequiredDTORel.setRelatedId(entityCustomIdRequiredDTORel.getRelatedId());

        restEntityCustomIdRequiredDTORelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityCustomIdRequiredDTORel.getRelatedId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityCustomIdRequiredDTORel))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdRequiredDTORel in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityCustomIdRequiredDTORelUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedEntityCustomIdRequiredDTORel, entityCustomIdRequiredDTORel),
            getPersistedEntityCustomIdRequiredDTORel(entityCustomIdRequiredDTORel)
        );
    }

    @Test
    @Transactional
    void fullUpdateEntityCustomIdRequiredDTORelWithPatch() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRequiredDTORel = entityCustomIdRequiredDTORelRepository.saveAndFlush(entityCustomIdRequiredDTORel);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the entityCustomIdRequiredDTORel using partial update
        EntityCustomIdRequiredDTORel partialUpdatedEntityCustomIdRequiredDTORel = new EntityCustomIdRequiredDTORel();
        partialUpdatedEntityCustomIdRequiredDTORel.setRelatedId(entityCustomIdRequiredDTORel.getRelatedId());

        restEntityCustomIdRequiredDTORelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityCustomIdRequiredDTORel.getRelatedId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEntityCustomIdRequiredDTORel))
            )
            .andExpect(status().isOk());

        // Validate the EntityCustomIdRequiredDTORel in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEntityCustomIdRequiredDTORelUpdatableFieldsEquals(
            partialUpdatedEntityCustomIdRequiredDTORel,
            getPersistedEntityCustomIdRequiredDTORel(partialUpdatedEntityCustomIdRequiredDTORel)
        );
    }

    @Test
    @Transactional
    void patchNonExistingEntityCustomIdRequiredDTORel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRequiredDTORel.setRelatedId(longCount.incrementAndGet());

        // Create the EntityCustomIdRequiredDTORel
        EntityCustomIdRequiredDTORelDTO entityCustomIdRequiredDTORelDTO = entityCustomIdRequiredDTORelMapper.toDto(
            entityCustomIdRequiredDTORel
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityCustomIdRequiredDTORelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, entityCustomIdRequiredDTORelDTO.getRelatedId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityCustomIdRequiredDTORelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdRequiredDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEntityCustomIdRequiredDTORel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRequiredDTORel.setRelatedId(longCount.incrementAndGet());

        // Create the EntityCustomIdRequiredDTORel
        EntityCustomIdRequiredDTORelDTO entityCustomIdRequiredDTORelDTO = entityCustomIdRequiredDTORelMapper.toDto(
            entityCustomIdRequiredDTORel
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdRequiredDTORelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityCustomIdRequiredDTORelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityCustomIdRequiredDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEntityCustomIdRequiredDTORel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        entityCustomIdRequiredDTORel.setRelatedId(longCount.incrementAndGet());

        // Create the EntityCustomIdRequiredDTORel
        EntityCustomIdRequiredDTORelDTO entityCustomIdRequiredDTORelDTO = entityCustomIdRequiredDTORelMapper.toDto(
            entityCustomIdRequiredDTORel
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityCustomIdRequiredDTORelMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(entityCustomIdRequiredDTORelDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityCustomIdRequiredDTORel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEntityCustomIdRequiredDTORel() throws Exception {
        // Initialize the database
        insertedEntityCustomIdRequiredDTORel = entityCustomIdRequiredDTORelRepository.saveAndFlush(entityCustomIdRequiredDTORel);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the entityCustomIdRequiredDTORel
        restEntityCustomIdRequiredDTORelMockMvc
            .perform(delete(ENTITY_API_URL_ID, entityCustomIdRequiredDTORel.getRelatedId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return entityCustomIdRequiredDTORelRepository.count();
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

    protected EntityCustomIdRequiredDTORel getPersistedEntityCustomIdRequiredDTORel(
        EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel
    ) {
        return entityCustomIdRequiredDTORelRepository.findById(entityCustomIdRequiredDTORel.getRelatedId()).orElseThrow();
    }

    protected void assertPersistedEntityCustomIdRequiredDTORelToMatchAllProperties(
        EntityCustomIdRequiredDTORel expectedEntityCustomIdRequiredDTORel
    ) {
        assertEntityCustomIdRequiredDTORelAllPropertiesEquals(
            expectedEntityCustomIdRequiredDTORel,
            getPersistedEntityCustomIdRequiredDTORel(expectedEntityCustomIdRequiredDTORel)
        );
    }

    protected void assertPersistedEntityCustomIdRequiredDTORelToMatchUpdatableProperties(
        EntityCustomIdRequiredDTORel expectedEntityCustomIdRequiredDTORel
    ) {
        assertEntityCustomIdRequiredDTORelAllUpdatablePropertiesEquals(
            expectedEntityCustomIdRequiredDTORel,
            getPersistedEntityCustomIdRequiredDTORel(expectedEntityCustomIdRequiredDTORel)
        );
    }
}
