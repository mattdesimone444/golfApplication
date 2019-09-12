import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGolfer } from 'app/shared/model/golfer.model';

type EntityResponseType = HttpResponse<IGolfer>;
type EntityArrayResponseType = HttpResponse<IGolfer[]>;

@Injectable({ providedIn: 'root' })
export class GolferService {
  public resourceUrl = SERVER_API_URL + 'api/golfers';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/golfers';

  constructor(protected http: HttpClient) {}

  create(golfer: IGolfer): Observable<EntityResponseType> {
    return this.http.post<IGolfer>(this.resourceUrl, golfer, { observe: 'response' });
  }

  update(golfer: IGolfer): Observable<EntityResponseType> {
    return this.http.put<IGolfer>(this.resourceUrl, golfer, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGolfer>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGolfer[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGolfer[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
