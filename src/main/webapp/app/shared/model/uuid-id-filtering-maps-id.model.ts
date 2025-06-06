import { type IUuidIdFiltering } from '@/shared/model/uuid-id-filtering.model';
import { type IUuidIdFilteringRelationship } from '@/shared/model/uuid-id-filtering-relationship.model';

export interface IUuidIdFilteringMapsId {
  id?: string;
  uuidIdFiltering?: IUuidIdFiltering | null;
  manyToManyMapsIdBacks?: IUuidIdFilteringRelationship[] | null;
}

export class UuidIdFilteringMapsId implements IUuidIdFilteringMapsId {
  constructor(
    public id?: string,
    public uuidIdFiltering?: IUuidIdFiltering | null,
    public manyToManyMapsIdBacks?: IUuidIdFilteringRelationship[] | null,
  ) {}
}
