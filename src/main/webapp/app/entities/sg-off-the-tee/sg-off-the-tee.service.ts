import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISGOffTheTee } from 'app/shared/model/sg-off-the-tee.model';

type EntityResponseType = HttpResponse<ISGOffTheTee>;
type EntityArrayResponseType = HttpResponse<ISGOffTheTee[]>;

@Injectable({ providedIn: 'root' })
export class SGOffTheTeeService {
  public resourceUrl = SERVER_API_URL + 'api/sg-off-the-tees';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/sg-off-the-tees';

  constructor(protected http: HttpClient) {}

  create(sGOffTheTee: ISGOffTheTee): Observable<EntityResponseType> {
    return this.http.post<ISGOffTheTee>(this.resourceUrl, sGOffTheTee, { observe: 'response' });
  }

  update(sGOffTheTee: ISGOffTheTee): Observable<EntityResponseType> {
    return this.http.put<ISGOffTheTee>(this.resourceUrl, sGOffTheTee, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISGOffTheTee>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISGOffTheTee[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISGOffTheTee[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
