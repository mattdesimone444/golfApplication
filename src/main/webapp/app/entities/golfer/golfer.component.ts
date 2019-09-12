import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IGolfer } from 'app/shared/model/golfer.model';
import { AccountService } from 'app/core';
import { GolferService } from './golfer.service';

@Component({
  selector: 'jhi-golfer',
  templateUrl: './golfer.component.html'
})
export class GolferComponent implements OnInit, OnDestroy {
  golfers: IGolfer[];
  currentAccount: any;
  eventSubscriber: Subscription;
  currentSearch: string;

  constructor(
    protected golferService: GolferService,
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
      this.golferService
        .search({
          query: this.currentSearch
        })
        .pipe(
          filter((res: HttpResponse<IGolfer[]>) => res.ok),
          map((res: HttpResponse<IGolfer[]>) => res.body)
        )
        .subscribe((res: IGolfer[]) => (this.golfers = res), (res: HttpErrorResponse) => this.onError(res.message));
      return;
    }
    this.golferService
      .query()
      .pipe(
        filter((res: HttpResponse<IGolfer[]>) => res.ok),
        map((res: HttpResponse<IGolfer[]>) => res.body)
      )
      .subscribe(
        (res: IGolfer[]) => {
          this.golfers = res;
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
    this.registerChangeInGolfers();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IGolfer) {
    return item.id;
  }

  registerChangeInGolfers() {
    this.eventSubscriber = this.eventManager.subscribe('golferListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
