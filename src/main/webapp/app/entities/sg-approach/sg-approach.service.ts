import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISGApproach } from 'app/shared/model/sg-approach.model';

type EntityResponseType = HttpResponse<ISGApproach>;
type EntityArrayResponseType = HttpResponse<ISGApproach[]>;

@Injectable({ providedIn: 'root' })
export class SGApproachService {
  public resourceUrl = SERVER_API_URL + 'api/sg-approaches';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/sg-approaches';

  constructor(protected http: HttpClient) {}

  create(sGApproach: ISGApproach): Observable<EntityResponseType> {
    return this.http.post<ISGApproach>(this.resourceUrl, sGApproach, { observe: 'response' });
  }

  update(sGApproach: ISGApproach): Observable<EntityResponseType> {
    return this.http.put<ISGApproach>(this.resourceUrl, sGApproach, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISGApproach>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISGApproach[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISGApproach[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
