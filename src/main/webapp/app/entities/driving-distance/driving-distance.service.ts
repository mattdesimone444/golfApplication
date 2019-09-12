import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDrivingDistance } from 'app/shared/model/driving-distance.model';

type EntityResponseType = HttpResponse<IDrivingDistance>;
type EntityArrayResponseType = HttpResponse<IDrivingDistance[]>;

@Injectable({ providedIn: 'root' })
export class DrivingDistanceService {
  public resourceUrl = SERVER_API_URL + 'api/driving-distances';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/driving-distances';

  constructor(protected http: HttpClient) {}

  create(drivingDistance: IDrivingDistance): Observable<EntityResponseType> {
    return this.http.post<IDrivingDistance>(this.resourceUrl, drivingDistance, { observe: 'response' });
  }

  update(drivingDistance: IDrivingDistance): Observable<EntityResponseType> {
    return this.http.put<IDrivingDistance>(this.resourceUrl, drivingDistance, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDrivingDistance>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDrivingDistance[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDrivingDistance[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
