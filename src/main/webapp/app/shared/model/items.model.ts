import { IItemPrices } from 'app/shared/model/item-prices.model';
import { IItemCodes } from 'app/shared/model/item-codes.model';
import { IBasket } from 'app/shared/model/basket.model';

export interface IItems {
  id?: number;
  title?: string;
  description?: string;
  imagePath?: string;
  isLeaf?: boolean;
  itemPrices?: IItemPrices;
  itemCodes?: IItemCodes[];
  basket?: IBasket;
}

export class Items implements IItems {
  constructor(
    public id?: number,
    public title?: string,
    public description?: string,
    public imagePath?: string,
    public isLeaf?: boolean,
    public itemPrices?: IItemPrices,
    public itemCodes?: IItemCodes[],
    public basket?: IBasket
  ) {
    this.isLeaf = this.isLeaf || false;
  }
}
