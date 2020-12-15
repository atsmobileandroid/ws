import { IOrderItems } from 'app/shared/model/order-items.model';
import { IUsers } from 'app/shared/model/users.model';

export interface IOrders {
  id?: number;
  totalPrice?: string;
  createdDate?: string;
  orderItems?: IOrderItems[];
  users?: IUsers;
}

export class Orders implements IOrders {
  constructor(
    public id?: number,
    public totalPrice?: string,
    public createdDate?: string,
    public orderItems?: IOrderItems[],
    public users?: IUsers
  ) {}
}
