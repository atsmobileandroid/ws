export interface IItemCodes {
  id?: number;
  code?: string;
  itemsId?: number;
}

export class ItemCodes implements IItemCodes {
  constructor(public id?: number, public code?: string, public itemsId?: number) {}
}
