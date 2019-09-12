import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ApproachShotDistanceToPin } from 'app/shared/model/approach-shot-distance-to-pin.model';
import { ApproachShotDistanceToPinService } from './approach-shot-distance-to-pin.service';
import { ApproachShotDistanceToPinComponent } from './approach-shot-distance-to-pin.component';
import { ApproachShotDistanceToPinDetailComponent } from './approach-shot-distance-to-pin-detail.component';
import { ApproachShotDistanceToPinUpdateComponent } from './approach-shot-distance-to-pin-update.component';
import { ApproachShotDistanceToPinDeletePopupComponent } from './approach-shot-distance-to-pin-delete-dialog.component';
import { IApproachShotDistanceToPin } from 'app/shared/model/approach-shot-distance-to-pin.model';

@Injectable({ providedIn: 'root' })
export class ApproachShotDistanceToPinResolve implements Resolve<IApproachShotDistanceToPin> {
  constructor(private service: ApproachShotDistanceToPinService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IApproachShotDistanceToPin> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ApproachShotDistanceToPin>) => response.ok),
        map((approachShotDistanceToPin: HttpResponse<ApproachShotDistanceToPin>) => approachShotDistanceToPin.body)
      );
    }
    return of(new ApproachShotDistanceToPin());
  }
}

export const approachShotDistanceToPinRoute: Routes = [
  {
    path: '',
    component: ApproachShotDistanceToPinComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApproachShotDistanceToPins'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ApproachShotDistanceToPinDetailComponent,
    resolve: {
      approachShotDistanceToPin: ApproachShotDistanceToPinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApproachShotDistanceToPins'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ApproachShotDistanceToPinUpdateComponent,
    resolve: {
      approachShotDistanceToPin: ApproachShotDistanceToPinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApproachShotDistanceToPins'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ApproachShotDistanceToPinUpdateComponent,
    resolve: {
      approachShotDistanceToPin: ApproachShotDistanceToPinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApproachShotDistanceToPins'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const approachShotDistanceToPinPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ApproachShotDistanceToPinDeletePopupComponent,
    resolve: {
      approachShotDistanceToPin: ApproachShotDistanceToPinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApproachShotDistanceToPins'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
