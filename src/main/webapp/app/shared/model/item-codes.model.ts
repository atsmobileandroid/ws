import { IItems } from 'app/shared/model/items.model';

export interface IItemCodes {
  id?: number;
  code?: string;
  items?: IItems;
}

export class ItemCodes implements IItemCodes {
  constructor(public id?: number, public code?: string, public items?: IItems) {}
}
