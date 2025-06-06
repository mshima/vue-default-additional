package tech.jhipster.sample.service;

import jakarta.persistence.criteria.JoinType;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.sample.domain.*; // for static metamodels
import tech.jhipster.sample.domain.UuidIdFilteringRelationship;
import tech.jhipster.sample.repository.UuidIdFilteringRelationshipRepository;
import tech.jhipster.sample.service.criteria.UuidIdFilteringRelationshipCriteria;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link UuidIdFilteringRelationship} entities in the database.
 * The main input is a {@link UuidIdFilteringRelationshipCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UuidIdFilteringRelationship} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class UuidIdFilteringRelationshipQueryService extends QueryService<UuidIdFilteringRelationship> {

    private static final Logger LOG = LoggerFactory.getLogger(UuidIdFilteringRelationshipQueryService.class);

    private final UuidIdFilteringRelationshipRepository uuidIdFilteringRelationshipRepository;

    public UuidIdFilteringRelationshipQueryService(UuidIdFilteringRelationshipRepository uuidIdFilteringRelationshipRepository) {
        this.uuidIdFilteringRelationshipRepository = uuidIdFilteringRelationshipRepository;
    }

    /**
     * Return a {@link List} of {@link UuidIdFilteringRelationship} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<UuidIdFilteringRelationship> findByCriteria(UuidIdFilteringRelationshipCriteria criteria) {
        LOG.debug("find by criteria : {}", criteria);
        final Specification<UuidIdFilteringRelationship> specification = createSpecification(criteria);
        return uuidIdFilteringRelationshipRepository.fetchBagRelationships(uuidIdFilteringRelationshipRepository.findAll(specification));
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(UuidIdFilteringRelationshipCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<UuidIdFilteringRelationship> specification = createSpecification(criteria);
        return uuidIdFilteringRelationshipRepository.count(specification);
    }

    /**
     * Function to convert {@link UuidIdFilteringRelationshipCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<UuidIdFilteringRelationship> createSpecification(UuidIdFilteringRelationshipCriteria criteria) {
        Specification<UuidIdFilteringRelationship> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : null,
                buildSpecification(criteria.getRelatedId(), UuidIdFilteringRelationship_.relatedId),
                buildSpecification(criteria.getOneToOneId(), root ->
                    root.join(UuidIdFilteringRelationship_.oneToOne, JoinType.LEFT).get(UuidIdFiltering_.customId)
                ),
                buildSpecification(criteria.getOneToOneMapsIdId(), root ->
                    root.join(UuidIdFilteringRelationship_.oneToOneMapsId, JoinType.LEFT).get(UuidIdFilteringMapsId_.customId)
                ),
                buildSpecification(criteria.getManyToOneId(), root ->
                    root.join(UuidIdFilteringRelationship_.manyToOne, JoinType.LEFT).get(UuidIdFiltering_.customId)
                ),
                buildSpecification(criteria.getManyToOneMapsIdId(), root ->
                    root.join(UuidIdFilteringRelationship_.manyToOneMapsId, JoinType.LEFT).get(UuidIdFilteringMapsId_.customId)
                ),
                buildSpecification(criteria.getManyToManyId(), root ->
                    root.join(UuidIdFilteringRelationship_.manyToManies, JoinType.LEFT).get(UuidIdFiltering_.customId)
                ),
                buildSpecification(criteria.getManyToManyMapsIdId(), root ->
                    root.join(UuidIdFilteringRelationship_.manyToManyMapsIds, JoinType.LEFT).get(UuidIdFilteringMapsId_.customId)
                )
            );
        }
        return specification;
    }
}
