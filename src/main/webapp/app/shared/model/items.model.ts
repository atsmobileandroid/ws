import { IItemCodes } from 'app/shared/model/item-codes.model';

export interface IItems {
  id?: number;
  title?: string;
  description?: string;
  imagePath?: string;
  isLeaf?: boolean;
  itemPricesId?: number;
  itemCodes?: IItemCodes[];
  basketId?: number;
}

export class Items implements IItems {
  constructor(
    public id?: number,
    public title?: string,
    public description?: string,
    public imagePath?: string,
    public isLeaf?: boolean,
    public itemPricesId?: number,
    public itemCodes?: IItemCodes[],
    public basketId?: number
  ) {
    this.isLeaf = this.isLeaf || false;
  }
}
