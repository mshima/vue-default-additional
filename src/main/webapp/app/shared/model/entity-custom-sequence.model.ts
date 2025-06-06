export interface IEntityCustomSequence {
  id?: number;
  name?: string | null;
}

export class EntityCustomSequence implements IEntityCustomSequence {
  constructor(
    public id?: number,
    public name?: string | null,
  ) {}
}
