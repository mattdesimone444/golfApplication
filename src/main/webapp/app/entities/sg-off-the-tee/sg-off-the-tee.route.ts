import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SGOffTheTee } from 'app/shared/model/sg-off-the-tee.model';
import { SGOffTheTeeService } from './sg-off-the-tee.service';
import { SGOffTheTeeComponent } from './sg-off-the-tee.component';
import { SGOffTheTeeDetailComponent } from './sg-off-the-tee-detail.component';
import { SGOffTheTeeUpdateComponent } from './sg-off-the-tee-update.component';
import { SGOffTheTeeDeletePopupComponent } from './sg-off-the-tee-delete-dialog.component';
import { ISGOffTheTee } from 'app/shared/model/sg-off-the-tee.model';

@Injectable({ providedIn: 'root' })
export class SGOffTheTeeResolve implements Resolve<ISGOffTheTee> {
  constructor(private service: SGOffTheTeeService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISGOffTheTee> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<SGOffTheTee>) => response.ok),
        map((sGOffTheTee: HttpResponse<SGOffTheTee>) => sGOffTheTee.body)
      );
    }
    return of(new SGOffTheTee());
  }
}

export const sGOffTheTeeRoute: Routes = [
  {
    path: '',
    component: SGOffTheTeeComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGOffTheTees'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SGOffTheTeeDetailComponent,
    resolve: {
      sGOffTheTee: SGOffTheTeeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGOffTheTees'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SGOffTheTeeUpdateComponent,
    resolve: {
      sGOffTheTee: SGOffTheTeeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGOffTheTees'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SGOffTheTeeUpdateComponent,
    resolve: {
      sGOffTheTee: SGOffTheTeeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGOffTheTees'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const sGOffTheTeePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: SGOffTheTeeDeletePopupComponent,
    resolve: {
      sGOffTheTee: SGOffTheTeeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGOffTheTees'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
