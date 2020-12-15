import { IItems } from 'app/shared/model/items.model';

export interface IBasket {
  id?: number;
  count?: number;
  toId?: string;
  addedDate?: string;
  items?: IItems[];
}

export class Basket implements IBasket {
  constructor(public id?: number, public count?: number, public toId?: string, public addedDate?: string, public items?: IItems[]) {}
}
