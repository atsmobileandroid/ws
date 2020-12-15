import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IItemPrices } from 'app/shared/model/item-prices.model';

type EntityResponseType = HttpResponse<IItemPrices>;
type EntityArrayResponseType = HttpResponse<IItemPrices[]>;

@Injectable({ providedIn: 'root' })
export class ItemPricesService {
  public resourceUrl = SERVER_API_URL + 'api/item-prices';

  constructor(protected http: HttpClient) {}

  create(itemPrices: IItemPrices): Observable<EntityResponseType> {
    return this.http.post<IItemPrices>(this.resourceUrl, itemPrices, { observe: 'response' });
  }

  update(itemPrices: IItemPrices): Observable<EntityResponseType> {
    return this.http.put<IItemPrices>(this.resourceUrl, itemPrices, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IItemPrices>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IItemPrices[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
