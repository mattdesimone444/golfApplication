import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GolfApplicationSharedModule } from 'app/shared';
import {
  SGTeeToGreenComponent,
  SGTeeToGreenDetailComponent,
  SGTeeToGreenUpdateComponent,
  SGTeeToGreenDeletePopupComponent,
  SGTeeToGreenDeleteDialogComponent,
  sGTeeToGreenRoute,
  sGTeeToGreenPopupRoute
} from './';

const ENTITY_STATES = [...sGTeeToGreenRoute, ...sGTeeToGreenPopupRoute];

@NgModule({
  imports: [GolfApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SGTeeToGreenComponent,
    SGTeeToGreenDetailComponent,
    SGTeeToGreenUpdateComponent,
    SGTeeToGreenDeleteDialogComponent,
    SGTeeToGreenDeletePopupComponent
  ],
  entryComponents: [
    SGTeeToGreenComponent,
    SGTeeToGreenUpdateComponent,
    SGTeeToGreenDeleteDialogComponent,
    SGTeeToGreenDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GolfApplicationSGTeeToGreenModule {}
