import { type IEntityCustomIdRequiredDTO } from '@/shared/model/entity-custom-id-required-dto.model';
import { type IEntityCustomIdRequiredDTORel } from '@/shared/model/entity-custom-id-required-dto-rel.model';

export interface IEntityCustomIdRequiredDTOMapsId {
  id?: number;
  entityCustomIdRequiredDTO?: IEntityCustomIdRequiredDTO | null;
  manyToManyMapsIdBacks?: IEntityCustomIdRequiredDTORel[] | null;
}

export class EntityCustomIdRequiredDTOMapsId implements IEntityCustomIdRequiredDTOMapsId {
  constructor(
    public id?: number,
    public entityCustomIdRequiredDTO?: IEntityCustomIdRequiredDTO | null,
    public manyToManyMapsIdBacks?: IEntityCustomIdRequiredDTORel[] | null,
  ) {}
}
