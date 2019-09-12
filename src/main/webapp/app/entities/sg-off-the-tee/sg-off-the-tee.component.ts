import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISGOffTheTee } from 'app/shared/model/sg-off-the-tee.model';
import { AccountService } from 'app/core';
import { SGOffTheTeeService } from './sg-off-the-tee.service';

@Component({
  selector: 'jhi-sg-off-the-tee',
  templateUrl: './sg-off-the-tee.component.html'
})
export class SGOffTheTeeComponent implements OnInit, OnDestroy {
  sGOffTheTees: ISGOffTheTee[];
  currentAccount: any;
  eventSubscriber: Subscription;
  currentSearch: string;

  constructor(
    protected sGOffTheTeeService: SGOffTheTeeService,
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
      this.sGOffTheTeeService
        .search({
          query: this.currentSearch
        })
        .pipe(
          filter((res: HttpResponse<ISGOffTheTee[]>) => res.ok),
          map((res: HttpResponse<ISGOffTheTee[]>) => res.body)
        )
        .subscribe((res: ISGOffTheTee[]) => (this.sGOffTheTees = res), (res: HttpErrorResponse) => this.onError(res.message));
      return;
    }
    this.sGOffTheTeeService
      .query()
      .pipe(
        filter((res: HttpResponse<ISGOffTheTee[]>) => res.ok),
        map((res: HttpResponse<ISGOffTheTee[]>) => res.body)
      )
      .subscribe(
        (res: ISGOffTheTee[]) => {
          this.sGOffTheTees = res;
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
    this.registerChangeInSGOffTheTees();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ISGOffTheTee) {
    return item.id;
  }

  registerChangeInSGOffTheTees() {
    this.eventSubscriber = this.eventManager.subscribe('sGOffTheTeeListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
