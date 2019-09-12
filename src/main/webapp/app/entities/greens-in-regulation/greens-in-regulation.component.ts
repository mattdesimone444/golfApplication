import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IGreensInRegulation } from 'app/shared/model/greens-in-regulation.model';
import { AccountService } from 'app/core';
import { GreensInRegulationService } from './greens-in-regulation.service';

@Component({
  selector: 'jhi-greens-in-regulation',
  templateUrl: './greens-in-regulation.component.html'
})
export class GreensInRegulationComponent implements OnInit, OnDestroy {
  greensInRegulations: IGreensInRegulation[];
  currentAccount: any;
  eventSubscriber: Subscription;
  currentSearch: string;

  constructor(
    protected greensInRegulationService: GreensInRegulationService,
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
      this.greensInRegulationService
        .search({
          query: this.currentSearch
        })
        .pipe(
          filter((res: HttpResponse<IGreensInRegulation[]>) => res.ok),
          map((res: HttpResponse<IGreensInRegulation[]>) => res.body)
        )
        .subscribe((res: IGreensInRegulation[]) => (this.greensInRegulations = res), (res: HttpErrorResponse) => this.onError(res.message));
      return;
    }
    this.greensInRegulationService
      .query()
      .pipe(
        filter((res: HttpResponse<IGreensInRegulation[]>) => res.ok),
        map((res: HttpResponse<IGreensInRegulation[]>) => res.body)
      )
      .subscribe(
        (res: IGreensInRegulation[]) => {
          this.greensInRegulations = res;
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
    this.registerChangeInGreensInRegulations();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IGreensInRegulation) {
    return item.id;
  }

  registerChangeInGreensInRegulations() {
    this.eventSubscriber = this.eventManager.subscribe('greensInRegulationListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
