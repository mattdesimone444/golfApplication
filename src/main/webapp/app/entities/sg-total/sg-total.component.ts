import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISGTotal } from 'app/shared/model/sg-total.model';
import { AccountService } from 'app/core';
import { SGTotalService } from './sg-total.service';

@Component({
  selector: 'jhi-sg-total',
  templateUrl: './sg-total.component.html'
})
export class SGTotalComponent implements OnInit, OnDestroy {
  sGTotals: ISGTotal[];
  currentAccount: any;
  eventSubscriber: Subscription;
  currentSearch: string;

  constructor(
    protected sGTotalService: SGTotalService,
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
      this.sGTotalService
        .search({
          query: this.currentSearch
        })
        .pipe(
          filter((res: HttpResponse<ISGTotal[]>) => res.ok),
          map((res: HttpResponse<ISGTotal[]>) => res.body)
        )
        .subscribe((res: ISGTotal[]) => (this.sGTotals = res), (res: HttpErrorResponse) => this.onError(res.message));
      return;
    }
    this.sGTotalService
      .query()
      .pipe(
        filter((res: HttpResponse<ISGTotal[]>) => res.ok),
        map((res: HttpResponse<ISGTotal[]>) => res.body)
      )
      .subscribe(
        (res: ISGTotal[]) => {
          this.sGTotals = res;
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
    this.registerChangeInSGTotals();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ISGTotal) {
    return item.id;
  }

  registerChangeInSGTotals() {
    this.eventSubscriber = this.eventManager.subscribe('sGTotalListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
