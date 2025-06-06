import { type IEntityUuidIdDTORel } from '@/shared/model/entity-uuid-id-dto-rel.model';

export interface IEntityUuidIdDTO {
  id?: string;
  manyToManyBacks?: IEntityUuidIdDTORel[] | null;
}

export class EntityUuidIdDTO implements IEntityUuidIdDTO {
  constructor(
    public id?: string,
    public manyToManyBacks?: IEntityUuidIdDTORel[] | null,
  ) {}
}
