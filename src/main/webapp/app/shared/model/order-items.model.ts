export interface IOrderItems {
  id?: number;
  itemTitle?: string;
  itemDescription?: string;
  itemImagePath?: string;
  itemPrice?: string;
  itemCount?: number;
  itemCodes?: string;
  toId?: string;
  isSentToId?: boolean;
  ordersId?: number;
}

export class OrderItems implements IOrderItems {
  constructor(
    public id?: number,
    public itemTitle?: string,
    public itemDescription?: string,
    public itemImagePath?: string,
    public itemPrice?: string,
    public itemCount?: number,
    public itemCodes?: string,
    public toId?: string,
    public isSentToId?: boolean,
    public ordersId?: number
  ) {
    this.isSentToId = this.isSentToId || false;
  }
}
