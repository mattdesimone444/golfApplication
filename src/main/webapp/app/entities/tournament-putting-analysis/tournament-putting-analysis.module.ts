import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GolfApplicationSharedModule } from 'app/shared';
import {
  TournamentPuttingAnalysisComponent,
  TournamentPuttingAnalysisDetailComponent,
  TournamentPuttingAnalysisUpdateComponent,
  TournamentPuttingAnalysisDeletePopupComponent,
  TournamentPuttingAnalysisDeleteDialogComponent,
  tournamentPuttingAnalysisRoute,
  tournamentPuttingAnalysisPopupRoute
} from './';

const ENTITY_STATES = [...tournamentPuttingAnalysisRoute, ...tournamentPuttingAnalysisPopupRoute];

@NgModule({
  imports: [GolfApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    TournamentPuttingAnalysisComponent,
    TournamentPuttingAnalysisDetailComponent,
    TournamentPuttingAnalysisUpdateComponent,
    TournamentPuttingAnalysisDeleteDialogComponent,
    TournamentPuttingAnalysisDeletePopupComponent
  ],
  entryComponents: [
    TournamentPuttingAnalysisComponent,
    TournamentPuttingAnalysisUpdateComponent,
    TournamentPuttingAnalysisDeleteDialogComponent,
    TournamentPuttingAnalysisDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GolfApplicationTournamentPuttingAnalysisModule {}
