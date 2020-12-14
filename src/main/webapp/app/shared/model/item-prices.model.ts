export interface IItemPrices {
  id?: number;
  price?: string;
  levelsId?: number;
}

export class ItemPrices implements IItemPrices {
  constructor(public id?: number, public price?: string, public levelsId?: number) {}
}
