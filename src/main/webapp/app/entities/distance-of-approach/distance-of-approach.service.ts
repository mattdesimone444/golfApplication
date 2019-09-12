import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDistanceOfApproach } from 'app/shared/model/distance-of-approach.model';

type EntityResponseType = HttpResponse<IDistanceOfApproach>;
type EntityArrayResponseType = HttpResponse<IDistanceOfApproach[]>;

@Injectable({ providedIn: 'root' })
export class DistanceOfApproachService {
  public resourceUrl = SERVER_API_URL + 'api/distance-of-approaches';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/distance-of-approaches';

  constructor(protected http: HttpClient) {}

  create(distanceOfApproach: IDistanceOfApproach): Observable<EntityResponseType> {
    return this.http.post<IDistanceOfApproach>(this.resourceUrl, distanceOfApproach, { observe: 'response' });
  }

  update(distanceOfApproach: IDistanceOfApproach): Observable<EntityResponseType> {
    return this.http.put<IDistanceOfApproach>(this.resourceUrl, distanceOfApproach, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDistanceOfApproach>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDistanceOfApproach[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDistanceOfApproach[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
