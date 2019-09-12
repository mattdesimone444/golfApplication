import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISGPutting } from 'app/shared/model/sg-putting.model';
import { AccountService } from 'app/core';
import { SGPuttingService } from './sg-putting.service';

@Component({
  selector: 'jhi-sg-putting',
  templateUrl: './sg-putting.component.html'
})
export class SGPuttingComponent implements OnInit, OnDestroy {
  sGPuttings: ISGPutting[];
  currentAccount: any;
  eventSubscriber: Subscription;
  currentSearch: string;

  constructor(
    protected sGPuttingService: SGPuttingService,
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
      this.sGPuttingService
        .search({
          query: this.currentSearch
        })
        .pipe(
          filter((res: HttpResponse<ISGPutting[]>) => res.ok),
          map((res: HttpResponse<ISGPutting[]>) => res.body)
        )
        .subscribe((res: ISGPutting[]) => (this.sGPuttings = res), (res: HttpErrorResponse) => this.onError(res.message));
      return;
    }
    this.sGPuttingService
      .query()
      .pipe(
        filter((res: HttpResponse<ISGPutting[]>) => res.ok),
        map((res: HttpResponse<ISGPutting[]>) => res.body)
      )
      .subscribe(
        (res: ISGPutting[]) => {
          this.sGPuttings = res;
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
    this.registerChangeInSGPuttings();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ISGPutting) {
    return item.id;
  }

  registerChangeInSGPuttings() {
    this.eventSubscriber = this.eventManager.subscribe('sGPuttingListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
