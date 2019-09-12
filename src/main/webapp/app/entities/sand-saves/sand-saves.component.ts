import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISandSaves } from 'app/shared/model/sand-saves.model';
import { AccountService } from 'app/core';
import { SandSavesService } from './sand-saves.service';

@Component({
  selector: 'jhi-sand-saves',
  templateUrl: './sand-saves.component.html'
})
export class SandSavesComponent implements OnInit, OnDestroy {
  sandSaves: ISandSaves[];
  currentAccount: any;
  eventSubscriber: Subscription;
  currentSearch: string;

  constructor(
    protected sandSavesService: SandSavesService,
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
      this.sandSavesService
        .search({
          query: this.currentSearch
        })
        .pipe(
          filter((res: HttpResponse<ISandSaves[]>) => res.ok),
          map((res: HttpResponse<ISandSaves[]>) => res.body)
        )
        .subscribe((res: ISandSaves[]) => (this.sandSaves = res), (res: HttpErrorResponse) => this.onError(res.message));
      return;
    }
    this.sandSavesService
      .query()
      .pipe(
        filter((res: HttpResponse<ISandSaves[]>) => res.ok),
        map((res: HttpResponse<ISandSaves[]>) => res.body)
      )
      .subscribe(
        (res: ISandSaves[]) => {
          this.sandSaves = res;
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
    this.registerChangeInSandSaves();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ISandSaves) {
    return item.id;
  }

  registerChangeInSandSaves() {
    this.eventSubscriber = this.eventManager.subscribe('sandSavesListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
