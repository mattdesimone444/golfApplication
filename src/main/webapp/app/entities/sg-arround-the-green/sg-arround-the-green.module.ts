import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GolfApplicationSharedModule } from 'app/shared';
import {
  SGArroundTheGreenComponent,
  SGArroundTheGreenDetailComponent,
  SGArroundTheGreenUpdateComponent,
  SGArroundTheGreenDeletePopupComponent,
  SGArroundTheGreenDeleteDialogComponent,
  sGArroundTheGreenRoute,
  sGArroundTheGreenPopupRoute
} from './';

const ENTITY_STATES = [...sGArroundTheGreenRoute, ...sGArroundTheGreenPopupRoute];

@NgModule({
  imports: [GolfApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SGArroundTheGreenComponent,
    SGArroundTheGreenDetailComponent,
    SGArroundTheGreenUpdateComponent,
    SGArroundTheGreenDeleteDialogComponent,
    SGArroundTheGreenDeletePopupComponent
  ],
  entryComponents: [
    SGArroundTheGreenComponent,
    SGArroundTheGreenUpdateComponent,
    SGArroundTheGreenDeleteDialogComponent,
    SGArroundTheGreenDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GolfApplicationSGArroundTheGreenModule {}
