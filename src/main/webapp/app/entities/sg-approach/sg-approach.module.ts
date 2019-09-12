import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GolfApplicationSharedModule } from 'app/shared';
import {
  SGApproachComponent,
  SGApproachDetailComponent,
  SGApproachUpdateComponent,
  SGApproachDeletePopupComponent,
  SGApproachDeleteDialogComponent,
  sGApproachRoute,
  sGApproachPopupRoute
} from './';

const ENTITY_STATES = [...sGApproachRoute, ...sGApproachPopupRoute];

@NgModule({
  imports: [GolfApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SGApproachComponent,
    SGApproachDetailComponent,
    SGApproachUpdateComponent,
    SGApproachDeleteDialogComponent,
    SGApproachDeletePopupComponent
  ],
  entryComponents: [SGApproachComponent, SGApproachUpdateComponent, SGApproachDeleteDialogComponent, SGApproachDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GolfApplicationSGApproachModule {}
