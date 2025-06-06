import { Authority } from '@/shared/security/authority';
const Entities = () => import('@/entities/entities.vue');

const EntityIntegerId = () => import('@/entities/entity-integer-id/entity-integer-id.vue');
const EntityIntegerIdUpdate = () => import('@/entities/entity-integer-id/entity-integer-id-update.vue');
const EntityIntegerIdDetails = () => import('@/entities/entity-integer-id/entity-integer-id-details.vue');

const EntityCustomSequence = () => import('@/entities/entity-custom-sequence/entity-custom-sequence.vue');
const EntityCustomSequenceUpdate = () => import('@/entities/entity-custom-sequence/entity-custom-sequence-update.vue');
const EntityCustomSequenceDetails = () => import('@/entities/entity-custom-sequence/entity-custom-sequence-details.vue');

const EntityUuidId = () => import('@/entities/entity-uuid-id/entity-uuid-id.vue');
const EntityUuidIdUpdate = () => import('@/entities/entity-uuid-id/entity-uuid-id-update.vue');
const EntityUuidIdDetails = () => import('@/entities/entity-uuid-id/entity-uuid-id-details.vue');

const EntityUuidIdMapsId = () => import('@/entities/entity-uuid-id-maps-id/entity-uuid-id-maps-id.vue');
const EntityUuidIdMapsIdUpdate = () => import('@/entities/entity-uuid-id-maps-id/entity-uuid-id-maps-id-update.vue');
const EntityUuidIdMapsIdDetails = () => import('@/entities/entity-uuid-id-maps-id/entity-uuid-id-maps-id-details.vue');

const EntityUuidIdRelationship = () => import('@/entities/entity-uuid-id-relationship/entity-uuid-id-relationship.vue');
const EntityUuidIdRelationshipUpdate = () => import('@/entities/entity-uuid-id-relationship/entity-uuid-id-relationship-update.vue');
const EntityUuidIdRelationshipDetails = () => import('@/entities/entity-uuid-id-relationship/entity-uuid-id-relationship-details.vue');

const EntityUuidIdDTO = () => import('@/entities/entity-uuid-id-dto/entity-uuid-id-dto.vue');
const EntityUuidIdDTOUpdate = () => import('@/entities/entity-uuid-id-dto/entity-uuid-id-dto-update.vue');
const EntityUuidIdDTODetails = () => import('@/entities/entity-uuid-id-dto/entity-uuid-id-dto-details.vue');

const EntityUuidIdDTOMapsId = () => import('@/entities/entity-uuid-id-dto-maps-id/entity-uuid-id-dto-maps-id.vue');
const EntityUuidIdDTOMapsIdUpdate = () => import('@/entities/entity-uuid-id-dto-maps-id/entity-uuid-id-dto-maps-id-update.vue');
const EntityUuidIdDTOMapsIdDetails = () => import('@/entities/entity-uuid-id-dto-maps-id/entity-uuid-id-dto-maps-id-details.vue');

const EntityUuidIdDTORel = () => import('@/entities/entity-uuid-id-dto-rel/entity-uuid-id-dto-rel.vue');
const EntityUuidIdDTORelUpdate = () => import('@/entities/entity-uuid-id-dto-rel/entity-uuid-id-dto-rel-update.vue');
const EntityUuidIdDTORelDetails = () => import('@/entities/entity-uuid-id-dto-rel/entity-uuid-id-dto-rel-details.vue');

const EntityCustomId = () => import('@/entities/entity-custom-id/entity-custom-id.vue');
const EntityCustomIdUpdate = () => import('@/entities/entity-custom-id/entity-custom-id-update.vue');
const EntityCustomIdDetails = () => import('@/entities/entity-custom-id/entity-custom-id-details.vue');

const EntityCustomIdMapsId = () => import('@/entities/entity-custom-id-maps-id/entity-custom-id-maps-id.vue');
const EntityCustomIdMapsIdUpdate = () => import('@/entities/entity-custom-id-maps-id/entity-custom-id-maps-id-update.vue');
const EntityCustomIdMapsIdDetails = () => import('@/entities/entity-custom-id-maps-id/entity-custom-id-maps-id-details.vue');

