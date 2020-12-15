import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IItemCodes } from 'app/shared/model/item-codes.model';

type EntityResponseType = HttpResponse<IItemCodes>;
type EntityArrayResponseType = HttpResponse<IItemCodes[]>;

@Injectable({ providedIn: 'root' })
export class ItemCodesService {
  public resourceUrl = SERVER_API_URL + 'api/item-codes';

  constructor(protected http: HttpClient) {}

  create(itemCodes: IItemCodes): Observable<EntityResponseType> {
    return this.http.post<IItemCodes>(this.resourceUrl, itemCodes, { observe: 'response' });
  }

  update(itemCodes: IItemCodes): Observable<EntityResponseType> {
    return this.http.put<IItemCodes>(this.resourceUrl, itemCodes, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IItemCodes>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IItemCodes[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
