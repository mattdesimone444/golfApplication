import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGreensInRegulation } from 'app/shared/model/greens-in-regulation.model';

type EntityResponseType = HttpResponse<IGreensInRegulation>;
type EntityArrayResponseType = HttpResponse<IGreensInRegulation[]>;

@Injectable({ providedIn: 'root' })
export class GreensInRegulationService {
  public resourceUrl = SERVER_API_URL + 'api/greens-in-regulations';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/greens-in-regulations';

  constructor(protected http: HttpClient) {}

  create(greensInRegulation: IGreensInRegulation): Observable<EntityResponseType> {
    return this.http.post<IGreensInRegulation>(this.resourceUrl, greensInRegulation, { observe: 'response' });
  }

  update(greensInRegulation: IGreensInRegulation): Observable<EntityResponseType> {
    return this.http.put<IGreensInRegulation>(this.resourceUrl, greensInRegulation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGreensInRegulation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGreensInRegulation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGreensInRegulation[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
