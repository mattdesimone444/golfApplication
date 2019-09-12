import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GolfApplicationSharedModule } from 'app/shared';
import {
  SGPuttingComponent,
  SGPuttingDetailComponent,
  SGPuttingUpdateComponent,
  SGPuttingDeletePopupComponent,
  SGPuttingDeleteDialogComponent,
  sGPuttingRoute,
  sGPuttingPopupRoute
} from './';

const ENTITY_STATES = [...sGPuttingRoute, ...sGPuttingPopupRoute];

@NgModule({
  imports: [GolfApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SGPuttingComponent,
    SGPuttingDetailComponent,
    SGPuttingUpdateComponent,
    SGPuttingDeleteDialogComponent,
    SGPuttingDeletePopupComponent
  ],
  entryComponents: [SGPuttingComponent, SGPuttingUpdateComponent, SGPuttingDeleteDialogComponent, SGPuttingDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GolfApplicationSGPuttingModule {}
