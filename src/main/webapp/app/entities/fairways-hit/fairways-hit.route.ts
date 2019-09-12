import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { FairwaysHit } from 'app/shared/model/fairways-hit.model';
import { FairwaysHitService } from './fairways-hit.service';
import { FairwaysHitComponent } from './fairways-hit.component';
import { FairwaysHitDetailComponent } from './fairways-hit-detail.component';
import { FairwaysHitUpdateComponent } from './fairways-hit-update.component';
import { FairwaysHitDeletePopupComponent } from './fairways-hit-delete-dialog.component';
import { IFairwaysHit } from 'app/shared/model/fairways-hit.model';

@Injectable({ providedIn: 'root' })
export class FairwaysHitResolve implements Resolve<IFairwaysHit> {
  constructor(private service: FairwaysHitService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IFairwaysHit> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<FairwaysHit>) => response.ok),
        map((fairwaysHit: HttpResponse<FairwaysHit>) => fairwaysHit.body)
      );
    }
    return of(new FairwaysHit());
  }
}

export const fairwaysHitRoute: Routes = [
  {
    path: '',
    component: FairwaysHitComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'FairwaysHits'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FairwaysHitDetailComponent,
    resolve: {
      fairwaysHit: FairwaysHitResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'FairwaysHits'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FairwaysHitUpdateComponent,
    resolve: {
      fairwaysHit: FairwaysHitResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'FairwaysHits'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FairwaysHitUpdateComponent,
    resolve: {
      fairwaysHit: FairwaysHitResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'FairwaysHits'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const fairwaysHitPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: FairwaysHitDeletePopupComponent,
    resolve: {
      fairwaysHit: FairwaysHitResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'FairwaysHits'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
