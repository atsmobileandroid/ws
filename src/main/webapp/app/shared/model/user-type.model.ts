export interface IUserType {
  id?: number;
  name?: string;
}

export class UserType implements IUserType {
  constructor(public id?: number, public name?: string) {}
}
