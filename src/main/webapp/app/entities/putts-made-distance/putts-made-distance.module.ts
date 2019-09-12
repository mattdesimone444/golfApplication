import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GolfApplicationSharedModule } from 'app/shared';
import {
  PuttsMadeDistanceComponent,
  PuttsMadeDistanceDetailComponent,
  PuttsMadeDistanceUpdateComponent,
  PuttsMadeDistanceDeletePopupComponent,
  PuttsMadeDistanceDeleteDialogComponent,
  puttsMadeDistanceRoute,
  puttsMadeDistancePopupRoute
} from './';

const ENTITY_STATES = [...puttsMadeDistanceRoute, ...puttsMadeDistancePopupRoute];

@NgModule({
  imports: [GolfApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    PuttsMadeDistanceComponent,
    PuttsMadeDistanceDetailComponent,
    PuttsMadeDistanceUpdateComponent,
    PuttsMadeDistanceDeleteDialogComponent,
    PuttsMadeDistanceDeletePopupComponent
  ],
  entryComponents: [
    PuttsMadeDistanceComponent,
    PuttsMadeDistanceUpdateComponent,
    PuttsMadeDistanceDeleteDialogComponent,
    PuttsMadeDistanceDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GolfApplicationPuttsMadeDistanceModule {}
