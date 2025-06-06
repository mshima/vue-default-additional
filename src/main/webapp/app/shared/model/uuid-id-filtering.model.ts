import { type IUuidIdFilteringRelationship } from '@/shared/model/uuid-id-filtering-relationship.model';

export interface IUuidIdFiltering {
  id?: string;
  manyToManyBacks?: IUuidIdFilteringRelationship[] | null;
}

export class UuidIdFiltering implements IUuidIdFiltering {
  constructor(
    public id?: string,
    public manyToManyBacks?: IUuidIdFilteringRelationship[] | null,
  ) {}
}
