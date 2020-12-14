import { IOrderItems } from 'app/shared/model/order-items.model';

export interface IOrders {
  id?: number;
  totalPrice?: string;
  createdDate?: string;
  orderItems?: IOrderItems[];
  usersId?: number;
}

export class Orders implements IOrders {
  constructor(
    public id?: number,
    public totalPrice?: string,
    public createdDate?: string,
    public orderItems?: IOrderItems[],
    public usersId?: number
  ) {}
}
