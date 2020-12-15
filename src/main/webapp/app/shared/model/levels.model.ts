export interface ILevels {
  id?: number;
  title?: string;
  color?: string;
}

export class Levels implements ILevels {
  constructor(public id?: number, public title?: string, public color?: string) {}
}
