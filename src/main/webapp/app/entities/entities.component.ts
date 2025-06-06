import { defineComponent, provide } from 'vue';

import EntityIntegerIdService from './entity-integer-id/entity-integer-id.service';
import EntityCustomSequenceService from './entity-custom-sequence/entity-custom-sequence.service';
import EntityUuidIdService from './entity-uuid-id/entity-uuid-id.service';
import EntityUuidIdMapsIdService from './entity-uuid-id-maps-id/entity-uuid-id-maps-id.service';
import EntityUuidIdRelationshipService from './entity-uuid-id-relationship/entity-uuid-id-relationship.service';
import EntityUuidIdDTOService from './entity-uuid-id-dto/entity-uuid-id-dto.service';
import EntityUuidIdDTOMapsIdService from './entity-uuid-id-dto-maps-id/entity-uuid-id-dto-maps-id.service';
import EntityUuidIdDTORelService from './entity-uuid-id-dto-rel/entity-uuid-id-dto-rel.service';
import EntityCustomIdService from './entity-custom-id/entity-custom-id.service';
import EntityCustomIdMapsIdService from './entity-custom-id-maps-id/entity-custom-id-maps-id.service';
import EntityCustomIdRelationshipService from './entity-custom-id-relationship/entity-custom-id-relationship.service';
import EntityCustomIdDTOService from './entity-custom-id-dto/entity-custom-id-dto.service';
import EntityCustomIdDTOMapsIdService from './entity-custom-id-dto-maps-id/entity-custom-id-dto-maps-id.service';
import EntityCustomIdDTORelService from './entity-custom-id-dto-rel/entity-custom-id-dto-rel.service';
import UuidIdFilteringService from './uuid-id-filtering/uuid-id-filtering.service';
import UuidIdFilteringMapsIdService from './uuid-id-filtering-maps-id/uuid-id-filtering-maps-id.service';
import UuidIdFilteringRelationshipService from './uuid-id-filtering-relationship/uuid-id-filtering-relationship.service';
import EntityCustomIdRequiredDTOService from './entity-custom-id-required-dto/entity-custom-id-required-dto.service';
import EntityCustomIdRequiredDTOMapsIdService from './entity-custom-id-required-dto-maps-id/entity-custom-id-required-dto-maps-id.service';
import EntityCustomIdRequiredDTORelService from './entity-custom-id-required-dto-rel/entity-custom-id-required-dto-rel.service';
import UserService from '@/entities/user/user.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

export default defineComponent({
  name: 'Entities',
  setup() {
    provide('userService', () => new UserService());
    provide('entityIntegerIdService', () => new EntityIntegerIdService());
    provide('entityCustomSequenceService', () => new EntityCustomSequenceService());
    provide('entityUuidIdService', () => new EntityUuidIdService());
    provide('entityUuidIdMapsIdService', () => new EntityUuidIdMapsIdService());
    provide('entityUuidIdRelationshipService', () => new EntityUuidIdRelationshipService());
    provide('entityUuidIdDTOService', () => new EntityUuidIdDTOService());
    provide('entityUuidIdDTOMapsIdService', () => new EntityUuidIdDTOMapsIdService());
    provide('entityUuidIdDTORelService', () => new EntityUuidIdDTORelService());
    provide('entityCustomIdService', () => new EntityCustomIdService());
    provide('entityCustomIdMapsIdService', () => new EntityCustomIdMapsIdService());
    provide('entityCustomIdRelationshipService', () => new EntityCustomIdRelationshipService());
    provide('entityCustomIdDTOService', () => new EntityCustomIdDTOService());
    provide('entityCustomIdDTOMapsIdService', () => new EntityCustomIdDTOMapsIdService());
    provide('entityCustomIdDTORelService', () => new EntityCustomIdDTORelService());
    provide('uuidIdFilteringService', () => new UuidIdFilteringService());
    provide('uuidIdFilteringMapsIdService', () => new UuidIdFilteringMapsIdService());
    provide('uuidIdFilteringRelationshipService', () => new UuidIdFilteringRelationshipService());
    provide('entityCustomIdRequiredDTOService', () => new EntityCustomIdRequiredDTOService());
    provide('entityCustomIdRequiredDTOMapsIdService', () => new EntityCustomIdRequiredDTOMapsIdService());
    provide('entityCustomIdRequiredDTORelService', () => new EntityCustomIdRequiredDTORelService());
    // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
  },
});
