import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISGTotal } from 'app/shared/model/sg-total.model';

type EntityResponseType = HttpResponse<ISGTotal>;
type EntityArrayResponseType = HttpResponse<ISGTotal[]>;

@Injectable({ providedIn: 'root' })
export class SGTotalService {
  public resourceUrl = SERVER_API_URL + 'api/sg-totals';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/sg-totals';

  constructor(protected http: HttpClient) {}

  create(sGTotal: ISGTotal): Observable<EntityResponseType> {
    return this.http.post<ISGTotal>(this.resourceUrl, sGTotal, { observe: 'response' });
  }

  update(sGTotal: ISGTotal): Observable<EntityResponseType> {
    return this.http.put<ISGTotal>(this.resourceUrl, sGTotal, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISGTotal>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISGTotal[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISGTotal[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
