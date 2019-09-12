import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SGPutting } from 'app/shared/model/sg-putting.model';
import { SGPuttingService } from './sg-putting.service';
import { SGPuttingComponent } from './sg-putting.component';
import { SGPuttingDetailComponent } from './sg-putting-detail.component';
import { SGPuttingUpdateComponent } from './sg-putting-update.component';
import { SGPuttingDeletePopupComponent } from './sg-putting-delete-dialog.component';
import { ISGPutting } from 'app/shared/model/sg-putting.model';

@Injectable({ providedIn: 'root' })
export class SGPuttingResolve implements Resolve<ISGPutting> {
  constructor(private service: SGPuttingService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISGPutting> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<SGPutting>) => response.ok),
        map((sGPutting: HttpResponse<SGPutting>) => sGPutting.body)
      );
    }
    return of(new SGPutting());
  }
}

export const sGPuttingRoute: Routes = [
  {
    path: '',
    component: SGPuttingComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGPuttings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SGPuttingDetailComponent,
    resolve: {
      sGPutting: SGPuttingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGPuttings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SGPuttingUpdateComponent,
    resolve: {
      sGPutting: SGPuttingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGPuttings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SGPuttingUpdateComponent,
    resolve: {
      sGPutting: SGPuttingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGPuttings'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const sGPuttingPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: SGPuttingDeletePopupComponent,
    resolve: {
      sGPutting: SGPuttingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SGPuttings'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
