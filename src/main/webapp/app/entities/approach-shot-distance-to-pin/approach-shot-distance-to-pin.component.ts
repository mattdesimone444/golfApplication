import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IApproachShotDistanceToPin } from 'app/shared/model/approach-shot-distance-to-pin.model';
import { AccountService } from 'app/core';
import { ApproachShotDistanceToPinService } from './approach-shot-distance-to-pin.service';

@Component({
  selector: 'jhi-approach-shot-distance-to-pin',
  templateUrl: './approach-shot-distance-to-pin.component.html'
})
export class ApproachShotDistanceToPinComponent implements OnInit, OnDestroy {
  approachShotDistanceToPins: IApproachShotDistanceToPin[];
  currentAccount: any;
  eventSubscriber: Subscription;
  currentSearch: string;

  constructor(
    protected approachShotDistanceToPinService: ApproachShotDistanceToPinService,
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
      this.approachShotDistanceToPinService
        .search({
          query: this.currentSearch
        })
        .pipe(
          filter((res: HttpResponse<IApproachShotDistanceToPin[]>) => res.ok),
          map((res: HttpResponse<IApproachShotDistanceToPin[]>) => res.body)
        )
        .subscribe(
          (res: IApproachShotDistanceToPin[]) => (this.approachShotDistanceToPins = res),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
      return;
    }
    this.approachShotDistanceToPinService
      .query()
      .pipe(
        filter((res: HttpResponse<IApproachShotDistanceToPin[]>) => res.ok),
        map((res: HttpResponse<IApproachShotDistanceToPin[]>) => res.body)
      )
      .subscribe(
        (res: IApproachShotDistanceToPin[]) => {
          this.approachShotDistanceToPins = res;
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
    this.registerChangeInApproachShotDistanceToPins();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IApproachShotDistanceToPin) {
    return item.id;
  }

  registerChangeInApproachShotDistanceToPins() {
    this.eventSubscriber = this.eventManager.subscribe('approachShotDistanceToPinListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
