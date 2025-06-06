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
import tech.jhipster.sample.domain.EntityCustomIdDTORel;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class EntityCustomIdDTORelRepositoryWithBagRelationshipsImpl implements EntityCustomIdDTORelRepositoryWithBagRelationships {

    private static final String RELATEDID_PARAMETER = "relatedId";
    private static final String ENTITYCUSTOMIDDTORELS_PARAMETER = "entityCustomIdDTORels";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<EntityCustomIdDTORel> fetchBagRelationships(Optional<EntityCustomIdDTORel> entityCustomIdDTORel) {
        return entityCustomIdDTORel.map(this::fetchManyToManies).map(this::fetchManyToManyMapsIds);
    }

    @Override
    public Page<EntityCustomIdDTORel> fetchBagRelationships(Page<EntityCustomIdDTORel> entityCustomIdDTORels) {
        return new PageImpl<>(
            fetchBagRelationships(entityCustomIdDTORels.getContent()),
            entityCustomIdDTORels.getPageable(),
            entityCustomIdDTORels.getTotalElements()
        );
    }

    @Override
    public List<EntityCustomIdDTORel> fetchBagRelationships(List<EntityCustomIdDTORel> entityCustomIdDTORels) {
        return Optional.of(entityCustomIdDTORels)
            .map(this::fetchManyToManies)
            .map(this::fetchManyToManyMapsIds)
            .orElse(Collections.emptyList());
    }

    EntityCustomIdDTORel fetchManyToManies(EntityCustomIdDTORel result) {
        return entityManager
            .createQuery(
                "select entityCustomIdDTORel from EntityCustomIdDTORel entityCustomIdDTORel left join fetch entityCustomIdDTORel.manyToManies where entityCustomIdDTORel.relatedId = :relatedId",
                EntityCustomIdDTORel.class
            )
            .setParameter(RELATEDID_PARAMETER, result.getRelatedId())
            .getSingleResult();
    }

    List<EntityCustomIdDTORel> fetchManyToManies(List<EntityCustomIdDTORel> entityCustomIdDTORels) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, entityCustomIdDTORels.size()).forEach(index ->
            order.put(entityCustomIdDTORels.get(index).getRelatedId(), index)
        );
        List<EntityCustomIdDTORel> result = entityManager
            .createQuery(
                "select entityCustomIdDTORel from EntityCustomIdDTORel entityCustomIdDTORel left join fetch entityCustomIdDTORel.manyToManies where entityCustomIdDTORel in :entityCustomIdDTORels",
                EntityCustomIdDTORel.class
            )
            .setParameter(ENTITYCUSTOMIDDTORELS_PARAMETER, entityCustomIdDTORels)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getRelatedId()), order.get(o2.getRelatedId())));
        return result;
    }

    EntityCustomIdDTORel fetchManyToManyMapsIds(EntityCustomIdDTORel result) {
        return entityManager
            .createQuery(
                "select entityCustomIdDTORel from EntityCustomIdDTORel entityCustomIdDTORel left join fetch entityCustomIdDTORel.manyToManyMapsIds where entityCustomIdDTORel.relatedId = :relatedId",
                EntityCustomIdDTORel.class
            )
            .setParameter(RELATEDID_PARAMETER, result.getRelatedId())
            .getSingleResult();
    }

    List<EntityCustomIdDTORel> fetchManyToManyMapsIds(List<EntityCustomIdDTORel> entityCustomIdDTORels) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, entityCustomIdDTORels.size()).forEach(index ->
            order.put(entityCustomIdDTORels.get(index).getRelatedId(), index)
        );
        List<EntityCustomIdDTORel> result = entityManager
            .createQuery(
                "select entityCustomIdDTORel from EntityCustomIdDTORel entityCustomIdDTORel left join fetch entityCustomIdDTORel.manyToManyMapsIds where entityCustomIdDTORel in :entityCustomIdDTORels",
                EntityCustomIdDTORel.class
            )
            .setParameter(ENTITYCUSTOMIDDTORELS_PARAMETER, entityCustomIdDTORels)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getRelatedId()), order.get(o2.getRelatedId())));
        return result;
    }
}
