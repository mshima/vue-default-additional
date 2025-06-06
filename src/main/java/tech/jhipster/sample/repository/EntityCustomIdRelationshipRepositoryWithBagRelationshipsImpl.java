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
import tech.jhipster.sample.domain.EntityCustomIdRelationship;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class EntityCustomIdRelationshipRepositoryWithBagRelationshipsImpl
    implements EntityCustomIdRelationshipRepositoryWithBagRelationships {

    private static final String RELATEDID_PARAMETER = "relatedId";
    private static final String ENTITYCUSTOMIDRELATIONSHIPS_PARAMETER = "entityCustomIdRelationships";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<EntityCustomIdRelationship> fetchBagRelationships(Optional<EntityCustomIdRelationship> entityCustomIdRelationship) {
        return entityCustomIdRelationship.map(this::fetchManyToManies).map(this::fetchManyToManyMapsIds);
    }

    @Override
    public Page<EntityCustomIdRelationship> fetchBagRelationships(Page<EntityCustomIdRelationship> entityCustomIdRelationships) {
        return new PageImpl<>(
            fetchBagRelationships(entityCustomIdRelationships.getContent()),
            entityCustomIdRelationships.getPageable(),
            entityCustomIdRelationships.getTotalElements()
        );
    }

    @Override
    public List<EntityCustomIdRelationship> fetchBagRelationships(List<EntityCustomIdRelationship> entityCustomIdRelationships) {
        return Optional.of(entityCustomIdRelationships)
            .map(this::fetchManyToManies)
            .map(this::fetchManyToManyMapsIds)
            .orElse(Collections.emptyList());
    }

    EntityCustomIdRelationship fetchManyToManies(EntityCustomIdRelationship result) {
        return entityManager
            .createQuery(
                "select entityCustomIdRelationship from EntityCustomIdRelationship entityCustomIdRelationship left join fetch entityCustomIdRelationship.manyToManies where entityCustomIdRelationship.relatedId = :relatedId",
                EntityCustomIdRelationship.class
            )
            .setParameter(RELATEDID_PARAMETER, result.getRelatedId())
            .getSingleResult();
    }

    List<EntityCustomIdRelationship> fetchManyToManies(List<EntityCustomIdRelationship> entityCustomIdRelationships) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, entityCustomIdRelationships.size()).forEach(index ->
            order.put(entityCustomIdRelationships.get(index).getRelatedId(), index)
        );
        List<EntityCustomIdRelationship> result = entityManager
            .createQuery(
                "select entityCustomIdRelationship from EntityCustomIdRelationship entityCustomIdRelationship left join fetch entityCustomIdRelationship.manyToManies where entityCustomIdRelationship in :entityCustomIdRelationships",
                EntityCustomIdRelationship.class
            )
            .setParameter(ENTITYCUSTOMIDRELATIONSHIPS_PARAMETER, entityCustomIdRelationships)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getRelatedId()), order.get(o2.getRelatedId())));
        return result;
    }

    EntityCustomIdRelationship fetchManyToManyMapsIds(EntityCustomIdRelationship result) {
        return entityManager
            .createQuery(
                "select entityCustomIdRelationship from EntityCustomIdRelationship entityCustomIdRelationship left join fetch entityCustomIdRelationship.manyToManyMapsIds where entityCustomIdRelationship.relatedId = :relatedId",
                EntityCustomIdRelationship.class
            )
            .setParameter(RELATEDID_PARAMETER, result.getRelatedId())
            .getSingleResult();
    }

    List<EntityCustomIdRelationship> fetchManyToManyMapsIds(List<EntityCustomIdRelationship> entityCustomIdRelationships) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, entityCustomIdRelationships.size()).forEach(index ->
            order.put(entityCustomIdRelationships.get(index).getRelatedId(), index)
        );
        List<EntityCustomIdRelationship> result = entityManager
            .createQuery(
                "select entityCustomIdRelationship from EntityCustomIdRelationship entityCustomIdRelationship left join fetch entityCustomIdRelationship.manyToManyMapsIds where entityCustomIdRelationship in :entityCustomIdRelationships",
                EntityCustomIdRelationship.class
            )
            .setParameter(ENTITYCUSTOMIDRELATIONSHIPS_PARAMETER, entityCustomIdRelationships)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getRelatedId()), order.get(o2.getRelatedId())));
        return result;
    }
}
