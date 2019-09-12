import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISGTeeToGreen } from 'app/shared/model/sg-tee-to-green.model';
import { AccountService } from 'app/core';
import { SGTeeToGreenService } from './sg-tee-to-green.service';

@Component({
  selector: 'jhi-sg-tee-to-green',
  templateUrl: './sg-tee-to-green.component.html'
})
export class SGTeeToGreenComponent implements OnInit, OnDestroy {
  sGTeeToGreens: ISGTeeToGreen[];
  currentAccount: any;
  eventSubscriber: Subscription;
  currentSearch: string;

  constructor(
    protected sGTeeToGreenService: SGTeeToGreenService,
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
      this.sGTeeToGreenService
        .search({
          query: this.currentSearch
        })
        .pipe(
          filter((res: HttpResponse<ISGTeeToGreen[]>) => res.ok),
          map((res: HttpResponse<ISGTeeToGreen[]>) => res.body)
        )
        .subscribe((res: ISGTeeToGreen[]) => (this.sGTeeToGreens = res), (res: HttpErrorResponse) => this.onError(res.message));
      return;
    }
    this.sGTeeToGreenService
      .query()
      .pipe(
        filter((res: HttpResponse<ISGTeeToGreen[]>) => res.ok),
        map((res: HttpResponse<ISGTeeToGreen[]>) => res.body)
      )
      .subscribe(
        (res: ISGTeeToGreen[]) => {
          this.sGTeeToGreens = res;
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
    this.registerChangeInSGTeeToGreens();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ISGTeeToGreen) {
    return item.id;
  }

  registerChangeInSGTeeToGreens() {
    this.eventSubscriber = this.eventManager.subscribe('sGTeeToGreenListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
