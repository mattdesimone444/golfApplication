import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISGArroundTheGreen } from 'app/shared/model/sg-arround-the-green.model';
import { AccountService } from 'app/core';
import { SGArroundTheGreenService } from './sg-arround-the-green.service';

@Component({
  selector: 'jhi-sg-arround-the-green',
  templateUrl: './sg-arround-the-green.component.html'
})
export class SGArroundTheGreenComponent implements OnInit, OnDestroy {
  sGArroundTheGreens: ISGArroundTheGreen[];
  currentAccount: any;
  eventSubscriber: Subscription;
  currentSearch: string;

  constructor(
    protected sGArroundTheGreenService: SGArroundTheGreenService,
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
      this.sGArroundTheGreenService
        .search({
          query: this.currentSearch
        })
        .pipe(
          filter((res: HttpResponse<ISGArroundTheGreen[]>) => res.ok),
          map((res: HttpResponse<ISGArroundTheGreen[]>) => res.body)
        )
        .subscribe((res: ISGArroundTheGreen[]) => (this.sGArroundTheGreens = res), (res: HttpErrorResponse) => this.onError(res.message));
      return;
    }
    this.sGArroundTheGreenService
      .query()
      .pipe(
        filter((res: HttpResponse<ISGArroundTheGreen[]>) => res.ok),
        map((res: HttpResponse<ISGArroundTheGreen[]>) => res.body)
      )
      .subscribe(
        (res: ISGArroundTheGreen[]) => {
          this.sGArroundTheGreens = res;
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
    this.registerChangeInSGArroundTheGreens();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ISGArroundTheGreen) {
    return item.id;
  }

  registerChangeInSGArroundTheGreens() {
    this.eventSubscriber = this.eventManager.subscribe('sGArroundTheGreenListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
