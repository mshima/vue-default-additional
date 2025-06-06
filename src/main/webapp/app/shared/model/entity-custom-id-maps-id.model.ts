import { type IEntityCustomId } from '@/shared/model/entity-custom-id.model';
import { type IEntityCustomIdRelationship } from '@/shared/model/entity-custom-id-relationship.model';

export interface IEntityCustomIdMapsId {
  id?: number;
  entityCustomId?: IEntityCustomId | null;
  manyToManyMapsIdBacks?: IEntityCustomIdRelationship[] | null;
}

export class EntityCustomIdMapsId implements IEntityCustomIdMapsId {
  constructor(
    public id?: number,
    public entityCustomId?: IEntityCustomId | null,
    public manyToManyMapsIdBacks?: IEntityCustomIdRelationship[] | null,
  ) {}
}
