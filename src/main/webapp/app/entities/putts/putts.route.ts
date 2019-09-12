import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Putts } from 'app/shared/model/putts.model';
import { PuttsService } from './putts.service';
import { PuttsComponent } from './putts.component';
import { PuttsDetailComponent } from './putts-detail.component';
import { PuttsUpdateComponent } from './putts-update.component';
import { PuttsDeletePopupComponent } from './putts-delete-dialog.component';
import { IPutts } from 'app/shared/model/putts.model';

@Injectable({ providedIn: 'root' })
export class PuttsResolve implements Resolve<IPutts> {
  constructor(private service: PuttsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPutts> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Putts>) => response.ok),
        map((putts: HttpResponse<Putts>) => putts.body)
      );
    }
    return of(new Putts());
  }
}

export const puttsRoute: Routes = [
  {
    path: '',
    component: PuttsComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Putts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PuttsDetailComponent,
    resolve: {
      putts: PuttsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Putts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PuttsUpdateComponent,
    resolve: {
      putts: PuttsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Putts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PuttsUpdateComponent,
    resolve: {
      putts: PuttsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Putts'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const puttsPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PuttsDeletePopupComponent,
    resolve: {
      putts: PuttsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Putts'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
