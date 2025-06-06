import { type IEntityCustomIdRequiredDTORel } from '@/shared/model/entity-custom-id-required-dto-rel.model';

export interface IEntityCustomIdRequiredDTO {
  id?: number;
  manyToManyBacks?: IEntityCustomIdRequiredDTORel[] | null;
}

export class EntityCustomIdRequiredDTO implements IEntityCustomIdRequiredDTO {
  constructor(
    public id?: number,
    public manyToManyBacks?: IEntityCustomIdRequiredDTORel[] | null,
  ) {}
}
