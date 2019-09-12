import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITournamentPuttingAnalysis } from 'app/shared/model/tournament-putting-analysis.model';

type EntityResponseType = HttpResponse<ITournamentPuttingAnalysis>;
type EntityArrayResponseType = HttpResponse<ITournamentPuttingAnalysis[]>;

@Injectable({ providedIn: 'root' })
export class TournamentPuttingAnalysisService {
  public resourceUrl = SERVER_API_URL + 'api/tournament-putting-analyses';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/tournament-putting-analyses';

  constructor(protected http: HttpClient) {}

  create(tournamentPuttingAnalysis: ITournamentPuttingAnalysis): Observable<EntityResponseType> {
    return this.http.post<ITournamentPuttingAnalysis>(this.resourceUrl, tournamentPuttingAnalysis, { observe: 'response' });
  }

  update(tournamentPuttingAnalysis: ITournamentPuttingAnalysis): Observable<EntityResponseType> {
    return this.http.put<ITournamentPuttingAnalysis>(this.resourceUrl, tournamentPuttingAnalysis, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITournamentPuttingAnalysis>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITournamentPuttingAnalysis[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITournamentPuttingAnalysis[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
