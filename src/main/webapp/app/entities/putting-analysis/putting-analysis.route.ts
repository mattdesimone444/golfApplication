import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PuttingAnalysis } from 'app/shared/model/putting-analysis.model';
import { PuttingAnalysisService } from './putting-analysis.service';
import { PuttingAnalysisComponent } from './putting-analysis.component';
import { PuttingAnalysisDetailComponent } from './putting-analysis-detail.component';
import { PuttingAnalysisUpdateComponent } from './putting-analysis-update.component';
import { PuttingAnalysisDeletePopupComponent } from './putting-analysis-delete-dialog.component';
import { IPuttingAnalysis } from 'app/shared/model/putting-analysis.model';

@Injectable({ providedIn: 'root' })
export class PuttingAnalysisResolve implements Resolve<IPuttingAnalysis> {
  constructor(private service: PuttingAnalysisService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPuttingAnalysis> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<PuttingAnalysis>) => response.ok),
        map((puttingAnalysis: HttpResponse<PuttingAnalysis>) => puttingAnalysis.body)
      );
    }
    return of(new PuttingAnalysis());
  }
}

export const puttingAnalysisRoute: Routes = [
  {
    path: '',
    component: PuttingAnalysisComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PuttingAnalyses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PuttingAnalysisDetailComponent,
    resolve: {
      puttingAnalysis: PuttingAnalysisResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PuttingAnalyses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PuttingAnalysisUpdateComponent,
    resolve: {
      puttingAnalysis: PuttingAnalysisResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PuttingAnalyses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PuttingAnalysisUpdateComponent,
    resolve: {
      puttingAnalysis: PuttingAnalysisResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PuttingAnalyses'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const puttingAnalysisPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PuttingAnalysisDeletePopupComponent,
    resolve: {
      puttingAnalysis: PuttingAnalysisResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PuttingAnalyses'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
