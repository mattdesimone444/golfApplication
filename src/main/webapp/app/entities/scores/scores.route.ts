import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Scores } from 'app/shared/model/scores.model';
import { ScoresService } from './scores.service';
import { ScoresComponent } from './scores.component';
import { ScoresDetailComponent } from './scores-detail.component';
import { ScoresUpdateComponent } from './scores-update.component';
import { ScoresDeletePopupComponent } from './scores-delete-dialog.component';
import { IScores } from 'app/shared/model/scores.model';

@Injectable({ providedIn: 'root' })
export class ScoresResolve implements Resolve<IScores> {
  constructor(private service: ScoresService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IScores> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Scores>) => response.ok),
        map((scores: HttpResponse<Scores>) => scores.body)
      );
    }
    return of(new Scores());
  }
}

export const scoresRoute: Routes = [
  {
    path: '',
    component: ScoresComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Scores'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ScoresDetailComponent,
    resolve: {
      scores: ScoresResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Scores'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ScoresUpdateComponent,
    resolve: {
      scores: ScoresResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Scores'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ScoresUpdateComponent,
    resolve: {
      scores: ScoresResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Scores'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const scoresPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ScoresDeletePopupComponent,
    resolve: {
      scores: ScoresResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Scores'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
