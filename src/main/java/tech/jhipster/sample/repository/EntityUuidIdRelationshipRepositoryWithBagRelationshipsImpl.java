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
import tech.jhipster.sample.domain.EntityUuidIdRelationship;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class EntityUuidIdRelationshipRepositoryWithBagRelationshipsImpl implements EntityUuidIdRelationshipRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String ENTITYUUIDIDRELATIONSHIPS_PARAMETER = "entityUuidIdRelationships";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<EntityUuidIdRelationship> fetchBagRelationships(Optional<EntityUuidIdRelationship> entityUuidIdRelationship) {
        return entityUuidIdRelationship.map(this::fetchManyToManies).map(this::fetchManyToManyMapsIds);
    }

    @Override
    public Page<EntityUuidIdRelationship> fetchBagRelationships(Page<EntityUuidIdRelationship> entityUuidIdRelationships) {
        return new PageImpl<>(
            fetchBagRelationships(entityUuidIdRelationships.getContent()),
            entityUuidIdRelationships.getPageable(),
            entityUuidIdRelationships.getTotalElements()
        );
    }

    @Override
    public List<EntityUuidIdRelationship> fetchBagRelationships(List<EntityUuidIdRelationship> entityUuidIdRelationships) {
        return Optional.of(entityUuidIdRelationships)
            .map(this::fetchManyToManies)
            .map(this::fetchManyToManyMapsIds)
            .orElse(Collections.emptyList());
    }

    EntityUuidIdRelationship fetchManyToManies(EntityUuidIdRelationship result) {
        return entityManager
            .createQuery(
                "select entityUuidIdRelationship from EntityUuidIdRelationship entityUuidIdRelationship left join fetch entityUuidIdRelationship.manyToManies where entityUuidIdRelationship.id = :id",
                EntityUuidIdRelationship.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<EntityUuidIdRelationship> fetchManyToManies(List<EntityUuidIdRelationship> entityUuidIdRelationships) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, entityUuidIdRelationships.size()).forEach(index ->
            order.put(entityUuidIdRelationships.get(index).getId(), index)
        );
        List<EntityUuidIdRelationship> result = entityManager
            .createQuery(
                "select entityUuidIdRelationship from EntityUuidIdRelationship entityUuidIdRelationship left join fetch entityUuidIdRelationship.manyToManies where entityUuidIdRelationship in :entityUuidIdRelationships",
                EntityUuidIdRelationship.class
            )
            .setParameter(ENTITYUUIDIDRELATIONSHIPS_PARAMETER, entityUuidIdRelationships)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    EntityUuidIdRelationship fetchManyToManyMapsIds(EntityUuidIdRelationship result) {
        return entityManager
            .createQuery(
                "select entityUuidIdRelationship from EntityUuidIdRelationship entityUuidIdRelationship left join fetch entityUuidIdRelationship.manyToManyMapsIds where entityUuidIdRelationship.id = :id",
                EntityUuidIdRelationship.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<EntityUuidIdRelationship> fetchManyToManyMapsIds(List<EntityUuidIdRelationship> entityUuidIdRelationships) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, entityUuidIdRelationships.size()).forEach(index ->
            order.put(entityUuidIdRelationships.get(index).getId(), index)
        );
        List<EntityUuidIdRelationship> result = entityManager
            .createQuery(
                "select entityUuidIdRelationship from EntityUuidIdRelationship entityUuidIdRelationship left join fetch entityUuidIdRelationship.manyToManyMapsIds where entityUuidIdRelationship in :entityUuidIdRelationships",
                EntityUuidIdRelationship.class
            )
            .setParameter(ENTITYUUIDIDRELATIONSHIPS_PARAMETER, entityUuidIdRelationships)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
