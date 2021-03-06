import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITournament } from 'app/shared/model/tournament.model';

type EntityResponseType = HttpResponse<ITournament>;
type EntityArrayResponseType = HttpResponse<ITournament[]>;

@Injectable({ providedIn: 'root' })
export class TournamentService {
  public resourceUrl = SERVER_API_URL + 'api/tournaments';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/tournaments';

  constructor(protected http: HttpClient) {}

  create(tournament: ITournament): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tournament);
    return this.http
      .post<ITournament>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tournament: ITournament): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tournament);
    return this.http
      .put<ITournament>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITournament>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITournament[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITournament[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(tournament: ITournament): ITournament {
    const copy: ITournament = Object.assign({}, tournament, {
      startDate: tournament.startDate != null && tournament.startDate.isValid() ? tournament.startDate.format(DATE_FORMAT) : null,
      endDate: tournament.endDate != null && tournament.endDate.isValid() ? tournament.endDate.format(DATE_FORMAT) : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.startDate = res.body.startDate != null ? moment(res.body.startDate) : null;
      res.body.endDate = res.body.endDate != null ? moment(res.body.endDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tournament: ITournament) => {
        tournament.startDate = tournament.startDate != null ? moment(tournament.startDate) : null;
        tournament.endDate = tournament.endDate != null ? moment(tournament.endDate) : null;
      });
    }
    return res;
  }
}
