import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SGTeeToGreen } from 'app/shared/model/sg-tee-to-green.model';
import { SGTeeToGreenService } from './sg-tee-to-green.service';
import { SGTeeToGreenComponent } from './sg-tee-to-green.component';
import { SGTeeToGreenDetailComponent } from './sg-tee-to-green-detail.component';
import { SGTeeToGreenUpdateComponent } from './sg-tee-to-green-update.component';
import { SGTeeToGreenDeletePopupComponent } from './sg-tee-to-green-delete-dialog.component';
import { ISGTeeToGreen } from 'app/shared/model/sg-tee-to-green.model';

@Injectable({ providedIn: 'root' })
export class SGTeeToGreenResolve implements Resolve<ISGTeeToGreen> {
  constructor(private service: SGTeeToGreenService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISGTeeToGreen> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<SGTeeToGreen>) => response.ok),
        map((sGTeeToGreen: HttpResponse<SGTeeToGreen>) => sGTeeToGreen.body)
      );
    }
    return of(new SGTeeToGreen());
  }
}

export const sGTeeToGreenRoute: Routes = [
  {
    path: '',
    component: SGTeeToGreenComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGTeeToGreens'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SGTeeToGreenDetailComponent,
    resolve: {
      sGTeeToGreen: SGTeeToGreenResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGTeeToGreens'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SGTeeToGreenUpdateComponent,
    resolve: {
      sGTeeToGreen: SGTeeToGreenResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGTeeToGreens'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SGTeeToGreenUpdateComponent,
    resolve: {
      sGTeeToGreen: SGTeeToGreenResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGTeeToGreens'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const sGTeeToGreenPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: SGTeeToGreenDeletePopupComponent,
    resolve: {
      sGTeeToGreen: SGTeeToGreenResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGTeeToGreens'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
