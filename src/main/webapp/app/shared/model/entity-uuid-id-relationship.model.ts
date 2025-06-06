import { type IEntityUuidId } from '@/shared/model/entity-uuid-id.model';
import { type IEntityUuidIdMapsId } from '@/shared/model/entity-uuid-id-maps-id.model';

export interface IEntityUuidIdRelationship {
  id?: string;
  oneToOne?: IEntityUuidId | null;
  oneToOneMapsId?: IEntityUuidIdMapsId | null;
  manyToOne?: IEntityUuidId | null;
  manyToOneMapsId?: IEntityUuidIdMapsId | null;
  manyToManies?: IEntityUuidId[] | null;
  manyToManyMapsIds?: IEntityUuidIdMapsId[] | null;
}

export class EntityUuidIdRelationship implements IEntityUuidIdRelationship {
  constructor(
    public id?: string,
    public oneToOne?: IEntityUuidId | null,
    public oneToOneMapsId?: IEntityUuidIdMapsId | null,
    public manyToOne?: IEntityUuidId | null,
    public manyToOneMapsId?: IEntityUuidIdMapsId | null,
    public manyToManies?: IEntityUuidId[] | null,
    public manyToManyMapsIds?: IEntityUuidIdMapsId[] | null,
  ) {}
}
