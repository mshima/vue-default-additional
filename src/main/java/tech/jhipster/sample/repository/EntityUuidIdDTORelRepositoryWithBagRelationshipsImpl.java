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
import tech.jhipster.sample.domain.EntityUuidIdDTORel;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class EntityUuidIdDTORelRepositoryWithBagRelationshipsImpl implements EntityUuidIdDTORelRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String ENTITYUUIDIDDTORELS_PARAMETER = "entityUuidIdDTORels";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<EntityUuidIdDTORel> fetchBagRelationships(Optional<EntityUuidIdDTORel> entityUuidIdDTORel) {
        return entityUuidIdDTORel.map(this::fetchManyToManies).map(this::fetchManyToManyMapsIds);
    }

    @Override
    public Page<EntityUuidIdDTORel> fetchBagRelationships(Page<EntityUuidIdDTORel> entityUuidIdDTORels) {
        return new PageImpl<>(
            fetchBagRelationships(entityUuidIdDTORels.getContent()),
            entityUuidIdDTORels.getPageable(),
            entityUuidIdDTORels.getTotalElements()
        );
    }

    @Override
    public List<EntityUuidIdDTORel> fetchBagRelationships(List<EntityUuidIdDTORel> entityUuidIdDTORels) {
        return Optional.of(entityUuidIdDTORels)
            .map(this::fetchManyToManies)
            .map(this::fetchManyToManyMapsIds)
            .orElse(Collections.emptyList());
    }

    EntityUuidIdDTORel fetchManyToManies(EntityUuidIdDTORel result) {
        return entityManager
            .createQuery(
                "select entityUuidIdDTORel from EntityUuidIdDTORel entityUuidIdDTORel left join fetch entityUuidIdDTORel.manyToManies where entityUuidIdDTORel.id = :id",
                EntityUuidIdDTORel.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<EntityUuidIdDTORel> fetchManyToManies(List<EntityUuidIdDTORel> entityUuidIdDTORels) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, entityUuidIdDTORels.size()).forEach(index -> order.put(entityUuidIdDTORels.get(index).getId(), index));
        List<EntityUuidIdDTORel> result = entityManager
            .createQuery(
                "select entityUuidIdDTORel from EntityUuidIdDTORel entityUuidIdDTORel left join fetch entityUuidIdDTORel.manyToManies where entityUuidIdDTORel in :entityUuidIdDTORels",
                EntityUuidIdDTORel.class
            )
            .setParameter(ENTITYUUIDIDDTORELS_PARAMETER, entityUuidIdDTORels)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    EntityUuidIdDTORel fetchManyToManyMapsIds(EntityUuidIdDTORel result) {
        return entityManager
            .createQuery(
                "select entityUuidIdDTORel from EntityUuidIdDTORel entityUuidIdDTORel left join fetch entityUuidIdDTORel.manyToManyMapsIds where entityUuidIdDTORel.id = :id",
                EntityUuidIdDTORel.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<EntityUuidIdDTORel> fetchManyToManyMapsIds(List<EntityUuidIdDTORel> entityUuidIdDTORels) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, entityUuidIdDTORels.size()).forEach(index -> order.put(entityUuidIdDTORels.get(index).getId(), index));
        List<EntityUuidIdDTORel> result = entityManager
            .createQuery(
                "select entityUuidIdDTORel from EntityUuidIdDTORel entityUuidIdDTORel left join fetch entityUuidIdDTORel.manyToManyMapsIds where entityUuidIdDTORel in :entityUuidIdDTORels",
                EntityUuidIdDTORel.class
            )
            .setParameter(ENTITYUUIDIDDTORELS_PARAMETER, entityUuidIdDTORels)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
