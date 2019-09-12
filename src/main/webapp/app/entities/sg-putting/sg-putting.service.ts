import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISGPutting } from 'app/shared/model/sg-putting.model';

type EntityResponseType = HttpResponse<ISGPutting>;
type EntityArrayResponseType = HttpResponse<ISGPutting[]>;

@Injectable({ providedIn: 'root' })
export class SGPuttingService {
  public resourceUrl = SERVER_API_URL + 'api/sg-puttings';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/sg-puttings';

  constructor(protected http: HttpClient) {}

  create(sGPutting: ISGPutting): Observable<EntityResponseType> {
    return this.http.post<ISGPutting>(this.resourceUrl, sGPutting, { observe: 'response' });
  }

  update(sGPutting: ISGPutting): Observable<EntityResponseType> {
    return this.http.put<ISGPutting>(this.resourceUrl, sGPutting, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISGPutting>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISGPutting[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISGPutting[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
