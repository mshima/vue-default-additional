package tech.jhipster.sample.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import tech.jhipster.sample.domain.UuidIdFilteringRelationship;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class UuidIdFilteringRelationshipRepositoryWithBagRelationshipsImpl
    implements UuidIdFilteringRelationshipRepositoryWithBagRelationships {

    private static final String RELATEDID_PARAMETER = "relatedId";
    private static final String UUIDIDFILTERINGRELATIONSHIPS_PARAMETER = "uuidIdFilteringRelationships";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<UuidIdFilteringRelationship> fetchBagRelationships(Optional<UuidIdFilteringRelationship> uuidIdFilteringRelationship) {
        return uuidIdFilteringRelationship.map(this::fetchManyToManies).map(this::fetchManyToManyMapsIds);
    }

    @Override
    public Page<UuidIdFilteringRelationship> fetchBagRelationships(Page<UuidIdFilteringRelationship> uuidIdFilteringRelationships) {
        return new PageImpl<>(
            fetchBagRelationships(uuidIdFilteringRelationships.getContent()),
            uuidIdFilteringRelationships.getPageable(),
            uuidIdFilteringRelationships.getTotalElements()
        );
    }

    @Override
    public List<UuidIdFilteringRelationship> fetchBagRelationships(List<UuidIdFilteringRelationship> uuidIdFilteringRelationships) {
        return Optional.of(uuidIdFilteringRelationships)
            .map(this::fetchManyToManies)
            .map(this::fetchManyToManyMapsIds)
            .orElse(Collections.emptyList());
    }

    UuidIdFilteringRelationship fetchManyToManies(UuidIdFilteringRelationship result) {
        return entityManager
            .createQuery(
                "select uuidIdFilteringRelationship from UuidIdFilteringRelationship uuidIdFilteringRelationship left join fetch uuidIdFilteringRelationship.manyToManies where uuidIdFilteringRelationship.relatedId = :relatedId",
                UuidIdFilteringRelationship.class
            )
            .setParameter(RELATEDID_PARAMETER, result.getRelatedId())
            .getSingleResult();
    }

    List<UuidIdFilteringRelationship> fetchManyToManies(List<UuidIdFilteringRelationship> uuidIdFilteringRelationships) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, uuidIdFilteringRelationships.size()).forEach(index ->
            order.put(uuidIdFilteringRelationships.get(index).getRelatedId(), index)
        );
        List<UuidIdFilteringRelationship> result = entityManager
            .createQuery(
                "select uuidIdFilteringRelationship from UuidIdFilteringRelationship uuidIdFilteringRelationship left join fetch uuidIdFilteringRelationship.manyToManies where uuidIdFilteringRelationship in :uuidIdFilteringRelationships",
                UuidIdFilteringRelationship.class
            )
            .setParameter(UUIDIDFILTERINGRELATIONSHIPS_PARAMETER, uuidIdFilteringRelationships)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getRelatedId()), order.get(o2.getRelatedId())));
        return result;
    }

    UuidIdFilteringRelationship fetchManyToManyMapsIds(UuidIdFilteringRelationship result) {
        return entityManager
            .createQuery(
                "select uuidIdFilteringRelationship from UuidIdFilteringRelationship uuidIdFilteringRelationship left join fetch uuidIdFilteringRelationship.manyToManyMapsIds where uuidIdFilteringRelationship.relatedId = :relatedId",
                UuidIdFilteringRelationship.class
            )
            .setParameter(RELATEDID_PARAMETER, result.getRelatedId())
            .getSingleResult();
    }

    List<UuidIdFilteringRelationship> fetchManyToManyMapsIds(List<UuidIdFilteringRelationship> uuidIdFilteringRelationships) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, uuidIdFilteringRelationships.size()).forEach(index ->
            order.put(uuidIdFilteringRelationships.get(index).getRelatedId(), index)
        );
        List<UuidIdFilteringRelationship> result = entityManager
            .createQuery(
                "select uuidIdFilteringRelationship from UuidIdFilteringRelationship uuidIdFilteringRelationship left join fetch uuidIdFilteringRelationship.manyToManyMapsIds where uuidIdFilteringRelationship in :uuidIdFilteringRelationships",
                UuidIdFilteringRelationship.class
            )
            .setParameter(UUIDIDFILTERINGRELATIONSHIPS_PARAMETER, uuidIdFilteringRelationships)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getRelatedId()), order.get(o2.getRelatedId())));
        return result;
    }
}
