import { type IEntityCustomId } from '@/shared/model/entity-custom-id.model';
import { type IEntityCustomIdMapsId } from '@/shared/model/entity-custom-id-maps-id.model';

export interface IEntityCustomIdRelationship {
  id?: number;
  oneToOne?: IEntityCustomId | null;
  oneToOneMapsId?: IEntityCustomIdMapsId | null;
  manyToOne?: IEntityCustomId | null;
  manyToOneMapsId?: IEntityCustomIdMapsId | null;
  manyToManies?: IEntityCustomId[] | null;
  manyToManyMapsIds?: IEntityCustomIdMapsId[] | null;
}

export class EntityCustomIdRelationship implements IEntityCustomIdRelationship {
  constructor(
    public id?: number,
    public oneToOne?: IEntityCustomId | null,
    public oneToOneMapsId?: IEntityCustomIdMapsId | null,
    public manyToOne?: IEntityCustomId | null,
    public manyToOneMapsId?: IEntityCustomIdMapsId | null,
    public manyToManies?: IEntityCustomId[] | null,
    public manyToManyMapsIds?: IEntityCustomIdMapsId[] | null,
  ) {}
}
