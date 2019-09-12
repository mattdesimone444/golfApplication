import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPuttsMadeDistance } from 'app/shared/model/putts-made-distance.model';
import { AccountService } from 'app/core';
import { PuttsMadeDistanceService } from './putts-made-distance.service';

@Component({
  selector: 'jhi-putts-made-distance',
  templateUrl: './putts-made-distance.component.html'
})
export class PuttsMadeDistanceComponent implements OnInit, OnDestroy {
  puttsMadeDistances: IPuttsMadeDistance[];
  currentAccount: any;
  eventSubscriber: Subscription;
  currentSearch: string;

  constructor(
    protected puttsMadeDistanceService: PuttsMadeDistanceService,
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
      this.puttsMadeDistanceService
        .search({
          query: this.currentSearch
        })
        .pipe(
          filter((res: HttpResponse<IPuttsMadeDistance[]>) => res.ok),
          map((res: HttpResponse<IPuttsMadeDistance[]>) => res.body)
        )
        .subscribe((res: IPuttsMadeDistance[]) => (this.puttsMadeDistances = res), (res: HttpErrorResponse) => this.onError(res.message));
      return;
    }
    this.puttsMadeDistanceService
      .query()
      .pipe(
        filter((res: HttpResponse<IPuttsMadeDistance[]>) => res.ok),
        map((res: HttpResponse<IPuttsMadeDistance[]>) => res.body)
      )
      .subscribe(
        (res: IPuttsMadeDistance[]) => {
          this.puttsMadeDistances = res;
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
    this.registerChangeInPuttsMadeDistances();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IPuttsMadeDistance) {
    return item.id;
  }

  registerChangeInPuttsMadeDistances() {
    this.eventSubscriber = this.eventManager.subscribe('puttsMadeDistanceListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
