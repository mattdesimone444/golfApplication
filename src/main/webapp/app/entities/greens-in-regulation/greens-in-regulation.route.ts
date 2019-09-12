import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { GreensInRegulation } from 'app/shared/model/greens-in-regulation.model';
import { GreensInRegulationService } from './greens-in-regulation.service';
import { GreensInRegulationComponent } from './greens-in-regulation.component';
import { GreensInRegulationDetailComponent } from './greens-in-regulation-detail.component';
import { GreensInRegulationUpdateComponent } from './greens-in-regulation-update.component';
import { GreensInRegulationDeletePopupComponent } from './greens-in-regulation-delete-dialog.component';
import { IGreensInRegulation } from 'app/shared/model/greens-in-regulation.model';

@Injectable({ providedIn: 'root' })
export class GreensInRegulationResolve implements Resolve<IGreensInRegulation> {
  constructor(private service: GreensInRegulationService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IGreensInRegulation> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<GreensInRegulation>) => response.ok),
        map((greensInRegulation: HttpResponse<GreensInRegulation>) => greensInRegulation.body)
      );
    }
    return of(new GreensInRegulation());
  }
}

export const greensInRegulationRoute: Routes = [
  {
    path: '',
    component: GreensInRegulationComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'GreensInRegulations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GreensInRegulationDetailComponent,
    resolve: {
      greensInRegulation: GreensInRegulationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'GreensInRegulations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GreensInRegulationUpdateComponent,
    resolve: {
      greensInRegulation: GreensInRegulationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'GreensInRegulations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GreensInRegulationUpdateComponent,
    resolve: {
      greensInRegulation: GreensInRegulationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'GreensInRegulations'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const greensInRegulationPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: GreensInRegulationDeletePopupComponent,
    resolve: {
      greensInRegulation: GreensInRegulationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'GreensInRegulations'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
