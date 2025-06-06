import { type IEntityUuidIdDTO } from '@/shared/model/entity-uuid-id-dto.model';
import { type IEntityUuidIdDTOMapsId } from '@/shared/model/entity-uuid-id-dto-maps-id.model';

export interface IEntityUuidIdDTORel {
  id?: string;
  oneToOne?: IEntityUuidIdDTO | null;
  oneToOneMapsId?: IEntityUuidIdDTOMapsId | null;
  manyToOne?: IEntityUuidIdDTO | null;
  manyToOneMapsId?: IEntityUuidIdDTOMapsId | null;
  manyToManies?: IEntityUuidIdDTO[] | null;
  manyToManyMapsIds?: IEntityUuidIdDTOMapsId[] | null;
}

export class EntityUuidIdDTORel implements IEntityUuidIdDTORel {
  constructor(
    public id?: string,
    public oneToOne?: IEntityUuidIdDTO | null,
    public oneToOneMapsId?: IEntityUuidIdDTOMapsId | null,
    public manyToOne?: IEntityUuidIdDTO | null,
    public manyToOneMapsId?: IEntityUuidIdDTOMapsId | null,
    public manyToManies?: IEntityUuidIdDTO[] | null,
    public manyToManyMapsIds?: IEntityUuidIdDTOMapsId[] | null,
  ) {}
}
