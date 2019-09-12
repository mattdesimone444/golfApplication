import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GolfApplicationSharedModule } from 'app/shared';
import {
  ScoresComponent,
  ScoresDetailComponent,
  ScoresUpdateComponent,
  ScoresDeletePopupComponent,
  ScoresDeleteDialogComponent,
  scoresRoute,
  scoresPopupRoute
} from './';

const ENTITY_STATES = [...scoresRoute, ...scoresPopupRoute];

@NgModule({
  imports: [GolfApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [ScoresComponent, ScoresDetailComponent, ScoresUpdateComponent, ScoresDeleteDialogComponent, ScoresDeletePopupComponent],
  entryComponents: [ScoresComponent, ScoresUpdateComponent, ScoresDeleteDialogComponent, ScoresDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GolfApplicationScoresModule {}
