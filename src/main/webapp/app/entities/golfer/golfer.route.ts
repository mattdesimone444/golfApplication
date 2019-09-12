import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Golfer } from 'app/shared/model/golfer.model';
import { GolferService } from './golfer.service';
import { GolferComponent } from './golfer.component';
import { GolferDetailComponent } from './golfer-detail.component';
import { GolferUpdateComponent } from './golfer-update.component';
import { GolferDeletePopupComponent } from './golfer-delete-dialog.component';
import { IGolfer } from 'app/shared/model/golfer.model';

@Injectable({ providedIn: 'root' })
export class GolferResolve implements Resolve<IGolfer> {
  constructor(private service: GolferService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IGolfer> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Golfer>) => response.ok),
        map((golfer: HttpResponse<Golfer>) => golfer.body)
      );
    }
    return of(new Golfer());
  }
}

export const golferRoute: Routes = [
  {
    path: '',
    component: GolferComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Golfers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GolferDetailComponent,
    resolve: {
      golfer: GolferResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Golfers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GolferUpdateComponent,
    resolve: {
      golfer: GolferResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Golfers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GolferUpdateComponent,
    resolve: {
      golfer: GolferResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Golfers'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const golferPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: GolferDeletePopupComponent,
    resolve: {
      golfer: GolferResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Golfers'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
