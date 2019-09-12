import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DrivingDistance } from 'app/shared/model/driving-distance.model';
import { DrivingDistanceService } from './driving-distance.service';
import { DrivingDistanceComponent } from './driving-distance.component';
import { DrivingDistanceDetailComponent } from './driving-distance-detail.component';
import { DrivingDistanceUpdateComponent } from './driving-distance-update.component';
import { DrivingDistanceDeletePopupComponent } from './driving-distance-delete-dialog.component';
import { IDrivingDistance } from 'app/shared/model/driving-distance.model';

@Injectable({ providedIn: 'root' })
export class DrivingDistanceResolve implements Resolve<IDrivingDistance> {
  constructor(private service: DrivingDistanceService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IDrivingDistance> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<DrivingDistance>) => response.ok),
        map((drivingDistance: HttpResponse<DrivingDistance>) => drivingDistance.body)
      );
    }
    return of(new DrivingDistance());
  }
}

export const drivingDistanceRoute: Routes = [
  {
    path: '',
    component: DrivingDistanceComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'DrivingDistances'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DrivingDistanceDetailComponent,
    resolve: {
      drivingDistance: DrivingDistanceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'DrivingDistances'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DrivingDistanceUpdateComponent,
    resolve: {
      drivingDistance: DrivingDistanceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'DrivingDistances'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DrivingDistanceUpdateComponent,
    resolve: {
      drivingDistance: DrivingDistanceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'DrivingDistances'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const drivingDistancePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: DrivingDistanceDeletePopupComponent,
    resolve: {
      drivingDistance: DrivingDistanceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'DrivingDistances'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
