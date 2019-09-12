import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITournamentPuttingAnalysis } from 'app/shared/model/tournament-putting-analysis.model';
import { AccountService } from 'app/core';
import { TournamentPuttingAnalysisService } from './tournament-putting-analysis.service';

@Component({
  selector: 'jhi-tournament-putting-analysis',
  templateUrl: './tournament-putting-analysis.component.html'
})
export class TournamentPuttingAnalysisComponent implements OnInit, OnDestroy {
  tournamentPuttingAnalyses: ITournamentPuttingAnalysis[];
  currentAccount: any;
  eventSubscriber: Subscription;
  currentSearch: string;

  constructor(
    protected tournamentPuttingAnalysisService: TournamentPuttingAnalysisService,
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
      this.tournamentPuttingAnalysisService
        .search({
          query: this.currentSearch
        })
        .pipe(
          filter((res: HttpResponse<ITournamentPuttingAnalysis[]>) => res.ok),
          map((res: HttpResponse<ITournamentPuttingAnalysis[]>) => res.body)
        )
        .subscribe(
          (res: ITournamentPuttingAnalysis[]) => (this.tournamentPuttingAnalyses = res),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
      return;
    }
    this.tournamentPuttingAnalysisService
      .query()
      .pipe(
        filter((res: HttpResponse<ITournamentPuttingAnalysis[]>) => res.ok),
        map((res: HttpResponse<ITournamentPuttingAnalysis[]>) => res.body)
      )
      .subscribe(
        (res: ITournamentPuttingAnalysis[]) => {
          this.tournamentPuttingAnalyses = res;
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
    this.registerChangeInTournamentPuttingAnalyses();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ITournamentPuttingAnalysis) {
    return item.id;
  }

  registerChangeInTournamentPuttingAnalyses() {
    this.eventSubscriber = this.eventManager.subscribe('tournamentPuttingAnalysisListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
