import { ILevels } from 'app/shared/model/levels.model';

export interface IItemPrices {
  id?: number;
  price?: string;
  levels?: ILevels;
}

export class ItemPrices implements IItemPrices {
  constructor(public id?: number, public price?: string, public levels?: ILevels) {}
}
