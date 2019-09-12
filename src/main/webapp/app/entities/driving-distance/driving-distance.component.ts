import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IDrivingDistance } from 'app/shared/model/driving-distance.model';
import { AccountService } from 'app/core';
import { DrivingDistanceService } from './driving-distance.service';

@Component({
  selector: 'jhi-driving-distance',
  templateUrl: './driving-distance.component.html'
})
export class DrivingDistanceComponent implements OnInit, OnDestroy {
  drivingDistances: IDrivingDistance[];
  currentAccount: any;
  eventSubscriber: Subscription;
  currentSearch: string;

  constructor(
    protected drivingDistanceService: DrivingDistanceService,
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
      this.drivingDistanceService
        .search({
          query: this.currentSearch
        })
        .pipe(
          filter((res: HttpResponse<IDrivingDistance[]>) => res.ok),
          map((res: HttpResponse<IDrivingDistance[]>) => res.body)
        )
        .subscribe((res: IDrivingDistance[]) => (this.drivingDistances = res), (res: HttpErrorResponse) => this.onError(res.message));
      return;
    }
    this.drivingDistanceService
      .query()
      .pipe(
        filter((res: HttpResponse<IDrivingDistance[]>) => res.ok),
        map((res: HttpResponse<IDrivingDistance[]>) => res.body)
      )
      .subscribe(
        (res: IDrivingDistance[]) => {
          this.drivingDistances = res;
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
    this.registerChangeInDrivingDistances();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IDrivingDistance) {
    return item.id;
  }

  registerChangeInDrivingDistances() {
    this.eventSubscriber = this.eventManager.subscribe('drivingDistanceListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
