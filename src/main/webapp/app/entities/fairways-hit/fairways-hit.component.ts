import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IFairwaysHit } from 'app/shared/model/fairways-hit.model';
import { AccountService } from 'app/core';
import { FairwaysHitService } from './fairways-hit.service';

@Component({
  selector: 'jhi-fairways-hit',
  templateUrl: './fairways-hit.component.html'
})
export class FairwaysHitComponent implements OnInit, OnDestroy {
  fairwaysHits: IFairwaysHit[];
  currentAccount: any;
  eventSubscriber: Subscription;
  currentSearch: string;

  constructor(
    protected fairwaysHitService: FairwaysHitService,
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
      this.fairwaysHitService
        .search({
          query: this.currentSearch
        })
        .pipe(
          filter((res: HttpResponse<IFairwaysHit[]>) => res.ok),
          map((res: HttpResponse<IFairwaysHit[]>) => res.body)
        )
        .subscribe((res: IFairwaysHit[]) => (this.fairwaysHits = res), (res: HttpErrorResponse) => this.onError(res.message));
      return;
    }
    this.fairwaysHitService
      .query()
      .pipe(
        filter((res: HttpResponse<IFairwaysHit[]>) => res.ok),
        map((res: HttpResponse<IFairwaysHit[]>) => res.body)
      )
      .subscribe(
        (res: IFairwaysHit[]) => {
          this.fairwaysHits = res;
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
    this.registerChangeInFairwaysHits();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IFairwaysHit) {
    return item.id;
  }

  registerChangeInFairwaysHits() {
    this.eventSubscriber = this.eventManager.subscribe('fairwaysHitListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
