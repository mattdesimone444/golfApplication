import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISGTeeToGreen } from 'app/shared/model/sg-tee-to-green.model';

type EntityResponseType = HttpResponse<ISGTeeToGreen>;
type EntityArrayResponseType = HttpResponse<ISGTeeToGreen[]>;

@Injectable({ providedIn: 'root' })
export class SGTeeToGreenService {
  public resourceUrl = SERVER_API_URL + 'api/sg-tee-to-greens';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/sg-tee-to-greens';

  constructor(protected http: HttpClient) {}

  create(sGTeeToGreen: ISGTeeToGreen): Observable<EntityResponseType> {
    return this.http.post<ISGTeeToGreen>(this.resourceUrl, sGTeeToGreen, { observe: 'response' });
  }

  update(sGTeeToGreen: ISGTeeToGreen): Observable<EntityResponseType> {
    return this.http.put<ISGTeeToGreen>(this.resourceUrl, sGTeeToGreen, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISGTeeToGreen>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISGTeeToGreen[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISGTeeToGreen[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
