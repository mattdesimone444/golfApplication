import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TournamentPuttingAnalysis } from 'app/shared/model/tournament-putting-analysis.model';
import { TournamentPuttingAnalysisService } from './tournament-putting-analysis.service';
import { TournamentPuttingAnalysisComponent } from './tournament-putting-analysis.component';
import { TournamentPuttingAnalysisDetailComponent } from './tournament-putting-analysis-detail.component';
import { TournamentPuttingAnalysisUpdateComponent } from './tournament-putting-analysis-update.component';
import { TournamentPuttingAnalysisDeletePopupComponent } from './tournament-putting-analysis-delete-dialog.component';
import { ITournamentPuttingAnalysis } from 'app/shared/model/tournament-putting-analysis.model';

@Injectable({ providedIn: 'root' })
export class TournamentPuttingAnalysisResolve implements Resolve<ITournamentPuttingAnalysis> {
  constructor(private service: TournamentPuttingAnalysisService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITournamentPuttingAnalysis> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TournamentPuttingAnalysis>) => response.ok),
        map((tournamentPuttingAnalysis: HttpResponse<TournamentPuttingAnalysis>) => tournamentPuttingAnalysis.body)
      );
    }
    return of(new TournamentPuttingAnalysis());
  }
}

export const tournamentPuttingAnalysisRoute: Routes = [
  {
    path: '',
    component: TournamentPuttingAnalysisComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'TournamentPuttingAnalyses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TournamentPuttingAnalysisDetailComponent,
    resolve: {
      tournamentPuttingAnalysis: TournamentPuttingAnalysisResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'TournamentPuttingAnalyses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TournamentPuttingAnalysisUpdateComponent,
    resolve: {
      tournamentPuttingAnalysis: TournamentPuttingAnalysisResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'TournamentPuttingAnalyses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TournamentPuttingAnalysisUpdateComponent,
    resolve: {
      tournamentPuttingAnalysis: TournamentPuttingAnalysisResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'TournamentPuttingAnalyses'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const tournamentPuttingAnalysisPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TournamentPuttingAnalysisDeletePopupComponent,
    resolve: {
      tournamentPuttingAnalysis: TournamentPuttingAnalysisResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'TournamentPuttingAnalyses'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
