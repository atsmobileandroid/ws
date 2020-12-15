import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUserType } from 'app/shared/model/user-type.model';

type EntityResponseType = HttpResponse<IUserType>;
type EntityArrayResponseType = HttpResponse<IUserType[]>;

@Injectable({ providedIn: 'root' })
export class UserTypeService {
  public resourceUrl = SERVER_API_URL + 'api/user-types';

  constructor(protected http: HttpClient) {}

  create(userType: IUserType): Observable<EntityResponseType> {
    return this.http.post<IUserType>(this.resourceUrl, userType, { observe: 'response' });
  }

  update(userType: IUserType): Observable<EntityResponseType> {
    return this.http.put<IUserType>(this.resourceUrl, userType, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUserType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUserType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
