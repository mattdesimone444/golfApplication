import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFairwaysHit } from 'app/shared/model/fairways-hit.model';

type EntityResponseType = HttpResponse<IFairwaysHit>;
type EntityArrayResponseType = HttpResponse<IFairwaysHit[]>;

@Injectable({ providedIn: 'root' })
export class FairwaysHitService {
  public resourceUrl = SERVER_API_URL + 'api/fairways-hits';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/fairways-hits';

  constructor(protected http: HttpClient) {}

  create(fairwaysHit: IFairwaysHit): Observable<EntityResponseType> {
    return this.http.post<IFairwaysHit>(this.resourceUrl, fairwaysHit, { observe: 'response' });
  }

  update(fairwaysHit: IFairwaysHit): Observable<EntityResponseType> {
    return this.http.put<IFairwaysHit>(this.resourceUrl, fairwaysHit, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFairwaysHit>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFairwaysHit[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFairwaysHit[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
