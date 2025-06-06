import { type IEntityUuidIdRelationship } from '@/shared/model/entity-uuid-id-relationship.model';

export interface IEntityUuidId {
  id?: string;
  manyToManyBacks?: IEntityUuidIdRelationship[] | null;
}

export class EntityUuidId implements IEntityUuidId {
  constructor(
    public id?: string,
    public manyToManyBacks?: IEntityUuidIdRelationship[] | null,
  ) {}
}
