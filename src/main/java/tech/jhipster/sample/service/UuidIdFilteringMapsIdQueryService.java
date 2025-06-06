package tech.jhipster.sample.service;

import jakarta.persistence.criteria.JoinType;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.sample.domain.*; // for static metamodels
import tech.jhipster.sample.domain.UuidIdFilteringMapsId;
import tech.jhipster.sample.repository.UuidIdFilteringMapsIdRepository;
import tech.jhipster.sample.service.criteria.UuidIdFilteringMapsIdCriteria;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link UuidIdFilteringMapsId} entities in the database.
 * The main input is a {@link UuidIdFilteringMapsIdCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UuidIdFilteringMapsId} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class UuidIdFilteringMapsIdQueryService extends QueryService<UuidIdFilteringMapsId> {

    private static final Logger LOG = LoggerFactory.getLogger(UuidIdFilteringMapsIdQueryService.class);

    private final UuidIdFilteringMapsIdRepository uuidIdFilteringMapsIdRepository;

    public UuidIdFilteringMapsIdQueryService(UuidIdFilteringMapsIdRepository uuidIdFilteringMapsIdRepository) {
        this.uuidIdFilteringMapsIdRepository = uuidIdFilteringMapsIdRepository;
    }

    /**
     * Return a {@link List} of {@link UuidIdFilteringMapsId} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<UuidIdFilteringMapsId> findByCriteria(UuidIdFilteringMapsIdCriteria criteria) {
        LOG.debug("find by criteria : {}", criteria);
        final Specification<UuidIdFilteringMapsId> specification = createSpecification(criteria);
        return uuidIdFilteringMapsIdRepository.findAll(specification);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(UuidIdFilteringMapsIdCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<UuidIdFilteringMapsId> specification = createSpecification(criteria);
        return uuidIdFilteringMapsIdRepository.count(specification);
    }

    /**
     * Function to convert {@link UuidIdFilteringMapsIdCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<UuidIdFilteringMapsId> createSpecification(UuidIdFilteringMapsIdCriteria criteria) {
        Specification<UuidIdFilteringMapsId> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : null,
                buildSpecification(criteria.getCustomId(), UuidIdFilteringMapsId_.customId),
                buildSpecification(criteria.getUuidIdFilteringId(), root ->
                    root.join(UuidIdFilteringMapsId_.uuidIdFiltering, JoinType.LEFT).get(UuidIdFiltering_.customId)
                ),
                buildSpecification(criteria.getOneToOneMapsIdBackId(), root ->
                    root.join(UuidIdFilteringMapsId_.oneToOneMapsIdBack, JoinType.LEFT).get(UuidIdFilteringRelationship_.relatedId)
                ),
                buildSpecification(criteria.getManyToOneMapsIdBackId(), root ->
                    root.join(UuidIdFilteringMapsId_.manyToOneMapsIdBacks, JoinType.LEFT).get(UuidIdFilteringRelationship_.relatedId)
                ),
                buildSpecification(criteria.getManyToManyMapsIdBackId(), root ->
                    root.join(UuidIdFilteringMapsId_.manyToManyMapsIdBacks, JoinType.LEFT).get(UuidIdFilteringRelationship_.relatedId)
                )
            );
        }
        return specification;
    }
}
