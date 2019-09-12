import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IScores } from 'app/shared/model/scores.model';
import { AccountService } from 'app/core';
import { ScoresService } from './scores.service';

@Component({
  selector: 'jhi-scores',
  templateUrl: './scores.component.html'
})
export class ScoresComponent implements OnInit, OnDestroy {
  scores: IScores[];
  currentAccount: any;
  eventSubscriber: Subscription;
  currentSearch: string;

  constructor(
    protected scoresService: ScoresService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected activatedRoute: ActivatedRoute,
    protected accountService: AccountService
  ) {
    this.currentSearch =
      this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['search'] ? this.activatedRoute.snapshot.params['search'] : '';
  }

  loadAll() {
    if (this.currentSearch) {
      this.scoresService
        .search({
          query: this.currentSearch
        })
        .pipe(
          filter((res: HttpResponse<IScores[]>) => res.ok),
          map((res: HttpResponse<IScores[]>) => res.body)
        )
        .subscribe((res: IScores[]) => (this.scores = res), (res: HttpErrorResponse) => this.onError(res.message));
      return;
    }
    this.scoresService
      .query()
      .pipe(
        filter((res: HttpResponse<IScores[]>) => res.ok),
        map((res: HttpResponse<IScores[]>) => res.body)
      )
      .subscribe(
        (res: IScores[]) => {
          this.scores = res;
          this.currentSearch = '';
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  search(query) {
    if (!query) {
      return this.clear();
    }
    this.currentSearch = query;
    this.loadAll();
  }

  clear() {
    this.currentSearch = '';
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInScores();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IScores) {
    return item.id;
  }

  registerChangeInScores() {
    this.eventSubscriber = this.eventManager.subscribe('scoresListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
