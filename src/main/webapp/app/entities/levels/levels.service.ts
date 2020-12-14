import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILevels } from 'app/shared/model/levels.model';

type EntityResponseType = HttpResponse<ILevels>;
type EntityArrayResponseType = HttpResponse<ILevels[]>;

@Injectable({ providedIn: 'root' })
export class LevelsService {
  public resourceUrl = SERVER_API_URL + 'api/levels';

  constructor(protected http: HttpClient) {}

  create(levels: ILevels): Observable<EntityResponseType> {
    return this.http.post<ILevels>(this.resourceUrl, levels, { observe: 'response' });
  }

  update(levels: ILevels): Observable<EntityResponseType> {
    return this.http.put<ILevels>(this.resourceUrl, levels, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILevels>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILevels[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
