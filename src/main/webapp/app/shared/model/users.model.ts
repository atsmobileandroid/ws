import { IBasket } from 'app/shared/model/basket.model';
import { IOrders } from 'app/shared/model/orders.model';
import { ICountries } from 'app/shared/model/countries.model';
import { IUserType } from 'app/shared/model/user-type.model';

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
  basket?: IBasket;
  orders?: IOrders[];
  countries?: ICountries;
  userType?: IUserType;
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
    public basket?: IBasket,
    public orders?: IOrders[],
    public countries?: ICountries,
    public userType?: IUserType
  ) {
    this.isActive = this.isActive || false;
  }
}
