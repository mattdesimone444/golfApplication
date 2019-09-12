import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GolfApplicationSharedModule } from 'app/shared';
import {
  SGTotalComponent,
  SGTotalDetailComponent,
  SGTotalUpdateComponent,
  SGTotalDeletePopupComponent,
  SGTotalDeleteDialogComponent,
  sGTotalRoute,
  sGTotalPopupRoute
} from './';

const ENTITY_STATES = [...sGTotalRoute, ...sGTotalPopupRoute];

@NgModule({
  imports: [GolfApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SGTotalComponent,
    SGTotalDetailComponent,
    SGTotalUpdateComponent,
    SGTotalDeleteDialogComponent,
    SGTotalDeletePopupComponent
  ],
  entryComponents: [SGTotalComponent, SGTotalUpdateComponent, SGTotalDeleteDialogComponent, SGTotalDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GolfApplicationSGTotalModule {}
