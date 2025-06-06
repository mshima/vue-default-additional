import { type IEntityUuidIdDTO } from '@/shared/model/entity-uuid-id-dto.model';
import { type IEntityUuidIdDTORel } from '@/shared/model/entity-uuid-id-dto-rel.model';

export interface IEntityUuidIdDTOMapsId {
  id?: string;
  entityUuidIdDTO?: IEntityUuidIdDTO | null;
  manyToManyMapsIdBacks?: IEntityUuidIdDTORel[] | null;
}

export class EntityUuidIdDTOMapsId implements IEntityUuidIdDTOMapsId {
  constructor(
    public id?: string,
    public entityUuidIdDTO?: IEntityUuidIdDTO | null,
    public manyToManyMapsIdBacks?: IEntityUuidIdDTORel[] | null,
  ) {}
}
