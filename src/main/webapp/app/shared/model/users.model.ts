import { IOrders } from 'app/shared/model/orders.model';

export interface IUsers {
  id?: number;
  email?: string;
  password?: string;
  fullName?: string;
  imagePath?: string;
  phoneNumber?: string;
  createdDate?: string;
  isActive?: boolean;
  deviceType?: number;
  deviceToken?: string;
  balance?: string;
  basketId?: number;
  orders?: IOrders[];
  countriesId?: number;
  userTypeId?: number;
}

export class Users implements IUsers {
  constructor(
    public id?: number,
    public email?: string,
    public password?: string,
    public fullName?: string,
    public imagePath?: string,
    public phoneNumber?: string,
    public createdDate?: string,
    public isActive?: boolean,
    public deviceType?: number,
    public deviceToken?: string,
    public balance?: string,
    public basketId?: number,
    public orders?: IOrders[],
    public countriesId?: number,
    public userTypeId?: number
  ) {
    this.isActive = this.isActive || false;
  }
}
