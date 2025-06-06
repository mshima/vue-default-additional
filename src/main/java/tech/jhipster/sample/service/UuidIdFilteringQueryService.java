package tech.jhipster.sample.service;

import jakarta.persistence.criteria.JoinType;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.sample.domain.*; // for static metamodels
import tech.jhipster.sample.domain.UuidIdFiltering;
import tech.jhipster.sample.repository.UuidIdFilteringRepository;
import tech.jhipster.sample.service.criteria.UuidIdFilteringCriteria;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link UuidIdFiltering} entities in the database.
 * The main input is a {@link UuidIdFilteringCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UuidIdFiltering} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class UuidIdFilteringQueryService extends QueryService<UuidIdFiltering> {

    private static final Logger LOG = LoggerFactory.getLogger(UuidIdFilteringQueryService.class);

    private final UuidIdFilteringRepository uuidIdFilteringRepository;

    public UuidIdFilteringQueryService(UuidIdFilteringRepository uuidIdFilteringRepository) {
        this.uuidIdFilteringRepository = uuidIdFilteringRepository;
    }

    /**
     * Return a {@link List} of {@link UuidIdFiltering} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<UuidIdFiltering> findByCriteria(UuidIdFilteringCriteria criteria) {
        LOG.debug("find by criteria : {}", criteria);
        final Specification<UuidIdFiltering> specification = createSpecification(criteria);
        return uuidIdFilteringRepository.findAll(specification);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(UuidIdFilteringCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<UuidIdFiltering> specification = createSpecification(criteria);
        return uuidIdFilteringRepository.count(specification);
    }

    /**
     * Function to convert {@link UuidIdFilteringCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<UuidIdFiltering> createSpecification(UuidIdFilteringCriteria criteria) {
        Specification<UuidIdFiltering> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : null,
                buildSpecification(criteria.getCustomId(), UuidIdFiltering_.customId),
                buildSpecification(criteria.getUuidIdFilteringMapsIdId(), root ->
                    root.join(UuidIdFiltering_.uuidIdFilteringMapsId, JoinType.LEFT).get(UuidIdFilteringMapsId_.customId)
                ),
                buildSpecification(criteria.getOneToOneBackId(), root ->
                    root.join(UuidIdFiltering_.oneToOneBack, JoinType.LEFT).get(UuidIdFilteringRelationship_.relatedId)
                ),
                buildSpecification(criteria.getManyToOneBackId(), root ->
                    root.join(UuidIdFiltering_.manyToOneBacks, JoinType.LEFT).get(UuidIdFilteringRelationship_.relatedId)
                ),
                buildSpecification(criteria.getManyToManyBackId(), root ->
                    root.join(UuidIdFiltering_.manyToManyBacks, JoinType.LEFT).get(UuidIdFilteringRelationship_.relatedId)
                )
            );
        }
        return specification;
    }
}
