import { type IEntityCustomIdDTO } from '@/shared/model/entity-custom-id-dto.model';
import { type IEntityCustomIdDTORel } from '@/shared/model/entity-custom-id-dto-rel.model';

export interface IEntityCustomIdDTOMapsId {
  id?: number;
  entityCustomIdDTO?: IEntityCustomIdDTO | null;
  manyToManyMapsIdBacks?: IEntityCustomIdDTORel[] | null;
}

export class EntityCustomIdDTOMapsId implements IEntityCustomIdDTOMapsId {
  constructor(
    public id?: number,
    public entityCustomIdDTO?: IEntityCustomIdDTO | null,
    public manyToManyMapsIdBacks?: IEntityCustomIdDTORel[] | null,
  ) {}
}
