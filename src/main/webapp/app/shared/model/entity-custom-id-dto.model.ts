import { type IEntityCustomIdDTORel } from '@/shared/model/entity-custom-id-dto-rel.model';

export interface IEntityCustomIdDTO {
  id?: number;
  manyToManyBacks?: IEntityCustomIdDTORel[] | null;
}

export class EntityCustomIdDTO implements IEntityCustomIdDTO {
  constructor(
    public id?: number,
    public manyToManyBacks?: IEntityCustomIdDTORel[] | null,
  ) {}
}
