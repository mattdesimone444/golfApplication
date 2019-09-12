import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GolfApplicationSharedModule } from 'app/shared';
import {
  PuttingAnalysisComponent,
  PuttingAnalysisDetailComponent,
  PuttingAnalysisUpdateComponent,
  PuttingAnalysisDeletePopupComponent,
  PuttingAnalysisDeleteDialogComponent,
  puttingAnalysisRoute,
  puttingAnalysisPopupRoute
} from './';

const ENTITY_STATES = [...puttingAnalysisRoute, ...puttingAnalysisPopupRoute];

@NgModule({
  imports: [GolfApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    PuttingAnalysisComponent,
    PuttingAnalysisDetailComponent,
    PuttingAnalysisUpdateComponent,
    PuttingAnalysisDeleteDialogComponent,
    PuttingAnalysisDeletePopupComponent
  ],
  entryComponents: [
    PuttingAnalysisComponent,
    PuttingAnalysisUpdateComponent,
    PuttingAnalysisDeleteDialogComponent,
    PuttingAnalysisDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GolfApplicationPuttingAnalysisModule {}
