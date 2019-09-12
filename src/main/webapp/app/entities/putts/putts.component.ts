import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPutts } from 'app/shared/model/putts.model';
import { AccountService } from 'app/core';
import { PuttsService } from './putts.service';

@Component({
  selector: 'jhi-putts',
  templateUrl: './putts.component.html'
})
export class PuttsComponent implements OnInit, OnDestroy {
  putts: IPutts[];
  currentAccount: any;
  eventSubscriber: Subscription;
  currentSearch: string;

  constructor(
    protected puttsService: PuttsService,
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
      this.puttsService
        .search({
          query: this.currentSearch
        })
        .pipe(
          filter((res: HttpResponse<IPutts[]>) => res.ok),
          map((res: HttpResponse<IPutts[]>) => res.body)
        )
        .subscribe((res: IPutts[]) => (this.putts = res), (res: HttpErrorResponse) => this.onError(res.message));
      return;
    }
    this.puttsService
      .query()
      .pipe(
        filter((res: HttpResponse<IPutts[]>) => res.ok),
        map((res: HttpResponse<IPutts[]>) => res.body)
      )
      .subscribe(
        (res: IPutts[]) => {
          this.putts = res;
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
    this.registerChangeInPutts();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IPutts) {
    return item.id;
  }

  registerChangeInPutts() {
    this.eventSubscriber = this.eventManager.subscribe('puttsListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
