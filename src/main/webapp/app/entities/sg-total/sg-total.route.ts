import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SGTotal } from 'app/shared/model/sg-total.model';
import { SGTotalService } from './sg-total.service';
import { SGTotalComponent } from './sg-total.component';
import { SGTotalDetailComponent } from './sg-total-detail.component';
import { SGTotalUpdateComponent } from './sg-total-update.component';
import { SGTotalDeletePopupComponent } from './sg-total-delete-dialog.component';
import { ISGTotal } from 'app/shared/model/sg-total.model';

@Injectable({ providedIn: 'root' })
export class SGTotalResolve implements Resolve<ISGTotal> {
  constructor(private service: SGTotalService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISGTotal> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<SGTotal>) => response.ok),
        map((sGTotal: HttpResponse<SGTotal>) => sGTotal.body)
      );
    }
    return of(new SGTotal());
  }
}

export const sGTotalRoute: Routes = [
  {
    path: '',
    component: SGTotalComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGTotals'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SGTotalDetailComponent,
    resolve: {
      sGTotal: SGTotalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGTotals'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SGTotalUpdateComponent,
    resolve: {
      sGTotal: SGTotalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGTotals'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SGTotalUpdateComponent,
    resolve: {
      sGTotal: SGTotalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGTotals'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const sGTotalPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: SGTotalDeletePopupComponent,
    resolve: {
      sGTotal: SGTotalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGTotals'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
