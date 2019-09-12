import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SGArroundTheGreen } from 'app/shared/model/sg-arround-the-green.model';
import { SGArroundTheGreenService } from './sg-arround-the-green.service';
import { SGArroundTheGreenComponent } from './sg-arround-the-green.component';
import { SGArroundTheGreenDetailComponent } from './sg-arround-the-green-detail.component';
import { SGArroundTheGreenUpdateComponent } from './sg-arround-the-green-update.component';
import { SGArroundTheGreenDeletePopupComponent } from './sg-arround-the-green-delete-dialog.component';
import { ISGArroundTheGreen } from 'app/shared/model/sg-arround-the-green.model';

@Injectable({ providedIn: 'root' })
export class SGArroundTheGreenResolve implements Resolve<ISGArroundTheGreen> {
  constructor(private service: SGArroundTheGreenService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISGArroundTheGreen> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<SGArroundTheGreen>) => response.ok),
        map((sGArroundTheGreen: HttpResponse<SGArroundTheGreen>) => sGArroundTheGreen.body)
      );
    }
    return of(new SGArroundTheGreen());
  }
}

export const sGArroundTheGreenRoute: Routes = [
  {
    path: '',
    component: SGArroundTheGreenComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGArroundTheGreens'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SGArroundTheGreenDetailComponent,
    resolve: {
      sGArroundTheGreen: SGArroundTheGreenResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGArroundTheGreens'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SGArroundTheGreenUpdateComponent,
    resolve: {
      sGArroundTheGreen: SGArroundTheGreenResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGArroundTheGreens'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SGArroundTheGreenUpdateComponent,
    resolve: {
      sGArroundTheGreen: SGArroundTheGreenResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGArroundTheGreens'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const sGArroundTheGreenPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: SGArroundTheGreenDeletePopupComponent,
    resolve: {
      sGArroundTheGreen: SGArroundTheGreenResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGArroundTheGreens'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
