import { type IEntityUuidId } from '@/shared/model/entity-uuid-id.model';
import { type IEntityUuidIdRelationship } from '@/shared/model/entity-uuid-id-relationship.model';

export interface IEntityUuidIdMapsId {
  id?: string;
  entityUuidId?: IEntityUuidId | null;
  manyToManyMapsIdBacks?: IEntityUuidIdRelationship[] | null;
}

export class EntityUuidIdMapsId implements IEntityUuidIdMapsId {
  constructor(
    public id?: string,
    public entityUuidId?: IEntityUuidId | null,
    public manyToManyMapsIdBacks?: IEntityUuidIdRelationship[] | null,
  ) {}
}
