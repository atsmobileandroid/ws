export interface ICountries {
  id?: number;
  countryNameEn?: string;
  countryNameAr?: string;
  flagImagePath?: string;
  currencyNameEn?: string;
  currencyNameAr?: string;
  exchangeRate?: string;
}

export class Countries implements ICountries {
  constructor(
    public id?: number,
    public countryNameEn?: string,
    public countryNameAr?: string,
    public flagImagePath?: string,
    public currencyNameEn?: string,
    public currencyNameAr?: string,
    public exchangeRate?: string
  ) {}
}
