import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPuttingAnalysis } from 'app/shared/model/putting-analysis.model';

type EntityResponseType = HttpResponse<IPuttingAnalysis>;
type EntityArrayResponseType = HttpResponse<IPuttingAnalysis[]>;

@Injectable({ providedIn: 'root' })
export class PuttingAnalysisService {
  public resourceUrl = SERVER_API_URL + 'api/putting-analyses';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/putting-analyses';

  constructor(protected http: HttpClient) {}

  create(puttingAnalysis: IPuttingAnalysis): Observable<EntityResponseType> {
    return this.http.post<IPuttingAnalysis>(this.resourceUrl, puttingAnalysis, { observe: 'response' });
  }

  update(puttingAnalysis: IPuttingAnalysis): Observable<EntityResponseType> {
    return this.http.put<IPuttingAnalysis>(this.resourceUrl, puttingAnalysis, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPuttingAnalysis>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPuttingAnalysis[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPuttingAnalysis[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
