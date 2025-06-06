export interface IEntityIntegerId {
  id?: number;
  name?: string | null;
}

export class EntityIntegerId implements IEntityIntegerId {
  constructor(
    public id?: number,
    public name?: string | null,
  ) {}
}
