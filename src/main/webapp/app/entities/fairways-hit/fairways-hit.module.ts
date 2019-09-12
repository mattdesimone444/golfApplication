import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GolfApplicationSharedModule } from 'app/shared';
import {
  FairwaysHitComponent,
  FairwaysHitDetailComponent,
  FairwaysHitUpdateComponent,
  FairwaysHitDeletePopupComponent,
  FairwaysHitDeleteDialogComponent,
  fairwaysHitRoute,
  fairwaysHitPopupRoute
} from './';

const ENTITY_STATES = [...fairwaysHitRoute, ...fairwaysHitPopupRoute];

@NgModule({
  imports: [GolfApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    FairwaysHitComponent,
    FairwaysHitDetailComponent,
    FairwaysHitUpdateComponent,
    FairwaysHitDeleteDialogComponent,
    FairwaysHitDeletePopupComponent
  ],
  entryComponents: [FairwaysHitComponent, FairwaysHitUpdateComponent, FairwaysHitDeleteDialogComponent, FairwaysHitDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GolfApplicationFairwaysHitModule {}
