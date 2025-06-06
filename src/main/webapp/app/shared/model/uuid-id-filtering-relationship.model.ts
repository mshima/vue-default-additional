import { type IUuidIdFiltering } from '@/shared/model/uuid-id-filtering.model';
import { type IUuidIdFilteringMapsId } from '@/shared/model/uuid-id-filtering-maps-id.model';

export interface IUuidIdFilteringRelationship {
  id?: string;
  oneToOne?: IUuidIdFiltering | null;
  oneToOneMapsId?: IUuidIdFilteringMapsId | null;
  manyToOne?: IUuidIdFiltering | null;
  manyToOneMapsId?: IUuidIdFilteringMapsId | null;
  manyToManies?: IUuidIdFiltering[] | null;
  manyToManyMapsIds?: IUuidIdFilteringMapsId[] | null;
}

export class UuidIdFilteringRelationship implements IUuidIdFilteringRelationship {
  constructor(
    public id?: string,
    public oneToOne?: IUuidIdFiltering | null,
    public oneToOneMapsId?: IUuidIdFilteringMapsId | null,
    public manyToOne?: IUuidIdFiltering | null,
    public manyToOneMapsId?: IUuidIdFilteringMapsId | null,
    public manyToManies?: IUuidIdFiltering[] | null,
    public manyToManyMapsIds?: IUuidIdFilteringMapsId[] | null,
  ) {}
}
