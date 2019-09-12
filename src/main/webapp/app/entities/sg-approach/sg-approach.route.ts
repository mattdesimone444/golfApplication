import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SGApproach } from 'app/shared/model/sg-approach.model';
import { SGApproachService } from './sg-approach.service';
import { SGApproachComponent } from './sg-approach.component';
import { SGApproachDetailComponent } from './sg-approach-detail.component';
import { SGApproachUpdateComponent } from './sg-approach-update.component';
import { SGApproachDeletePopupComponent } from './sg-approach-delete-dialog.component';
import { ISGApproach } from 'app/shared/model/sg-approach.model';

@Injectable({ providedIn: 'root' })
export class SGApproachResolve implements Resolve<ISGApproach> {
  constructor(private service: SGApproachService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISGApproach> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<SGApproach>) => response.ok),
        map((sGApproach: HttpResponse<SGApproach>) => sGApproach.body)
      );
    }
    return of(new SGApproach());
  }
}

export const sGApproachRoute: Routes = [
  {
    path: '',
    component: SGApproachComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGApproaches'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SGApproachDetailComponent,
    resolve: {
      sGApproach: SGApproachResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGApproaches'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SGApproachUpdateComponent,
    resolve: {
      sGApproach: SGApproachResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGApproaches'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SGApproachUpdateComponent,
    resolve: {
      sGApproach: SGApproachResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGApproaches'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const sGApproachPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: SGApproachDeletePopupComponent,
    resolve: {
      sGApproach: SGApproachResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGApproaches'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
