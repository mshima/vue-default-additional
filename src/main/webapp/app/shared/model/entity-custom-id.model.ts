import { type IEntityCustomIdRelationship } from '@/shared/model/entity-custom-id-relationship.model';

export interface IEntityCustomId {
  id?: number;
  manyToManyBacks?: IEntityCustomIdRelationship[] | null;
}

export class EntityCustomId implements IEntityCustomId {
  constructor(
    public id?: number,
    public manyToManyBacks?: IEntityCustomIdRelationship[] | null,
  ) {}
}
