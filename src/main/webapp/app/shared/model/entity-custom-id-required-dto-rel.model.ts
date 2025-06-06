import { type IEntityCustomIdRequiredDTO } from '@/shared/model/entity-custom-id-required-dto.model';
import { type IEntityCustomIdRequiredDTOMapsId } from '@/shared/model/entity-custom-id-required-dto-maps-id.model';

export interface IEntityCustomIdRequiredDTORel {
  id?: number;
  oneToOne?: IEntityCustomIdRequiredDTO;
  oneToOneMapsId?: IEntityCustomIdRequiredDTOMapsId;
  manyToOne?: IEntityCustomIdRequiredDTO;
  manyToOneMapsId?: IEntityCustomIdRequiredDTOMapsId;
  manyToManies?: IEntityCustomIdRequiredDTO[];
  manyToManyMapsIds?: IEntityCustomIdRequiredDTOMapsId[];
}

export class EntityCustomIdRequiredDTORel implements IEntityCustomIdRequiredDTORel {
  constructor(
    public id?: number,
    public oneToOne?: IEntityCustomIdRequiredDTO,
    public oneToOneMapsId?: IEntityCustomIdRequiredDTOMapsId,
    public manyToOne?: IEntityCustomIdRequiredDTO,
    public manyToOneMapsId?: IEntityCustomIdRequiredDTOMapsId,
    public manyToManies?: IEntityCustomIdRequiredDTO[],
    public manyToManyMapsIds?: IEntityCustomIdRequiredDTOMapsId[],
  ) {}
}
