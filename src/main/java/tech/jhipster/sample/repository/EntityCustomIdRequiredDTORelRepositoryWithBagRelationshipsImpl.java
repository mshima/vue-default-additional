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
import tech.jhipster.sample.domain.EntityCustomIdRequiredDTORel;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class EntityCustomIdRequiredDTORelRepositoryWithBagRelationshipsImpl
    implements EntityCustomIdRequiredDTORelRepositoryWithBagRelationships {

    private static final String RELATEDID_PARAMETER = "relatedId";
    private static final String ENTITYCUSTOMIDREQUIREDDTORELS_PARAMETER = "entityCustomIdRequiredDTORels";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<EntityCustomIdRequiredDTORel> fetchBagRelationships(
        Optional<EntityCustomIdRequiredDTORel> entityCustomIdRequiredDTORel
    ) {
        return entityCustomIdRequiredDTORel.map(this::fetchManyToManies).map(this::fetchManyToManyMapsIds);
    }

    @Override
    public Page<EntityCustomIdRequiredDTORel> fetchBagRelationships(Page<EntityCustomIdRequiredDTORel> entityCustomIdRequiredDTORels) {
        return new PageImpl<>(
            fetchBagRelationships(entityCustomIdRequiredDTORels.getContent()),
            entityCustomIdRequiredDTORels.getPageable(),
            entityCustomIdRequiredDTORels.getTotalElements()
        );
    }

    @Override
    public List<EntityCustomIdRequiredDTORel> fetchBagRelationships(List<EntityCustomIdRequiredDTORel> entityCustomIdRequiredDTORels) {
        return Optional.of(entityCustomIdRequiredDTORels)
            .map(this::fetchManyToManies)
            .map(this::fetchManyToManyMapsIds)
            .orElse(Collections.emptyList());
    }

    EntityCustomIdRequiredDTORel fetchManyToManies(EntityCustomIdRequiredDTORel result) {
        return entityManager
            .createQuery(
                "select entityCustomIdRequiredDTORel from EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel left join fetch entityCustomIdRequiredDTORel.manyToManies where entityCustomIdRequiredDTORel.relatedId = :relatedId",
                EntityCustomIdRequiredDTORel.class
            )
            .setParameter(RELATEDID_PARAMETER, result.getRelatedId())
            .getSingleResult();
    }

    List<EntityCustomIdRequiredDTORel> fetchManyToManies(List<EntityCustomIdRequiredDTORel> entityCustomIdRequiredDTORels) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, entityCustomIdRequiredDTORels.size()).forEach(index ->
            order.put(entityCustomIdRequiredDTORels.get(index).getRelatedId(), index)
        );
        List<EntityCustomIdRequiredDTORel> result = entityManager
            .createQuery(
                "select entityCustomIdRequiredDTORel from EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel left join fetch entityCustomIdRequiredDTORel.manyToManies where entityCustomIdRequiredDTORel in :entityCustomIdRequiredDTORels",
                EntityCustomIdRequiredDTORel.class
            )
            .setParameter(ENTITYCUSTOMIDREQUIREDDTORELS_PARAMETER, entityCustomIdRequiredDTORels)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getRelatedId()), order.get(o2.getRelatedId())));
        return result;
    }

    EntityCustomIdRequiredDTORel fetchManyToManyMapsIds(EntityCustomIdRequiredDTORel result) {
        return entityManager
            .createQuery(
                "select entityCustomIdRequiredDTORel from EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel left join fetch entityCustomIdRequiredDTORel.manyToManyMapsIds where entityCustomIdRequiredDTORel.relatedId = :relatedId",
                EntityCustomIdRequiredDTORel.class
            )
            .setParameter(RELATEDID_PARAMETER, result.getRelatedId())
            .getSingleResult();
    }

    List<EntityCustomIdRequiredDTORel> fetchManyToManyMapsIds(List<EntityCustomIdRequiredDTORel> entityCustomIdRequiredDTORels) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, entityCustomIdRequiredDTORels.size()).forEach(index ->
            order.put(entityCustomIdRequiredDTORels.get(index).getRelatedId(), index)
        );
        List<EntityCustomIdRequiredDTORel> result = entityManager
            .createQuery(
                "select entityCustomIdRequiredDTORel from EntityCustomIdRequiredDTORel entityCustomIdRequiredDTORel left join fetch entityCustomIdRequiredDTORel.manyToManyMapsIds where entityCustomIdRequiredDTORel in :entityCustomIdRequiredDTORels",
                EntityCustomIdRequiredDTORel.class
            )
            .setParameter(ENTITYCUSTOMIDREQUIREDDTORELS_PARAMETER, entityCustomIdRequiredDTORels)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getRelatedId()), order.get(o2.getRelatedId())));
        return result;
    }
}
