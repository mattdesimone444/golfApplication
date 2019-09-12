import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GolfApplicationSharedModule } from 'app/shared';
import {
  ApproachShotDistanceToPinComponent,
  ApproachShotDistanceToPinDetailComponent,
  ApproachShotDistanceToPinUpdateComponent,
  ApproachShotDistanceToPinDeletePopupComponent,
  ApproachShotDistanceToPinDeleteDialogComponent,
  approachShotDistanceToPinRoute,
  approachShotDistanceToPinPopupRoute
} from './';

const ENTITY_STATES = [...approachShotDistanceToPinRoute, ...approachShotDistanceToPinPopupRoute];

@NgModule({
  imports: [GolfApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ApproachShotDistanceToPinComponent,
    ApproachShotDistanceToPinDetailComponent,
    ApproachShotDistanceToPinUpdateComponent,
    ApproachShotDistanceToPinDeleteDialogComponent,
    ApproachShotDistanceToPinDeletePopupComponent
  ],
  entryComponents: [
    ApproachShotDistanceToPinComponent,
    ApproachShotDistanceToPinUpdateComponent,
    ApproachShotDistanceToPinDeleteDialogComponent,
    ApproachShotDistanceToPinDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GolfApplicationApproachShotDistanceToPinModule {}
