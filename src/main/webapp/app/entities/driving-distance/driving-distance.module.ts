import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GolfApplicationSharedModule } from 'app/shared';
import {
  DrivingDistanceComponent,
  DrivingDistanceDetailComponent,
  DrivingDistanceUpdateComponent,
  DrivingDistanceDeletePopupComponent,
  DrivingDistanceDeleteDialogComponent,
  drivingDistanceRoute,
  drivingDistancePopupRoute
} from './';

const ENTITY_STATES = [...drivingDistanceRoute, ...drivingDistancePopupRoute];

@NgModule({
  imports: [GolfApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    DrivingDistanceComponent,
    DrivingDistanceDetailComponent,
    DrivingDistanceUpdateComponent,
    DrivingDistanceDeleteDialogComponent,
    DrivingDistanceDeletePopupComponent
  ],
  entryComponents: [
    DrivingDistanceComponent,
    DrivingDistanceUpdateComponent,
    DrivingDistanceDeleteDialogComponent,
    DrivingDistanceDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GolfApplicationDrivingDistanceModule {}
