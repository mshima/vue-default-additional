import { type IEntityCustomIdDTO } from '@/shared/model/entity-custom-id-dto.model';
import { type IEntityCustomIdDTOMapsId } from '@/shared/model/entity-custom-id-dto-maps-id.model';

export interface IEntityCustomIdDTORel {
  id?: number;
  oneToOne?: IEntityCustomIdDTO | null;
  oneToOneMapsId?: IEntityCustomIdDTOMapsId | null;
  manyToOne?: IEntityCustomIdDTO | null;
  manyToOneMapsId?: IEntityCustomIdDTOMapsId | null;
  manyToManies?: IEntityCustomIdDTO[] | null;
  manyToManyMapsIds?: IEntityCustomIdDTOMapsId[] | null;
}

export class EntityCustomIdDTORel implements IEntityCustomIdDTORel {
  constructor(
    public id?: number,
    public oneToOne?: IEntityCustomIdDTO | null,
    public oneToOneMapsId?: IEntityCustomIdDTOMapsId | null,
    public manyToOne?: IEntityCustomIdDTO | null,
    public manyToOneMapsId?: IEntityCustomIdDTOMapsId | null,
    public manyToManies?: IEntityCustomIdDTO[] | null,
    public manyToManyMapsIds?: IEntityCustomIdDTOMapsId[] | null,
  ) {}
}
