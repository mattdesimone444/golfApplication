import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IApproachShotDistanceToPin } from 'app/shared/model/approach-shot-distance-to-pin.model';

type EntityResponseType = HttpResponse<IApproachShotDistanceToPin>;
type EntityArrayResponseType = HttpResponse<IApproachShotDistanceToPin[]>;

@Injectable({ providedIn: 'root' })
export class ApproachShotDistanceToPinService {
  public resourceUrl = SERVER_API_URL + 'api/approach-shot-distance-to-pins';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/approach-shot-distance-to-pins';

  constructor(protected http: HttpClient) {}

  create(approachShotDistanceToPin: IApproachShotDistanceToPin): Observable<EntityResponseType> {
    return this.http.post<IApproachShotDistanceToPin>(this.resourceUrl, approachShotDistanceToPin, { observe: 'response' });
  }

  update(approachShotDistanceToPin: IApproachShotDistanceToPin): Observable<EntityResponseType> {
    return this.http.put<IApproachShotDistanceToPin>(this.resourceUrl, approachShotDistanceToPin, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IApproachShotDistanceToPin>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IApproachShotDistanceToPin[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IApproachShotDistanceToPin[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
