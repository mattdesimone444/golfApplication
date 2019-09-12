import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SandSaves } from 'app/shared/model/sand-saves.model';
import { SandSavesService } from './sand-saves.service';
import { SandSavesComponent } from './sand-saves.component';
import { SandSavesDetailComponent } from './sand-saves-detail.component';
import { SandSavesUpdateComponent } from './sand-saves-update.component';
import { SandSavesDeletePopupComponent } from './sand-saves-delete-dialog.component';
import { ISandSaves } from 'app/shared/model/sand-saves.model';

@Injectable({ providedIn: 'root' })
export class SandSavesResolve implements Resolve<ISandSaves> {
  constructor(private service: SandSavesService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISandSaves> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<SandSaves>) => response.ok),
        map((sandSaves: HttpResponse<SandSaves>) => sandSaves.body)
      );
    }
    return of(new SandSaves());
  }
}

export const sandSavesRoute: Routes = [
  {
    path: '',
    component: SandSavesComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SandSaves'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SandSavesDetailComponent,
    resolve: {
      sandSaves: SandSavesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SandSaves'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SandSavesUpdateComponent,
    resolve: {
      sandSaves: SandSavesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SandSaves'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SandSavesUpdateComponent,
    resolve: {
      sandSaves: SandSavesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SandSaves'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const sandSavesPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: SandSavesDeletePopupComponent,
    resolve: {
      sandSaves: SandSavesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SandSaves'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
