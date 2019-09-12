import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GolfApplicationSharedModule } from 'app/shared';
import {
  GreensInRegulationComponent,
  GreensInRegulationDetailComponent,
  GreensInRegulationUpdateComponent,
  GreensInRegulationDeletePopupComponent,
  GreensInRegulationDeleteDialogComponent,
  greensInRegulationRoute,
  greensInRegulationPopupRoute
} from './';

const ENTITY_STATES = [...greensInRegulationRoute, ...greensInRegulationPopupRoute];

@NgModule({
  imports: [GolfApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    GreensInRegulationComponent,
    GreensInRegulationDetailComponent,
    GreensInRegulationUpdateComponent,
    GreensInRegulationDeleteDialogComponent,
    GreensInRegulationDeletePopupComponent
  ],
  entryComponents: [
    GreensInRegulationComponent,
    GreensInRegulationUpdateComponent,
    GreensInRegulationDeleteDialogComponent,
    GreensInRegulationDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GolfApplicationGreensInRegulationModule {}
