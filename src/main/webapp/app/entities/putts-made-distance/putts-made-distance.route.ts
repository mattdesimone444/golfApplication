import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PuttsMadeDistance } from 'app/shared/model/putts-made-distance.model';
import { PuttsMadeDistanceService } from './putts-made-distance.service';
import { PuttsMadeDistanceComponent } from './putts-made-distance.component';
import { PuttsMadeDistanceDetailComponent } from './putts-made-distance-detail.component';
import { PuttsMadeDistanceUpdateComponent } from './putts-made-distance-update.component';
import { PuttsMadeDistanceDeletePopupComponent } from './putts-made-distance-delete-dialog.component';
import { IPuttsMadeDistance } from 'app/shared/model/putts-made-distance.model';

@Injectable({ providedIn: 'root' })
export class PuttsMadeDistanceResolve implements Resolve<IPuttsMadeDistance> {
  constructor(private service: PuttsMadeDistanceService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPuttsMadeDistance> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<PuttsMadeDistance>) => response.ok),
        map((puttsMadeDistance: HttpResponse<PuttsMadeDistance>) => puttsMadeDistance.body)
      );
    }
    return of(new PuttsMadeDistance());
  }
}

export const puttsMadeDistanceRoute: Routes = [
  {
    path: '',
    component: PuttsMadeDistanceComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PuttsMadeDistances'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PuttsMadeDistanceDetailComponent,
    resolve: {
      puttsMadeDistance: PuttsMadeDistanceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PuttsMadeDistances'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PuttsMadeDistanceUpdateComponent,
    resolve: {
      puttsMadeDistance: PuttsMadeDistanceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PuttsMadeDistances'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PuttsMadeDistanceUpdateComponent,
    resolve: {
      puttsMadeDistance: PuttsMadeDistanceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PuttsMadeDistances'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const puttsMadeDistancePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PuttsMadeDistanceDeletePopupComponent,
    resolve: {
      puttsMadeDistance: PuttsMadeDistanceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PuttsMadeDistances'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
