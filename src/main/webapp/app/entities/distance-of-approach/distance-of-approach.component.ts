import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IDistanceOfApproach } from 'app/shared/model/distance-of-approach.model';
import { AccountService } from 'app/core';
import { DistanceOfApproachService } from './distance-of-approach.service';

@Component({
  selector: 'jhi-distance-of-approach',
  templateUrl: './distance-of-approach.component.html'
})
export class DistanceOfApproachComponent implements OnInit, OnDestroy {
  distanceOfApproaches: IDistanceOfApproach[];
  currentAccount: any;
  eventSubscriber: Subscription;
  currentSearch: string;

  constructor(
    protected distanceOfApproachService: DistanceOfApproachService,
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
      this.distanceOfApproachService
        .search({
          query: this.currentSearch
        })
        .pipe(
          filter((res: HttpResponse<IDistanceOfApproach[]>) => res.ok),
          map((res: HttpResponse<IDistanceOfApproach[]>) => res.body)
        )
        .subscribe(
          (res: IDistanceOfApproach[]) => (this.distanceOfApproaches = res),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
      return;
    }
    this.distanceOfApproachService
      .query()
      .pipe(
        filter((res: HttpResponse<IDistanceOfApproach[]>) => res.ok),
        map((res: HttpResponse<IDistanceOfApproach[]>) => res.body)
      )
      .subscribe(
        (res: IDistanceOfApproach[]) => {
          this.distanceOfApproaches = res;
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
    this.registerChangeInDistanceOfApproaches();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IDistanceOfApproach) {
    return item.id;
  }

  registerChangeInDistanceOfApproaches() {
    this.eventSubscriber = this.eventManager.subscribe('distanceOfApproachListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
