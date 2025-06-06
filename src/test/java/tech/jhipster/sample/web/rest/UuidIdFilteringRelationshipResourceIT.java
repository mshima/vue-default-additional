package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.sample.domain.UuidIdFilteringRelationshipAsserts.*;
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
import tech.jhipster.sample.domain.UuidIdFiltering;
import tech.jhipster.sample.domain.UuidIdFilteringMapsId;
import tech.jhipster.sample.domain.UuidIdFilteringRelationship;
import tech.jhipster.sample.repository.UuidIdFilteringRelationshipRepository;
import tech.jhipster.sample.service.UuidIdFilteringRelationshipService;

/**
 * Integration tests for the {@link UuidIdFilteringRelationshipResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class UuidIdFilteringRelationshipResourceIT {

    private static final String ENTITY_API_URL = "/api/uuid-id-filtering-relationships";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{relatedId}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private UuidIdFilteringRelationshipRepository uuidIdFilteringRelationshipRepository;

    @Mock
    private UuidIdFilteringRelationshipRepository uuidIdFilteringRelationshipRepositoryMock;

    @Mock
    private UuidIdFilteringRelationshipService uuidIdFilteringRelationshipServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUuidIdFilteringRelationshipMockMvc;

    private UuidIdFilteringRelationship uuidIdFilteringRelationship;

    private UuidIdFilteringRelationship insertedUuidIdFilteringRelationship;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UuidIdFilteringRelationship createEntity() {
        return new UuidIdFilteringRelationship();
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UuidIdFilteringRelationship createUpdatedEntity() {
        return new UuidIdFilteringRelationship();
    }

    @BeforeEach
    void initTest() {
        uuidIdFilteringRelationship = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedUuidIdFilteringRelationship != null) {
            uuidIdFilteringRelationshipRepository.delete(insertedUuidIdFilteringRelationship);
            insertedUuidIdFilteringRelationship = null;
        }
    }

    @Test
    @Transactional
    void createUuidIdFilteringRelationship() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the UuidIdFilteringRelationship
        var returnedUuidIdFilteringRelationship = om.readValue(
            restUuidIdFilteringRelationshipMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(uuidIdFilteringRelationship))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            UuidIdFilteringRelationship.class
        );

        // Validate the UuidIdFilteringRelationship in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertUuidIdFilteringRelationshipUpdatableFieldsEquals(
            returnedUuidIdFilteringRelationship,
            getPersistedUuidIdFilteringRelationship(returnedUuidIdFilteringRelationship)
        );

        insertedUuidIdFilteringRelationship = returnedUuidIdFilteringRelationship;
    }

    @Test
    @Transactional
    void createUuidIdFilteringRelationshipWithExistingId() throws Exception {
        // Create the UuidIdFilteringRelationship with an existing ID
        insertedUuidIdFilteringRelationship = uuidIdFilteringRelationshipRepository.saveAndFlush(uuidIdFilteringRelationship);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUuidIdFilteringRelationshipMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(uuidIdFilteringRelationship))
            )
            .andExpect(status().isBadRequest());

        // Validate the UuidIdFilteringRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUuidIdFilteringRelationships() throws Exception {
        // Initialize the database
        insertedUuidIdFilteringRelationship = uuidIdFilteringRelationshipRepository.saveAndFlush(uuidIdFilteringRelationship);

        // Get all the uuidIdFilteringRelationshipList
        restUuidIdFilteringRelationshipMockMvc
            .perform(get(ENTITY_API_URL + "?sort=relatedId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].relatedId").value(hasItem(uuidIdFilteringRelationship.getRelatedId().toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllUuidIdFilteringRelationshipsWithEagerRelationshipsIsEnabled() throws Exception {
        when(uuidIdFilteringRelationshipServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restUuidIdFilteringRelationshipMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(uuidIdFilteringRelationshipServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllUuidIdFilteringRelationshipsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(uuidIdFilteringRelationshipServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restUuidIdFilteringRelationshipMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(uuidIdFilteringRelationshipRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getUuidIdFilteringRelationship() throws Exception {
        // Initialize the database
        insertedUuidIdFilteringRelationship = uuidIdFilteringRelationshipRepository.saveAndFlush(uuidIdFilteringRelationship);

        // Get the uuidIdFilteringRelationship
        restUuidIdFilteringRelationshipMockMvc
            .perform(get(ENTITY_API_URL_ID, uuidIdFilteringRelationship.getRelatedId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.relatedId").value(uuidIdFilteringRelationship.getRelatedId().toString()));
    }

    @Test
    @Transactional
    void getUuidIdFilteringRelationshipsByIdFiltering() throws Exception {
        // Initialize the database
        insertedUuidIdFilteringRelationship = uuidIdFilteringRelationshipRepository.saveAndFlush(uuidIdFilteringRelationship);

        UUID id = uuidIdFilteringRelationship.getRelatedId();

        defaultUuidIdFilteringRelationshipFiltering("relatedId.equals=" + id, "relatedId.notEquals=" + id);
    }

    @Test
    @Transactional
    void getAllUuidIdFilteringRelationshipsByOneToOneIsEqualToSomething() throws Exception {
        UuidIdFiltering oneToOne;
        if (TestUtil.findAll(em, UuidIdFiltering.class).isEmpty()) {
            uuidIdFilteringRelationshipRepository.saveAndFlush(uuidIdFilteringRelationship);
            oneToOne = UuidIdFilteringResourceIT.createEntity();
        } else {
            oneToOne = TestUtil.findAll(em, UuidIdFiltering.class).get(0);
        }
        em.persist(oneToOne);
        em.flush();
        uuidIdFilteringRelationship.setOneToOne(oneToOne);
        uuidIdFilteringRelationshipRepository.saveAndFlush(uuidIdFilteringRelationship);
        UUID oneToOneId = oneToOne.getCustomId();
        // Get all the uuidIdFilteringRelationshipList where oneToOne equals to oneToOneId
        defaultUuidIdFilteringRelationshipShouldBeFound("oneToOneId.equals=" + oneToOneId);

        // Get all the uuidIdFilteringRelationshipList where oneToOne equals to UUID.randomUUID()
        defaultUuidIdFilteringRelationshipShouldNotBeFound("oneToOneId.equals=" + UUID.randomUUID());
    }

    @Test
    @Transactional
    void getAllUuidIdFilteringRelationshipsByOneToOneMapsIdIsEqualToSomething() throws Exception {
        UuidIdFilteringMapsId oneToOneMapsId;
        if (TestUtil.findAll(em, UuidIdFilteringMapsId.class).isEmpty()) {
            uuidIdFilteringRelationshipRepository.saveAndFlush(uuidIdFilteringRelationship);
            oneToOneMapsId = UuidIdFilteringMapsIdResourceIT.createEntity(em);
        } else {
            oneToOneMapsId = TestUtil.findAll(em, UuidIdFilteringMapsId.class).get(0);
        }
        em.persist(oneToOneMapsId);
        em.flush();
        uuidIdFilteringRelationship.setOneToOneMapsId(oneToOneMapsId);
        uuidIdFilteringRelationshipRepository.saveAndFlush(uuidIdFilteringRelationship);
        UUID oneToOneMapsIdId = oneToOneMapsId.getCustomId();
        // Get all the uuidIdFilteringRelationshipList where oneToOneMapsId equals to oneToOneMapsIdId
        defaultUuidIdFilteringRelationshipShouldBeFound("oneToOneMapsIdId.equals=" + oneToOneMapsIdId);

        // Get all the uuidIdFilteringRelationshipList where oneToOneMapsId equals to UUID.randomUUID()
        defaultUuidIdFilteringRelationshipShouldNotBeFound("oneToOneMapsIdId.equals=" + UUID.randomUUID());
    }

    @Test
    @Transactional
    void getAllUuidIdFilteringRelationshipsByManyToOneIsEqualToSomething() throws Exception {
        UuidIdFiltering manyToOne;
        if (TestUtil.findAll(em, UuidIdFiltering.class).isEmpty()) {
            uuidIdFilteringRelationshipRepository.saveAndFlush(uuidIdFilteringRelationship);
            manyToOne = UuidIdFilteringResourceIT.createEntity();
        } else {
            manyToOne = TestUtil.findAll(em, UuidIdFiltering.class).get(0);
        }
        em.persist(manyToOne);
        em.flush();
        uuidIdFilteringRelationship.setManyToOne(manyToOne);
        uuidIdFilteringRelationshipRepository.saveAndFlush(uuidIdFilteringRelationship);
        UUID manyToOneId = manyToOne.getCustomId();
        // Get all the uuidIdFilteringRelationshipList where manyToOne equals to manyToOneId
        defaultUuidIdFilteringRelationshipShouldBeFound("manyToOneId.equals=" + manyToOneId);

        // Get all the uuidIdFilteringRelationshipList where manyToOne equals to UUID.randomUUID()
        defaultUuidIdFilteringRelationshipShouldNotBeFound("manyToOneId.equals=" + UUID.randomUUID());
    }

    @Test
    @Transactional
    void getAllUuidIdFilteringRelationshipsByManyToOneMapsIdIsEqualToSomething() throws Exception {
        UuidIdFilteringMapsId manyToOneMapsId;
        if (TestUtil.findAll(em, UuidIdFilteringMapsId.class).isEmpty()) {
            uuidIdFilteringRelationshipRepository.saveAndFlush(uuidIdFilteringRelationship);
            manyToOneMapsId = UuidIdFilteringMapsIdResourceIT.createEntity(em);
        } else {
            manyToOneMapsId = TestUtil.findAll(em, UuidIdFilteringMapsId.class).get(0);
        }
        em.persist(manyToOneMapsId);
        em.flush();
        uuidIdFilteringRelationship.setManyToOneMapsId(manyToOneMapsId);
        uuidIdFilteringRelationshipRepository.saveAndFlush(uuidIdFilteringRelationship);
        UUID manyToOneMapsIdId = manyToOneMapsId.getCustomId();
        // Get all the uuidIdFilteringRelationshipList where manyToOneMapsId equals to manyToOneMapsIdId
        defaultUuidIdFilteringRelationshipShouldBeFound("manyToOneMapsIdId.equals=" + manyToOneMapsIdId);

        // Get all the uuidIdFilteringRelationshipList where manyToOneMapsId equals to UUID.randomUUID()
        defaultUuidIdFilteringRelationshipShouldNotBeFound("manyToOneMapsIdId.equals=" + UUID.randomUUID());
    }

    @Test
    @Transactional
    void getAllUuidIdFilteringRelationshipsByManyToManyIsEqualToSomething() throws Exception {
        UuidIdFiltering manyToMany;
        if (TestUtil.findAll(em, UuidIdFiltering.class).isEmpty()) {
            uuidIdFilteringRelationshipRepository.saveAndFlush(uuidIdFilteringRelationship);
            manyToMany = UuidIdFilteringResourceIT.createEntity();
        } else {
            manyToMany = TestUtil.findAll(em, UuidIdFiltering.class).get(0);
        }
        em.persist(manyToMany);
        em.flush();
        uuidIdFilteringRelationship.addManyToMany(manyToMany);
        uuidIdFilteringRelationshipRepository.saveAndFlush(uuidIdFilteringRelationship);
        UUID manyToManyId = manyToMany.getCustomId();
        // Get all the uuidIdFilteringRelationshipList where manyToMany equals to manyToManyId
        defaultUuidIdFilteringRelationshipShouldBeFound("manyToManyId.equals=" + manyToManyId);

        // Get all the uuidIdFilteringRelationshipList where manyToMany equals to UUID.randomUUID()
        defaultUuidIdFilteringRelationshipShouldNotBeFound("manyToManyId.equals=" + UUID.randomUUID());
    }

    @Test
    @Transactional
    void getAllUuidIdFilteringRelationshipsByManyToManyMapsIdIsEqualToSomething() throws Exception {
        UuidIdFilteringMapsId manyToManyMapsId;
        if (TestUtil.findAll(em, UuidIdFilteringMapsId.class).isEmpty()) {
            uuidIdFilteringRelationshipRepository.saveAndFlush(uuidIdFilteringRelationship);
            manyToManyMapsId = UuidIdFilteringMapsIdResourceIT.createEntity(em);
        } else {
            manyToManyMapsId = TestUtil.findAll(em, UuidIdFilteringMapsId.class).get(0);
        }
        em.persist(manyToManyMapsId);
        em.flush();
        uuidIdFilteringRelationship.addManyToManyMapsId(manyToManyMapsId);
        uuidIdFilteringRelationshipRepository.saveAndFlush(uuidIdFilteringRelationship);
        UUID manyToManyMapsIdId = manyToManyMapsId.getCustomId();
        // Get all the uuidIdFilteringRelationshipList where manyToManyMapsId equals to manyToManyMapsIdId
        defaultUuidIdFilteringRelationshipShouldBeFound("manyToManyMapsIdId.equals=" + manyToManyMapsIdId);

        // Get all the uuidIdFilteringRelationshipList where manyToManyMapsId equals to UUID.randomUUID()
        defaultUuidIdFilteringRelationshipShouldNotBeFound("manyToManyMapsIdId.equals=" + UUID.randomUUID());
    }

    private void defaultUuidIdFilteringRelationshipFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultUuidIdFilteringRelationshipShouldBeFound(shouldBeFound);
        defaultUuidIdFilteringRelationshipShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultUuidIdFilteringRelationshipShouldBeFound(String filter) throws Exception {
        restUuidIdFilteringRelationshipMockMvc
            .perform(get(ENTITY_API_URL + "?sort=relatedId,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].relatedId").value(hasItem(uuidIdFilteringRelationship.getRelatedId().toString())));

        // Check, that the count call also returns 1
        restUuidIdFilteringRelationshipMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=relatedId,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultUuidIdFilteringRelationshipShouldNotBeFound(String filter) throws Exception {
        restUuidIdFilteringRelationshipMockMvc
            .perform(get(ENTITY_API_URL + "?sort=relatedId,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restUuidIdFilteringRelationshipMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=relatedId,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingUuidIdFilteringRelationship() throws Exception {
        // Get the uuidIdFilteringRelationship
        restUuidIdFilteringRelationshipMockMvc
            .perform(get(ENTITY_API_URL_ID, UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingUuidIdFilteringRelationship() throws Exception {
        // Initialize the database
        insertedUuidIdFilteringRelationship = uuidIdFilteringRelationshipRepository.saveAndFlush(uuidIdFilteringRelationship);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the uuidIdFilteringRelationship
        UuidIdFilteringRelationship updatedUuidIdFilteringRelationship = uuidIdFilteringRelationshipRepository
            .findById(uuidIdFilteringRelationship.getRelatedId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedUuidIdFilteringRelationship are not directly saved in db
        em.detach(updatedUuidIdFilteringRelationship);

        restUuidIdFilteringRelationshipMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedUuidIdFilteringRelationship.getRelatedId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedUuidIdFilteringRelationship))
            )
            .andExpect(status().isOk());

        // Validate the UuidIdFilteringRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedUuidIdFilteringRelationshipToMatchAllProperties(updatedUuidIdFilteringRelationship);
    }

    @Test
    @Transactional
    void putNonExistingUuidIdFilteringRelationship() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        uuidIdFilteringRelationship.setRelatedId(UUID.randomUUID());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUuidIdFilteringRelationshipMockMvc
            .perform(
                put(ENTITY_API_URL_ID, uuidIdFilteringRelationship.getRelatedId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(uuidIdFilteringRelationship))
            )
            .andExpect(status().isBadRequest());

        // Validate the UuidIdFilteringRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUuidIdFilteringRelationship() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        uuidIdFilteringRelationship.setRelatedId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUuidIdFilteringRelationshipMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(uuidIdFilteringRelationship))
            )
            .andExpect(status().isBadRequest());

        // Validate the UuidIdFilteringRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUuidIdFilteringRelationship() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        uuidIdFilteringRelationship.setRelatedId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUuidIdFilteringRelationshipMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(uuidIdFilteringRelationship)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UuidIdFilteringRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUuidIdFilteringRelationshipWithPatch() throws Exception {
        // Initialize the database
        insertedUuidIdFilteringRelationship = uuidIdFilteringRelationshipRepository.saveAndFlush(uuidIdFilteringRelationship);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the uuidIdFilteringRelationship using partial update
        UuidIdFilteringRelationship partialUpdatedUuidIdFilteringRelationship = new UuidIdFilteringRelationship();
        partialUpdatedUuidIdFilteringRelationship.setRelatedId(uuidIdFilteringRelationship.getRelatedId());

        restUuidIdFilteringRelationshipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUuidIdFilteringRelationship.getRelatedId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedUuidIdFilteringRelationship))
            )
            .andExpect(status().isOk());

        // Validate the UuidIdFilteringRelationship in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertUuidIdFilteringRelationshipUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedUuidIdFilteringRelationship, uuidIdFilteringRelationship),
            getPersistedUuidIdFilteringRelationship(uuidIdFilteringRelationship)
        );
    }

    @Test
    @Transactional
    void fullUpdateUuidIdFilteringRelationshipWithPatch() throws Exception {
        // Initialize the database
        insertedUuidIdFilteringRelationship = uuidIdFilteringRelationshipRepository.saveAndFlush(uuidIdFilteringRelationship);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the uuidIdFilteringRelationship using partial update
        UuidIdFilteringRelationship partialUpdatedUuidIdFilteringRelationship = new UuidIdFilteringRelationship();
        partialUpdatedUuidIdFilteringRelationship.setRelatedId(uuidIdFilteringRelationship.getRelatedId());

        restUuidIdFilteringRelationshipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUuidIdFilteringRelationship.getRelatedId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedUuidIdFilteringRelationship))
            )
            .andExpect(status().isOk());

        // Validate the UuidIdFilteringRelationship in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertUuidIdFilteringRelationshipUpdatableFieldsEquals(
            partialUpdatedUuidIdFilteringRelationship,
            getPersistedUuidIdFilteringRelationship(partialUpdatedUuidIdFilteringRelationship)
        );
    }

    @Test
    @Transactional
    void patchNonExistingUuidIdFilteringRelationship() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        uuidIdFilteringRelationship.setRelatedId(UUID.randomUUID());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUuidIdFilteringRelationshipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, uuidIdFilteringRelationship.getRelatedId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(uuidIdFilteringRelationship))
            )
            .andExpect(status().isBadRequest());

        // Validate the UuidIdFilteringRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUuidIdFilteringRelationship() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        uuidIdFilteringRelationship.setRelatedId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUuidIdFilteringRelationshipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(uuidIdFilteringRelationship))
            )
            .andExpect(status().isBadRequest());

        // Validate the UuidIdFilteringRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUuidIdFilteringRelationship() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        uuidIdFilteringRelationship.setRelatedId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUuidIdFilteringRelationshipMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(uuidIdFilteringRelationship))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UuidIdFilteringRelationship in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUuidIdFilteringRelationship() throws Exception {
        // Initialize the database
        insertedUuidIdFilteringRelationship = uuidIdFilteringRelationshipRepository.saveAndFlush(uuidIdFilteringRelationship);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the uuidIdFilteringRelationship
        restUuidIdFilteringRelationshipMockMvc
            .perform(delete(ENTITY_API_URL_ID, uuidIdFilteringRelationship.getRelatedId().toString()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return uuidIdFilteringRelationshipRepository.count();
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

    protected UuidIdFilteringRelationship getPersistedUuidIdFilteringRelationship(UuidIdFilteringRelationship uuidIdFilteringRelationship) {
        return uuidIdFilteringRelationshipRepository.findById(uuidIdFilteringRelationship.getRelatedId()).orElseThrow();
    }

    protected void assertPersistedUuidIdFilteringRelationshipToMatchAllProperties(
        UuidIdFilteringRelationship expectedUuidIdFilteringRelationship
    ) {
        assertUuidIdFilteringRelationshipAllPropertiesEquals(
            expectedUuidIdFilteringRelationship,
            getPersistedUuidIdFilteringRelationship(expectedUuidIdFilteringRelationship)
        );
    }

    protected void assertPersistedUuidIdFilteringRelationshipToMatchUpdatableProperties(
        UuidIdFilteringRelationship expectedUuidIdFilteringRelationship
    ) {
        assertUuidIdFilteringRelationshipAllUpdatablePropertiesEquals(
            expectedUuidIdFilteringRelationship,
            getPersistedUuidIdFilteringRelationship(expectedUuidIdFilteringRelationship)
        );
    }
}
