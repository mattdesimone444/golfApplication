import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GolfApplicationSharedModule } from 'app/shared';
import {
  TournamentComponent,
  TournamentDetailComponent,
  TournamentUpdateComponent,
  TournamentDeletePopupComponent,
  TournamentDeleteDialogComponent,
  tournamentRoute,
  tournamentPopupRoute
} from './';

const ENTITY_STATES = [...tournamentRoute, ...tournamentPopupRoute];

@NgModule({
  imports: [GolfApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    TournamentComponent,
    TournamentDetailComponent,
    TournamentUpdateComponent,
    TournamentDeleteDialogComponent,
    TournamentDeletePopupComponent
  ],
  entryComponents: [TournamentComponent, TournamentUpdateComponent, TournamentDeleteDialogComponent, TournamentDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GolfApplicationTournamentModule {}
