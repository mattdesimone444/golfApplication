import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPutts } from 'app/shared/model/putts.model';

type EntityResponseType = HttpResponse<IPutts>;
type EntityArrayResponseType = HttpResponse<IPutts[]>;

@Injectable({ providedIn: 'root' })
export class PuttsService {
  public resourceUrl = SERVER_API_URL + 'api/putts';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/putts';

  constructor(protected http: HttpClient) {}

  create(putts: IPutts): Observable<EntityResponseType> {
    return this.http.post<IPutts>(this.resourceUrl, putts, { observe: 'response' });
  }

  update(putts: IPutts): Observable<EntityResponseType> {
    return this.http.put<IPutts>(this.resourceUrl, putts, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPutts>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPutts[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPutts[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
