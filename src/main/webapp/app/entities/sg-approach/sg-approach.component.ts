import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISGApproach } from 'app/shared/model/sg-approach.model';
import { AccountService } from 'app/core';
import { SGApproachService } from './sg-approach.service';

@Component({
  selector: 'jhi-sg-approach',
  templateUrl: './sg-approach.component.html'
})
export class SGApproachComponent implements OnInit, OnDestroy {
  sGApproaches: ISGApproach[];
  currentAccount: any;
  eventSubscriber: Subscription;
  currentSearch: string;

  constructor(
    protected sGApproachService: SGApproachService,
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
      this.sGApproachService
        .search({
          query: this.currentSearch
        })
        .pipe(
          filter((res: HttpResponse<ISGApproach[]>) => res.ok),
          map((res: HttpResponse<ISGApproach[]>) => res.body)
        )
        .subscribe((res: ISGApproach[]) => (this.sGApproaches = res), (res: HttpErrorResponse) => this.onError(res.message));
      return;
    }
    this.sGApproachService
      .query()
      .pipe(
        filter((res: HttpResponse<ISGApproach[]>) => res.ok),
        map((res: HttpResponse<ISGApproach[]>) => res.body)
      )
      .subscribe(
        (res: ISGApproach[]) => {
          this.sGApproaches = res;
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
    this.registerChangeInSGApproaches();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ISGApproach) {
    return item.id;
  }

  registerChangeInSGApproaches() {
    this.eventSubscriber = this.eventManager.subscribe('sGApproachListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
