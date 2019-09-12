import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DistanceOfApproach } from 'app/shared/model/distance-of-approach.model';
import { DistanceOfApproachService } from './distance-of-approach.service';
import { DistanceOfApproachComponent } from './distance-of-approach.component';
import { DistanceOfApproachDetailComponent } from './distance-of-approach-detail.component';
import { DistanceOfApproachUpdateComponent } from './distance-of-approach-update.component';
import { DistanceOfApproachDeletePopupComponent } from './distance-of-approach-delete-dialog.component';
import { IDistanceOfApproach } from 'app/shared/model/distance-of-approach.model';

@Injectable({ providedIn: 'root' })
export class DistanceOfApproachResolve implements Resolve<IDistanceOfApproach> {
  constructor(private service: DistanceOfApproachService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IDistanceOfApproach> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<DistanceOfApproach>) => response.ok),
        map((distanceOfApproach: HttpResponse<DistanceOfApproach>) => distanceOfApproach.body)
      );
    }
    return of(new DistanceOfApproach());
  }
}

export const distanceOfApproachRoute: Routes = [
  {
    path: '',
    component: DistanceOfApproachComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'DistanceOfApproaches'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DistanceOfApproachDetailComponent,
    resolve: {
      distanceOfApproach: DistanceOfApproachResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'DistanceOfApproaches'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DistanceOfApproachUpdateComponent,
    resolve: {
      distanceOfApproach: DistanceOfApproachResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'DistanceOfApproaches'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DistanceOfApproachUpdateComponent,
    resolve: {
      distanceOfApproach: DistanceOfApproachResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'DistanceOfApproaches'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const distanceOfApproachPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: DistanceOfApproachDeletePopupComponent,
    resolve: {
      distanceOfApproach: DistanceOfApproachResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'DistanceOfApproaches'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