const EntityCustomIdRelationship = () => import('@/entities/entity-custom-id-relationship/entity-custom-id-relationship.vue');
const EntityCustomIdRelationshipUpdate = () => import('@/entities/entity-custom-id-relationship/entity-custom-id-relationship-update.vue');
const EntityCustomIdRelationshipDetails = () =>
  import('@/entities/entity-custom-id-relationship/entity-custom-id-relationship-details.vue');

const EntityCustomIdDTO = () => import('@/entities/entity-custom-id-dto/entity-custom-id-dto.vue');
const EntityCustomIdDTOUpdate = () => import('@/entities/entity-custom-id-dto/entity-custom-id-dto-update.vue');
const EntityCustomIdDTODetails = () => import('@/entities/entity-custom-id-dto/entity-custom-id-dto-details.vue');

const EntityCustomIdDTOMapsId = () => import('@/entities/entity-custom-id-dto-maps-id/entity-custom-id-dto-maps-id.vue');
const EntityCustomIdDTOMapsIdUpdate = () => import('@/entities/entity-custom-id-dto-maps-id/entity-custom-id-dto-maps-id-update.vue');
const EntityCustomIdDTOMapsIdDetails = () => import('@/entities/entity-custom-id-dto-maps-id/entity-custom-id-dto-maps-id-details.vue');

const EntityCustomIdDTORel = () => import('@/entities/entity-custom-id-dto-rel/entity-custom-id-dto-rel.vue');
const EntityCustomIdDTORelUpdate = () => import('@/entities/entity-custom-id-dto-rel/entity-custom-id-dto-rel-update.vue');
const EntityCustomIdDTORelDetails = () => import('@/entities/entity-custom-id-dto-rel/entity-custom-id-dto-rel-details.vue');

const UuidIdFiltering = () => import('@/entities/uuid-id-filtering/uuid-id-filtering.vue');
const UuidIdFilteringUpdate = () => import('@/entities/uuid-id-filtering/uuid-id-filtering-update.vue');
const UuidIdFilteringDetails = () => import('@/entities/uuid-id-filtering/uuid-id-filtering-details.vue');

const UuidIdFilteringMapsId = () => import('@/entities/uuid-id-filtering-maps-id/uuid-id-filtering-maps-id.vue');
const UuidIdFilteringMapsIdUpdate = () => import('@/entities/uuid-id-filtering-maps-id/uuid-id-filtering-maps-id-update.vue');
const UuidIdFilteringMapsIdDetails = () => import('@/entities/uuid-id-filtering-maps-id/uuid-id-filtering-maps-id-details.vue');

const UuidIdFilteringRelationship = () => import('@/entities/uuid-id-filtering-relationship/uuid-id-filtering-relationship.vue');
const UuidIdFilteringRelationshipUpdate = () =>
  import('@/entities/uuid-id-filtering-relationship/uuid-id-filtering-relationship-update.vue');
const UuidIdFilteringRelationshipDetails = () =>
  import('@/entities/uuid-id-filtering-relationship/uuid-id-filtering-relationship-details.vue');

const EntityCustomIdRequiredDTO = () => import('@/entities/entity-custom-id-required-dto/entity-custom-id-required-dto.vue');
const EntityCustomIdRequiredDTOUpdate = () => import('@/entities/entity-custom-id-required-dto/entity-custom-id-required-dto-update.vue');
const EntityCustomIdRequiredDTODetails = () => import('@/entities/entity-custom-id-required-dto/entity-custom-id-required-dto-details.vue');

const EntityCustomIdRequiredDTOMapsId = () =>
  import('@/entities/entity-custom-id-required-dto-maps-id/entity-custom-id-required-dto-maps-id.vue');
const EntityCustomIdRequiredDTOMapsIdUpdate = () =>
  import('@/entities/entity-custom-id-required-dto-maps-id/entity-custom-id-required-dto-maps-id-update.vue');
