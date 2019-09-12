import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GolfApplicationSharedModule } from 'app/shared';
import {
  DistanceOfApproachComponent,
  DistanceOfApproachDetailComponent,
  DistanceOfApproachUpdateComponent,
  DistanceOfApproachDeletePopupComponent,
  DistanceOfApproachDeleteDialogComponent,
  distanceOfApproachRoute,
  distanceOfApproachPopupRoute
} from './';

const ENTITY_STATES = [...distanceOfApproachRoute, ...distanceOfApproachPopupRoute];

@NgModule({
  imports: [GolfApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    DistanceOfApproachComponent,
    DistanceOfApproachDetailComponent,
    DistanceOfApproachUpdateComponent,
    DistanceOfApproachDeleteDialogComponent,
    DistanceOfApproachDeletePopupComponent
  ],
  entryComponents: [
    DistanceOfApproachComponent,
    DistanceOfApproachUpdateComponent,
    DistanceOfApproachDeleteDialogComponent,
    DistanceOfApproachDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GolfApplicationDistanceOfApproachModule {}
