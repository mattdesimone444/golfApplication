import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPuttsMadeDistance } from 'app/shared/model/putts-made-distance.model';

type EntityResponseType = HttpResponse<IPuttsMadeDistance>;
type EntityArrayResponseType = HttpResponse<IPuttsMadeDistance[]>;

@Injectable({ providedIn: 'root' })
export class PuttsMadeDistanceService {
  public resourceUrl = SERVER_API_URL + 'api/putts-made-distances';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/putts-made-distances';

  constructor(protected http: HttpClient) {}

  create(puttsMadeDistance: IPuttsMadeDistance): Observable<EntityResponseType> {
    return this.http.post<IPuttsMadeDistance>(this.resourceUrl, puttsMadeDistance, { observe: 'response' });
  }

  update(puttsMadeDistance: IPuttsMadeDistance): Observable<EntityResponseType> {
    return this.http.put<IPuttsMadeDistance>(this.resourceUrl, puttsMadeDistance, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPuttsMadeDistance>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPuttsMadeDistance[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPuttsMadeDistance[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