const EntityCustomIdRequiredDTOMapsIdDetails = () =>
  import('@/entities/entity-custom-id-required-dto-maps-id/entity-custom-id-required-dto-maps-id-details.vue');

const EntityCustomIdRequiredDTORel = () => import('@/entities/entity-custom-id-required-dto-rel/entity-custom-id-required-dto-rel.vue');
const EntityCustomIdRequiredDTORelUpdate = () =>
  import('@/entities/entity-custom-id-required-dto-rel/entity-custom-id-required-dto-rel-update.vue');
const EntityCustomIdRequiredDTORelDetails = () =>
  import('@/entities/entity-custom-id-required-dto-rel/entity-custom-id-required-dto-rel-details.vue');

// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'entity-integer-id',
      name: 'EntityIntegerId',
      component: EntityIntegerId,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-integer-id/new',
      name: 'EntityIntegerIdCreate',
      component: EntityIntegerIdUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-integer-id/:entityIntegerIdId/edit',
      name: 'EntityIntegerIdEdit',
      component: EntityIntegerIdUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-integer-id/:entityIntegerIdId/view',
      name: 'EntityIntegerIdView',
      component: EntityIntegerIdDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-sequence',
      name: 'EntityCustomSequence',
      component: EntityCustomSequence,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-sequence/new',
      name: 'EntityCustomSequenceCreate',
      component: EntityCustomSequenceUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-sequence/:entityCustomSequenceId/edit',
      name: 'EntityCustomSequenceEdit',
      component: EntityCustomSequenceUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-sequence/:entityCustomSequenceId/view',
      name: 'EntityCustomSequenceView',
      component: EntityCustomSequenceDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id',
      name: 'EntityUuidId',
      component: EntityUuidId,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id/new',
      name: 'EntityUuidIdCreate',
      component: EntityUuidIdUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id/:entityUuidIdId/edit',
      name: 'EntityUuidIdEdit',
      component: EntityUuidIdUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id/:entityUuidIdId/view',
      name: 'EntityUuidIdView',
      component: EntityUuidIdDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id-maps-id',
      name: 'EntityUuidIdMapsId',
      component: EntityUuidIdMapsId,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id-maps-id/new',
      name: 'EntityUuidIdMapsIdCreate',
      component: EntityUuidIdMapsIdUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id-maps-id/:entityUuidIdMapsIdId/edit',
      name: 'EntityUuidIdMapsIdEdit',
      component: EntityUuidIdMapsIdUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id-maps-id/:entityUuidIdMapsIdId/view',
      name: 'EntityUuidIdMapsIdView',
      component: EntityUuidIdMapsIdDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id-relationship',
      name: 'EntityUuidIdRelationship',
      component: EntityUuidIdRelationship,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id-relationship/new',
      name: 'EntityUuidIdRelationshipCreate',
      component: EntityUuidIdRelationshipUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id-relationship/:entityUuidIdRelationshipId/edit',
      name: 'EntityUuidIdRelationshipEdit',
      component: EntityUuidIdRelationshipUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id-relationship/:entityUuidIdRelationshipId/view',
      name: 'EntityUuidIdRelationshipView',
      component: EntityUuidIdRelationshipDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id-dto',
      name: 'EntityUuidIdDTO',
      component: EntityUuidIdDTO,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id-dto/new',
      name: 'EntityUuidIdDTOCreate',
      component: EntityUuidIdDTOUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id-dto/:entityUuidIdDTOId/edit',
      name: 'EntityUuidIdDTOEdit',
      component: EntityUuidIdDTOUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id-dto/:entityUuidIdDTOId/view',
      name: 'EntityUuidIdDTOView',
      component: EntityUuidIdDTODetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id-dto-maps-id',
      name: 'EntityUuidIdDTOMapsId',
      component: EntityUuidIdDTOMapsId,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id-dto-maps-id/new',
      name: 'EntityUuidIdDTOMapsIdCreate',
      component: EntityUuidIdDTOMapsIdUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id-dto-maps-id/:entityUuidIdDTOMapsIdId/edit',
      name: 'EntityUuidIdDTOMapsIdEdit',
      component: EntityUuidIdDTOMapsIdUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id-dto-maps-id/:entityUuidIdDTOMapsIdId/view',
      name: 'EntityUuidIdDTOMapsIdView',
      component: EntityUuidIdDTOMapsIdDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id-dto-rel',
      name: 'EntityUuidIdDTORel',
      component: EntityUuidIdDTORel,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id-dto-rel/new',
      name: 'EntityUuidIdDTORelCreate',
      component: EntityUuidIdDTORelUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id-dto-rel/:entityUuidIdDTORelId/edit',
      name: 'EntityUuidIdDTORelEdit',
      component: EntityUuidIdDTORelUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-uuid-id-dto-rel/:entityUuidIdDTORelId/view',
      name: 'EntityUuidIdDTORelView',
      component: EntityUuidIdDTORelDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id',
      name: 'EntityCustomId',
      component: EntityCustomId,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id/new',
      name: 'EntityCustomIdCreate',
      component: EntityCustomIdUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id/:entityCustomIdId/edit',
      name: 'EntityCustomIdEdit',
      component: EntityCustomIdUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id/:entityCustomIdId/view',
      name: 'EntityCustomIdView',
      component: EntityCustomIdDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-maps-id',
      name: 'EntityCustomIdMapsId',
      component: EntityCustomIdMapsId,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-maps-id/new',
      name: 'EntityCustomIdMapsIdCreate',
      component: EntityCustomIdMapsIdUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-maps-id/:entityCustomIdMapsIdId/edit',
      name: 'EntityCustomIdMapsIdEdit',
      component: EntityCustomIdMapsIdUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-maps-id/:entityCustomIdMapsIdId/view',
      name: 'EntityCustomIdMapsIdView',
      component: EntityCustomIdMapsIdDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-relationship',
      name: 'EntityCustomIdRelationship',
      component: EntityCustomIdRelationship,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-relationship/new',
      name: 'EntityCustomIdRelationshipCreate',
      component: EntityCustomIdRelationshipUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-relationship/:entityCustomIdRelationshipId/edit',
      name: 'EntityCustomIdRelationshipEdit',
      component: EntityCustomIdRelationshipUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-relationship/:entityCustomIdRelationshipId/view',
      name: 'EntityCustomIdRelationshipView',
      component: EntityCustomIdRelationshipDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-dto',
      name: 'EntityCustomIdDTO',
      component: EntityCustomIdDTO,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-dto/new',
      name: 'EntityCustomIdDTOCreate',
      component: EntityCustomIdDTOUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-dto/:entityCustomIdDTOId/edit',
      name: 'EntityCustomIdDTOEdit',
      component: EntityCustomIdDTOUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-dto/:entityCustomIdDTOId/view',
      name: 'EntityCustomIdDTOView',
      component: EntityCustomIdDTODetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-dto-maps-id',
      name: 'EntityCustomIdDTOMapsId',
      component: EntityCustomIdDTOMapsId,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-dto-maps-id/new',
      name: 'EntityCustomIdDTOMapsIdCreate',
      component: EntityCustomIdDTOMapsIdUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-dto-maps-id/:entityCustomIdDTOMapsIdId/edit',
      name: 'EntityCustomIdDTOMapsIdEdit',
      component: EntityCustomIdDTOMapsIdUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-dto-maps-id/:entityCustomIdDTOMapsIdId/view',
      name: 'EntityCustomIdDTOMapsIdView',
      component: EntityCustomIdDTOMapsIdDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-dto-rel',
      name: 'EntityCustomIdDTORel',
      component: EntityCustomIdDTORel,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-dto-rel/new',
      name: 'EntityCustomIdDTORelCreate',
      component: EntityCustomIdDTORelUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-dto-rel/:entityCustomIdDTORelId/edit',
      name: 'EntityCustomIdDTORelEdit',
      component: EntityCustomIdDTORelUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-dto-rel/:entityCustomIdDTORelId/view',
      name: 'EntityCustomIdDTORelView',
      component: EntityCustomIdDTORelDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'uuid-id-filtering',
      name: 'UuidIdFiltering',
      component: UuidIdFiltering,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'uuid-id-filtering/new',
      name: 'UuidIdFilteringCreate',
      component: UuidIdFilteringUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'uuid-id-filtering/:uuidIdFilteringId/edit',
      name: 'UuidIdFilteringEdit',
      component: UuidIdFilteringUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'uuid-id-filtering/:uuidIdFilteringId/view',
      name: 'UuidIdFilteringView',
      component: UuidIdFilteringDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'uuid-id-filtering-maps-id',
      name: 'UuidIdFilteringMapsId',
      component: UuidIdFilteringMapsId,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'uuid-id-filtering-maps-id/new',
      name: 'UuidIdFilteringMapsIdCreate',
      component: UuidIdFilteringMapsIdUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'uuid-id-filtering-maps-id/:uuidIdFilteringMapsIdId/edit',
      name: 'UuidIdFilteringMapsIdEdit',
      component: UuidIdFilteringMapsIdUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'uuid-id-filtering-maps-id/:uuidIdFilteringMapsIdId/view',
      name: 'UuidIdFilteringMapsIdView',
      component: UuidIdFilteringMapsIdDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'uuid-id-filtering-relationship',
      name: 'UuidIdFilteringRelationship',
      component: UuidIdFilteringRelationship,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'uuid-id-filtering-relationship/new',
      name: 'UuidIdFilteringRelationshipCreate',
      component: UuidIdFilteringRelationshipUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'uuid-id-filtering-relationship/:uuidIdFilteringRelationshipId/edit',
      name: 'UuidIdFilteringRelationshipEdit',
      component: UuidIdFilteringRelationshipUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'uuid-id-filtering-relationship/:uuidIdFilteringRelationshipId/view',
      name: 'UuidIdFilteringRelationshipView',
      component: UuidIdFilteringRelationshipDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-required-dto',
      name: 'EntityCustomIdRequiredDTO',
      component: EntityCustomIdRequiredDTO,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-required-dto/new',
      name: 'EntityCustomIdRequiredDTOCreate',
      component: EntityCustomIdRequiredDTOUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-required-dto/:entityCustomIdRequiredDTOId/edit',
      name: 'EntityCustomIdRequiredDTOEdit',
      component: EntityCustomIdRequiredDTOUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-required-dto/:entityCustomIdRequiredDTOId/view',
      name: 'EntityCustomIdRequiredDTOView',
      component: EntityCustomIdRequiredDTODetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-required-dto-maps-id',
      name: 'EntityCustomIdRequiredDTOMapsId',
      component: EntityCustomIdRequiredDTOMapsId,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-required-dto-maps-id/new',
      name: 'EntityCustomIdRequiredDTOMapsIdCreate',
      component: EntityCustomIdRequiredDTOMapsIdUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-required-dto-maps-id/:entityCustomIdRequiredDTOMapsIdId/edit',
      name: 'EntityCustomIdRequiredDTOMapsIdEdit',
      component: EntityCustomIdRequiredDTOMapsIdUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-required-dto-maps-id/:entityCustomIdRequiredDTOMapsIdId/view',
      name: 'EntityCustomIdRequiredDTOMapsIdView',
      component: EntityCustomIdRequiredDTOMapsIdDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-required-dto-rel',
      name: 'EntityCustomIdRequiredDTORel',
      component: EntityCustomIdRequiredDTORel,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-required-dto-rel/new',
      name: 'EntityCustomIdRequiredDTORelCreate',
      component: EntityCustomIdRequiredDTORelUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-required-dto-rel/:entityCustomIdRequiredDTORelId/edit',
      name: 'EntityCustomIdRequiredDTORelEdit',
      component: EntityCustomIdRequiredDTORelUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'entity-custom-id-required-dto-rel/:entityCustomIdRequiredDTORelId/view',
      name: 'EntityCustomIdRequiredDTORelView',
      component: EntityCustomIdRequiredDTORelDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
