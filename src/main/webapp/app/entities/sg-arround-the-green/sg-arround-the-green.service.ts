import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISGArroundTheGreen } from 'app/shared/model/sg-arround-the-green.model';

type EntityResponseType = HttpResponse<ISGArroundTheGreen>;
type EntityArrayResponseType = HttpResponse<ISGArroundTheGreen[]>;

@Injectable({ providedIn: 'root' })
export class SGArroundTheGreenService {
  public resourceUrl = SERVER_API_URL + 'api/sg-arround-the-greens';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/sg-arround-the-greens';

  constructor(protected http: HttpClient) {}

  create(sGArroundTheGreen: ISGArroundTheGreen): Observable<EntityResponseType> {
    return this.http.post<ISGArroundTheGreen>(this.resourceUrl, sGArroundTheGreen, { observe: 'response' });
  }

  update(sGArroundTheGreen: ISGArroundTheGreen): Observable<EntityResponseType> {
    return this.http.put<ISGArroundTheGreen>(this.resourceUrl, sGArroundTheGreen, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISGArroundTheGreen>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISGArroundTheGreen[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISGArroundTheGreen[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
