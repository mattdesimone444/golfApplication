import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISandSaves } from 'app/shared/model/sand-saves.model';

type EntityResponseType = HttpResponse<ISandSaves>;
type EntityArrayResponseType = HttpResponse<ISandSaves[]>;

@Injectable({ providedIn: 'root' })
export class SandSavesService {
  public resourceUrl = SERVER_API_URL + 'api/sand-saves';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/sand-saves';

  constructor(protected http: HttpClient) {}

  create(sandSaves: ISandSaves): Observable<EntityResponseType> {
    return this.http.post<ISandSaves>(this.resourceUrl, sandSaves, { observe: 'response' });
  }

  update(sandSaves: ISandSaves): Observable<EntityResponseType> {
    return this.http.put<ISandSaves>(this.resourceUrl, sandSaves, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISandSaves>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISandSaves[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISandSaves[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
